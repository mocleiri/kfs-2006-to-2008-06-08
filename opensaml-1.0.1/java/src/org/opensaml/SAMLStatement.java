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
import java.lang.reflect.Constructor;
import java.util.Hashtable;

import org.apache.log4j.Category;
import org.apache.log4j.NDC;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *  Abstract base class for all SAML statements
 *
 * @author     Scott Cantor
 * @created    March 25, 2002
 */
public abstract class SAMLStatement extends SAMLObject implements Cloneable
{
    /**  Maps SAML statement types (as XML QNames) to Java class implementations */
    protected static Hashtable statementTypeMap = new Hashtable();

    /**
     *  Registers a class to handle a specific SAML statement type when parsing XML
     * 
     * @param type          The statement type or element name
     * @param className     The Java class that handles this query
     */
    public static void regFactory(QName type, String className) {
        statementTypeMap.put(type, className);
    }

    /**
     *  Unregisters a class to handle a specific SAML statement type when parsing XML
     * 
     * @param type          The statement type or element name
     */
    public static void unregFactory(QName type) {
        statementTypeMap.remove(type);
    }
    
    /**
     *  Locates an implementation class for a statement and constructs it based
     *  on the DOM provided.
     * 
     * @param e     The root of a DOM containing the SAML statement
     * @return SAMLStatement    A constructed statement object
     * 
     * @throws SAMLException    Thrown if an error occurs while constructing the object
     */
    public static SAMLStatement getInstance(Element e) throws SAMLException {
        if (e == null)
            throw new MalformedException(SAMLException.RESPONDER, "SAMLStatement.getInstance() given an empty DOM");
       
        try
        {
            if (XML.isElementNamed(e,XML.SAML_NS,"Statement") || XML.isElementNamed(e,XML.SAML_NS,"SubjectStatement"))
            {
                String className = (String)statementTypeMap.get(QName.getQNameAttribute(e, XML.XSI_NS, "type"));
                if (className == null)
                    throw new UnsupportedExtensionException(SAMLException.RESPONDER, "SAMLStatement.getInstance() unable to locate an implementation of specified statement type");
                Class implementation = Class.forName(className);
                Class[] paramtypes = {Class.forName("org.w3c.dom.Element")};
                Object[] params = {e};
                Constructor ctor = implementation.getDeclaredConstructor(paramtypes);
                return (SAMLStatement)ctor.newInstance(params);
            }
            else
            {
                String className = (String)statementTypeMap.get(new QName(e.getNamespaceURI(), e.getLocalName()));
                if (className == null)
                    throw new UnsupportedExtensionException(SAMLException.RESPONDER, "SAMLStatement.getInstance() unable to locate an implementation of specified statement type");
                Class implementation = Class.forName(className);
                Class[] paramtypes = {Class.forName("org.w3c.dom.Element")};
                Object[] params = {e};
                Constructor ctor = implementation.getDeclaredConstructor(paramtypes);
                return (SAMLStatement)ctor.newInstance(params);
            }
        }
        catch (ClassNotFoundException ex)
        {
            throw new SAMLException(SAMLException.REQUESTER, "SAMLStatement.getInstance() unable to locate implementation class for statement", ex);
        }
        catch (NoSuchMethodException ex)
        {
            throw new SAMLException(SAMLException.REQUESTER, "SAMLStatement.getInstance() unable to bind to constructor for statement", ex);
        }
        catch (InstantiationException ex)
        {
            throw new SAMLException(SAMLException.REQUESTER, "SAMLStatement.getInstance() unable to build implementation object for statement", ex);
        }
        catch (IllegalAccessException ex)
        {
            throw new SAMLException(SAMLException.REQUESTER, "SAMLStatement.getInstance() unable to access implementation of statement", ex);
        }
        catch (java.lang.reflect.InvocationTargetException ex)
        {
            ex.printStackTrace();
            Throwable e2 = ex.getTargetException();
            if (e2 instanceof SAMLException)
                throw (SAMLException)e2;
            else
                throw new SAMLException(SAMLException.REQUESTER, "SAMLStatement.getInstance() caught unknown exception while building statement object: " + e2.getMessage());
        }
    }

    /**
     *  Locates an implementation class for a statement and constructs it based
     *  on the stream provided.
     * 
     * @param in     The stream to deserialize from
     * @return SAMLStatement    A constructed statement object
     * 
     * @throws SAMLException    Thrown if an error occurs while constructing the object
     */
    public static SAMLStatement getInstance(InputStream in) throws SAMLException {
        try
        {
            Document doc = XML.parserPool.parse(in);
            return getInstance(doc.getDocumentElement());
        }
        catch (SAXException e)
        {
            NDC.push("getInstance");
            Category.getInstance("SAMLStatement").error("caught an exception while parsing a stream:\n" + e.getMessage());
            NDC.pop();
            throw new MalformedException("SAMLStatement.getInstance() caught exception while parsing a stream",e);
        }
        catch (java.io.IOException e)
        {
            NDC.push("getInstance");
            Category.getInstance("SAMLStatement").error("caught an exception while parsing a stream:\n" + e.getMessage());
            NDC.pop();
            throw new MalformedException("SAMLStatement.getInstance() caught exception while parsing a stream",e);
        }
    }
}

