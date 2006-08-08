/* 
 * The OpenSAML License, Version 1. 
 * Copyright (c) 2002 
 * University Corporation for Advanced Internet Development, Inc. 
 * All rights reserved
 * 
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this 
 * list of conditions and the following disclaimer.
 * 
 * Redistributions in binary form must reproduce the above copyright notice, 
 * this list of conditions and the following disclaimer in the documentation 
 * and/or other materials provided with the distribution, if any, must include 
 * the following acknowledgment: "This product includes software developed by 
 * the University Corporation for Advanced Internet Development 
 * <http://www.ucaid.edu>Internet2 Project. Alternately, this acknowledegement 
 * may appear in the software itself, if and wherever such third-party 
 * acknowledgments normally appear.
 * 
 * Neither the name of OpenSAML nor the names of its contributors, nor 
 * Internet2, nor the University Corporation for Advanced Internet Development, 
 * Inc., nor UCAID may be used to endorse or promote products derived from this 
 * software without specific prior written permission. For written permission, 
 * please contact opensaml@opensaml.org
 * 
 * Products derived from this software may not be called OpenSAML, Internet2, 
 * UCAID, or the University Corporation for Advanced Internet Development, nor 
 * may OpenSAML appear in their name, without prior written permission of the 
 * University Corporation for Advanced Internet Development.
 * 
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND WITH ALL FAULTS. ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE, AND NON-INFRINGEMENT ARE DISCLAIMED AND THE ENTIRE RISK 
 * OF SATISFACTORY QUALITY, PERFORMANCE, ACCURACY, AND EFFORT IS WITH LICENSEE. 
 * IN NO EVENT SHALL THE COPYRIGHT OWNER, CONTRIBUTORS OR THE UNIVERSITY 
 * CORPORATION FOR ADVANCED INTERNET DEVELOPMENT, INC. BE LIABLE FOR ANY DIRECT, 
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND 
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 
package org.opensaml;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;
import org.w3c.dom.*;

/**
 *  Represents a SAML Authentication Statement
 *
 * @author     Scott Cantor
 * @created    March 25, 2002
 */
public class SAMLAuthenticationStatement extends SAMLSubjectStatement implements Cloneable
{
    protected String subjectIP = null;
    protected String subjectDNS = null;
    protected String authMethod = null;
    protected Date authInstant = null;
    protected ArrayList bindings = new ArrayList();
    
    /** The authentication was performed by means of a password. */
    public static final String AuthenticationMethod_Password = "urn:oasis:names:tc:SAML:1.0:am:password";
	
	/** The authentication was performed by means of the Kerberos protocol [RFC 1510], 
	 * an instantiation of the Needham-Schroeder symmetric key authentication mechanism [Needham78]. */
	public static final String AuthenticationMethod_Kerberos = "urn:ietf:rfc:1510"; 
	
	/** The authentication was performed by means of Secure Remote Password protocol as specified in [RFC 2945]. */
	public static final String AuthenticationMethod_SRP = "urn:ietf:rfc:2945";
	
	/** The authentication was performed by means of an unspecified hardware token. */
	public static final String AuthenticationMethod_HardwareToken = "urn:oasis:names:tc:SAML:1.0:am:HardwareToken";
	
	/** The authentication was performed using either the SSL or TLS protocol with certificate based client 
	 * authentication. TLS is described in [RFC 2246]. */
	public static final String AuthenticationMethod_SSL_TLS_Client = "urn:ietf:rfc:2246";

	/** The authentication was performed by some (unspecified) mechanism on a key authenticated by means of an 
	 * X.509 PKI [X.500][PKIX]. It may have been one of the mechanisms for which a more specific identifier 
	 * has been defined. */
	public static final String AuthenticationMethod_X509_PublicKey = "urn:oasis:names:tc:SAML:1.0:am:X509-PKI";
	
	/** The authentication was performed by some (unspecified) mechanism on a key authenticated by means of 
	 * a PGP web of trust [PGP]. It may have been one of the mechanisms for which a more specific identifier 
	 * has been defined. */
	public static final String AuthenticationMethod_PGP_PublicKey = "urn:oasis:names:tc:SAML:1.0:am:PGP";
	
	/** The authentication was performed by some (unspecified) mechanism on a key authenticated by means of a 
	 * SPKI PKI [SPKI]. It may have been one of the mechanisms for which a more specific identifier has been 
	 * defined. */
	public static final String AuthenticationMethod_SPKI_PublicKey = "urn:oasis:names:tc:SAML:1.0:am:SPKI";
	
