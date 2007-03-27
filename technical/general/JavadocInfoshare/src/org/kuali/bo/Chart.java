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
import org.kuali.util.SpringServiceLocator;
import org.kuali.validation.ValidationError;
import org.kuali.validation.ValidationErrorList;

public class Chart extends BusinessObjectBase {
	
    private String chartOfAccountsCode;

    private Long chartManagerUniversalId;

    private String chartOriginCode;

    private String chartDescription;

    private Boolean chartActive;

    private String cashObjectCode;

    private String accountsPayableObjectCode;

    private String incomeBudgetEliminationObjectCode;

    private String expenseBudgetEliminationObjectCode;

    private String reportsToChartOfAccountsCode;

    private String accountsReceivableObjectCode;

    private String internalEncumbrancesObjectCode;

    private String externalEncumbrancesObjectCode;

    private String preEncumbrancesObjectCode;

    private String indirectCostRecoveryIncomeObjectCode;

    private String indirectCostRecoveryExpenseObjectCode;

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
     * @return Returns the expenseBudgetEliminationObjectCode.
     */
    public String getExpenseBudgetEliminationObjectCode() {
        return expenseBudgetEliminationObjectCode;
    }

    /**
     * @param expenseBudgetEliminationObjectCode
     *            The expenseBudgetEliminationObjectCode to set.
     */
    public void setExpenseBudgetEliminationObjectCode(
            String expenseBudgetEliminationObjectCode) {
        this.expenseBudgetEliminationObjectCode = expenseBudgetEliminationObjectCode;
    }

    /**
     * @return Returns the accountsPayableObjectCode.
     */
    public String getAccountsPayableObjectCode() {
        return accountsPayableObjectCode;
    }

    /**
     * @param accountsPayableObjectCode
     *            The AccountsPayableObjectCode to set.
     */
    public void setAccountsPayableObjectCode(
            String accountsPayableObjectCode) {
        this.accountsPayableObjectCode = accountsPayableObjectCode;
    }

    /**
     * @return Returns the accountsReceivableObjectCode.
     */
    public String getAccountsReceivableObjectCode() {
        return accountsReceivableObjectCode;
    }

    /**
     * @param accountsReceivableObjectCode
     *            The accountsReceivableObjectCode to set.
     */
    public void setAccountsReceivableObjectCode(
            String accountsReceivableObjectCode) {
        this.accountsReceivableObjectCode = accountsReceivableObjectCode;
    }

    /**
     * @return Returns the cashObjectCode.
     */
    public String getCashObjectCode() {
        return cashObjectCode;
    }

    /**
     * @param cashObjectCode
     *            The cashObjectCode to set.
     */
    public void setCashObjectCode(String cashObjectCode) {
        this.cashObjectCode = cashObjectCode;
    }

    /**
     * @return Returns the chartActiveCode.
     */
    public boolean getChartActive() {
        return chartActive.booleanValue();
    }

    /**
     * @param chartActiveCode
     *            The chartActiveCode to set.
     */
    public void setChartActive(boolean chartActive) {
        this.chartActive = Boolean.valueOf(chartActive);
    }

    /**
     * @return Returns the chartDescription.
     */
    public String getChartDescription() {
        return chartDescription;
    }

    /**
     * @param chartDescription
     *            The chartDescription to set.
     */
    public void setChartDescription(String chartDescription) {
        this.chartDescription = chartDescription;
    }

    /**
     * @return Returns the chartManagerUniversalId.
     */
    public Long getChartManagerUniversalId() {
        return chartManagerUniversalId;
    }

    /**
     * @param chartManagerUniversalId
     *            The chartManagerUniversalId to set.
     */
    public void setChartManagerUniversalId(
            Long chartManagerUniversalId) {
        this.chartManagerUniversalId = chartManagerUniversalId;
    }

    /**
     * @return Returns the chartOriginCode.
     */
    public String getChartOriginCode() {
        return chartOriginCode;
    }

