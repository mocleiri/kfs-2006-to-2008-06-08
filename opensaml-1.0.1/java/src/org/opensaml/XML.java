/*
    The OpenSAML License, Version 1.
    Copyright (c) 2002
    University Corporation for Advanced Internet Development, Inc.
    All rights reserved
    Redistribution and use in source and binary forms, with or without
    modification, are permitted provided that the following conditions are met:
    Redistributions of source code must retain the above copyright notice, this
    list of conditions and the following disclaimer.
    Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution, if any, must include
    the following acknowledgment: "This product includes software developed by
    the University Corporation for Advanced Internet Development
    <http://www.ucaid.edu>Internet2 Project. Alternately, this acknowledegement
    may appear in the software itself, if and wherever such third-party
    acknowledgments normally appear.
    Neither the name of OpenSAML nor the names of its contributors, nor
    Internet2, nor the University Corporation for Advanced Internet Development,
    Inc., nor UCAID may be used to endorse or promote products derived from this
    software without specific prior written permission. For written permission,
    please contact opensaml@opensaml.org
    Products derived from this software may not be called OpenSAML, Internet2,
    UCAID, or the University Corporation for Advanced Internet Development, nor
    may OpenSAML appear in their name, without prior written permission of the
    University Corporation for Advanced Internet Development.
    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
    AND WITH ALL FAULTS. ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
    LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
    PARTICULAR PURPOSE, AND NON-INFRINGEMENT ARE DISCLAIMED AND THE ENTIRE RISK
    OF SATISFACTORY QUALITY, PERFORMANCE, ACCURACY, AND EFFORT IS WITH LICENSEE.
    IN NO EVENT SHALL THE COPYRIGHT OWNER, CONTRIBUTORS OR THE UNIVERSITY
    CORPORATION FOR ADVANCED INTERNET DEVELOPMENT, INC. BE LIABLE FOR ANY DIRECT,
    INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
    (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
    LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
    ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
    (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
    SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
  */
package org.opensaml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *  Utility classes for XML constants and optimizations
 *
 * @author     Scott Cantor
 * @created    January 2, 2002
 */
public class XML
{
    /**  XML core namespace */
    public final static String XML_NS = "http://www.w3.org/XML/1998/namespace";

    /**  XML namespace for xmlns attributes */
    public final static String XMLNS_NS = "http://www.w3.org/2000/xmlns/";

    /**  XML Schema Instance namespace */
    public final static String XSI_NS = "http://www.w3.org/2001/XMLSchema-instance";

    /**  XML Schema Instance namespace */
    public final static String XSD_NS = "http://www.w3.org/2001/XMLSchema";

    /**  OpenSAML XML namespace */
    public final static String OPENSAML_NS = "http://www.opensaml.org";

    /**  SAML XML namespace */
    public final static String SAML_NS = "urn:oasis:names:tc:SAML:1.0:assertion";

    /**  SAML protocol XML namespace */
    public final static String SAMLP_NS = "urn:oasis:names:tc:SAML:1.0:protocol";

    /**  XML Signature namespace */
    public final static String XMLSIG_NS = "http://www.w3.org/2000/09/xmldsig#";

    /**  XPath Filter 2 namespace */
    public final static String XPATH2_NS = "http://www.w3.org/2002/06/xmldsig-filter2";

    /**  SOAP 1.1 Envelope XML namespace */
    public final static String SOAP11ENV_NS = "http://schemas.xmlsoap.org/soap/envelope/";

    /**  XML core schema identifier */
    public final static String XML_SCHEMA_ID = "xml.xsd";

    /**  SAML XML Schema Identifier */
    public final static String SAML_SCHEMA_ID = "cs-sstc-schema-assertion-01.xsd";

    /**  SAML protocol XML Schema Identifier */
    public final static String SAMLP_SCHEMA_ID = "cs-sstc-schema-protocol-01.xsd";

