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

import static org.kuali.kfs.bo.AccountingLineOverride.CODE.EXPIRED_ACCOUNT_AND_NON_FRINGE_ACCOUNT_USED;
import static org.kuali.kfs.bo.AccountingLineOverride.CODE.NON_FRINGE_ACCOUNT_USED;
import static org.kuali.kfs.bo.AccountingLineOverride.CODE.EXPIRED_ACCOUNT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.document.Document;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.GeneralLedgerPendingEntrySequenceHelper;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kfs.KFSKeyConstants;
import org.kuali.kfs.KFSPropertyConstants;
import org.kuali.kfs.bo.AccountingLine;
import org.kuali.kfs.document.AccountingDocument;
import org.kuali.kfs.util.SpringServiceLocator;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.AccountingPeriod;
import org.kuali.module.labor.LaborConstants;
import org.kuali.module.labor.bo.ExpenseTransferAccountingLine;
import org.kuali.module.labor.bo.ExpenseTransferSourceAccountingLine;
import org.kuali.module.labor.bo.LaborLedgerPendingEntry;
import org.kuali.module.labor.bo.LaborObject;
import org.kuali.module.labor.document.BenefitExpenseTransferDocument;
import org.kuali.module.labor.document.LaborLedgerPostingDocument;
import org.kuali.module.labor.util.ObjectUtil;

/**
 * Business rule(s) applicable to Benefit Expense Transfer documents.
 */
public class BenefitExpenseTransferDocumentRule extends LaborExpenseTransferDocumentRules {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(BenefitExpenseTransferDocumentRule.class);

    private BusinessObjectService businessObjectService = SpringServiceLocator.getBusinessObjectService();

    /**
     * Constructs a BenefitExpenseTransferDocumentRule.java.
     */
    public BenefitExpenseTransferDocumentRule() {
        super();
    }

