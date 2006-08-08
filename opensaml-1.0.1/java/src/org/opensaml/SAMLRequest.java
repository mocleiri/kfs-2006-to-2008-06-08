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
 *  Represents a SAML protocol request
 *
 * @author     Scott Cantor
 * @created    March 30, 2002
 */
public class SAMLRequest extends SAMLSignedObject implements Cloneable
{
    protected String requestId = new SAMLIdentifier().toString();
    protected Date issueInstant = new Date();
    protected ArrayList respondWiths = new ArrayList();
    protected SAMLQuery query = null;
    protected ArrayList assertionIdRefs = new ArrayList();
    protected ArrayList artifacts = new ArrayList();

    /**
     *  Places the signature into the object's DOM to prepare for signing<p>

     * @throws SAMLException    Thrown if an error occurs while placing the signature
     */
    protected void insertSignature() throws SAMLException {
        // Goes after any RespondWith elements.
        Element n=XML.getFirstChildElement(root);
        while (n != null && XML.isElementNamed(n, XML.SAMLP_NS, "RespondWith"))
            n = XML.getNextSiblingElement(n);
        root.insertBefore(getSignatureElement(),n);
    }

    /**
     *  Default constructor
     */
    public SAMLRequest() {
    }

    /**
     *  Builds a SAML request out of its component parts<p>
     * 
     *  The last three parameters are mutually exclusive of each other, and
     *  two of them should be null. They will be evaluated in order.</p>
     *
     * @param  respondWiths         The types of SAML statements (as QNames) to
     *      accept in the query response
     * @param  query                A query to place in the request
     * @param  assertionIdRefs      A set of AssertionIDRef values to dereference
     * @param  artifacts            A set of artifacts to dereference
     * @exception  SAMLException    Thrown if a request cannot be constructed from
     *      the supplied information
     */
    public SAMLRequest(Collection respondWiths, SAMLQuery query, Collection assertionIdRefs, Collection artifacts)
        throws SAMLException {

        this.query = query;
        if (assertionIdRefs != null)
            this.assertionIdRefs.addAll(assertionIdRefs);
        if (artifacts != null)
            this.artifacts.addAll(artifacts);
        if (respondWiths != null)
            this.respondWiths.addAll(respondWiths);
    }

    /**
     *  Reconstructs a request from a DOM tree
     *
     * @param  e                  The root of a DOM tree
     * @exception  SAMLException  Thrown if the object cannot be constructed
     */
    public SAMLRequest(Element e) throws SAMLException {
        fromDOM(e);
    }

    /**
     *  Reconstructs a request from a stream
     *
     * @param  in                   A stream containing XML
     * @exception  SAMLException  Raised if an exception occurs while constructing
     *                              the object.
     */
    public SAMLRequest(InputStream in) throws SAMLException {
        fromDOM(fromStream(in));
    }
    
    /**
     * @see org.opensaml.SAMLObject#fromDOM(org.w3c.dom.Element)
     */
    public void fromDOM(Element e) throws SAMLException {
        super.fromDOM(e);
        
        if (config.getBooleanProperty("org.opensaml.strict-dom-checking") && !XML.isElementNamed(e,XML.SAMLP_NS,"Request"))
            throw new MalformedException(SAMLException.RESPONDER,"SAMLRequest.fromDOM() requires samlp:Request at root");

        if (Integer.parseInt(e.getAttributeNS(null, "MajorVersion")) != 1)
            throw new MalformedException(SAMLException.VERSION, "SAMLRequest() detected incompatible request major version of " +
                e.getAttributeNS(null, "MajorVersion"));

        requestId = e.getAttributeNS(null, "RequestID");
        e.setIdAttributeNode(e.getAttributeNodeNS(null, "RequestID"), true);

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
            throw new MalformedException(SAMLException.REQUESTER, "SAMLRequest() detected an invalid datetime while parsing request", ex);
        }
        
        // Process RespondWith elements.
        Element n = XML.getFirstChildElement(e);
        while (n != null && XML.isElementNamed(n, XML.SAMLP_NS, "RespondWith")) {
            respondWiths.add(QName.getQNameTextNode((Text)n.getFirstChild()));
            n = XML.getNextSiblingElement(n);
        }
        