    /**  SAML 1.1 XML Schema Identifier */
    public final static String SAML11_SCHEMA_ID = "cs-sstc-schema-assertion-1.1.xsd";

    /**  SAML 1.1 protocol XML Schema Identifier */
    public final static String SAMLP11_SCHEMA_ID = "cs-sstc-schema-protocol-1.1.xsd";

    /**  XML Signature Schema Identifier */
    public final static String XMLSIG_SCHEMA_ID = "xmldsig-core-schema.xsd";

    /**  XPath Filter 2 Schema Identifier */
    public final static String XPATH2_SCHEMA_ID = "xmldsig-filter2.xsd";

    /**  SOAP 1.1 Envelope Schema Identifier */
    public final static String SOAP11ENV_SCHEMA_ID = "soap-envelope.xsd";

    /**  Cached copy of XML core schema */
    protected static byte[] XML_schema;

    /**  Cached copy of SAML schema */
    protected static byte[] SAML_schema;

    /**  Cached copy of SAML protocol schema */
    protected static byte[] SAMLP_schema;

    /**  Cached copy of SAML 1.1 schema */
    protected static byte[] SAML11_schema;

    /**  Cached copy of SAML 1.1 protocol schema */
    protected static byte[] SAMLP11_schema;

    /**  Cached copy of XML Signature schema */
    protected static byte[] XMLSig_schema;

    /**  Cached copy of XPath Filter2 schema */
    protected static byte[] XPath2_schema;

    /**  Cached copy of SOAP 1.1 Envelope schema */
    protected static byte[] SOAP11Env_schema;

    /**  A global object to manage a pool of custom DOM parsers */
    public static ParserPool parserPool = new ParserPool();

    private static Logger log = Logger.getLogger(XML.class.getName());

    /**
     *  A "safe" null/empty check for strings.
     * 
     * @param s     The string to check
     * @return  true iff the string is null or length zero
     */
    public static boolean isEmpty(String s) {
        return (s==null || s.length() == 0);
    }

    /**
     *  Compares two strings for equality, allowing for nulls
     * 
     * @param s1    The first operand
     * @param s2    The second operand
     * 
     * @return  true iff both are null or both are non-null and the same strng value
     */
    public static boolean safeCompare(String s1, String s2) {
        if (s1 == null || s2 == null)
            return s1 == s2;
        else
            return s1.equals(s2);
    }

    /**
     *  Shortcut for checking a DOM element node's namespace and local name
     *
     * @param  e            An element to compare against
     * @param  ns           An XML namespace to compare
     * @param  localName    A local name to compare
     * @return              true iff the element's local name and namespace match the
     *                          parameters
     */
    public static boolean isElementNamed(Element e, String ns, String localName) {
        return (e != null && safeCompare(ns, e.getNamespaceURI()) && safeCompare(localName, e.getLocalName()));
    }
    
    /**
     *  Gets the first child Element of the node, skipping any Text nodes such as whitespace.
     * 
     * @param n     The parent in which to search for children
     * @return      The first child Element of n, or null if none
     */
    public static Element getFirstChildElement(Node n) {
        Node child = n.getFirstChild();
        while (child != null && child.getNodeType() != Node.ELEMENT_NODE)
            child = child.getNextSibling();
        if (child != null)
            return (Element)child;
        else
            return null;
    }    

    /**
     *  Gets the last child Element of the node, skipping any Text nodes such as whitespace.
     * 
     * @param n     The parent in which to search for children
     * @return      The last child Element of n, or null if none
     */
    public static Element getLastChildElement(Node n) {
        Node child = n.getLastChild();
        while (child != null && child.getNodeType() != Node.ELEMENT_NODE)
            child = child.getPreviousSibling();
        if (child != null)
            return (Element)child;
        else
            return null;
    }    

