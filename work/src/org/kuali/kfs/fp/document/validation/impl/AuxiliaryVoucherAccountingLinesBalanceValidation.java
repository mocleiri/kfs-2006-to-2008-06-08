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
import static org.kuali.kfs.KFSKeyConstants.ERROR_DOCUMENT_BALANCE_CONSIDERING_CREDIT_AND_DEBIT_AMOUNTS;

import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kfs.rule.event.AttributedDocumentEvent;
import org.kuali.kfs.validation.GenericValidation;
import org.kuali.module.financial.document.AuxiliaryVoucherDocument;

/**
 * Validation for Auxiliary Voucher, which checks tha tthe accounting lines on the document, with all of
 * their various credits and debits, balance.
 */
public class AuxiliaryVoucherAccountingLinesBalanceValidation extends GenericValidation {
    private AuxiliaryVoucherDocument auxiliaryVoucherDocumentForValidation;

    /**
     * Returns true if credit/debit entries are in balance
     * @see org.kuali.kfs.validation.Validation#validate(org.kuali.kfs.rule.event.AttributedDocumentEvent)
     */
    public boolean validate(AttributedDocumentEvent event) {
        KualiDecimal creditAmount = getAuxiliaryVoucherDocumentForValidation().getCreditTotal();
        KualiDecimal debitAmount = getAuxiliaryVoucherDocumentForValidation().getDebitTotal();

        boolean balanced = debitAmount.equals(creditAmount);
        if (!balanced) {
            String errorParams[] = { creditAmount.toString(), debitAmount.toString() };
            GlobalVariables.getErrorMap().putError(ACCOUNTING_LINE_ERRORS, ERROR_DOCUMENT_BALANCE_CONSIDERING_CREDIT_AND_DEBIT_AMOUNTS, errorParams);
        }
        return balanced;
    }

    /**
     * Gets the auxiliaryVoucherDocumentForValidation attribute. 
     * @return Returns the auxiliaryVoucherDocumentForValidation.
     */
    public AuxiliaryVoucherDocument getAuxiliaryVoucherDocumentForValidation() {
        return auxiliaryVoucherDocumentForValidation;
    }

    /**
     * Sets the auxiliaryVoucherDocumentForValidation attribute value.
     * @param auxiliaryVoucherDocumentForValidation The auxiliaryVoucherDocumentForValidation to set.
     */
    public void setAuxiliaryVoucherDocumentForValidation(AuxiliaryVoucherDocument accountingDocumentForValidation) {
        this.auxiliaryVoucherDocumentForValidation = accountingDocumentForValidation;
    }
}
