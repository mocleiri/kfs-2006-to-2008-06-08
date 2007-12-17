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

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kfs.bo.Country;
import org.kuali.module.purap.document.PurchaseOrderDocument;

/**
 * Purchase Order Vendor Quote Business Object.
 * 
 * THIS CODE IS NOT USED IN RELEASE 2 BUT THE CODE WAS LEFT IN TO
 * FACILITATE TURNING IT BACK ON EARLY IN THE DEVELOPMENT CYCLE OF RELEASE 3.
 * 
 */
public class PurchaseOrderVendorQuote extends PersistableBusinessObjectBase {

    private String documentNumber;
    private Integer purchaseOrderVendorQuoteIdentifier;
    private Integer vendorHeaderGeneratedIdentifier;
    private Integer vendorDetailAssignedIdentifier;
    private String vendorName;
    private String vendorLine1Address;
    private String vendorLine2Address;
    private String vendorCityName;
    private String vendorStateCode;
    private String vendorPostalCode;
    private String vendorPhoneNumber;
    private String vendorFaxNumber;
    private String vendorEmailAddress;
    private String vendorAttentionName;
    private String purchaseOrderQuoteTransmitTypeCode;
    private Date purchaseOrderQuoteTransmitDate;
    private Date purchaseOrderQuotePriceExpirationDate;
    private String purchaseOrderQuoteStatusCode;
    private Date purchaseOrderQuoteAwardDate;
    private String purchaseOrderQuoteRankNumber;
    private String vendorCountryCode;
    private String vendorAddressInternationalProvinceName;
    private boolean isTransmitPrintDisplayed = false;

    private PurchaseOrderDocument purchaseOrder;
    private PurchaseOrderQuoteStatus purchaseOrderQuoteStatus;
    private Country vendorCountry;

