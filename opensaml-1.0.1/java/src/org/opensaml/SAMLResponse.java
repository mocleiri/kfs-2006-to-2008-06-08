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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;

import org.w3c.dom.*;

/**
 *  Represents a SAML protocol response
 *
 *      Scott Cantor
 * @created    March 18, 2002
 */
public class SAMLResponse extends SAMLSignedObject
{
    protected String responseId = new SAMLIdentifier().toString();
    protected String inResponseTo = null;
    protected Date issueInstant = new Date();
    protected String recipient = null;
    protected ArrayList assertions = new ArrayList();
    protected SAMLException e = null;

    /**
     *  Places the signature into the object's DOM to prepare for signing<p>

     * @throws SAMLException    Thrown if an error occurs while placing the signature
     */
    protected void insertSignature() throws SAMLException {
        root.insertBefore(getSignatureElement(),root.getFirstChild());
    }
    
    /**
     *  Default constructor
     */
    public SAMLResponse() {
    }

    /**
     *  Builds a SAML response out of its component parts<P>
     *
     *  If a signature object is provided, it is annotated with transforms to
     *  sign the entire response and use the SHA-1 digest algorithm.
     *
     * @param  inResponseTo       The request ID that prompted the response, if any
     * @param  recipient          The URL of the intended recipient of the response
     * @param  assertions         The SAML assertion(s) to return in the
     *      response, if any
     * @param  e                  The SAML error status information to return in
     *      the response, if any
     * @exception  SAMLException  Raised if a response cannot be constructed
     *      from the supplied information
     */
    public SAMLResponse(String inResponseTo, String recipient, Collection assertions, SAMLException e) throws SAMLException {
        this.inResponseTo = inResponseTo;
        this.recipient = recipient;
        this.e = e;

        if (assertions != null)
            this.assertions.addAll(assertions);
    }

    /**
     *  Reconstructs a response from a DOM tree
     *
     * @param  e                  The root of a DOM tree
     * @exception  SAMLException  Thrown if the object cannot be constructed
     */
    public SAMLResponse(Element e) throws SAMLException {
        fromDOM(e);
    }

    /**
     *  Reconstructs a response from a stream
     *
     * @param  in                   A stream containing XML
     * @exception  SAMLException  Raised if an exception occurs while constructing
     *                              the object.
     */
    public SAMLResponse(InputStream in) throws SAMLException {
        fromDOM(fromStream(in));
    }
    
    /**
     * @see org.opensaml.SAMLObject#fromDOM(org.w3c.dom.Element)
     */
    public void fromDOM(Element e) throws SAMLException {
        super.fromDOM(e);
        
        if (config.getBooleanProperty("org.opensaml.strict-dom-checking") && !XML.isElementNamed(e,XML.SAMLP_NS,"Response"))
            throw new MalformedException(SAMLException.RESPONDER,"SAMLResponse.fromDOM() requires samlp:Response at root");

        if (Integer.parseInt(e.getAttributeNS(null, "MajorVersion")) != 1)
            throw new MalformedException(SAMLException.VERSION,
                "SAMLResponse() detected incompatible response major version of " + e.getAttributeNS(null, "MajorVersion"));
            
        responseId = e.getAttributeNS(null, "ResponseID");
        e.setIdAttributeNode(e.getAttributeNodeNS(null, "ResponseID"), true);
        inResponseTo = e.getAttributeNS(null, "InResponseTo");
        recipient = e.getAttributeNS(null, "Recipient");

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
        }
        catch (java.text.ParseException ex) {
            throw new MalformedException(SAMLException.RESPONDER, "SAMLResponse() detected an invalid datetime while parsing response", ex);
        }

        Element n = XML.getFirstChildElement(e, XML.SAMLP_NS, "Status");
            
        // Process status, and toss out any errors.
        this.e = SAMLException.getInstance(n);
        Iterator it=this.e.getCodes();
        if (it.hasNext())
            if (!it.next().equals(SAMLException.SUCCESS))
                throw this.e;

