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

package org.kuali.module.ar.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kfs.bo.State;
import org.kuali.module.chart.bo.Chart;
import org.kuali.module.chart.bo.Org;

/**
 * 
 */
public class OrgOptions extends PersistableBusinessObjectBase {

    private String chartOfAccountsCode;
    private String organizationCode;
    private String processingFinChartOfAcctCd;
    private String processingOrganizationCode;
    private boolean orgCreditCardOptionIndicator;
    private String acctRcvbPrintInvoiceInd;
    private String organizationPaymentTermsText;
    private String organizationMessageText;
    private String orgRemitToAddressName;
    private String orgRemitToLine1StreetAddr;
    private String orgRemitToLine2StreetAddr;
    private String organizationRemitToCityName;
    private String organizationRemitToStateCode;
    private String organizationRemitToZipCode;
    private boolean organizationLateFeeIndicator;
    private KualiDecimal orgLatePaymentChargeAmt;
    private boolean acctReceivableAutoPayInd;
    private String organizationPhoneNumber;
    private String organization800PhoneNumber;
    private String organizationFaxNumber;
    private String universityName;
    private String orgCheckPayableToName;

    private State organizationRemitToState;
    private Chart chart;
    private Chart processingFinChartOfAcct;
    private Org org;
    private Org processingOrg;


    /**
     * Default constructor.
     */
    public OrgOptions() {

    }

