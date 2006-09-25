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

/**
 *  Abstract base for SAML binding implementations
 *
 *      Scott Cantor
 * @created    November 25, 2001
 */
public interface SAMLBinding
{
    /**  SAML SOAP over HTTPS binding protocol */
    public final static String SAML_SOAP_HTTPS = "urn:oasis:names:tc:SAML:1.0:bindings:SOAP-binding";

    /**
     *  Used by requester to send a SAML request to an authority, and obtain a
     *  response in return
     *
     * @param  bindingInfo        Defines the binding protocol and the authority
     *      to contact
     * @param  request            SAML request to send
     * @return                    SAML response received from authority
     * @exception  SAMLException  Base class of exceptions that may be thrown
     *      during processing
     */
    public abstract SAMLResponse send(SAMLAuthorityBinding bindingInfo, SAMLRequest request)
        throws SAMLException;

    /**
     *  Used by responder to process the receipt of a SAML request
     *
     * @param  reqContext         A generic placeholder for binding-specific
     *      request context
     * @param  requester          The authenticated name of the requester, if
     *      possible to determine from the binding
     * @return                    A SAML request
     * @exception  SAMLException  Base class of exceptions that may be thrown
     *      during processing
     * @deprecated
     */
    public abstract SAMLRequest receive(Object reqContext, StringBuffer requester)
        throws SAMLException;
    /**
     *  Used by responder to process the receipt of a SAML request
     *
     * @param  reqContext         A generic placeholder for binding-specific
     *      request context
     * @return                    A SAML request
     * @exception  SAMLException  Base class of exceptions that may be thrown
     *      during processing
     */
    public abstract SAMLRequest receive(Object reqContext)
        throws SAMLException;

    /**
     *  Return a response to a requester
     *
     * @param  respContext              A generic placeholder for
     *      binding-specific response context
     * @param  response                 The SAML response to return (optional)
     * @param  e                        An exception to translate into a binding
     *      fault (optional)
     * @exception  java.io.IOException  Thrown if sending of data fails
     */
    public abstract void respond(Object respContext, SAMLResponse response, SAMLException e)
        throws java.io.IOException;
}

