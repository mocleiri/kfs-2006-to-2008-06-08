/*
 * Copyright 2006 The Kuali Foundation.
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

package org.kuali.module.financial.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.core.bo.BusinessObjectBase;
import org.kuali.core.util.KualiDecimal;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.Chart;

/**
 * 
 */
public class SalesTax extends BusinessObjectBase {

    private String financialDocumentNumber;
    private String financialDocumentLineTypeCode;
    private Integer financialDocumentLineNumber;
    private String chartOfAccountsCode;
    private String accountNumber;
    private KualiDecimal financialDocumentGrossSalesAmount;
    private KualiDecimal financialDocumentTaxableSalesAmount;
    private Date financialDocumentSaleDate;

    private Account account;
    private Chart chartOfAccounts;

    /**
     * Default constructor.
     */
    public SalesTax() {

    }

    /**
     * Gets the financialDocumentNumber attribute.
     * 
     * @return - Returns the financialDocumentNumber
     * 
     */
    public String getFinancialDocumentNumber() {
        return financialDocumentNumber;
    }

    /**
     * Sets the financialDocumentNumber attribute.
     * 
     * @param financialDocumentNumber The financialDocumentNumber to set.
     * 
     */
    public void setFinancialDocumentNumber(String financialDocumentNumber) {
        this.financialDocumentNumber = financialDocumentNumber;
    }


    /**
     * Gets the financialDocumentLineTypeCode attribute.
     * 
     * @return - Returns the financialDocumentLineTypeCode
     * 
     */
    public String getFinancialDocumentLineTypeCode() {
        return financialDocumentLineTypeCode;
    }

    /**
     * Sets the financialDocumentLineTypeCode attribute.
     * 
     * @param financialDocumentLineTypeCode The financialDocumentLineTypeCode to set.
     * 
     */
    public void setFinancialDocumentLineTypeCode(String financialDocumentLineTypeCode) {
        this.financialDocumentLineTypeCode = financialDocumentLineTypeCode;
    }


    /**
     * Gets the financialDocumentLineNumber attribute.
     * 
     * @return - Returns the financialDocumentLineNumber
     * 
     */
    public Integer getFinancialDocumentLineNumber() {
        return financialDocumentLineNumber;
    }

    /**
     * Sets the financialDocumentLineNumber attribute.
     * 
     * @param financialDocumentLineNumber The financialDocumentLineNumber to set.
     * 
     */
    public void setFinancialDocumentLineNumber(Integer financialDocumentLineNumber) {
        this.financialDocumentLineNumber = financialDocumentLineNumber;
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
     * Gets the financialDocumentGrossSalesAmount attribute.
     * 
     * @return - Returns the financialDocumentGrossSalesAmount
     * 
     */
    public KualiDecimal getFinancialDocumentGrossSalesAmount() {
        return financialDocumentGrossSalesAmount;
    }

    /**
     * Sets the financialDocumentGrossSalesAmount attribute.
     * 
     * @param financialDocumentGrossSalesAmount The financialDocumentGrossSalesAmount to set.
     * 
     */
    public void setFinancialDocumentGrossSalesAmount(KualiDecimal financialDocumentGrossSalesAmount) {
        this.financialDocumentGrossSalesAmount = financialDocumentGrossSalesAmount;
    }


    /**
     * Gets the financialDocumentTaxableSalesAmount attribute.
     * 
     * @return - Returns the financialDocumentTaxableSalesAmount
     * 
     */
    public KualiDecimal getFinancialDocumentTaxableSalesAmount() {
        return financialDocumentTaxableSalesAmount;
    }

    /**
     * Sets the financialDocumentTaxableSalesAmount attribute.
     * 
     * @param financialDocumentTaxableSalesAmount The financialDocumentTaxableSalesAmount to set.
     * 
     */
    public void setFinancialDocumentTaxableSalesAmount(KualiDecimal financialDocumentTaxableSalesAmount) {
        this.financialDocumentTaxableSalesAmount = financialDocumentTaxableSalesAmount;
    }


    /**
     * Gets the financialDocumentSaleDate attribute.
     * 
     * @return - Returns the financialDocumentSaleDate
     * 
     */
    public Date getFinancialDocumentSaleDate() {
        return financialDocumentSaleDate;
    }

    /**
     * Sets the financialDocumentSaleDate attribute.
     * 
     * @param financialDocumentSaleDate The financialDocumentSaleDate to set.
     * 
     */
    public void setFinancialDocumentSaleDate(Date financialDocumentSaleDate) {
        this.financialDocumentSaleDate = financialDocumentSaleDate;
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
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("financialDocumentNumber", this.financialDocumentNumber);
        m.put("financialDocumentLineTypeCode", this.financialDocumentLineTypeCode);
        if (this.financialDocumentLineNumber != null) {
            m.put("financialDocumentLineNumber", this.financialDocumentLineNumber.toString());
        }
        return m;
    }
}