    /**
     *  Gets the first child Element of the node of the given name,
     *  skipping any Text nodes such as whitespace.
     * 
     * @param n     The parent in which to search for children
     * @param ns    The namespace URI of the element to locate
     * @param localName     The local name of the element to locate
     * @return      The first child Element of n with the specified name, or null if none
     */
    public static Element getFirstChildElement(Node n, String ns, String localName) {
        Element e = getFirstChildElement(n);
        while (e != null && !isElementNamed(e, ns, localName))
            e = getNextSiblingElement(e);
        return e;
    }    

    /**
     *  Gets the last child Element of the node of the given name,
     *  skipping any Text nodes such as whitespace.
     * 
     * @param n     The parent in which to search for children
     * @param ns    The namespace URI of the element to locate
     * @param localName     The local name of the element to locate
     * @return      The last child Element of n with the specified name, or null if none
     */
    public static Element getLastChildElement(Node n, String ns, String localName) {
        Element e = getLastChildElement(n);
        while (e != null && !isElementNamed(e, ns, localName))
            e = getPreviousSiblingElement(e);
        return e;
    }    

    /**
     *  Gets the next sibling Element of the node, skipping any Text nodes such as whitespace.
     * 
     * @param n     The sibling to start with
     * @return      The next sibling Element of n, or null if none
     */
    public static Element getNextSiblingElement(Node n) {
        Node sib = n.getNextSibling();
        while (sib != null && sib.getNodeType() != Node.ELEMENT_NODE)
            sib = sib.getNextSibling();
        if (sib != null)
            return (Element)sib;
        else
            return null;
    }

    /**
     *  Gets the previous sibling Element of the node, skipping any Text nodes such as whitespace.
     * 
     * @param n     The sibling to start with
     * @return      The previous sibling Element of n, or null if none
     */
    public static Element getPreviousSiblingElement(Node n) {
        Node sib = n.getPreviousSibling();
        while (sib != null && sib.getNodeType() != Node.ELEMENT_NODE)
            sib = sib.getPreviousSibling();
        if (sib != null)
            return (Element)sib;
        else
            return null;
    }

    /**
     *  Gets the next sibling Element of the node of the given name,
     *  skipping any Text nodes such as whitespace.
     * 
     * @param n     The sibling to start with
     * @param ns    The namespace URI of the element to locate
     * @param localName     The local name of the element to locate
     * @return      The next sibling Element of n with the specified name, or null if none
     */
    public static Element getNextSiblingElement(Node n, String ns, String localName) {
        Element e = getNextSiblingElement(n);
        while (e != null && !isElementNamed(e, ns, localName))
            e = getNextSiblingElement(e);
        return e;
    }

    /**
     *  Gets the previous sibling Element of the node of the given name,
     *  skipping any Text nodes such as whitespace.
     * 
     * @param n     The sibling to start with
     * @param ns    The namespace URI of the element to locate
     * @param localName     The local name of the element to locate
     * @return      The previous sibling Element of n with the specified name, or null if none
     */
    public static Element getPreviousSiblingElement(Node n, String ns, String localName) {
        Element e = getPreviousSiblingElement(n);
        while (e != null && !isElementNamed(e, ns, localName))
            e = getPreviousSiblingElement(e);
        return e;
    }

    /**
     *  Nested class that provides XML parsers as a pooled resource
     *
     * @author     Scott Cantor
     * @created    January 15, 2002
     */
    public static class ParserPool implements EntityResolver, ErrorHandler
    {
        private Stack pool = null;
        private Vector resolvers = null;
        private String schemaLocations = null;
        private Hashtable schemaLocMap = null;

