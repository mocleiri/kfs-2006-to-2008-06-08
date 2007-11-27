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
package org.kuali.module.purap.rule.event;

import org.kuali.core.document.Document;
import org.kuali.core.rule.BusinessRule;
import org.kuali.core.rule.event.KualiDocumentEventBase;
import org.kuali.kfs.KFSConstants;
import org.kuali.module.purap.document.AccountsPayableDocument;
import org.kuali.module.purap.rule.CalculateAccountsPayableRule;

/**
 * Calculate event for Accounts Payable Document
 * This could be triggered when a user presses the Calculate button to calculate the doc.
 */
public final class CalculateAccountsPayableEvent extends KualiDocumentEventBase {

    /**
     * Overridden constructor.
     * 
     * @param document the document for this event
     */
    public CalculateAccountsPayableEvent(Document document) {
        this(KFSConstants.EMPTY_STRING, document);
    }

    /**
     * Constructs a CalculateAccountsPayableEvent with the given errorPathPrefix, document, and item.
     * 
     * @param errorPathPrefix the error path
     * @param document document the event was invoked upon
     */
    public CalculateAccountsPayableEvent(String errorPathPrefix, Document document) {
        super("calculating on document " + getDocumentId(document), errorPathPrefix, document);
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return CalculateAccountsPayableRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((CalculateAccountsPayableRule) rule).processCalculateAccountsPayableBusinessRules((AccountsPayableDocument) getDocument());
    }
}