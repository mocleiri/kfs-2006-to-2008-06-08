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

import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.keys.KeyInfo;
import org.w3c.dom.*;

/**
 *  Represents a SAML Subject
 *
 * @author     Scott Cantor
 * @created    March 25, 2002
 */
public class SAMLSubject extends SAMLObject implements Cloneable
{
    protected SAMLNameIdentifier nameId = null;
    protected ArrayList confirmationMethods = new ArrayList();
    protected Element confirmationData = null;
    protected KeyInfo keyInfo = null;

    /**  Type 01 Artifact Confirmation Method Identifier */
    public final static String CONF_ARTIFACT = "urn:oasis:names:tc:SAML:1.0:cm:artifact";

    /**  Assertion Bearer Confirmation Method Identifier */
    public final static String CONF_BEARER = "urn:oasis:names:tc:SAML:1.0:cm:bearer";
    
    /**  Holder of Key Confirmation Method Identifier */
    public final static String CONF_HOLDER_KEY = "urn:oasis:names:tc:SAML:1.0:cm:holder-of-key";
    
    /**  Sender Vouches Confirmation Method Identifier */
    public final static String CONF_SENDER_VOUCHES = "urn:oasis:names:tc:SAML:1.0:cm:sender-vouches";
    
    /**
     *  Default constructor
     */
    public SAMLSubject() {
    }
    
    /**
     *  Builds a subject out of its component parts
     *
     * @param  nameId       Name of subject (optional)
     * @param  confirmationMethods  Confirmation method(s) that bind the subject
     *      to an enclosing assertion (optional)
     * @param  confirmationData     Arbitrary confirmation data DOM (optional)
     * @param  keyInfo              A ds:KeyInfo, either from an xmlsig library or a DOM element
     * @exception  SAMLException    Raised if a subject cannot be constructed
     *      from the supplied information
     */
    public SAMLSubject(SAMLNameIdentifier nameId, Collection confirmationMethods, Element confirmationData, Object keyInfo)
        throws SAMLException {

        this.nameId = nameId;
        this.confirmationData = confirmationData;

        if (confirmationMethods != null)
            this.confirmationMethods.addAll(confirmationMethods);

        if (keyInfo != null) {
            try {
                if (keyInfo instanceof KeyInfo)
                    this.keyInfo = (KeyInfo)keyInfo;
                else if (keyInfo instanceof Element)
                    this.keyInfo = new KeyInfo((Element)keyInfo, null);
                else
                    throw new MalformedException("SAMLSubject() unable to handle the provided keyInfo type");
            }
            catch (XMLSecurityException e) {
                throw new MalformedException("SAMLSubject() caught an XML security exception", e);
            }
        }
    }

    /**
     *  Reconstructs a subject from a DOM tree
     *
     * @param  e                  The root of a DOM tree
     * @exception  SAMLException  Thrown if the object cannot be constructed
     */
    public SAMLSubject(Element e) throws SAMLException {
        fromDOM(e);
    }

    /**
     *  Reconstructs a subject from a stream
     *
     * @param  in                   A stream containing XML
     * @exception  SAMLException  Raised if an exception occurs while constructing
     *                              the object.
     */
    public SAMLSubject(InputStream in) throws SAMLException {
        fromDOM(fromStream(in));
    }

    /**
     * @see org.opensaml.SAMLObject#fromDOM(org.w3c.dom.Element)
     */
    public void fromDOM(Element e) throws SAMLException {
        super.fromDOM(e);

        if (config.getBooleanProperty("org.opensaml.strict-dom-checking") && !XML.isElementNamed(e,XML.SAML_NS,"Subject"))
            throw new MalformedException("SAMLSubject.fromDOM() requires saml:Subject at root");

        // Look for NameIdentifier.
        Element n = XML.getFirstChildElement(e, XML.SAML_NS, "NameIdentifier");
        if (n != null) {
            nameId = new SAMLNameIdentifier(n);
            n = XML.getNextSiblingElement(n);
        }

        // Look for SubjectConfirmation.
        if (n != null && XML.isElementNamed(n, XML.SAML_NS, "SubjectConfirmation")) {
            // Iterate over ConfirmationMethods.
            Element n2 = XML.getFirstChildElement(n);
            while (n2 != null && XML.isElementNamed(n2, XML.SAML_NS, "ConfirmationMethod")) {
                confirmationMethods.add(n2.getFirstChild().getNodeValue());
                n2 = XML.getNextSiblingElement(n2);
            }

            // Extract optional SubjectConfirmationData.
            if (n2 != null && XML.isElementNamed(n2, XML.SAML_NS, "SubjectConfirmationData")) {
                confirmationData = n2;
                n2 = XML.getNextSiblingElement(n2);
            }

            // Extract optional ds:KeyInfo.
            if (n2 != null && XML.isElementNamed(n2, XML.XMLSIG_NS, "KeyInfo")) {
                try {
                    keyInfo = new KeyInfo(n2, null);
                }
                catch (XMLSecurityException ex) {
                    throw new MalformedException("SAMLSubject.fromDOM() caught an XML security exception", ex);
                }
            }
        }
        checkValidity();
    }

