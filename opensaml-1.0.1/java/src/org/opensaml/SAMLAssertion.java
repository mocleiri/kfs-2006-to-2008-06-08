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
import java.util.Date;
import java.util.TimeZone;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.w3c.dom.*;

/**
 *  Represents a SAML Assertion
 *
 *      Scott Cantor
 * @created    March 18, 2002
 */
public class SAMLAssertion extends SAMLSignedObject implements Cloneable
{
    protected String assertionId = new SAMLIdentifier().toString();
    protected String issuer = null;
    protected Date issueInstant = new Date();
    protected Date notBefore = null;
    protected Date notOnOrAfter = null;
    protected ArrayList conditions = new ArrayList();
    protected ArrayList advice = new ArrayList();
    protected ArrayList statements = new ArrayList();

    /**
     *  Places the signature into the object's DOM to prepare for signing<p>

     * @throws SAMLException    Thrown if an error occurs while placing the signature
     */
    protected void insertSignature() throws SAMLException {
        root.appendChild(getSignatureElement());
    }

    /**
     *  Default constructor
     */
    public SAMLAssertion() {
    }

    /**
     *  Builds an assertion out of its component parts
     *
     * @param  issuer             Name of SAML authority issuing assertion
     * @param  notBefore          Optional start of validity
     * @param  notOnOrAfter       Optional end of validity
     * @param  conditions         Set of conditions on validity
     * @param  advice             Optional advice content
     * @param  statements         Set of SAML statements to place in assertion
     * @exception  SAMLException  Raised if an assertion cannot be constructed
     *      from the supplied information
     */
    public SAMLAssertion(String issuer, Date notBefore, Date notOnOrAfter,
                          Collection conditions, Collection advice, Collection statements) throws SAMLException {
        // Copy pieces/parts to populate assertion.
        this.issuer = issuer;
        this.notBefore = notBefore;
        this.notOnOrAfter = notOnOrAfter;

        if (conditions != null)
            this.conditions.addAll(conditions);
        
        if (advice != null)
            this.advice.addAll(advice);

        if (statements != null)
            this.statements.addAll(statements);
    }

    /**
     *  Reconstructs an assertion from a DOM tree
     *
     * @param  e                  The root of a DOM tree
     * @exception  SAMLException  Thrown if the object cannot be constructed
     */
    public SAMLAssertion(Element e) throws SAMLException {
        fromDOM(e);
    }

    /**
     *  Reconstructs an assertion from a stream
     *
     * @param  in                   A stream containing XML
     * @exception  SAMLException  Raised if an exception occurs while constructing
     *                              the object.
     */
    public SAMLAssertion(InputStream in) throws SAMLException {
        fromDOM(fromStream(in));
    }
    
