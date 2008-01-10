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
package org.kuali.module.kra.routingform.rules.event;

import org.kuali.core.document.Document;
import org.kuali.core.rule.BusinessRule;
import org.kuali.core.rule.event.KualiDocumentEvent;
import org.kuali.core.rule.event.KualiDocumentEventBase;
import org.kuali.module.kra.routingform.document.RoutingFormDocument;
import org.kuali.module.kra.routingform.rules.RoutingFormDocumentRule;

/**
 * Class capturing a run audit event.
 */
public class RoutingFormBudgetLinkEvent extends KualiDocumentEventBase implements KualiDocumentEvent {

    private String[] selectedBudgetPeriods;
    private boolean allPeriods;
    private boolean checkPeriods;


    /**
     * Constructs a RunAuditEvent with the given errorPathPrefix and document.
     * 
     * @param errorPathPrefix
     * @param document
     * @param accountingLine
     */
    public RoutingFormBudgetLinkEvent(String errorPathPrefix, Document document) {
        super("Linking Budget to " + getDocumentId(document), errorPathPrefix, document);
    }

    /**
     * Constructs a RunAuditEvent with the given document.
     * 
     * @param errorPathPrefix
     * @param document
     * @param accountingLine
     */
    public RoutingFormBudgetLinkEvent(RoutingFormDocument routingFormDocument, String[] selectedBudgetPeriods, boolean allPeriods, boolean checkPeriods) {
        this("", routingFormDocument);
        this.selectedBudgetPeriods = selectedBudgetPeriods;
        this.allPeriods = allPeriods;
        this.checkPeriods = checkPeriods;

    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return RoutingFormDocumentRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((RoutingFormDocumentRule) rule).processBudgetRoutingFormLink(((RoutingFormDocument) this.getDocument()), selectedBudgetPeriods, allPeriods, checkPeriods);
    }
}