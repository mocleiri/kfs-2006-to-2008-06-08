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

import org.apache.commons.lang.StringUtils;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.document.Document;
import org.kuali.core.exceptions.UserNotFoundException;
import org.kuali.core.exceptions.ValidationException;
import org.kuali.core.rule.event.ApproveDocumentEvent;
import org.kuali.core.rules.TransactionalDocumentRuleBase;
import org.kuali.core.service.UniversalUserService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kfs.context.SpringContext;
import org.kuali.kfs.service.ParameterService;
import org.kuali.kfs.service.impl.ParameterConstants;
import org.kuali.module.purap.PurapKeyConstants;
import org.kuali.module.purap.PurapParameterConstants;
import org.kuali.module.purap.PurapPropertyConstants;
import org.kuali.module.purap.PurapConstants.PurchaseOrderStatuses;
import org.kuali.module.purap.document.PurchaseOrderDocument;
import org.kuali.module.purap.service.PurchaseOrderService;

/**
 * Rules for Purchase Order Remove Hold document creation. Should not extend <code>PurchaseOrderDocumentRule</code>, since it
 * does not allow the purchase order to be edited, nor should it create GL entries.
 */
public class PurchaseOrderRemoveHoldDocumentRule extends TransactionalDocumentRuleBase {

    /**
     * @see org.kuali.module.financial.rules.TransactionalDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.Document)
     */
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        boolean isValid = true;
        PurchaseOrderDocument porDocument = (PurchaseOrderDocument) document;
        return isValid &= processValidation(porDocument);
    }

    /**
     * @see org.kuali.core.rules.DocumentRuleBase#processCustomSaveDocumentBusinessRules(org.kuali.core.document.Document)
     */
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        boolean isValid = true;
        PurchaseOrderDocument porDocument = (PurchaseOrderDocument) document;
        return isValid &= processValidation(porDocument);
    }

    /**
     * @see org.kuali.core.rules.DocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.core.rule.event.ApproveDocumentEvent)
     */
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(ApproveDocumentEvent approveEvent) {
        boolean isValid = true;
        PurchaseOrderDocument porDocument = (PurchaseOrderDocument) approveEvent.getDocument();
        return isValid;
    }

    /**
     * Central method to control the processing of rule checks. Checks that the purchase order document is not null, that it is in
     * the correct status, and checks that the user is in the correct user group.
     * 
     * @param document A PurchaseOrderDocument. (A PurchaseOrderPaymentHoldDocument at this point.)
     * @return True if the document passes all the validations.
     */
    boolean processValidation(PurchaseOrderDocument document) {
        boolean valid = true;

        // Check that the PO is not null
        if (ObjectUtils.isNull(document)) {
            throw new ValidationException("Purchase Order Remove Hold document was null on validation.");
        }
        else {
            PurchaseOrderDocument currentPO = SpringContext.getBean(PurchaseOrderService.class).getCurrentPurchaseOrder(document.getPurapDocumentIdentifier());
            // Check the PO status
            if (!StringUtils.equalsIgnoreCase(currentPO.getStatusCode(), PurchaseOrderStatuses.PAYMENT_HOLD) && !StringUtils.equalsIgnoreCase(currentPO.getStatusCode(), PurchaseOrderStatuses.PENDING_REMOVE_HOLD)) {
                valid = false;
                GlobalVariables.getErrorMap().putError(PurapPropertyConstants.STATUS_CODE, PurapKeyConstants.ERROR_PURCHASE_ORDER_STATUS_NOT_REQUIRED_STATUS, PurchaseOrderStatuses.PAYMENT_HOLD);
            }

            // Check that the user is in purchasing workgroup.
            String initiatorNetworkId = document.getDocumentHeader().getWorkflowDocument().getInitiatorNetworkId();
            UniversalUserService uus = SpringContext.getBean(UniversalUserService.class);
            UniversalUser user = null;
            try {
                user = uus.getUniversalUserByAuthenticationUserId(initiatorNetworkId);
                String purchasingGroup = SpringContext.getBean(ParameterService.class).getParameterValue(ParameterConstants.PURCHASING_DOCUMENT.class, PurapParameterConstants.Workgroups.WORKGROUP_PURCHASING);
                if (!uus.isMember(user, purchasingGroup)) {
                    valid = false;
                }
            }
            catch (UserNotFoundException ue) {
                valid = false;
            }
        }
        return valid;
    }
}