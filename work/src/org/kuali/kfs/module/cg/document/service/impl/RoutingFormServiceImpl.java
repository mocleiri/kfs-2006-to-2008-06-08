/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/work/src/org/kuali/kfs/module/cg/document/service/impl/RoutingFormServiceImpl.java,v $
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
package org.kuali.module.kra.routingform.service.impl;

import org.kuali.core.service.DocumentService;
import org.kuali.module.kra.routingform.document.RoutingFormDocument;
import org.kuali.module.kra.routingform.service.RoutingFormResearchRiskService;
import org.kuali.module.kra.routingform.service.RoutingFormService;

import edu.iu.uis.eden.exception.WorkflowException;

public class RoutingFormServiceImpl implements RoutingFormService {

    private DocumentService documentService;
    
    public void initializeRoutingForm(RoutingFormDocument routingFormDocument) throws WorkflowException {
        //TODO
    }

    /**
     * @see org.kuali.module.kra.routingform.service.RoutingFormService#prepareRoutingFormForSave(org.kuali.module.kra.routingform.document.RoutingFormDocument)
     */
    public void prepareRoutingFormForSave(RoutingFormDocument routingFormDocument) throws WorkflowException {
        // TODO not sure if the following is appropriate, Budget doesn't do this. But for now this works.
        
        if (routingFormDocument.getContractGrantProposal() != null) {
            routingFormDocument.getContractGrantProposal().setDocumentNumber(routingFormDocument.getDocumentHeader().getDocumentNumber());
        }
        
        if (routingFormDocument.getRoutingFormAgency() != null) {
            routingFormDocument.getRoutingFormAgency().setDocumentNumber(routingFormDocument.getDocumentHeader().getDocumentNumber());
        }
    }

    /**
     * Sets the documentService attribute value.
     * 
     * @param documentService The documentService to set.
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
}
