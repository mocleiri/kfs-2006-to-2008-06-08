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

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.apache.xml.security.utils.Base64;
import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *  Abstract base class for all SAML constructs
 *
 *      Scott Cantor
 * @created    November 17, 2001
 */
public abstract class SAMLObject implements Cloneable
{
    /**  Root node of a DOM tree capturing the object */
    protected Node root = null;
    
    /** Class-specific logging object */
    protected Logger log = Logger.getLogger(this.getClass());
    
	/** OpenSAML configuration */
	protected SAMLConfig config = SAMLConfig.instance();

    /**
     *  Allows parsing of objects from a stream of XML
     *
     * @param  in                   A stream containing XML
     * @return                      The root of the XML document instance
     * @exception  SAMLException  Raised if an exception occurs while constructing
     *                              the object
     */
    static protected Element fromStream(InputStream in) throws SAMLException {
        try
        {
            Document doc = XML.parserPool.parse(in);
            return doc.getDocumentElement();
        }
        catch (Exception e)
        {
            NDC.push("fromStream");
            Logger.getLogger(SAMLObject.class.getName()).error("caught an exception while parsing a stream:\n" + e.getMessage());
            NDC.pop();
            throw new MalformedException("SAMLObject.fromStream() caught exception while parsing a stream",e);
        }
    }
    
    /**
     *  Evaluates the object's content to see if it is currently valid if serialized.
     *  Does not evaluate embedded objects except on the basis of whether they exist.
     *  For example, an Assertion must have a Statement, but if an invalid statement
     *  is added, SAMLAssertion.isValid() would succeed, while SAMLStatement.isValid
     *  would raise an exception.
     * 
     * @exception   SAMLException      Raised if the serialized object would be invalid SAML,
     *      excluding any embedded objects
     */
    public void checkValidity() throws SAMLException {
    }
    
    /**
     *  Initialization of an object from a DOM element
     *
     * @param  e                   Root element of a DOM tree
     * @exception  SAMLException   Raised if an exception occurs while constructing
     *                              the object
     */
    public void fromDOM(Element e) throws SAMLException {
        if (e==null)
            throw new MalformedException("SAMLObject.fromDOM() given an empty DOM");
        root = e;
    }

    /**
     *  Serializes the XML representation of the SAML object to a stream
     *
     * @param  out                      Stream to use for output
     * @exception  java.io.IOException  Raised if an I/O problem is detected
     * @exception  SAMLException Raised if the object is incompletely defined 
     */
    public void toStream(OutputStream out) throws java.io.IOException, SAMLException {
        try
        {
        	toDOM();
        	plantRoot();
            Canonicalizer c = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_EXCL_OMIT_COMMENTS);
            out.write(c.canonicalizeSubtree(root, config.getProperty("org.opensaml.inclusive-namespace-prefixes")));
        }
        catch (InvalidCanonicalizerException e)
        {
            throw new java.io.IOException(e.getMessage());
        }
        catch (CanonicalizationException e)
        {
            throw new java.io.IOException(e.getMessage());
        }
    }

    /**
     *  Returns a base64-encoded XML representation of the SAML object
     *
     * @return                          A byte array containing the encoded data
     * @exception  java.io.IOException  Raised if an I/O problem is detected
     * @exception  SAMLException Raised if the object is incompletely defined
     */
    public byte[] toBase64() throws java.io.IOException, SAMLException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        toStream(out);
        return Base64.encode(
            out.toByteArray(),
            config.getBooleanProperty("org.opensaml.compatibility-mode") ? 0 : 76
            ).getBytes();
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
        checkValidity();
        if (root != null)
        {
            // If the DOM tree is already generated, compare the Documents.
            if (root.getOwnerDocument() != doc)
            {
                // We already built a tree. Just adopt it into the new document.
                root = doc.adoptNode(root);
            }
        }
        return root;
    }

	/**
	 *  Transforms the object into a DOM tree without an existing document context
	 *
     * @param  xmlns             Include namespace(s) on root element?
	 * @return                   Root element node of the DOM tree capturing the object
	 * @exception  SAMLException Raised if the object is incompletely defined
	 */
	public Node toDOM(boolean xmlns) throws SAMLException {
		checkValidity();
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
	 * @exception  SAMLException Raised if the object is incompletely defined
	 */
	public Node toDOM(Document doc) throws SAMLException {
		return toDOM(doc, true);
	}

	/**
	 *  Transforms the object into a DOM tree without an existing document context,
	 *  including namespace declarations
	 *
	 * @return                   Root element node of the DOM tree capturing the object
	 * @exception  SAMLException Raised if the object is incompletely defined
	 */
	public Node toDOM() throws SAMLException {
		return toDOM(true);
	}

    /**
     *  Installs the root node of this DOM as the document element
     *
     * @return    The root node so planted
     */
    protected Node plantRoot() {
        if (root!=null) {
            Node domroot=root;
            while (domroot.getParentNode()!=null && domroot.getParentNode().getNodeType() != Node.DOCUMENT_NODE)
                domroot=domroot.getParentNode();
            Element e=root.getOwnerDocument().getDocumentElement();
            if (e!=null && e!=domroot)
                root.getOwnerDocument().replaceChild(domroot,e);
            else if (e==null)
                root.getOwnerDocument().appendChild(domroot);
        }
        return root;
    }
    
    
    /**
     *  Copies a SAML object such that no dependencies exist between the original
     *  and the copy.
     * 
     * @return      The new object
     * @see java.lang.Object#clone()
     */
    protected Object clone() throws CloneNotSupportedException {
        SAMLObject dup=(SAMLObject)super.clone();
        
        // Clear the DOM before returning the copy.
        dup.root = null;
        
        return dup;
    }

    /**
     *  Serializes a SAML object to a string in exclusive canonical form.
     * 
     * @return      The canonicalized output
     * @see java.lang.Object#toString()
     */
    public String toString() {
        try
        {
            // We already support serialization to streams, but note that c14n XML is always in UTF-8.
            ByteArrayOutputStream os= new ByteArrayOutputStream();
            toStream(os);
            return os.toString("UTF8");
        }
        catch (java.io.IOException e)
        {
            NDC.push("toString");
            log.error("caught an I/O exception while serializing XML:" + e);
            NDC.pop();
            return "";
        }
        catch (SAMLException e)
        {
            NDC.push("toString");
            log.error("caught a SAML exception while serializing XML: " + e);
            NDC.pop();
            return "";
        }
    }
}

