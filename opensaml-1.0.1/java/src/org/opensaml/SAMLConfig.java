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
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.xml.utils.WrongParserException;
import org.w3c.dom.Element;

/**
 *  OpenSAML configuration bundle.  Implemented as a singleton.  
 * 
 * @author     Walter Hoehn (wassa@columbia.edu)
 */
public class SAMLConfig {

	private static SAMLConfig instance;
	protected Properties properties;
	private static Logger log = Logger.getLogger(SAMLConfig.class.getName());

	protected SAMLConfig() {

		verifyUsableXmlParser();

		properties = new Properties();
		try {
			loadProperties(this.getClass().getResourceAsStream("/conf/opensaml.properties"));
		} catch (IOException e) {
			log.warn("Unable to load default library properties.");
		}

		org.apache.xml.security.Init.init();

		SAMLCondition.conditionTypeMap.put(
			new QName(XML.SAML_NS, "AudienceRestrictionCondition"),
			"org.opensaml.SAMLAudienceRestrictionCondition");
        SAMLCondition.conditionTypeMap.put(
            new QName(XML.SAML_NS, "AudienceRestrictionConditionType"),
            "org.opensaml.SAMLAudienceRestrictionCondition");
        SAMLCondition.conditionTypeMap.put(
            new QName(XML.SAML_NS, "DoNotCacheCondition"),
            "org.opensaml.SAMLDoNotCacheCondition");
        SAMLCondition.conditionTypeMap.put(
            new QName(XML.SAML_NS, "DoNotCacheConditionType"),
            "org.opensaml.SAMLDoNotCacheCondition");

		SAMLQuery.queryTypeMap.put(
            new QName(XML.SAMLP_NS, "AttributeQuery"),
            "org.opensaml.SAMLAttributeQuery");
		SAMLQuery.queryTypeMap.put(
            new QName(XML.SAMLP_NS, "AttributeQueryType"),
            "org.opensaml.SAMLAttributeQuery");
		SAMLQuery.queryTypeMap.put(
			new QName(XML.SAMLP_NS, "AuthenticationQuery"),
			"org.opensaml.SAMLAuthenticationQuery");
		SAMLQuery.queryTypeMap.put(
			new QName(XML.SAMLP_NS, "AuthenticationQueryType"),
			"org.opensaml.SAMLAuthenticationQuery");
		SAMLQuery.queryTypeMap.put(
			new QName(XML.SAMLP_NS, "AuthorizationDecisionQuery"),
			"org.opensaml.SAMLAuthorizationDecisionQuery");
		SAMLQuery.queryTypeMap.put(
			new QName(XML.SAMLP_NS, "AuthorizationDecisionQueryType"),
			"org.opensaml.SAMLAuthorizationDecisionQuery");

		SAMLStatement.statementTypeMap.put(
			new QName(XML.SAML_NS, "AttributeStatement"),
			"org.opensaml.SAMLAttributeStatement");
		SAMLStatement.statementTypeMap.put(
			new QName(XML.SAML_NS, "AttributeStatementType"),
			"org.opensaml.SAMLAttributeStatement");
		SAMLStatement.statementTypeMap.put(
			new QName(XML.SAML_NS, "AuthenticationStatement"),
			"org.opensaml.SAMLAuthenticationStatement");
		SAMLStatement.statementTypeMap.put(
			new QName(XML.SAML_NS, "AuthenticationStatementType"),
			"org.opensaml.SAMLAuthenticationStatement");
		SAMLStatement.statementTypeMap.put(
			new QName(XML.SAML_NS, "AuthorizationDecisionStatement"),
			"org.opensaml.SAMLAuthorizationDecisionStatement");
		SAMLStatement.statementTypeMap.put(
			new QName(XML.SAML_NS, "AuthorizationDecisionStatementType"),
			"org.opensaml.SAMLAuthorizationDecisionStatement");
	}

	/**
	 * Returns the active OpenSAML configuration.
	 * @return SAMLConfig
	 */
	public static SAMLConfig instance() {
		if (instance == null) {
			instance = new SAMLConfig();
			return instance;
		}
		return instance;
	}

	/**
	 * Enables a set of configuration properties.
	 * @param properties the configuration properties to be enabled
	 */
	public void setProperties(Properties properties) {
		this.properties.putAll(properties);
	}

	/**
	 * Enables a set of configuration properties.
	 * @param inStream an <code>InputStream</code> from which 
	 * a java properties file can be obtained.
	 */
	public void loadProperties(InputStream inStream) throws IOException {
		Properties newProperties = new Properties();
		newProperties.load(inStream);
		setProperties(newProperties);
	}

	/**
	 *  Sets a library configuration property<p>
	 * 
	 * @param  key      A property name
	 * @param  value    The value to set
	 */
	public void setProperty(String key, String value) {
		properties.setProperty(key, value);
	}

	/**
	 *  Gets a library configuration property
	 *
	 * @param  key      A property name
	 * @return          The property's value, or null if the property isn't set
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	/**
	 *  Gets a binary library configuration property in boolean form
	 *
	 * @param  key      A property name
	 * @return          The property's boolean value, or false if the property isn't set
	 */
	public boolean getBooleanProperty(String key) {
		return new Boolean(properties.getProperty(key)).booleanValue();
	}

    /**
     *  Sets a binary library configuration property in boolean form
     *
     * @param  key      A property name
     * @return          The property's boolean value, or false if the property isn't set
     */
    public void setBooleanProperty(String key, Boolean value) {
        setProperty(key, value.toString());
    }
    
    /**
     *  Refreshes state of library regarding SAML compatibility mode 
     */
    public void refresh() {
        XML.parserPool.registerSchema(
            XML.SAML_NS,
            getBooleanProperty("org.opensaml.compatibility-mode") ? XML.SAML_SCHEMA_ID : XML.SAML11_SCHEMA_ID,
            null);
        XML.parserPool.registerSchema(
            XML.SAMLP_NS,
            getBooleanProperty("org.opensaml.compatibility-mode") ? XML.SAMLP_SCHEMA_ID : XML.SAMLP11_SCHEMA_ID,
            null);
    }
    
	private void verifyUsableXmlParser() {
		try {
			Element.class.getDeclaredMethod(
				"setIdAttributeNS",
				new Class[] { String.class, String.class, java.lang.Boolean.TYPE });
		} catch (NoSuchMethodException e) {
			throw new WrongParserException(
				"OpenSAML requires an xml parser that supports DOM3 calls.  "
					+ "Xerces 2.5.0 (built with DOM3 support) has been included with this release and is "
					+ "recommended.  If you are using Java 1.4, make sure that you have enabled the Endorsed "
					+ "Standards Override Mechanism for this parser.");
		}

	}
}
