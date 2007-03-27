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
import org.kuali.service.SubObjectCodeService;
import org.kuali.util.SpringServiceLocator;
import org.kuali.validation.ValidationError;
import org.kuali.validation.ValidationErrorList;

/**
 * 
 * @author cahana
 * 
 * SubObjectCode Pojo to access subobject code information.
 * 
 * Getters/setters for CA_SUB_OBJECT_CD_T as specified in OJB mapping.
 * 
 * SubObject code validation happens here.
 *  
 */
public class SubObjectCode extends BusinessObjectBase {

    private String accountNumber;

    private String chartOfAccountsCode;

    private String financialObjectCode;

    private String financialSubObjectActiveCode;

    private String financialSubObjectCodeShortName;

    private String financialSubObjectCode;

    private String financialSubObjectCodeName;

    private Integer universityFiscalYear;
    
    /**
     * Static method to support spring-injection of the service
     * 
     * @return Returns the SubObjectCodeService.
     */
    public static SubObjectCodeService getSubObjectCodeService() {
        return SpringServiceLocator.getSubObjectCodeService();
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
     * @return Returns the financialObjectCode.
     */
    public String getFinancialObjectCode() {
        return financialObjectCode;
    }

    /**
     * @param financialObjectCode
     *            The financialObjectCode to set.
     */
    public void setFinancialObjectCode(String financialObjectCode) {
        this.financialObjectCode = financialObjectCode;
    }

    /**
     * @return Returns the financialSubObjectActiveCode.
     */
    public String getFinancialSubObjectActiveCode() {
        return financialSubObjectActiveCode;
    }

    /**
     * @param financialSubObjectActiveCode
     *            The financialSubObjectActiveCode to set.
     */
    public void setFinancialSubObjectActiveCode(String financialSubObjectActiveCode) {
        this.financialSubObjectActiveCode = financialSubObjectActiveCode;
    }

    /**
     * @return Returns the financialSubObjectCode.
     */
    public String getFinancialSubObjectCode() {
        return financialSubObjectCode;
    }

    /**
     * @param financialSubObjectCode
     *            The financialSubObjectCode to set.
     */
    public void setFinancialSubObjectCode(String financialSubObjectCode) {
        this.financialSubObjectCode = financialSubObjectCode;
    }

    /**
     * @return Returns the financialSubObjectCodeName.
     */
    public String getFinancialSubObjectCodeName() {
        return financialSubObjectCodeName;
    }

    /**
     * @param financialSubObjectCodeName
     *            The financialSubObjectCodeName to set.
     */
    public void setFinancialSubObjectCodeName(String financialSubObjectCodeName) {
        this.financialSubObjectCodeName = financialSubObjectCodeName;
    }

    /**
     * @return Returns the financialSubObjectCodeShortName.
     */
    public String getFinancialSubObjectCodeShortName() {
        return financialSubObjectCodeShortName;
    }

    /**
     * @param financialSubObjectCodeShortName
     *            The financialSubObjectCodeShortName to set.
     */
    public void setFinancialSubObjectCodeShortName(String financialSubObjectCodeShortName) {
        this.financialSubObjectCodeShortName = financialSubObjectCodeShortName;
    }

    /**
     * @return Returns the universityFiscalYear.
     */
    public Integer getUniversityFiscalYear() {
        return universityFiscalYear;
    }

    /**
     * @param universityFiscalYear
     *            The universityFiscalYear to set.
     */
    public void setUniversityFiscalYear(Integer universityFiscalYear) {
        this.universityFiscalYear = universityFiscalYear;
    }

    /**
     * Standard validate method that checks that the subobject exists and is active within
     * the financial system.
   * @throws IllegalObjectStateException
     * @throws ErrorList
     */
    public void validate(ValidationErrorList errors) throws ValidationErrorList, IllegalObjectStateException {
      validationNumber = errors.getNextValidationNumber();
      
      //	do not do validations if this is an empty object
      //	empty is defined by the primary key(s) having no data
      if (universityFiscalYear == null || 
              			universityFiscalYear.intValue() == 0 ||
              			chartOfAccountsCode == null ||
              			"".equals(chartOfAccountsCode) ||
              			accountNumber == null ||
              			"".equals(accountNumber) ||
              			financialObjectCode == null ||
              			"".equals(financialObjectCode) ||
              			financialSubObjectCode == null ||
              			"".equals(financialSubObjectCode)) {
          return;
         // throw new IllegalObjectStateException("key is null");
      }
      
      //	test that the values specified are valid codes in the system
      SubObjectCode subObjectCode = getSubObjectCodeService().getByPrimaryId(this.universityFiscalYear,
        this.chartOfAccountsCode, this.accountNumber, this.financialObjectCode,
        this.financialSubObjectCode);
      if (subObjectCode == null) {
          ValidationError error = new ValidationError(
                  Constants.SUBOBJECT_CD_PROPERTY_NAME,
                  Constants.ERROR_FIELD_EXISTENCE);
          errors.addError(validationNumber, error);
      }
      else {
      
        //	disallow inActive codes
        if (!subObjectCode.getFinancialSubObjectActiveCode().equals("Y")) {
            ValidationError error = new ValidationError(
                    Constants.SUBOBJECT_CD_PROPERTY_NAME,
                    Constants.ERROR_FIELD_INACTIVE);
            errors.addError(validationNumber, error);
        }
      }
      
      //	throw any errors that occurred
      errors.throwErrors();

    }
}