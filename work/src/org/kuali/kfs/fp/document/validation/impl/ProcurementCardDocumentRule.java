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

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.KeyConstants;
import org.kuali.PropertyConstants;
import org.kuali.core.bo.AccountingLine;
import org.kuali.core.document.TransactionalDocument;
import org.kuali.core.rule.KualiParameterRule;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.util.ObjectUtils;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.core.workflow.service.KualiWorkflowDocument;
import org.kuali.module.financial.bo.ProcurementCardTargetAccountingLine;
import org.kuali.module.financial.bo.ProcurementCardTransactionDetail;
import org.kuali.module.financial.document.ProcurementCardDocument;
import org.kuali.module.financial.document.ProcurementCardDocumentAuthorizer.ProcurementCardRouteLevels;

import edu.iu.uis.eden.exception.WorkflowException;


/**
 * Business rule(s) applicable to Procurement Card document.
 * 
 * @author Kuali Financial Transactions Team (kualidev@oncourse.iu.edu)
 */
public class ProcurementCardDocumentRule extends TransactionalDocumentRuleBase {
    public static String MCC_OBJECT_CODE_GROUP_NM = "PCMccObjectCodeRestrictions";
    public static String MCC_OBJECT_SUB_TYPE_GROUP_NM = "PCMccObjectSubTypeRestrictions";
    public static String GLOBAL_FIELD_RESTRICTIONS_GROUP_NM = "PCGlobalFieldRestrictions";
    public static String OBJECT_TYPE_GLOBAL_RESTRICTION_PARM_NM = "OBJECT_TYPE_RESTRICTIONS";
    public static String OBJECT_SUB_TYPE_GLOBAL_RESTRICTION_PARM_NM = "OBJECT_SUB_TYPE_RESTRICTIONS";
    public static String OBJECT_LEVEL_GLOBAL_RESTRICTION_PARM_NM = "OBJECT_LEVEL_RESTRICTIONS";
    public static String OBJECT_CONSOLIDATION_GLOBAL_RESTRICTION_PARM_NM = "OBJECT_CONSOLIDATION_RESTRICTIONS";
    public static String OBJECT_CODE_GLOBAL_RESTRICTION_PARM_NM = "OBJECT_CODE_RESTRICTIONS";
    public static String ACCOUNT_NUMBER_GLOBAL_RESTRICTION_PARM_NM = "ACCOUNT_NUMBER_RESTRICTIONS";
    public static String SUB_FUND_GLOBAL_RESTRICTION_PARM_NM = "SUB_FUND_RESTRICTIONS";
    public static String FUNCTION_CODE_GLOBAL_RESTRICTION_PARM_NM = "FUNCTION_CODE_RESTRICTIONS";
    public static String MCC_PARM_PREFIX = "MCC_";

    /**
     * Only target lines can be changed, so we need to only validate them
     * @see org.kuali.module.financial.rules.TransactionalDocumentRuleBase#processCustomAddAccountingLineBusinessRules(org.kuali.core.document.TransactionalDocument,
     *      org.kuali.core.bo.AccountingLine)
     */
    protected boolean processCustomAddAccountingLineBusinessRules(TransactionalDocument transactionalDocument,
            AccountingLine accountingLine) {
        boolean allow = true;

        if (accountingLine instanceof ProcurementCardTargetAccountingLine) {
            LOG.debug("validating accounting line # " + accountingLine.getSequenceNumber());

            LOG.debug("beginning object code validation ");
            allow = validateObjectCode(transactionalDocument, accountingLine);

            LOG.debug("beginning account number validation ");
            allow = allow & validateAccountNumber(transactionalDocument, accountingLine);

            LOG.debug("end validating accounting line, has errors: " + allow);
        }

        return allow;
    }

