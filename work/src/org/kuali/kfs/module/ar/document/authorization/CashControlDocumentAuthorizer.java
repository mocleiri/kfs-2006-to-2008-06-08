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
package org.kuali.module.ar.document.authorization;

import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.document.Document;
import org.kuali.core.document.authorization.DocumentActionFlags;
import org.kuali.core.document.authorization.TransactionalDocumentAuthorizerBase;
import org.kuali.core.exceptions.DocumentInitiationAuthorizationException;
import org.kuali.core.exceptions.DocumentTypeAuthorizationException;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.workflow.service.KualiWorkflowDocument;
import org.kuali.kfs.context.SpringContext;
import org.kuali.module.ar.ArConstants;
import org.kuali.module.ar.bo.CashControlDetail;
import org.kuali.module.ar.document.CashControlDocument;
import org.kuali.module.ar.document.PaymentApplicationDocument;
import org.kuali.module.ar.service.OrganizationOptionsService;
import org.kuali.module.chart.bo.ChartUser;
import org.kuali.module.chart.lookup.valuefinder.ValueFinderUtil;

public class CashControlDocumentAuthorizer extends TransactionalDocumentAuthorizerBase {
    
    /**
     * @see org.kuali.core.document.DocumentAuthorizerBase#getDocumentActionFlags(org.kuali.core.document.Document,
     *      org.kuali.core.bo.user.KualiUser)
     */
    @Override
    public DocumentActionFlags getDocumentActionFlags(Document document, UniversalUser user) {

        DocumentActionFlags flags = super.getDocumentActionFlags(document, user);
        CashControlDocument cashControlDocument = (CashControlDocument) document;

        // Blanket Approval is not used for CashControlDocument
        flags.setCanBlanketApprove(false);

        // if at least one application document has been approved the Cash Control Document cannot be disapproved
        if (hasAtLeastOneAppDocApproved(cashControlDocument)) {
            flags.setCanDisapprove(false);
        }

        // if not all application documents have been approved the CashControlDocument cannot be approved
        if (!hasAllAppDocsApproved(cashControlDocument)) {
            flags.setCanApprove(false);
        }

        return flags;

    }
    
    /**
     * This method checks if the CashControlDocument has at least one application document that has been approved
     * 
     * @param ccDoc the CashControlDocument
     * @return true if it has at least one application document approved, false otherwise
     */
    private boolean hasAtLeastOneAppDocApproved(CashControlDocument cashControlDocument) {
        boolean result = false;
        // check if there is at least one Application Document approved
        for (CashControlDetail cashControlDetail : cashControlDocument.getCashControlDetails()) {
            PaymentApplicationDocument applicationDocument = cashControlDetail.getReferenceFinancialDocument();
            KualiWorkflowDocument workflowDocument = applicationDocument.getDocumentHeader().getWorkflowDocument();

            if (workflowDocument != null && workflowDocument.stateIsApproved()) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    /**
     * This method chech if all application document have been approved
     * 
     * @param cashControlDocument the CashControlDocument
     * @return true if all application documents have been approved, false otherwise
     */
    private boolean hasAllAppDocsApproved(CashControlDocument cashControlDocument) {
        boolean result = true;
        for (CashControlDetail cashControlDetail : cashControlDocument.getCashControlDetails()) {

            PaymentApplicationDocument applicationDocument = cashControlDetail.getReferenceFinancialDocument();
            KualiWorkflowDocument workflowDocument = applicationDocument.getDocumentHeader().getWorkflowDocument();

            if (!(workflowDocument.stateIsApproved() || workflowDocument.stateIsFinal())) {
                result = false;
                break;
            }

        }
        return result;
    }
    
    /**
     * @see org.kuali.core.document.authorization.DocumentAuthorizerBase#canInitiate(java.lang.String, org.kuali.core.bo.user.UniversalUser)
     */
    @Override
    public void canInitiate(String documentTypeName, UniversalUser user) throws DocumentTypeAuthorizationException {
        super.canInitiate(documentTypeName, user);
        // to initiate, the user must have the organization options set up.
        ChartUser chartUser = ValueFinderUtil.getCurrentChartUser();
        String chartCode = chartUser.getChartOfAccountsCode();
        String orgCode = chartUser.getUserOrganizationCode();
        OrganizationOptionsService service = SpringContext.getBean(OrganizationOptionsService.class);
        KualiConfigurationService configurationService = SpringContext.getBean(KualiConfigurationService.class);

        if (null == service.getByPrimaryKey(chartCode, orgCode)) {
            throw new DocumentInitiationAuthorizationException(ArConstants.ERROR_ORGANIZATION_OPTIONS_MUST_BE_SET_FOR_USER_ORG, new String[] {});

        }
    }

}
