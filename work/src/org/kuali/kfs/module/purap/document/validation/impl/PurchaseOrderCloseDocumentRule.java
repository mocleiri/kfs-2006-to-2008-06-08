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
package org.kuali.module.purap.rules;

import static org.kuali.kfs.KFSConstants.GL_CREDIT_CODE;
import static org.kuali.kfs.KFSConstants.MONTH1;
import static org.kuali.module.purap.PurapConstants.PurchaseOrderDocumentTypeCodes.PO_CLOSE;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.document.Document;
import org.kuali.core.exceptions.ValidationException;
import org.kuali.core.rule.event.ApproveDocumentEvent;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kfs.bo.AccountingLine;
import org.kuali.kfs.bo.GeneralLedgerPendingEntry;
import org.kuali.kfs.document.AccountingDocument;
import org.kuali.kfs.util.SpringServiceLocator;
import org.kuali.module.gl.bo.UniversityDate;
import org.kuali.module.purap.PurapKeyConstants;
import org.kuali.module.purap.PurapPropertyConstants;
import org.kuali.module.purap.PurapConstants.PaymentRequestStatuses;
import org.kuali.module.purap.PurapConstants.PurchaseOrderStatuses;
import org.kuali.module.purap.document.PaymentRequestDocument;
import org.kuali.module.purap.document.PurchaseOrderDocument;

public class PurchaseOrderCloseDocumentRule extends PurchasingDocumentRuleBase {

    /**
     * @see org.kuali.module.financial.rules.TransactionalDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.Document)
     */
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        boolean isValid = true;
        PurchaseOrderDocument poDocument = (PurchaseOrderDocument) document;
        return isValid &= processValidation(poDocument);
    }

    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        boolean isValid = true;
        PurchaseOrderDocument poDocument = (PurchaseOrderDocument) document;
        return isValid &= processValidation(poDocument);
    }

    @Override
    protected boolean processCustomApproveDocumentBusinessRules(ApproveDocumentEvent approveEvent) {
        boolean isValid = true;
        PurchaseOrderDocument poDocument = (PurchaseOrderDocument) approveEvent.getDocument();
        return isValid;
    }

    public boolean processValidation(PurchaseOrderDocument document) {
        boolean valid = true;
        // Check that the PO is not null
        if (ObjectUtils.isNull(document)) {
            throw new ValidationException("Purchase Order Close document was null on validation.");
        }
        else {
            // TODO: Get this from Business Rules.
            // The PO must be in OPEN status.
            if (!StringUtils.equalsIgnoreCase(document.getStatusCode(), PurchaseOrderStatuses.OPEN)) {
                valid = false;
                GlobalVariables.getErrorMap().putError(PurapPropertyConstants.STATUS_CODE, PurapKeyConstants.ERROR_PURCHASE_ORDER_STATUS_NOT_REQUIRED_STATUS, PurchaseOrderStatuses.OPEN );
            }
            else {
                valid &= processPaymentRequestRules( document );
            }
        }
        return valid;
    }

    public boolean processPaymentRequestRules(PurchaseOrderDocument document) {
        boolean valid = true;
        // The PO must have at least one PREQ against it.
        Integer poDocId = document.getPurapDocumentIdentifier();
        List<PaymentRequestDocument> pReqs = SpringServiceLocator.getPaymentRequestService().getPaymentRequestsByPurchaseOrderId(poDocId);
        if (ObjectUtils.isNotNull(pReqs)) {
            if (pReqs.size() == 0) {
                valid = false;
                GlobalVariables.getErrorMap().putError(PurapPropertyConstants.PURAP_DOC_ID, PurapKeyConstants.ERROR_PURCHASE_ORDER_CLOSE_NO_PREQ, PurchaseOrderStatuses.OPEN);
            }
            else {
                // None of the PREQs against this PO may be in 'In Process' status.
                for (PaymentRequestDocument pReq : pReqs) {
                    if (StringUtils.equalsIgnoreCase(pReq.getStatusCode(), PaymentRequestStatuses.IN_PROCESS)) {
                        valid = false;
                        GlobalVariables.getErrorMap().putError(PurapPropertyConstants.PURAP_DOC_ID, PurapKeyConstants.ERROR_PURCHASE_ORDER_CLOSE_PREQ_IN_PROCESS, PaymentRequestStatuses.IN_PROCESS);
                    }
                }
            }
        }
        return valid;
    }

    @Override
    protected void customizeExplicitGeneralLedgerPendingEntry(AccountingDocument accountingDocument, AccountingLine accountingLine, GeneralLedgerPendingEntry explicitEntry) {
        super.customizeExplicitGeneralLedgerPendingEntry(accountingDocument, accountingLine, explicitEntry);
        PurchaseOrderDocument po = (PurchaseOrderDocument)accountingDocument;

        purapCustomizeGeneralLedgerPendingEntry(po, accountingLine, explicitEntry, po.getPurapDocumentIdentifier(), GL_CREDIT_CODE, true);
        
        explicitEntry.setTransactionLedgerEntryDescription(entryDescription(po.getVendorName()));
        explicitEntry.setFinancialDocumentTypeCode(PO_CLOSE);  //don't think i should have to override this, but default isn't getting the right PO doc
        
        UniversityDate uDate = SpringServiceLocator.getUniversityDateService().getCurrentUniversityDate();
        if (po.getPostingYear().compareTo(uDate.getUniversityFiscalYear()) > 0) {
            //USE NEXT AS SET ON PO; POs can be forward dated to not encumber until next fiscal year
            explicitEntry.setUniversityFiscalYear(po.getPostingYear());
            explicitEntry.setUniversityFiscalPeriodCode(MONTH1);
}
        else {
            //USE CURRENT; don't use FY on PO in case it's a prior year
            explicitEntry.setUniversityFiscalYear(uDate.getUniversityFiscalYear());
            explicitEntry.setUniversityFiscalPeriodCode(uDate.getUniversityFiscalAccountingPeriod());
            //TODO do we need to update the doc posting year?
        }
    }
}