    /**
     *  Gets the name identifier of the Subject
     *
     * @return    The name identifier
     */
    public SAMLNameIdentifier getName() {
        return nameId;
    }
    
    /**
     *  Sets the name identifier of the Subject
     * 
     * @param   nameId    The name identifier
     * @exception SAMLException     Raised if the object is invalid
     */
    public void setName(SAMLNameIdentifier nameId) throws SAMLException {
        if (root != null) {
            Element n = XML.getFirstChildElement(root, XML.SAML_NS, "NameIdentifier");
            if (n != null)
                root.removeChild(n);
            if (nameId != null)
                root.insertBefore(nameId.toDOM(root.getOwnerDocument()), root.getFirstChild());
        }
        this.nameId = nameId;
    }

    /**
     *  Gets the confirmation methods of the Subject
     *
     * @return    An iterator of Subject confirmation method URIs
     */
    public Iterator getConfirmationMethods() {
        return confirmationMethods.iterator();
    }
    
    /**
     *  Sets the confirmation methods of the Subject
     * 
     * @param   confirmationMethods     The confirmation methods
     */
    public void setConfirmationMethods(Collection confirmationMethods) {
        while (this.confirmationMethods.size() > 0) {
            removeConfirmationMethod(0);
        }
        
        if (confirmationMethods != null) {
            for (Iterator i = confirmationMethods.iterator(); i.hasNext(); )
                addConfirmationMethod((String)i.next());
        }
    }

    /**
     *  Adds a confirmation method to the Subject
     * 
     * @param   confirmationMethod  The method URI to add
     */
    public void addConfirmationMethod(String confirmationMethod) {
        if (!XML.isEmpty(confirmationMethod)) {
            if (root != null) {
                Element meth = root.getOwnerDocument().createElementNS(XML.SAML_NS, "ConfirmationMethod");
                meth.appendChild(root.getOwnerDocument().createTextNode(confirmationMethod));
                Element sc = XML.getFirstChildElement(root, XML.SAML_NS, "SubjectConfirmation");
                if (sc == null) {
                    Element ident = XML.getFirstChildElement(root, XML.SAML_NS, "NameIdentifier");
                    if (ident == null)
                        sc = (Element)root.insertBefore(
                                root.getOwnerDocument().createElementNS(XML.SAML_NS, "SubjectConfirmation"),
                                root.getFirstChild()
                                );
                    else
                        sc = (Element)root.insertBefore(
                                root.getOwnerDocument().createElementNS(XML.SAML_NS, "SubjectConfirmation"),
                                ident.getNextSibling()
                                );
                    sc.appendChild(meth);                
                }
                else {
                    Element last = XML.getLastChildElement(sc, XML.SAML_NS, "ConfirmationMethod");
                    if (last == null)
                        sc.insertBefore(meth, sc.getFirstChild());
                    else
                        sc.insertBefore(meth, last.getNextSibling());
                }
            }
            confirmationMethods.add(confirmationMethod);
        }
        else
            throw new IllegalArgumentException("confirmationMethod cannot be null or empty");
    }

