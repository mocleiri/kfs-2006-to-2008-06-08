/*
 * Copyright (c) 2004, 2005 The National Association of College and University Business Officers,
 * Cornell University, Trustees of Indiana University, Michigan State University Board of Trustees,
 * Trustees of San Joaquin Delta College, University of Hawai'i, The Arizona Board of Regents on
 * behalf of the University of Arizona, and the r*smart group.
 * 
 * Licensed under the Educational Community License Version 1.0 (the "License"); By obtaining,
 * using and/or copying this Original Work, you agree that you have read, understand, and will
 * comply with the terms and conditions of the Educational Community License.
 * 
 * You may obtain a copy of the License at:
 * 
 * http://kualiproject.org/license.html
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.kuali.module.financial.rules;

import static org.kuali.module.financial.rules.InternalBillingDocumentRuleConstants.CAPITAL_OBJECT_SUB_TYPE_CODES;
import static org.kuali.module.financial.rules.InternalBillingDocumentRuleConstants.INTERNAL_BILLING_DOCUMENT_SECURITY_GROUPING;
import static org.kuali.module.financial.rules.InternalBillingDocumentRuleConstants.RESTRICTED_FUND_GROUP_CODES;
import static org.kuali.module.financial.rules.InternalBillingDocumentRuleConstants.RESTRICTED_OBJECT_LEVEL_CODES;
import static org.kuali.module.financial.rules.InternalBillingDocumentRuleConstants.RESTRICTED_OBJECT_SUB_TYPE_CODES;
import static org.kuali.module.financial.rules.InternalBillingDocumentRuleConstants.RESTRICTED_OBJECT_TYPE_CODES;
import static org.kuali.module.financial.rules.InternalBillingDocumentRuleConstants.RESTRICTED_SUB_FUND_GROUP_CODES;

import org.kuali.Constants;
import org.kuali.KeyConstants;
import org.kuali.PropertyConstants;
import org.kuali.core.bo.AccountingLine;
import org.kuali.core.bo.SourceAccountingLine;
import org.kuali.core.document.Document;
import org.kuali.core.document.TransactionalDocument;
import org.kuali.core.rule.KualiParameterRule;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.ExceptionUtils;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.SpringServiceLocator;
import static org.kuali.core.util.SpringServiceLocator.getDictionaryValidationService;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.ObjectCode;
import org.kuali.module.chart.bo.SubFundGroup;
import org.kuali.module.financial.document.InternalBillingDocument;

/**
 * Business rule(s) applicable to InternalBilling document.
 * 
 * @author Kuali Financial Transactions Team ()
 */
public class InternalBillingDocumentRule extends TransactionalDocumentRuleBase {

    /**
     * @see IsDebitUtils#isDebitConsideringSection(TransactionalDocumentRuleBase, TransactionalDocument, AccountingLine)
     * 
     * @see org.kuali.core.rule.AccountingLineRule#isDebit(org.kuali.core.document.TransactionalDocument,
     *      org.kuali.core.bo.AccountingLine)
     */
    public boolean isDebit(TransactionalDocument transactionalDocument, AccountingLine accountingLine) {
        return IsDebitUtils.isDebitConsideringSection(this, transactionalDocument, accountingLine);
    }

    /**
     * Overrides to only disallow zero, allowing negative amounts.
     * 
     * @see org.kuali.module.financial.rules.TransactionalDocumentRuleBase#isAmountValid(TransactionalDocument, AccountingLine)
     */
    @Override
    public boolean isAmountValid(TransactionalDocument document, AccountingLine accountingLine) {
        if (accountingLine.getAmount().equals(Constants.ZERO)) {
            GlobalVariables.getErrorMap().putError(Constants.AMOUNT_PROPERTY_NAME, KeyConstants.ERROR_ZERO_AMOUNT, "an accounting line");
            LOG.info("failing isAmountValid - zero check");
            return false;
        }
        return true;
    }

