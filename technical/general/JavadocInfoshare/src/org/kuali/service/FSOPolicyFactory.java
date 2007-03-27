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
 * ,A%(B The full text of the Educational Community License in a location
 * viewable to users of the redistributed or derivative work.
 *
 * ,A%(B Any pre-existing intellectual property disclaimers, notices, or terms
 * and conditions.
 *
 * ,A%(B Notice of any changes or modifications to the Original Work,
 * including the date the changes were made.
 *
 * ,A%(B Any modifications of the Original Work must be distributed in such a
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
package org.kuali.service;

import java.util.ArrayList;
import java.util.List;

import org.kuali.bo.Document;

/**
 * A <code>{@link PolicyFactory}</code> definition that retrieves <code>{@link Policy}</code> 
 * instances for University of Arizona &copy; Financial Services Office specific business rules.<br/>
 * <br/>
 * <code>{@link Policy}</code> class names are mapped directly to <code>{@link Document}</code>
 * class names where there is a possibility of many-to-many <code>{@link Policy}/<code>{@link Document} mapping combinations.
 *
 * @author Leo Przybylski
 * @see Document
 * @see Policy
 * @see PolicyFactory
 */ 
public class FSOPolicyFactory extends PolicyFactory {
    /**
     * Create a new instance of <code>{@link FSOPolicyFactory}</code>
     */
    public FSOPolicyFactory() {
    }

    /**
     * @see PolicyFactory#getObjectInstance(Document)
     * @see PolicyFactory
     * @see Policy
     * @see FSOPolicy
     */ 
    public Object geObjectInstance(Document document) throws Exception {
        // The purpose behind the factory is flexibility with
        // assigning Policy implementations to a
        // TransactionalDocument.
        
        // There is already a mapping to the PolicyFactory from
        // the TransactionalDocument, so another mapping isn$(B!G(Bt
        // necessary. It is possible to just add object
        // instantiations here without losing any flexibility at
        // all. For example, the following is possible:

        List retval = new ArrayList();

        retval.add(new FSOPolicy(document));
        // retval.add(new FSOInternalBillingPolicy(document));
        // retval.add(new FSOPayrollPolicy(document));
        return retval;

        // It is just EXTRA flexibility to reuse the 
        // mapping interface
    }
}
