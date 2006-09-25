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
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *  Encapsulates a general SAML error. <p>
 *
 *  If the runtime needs to pass through other types of exceptions, it must wrap
 *  those exceptions in a SAMLException or an exception derived from a
 *  SAMLxception.</p><p>
 * 
 *  Ideally, this would also inherit from SAMLObject, but Java is limited to single
 *  inheritance, so there's no way to inherit the functionality; instead we reimplement
 *  the SAMLObject code here. (Note that interfaces!=multiple inheritance and don't
 *  solve the problem.)
 *
 *      Scott Cantor
 * @created    December 2, 2001
 */
public class SAMLException extends Exception implements Cloneable
{
    /**  SAML Success status code */
    public final static QName SUCCESS = new QName(XML.SAMLP_NS, "Success");

    /**  SAML Requester status code */
    public final static QName REQUESTER = new QName(XML.SAMLP_NS, "Requester");

    /**  SAML Responder status code */
    public final static QName RESPONDER = new QName(XML.SAMLP_NS, "Responder");

    /**  SAML Version Mismatch status code */
    public final static QName VERSION = new QName(XML.SAMLP_NS, "VersionMismatch");

    /**  Status message embedded in exception */
    protected String msg = null;
    
    /**  Embedded exception, if any */
    protected Exception e = null;
    
    /**  Collection of status codes (QNames) */
    protected ArrayList codes = new ArrayList();
    
    /**  Root node of a DOM tree capturing the object */
    protected Node root = null;

    /** Class-specific logging object */
    protected Logger log = Logger.getLogger(this.getClass());
    
	/** OpenSAML configuration */
    protected SAMLConfig config = SAMLConfig.instance();

    /**
     *  Locates an implementation class for an exception and constructs it based
     *  on the DOM provided.
     * 
     * @param e     The root of a DOM containing the SAML exception
     * @return SAMLException    A constructed exception object
     * 
     * @throws SAMLException    Thrown if an error occurs while constructing the object
     */
    public static SAMLException getInstance(Element e) throws SAMLException {
        NDC.push("getInstance");
        
        if (e == null)
            throw new MalformedException(SAMLException.RESPONDER, "SAMLException.getInstance() given an empty DOM");
       
        try {
            // Find the StatusDetail element.
            Element detail = XML.getLastChildElement(e, XML.SAMLP_NS, "StatusDetail");
            if (detail != null) {
                // Look for the special OpenSAML ExceptionClass element.
                Element eclass = XML.getFirstChildElement(detail, XML.OPENSAML_NS, "ExceptionClass");
                if (eclass != null && eclass.getFirstChild() != null && eclass.getFirstChild().getNodeType() == Node.TEXT_NODE)
                {
                    String className = eclass.getFirstChild().getNodeValue();
                    if (className != null && className.length() > 0) {
                        Class implementation = Class.forName(className);
                        Class[] paramtypes = {Class.forName("org.w3c.dom.Element")};
                        Object[] params = {e};
                        Constructor ctor = implementation.getDeclaredConstructor(paramtypes);
                        return (SAMLException)ctor.newInstance(params);
                    }
                }
            }
        }
        catch (ClassNotFoundException ex) {
            Category.getInstance("SAMLException").error("unable to locate implementation class for exception: " + ex.getMessage());
        }
        catch (NoSuchMethodException ex) {
            Category.getInstance("SAMLException").error("unable to bind to constructor for exception: " + ex.getMessage());
        }
        catch (InstantiationException ex) {
            Category.getInstance("SAMLException").error("unable to build implementation object for exception: " + ex.getMessage());
        }
        catch (IllegalAccessException ex) {
            Category.getInstance("SAMLException").error("unable to access implementation of exception: " + ex.getMessage());
        }
        catch (java.lang.reflect.InvocationTargetException ex) {
            ex.printStackTrace();
            Category.getInstance("SAMLException").error("caught unknown exception while building exception object: " + ex.getTargetException().getMessage());
        }
        finally {
            NDC.pop();
        }
        
        return new SAMLException(e);
    }

