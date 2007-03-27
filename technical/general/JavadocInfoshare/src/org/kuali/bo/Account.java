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
import java.util.Collection;

import org.kuali.Constants;
import org.kuali.util.SpringServiceLocator;
import org.kuali.validation.ValidationError;
import org.kuali.validation.ValidationErrorList;

/**
 * 
 * @author tdurkin, dohester, jkneal
 *
 * Generic Account Pojo to access Account information.
 * 
 * Getters/setters for CA_ACCOUNT_T as specified in OJB mapping.
 * 
 * Account validation happens here.
 * 
 */
public class Account extends BusinessObjectBase {

    private String chartOfAccountsCode;
    private String accountNumber;    
    private String accountTitle;
    private String accountName;
	private Long accountFiscalOfficerSystemId;	
	private Long accountSupervisorSystemId;	
	private Long accountManagerSystemId;
    private String organizationCode;
    private String accountTypeCode;
    private String accountPhysicalCampusCode;
    private String subFundGroupCode;
    private Boolean accountFringeBenefit;
    private String financialHigherEducationFunctionCode;
    private String accountRestrictedStatusCode;
    private Timestamp accountRestrictedStatusDate;
    private String accountCityName;
    private String accountStateCode;
    private String accountStreetAddress;
    private String accountZipCode;
    private String reportsToChartOfAccountsCode;
    private String reportsToAccountNumber;
    private Timestamp accountCreateDate;
    private Timestamp accountEffectiveDate;
    private Timestamp accountExpirationDate;
    private String continuationChartOfAccountsCode;
    private String continuationAccountNumber;
    private Integer awardPeriodEndYear;
    private String awardPeriodEndMonth;
    private Integer awardPeriodBeginYear;
    private String awardPeriodBeginMonth;
    private String endowmentChartOfAccountsCode;
    private String endowmentAccountNumber;
    private String contractControlChartOfAccountsCode;
    private String contractControlAccountNumber;
    private String incomeStreamChartOfAccountsCode;
    private String incomeStreamAccountNumber;
    private String accountIndirectCostRecoveryTypeCode;
    private String accountCustomizedIndirectCostExclusionCode;
    private String financialSeriesId;
    private String indirectCostRecoveryChartOfAccountsCode;
    private String indirectCostRecoveryAccountNumber;
    private Boolean accountInFinancialProcessing;
    private String budgetRecordingLevelCode;
    private String accountSufficientFundsCode;
    private String pendingAccountSufficientFundsCode;
    private String externalEncumbranceSufficientFundsCode;
    private String internalEncumberanceSufficientFundsCode;
    private String preEncumbranceSufficientFundsCode;
    private Boolean objectPresenceControl;
    private String codeOfFederalDomesticAssistanceNumber;
    private String accountOffCampusIndicator;
    private String accountClosedIndicator;
    private Collection subAccounts;
    
    private AccountGuideline accountGuideline;
    
    public Account(){
        this.accountGuideline = new AccountGuideline();
        objectPresenceControl = Boolean.valueOf(true);
    }
   
    /**
     * @return Returns the subAccounts.
     */
    public Collection getSubAccounts() {
        return subAccounts;
    }
    
    /**
     * @param subAccounts The subAccounts to set.
     */
    public void setSubAccounts(Collection subAccounts) {
        this.subAccounts = subAccounts;
    }
    
    /**
     * @return Returns the accountCityName.
     */
    public String getAccountCityName() {
        return accountCityName;
    }

    /**
     * @param accountCityName
     *            The accountCityName to set.
     */
    public void setAccountCityName(String accountCityName) {
        this.accountCityName = accountCityName;
    }

    /**
     * @return Returns the accountClosedIndicator.
     */
    public String getAccountClosedIndicator() {
        return accountClosedIndicator;
    }

    /**
     * @param accountClosedIndicator
     *            The accountClosedIndicator to set.
     */
    public void setAccountClosedIndicator(String accountClosedIndicator) {
        this.accountClosedIndicator = accountClosedIndicator;
    }