    /**
     *  Removes a confirmation method by position (zero-based)
     * 
     * @param   index   The position of the method to remove
     */
    public void removeConfirmationMethod(int index) throws IndexOutOfBoundsException {
        confirmationMethods.remove(index);
        if (root != null) {
            Element sc = XML.getFirstChildElement(root, XML.SAML_NS, "SubjectConfirmation");

            if (confirmationMethods.size() == 0 && confirmationData == null && keyInfo == null) {
                root.removeChild(sc);
                return;
            }

            Element e = XML.getFirstChildElement(sc);
            while (e != null && index > 0) {
                e = XML.getNextSiblingElement(e);
                index--;
            }
            if (e != null)
                sc.removeChild(e);
            else
                throw new IndexOutOfBoundsException();
        }
    }

    /**
     *  Gets the optional confirmation data of the Subject
     *
     * @return    The saml:SubjectConfirmationData element
     */
    public Element getConfirmationData() {
        return confirmationData;
    }

    /**
     *  Sets the optional confirmation data of the Subject
     * 
     * @param   confirmationData    The saml:SubjectConfirmationData element
     */
    public void setConfirmationData(Element confirmationData) {
        if (confirmationData != null && !XML.isElementNamed(confirmationData, XML.SAML_NS, "SubjectConfirmationData"))
            throw new IllegalArgumentException("confirmationData must be a saml:SubjectConfirmationData element");
            
        if (root != null) {
            //Clear out the existing value.
            Element sc = XML.getFirstChildElement(root, XML.SAML_NS, "SubjectConfirmation");
            if (this.confirmationData != null) {
                sc.removeChild(this.confirmationData);
                //Still need the element at all?
                if (confirmationData == null && keyInfo == null && confirmationMethods.size() == 0) {
                    root.removeChild(sc);
                }
            }
            
            if (confirmationData != null) {
                //Recreate element if needed.
                if (sc == null) {
                    Element ident = XML.getFirstChildElement(root, XML.SAML_NS, "NameIdentifier");
                    if (ident == null)
                        sc = (Element)root.insertBefore(
                                root.getOwnerDocument().createElementNS(XML.SAML_NS, "SubjectConfirmation"),
                                root.getFirstChild()
                                );
                    else
                        sc = (Element)root.insertBefore(
                                root.getOwnerDocument().createElementNS(XML.SAML_NS, "SubjectConfirmation"),
                                ident.getNextSibling()
                                );
                    sc.appendChild(root.getOwnerDocument().adoptNode(confirmationData));
                }
                else {
                    Element last = XML.getLastChildElement(sc, XML.SAML_NS, "ConfirmationMethod");
                    if (last == null)
                        sc.insertBefore(root.getOwnerDocument().adoptNode(confirmationData), sc.getFirstChild());
                    else
                        sc.insertBefore(root.getOwnerDocument().adoptNode(confirmationData), last.getNextSibling());
                }
            }
        }
        this.confirmationData = confirmationData;
    }

    /**
     *  Gets the ds:KeyInfo DOM that is included in the subject, if any
     * 
     * @return  Root of the ds:KeyInfo DOM
     */
    public Element getKeyInfo() {
        return (keyInfo != null) ? keyInfo.getElement() : null;
    }

    /**
     *  Gets the native library object for the ds:KeyInfo that is included in the subject, if any
     * 
     * @return  The native library object containing the ds:KeyInfo
     */
    public Object getNativeKeyInfo() {
        return keyInfo;
    }