    /**
     * Checks object codes restrictions, including restrictions in parameters table.
     */
    public boolean validateObjectCode(TransactionalDocument transactionalDocument, AccountingLine accountingLine) {
        ProcurementCardDocument pcDocument = (ProcurementCardDocument) transactionalDocument;
        ErrorMap errors = GlobalVariables.getErrorMap();

        String errorKey = PropertyConstants.FINANCIAL_OBJECT_LEVEL_CODE;
        boolean objectCodeAllowed = true;

        /* object code exist done in super, check we have a valid object */
        if (ObjectUtils.isNull(accountingLine.getObjectCode())) {
            return false;
        }

        /* make sure object code is active */
        if (!accountingLine.getObjectCode().isFinancialObjectActiveCode()) {
            errors.put(errorKey, KeyConstants.ERROR_INACTIVE, "object code");
            objectCodeAllowed = false;
        }

        /* check object type global restrictions */
        objectCodeAllowed = objectCodeAllowed
                && executeApplicationParameterRestriction(GLOBAL_FIELD_RESTRICTIONS_GROUP_NM,
                        OBJECT_TYPE_GLOBAL_RESTRICTION_PARM_NM, accountingLine.getObjectCode().getFinancialObjectTypeCode(),
                        errorKey, "Object type");

        /* check object sub type global restrictions */
        objectCodeAllowed = objectCodeAllowed
                && executeApplicationParameterRestriction(GLOBAL_FIELD_RESTRICTIONS_GROUP_NM,
                        OBJECT_SUB_TYPE_GLOBAL_RESTRICTION_PARM_NM, accountingLine.getObjectCode().getFinancialObjectSubTypeCode(),
                        errorKey, "Object sub type");

        /* check object level global restrictions */
        objectCodeAllowed = objectCodeAllowed
                && executeApplicationParameterRestriction(GLOBAL_FIELD_RESTRICTIONS_GROUP_NM,
                        OBJECT_LEVEL_GLOBAL_RESTRICTION_PARM_NM, accountingLine.getObjectCode().getFinancialObjectLevelCode(),
                        errorKey, "Object level");

        /* check object consolidation global restrictions */
        objectCodeAllowed = objectCodeAllowed
                && executeApplicationParameterRestriction(GLOBAL_FIELD_RESTRICTIONS_GROUP_NM,
                        OBJECT_CONSOLIDATION_GLOBAL_RESTRICTION_PARM_NM, accountingLine.getObjectCode().getFinancialObjectLevel()
                                .getFinancialConsolidationObjectCode(), errorKey, "Object consolidation code");

        /* get mcc restriction from transaction */
        String mccRestriction = "";
        ProcurementCardTargetAccountingLine line = (ProcurementCardTargetAccountingLine) accountingLine;
        List pcTransactions = pcDocument.getTransactionEntries();
        for (Iterator iter = pcTransactions.iterator(); iter.hasNext();) {
            ProcurementCardTransactionDetail transactionEntry = (ProcurementCardTransactionDetail) iter.next();
            if (transactionEntry.getFinancialDocumentTransactionLineNumber().equals(
                    line.getFinancialDocumentTransactionLineNumber())) {
                mccRestriction = transactionEntry.getProcurementCardVendor().getTransactionMerchantCategoryCode();
            }
        }

        if (StringUtils.isBlank(mccRestriction)) {
            return objectCodeAllowed;
        }

        /* check object code is in permitted list for mcc */
        objectCodeAllowed = objectCodeAllowed
                && executeApplicationParameterRestriction(MCC_OBJECT_CODE_GROUP_NM, MCC_PARM_PREFIX + mccRestriction,
                        accountingLine.getFinancialObjectCode(), errorKey, "Object code");

        /* check object sub type is in permitted list for mcc */
        objectCodeAllowed = objectCodeAllowed
                && executeApplicationParameterRestriction(MCC_OBJECT_SUB_TYPE_GROUP_NM, MCC_PARM_PREFIX + mccRestriction,
                        accountingLine.getObjectCode().getFinancialObjectSubTypeCode(), errorKey, "Object sub type code");

        return objectCodeAllowed;
    }

