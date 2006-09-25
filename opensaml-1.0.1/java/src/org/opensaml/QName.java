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

import org.w3c.dom.*;

/**
 *  Processes QName attribute values to determine their namespace and local name
 *
 *      Scott Cantor
 * @created    March 28, 2002
 */
public class QName
{
    private String namespace = null;
    private String localName = null;

    /**
     *  Manual constructor for a QName object
     *
     * @param  namespace  Namespace URI
     * @param  localName  Local name
     */
    public QName(String namespace, String localName)
    {
        this.namespace = namespace;
        this.localName = localName;
    }

    /**
     *  Builds a QName from a QName-valued attribute by evaluating it
     *
     * @param  e          The element containing the attribute
     * @param  namespace  The namespace of the attribute
     * @param  name       The local name of the attribute
     * @return            A QName containing the attribute value as a
     *      namespace/local name pair.
     */
    public static QName getQNameAttribute(Element e, String namespace, String name)
    {
        String qval = e.getAttributeNS(namespace, name);
        if (qval == null)
            return null;
        return new QName(getNamespaceForQName(qval, e), qval.substring(qval.indexOf(':') + 1));
    }

    /**
     *  Builds a QName from a QName-valued text node by evaluating it
     *
     * @param  t  The text node containing the QName value
     * @return    A QName containing the text node value as a namespace/local
     *      name pair.
     */
    public static QName getQNameTextNode(Text t)
    {
        String qval = t.getNodeValue();
        Node n = t.getParentNode();
        if (qval == null || n == null || n.getNodeType() != Node.ELEMENT_NODE)
            return null;
        return new QName(getNamespaceForQName(qval, (Element)n), qval.substring(qval.indexOf(':') + 1));
    }

    /**
     *  Gets the XML namespace URI that is mapped to the prefix of a QName, in
     *  the context of the DOM element e
     *
     * @param  qname  The QName value to map a prefix from
     * @param  e      The DOM element in which to calculate the prefix binding
     * @return        The XML namespace URI mapped to qname's prefix in the
     *      context of e
     */
    public static String getNamespaceForQName(String qname, Element e)
    {
        // Determine the QName prefix.
        String prefix = "";
        if (qname != null && qname.indexOf(':') >= 0)
            prefix = qname.substring(0, qname.indexOf(':'));
        return getNamespaceForPrefix(prefix, e);
    }

    /**
     *  Gets the XML namespace URI that is mapped to the specified prefix, in
     *  the context of the DOM element e
     *
     * @param  prefix  The namespace prefix to map
     * @param  e       The DOM element in which to calculate the prefix binding
     * @return         The XML namespace URI mapped to prefix in the context of
     *      e
     */
    public static String getNamespaceForPrefix(String prefix, Element e)
    {
        Node n = e;
        String ns = null;

        if (prefix != null)
        {
            if (prefix.equals("xml"))
                return XML.XML_NS;
            else if (prefix.equals("xmlns"))
                return XML.XMLNS_NS;
        }

        while ((ns == null || ns.length()==0) && n != null && n.getNodeType() == Node.ELEMENT_NODE)
        {
            ns = ((Element)n).getAttributeNS(XML.XMLNS_NS,(prefix!=null) ? prefix : "xmlns");
            n = n.getParentNode();
        }
        return ns;
    }

    /**
     *  Gets the namespace URI of the QName
     *
     * @return    The namespace URI
     */
    public String getNamespaceURI()
    {
        return namespace;
    }

    /**
     *  Gets the local name of the QName
     *
     * @return    The local name
     */
    public String getLocalName()
    {
        return localName;
    }

    /**
     *  Overrides equality testing
     *
     * @param  obj  The object to compare
     * @return      true iff both the namespace URI and local names match
     */
    public boolean equals(Object obj) {
        return ((obj instanceof QName) &&
            XML.safeCompare(namespace, ((QName)obj).getNamespaceURI()) &&
            XML.safeCompare(localName, ((QName)obj).getLocalName()));
    }

    /**
     *  Overrides hash computation
     *
     * @return    The sum of the hashes of the namespace URI and local name
     */
    public int hashCode()
    {
        return ((namespace == null) ? 0 : namespace.hashCode()) + ((localName == null) ? 0 : localName.hashCode());
    }

    /**
     *  Converts the QName to a useful debugging string form
     *
     * @return    The namespace URI in braces, then a colon, then the local name
     */
    public String toString()
    {
        return '{' + (namespace == null ? "none" : namespace) + "}:" + localName;
    }
}