    /**
     * @see org.kuali.module.financial.rules.TransactionalDocumentRuleBase#processCustomAddAccountingLineBusinessRules(TransactionalDocument,
     *      AccountingLine)
     */
    @Override
    public boolean processCustomAddAccountingLineBusinessRules(TransactionalDocument document, AccountingLine accountingLine) {
        return processCommonCustomAccountingLineRules(accountingLine);
    }

    /**
     * @see org.kuali.module.financial.rules.TransactionalDocumentRuleBase#processCustomReviewAccountingLineBusinessRules(TransactionalDocument,
     *      AccountingLine)
     */
    @Override
    public boolean processCustomReviewAccountingLineBusinessRules(TransactionalDocument document, AccountingLine accountingLine) {
        return processCommonCustomAccountingLineRules(accountingLine);
    }

    /**
     * @see org.kuali.module.financial.rules.TransactionalDocumentRuleBase#processCustomUpdateAccountingLineBusinessRules(TransactionalDocument,
     *      AccountingLine, AccountingLine)
     */
    @Override
    public boolean processCustomUpdateAccountingLineBusinessRules(TransactionalDocument document, AccountingLine originalAccountingLine, AccountingLine updatedAccountingLine) {
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
            reportError(PropertyConstants.OBJECT_CODE, KeyConstants.ERROR_DOCUMENT_INCORRECT_OBJ_CODE_WITH_SUB_FUND_GROUP, accountingLine.getFinancialObjectCode(), objectSubTypeCode, requiredSubFundGroupCode, actualSubFundGroupCode);
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
            GlobalVariables.getErrorMap().putError(PropertyConstants.FINANCIAL_OBJECT_CODE, KeyConstants.ERROR_DOCUMENT_IB_CAPITAL_OBJECT_IN_INCOME_SECTION);
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
        return getParameterRule(INTERNAL_BILLING_DOCUMENT_SECURITY_GROUPING, CAPITAL_OBJECT_SUB_TYPE_CODES).succeedsRule(accountingLine.getObjectCode().getFinancialObjectSubTypeCode());
    }

    /**
     * @see TransactionalDocumentRuleBase#processCustomRouteDocumentBusinessRules(Document)
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
    private static boolean validateItems(InternalBillingDocument internalBillingDocument) {
        boolean retval = true;
        for (int i = 0; i < internalBillingDocument.getItems().size(); i++) {
            String propertyName = Constants.DOCUMENT_PROPERTY_NAME + "." + PropertyConstants.ITEM + "[" + i + "]";
            retval &= getDictionaryValidationService().isBusinessObjectValid(internalBillingDocument.getItem(i), propertyName);
        }
        return retval;
    }

    /**
     * @see org.kuali.core.rule.AccountingLineRule#isObjectTypeAllowed(org.kuali.core.bo.AccountingLine)
     */
    @Override
    public boolean isObjectTypeAllowed(AccountingLine accountingLine) {
        KualiParameterRule combinedRule = getObjectTypeRule();
        AttributeReference direct = createObjectCodeAttributeReference(accountingLine);
        AttributeReference indirect = createObjectTypeAttributeReference(accountingLine);
        boolean allowed = indirectRuleSucceeds(combinedRule, direct, indirect);
        if (allowed) {
            allowed &= super.isObjectTypeAllowed(accountingLine);
        }
        return allowed;
    }

    /**
     * @return the object type APC rule for IB
     */
    protected KualiParameterRule getObjectTypeRule() {
        return KualiParameterRule.and(getGlobalObjectTypeRule(), getParameterRule(INTERNAL_BILLING_DOCUMENT_SECURITY_GROUPING, RESTRICTED_OBJECT_TYPE_CODES));
    }

    /**
     * Overrides the parent to make sure that the chosen object code's object sub-type code isn't restricted according to the APC.
     * 
     * @see org.kuali.core.rule.AddAccountingLineRule#isObjectSubTypeAllowed(AccountingLine)
     */
    @Override
    public boolean isObjectSubTypeAllowed(AccountingLine accountingLine) {
        boolean allowed = super.isObjectSubTypeAllowed(accountingLine);
        if (allowed) {
            KualiParameterRule parameterRule = getParameterRule(INTERNAL_BILLING_DOCUMENT_SECURITY_GROUPING, RESTRICTED_OBJECT_SUB_TYPE_CODES);
            AttributeReference direct = createObjectCodeAttributeReference(accountingLine);
            AttributeReference indirect = new AttributeReference(ObjectCode.class, PropertyConstants.FINANCIAL_OBJECT_SUB_TYPE_CODE, accountingLine.getObjectCode().getFinancialObjectSubTypeCode());
            allowed &= indirectRuleSucceeds(parameterRule, direct, indirect);
        }
        return allowed;
    }