    /**
     * @return Returns the accountCreateDate.
     */
    public Timestamp getAccountCreateDate() {
        return accountCreateDate;
    }

    /**
     * @param accountCreateDate
     *            The accountCreateDate to set.
     */
    public void setAccountCreateDate(Timestamp accountCreateDate) {
        this.accountCreateDate = accountCreateDate;
    }

    /**
     * @return Returns the accountCustomizedIndirectCostExclusionCode.
     */
    public String getAccountCustomizedIndirectCostExclusionCode() {
        return accountCustomizedIndirectCostExclusionCode;
    }

    /**
     * @param accountCustomizedIndirectCostExclusionCode
     *            The accountCustomizedIndirectCostExclusionCode to set.
     */
    public void setAccountCustomizedIndirectCostExclusionCode(
            String accountCustomizedIndirectCostExclusionCode) {
        this.accountCustomizedIndirectCostExclusionCode = accountCustomizedIndirectCostExclusionCode;
    }

    /**
     * @return Returns the accountEffectiveDate.
     */
    public Timestamp getAccountEffectiveDate() {
        return accountEffectiveDate;
    }

    /**
     * @param accountEffectiveDate
     *            The accountEffectiveDate to set.
     */
    public void setAccountEffectiveDate(Timestamp accountEffectiveDate) {
        this.accountEffectiveDate = accountEffectiveDate;
    }

    /**
     * @return Returns the accountExpirationDate.
     */
    public Timestamp getAccountExpirationDate() {
        return accountExpirationDate;
    }

    /**
     * @param accountExpirationDate
     *            The accountExpirationDate to set.
     */
    public void setAccountExpirationDate(Timestamp accountExpirationDate) {
        this.accountExpirationDate = accountExpirationDate;
    }

    /**
     * @return Returns the accountFringeBenefit.
     */
    public boolean isAccountFringeBenefit() {
        return accountFringeBenefit.booleanValue();
    }

    /**
     * @param accountFringeBenefit
     *            Whether this account accepts fringe benefits.
     */
    public void setAccountFringeBenefit(boolean accountFringeBenefit) {
        this.accountFringeBenefit = Boolean.valueOf(accountFringeBenefit);
    }

    /**
     * @return Returns the accountIncomeFinancialProcessingCode.
     */
    public boolean isAccountInFinancialProcessing() {
        return accountInFinancialProcessing.booleanValue();
    }

    /**
     * @param accountIncomeFinancialProcessingCode
     *            The accountIncomeFinancialProcessingCode to set.
     */
    public void setAccountInFinancialProcessing(boolean accountInFinancialProcessing) {
        this.accountInFinancialProcessing = Boolean.valueOf( accountInFinancialProcessing);
    }

    /**
     * @return Returns the accountIndirectCostRecoveryTypeCode.
     */
    public String getAccountIndirectCostRecoveryTypeCode() {
        return accountIndirectCostRecoveryTypeCode;
    }

    /**
     * @param accountIndirectCostRecoveryTypeCode
     *            The accountIndirectCostRecoveryTypeCode to set.
     */
    public void setAccountIndirectCostRecoveryTypeCode(
            String accountIndirectCostRecoveryTypeCode) {
        this.accountIndirectCostRecoveryTypeCode = accountIndirectCostRecoveryTypeCode;
    }

    /**
     * @return Returns the accountName.
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName
     *            The accountName to set.
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
     
    /**
     * 
     * @return
     */
    public Long getAccountFiscalOfficerSystemId() {
        return accountFiscalOfficerSystemId;
    }
    
    /**
     * 
     * @param accountFiscalOfficerSystemId
     */
    public void setAccountFiscalOfficerSystemId(
            Long accountFiscalOfficerSystemId) {
        this.accountFiscalOfficerSystemId = accountFiscalOfficerSystemId;
    }
    
