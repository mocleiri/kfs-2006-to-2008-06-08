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
package org.kuali.module.gl.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.TransientBusinessObjectBase;
import org.kuali.core.util.KualiDecimal;
import org.kuali.module.gl.util.OJBUtility;

/**
 * This class represents the transient balance inquiry attributes which is typically used as a "dummy business object"
 */
public class TransientBalanceInquiryAttributes extends TransientBusinessObjectBase {

    private String pendingEntryOption;
    private String consolidationOption;
    private String amountViewOption;
    private String costShareOption;
    private String linkButtonOption;
    private KualiDecimal genericAmount;
    private String genericText;

    private String consolidationObjectCode;
    private String reportingSortCode;
    private String consolidationReportingSortCode;
    private String levelObjectCode;


    /**
     * Constructs a DummyBusinessObject.java.
     */
    public TransientBalanceInquiryAttributes() {
        super();
        this.pendingEntryOption = "";
        this.consolidationOption = "";
        this.genericAmount = KualiDecimal.ZERO;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        return (LinkedHashMap) OJBUtility.buildPropertyMap(this);
    }

    /**
     * Gets the pendingEntryOption attribute.
     * 
     * @return Returns the pendingEntryOption.
     */
    public String getPendingEntryOption() {
        return pendingEntryOption;
    }

    /**
     * Sets the pendingEntryOption attribute value.
     * 
     * @param pendingEntryOption The pendingEntryOption to set.
     */
    public void setPendingEntryOption(String pendingEntryOption) {
        this.pendingEntryOption = pendingEntryOption;
    }

    /**
     * Gets the consolidationOption attribute.
     * 
     * @return Returns the consolidationOption.
     */
    public String getConsolidationOption() {
        return consolidationOption;
    }

    /**
     * Sets the consolidationOption attribute value.
     * 
     * @param consolidationOption The consolidationOption to set.
     */
    public void setConsolidationOption(String consolidationOption) {
        this.consolidationOption = consolidationOption;
    }

    /**
     * Gets the linkButtonOption attribute.
     * 
     * @return Returns the linkButtonOption.
     */
    public String getLinkButtonOption() {
        return linkButtonOption;
    }

    /**
     * Sets the linkButtonOption attribute value.
     * 
     * @param linkButtonOption The linkButtonOption to set.
     */
    public void setLinkButtonOption(String linkButtonOption) {
        this.linkButtonOption = linkButtonOption;
    }

    /**
     * Gets the genericAmount attribute.
     * 
     * @return Returns the genericAmount.
     */
    public KualiDecimal getGenericAmount() {
        return genericAmount;
    }

    /**
     * Sets the genericAmount attribute value.
     * 
     * @param genericAmount The genericDecimal to set.
     */
    public void setGenericAmount(KualiDecimal genericAmount) {
        this.genericAmount = genericAmount;
    }

    /**
     * Gets the amountViewOption attribute.
     * 
     * @return Returns the amountViewOption.
     */
    public String getAmountViewOption() {
        return amountViewOption;
    }

    /**
     * Sets the amountViewOption attribute value.
     * 
     * @param amountViewOption The amountViewOption to set.
     */
    public void setAmountViewOption(String amountViewOption) {
        this.amountViewOption = amountViewOption;
    }

    /**
     * Gets the costShareOption attribute.
     * 
     * @return Returns the costShareOption.
     */
    public String getCostShareOption() {
        return costShareOption;
    }

    /**
     * Sets the costShareOption attribute value.
     * 
     * @param costShareOption The costShareOption to set.
     */
    public void setCostShareOption(String costShareOption) {
        this.costShareOption = costShareOption;
    }

    /**
     * Gets the consolidationObjectCode attribute.
     * 
     * @return Returns the consolidationObjectCode.
     */
    public String getConsolidationObjectCode() {
        return consolidationObjectCode;
    }

    /**
     * Sets the consolidationObjectCode attribute value.
     * 
     * @param consolidationObjectCode The consolidationObjectCode to set.
     */
    public void setConsolidationObjectCode(String consolidationObjectCode) {
        this.consolidationObjectCode = consolidationObjectCode;
    }

    /**
     * Gets the consolidationReportingSortCode attribute.
     * 
     * @return Returns the consolidationReportingSortCode.
     */
    public String getConsolidationReportingSortCode() {
        return consolidationReportingSortCode;
    }

    /**
     * Sets the consolidationReportingSortCode attribute value.
     * 
     * @param consolidationReportingSortCode The consolidationReportingSortCode to set.
     */
    public void setConsolidationReportingSortCode(String consolidationReportingSortCode) {
        this.consolidationReportingSortCode = consolidationReportingSortCode;
    }

    /**
     * Gets the reportingSortCode attribute.
     * 
     * @return Returns the reportingSortCode.
     */
    public String getReportingSortCode() {
        return reportingSortCode;
    }

    /**
     * Sets the reportingSortCode attribute value.
     * 
     * @param reportingSortCode The reportingSortCode to set.
     */
    public void setReportingSortCode(String reportingSortCode) {
        this.reportingSortCode = reportingSortCode;
    }

    /**
     * Gets the levelObjectCode attribute.
     * 
     * @return Returns the levelObjectCode.
     */
    public String getLevelObjectCode() {
        return levelObjectCode;
    }

    /**
     * Sets the levelObjectCode attribute value.
     * 
     * @param levelObjectCode The levelObjectCode to set.
     */
    public void setLevelObjectCode(String levelObjectCode) {
        this.levelObjectCode = levelObjectCode;
    }

    /**
     * Gets the genericText attribute.
     * 
     * @return Returns the genericText.
     */
    public String getGenericText() {
        return genericText;
    }

    /**
     * Sets the genericText attribute value.
     * 
     * @param genericText The genericText to set.
     */
    public void setGenericText(String genericText) {
        this.genericText = genericText;
    }
}
