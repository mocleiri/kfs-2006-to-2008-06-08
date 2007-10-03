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
package org.kuali.module.labor.rules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.document.Document;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.GeneralLedgerPendingEntrySequenceHelper;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.KFSKeyConstants;
import org.kuali.kfs.KFSPropertyConstants;
import org.kuali.kfs.bo.AccountingLine;
import org.kuali.kfs.context.SpringContext;
import org.kuali.kfs.document.AccountingDocument;
import org.kuali.module.labor.LaborConstants;
import org.kuali.module.labor.bo.ExpenseTransferAccountingLine;
import org.kuali.module.labor.bo.ExpenseTransferSourceAccountingLine;
import org.kuali.module.labor.bo.ExpenseTransferTargetAccountingLine;
import org.kuali.module.labor.bo.LaborLedgerPendingEntry;
import org.kuali.module.labor.bo.LaborObject;
import org.kuali.module.labor.document.LaborExpenseTransferDocumentBase;
import org.kuali.module.labor.document.LaborLedgerPostingDocument;
import org.kuali.module.labor.document.SalaryExpenseTransferDocument;
import org.kuali.module.labor.rule.GenerateLaborLedgerBenefitClearingPendingEntriesRule;
import org.kuali.module.labor.service.LaborLedgerPendingEntryService;
import org.kuali.module.labor.util.LaborPendingEntryGenerator;


/**
 * Business rule(s) applicable to Salary Expense Transfer documents.
 */
public class SalaryExpenseTransferDocumentRule extends LaborExpenseTransferDocumentRules implements GenerateLaborLedgerBenefitClearingPendingEntriesRule<LaborLedgerPostingDocument> {

    /**
     * Constructs a SalaryExpenseTransferDocumentRule.java.
     */
    public SalaryExpenseTransferDocumentRule() {
        super();
    }

    /**
     * Checks if user is allowed to edit the object code and check the object code balances match when document was inititated, else
     * check they balance
     * 
     * @param accountingDocument
     * @return boolean
     * @see org.kuali.module.labor.rules.LaborExpenseTransferDocumentRules#isValidAmountTransferredByObjectCode(org.kuali.kfs.document.AccountingDocument)
     */
    @Override
    protected boolean isValidAmountTransferredByObjectCode(AccountingDocument accountingDocument) {
        LaborExpenseTransferDocumentBase expenseTransferDocument = (LaborExpenseTransferDocumentBase) accountingDocument;

        // check if user is allowed to edit the object code.
        String adminGroupName = SpringContext.getBean(KualiConfigurationService.class).getParameterValue(LaborConstants.LABOR_NAMESPACE, LaborConstants.Components.SALARY_EXPENSE_TRANSFER, LaborConstants.SalaryExpenseTransfer.SET_ADMIN_WORKGROUP_PARM_NM);
        boolean isAdmin = false;
        try {
            isAdmin = GlobalVariables.getUserSession().getUniversalUser().isMember(adminGroupName);
        }
        catch (Exception e) {
            throw new RuntimeException("Workgroup " + LaborConstants.SalaryExpenseTransfer.SET_ADMIN_WORKGROUP_PARM_NM + " not found", e);
        }

        if (isAdmin) {
            return true;
        }

        // if approving document, check the object code balances match when document was inititated, else check the balance
        boolean isValid = true;
        if (accountingDocument.getDocumentHeader().getWorkflowDocument().isApprovalRequested()) {
            if (!isObjectCodeBalancesUnchanged(accountingDocument)) {
                reportError(KFSPropertyConstants.TARGET_ACCOUNTING_LINES, KFSKeyConstants.Labor.ERROR_TRANSFER_AMOUNT_BY_OBJECT_APPROVAL_CHANGE);
                isValid = false;
            }
        }
        else {
            if (!expenseTransferDocument.getUnbalancedObjectCodes().isEmpty()) {
                reportError(KFSPropertyConstants.TARGET_ACCOUNTING_LINES, KFSKeyConstants.Labor.ERROR_TRANSFER_AMOUNT_NOT_BALANCED_BY_OBJECT);
                isValid = false;
            }
        }

        return isValid;
    }

