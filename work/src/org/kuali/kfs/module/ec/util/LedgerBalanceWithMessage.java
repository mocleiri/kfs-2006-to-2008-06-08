/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.module.effort.util;

import org.kuali.module.labor.bo.LedgerBalance;

/**
 * This class...
 */
public class LedgerBalanceWithMessage {
    
    private String chartOfAccountsCode;
    private String accountNumber;
    private String subAccountNumber;
    private String financialObjectCode;
    private String positionNumber;
    private String emplid;    
    private String message;
    
    /**
     * Constructs a LedgerBalanceWithMessage.java.
     */
    public LedgerBalanceWithMessage() {
        super();
    }    

    /**
     * Constructs a LedgerBalanceWithMessage.java.
     * @param ledgerBalance the given ledger balance 
     * @param message the message associated with the given ledger balance
     */
    public LedgerBalanceWithMessage(LedgerBalance ledgerBalance, String message) {
        super();
        this.chartOfAccountsCode = ledgerBalance.getChartOfAccountsCode();
        this.accountNumber = ledgerBalance.getAccountNumber();
        this.subAccountNumber = ledgerBalance.getSubAccountNumber();
        this.financialObjectCode = ledgerBalance.getFinancialObjectCode();
        this.positionNumber = ledgerBalance.getPositionNumber();
        this.emplid = ledgerBalance.getEmplid();
        this.message = message;
    }

    /**
     * Gets the chartOfAccountsCode attribute. 
     * @return Returns the chartOfAccountsCode.
     */
    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }

    /**
     * Sets the chartOfAccountsCode attribute value.
     * @param chartOfAccountsCode The chartOfAccountsCode to set.
     */
    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }

    /**
     * Gets the accountNumber attribute. 
     * @return Returns the accountNumber.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the accountNumber attribute value.
     * @param accountNumber The accountNumber to set.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets the subAccountNumber attribute. 
     * @return Returns the subAccountNumber.
     */
    public String getSubAccountNumber() {
        return subAccountNumber;
    }

    /**
     * Sets the subAccountNumber attribute value.
     * @param subAccountNumber The subAccountNumber to set.
     */
    public void setSubAccountNumber(String subAccountNumber) {
        this.subAccountNumber = subAccountNumber;
    }

    /**
     * Gets the financialObjectCode attribute. 
     * @return Returns the financialObjectCode.
     */
    public String getFinancialObjectCode() {
        return financialObjectCode;
    }

    /**
     * Sets the financialObjectCode attribute value.
     * @param financialObjectCode The financialObjectCode to set.
     */
    public void setFinancialObjectCode(String financialObjectCode) {
        this.financialObjectCode = financialObjectCode;
    }

    /**
     * Gets the positionNumber attribute. 
     * @return Returns the positionNumber.
     */
    public String getPositionNumber() {
        return positionNumber;
    }

    /**
     * Sets the positionNumber attribute value.
     * @param positionNumber The positionNumber to set.
     */
    public void setPositionNumber(String positionNumber) {
        this.positionNumber = positionNumber;
    }

    /**
     * Gets the emplid attribute. 
     * @return Returns the emplid.
     */
    public String getEmplid() {
        return emplid;
    }

    /**
     * Sets the emplid attribute value.
     * @param emplid The emplid to set.
     */
    public void setEmplid(String emplid) {
        this.emplid = emplid;
    }

    /**
     * Gets the message attribute. 
     * @return Returns the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message attribute value.
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}