    /**
     *  Locates an implementation class for an exception and constructs it based
     *  on the stream provided.
     * 
     * @param in     The stream to deserialize from
     * @return SAMLException    A constructed exception object
     * 
     * @throws SAMLException    Thrown if an error occurs while constructing the object
     */
    public static SAMLException getInstance(InputStream in) throws SAMLException {
        try {
            Document doc = XML.parserPool.parse(in);
            return getInstance(doc.getDocumentElement());
        }
        catch (SAXException e) {
            NDC.push("getInstance");
            Category.getInstance("SAMLException").error("caught an exception while parsing a stream:\n" + e.getMessage());
            NDC.pop();
            throw new MalformedException("SAMLException.getInstance() caught exception while parsing a stream",e);
        }
        catch (java.io.IOException e) {
            NDC.push("getInstance");
            Category.getInstance("SAMLException").error("caught an exception while parsing a stream:\n" + e.getMessage());
            NDC.pop();
            throw new MalformedException("SAMLException.getInstance() caught exception while parsing a stream",e);
        }
    }

    /**
     *  Initializes an exception from a DOM tree
     *
     * @param  e                   The root of a DOM tree
     * @exception  SAMLException   Raised if an exception occurs while constructing
     *                              the object.
     */
    protected SAMLException(Element e) throws SAMLException {
        fromDOM(e);
    }

    /**
     *  Creates a new SAMLException
     *
     * @param  msg    The detail message
     */
    public SAMLException(String msg) {
        super(msg);
        this.msg = msg;
    }

    /**
     *  Creates a new SAMLException
     *
     * @param  msg    The detail message
     * @param  e      The exception to be wrapped in a SAMLException
     */
    public SAMLException(String msg, Exception e) {
        super(msg);
        this.msg = msg;
        this.e = e;
    }

    /**
     *  Creates a new SAMLException
     *
     * @param  codes  A collection of QNames
     */
    public SAMLException(Collection codes) {
        if (codes != null)
            this.codes.addAll(codes);
    }

    /**
     *  Creates a new SAMLException
     *
     * @param  codes  A collection of QNames
     * @param  msg    The detail message
     */
    public SAMLException(Collection codes, String msg) {
        super(msg);
        this.msg = msg;
        if (codes != null)
            this.codes.addAll(codes);
    }

    /**
     *  Creates a new SAMLException wrapping an existing exception <p>
     *
     *  The existing exception will be embedded in the new one, and its message
     *  will become the default message for the SAMLException.</p>
     *
     * @param  codes  A collection of QNames
     * @param  e      The exception to be wrapped in a SAMLException
     */
    public SAMLException(Collection codes, Exception e) {
        this(codes, (String)null);
        this.e = e;
    }

    /**
     *  Creates a new SAMLException from an existing exception. <p>
     *
     *  The existing exception will be embedded in the new one, but the new
     *  exception will have its own message.</p>
     *
     * @param  codes  A collection of QNames
     * @param  msg    The detail message
     * @param  e      The exception to be wrapped in a SAMLException
     */
    public SAMLException(Collection codes, String msg, Exception e) {
        this(codes,msg);
        this.e = e;
    }

    /**
     *  Creates a new SAMLException
     *
     * @param  code   A status code
     */
    public SAMLException(QName code) {
        if (code != null)
            codes.add(code);
    }

    /**
     *  Creates a new SAMLException
     *
     * @param  code   A status code
     * @param  msg    The detail message
     */
    public SAMLException(QName code, String msg) {
        this(msg);
        if (code != null)
            codes.add(code);
    }

    /**
     *  Creates a new SAMLException wrapping an existing exception <p>
     *
     *  The existing exception will be embedded in the new one, and its message
     *  will become the default message for the SAMLException.</p>
     *
     * @param  code   A status code
     * @param  e      The exception to be wrapped in a SAMLException
     */
    public SAMLException(QName code, Exception e) {
        this(code, (String)null);
        this.e = e;
    }

