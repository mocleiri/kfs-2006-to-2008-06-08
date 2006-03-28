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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.Constants;
import org.kuali.KeyConstants;
import org.kuali.core.bo.AccountingLine;
import org.kuali.core.bo.SourceAccountingLine;
import org.kuali.core.bo.TargetAccountingLine;
import org.kuali.core.document.Document;
import org.kuali.core.document.TransactionalDocument;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.module.financial.document.TransferOfFundsDocument;
import org.kuali.module.gl.bo.GeneralLedgerPendingEntry;
import org.kuali.module.gl.util.SufficientFundsItemHelper.SufficientFundsItem;

/**
 * Business rule(s) applicable to Transfer of Funds documents.
 * 
 * @author Kuali Financial Transactions Team (kualidev@oncourse.iu.edu)
 */
public class TransferOfFundsDocumentRule extends TransactionalDocumentRuleBase implements TransferOfFundsDocumentRuleConstants {

    /**
     * Set attributes of an offset pending entry according to rules specific to TransferOfFundsDocument.
     * 
     * @see org.kuali.module.financial.rules.TransactionalDocumentRuleBase#customizeOffsetGeneralLedgerPendingEntry(org.kuali.core.document.TransactionalDocument, org.kuali.core.bo.AccountingLine, org.kuali.module.gl.bo.GeneralLedgerPendingEntry, org.kuali.module.gl.bo.GeneralLedgerPendingEntry)
     */
    protected boolean customizeOffsetGeneralLedgerPendingEntry(TransactionalDocument transactionalDocument,
            AccountingLine accountingLine, GeneralLedgerPendingEntry explicitEntry, GeneralLedgerPendingEntry offsetEntry) {
        offsetEntry.setFinancialBalanceTypeCode(BALANCE_TYPE_CODE.ACTUAL);
        return true;
    }

    /**
     * Set attributes of an explicit pending entry according to rules specific to TransferOfFundsDocument.
     * 
     * @see org.kuali.module.financial.rules.TransactionalDocumentRuleBase#customizeExplicitGeneralLedgerPendingEntry(org.kuali.core.document.TransactionalDocument,
     *      org.kuali.core.bo.AccountingLine, org.kuali.module.gl.bo.GeneralLedgerPendingEntry)
     */
    protected void customizeExplicitGeneralLedgerPendingEntry(TransactionalDocument transactionalDocument,
            AccountingLine accountingLine, GeneralLedgerPendingEntry explicitEntry) {
        explicitEntry.setFinancialBalanceTypeCode(BALANCE_TYPE_CODE.ACTUAL);
        if (isExpense(accountingLine)) {
            explicitEntry.setFinancialObjectTypeCode(OBJECT_TYPE_CODE.TRANSFER_EXPENSE);
        }
        else {
            if (isIncome(accountingLine)) {
                explicitEntry.setFinancialObjectTypeCode(OBJECT_TYPE_CODE.TRANSFER_INCOME);
            }
            else {
                explicitEntry.setFinancialObjectTypeCode(getObjectCodeTypeCodeWithoutSideEffects(accountingLine));
            }
        }
    }

    /**
     * The TOF spec says that all positive "From" section accounting line amounts are debits, and vice versa for the "To" section,
     * for GL pending entries.
     * 
     * @see org.kuali.module.financial.rules.TransactionalDocumentRuleBase#isDebit(org.kuali.core.bo.AccountingLine)
     */
    public boolean isDebit(AccountingLine accountingLine) throws IllegalStateException {
        if (isExpenseOrAsset(accountingLine) || isIncomeOrLiability(accountingLine)) {
            if (isSourceAccountingLine(accountingLine)) {
                return accountingLine.getAmount().isGreaterThan(Constants.ZERO);
            }
            else {
                return accountingLine.getAmount().isLessEqual(Constants.ZERO);
            }
        }
        throw new IllegalStateException(objectTypeCodeIllegalStateExceptionMessage);
    }

    /**
     * The TOF spec says that all GL pending entry amounts are positive. I.e., it says that the pending entry uses the absolute
     * value of non-positive accounting line amounts.
     * 
     * @see org.kuali.module.financial.rules.TransactionalDocumentRuleBase#getGeneralLedgerPendingEntryAmountForAccountingLine(org.kuali.core.bo.AccountingLine)
     */
    protected KualiDecimal getGeneralLedgerPendingEntryAmountForAccountingLine(AccountingLine accountingLine) {
        return accountingLine.getAmount().abs();
    }

    /**
     * This document specific routing business rule check calls the balance check that is done to make sure that totals between the
     * "From" and "To" section are balanced within the groupings of "Mandatory Transfers" and "Non-Mandatory Transfers".
     * 
     * @see org.kuali.core.rule.DocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.Document)
     */
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        boolean isValid = super.processCustomRouteDocumentBusinessRules(document);