        /**  Constructor for the ParserPool object */
        public ParserPool()
        {
            pool = new Stack();
            resolvers = new Vector();
            schemaLocMap = new Hashtable();
            registerSchema(
                XML.SAML_NS,
                SAMLConfig.instance().getBooleanProperty("org.opensaml.compatibility-mode") ? XML.SAML_SCHEMA_ID : XML.SAML11_SCHEMA_ID,
                null);
            registerSchema(
                XML.SAMLP_NS,
                SAMLConfig.instance().getBooleanProperty("org.opensaml.compatibility-mode") ? XML.SAMLP_SCHEMA_ID : XML.SAMLP11_SCHEMA_ID,
                null);
            registerSchema(XML.SOAP11ENV_NS, XML.SOAP11ENV_SCHEMA_ID, null);
            registerSchema(XML.XMLSIG_NS, XML.XMLSIG_SCHEMA_ID, null);
            registerSchema(XML.XML_NS, XML.XML_SCHEMA_ID, null);
            registerSchema(XML.XPATH2_NS, XML.XPATH2_SCHEMA_ID, null);
            System.setProperty("org.apache.xerces.xni.parser.Configuration", "org.apache.xerces.parsers.XMLGrammarCachingConfiguration");
        }

        /**
         *  Get a DOM parser suitable for our task
         *
         * @return                    A DOM parser ready to use
         * @exception  SAMLException  Raised if a system error prevents a parser
         *      from being created
         */
        public synchronized DOMParser get()
            throws SAMLException
        {
            try
            {
                DOMParser p = null;
                if (pool.empty())
                {
                    // Build a parser to order.
                    p = new DOMParser();
                    p.setFeature("http://xml.org/sax/features/validation", true);
                    p.setFeature("http://apache.org/xml/features/validation/schema", true);
                    p.setFeature("http://apache.org/xml/features/validation/schema/normalized-value", false);
                    p.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", false);

                    p.setEntityResolver(this);
                    p.setErrorHandler(this);
                }
                else
                    p = (DOMParser)pool.pop();

                p.setProperty("http://apache.org/xml/properties/schema/external-schemaLocation",schemaLocations);
                return p;
            }
            catch (org.xml.sax.SAXException e)
            {
                throw new SAMLException("XML.ParserPool.get() unable to configure parser properly",e);
            }
        }

        /**
         *  Parses a document using a pooled parser with the proper settings
         *
         * @param  in                       A stream containing the content to
         *      be parsed
         * @return                          The DOM document resulting from the
         *      parse
         * @exception  SAMLException        Raised if a parser is unavailable
         * @exception  SAXException         Raised if a parsing error occurs
         * @exception  java.io.IOException  Raised if an I/O error occurs
         */
        public Document parse(InputStream in)
            throws SAMLException, SAXException, java.io.IOException
        {
            DOMParser p = get();
            try
            {
                p.parse(new InputSource(in));
                return p.getDocument();
            }
            finally
            {
                put(p);
            }
        }

        /**
         *  Parses a document using a pooled parser with the proper settings
         *
         * @param  systemId                 The URI to parse
         * @return                          The DOM document resulting from the
         *      parse
         * @exception  SAMLException        Raised if a parser is unavailable
         * @exception  SAXException         Raised if a parsing error occurs
         * @exception  java.io.IOException  Raised if an I/O error occurs
         */
        public Document parse(String systemId)
            throws SAMLException, SAXException, java.io.IOException
        {
            DOMParser p = get();
            try
            {
                p.parse(new InputSource(systemId));
                return p.getDocument();
            }
            finally
            {
                put(p);
            }
        }

        /**
         *  Builds a new DOM document
         *
         * @return    An empty DOM document 
         */
        public Document newDocument()
        {
            return new DocumentImpl();
        }

        /**
         *  Registers an extension schema and optional resolver with the parser
         *  runtime
         *
         * @param  namespace  The extension namespace
         * @param  schema     The system ID of the extension schema
         * @param  resolver   An optional entity resolver that can custom
         *      resolve the schema
         */
        public synchronized void registerSchema(String namespace, String schema, EntityResolver resolver)
        {
            if (namespace != null && schema != null)
            {
                if (schemaLocMap.containsKey(namespace))
                    schemaLocMap.remove(namespace);
                
                schemaLocMap.put(namespace, schema);
                if (resolver != null)
                    resolvers.add(resolver);

                schemaLocations = null;
                Iterator i = schemaLocMap.entrySet().iterator();
                while (i.hasNext())
                {
                    Map.Entry e = (Map.Entry)i.next();
                    if (schemaLocations == null)
                        schemaLocations = e.getKey() + " " + e.getValue();
                    else
                        schemaLocations += " " + e.getKey() + " " + e.getValue();
                }
            }
        }

