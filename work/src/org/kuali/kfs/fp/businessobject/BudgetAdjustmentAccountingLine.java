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
package org.kuali.module.financial.bo;

import org.kuali.core.util.KualiDecimalMoney;
import org.kuali.core.util.KualiInteger;

/**
 * @author Kuali Financial Transactions Team (kualidev@oncourse.iu.edu)
 */
public interface BudgetAdjustmentAccountingLine {

    public abstract KualiDecimalMoney getMonthlyLinesTotal();

    public abstract KualiInteger getBaseBudgetAdjustmentAmount();

    public abstract void setBaseBudgetAdjustmentAmount(KualiInteger baseBudgetAdjustmentAmount);

    public abstract String getBudgetAdjustmentPeriodCode();

    public abstract void setBudgetAdjustmentPeriodCode(String budgetAdjustmentPeriodCode);

    public abstract KualiDecimalMoney getCurrentBudgetAdjustmentAmount();

    public abstract void setCurrentBudgetAdjustmentAmount(KualiDecimalMoney currentBudgetAdjustmentAmount);

    /**
     * Gets the financialDocumentMonth1LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth1LineAmount
     * 
     */
    public abstract KualiDecimalMoney getFinancialDocumentMonth1LineAmount();

    /**
     * Sets the financialDocumentMonth1LineAmount attribute.
     * 
     * @param - financialDocumentMonth1LineAmount The financialDocumentMonth1LineAmount to set.
     * 
     */
    public abstract void setFinancialDocumentMonth1LineAmount(KualiDecimalMoney financialDocumentMonth1LineAmount);

    /**
     * Gets the financialDocumentMonth2LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth2LineAmount
     * 
     */
    public abstract KualiDecimalMoney getFinancialDocumentMonth2LineAmount();

    /**
     * Sets the financialDocumentMonth2LineAmount attribute.
     * 
     * @param - financialDocumentMonth2LineAmount The financialDocumentMonth2LineAmount to set.
     * 
     */
    public abstract void setFinancialDocumentMonth2LineAmount(KualiDecimalMoney financialDocumentMonth2LineAmount);

    /**
     * Gets the financialDocumentMonth3LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth3LineAmount
     * 
     */
    public abstract KualiDecimalMoney getFinancialDocumentMonth3LineAmount();

    /**
     * Sets the financialDocumentMonth3LineAmount attribute.
     * 
     * @param - financialDocumentMonth3LineAmount The financialDocumentMonth3LineAmount to set.
     * 
     */
    public abstract void setFinancialDocumentMonth3LineAmount(KualiDecimalMoney financialDocumentMonth3LineAmount);

    /**
     * Gets the financialDocumentMonth4LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth4LineAmount
     * 
     */
    public abstract KualiDecimalMoney getFinancialDocumentMonth4LineAmount();

    /**
     * Sets the financialDocumentMonth4LineAmount attribute.
     * 
     * @param - financialDocumentMonth4LineAmount The financialDocumentMonth4LineAmount to set.
     * 
     */
    public abstract void setFinancialDocumentMonth4LineAmount(KualiDecimalMoney financialDocumentMonth4LineAmount);

    /**
     * Gets the financialDocumentMonth5LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth5LineAmount
     * 
     */
    public abstract KualiDecimalMoney getFinancialDocumentMonth5LineAmount();

    /**
     * Sets the financialDocumentMonth5LineAmount attribute.
     * 
     * @param - financialDocumentMonth5LineAmount The financialDocumentMonth5LineAmount to set.
     * 
     */
    public abstract void setFinancialDocumentMonth5LineAmount(KualiDecimalMoney financialDocumentMonth5LineAmount);

    /**
     * Gets the financialDocumentMonth6LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth6LineAmount
     * 
     */
    public abstract KualiDecimalMoney getFinancialDocumentMonth6LineAmount();

    /**
     * Sets the financialDocumentMonth6LineAmount attribute.
     * 
     * @param - financialDocumentMonth6LineAmount The financialDocumentMonth6LineAmount to set.
     * 
     */
    public abstract void setFinancialDocumentMonth6LineAmount(KualiDecimalMoney financialDocumentMonth6LineAmount);

    /**
     * Gets the financialDocumentMonth7LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth7LineAmount
     * 
     */
    public abstract KualiDecimalMoney getFinancialDocumentMonth7LineAmount();

    /**
     * Sets the financialDocumentMonth7LineAmount attribute.
     * 
     * @param - financialDocumentMonth7LineAmount The financialDocumentMonth7LineAmount to set.
     * 
     */
    public abstract void setFinancialDocumentMonth7LineAmount(KualiDecimalMoney financialDocumentMonth7LineAmount);

    /**
     * Gets the financialDocumentMonth8LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth8LineAmount
     * 
     */
    public abstract KualiDecimalMoney getFinancialDocumentMonth8LineAmount();

    /**
     * Sets the financialDocumentMonth8LineAmount attribute.
     * 
     * @param - financialDocumentMonth8LineAmount The financialDocumentMonth8LineAmount to set.
     * 
     */
    public abstract void setFinancialDocumentMonth8LineAmount(KualiDecimalMoney financialDocumentMonth8LineAmount);

    /**
     * Gets the financialDocumentMonth9LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth9LineAmount
     * 
     */
    public abstract KualiDecimalMoney getFinancialDocumentMonth9LineAmount();

    /**
     * Sets the financialDocumentMonth9LineAmount attribute.
     * 
     * @param - financialDocumentMonth9LineAmount The financialDocumentMonth9LineAmount to set.
     * 
     */
    public abstract void setFinancialDocumentMonth9LineAmount(KualiDecimalMoney financialDocumentMonth9LineAmount);

    /**
     * Gets the financialDocumentMonth10LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth10LineAmount
     * 
     */
    public abstract KualiDecimalMoney getFinancialDocumentMonth10LineAmount();

    /**
     * Sets the financialDocumentMonth10LineAmount attribute.
     * 
     * @param - financialDocumentMonth10LineAmount The financialDocumentMonth10LineAmount to set.
     * 
     */
    public abstract void setFinancialDocumentMonth10LineAmount(KualiDecimalMoney financialDocumentMonth10LineAmount);

    /**
     * Gets the financialDocumentMonth11LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth11LineAmount
     * 
     */
    public abstract KualiDecimalMoney getFinancialDocumentMonth11LineAmount();

    /**
     * Sets the financialDocumentMonth11LineAmount attribute.
     * 
     * @param - financialDocumentMonth11LineAmount The financialDocumentMonth11LineAmount to set.
     * 
     */
    public abstract void setFinancialDocumentMonth11LineAmount(KualiDecimalMoney financialDocumentMonth11LineAmount);

    /**
     * Gets the financialDocumentMonth12LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth12LineAmount
     * 
     */
    public abstract KualiDecimalMoney getFinancialDocumentMonth12LineAmount();

    /**
     * Sets the financialDocumentMonth12LineAmount attribute.
     * 
     * @param - financialDocumentMonth12LineAmount The financialDocumentMonth12LineAmount to set.
     * 
     */
    public abstract void setFinancialDocumentMonth12LineAmount(KualiDecimalMoney financialDocumentMonth12LineAmount);

    /**
     * Gets the fringeBenefitIndicator attribute.
     * 
     * @return - Returns the fringeBenefitIndicator
     * 
     */
    public abstract boolean isFringeBenefitIndicator();

    /**
     * Sets the fringeBenefitIndicator attribute.
     * 
     * @param - fringeBenefitIndicator The fringeBenefitIndicator to set.
     * 
     */
    public abstract void setFringeBenefitIndicator(boolean fringeBenefitIndicator);


}