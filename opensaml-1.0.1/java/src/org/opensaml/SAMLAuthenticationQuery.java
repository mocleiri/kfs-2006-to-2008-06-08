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

import org.w3c.dom.*;

/**
 *  Represents a SAML Authentication Query object
 *
 *      Scott Cantor
 * @created    March 25, 2002
 */
public class SAMLAuthenticationQuery extends SAMLSubjectQuery implements Cloneable
{
    protected String authMethod = null;

    /**
     *  Default constructor
     */
    public SAMLAuthenticationQuery() {
    }

    /**
     *  Builds an authentication query out of its component parts
     *
     * @param  subject            Subject of query
     * @param  authMethod         Authentication method in query
     * @exception  SAMLException  Raised if the query cannot be constructed from
     *      the supplied information
     */
    public SAMLAuthenticationQuery(SAMLSubject subject, String authMethod) throws SAMLException {
        super(subject);
        this.authMethod = authMethod;
    }

    /**
     *  Reconstructs an authentication query from a DOM tree
     *
     * @param  e                  The root of a DOM tree
     * @exception  SAMLException  Thrown if the object cannot be constructed
     */
    public SAMLAuthenticationQuery(Element e) throws SAMLException {
        fromDOM(e);
    }

    /**
     *  Reconstructs an authentication query from a stream
     *
     * @param  in                   A stream containing XML
     * @exception  SAMLException  Raised if an exception occurs while constructing
     *                              the object.
     */
    public SAMLAuthenticationQuery(InputStream in) throws SAMLException {
        fromDOM(fromStream(in));
    }

    /**
     * @see org.opensaml.SAMLObject#fromDOM(org.w3c.dom.Element)
     */
    public void fromDOM(Element e) throws SAMLException {
        super.fromDOM(e);
        
        if (config.getBooleanProperty("org.opensaml.strict-dom-checking") && !XML.isElementNamed(e,XML.SAMLP_NS,"AuthenticationQuery"))
        {
            QName q = QName.getQNameAttribute(e, XML.XSI_NS, "type");
            if (!XML.isElementNamed(e,XML.SAMLP_NS,"Query") || !XML.isElementNamed(e,XML.SAMLP_NS,"SubjectQuery") ||
                q == null || !XML.SAMLP_NS.equals(q.getNamespaceURI()) || !"AuthenticationQueryType".equals(q.getLocalName()))
                throw new MalformedException(SAMLException.REQUESTER, "SAMLAuthenticationQuery.fromDOM() requires samlp:AuthenticationQuery at root");
        }

        authMethod = e.getAttributeNS(null, "AuthenticationMethod");
        checkValidity();
    }

    /**
     *  Gets the authentication method inside the query
     *
     * @return    The authentication method URI
     */
    public String getAuthMethod() {
        return authMethod;
    }

    /**
     *  Sets the authentication method inside the query
     * 
     * @param   authMethod    The authentication method URI
     */
    public void setAuthMethod(String authMethod) {
        if (XML.isEmpty(authMethod))
            throw new IllegalArgumentException("authMethod cannot be null or empty");
        this.authMethod = authMethod;
        if (root != null) {
            ((Element)root).getAttributeNodeNS(null,"AuthenticationMethod").setNodeValue(authMethod);
        }
    }

    /**
     *  @see org.opensaml.SAMLObject#toDOM(org.w3c.dom.Document,boolean)
     */
    public Node toDOM(Document doc, boolean xmlns) throws SAMLException {
		if ((root = super.toDOM(doc, xmlns)) != null) {
			if (xmlns)
				((Element)root).setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAMLP_NS);
			return root;
        }

        Element q = doc.createElementNS(XML.SAMLP_NS, "AuthenticationQuery");
		if (xmlns)
			q.setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAMLP_NS);
        if (!XML.isEmpty(authMethod))
        	q.setAttributeNS(null, "AuthenticationMethod", authMethod);
        q.appendChild(subject.toDOM(doc));
        
        return root = q;
    }

    /**
     *  Copies a SAML object such that no dependencies exist between the original
     *  and the copy
     * 
     * @return      The new object
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