    /**
     * Overrides the parent's implementation to check to make sure that the provided object code's level isn't a contract and grants
     * level.
     * 
     * @see TransactionalDocumentRuleBase#isObjectLevelAllowed(AccountingLine)
     */
    @Override
    public boolean isObjectLevelAllowed(AccountingLine accountingLine) {
        boolean allowed = super.isObjectLevelAllowed(accountingLine);
        if (allowed) {
            KualiParameterRule parameterRule = getParameterRule(INTERNAL_BILLING_DOCUMENT_SECURITY_GROUPING, RESTRICTED_OBJECT_LEVEL_CODES);
            AttributeReference direct = createObjectCodeAttributeReference(accountingLine);
            AttributeReference indirect = new AttributeReference(ObjectCode.class, PropertyConstants.FINANCIAL_OBJECT_LEVEL_CODE, accountingLine.getObjectCode().getFinancialObjectLevelCode());
            allowed &= indirectRuleSucceeds(parameterRule, direct, indirect);
        }
        return allowed;
    }

    /**
     * This implementation overrides the parent to check and make sure that the fund group is not a Loan fund group.
     * 
     * @see TransactionalDocumentRuleBase#isFundGroupAllowed(AccountingLine)
     */
    @Override
    public boolean isFundGroupAllowed(AccountingLine accountingLine) {
        boolean allowed = super.isFundGroupAllowed(accountingLine);
        if (allowed) {
            KualiParameterRule parameterRule = getParameterRule(INTERNAL_BILLING_DOCUMENT_SECURITY_GROUPING, RESTRICTED_FUND_GROUP_CODES);
            AttributeReference direct = createAccountNumberAttributeReference(accountingLine);
            AttributeReference indirect = new AttributeReference(SubFundGroup.class, PropertyConstants.FUND_GROUP_CODE, accountingLine.getAccount().getSubFundGroup().getFundGroupCode());
            allowed &= indirectRuleSucceeds(parameterRule, direct, indirect);
            // This calls for double indirection, but I'm not sure if such an error message would be more user friendly.
        }
        return allowed;
    }

    /**
     * Creates an AttributeReference for the account number of the given AccountingLine.
     * 
     * @param accountingLine
     * @return an AttributeReference for the account number of the given AccountingLine.
     */
    private static AttributeReference createAccountNumberAttributeReference(AccountingLine accountingLine) {
        return new AttributeReference(SourceAccountingLine.class, PropertyConstants.ACCOUNT_NUMBER, accountingLine.getAccountNumber());
    }

    /**
     * Overrides the parent's implementation to check and make sure that the sub fund group is not the Retire Indebt or the
     * Investment Plant sub fund group.
     * 
     * @see TransactionalDocumentRuleBase#isSubFundGroupAllowed(AccountingLine)
     */
    @Override
    public boolean isSubFundGroupAllowed(AccountingLine accountingLine) {
        boolean allowed = super.isSubFundGroupAllowed(accountingLine);
        if (allowed) {
            KualiParameterRule parameterRule = getParameterRule(INTERNAL_BILLING_DOCUMENT_SECURITY_GROUPING, RESTRICTED_SUB_FUND_GROUP_CODES);
            AttributeReference direct = createAccountNumberAttributeReference(accountingLine);
            AttributeReference indirect = new AttributeReference(Account.class, PropertyConstants.SUB_FUND_GROUP_CODE, accountingLine.getAccount().getSubFundGroupCode());
            allowed &= indirectRuleSucceeds(parameterRule, direct, indirect);
        }
        return allowed;
    }
}