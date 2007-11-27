/*
 * Copyright 2005-2007 The Kuali Foundation.
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

package org.kuali.module.financial.rules;

import org.kuali.kfs.bo.AccountingLine;
import org.kuali.kfs.bo.GeneralLedgerPendingEntry;
import org.kuali.kfs.document.AccountingDocument;
import org.kuali.module.financial.document.DistributionOfIncomeAndExpenseDocument;
import org.kuali.module.financial.document.YearEndDocumentUtil;

/**
 * Business rules applicable to <code>YearEndDistributionOfIncomeAndExpenseDocument</code>
 */
public class YearEndDistributionOfIncomeAndExpenseDocumentRule extends DistributionOfIncomeAndExpenseDocumentRule {

    /**
     * This method calls the super class's overridden method to perform the general customization actions, then calls the 
     * YearEndDocumentUtil matching method to perform year end specific customization activities.
     * 
     * @param accountingDocument The accounting document containing the general ledger pending entries being customized.
     * @param accountingLine The accounting line the explicit general ledger pending entry was generated from.
     * @param explicitEntry The explicit general ledger pending entry to be customized.
     * 
     * @see org.kuali.module.financial.rules.DistributeOfIncomeAndExpenseDocumentRule#customizeExplicitGeneralLedgerPendingEntry(org.kuali.kfs.document.AccountingDocument,
     *      org.kuali.kfs.bo.AccountingLine, org.kuali.module.gl.bo.GeneralLedgerPendingEntry)
     * @see YearEndDocumentUtil#customizeExplicitGeneralLedgerPendingEntry(TransactionalDocument, AccountingLine,
     *      GeneralLedgerPendingEntry)
     */
    @Override
    protected void customizeExplicitGeneralLedgerPendingEntry(AccountingDocument accountingDocument, AccountingLine accountingLine, GeneralLedgerPendingEntry explicitEntry) {
        super.customizeExplicitGeneralLedgerPendingEntry(accountingDocument, accountingLine, explicitEntry);
        YearEndDocumentUtil.customizeExplicitGeneralLedgerPendingEntry(accountingDocument, accountingLine, explicitEntry);
    }

    /**
     * Overriding to return the corresponding parent class DistributionOfIncomeAndExpenseDocument.
     * 
     * @param financialDocument The financial document the class will be determined for.
     * @return The class type of the document passed in.
     * 
     * @see org.kuali.kfs.rules.AccountingDocumentRuleBase#getAccountingLineDocumentClass(org.kuali.kfs.document.AccountingDocument)
     */
    @Override
    protected Class getAccountingLineDocumentClass(AccountingDocument financialDocument) {
        return DistributionOfIncomeAndExpenseDocument.class;
    }

}