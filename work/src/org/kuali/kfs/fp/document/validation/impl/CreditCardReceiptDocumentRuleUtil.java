/*
 * Copyright 2006-2007 The Kuali Foundation.
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

import static org.kuali.kfs.rules.AccountingDocumentRuleBaseConstants.ERROR_PATH.DOCUMENT_ERROR_PREFIX;
import static org.kuali.module.financial.rules.CreditCardReceiptDocumentRuleConstants.CREDIT_CARD_RECEIPT_PREFIX;

import org.kuali.core.service.DataDictionaryService;
import org.kuali.core.service.DictionaryValidationService;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kfs.KFSKeyConstants;
import org.kuali.kfs.KFSPropertyConstants;
import org.kuali.kfs.KFSKeyConstants.CashReceipt;
import org.kuali.kfs.context.SpringContext;
import org.kuali.module.financial.bo.CreditCardDetail;
import org.kuali.module.financial.document.CreditCardReceiptDocument;

/**
 * Common Credit Card Receipt Document rule utilities.
 */
public class CreditCardReceiptDocumentRuleUtil {
    /**
     * This method method will invoke the data dictionary validation for a CreditCardDetail bo instance, in addition to checking
     * existence of the CreditCardType and CreditCardVendor attributes that hang off of it. This method assumes that the document
     * hierarchy for the error map path is managed outside of this call.
     * 
     * @param creditCardReceipt
     * @return boolean
     */
    public static boolean validateCreditCardReceipt(CreditCardDetail creditCardReceipt) {
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        int originalErrorCount = errorMap.getErrorCount();

        // call the DD validation which checks basic data integrity
        SpringContext.getBean(DictionaryValidationService.class).validateBusinessObject(creditCardReceipt);
        boolean isValid = (errorMap.getErrorCount() == originalErrorCount);

        // check that dollar amount is not zero before continuing
        if (isValid) {
            isValid = !creditCardReceipt.getCreditCardAdvanceDepositAmount().isZero();
            if (!isValid) {
                String label = SpringContext.getBean(DataDictionaryService.class).getAttributeLabel(CreditCardDetail.class, KFSPropertyConstants.CREDIT_CARD_ADVANCE_DEPOSIT_AMOUNT);
                errorMap.putError(KFSPropertyConstants.CREDIT_CARD_ADVANCE_DEPOSIT_AMOUNT, KFSKeyConstants.ERROR_ZERO_AMOUNT, label);
            }
        }

        if (isValid) {
            isValid = SpringContext.getBean(DictionaryValidationService.class).validateReferenceExists(creditCardReceipt, KFSPropertyConstants.CREDIT_CARD_TYPE);
            if (!isValid) {
                String label = SpringContext.getBean(DataDictionaryService.class).getAttributeLabel(CreditCardDetail.class, KFSPropertyConstants.FINANCIAL_DOCUMENT_CREDIT_CARD_TYPE_CODE);
                errorMap.putError(KFSPropertyConstants.FINANCIAL_DOCUMENT_CREDIT_CARD_TYPE_CODE, KFSKeyConstants.ERROR_EXISTENCE, label);
            }
        }
        if (isValid) {
            isValid = SpringContext.getBean(DictionaryValidationService.class).validateReferenceExists(creditCardReceipt, KFSPropertyConstants.CREDIT_CARD_VENDOR);
            if (!isValid) {
                String label = SpringContext.getBean(DataDictionaryService.class).getAttributeLabel(CreditCardDetail.class, KFSPropertyConstants.FINANCIAL_DOCUMENT_CREDIT_CARD_VENDOR_NUMBER);
                errorMap.putError(KFSPropertyConstants.FINANCIAL_DOCUMENT_CREDIT_CARD_VENDOR_NUMBER, KFSKeyConstants.ERROR_EXISTENCE, label);
            }
        }

        return isValid;
    }


    /**
     * Checks whether the CashReceiptDocument's cash totals are invalid, generating global errors if so.
     * 
     * @param cashReceiptDocument
     * @return whether any of the CashReceiptDocument's cash totals are invalid
     */
    public static boolean areCashTotalsInvalid(CreditCardReceiptDocument ccrDocument) {
        String documentEntryName = ccrDocument.getDocumentHeader().getWorkflowDocument().getDocumentType();

        boolean isInvalid = isTotalInvalid(ccrDocument, ccrDocument.getTotalDollarAmount(), documentEntryName, KFSPropertyConstants.CREDIT_CARD_RECEIPTS_TOTAL);

        return isInvalid;
    }

    /**
     * puts an error message in the error map for that property if the amount is negative.
     * 
     * @param cashReceiptDocument
     * @param totalAmount
     * @param documentEntryName
     * @param propertyName
     * @return true if the totalAmount is an invalid value
     */
    private static boolean isTotalInvalid(CreditCardReceiptDocument ccrDocument, KualiDecimal totalAmount, String documentEntryName, String propertyName) {
        boolean isInvalid = false;
        String errorProperty = CREDIT_CARD_RECEIPT_PREFIX + propertyName;

        // treating null totalAmount as if it were a zero
        DataDictionaryService dds = SpringContext.getBean(DataDictionaryService.class);
        String errorLabel = dds.getAttributeLabel(documentEntryName, propertyName);
        if ((totalAmount == null) || totalAmount.isZero()) {
            GlobalVariables.getErrorMap().putError(errorProperty, CashReceipt.ERROR_ZERO_TOTAL, errorLabel);

            isInvalid = true;
        }
        else {
            int precount = GlobalVariables.getErrorMap().size();

            DictionaryValidationService dvs = SpringContext.getBean(DictionaryValidationService.class);
            dvs.validateDocumentAttribute(ccrDocument, propertyName, DOCUMENT_ERROR_PREFIX);

            // replace generic error message, if any, with something more readable
            GlobalVariables.getErrorMap().replaceError(errorProperty, KFSKeyConstants.ERROR_MAX_LENGTH, CashReceipt.ERROR_EXCESSIVE_TOTAL, errorLabel);

            int postcount = GlobalVariables.getErrorMap().size();
            isInvalid = (postcount > precount);
        }

        return isInvalid;
    }
}