    /**
     * Checks account number restrictions, including restrictions in parameters table.
     */
    public boolean validateAccountNumber(TransactionalDocument transactionalDocument, AccountingLine accountingLine) {
        ProcurementCardDocument pcDocument = (ProcurementCardDocument) transactionalDocument;
        ErrorMap errors = GlobalVariables.getErrorMap();

        String errorKey = PropertyConstants.ACCOUNT_NUMBER;
        boolean accountNumberAllowed = true;

        /* account exist and object exist done in super, check we have a valid object */
        if (ObjectUtils.isNull(accountingLine.getAccount()) || ObjectUtils.isNull(accountingLine.getObjectCode())) {
            return false;
        }

        /* global account number restrictions */
        accountNumberAllowed = accountNumberAllowed
                && executeApplicationParameterRestriction(GLOBAL_FIELD_RESTRICTIONS_GROUP_NM,
                        ACCOUNT_NUMBER_GLOBAL_RESTRICTION_PARM_NM, accountingLine.getAccountNumber(), errorKey, "Account number");

        /* global sub fund restrictions */
        accountNumberAllowed = accountNumberAllowed
                && executeApplicationParameterRestriction(GLOBAL_FIELD_RESTRICTIONS_GROUP_NM, SUB_FUND_GLOBAL_RESTRICTION_PARM_NM,
                        accountingLine.getAccount().getSubFundGroupCode(), errorKey, "Sub fund code");

        /* global function code restrictions */
        accountNumberAllowed = accountNumberAllowed
                && executeApplicationParameterRestriction(GLOBAL_FIELD_RESTRICTIONS_GROUP_NM,
                        FUNCTION_CODE_GLOBAL_RESTRICTION_PARM_NM, accountingLine.getAccount().getFinancialHigherEdFunctionCd(),
                        errorKey, "Function code");

        return accountNumberAllowed;
    }

    /**
     * Checks the given field value against a restriction defined in the application parameters table. If the rule fails, an error
     * is added to the global error map.
     * 
     * @param parameterGroupName - Security Group name
     * @param parameterName - Parameter Name
     * @param restrictedFieldValue - Value to check
     * @param errorField - Key to associate error with in error map
     * @param errorParameter - String parameter for the restriction error message
     * @return boolean indicating whether or not the rule passed
     */
    private boolean executeApplicationParameterRestriction(String parameterGroupName, String parameterName,
            String restrictedFieldValue, String errorField, String errorParameter) {
        boolean rulePassed = true;

        if (SpringServiceLocator.getKualiConfigurationService().hasApplicationParameter(parameterGroupName, parameterName)) {
            KualiParameterRule rule = SpringServiceLocator.getKualiConfigurationService().getApplicationParameterRule(
                    parameterGroupName, parameterName);
            if (rule.failsRule(restrictedFieldValue)) {
                GlobalVariables.getErrorMap().put(
                        errorField,
                        rule.getErrorMessageKey(),
                        new String[] { errorParameter, restrictedFieldValue, parameterName, parameterGroupName,
                                rule.getParameterText() });
                rulePassed = false;
            }
        }
        else {
            LOG.warn("Did not find apc parameter record for group " + parameterGroupName + " with parm name " + parameterName);
        }

        return rulePassed;
    }

    /**
     * On procurement card, positive source amounts are credits, negative source amounts are debits
     * @see org.kuali.module.financial.rules.TransactionalDocumentRuleBase#isDebit(org.kuali.core.bo.AccountingLine)
     */
    public boolean isDebit(AccountingLine accountingLine) throws IllegalStateException {
        return !isDebitConsideringSection(accountingLine);
    }

    /**
     * Override for fiscal officer full approve, in which case any account can be used.
     * @see org.kuali.module.financial.rules.TransactionalDocumentRuleBase#accountIsAccessible(org.kuali.core.document.TransactionalDocument,
     *      org.kuali.core.bo.AccountingLine)
     */
    protected boolean accountIsAccessible(TransactionalDocument transactionalDocument, AccountingLine accountingLine) {
        KualiWorkflowDocument workflowDocument = transactionalDocument.getDocumentHeader().getWorkflowDocument();
        List activeNodes = null;
        try {
            activeNodes = Arrays.asList(workflowDocument.getNodeNames());
        }
        catch (WorkflowException e) {
            LOG.error("Error getting active nodes " + e.getMessage());
            throw new RuntimeException("Error getting active nodes " + e.getMessage());
        }

        if (workflowDocument.stateIsEnroute() && activeNodes.contains(ProcurementCardRouteLevels.ACCOUNT_REVIEW_FULL_EDIT)) {
            return true;
        }

        return super.accountIsAccessible(transactionalDocument, accountingLine);
    }
}