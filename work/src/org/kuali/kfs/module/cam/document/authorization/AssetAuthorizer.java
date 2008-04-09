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

import static org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase.MAINTAINABLE_ERROR_PREFIX;
import static org.kuali.module.cams.CamsPropertyConstants.Asset.ASSET_DATE_OF_SERVICE;
import static org.kuali.module.cams.CamsPropertyConstants.Asset.ASSET_INVENTORY_STATUS;
import static org.kuali.module.cams.CamsPropertyConstants.Asset.ORGANIZATION_OWNER_ACCOUNT_NUMBER;
import static org.kuali.module.cams.CamsPropertyConstants.Asset.VENDOR_NAME;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.document.Document;
import org.kuali.core.document.MaintenanceDocument;
import org.kuali.core.document.authorization.DocumentActionFlags;
import org.kuali.core.document.authorization.MaintenanceDocumentAuthorizations;
import org.kuali.core.document.authorization.MaintenanceDocumentAuthorizerBase;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kfs.context.SpringContext;
import org.kuali.kfs.service.ParameterService;
import org.kuali.module.cams.CamsConstants;
import org.kuali.module.cams.CamsKeyConstants;
import org.kuali.module.cams.CamsPropertyConstants;
import org.kuali.module.cams.bo.Asset;
import org.kuali.module.cams.service.AssetService;

/**
 * AssetAuthorizer for Asset edit.
 */
public class AssetAuthorizer extends MaintenanceDocumentAuthorizerBase {

    private static ParameterService parameterService = SpringContext.getBean(ParameterService.class);
    private static AssetService assetService = SpringContext.getBean(AssetService.class);


    /**
     * Returns the set of authorization restrictions (if any) that apply to this Asset in this context.
     * 
     * @param document
     * @param user
     * @return a new set of {@link MaintenanceDocumentAuthorizations} that marks certain fields as necessary
     */
    public MaintenanceDocumentAuthorizations getFieldAuthorizations(MaintenanceDocument document, UniversalUser user) {
        MaintenanceDocumentAuthorizations auths = super.getFieldAuthorizations(document, user);
        Asset asset = (Asset) document.getNewMaintainableObject().getBusinessObject();

        if (asset.getCapitalAssetNumber() == null) {
            // fabrication request asset creation. Hide fields that are only applicable to asset fabrication. For
            // sections that are to be hidden on asset fabrication see AssetMaintainableImpl.getCoreSections
            hideFields(auths, CamsConstants.Asset.EDIT_DETAIL_INFORMATION_FIELDS);
            hideFields(auths, CamsConstants.Asset.EDIT_ORGANIZATION_INFORMATION_FIELDS);
        }
        else {
            // Asset edit: workgroup authorization
            if (user.isMember(CamsConstants.Workgroups.WORKGROUP_CM_ASSET_MERGE_SEPARATE_USERS)) {
                hideFields(auths, parameterService.getParameterValues(Asset.class, CamsConstants.Parameters.MERGE_SEPARATE_VIEWABLE_FIELDS));
            }
            else if (!user.isMember(CamsConstants.Workgroups.WORKGROUP_CM_SUPER_USERS)) {
                // If departmental user
                hideFields(auths, parameterService.getParameterValues(Asset.class, CamsConstants.Parameters.DEPARTMENT_VIEWABLE_FIELDS));
            }
        }

        hidePaymentSequence(auths, asset);
        if (!CamsConstants.RETIREMENT_REASON_CODE_M.equals(asset.getRetirementReasonCode())) {
            // hide merge target capital asset number
            auths.addHiddenAuthField(CamsPropertyConstants.Asset.RETIREMENT_INFO_MERGED_TARGET);

        }

        setFieldsReadOnlyAccessMode(auths, asset, user);
        return auths;
    }

    /**
     * Check and set view only for campusTagNumber,assetTypeCode and assetDescription
     * 
     * @param auths
     * @param asset
     */
    private void setFieldsReadOnlyAccessMode(MaintenanceDocumentAuthorizations auths, Asset asset, UniversalUser user) {

        if (ObjectUtils.isNotNull(asset.getCampusTagNumber()) && !user.isMember(CamsConstants.Workgroups.WORKGROUP_CM_SUPER_USERS) && !user.isMember(CamsConstants.Workgroups.WORKGROUP_CM_ASSET_MERGE_SEPARATE_USERS)) {
            // Set the Tag Number as view only if the asset is tagged and the user is none of CAMS Administration group nor
            // CM_ASSET_MERGE_SEPARATE_USERS
            auths.addReadonlyAuthField(CamsPropertyConstants.Asset.CAMPUS_TAG_NUMBER);


            if (assetService.isAssetTaggedInPriorFiscalYear(asset)) {
                // Set the Asset Type Code & Asset Description if it was tagged in prior FY and the user is none of CAMS
                // Administration group nor CM_ASSET_MERGE_SEPARATE_USERS
                auths.addReadonlyAuthField(CamsPropertyConstants.Asset.CAPITAL_ASSET_TYPE_CODE);
                auths.addReadonlyAuthField(CamsPropertyConstants.Asset.CAPITAL_ASSET_DESCRIPTION);
            }
        }
    }

    private void hidePaymentSequence(MaintenanceDocumentAuthorizations auths, Asset asset) {
        int size = asset.getAssetPayments().size();
        for (int i = 0; i < size; i++) {
            auths.addHiddenAuthField(CamsPropertyConstants.Asset.ASSET_PAYMENTS + "[" + i + "]." + CamsPropertyConstants.AssetPayment.PAYMENT_SEQ_NUMBER);
        }
    }


    private void hideFields(MaintenanceDocumentAuthorizations auths, List<String> readOnlyFields) {
        for (String field : readOnlyFields) {
            auths.addHiddenAuthField(field);
        }
    }

    private void hideFields(MaintenanceDocumentAuthorizations auths, String[] readOnlyFields) {
        for (String field : readOnlyFields) {
            auths.addHiddenAuthField(field);
        }
    }

    @Override
    public DocumentActionFlags getDocumentActionFlags(Document document, UniversalUser user) {
        DocumentActionFlags actionFlags = super.getDocumentActionFlags(document, user);

        // If asset is retired then deny "Save", "Submit" and "Approve"
        Asset asset = (Asset) document.getDocumentBusinessObject();
        if (assetService.isAssetRetired(asset)) {
            actionFlags.setCanAdHocRoute(false);
            actionFlags.setCanApprove(false);
            actionFlags.setCanBlanketApprove(false);
            actionFlags.setCanRoute(false);
            actionFlags.setCanSave(false);
            GlobalVariables.getErrorMap().putError(MAINTAINABLE_ERROR_PREFIX + ASSET_INVENTORY_STATUS, CamsKeyConstants.ERROR_ASSET_RETIRED_NOEDIT, new String[] {});
        }
        return actionFlags;
    }

}
