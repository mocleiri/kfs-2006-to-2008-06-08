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
 *  Represents a SAML AudienceRestrictionCondition
 *
 *      Scott Cantor
 * @created    March 30, 2002
 */
public class SAMLAudienceRestrictionCondition extends SAMLCondition implements Cloneable
{
    protected ArrayList audiences = new ArrayList();

    /**
     *  Default constructor
     */
    public SAMLAudienceRestrictionCondition() {
    }

    /**
     *  Builds a condition out of its component parts
     *
     * @param  audiences          Array of audiences to embed in condition
     * @exception  SAMLException  Raised if a condition cannot be constructed
     *      from the supplied information
     */
    public SAMLAudienceRestrictionCondition(Collection audiences) throws SAMLException {
        if (audiences != null)
            this.audiences.addAll(audiences);
    }

    /**
     *  Reconstructs a condition from a DOM tree
     *
     * @param  e                  The root of a DOM tree
     * @exception  SAMLException  Thrown if the object cannot be constructed
     */
    public SAMLAudienceRestrictionCondition(Element e) throws SAMLException {
        fromDOM(e);
    }

    /**
     *  Reconstructs a condition from a stream
     *
     * @param  in                   A stream containing XML
     * @exception  SAMLException  Raised if an exception occurs while constructing
     *                              the object.
     */
    public SAMLAudienceRestrictionCondition(InputStream in) throws SAMLException {
        fromDOM(fromStream(in));
    }
    
    /**
     * @see org.opensaml.SAMLObject#fromDOM(org.w3c.dom.Element)
     */
    public void fromDOM(Element e) throws SAMLException {
        super.fromDOM(e);
        
        if (config.getBooleanProperty("org.opensaml.strict-dom-checking")) {
            if (!XML.isElementNamed(e,XML.SAML_NS,"AudienceRestrictionCondition")) {
                QName q = QName.getQNameAttribute(e, XML.XSI_NS, "type");
                if (!XML.isElementNamed(e,XML.SAML_NS,"Condition") || q == null ||
                    !XML.SAML_NS.equals(q.getNamespaceURI()) || !"AudienceRestrictionConditionType".equals(q.getLocalName()))
                    throw new MalformedException(SAMLException.RESPONDER, "SAMLAudienceRestrictionCondition() requires saml:AudienceRestrictionCondition at root");
            }
        }
        
        // Extract audiences.
        Element aud = XML.getFirstChildElement(e);
        while (aud != null) {
            String val = aud.getFirstChild().getNodeValue();
            if (!XML.isEmpty(val))
                audiences.add(val);
            aud = XML.getNextSiblingElement(aud);
        }
        checkValidity();
    }

    /**
     *  Gets the audiences included in the condition
     * 
     * @return The audiences in the condition
     */
    public Iterator getAudiences() {
        return audiences.iterator();
    }

    /**
     *  Sets the audiences to include in the condition
     * 
     * @param   audiences   The audiences to include
     */
    public void setAudiences(Collection audiences) {
        while (this.audiences.size() > 0)
            removeAudience(0);
        
        if (audiences != null) {
            for (Iterator i = audiences.iterator(); i.hasNext(); )
                addAudience((String)i.next());
        }
    }
    
    /**
     *  Adds an audience to the condition
     * 
     * @param audience  The audience to add
     */
    public void addAudience(String audience) {
        if (!XML.isEmpty(audience)) {
            if (root != null) {
                Element a = root.getOwnerDocument().createElementNS(XML.SAML_NS, "Audience");
                a.appendChild(root.getOwnerDocument().createTextNode(audience));
                Element last = XML.getLastChildElement(root, XML.SAML_NS, "Audience");
                if (last == null)
                    root.insertBefore(a, root.getFirstChild());
                else
                    root.insertBefore(a, last.getNextSibling());
            }
            audiences.add(audience);
        }
        else
            throw new IllegalArgumentException("audience cannot be null or empty");
    }

    /**
     *  Removes an audience by position (zero-based)
     * 
     * @param   index   The position of the audience to remove
     */
    public void removeAudience(int index) {
        audiences.remove(index);
        if (root != null) {
            Element e = XML.getFirstChildElement(root);
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

        Element condition = doc.createElementNS(XML.SAML_NS, "AudienceRestrictionCondition");
		if (xmlns)
			condition.setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAML_NS);

        Iterator i=audiences.iterator();
        while (i.hasNext()) {
            String s = (String)i.next();
            if (!XML.isEmpty(s)) 
                condition.appendChild(doc.createElementNS(XML.SAML_NS,"Audience")).appendChild(doc.createTextNode(s));
        }

        return root = condition;
    }

    /**
     *  Evaluates the condition
     *
     * @param  audiences  A collection of audiences deemed acceptable by the evaluator
     * @return            Returns true iff the condition's audiences intersect
     *      with those of the evaluator
     */
    public boolean eval(Collection audiences) {
        if (audiences == null || audiences.size() == 0)
            return false;
        
        for (Iterator i=audiences.iterator(); i.hasNext();)
            if (this.audiences.contains(i.next()))
                return true;
        return false;
    }

    /**
     * @see org.opensaml.SAMLObject#checkValidity()
     */
    public void checkValidity() throws SAMLException {
        if (audiences == null || audiences.size() == 0)
            throw new MalformedException(SAMLException.RESPONDER, "AudienceRestrictionCondition is invalid, requires at least one audience");

    }

    /**
     *  Copies a SAML object such that no dependencies exist between the original
     *  and the copy
     * 
     * @return      The new object
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
        SAMLAudienceRestrictionCondition dup=(SAMLAudienceRestrictionCondition)super.clone();
        dup.audiences=(ArrayList)audiences.clone();
        return dup;
    }
}