    /**
     *  Creates a new SAMLException from an existing exception. <p>
     *
     *  The existing exception will be embedded in the new one, but the new
     *  exception will have its own message.</p>
     *
     * @param  code   A status code
     * @param  msg    The detail message
     * @param  e      The exception to be wrapped in a SAMLException
     */
    public SAMLException(QName code, String msg, Exception e) {
        this(code, msg);
        this.e = e;
    }

    
    /**
     *  Handles initialization of exceptions from a DOM element
     *
     * @param  e                
     * @exception  SAMLException  Raised if an exception occurs while initializing the object
     */
    public void fromDOM(Element e) throws SAMLException {
        if (e==null)
            throw new MalformedException("SAMLException.fromDOM() given an empty DOM");
        root = e;
        
        if (config.getBooleanProperty("org.opensaml.strict-dom-checking") && !XML.isElementNamed(e,XML.SAMLP_NS,"Status"))
            throw new MalformedException(SAMLException.RESPONDER,"SAMLException.fromDOM() requires samlp:Status at root");
    
        // Extract the status message.
        Element m = XML.getFirstChildElement(e, XML.SAMLP_NS, "StatusMessage");
        if (m!=null && m.getFirstChild()!=null)
            msg=m.getFirstChild().getNodeValue();
    
        NodeList nlist=e.getElementsByTagNameNS(XML.SAMLP_NS,"StatusCode");
        for (int i=0; nlist!=null && i<nlist.getLength(); i++)
        {
            QName qptr=QName.getQNameAttribute((Element)nlist.item(i),null,"Value");
            if (qptr!=null)
                codes.add(qptr);
            else
                throw new MalformedException(SAMLException.RESPONDER,"SAMLException.fromDOM() unable to evaluate QName Value");
        }
    }

