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

import static org.kuali.module.financial.rules.InternalBillingDocumentRuleConstants.CAPITAL_OBJECT_SUB_TYPE_CODES;

import org.kuali.core.document.Document;
import org.kuali.core.util.ExceptionUtils;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.KFSKeyConstants;
import org.kuali.kfs.KFSPropertyConstants;
import org.kuali.kfs.bo.AccountingLine;
import org.kuali.kfs.context.SpringContext;
import org.kuali.kfs.document.AccountingDocument;
import org.kuali.kfs.rules.AccountingDocumentRuleBase;
import org.kuali.kfs.service.ParameterEvaluator;
import org.kuali.kfs.service.ParameterService;
import org.kuali.module.financial.document.InternalBillingDocument;

/**
 * Business rule(s) applicable to InternalBilling document.
 */
public class InternalBillingDocumentRule extends AccountingDocumentRuleBase {

    /**
     * @see IsDebitUtils#isDebitConsideringSection(FinancialDocumentRuleBase, FinancialDocument, AccountingLine)
     * @see org.kuali.core.rule.AccountingLineRule#isDebit(org.kuali.core.document.FinancialDocument,
     *      org.kuali.core.bo.AccountingLine)
     */
    public boolean isDebit(AccountingDocument transactionalDocument, AccountingLine accountingLine) {
        return IsDebitUtils.isDebitConsideringSection(this, transactionalDocument, accountingLine);
    }

    /**
     * Overrides to only disallow zero, allowing negative amounts.
     * 
     * @see org.kuali.module.financial.rules.FinancialDocumentRuleBase#isAmountValid(FinancialDocument, AccountingLine)
     */
    @Override
    public boolean isAmountValid(AccountingDocument document, AccountingLine accountingLine) {
        if (accountingLine.getAmount().equals(KFSConstants.ZERO)) {
            GlobalVariables.getErrorMap().putError(KFSConstants.AMOUNT_PROPERTY_NAME, KFSKeyConstants.ERROR_ZERO_AMOUNT, "an accounting line");
            LOG.info("failing isAmountValid - zero check");
            return false;
        }
        return true;
    }

    /**
     * @see org.kuali.module.financial.rules.FinancialDocumentRuleBase#processCustomAddAccountingLineBusinessRules(FinancialDocument,
     *      AccountingLine)
     */
    @Override
    public boolean processCustomAddAccountingLineBusinessRules(AccountingDocument document, AccountingLine accountingLine) {
        return processCommonCustomAccountingLineRules(accountingLine);
    }

    /**
     * @see org.kuali.module.financial.rules.FinancialDocumentRuleBase#processCustomReviewAccountingLineBusinessRules(FinancialDocument,
     *      AccountingLine)
     */
    @Override
    public boolean processCustomReviewAccountingLineBusinessRules(AccountingDocument document, AccountingLine accountingLine) {
        return processCommonCustomAccountingLineRules(accountingLine);
    }

    /**
     * @see org.kuali.module.financial.rules.FinancialDocumentRuleBase#processCustomUpdateAccountingLineBusinessRules(FinancialDocument,
     *      AccountingLine, AccountingLine)
     */
    @Override
    public boolean processCustomUpdateAccountingLineBusinessRules(AccountingDocument document, AccountingLine originalAccountingLine, AccountingLine updatedAccountingLine) {
        return processCommonCustomAccountingLineRules(updatedAccountingLine);
    }

    /**
     * Processes rules common to the three custom accounting line rule methods.
     * 
     * @param accountingLine
     * @return whether the rule succeeds
     */
    private boolean processCommonCustomAccountingLineRules(AccountingLine accountingLine) {
        boolean success = true;
        success &= validIndianaStudentFeesNotContinueEduc(accountingLine);
        if (success) {
            success &= validateCapitalObjectCodes(accountingLine);
        }
        return success;
    }