    /**
     * @see org.opensaml.SAMLObject#fromDOM(org.w3c.dom.Element)
     */
    public void fromDOM(Element e) throws SAMLException {
        super.fromDOM(e);
        
        if (config.getBooleanProperty("org.opensaml.strict-dom-checking") && !XML.isElementNamed(e,XML.SAML_NS,"Assertion"))
            throw new MalformedException(SAMLException.RESPONDER,"SAMLAssertion.fromDOM() requires saml:Assertion at root");

        if (Integer.parseInt(e.getAttributeNS(null, "MajorVersion")) != 1)
            throw new MalformedException(SAMLException.VERSION, "SAMLAssertion.fromDOM() detected incompatible assertion major version of " +
                e.getAttributeNS(null, "MajorVersion"));

        issuer = e.getAttributeNS(null, "Issuer");
        assertionId = e.getAttributeNS(null, "AssertionID");
        e.setIdAttributeNode(e.getAttributeNodeNS(null, "AssertionID"), true);
            
        try {
            SimpleDateFormat formatter = null;
            String dateTime = e.getAttributeNS(null, "IssueInstant");
            int dot = dateTime.indexOf('.');
            if (dot > 0) {
                formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            }
            else {
                formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            }
            formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
            issueInstant = formatter.parse(dateTime);

            Element n = XML.getFirstChildElement(e);
            while (n != null) {
                // The top level children may be one of three different types.
                if (XML.isElementNamed(n, XML.SAML_NS, "Conditions")) {
                    // Check validity time attributes.
                    if (n.hasAttributeNS(null, "NotBefore"))
                        notBefore = formatter.parse(n.getAttributeNS(null, "NotBefore"));
                    if (n.hasAttributeNS(null, "NotOnOrAfter"))
                        notOnOrAfter = formatter.parse(n.getAttributeNS(null, "NotOnOrAfter"));

                    // Iterate over conditions.
                    Element cond = XML.getFirstChildElement(n);
                    while (cond != null) {
                        conditions.add(SAMLCondition.getInstance(cond));
                        cond = XML.getNextSiblingElement(cond);
                    }
                }
                else if (XML.isElementNamed(n, XML.SAML_NS, "Advice")) {
                    Element child = XML.getFirstChildElement(n);
                    while (child != null) {
                        if (XML.isElementNamed(child, XML.SAML_NS, "AssertionIDReference")) {
                            advice.add(child.getFirstChild().getNodeValue());
                        }
                        else if (XML.isElementNamed(child, XML.SAML_NS, "Assertion")) {
                            advice.add(new SAMLAssertion(child));
                        }
                        else {
                            advice.add(child);
                        }
                        child = XML.getNextSiblingElement(child);
                    }
                }
                else if (!XML.isElementNamed(n, XML.XMLSIG_NS, "Signature"))
                    statements.add(SAMLStatement.getInstance(n));
                n = XML.getNextSiblingElement(n);
            }
        }
        catch (java.text.ParseException ex) {
            throw new MalformedException(SAMLException.RESPONDER, "SAMLAssertion.fromDOM() detected an invalid datetime while parsing assertion", ex);
        }
        checkValidity();
    }

    /**
     *  Gets the assertion ID from the assertion
     *
     * @return    The assertion ID
     */
    public String getId() {
        return assertionId;
    }

    /**
     *  Sets the assertion ID
     * 
     *  <b>NOTE:</b> Use this method with caution. Assertions must contain unique identifiers
     *  and only specialized applications should need to explicitly assign an identifier.
     *
     * @param   id    The assertion ID
     */
    public void setId(String id) {
        if (XML.isEmpty(id))
            throw new IllegalArgumentException("id cannot be null");
        assertionId=id;
        if (root != null) {
            unsign();
            ((Element)root).getAttributeNodeNS(null,"AssertionID").setNodeValue(id);
        }
    }

    /**
     *  Gets the issuer of the assertion
     *
     * @return    The issuer name
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     *  Sets the issuer name
     * 
     * @param   issuer    The issuer name
     */
    public void setIssuer(String issuer) {
        if (XML.isEmpty(issuer))
            throw new IllegalArgumentException("issuer cannot be null");
        this.issuer = issuer;
        if (root != null) {
            unsign();
            ((Element)root).getAttributeNodeNS(null,"Issuer").setNodeValue(issuer);
        }
    }
    
    /**
     *  Gets the issue timestamp of the assertion
     *
     * @return    The issue timestamp
     */
    public Date getIssueInstant() {
        return issueInstant;
    }

    /**
     *  Sets the issue timestamp of the assertion
     *
     * @param   issueInstant    The issue timestamp
     */
    public void setIssueInstant(Date issueInstant) {
        if (issueInstant == null)
            throw new IllegalArgumentException("issueInstant cannot be null");
        if (root != null) {
            unsign();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
            ((Element)root).getAttributeNodeNS(null, "IssueInstant").setNodeValue(formatter.format(issueInstant));
        }
        this.issueInstant = issueInstant;
    }

    /**
     *  Gets the start of the assertion's validity period
     *
     * @return    The starting validity date and time
     */
    public Date getNotBefore() {
        return notBefore;
    }

    /**
     *  Sets the start of the assertion's validity period
     * 
     * @param   notBefore    The starting validity date and time
     */
    public void setNotBefore(Date notBefore) {
        if (root != null) {
            //Clear out the existing value.
            unsign();
            Element cond = XML.getFirstChildElement(root, XML.SAML_NS, "Conditions");
            if (this.notBefore != null) {
                cond.removeAttributeNS(null,"NotBefore");
                //Still need the element at all?
                if (notBefore == null && notOnOrAfter == null && conditions.size() == 0) {
                    root.removeChild(cond);
                }
            }
            
            if (notBefore != null) {
                //Recreate element if needed.
                if (cond == null)
                    cond = (Element)root.insertBefore(
                        root.getOwnerDocument().createElementNS(XML.SAML_NS, "Conditions"),
                        root.getFirstChild());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
                cond.setAttributeNS(null, "NotBefore", formatter.format(notBefore));
            }
        }
        this.notBefore = notBefore;
    }

