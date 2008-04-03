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
package org.kuali.module.cams.maintenance;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.kuali.core.document.MaintenanceDocument;
import org.kuali.core.maintenance.KualiMaintainableImpl;
import org.kuali.core.maintenance.Maintainable;
import org.kuali.core.web.ui.Section;
import org.kuali.kfs.context.SpringContext;
import org.kuali.kfs.service.ParameterService;
import org.kuali.module.cams.CamsConstants;
import org.kuali.module.cams.bo.Asset;
import org.kuali.module.cams.service.AssetDispositionService;
import org.kuali.module.cams.service.AssetLocationService;
import org.kuali.module.cams.service.EquipmentLoanInfoService;
import org.kuali.module.cams.service.PaymentSummaryService;
import org.kuali.module.cams.service.RetirementInfoService;

/**
 * This class implements custom data preparation for displaying asset edit screen.
 */
public class AssetMaintainableImpl extends KualiMaintainableImpl implements Maintainable {
    private static ParameterService parameterService = SpringContext.getBean(ParameterService.class);
    private Asset newAsset;
    private Asset copyAsset;

    /**
     * @see org.kuali.core.maintenance.Maintainable#processAfterEdit(org.kuali.core.document.MaintenanceDocument, java.util.Map)
     * @param document Maintenance Document used for editing
     * @param parameters Parameters available
     */
    public void processAfterEdit(MaintenanceDocument document, Map parameters) {
        initializeAttributes(document);
        // Identifies the latest location information
        AssetLocationService assetlocationService = SpringContext.getBean(AssetLocationService.class);
        assetlocationService.setOffCampusLocation(copyAsset);
        assetlocationService.setOffCampusLocation(newAsset);

        // Calculates payment summary and depreciation summary based on available payment records
        PaymentSummaryService paymentSummaryService = SpringContext.getBean(PaymentSummaryService.class);
        paymentSummaryService.calculateAndSetPaymentSummary(copyAsset);
        paymentSummaryService.calculateAndSetPaymentSummary(newAsset);

        // Identifies the merge history and separation history based on asset disposition records
        AssetDispositionService assetDispService = SpringContext.getBean(AssetDispositionService.class);
        assetDispService.setAssetDispositionHistory(copyAsset);
        assetDispService.setAssetDispositionHistory(newAsset);

        // Finds out the latest retirement info, is asset is currently retired.
        RetirementInfoService retirementInfoService = SpringContext.getBean(RetirementInfoService.class);
        retirementInfoService.setRetirementInfo(copyAsset);
        retirementInfoService.setRetirementInfo(newAsset);

        // Finds out the latest equipment loan or return information if available
        EquipmentLoanInfoService equipmentLoanInfoService = SpringContext.getBean(EquipmentLoanInfoService.class);
        equipmentLoanInfoService.setEquipmentLoanInfo(copyAsset);
        equipmentLoanInfoService.setEquipmentLoanInfo(newAsset);

        super.processAfterEdit(document, parameters);
    }

    /**
     * Hide a few sections if this is a create new (fabrication request)
     * 
     * @see org.kuali.core.maintenance.KualiMaintainableImpl#refresh(java.lang.String, java.util.Map,
     *      org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    public List<Section> getCoreSections(Maintainable oldMaintainable) {
        List<Section> sections = super.getCoreSections(oldMaintainable);

        Asset asset = (Asset) getBusinessObject();
        if ((asset).getCapitalAssetNumber() == null) {
            // fabrication request asset creation. Hide sections that are only applicable to asset edit. For fields
            // that are to be hidden for asset edit, see AssetAuthorizer.getFieldAuthorizations
            for (Section section : sections) {
                if (CamsConstants.Asset.SECTION_ID_LAND_INFORMATION.equals(section.getSectionId()) || CamsConstants.Asset.SECTION_ID_PAYMENT_INFORMATION.equals(section.getSectionId()) || CamsConstants.Asset.SECTION_ID_DEPRECIATION_INFORMATION.equals(section.getSectionId()) || CamsConstants.Asset.SECTION_ID_HISTORY.equals(section.getSectionId()) || CamsConstants.Asset.SECTION_ID_RETIREMENT_INFORMATION.equals(section.getSectionId()) || CamsConstants.Asset.SECTION_ID_EQUIPMENT_LOAN_INFORMATION.equals(section.getSectionId()) || CamsConstants.Asset.SECTION_ID_WARRENTY.equals(section.getSectionId()) || CamsConstants.Asset.SECTION_ID_REPAIR_HISTORY.equals(section.getSectionId()) || CamsConstants.Asset.SECTION_ID_COMPONENTS.equals(section.getSectionId())) {
                    section.setHidden(true);
                }

            }
        }
        else {
            // asset edit. Hide sections that are only applicable to fabrication request
            for (Section section : sections) {
                if (CamsConstants.Asset.SECTION_ID_FABRICATION_INFORMATION.equals(section.getSectionId())) {
                    section.setHidden(true);
                }
                // if asset is retired
                if (CamsConstants.Asset.SECTION_ID_RETIREMENT_INFORMATION.equals(section.getSectionId()) && !parameterService.getParameterValues(Asset.class, CamsConstants.Parameters.RETIRED_STATUS_CODES).contains(asset.getInventoryStatusCode())) {
                    section.setHidden(true);
                }
            }
        }


        return sections;
    }

    /**
     * This method gets old and new maintainable objects and creates convenience handles to them
     * 
     * @param document Asset Edit Document
     */
    private void initializeAttributes(MaintenanceDocument document) {
        if (newAsset == null) {
            newAsset = (Asset) document.getNewMaintainableObject().getBusinessObject();
        }
        if (copyAsset == null) {
            copyAsset = (Asset) document.getOldMaintainableObject().getBusinessObject();
        }
    }
}
