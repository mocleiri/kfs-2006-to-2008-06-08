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

import org.w3c.dom.*;

/**
 *  Represents a SAML Attribute Query object
 *
 *      Scott Cantor
 * @created    March 25, 2002
 */
public class SAMLAttributeQuery extends SAMLSubjectQuery implements Cloneable
{
    protected String resource = null;
    protected ArrayList designators = new ArrayList();

    /**
     *  Default constructor
     */
    public SAMLAttributeQuery() {
    }

    /**
     *  Builds an attribute query out of its component parts
     *
     * @param  subject            Subject of query
     * @param  resource           URI of resource being accessed at time of
     *      query
     * @param  designators        Indicates specific attributes to query for
     * @exception  SAMLException  Raised if the query cannot be constructed from
     *      the supplied information
     */
    public SAMLAttributeQuery(SAMLSubject subject, String resource, Collection designators) throws SAMLException {
        super(subject);

        this.resource = resource;
        if (designators != null)
            this.designators.addAll(designators);
    }

    /**
     *  Reconstructs an attribute query from a DOM tree
     *
     * @param  e                  The root of a DOM tree
     * @exception  SAMLException  Thrown if the object cannot be constructed
     */
    public SAMLAttributeQuery(Element e) throws SAMLException {
        fromDOM(e);
    }

    /**
     *  Reconstructs an attribute query from a stream
     *
     * @param  in                   A stream containing XML
     * @exception  SAMLException  Raised if an exception occurs while constructing
     *                              the object.
     */
    public SAMLAttributeQuery(InputStream in) throws SAMLException {
        fromDOM(fromStream(in));
    }

    /**
     * @see org.opensaml.SAMLObject#fromDOM(org.w3c.dom.Element)
     */
    public void fromDOM(Element e) throws SAMLException {
        super.fromDOM(e);
        
        if (config.getBooleanProperty("org.opensaml.strict-dom-checking") && !XML.isElementNamed(e,XML.SAMLP_NS,"AttributeQuery")) {
            QName q = QName.getQNameAttribute(e, XML.XSI_NS, "type");
            if (!XML.isElementNamed(e,XML.SAMLP_NS,"Query") || !XML.isElementNamed(e,XML.SAMLP_NS,"SubjectQuery") ||
                q == null || !XML.SAMLP_NS.equals(q.getNamespaceURI()) || !"AttributeQueryType".equals(q.getLocalName()))
                throw new MalformedException(SAMLException.REQUESTER, "SAMLAttributeQuery() requires samlp:AttributeQuery at root");
        }

        if (e.hasAttributeNS(null, "Resource"))
            resource = e.getAttributeNS(null, "Resource");

        // Extract attribute designators, if any.
        Element n = XML.getFirstChildElement(root, XML.SAML_NS, "AttributeDesignator");
        while (n != null) {
            designators.add(new SAMLAttributeDesignator(n));
            n = XML.getNextSiblingElement(n, XML.SAML_NS, "AttributeDesignator");
        }
    }

    /**
     *  Gets the resource URI inside the query
     *
     * @return    The resource URI
     */
    public String getResource()
    {
        return resource;
    }

    /**
     *  Sets the resource URI inside the query
     * 
     * @param   resource    The resource URI
     */
    public void setResource(String resource) {
        this.resource = resource;
        if (root != null) {
            ((Element)root).removeAttributeNS(null, "Resource");
            if (!XML.isEmpty(resource))
                ((Element)root).setAttributeNS(null, "Resource", resource);
        }
    }

    /**
     *  Gets the attribute designators inside the query
     *
     * @return    An iterator of attribute designators
     */
    public Iterator getDesignators() {
        return designators.iterator();
    }

    /**
     *  Sets the attribute designators inside the query
     * 
     * @param designators    The designators to include
     * @exception   SAMLException   Raised if the designators are invalid
     */
    public void setDesignators(Collection designators) throws SAMLException {
        while (this.designators.size() > 0)
            removeDesignator(0);
        
        if (designators != null) {
            for (Iterator i = designators.iterator(); i.hasNext(); )
                addDesignator((SAMLAttributeDesignator)i.next());
        }
    }

    /**
     *  Adds an attribute designator to the query
     * 
     * @param designator    The designator to add
     * @exception   SAMLException   Raised if the designator is invalid
     */
    public void addDesignator(SAMLAttributeDesignator designator) throws SAMLException {
        if (designator != null) {
            if (root != null) {
                designator.checkValidity();
                
                //This is defensive code in case somebody extends this type and adds new elements.
                Element last = XML.getLastChildElement(root, XML.SAML_NS, "AttributeDesignator");
                if (last == null)
                    root.insertBefore(designator.toDOM(root.getOwnerDocument()), subject.root.getNextSibling());
                else
                    root.insertBefore(designator.toDOM(root.getOwnerDocument()), last.getNextSibling());
            }
            designators.add(designator);
        }
        else
            throw new IllegalArgumentException("designator cannot be null");
    }
    
    /**
     *  Removes a designator by position (zero-based)
     * 
     * @param   index   The position of the designator to remove
     */
    public void removeDesignator(int index) {
        designators.remove(index);
        if (root != null) {
            Element e = XML.getFirstChildElement(root, XML.SAML_NS, "AttributeDesignator");
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
				((Element)root).setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAMLP_NS);
			return root;
        }

        Element q = doc.createElementNS(XML.SAMLP_NS, "AttributeQuery");
		if (xmlns)
			q.setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAMLP_NS);
        if (resource != null && resource.length() > 0)
            q.setAttributeNS(null, "Resource", resource);
        q.appendChild(subject.toDOM(doc));

        for (Iterator i=designators.iterator(); i.hasNext(); ) {
            SAMLAttributeDesignator d=(SAMLAttributeDesignator)i.next();
            d.checkValidity();
            q.appendChild(d.toDOM(doc, true));
        }
        
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
        SAMLAttributeQuery dup=(SAMLAttributeQuery)super.clone();
        
        // Clone the embedded objects.
        for (Iterator i=designators.iterator(); i.hasNext(); )
            dup.designators.add(((SAMLAttributeDesignator)i.next()).clone());
        
        return dup;
    }
}