	/** The authentication was performed by some (unspecified) mechanism on a key authenticated by means of a 
	 * XKMS trust service [XKMS]. It may have been one of the mechanisms for which a more specific identifier 
	 * has been defined. */
	public static final String AuthenticationMethod_XKMS_PublicKey = "urn:oasis:names:tc:SAML:1.0:am:XKMS";
	
	/** The authentication was performed by means of an XML digital signature [RFC 3075]. */
	public static final String AuthenticationMethod_XML_DSig = "urn:ietf:rfc:3075";
	
	/** The authentication was performed by an unspecified means. */
	public static final String AuthenticationMethod_Unspecified = "urn:oasis:names:tc:SAML:1.0:am:unspecified";
	
    /**
     *  Default constructor
     */
    public SAMLAuthenticationStatement() {
    }

    /**
     *  Builds a statement out of its component parts
     *
     * @param  subject            Subject of statement
     * @param  authMethod         URI of authentication method
     * @param  authInstant        Datetime of authentication
     * @param  subjectIP          IP address of subject in dotted decimal
     *      notation (optional)
     * @param  subjectDNS         DNS address of subject (optional)
     * @param  bindings           Collection of SAMLAuthorityBinding objects to
     *      reference SAML responders (optional)
     * @exception  SAMLException  Raised if a statement cannot be constructed
     *      from the supplied information
     */
    public SAMLAuthenticationStatement(SAMLSubject subject, String authMethod, Date authInstant, String subjectIP,
                                        String subjectDNS, Collection bindings) throws SAMLException {
        super(subject);
        
        this.subjectIP = subjectIP;
        this.subjectDNS = subjectDNS;
        this.authMethod = authMethod;
        this.authInstant = authInstant;
        if (bindings != null)
            this.bindings.addAll(bindings);
    }
    
	/**
	 *  Builds a statement out of its component parts
	 *
	 * @param  subject            Subject of statement
	 * @param  authInstant        Datetime of authentication
	 * @param  subjectIP          IP address of subject in dotted decimal
	 *      notation (optional)
	 * @param  subjectDNS         DNS address of subject (optional)
	 * @param  bindings           Collection of SAMLAuthorityBinding objects to
	 *      reference SAML responders (optional)
	 * @exception  SAMLException  Raised if a statement cannot be constructed
	 *      from the supplied information
	 */
	public SAMLAuthenticationStatement(SAMLSubject subject, Date authInstant, String subjectIP,
										String subjectDNS, Collection bindings) throws SAMLException {
		this(subject,
			SAMLAuthenticationStatement.AuthenticationMethod_Unspecified, 
			authInstant,
			subjectIP,
			subjectDNS,
			bindings);
	}

    /**
     *  Reconstructs a statement from a DOM tree
     *
     * @param  e                  The root of a DOM tree
     * @exception  SAMLException  Thrown if the object cannot be constructed
     */
    public SAMLAuthenticationStatement(Element e) throws SAMLException {
        fromDOM(e);
    }

    /**
     *  Reconstructs a statement from a stream
     *
     * @param  in                   A stream containing XML
     * @exception  SAMLException  Raised if an exception occurs while constructing
     *                              the object.
     */
    public SAMLAuthenticationStatement(InputStream in) throws SAMLException {
        fromDOM(fromStream(in));
    }
    
    /**
     * @see org.opensaml.SAMLObject#fromDOM(org.w3c.dom.Element)
     */
    public void fromDOM(Element e) throws SAMLException {
        super.fromDOM(e);

        if (config.getBooleanProperty("org.opensaml.strict-dom-checking") && !XML.isElementNamed(e,XML.SAML_NS,"AuthenticationStatement"))
        {
            QName q=QName.getQNameAttribute(e,XML.XSI_NS,"type");
            if (!XML.isElementNamed(e,XML.SAML_NS,"Statement") || q==null ||
                !XML.SAML_NS.equals(q.getNamespaceURI()) || !"AuthenticationStatementType".equals(q.getLocalName()))
                throw new MalformedException(SAMLException.RESPONDER, "SAMLAuthenticationStatement() requires saml:AuthenticationStatement at root");
        }

        authMethod = e.getAttributeNS(null,"AuthenticationMethod");

        try
        {
            SimpleDateFormat formatter = null;
            String dateTime = e.getAttributeNS(null, "AuthenticationInstant");
            int dot = dateTime.indexOf('.');
            if (dot > 0) {
                formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            }
            else {
                formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            }
            formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
            authInstant = formatter.parse(dateTime);
        }
        catch (java.text.ParseException ex)
        {
            throw new MalformedException(SAMLException.RESPONDER, "SAMLAuthenticationStatement() detected an invalid datetime while parsing statement", ex);
        }

        // Check for locality
        Element n = XML.getFirstChildElement(root, XML.SAML_NS, "SubjectLocality");
        if (n != null) {
            subjectIP = n.getAttributeNS(null, "IPAddress");
            subjectDNS = n.getAttributeNS(null, "DNSAddress");
            n = XML.getNextSiblingElement(n);
        }

        // Extract bindings.
        n = XML.getFirstChildElement(root, XML.SAML_NS, "AuthorityBinding");
        while (n != null) {
            bindings.add(new SAMLAuthorityBinding(n));
            n = XML.getNextSiblingElement(n, XML.SAML_NS, "AuthorityBinding");
        }
        
        checkValidity();
    }

