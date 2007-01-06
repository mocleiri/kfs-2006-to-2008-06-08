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

import static org.kuali.Constants.BALANCE_TYPE_PRE_ENCUMBRANCE;
import static org.kuali.PropertyConstants.REFERENCE_NUMBER;
import static org.kuali.PropertyConstants.REVERSAL_DATE;
import static org.kuali.core.util.AssertionUtils.assertThat;
import static org.kuali.module.financial.rules.PreEncumbranceDocumentRuleConstants.PRE_ENCUMBRANCE_DOCUMENT_SECURITY_GROUPING;
import static org.kuali.module.financial.rules.PreEncumbranceDocumentRuleConstants.RESTRICTED_OBJECT_TYPE_CODES;
import static org.kuali.module.financial.rules.TransactionalDocumentRuleBaseConstants.ERROR_PATH.DOCUMENT_ERROR_PREFIX;

import org.apache.commons.lang.StringUtils;
import org.kuali.Constants;
import org.kuali.KeyConstants;
import org.kuali.core.bo.AccountingLine;
import org.kuali.core.bo.TargetAccountingLine;
import org.kuali.core.datadictionary.BusinessObjectEntry;
import org.kuali.core.document.Document;
import org.kuali.core.document.TransactionalDocument;
import org.kuali.core.rule.KualiParameterRule;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.module.financial.document.PreEncumbranceDocument;
import org.kuali.module.gl.bo.GeneralLedgerPendingEntry;


/**
 * Business rule(s) applicable to PreEncumbrance documents.
 * 
 * 
 */
public class PreEncumbranceDocumentRule extends TransactionalDocumentRuleBase {

    /**
     * @see TransactionalDocumentRuleBase#processCustomAddAccountingLineBusinessRules(org.kuali.core.document.TransactionalDocument,
     *      org.kuali.core.bo.AccountingLine)
     */
    @Override
    public boolean processCustomAddAccountingLineBusinessRules(TransactionalDocument transactionalDocument, AccountingLine accountingLine) {
        return isRequiredReferenceFieldsValid(accountingLine);
    }

    /**
     * @see TransactionalDocumentRuleBase#processCustomUpdateAccountingLineBusinessRules(org.kuali.core.document.TransactionalDocument,
     *      org.kuali.core.bo.AccountingLine, org.kuali.core.bo.AccountingLine)
     */
    @Override
    protected boolean processCustomUpdateAccountingLineBusinessRules(TransactionalDocument transactionalDocument, AccountingLine originalAccountingLine, AccountingLine updatedAccountingLine) {
        return isRequiredReferenceFieldsValid(updatedAccountingLine);
    }

    /**
     * @see TransactionalDocumentRuleBase#processCustomReviewAccountingLineBusinessRules(org.kuali.core.document.TransactionalDocument,
     *      org.kuali.core.bo.AccountingLine)
     */
    @Override
    protected boolean processCustomReviewAccountingLineBusinessRules(TransactionalDocument transactionalDocument, AccountingLine accountingLine) {
        return isRequiredReferenceFieldsValid(accountingLine);
    }

    /**
     * This method checks that there is a value in a disencumbrance line's reference number field. This cannot be done by the
     * DataDictionary validation because not all documents require it. It does not validate the existence of the referenced
     * document.
     * 
     * @param accountingLine
     * 
     * @return True if the required external encumbrance reference field is valid, false otherwise.
     */
    private boolean isRequiredReferenceFieldsValid(AccountingLine accountingLine) {
        boolean valid = true;

        if (accountingLine.isTargetAccountingLine()) {
            BusinessObjectEntry boe = SpringServiceLocator.getDataDictionaryService().getDataDictionary().getBusinessObjectEntry(TargetAccountingLine.class);
            if (StringUtils.isEmpty(accountingLine.getReferenceNumber())) {
                putRequiredPropertyError(boe, REFERENCE_NUMBER);
                valid = false;
            }
        }
        return valid;
    }

    /**
     * @see TransactionalDocumentRuleBase#isObjectTypeAllowed(org.kuali.core.bo.AccountingLine)
     */
    @Override
    public boolean isObjectTypeAllowed(AccountingLine accountingLine) {
        KualiParameterRule combinedRule = KualiParameterRule.and(getGlobalObjectTypeRule(), getParameterRule(PRE_ENCUMBRANCE_DOCUMENT_SECURITY_GROUPING, RESTRICTED_OBJECT_TYPE_CODES));
        AttributeReference direct = createObjectCodeAttributeReference(accountingLine);
        AttributeReference indirect = createObjectTypeAttributeReference(accountingLine);
        boolean allowed = indirectRuleSucceeds(combinedRule, direct, indirect);
        if (allowed) {
            allowed &= super.isObjectTypeAllowed(accountingLine);
        }
        return allowed;
    }

