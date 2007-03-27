/*
 * Copyright (c) 2004, 2005 The National Association of College and University Business Officers,
 * Cornell University, Trustees of Indiana University, Michigan State University Board of Trustees,
 * Trustees of San Joaquin Delta College, University of Hawai'i, The Arizona Board of Regents on
 * behalf of the University of Arizona, and the r*smart group.
 * 
 * Licensed under the Educational Community License Version 1.0 (the "License"); By obtaining,
 * using and/or copying this Original Work, you agree that you have read, understand, and will
 * comply with the terms and conditions of the Educational Community License.
 * 
 * You may obtain a copy of the License at:
 * 
 * http://kualiproject.org/license.html
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.kuali.bo;

import java.sql.Timestamp;

import org.kuali.Constants;
import org.kuali.validation.ValidationError;
import org.kuali.validation.ValidationErrorList;

/**
 * @author tdurkin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Organization extends BusinessObjectBase {

    private String chartOfAccountsCode;
    private String organizationCode;
    private Long organizationManagerUniversalId;
    private String organizationName;
    private String responsibilityCenterCode;
    private String organizationPhysicalCampusCode;
    private String organizationTypeCode;
    private String organizationDefaultAccountNumber;
    private String organizationStreetAddress;
    private String organizationCityName;
    private String organizationStateCode;
    private String organizationZipCode;
    private Timestamp organizationBeginDate;
    private Timestamp organizationEndDate;
    private String reportsToChartOfAccountsCode;
    private String reportsToOrganizationCode;
    private String humanResourcesOrganizationCode;
    private Boolean organizationActive;
    private Boolean organizationInFinancialProcessing;
    private String organizationPlantAccountNumber;
    private String campusPlantAccountNumber;
    private String organizationPlantChartOfAccountsCode;
    private String campusPlantChartOfAccountsCode;
    
    private Account defaultAccount;
    private Account organizationPlantAccount;
    private Account campusPlantAccount;
    private Organization reportsToOrganization;
    
    
    /**
     * @return Returns the organizationActive.
     */
    public boolean getOrganizationActive() {
        return organizationActive.booleanValue();
    }
    /**
     * @param organizationActive The organizationActive to set.
     */
    public void setOrganizationActive(boolean organizationActive) {
        this.organizationActive = Boolean.valueOf(organizationActive);
    }
    /**
     * @return Returns the organizationInFinancialProcessing.
     */
    public boolean getOrganizationInFinancialProcessing() {
        return organizationInFinancialProcessing.booleanValue();
    }
    /**
     * @param organizationInFinancialProcessing The organizationInFinancialProcessing to set.
     */
    public void setOrganizationInFinancialProcessing(boolean organizationInFinancialProcessing) {
        this.organizationInFinancialProcessing = Boolean.valueOf(organizationInFinancialProcessing);
    }
    /**
     * @return Returns the campusPlantAccount.
     */
    public Account getCampusPlantAccount() {
        return campusPlantAccount;
    }
    /**
     * @param campusPlantAccount The campusPlantAccount to set.
     */
    public void setCampusPlantAccount(Account campusPlantAccount) {
        this.campusPlantAccount = campusPlantAccount;
    }
    /**
     * @return Returns the defaultAccount.
     */
    public Account getDefaultAccount() {
        return defaultAccount;
    }
    /**
     * @param defaultAccount The defaultAccount to set.
     */
    public void setDefaultAccount(Account defaultAccount) {
        this.defaultAccount = defaultAccount;
    }
    /**
     * @return Returns the organizationPlantAccount.
     */
    public Account getOrganizationPlantAccount() {
        return organizationPlantAccount;
    }
    /**
     * @param organizationPlantAccount The organizationPlantAccount to set.
     */
    public void setOrganizationPlantAccount(Account organizationPlantAccount) {
        this.organizationPlantAccount = organizationPlantAccount;
    }
    /**
     * @return Returns the reportsToOrganization.
     */
    public Organization getReportsToOrganization() {
        return reportsToOrganization;
    }
    /**
     * @param reportsToOrganization The reportsToOrganization to set.
     */
    public void setReportsToOrganization(Organization reportsToOrganization) {
        this.reportsToOrganization = reportsToOrganization;
    }
    /**
     * @return Returns the campusPlantAccountNumber.
     */
    public String getCampusPlantAccountNumber() {
        return campusPlantAccountNumber;
    }
    /**
     * @param campusPlantAccountNumber The campusPlantAccountNumber to set.
     */
    public void setCampusPlantAccountNumber(String campusPlantAccountNumber) {
        this.campusPlantAccountNumber = campusPlantAccountNumber;
    }
    /**
     * @return Returns the campusPlantChartOfAccountsCode.
     */
    public String getCampusPlantChartOfAccountsCode() {
        return campusPlantChartOfAccountsCode;
    }
    /**
     * @param campusPlantChartOfAccountsCode The campusPlantChartOfAccountsCode to set.
     */
    public void setCampusPlantChartOfAccountsCode(
            String campusPlantChartOfAccountsCode) {
        this.campusPlantChartOfAccountsCode = campusPlantChartOfAccountsCode;
    }
    /**
     * @return Returns the chartOfAccountsCode.
     */
    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }
    /**
     * @param chartOfAccountsCode The chartOfAccountsCode to set.
     */
    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }
    /**
     * @return Returns the humanResourcesOrganizationCode.
     */
    public String getHumanResourcesOrganizationCode() {
        return humanResourcesOrganizationCode;
    }
    /**
     * @param humanResourcesOrganizationCode The humanResourcesOrganizationCode to set.
     */
    public void setHumanResourcesOrganizationCode(
            String humanResourcesOrganizationCode) {
        this.humanResourcesOrganizationCode = humanResourcesOrganizationCode;
    }
    /**
     * @return Returns the organizationBeginDate.
     */
    public Timestamp getOrganizationBeginDate() {
        return organizationBeginDate;
    }
    /**
     * @param organizationBeginDate The organizationBeginDate to set.
     */
    public void setOrganizationBeginDate(Timestamp organizationBeginDate) {
        this.organizationBeginDate = organizationBeginDate;
    }
    /**
     * @return Returns the organizationCityName.
     */
    public String getOrganizationCityName() {
        return organizationCityName;
    }
    /**
     * @param organizationCityName The organizationCityName to set.
     */
    public void setOrganizationCityName(String organizationCityName) {
        this.organizationCityName = organizationCityName;
    }
    /**
     * @return Returns the organizationCode.
     */
    public String getOrganizationCode() {
        return organizationCode;
    }
    /**
     * @param organizationCode The organizationCode to set.
     */
    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }
    /**
     * @return Returns the organizationDefaultAccountNumber.
     */
    public String getOrganizationDefaultAccountNumber() {
        return organizationDefaultAccountNumber;
    }
    /**
     * @param organizationDefaultAccountNumber The organizationDefaultAccountNumber to set.
     */
    public void setOrganizationDefaultAccountNumber(
            String organizationDefaultAccountNumber) {
        this.organizationDefaultAccountNumber = organizationDefaultAccountNumber;
    }
    /**
     * @return Returns the organizationEndDate.
     */
    public Timestamp getOrganizationEndDate() {
        return organizationEndDate;
    }
    /**
     * @param organizationEndDate The organizationEndDate to set.
     */
    public void setOrganizationEndDate(Timestamp organizationEndDate) {
        this.organizationEndDate = organizationEndDate;
    }
    /**
     * @return Returns the organizationManagerUniversalId.
     */
    public Long getOrganizationManagerUniversalId() {
        return organizationManagerUniversalId;
    }
    /**
     * @param organizationManagerUniversalId The organizationManagerUniversalId to set.
     */
    public void setOrganizationManagerUniversalId(
            Long organizationManagerUniversalId) {
        this.organizationManagerUniversalId = organizationManagerUniversalId;
    }
    /**
     * @return Returns the organizationName.
     */
    public String getOrganizationName() {
        return organizationName;
    }
    /**
     * @param organizationName The organizationName to set.
     */
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
    /**
     * @return Returns the organizationPhysicalCampusCode.
     */
    public String getOrganizationPhysicalCampusCode() {
        return organizationPhysicalCampusCode;
    }
    /**
     * @param organizationPhysicalCampusCode The organizationPhysicalCampusCode to set.
     */
    public void setOrganizationPhysicalCampusCode(
            String organizationPhysicalCampusCode) {
        this.organizationPhysicalCampusCode = organizationPhysicalCampusCode;
    }
    /**
     * @return Returns the organizationPlantAccountNumber.
     */
    public String getOrganizationPlantAccountNumber() {
        return organizationPlantAccountNumber;
    }
    /**
     * @param organizationPlantAccountNumber The organizationPlantAccountNumber to set.
     */
    public void setOrganizationPlantAccountNumber(
            String organizationPlantAccountNumber) {
        this.organizationPlantAccountNumber = organizationPlantAccountNumber;
    }
    /**
     * @return Returns the organizationPlantChartOfAccountsCode.
     */
    public String getOrganizationPlantChartOfAccountsCode() {
        return organizationPlantChartOfAccountsCode;
    }
    /**
     * @param organizationPlantChartOfAccountsCode The organizationPlantChartOfAccountsCode to set.
     */
    public void setOrganizationPlantChartOfAccountsCode(
            String organizationPlantChartOfAccountsCode) {
        this.organizationPlantChartOfAccountsCode = organizationPlantChartOfAccountsCode;
    }
    /**
     * @return Returns the organizationStateCode.
     */
    public String getOrganizationStateCode() {
        return organizationStateCode;
    }
    /**
     * @param organizationStateCode The organizationStateCode to set.
     */
    public void setOrganizationStateCode(String organizationStateCode) {
        this.organizationStateCode = organizationStateCode;
    }
    /**
     * @return Returns the organizationStreetAddress.
     */
    public String getOrganizationStreetAddress() {
        return organizationStreetAddress;
    }
    /**
     * @param organizationStreetAddress The organizationStreetAddress to set.
     */
    public void setOrganizationStreetAddress(String organizationStreetAddress) {
        this.organizationStreetAddress = organizationStreetAddress;
    }
    /**
     * @return Returns the organizationTypeCode.
     */
    public String getOrganizationTypeCode() {
        return organizationTypeCode;
    }
    /**
     * @param organizationTypeCode The organizationTypeCode to set.
     */
    public void setOrganizationTypeCode(String organizationTypeCode) {
        this.organizationTypeCode = organizationTypeCode;
    }
    /**
     * @return Returns the organizationZipCode.
     */
    public String getOrganizationZipCode() {
        return organizationZipCode;
    }
    /**
     * @param organizationZipCode The organizationZipCode to set.
     */
    public void setOrganizationZipCode(String organizationZipCode) {
        this.organizationZipCode = organizationZipCode;
    }
    /**
     * @return Returns the reportsToChartOfAccountsCode.
     */
    public String getReportsToChartOfAccountsCode() {
        return reportsToChartOfAccountsCode;
    }
    /**
     * @param reportsToChartOfAccountsCode The reportsToChartOfAccountsCode to set.
     */
    public void setReportsToChartOfAccountsCode(
            String reportsToChartOfAccountsCode) {
        this.reportsToChartOfAccountsCode = reportsToChartOfAccountsCode;
    }
    /**
     * @return Returns the reportsToOrganizationCode.
     */
    public String getReportsToOrganizationCode() {
        return reportsToOrganizationCode;
    }
    /**
     * @param reportsToOrganizationCode The reportsToOrganizationCode to set.
     */
    public void setReportsToOrganizationCode(String reportsToOrganizationCode) {
        this.reportsToOrganizationCode = reportsToOrganizationCode;
    }
    /**
     * @return Returns the responsibilityCenterCode.
     */
    public String getResponsibilityCenterCode() {
        return responsibilityCenterCode;
    }
    /**
     * @param responsibilityCenterCode The responsibilityCenterCode to set.
     */
    public void setResponsibilityCenterCode(String responsibilityCenterCode) {
        this.responsibilityCenterCode = responsibilityCenterCode;
    }
    
    
    /* (non-Javadoc)
     * @see org.kuali.bo.BusinessObject#validate()
     */
    public void validate(ValidationErrorList errors) throws ValidationErrorList {
        validationNumber = errors.getNextValidationNumber();
        
        //TODO need to implement the validate method based on real business rules - for now just ensure that there's a FIN_COA_CD and ORG_CD
        if ("".equals(chartOfAccountsCode) || chartOfAccountsCode == null) {
            ValidationError error = new ValidationError(Constants.CHART_PROPERTY_NAME,Constants.ERROR_FIELD_REQUIRED);
            errors.addError(validationNumber, error);
        }
        
        if ("".equals(organizationCode) || organizationCode == null) {
            ValidationError error = new ValidationError(Constants.ORG_CODE_NAME,Constants.ERROR_FIELD_REQUIRED);
            errors.addError(validationNumber, error);
        }
        
        errors.throwErrors();

    }

}
