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

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.ArrayList;

import org.apache.log4j.Category;
import org.apache.log4j.NDC;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *  Basic SAML Attribute implementation that handles rudimentary attribute value
 *  types
 *
 *      Scott Cantor
 * @created    May 9, 2002
 */
public class SAMLAttribute extends SAMLObject implements Cloneable
{
    /**  Maps SAML attribute name/namespace pairs to Java class implementations */
    private static Hashtable attributeMap = new Hashtable();
    
    /**
     *  Registers a class to handle a specific SAML attribute when parsing XML
     * 
     * @param name          The attribute name
     * @param namespace     The attribute namespace
     * @param className     The Java class that handles this attribute
     */
    public static void regFactory(String name, String namespace, String className) {
        attributeMap.put(namespace + "!!" + name, className);
    }

    /**
     *  Unregisters a class to handle a specific SAML attribute when parsing XML
     * 
     * @param name          The attribute name
     * @param namespace     The attribute namespace
     */
    public static void unregFactory(String name, String namespace) {
        attributeMap.remove(namespace + "!!" + name);
    }
    
    /**
     *  Locates an implementation class for an attribute and constructs it based
     *  on the DOM provided.
     * 
     * @param e     The root of a DOM containing the SAML attribute
     * @return SAMLAttribute    A constructed attribute object
     * 
     * @throws SAMLException    Thrown if an error occurs while constructing the object
     */
    public static SAMLAttribute getInstance(Element e) throws SAMLException {
        if (e == null)
            throw new MalformedException(SAMLException.RESPONDER, "SAMLAttribute.getInstance() given an empty DOM");
    
        String name=e.getAttributeNS(null,"AttributeName");
        String namespace=e.getAttributeNS(null,"AttributeNamespace");
    
        if (name == null || namespace == null)
            throw new MalformedException(SAMLException.RESPONDER, "SAMLAttribute.getInstance() can't find AttributeName or Namespace on root element");
    
        String className = (String)attributeMap.get(namespace + "!!" + name);
        if (className == null)
            return new SAMLAttribute(e);
        try
        {
            Class implementation = Class.forName(className);
            Class[] paramtypes = {Class.forName("org.w3c.dom.Element")};
            Object[] params = {e};
            Constructor ctor = implementation.getDeclaredConstructor(paramtypes);
            return (SAMLAttribute)ctor.newInstance(params);
        }
        catch (ClassNotFoundException ex)
        {
            throw new SAMLException(SAMLException.REQUESTER, "SAMLAttribute.getInstance() unable to locate implementation class for attribute", ex);
        }
        catch (NoSuchMethodException ex)
        {
            throw new SAMLException(SAMLException.REQUESTER, "SAMLAttribute.getInstance() unable to bind to constructor for attribute", ex);
        }
        catch (InstantiationException ex)
        {
            throw new SAMLException(SAMLException.REQUESTER, "SAMLAttribute.getInstance() unable to build implementation object for attribute", ex);
        }
        catch (IllegalAccessException ex)
        {
            throw new SAMLException(SAMLException.REQUESTER, "SAMLAttribute.getInstance() unable to access implementation of attribute", ex);
        }
        catch (java.lang.reflect.InvocationTargetException ex)
        {
            ex.printStackTrace();
            Throwable e2 = ex.getTargetException();
            if (e2 instanceof SAMLException)
                throw (SAMLException)e2;
            else
                throw new SAMLException(SAMLException.REQUESTER, "SAMLAttribute.getInstance() caught unknown exception while building attribute object: " + e2.getMessage());
        }
    }

    /**
     *  Locates an implementation class for an attribute and constructs it based
     *  on the stream provided.
     * 
     * @param in     The stream to deserialize from
     * @return SAMLAttribute    A constructed attribute object
     * 
     * @throws SAMLException    Thrown if an error occurs while constructing the object
     */
    public static SAMLAttribute getInstance(InputStream in) throws SAMLException {
        try
        {
            Document doc = XML.parserPool.parse(in);
            return getInstance(doc.getDocumentElement());
        }
        catch (SAXException e)
        {
            NDC.push("SAMLAttribute");
            Category.getInstance("getInstance").error("caught an exception while parsing a stream:\n" + e.getMessage());
            NDC.pop();
            throw new MalformedException("SAMLAttribute.getInstance() caught exception while parsing a stream",e);
        }
        catch (IOException e)
        {
            NDC.push("SAMLAttribute");
            Category.getInstance("getInstance").error("caught an exception while parsing a stream:\n" + e.getMessage());
            NDC.pop();
            throw new MalformedException("SAMLAttribute.getInstance() caught exception while parsing a stream",e);
        }
    }