    /**
     * 
     * @return
     */
    public Long getAccountManagerSystemId() {
        return accountManagerSystemId;
    }
    
    /**
     * 
     * @param accountManagerSystemId
     */
    public void setAccountManagerSystemId(Long accountManagerSystemId) {
        this.accountManagerSystemId = accountManagerSystemId;
    }
    
    /**
     * 
     * @return
     */
    public Long getAccountSupervisorSystemId() {
        return accountSupervisorSystemId;
    }
    
    /**
     * 
     * @param accountSupervisorSystemId
     */
    public void setAccountSupervisorSystemId(Long accountSupervisorSystemId) {
        this.accountSupervisorSystemId = accountSupervisorSystemId;
    }
    
    /**
     * 
     * @return
     */
    public String getAccountTitle() {
        return accountTitle;
    }
    
    /**
     * 
     * @param accountTitle
     */
    public void setAccountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
    }
    
    /**
     * @return Returns the accountNumber.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber
     *            The accountNumber to set.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return Returns the accountOffCampusIndicator.
     */
    public String getAccountOffCampusIndicator() {
        return accountOffCampusIndicator;
    }

    /**
     * @param accountOffCampusIndicator
     *            The accountOffCampusIndicator to set.
     */
    public void setAccountOffCampusIndicator(String accountOffCampusIndicator) {
        this.accountOffCampusIndicator = accountOffCampusIndicator;
    }

    /**
     * @return Returns the accountPhysicalCampusCode.
     */
    public String getAccountPhysicalCampusCode() {
        return accountPhysicalCampusCode;
    }

    /**
     * @param accountPhysicalCampusCode
     *            The accountPhysicalCampusCode to set.
     */
    public void setAccountPhysicalCampusCode(String accountPhysicalCampusCode) {
        this.accountPhysicalCampusCode = accountPhysicalCampusCode;
    }

    /**
     * @return Returns the accountRestrictedStatusCode.
     */
    public String getAccountRestrictedStatusCode() {
        return accountRestrictedStatusCode;
    }

    /**
     * @param accountRestrictedStatusCode
     *            The accountRestrictedStatusCode to set.
     */
    public void setAccountRestrictedStatusCode(
            String accountRestrictedStatusCode) {
        this.accountRestrictedStatusCode = accountRestrictedStatusCode;
    }

    /**
     * @return Returns the accountRestrictedStatusDate.
     */
    public Timestamp getAccountRestrictedStatusDate() {
        return accountRestrictedStatusDate;
    }

    /**
     * @param accountRestrictedStatusDate
     *            The accountRestrictedStatusDate to set.
     */
    public void setAccountRestrictedStatusDate(
            Timestamp accountRestrictedStatusDate) {
        this.accountRestrictedStatusDate = accountRestrictedStatusDate;
    }

    /**
     * @return Returns the accountStateCode.
     */
    public String getAccountStateCode() {
        return accountStateCode;
    }

    /**
     * @param accountStateCode
     *            The accountStateCode to set.
     */
    public void setAccountStateCode(String accountStateCode) {
        this.accountStateCode = accountStateCode;
    }

    /**
     * @return Returns the accountStreetAddress.
     */
    public String getAccountStreetAddress() {
        return accountStreetAddress;
    }

    /**
     * @param accountStreetAddress
     *            The accountStreetAddress to set.
     */
    public void setAccountStreetAddress(String accountStreetAddress) {
        this.accountStreetAddress = accountStreetAddress;
    }

    /**
     * @return Returns the accountSufficientFundsCode.
     */
    public String getAccountSufficientFundsCode() {
        return accountSufficientFundsCode;
    }

    /**
     * @param accountSufficientFundsCode
     *            The accountSufficientFundsCode to set.
     */
    public void setAccountSufficientFundsCode(String accountSufficientFundsCode) {
        this.accountSufficientFundsCode = accountSufficientFundsCode;
    }

    /**
     * @return Returns the accountTypeCode.
     */
    public String getAccountTypeCode() {
        return accountTypeCode;
    }

    /**
     * @param accountTypeCode
     *            The accountTypeCode to set.
     */
    public void setAccountTypeCode(String accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
    }

    /**
     * @return Returns the accountZipCode.
     */
    public String getAccountZipCode() {
        return accountZipCode;
    }

    /**
     * @param accountZipCode
     *            The accountZipCode to set.
     */
    public void setAccountZipCode(String accountZipCode) {
        this.accountZipCode = accountZipCode;
    }

    /**
     * @return Returns the awardPeriodBeginMonth.
     */
    public String getAwardPeriodBeginMonth() {
        return awardPeriodBeginMonth;
    }

    /**
     * @param awardPeriodBeginMonth
     *            The awardPeriodBeginMonth to set.
     */
    public void setAwardPeriodBeginMonth(String awardPeriodBeginMonth) {
        this.awardPeriodBeginMonth = awardPeriodBeginMonth;
    }

    /**
     * @return Returns the awardPeriodBeginYear.
     */
    public Integer getAwardPeriodBeginYear() {
        return awardPeriodBeginYear;
    }

    /**
     * @param awardPeriodBeginYear
     *            The awardPeriodBeginYear to set.
     */
    public void setAwardPeriodBeginYear(Integer awardPeriodBeginYear) {
        this.awardPeriodBeginYear = awardPeriodBeginYear;
    }

    /**
     * @return Returns the awardPeriodEndMonth.
     */
    public String getAwardPeriodEndMonth() {
        return awardPeriodEndMonth;
    }

    /**
     * @param awardPeriodEndMonth
     *            The awardPeriodEndMonth to set.
     */
    public void setAwardPeriodEndMonth(String awardPeriodEndMonth) {
        this.awardPeriodEndMonth = awardPeriodEndMonth;
    }

    /**
     * @return Returns the awardPeriodEndYear.
     */
    public Integer getAwardPeriodEndYear() {
        return awardPeriodEndYear;
    }

    /**
     * @param awardPeriodEndYear
     *            The awardPeriodEndYear to set.
     */
    public void setAwardPeriodEndYear(Integer awardPeriodEndYear) {
        this.awardPeriodEndYear = awardPeriodEndYear;
    }

    /**
     * @return Returns the budgetRecordingLevelCode.
     */
    public String getBudgetRecordingLevelCode() {
        return budgetRecordingLevelCode;
    }

    /**
     * @param budgetRecordingLevelCode
     *            The budgetRecordingLevelCode to set.
     */
    public void setBudgetRecordingLevelCode(String budgetRecordingLevelCode) {
        this.budgetRecordingLevelCode = budgetRecordingLevelCode;
    }

    /**
     * @return Returns the chartOfAccountsCode.
     */
    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }

    /**
     * @param chartOfAccountsCode
     *            The chartOfAccountsCode to set.
     */
    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }

    /**
     * @return Returns the codeOfFederalDomesticAssistanceNumber.
     */
    public String getCodeOfFederalDomesticAssistanceNumber() {
        return codeOfFederalDomesticAssistanceNumber;
    }

    /**
     * @param codeOfFederalDomesticAssistanceNumber
     *            The codeOfFederalDomesticAssistanceNumber to set.
     */
    public void setCodeOfFederalDomesticAssistanceNumber(
            String codeOfFederalDomesticAssistanceNumber) {
        this.codeOfFederalDomesticAssistanceNumber = codeOfFederalDomesticAssistanceNumber;
    }

    /**
     * @return Returns the continuationAccountNumber.
     */
    public String getContinuationAccountNumber() {
        return continuationAccountNumber;
    }

    /**
     * @param continuationAccountNumber
     *            The continuationAccountNumber to set.
     */
    public void setContinuationAccountNumber(String continuationAccountNumber) {
        this.continuationAccountNumber = continuationAccountNumber;
    }

    /**
     * @return Returns the continuationChartOfAccountsCode.
     */
    public String getContinuationChartOfAccountsCode() {
        return continuationChartOfAccountsCode;
    }

    /**
     * @param continuationChartOfAccountsCode
     *            The continuationChartOfAccountsCode to set.
     */
    public void setContinuationChartOfAccountsCode(
            String continuationChartOfAccountsCode) {
        this.continuationChartOfAccountsCode = continuationChartOfAccountsCode;
    }

    /**
     * @return Returns the contractControlAccountNumber.
     */
    public String getContractControlAccountNumber() {
        return contractControlAccountNumber;
    }

    /**
     * @param contractControlAccountNumber
     *            The contractControlAccountNumber to set.
     */
    public void setContractControlAccountNumber(
            String contractControlAccountNumber) {
        this.contractControlAccountNumber = contractControlAccountNumber;
    }

    /**
     * @return Returns the contractControlChartOfAccountsCode.
     */
    public String getContractControlChartOfAccountsCode() {
        return contractControlChartOfAccountsCode;
    }

    /**
     * @param contractControlChartOfAccountsCode
     *            The contractControlChartOfAccountsCode to set.
     */
    public void setContractControlChartOfAccountsCode(
            String contractControlChartOfAccountsCode) {
        this.contractControlChartOfAccountsCode = contractControlChartOfAccountsCode;
    }

    /**
     * @return Returns the endowmentAccountNumber.
     */
    public String getEndowmentAccountNumber() {
        return endowmentAccountNumber;
    }

    /**
     * @param endowmentAccountNumber
     *            The endowmentAccountNumber to set.
     */
    public void setEndowmentAccountNumber(String endowmentAccountNumber) {
        this.endowmentAccountNumber = endowmentAccountNumber;
    }

    /**
     * @return Returns the endowmentChartOfAccountsCode.
     */
    public String getEndowmentChartOfAccountsCode() {
        return endowmentChartOfAccountsCode;
    }

    /**
     * @param endowmentChartOfAccountsCode
     *            The endowmentChartOfAccountsCode to set.
     */
    public void setEndowmentChartOfAccountsCode(
            String endowmentChartOfAccountsCode) {
        this.endowmentChartOfAccountsCode = endowmentChartOfAccountsCode;
    }

    /**
     * @return Returns the externalEncumbranceSufficientFundsCode.
     */
    public String getExternalEncumbranceSufficientFundsCode() {
        return externalEncumbranceSufficientFundsCode;
    }

    /**
     * @param externalEncumbranceSufficientFundsCode
     *            The externalEncumbranceSufficientFundsCode to set.
     */
    public void setExternalEncumbranceSufficientFundsCode(
            String externalEncumbranceSufficientFundsCode) {
        this.externalEncumbranceSufficientFundsCode = externalEncumbranceSufficientFundsCode;
    }

    /**
     * @return Returns the financialHigherEducationFunctionCode.
     */
    public String getFinancialHigherEducationFunctionCode() {
        return financialHigherEducationFunctionCode;
    }

    /**
     * @param financialHigherEducationFunctionCode
     *            The financialHigherEducationFunctionCode to set.
     */
    public void setFinancialHigherEducationFunctionCode(
            String financialHigherEducationFunctionCode) {
        this.financialHigherEducationFunctionCode = financialHigherEducationFunctionCode;
    }

    /**
     * @return Returns the objectPresenceControlCode.
     */
    public boolean getObjectPresenceControl() {
        return objectPresenceControl.booleanValue();
    }

    /**
     * @param objectPresenceControlCode
     *            The objectPresenceControlCode to set.
     */
    public void setObjectPresenceControl(boolean financialObjectPresenceControl) {
        this.objectPresenceControl = Boolean.valueOf( financialObjectPresenceControl);
    }

    /**
     * @return Returns the financialSeriesId.
     */
    public String getFinancialSeriesId() {
        return financialSeriesId;
    }

    /**
     * @param financialSeriesId
     *            The financialSeriesId to set.
     */
    public void setFinancialSeriesId(String financialSeriesId) {
        this.financialSeriesId = financialSeriesId;
    }

    /**
     * @return Returns the incomeStreamAccountNumber.
     */
    public String getIncomeStreamAccountNumber() {
        return incomeStreamAccountNumber;
    }

    /**
     * @param incomeStreamAccountNumber
     *            The incomeStreamAccountNumber to set.
     */
    public void setIncomeStreamAccountNumber(String incomeStreamAccountNumber) {
        this.incomeStreamAccountNumber = incomeStreamAccountNumber;
    }

    /**
     * @return Returns the incomeStreamChartOfAccountsCode.
     */
    public String getIncomeStreamChartOfAccountsCode() {
        return incomeStreamChartOfAccountsCode;
    }

    /**
     * @param incomeStreamChartOfAccountsCode
     *            The incomeStreamChartOfAccountsCode to set.
     */
    public void setIncomeStreamChartOfAccountsCode(
            String incomeStreamChartOfAccountsCode) {
        this.incomeStreamChartOfAccountsCode = incomeStreamChartOfAccountsCode;
    }

    /**
     * @return Returns the indirectCostRecoveryAccountNumber.
     */
    public String getIndirectCostRecoveryAccountNumber() {
        return indirectCostRecoveryAccountNumber;
    }

    /**
     * @param indirectCostRecoveryAccountNumber
     *            The indirectCostRecoveryAccountNumber to set.
     */
    public void setIndirectCostRecoveryAccountNumber(
            String indirectCostRecoveryAccountNumber) {
        this.indirectCostRecoveryAccountNumber = indirectCostRecoveryAccountNumber;
    }

    /**
     * @return Returns the indirectCostRecoveryChartOfAccountsCode.
     */
    public String getIndirectCostRecoveryChartOfAccountsCode() {
        return indirectCostRecoveryChartOfAccountsCode;
    }

    /**
     * @param indirectCostRecoveryChartOfAccountsCode
     *            The indirectCostRecoveryChartOfAccountsCode to set.
     */
    public void setIndirectCostRecoveryChartOfAccountsCode(
            String indirectCostRecoveryChartOfAccountsCode) {
        this.indirectCostRecoveryChartOfAccountsCode = indirectCostRecoveryChartOfAccountsCode;
    }

    /**
     * @return Returns the internalEncumberanceSufficientFundsCode.
     */
    public String getInternalEncumberanceSufficientFundsCode() {
        return internalEncumberanceSufficientFundsCode;
    }

    /**
     * @param internalEncumberanceSufficientFundsCode
     *            The internalEncumberanceSufficientFundsCode to set.
     */
    public void setInternalEncumberanceSufficientFundsCode(
            String internalEncumberanceSufficientFundsCode) {
        this.internalEncumberanceSufficientFundsCode = internalEncumberanceSufficientFundsCode;
    }

    /**
     * @return Returns the organizationCode.
     */
    public String getOrganizationCode() {
        return organizationCode;
    }

    /**
     * @param organizationCode
     *            The organizationCode to set.
     */
    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    /**
     * @return Returns the pendingAccountSufficientFundsCode.
     */
    public String getPendingAccountSufficientFundsCode() {
        return pendingAccountSufficientFundsCode;
    }

    /**
     * @param pendingAccountSufficientFundsCode
     *            The pendingAccountSufficientFundsCode to set.
     */
    public void setPendingAccountSufficientFundsCode(
            String pendingAccountSufficientFundsCode) {
        this.pendingAccountSufficientFundsCode = pendingAccountSufficientFundsCode;
    }

    /**
     * @return Returns the preEncumbranceSufficientFundsCode.
     */
    public String getPreEncumbranceSufficientFundsCode() {
        return preEncumbranceSufficientFundsCode;
    }

    /**
     * @param preEncumbranceSufficientFundsCode
     *            The preEncumbranceSufficientFundsCode to set.
     */
    public void setPreEncumbranceSufficientFundsCode(
            String preEncumbranceSufficientFundsCode) {
        this.preEncumbranceSufficientFundsCode = preEncumbranceSufficientFundsCode;
    }

    /**
     * @return Returns the reportsToAccountNumber.
     */
    public String getReportsToAccountNumber() {
        return reportsToAccountNumber;
    }

    /**
     * @param reportsToAccountNumber
     *            The reportsToAccountNumber to set.
     */
    public void setReportsToAccountNumber(String reportsToAccountNumber) {
        this.reportsToAccountNumber = reportsToAccountNumber;
    }

    /**
     * @return Returns the reportsToChartOfAccountsCode.
     */
    public String getReportsToChartOfAccountsCode() {
        return reportsToChartOfAccountsCode;
    }

    /**
     * @param reportsToChartOfAccountsCode
     *            The reportsToChartOfAccountsCode to set.
     */
    public void setReportsToChartOfAccountsCode(
            String reportsToChartOfAccountsCode) {
        this.reportsToChartOfAccountsCode = reportsToChartOfAccountsCode;
    }

    /**
     * @return Returns the subFundGroupCode.
     */
    public String getSubFundGroupCode() {
        return subFundGroupCode;
    }

    /**
     * @param subFundGroupCode
     *            The subFundGroupCode to set.
     */
    public void setSubFundGroupCode(String subFundGroupCode) {
        this.subFundGroupCode = subFundGroupCode;
    }
    
    /**
     * Standard validate method, specified in BusinessObject interface implemented by this class
     * @throws ErrorList
     * @see BusinessObject
     */
    public void validate(ValidationErrorList errors)throws ValidationErrorList{
        
        //TODO Implement Account validation here based on FIS business rules.
        validationNumber = errors.getNextValidationNumber();
        
        //	if the primary keys for this object aren't populated, then do not validate
        if (this.chartOfAccountsCode == null) {
            return;
        }
        if ("".equals(this.chartOfAccountsCode)) {
            return;
        }
        if (this.accountNumber == null) {
            return;
        }
        if ("".equals(this.accountNumber)) {
            return;
        }
        
        //	existence test for required field Chart Code
        if ("".equals(this.chartOfAccountsCode)) {
            ValidationError error = new ValidationError(Constants.CHART_PROPERTY_NAME, Constants.ERROR_FIELD_REQUIRED);
            errors.addError(validationNumber, error);
        }
        
        //	existence test for required field Account Number
        if (this.getAccountNumber().equals("")) {
            ValidationError error = new ValidationError(Constants.ACCOUNT_PROPERTY_NAME, Constants.ERROR_FIELD_REQUIRED);
            errors.addError(validationNumber, error);
        }
        
        //	test to make sure that the combination of Chart Code and Account
        // Number exists in the system
        Account account = SpringServiceLocator.getAccountService().getByPrimaryId(chartOfAccountsCode, accountNumber);
        if (account == null) {
            ValidationError error = new ValidationError(Constants.ACCOUNT_PROPERTY_NAME, Constants.ERROR_FIELD_EXISTENCE);
            errors.addError(validationNumber, error);
        }
        
        errors.throwErrors();
    }

    /**
     * @return Returns the accountGuideline.
     */
    public AccountGuideline getAccountGuideline() {
        if (accountGuideline == null){
            accountGuideline = new AccountGuideline();
        }
        return accountGuideline;
    }
    
    
    /**
     * @param accountGuideline The accountGuideline to set.
     */
    public void setAccountGuideline(AccountGuideline accountGuideline) {
        if (accountGuideline != null){
          this.accountGuideline = accountGuideline;
        }  
    }
}