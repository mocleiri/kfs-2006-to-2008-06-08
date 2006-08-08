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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *  Represents a SAML AuthorizationDecisionQuery
 *
 * @author     Helen Rehn, Scott Cantor
 * @created    October 4, 2002
 */

public class SAMLAuthorizationDecisionQuery extends SAMLSubjectQuery implements Cloneable
{
    protected String resource = null;
    protected ArrayList actions = new ArrayList();
    protected ArrayList evidence = new ArrayList();

    /**
     *  Default constructor
     */
    public SAMLAuthorizationDecisionQuery() {
    }

    /**
     *  Builds an AuthorizationDecisionQuery out of its component parts
     *
     * @param  subject     subject of the query        
     * @param  resource    URI of the resource being accessed at the time of 
     *                           the query
     * @param  actions     specific actions being queried for, must contain SAMLAction objects
     * @param  evidence    evidence which may be considered, must contain String or SAMLAssertion objects
     * @exception  SAMLException  Raised if an AuthorizationDecisionQuery
     *             cannot be constructed from the supplied information
     */
    public SAMLAuthorizationDecisionQuery(SAMLSubject subject, String resource, Collection actions, Collection evidence) throws SAMLException {
        super(subject);
        
        this.resource = resource;
        this.actions.addAll(actions);
        if (evidence != null)
            this.evidence.addAll(evidence);
    }
    
    /**
     *  Reconstructs a query from a DOM tree
     *
     * @param  e                  The root of a DOM tree
     * @exception  SAMLException  Thrown if the object cannot be constructed
     */
    public SAMLAuthorizationDecisionQuery(Element e) throws SAMLException {
        fromDOM(e);
    }

    /**
     *  Reconstructs a query from a stream
     *
     * @param  in                   A stream containing XML
     * @exception  SAMLException  Raised if an exception occurs while constructing
     *                              the object.
     */
    public SAMLAuthorizationDecisionQuery(InputStream in) throws SAMLException {
        fromDOM(fromStream(in));
    }
    
    /**
     * @see org.opensaml.SAMLObject#fromDOM(org.w3c.dom.Element)
     */
    public void fromDOM(Element e) throws SAMLException {
        super.fromDOM(e);
        
        if (config.getBooleanProperty("org.opensaml.strict-dom-checking") && !XML.isElementNamed(e,XML.SAMLP_NS,"AuthorizationDecisionQuery"))
        {
            QName q = QName.getQNameAttribute(e, XML.XSI_NS, "type");
            if (!XML.isElementNamed(e,XML.SAMLP_NS,"Query") || !XML.isElementNamed(e,XML.SAMLP_NS,"SubjectQuery") ||
                q == null || !XML.SAMLP_NS.equals(q.getNamespaceURI()) || !"AuthorizationDecisionQueryType".equals(q.getLocalName()))
                throw new MalformedException(SAMLException.REQUESTER, "SAMLAuthorizationDecisionQuery.fromDOM() requires samlp:AuthorizationDecisionQuery at root");
        }

        resource = e.getAttributeNS(null, "Resource");

        Element n = XML.getFirstChildElement(e, XML.SAML_NS, "Action");
        while (n != null) {
            actions.add(new SAMLAction(n));
            n = XML.getNextSiblingElement(n, XML.SAML_NS, "Action");
        }

        n = XML.getFirstChildElement(e, XML.SAML_NS, "Evidence");
        if (n != null) {
            Element n2 = XML.getFirstChildElement(n);
            while (n2 != null) {
                if (XML.isElementNamed(n2, XML.SAML_NS, "Assertion"))
                    evidence.add(new SAMLAssertion(n2));
                else if (XML.isElementNamed(n2, XML.SAML_NS, "AssertionIDReference"))
                    evidence.add(n2.getFirstChild().getNodeValue());
                n2 = XML.getNextSiblingElement(n2);
            }
        }
        
        checkValidity();
    }
    
    /**
     *  Gets the resource URI inside the query
     *
     * @return    The resource URI
     */
    public String getResource() {
        return resource;
    }
    
    /**
     *  Sets the resource URI inside the query
     * 
     * @param   resource    The resource URI
     */
    public void setResource(String resource) {
        if (XML.isEmpty(resource))
            throw new IllegalArgumentException("resource cannot be null");
        this.resource = resource;
        if (root != null) {
            ((Element)root).getAttributeNodeNS(null,"Resource").setNodeValue(resource);
        }
    }

    /**
     * Gets the actions inside the query
     *
     * @return    An iterator over the actions
     */
    public Iterator getActions() {
        return actions.iterator();
    }

    /**
     *  Sets the actions to include in the query
     * 
     * @param   actions  The actions to include
     * @exception SAMLException     Raised if the actions are invalid
     */
    public void setActions(Collection actions) throws SAMLException {
        while (this.actions.size() > 0)
            removeAction(0);
        
        if (actions != null) {
            for (Iterator i = actions.iterator(); i.hasNext(); )
                addAction((SAMLAction)i.next());
        }
    }
    
    /**
     *  Adds an action to the query
     * @param   action  The action to add
     * @exception SAMLException     Raised if the action if invalid
     */
    public void addAction(SAMLAction action) throws SAMLException {
        if (action != null) {
            if (root != null) {
                Element last = XML.getLastChildElement(root, XML.SAML_NS, "Action");
                if (last == null)
                    root.insertBefore(action.toDOM(root.getOwnerDocument()), subject.root.getNextSibling());
                else
                    root.insertBefore(action.toDOM(root.getOwnerDocument()), last.getNextSibling());
            }
            actions.add(action);
        }
        else
            throw new IllegalArgumentException("action cannot be null");
    }

