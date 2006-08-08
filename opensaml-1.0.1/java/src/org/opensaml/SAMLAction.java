/* 
 * The OpenSAML License, Version 1. 
 * Copyright (c) 2003
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

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *  Represents a SAML Action
 *
 * @author     Helen Rehn
 * @created    October 4, 2002
 */
public class SAMLAction extends SAMLObject implements Cloneable
{
    /** SAML Action Namespace URI values */

    public static final String SAML_ACTION_NAMESPACE_RWEDC = "urn:oasis:names:tc:SAML:1.0:action:rwedc";

    public static final String SAML_ACTION_NAMESPACE_RWEDC_NEG = "urn:oasis:names:tc:SAML:1.0:action:rwedc-negation";

    public static final String SAML_ACTION_NAMESPACE_GHPP = "urn:oasis:names:tc:SAML:1.0:action:ghpp";

    public static final String SAML_ACTION_NAMESPACE_UNIX = "urn:oasis:names:tc:SAML:1.0:action:unix";

    private String namespace = null;
    private String data = null;

    /**
     *  Default constructor
     */
    public SAMLAction() {
    }

    /**
     *  Builds an action out of its component parts
     *
     * @param  namespace  a URI reference representing the namespace in
     *                    which the name of the specified action is to be
     *                    interpreted
     * @param  data       an action sought to be performed on the specified
     *                    resource
     * @exception  SAMLException  Raised if an action cannot be constructed
     *      from the supplied information
     */
    public SAMLAction(String namespace, String data) throws SAMLException {
  	    this.namespace = namespace;
  	    this.data = data;
    }

    /**
     *  Reconstructs an action from a DOM tree
     *
     * @param  e                  The root of a DOM tree
     * @exception  SAMLException  Thrown if the object cannot be constructed
     */
    public SAMLAction(Element e) throws SAMLException {
        fromDOM(e);
    }

    /**
     *  Reconstructs an action from a stream
     *
     * @param  in                   A stream containing XML
     * @exception  SAMLException  Raised if an exception occurs while constructing
     *                              the object.
     */
    public SAMLAction(InputStream in) throws SAMLException {
        fromDOM(fromStream(in));
    }
    
    /**
     * @see org.opensaml.SAMLObject#fromDOM(org.w3c.dom.Element)
     */
    public void fromDOM(Element e) throws SAMLException {
        super.fromDOM(e);
        
        if (config.getBooleanProperty("org.opensaml.strict-dom-checking") && !XML.isElementNamed(e,XML.SAML_NS,"Action"))
            throw new MalformedException(SAMLException.RESPONDER, "SAMLAction() requires saml:Action at root");
            
	    namespace = e.getAttributeNS(null,"Namespace");
        data = e.getFirstChild().getNodeValue();
        
	    checkValidity();
    }
    
    /**
     *  Gets the namespace from the action
     *
     * @return    the namespace
     */
    public String getNamespace() {
	   return namespace;
    }

    /**
     *  Gets the data from the action
     *
     * @return    the data
     */
    public String getData() {
    	return data;
    }

    /**
     *  Sets the namespace of the action
     * 
     * @param namespace    the namespace
     */
    public void setNamespace(String namespace) {
        if (root != null) {
            if (!XML.isEmpty(namespace))
                ((Element)root).setAttributeNS(null,"Namespace",namespace);
            else if (!XML.isEmpty(this.namespace))
                ((Element)root).removeAttributeNS(null,"Namespace");
        }
        this.namespace = namespace;
    }

    /**
     *  Sets the data of the action
     * 
     * @param data    the data
     */
    public void setData(String data) {
        if (XML.isEmpty(data))
            throw new IllegalArgumentException("data cannot be null or empty");
        this.data = data;
        if (root != null)
            root.getFirstChild().setNodeValue(data);
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
    
    	Element a = doc.createElementNS(XML.SAML_NS, "Action");
    	if (xmlns)
        	a.setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAML_NS);
        if (!XML.isEmpty(namespace))
        	a.setAttributeNS(null, "Namespace", namespace); 
    	a.appendChild(doc.createTextNode(data));
    	return root = a;
    }
    
    /**
     * @see org.opensaml.SAMLObject#checkValidity()
     */
    public void checkValidity() throws SAMLException {
        if (XML.isEmpty(data))
            throw new MalformedException("Action is invalid, data must have a value");
    }

    /**
     *  Copies a SAML object such that no dependencies exist between the original and the copy
     * 
     * @return      The new object
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

