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
package org.kuali.module.purap.rules;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.RicePropertyConstants;
import org.kuali.core.datadictionary.validation.fieldlevel.ZipcodeValidationPattern;
import org.kuali.core.document.AmountTotaling;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GeneralLedgerPendingEntrySequenceHelper;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.ObjectUtils;
import org.kuali.core.workflow.service.KualiWorkflowDocument;
import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.bo.AccountingLine;
import org.kuali.kfs.bo.GeneralLedgerPendingEntry;
import org.kuali.kfs.context.SpringContext;
import org.kuali.kfs.document.AccountingDocument;
import org.kuali.module.purap.PurapConstants;
import org.kuali.module.purap.PurapKeyConstants;
import org.kuali.module.purap.PurapPropertyConstants;
import org.kuali.module.purap.PurapWorkflowConstants.RequisitionDocument.NodeDetailEnum;
import org.kuali.module.purap.document.PurchasingAccountsPayableDocument;
import org.kuali.module.purap.document.PurchasingDocument;
import org.kuali.module.purap.document.RequisitionDocument;
import org.kuali.module.purap.service.PurApWorkflowIntegrationService;

public class RequisitionDocumentRule extends PurchasingDocumentRuleBase {

    /**
     * Tabs included on Purchasing Documents are: Payment Info, Delivery, and Additional
     * 
     * @see org.kuali.module.purap.rules.PurchasingAccountsPayableDocumentRuleBase#processValidation(org.kuali.module.purap.document.PurchasingAccountsPayableDocument)
     */
    @Override
    public boolean processValidation(PurchasingAccountsPayableDocument purapDocument) {
        boolean valid = super.processValidation(purapDocument);
        valid &= processAdditionalValidation((PurchasingDocument) purapDocument);
        return valid;
    }
    
    /**
     * Check to see if the Requisition is going to stop at content review route level.
     * 
     * @see org.kuali.module.purap.rules.PurchasingAccountsPayableDocumentRuleBase#requiresAccountValidationOnAllEnteredItems(org.kuali.module.purap.document.PurchasingAccountsPayableDocument)
     */
    @Override
    public boolean requiresAccountValidationOnAllEnteredItems(PurchasingAccountsPayableDocument document) {
        if (SpringContext.getBean(PurApWorkflowIntegrationService.class).willDocumentStopAtGivenFutureRouteNode(document, NodeDetailEnum.CONTENT_REVIEW)) {
            return false;
        }
        return super.requiresAccountValidationOnAllEnteredItems(document);
    }
    
    /**
     * This method performs any validation for the Additional tab.
     * 
     * @param purDocument
     * @return
     */
    public boolean processAdditionalValidation(PurchasingDocument purDocument) {
        boolean valid = true;
        valid = validateTotalDollarAmountIsLessThanPurchaseOrderTotalLimit(purDocument);
        return valid;
    }
    
    /**
     * 
     * This method performs validations for the fields in vendor tab.
     * The business rules to be validated are:
     * 1.  If this is a standard order requisition (not B2B), then if Country is United 
     *     States and the postal code is required and if zip code is entered, it should 
     *     be a valid US Zip code. (format)
     *     
     * @param purapDocument The requisition document object whose vendor tab is to be validated
     * @return true if it passes vendor validation and false otherwise.
     */
    @Override
    public boolean processVendorValidation(PurchasingAccountsPayableDocument purapDocument) {
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        errorMap.clearErrorPath();
        errorMap.addToErrorPath(RicePropertyConstants.DOCUMENT);
        boolean valid = super.processVendorValidation(purapDocument);
        RequisitionDocument reqDocument = (RequisitionDocument)purapDocument;
        if (reqDocument.getRequisitionSourceCode().equals(PurapConstants.RequisitionSources.STANDARD_ORDER)) { 
            if (!StringUtils.isBlank(reqDocument.getVendorCountryCode()) &&
                    reqDocument.getVendorCountryCode().equals(KFSConstants.COUNTRY_CODE_UNITED_STATES) && 
                !StringUtils.isBlank(reqDocument.getVendorPostalCode())) {
                ZipcodeValidationPattern zipPattern = new ZipcodeValidationPattern();
                if (!zipPattern.matches(reqDocument.getVendorPostalCode())) {
                    valid = false;
                    errorMap.putError(PurapPropertyConstants.VENDOR_POSTAL_CODE, PurapKeyConstants.ERROR_POSTAL_CODE_INVALID);
                }
            }
        }
        errorMap.clearErrorPath();
        return valid;
    }
    