    /**
     * Saves document
     * 
     * @param document
     * @return boolean
     * @see org.kuali.core.rules.SaveDocumentRule#processCustomSaveDocumentBusinessRules(Document)
     */
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        // Validate that an employee ID is enterred.
        SalaryExpenseTransferDocument salaryExpenseTransferDocument = (SalaryExpenseTransferDocument) document;
        String emplid = salaryExpenseTransferDocument.getEmplid();
        if ((emplid == null) || (emplid.trim().length() == 0)) {
            reportError(KFSConstants.EMPLOYEE_LOOKUP_ERRORS, KFSKeyConstants.Labor.MISSING_EMPLOYEE_ID, emplid);
            return false;
        }

        // ensure the employee ids in the source accounting lines are same
        AccountingDocument accountingDocument = (AccountingDocument) document;
        if (!hasAccountingLinesSameEmployee(accountingDocument)) {
            return false;
        }

        return true;
    }

    /**
     * Adds an accounting line in the document and validates only salary labor object codes are allowed on the salary expense
     * transfer document
     * 
     * @param accountingDocument
     * @param accountingLine
     * @return boolean
     * @see org.kuali.kfs.rules.AccountingDocumentRuleBase#processCustomAddAccountingLineBusinessRules(org.kuali.kfs.document.AccountingDocument,
     *      org.kuali.kfs.bo.AccountingLine)
     */
    @Override
    protected boolean processCustomAddAccountingLineBusinessRules(AccountingDocument accountingDocument, AccountingLine accountingLine) {
        boolean isValid = super.processCustomAddAccountingLineBusinessRules(accountingDocument, accountingLine);

        // only salary labor object codes are allowed on the salary expense transfer document
        if (!isSalaryObjectCode(accountingLine)) {
            reportError(KFSPropertyConstants.ACCOUNT, KFSKeyConstants.Labor.INVALID_SALARY_OBJECT_CODE_ERROR, accountingLine.getAccountNumber());
            return false;
        }

        return isValid;
    }

    /**
     * Process the routing of the document and validates that document must not have any pending labor ledger entries with same
     * emplId, periodCode, accountNumber, objectCode
     * 
     * @param document
     * @return boolean
     * @see org.kuali.module.labor.rules.LaborExpenseTransferDocumentRules#processCustomRouteDocumentBusinessRules(org.kuali.core.document.Document)
     */
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        boolean isValid = super.processCustomRouteDocumentBusinessRules(document);
        SalaryExpenseTransferDocument expenseTransferDocument = (SalaryExpenseTransferDocument) document;

        // must not have any pending labor ledger entries with same emplId, periodCode, accountNumber, objectCode
        if (isValid) {
            isValid = !hasPendingLedgerEntry(expenseTransferDocument);
        }

        return isValid;
    }

    /**
     * Determines whether the object code of given accounting line is a salary labor object code
     * 
     * @param accountingLine the given accounting line
     * @return true if the object code of given accounting line is a salary; otherwise, false
     */
    private boolean isSalaryObjectCode(AccountingLine accountingLine) {
        ExpenseTransferAccountingLine expenseTransferAccountingLine = (ExpenseTransferAccountingLine) accountingLine;

        LaborObject laborObject = expenseTransferAccountingLine.getLaborObject();
        if (laborObject == null) {
            return false;
        }

        String fringeOrSalaryCode = laborObject.getFinancialObjectFringeOrSalaryCode();
        return StringUtils.equals(LaborConstants.SalaryExpenseTransfer.LABOR_LEDGER_SALARY_CODE, fringeOrSalaryCode);
    }

    /**
     * Checks the current object code balance map of the document against the balances captured before the document was returned for
     * approval.
     * 
     * @param accountingDocument SalaryExpenseTransferDocument to check
     * @return true if the balances have not changed, false if they have
     */
    private boolean isObjectCodeBalancesUnchanged(AccountingDocument accountingDocument) {
        boolean isUnchanged = true;

        Map<String, KualiDecimal> initiatedObjectCodeBalances = ((SalaryExpenseTransferDocument) accountingDocument).getApprovalObjectCodeBalances();
        Map<String, KualiDecimal> currentObjectCodeBalances = ((SalaryExpenseTransferDocument) accountingDocument).getUnbalancedObjectCodes();

        Set<Entry<String, KualiDecimal>> initiatedObjectCodes = initiatedObjectCodeBalances.entrySet();
        Set<Entry<String, KualiDecimal>> currentObjectCodes = currentObjectCodeBalances.entrySet();

        if (initiatedObjectCodes == null) {
            if (currentObjectCodes != null) {
                isUnchanged = false;
            }
        }
        else {
            if (!initiatedObjectCodes.equals(currentObjectCodes)) {
                isUnchanged = false;
            }
        }

        return isUnchanged;
    }

    /**
     * determine whether the employees in the source accouting lines are same
     * 
     * @param accountingDocument the given accouting document
     * @return true if the employees in the source accouting lines are same; otherwise, false 
     */
    private boolean hasAccountingLinesSameEmployee(AccountingDocument accountingDocument) {
        LOG.debug("stared hasDocumentsSameEmployee");

        LaborExpenseTransferDocumentBase expenseTransferDocument = (LaborExpenseTransferDocumentBase) accountingDocument;
        List<ExpenseTransferSourceAccountingLine> sourceAccountingLines = expenseTransferDocument.getSourceAccountingLines();
        List<ExpenseTransferTargetAccountingLine> targetAccountingLines = expenseTransferDocument.getTargetAccountingLines();

        boolean sourceAccountingLinesValidationResult = true;
        boolean targetAccountingLinesValidationResult = true;

        String employeeID = expenseTransferDocument.getEmplid();
        String accountingLineEmplID = null;

        // Source Lines
        for (ExpenseTransferSourceAccountingLine sourceAccountingLine : sourceAccountingLines) {
            accountingLineEmplID = sourceAccountingLine.getEmplid();
            if (accountingLineEmplID == null) {
                sourceAccountingLinesValidationResult = false;
            }
            else if (!employeeID.equals(accountingLineEmplID)) {
                sourceAccountingLinesValidationResult = false;
            }
        }

        // Target lines
        for (ExpenseTransferTargetAccountingLine targetAccountingLine : targetAccountingLines) {
            accountingLineEmplID = targetAccountingLine.getEmplid();
            if (accountingLineEmplID == null) {
                targetAccountingLinesValidationResult = false;
            }
            else if (!employeeID.equals(accountingLineEmplID)) {
                targetAccountingLinesValidationResult = false;
            }
        }

        if (!sourceAccountingLinesValidationResult) {
            reportError(KFSPropertyConstants.SOURCE_ACCOUNTING_LINES, KFSKeyConstants.Labor.ERROR_EMPLOYEE_ID_NOT_SAME);
        }

        if (!targetAccountingLinesValidationResult) {
            reportError(KFSPropertyConstants.TARGET_ACCOUNTING_LINES, KFSKeyConstants.Labor.ERROR_EMPLOYEE_ID_NOT_SAME_IN_TARGET);
        }

        return (sourceAccountingLinesValidationResult && targetAccountingLinesValidationResult);
    }


    /**
     * determine if there is any pending entry for the source accounting lines of the given document
     * 
     * @param accountingDocument the given accounting document
     * @return true if there is a pending entry for the source accounting lines of the given document; otherwise, false
     */
    public boolean hasPendingLedgerEntry(AccountingDocument accountingDocument) {
        LOG.info("started hasPendingLedgerEntry(accountingDocument)");

        LaborExpenseTransferDocumentBase expenseTransferDocument = (LaborExpenseTransferDocumentBase) accountingDocument;
        List<ExpenseTransferAccountingLine> sourceAccountingLines = expenseTransferDocument.getSourceAccountingLines();

        Map<String, String> fieldValues = new HashMap<String, String>();
        for (ExpenseTransferAccountingLine sourceAccountingLine : sourceAccountingLines) {
            String payPeriodCode = sourceAccountingLine.getPayrollEndDateFiscalPeriodCode();
            String accountNumber = sourceAccountingLine.getAccountNumber();
            String objectCode = sourceAccountingLine.getFinancialObjectCode();
            String emplid = sourceAccountingLine.getEmplid();
            String documentNumber = accountingDocument.getDocumentNumber();

            fieldValues.put(KFSPropertyConstants.PAYROLL_END_DATE_FISCAL_PERIOD_CODE, payPeriodCode);
            fieldValues.put(KFSPropertyConstants.ACCOUNT_NUMBER, accountNumber);
            fieldValues.put(KFSPropertyConstants.FINANCIAL_OBJECT_CODE, objectCode);
            fieldValues.put(KFSPropertyConstants.EMPLID, emplid);
            fieldValues.put(KFSPropertyConstants.DOCUMENT_NUMBER, KFSConstants.NOT_LOGICAL_OPERATOR + documentNumber);

            if (SpringContext.getBean(LaborLedgerPendingEntryService.class).hasPendingLaborLedgerEntry(fieldValues)) {
                reportError(KFSConstants.EMPLOYEE_LOOKUP_ERRORS, KFSKeyConstants.Labor.PENDING_SALARY_TRANSFER_ERROR, emplid, payPeriodCode, accountNumber, objectCode);
                return true;
            }
        }
        
        return false;
    }

    /**
     * @param LaborLedgerPostingDocument the given labor ledger accounting document
     * @return true after creating a list of Expense Pending entries and Benefit pending Entries
     * @see org.kuali.module.labor.rules.LaborExpenseTransferDocumentRules#processGenerateLaborLedgerPendingEntries(org.kuali.module.labor.document.LaborLedgerPostingDocument,
     *      org.kuali.module.labor.bo.ExpenseTransferAccountingLine, org.kuali.core.util.GeneralLedgerPendingEntrySequenceHelper)
     */
    @Override
    public boolean processGenerateLaborLedgerPendingEntries(LaborLedgerPostingDocument document, AccountingLine accountingLine, GeneralLedgerPendingEntrySequenceHelper sequenceHelper) {
        LOG.info("started processGenerateLaborLedgerPendingEntries()");

        ExpenseTransferAccountingLine expenseTransferAccountingLine = (ExpenseTransferAccountingLine) accountingLine;

        List<LaborLedgerPendingEntry> expensePendingEntries = LaborPendingEntryGenerator.generateExpensePendingEntries(document, expenseTransferAccountingLine, sequenceHelper);
        document.getLaborLedgerPendingEntries().addAll(expensePendingEntries);

        List<LaborLedgerPendingEntry> benefitPendingEntries = LaborPendingEntryGenerator.generateBenefitPendingEntries(document, expenseTransferAccountingLine, sequenceHelper);
        document.getLaborLedgerPendingEntries().addAll(benefitPendingEntries);

        return true;
    }

    /**
     * @param LaborLedgerPostingDocument the given labor ledger accounting document
     * @return true after generate Benefit Clearing Pending Entries for the document
     * @see org.kuali.module.labor.rule.GenerateLaborLedgerBenefitClearingPendingEntriesRule#processGenerateLaborLedgerBenefitClearingPendingEntries(org.kuali.kfs.document.AccountingDocument,
     *      org.kuali.core.util.GeneralLedgerPendingEntrySequenceHelper)
     */
    public boolean processGenerateLaborLedgerBenefitClearingPendingEntries(LaborLedgerPostingDocument document, GeneralLedgerPendingEntrySequenceHelper sequenceHelper) {
        LOG.info("started processGenerateLaborLedgerBenefitClearingPendingEntries()");

        String chartOfAccountsCode = SpringContext.getBean(KualiConfigurationService.class).getParameterValue(LaborConstants.LABOR_NAMESPACE, LaborConstants.Components.SALARY_EXPENSE_TRANSFER, LaborConstants.SalaryExpenseTransfer.BENEFIT_CLEARING_CHART_PARM_NM);
        String accountNumber = SpringContext.getBean(KualiConfigurationService.class).getParameterValue(LaborConstants.LABOR_NAMESPACE, LaborConstants.Components.SALARY_EXPENSE_TRANSFER, LaborConstants.SalaryExpenseTransfer.BENEFIT_CLEARING_ACCOUNT_PARM_NM);

        List<LaborLedgerPendingEntry> benefitClearingPendingEntries = LaborPendingEntryGenerator.generateBenefitClearingPendingEntries(document, sequenceHelper, accountNumber, chartOfAccountsCode);
        document.getLaborLedgerPendingEntries().addAll(benefitClearingPendingEntries);

        return true;
    }
}