    /**
     *  Sets the ds:KeyInfo of the Subject
     * 
     * @param   keyInfo    The ds:KeyInfo DOM or native library object
     * @exception   SAMLException   Raised if the object is invalid
     */
    public void setKeyInfo(Object keyInfo) throws SAMLException {
        if (keyInfo != null && !(keyInfo instanceof KeyInfo || keyInfo instanceof Element))
            throw new IllegalArgumentException("keyInfo must be a ds:KeyInfo element or a native library object");
        
        //Try and build a native object.
        KeyInfo nativeki = null;
        try {
            if (keyInfo instanceof Element) {
                if (root != null)
                    nativeki = new KeyInfo((Element)root.getOwnerDocument().adoptNode((Node)keyInfo), null);
                else
                    nativeki = new KeyInfo((Element)keyInfo, null);
            }
            else
                nativeki = (KeyInfo)keyInfo;
        }
        catch (XMLSecurityException ex) {
            throw new SAMLException("setKeyInfo() caught an XML security exception", ex);
        }
        
        if (root != null) {
            //Clear out the existing value.
            Element sc = XML.getFirstChildElement(root, XML.SAML_NS, "SubjectConfirmation");
            if (this.keyInfo != null) {
                sc.removeChild(this.keyInfo.getElement());
                //Still need the element at all?
                if (confirmationData == null && keyInfo == null && confirmationMethods.size() == 0) {
                    root.removeChild(sc);
                }
            }
            
            if (keyInfo != null) {
                //Recreate element if needed.
                if (sc == null) {
                    Element ident = XML.getFirstChildElement(root, XML.SAML_NS, "NameIdentifier");
                    if (ident == null)
                        sc = (Element)root.insertBefore(
                                root.getOwnerDocument().createElementNS(XML.SAML_NS, "SubjectConfirmation"),
                                root.getFirstChild()
                                );
                    else
                        sc = (Element)root.insertBefore(
                                root.getOwnerDocument().createElementNS(XML.SAML_NS, "SubjectConfirmation"),
                                ident.getNextSibling()
                                );
                    sc.appendChild(nativeki.getElement());
                }
                else {
                    if (confirmationData == null) {
                        Element last = XML.getLastChildElement(sc, XML.SAML_NS, "ConfirmationMethod");
                        if (last == null)
                            sc.insertBefore(nativeki.getElement(), sc.getFirstChild());
                        else
                            sc.insertBefore(nativeki.getElement(), last.getNextSibling());
                    }
                    else
                        sc.insertBefore(nativeki.getElement(), confirmationData.getNextSibling());
                }
            }
        }
        this.keyInfo = nativeki;
    }

    /**
     *  @see org.opensaml.SAMLObject#toDOM()
     */
    public Node toDOM() throws SAMLException {
        if (confirmationData != null)
            return toDOM(confirmationData.getOwnerDocument());
        else if (keyInfo != null)
            return toDOM(keyInfo.getDocument());
        else
            return super.toDOM();
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

        // This is the meat. Build a new XML instance using our state and the specified Document.
        Element subject = doc.createElementNS(XML.SAML_NS, "Subject");
        if (xmlns)
        	subject.setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAML_NS);

        if (nameId != null)
            subject.appendChild(nameId.toDOM(doc, false));

        if (confirmationMethods.size() > 0) {
            Element conf = doc.createElementNS(XML.SAML_NS, "SubjectConfirmation");
            Iterator i=confirmationMethods.iterator();
            while (i.hasNext())
                conf.appendChild(doc.createElementNS(XML.SAML_NS, "ConfirmationMethod")).appendChild(doc.createTextNode((String)i.next()));
            if (confirmationData != null)
                conf.appendChild(doc.adoptNode(confirmationData));
            if (keyInfo != null)
                conf.appendChild(doc.adoptNode(keyInfo.getElement()));
            subject.appendChild(conf);
        }

        return root = subject;
    }

    /**
     * @see org.opensaml.SAMLObject#checkValidity()
     */
    public void checkValidity() throws SAMLException {
        if (nameId == null && (confirmationMethods == null || confirmationMethods.size() == 0))
            throw new MalformedException("Subject is invalid, requires either NameIdentifier or at least one ConfirmationMethod");
        else if (confirmationData != null && !XML.isElementNamed(confirmationData, XML.SAML_NS, "SubjectConfirmationData"))
            throw new MalformedException("Subject is invalid, requires that confirmation data be a saml:SubjectConfirmationData element");
    }
    
    /**
     *  Copies a SAML object such that no dependencies exist between the original
     *  and the copy
     * 
     * @return      The new object
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
        SAMLSubject dup=(SAMLSubject)super.clone();

        if (nameId != null)
            dup.nameId = (SAMLNameIdentifier)nameId.clone();
        
        dup.confirmationMethods = (ArrayList)confirmationMethods.clone();

        if (confirmationData != null)
            dup.confirmationData = (Element)confirmationData.cloneNode(true);
        
        if (keyInfo != null) {
            try {
                dup.keyInfo = new KeyInfo((Element)keyInfo.getElement().cloneNode(true), null);
            }
            catch (XMLSecurityException e) {
                throw new RuntimeException("SAMLSubject.clone() unable to copy keyInfo");
            }
        }

        return dup;
    }
}