    /**  Name of attribute */
    protected String name = null;

    /**  Namespace/qualifier of attribute */
    protected String namespace = null;

    /**  The schema type of attribute value(s) */
    protected QName type = null;

    /**  Effective lifetime of attribute's value(s) in seconds (0 means infinite) */
    protected long lifetime = 0;

    /**  An array of attribute values */
    protected ArrayList values = new ArrayList();

    /**
     *  Computes the xsi:type attribute on each AttributeValue element with any supporting
     *  declarations created.
     * 
     * @param e     The root element of the attribute
     * @return      The xsi:type value to use
     */
    protected String computeTypeDecl(Element e) {
        String xsitype = null;
        e.removeAttributeNS(XML.XMLNS_NS, "xmlns:typens");
        if (type != null)
        {
            String prefix;
            if (XML.XSD_NS.equals(type.getNamespaceURI())) {
                prefix = "xsd";
            }
            else {
                e.setAttributeNS(XML.XMLNS_NS, "xmlns:typens", type.getNamespaceURI());
                prefix = "typens";
            }
            xsitype = prefix + ":" + type.getLocalName();
        }
        return xsitype;
    }

    /**
     *  Creates the DOM representation of an attribute value in the specified element.<P>
     * 
     *  The base implementation handles string values by creating a single Text node.
     *  Override this method in your subclass to perform more advanced processing. 
     * 
     * @param value     The attribute value to DOM-ify
     * @param e         The AttributeValue element to write into
     * @exception   SAMLException   Raised if an error occurs while creating the DOM
     */
    protected void valueToDOM(Object value, Element e) throws SAMLException {
        e.appendChild(e.getOwnerDocument().createTextNode(value.toString()));
    }
    
    /**
     *  Default constructor
     */
    public SAMLAttribute() {
    }

    /**
     *  Builds an Attribute out of its component parts
     *
     * @param  name               Name of attribute
     * @param  namespace          Namespace/qualifier of attribute
     * @param  type               The schema type of attribute value(s)
     * @param  lifetime           Effective lifetime of attribute's value(s) in
     *      seconds (0 means infinite)
     * @param  values             An array of attribute values
     * @exception  SAMLException  Thrown if attribute cannot be built from the
     *      supplied information
     */
    public SAMLAttribute(String name, String namespace, QName type, long lifetime, Collection values) throws SAMLException {
        this.name = name;
        this.namespace = namespace;
        this.type = type;
        this.lifetime = lifetime;

        if (values != null)
            this.values.addAll(values);
    }

    /**
     *  Reconstructs an attribute from a DOM tree
     *
     * @param  e                  The root of a DOM tree
     * @exception  SAMLException  Thrown if the object cannot be constructed
     */
    public SAMLAttribute(Element e) throws SAMLException {
        fromDOM(e);
    }

    /**
     *  Reconstructs an attribute from a stream
     *
     * @param  in                   A stream containing XML
     * @exception  SAMLException  Raised if an exception occurs while constructing
     *                              the object.
     */
    public SAMLAttribute(InputStream in) throws SAMLException {
        fromDOM(fromStream(in));
    }
    