        // Skip signature.
        if (XML.isElementNamed(n, XML.XMLSIG_NS, "Signature"))
            n = XML.getNextSiblingElement(n);
        
        // We're pointed at one of the request content options...
        if (XML.isElementNamed(n, XML.SAML_NS, "AssertionIDReference")) {
            while (n != null) {
                assertionIdRefs.add(n.getFirstChild().getNodeValue());
                n = XML.getNextSiblingElement(n, XML.SAML_NS, "AssertionIDReference");
            }
        }
        else if (XML.isElementNamed(n, XML.SAMLP_NS, "AssertionArtifact")) {
            while (n != null) {
                artifacts.add(n.getFirstChild().getNodeValue());
                n = XML.getNextSiblingElement(n, XML.SAMLP_NS, "AssertionArtifact");
            }
        }
        else {
            query = SAMLQuery.getInstance(n);
        }
        
        checkValidity();
    }

    /**
     *  Gets the request ID
     *
     * @return    The request ID
     */
    public String getId() {
        return requestId;
    }

    /**
     *  Sets the request ID
     * 
     *  <b>NOTE:</b> Use this method with caution. Requests must contain unique identifiers
     *  and only specialized applications should need to explicitly assign an identifier.
     *
     * @param   id    The request ID
     */
    public void setId(String id) {
        if (XML.isEmpty(id))
            throw new IllegalArgumentException("id cannot be null");
        requestId = id;
        if (root != null) {
            unsign();
            ((Element)root).getAttributeNodeNS(null,"RequestID").setNodeValue(id);
        }
    }

    /**
     *  Gets the issue timestamp of the request
     *
     * @return    The issue timestamp
     */
    public Date getIssueInstant() {
        return issueInstant;
    }

    /**
     *  Sets the issue timestamp of the request
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
     *  Gets the types of statements the requester is prepared to accept
     *
     * @return    An iterator of QNames representing statement types
     */
    public Iterator getRespondWiths() {
        return respondWiths.iterator();
    }

    /**
     *  Sets the types of statements the requester is prepared to accept
     *
     * @param   respondWiths    An iterator of QNames representing statement types
     */
    public void setRespondWiths(Collection respondWiths) {
        while (this.respondWiths.size() > 0)
            removeRespondWith(0);
        
        if (respondWiths != null) {
            for (Iterator i = respondWiths.iterator(); i.hasNext(); )
                addRespondWith((QName)i.next());
        }
    }

    /**
     *  Adds a statement type to the request
     * 
     * @param respondWith     The type to add
     */
    public void addRespondWith(QName respondWith) {
        if (respondWith != null) {
            if (root != null) {
                unsign();
                
                Element rw = root.getOwnerDocument().createElementNS(XML.SAMLP_NS, "RespondWith");
                String rwns = respondWith.getNamespaceURI();
                if (rwns == null)
                    rwns = "";
                if (!XML.SAML_NS.equals(rwns)) {
                    rw.setAttributeNS(XML.XMLNS_NS, "xmlns:rw", rwns);
                    rwns = "rw:";
                }
                else
                    rwns = "saml:";
                rw.appendChild(root.getOwnerDocument().createTextNode(rwns + respondWith.getLocalName()));
                
                Element last = XML.getLastChildElement(root, XML.SAMLP_NS, "RespondWith");
                if (last == null)
                    root.insertBefore(rw, root.getFirstChild());
                else
                    root.insertBefore(rw, last.getNextSibling());
            }
            respondWiths.add(respondWith);
        }
        else
            throw new IllegalArgumentException("respondWith cannot be null");
    }

    /**
     *  Removes a statement type by position (zero-based)
     * 
     * @param   index   The position of the statement type to remove
     */
    public void removeRespondWith(int index) throws IndexOutOfBoundsException {
        respondWiths.remove(index);
        if (root != null) {
            unsign();
            Element e = XML.getFirstChildElement(root, XML.SAMLP_NS, "RespondWith");
            while (e != null && index > 0) {
                e = XML.getNextSiblingElement(e, XML.SAMLP_NS, "RespondWith");
                index--;
            }
            if (e != null)
                root.removeChild(e);
            else
                throw new IndexOutOfBoundsException();
        }
    }

    /**
     *  Gets the query contained within the request
     *
     * @return    The query in the request
     */
    public SAMLQuery getQuery() {
        return query;
    }

    /**
     *  Sets the query contained within the request
     *
     * @param query    The query for the request
     * @exception   SAMLException   Raised if the query is invalid
     */
    public void setQuery(SAMLQuery query) throws SAMLException {
        if (query != null) {
            setAssertionIdRefs(null);
            setArtifacts(null);
        }

        if (root != null) {
            unsign();
            Element last = XML.getLastChildElement(root, XML.SAMLP_NS, "RespondWith");
            
            if (query != null) {
                if (this.query != null)
                    root.replaceChild(query.toDOM(root.getOwnerDocument()), this.query.root);
                else {
                    if (last == null)
                        root.insertBefore(query.toDOM(root.getOwnerDocument()), root.getFirstChild());
                    else
                        root.insertBefore(query.toDOM(root.getOwnerDocument()), last.getNextSibling());
                }
            }
            else {
                root.removeChild(this.query.root);
            }
        }
        this.query = query;
    }

    /**
     * Gets the assertion ID references contained within the request
     * 
     * @return An iterator over the references
     */
    public Iterator getAssertionIdRefs() {
        return assertionIdRefs.iterator();
    }
    
    /**
     *  Adds an assertion ID reference to the request
     * 
     * @param   ref     The reference to add
     */
    public void addAssertionIdRef(String ref) {
        if (XML.isEmpty(ref))
            throw new IllegalArgumentException("ref cannot be null or empty");
        try {
            setQuery(null);
            setArtifacts(null);
        }
        catch (SAMLException ex) {
        }
        
        if (root != null) {
            unsign();
            Document doc = root.getOwnerDocument();
            Element e = doc.createElementNS(XML.SAML_NS, "saml:AssertionIDReference");
            e.appendChild(doc.createTextNode(ref));
            Element last = XML.getLastChildElement(root, XML.SAMLP_NS, "RespondWith");
            if (last == null)
                root.insertBefore(e, root.getFirstChild());
            else
                root.insertBefore(e, last.getNextSibling());
        }
        assertionIdRefs.add(ref);
    }

    /**
     *  Sets the assertion ID references contained within the request 
     *
     * @param   refs    The references to include
     */
    public void setAssertionIdRefs(Collection refs) {
        while (this.assertionIdRefs.size() > 0)
            removeAssertionIdRef(0);
        
        if (refs != null) {
            for (Iterator i = refs.iterator(); i.hasNext(); )
                addAssertionIdRef((String)i.next());
        }
    }

    /**
     *  Removes an assertion reference by position (zero-based)
     * 
     * @param   index   The position of the reference to remove
     */
    public void removeAssertionIdRef(int index) throws IndexOutOfBoundsException {
        assertionIdRefs.remove(index);
        if (root != null) {
            unsign();
            Element e = XML.getFirstChildElement(root, XML.SAML_NS, "AssertionIDReference");
            while (e != null && index > 0) {
                e = XML.getNextSiblingElement(e, XML.SAML_NS, "AssertionIDReference");
                index--;
            }
            if (e != null)
                root.removeChild(e);
            else
                throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Gets the artifacts contained within the request
     * 
     * @return An iterator over the artifacts
     */
    public Iterator getArtifacts() {
        return artifacts.iterator();
    }

    /**
     *  Sets the artifacts contained within the request 
     *
     * @param   refs    The artifacts to include
     */
    public void setArtifacts(Collection artifacts) {
        while (this.artifacts.size() > 0)
            removeArtifact(0);
        
        if (artifacts != null) {
            for (Iterator i = artifacts.iterator(); i.hasNext(); )
                addArtifact((String)i.next());
        }
    }

    /**
     *  Adds an artifact to the request
     * 
     * @param   artifact     The artifact to add
     */
    public void addArtifact(String artifact) {
        if (XML.isEmpty(artifact))
            throw new IllegalArgumentException("artifact cannot be null or empty");
        try {
            setQuery(null);
            setAssertionIdRefs(null);
        }
        catch (SAMLException ex) {
        }
        
        if (root != null) {
            unsign();
            Document doc = root.getOwnerDocument();
            Element e = doc.createElementNS(XML.SAMLP_NS, "AssertionArtifact");
            e.appendChild(doc.createTextNode(artifact));
            Element last = XML.getLastChildElement(root, XML.SAMLP_NS, "RespondWith");
            if (last == null)
                root.insertBefore(e, root.getFirstChild());
            else
                root.insertBefore(e, last.getNextSibling());
        }
        artifacts.add(artifact);
    }

    /**
     *  Removes an artifact by position (zero-based)
     * 
     * @param   index   The position of the artifact to remove
     */
    public void removeArtifact(int index) throws IndexOutOfBoundsException {
        artifacts.remove(index);
        if (root != null) {
            unsign();
            Element e = XML.getFirstChildElement(root, XML.SAMLP_NS, "AssertionArtifact");
            while (e != null && index > 0) {
                e = XML.getNextSiblingElement(e, XML.SAMLP_NS, "AssertionArtifact");
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

        // Generate a SAML Request.
        Element r = doc.createElementNS(XML.SAMLP_NS, "Request");
        if (xmlns)
        	r.setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAMLP_NS);
        r.setAttributeNS(XML.XMLNS_NS, "xmlns:samlp", XML.SAMLP_NS);
        r.setAttributeNS(XML.XMLNS_NS, "xmlns:saml", XML.SAML_NS);
        r.setAttributeNS(null, "MajorVersion", "1");
        r.setAttributeNS(null, "MinorVersion", config.getBooleanProperty("org.opensaml.compatibility-mode") ? "0" : "1");
        r.setAttributeNS(null, "RequestID", requestId);
        r.setIdAttributeNS(null, "RequestID", true);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        r.setAttributeNS(null, "IssueInstant", formatter.format(issueInstant));

        Iterator i=respondWiths.iterator();
        while (i.hasNext()) {
            QName qn=(QName)i.next();
            Element rw = doc.createElementNS(XML.SAMLP_NS, "RespondWith");
            String rwns = qn.getNamespaceURI();
            if (rwns==null)
                rwns="";
            if (!XML.SAML_NS.equals(rwns)) {
                rw.setAttributeNS(XML.XMLNS_NS, "xmlns:rw", rwns);
                rwns="rw:";
            }
            else
                rwns="saml:";
            rw.appendChild(doc.createTextNode(rwns + qn.getLocalName()));
            r.appendChild(rw);
        }

        if (query != null)
            r.appendChild(query.toDOM(doc, false));
        else if (assertionIdRefs.size() > 0) {
            i=assertionIdRefs.iterator();
            while (i.hasNext())
                r.appendChild(doc.createElementNS(XML.SAML_NS,"saml:AssertionIDReference")).appendChild(doc.createTextNode((String)i.next()));
        }
        else {
            i=artifacts.iterator();
            while (i.hasNext())
                r.appendChild(doc.createElementNS(XML.SAMLP_NS,"AssertionArtifact")).appendChild(doc.createTextNode((String)i.next()));
        }

        return root = r;
    }

    /**
     * @see org.opensaml.SAMLObject#checkValidity()
     */
    public void checkValidity() throws SAMLException {
        if (query == null && assertionIdRefs.size() == 0 && artifacts.size() == 0)
            throw new MalformedException("Request is invalid, must have Query, assertion references, or artifacts");
    }

    /**
     *  Copies a SAML object such that no dependencies exist between the original
     *  and the copy
     * 
     * @return      The new object
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
        SAMLRequest dup=(SAMLRequest)super.clone();
        
        dup.respondWiths = (ArrayList)respondWiths.clone();
        dup.query = (SAMLQuery)query.clone();
        dup.assertionIdRefs = (ArrayList)assertionIdRefs.clone();
        dup.artifacts = (ArrayList)artifacts.clone();
        
        return dup;
    }
}

