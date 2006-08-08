/*
    The OpenSAML License, Version 1.
    Copyright (c) 2002
    University Corporation for Advanced Internet Development, Inc.
    All rights reserved
    Redistribution and use in source and binary forms, with or without
    modification, are permitted provided that the following conditions are met:
    Redistributions of source code must retain the above copyright notice, this
    list of conditions and the following disclaimer.
    Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution, if any, must include
    the following acknowledgment: "This product includes software developed by
    the University Corporation for Advanced Internet Development
    <http://www.ucaid.edu>Internet2 Project. Alternately, this acknowledegement
    may appear in the software itself, if and wherever such third-party
    acknowledgments normally appear.
    Neither the name of OpenSAML nor the names of its contributors, nor
    Internet2, nor the University Corporation for Advanced Internet Development,
    Inc., nor UCAID may be used to endorse or promote products derived from this
    software without specific prior written permission. For written permission,
    please contact opensaml@opensaml.org
    Products derived from this software may not be called OpenSAML, Internet2,
    UCAID, or the University Corporation for Advanced Internet Development, nor
    may OpenSAML appear in their name, without prior written permission of the
    University Corporation for Advanced Internet Development.
    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
    AND WITH ALL FAULTS. ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
    LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
    PARTICULAR PURPOSE, AND NON-INFRINGEMENT ARE DISCLAIMED AND THE ENTIRE RISK
    OF SATISFACTORY QUALITY, PERFORMANCE, ACCURACY, AND EFFORT IS WITH LICENSEE.
    IN NO EVENT SHALL THE COPYRIGHT OWNER, CONTRIBUTORS OR THE UNIVERSITY
    CORPORATION FOR ADVANCED INTERNET DEVELOPMENT, INC. BE LIABLE FOR ANY DIRECT,
    INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
    (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
    LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
    ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
    (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
    SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
  */
package org.opensaml;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.xml.security.exceptions.Base64DecodingException;
import org.apache.xml.security.utils.Base64;


/**
 *  Basic implementation of SAML POST browser profile
 *
 * @author     Scott Cantor
 * @created    April 1, 2002
 */
public class SAMLPOSTProfile
{
	private static Logger log = Logger.getLogger(SAMLPOSTProfile.class.getName());
    private static TreeMap replayExpMap = new TreeMap();
    private static HashSet replayCache = new HashSet();

    /**
     *  Locates an assertion containing a "bearer" AuthenticationStatement in
     *  the response and validates the enclosing assertion with respect to the
     *  POST profile
     *
     * @param  r          The response to the accepting site
     * @param  audiences  The set of audience values to test any conditions
     *      against
     * @return            An SSO assertion
     * 
     * @throws SAMLException    Thrown if a valid SSO assertion cannot be found
     */
    public static SAMLAssertion getSSOAssertion(SAMLResponse r, Collection audiences)
        throws SAMLException
    {
        int acount = 0;
        boolean bExpired = false;
        
        Iterator assertions = r.getAssertions();
        assertion_loop :
        while (assertions.hasNext())
        {
            acount++;
            bExpired = false;
            SAMLAssertion a=(SAMLAssertion)assertions.next();
            
            // A SSO assertion must be bounded front and back.
            Date notBefore = a.getNotBefore();
            Date notOnOrAfter = a.getNotOnOrAfter();
            if (notBefore == null || notOnOrAfter == null)
                continue;

            if (notBefore.getTime() - 300000 > System.currentTimeMillis())
            {
                bExpired = true;
                continue;
            }
            
            if (notOnOrAfter.getTime() + 300000 <= System.currentTimeMillis())
            {
                bExpired = true;
                continue;
            }

            // Check conditions. The only type we know about is an audience restriction.
            Iterator conditions = a.getConditions();
            while (conditions.hasNext())
            {
                SAMLCondition c=(SAMLCondition)conditions.next();
                if (!(c instanceof SAMLAudienceRestrictionCondition) ||
                    !((SAMLAudienceRestrictionCondition)c).eval(audiences))
                    continue assertion_loop;
            }
            
            // Look for an authentication statement.
            Iterator statements = a.getStatements();
            while (statements.hasNext())
            {
                SAMLStatement s=(SAMLStatement)statements.next();
                if (!(s instanceof SAMLAuthenticationStatement))
                    continue;
                    
                SAMLSubject subject=((SAMLAuthenticationStatement)s).getSubject();
                Iterator methods=subject.getConfirmationMethods();
                while (methods.hasNext())
                    if (((String)methods.next()).equals(SAMLSubject.CONF_BEARER))
                        return a;
            }
        }
        if (bExpired == true && acount == 1)
            throw new ExpiredAssertionException(SAMLException.RESPONDER,"SAMLPOSTProfile.getSSOAssertion() unable to find a SSO assertion with valid time condition");
    
        throw new FatalProfileException(SAMLException.RESPONDER,"SAMLPOSTProfile.getSSOAssertion() unable to find a valid SSO assertion");
    }