    /**
     *  Initialization of attribute from a DOM element.<P>
     * 
     *  Checks the attribute's syntactic validity. An exception
     *  is thrown if any problems are detected. The exception will contain a
     *  message describing the problem, and may wrap another exception.<P>
     *
     *  Because attributes are generalized, this base method only handles
     *  attributes whose values are of uniform schema type. The
     *  attribute's schema type is set by the first xsi:type attribute found in
     *  the value list, if any.<P>
     * 
     *  The addValue method is used to actually process the values, and can be
     *  overridden to handle more complex values
     *
     * @param  e                   Root element of a DOM tree
     * @exception  SAMLException   Raised if an exception occurs while constructing the object.
     */
    public void fromDOM(Element e) throws SAMLException {
        super.fromDOM(e);

        if (config.getBooleanProperty("org.opensaml.strict-dom-checking") && !XML.isElementNamed(e,XML.SAML_NS,"Attribute"))
            throw new MalformedException("SAMLAttribute.fromDOM() requires saml:Attribute at root");

        name = e.getAttributeNS(null, "AttributeName");
        namespace = e.getAttributeNS(null, "AttributeNamespace");

        // Iterate over AttributeValues.
        Element n = XML.getFirstChildElement(e, XML.SAML_NS, "AttributeValue");
        while (n != null) {
            if (type == null)
                type = QName.getQNameAttribute(n, XML.XSI_NS, "type");
            if (accept(n))
                addValue(n);
            n = XML.getNextSiblingElement(n);
        }

        checkValidity();
    }

    /**
     *  Gets the AttributeName attribute of the SAML Attribute
     *
     * @return    The name value
     */
    public String getName() {
        return name;
    }

    /**
     *  Sets the AttributeName attribute of the SAML Attribute
     *
     * @param   name    The name value
     */
    public void setName(String name) {
        if (XML.isEmpty(name))
            throw new IllegalArgumentException("name cannot be null");
        this.name = name;
        if (root != null) {
            ((Element)root).getAttributeNodeNS(null, "AttributeName").setNodeValue(name);
        }
    }

    /**
     *  Gets the AttributeNamespace attribute of the SAML Attribute
     *
     * @return    The namespace value
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     *  Sets the AttributeNamespace attribute of the SAML Attribute
     *
     * @param   namespace    The name value
     */
    public void setNamespace(String namespace) {
        if (XML.isEmpty(namespace))
            throw new IllegalArgumentException("namespace cannot be null");
        this.namespace = namespace;
        if (root != null) {
            ((Element)root).getAttributeNodeNS(null, "AttributeNamespace").setNodeValue(namespace);
        }
    }

    /**
     *  Gets the value of the xsi:type attribute, if any, of the SAML Attribute
     *
     * @return    The schema type value
     */
    public QName getType() {
        return type;
    }

    /**
     *  Sets the value of the xsi:type attribute, if any, of the SAML Attribute
     *
     * @param   type    The schema type value
     */
    public void setType(QName type) {
        this.type = type;
        if (root != null) {
            String xsitype = computeTypeDecl((Element)root);
            Element child = XML.getFirstChildElement(root);
            while (child != null) {
                child.removeAttributeNS(XML.XSI_NS, "xsi:type");
                if (xsitype != null)
                    child.setAttributeNS(XML.XSI_NS, "xsi:type", xsitype);
                child = XML.getNextSiblingElement(child);
            }
        }
    }

    /**
     *  Gets the value's lifetime, in seconds
     *
     * @return    The effective lifetime of the attribute value, in seconds (0
     *      means infinite)
     */
    public long getLifetime() {
        return lifetime;
    }
    
    /**
     *  Sets the value's lifetime, in seconds
     *
     * @param   lifetime    The effective lifetime of the attribute value, in seconds (0
     *      means infinite)
     */
    public void setLifetime(long lifetime) {
        this.lifetime = lifetime;
    }

    /**
     *  Gets the values of the SAML Attribute
     *
     * @return    An iterator over the values
     */
    public Iterator getValues() {
        return values.iterator();
    }

    /**
     *  Attribute acceptance hook used while consuming attributes from an
     *  assertion. Base class simply accepts anything. Override for desired
     *  behavior.
     *
     * @param  e  An AttributeValue element to check
     * @return    true iff the value is deemed acceptable
     */
    public boolean accept(Element e) {
        if (e == null)
            throw new IllegalArgumentException("e cannot be null");
        return true;
    }