        /**
         *  Return a parser to the pool
         *
         * @param  p  Description of Parameter
         */
        public synchronized void put(DOMParser p)
        {
            pool.push(p);
        }

        /**
         *  A customized entity resolver that utilizes internal machinery for
         *  the SAML, SOAP, and DSig schemas and chains to externally provided
         *  resolvers
         *
         * @param  publicId                 The public identifier of the entity
         * @param  systemId                 The system identifier of the entity
         * @return                          A source of bytes for the entity or
         *      null
         * @exception  SAXException         Raised if an XML parsing problem
         *      occurs
         * @exception  java.io.IOException  Raised if an I/O problem is detected
         */
        public InputSource resolveEntity(String publicId, String systemId)
            throws SAXException, java.io.IOException
        {
            log.debug("ParserPool resolving entity: publicId = " + publicId + " : systemId = " + systemId);
            InputSource src = null;
            if (systemId.endsWith(SAML_SCHEMA_ID) && SAML_schema != null)
                src = new InputSource(new ByteArrayInputStream(SAML_schema));
            else if (systemId.endsWith(SAMLP_SCHEMA_ID) && SAMLP_schema != null)
                src = new InputSource(new ByteArrayInputStream(SAMLP_schema));
            else if (systemId.endsWith(SAML11_SCHEMA_ID) && SAML11_schema != null)
                src = new InputSource(new ByteArrayInputStream(SAML11_schema));
            else if (systemId.endsWith(SAMLP11_SCHEMA_ID) && SAMLP11_schema != null)
                src = new InputSource(new ByteArrayInputStream(SAMLP11_schema));
            else if (systemId.endsWith(XMLSIG_SCHEMA_ID) && XMLSig_schema != null)
                src = new InputSource(new ByteArrayInputStream(XMLSig_schema));
            else if (systemId.endsWith(SOAP11ENV_SCHEMA_ID) && SOAP11Env_schema != null)
                src = new InputSource(new ByteArrayInputStream(SOAP11Env_schema));
            else if (systemId.endsWith(XML_SCHEMA_ID) && XML_schema != null)
                src = new InputSource(new ByteArrayInputStream(XML_schema));
            else if (systemId.endsWith(XPATH2_SCHEMA_ID) && XPath2_schema != null)
                src = new InputSource(new ByteArrayInputStream(XPath2_schema));
            else if (resolvers != null)
            {
                for (Iterator i = resolvers.iterator(); src == null && i.hasNext(); )
                    src = ((EntityResolver)(i.next())).resolveEntity(publicId, systemId);
            }
            if (src != null)
            {
                log.debug("entity resolved by ParserPool");
            }
            return src;
        }

        /**
         *  Called by parser if a fatal error is detected, does nothing
         *
         * @param  exception         Describes the error
         * @exception  SAXException  Can be raised to indicate an explicit error
         */
        public void fatalError(SAXParseException exception)
            throws SAXException { }

        /**
         *  Called by parser if an error is detected, currently just throws e
         *
         * @param  e                      Description of Parameter
         * @exception  SAXParseException  Can be raised to indicate an explicit
         *      error
         */
        public void error(SAXParseException e)
            throws SAXParseException
        {
            throw e;
        }

        /**
         *  Called by parser if a warning is issued, currently logs the
         *  condition
         *
         * @param  e                      Describes the warning
         * @exception  SAXParseException  Can be raised to indicate an explicit
         *      error
         */
        public void warning(SAXParseException e)
            throws SAXParseException
        {
            log.warn("Parser warning: line = " + e.getLineNumber() + " : uri = " + e.getSystemId());
            log.warn("Parser warning (root cause): " + e.getMessage());
        }
    }