    /**
     *  Gets the end of the assertion's validity period
     *
     * @return    The ending validity date and time
     */
    public Date getNotOnOrAfter() {
        return notOnOrAfter;
    }

    /**
     *  Sets the end of the assertion's validity period
     * 
     * @param   notOnOrAfter    The ending validity date and time
     */
    public void setNotOnOrAfter(Date notOnOrAfter) {
        if (root != null) {
            //Clear out the existing value.
            unsign();
            Element cond = XML.getFirstChildElement(root, XML.SAML_NS, "Conditions");
            if (this.notOnOrAfter != null) {
                cond.removeAttributeNS(null,"NotOnOrAfter");
                //Still need the element at all?
                if (notBefore == null && notOnOrAfter == null && conditions.size() == 0) {
                    root.removeChild(cond);
                }
            }
            
            if (notOnOrAfter != null) {
                //Recreate element if needed.
                if (cond == null)
                    cond = (Element)root.insertBefore(
                        root.getOwnerDocument().createElementNS(XML.SAML_NS, "Conditions"),
                        root.getFirstChild());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
                cond.setAttributeNS(null, "NotOnOrAfter", formatter.format(notOnOrAfter));
            }
        }
        this.notOnOrAfter = notOnOrAfter;
    }

    /**
     *  Gets the conditions included in the assertion
     *
     * @return    An iterator of SAML conditions
     */
    public Iterator getConditions() {
        return conditions.iterator();
    }
    
    /**
     *  Sets the conditions included in the assertion
     * 
     * @param conditions    The conditions to include in the assertion
     * @throws SAMLException    Raised if any of the conditions are invalid
     */
    public void setConditions(Collection conditions) throws SAMLException {
        while (this.conditions.size() > 0) {
            removeCondition(0);
        }
        
        if (conditions != null) {
            for (Iterator i = conditions.iterator(); i.hasNext(); )
                addCondition((SAMLCondition)i.next());
        }
    }

    /**
     *  Adds a condition to the assertion
     * 
     * @param c     The condition to add
     * @exception   SAMLException   Raised if an error occurs while adding the condition
     */
    public void addCondition(SAMLCondition c) throws SAMLException {
        if (c != null) {
            if (root != null) {
                unsign();
                Element conds = XML.getFirstChildElement(root, XML.SAML_NS, "Conditions");
                if (conds == null)
                    root.insertBefore(
                        root.getOwnerDocument().createElementNS(XML.SAML_NS, "Conditions"),
                        root.getFirstChild()
                        );
                conds.appendChild(c.toDOM(root.getOwnerDocument()));
            }
            conditions.add(c);
        }
        else
            throw new IllegalArgumentException("c cannot be null");
    }

    /**
     *  Removes a condition by position (zero-based)
     * 
     * @param   index   The position of the condition to remove
     */
    public void removeCondition(int index) throws IndexOutOfBoundsException {
        conditions.remove(index);
        if (root != null) {
            unsign();
            Element cond = XML.getFirstChildElement(root, XML.SAML_NS, "Conditions");

            if (conditions.size() == 0 && notBefore == null && notOnOrAfter == null) {
                root.removeChild(cond);
                return;
            }

            Element e = XML.getFirstChildElement(cond);
            while (e != null && index > 0) {
                e = XML.getNextSiblingElement(e);
                index--;
            }
            if (e != null)
                cond.removeChild(e);
            else
                throw new IndexOutOfBoundsException();
        }
    }

    /**
     *  Gets the optional Advice data included in the assertion
     * 
     *  Advice can be Strings (assertion references), Assertions, or DOM Elements.
     *
     * @return    An iterator over the advice
     */
    public Iterator getAdvice() {
        return advice.iterator();
    }

