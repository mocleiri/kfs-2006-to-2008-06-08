/*
 * Copyright 2008 The Kuali Foundation.
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

import java.util.List;
import java.util.Map;

import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.document.Document;
import org.kuali.core.util.ObjectUtils;
import org.kuali.core.workflow.service.KualiWorkflowDocument;
import org.kuali.kfs.document.authorization.AccountingDocumentAuthorizerBase;
import org.kuali.module.ar.ArAuthorizationConstants;
import org.kuali.module.ar.document.CustomerInvoiceDocument;

public class CustomerInvoiceDocumentAuthorizer extends AccountingDocumentAuthorizerBase {
    
    /**
     * Sets edit mode for customer invoice document.  If customer number has already be selected, make the field read only.
     * 
     * @see org.kuali.kfs.document.authorization.AccountingDocumentAuthorizer#getEditMode(org.kuali.core.document.Document,
     *      org.kuali.core.bo.user.UniversalUser, java.util.List, java.util.List)
     */
    @Override
    public Map getEditMode(Document document, UniversalUser user, List sourceAccountingLines, List targetAccountingLines) {
        Map editModeMap = super.getEditMode(document, user);
        KualiWorkflowDocument workflowDocument = document.getDocumentHeader().getWorkflowDocument();
        CustomerInvoiceDocument customerInvoiceDocument = (CustomerInvoiceDocument) document;

        if (workflowDocument.stateIsInitiated() || workflowDocument.stateIsSaved() || workflowDocument.stateIsEnroute()) {
            if (ObjectUtils.isNotNull(customerInvoiceDocument.getAccountsReceivableDocumentHeader().getCustomerNumber())) {
                editModeMap.put(ArAuthorizationConstants.CustomerInvoiceDocumentEditMode.LOCK_CUSTOMER_ENTRY, "TRUE");
            }
        }

        return editModeMap;
    }

}