    /**
     *  Locates a "bearer" AuthenticationStatement in the assertion and
     *  validates the statement with respect to the POST profile
     *
     * @param  a  The SSO assertion sent to the accepting site
     * @return    A "bearer" authentication statement
     * 
     * @throws SAMLException    Thrown if a SSO statement cannot be found
     */
    public static SAMLAuthenticationStatement getSSOStatement(SAMLAssertion a)
        throws SAMLException
    {
        // Look for an authentication statement.
        Iterator statements = a.getStatements();
        while (statements.hasNext())
        {
            SAMLStatement s=(SAMLStatement)statements.next();
            if (!(s instanceof SAMLAuthenticationStatement))
                continue;
                
            SAMLSubject subject=((SAMLAuthenticationStatement)s).getSubject();
            Iterator methods=subject.getConfirmationMethods();
            while (methods.hasNext())
                if (((String)methods.next()).equals(SAMLSubject.CONF_BEARER))
                    return (SAMLAuthenticationStatement)s;
        }
    
        throw new FatalProfileException(SAMLException.RESPONDER,"SAMLPOSTProfile.getSSOStatement() unable to find a valid SSO statement");
    }

    /**
     *  Searches the replay cache for the specified assertion and inserts a
     *  newly seen assertion into the cache<P>
     *
     *  Also performs garbage collection of the cache by deleting expired
     *  entries.
     *
     * @param  a  The assertion to look up and possibly add
     * @return    true iff the assertion has not been seen before
     */
    public static synchronized boolean checkReplayCache(SAMLAssertion a)
    {
        // Garbage collect any expired entries.
        Set trash = replayExpMap.headMap(new Date()).keySet();
        for (Iterator i = trash.iterator(); i.hasNext(); replayCache.remove(replayExpMap.get(i.next())))
            ;
        trash.clear();

        // If it's already been seen, bail.
        if (!replayCache.add(a.getId()))
            return false;

        // Not a multi-map, so if there's duplicate timestamp, increment by a millisecond.
        Date expires = new Date(a.getNotOnOrAfter().getTime() + 300000);
        while (replayExpMap.containsKey(expires))
            expires.setTime(expires.getTime() + 1);

        // Add the pair to the expiration map.
        replayExpMap.put(expires, a.getId());
        return true;
    }

    /**
     *  Parse a Base-64 encoded buffer back into a SAML response and optionally test its
     *  validity against the POST profile<P>
     *
     *  The signature over the response is not verified or examined, nor is the
     *  identity of the signer. The replay cache is also not checked.
     *
     * @param  buf                A Base-64 encoded buffer containing a SAML
     *      response
     * @param  receiver           The URL of the intended consumer of the
     *      response
     * @param  ttlSeconds         Seconds allowed to lapse from the issuance of
     *      the response
     * @param  process            Process the response or just decode and parse it?
     * @return                    SAML response sent by origin site
     * @exception  SAMLException  Thrown if the response is invalid
     */
    public static SAMLResponse accept(byte[] buf, String receiver, int ttlSeconds, boolean process)
        throws SAMLException
    {
    	
        try
        {
            SAMLResponse r = new SAMLResponse(new ByteArrayInputStream(Base64.decode(buf)));
            if (process)
                process(r, receiver, ttlSeconds);
            return r;
        }
        catch (Base64DecodingException e)
        {
            throw new InvalidAssertionException(SAMLException.REQUESTER, "SAMLPOSTProfile.accept() unable to decode base64 response");
        }
    }

