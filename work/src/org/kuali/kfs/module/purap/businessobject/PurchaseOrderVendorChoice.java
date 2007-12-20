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

package org.kuali.module.purap.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;

/**
 * Purchase Order Vendor Choice Business Object.
 */
public class PurchaseOrderVendorChoice extends PersistableBusinessObjectBase {

    private String purchaseOrderVendorChoiceCode;
    private String purchaseOrderVendorChoiceDescription;
    private boolean active;

    /**
     * Default constructor.
     */
    public PurchaseOrderVendorChoice() {

    }

    public String getPurchaseOrderVendorChoiceCode() {
        return purchaseOrderVendorChoiceCode;
    }

    public void setPurchaseOrderVendorChoiceCode(String purchaseOrderVendorChoiceCode) {
        this.purchaseOrderVendorChoiceCode = purchaseOrderVendorChoiceCode;
    }

    public String getPurchaseOrderVendorChoiceDescription() {
        return purchaseOrderVendorChoiceDescription;
    }

    public void setPurchaseOrderVendorChoiceDescription(String purchaseOrderVendorChoiceDescription) {
        this.purchaseOrderVendorChoiceDescription = purchaseOrderVendorChoiceDescription;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("purchaseOrderVendorChoiceCode", this.purchaseOrderVendorChoiceCode);
        return m;
    }

}