    /**
     * Validate that if the PurchaseOrderTotalLimit is not null then the TotalDollarAmount cannot be greater than the
     * PurchaseOrderTotalLimit.
     * 
     * @return True if the TotalDollarAmount is less than the PurchaseOrderTotalLimit. False otherwise.
     */
    public boolean validateTotalDollarAmountIsLessThanPurchaseOrderTotalLimit(PurchasingDocument purDocument) {
        boolean valid = true;
        GlobalVariables.getErrorMap().clearErrorPath();
        GlobalVariables.getErrorMap().addToErrorPath(RicePropertyConstants.DOCUMENT);
        if (ObjectUtils.isNotNull(purDocument.getPurchaseOrderTotalLimit()) && ObjectUtils.isNotNull(((AmountTotaling) purDocument).getTotalDollarAmount())) {
            if (((AmountTotaling) purDocument).getTotalDollarAmount().isGreaterThan(purDocument.getPurchaseOrderTotalLimit())) {
                valid &= false;
                GlobalVariables.getErrorMap().putError(PurapPropertyConstants.PURCHASE_ORDER_TOTAL_LIMIT, PurapKeyConstants.ERROR_PURCHASE_ORDER_EXCEEDING_TOTAL_LIMIT);                
            }
        }
        GlobalVariables.getErrorMap().clearErrorPath();
        return valid;
    }

    /**
     * @see org.kuali.kfs.rules.AccountingDocumentRuleBase#checkAccountingLineAccountAccessibility(org.kuali.kfs.document.AccountingDocument, org.kuali.kfs.bo.AccountingLine, org.kuali.kfs.rules.AccountingDocumentRuleBase.AccountingLineAction)
     */
    @Override
    protected boolean checkAccountingLineAccountAccessibility(AccountingDocument financialDocument, AccountingLine accountingLine, AccountingLineAction action) {
        KualiWorkflowDocument workflowDocument = financialDocument.getDocumentHeader().getWorkflowDocument();
        List currentRouteLevels = getCurrentRouteLevels(workflowDocument);

        if (((RequisitionDocument)financialDocument).isDocumentStoppedInRouteNode(NodeDetailEnum.CONTENT_REVIEW)) {
            // DO NOTHING: do not check that user owns acct lines; at this level, approvers can edit all detail on REQ
            return true;
        }
        else {
            return super.checkAccountingLineAccountAccessibility(financialDocument, accountingLine, action);
        }
    }
    
    /**
     * @see org.kuali.kfs.rules.GeneralLedgerPostingDocumentRuleBase#populateOffsetGeneralLedgerPendingEntry(java.lang.Integer,
     *      org.kuali.kfs.bo.GeneralLedgerPendingEntry, org.kuali.core.util.GeneralLedgerPendingEntrySequenceHelper,
     *      org.kuali.kfs.bo.GeneralLedgerPendingEntry)
     */
    @Override
    protected boolean populateOffsetGeneralLedgerPendingEntry(Integer universityFiscalYear, GeneralLedgerPendingEntry explicitEntry, GeneralLedgerPendingEntrySequenceHelper sequenceHelper, GeneralLedgerPendingEntry offsetEntry) {
        //Requisition doesn't generate GL entries
        return true;
    }

    @Override
    public boolean processAddAccountingLineBusinessRules(AccountingDocument financialDocument, AccountingLine accountingLine) {
        // make sure it's active for usage
        if (isAccountClosed(accountingLine)) {
            return false;
        }
        return super.processAddAccountingLineBusinessRules(financialDocument, accountingLine);
    }

    @Override
    public boolean processReviewAccountingLineBusinessRules(AccountingDocument financialDocument, AccountingLine accountingLine) {
        // make sure it's active for usage
        if (isAccountClosed(accountingLine)) {
            return false;
        }
        return super.processReviewAccountingLineBusinessRules(financialDocument, accountingLine);
    }

    @Override
    public boolean processUpdateAccountingLineBusinessRules(AccountingDocument financialDocument, AccountingLine accountingLine, AccountingLine updatedAccountingLine) {
        // make sure it's active for usage
        if (isAccountClosed(accountingLine)) {
            return false;
        }
        return super.processUpdateAccountingLineBusinessRules(financialDocument, accountingLine, updatedAccountingLine);
    } 
    
    private boolean isAccountClosed(AccountingLine accountingLine) {
        accountingLine.refreshNonUpdateableReferences();
        if (accountingLine.getAccount() != null && accountingLine.getAccount().isAccountClosedIndicator()) {
            GlobalVariables.getErrorMap().putError(PurapPropertyConstants.ACCOUNTS, PurapKeyConstants.ERROR_REQUISITION_ACCOUNT_CLOSED, accountingLine.getChartOfAccountsCode(), accountingLine.getAccountNumber());
            return true;
        }
        return false;
    }

}