    /**
     * @see org.kuali.module.labor.rules.LaborExpenseTransferDocumentRules#processCustomSaveDocumentBusinessRules(org.kuali.core.document.Document)
     */
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        return true;
    }

    /**
     * @see org.kuali.kfs.rules.AccountingDocumentRuleBase#processCustomAddAccountingLineBusinessRules(org.kuali.kfs.document.AccountingDocument,
     *      org.kuali.kfs.bo.AccountingLine)
     */
    @Override
    protected boolean processCustomAddAccountingLineBusinessRules(AccountingDocument accountingDocument, AccountingLine accountingLine) {
        LOG.info("started processCustomAddAccountingLineBusinessRules");

        // benefit transfers cannot be made between two different fringe benefit labor object codes.
        if (!this.hasSameFringeBenefitObjectCodes(accountingDocument, accountingLine)) {
            reportError(KFSPropertyConstants.FINANCIAL_OBJECT_CODE, KFSKeyConstants.Labor.DISTINCT_OBJECT_CODE_ERROR);
            return false;
        }

        // only fringe benefit labor object codes are allowed on the befefit expense transfer document
        if (!this.isFringeBenefitObjectCode(accountingLine)) {
            reportError(KFSPropertyConstants.FINANCIAL_OBJECT_CODE, KFSKeyConstants.Labor.INVALID_FRINGE_OBJECT_CODE_ERROR, accountingLine.getAccountNumber());
            return false;
        }

        // validate the accounting year
        if (!this.isValidPayFiscalYear(accountingLine)) {
            reportError(KFSPropertyConstants.PAYROLL_END_DATE_FISCAL_YEAR, KFSKeyConstants.Labor.INVALID_PAY_YEAR);
            return false;
        }

        // validate the accounting period code
        if (!this.isValidPayFiscalPeriod(accountingLine)) {
            reportError(KFSPropertyConstants.PAYROLL_END_DATE_FISCAL_PERIOD_CODE, KFSKeyConstants.Labor.INVALID_PAY_PERIOD_CODE);
            return false;
        }

        // not allow the duplicate source accounting line in the document
        if (this.isDuplicateSourceAccountingLine(accountingDocument, accountingLine)) {
            reportError(KFSPropertyConstants.SOURCE_ACCOUNTING_LINES, KFSKeyConstants.Labor.ERROR_DUPLICATE_SOURCE_ACCOUNTING_LINE);
            return false;
        }
               
        // determine if an expired account can be used to accept amount transfer
        boolean canExpiredAccountBeUsed = canExpiredAccountBeUsed(accountingLine);
        if (!canExpiredAccountBeUsed) {
            reportError(KFSPropertyConstants.ACCOUNT, KFSKeyConstants.ERROR_ACCOUNT_EXPIRED);
            return false;
        }

        return true;
    }

    /**
     * @see org.kuali.kfs.rules.AccountingDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.Document)
     */
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        LOG.info("started processCustomRouteDocumentBusinessRules");

        BenefitExpenseTransferDocument benefitExpenseTransferDocument = (BenefitExpenseTransferDocument) document;
        List sourceLines = benefitExpenseTransferDocument.getSourceAccountingLines();
        List targetLines = benefitExpenseTransferDocument.getTargetAccountingLines();

        boolean isValid = super.processCustomRouteDocumentBusinessRules(document);

        // check to ensure totals of accounting lines in source and target sections match
        isValid = isValid && isAccountingLineTotalsMatch(sourceLines, targetLines);

        // check to ensure totals of accounting lines in source and target sections match by pay FY + pay period
        isValid = isValid && isAccountingLineTotalsMatchByPayFYAndPayPeriod(sourceLines, targetLines);

        // verify if the accounts in target accounting lines accept fringe benefits
        if (!this.isAccountsAcceptFringeBenefit(benefitExpenseTransferDocument)) {
            reportError(KFSPropertyConstants.TARGET_ACCOUNTING_LINES, KFSKeyConstants.Labor.ERROR_ACCOUNT_NOT_ACCEPT_FRINGES);
            return false;
        }

        // benefit transfers cannot be made between two different fringe benefit labor object codes.
        boolean hasSameFringeBenefitObjectCodes = this.hasSameFringeBenefitObjectCodes(benefitExpenseTransferDocument);
        if (!hasSameFringeBenefitObjectCodes) {
            reportError(KFSPropertyConstants.TARGET_ACCOUNTING_LINES, KFSKeyConstants.Labor.DISTINCT_OBJECT_CODE_ERROR);
            isValid = false;
        }

        // target accouting lines must have the same amounts as source accounting lines for each object code
        boolean isValidAmountTransferredByObjectCode = isValidAmountTransferredByObjectCode(benefitExpenseTransferDocument);
        if (!isValidAmountTransferredByObjectCode) {
            reportError(KFSPropertyConstants.TARGET_ACCOUNTING_LINES, KFSKeyConstants.Labor.ERROR_TRANSFER_AMOUNT_NOT_BALANCED_BY_OBJECT);
            isValid = false;
        }

        return isValid;
    }

    /**
     * @see org.kuali.module.labor.rules.LaborExpenseTransferDocumentRules#processGenerateLaborLedgerPendingEntries(org.kuali.module.labor.document.LaborLedgerPostingDocument,
     *      org.kuali.module.labor.bo.ExpenseTransferAccountingLine, org.kuali.core.util.GeneralLedgerPendingEntrySequenceHelper)
     */
    @Override
    public boolean processGenerateLaborLedgerPendingEntries(LaborLedgerPostingDocument accountingDocument, ExpenseTransferAccountingLine accountingLine, GeneralLedgerPendingEntrySequenceHelper sequenceHelper) {
        LOG.info("started processGenerateLaborLedgerPendingEntries");

        // setup default values, so they don't have to be set multiple times
        LaborLedgerPendingEntry defaultEntry = new LaborLedgerPendingEntry();
        populateDefaultLaborLedgerPendingEntry(accountingDocument, accountingLine, defaultEntry);

        // Generate original entry
        LaborLedgerPendingEntry originalEntry = (LaborLedgerPendingEntry) ObjectUtils.deepCopy(defaultEntry);
        boolean success = processOriginalLaborLedgerPendingEntry(accountingDocument, sequenceHelper, accountingLine, originalEntry);

        // Generate A21 entry
        LaborLedgerPendingEntry a21Entry = (LaborLedgerPendingEntry) ObjectUtils.deepCopy(defaultEntry);
        success &= processA21LaborLedgerPendingEntry(accountingDocument, sequenceHelper, accountingLine, a21Entry);

        // Generate A21 reversal entry
        LaborLedgerPendingEntry a21RevEntry = (LaborLedgerPendingEntry) ObjectUtils.deepCopy(defaultEntry);
        success &= processA21RevLaborLedgerPendingEntry(accountingDocument, sequenceHelper, accountingLine, a21RevEntry);

        return success;
    }

    /**
     * @see org.kuali.kfs.rules.AccountingDocumentRuleBase#isAmountValid(org.kuali.kfs.document.AccountingDocument,
     *      org.kuali.kfs.bo.AccountingLine)
     */
    @Override
    public boolean isAmountValid(AccountingDocument document, AccountingLine accountingLine) {
        LOG.debug("started isAmountValid");

        KualiDecimal amount = accountingLine.getAmount();

        // Check for zero amount
        if (amount.isZero()) {
            reportError(KFSPropertyConstants.AMOUNT, KFSKeyConstants.ERROR_ZERO_AMOUNT, "an accounting line");
            return false;
        }
        return true;
    }

    /**
     * Determine whether target accouting lines have the same fringe benefit object codes as source accounting lines
     * 
     * @param accountingDocument the given accounting document
     * @return true if target accouting lines have the same fringe benefit object codes as source accounting lines; otherwise, false
     */
    private boolean hasSameFringeBenefitObjectCodes(AccountingDocument accountingDocument) {
        LOG.debug("started hasSameFringeBenefitObjectCodes");
        BenefitExpenseTransferDocument benefitExpenseTransferDocument = (BenefitExpenseTransferDocument) accountingDocument;

        Set<String> objectCodesFromSourceLine = new HashSet<String>();
        for (Object sourceAccountingLine : benefitExpenseTransferDocument.getSourceAccountingLines()) {
            AccountingLine line = (AccountingLine) sourceAccountingLine;
            objectCodesFromSourceLine.add(line.getFinancialObjectCode());
        }

        Set<String> objectCodesFromTargetLine = new HashSet<String>();
        for (Object targetAccountingLine : benefitExpenseTransferDocument.getTargetAccountingLines()) {
            AccountingLine line = (AccountingLine) targetAccountingLine;
            objectCodesFromTargetLine.add(line.getFinancialObjectCode());
        }

        if (objectCodesFromSourceLine.size() != objectCodesFromTargetLine.size()) {
            return false;
        }
        return objectCodesFromSourceLine.containsAll(objectCodesFromTargetLine);
    }

    /**
     * Determine whether target accouting lines have the same amounts as source accounting lines for each object code
     * 
     * @param accountingDocument the given accounting document
     * @return true if target accouting lines have the same amounts as source accounting lines for each object code; otherwise,
     *         false
     */
    private boolean isValidAmountTransferredByObjectCode(AccountingDocument accountingDocument) {
        LOG.debug("started isValidAmountTransferredByObjectCode");
        BenefitExpenseTransferDocument benefitExpenseTransferDocument = (BenefitExpenseTransferDocument) accountingDocument;
        Map<String, KualiDecimal> amountsFromSourceLine = summerizeByObjectCode(benefitExpenseTransferDocument.getSourceAccountingLines());
        Map<String, KualiDecimal> amountsFromTargetLine = summerizeByObjectCode(benefitExpenseTransferDocument.getTargetAccountingLines());

        if (amountsFromSourceLine.size() != amountsFromTargetLine.size()) {
            return false;
        }

        for (String objectCode : amountsFromSourceLine.keySet()) {
            if (!amountsFromTargetLine.containsKey(objectCode)) {
                return false;
            }

            KualiDecimal sourceAmount = amountsFromSourceLine.get(objectCode);
            KualiDecimal targetAmount = amountsFromTargetLine.get(objectCode);
            if (!sourceAmount.equals(targetAmount)) {
                return false;
            }
        }
        return true;
    }

    /**
     * summerize the amounts of accounting lines by object codes
     * 
     * @param accountingLines the given accounting line list
     * @return the summerized amounts by object codes
     */
    private Map<String, KualiDecimal> summerizeByObjectCode(List accountingLines) {
        Map<String, KualiDecimal> amountByObjectCode = new HashMap<String, KualiDecimal>();

        for (Object accountingLine : accountingLines) {
            AccountingLine line = (AccountingLine) accountingLine;
            String objectCode = line.getFinancialObjectCode();
            KualiDecimal amount = line.getAmount();

            if (amountByObjectCode.containsKey(objectCode)) {
                amount = amount.add(amountByObjectCode.get(objectCode));
            }
            amountByObjectCode.put(objectCode, amount);
        }
        return amountByObjectCode;
    }

    /**
     * Determine whether the object code of the given accouting line is one of fringe benefit objects of source accounting lines
     * 
     * @param accountingDocument the given accounting document
     * @param accountingLine the given accounting line
     * @return true if the object code of the given accouting line is one of fringe benefit objects of source accounting lines;
     *         otherwise, false
     */
    private boolean hasSameFringeBenefitObjectCodes(AccountingDocument accountingDocument, AccountingLine accountingLine) {
        LOG.debug("started hasSameFringeBenefitObjectCodes");
        BenefitExpenseTransferDocument benefitExpenseTransferDocument = (BenefitExpenseTransferDocument) accountingDocument;

        List<String> objectCodesFromSourceLine = new ArrayList<String>();
        for (Object sourceAccountingLine : benefitExpenseTransferDocument.getSourceAccountingLines()) {
            AccountingLine line = (AccountingLine) sourceAccountingLine;
            objectCodesFromSourceLine.add(line.getFinancialObjectCode());
        }

        return objectCodesFromSourceLine.contains(accountingLine.getFinancialObjectCode());
    }

    /**
     * determine whether the given accounting line has already been in the given document
     * 
     * @param accountingDocument the given document
     * @param accountingLine the given accounting line
     * @return true if the given accounting line has already been in the given document; otherwise, false
     */
    private boolean isDuplicateSourceAccountingLine(AccountingDocument accountingDocument, AccountingLine accountingLine) {
        LOG.debug("started isDuplicateSourceAccountingLine");
        
        // only check source accounting lines
        if (!(accountingLine instanceof ExpenseTransferSourceAccountingLine)) {
            return false;
        }

        BenefitExpenseTransferDocument benefitExpenseTransferDocument = (BenefitExpenseTransferDocument) accountingDocument;
        List<ExpenseTransferSourceAccountingLine> sourceAccountingLines = benefitExpenseTransferDocument.getSourceAccountingLines();
        List<String> key = defaultKeyOfExpenseTransferAccountingLine();

        int counter = 0;
        for (AccountingLine sourceAccountingLine : sourceAccountingLines) {
            boolean isExisting = ObjectUtil.compareObject(accountingLine, sourceAccountingLine, key);
            counter = isExisting ? counter + 1 : counter;

            if (counter > 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine whether the object code of given accounting line is a fringe benefit labor object code
     * 
     * @param accountingLine the given accounting line
     * @return true if the object code of given accounting line is a fringe benefit labor object code; otherwise, false
     */
    private boolean isFringeBenefitObjectCode(AccountingLine accountingLine) {
        LOG.debug("started isFringeBenefitObjectCode");
        ExpenseTransferAccountingLine expenseTransferAccountingLine = (ExpenseTransferAccountingLine) accountingLine;

        LaborObject laborObject = expenseTransferAccountingLine.getLaborObject();
        if (laborObject == null) {
            return false;
        }

        String fringeOrSalaryCode = laborObject.getFinancialObjectFringeOrSalaryCode();
        return StringUtils.equals(LaborConstants.BenefitExpenseTransfer.LABOR_LEDGER_BENEFIT_CODE, fringeOrSalaryCode);
    }

    /**
     * determine whether the pay fiscal year of the given accounting line is valid
     * 
     * @param accountingLine the given accouting line
     * @return true if the pay fiscal year of the given accounting line is valid; otherwise, false
     */
    private boolean isValidPayFiscalYear(AccountingLine accountingLine) {
        LOG.debug("started isValidPayFiscalYear");
        ExpenseTransferAccountingLine expenseTransferAccountingLine = (ExpenseTransferAccountingLine) accountingLine;

        Integer payrollFiscalYear = expenseTransferAccountingLine.getPayrollEndDateFiscalYear();
        if (payrollFiscalYear == null) {
            return false;
        }

        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR, payrollFiscalYear.toString());

        return businessObjectService.countMatching(AccountingPeriod.class, fieldValues) > 0;
    }

    /**
     * determine whether the period code of the given accounting line is valid
     * 
     * @param accountingLine the given accouting line
     * @return true if the period code of the given accounting line is valid; otherwise, false
     */
    private boolean isValidPayFiscalPeriod(AccountingLine accountingLine) {
        LOG.debug("started isValidPayFiscalPeriod");
        ExpenseTransferAccountingLine expenseTransferAccountingLine = (ExpenseTransferAccountingLine) accountingLine;

        Integer payrollFiscalYear = expenseTransferAccountingLine.getPayrollEndDateFiscalYear();
        if (payrollFiscalYear == null) {
            return false;
        }

        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR, payrollFiscalYear.toString());
        fieldValues.put(KFSPropertyConstants.UNIVERSITY_FISCAL_PERIOD_CODE, expenseTransferAccountingLine.getPayrollEndDateFiscalPeriodCode());

        return businessObjectService.countMatching(AccountingPeriod.class, fieldValues) > 0;
    }

    /**
     * determine whether the accounts in the target accounting lines accept fringe benefits.
     * 
     * @param accountingDocument the given accounting document
     * @return true if the accounts in the target accounting lines accept fringe benefits; otherwise, false
     */
    private boolean isAccountsAcceptFringeBenefit(AccountingDocument accountingDocument) {
        LOG.debug("started isAccountsAcceptFringeBenefit");
        List<AccountingLine> accountingLines = accountingDocument.getTargetAccountingLines();

        for (AccountingLine accountingLine : accountingLines) {
            Account account = accountingLine.getAccount();
            if (account!=null && !account.isAccountsFringesBnftIndicator()) {
                String overrideCode = accountingLine.getOverrideCode();
                boolean canNonFringeAccountUsed = NON_FRINGE_ACCOUNT_USED.equals(overrideCode);
                canNonFringeAccountUsed = canNonFringeAccountUsed || EXPIRED_ACCOUNT_AND_NON_FRINGE_ACCOUNT_USED.equals(overrideCode);

                if (!canNonFringeAccountUsed) {
                    return false;
                }
            }
        }
        return true;
    }
}