    /**
     *  Serializes the XML representation of a SAML Status to a stream
     *
     * @param  out                      Stream to use for output
     * @exception  java.io.IOException  Raised if an I/O problem is detected
     * @exception   SAMLException       Raised if the object is invalid
     */
    public void toStream(OutputStream out) throws java.io.IOException, SAMLException {
        try {
            Canonicalizer c = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_EXCL_OMIT_COMMENTS);
            out.write(c.canonicalizeSubtree(toDOM()));
        }
        catch (InvalidCanonicalizerException e) {
            throw new java.io.IOException(e.getMessage());
        }
        catch (CanonicalizationException e) {
            throw new java.io.IOException(e.getMessage());
        }
    }

	/**
	 *  Transforms the object into a DOM tree using an existing document context
	 *
	 * @param  doc               A Document object to use in manufacturing the tree
	 * @param  xmlns             Include namespace(s) on root element?
	 * @return                   Root element node of the DOM tree capturing the object
	 * @exception  SAMLException Raised if the object is incompletely defined
	 */
    public Node toDOM(Document doc, boolean xmlns) throws SAMLException {
        if (root != null) {
            // If the DOM tree is already generated, compare the Documents.
            if (root.getOwnerDocument() != doc) {
                root = doc.adoptNode(root);
            }
        }
        else {
            // Construct a Status element.
            Element s = doc.createElementNS(XML.SAMLP_NS, "Status");
			s.setAttributeNS(XML.XMLNS_NS, "xmlns:samlp", XML.SAMLP_NS);

            if (codes == null || codes.isEmpty()) {
                Element sc = doc.createElementNS(XML.SAMLP_NS, "StatusCode");
                sc.setAttributeNS(null, "Value", SAMLException.RESPONDER.getLocalName());
                s.appendChild(sc);
            }
            else {
                Node base = s;
                Iterator i = codes.iterator();
                while(i.hasNext()) {
                    QName qcode = (QName)i.next();
                    Element sc = doc.createElementNS(XML.SAMLP_NS, "StatusCode");
                    String codens = qcode.getNamespaceURI();
                    if (!codens.equals(XML.SAMLP_NS)) {
                        sc.setAttributeNS(XML.XMLNS_NS, "xmlns:code", codens);
                        codens = "code:";
                    }
                    else
                        codens = "samlp:";
                    sc.setAttributeNS(null, "Value", codens + qcode.getLocalName());
                    base = base.appendChild(sc);
                }
            }
        
            if (!XML.isEmpty(getMessage())) {
                Element msg = doc.createElementNS(XML.SAMLP_NS, "StatusMessage");
                msg.appendChild(doc.createTextNode(getMessage()));
                s.appendChild(msg);
            }

            if (!(this instanceof SAMLException)) {
                Element detail=doc.createElementNS(XML.SAMLP_NS, "StatusDetail");
                detail.appendChild(doc.createElementNS(XML.OPENSAML_NS, "ExceptionClass")).appendChild(doc.createTextNode(this.getClass().getName()));
            }
            
            root = s;
        }
        
        if (xmlns)
        	((Element)root).setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAMLP_NS);
        return root;
    }

    /**
     *  Transforms the object into a DOM tree without an existing document
     *  context
     *
	 * @param  xmlns             Include namespace(s) on root element?
     * @return      Root element node of the DOM tree capturing the object
     * @exception  SAMLException  Raised if the exception is invalid
     */
    public Node toDOM(boolean xmlns) throws SAMLException {
        if (root != null)
            return root;

        // No existing document object, so we have to create one.
        return toDOM(XML.parserPool.newDocument(), xmlns);
    }

	/**
	 *  Transforms the object into a DOM tree using an existing document context,
	 *  including namespace declarations
	 *
	 * @param  doc               A Document object to use in manufacturing the tree
	 * @return                   Root element node of the DOM tree capturing the object
	 * @exception  SAMLException  Raised if the exception is invalid
	 */
	public Node toDOM(Document doc) throws SAMLException {
		return toDOM(doc, true);
	}

	/**
	 *  Transforms the object into a DOM tree without an existing document context,
	 *  including namespace declarations
	 *
	 * @return      Root element node of the DOM tree capturing the object
	 * @exception  SAMLException  Raised if the exception is invalid
	 */
	public Node toDOM() throws SAMLException {
		return toDOM(true);
	}

    /**
     *  Gets the status or fault code QNames
     *
     * @return    An iterator of QNames
     */
    public Iterator getCodes() {
        return codes.iterator();
    }

    /**
     *  Returns a detail message for this exception <p>
     *
     *  If there is an embedded exception, and if the SAMLException has no
     *  detail message of its own, this method will return the detail message
     *  from the embedded exception.</p>
     *
     * @return    The error message
     */
    public String getMessage() {
        if (msg != null && e != null)
            return msg + " (wrapped: " + e.getMessage() + ')';
        else if (e != null)
        	return "(wrapped: " + e.getMessage() + ")";
        else
            return msg;
    }

    /**
     *  Returns the embedded exception, if any
     *
     * @return    The embedded exception, or null if there is none
     */
    public Exception getException() {
        return e;
    }

    /**
     *  Overrides toString to pick up any embedded exception<p>
     * 
     *  One quirk is that this method does not produce a serialized XML
     *  representation of the object. toString is oriented around the usual
     *  expectations of Exception clients, while toStream implements the behavior
     *  expected by SAMLObject clients.
     *
     * @return    A string (but not XML) representation of this exception
     */
    public String toString() {
        return (e != null) ? e.toString() : super.toString();
    }

    /**
     *  Copies a SAML object such that no dependencies exist between the original
     *  and the copy.
     * 
     * @return      The new object
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
        SAMLException dup=(SAMLException)super.clone();
        
        dup.root = null;
        dup.codes = (ArrayList)codes.clone();
        
        return dup;
    }
}