    /**
     *  Sets the optional Advice data to include in the assertion
     *
     * @param advice    The Advice to include in the assertion
     * @exception   SAMLException   Raised if unable to construct new Advice objects
     */
    public void setAdvice(Collection advice) throws SAMLException {
        while (this.advice.size() > 0) {
            removeAdvice(0);
        }
        
        if (advice != null) {
            for (Iterator i = advice.iterator(); i.hasNext(); )
                addAdvice(i.next());
        }
    }

    /**
     *  Adds an advice element
     *
     * @param   data    a String, SAMLAssertion, or DOM Element
     * @exception SAMLException     Raised if object is invalid 
     */
    public void addAdvice(Object advice) throws SAMLException {
        if (advice != null && (advice instanceof String || advice instanceof SAMLAssertion ||
                (advice instanceof Element && !((Element)advice).getNamespaceURI().equals(XML.SAML_NS)))) {
            if (root != null) {
                Document doc = root.getOwnerDocument();
                Element a = XML.getFirstChildElement(root, XML.SAML_NS, "Advice");
                if (advice instanceof String && !XML.isEmpty((String)advice)) {
                    Element ref = doc.createElementNS(XML.SAML_NS, "AssertionIDReference");
                    ref.appendChild(doc.createTextNode((String)advice));
                    a.appendChild(ref);
                }
                else if (advice instanceof SAMLAssertion) {
                    a.appendChild(((SAMLAssertion)advice).toDOM(doc));
                }
                else if (advice instanceof Element) {
                    a.appendChild(doc.adoptNode((Element)advice));
                }
            }
            this.advice.add(advice);
        }
        else
            throw new IllegalArgumentException("SAMLAssertion.addAdvice() can only process Strings, SAMLAssertions, or DOM elements from a non-saml namespace");
    }
    
    /**
     *  Removes an advice element by position (zero-based)
     * 
     * @param   index   The position of the element to remove
     */
    public void removeAdvice(int index) throws IndexOutOfBoundsException {
        advice.remove(index);
        if (root != null) {
            Element a = XML.getFirstChildElement(root, XML.SAML_NS, "Advice");
            Element e = XML.getFirstChildElement(a);
            while (e != null && index > 0) {
                e = XML.getNextSiblingElement(e);
                index--;
            }
            if (e != null)
                a.removeChild(e);
            else
                throw new IndexOutOfBoundsException();
        }
    }

    /**
     *  Gets the statements included in the assertion
     *
     * @return    An iterator of SAML statements
     */
    public Iterator getStatements() {
        return statements.iterator();
    }

    /**
     *  Sets the statements to include in the assertion
     *
     * @param statements    The statements to include in the assertion
     * @exception   SAMLException   Raised if unable to construct new statement objects
     */
    public void setStatements(Collection statements) throws SAMLException {
        while (this.statements.size() > 0)
            removeStatement(0);
        
        if (statements != null) {
            for (Iterator i = statements.iterator(); i.hasNext(); )
                addStatement((SAMLStatement)i.next());
        }
    }

    /**
     *  Adds a statement to the assertion
     * 
     * @param s     The statement to add
     * @exception   SAMLException   Raised if an error occurs while adding the statement
     */
    public void addStatement(SAMLStatement s) throws SAMLException {
        if (s != null) {
            if (root != null) {
                unsign();
                if (statements.size() > 0) {
                	Node n = ((SAMLStatement)statements.get(statements.size()-1)).root;
                	root.insertBefore(s.toDOM(root.getOwnerDocument()),n.getNextSibling());
                }
                else
                    root.appendChild(s.toDOM(root.getOwnerDocument()));
            }
            statements.add(s);
        }
        else
            throw new IllegalArgumentException("s cannot be null");
    }

    /**
     *  Removes a statement by position (zero-based)
     * 
     * @param   index   The position of the statement to remove
     */
    public void removeStatement(int index) throws IndexOutOfBoundsException {
        statements.remove(index);
        if (root != null) {
            unsign();
            Element e = XML.getFirstChildElement(root);
            if (XML.isElementNamed(e, XML.SAML_NS, "Conditions"))
                e = XML.getNextSiblingElement(e);
            if (XML.isElementNamed(e, XML.SAML_NS, "Advice"))
                e = XML.getNextSiblingElement(e);
            
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
        	if (xmlns) {
				((Element)root).setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAML_NS);
				((Element)root).setAttributeNS(XML.XMLNS_NS, "xmlns:saml", XML.SAML_NS);
				((Element)root).setAttributeNS(XML.XMLNS_NS, "xmlns:samlp", XML.SAMLP_NS);
				((Element)root).setAttributeNS(XML.XMLNS_NS, "xmlns:xsd", XML.XSD_NS);
				((Element)root).setAttributeNS(XML.XMLNS_NS, "xmlns:xsi", XML.XSI_NS);
        	}
			return root;
        }

