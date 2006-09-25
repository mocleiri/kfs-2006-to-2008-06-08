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
import org.w3c.dom.*;

/**
 *  Represents a SAML Subject
 *
 *      Scott Cantor
 * @created    March 25, 2002
 */
public class SAMLNameIdentifier extends SAMLObject implements Cloneable
{
    protected String name = null;
    protected String nameQualifier = null;
    protected String format = null;

    /**  Unspecified Format Identifier */    
    public final static String FORMAT_UNSPECIFIED = "urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified";

    /**  Email Format Identifier */    
    public final static String FORMAT_EMAIL = "urn:oasis:names:tc:SAML:1.1:nameid-format:emailAddress";

    /**  X.509 Subject Format Identifier */    
    public final static String FORMAT_X509 = "urn:oasis:names:tc:SAML:1.1:nameid-format:X509SubjectName";

    /**  Windows Domain Format Identifier */    
    public final static String FORMAT_WINDOWS = "urn:oasis:names:tc:SAML:1.1:nameid-format:WindowsDomainQualifiedName";
    
    /**
     *  Default constructor
     */
    public SAMLNameIdentifier() {
    }
    
    /**
     *  Builds a name identifier out of its component parts
     *
     * @param  name                 Name of subject (optional)
     * @param  nameQualifier        Federates or qualifies subject name (optional)
     * @param  format               URI describing name semantics and format (optional)
     * @exception  SAMLException    Raised if a name cannot be constructed
     *      from the supplied information
     */
    public SAMLNameIdentifier(String name, String nameQualifier, String format) throws SAMLException {
        this.name = name;
        this.nameQualifier = nameQualifier;
        this.format = format;        
    }

    /**
     *  Reconstructs a name identifier from a DOM tree
     *
     * @param  e                  The root of a DOM tree
     * @exception  SAMLException  Thrown if the object cannot be constructed
     */
    public SAMLNameIdentifier(Element e) throws SAMLException {
        fromDOM(e);
    }

    /**
     *  Reconstructs a name identifier from a stream
     *
     * @param  in                   A stream containing XML
     * @exception  SAMLException  Raised if an exception occurs while constructing
     *                              the object.
     */
    public SAMLNameIdentifier(InputStream in) throws SAMLException {
        fromDOM(fromStream(in));
    }

    /**
     * @see org.opensaml.SAMLObject#fromDOM(org.w3c.dom.Element)
     */
    public void fromDOM(Element e) throws SAMLException {
        super.fromDOM(e);

        if (config.getBooleanProperty("org.opensaml.strict-dom-checking") && !XML.isElementNamed(e,XML.SAML_NS,"NameIdentifier"))
            throw new MalformedException("SAMLNameIdentifier.fromDOM() requires saml:NameIdentifier at root");

        nameQualifier = e.getAttributeNS(null,"NameQualifier");
        format = e.getAttributeNS(null,"Format");
        name = e.getFirstChild().getNodeValue();
        
        checkValidity();
    }

    /**
     *  Gets the name of the Subject
     *
     * @return    The Subject name
     */
    public String getName() {
        return name;
    }
    
    /**
     *  Sets the name of the Subject
     * 
     * @param   name    The name
     */
    public void setName(String name) {
        if (XML.isEmpty(name))
            throw new IllegalArgumentException("name cannot be empty");
        
        if (root != null)
            root.getFirstChild().setNodeValue(name);
        this.name = name;
    }

    /**
     *  Gets the name qualifier
     *
     * @return    The name qualifier
     */
    public String getNameQualifier() {
        return nameQualifier;
    }

    /**
     *  Sets the name qualifier
     * 
     * @param   nameQualifier    The name qualifier
     */
    public void setNameQualifier(String nameQualifier) {
        this.nameQualifier = nameQualifier;
        if (root != null) {
            ((Element)root).removeAttributeNS(null, "NameQualifier");
            if (!XML.isEmpty(nameQualifier))
                ((Element)root).setAttributeNS(null, "NameQualifier", nameQualifier);
        }
    }

    /**
     *  Gets the format of the name
     *
     * @return    The name format URI
     */
    public String getFormat() {
        return format;
    }

    /**
     *  Sets the format of the name
     * 
     * @param   format    The name format URI
     */
    public void setFormat(String format) {
        this.format = format;
        if (root != null) {
            ((Element)root).removeAttributeNS(null, "Format");
            if (!XML.isEmpty(format))
                ((Element)root).setAttributeNS(null, "Format", format);
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

        Element nameid = doc.createElementNS(XML.SAML_NS, "NameIdentifier");
        if (!XML.isEmpty(nameQualifier))
            nameid.setAttributeNS(null,"NameQualifier", nameQualifier);
        if (!XML.isEmpty(format))
            nameid.setAttributeNS(null,"Format", format);
        nameid.appendChild(doc.createTextNode(name));

        return root = nameid;
    }

    /**
     * @see org.opensaml.SAMLObject#checkValidity()
     */
    public void checkValidity() throws SAMLException {
        if (XML.isEmpty(name))
            throw new MalformedException("NameIdentifier is invalid, requires name");
    }
    
    /**
     *  Copies a SAML object such that no dependencies exist between the original
     *  and the copy
     * 
     * @return      The new object
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
        return (SAMLNameIdentifier)super.clone();
    }
}
