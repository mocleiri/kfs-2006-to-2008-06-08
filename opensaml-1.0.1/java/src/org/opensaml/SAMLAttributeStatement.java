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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.NDC;
import org.w3c.dom.*;

/**
 *  Represents a SAML Attribute Statement
 *
 * @author     Scott Cantor
 * @created    March 25, 2002
 */
public class SAMLAttributeStatement extends SAMLSubjectStatement implements Cloneable
{
    protected ArrayList attrs = new ArrayList();

    /**
     *  Default constructor
     */
    public SAMLAttributeStatement() {
    }

    /**
     *  Builds a statement out of its component parts
     *
     * @param  subject            Subject of statement
     * @param  attrs              Collection of attributes
     * @exception  SAMLException  Raised if a statement cannot be constructed
     *      from the supplied information
     */
    public SAMLAttributeStatement(SAMLSubject subject, Collection attrs) throws SAMLException {
        super(subject);

        this.attrs.addAll(attrs);
    }

    /**
     *  Reconstructs an attribute statement from a DOM tree
     *
     * @param  e                  The root of a DOM tree
     * @exception  SAMLException  Thrown if the object cannot be constructed
     */
    public SAMLAttributeStatement(Element e) throws SAMLException {
        fromDOM(e);
    }

    /**
     *  Reconstructs an attribute statement from a stream
     *
     * @param  in                   A stream containing XML
     * @exception  SAMLException  Raised if an exception occurs while constructing
     *                              the object.
     */
    public SAMLAttributeStatement(InputStream in) throws SAMLException {
        fromDOM(fromStream(in));
    }

    /**
     * @see org.opensaml.SAMLObject#fromDOM(org.w3c.dom.Element)
     */
    public void fromDOM(Element e) throws SAMLException {
        super.fromDOM(e);
        
        if (config.getBooleanProperty("org.opensaml.strict-dom-checking") && !XML.isElementNamed(e,XML.SAML_NS,"AttributeStatement"))
        {
            QName q = QName.getQNameAttribute(e, XML.XSI_NS, "type");
            if (!XML.isElementNamed(e,XML.SAML_NS,"Statement") || !XML.isElementNamed(e,XML.SAML_NS,"SubjectStatement") ||
                q == null || !XML.SAML_NS.equals(q.getNamespaceURI()) || !"AttributeStatementType".equals(q.getLocalName()))
                throw new MalformedException(SAMLException.REQUESTER, "SAMLAttributeStatement() requires saml:AttributeStatement at root");
        }

        // Extract attributes.
        Element n = XML.getFirstChildElement(root, XML.SAML_NS, "Attribute");
        while (n != null) {
            try {
                attrs.add(SAMLAttribute.getInstance(n));
            }
            catch (SAMLException ex) {
                log.warn("exception while instantiating a SAMLAttribute: " + ex.getMessage());
            }
            n = XML.getNextSiblingElement(n, XML.SAML_NS, "Attribute");
        }
    }

    /**
     *  Gets attributes from the statement, if any
     *
     * @return    An array of attributes
     */
    public Iterator getAttributes() {
        return attrs.iterator();
    }

    /**
     *  Sets the attributes inside the statement
     * 
     * @param attributes    The attributes to include
     * @exception   SAMLException   Raised if the attributes are invalid
     */
    public void setAttributes(Collection attributes) throws SAMLException {
        while (attrs.size() > 0)
            removeAttribute(0);
        
        if (attributes != null) {
            for (Iterator i = attributes.iterator(); i.hasNext(); )
                addAttribute((SAMLAttribute)i.next());
        }
    }

    /**
     *  Adds an attribute to the statement
     * 
     * @param attribute    The attribute to add
     * @exception SAMLException     Raised if the subject is invalid 
     */
    public void addAttribute(SAMLAttribute attribute) throws SAMLException {
        if (attribute != null) {
            if (root != null) {
                //This is defensive code in case somebody extends this type and adds new elements.
                Element last = XML.getLastChildElement(root, XML.SAML_NS, "Attribute");
                if (last == null)
                    root.insertBefore(attribute.toDOM(root.getOwnerDocument()), subject.root.getNextSibling());
                else
                    root.insertBefore(attribute.toDOM(root.getOwnerDocument()), last.getNextSibling());
            }
            attrs.add(attribute);
        }
        else
            throw new IllegalArgumentException("attribute cannot be null");
    }
    
    /**
     *  Removes an attribute by position (zero-based)
     * 
     * @param   index   The position of the attribute to remove
     */
    public void removeAttribute(int index) {
        attrs.remove(index);
        if (root != null) {
            Element e = XML.getFirstChildElement(root, XML.SAML_NS, "Attribute");
            while (e != null && index > 0) {
                e = XML.getNextSiblingElement(e);
                index--;
            }
            if (e != null)
                root.removeChild(e);
            else
                throw new IndexOutOfBoundsException();
        }
        if (attrs.size() == 0) {
            NDC.push("removeAttribute");
            log.warn("all attributes have been removed, statement is in an illegal state");
            NDC.pop();
        }
    }

    /**
     *  @see org.opensaml.SAMLObject#toDOM(org.w3c.dom.Document,boolean)
     */
    public Node toDOM(Document doc, boolean xmlns) throws SAMLException {
		if ((root = super.toDOM(doc, xmlns)) != null) {
			if (xmlns) {
				((Element)root).setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAML_NS);
				((Element)root).setAttributeNS(XML.XMLNS_NS, "xmlns:xsd", XML.XSD_NS);
				((Element)root).setAttributeNS(XML.XMLNS_NS, "xmlns:xsi", XML.XSI_NS);
			}
			return root;
        }

        // This is the meat. Build a new XML instance using our state and the specified Document.
        Element statement = doc.createElementNS(XML.SAML_NS, "AttributeStatement");
        if (xmlns)
			statement.setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAML_NS);
        statement.appendChild(subject.toDOM(doc, false));

        for (Iterator i=attrs.iterator(); i.hasNext();)
            statement.appendChild(((SAMLAttribute)i.next()).toDOM(doc, false));

        return root = statement;
    }

    /**
     * @see org.opensaml.SAMLObject#checkValidity()
     */
    public void checkValidity() throws SAMLException {
        if (attrs == null || attrs.size() == 0)
            throw new MalformedException(SAMLException.RESPONDER, "AttributeStatement is invalid, requires at least one attribute");
    }

    /**
     *  Copies a SAML object such that no dependencies exist between the original
     *  and the copy
     * 
     * @return      The new object
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
        SAMLAttributeStatement dup=(SAMLAttributeStatement)super.clone();
        
        // Clone the embedded objects.
        for (Iterator i=attrs.iterator(); i.hasNext(); )
            dup.attrs.add(((SAMLAttribute)i.next()).clone());
        
        return dup;
    }
}

