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
package org.kuali.module.purap.web.struts.action;

import org.kuali.core.web.struts.action.KualiTransactionalDocumentActionBase;
import org.kuali.core.web.struts.form.KualiDocumentFormBase;
import org.kuali.module.purap.PurapConstants;
import org.kuali.module.purap.document.AssignContractManagerDocument;

import edu.iu.uis.eden.exception.WorkflowException;

/**
 * This class handles Actions for AssignContractManager.
 */
public class AssignContractManagerAction extends KualiTransactionalDocumentActionBase {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(AssignContractManagerAction.class);

    /**
     * Do initialization for a new AssignContractManagerDocument
     * 
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#createDocument(org.kuali.core.web.struts.form.KualiDocumentFormBase)
     */
    @Override
    protected void createDocument(KualiDocumentFormBase kualiDocumentFormBase) throws WorkflowException {
        super.createDocument(kualiDocumentFormBase);
        AssignContractManagerDocument acmDocument = (AssignContractManagerDocument) kualiDocumentFormBase.getDocument();
        acmDocument.getDocumentHeader().setFinancialDocumentDescription(PurapConstants.ASSIGN_CONTRACT_MANAGER_DEFAULT_DESC);
        acmDocument.populateDocumentWithRequisitions();
    }
}