        if(isValid) {
            // check the balance across mandatory and non-mandatory transfers
            TransferOfFundsDocument tofDoc = (TransferOfFundsDocument) document;
            isValid = isMandatoryTransferTotalAndNonMandatoryTransferTotalBalanceValid(tofDoc);
        }
        
        return isValid;
    }

    /**
     * This method checks the sum of all of the "From" accounting lines with mandatory transfer object codes against the sum of all
     * of the "To" accounting lines with mandatory transfer object codes. In addition, it does the same, but for accounting lines
     * with non-mandatory transfer object code. This is to enforce the rule that the document must balance within the object code
     * object sub-type codes of mandatory transfers and non-mandatory transfers.
     * 
     * @param tofDoc
     * @return True if they balance; false otherwise.
     */
    private boolean isMandatoryTransferTotalAndNonMandatoryTransferTotalBalanceValid(TransferOfFundsDocument tofDoc) {
        List lines = new ArrayList();

        lines.addAll(tofDoc.getSourceAccountingLines());
        lines.addAll(tofDoc.getTargetAccountingLines());

        // sum the from lines.
        KualiDecimal mandatoryTransferFromAmount = new KualiDecimal(0);
        KualiDecimal nonMandatoryTransferFromAmount = new KualiDecimal(0);
        KualiDecimal mandatoryTransferToAmount = new KualiDecimal(0);
        KualiDecimal nonMandatoryTransferToAmount = new KualiDecimal(0);

        for (Iterator i = lines.iterator(); i.hasNext();) {
            AccountingLine line = (AccountingLine) i.next();
            line.refreshReferenceObject("objectCode"); // refresh b/c of proxying in OJB
            String objectSubTypeCode = line.getObjectCode().getFinancialObjectSubTypeCode();

            if (isNonMandatoryTransfersSubType(objectSubTypeCode)) {
                if (line.isSourceAccountingLine()) {
                    nonMandatoryTransferFromAmount = nonMandatoryTransferFromAmount.add(line.getAmount());
                }
                else {
                    nonMandatoryTransferToAmount = nonMandatoryTransferToAmount.add(line.getAmount());
                }
            }
            else if (isMandatoryTransfersSubType(objectSubTypeCode)) {
                if (line.isSourceAccountingLine()) {
                    mandatoryTransferFromAmount = mandatoryTransferFromAmount.add(line.getAmount());
                }
                else {
                    mandatoryTransferToAmount = mandatoryTransferToAmount.add(line.getAmount());
                }
            }
        }

        // check that the amounts balance across mandatory transfers and
        // non-mandatory transfers
        boolean isValid = true;

        if (mandatoryTransferFromAmount.compareTo(mandatoryTransferToAmount) != 0) {
            isValid = false;
            GlobalVariables.getErrorMap().put("document.sourceAccountingLines",
                    KeyConstants.ERROR_DOCUMENT_TOF_MANDATORY_TRANSFERS_DO_NOT_BALANCE);
        }

        if (nonMandatoryTransferFromAmount.compareTo(nonMandatoryTransferToAmount) != 0) {
            isValid = false;
            GlobalVariables.getErrorMap().put("document.sourceAccountingLines",
                    KeyConstants.ERROR_DOCUMENT_TOF_NON_MANDATORY_TRANSFERS_DO_NOT_BALANCE);
        }

        return isValid;
    }

    /**
     * Overrides the parent to make sure that the chosen object code's object sub-type code is either Mandatory Transfer or
     * Non-Mandatory Transfer. This is called by the parent's processAddAccountingLine() method.
     * 
     * @param accountingLine
     * @return True if the object code's object sub-type code is a mandatory or non-mandatory transfer; false otherwise.
     * 
     * @see org.kuali.core.rule.AccountingLineRule#isObjectSubTypeAllowed(org.kuali.core.bo.AccountingLine)
     */
    public boolean isObjectSubTypeAllowed(AccountingLine accountingLine) {
        accountingLine.refreshReferenceObject("objectCode");
        String objectSubTypeCode = accountingLine.getObjectCode().getFinancialObjectSubTypeCode();
        
        // make sure a object sub type code exists for this object code
        if(StringUtils.isBlank(objectSubTypeCode)) {
            GlobalVariables.getErrorMap().put(
                    "financialObjectCode",
                    KeyConstants.ERROR_DOCUMENT_TOF_OBJECT_SUB_TYPE_IS_NULL,
                    accountingLine.getFinancialObjectCode());
            return false;
        }

        if (!isMandatoryTransfersSubType(objectSubTypeCode) && !isNonMandatoryTransfersSubType(objectSubTypeCode)) {
            GlobalVariables.getErrorMap().put(
                    "financialObjectCode",
                    KeyConstants.ERROR_DOCUMENT_TOF_OBJECT_SUB_TYPE_NOT_MANDATORY_OR_NON_MANDATORY_TRANSFER,
                    new String[] { accountingLine.getObjectCode().getFinancialObjectSubType().getFinancialObjectSubTypeName(),
                            accountingLine.getFinancialObjectCode() });
            return false;
        }

        return true;
    }

    /**
     * 
     * @see org.kuali.module.financial.rules.TransactionalDocumentRuleBase#processSourceAccountingLineSufficientFundsCheckingPreparation(TransactionalDocument, org.kuali.core.bo.SourceAccountingLine)
     */
    protected SufficientFundsItem processSourceAccountingLineSufficientFundsCheckingPreparation(
            TransactionalDocument transactionalDocument, SourceAccountingLine sourceAccountingLine) {
        return processAccountingLineSufficientFundsCheckingPreparation(sourceAccountingLine);
    }

    /**
     * 
     * @see org.kuali.module.financial.rules.TransactionalDocumentRuleBase#processTargetAccountingLineSufficientFundsCheckingPreparation(TransactionalDocument, org.kuali.core.bo.TargetAccountingLine)
     */
    protected SufficientFundsItem processTargetAccountingLineSufficientFundsCheckingPreparation(
            TransactionalDocument transactionalDocument, TargetAccountingLine targetAccountingLine) {
        return processAccountingLineSufficientFundsCheckingPreparation(targetAccountingLine);
    }

    /**
     * fi_dtf:lp_proc_frm_ln,lp_proc_to_ln conslidated
     * 
     * @param accountingLine
     * @return SufficientFundsItem
     */
    private final SufficientFundsItem processAccountingLineSufficientFundsCheckingPreparation(AccountingLine accountingLine) {
        SufficientFundsItem item = null;
        String objectType = accountingLine.getObjectTypeCode();
        String offsetDebitCreditCode = null;
        KualiDecimal lineAmount = accountingLine.getAmount();
        if (lineAmount == null) {
            throw new IllegalArgumentException("Invalid (null) line amount");
        }
        // expense object types
        // fi_dtf:lp_proc_frm_ln.36-2...48-3,50-3...67-3;; lp_proc_to_ln.36-2...48-3,50-3...67-3
        if (isExpense(accountingLine) || isIncome(accountingLine) || isAsset(accountingLine) || isLiability(accountingLine)) {
            if (accountingLine.getAmount().isPositive()) {
                if (accountingLine.isSourceAccountingLine()) {
                    offsetDebitCreditCode = Constants.GL_CREDIT_CODE;
                }
                else {
                    offsetDebitCreditCode = Constants.GL_DEBIT_CODE;
                }
            }
            else {
                lineAmount = lineAmount.multiply(new KualiDecimal(Constants.NEGATIVE_ONE));
                if (accountingLine.isSourceAccountingLine()) {
                    offsetDebitCreditCode = Constants.GL_DEBIT_CODE;
                }
                else {
                    offsetDebitCreditCode = Constants.GL_CREDIT_CODE;
                }
            }

            // fi_dtf:lp_proc_frm_ln.46-03...48-3,lp_proc_to_ln.46-03...48-3
            if (isExpense(accountingLine)) {
                objectType = SpringServiceLocator.getKualiConfigurationService().getApplicationParameterValue(
                        KUALI_TRANSACTION_PROCESSING_TRANSFER_OF_FUNDS_SECURITY_GROUPING,
                        TRANSFER_OF_FUNDS_EXPENSE_OBJECT_TYPE_CODE);
            }
            // fi_dtf:lp_proc_frm_ln.60-4...62-4,lp_proc_to_ln.60-4...62-4
            else if (isIncome(accountingLine)) {
                objectType = SpringServiceLocator.getKualiConfigurationService()
                        .getApplicationParameterValue(KUALI_TRANSACTION_PROCESSING_TRANSFER_OF_FUNDS_SECURITY_GROUPING,
                                TRANSFER_OF_FUNDS_INCOME_OBJECT_TYPE_CODE);
            }
            if (StringUtils.isBlank(objectType)) {
                throw new IllegalArgumentException("Invalid (null) object type");
            }
            String chartOfAccountsCode = accountingLine.getChartOfAccountsCode();
            String acocuntNumber = accountingLine.getAccountNumber();
            String accountSufficientFundsCode = accountingLine.getAccount().getAccountSufficientFundsCode();
            String financialObjectCode = accountingLine.getFinancialObjectCode();
            String financialObjectLevelCode = accountingLine.getObjectCode().getFinancialObjectLevelCode();
            String sufficientFundsObjectCode = SpringServiceLocator.getSufficientFundsService().getSufficientFundsObjectCode(
                    chartOfAccountsCode, financialObjectCode, accountSufficientFundsCode, financialObjectLevelCode);

            item = buildSufficentFundsItem(acocuntNumber, accountSufficientFundsCode, lineAmount, chartOfAccountsCode,
                    sufficientFundsObjectCode, offsetDebitCreditCode, financialObjectCode, financialObjectLevelCode, accountingLine
                            .getPostingYear(), objectType);
        }
        return item;
    }
}