        n = XML.getNextSiblingElement(n, XML.SAML_NS, "Assertion");
        while (n != null) {
            assertions.add(new SAMLAssertion(n));
            n = XML.getNextSiblingElement(n, XML.SAML_NS, "Assertion");
        }
    }

    /**
     *  Gets the response ID
     *
     * @return    The response ID
     */
    public String getId() {
        return responseId;
    }

    /**
     *  Sets the response ID
     * 
     *  <b>NOTE:</b> Use this method with caution. Responses must contain unique identifiers
     *  and only specialized applications should need to explicitly assign an identifier.
     *
     * @param   id    The response ID
     */
    public void setId(String id) {
        if (XML.isEmpty(id))
            throw new IllegalArgumentException("id cannot be null");
        responseId = id;
        if (root != null) {
            unsign();
            ((Element)root).getAttributeNodeNS(null,"ResponseID").setNodeValue(id);
        }
    }

    /**
     *  Gets the InResponseTo attribute
     *
     * @return    The InResponseTo value
     */
    public String getInResponseTo() {
        return inResponseTo;
    }

    /**
     *  Sets the InResponseTo attribute
     * 
     * @param   inResponseTo    The InResponseTo value
     */
    public void setInResponseTo(String inResponseTo) {
        this.inResponseTo = inResponseTo;
        if (root != null) {
            ((Element)root).removeAttributeNS(null, "InResponseTo");
            if (!XML.isEmpty(inResponseTo))
                ((Element)root).setAttributeNS(null, "InResponseTo", inResponseTo);
        }
    }

    /**
     *  Gets the issue timestamp of the SAML response
     *
     * @return    The issue timestamp
     */
    public Date getIssueInstant() {
        return issueInstant;
    }

    /**
     *  Sets the issue timestamp of the response
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
     *  Gets the Recipient attribute of the SAML response
     *
     * @return    The Recipient value
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     *  Sets the Recipient attribute
     * 
     * @param   recipient    The Recipient value
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
        if (root != null) {
            ((Element)root).removeAttributeNS(null, "Recipient");
            if (!XML.isEmpty(recipient))
                ((Element)root).setAttributeNS(null, "Recipient", recipient);
        }
    }

    /**
     *  Gets the SAML assertions contained in the response, if any
     *
     * @return    The assertions in the response
     */
    public Iterator getAssertions() {
        return assertions.iterator();
    }
    
    /**
     *  Sets the SAML assertions to include in the response
     *
     * @param assertions   The assertions to include
     * @exception SAMLException     Raised if the assertions are invalid
     */
    public void setAssertions(Collection assertions) throws SAMLException {
        while (this.assertions.size() > 0)
            removeAssertion(0);
        
        if (assertions != null) {
            for (Iterator i = assertions.iterator(); i.hasNext(); )
                addAssertion((SAMLAssertion)i.next());
        }
    }

    /**
     *  Adds an assertion to the response
     * 
     * @param   assertion   The assertion to add
     * @exception   SAMLException   Raised if the assertion is invalid
     */
    public void addAssertion(SAMLAssertion assertion) throws SAMLException {
        if (assertion != null) {
            if (root != null) {
                unsign();
                
                Element last = XML.getLastChildElement(root, XML.SAML_NS, "Assertion");
                if (last == null)
                    root.insertBefore(
                        assertion.toDOM(root.getOwnerDocument()),
                        XML.getFirstChildElement(root, XML.SAMLP_NS, "Status").getNextSibling()
                        );
                else
                    root.insertBefore(assertion.toDOM(root.getOwnerDocument()), last.getNextSibling());
            }
            assertions.add(assertion);
        }
        else
            throw new IllegalArgumentException("assertion cannot be null");
    }

    /**
     *  Removes assertion by position (zero-based)
     * 
     * @param   index   The position of the assertion to remove
     */
    public void removeAssertion(int index) throws IndexOutOfBoundsException {
        assertions.remove(index);
        if (root != null) {
            unsign();
            Element e = XML.getFirstChildElement(root, XML.SAML_NS, "Assertion");
            while (e != null && index > 0) {
                e = XML.getNextSiblingElement(e, XML.SAML_NS, "Assertion");
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
				((Element)root).setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAMLP_NS);
				((Element)root).setAttributeNS(XML.XMLNS_NS, "xmlns:saml", XML.SAML_NS);
				((Element)root).setAttributeNS(XML.XMLNS_NS, "xmlns:samlp", XML.SAMLP_NS);
				((Element)root).setAttributeNS(XML.XMLNS_NS, "xmlns:xsi", XML.XSI_NS);
				((Element)root).setAttributeNS(XML.XMLNS_NS, "xmlns:xsd", XML.XSD_NS);
			}
			return root;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

        // Generate a SAML Response.
        Element r = doc.createElementNS(XML.SAMLP_NS, "Response");
        if (xmlns)
        	r.setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAMLP_NS);
        r.setAttributeNS(XML.XMLNS_NS, "xmlns:saml", XML.SAML_NS);
        r.setAttributeNS(XML.XMLNS_NS, "xmlns:samlp", XML.SAMLP_NS);
        r.setAttributeNS(null, "MajorVersion", "1");
        r.setAttributeNS(null, "MinorVersion", config.getBooleanProperty("org.opensaml.compatibility-mode") ? "0" : "1");
        r.setAttributeNS(null, "ResponseID", responseId);
        r.setIdAttributeNS(null, "ResponseID", true);
        if (!XML.isEmpty(inResponseTo))
            r.setAttributeNS(null, "InResponseTo", inResponseTo);
        r.setAttributeNS(null, "IssueInstant", formatter.format(issueInstant));
        if (!XML.isEmpty(recipient))
            r.setAttributeNS(null, "Recipient", recipient);

        // Fill in a status.
        if (e!=null)
            r.appendChild(e.toDOM(doc, false));
        else {
            Element status = doc.createElementNS(XML.SAMLP_NS, "Status");
            Element code = doc.createElementNS(XML.SAMLP_NS, "StatusCode");
            code.setAttributeNS(null, "Value", "samlp:" + SAMLException.SUCCESS.getLocalName());
            status.appendChild(code);
            r.appendChild(status);
        }

        // Embed the assertions.
        Iterator i = assertions.iterator();
        while (i.hasNext())
            r.appendChild(((SAMLAssertion)i.next()).toDOM(doc));

        return root = r;
    }

    /**
     *  Copies a SAML object such that no dependencies exist between the original
     *  and the copy
     * 
     * @return      The new object
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
        SAMLResponse dup=(SAMLResponse)super.clone();
        
        // Clone the embedded objects.
        if (e != null)
            dup.e = (SAMLException)e.clone();
        for (Iterator i = assertions.iterator(); i.hasNext(); )
            dup.assertions.add(((SAMLAssertion)i.next()).clone());
        
        return dup;
    }
}