    /**
     *  Removes an action by position (zero-based)
     * 
     * @param   index   The position of the action to remove
     */
    public void removeAction(int index) {
        actions.remove(index);
        if (root != null) {
            Element e = XML.getFirstChildElement(root, XML.SAML_NS, "Action");
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
     *  Gets the evidence inside the query
     *
     * @return     An iterator over the evidence
     */
    public Iterator getEvidence() {
        return evidence.iterator();
    }

    /**
     *  Sets the evidence to include in the query
     * 
     * @param   evidence  The evidence to include
     * @exception SAMLException     Raised if the evidence is invalid
     */
    public void setEvidence(Collection evidence) throws SAMLException {
        while (this.evidence.size() > 0)
            removeEvidence(0);
        
        if (evidence != null) {
            for (Iterator i = evidence.iterator(); i.hasNext(); )
                addEvidence(i.next());
        }
    }

    /**
     *  Adds an evidence element
     *
     * @param   evidence    a String or SAMLAssertion
     * @exception SAMLException     Raised if an invalid kind of object is provided
     */
    public void addEvidence(Object evidence) throws SAMLException {
        if (evidence != null && (evidence instanceof String || evidence instanceof SAMLAssertion)) {
            if (root != null) {
                Document doc = root.getOwnerDocument();
                Element wrapper = XML.getFirstChildElement(root, XML.SAML_NS, "Evidence");
                if (wrapper == null) {
                    wrapper = doc.createElementNS(XML.SAML_NS, "Evidence");
                    if (actions.size() == 0) {
                        root.insertBefore(wrapper, subject.root.getNextSibling());
                    }
                    else {
                        root.insertBefore(wrapper, ((SAMLAction)actions.get(actions.size()-1)).root.getNextSibling());
                    }
                }
                if (evidence instanceof String && !XML.isEmpty((String)evidence)) {
                    Element ref = doc.createElementNS(XML.SAML_NS, "AssertionIDReference");
                    ref.appendChild(doc.createTextNode((String)evidence));
                    wrapper.appendChild(ref);
                }
                else if (evidence instanceof SAMLAssertion) {
                    wrapper.appendChild(((SAMLAssertion)evidence).toDOM(doc));
                }
            }
            this.evidence.add(evidence);
        }
        else
            throw new IllegalArgumentException("can only add Strings or SAMLAssertions");
    }
    
    /**
     *  Removes an evidence element by position (zero-based)
     * 
     * @param   index   The position of the element to remove
     */
    public void removeEvidence(int index) throws IndexOutOfBoundsException {
        evidence.remove(index);
        if (root != null) {
            Element wrapper = XML.getFirstChildElement(root, XML.SAML_NS, "Evidence");
            Element e = XML.getFirstChildElement(wrapper);
            while (e != null && index > 0) {
                e = XML.getNextSiblingElement(e);
                index--;
            }
            if (e != null)
                wrapper.removeChild(e);
            else
                throw new IndexOutOfBoundsException();
            
            if (evidence.size() == 0)
                root.removeChild(wrapper);
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

        Element q = doc.createElementNS(XML.SAMLP_NS, "AuthorizationDecisionQuery");
		if (xmlns)
			q.setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAMLP_NS);
        q.setAttributeNS(null, "Resource", resource);
        q.appendChild(subject.toDOM(doc));
	
        Iterator i = actions.iterator();
        while (i.hasNext())
            q.appendChild(((SAMLAction)i.next()).toDOM(doc));
	
        if (evidence.size()>0) {
            Element ev = doc.createElementNS(XML.SAML_NS, "Evidence");
            ev.setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAML_NS);
            i = evidence.iterator();
            while (i.hasNext()) {
                Object o = i.next();
                if (o instanceof SAMLAssertion)
                    ev.appendChild(((SAMLAssertion)o).toDOM(doc, false));
                else if (o instanceof String && !XML.isEmpty((String)o))
                    ev.appendChild(doc.createElementNS(XML.SAML_NS,"AssertionIDReference")).appendChild(doc.createTextNode((String)o));
            }
            q.appendChild(ev);
        } 

        return root = q;
    }

    /**
     * @see org.opensaml.SAMLObject#checkValidity()
     */
    public void checkValidity() throws SAMLException {
        super.checkValidity();
        if (XML.isEmpty(resource) || actions.size() == 0)
            throw new MalformedException("AuthorizationDecisionQuery is invalid, must have Resource and at least one Action");
    }

    /**
     *  Copies a SAML object such that no dependencies exist between the original
     *  and the copy
     * 
     * @return      The new object
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
        SAMLAuthorizationDecisionQuery dup=(SAMLAuthorizationDecisionQuery)super.clone();
        
        // Clone the embedded objects.
        for (Iterator i=actions.iterator(); i.hasNext(); )
            dup.actions.add(((SAMLAction)i.next()).clone());
                    
        for (Iterator i=evidence.iterator(); i.hasNext(); ) {
            Object o = i.next();
            if (o instanceof SAMLAssertion)
                dup.evidence.add(((SAMLAssertion)o).clone());
            else if (o instanceof String)
                dup.evidence.add(o);
        }
        
        return dup;
    }
}