    /**
     * Default constructor.
     */
    public PurchaseOrderVendorQuote() {

    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Integer getPurchaseOrderVendorQuoteIdentifier() {
        return purchaseOrderVendorQuoteIdentifier;
    }

    public Country getVendorCountry() {
        return vendorCountry;
    }

    public void setVendorCountry(Country vendorCountry) {
        this.vendorCountry = vendorCountry;
    }

    public void setPurchaseOrderVendorQuoteIdentifier(Integer purchaseOrderVendorQuoteIdentifier) {
        this.purchaseOrderVendorQuoteIdentifier = purchaseOrderVendorQuoteIdentifier;
    }

    public Integer getVendorHeaderGeneratedIdentifier() {
        return vendorHeaderGeneratedIdentifier;
    }

    public void setVendorHeaderGeneratedIdentifier(Integer vendorHeaderGeneratedIdentifier) {
        this.vendorHeaderGeneratedIdentifier = vendorHeaderGeneratedIdentifier;
    }

    public Integer getVendorDetailAssignedIdentifier() {
        return vendorDetailAssignedIdentifier;
    }

    public void setVendorDetailAssignedIdentifier(Integer vendorDetailAssignedIdentifier) {
        this.vendorDetailAssignedIdentifier = vendorDetailAssignedIdentifier;
    }
    
    public String getVendorAddressInternationalProvinceName() {
        return vendorAddressInternationalProvinceName;
    }

    public void setVendorAddressInternationalProvinceName(String vendorAddressInternationalProvinceName) {
        this.vendorAddressInternationalProvinceName = vendorAddressInternationalProvinceName;
    }

    public String getVendorNumber() {
        String vendorNumber = "";
        if (ObjectUtils.isNotNull(this.vendorHeaderGeneratedIdentifier)) {
            vendorNumber = this.vendorHeaderGeneratedIdentifier.toString();
        }
        if (ObjectUtils.isNotNull(this.vendorDetailAssignedIdentifier)) {
            vendorNumber += "-" + this.vendorDetailAssignedIdentifier.toString();
        }
        return vendorNumber;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorLine1Address() {
        return vendorLine1Address;
    }

    public void setVendorLine1Address(String vendorLine1Address) {
        this.vendorLine1Address = vendorLine1Address;
    }

    public String getVendorLine2Address() {
        return vendorLine2Address;
    }

    public void setVendorLine2Address(String vendorLine2Address) {
        this.vendorLine2Address = vendorLine2Address;
    }

    public String getVendorCityName() {
        return vendorCityName;
    }

    public void setVendorCityName(String vendorCityName) {
        this.vendorCityName = vendorCityName;
    }

    public String getVendorStateCode() {
        return vendorStateCode;
    }

    public void setVendorStateCode(String vendorStateCode) {
        this.vendorStateCode = vendorStateCode;
    }

    public String getVendorPostalCode() {
        return vendorPostalCode;
    }

    public void setVendorPostalCode(String vendorPostalCode) {
        this.vendorPostalCode = vendorPostalCode;
    }

    public String getVendorPhoneNumber() {
        return vendorPhoneNumber;
    }

    public void setVendorPhoneNumber(String vendorPhoneNumber) {
        this.vendorPhoneNumber = vendorPhoneNumber;
    }

    public String getVendorFaxNumber() {
        return vendorFaxNumber;
    }

    public void setVendorFaxNumber(String vendorFaxNumber) {
        this.vendorFaxNumber = vendorFaxNumber;
    }

    public String getVendorEmailAddress() {
        return vendorEmailAddress;
    }

    public void setVendorEmailAddress(String vendorEmailAddress) {
        this.vendorEmailAddress = vendorEmailAddress;
    }

    public String getVendorAttentionName() {
        return vendorAttentionName;
    }

    public void setVendorAttentionName(String vendorAttentionName) {
        this.vendorAttentionName = vendorAttentionName;
    }

    public String getPurchaseOrderQuoteTransmitTypeCode() {
        return purchaseOrderQuoteTransmitTypeCode;
    }

    public void setPurchaseOrderQuoteTransmitTypeCode(String purchaseOrderQuoteTransmitTypeCode) {
        this.purchaseOrderQuoteTransmitTypeCode = purchaseOrderQuoteTransmitTypeCode;
    }

    public Date getPurchaseOrderQuoteTransmitDate() {
        return purchaseOrderQuoteTransmitDate;
    }

    public void setPurchaseOrderQuoteTransmitDate(Date purchaseOrderQuoteTransmitDate) {
        this.purchaseOrderQuoteTransmitDate = purchaseOrderQuoteTransmitDate;
    }

    public Date getPurchaseOrderQuotePriceExpirationDate() {
        return purchaseOrderQuotePriceExpirationDate;
    }

    public void setPurchaseOrderQuotePriceExpirationDate(Date purchaseOrderQuotePriceExpirationDate) {
        this.purchaseOrderQuotePriceExpirationDate = purchaseOrderQuotePriceExpirationDate;
    }

    public String getPurchaseOrderQuoteStatusCode() {
        return purchaseOrderQuoteStatusCode;
    }

    public void setPurchaseOrderQuoteStatusCode(String purchaseOrderQuoteStatusCode) {
        this.purchaseOrderQuoteStatusCode = purchaseOrderQuoteStatusCode;
    }

    public Date getPurchaseOrderQuoteAwardDate() {
        return purchaseOrderQuoteAwardDate;
    }

    public void setPurchaseOrderQuoteAwardDate(Date purchaseOrderQuoteAwardDate) {
        this.purchaseOrderQuoteAwardDate = purchaseOrderQuoteAwardDate;
    }

    public String getPurchaseOrderQuoteRankNumber() {
        return purchaseOrderQuoteRankNumber;
    }

    public void setPurchaseOrderQuoteRankNumber(String purchaseOrderQuoteRankNumber) {
        this.purchaseOrderQuoteRankNumber = purchaseOrderQuoteRankNumber;
    }

    public PurchaseOrderDocument getPurchaseOrder() {
        return purchaseOrder;
    }

    /**
     * Sets the purchaseOrder attribute.
     * 
     * @param purchaseOrder The purchaseOrder to set.
     * @deprecated
     */
    public void setPurchaseOrder(PurchaseOrderDocument purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public PurchaseOrderQuoteStatus getPurchaseOrderQuoteStatus() {
        return purchaseOrderQuoteStatus;
    }

    /**
     * Sets the purchaseOrderQuoteStatus attribute.
     * 
     * @param purchaseOrderQuoteStatus The purchaseOrderQuoteStatus to set.
     * @deprecated
     */
    public void setPurchaseOrderQuoteStatus(PurchaseOrderQuoteStatus purchaseOrderQuoteStatus) {
        this.purchaseOrderQuoteStatus = purchaseOrderQuoteStatus;
    }

    public String getVendorCountryCode() {
        return vendorCountryCode;
    }

    public void setVendorCountryCode(String vendorCountryCode) {
        this.vendorCountryCode = vendorCountryCode;
    }

    public boolean isTransmitPrintDisplayed() {
        return isTransmitPrintDisplayed;
    }

    public void setTransmitPrintDisplayed(boolean isTransmitPrintDisplayed) {
        this.isTransmitPrintDisplayed = isTransmitPrintDisplayed;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("documentNumber", this.documentNumber);
        if (this.purchaseOrderVendorQuoteIdentifier != null) {
            m.put("purchaseOrderVendorQuoteIdentifier", this.purchaseOrderVendorQuoteIdentifier.toString());
        }
        return m;
    }

}
