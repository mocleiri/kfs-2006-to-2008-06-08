/*
 * Copyright (c) 2004, 2005 The National Association of College and University 
 * Business Officers, Cornell University, Trustees of Indiana University, 
 * Michigan State University Board of Trustees, Trustees of San Joaquin Delta 
 * College, University of Hawai'i, The Arizona Board of Regents on behalf of the 
 * University of Arizona, and the r*smart group.
 * 
 * Licensed under the Educational Community License Version 1.0 (the "License"); 
 * By obtaining, using and/or copying this Original Work, you agree that you 
 * have read, understand, and will comply with the terms and conditions of the 
 * Educational Community License.
 * 
 * You may obtain a copy of the License at:
 * 
 * http://kualiproject.org/license.html
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,  DAMAGES OR OTHER 
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN 
 * THE SOFTWARE.
 */

package org.kuali.module.financial.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.BusinessObjectBase;
import org.kuali.core.bo.user.Options;
import org.kuali.core.util.KualiDecimal;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.Chart;
import org.kuali.module.chart.bo.ObjectCode;

/**
 * 
 */
public class WireCharge extends BusinessObjectBase {

    private Integer universityFiscalYear;
    private String chartOfAccountsCode;
    private String accountNumber;
    private String incomeFinancialObjectCode;
    private String expenseFinancialObjectCode;
    private KualiDecimal domesticChargeAmt;
    private KualiDecimal foreignChargeAmt;

    private Options fiscalYear;
    private Chart chartOfAccounts;
    private ObjectCode incomeFinancialObject;
    private ObjectCode expenseFinancialObject;
    private Account account;

    /**
     * Default no-arg constructor.
     */
    public WireCharge() {

    }

    /**
     * Gets the universityFiscalYear attribute.
     * 
     * @return - Returns the universityFiscalYear
     * 
     */
    public Integer getUniversityFiscalYear() {
        return universityFiscalYear;
    }


    /**
     * Sets the universityFiscalYear attribute.
     * 
     * @param universityFiscalYear The universityFiscalYear to set.
     * 
     */
    public void setUniversityFiscalYear(Integer universityFiscalYear) {
        this.universityFiscalYear = universityFiscalYear;
    }

    /**
     * Gets the chartOfAccountsCode attribute.
     * 
     * @return - Returns the chartOfAccountsCode
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
     * Gets the accountNumber attribute.
     * 
     * @return - Returns the accountNumber
     * 
     */
    public String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber attribute.
     * 
     * @param accountNumber The accountNumber to set.
     * 
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets the incomeFinancialObjectCode attribute.
     * 
     * @return - Returns the incomeFinancialObjectCode
     * 
     */
    public String getIncomeFinancialObjectCode() {
        return incomeFinancialObjectCode;
    }


    /**
     * Sets the incomeFinancialObjectCode attribute.
     * 
     * @param incomeFinancialObjectCode The incomeFinancialObjectCode to set.
     * 
     */
    public void setIncomeFinancialObjectCode(String incomeFinancialObjectCode) {
        this.incomeFinancialObjectCode = incomeFinancialObjectCode;
    }

    /**
     * Gets the expenseFinancialObjectCode attribute.
     * 
     * @return - Returns the expenseFinancialObjectCode
     * 
     */
    public String getExpenseFinancialObjectCode() {
        return expenseFinancialObjectCode;
    }


    /**
     * Sets the expenseFinancialObjectCode attribute.
     * 
     * @param expenseFinancialObjectCode The expenseFinancialObjectCode to set.
     * 
     */
    public void setExpenseFinancialObjectCode(String expenseFinancialObjectCode) {
        this.expenseFinancialObjectCode = expenseFinancialObjectCode;
    }

    /**
     * Gets the domesticChargeAmt attribute.
     * 
     * @return - Returns the domesticChargeAmt
     * 
     */
    public KualiDecimal getDomesticChargeAmt() {
        return domesticChargeAmt;
    }


    /**
     * Sets the domesticChargeAmt attribute.
     * 
     * @param domesticChargeAmt The domesticChargeAmt to set.
     * 
     */
    public void setDomesticChargeAmt(KualiDecimal domesticChargeAmt) {
        this.domesticChargeAmt = domesticChargeAmt;
    }

    /**
     * Gets the foreignChargeAmt attribute.
     * 
     * @return - Returns the foreignChargeAmt
     * 
     */
    public KualiDecimal getForeignChargeAmt() {
        return foreignChargeAmt;
    }


    /**
     * Sets the foreignChargeAmt attribute.
     * 
     * @param foreignChargeAmt The foreignChargeAmt to set.
     * 
     */
    public void setForeignChargeAmt(KualiDecimal foreignChargeAmt) {
        this.foreignChargeAmt = foreignChargeAmt;
    }

    /**
     * Gets the chartOfAccounts attribute.
     * 
     * @return - Returns the chartOfAccounts
     * 
     */
    public Chart getChartOfAccounts() {
        return chartOfAccounts;
    }


    /**
     * Sets the chartOfAccounts attribute.
     * 
     * @param chartOfAccounts The chartOfAccounts to set.
     * @deprecated
     */
    public void setChartOfAccounts(Chart chartOfAccounts) {
        this.chartOfAccounts = chartOfAccounts;
    }

    /**
     * Gets the incomeFinancialObject attribute.
     * 
     * @return - Returns the incomeFinancialObject
     * 
     */
    public ObjectCode getIncomeFinancialObject() {
        return incomeFinancialObject;
    }


    /**
     * Sets the incomeFinancialObject attribute.
     * 
     * @param incomeFinancialObject The incomeFinancialObject to set.
     * @deprecated
     */
    public void setIncomeFinancialObject(ObjectCode incomeFinancialObject) {
        this.incomeFinancialObject = incomeFinancialObject;
    }

    /**
     * Gets the expenseFinancialObject attribute.
     * 
     * @return - Returns the expenseFinancialObject
     * 
     */
    public ObjectCode getExpenseFinancialObject() {
        return expenseFinancialObject;
    }


    /**
     * Sets the expenseFinancialObject attribute.
     * 
     * @param expenseFinancialObject The expenseFinancialObject to set.
     * @deprecated
     */
    public void setExpenseFinancialObject(ObjectCode expenseFinancialObject) {
        this.expenseFinancialObject = expenseFinancialObject;
    }

    /**
     * Gets the account attribute.
     * 
     * @return - Returns the account
     * 
     */
    public Account getAccount() {
        return account;
    }


    /**
     * Sets the account attribute.
     * 
     * @param account The account to set.
     * @deprecated
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * @return Returns the fiscalYear.
     */
    public Options getFiscalYear() {
        return fiscalYear;
    }


    /**
     * @param fiscalYear The fiscalYear to set.
     */
    public void setFiscalYear(Options fiscalYear) {
        this.fiscalYear = fiscalYear;
    }


    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("universityFiscalYear", this.universityFiscalYear.toString());
        return m;
    }
}