    // Static initializer block preloads schemas.
    static
    {
        try
        {
            int b;
            StringBuffer buf = new StringBuffer(1024);
            InputStream xmlin = XML.class.getResourceAsStream("/schemas/" + SAML_SCHEMA_ID);
            if (xmlin == null)
                throw new RuntimeException("XML static initializer unable to locate SAML assertion schema");
            else
            {
                while ((b = xmlin.read()) != -1)
                    buf.append((char)b);
                SAML_schema = buf.toString().getBytes();
                xmlin.close();
            }

            xmlin = XML.class.getResourceAsStream("/schemas/" + SAMLP_SCHEMA_ID);
            if (xmlin == null)
                throw new RuntimeException("XML static initializer unable to locate SAML protocol schema");
            else
            {
                buf.setLength(0);
                while ((b = xmlin.read()) != -1)
                    buf.append((char)b);
                SAMLP_schema = buf.toString().getBytes();
                xmlin.close();
            }

            xmlin = XML.class.getResourceAsStream("/schemas/" + SAML11_SCHEMA_ID);
            if (xmlin == null)
                throw new RuntimeException("XML static initializer unable to locate SAML 1.1 assertion schema");
            else
            {
                buf.setLength(0);
                while ((b = xmlin.read()) != -1)
                    buf.append((char)b);
                SAML11_schema = buf.toString().getBytes();
                xmlin.close();
            }

            xmlin = XML.class.getResourceAsStream("/schemas/" + SAMLP11_SCHEMA_ID);
            if (xmlin == null)
                throw new RuntimeException("XML static initializer unable to locate SAML 1.1 protocol schema");
            else
            {
                buf.setLength(0);
                while ((b = xmlin.read()) != -1)
                    buf.append((char)b);
                SAMLP11_schema = buf.toString().getBytes();
                xmlin.close();
            }

            xmlin = XML.class.getResourceAsStream("/schemas/" + XMLSIG_SCHEMA_ID);
            if (xmlin == null)
                throw new RuntimeException("XML static initializer unable to locate XML Signature schema");
            else
            {
                buf.setLength(0);
                while ((b = xmlin.read()) != -1)
                    buf.append((char)b);
                XMLSig_schema = buf.toString().getBytes();
                xmlin.close();
            }

            xmlin = XML.class.getResourceAsStream("/schemas/" + XPATH2_SCHEMA_ID);
            if (xmlin == null)
                throw new RuntimeException("XML static initializer unable to locate XPath Filter2 schema");
            else
            {
                buf.setLength(0);
                while ((b = xmlin.read()) != -1)
                    buf.append((char)b);
                XPath2_schema = buf.toString().getBytes();
                xmlin.close();
            }

            xmlin = XML.class.getResourceAsStream("/schemas/" + SOAP11ENV_SCHEMA_ID);
            if (xmlin == null)
                throw new RuntimeException("XML static initializer unable to locate SOAP 1.1 Envelope schema");
            else
            {
                buf.setLength(0);
                while ((b = xmlin.read()) != -1)
                    buf.append((char)b);
                SOAP11Env_schema = buf.toString().getBytes();
                xmlin.close();
            }

            xmlin = XML.class.getResourceAsStream("/schemas/" + XML_SCHEMA_ID);
            if (xmlin == null)
                throw new RuntimeException("XM static initializer unable to locate XML core schema");
            else
            {
                buf.setLength(0);
                while ((b = xmlin.read()) != -1)
                    buf.append((char)b);
                XML_schema = buf.toString().getBytes();
                xmlin.close();
            }
        }
        catch (java.io.IOException e)
        {
            throw new RuntimeException("XML static initializer caught an I/O error");
        }
    }
}