    /**
     * Note: This method implements an IU specific business rule. <p/> This method evaluates the accounting line's object sub type
     * code for its object code in addition to the accounting line's sub fund group for the account. This didn't fit any of the
     * interface methods, so this rule was programmed in the "custom rule" method. It only applies to lines in the income section.
     * 
     * @param accountingLine
     * @return whether this rule passes
     */
    private boolean validIndianaStudentFeesNotContinueEduc(AccountingLine accountingLine) {
        String objectSubTypeCode = accountingLine.getObjectCode().getFinancialObjectSubTypeCode();
        String actualSubFundGroupCode = accountingLine.getAccount().getSubFundGroupCode();
        String requiredSubFundGroupCode = SUB_FUND_GROUP_CODE.CONTINUE_EDUC;

        if (accountingLine.isSourceAccountingLine() && OBJECT_SUB_TYPE_CODE.STUDENT_FEES.equals(objectSubTypeCode) && !requiredSubFundGroupCode.equals(actualSubFundGroupCode)) {
            // The user could fix this via either ObjectCode or Account, but we arbitrarily choose the ObjectCode to highlight.
            reportError(KFSPropertyConstants.OBJECT_CODE, KFSKeyConstants.ERROR_DOCUMENT_INCORRECT_OBJ_CODE_WITH_SUB_FUND_GROUP, accountingLine.getFinancialObjectCode(), objectSubTypeCode, requiredSubFundGroupCode, actualSubFundGroupCode);
            return false;
        }
        return true;
    }

    /**
     * Evaluates the object sub type code of the accounting line's object code to determine whether the object code is a capital
     * object code. If so, and this accounting line is in the income section, then it is not valid. <p/> Note: this is an IU
     * specific business rule.
     * 
     * @param accountingLine
     * @return whether the given line is valid with respect to capital object codes
     */
    private boolean validateCapitalObjectCodes(AccountingLine accountingLine) {
        if (accountingLine.isSourceAccountingLine() && isCapitalObject(accountingLine)) {
            GlobalVariables.getErrorMap().putError(KFSPropertyConstants.FINANCIAL_OBJECT_CODE, KFSKeyConstants.ERROR_DOCUMENT_IB_CAPITAL_OBJECT_IN_INCOME_SECTION);
            LOG.debug("APC rule failure " + ExceptionUtils.describeStackLevel(0));
            return false;
        }
        else {
            return true;
        }
        // todo: phase II
        // int pendPurchaseCount = 0; // TODO need to do something with this but I have no idea what
        // if (!SUB_FUND_GROUP_CODE.CODE_EXTAGY.equals(subFundGroupCode) && restrictedCapitalObjectCodes.contains(objectSubTypeCode)
        // && (pendPurchaseCount <= 0))
    }

    /**
     * Checks whether the given AccountingLine's ObjectCode is a capital one.
     * 
     * @param accountingLine
     * @return whether the given AccountingLine's ObjectCode is a capital one.
     */
    private boolean isCapitalObject(AccountingLine accountingLine) {
        ParameterEvaluator evaluator = SpringContext.getBean(ParameterService.class).getParameterEvaluator(InternalBillingDocument.class, CAPITAL_OBJECT_SUB_TYPE_CODES, accountingLine.getObjectCode().getFinancialObjectSubTypeCode());
        return evaluator.evaluationSucceeds();
    }

    /**
     * @see FinancialDocumentRuleBase#processCustomRouteDocumentBusinessRules(Document)
     */
    @Override
    public boolean processCustomRouteDocumentBusinessRules(Document document) {
        // This super method actually does something.
        boolean success = true;
        success &= super.processCustomRouteDocumentBusinessRules(document);
        if (success) {
            success &= validateItems((InternalBillingDocument) document);
        }
        // todo: for phase II, when capital object codes are allowed on expense accounting lines, check that there are any if and
        // only if the Capital Assets tab contains information about the associated capital asset.
        // todo: for phase II, check that this bills for no more than one capital asset.
        return success;
    }

    /**
     * Validates all the InternalBillingItems in the given Document, adding global errors for invalid items. It just uses the
     * DataDictionary validation.
     * 
     * @param internalBillingDocument
     * @return whether any items were invalid
     */
    private boolean validateItems(InternalBillingDocument internalBillingDocument) {
        boolean retval = true;
        for (int i = 0; i < internalBillingDocument.getItems().size(); i++) {
            String propertyName = KFSConstants.DOCUMENT_PROPERTY_NAME + "." + KFSPropertyConstants.ITEM + "[" + i + "]";
            retval &= getDictionaryValidationService().isBusinessObjectValid(internalBillingDocument.getItem(i), propertyName);
        }
        return retval;
    }
}