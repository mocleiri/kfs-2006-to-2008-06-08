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
package org.kuali.module.cams.document.authorization;

import java.util.Map;

import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.document.Document;
import org.kuali.core.document.authorization.DocumentActionFlags;
import org.kuali.core.document.authorization.TransactionalDocumentAuthorizerBase;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kfs.context.SpringContext;
import org.kuali.module.cams.CamsConstants;
import org.kuali.module.cams.document.EquipmentLoanOrReturnDocument;
import org.kuali.module.cams.service.EquipmentLoanOrReturnService;


/**
 * Uses defaults.
 */
public class EquipmentLoanOrReturnDocumentAuthorizer extends TransactionalDocumentAuthorizerBase {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(EquipmentLoanOrReturnDocumentAuthorizer.class);

    public Map getEditMode(Document document, UniversalUser user) {

        Map<String, String> editModeMap = super.getEditMode(document, user);
        EquipmentLoanOrReturnDocument equipmentLoanOrReturnDocument = (EquipmentLoanOrReturnDocument) document;

        if (ObjectUtils.isNotNull(equipmentLoanOrReturnDocument.getExpectedReturnDate())) {
            editModeMap.put(CamsConstants.EquipmentLoanOrReturnEditMode.DISPLAY_NEW_LOAN_TAB, "FALSE");
        }
        else {
            editModeMap.put(CamsConstants.EquipmentLoanOrReturnEditMode.DISPLAY_NEW_LOAN_TAB, "TRUE");
        }

        if (ObjectUtils.isNotNull(equipmentLoanOrReturnDocument.getLoanReturnDate())) {
            editModeMap.put(CamsConstants.EquipmentLoanOrReturnEditMode.DISPLAY_RENEW_LOAN_TAB, "TRUE");
            editModeMap.put(CamsConstants.EquipmentLoanOrReturnEditMode.DISPLAY_VIEW_ONLY_TAB, "TRUE");
        }
        else {
            editModeMap.put(CamsConstants.EquipmentLoanOrReturnEditMode.DISPLAY_RENEW_LOAN_TAB, "FALSE");
        }

        return editModeMap;
    }


    /**
     * @see org.kuali.core.document.authorization.TransactionalDocumentAuthorizerBase#getDocumentActionFlags(org.kuali.core.document.Document,
     *      org.kuali.core.bo.user.UniversalUser) This method determines if user can continue with transfer action or not, following
     *      conditions are checked to decide
     *      <li>Check if asset is active and not retired</li>
     *      <li>Find all pending documents associated with this asset, if any found disable the transfer action</li>
     */
    @Override
    public DocumentActionFlags getDocumentActionFlags(Document document, UniversalUser user) {
        DocumentActionFlags actionFlags = super.getDocumentActionFlags(document, user);
        EquipmentLoanOrReturnDocument equipmentLoanOrReturnDocument = (EquipmentLoanOrReturnDocument) document;
        // Disable the buttons, if asset is not lonable

        if (!SpringContext.getBean(EquipmentLoanOrReturnService.class).canBeLoaned(equipmentLoanOrReturnDocument)) {
            actionFlags.setCanAdHocRoute(false);
            actionFlags.setCanApprove(false);
            actionFlags.setCanBlanketApprove(false);
            actionFlags.setCanRoute(false);
            actionFlags.setCanSave(false);
        }

        if (ObjectUtils.isNotNull(equipmentLoanOrReturnDocument.getLoanReturnDate())) {
            actionFlags.setCanAdHocRoute(false);
            actionFlags.setCanApprove(false);
            actionFlags.setCanBlanketApprove(false);
            actionFlags.setCanClose(false);
            actionFlags.setCanCancel(false);
            actionFlags.setCanRoute(false);
            actionFlags.setCanSave(false);
        }

        return actionFlags;
    }


}
