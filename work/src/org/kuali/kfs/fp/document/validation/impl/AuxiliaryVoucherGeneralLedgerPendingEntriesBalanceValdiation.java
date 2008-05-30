/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.module.financial.document.validation.impl;

import static org.kuali.kfs.KFSConstants.ACCOUNTING_LINE_ERRORS;
import static org.kuali.kfs.KFSConstants.GL_CREDIT_CODE;
import static org.kuali.kfs.KFSKeyConstants.ERROR_DOCUMENT_BALANCE;

import org.kuali.core.exceptions.ValidationException;
import org.kuali.core.service.DocumentTypeService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kfs.bo.GeneralLedgerPendingEntry;
import org.kuali.kfs.context.SpringContext;
import org.kuali.kfs.document.AccountingDocument;
import org.kuali.kfs.rule.event.AttributedDocumentEvent;
import org.kuali.kfs.service.GeneralLedgerPendingEntryService;
import org.kuali.kfs.validation.GenericValidation;
import org.kuali.module.financial.document.DistributionOfIncomeAndExpenseDocument;

/**
 * Validation that checks that the general ledger pending entries associated with an auxiliary voucher
 * document balance.
 */
public class AuxiliaryVoucherGeneralLedgerPendingEntriesBalanceValdiation extends GenericValidation {
    private AccountingDocument accountingDocumentForValidation;
    private GeneralLedgerPendingEntryService generalLedgerPendingEntryService;

    /**
     * Returns true if the explicit, non-DI credit and debit GLPEs derived from the document's accountingLines are in balance
     * @see org.kuali.kfs.validation.Validation#validate(org.kuali.kfs.rule.event.AttributedDocumentEvent)
     */
    public boolean validate(AttributedDocumentEvent event) {
//      generate GLPEs specifically here so that we can compare debits to credits
        if (!getGeneralLedgerPendingEntryService().generateGeneralLedgerPendingEntries(getAccountingDocumentForValidation())) {
            throw new ValidationException("general ledger GLPE generation failed");
        }

        // now loop through all of the GLPEs and calculate buckets for debits and credits
        KualiDecimal creditAmount = KualiDecimal.ZERO;
        KualiDecimal debitAmount = KualiDecimal.ZERO;

        for (GeneralLedgerPendingEntry glpe : getAccountingDocumentForValidation().getGeneralLedgerPendingEntries()) {
            // make sure we are looking at only the explicit entries that aren't DI types
            if (!glpe.isTransactionEntryOffsetIndicator() && !glpe.getFinancialDocumentTypeCode().equals(SpringContext.getBean(DocumentTypeService.class).getDocumentTypeCodeByClass(DistributionOfIncomeAndExpenseDocument.class))) {
                if (GL_CREDIT_CODE.equals(glpe.getTransactionDebitCreditCode())) {
                    creditAmount = creditAmount.add(glpe.getTransactionLedgerEntryAmount());
                }
                else { // DEBIT
                    debitAmount = debitAmount.add(glpe.getTransactionLedgerEntryAmount());
                }
            }
        }

        boolean balanced = debitAmount.equals(creditAmount);
        if (!balanced) {
            String errorParams[] = { creditAmount.toString(), debitAmount.toString() };
            GlobalVariables.getErrorMap().putError(ACCOUNTING_LINE_ERRORS, ERROR_DOCUMENT_BALANCE, errorParams);
        }
        return balanced;
    }

    /**
     * Gets the accountingDocumentForValidation attribute. 
     * @return Returns the accountingDocumentForValidation.
     */
    public AccountingDocument getAccountingDocumentForValidation() {
        return accountingDocumentForValidation;
    }

    /**
     * Sets the accountingDocumentForValidation attribute value.
     * @param accountingDocumentForValidation The accountingDocumentForValidation to set.
     */
    public void setAccountingDocumentForValidation(AccountingDocument accountingDocumentForValidation) {
        this.accountingDocumentForValidation = accountingDocumentForValidation;
    }

    /**
     * Gets the generalLedgerPendingEntryService attribute. 
     * @return Returns the generalLedgerPendingEntryService.
     */
    public GeneralLedgerPendingEntryService getGeneralLedgerPendingEntryService() {
        return generalLedgerPendingEntryService;
    }

    /**
     * Sets the generalLedgerPendingEntryService attribute value.
     * @param generalLedgerPendingEntryService The generalLedgerPendingEntryService to set.
     */
    public void setGeneralLedgerPendingEntryService(GeneralLedgerPendingEntryService generalLedgerPendingEntryService) {
        this.generalLedgerPendingEntryService = generalLedgerPendingEntryService;
    }
}