    /**
     * @param chartOriginCode
     *            The chartOriginCode to set.
     */
    public void setChartOriginCode(String chartOriginCode) {
        this.chartOriginCode = chartOriginCode;
    }

    /**
     * @return Returns the externalEncumbrancesObjectCode.
     */
    public String getExternalEncumbrancesObjectCode() {
        return externalEncumbrancesObjectCode;
    }

    /**
     * @param externalEncumbrancesObjectCode
     *            The externalEncumbrancesObjectCode to set.
     */
    public void setExternalEncumbrancesObjectCode(
            String externalEncumbrancesObjectCode) {
        this.externalEncumbrancesObjectCode = externalEncumbrancesObjectCode;
    }

    /**
     * @return Returns the internalEncumbrancesObjectCode.
     */
    public String getInternalEncumbrancesObjectCode() {
        return internalEncumbrancesObjectCode;
    }

    /**
     * @param internalEncumbrancesObjectCode
     *            The InternalEncumbrancesObjectCode to set.
     */
    public void setInternalEncumbrancesObjectCode(
            String internalEncumbrancesObjectCode) {
        this.internalEncumbrancesObjectCode = internalEncumbrancesObjectCode;
    }

    /**
     * @return Returns the PreEncumbrancesObjectCode.
     */
    public String getPreEncumbrancesObjectCode() {
        return preEncumbrancesObjectCode;
    }

    /**
     * @param preEncumbrancesObjectCode
     *            The preEncumbrancesObjectCode to set.
     */
    public void setPreEncumbrancesObjectCode(
            String preEncumbrancesObjectCode) {
        this.preEncumbrancesObjectCode = preEncumbrancesObjectCode;
    }

    /**
     * @return Returns the incomeBudgetEliminationObjectCode.
     */
    public String getIncomeBudgetEliminationObjectCode() {
        return incomeBudgetEliminationObjectCode;
    }

    /**
     * @param incomeBudgetEliminationObjectCode
     *            The incomeBudgetEliminationObjectCode to set.
     */
    public void setIncomeBudgetEliminationObjectCode(
            String incomeBudgetEliminationObjectCode) {
        this.incomeBudgetEliminationObjectCode = incomeBudgetEliminationObjectCode;
    }

    /**
     * @return Returns the indirectCostRecoveryExpenseObjectCode.
     */
    public String getIndirectCostRecoveryExpenseObjectCode() {
        return indirectCostRecoveryExpenseObjectCode;
    }

    /**
     * @param indirectCostRecoveryExpenseObjectCode
     *            The indirectCostRecoveryExpenseObjectCode to set.
     */
    public void setIndirectCostRecoveryExpenseObjectCode(
            String indirectCostRecoveryExpenseObjectCode) {
        this.indirectCostRecoveryExpenseObjectCode = indirectCostRecoveryExpenseObjectCode;
    }

    /**
     * @return Returns the indirectCostRecoveryIncomeObjectCode.
     */
    public String getIndirectCostRecoveryIncomeObjectCode() {
        return indirectCostRecoveryIncomeObjectCode;
    }

    /**
     * @param indirectCostRecoveryIncomeObjectCode
     *            The indirectCostRecoveryIncomeObjectCode to set.
     */
    public void setIndirectCostRecoveryIncomeObjectCode(
            String indirectCostRecoveryIncomeObjectCode) {
        this.indirectCostRecoveryIncomeObjectCode = indirectCostRecoveryIncomeObjectCode;
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
	 * @throws ErrorList
	 */
	public void validate(ValidationErrorList errors) throws ValidationErrorList {
	    validationNumber = errors.getNextValidationNumber();

        Chart chart = SpringServiceLocator.getChartService().getByPrimaryId(
                this.chartOfAccountsCode );
        if (chart == null) {
            ValidationError error = new ValidationError(Constants.CHART_PROPERTY_NAME, Constants.ERROR_FIELD_REQUIRED);
            errors.addError(validationNumber, error);
        }
        
        errors.throwErrors();
    }
}