    /**
     *  Gets the subject's IP address
     *
     * @return    The subject's IP address in dotted decimal notation
     */
    public String getSubjectIP() {
        return subjectIP;
    }

    /**
     *  Sets the subject's IP address
     * 
     * @param   subjectIP   The subject's IP address
     */
    public void setSubjectIP(String subjectIP) {
        this.subjectIP = subjectIP;
        if (root != null) {
            Element loc = XML.getFirstChildElement(root, XML.SAML_NS, "SubjectLocality");
            if (loc != null) {
                loc.removeAttributeNS(null, "IPAddress");
                if (!XML.isEmpty(subjectIP))
                    loc.setAttributeNS(null, "IPAddress", subjectIP);
                else if (!loc.hasAttributes())
                    root.removeChild(loc);
            }
            else if (!XML.isEmpty(subjectIP)) {
                loc = root.getOwnerDocument().createElementNS(XML.SAML_NS, "SubjectLocality");
                loc.setAttributeNS(null, "IPAddress", subjectIP);
                root.insertBefore(loc, subject.root.getNextSibling());
            }
        }
    }

    /**
     *  Gets the subject's DNS address
     *
     * @return    The subject's DNS address
     */
    public String getSubjectDNS() {
        return subjectDNS;
    }

    /**
     *  Sets the subject's DNS address
     * 
     * @param   subjectDNS  The subject's DNS address
     */
    public void setSubjectDNS(String subjectDNS) {
        this.subjectDNS = subjectDNS;
        if (root != null) {
            Element loc = XML.getFirstChildElement(root, XML.SAML_NS, "SubjectLocality");
            if (loc != null) {
                loc.removeAttributeNS(null, "DNSAddress");
                if (!XML.isEmpty(subjectDNS))
                    loc.setAttributeNS(null, "DNSAddress", subjectDNS);
                else if (!loc.hasAttributes())
                    root.removeChild(loc);
            }
            else if (!XML.isEmpty(subjectDNS)) {
                loc = root.getOwnerDocument().createElementNS(XML.SAML_NS, "SubjectLocality");
                loc.setAttributeNS(null, "DNSAddress", subjectDNS);
                root.insertBefore(loc, subject.root.getNextSibling());
            }
        }
    }

    /**
     *  Gets the authentication method
     *
     * @return    The authentication method URI
     */
    public String getAuthMethod() {
        return authMethod;
    }

    /**
     *  Sets the authentication method
     * 
     * @param   authMethod    The authentication method URI
     */
    public void setAuthMethod(String authMethod) {
        if (XML.isEmpty(authMethod))
            throw new IllegalArgumentException("authMethod cannot be null");
        this.authMethod = authMethod;
        if (root != null) {
            ((Element)root).getAttributeNodeNS(null,"AuthenticationMethod").setNodeValue(authMethod);
        }
    }

    /**
     *  Gets the datetime of authentication
     *
     * @return    The date and time of authentication
     */
    public Date getAuthInstant() {
        return authInstant;
    }

    /**
     *  Sets the datetime of authentication
     *
     * @param   authInstant    The date and time of authentication
     */
    public void setAuthInstant(Date authInstant) {
        if (authInstant == null)
            throw new IllegalArgumentException("authInstant cannot be null");
        if (root != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
            ((Element)root).getAttributeNodeNS(null, "AuthenticationInstant").setNodeValue(formatter.format(authInstant));
        }
        this.authInstant = authInstant;
    }

    /**
     *  Gets SAML authority binding information
     *
     * @return    An iterator of bindings
     */
    public Iterator getBindings() {
        return bindings.iterator();
    }

