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

import org.kuali.Constants;
import org.kuali.exceptions.IllegalObjectStateException;
import org.kuali.service.SubAccountService;
import org.kuali.util.SpringServiceLocator;
import org.kuali.validation.ValidationError;
import org.kuali.validation.ValidationErrorList;

/**
 * 
 * @author tdurkin, dohester, jkneal
 * 
 * Generic SubAccount Pojo to access SubAccount information.
 * 
 * Getters/setters for CA_SUB_ACCT_T as specified in OJB mapping.
 * 
 * Account validation happens here.
 *  
 */
public class SubAccount extends BusinessObjectBase {

    private String accountNumber;

    private String chartOfAccountsCode;

    private String financialReportingCode;

    private String financialReportingChartCode;

    private String financialReportingOrgCode;

    private String subAccountActiveCode;

    private String subAccountNumber;

    private String subAccountName;

    private Account account;
    
    public SubAccount() {
        account = new Account();
    }
    /**
     * Static method to support spring-injection of the service
     * 
     * @return Returns the ObjectCodeService.
     */
    public static SubAccountService getSubAccountService() {
        return SpringServiceLocator.getSubAccountService();
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
     * @return Returns the financialReportingChartCode.
     */
    public String getFinancialReportingChartCode() {
        return financialReportingChartCode;
    }

    /**
     * @param financialReportingChartCode
     *            The financialReportingChartCode to set.
     */
    public void setFinancialReportingChartCode(String financialReportingChartCode) {
        this.financialReportingChartCode = financialReportingChartCode;
    }

    /**
     * @return Returns the financialReportingCode.
     */
    public String getFinancialReportingCode() {
        return financialReportingCode;
    }

    /**
     * @param financialReportingCode
     *            The financialReportingCode to set.
     */
    public void setFinancialReportingCode(String financialReportingCode) {
        this.financialReportingCode = financialReportingCode;
    }

    /**
     * @return Returns the financialReportingOrgCode.
     */
    public String getFinancialReportingOrgCode() {
        return financialReportingOrgCode;
    }

    /**
     * @param financialReportingOrgCode
     *            The financialReportingOrgCode to set.
     */
    public void setFinancialReportingOrgCode(String financialReportingOrgCode) {
        this.financialReportingOrgCode = financialReportingOrgCode;
    }

    /**
     * @return Returns the subAccountActiveCode.
     */
    public String getSubAccountActiveCode() {
        return subAccountActiveCode;
    }

    /**
     * @param subAccountActiveCode
     *            The subAccountActiveCode to set.
     */
    public void setSubAccountActiveCode(String subAccountActiveCode) {
        this.subAccountActiveCode = subAccountActiveCode;
    }

    /**
     * @return Returns the subAccountName.
     */
    public String getSubAccountName() {
        return subAccountName;
    }

    /**
     * @param subAccountName
     *            The subAccountName to set.
     */
    public void setSubAccountName(String subAccountName) {
        this.subAccountName = subAccountName;
    }

    /**
     * @return Returns the subAccountNumber.
     */
    public String getSubAccountNumber() {
        return subAccountNumber;
    }

    /**
     * @param subAccountNumber
     *            The subAccountNumber to set.
     */
    public void setSubAccountNumber(String subAccountNumber) {
        this.subAccountNumber = subAccountNumber;
    }

    /**
     * @return Returns the account.
     */
    public Account getAccount() {
        return account;
    }

    /**
     * @param account
     *            The account to set.
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Standard validate method, specified in BusinessObject interface
     * implemented by this class
     * 
     * @throws IllegalObjectStateException
     * @throws ErrorList
     * @see BusinessObject
     */
    public void validate(ValidationErrorList errors)
            throws ValidationErrorList, IllegalObjectStateException {
        validationNumber = errors.getNextValidationNumber();

        //	do not do validations if this is an empty object
        //	empty is defined by the primary key(s) having no data
        if (chartOfAccountsCode == null || "".equals(chartOfAccountsCode)
                || accountNumber == null || "".equals(accountNumber)
                || subAccountNumber == null || "".equals(subAccountNumber)) {
            return;
           // throw new IllegalObjectStateException("Key is null");
        }

        //	get a new copy of the subAccount by unique IDs for existence testing
        SubAccount _subAccount = getSubAccountService().getByPrimaryId(
                this.chartOfAccountsCode, this.accountNumber,
                this.subAccountNumber);

        //	make sure the object exists
        if (_subAccount == null) {
            ValidationError error = new ValidationError(
                    Constants.SUBACCOUNT_PROPERTY_NAME,
                    Constants.ERROR_FIELD_REQUIRED);
            errors.addError(validationNumber, error);
        }
        else {

          //	disallow InActive subAccounts
          if (!_subAccount.getSubAccountActiveCode().equals("Y")) {
              ValidationError error = new ValidationError(
                      Constants.SUBACCOUNT_PROPERTY_NAME,
                      Constants.ERROR_FIELD_INACTIVE);
              errors.addError(validationNumber, error);
          }
        }

        //	throw any errors that may have occurred
        errors.throwErrors();
    }
}