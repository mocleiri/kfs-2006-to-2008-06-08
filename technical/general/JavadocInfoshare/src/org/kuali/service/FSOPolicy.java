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
package org.kuali.service;

import org.kuali.bo.Document;

/**
 * Class definition of a business rule. <code>{@link Policy}<?code> instances are evaluated. 
 * With each <code>{@link Policy}</code> instance that is evaluated by a 
 * <code>{@link DocumentProcessor}</code> a new business rule is evaluated against a 
 * <code>{@link Document}  {@link FSOPolicy}</code> class is an implementation of the 
 * <code>{@link Policy}</code> interface. It evaluates the following business rules described for 
 * the University of Arizona &copy; Financial Services Office.<br/>
 * <br/>
 * The following rules are enforced:
 * <ul>
 *   <li/> <code>ObjectCode</code> instances do not start with 1
 *   <li/> <code>accountTypeCode</code> field does not equal 'C'
 *   <li/> <code>subFundGroupCode</code> does not equal 'G' when the <code>{@link Document}</code> 
 * instance's <code>ObjectCode</code> equals 555000
 *   <li/> Check <code>ObjectCode</code> instances do not start with 1 
 * </ul>
 *
 * @author Leo Przybylski
 * @see org.kuali.bo.Document
 * @see org.kuali.service.DocumentProcessor
 * @see org.kuali.service.Policy
 */
public class FSOPolicy implements Policy {
    private Document _document;
    private static final String BAD_ACCOUNT_TYPE_MESSAGE = "";
    private static final String BAD_GROUP_CODE_MESSAGE = "";
    private static final String BAD_OBJECT_CODE_MESSAGE = "";

    /**
     * Default Constructor
     * 
     * @param document is a <code>{@link Document}</code>
     */
    public FSOPolicy(Document document) {
        setDocument(document);
    }
    
    /**
     * @see org.kuali.service.Policy#getDocument()
     */
    public Document getDocument() {
        return _document;
    }

    /**
     * @see org.kuali.service.Policy#getDocument()
     */
    public void setDocument(Document document) {
        _document = document;
    }

    /**
     * @see org.kuali.service.Policy#evaluate()
     */
    public void evaluate() throws PolicyException {
        InternalBillingDocument doc = (InternalBillingDocument) getDocument();
        AccountingLine act1 = null;
        
        for (Iterator line_it = doc.getSourceAccountingLines();
             line_it.hasNext(); act1 = line_it.next()) {
            
            if (act1.getObjectCode().toString().startsWith("1")) {
                throw new PolicyException(BAD_OBJECT_CODE_MESSAGE);
            }
            if (act1.getAccount().getAccountTypeCode().equals("C")) {
                throw new PolicyException(BAD_ACCOUNT_TYPE_MESSAGE);
            }
            if (act1.getAccount().getSubFundGroupCode.equals("G")
                && act1.getObjectCode().toString().equals("555000")) {
                throw new PolicyException(BAD_GROUP_CODE_MESSAGE);
            }
        }
             
    }
}