    /**
     *  Sets SAML authority binding information
     * 
     * @param bindings    The bindings to include
     * @throws SAMLException    Raised if any of the bindings are invalid
     */
    public void setBindings(Collection bindings) throws SAMLException {
        while (this.bindings.size() > 0) {
            removeBinding(0);
        }
        
        if (bindings != null) {
            for (Iterator i = bindings.iterator(); i.hasNext(); )
                addBinding((SAMLAuthorityBinding)i.next());
        }
    }

    /**
     *  Adds SAML authority binding information
     * 
     * @param binding    The binding to add
     * @exception SAMLException     Raised if the binding is invalid
     */
    public void addBinding(SAMLAuthorityBinding binding) throws SAMLException {
        if (binding != null) {
            if (root != null) {
                Node b = binding.toDOM(root.getOwnerDocument());
                
                //This is defensive code in case somebody extends this type and adds new elements.
                Element last = XML.getLastChildElement(root, XML.SAML_NS, "AuthorityBinding");
                if (last == null) {
                    Element loc = XML.getFirstChildElement(root, XML.SAML_NS, "SubjectLocality");
                    if (loc == null) {
                        // Stick it after the Subject.
                        root.insertBefore(b, subject.root.getNextSibling());
                    }
                    else {
                        // Stick it after SubjectLocality.
                        root.insertBefore(b, loc.getNextSibling());
                    }
                }
                else {
                    // Stick it after the last current binding.
                    root.insertBefore(b, last.getNextSibling());
                }
            }
            bindings.add(binding);
        }
        else
            throw new IllegalArgumentException("binding cannot be null");
    }
    
    /**
     *  Removes a binding by position (zero-based)
     * 
     * @param   index   The position of the binding to remove
     */
    public void removeBinding(int index) {
        bindings.remove(index);
        if (root != null) {
            Element e = XML.getFirstChildElement(root, XML.SAML_NS, "AuthorityBinding");
            while (e != null && index > 0) {
                e = XML.getNextSiblingElement(e);
                index--;
            }
            if (e != null)
                root.removeChild(e);
            else
                throw new IndexOutOfBoundsException();
        }
    }

    /**
     *  @see org.opensaml.SAMLObject#toDOM(org.w3c.dom.Document,boolean)
     */
    public Node toDOM(Document doc, boolean xmlns) throws SAMLException {
		if ((root = super.toDOM(doc, xmlns)) != null) {
			if (xmlns)
				((Element)root).setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAML_NS);
			return root;
        }

        // This is the meat. Build a new XML instance using our state and the specified Document.
        Element statement = doc.createElementNS(XML.SAML_NS, "AuthenticationStatement");
		if (xmlns)
			statement.setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAML_NS);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        statement.setAttributeNS(null, "AuthenticationInstant", formatter.format(authInstant));
        statement.setAttributeNS(null, "AuthenticationMethod", authMethod);

        statement.appendChild(subject.toDOM(doc, false));

        if (!XML.isEmpty(subjectIP) || !XML.isEmpty(subjectDNS)) {
            Element locality = doc.createElementNS(XML.SAML_NS, "SubjectLocality");
            if (!XML.isEmpty(subjectIP))
                locality.setAttributeNS(null,"IPAddress", subjectIP);
            if (!XML.isEmpty(subjectDNS))
                locality.setAttributeNS(null,"DNSAddress", subjectDNS);
            statement.appendChild(locality);
        }

        for (Iterator i=bindings.iterator(); i.hasNext(); )
            statement.appendChild(((SAMLAuthorityBinding)i.next()).toDOM(doc, false));

        return root = statement;
    }

    /**
     * @see org.opensaml.SAMLObject#checkValidity()
     */
    public void checkValidity() throws SAMLException {
        if (XML.isEmpty(authMethod) || authInstant == null)
            throw new MalformedException(SAMLException.RESPONDER, "AuthenticationStatement is invalid, requires AuthenticationMethod and AuthenticationInstant");
    }

    /**
     *  Copies a SAML object such that no dependencies exist between the original
     *  and the copy
     * 
     * @return      The new object
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
        SAMLAuthenticationStatement dup=(SAMLAuthenticationStatement)super.clone();
        
        // Clone the embedded objects.
        for (Iterator i = bindings.iterator(); i.hasNext(); )
            dup.bindings.add(((SAMLAuthorityBinding)i.next()).clone());
        
        return dup;
    }
}

