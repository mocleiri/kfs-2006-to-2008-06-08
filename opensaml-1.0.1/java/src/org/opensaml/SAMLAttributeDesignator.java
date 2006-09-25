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
 *  SAML Attribute Designator implementation
 *
 *      Scott Cantor
 * @created    Nov 22, 2003
 */
public class SAMLAttributeDesignator extends SAMLObject implements Cloneable
{
    /**  Name of attribute */
    protected String name = null;

    /**  Namespace/qualifier of attribute */
    protected String namespace = null;

    /**
     *  Default constructor
     */
    public SAMLAttributeDesignator() {
    }

    /**
     *  Builds an AttributeDesignator out of its component parts
     *
     * @param  name               Name of attribute
     * @param  namespace          Namespace/qualifier of attribute
     * @exception  SAMLException  Thrown if attribute cannot be built from the
     *      supplied information
     */
    public SAMLAttributeDesignator(String name, String namespace) throws SAMLException {
        this.name = name;
        this.namespace = namespace;
    }

    /**
     *  Reconstructs an AttributeDesignator from a DOM tree
     *
     * @param  e                  The root of a DOM tree
     * @exception  SAMLException  Thrown if the object cannot be constructed
     */
    public SAMLAttributeDesignator(Element e) throws SAMLException {
        fromDOM(e);
    }

    /**
     *  Reconstructs an AttributeDesignator from a stream
     *
     * @param  in                   A stream containing XML
     * @exception  SAMLException  Raised if an exception occurs while constructing
     *                              the object.
     */
    public SAMLAttributeDesignator(InputStream in) throws SAMLException {
        fromDOM(fromStream(in));
    }
    
    /**
     *  Initialization of AttributeDesignator from a DOM element.<P>
     * 
     * @param  e                   Root element of a DOM tree
     * @exception  SAMLException   Raised if an exception occurs while constructing the object.
     */
    public void fromDOM(Element e) throws SAMLException {
        super.fromDOM(e);

        if (config.getBooleanProperty("org.opensaml.strict-dom-checking") && !XML.isElementNamed(e,XML.SAML_NS,"AttributeDesignator"))
            throw new MalformedException("SAMLAttributeDesignator.fromDOM() requires saml:AttributeDesignator at root");

        name = e.getAttributeNS(null, "AttributeName");
        namespace = e.getAttributeNS(null, "AttributeNamespace");

        checkValidity();
    }

    /**
     *  Gets the AttributeName attribute of the SAML AttributeDesignator
     *
     * @return    The name value
     */
    public String getName() {
        return name;
    }

    /**
     *  Sets the AttributeName attribute of the SAML AttributeDesignator
     *
     * @param   name    The name value
     */
    public void setName(String name) {
        if (XML.isEmpty(name))
            throw new IllegalArgumentException("name cannot be null");
        this.name = name;
        if (root != null) {
            ((Element)root).getAttributeNodeNS(null, "AttributeName").setNodeValue(name);
        }
    }

    /**
     *  Gets the AttributeNamespace attribute of the SAML AttributeDesignator
     *
     * @return    The namespace value
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     *  Sets the AttributeNamespace attribute of the SAML AttributeDesignator
     *
     * @param   namespace    The name value
     */
    public void setNamespace(String namespace) {
        if (XML.isEmpty(namespace))
            throw new IllegalArgumentException("namespace cannot be null");
        this.namespace = namespace;
        if (root != null) {
            ((Element)root).getAttributeNodeNS(null, "AttributeNamespace").setNodeValue(namespace);
        }
    }

    /**
     * @see org.opensaml.SAMLObject#toDOM(org.w3c.dom.Document,boolean)
     */
    public Node toDOM(Document doc, boolean xmlns) throws SAMLException {
		if ((root = super.toDOM(doc, xmlns)) != null) {
			if (xmlns) {
				((Element)root).setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAML_NS);
			}
			return root;
        }

        Element a = doc.createElementNS(XML.SAML_NS, "AttributeDesignator");
		if (xmlns)
			a.setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAML_NS);
        a.setAttributeNS(null, "AttributeName", name);
        a.setAttributeNS(null, "AttributeNamespace", namespace);

        return root = a;
    }
    
    /**
     * @see org.opensaml.SAMLObject#checkValidity()
     */
    public void checkValidity() throws SAMLException {
        if (XML.isEmpty(name) || XML.isEmpty(namespace))
            throw new MalformedException(SAMLException.RESPONDER, "AttributeDesignator invalid, requires name and namespace");
    }

    /**
     *  Copies a SAML object such that no dependencies exist between the original
     *  and the copy. Does not clone values.
     * 
     * @return      The new object
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
        return (SAMLAttributeDesignator)super.clone();
    }
}
