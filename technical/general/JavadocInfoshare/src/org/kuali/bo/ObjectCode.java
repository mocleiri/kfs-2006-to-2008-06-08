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
import org.kuali.bo.codes.BudgetAggregationCode;
import org.kuali.bo.codes.FederalFundedCode;
import org.kuali.bo.codes.MandatoryTransferEliminationCode;
import org.kuali.bo.codes.ObjectSubTypeCode;
import org.kuali.bo.codes.ObjectTypeCode;
import org.kuali.service.ObjectCodeService;
import org.kuali.util.SpringServiceLocator;
import org.kuali.validation.ValidationError;
import org.kuali.validation.ValidationErrorList;

/**
 * ObjectCode Description: Object Code Business Object and Validation
 */
public class ObjectCode extends BusinessObjectBase {
    private Integer universityFiscalYear;

    private String chartOfAccountsCode;

    private String financialObjectCode;

    private String financialObjectCodeName;

    private String financialObjectCodeShortName;

    private String reportsToChartOfAccountsCode;

    private String reportsToFinancialObjectCode;

    private String financialObjectTypeCode;

    private String financialObjectLevelCode;

    private String financialObjectSubTypeCode;

    private String historicalFinancialObjectCode;

    private String financialObjectActiveCode;

    private String financialObjectBudgetAggregationCode;

    private String financialObjectMandatoryTransferOrEliminationsCode;

    private String financialFederalFundedCode;

    private String nextYearFinancialObjectCode;

    private ObjectLevel objectLevelCode;

    private ObjectTypeCode objectTypeCode;

    private ObjectSubTypeCode objectSubTypeCode;

    private BudgetAggregationCode budgetAggregationCode;

    private MandatoryTransferEliminationCode mandatoryTransferEliminationCode;

