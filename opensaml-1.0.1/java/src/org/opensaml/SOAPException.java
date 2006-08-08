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

import java.util.Collection;

import org.w3c.dom.*;

/**
 *  Indicates that a SOAP processing error occurred in the context of the SAML
 *  SOAP binding. This subclass signals a binding implementation to return a
 *  SOAP fault instead of a SAML error.
 *
 * @author     Scott Cantor
 * @created    January 15, 2002
 */
public class SOAPException extends BindingException implements Cloneable
{
    /**  SOAP Client fault code */
    public final static QName CLIENT = new QName(XML.SOAP11ENV_NS, "Client");

    /**  SOAP Server fault code */
    public final static QName SERVER = new QName(XML.SOAP11ENV_NS, "Server");

    /**  SOAP MustUnderstand fault code */
    public final static QName MUSTUNDERSTAND = new QName(XML.SOAP11ENV_NS, "MustUnderstand");

    /**  SOAP Version Mismatch status code */
    public final static QName VERSION = new QName(XML.SOAP11ENV_NS, "VersionMismatch");

    /**
     *  Creates a new SOAPException
     *
     * @param  e    The root of a DOM tree
     * @exception  SAMLException   Raised if an exception occurs while constructing
     *                              the object.
     */
    protected SOAPException(Element e)
        throws SAMLException
    {
        super(e);
    }

    /**
     *  Creates a new SOAPException
     *
     * @param  msg    The detail message
     */
    public SOAPException(String msg)
    {
        super(msg);
    }

    /**
     *  Creates a new SOAPException
     *
     * @param  msg    The detail message
     * @param  e      The exception to be wrapped in a SOAPException
     */
    public SOAPException(String msg, Exception e)
    {
        super(msg,e);
    }

    /**
     *  Creates a new SOAPException
     *
     * @param  codes  A collection of QNames
     * @param  msg    The detail message
     */
    public SOAPException(Collection codes, String msg)
    {
        super(codes,msg);
    }

    /**
     *  Creates a new SOAPException wrapping an existing exception <p>
     *
     *  The existing exception will be embedded in the new one, and its message
     *  will become the default message for the SOAPException.</p>
     *
     * @param  codes  A collection of QNames
     * @param  e      The exception to be wrapped in a SOAPException
     */
    public SOAPException(Collection codes, Exception e)
    {
        super(codes,e);
    }

    /**
     *  Creates a new SOAPException from an existing exception. <p>
     *
     *  The existing exception will be embedded in the new one, but the new
     *  exception will have its own message.</p>
     *
     * @param  codes  A collection of QNames
     * @param  msg    The detail message
     * @param  e      The exception to be wrapped in a SOAPException
     */
    public SOAPException(Collection codes, String msg, Exception e)
    {
        super(codes,msg,e);
    }

    /**
     *  Creates a new SOAPException
     *
     * @param  code   A status code
     * @param  msg    The detail message
     */
    public SOAPException(QName code, String msg)
    {
        super(code,msg);
    }

    /**
     *  Creates a new SOAPException wrapping an existing exception <p>
     *
     *  The existing exception will be embedded in the new one, and its message
     *  will become the default message for the SOAPException.</p>
     *
     * @param  code   A status code
     * @param  e      The exception to be wrapped in a SOAPException
     */
    public SOAPException(QName code, Exception e)
    {
        super(code,e);
    }

    /**
     *  Creates a new SOAPException from an existing exception. <p>
     *
     *  The existing exception will be embedded in the new one, but the new
     *  exception will have its own message.</p>
     *
     * @param  code   A status code
     * @param  msg    The detail message
     * @param  e      The exception to be wrapped in a SOAPException
     */
    public SOAPException(QName code, String msg, Exception e)
    {
        super(code,msg,e);
    }

    /**
     *  Handles initialization of exceptions from a DOM element
     *
     * @param  e                
     * @exception  SAMLException  Raised if an exception occurs while initializing the object
     */
    public void fromDOM(Element e)
        throws SAMLException
    {
        if (e==null)
            throw new MalformedException("SOAPException.fromDOM() given an empty DOM");
        root = e;
        
        if (config.getBooleanProperty("org.opensaml.strict-dom-checking") && !XML.isElementNamed(e,XML.SOAP11ENV_NS,"Fault"))
            throw new MalformedException(SAMLException.RESPONDER,"SOAPException.fromDOM() requires soap:Fault at root");
    
        // Get the first child, the faultcode.
        Node code=e.getFirstChild();
        while (code!=null && code.getNodeType()!=Node.ELEMENT_NODE)
            code=code.getNextSibling();

        QName q=QName.getQNameTextNode((Text)code.getFirstChild());
        if (q!=null)
            codes.add(q);
        else
            throw new MalformedException(SAMLException.RESPONDER,"SAMLException.fromDOM() unable to evaluate faultcode value");
    
        // Extract the status message.
        Node m=code.getNextSibling();
        while (m!=null && (m.getNodeType()!=Node.ELEMENT_NODE || !XML.isElementNamed(e,null,"faultstring")))
            m=m.getNextSibling();
        if (m!=null)
            msg=m.getFirstChild().getNodeValue();
    }

    /**
     *  Transforms the object into a DOM tree using an existing document context
     *
     * @param  doc               A Document object to use in manufacturing the
     *      tree
     * @return                   Root element node of the DOM tree capturing the
     *      object
     * @exception  DOMException  Raised if an XML exception is detected
     */
    public Node toDOM(Document doc)
        throws DOMException
    {
        if (root != null)
        {
            // If the DOM tree is already generated, compare the Documents.
            if (root.getOwnerDocument() != doc)
            {
                // We already built a tree. Just import it into the new document.
                // XXX NOTE DOM Level 3 will have an adoptNode() method.
                root = doc.importNode(root, true);
            }
        }
        else
        {
            // Construct a Fault element.
            Element s=doc.createElementNS(XML.SOAP11ENV_NS,"Fault");
            s.setAttributeNS(XML.XMLNS_NS,"xmlns:soap",XML.SOAP11ENV_NS);
        
            Element sc=doc.createElementNS(null,"faultcode");
            if (codes==null || codes.isEmpty())
                sc.appendChild(doc.createTextNode("soap:Server"));
            else
                sc.appendChild(doc.createTextNode("soap:" + ((QName)(codes.get(0))).getLocalName()));
            s.appendChild(sc);
    
            if (getMessage() != null)
            {
                Element msg=doc.createElementNS(null,"faultstring");
                msg.appendChild(doc.createTextNode(getMessage()));
                s.appendChild(msg);
            }
            root = s;
        }
        
        return root;
    }
}