    /**
     * Pre Encumbrance document specific business rule checks for the "route document" event.
     * 
     * @param document
     * 
     * @return boolean True if the rules checks passed, false otherwise.
     */
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        boolean valid = isReversalDateValidForRouting((PreEncumbranceDocument) document);
        // this short-circuiting is just because it's the current eDocs policy (altho I think it's user-unfriendly)
        if (valid) {
            // super is to call isAccountingLinesRequiredNumberForRoutingMet()
            valid &= super.processCustomRouteDocumentBusinessRules(document);
        }
        return valid;
    }

    /**
     * If a PreEncumbrance document has a reversal date, it must not be earlier than the current date to route.
     * 
     * @param preEncumbranceDocument
     * 
     * @return boolean True if this document does not have a reversal date earlier than the current date, false otherwise.
     */
    private boolean isReversalDateValidForRouting(PreEncumbranceDocument preEncumbranceDocument) {
        java.sql.Date reversalDate = preEncumbranceDocument.getReversalDate();
        return TransactionalDocumentRuleUtil.isValidReversalDate(reversalDate, DOCUMENT_ERROR_PREFIX + REVERSAL_DATE);
    }

    /**
     * PreEncumbrance documents require at least one accounting line in either section for routing.
     * 
     * @param transactionalDocument
     * 
     * @return boolean True if the number of accounting lines are valid for routing, false otherwise.
     */
    @Override
    protected boolean isAccountingLinesRequiredNumberForRoutingMet(TransactionalDocument transactionalDocument) {
        if (0 == transactionalDocument.getSourceAccountingLines().size() && 0 == transactionalDocument.getTargetAccountingLines().size()) {
            GlobalVariables.getErrorMap().putError(Constants.ACCOUNTING_LINE_ERRORS, KeyConstants.ERROR_DOCUMENT_NO_ACCOUNTING_LINES);
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * PreEncumbrance documents don't need to balance in any way.
     * 
     * @see TransactionalDocumentRuleBase#isDocumentBalanceValid(org.kuali.core.document.TransactionalDocument)
     */
    @Override
    protected boolean isDocumentBalanceValid(TransactionalDocument transactionalDocument) {
        return true;
    }

    /**
     * This method contains Pre Encumbrance document specific GLPE explicit entry attribute assignments.
     * 
     * @see org.kuali.module.financial.rules.TransactionalDocumentRuleBase#customizeExplicitGeneralLedgerPendingEntry(org.kuali.core.document.TransactionalDocument,
     *      org.kuali.core.bo.AccountingLine, org.kuali.module.gl.bo.GeneralLedgerPendingEntry)
     */
    @Override
    protected void customizeExplicitGeneralLedgerPendingEntry(TransactionalDocument transactionalDocument, AccountingLine accountingLine, GeneralLedgerPendingEntry explicitEntry) {
        explicitEntry.setFinancialBalanceTypeCode(BALANCE_TYPE_PRE_ENCUMBRANCE);

        // set the reversal date to what was chosen by the user in the interface
        PreEncumbranceDocument peDoc = (PreEncumbranceDocument) transactionalDocument;
        if (peDoc.getReversalDate() != null) {
            explicitEntry.setFinancialDocumentReversalDate(peDoc.getReversalDate());
        }
        explicitEntry.setTransactionEntryProcessedTs(null);
        if (accountingLine.isSourceAccountingLine()) {
            explicitEntry.setTransactionEncumbranceUpdateCode(Constants.ENCUMB_UPDT_DOCUMENT_CD);
        }
        else {
            assertThat(accountingLine.isTargetAccountingLine(), accountingLine);
            explicitEntry.setTransactionEncumbranceUpdateCode(Constants.ENCUMB_UPDT_REFERENCE_DOCUMENT_CD);
            explicitEntry.setReferenceFinancialSystemOriginationCode(SpringServiceLocator.getHomeOriginationService().getHomeOrigination().getFinSystemHomeOriginationCode());
            explicitEntry.setReferenceFinancialDocumentNumber(accountingLine.getReferenceNumber());
            explicitEntry.setReferenceFinancialDocumentTypeCode(explicitEntry.getFinancialDocumentTypeCode()); // "PE"
        }
    }

    /**
     * limits only to expense object type codes
     * 
     * @see IsDebitUtils#isDebitConsideringSection(TransactionalDocumentRuleBase, TransactionalDocument, AccountingLine)
     * 
     * @see org.kuali.core.rule.AccountingLineRule#isDebit(org.kuali.core.document.TransactionalDocument,
     *      org.kuali.core.bo.AccountingLine)
     */
    public boolean isDebit(TransactionalDocument transactionalDocument, AccountingLine accountingLine) {
        // if not expense, or positive amount on an error-correction, or negative amount on a non-error-correction, throw exception
        if (!isExpense(accountingLine) || (isErrorCorrection(transactionalDocument) == accountingLine.getAmount().isPositive())) {
            throw new IllegalStateException(IsDebitUtils.isDebitCalculationIllegalStateExceptionMessage);
        }

        return !IsDebitUtils.isDebitConsideringSection(this, transactionalDocument, accountingLine);
    }
}