    /**
     * Gets the chartOfAccountsCode attribute.
     * 
     * @return Returns the chartOfAccountsCode
     * 
     */
    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }

    /**
     * Sets the chartOfAccountsCode attribute.
     * 
     * @param chartOfAccountsCode The chartOfAccountsCode to set.
     * 
     */
    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }


    /**
     * Gets the organizationCode attribute.
     * 
     * @return Returns the organizationCode
     * 
     */
    public String getOrganizationCode() {
        return organizationCode;
    }

    /**
     * Sets the organizationCode attribute.
     * 
     * @param organizationCode The organizationCode to set.
     * 
     */
    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }


    /**
     * Gets the processingFinChartOfAcctCd attribute.
     * 
     * @return Returns the processingFinChartOfAcctCd
     * 
     */
    public String getProcessingFinChartOfAcctCd() {
        return processingFinChartOfAcctCd;
    }

    /**
     * Sets the processingFinChartOfAcctCd attribute.
     * 
     * @param processingFinChartOfAcctCd The processingFinChartOfAcctCd to set.
     * 
     */
    public void setProcessingFinChartOfAcctCd(String processingFinChartOfAcctCd) {
        this.processingFinChartOfAcctCd = processingFinChartOfAcctCd;
    }


    /**
     * Gets the processingOrganizationCode attribute.
     * 
     * @return Returns the processingOrganizationCode
     * 
     */
    public String getProcessingOrganizationCode() {
        return processingOrganizationCode;
    }

    /**
     * Sets the processingOrganizationCode attribute.
     * 
     * @param processingOrganizationCode The processingOrganizationCode to set.
     * 
     */
    public void setProcessingOrganizationCode(String processingOrganizationCode) {
        this.processingOrganizationCode = processingOrganizationCode;
    }


    /**
     * Gets the orgCreditCardOptionIndicator attribute.
     * 
     * @return Returns the orgCreditCardOptionIndicator
     * 
     */
    public boolean isOrgCreditCardOptionIndicator() {
        return orgCreditCardOptionIndicator;
    }


    /**
     * Sets the orgCreditCardOptionIndicator attribute.
     * 
     * @param orgCreditCardOptionIndicator The orgCreditCardOptionIndicator to set.
     * 
     */
    public void setOrgCreditCardOptionIndicator(boolean orgCreditCardOptionIndicator) {
        this.orgCreditCardOptionIndicator = orgCreditCardOptionIndicator;
    }


    /**
     * Gets the acctRcvbPrintInvoiceInd attribute.
     * 
     * @return Returns the acctRcvbPrintInvoiceInd
     * 
     */
    public String getAcctRcvbPrintInvoiceInd() {
        return acctRcvbPrintInvoiceInd;
    }

    /**
     * Sets the acctRcvbPrintInvoiceInd attribute.
     * 
     * @param acctRcvbPrintInvoiceInd The acctRcvbPrintInvoiceInd to set.
     * 
     */
    public void setAcctRcvbPrintInvoiceInd(String acctRcvbPrintInvoiceInd) {
        this.acctRcvbPrintInvoiceInd = acctRcvbPrintInvoiceInd;
    }


    /**
     * Gets the organizationPaymentTermsText attribute.
     * 
     * @return Returns the organizationPaymentTermsText
     * 
     */
    public String getOrganizationPaymentTermsText() {
        return organizationPaymentTermsText;
    }

    /**
     * Sets the organizationPaymentTermsText attribute.
     * 
     * @param organizationPaymentTermsText The organizationPaymentTermsText to set.
     * 
     */
    public void setOrganizationPaymentTermsText(String organizationPaymentTermsText) {
        this.organizationPaymentTermsText = organizationPaymentTermsText;
    }


    /**
     * Gets the organizationMessageText attribute.
     * 
     * @return Returns the organizationMessageText
     * 
     */
    public String getOrganizationMessageText() {
        return organizationMessageText;
    }

    /**
     * Sets the organizationMessageText attribute.
     * 
     * @param organizationMessageText The organizationMessageText to set.
     * 
     */
    public void setOrganizationMessageText(String organizationMessageText) {
        this.organizationMessageText = organizationMessageText;
    }


    /**
     * Gets the orgRemitToAddressName attribute.
     * 
     * @return Returns the orgRemitToAddressName
     * 
     */
    public String getOrgRemitToAddressName() {
        return orgRemitToAddressName;
    }

    /**
     * Sets the orgRemitToAddressName attribute.
     * 
     * @param orgRemitToAddressName The orgRemitToAddressName to set.
     * 
     */
    public void setOrgRemitToAddressName(String orgRemitToAddressName) {
        this.orgRemitToAddressName = orgRemitToAddressName;
    }


    /**
     * Gets the orgRemitToLine1StreetAddr attribute.
     * 
     * @return Returns the orgRemitToLine1StreetAddr
     * 
     */
    public String getOrgRemitToLine1StreetAddr() {
        return orgRemitToLine1StreetAddr;
    }

    /**
     * Sets the orgRemitToLine1StreetAddr attribute.
     * 
     * @param orgRemitToLine1StreetAddr The orgRemitToLine1StreetAddr to set.
     * 
     */
    public void setOrgRemitToLine1StreetAddr(String orgRemitToLine1StreetAddr) {
        this.orgRemitToLine1StreetAddr = orgRemitToLine1StreetAddr;
    }


    /**
     * Gets the orgRemitToLine2StreetAddr attribute.
     * 
     * @return Returns the orgRemitToLine2StreetAddr
     * 
     */
    public String getOrgRemitToLine2StreetAddr() {
        return orgRemitToLine2StreetAddr;
    }

    /**
     * Sets the orgRemitToLine2StreetAddr attribute.
     * 
     * @param orgRemitToLine2StreetAddr The orgRemitToLine2StreetAddr to set.
     * 
     */
    public void setOrgRemitToLine2StreetAddr(String orgRemitToLine2StreetAddr) {
        this.orgRemitToLine2StreetAddr = orgRemitToLine2StreetAddr;
    }


    /**
     * Gets the organizationRemitToCityName attribute.
     * 
     * @return Returns the organizationRemitToCityName
     * 
     */
    public String getOrganizationRemitToCityName() {
        return organizationRemitToCityName;
    }

    /**
     * Sets the organizationRemitToCityName attribute.
     * 
     * @param organizationRemitToCityName The organizationRemitToCityName to set.
     * 
     */
    public void setOrganizationRemitToCityName(String organizationRemitToCityName) {
        this.organizationRemitToCityName = organizationRemitToCityName;
    }


    /**
     * Gets the organizationRemitToStateCode attribute.
     * 
     * @return Returns the organizationRemitToStateCode
     * 
     */
    public String getOrganizationRemitToStateCode() {
        return organizationRemitToStateCode;
    }

    /**
     * Sets the organizationRemitToStateCode attribute.
     * 
     * @param organizationRemitToStateCode The organizationRemitToStateCode to set.
     * 
     */
    public void setOrganizationRemitToStateCode(String organizationRemitToStateCode) {
        this.organizationRemitToStateCode = organizationRemitToStateCode;
    }


    /**
     * Gets the organizationRemitToZipCode attribute.
     * 
     * @return Returns the organizationRemitToZipCode
     * 
     */
    public String getOrganizationRemitToZipCode() {
        return organizationRemitToZipCode;
    }

    /**
     * Sets the organizationRemitToZipCode attribute.
     * 
     * @param organizationRemitToZipCode The organizationRemitToZipCode to set.
     * 
     */
    public void setOrganizationRemitToZipCode(String organizationRemitToZipCode) {
        this.organizationRemitToZipCode = organizationRemitToZipCode;
    }


    /**
     * Gets the organizationLateFeeIndicator attribute.
     * 
     * @return Returns the organizationLateFeeIndicator
     * 
     */
    public boolean isOrganizationLateFeeIndicator() {
        return organizationLateFeeIndicator;
    }


    /**
     * Sets the organizationLateFeeIndicator attribute.
     * 
     * @param organizationLateFeeIndicator The organizationLateFeeIndicator to set.
     * 
     */
    public void setOrganizationLateFeeIndicator(boolean organizationLateFeeIndicator) {
        this.organizationLateFeeIndicator = organizationLateFeeIndicator;
    }


    /**
     * Gets the orgLatePaymentChargeAmt attribute.
     * 
     * @return Returns the orgLatePaymentChargeAmt
     * 
     */
    public KualiDecimal getOrgLatePaymentChargeAmt() {
        return orgLatePaymentChargeAmt;
    }

    /**
     * Sets the orgLatePaymentChargeAmt attribute.
     * 
     * @param orgLatePaymentChargeAmt The orgLatePaymentChargeAmt to set.
     * 
     */
    public void setOrgLatePaymentChargeAmt(KualiDecimal orgLatePaymentChargeAmt) {
        this.orgLatePaymentChargeAmt = orgLatePaymentChargeAmt;
    }


    /**
     * Gets the acctReceivableAutoPayInd attribute.
     * 
     * @return Returns the acctReceivableAutoPayInd
     * 
     */
    public boolean isAcctReceivableAutoPayInd() {
        return acctReceivableAutoPayInd;
    }


    /**
     * Sets the acctReceivableAutoPayInd attribute.
     * 
     * @param acctReceivableAutoPayInd The acctReceivableAutoPayInd to set.
     * 
     */
    public void setAcctReceivableAutoPayInd(boolean acctReceivableAutoPayInd) {
        this.acctReceivableAutoPayInd = acctReceivableAutoPayInd;
    }


    /**
     * Gets the organizationPhoneNumber attribute.
     * 
     * @return Returns the organizationPhoneNumber
     * 
     */
    public String getOrganizationPhoneNumber() {
        return organizationPhoneNumber;
    }

    /**
     * Sets the organizationPhoneNumber attribute.
     * 
     * @param organizationPhoneNumber The organizationPhoneNumber to set.
     * 
     */
    public void setOrganizationPhoneNumber(String organizationPhoneNumber) {
        this.organizationPhoneNumber = organizationPhoneNumber;
    }


    /**
     * Gets the organization800PhoneNumber attribute.
     * 
     * @return Returns the organization800PhoneNumber
     * 
     */
    public String getOrganization800PhoneNumber() {
        return organization800PhoneNumber;
    }

    /**
     * Sets the organization800PhoneNumber attribute.
     * 
     * @param organization800PhoneNumber The organization800PhoneNumber to set.
     * 
     */
    public void setOrganization800PhoneNumber(String organization800PhoneNumber) {
        this.organization800PhoneNumber = organization800PhoneNumber;
    }


    /**
     * Gets the organizationFaxNumber attribute.
     * 
     * @return Returns the organizationFaxNumber
     * 
     */
    public String getOrganizationFaxNumber() {
        return organizationFaxNumber;
    }

    /**
     * Sets the organizationFaxNumber attribute.
     * 
     * @param organizationFaxNumber The organizationFaxNumber to set.
     * 
     */
    public void setOrganizationFaxNumber(String organizationFaxNumber) {
        this.organizationFaxNumber = organizationFaxNumber;
    }


    /**
     * Gets the universityName attribute.
     * 
     * @return Returns the universityName
     * 
     */
    public String getUniversityName() {
        return universityName;
    }

    /**
     * Sets the universityName attribute.
     * 
     * @param universityName The universityName to set.
     * 
     */
    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }


    /**
     * Gets the orgCheckPayableToName attribute.
     * 
     * @return Returns the orgCheckPayableToName
     * 
     */
    public String getOrgCheckPayableToName() {
        return orgCheckPayableToName;
    }

    /**
     * Sets the orgCheckPayableToName attribute.
     * 
     * @param orgCheckPayableToName The orgCheckPayableToName to set.
     * 
     */
    public void setOrgCheckPayableToName(String orgCheckPayableToName) {
        this.orgCheckPayableToName = orgCheckPayableToName;
    }


    /**
     * Gets the chart attribute.
     * 
     * @return Returns the chart
     * 
     */
    public Chart getChart() {
        return chart;
    }

    /**
     * Sets the chart attribute.
     * 
     * @param chart The chart to set.
     * @deprecated
     */
    public void setChart(Chart chart) {
        this.chart = chart;
    }

    /**
     * Gets the processingFinChartOfAcct attribute.
     * 
     * @return Returns the processingFinChartOfAcct
     * 
     */
    public Chart getProcessingFinChartOfAcct() {
        return processingFinChartOfAcct;
    }

    /**
     * Sets the processingFinChartOfAcct attribute.
     * 
     * @param processingFinChartOfAcct The processingFinChartOfAcct to set.
     * @deprecated
     */
    public void setProcessingFinChartOfAcct(Chart processingFinChartOfAcct) {
        this.processingFinChartOfAcct = processingFinChartOfAcct;
    }

    /**
     * Gets the org attribute.
     * 
     * @return Returns the org
     * 
     */
    public Org getOrg() {
        return org;
    }

    /**
     * Sets the org attribute.
     * 
     * @param org The org to set.
     * @deprecated
     */
    public void setOrg(Org org) {
        this.org = org;
    }

    /**
     * Gets the organizationRemitToState attribute.
     * 
     * @return Returns the organizationRemitToState.
     */
    public State getOrganizationRemitToState() {
        return organizationRemitToState;
    }


    /**
     * Gets the processingOrg attribute.
     * 
     * @return Returns the processingOrg.
     */
    public Org getProcessingOrg() {
        return processingOrg;
    }

    /**
     * Sets the processingOrg attribute value.
     * 
     * @param processingOrg The processingOrg to set.
     */
    public void setProcessingOrg(Org processingOrg) {
        this.processingOrg = processingOrg;
    }

    /**
     * Sets the organizationRemitToState attribute value.
     * 
     * @param organizationRemitToState The organizationRemitToState to set.
     */
    public void setOrganizationRemitToState(State organizationRemitToState) {
        this.organizationRemitToState = organizationRemitToState;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("chartOfAccountsCode", this.chartOfAccountsCode);
        m.put("organizationCode", this.organizationCode);
        return m;
    }
}
