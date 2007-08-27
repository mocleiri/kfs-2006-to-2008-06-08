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
package org.kuali.module.purap.document.authorization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.authorization.AuthorizationConstants;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.document.Document;
import org.kuali.core.document.authorization.DocumentActionFlags;
import org.kuali.core.exceptions.GroupNotFoundException;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.service.KualiGroupService;
import org.kuali.core.workflow.service.KualiWorkflowDocument;
import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.context.SpringContext;
import org.kuali.kfs.document.authorization.AccountingDocumentAuthorizerBase;
import org.kuali.module.purap.PurapAuthorizationConstants;
import org.kuali.module.purap.PurapParameterConstants;
import org.kuali.module.purap.PurapWorkflowConstants;
import org.kuali.module.purap.PurapConstants.PurchaseOrderStatuses;
import org.kuali.module.purap.document.PurchaseOrderDocument;

/**
 * Document Authorizer for the PO document.
 * 
 */
public class PurchaseOrderDocumentAuthorizer extends AccountingDocumentAuthorizerBase {

    @Override
    public boolean hasInitiateAuthorization(Document document, UniversalUser user) {
        String authorizedWorkgroup = SpringContext.getBean(KualiConfigurationService.class).getParameterValue(KFSConstants.PURAP_NAMESPACE, PurapParameterConstants.Workgroups.PURAP_DOCUMENT_PO_INITIATE_ACTION);
        try {
            return SpringContext.getBean(KualiGroupService.class).getByGroupName(authorizedWorkgroup).hasMember(user);
        }
        catch (GroupNotFoundException e) {
            throw new RuntimeException("Workgroup " + authorizedWorkgroup + " not found",e);
        }
    }

    /**
     * This is essentially the same getEditMode as in DocumentAuthorizerBase.java.
     * In AccountingDocumentAuthorizerBase.java, which is currently the superclass of this class,
     * this method is being overriden. Unfortunately it will return view only edit mode if the
     * initiator of the document is different than the current user.
     * Currently the initiators of Purchase Order Document are all "Kuali System User" which is
     * different than the users that we use to log in. Therefore here we have to re-override
     * the getEditMode to prevent the problem where the fields appear as read-only.
     * 
     * There has been an addition to this method, which at this point I'm not sure whether there would be any cases where the
     * Purchase Order Document would have status "RETR". If so, then when the status code is "RETR" (retransmit), the edit mode 
     * should be set to displayRetransmitTab because we want to hide the other tabs and display the retransmit tab when
     * the user clicks on the Retransmit button (is that what we want ?)
     * 
     * @see org.kuali.core.document.authorization.DocumentAuthorizer#getEditMode(org.kuali.core.document.Document, org.kuali.core.bo.user.UniversalUser)
     */
    @Override
    public Map getEditMode(Document d, UniversalUser user, List sourceAccountingLines, List targetAccountingLines) {
        Map editModeMap = new HashMap();
        String editMode = AuthorizationConstants.EditMode.VIEW_ONLY;

        KualiWorkflowDocument workflowDocument = d.getDocumentHeader().getWorkflowDocument();
        if (workflowDocument.stateIsInitiated() || workflowDocument.stateIsSaved()) {
            if (hasInitiateAuthorization(d, user)) {
                editMode = AuthorizationConstants.EditMode.FULL_ENTRY;
            }
        }
        else if (workflowDocument.stateIsEnroute() && workflowDocument.isApprovalRequested()) {
            List currentRouteLevels = getCurrentRouteLevels(workflowDocument);

            /**
             * INTERNAL PURCHASING ROUTE LEVEL - Approvers can edit full detail on Purchase Order except they cannot change the CHART/ORG.
             */
            if (((PurchaseOrderDocument)d).isDocumentStoppedInRouteNode(PurapWorkflowConstants.PurchaseOrderDocument.NodeDetailEnum.INTERNAL_PURCHASING_REVIEW)) {
                //if (currentRouteLevels.contains(NodeDetailEnum.INTERNAL_PURCHASING_REVIEW.getName()) && workflowDocument.isApprovalRequested()) {
                // FULL_ENTRY allowed; also set internal purchasing lock
                editMode = AuthorizationConstants.EditMode.FULL_ENTRY;
                editModeMap.put(PurapAuthorizationConstants.PurchaseOrderEditMode.LOCK_INTERNAL_PURCHASING_ENTRY, "TRUE");
            }

            /**
             * CONTRACTS & GRANTS ROUTE LEVEL - Approvers cannot edit any detail on PO.
             * BUDGET OFFICE ROUTE LEVEL - Approvers cannot edit any detail on PO.
             * VENDOR TAX ROUTE LEVEL - Approvers cannot edit any detail on PO.
             * DOCUMENT TRANSMISSION ROUTE LEVEL - Approvers cannot edit any detail on PO.
             */
            else {
                //VIEW_ENTRY that is already being set is sufficient. 
            }
        }
        editModeMap.put(editMode, "TRUE");
        
        // The ability to change contract manager is dependent on being in one of a specific set of statuses prior to routing.
        if( PurchaseOrderStatuses.CONTRACT_MANAGER_CHANGEABLE_STATUSES.contains(((PurchaseOrderDocument)d).getStatusCode()) ) {
            editModeMap.put(PurapAuthorizationConstants.PurchaseOrderEditMode.CONTRACT_MANAGER_CHANGEABLE, "TRUE");
        }

        return editModeMap;
    }
    
    @Override
    public DocumentActionFlags getDocumentActionFlags(Document document, UniversalUser user) {
        DocumentActionFlags flags = super.getDocumentActionFlags(document, user);
        PurchaseOrderDocument po = (PurchaseOrderDocument)document;
        String statusCode = po.getStatusCode();
        
        if ((StringUtils.equals(statusCode,PurchaseOrderStatuses.WAITING_FOR_DEPARTMENT)) ||
            (StringUtils.equals(statusCode,PurchaseOrderStatuses.WAITING_FOR_VENDOR))){
            flags.setCanRoute(false);
        }       
        return flags;
    }
}