    private FederalFundedCode federalFundedCode;
    
    
    /**
     * Static method to support spring-injection of the service
     * 
     * @return Returns the ObjectCodeService.
     */
    public static ObjectCodeService getObjectCodeService() {
        return SpringServiceLocator.getObjectCodeService();
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
     * @return Returns the financialFederalFundedCode.
     */
    public String getFinancialFederalFundedCode() {
        return financialFederalFundedCode;
    }

    /**
     * @param financialFederalFundedCode
     *            The financialFederalFundedCode to set.
     */
    public void setFinancialFederalFundedCode(String financialFederalFundedCode) {
        this.financialFederalFundedCode = financialFederalFundedCode;
    }

    /**
     * @return Returns the financialObjectActiveCode.
     */
    public String getFinancialObjectActiveCode() {
        return financialObjectActiveCode;
    }

    /**
     * @param financialObjectActiveCode
     *            The financialObjectActiveCode to set.
     */
    public void setFinancialObjectActiveCode(String financialObjectActiveCode) {
        this.financialObjectActiveCode = financialObjectActiveCode;
    }

    /**
     * @return Returns the financialObjectBudgetAggregationCode.
     */
    public String getFinancialObjectBudgetAggregationCode() {
        return financialObjectBudgetAggregationCode;
    }

    /**
     * @param financialObjectBudgetAggregationCode
     *            The financialObjectBudgetAggregationCode to set.
     */
    public void setFinancialObjectBudgetAggregationCode(String financialObjectBudgetAggregationCode) {
        this.financialObjectBudgetAggregationCode = financialObjectBudgetAggregationCode;
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
     * @return Returns the financialObjectCodeName.
     */
    public String getFinancialObjectCodeName() {
        return financialObjectCodeName;
    }

    /**
     * @param financialObjectCodeName
     *            The financialObjectCodeName to set.
     */
    public void setFinancialObjectCodeName(String financialObjectCodeName) {
        this.financialObjectCodeName = financialObjectCodeName;
    }

    /**
     * @return Returns the financialObjectCodeShortName.
     */
    public String getFinancialObjectCodeShortName() {
        return financialObjectCodeShortName;
    }

    /**
     * @param financialObjectCodeShortName
     *            The financialObjectCodeShortName to set.
     */
    public void setFinancialObjectCodeShortName(String financialObjectCodeShortName) {
        this.financialObjectCodeShortName = financialObjectCodeShortName;
    }

    /**
     * @return Returns the financialObjectMandatoryTransferOrEliminationsCode.
     */
    public String getFinancialObjectMandatoryTransferOrEliminationsCode() {
        return financialObjectMandatoryTransferOrEliminationsCode;
    }

    /**
     * @param financialObjectMandatoryTransferOrEliminationsCode
     *            The financialObjectMandatoryTransferOrEliminationsCode to set.
     */
    public void setFinancialObjectMandatoryTransferOrEliminationsCode(String financialObjectMandatoryTransferOrEliminationsCode) {
        this.financialObjectMandatoryTransferOrEliminationsCode = financialObjectMandatoryTransferOrEliminationsCode;
    }

    /**
     * @return Returns the financialObjectSubTypeCode.
     */
    public String getFinancialObjectSubTypeCode() {
        return financialObjectSubTypeCode;
    }

    /**
     * @param financialObjectSubTypeCode
     *            The financialObjectSubTypeCode to set.
     */
    public void setFinancialObjectSubTypeCode(String financialObjectSubTypeCode) {
        this.financialObjectSubTypeCode = financialObjectSubTypeCode;
    }

    /**
     * @return Returns the financialObjectTypeCode.
     */
    public String getFinancialObjectTypeCode() {
        return financialObjectTypeCode;
    }

    /**
     * @param financialObjectTypeCode
     *            The financialObjectTypeCode to set.
     */
    public void setFinancialObjectTypeCode(String financialObjectTypeCode) {
        this.financialObjectTypeCode = financialObjectTypeCode;
    }

    /**
     * @return Returns the historicalFinancialObjectCode.
     */
    public String getHistoricalFinancialObjectCode() {
        return historicalFinancialObjectCode;
    }

    /**
     * @param historicalFinancialObjectCode
     *            The historicalFinancialObjectCode to set.
     */
    public void setHistoricalFinancialObjectCode(String historicalFinancialObjectCode) {
        this.historicalFinancialObjectCode = historicalFinancialObjectCode;
    }

    /**
     * @return Returns the nextYearFinancialObjectCode.
     */
    public String getNextYearFinancialObjectCode() {
        return nextYearFinancialObjectCode;
    }

    /**
     * @param nextYearFinancialObjectCode
     *            The nextYearFinancialObjectCode to set.
     */
    public void setNextYearFinancialObjectCode(String nextYearFinancialObjectCode) {
        this.nextYearFinancialObjectCode = nextYearFinancialObjectCode;
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
    public void setReportsToChartOfAccountsCode(String reportsToChartOfAccountsCode) {
        this.reportsToChartOfAccountsCode = reportsToChartOfAccountsCode;
    }

    /**
     * @return Returns the reportsToFinancialObjectCode.
     */
    public String getReportsToFinancialObjectCode() {
        return reportsToFinancialObjectCode;
    }

    /**
     * @param reportsToFinancialObjectCode
     *            The reportsToFinancialObjectCode to set.
     */
    public void setReportsToFinancialObjectCode(String reportsToFinancialObjectCode) {
        this.reportsToFinancialObjectCode = reportsToFinancialObjectCode;
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
     * Standard validate method specified in BusinessObject interface
     * 
     * @see BusinessObject
     */
    public void validate(ValidationErrorList errors)
            throws ValidationErrorList {
        validationNumber = errors.getNextValidationNumber();

        //	make sure the code specified is a valid one in the system
        ObjectCode objectCode = getObjectCodeService().getByPrimaryId(
                this.universityFiscalYear, this.chartOfAccountsCode,
                this.financialObjectCode);
        if (objectCode == null) {
            ValidationError error = new ValidationError(
                    Constants.OBJECT_CD_PROPERTY_NAME,
                    Constants.ERROR_FIELD_EXISTENCE);
            errors.addError(validationNumber, error);
        }

        //	throw any errors that came up
        errors.throwErrors();
    }

    /**
     * @return Returns the objectSubTypeCode.
     */
    public ObjectSubTypeCode getObjectSubTypeCode() {
        return objectSubTypeCode;
    }

    /**
     * @param objectSubTypeCode
     *            The objectSubTypeCode to set.
     */
    public void setObjectSubTypeCode(ObjectSubTypeCode objectSubTypeCode) {
        this.objectSubTypeCode = objectSubTypeCode;
    }

    /**
     * @return Returns the objectTypeCode.
     */
    public ObjectTypeCode getObjectTypeCode() {
        return objectTypeCode;
    }

    /**
     * @param objectTypeCode
     *            The objectTypeCode to set.
     */
    public void setObjectTypeCode(ObjectTypeCode objectTypeCode) {
        this.objectTypeCode = objectTypeCode;
    }

    /**
     * @return Returns the budgetAggregationCode.
     */
    public BudgetAggregationCode getBudgetAggregationCode() {
        return budgetAggregationCode;
    }

    /**
     * @param budgetAggregationCode
     *            The budgetAggregationCode to set.
     */
    public void setBudgetAggregationCode(BudgetAggregationCode budgetAggregationCode) {
        this.budgetAggregationCode = budgetAggregationCode;
    }

    /**
     * @return Returns the mandatoryTransferEliminationCode.
     */
    public MandatoryTransferEliminationCode getMandatoryTransferEliminationCode() {
        return mandatoryTransferEliminationCode;
    }

    /**
     * @param mandatoryTransferEliminationCode
     *            The mandatoryTransferEliminationCode to set.
     */
    public void setMandatoryTransferEliminationCode(MandatoryTransferEliminationCode mandatoryTransferEliminationCode) {
        this.mandatoryTransferEliminationCode = mandatoryTransferEliminationCode;
    }

    /**
     * @return Returns the federalFundedCode.
     */
    public FederalFundedCode getFederalFundedCode() {
        return federalFundedCode;
    }

    /**
     * @param federalFundedCode
     *            The federalFundedCode to set.
     */
    public void setFederalFundedCode(FederalFundedCode federalFundedCode) {
        this.federalFundedCode = federalFundedCode;
    }

    /**
     * @return Returns the objectLevel.
     */
    public ObjectLevel getObjectLevelCode() {

        return objectLevelCode;
    }

    /**
     * @param objectLevel
     *            The objectLevel to set.
     */
    public void setObjectLevelCode(ObjectLevel objectLevelCode) {

        this.objectLevelCode = objectLevelCode;
    }

    /**
     * @return Returns the financialObjectLevelCode.
     */
    public String getFinancialObjectLevelCode() {
        return financialObjectLevelCode;
    }

    /**
     * @param financialObjectLevelCode
     *            The financialObjectLevelCode to set.
     */
    public void setFinancialObjectLevelCode(String financialObjectLevelCode) {
        this.financialObjectLevelCode = financialObjectLevelCode;
    }
}