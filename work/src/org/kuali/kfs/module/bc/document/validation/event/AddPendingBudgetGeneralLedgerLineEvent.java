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
package org.kuali.module.budget.rule.event;

import org.kuali.core.document.Document;
import org.kuali.core.rule.BusinessRule;
import org.kuali.core.rule.event.KualiDocumentEventBase;
import org.kuali.kfs.bo.AccountingLine;
import org.kuali.module.budget.bo.PendingBudgetConstructionGeneralLedger;
import org.kuali.module.budget.document.BudgetConstructionDocument;
import org.kuali.module.budget.rule.AddPendingBudgetGeneralLedgerLineRule;

/**
 * Defines the add new line event for revenue or expenditure tab on the Budget Construction Document.
 */
public class AddPendingBudgetGeneralLedgerLineEvent extends KualiDocumentEventBase {
    private BudgetConstructionDocument budgetConstructionDocument;
    private PendingBudgetConstructionGeneralLedger pendingBudgetConstructionGeneralLedger;
    private boolean isRevenue;

    /**
     * Constructs a AddPendingBudgetGeneralLedgerLineEvent.java.
     * 
     * @param errorPathPrefix
     * @param document
     * @param pendingBudgetConstructionGeneralLedger
     * @param isRev
     */
    public AddPendingBudgetGeneralLedgerLineEvent(String errorPathPrefix, Document document, PendingBudgetConstructionGeneralLedger pendingBudgetConstructionGeneralLedger, boolean isRev) {
        super("", errorPathPrefix, document);
        this.pendingBudgetConstructionGeneralLedger = pendingBudgetConstructionGeneralLedger;
        this.budgetConstructionDocument = (BudgetConstructionDocument) document;
        this.isRevenue = isRev;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        return AddPendingBudgetGeneralLedgerLineRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddPendingBudgetGeneralLedgerLineRule) rule).processAddPendingBudgetGeneralLedgerLineRules(budgetConstructionDocument, pendingBudgetConstructionGeneralLedger, isRevenue);
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEventBase#validate()
     */
    @Override
    public void validate() {
        // TODO Auto-generated method stub
        super.validate();
    }

    /**
     * Gets the budgetConstructionDocument attribute.
     * 
     * @return Returns the budgetConstructionDocument.
     */
    public BudgetConstructionDocument getBudgetConstructionDocument() {
        return budgetConstructionDocument;
    }

    /**
     * Sets the budgetConstructionDocument attribute value.
     * 
     * @param budgetConstructionDocument The budgetConstructionDocument to set.
     */
    public void setBudgetConstructionDocument(BudgetConstructionDocument budgetConstructionDocument) {
        this.budgetConstructionDocument = budgetConstructionDocument;
    }

    /**
     * Gets the pendingBudgetConstructionGeneralLedger attribute.
     * 
     * @return Returns the pendingBudgetConstructionGeneralLedger.
     */
    public PendingBudgetConstructionGeneralLedger getPendingBudgetConstructionGeneralLedger() {
        return pendingBudgetConstructionGeneralLedger;
    }

    /**
     * Sets the pendingBudgetConstructionGeneralLedger attribute value.
     * 
     * @param pendingBudgetConstructionGeneralLedger The pendingBudgetConstructionGeneralLedger to set.
     */
    public void setPendingBudgetConstructionGeneralLedger(PendingBudgetConstructionGeneralLedger pendingBudgetConstructionGeneralLedger) {
        this.pendingBudgetConstructionGeneralLedger = pendingBudgetConstructionGeneralLedger;
    }


}