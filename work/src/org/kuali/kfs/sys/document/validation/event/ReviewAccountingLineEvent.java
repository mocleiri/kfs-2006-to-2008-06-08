/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kfs.rule.event;

import org.kuali.core.document.Document;
import org.kuali.core.rule.BusinessRule;
import org.kuali.kfs.bo.AccountingLine;
import org.kuali.kfs.document.AccountingDocument;
import org.kuali.kfs.rule.ReviewAccountingLineRule;

/**
 * This class represents the review accounting line event, which is generated by the Document service for every accountingLine of an
 * incoming document, when that accountingLine isn't being updated or deleted or added.
 */
public final class ReviewAccountingLineEvent extends AccountingLineEventBase {
    /**
     * Constructs an ReviewAccountingLineEvent with the given errorPathPrefix, document, and accountingLine.
     * 
     * @param errorPathPrefix
     * @param document
     * @param accountingLine
     */
    public ReviewAccountingLineEvent(String errorPathPrefix, Document document, AccountingLine accountingLine) {
        super("reviewing accountingLine in document " + getDocumentId(document), errorPathPrefix, document, accountingLine);
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return ReviewAccountingLineRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((ReviewAccountingLineRule) rule).processReviewAccountingLineBusinessRules((AccountingDocument) getDocument(), getAccountingLine());
    }
}