    /**
     *  Imports a value to the state of the SAML Attribute, subject to acceptance.<P>
     *  The base version only supports a simple text node content model and uses Strings.<P>
     *  Override this method to perform advanced processing during XML import.
     *
     * @param  e  The AttributeValue element containing the value to add
     * @return    true iff the value was understandable
     */
    public boolean addValue(Element e) {
        Node n = e.getFirstChild();
        if (accept(e)) {
            if (n != null && n.getNodeType() == Node.TEXT_NODE) {
                values.add(n.getNodeValue());
                return true;
            }
            log.warn("rejecting an AttributeValue without a simple, non-empty text node");
        }
        return false;
    }
    
    /**
     *  Sets the values of the attribute
     * 
     * @param values    The values to use
     * @throws SAMLException    Raised if the value cannot be added to the attribute
     */
    public void setValues(Collection values) throws SAMLException {
        while (this.values.size() > 0)
            removeValue(0);
        
        if (values != null) {
            for (Iterator i = values.iterator(); i.hasNext(); )
                addValue(i.next());
        }
    }

    /**
     *  Adds a value to the attribute
     * 
     * @param value     The value to add
     * @exception   SAMLException   Raised if the value cannot be properly added
     */
    public void addValue(Object value) throws SAMLException {
        if (value != null) {
            if (root != null) {
                String xsitype = computeTypeDecl((Element)root);
                Element v = root.getOwnerDocument().createElementNS(XML.SAML_NS, "AttributeValue");
                if (xsitype != null)
                    v.setAttributeNS(XML.XSI_NS, "xsi:type", xsitype);
                valueToDOM(value, v);
                root.appendChild(v);
            }
            values.add(value);
        }
        else
            throw new IllegalArgumentException("value cannot be null");
    }

    /**
     *  Removes a value by position (zero-based)
     * 
     * @param   index   The position of the value to remove
     */
    public void removeValue(int index) throws IndexOutOfBoundsException {
        values.remove(index);
        if (root != null) {
            Element e = XML.getFirstChildElement(root);
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
     * @see org.opensaml.SAMLObject#toDOM(org.w3c.dom.Document,boolean)
     */
    public Node toDOM(Document doc, boolean xmlns) throws SAMLException {
		if ((root = super.toDOM(doc, xmlns)) != null) {
			if (xmlns) {
				((Element)root).setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAML_NS);
				((Element)root).setAttributeNS(XML.XMLNS_NS, "xmlns:xsi", XML.XSI_NS);
				((Element)root).setAttributeNS(XML.XMLNS_NS, "xmlns:xsd", XML.XSD_NS);
			}
			return root;
        }

        Element a = doc.createElementNS(XML.SAML_NS, "Attribute");
		if (xmlns)
			a.setAttributeNS(XML.XMLNS_NS, "xmlns", XML.SAML_NS);
		a.setAttributeNS(XML.XMLNS_NS, "xmlns:xsi", XML.XSI_NS);
		a.setAttributeNS(XML.XMLNS_NS, "xmlns:xsd", XML.XSD_NS);
        a.setAttributeNS(null, "AttributeName", name);
        a.setAttributeNS(null, "AttributeNamespace", namespace);

        String xsitype = computeTypeDecl(a);

        for (Iterator i = values.iterator(); i.hasNext(); ) {
            Element v = doc.createElementNS(XML.SAML_NS, "AttributeValue");
            if (xsitype != null)
                v.setAttributeNS(XML.XSI_NS, "xsi:type", xsitype);
            valueToDOM(i.next(), v);
            a.appendChild(v);
        }

        return root = a;
    }
    
    /**
     * @see org.opensaml.SAMLObject#checkValidity()
     */
    public void checkValidity() throws SAMLException {
        if (XML.isEmpty(name) || XML.isEmpty(namespace) || values.size() == 0)
            throw new MalformedException(SAMLException.RESPONDER, "Attribute invalid, requires name and namespace, and at least one value");
    }

    /**
     *  Copies a SAML object such that no dependencies exist between the original
     *  and the copy. Does not clone values.
     * 
     * @return      The new object
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
        SAMLAttribute dup=(SAMLAttribute)super.clone();

        dup.values = (ArrayList)values.clone();

        return dup;
    }
}
