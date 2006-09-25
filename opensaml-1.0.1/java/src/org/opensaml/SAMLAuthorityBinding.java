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
 *  Wraps a SAML Authority Binding
 *
 *      Scott Cantor
 * @created    March 25, 2002
 */
public class SAMLAuthorityBinding extends SAMLObject implements Cloneable
{
    protected String binding = null;
    protected String location = null;
    protected QName authorityKind = null;

    /**
     *  Default constructor
     */
    public SAMLAuthorityBinding() {
    }

    /**
     *  Constructor builds a SAML AuthorityBinding out of its component parts
     *
     * @param  binding            The SAML binding protocol to use
     * @param  location           The AA address (URI, format dependent on the protocol)
     * @param  authorityKind      The QName of the Query element that the authority knows
     *      how to process
     * @exception  SAMLException  Thrown if any parameters are invalid
     */
    public SAMLAuthorityBinding(String binding, String location, QName authorityKind) throws SAMLException {
        this.binding = binding;
        this.location = location;
        this.authorityKind = authorityKind;
    }

    /**
     *  Reconstructs a binding from a DOM tree
     *
     * @param  e                  The root of a DOM tree
     * @exception  SAMLException  Thrown if the object cannot be constructed
     */
    public SAMLAuthorityBinding(Element e) throws SAMLException {
        fromDOM(e);
    }

    /**
     *  Reconstructs a binding from a stream
     *
     * @param  in                   A stream containing XML
     * @exception  SAMLException  Raised if an exception occurs while constructing
     *                              the object.
     */
    public SAMLAuthorityBinding(InputStream in) throws SAMLException {
        fromDOM(fromStream(in));
    }
    
    /**
     * @see org.opensaml.SAMLObject#fromDOM(org.w3c.dom.Element)
     */
    public void fromDOM(Element e) throws SAMLException {
        super.fromDOM(e);
        
        if (config.getBooleanProperty("org.opensaml.strict-dom-checking") && !XML.isElementNamed(e,XML.SAML_NS,"AuthorityBinding"))
            throw new MalformedException(SAMLException.RESPONDER, "SAMLAuthorityBinding() requires saml:AuthorityBinding at root");

        binding = e.getAttributeNS(null,"Binding");
        location = e.getAttributeNS(null,"Location");
        authorityKind = QName.getQNameAttribute(e,null,"AuthorityKind");
        
        checkValidity();
    }

    /**
     *  Gets the protocol binding attribute of the authority binding
     *
     * @return    The binding protocol value
     */
    public String getBinding() {
        return binding;
    }

    /**
     *  Sets the protocol binding attribute of the authority binding
     * 
     * @param   binding    The binding protocol value
     */
    public void setBinding(String binding) {
        if (XML.isEmpty(binding))
            throw new IllegalArgumentException("binding cannot be null or empty");
        this.binding = binding;
        if (root != null) {
            ((Element)root).getAttributeNodeNS(null,"Binding").setNodeValue(binding);
        }
    }

    /**
     *  Gets the location attribute of the authority binding
     *
     * @return    The location value
     */
    public String getLocation() {
        return location;
    }

    /**
     *  Sets the location attribute of the authority binding
     * 
     * @param   location    The location value
     */
    public void setLocation(String location) {
        if (XML.isEmpty(location))
            throw new IllegalArgumentException("location cannot be null or empty");
        this.location = location;
        if (root != null) {
            ((Element)root).getAttributeNodeNS(null,"Location").setNodeValue(location);
        }
    }

    /**
     *  Gets the QName of the query element processable by the authority
     *
     * @return    The query element QName
     */
    public QName getAuthorityKind() {
        return authorityKind;
    }

    /**
     *  Sets the QName of the query element processable by the authority
     * 
     * @param   authorityKind    The query element QName
     */
    public void setAuthorityKind(QName authorityKind) {
        if (authorityKind == null)
            throw new IllegalArgumentException("authorityKind cannot be null");
        this.authorityKind = authorityKind;
        if (root != null) {
            if (!XML.SAMLP_NS.equals(authorityKind.getNamespaceURI())) {
                ((Element)root).setAttributeNS(XML.XMLNS_NS, "xmlns:kind", authorityKind.getNamespaceURI());
                ((Element)root).getAttributeNodeNS(null, "AuthorityKind").setNodeValue("kind:" + authorityKind.getLocalName());
            }
            else {
                ((Element)root).removeAttributeNS(XML.XMLNS_NS, "xmlns:kind");
                ((Element)root).getAttributeNodeNS(null, "AuthorityKind").setNodeValue("samlp:" + authorityKind.getLocalName());
            }
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

        Element ab = doc.createElementNS(XML.SAML_NS, "AuthorityBinding");
		if (xmlns)
			ab.setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAML_NS);
        ab.setAttributeNS(null, "Binding", binding);
        ab.setAttributeNS(null, "Location", location);
        if (!XML.SAMLP_NS.equals(authorityKind.getNamespaceURI())) {
            ab.setAttributeNS(XML.XMLNS_NS, "xmlns:kind", authorityKind.getNamespaceURI());
            ab.setAttributeNS(null, "AuthorityKind","kind:" + authorityKind.getLocalName());
        }
        else
            ab.setAttributeNS(null, "AuthorityKind","samlp:" + authorityKind.getLocalName());
            
        return root = ab;
    }

    /**
     * @see org.opensaml.SAMLObject#checkValidity()
     */
    public void checkValidity() throws SAMLException {
        if (XML.isEmpty(binding) || XML.isEmpty(location) || authorityKind == null)
            throw new MalformedException("AuthorityBinding is invalid, must have Binding, Location, and AuthorityKind");
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

