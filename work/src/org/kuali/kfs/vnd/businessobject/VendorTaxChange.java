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

package org.kuali.module.vendor.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.service.UniversalUserService;
import org.kuali.kfs.context.SpringContext;

/**
 * Records any changes to a Vendor's Tax Number or Type. Not shown on the screen.
 */
public class VendorTaxChange extends PersistableBusinessObjectBase {

    private Integer vendorTaxChangeGeneratedIdentifier;
    private Integer vendorHeaderGeneratedIdentifier;
    private Date vendorTaxChangeDate;
    private String vendorPreviousTaxNumber;
    private String vendorPreviousTaxTypeCode;
    private String vendorTaxChangePersonIdentifier;

    private UniversalUser vendorTaxChangePerson;
    private VendorHeader vendorHeader;

    /**
     * Default constructor.
     */
    public VendorTaxChange() {
    }

    /**
     * Constructs a VendorTaxChange.
     * 
     * @param vndrHdrGenId The generated Id of the Vendor Header
     * @param taxChangeDate The date of this change
     * @param prevTaxNum The tax number previously
     * @param prevTaxTypeCode The tax type previously
     * @param taxChangePersonId The Id of the user who is making this change
     */
    public VendorTaxChange(Integer vndrHdrGenId, Date taxChangeDate, String prevTaxNum, String prevTaxTypeCode, String taxChangePersonId) {
        this.vendorHeaderGeneratedIdentifier = vndrHdrGenId;
        this.vendorTaxChangeDate = taxChangeDate;
        this.vendorPreviousTaxNumber = prevTaxNum;
        this.vendorPreviousTaxTypeCode = prevTaxTypeCode;
        this.vendorTaxChangePersonIdentifier = taxChangePersonId;
    }

    public Integer getVendorTaxChangeGeneratedIdentifier() {
        return vendorTaxChangeGeneratedIdentifier;
    }

    public void setVendorTaxChangeGeneratedIdentifier(Integer vendorTaxChangeGeneratedIdentifier) {
        this.vendorTaxChangeGeneratedIdentifier = vendorTaxChangeGeneratedIdentifier;
    }

    public Integer getVendorHeaderGeneratedIdentifier() {
        return vendorHeaderGeneratedIdentifier;
    }

    public void setVendorHeaderGeneratedIdentifier(Integer vendorHeaderGeneratedIdentifier) {
        this.vendorHeaderGeneratedIdentifier = vendorHeaderGeneratedIdentifier;
    }

    public Date getVendorTaxChangeDate() {
        return vendorTaxChangeDate;
    }

    public void setVendorTaxChangeDate(Date vendorTaxChangeDate) {
        this.vendorTaxChangeDate = vendorTaxChangeDate;
    }

    public String getVendorPreviousTaxNumber() {
        return vendorPreviousTaxNumber;
    }

    public void setVendorPreviousTaxNumber(String vendorPreviousTaxNumber) {
        this.vendorPreviousTaxNumber = vendorPreviousTaxNumber;
    }

    public String getVendorPreviousTaxTypeCode() {
        return vendorPreviousTaxTypeCode;
    }

    public void setVendorPreviousTaxTypeCode(String vendorPreviousTaxTypeCode) {
        this.vendorPreviousTaxTypeCode = vendorPreviousTaxTypeCode;
    }

    public String getVendorTaxChangePersonIdentifier() {
        return vendorTaxChangePersonIdentifier;
    }

    public void setVendorTaxChangePersonIdentifier(String vendorTaxChangePersonIdentifier) {
        this.vendorTaxChangePersonIdentifier = vendorTaxChangePersonIdentifier;
    }

    public UniversalUser getVendorTaxChangePerson() {
        vendorTaxChangePerson = SpringContext.getBean(UniversalUserService.class).updateUniversalUserIfNecessary(vendorTaxChangePersonIdentifier, vendorTaxChangePerson);
        return vendorTaxChangePerson;
    }

    /**
     * Sets the vendorTaxChangePerson attribute.
     * 
     * @param vendorTaxChangePerson The vendorTaxChangePerson to set.
     * @deprecated
     */
    public void setVendorTaxChangePerson(UniversalUser vendorTaxChangePerson) {
        this.vendorTaxChangePerson = vendorTaxChangePerson;
    }

    public VendorHeader getVendorHeader() {
        return vendorHeader;
    }

    /**
     * Sets the vendorHeader attribute value.
     * 
     * @param vendorHeader The vendorHeader to set.
     * @deprecated
     */
    public void setVendorHeader(VendorHeader vendorHeader) {
        this.vendorHeader = vendorHeader;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();
        if (this.vendorTaxChangeGeneratedIdentifier != null) {
            m.put("vendorTaxChangeGeneratedIdentifier", this.vendorTaxChangeGeneratedIdentifier.toString());
        }
        return m;
    }

}