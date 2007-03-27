/* By Leo Przybylski for the University of Arizona Summer 2005
 *
 * The Educational Community License
 *
 * This Educational Community License (the "License") applies to any
 * original work of authorship (the "Original Work") whose owner (the
 * "Licensor") has placed the following notice immediately following the
 * copyright notice for the Original Work:
 *
 * Copyright (c) 2005 Leo Przybylski
 *
 * Licensed under the Educational Community License version 1.0
 *
 * This Original Work, including software, source code, documents, or
 * other related items, is being provided by the copyright holder(s)
 * subject to the terms of the Educational Community License. By
 * obtaining, using and/or copying this Original Work, you agree that you
 * have read, understand, and will comply with the following terms and
 * conditions of the Educational Community License:
 *
 * Permission to use, copy, modify, merge, publish, distribute, and
 * sublicense this Original Work and its documentation, with or without
 * modification, for any purpose, and without fee or royalty to the
 * copyright holder(s) is hereby granted, provided that you include the
 * following on ALL copies of the Original Work or portions thereof,
 * including modifications or derivatives, that you make:
 * 
 * ¥ The full text of the Educational Community License in a location
 * viewable to users of the redistributed or derivative work.
 *
 * ¥ Any pre-existing intellectual property disclaimers, notices, or terms
 * and conditions.
 *
 * ¥ Notice of any changes or modifications to the Original Work,
 * including the date the changes were made.
 *
 * ¥ Any modifications of the Original Work must be distributed in such a
 * manner as to avoid any confusion with the Original Work of the
 * copyright holders.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * The name and trademarks of copyright holder(s) may NOT be used in
 * advertising or publicity pertaining to the Original or Derivative Works
 * without specific, written prior permission. Title to copyright in the
 * Original Work and any associated documentation will at all times remain
 * with the copyright holders.
 */
package org.kuali.toolkit;

import java.util.List;
import java.util.Map;

import org.kuali.bo.Document;
import org.kuali.service.DocumentProcessor;
import org.kuali.service.Policy;
import org.kuali.service.PolicyFactory;

/**
 * Determines what <code>{@link Policy}</code> implementation to be used. The only 
 * class that really uses a <code>{@link Policy}</code> implementation 
 * is the <code>{@link DocumentProcessor}</code> for processing a <code>{@link Document}</code> 
 * with arbitrary business rules. This class is in essence a factory for 
 * <code>{@link Policy}</code> instances. <code>{@link PolicyFactory}</code> class names 
 * are mapped directly to <code>{@link Document}</code> class names. 
 *
 * @author Leo Przybylski
 * @see org.kuali.bo.Document
 * @see org.kuali.service.DocumentProcessor
 * @see org.kuali.service.Policy
 * @see org.kuali.service.PolicyFactory
 */
public class PolicyResource {
    private static final String FACTORY_PREFIX = "kuali.map.factory.";
    private static final String MAP_STRING = "kuali.map.class";
    
    public PolicyResource() {
    }

    /**
     * Inquire from the policy mapping interface which <code>{@link PolicyFactory}</code> 
     * class is approriate for the specified
     *
     * @param document <code>{@link Document}</code> instance to find the <code>{@link PolicyFactory}</code> for.
     * @return PolicyFactory
     * @throws Exception
     */
    public static PolicyFactory findPolicyFactory(Document document) throws Exception {
        return getPolicyFactoryByName(getFactoryName(getPolicyMap(), document));
    }
    
    /**
     * Discover which <code>{@link PolicyFactory}</code> will produce the desired <code>{@link List}</code> of 
     * <code>{@link Policy}</code> objects to evalute business rules on a <code>{@link Document}</code> object.
     *
     * @param document <code>{@link Document}</code> used to determine which <code>{@link Policy}</code> instances
     * to retrieve based-on a mapping.
     * @return <code>{@link List}</code> of <code>{@link Policy}</code> instances to be evaluated in order.
     * @throws Exception
     */
    public static List getPolicyList(Document document) throws Exception {
        return (List) findPolicyFactory(document).getObjectInstance();
    }
    
    /**
     * Gets the <code>{@link Map}</code> of <code>{@link PolicyFactory}</code> definitions by querying the 
     * application configuration in the State.
     *
     * @param state <code>{@link StateManager}</code> with access to configuration
     * @return Map
     * @throws Exception 
     */
    private Map getPolicyMap(StateManager state) throws Exception {
        return (Map) Class.forName(state.get(MAP_STRING).toString()).newInstance();
    }
    
    /**
     * Query the <code>{@link Map}</code> of <code>{@link PolicyFactory}</code> definitions 
     * for the class name of the <code>{@link PolicyFactory}</code>
     *
     * @param policyMap <code>{@link Map}</code> of <cod>{@link PolicyFactory}</code> definitions.
     * @param document <code>{@link Document}</code> instance that desired 
     * <code>{@link PolicyFactory}</code> is mapped to.
     * @return String
     * @throws Exception
     * @see Map
     * @see Document
     * @see PolicyFactory
     */
    private String getFactoryName(Map policyMap, Document document) {
        policyMap.get(FACTORY_PREFIX + document.getClass().getName()).toString();
    }

    /**
     * Use <code>{@link String}</code> containing the desired <code>{@link PolicyFactory}</code> 
     * class name to create a <code>{@link PolicyFactory}</code> instance.
     *
     * @param factory_name desired <code>{@link PolicyFactory}</code> class name.
     * @return PolicyFactory
     * @throws Exception 
     */
    private PolicyFactory getPolicyFactoryByName(String factory_name) throws Exception {
        return Class.forName(factory_name).newInstance();
    }
}
