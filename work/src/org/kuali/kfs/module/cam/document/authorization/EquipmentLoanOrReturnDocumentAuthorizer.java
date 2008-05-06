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

import org.apache.commons.lang.StringUtils;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.document.Document;
import org.kuali.core.document.authorization.DocumentActionFlags;
import org.kuali.core.document.authorization.TransactionalDocumentAuthorizerBase;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kfs.context.SpringContext;
import org.kuali.module.cams.CamsKeyConstants;
import org.kuali.module.cams.document.AssetTransferDocument;
import org.kuali.module.cams.document.EquipmentLoanOrReturnDocument;
import org.kuali.module.cams.rules.AssetTransferDocumentRule;
import org.kuali.module.cams.service.AssetTransferService;
import org.kuali.module.cams.web.struts.action.EquipmentLoanOrReturnAction;

/**
 * Uses defaults.
 */
public class EquipmentLoanOrReturnDocumentAuthorizer extends TransactionalDocumentAuthorizerBase {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(EquipmentLoanOrReturnDocumentAuthorizer.class);

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
        String tagNumber = equipmentLoanOrReturnDocument.getAsset().getCampusTagNumber();
//      LOG.info("EquipmentLoanOrReturnDocumentAuthorizer: " + tagNumber);
        // Disable the buttons, if asset is not tagged
        if (!StringUtils.isNotEmpty(tagNumber)) {
            GlobalVariables.getErrorMap().putError(AssetTransferDocumentRule.DOC_HEADER_PATH, CamsKeyConstants.Transfer.ERROR_ASSET_RETIRED_NOTRANSFER, equipmentLoanOrReturnDocument.getAsset().getCapitalAssetNumber().toString());
            actionFlags.setCanAdHocRoute(false);
            actionFlags.setCanApprove(false);
            actionFlags.setCanBlanketApprove(false);
            actionFlags.setCanRoute(false);
            actionFlags.setCanSave(false);
        }
        return actionFlags;
    }
    

}