        if (issuer == null || issuer.length() == 0 || statements == null || statements.size() == 0)
            throw new MalformedException(SAMLException.RESPONDER, "SAMLAssertion.toDOM() requires issuer and at least one statement");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

        Element assertion = doc.createElementNS(XML.SAML_NS, "Assertion");
        assertion.setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAML_NS);
        assertion.setAttributeNS(XML.XMLNS_NS, "xmlns:saml", XML.SAML_NS);
        assertion.setAttributeNS(XML.XMLNS_NS, "xmlns:samlp", XML.SAMLP_NS);
        assertion.setAttributeNS(null, "MajorVersion", "1");
        assertion.setAttributeNS(null, "MinorVersion", config.getBooleanProperty("org.opensaml.compatibility-mode") ? "0" : "1");
        assertion.setAttributeNS(null, "AssertionID", assertionId);
        assertion.setIdAttributeNS(null, "AssertionID", true);
        assertion.setAttributeNS(null, "Issuer", issuer);
        assertion.setAttributeNS(null, "IssueInstant", formatter.format(issueInstant));

        if (conditions.size() > 0 || notBefore != null || notOnOrAfter != null)
        {
            Element conds = doc.createElementNS(XML.SAML_NS, "Conditions");
            if (notBefore != null)
                conds.setAttributeNS(null, "NotBefore", formatter.format(notBefore));
            if (notOnOrAfter != null)
                conds.setAttributeNS(null, "NotOnOrAfter", formatter.format(notOnOrAfter));
            assertion.appendChild(conds);

            for (Iterator i = conditions.iterator(); i.hasNext(); )
                conds.appendChild(((SAMLCondition)i.next()).toDOM(doc, false));
        }
        
        if (advice.size() > 0) {
            Element a = doc.createElementNS(XML.SAML_NS, "Advice");
            Iterator i = advice.iterator();
            while (i.hasNext()) {
                Object obj = i.next();
                if (obj instanceof String && !XML.isEmpty((String)obj)) {
                    Element ref = doc.createElementNS(XML.SAML_NS, "AssertionIDReference");
                    ref.appendChild(doc.createTextNode((String)obj));
                    a.appendChild(ref);
                }
                else if (obj instanceof SAMLAssertion) {
                    a.appendChild(((SAMLAssertion)obj).toDOM(doc, false));
                }
                else if (obj instanceof Element) {
                    a.appendChild(doc.adoptNode((Element)obj));
                }
            }
            assertion.appendChild(a);
        }

        for (Iterator i = statements.iterator(); i.hasNext(); )
            assertion.appendChild(((SAMLStatement)i.next()).toDOM(doc, false));

        return root = assertion;
    }

    /**
     * @see org.opensaml.SAMLObject#checkValidity()
     */
    public void checkValidity() throws SAMLException {
        if (XML.isEmpty(issuer) || statements.size() == 0)
            throw new MalformedException("Assertion is invalid, must have Issuer and at least one Statement");
    }

    /**
     *  Copies a SAML object such that no dependencies exist between the original
     *  and the copy
     * 
     * @return      The new object
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
        SAMLAssertion dup=(SAMLAssertion)super.clone();
        
        // Clone the embedded objects.
        for (Iterator i = conditions.iterator(); i.hasNext(); )
            dup.conditions.add(((SAMLCondition)i.next()).clone());
        
        for (Iterator i = advice.iterator(); i.hasNext(); ) {
            Object obj=i.next();
            if (obj instanceof String)
                dup.advice.add(obj);
            else if (obj instanceof SAMLAssertion)
                dup.advice.add(((SAMLAssertion)obj).clone());
            else
                dup.advice.add(((Element)obj).cloneNode(true));
        }
        
        for (Iterator i = statements.iterator(); i.hasNext(); )
            dup.statements.add(((SAMLStatement)i.next()).clone());
        
        return dup;
    }
}

