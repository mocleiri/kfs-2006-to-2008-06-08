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
package org.kuali.module.cams.service;

import org.kuali.module.cams.bo.Asset;
import org.kuali.module.cams.bo.AssetDepreciationConvention;
import org.kuali.module.cams.bo.AssetType;

public interface AssetDateService {

    /**
     * Last inventory Date shall be updated whenever changes made to location or tag number
     */
    void checkAndUpdateLastInventoryDate(Asset copyAsset, Asset newAsset);

    /**
     * In Asset Edit, DepreciationDate shall be updated whenever In-service Date changed.
     */
    void checkAndUpdateDepreciationDate(Asset copyAsset, Asset newAsset);

    /**
     * This method computes the depreciation date based on input parameters
     * 
     * @param assetType Asset Type Object
     * @param depreciationConvention Depreciation Convention for the asset
     * @param inServiceDate Current in-service date value
     * @return Computed Asset Depreciation Date
     */
    java.sql.Date computeDepreciationDate(AssetType assetType, AssetDepreciationConvention depreciationConvention, java.sql.Date inServiceDate);

}