    /**
     *  Test the validity of a response against the POST profile<P>
     *
     *  The signature over the response is not verified or examined, nor is the
     *  identity of the signer. The replay cache is also not checked.
     *
     * @param  r                  The response to process 
     * @param  receiver           The URL of the intended consumer of the
     *      response
     * @param  ttlSeconds         Seconds allowed to lapse from the issuance of
     *      the response
     * @return                    SAML response sent by origin site
     * @exception  SAMLException  Thrown if the response is invalid
     */
    public static void process(SAMLResponse r, String receiver, int ttlSeconds)
        throws SAMLException
    {
        if (receiver == null || receiver.length() == 0 || !receiver.equals(r.getRecipient()))
            throw new InvalidAssertionException(SAMLException.REQUESTER, "SAMLPOSTProfile.accept() detected recipient mismatch: " + r.getRecipient());
        if (r.getIssueInstant().getTime() + (1000 * ttlSeconds) + 300000 < System.currentTimeMillis())
            throw new ExpiredAssertionException(SAMLException.RESPONDER, "SAMLPOSTProfile.accept() detected expired response");
    }
    
	/**
	 *  Used by authenticating site to generate a SAML response conforming to
	 *  the POST profile<P>
	 *
	 *  The response MUST be signed by the caller before sending to relying
	 *  site.<P>
	 *
	 *  Implementations that need to embed additional statements or more complex
	 *  conditions can override or ignore this class.
	 *
	 * @param  recipient          URL of intended consumer
	 * @param  issuer             Issuer of assertion
	 * @param  audiences          URIs identifying intended relying
	 *      parties/communities (optional)
	 * @param  name               Name of subject
	 * @param  nameQualifier      Federates or qualifies subject name (optional)
	 * @param  format             URI describing name semantics and format
	 *      (optional)
	 * @param  subjectIP          Client address of subject (optional)
	 * @param  authMethod         URI of authentication method being asserted
	 * @param  authInstant        Date and time of authentication being asserted
	 * @param  bindings           Set of SAML authorities the relying party
	 *      may contact (optional)
	 * @return                    SAML response to send to accepting site
	 * @exception  SAMLException  Base class of exceptions that may be thrown
	 *      during processing
	 * @deprecated 				Callers should prefer the overloaded method
	 * 		that accepts <code>SAMLNameIdentifier</code> objects
	 */
	 public static SAMLResponse prepare(
		String recipient,
		String issuer,
		Collection audiences,
		String name,
		String nameQualifier,
		String format,
		String subjectIP,
		String authMethod,
		Date authInstant,
		Collection bindings)
		throws SAMLException {

		return prepare(
			recipient,
			issuer,
			audiences,
			new SAMLNameIdentifier(name, nameQualifier, format),
			subjectIP,
			authMethod,
			authInstant,
			bindings);

	}
	/**
	 *  Used by authenticating site to generate a SAML response conforming to
	 *  the POST profile<P>
	 *
	 *  The response MUST be signed by the caller before sending to relying
	 *  site.<P>
	 *
	 *  Implementations that need to embed additional statements or more complex
	 *  conditions can override or ignore this class.
	 *
	 * @param  recipient          URL of intended consumer
	 * @param  issuer             Issuer of assertion
	 * @param  audiences          URIs identifying intended relying
	 *      parties/communities (optional)
	 * @param  nameId			  Name Identifier representing the subject
	 * @param  subjectIP          Client address of subject (optional)
	 * @param  authMethod         URI of authentication method being asserted
	 * @param  authInstant        Date and time of authentication being asserted
	 * @param  bindings           Set of SAML authorities the relying party
	 *      may contact (optional)
	 * @return                    SAML response to send to accepting site
	 * @exception  SAMLException  Base class of exceptions that may be thrown
	 *      during processing
	 */
	public static SAMLResponse prepare(String recipient,
										String issuer,
										Collection audiences,
										SAMLNameIdentifier nameId,
										String subjectIP,
										String authMethod,
										Date authInstant,
										Collection bindings)
		throws SAMLException
	{
		log.info("Creating SAML Response.");
    	
		if (recipient == null || recipient.length() == 0)
			throw new SAMLException(SAMLException.RESPONDER, "SAMLPOSTProfile.prepare() requires recipient");

		Vector conditions = new Vector(1);
		if (audiences != null && audiences.size() > 0)
			conditions.add(new SAMLAudienceRestrictionCondition(audiences));

		String[] confirmationMethods = {SAMLSubject.CONF_BEARER};
		SAMLSubject subject = new SAMLSubject(nameId, Arrays.asList(confirmationMethods), null, null);
		SAMLStatement[] statements =
			{new SAMLAuthenticationStatement(subject, authMethod, authInstant, subjectIP, null, bindings)};
		SAMLAssertion[] assertions = {
			new SAMLAssertion(issuer, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 300000),
								conditions, null, Arrays.asList(statements))
			};

		return new SAMLResponse(null, recipient, Arrays.asList(assertions), null);
	}
}

