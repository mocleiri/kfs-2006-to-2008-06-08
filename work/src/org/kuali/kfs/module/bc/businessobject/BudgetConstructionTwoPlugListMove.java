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

package org.kuali.module.budget.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.core.util.KualiDecimal;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.Chart;
import org.kuali.module.chart.bo.Org;
import org.kuali.module.chart.bo.SubAccount;

/**
 * 
 */
public class BudgetConstructionTwoPlugListMove extends PersistableBusinessObjectBase {

	private String personUniversalIdentifier;
	private String organizationChartOfAccountsCode;
	private String organizationCode;
	private String chartOfAccountsCode;
	private String accountNumber;
	private String subAccountNumber;
	private KualiDecimal accountLineAnnualBalanceAmount;

    private Chart organizationChartOfAccounts;
	private Org organization;
	private Chart chartOfAccounts;
	private Account account;
    SubAccount subAccount;
    
	/**
	 * Default constructor.
	 */
	public BudgetConstructionTwoPlugListMove() {

	}

	/**
	 * Gets the personUniversalIdentifier attribute.
	 * 
	 * @return Returns the personUniversalIdentifier
	 * 
	 */
	public String getPersonUniversalIdentifier() { 
		return personUniversalIdentifier;
	}

	/**
	 * Sets the personUniversalIdentifier attribute.
	 * 
	 * @param personUniversalIdentifier The personUniversalIdentifier to set.
	 * 
	 */
	public void setPersonUniversalIdentifier(String personUniversalIdentifier) {
		this.personUniversalIdentifier = personUniversalIdentifier;
	}


	/**
	 * Gets the organizationChartOfAccountsCode attribute.
	 * 
	 * @return Returns the organizationChartOfAccountsCode
	 * 
	 */
	public String getOrganizationChartOfAccountsCode() { 
		return organizationChartOfAccountsCode;
	}

	/**
	 * Sets the organizationChartOfAccountsCode attribute.
	 * 
	 * @param organizationChartOfAccountsCode The organizationChartOfAccountsCode to set.
	 * 
	 */
	public void setOrganizationChartOfAccountsCode(String organizationChartOfAccountsCode) {
		this.organizationChartOfAccountsCode = organizationChartOfAccountsCode;
	}


	/**
	 * Gets the organizationCode attribute.
	 * 
	 * @return Returns the organizationCode
	 * 
	 */
	public String getOrganizationCode() { 
		return organizationCode;
	}

	/**
	 * Sets the organizationCode attribute.
	 * 
	 * @param organizationCode The organizationCode to set.
	 * 
	 */
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}


	/**
	 * Gets the chartOfAccountsCode attribute.
	 * 
	 * @return Returns the chartOfAccountsCode
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
	 * @return Returns the accountNumber
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
	 * Gets the subAccountNumber attribute.
	 * 
	 * @return Returns the subAccountNumber
	 * 
	 */
	public String getSubAccountNumber() { 
		return subAccountNumber;
	}

	/**
	 * Sets the subAccountNumber attribute.
	 * 
	 * @param subAccountNumber The subAccountNumber to set.
	 * 
	 */
	public void setSubAccountNumber(String subAccountNumber) {
		this.subAccountNumber = subAccountNumber;
	}


	/**
	 * Gets the accountLineAnnualBalanceAmount attribute.
	 * 
	 * @return Returns the accountLineAnnualBalanceAmount
	 * 
	 */
	public KualiDecimal getAccountLineAnnualBalanceAmount() { 
		return accountLineAnnualBalanceAmount;
	}

	/**
	 * Sets the accountLineAnnualBalanceAmount attribute.
	 * 
	 * @param accountLineAnnualBalanceAmount The accountLineAnnualBalanceAmount to set.
	 * 
	 */
	public void setAccountLineAnnualBalanceAmount(KualiDecimal accountLineAnnualBalanceAmount) {
		this.accountLineAnnualBalanceAmount = accountLineAnnualBalanceAmount;
	}


	/**
	 * Gets the organizationChartOfAccounts attribute.
	 * 
	 * @return Returns the organizationChartOfAccounts
	 * 
	 */
	public Chart getOrganizationChartOfAccounts() { 
		return organizationChartOfAccounts;
	}

	/**
	 * Sets the organizationChartOfAccounts attribute.
	 * 
	 * @param organizationChartOfAccounts The organizationChartOfAccounts to set.
	 * @deprecated
	 */
	public void setOrganizationChartOfAccounts(Chart organizationChartOfAccounts) {
		this.organizationChartOfAccounts = organizationChartOfAccounts;
	}

	/**
	 * Gets the organization attribute.
	 * 
	 * @return Returns the organization
	 * 
	 */
	public Org getOrganization() { 
		return organization;
	}

	/**
	 * Sets the organization attribute.
	 * 
	 * @param organization The organization to set.
	 * @deprecated
	 */
	public void setOrganization(Org organization) {
		this.organization = organization;
	}

	/**
	 * Gets the chartOfAccounts attribute.
	 * 
	 * @return Returns the chartOfAccounts
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
	 * Gets the account attribute.
	 * 
	 * @return Returns the account
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
     * Gets the subAccount attribute. 
     * @return Returns the subAccount.
     */
    public SubAccount getSubAccount() {
        return subAccount;
    }

    /**
     * Sets the subAccount attribute value.
     * @param subAccount The subAccount to set.
     * @deprecated
     */
    public void setSubAccount(SubAccount subAccount) {
        this.subAccount = subAccount;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();      
        m.put("personUniversalIdentifier", this.personUniversalIdentifier);
        m.put("organizationChartOfAccountsCode", this.organizationChartOfAccountsCode);
        m.put("organizationCode", this.organizationCode);
        m.put("chartOfAccountsCode", this.chartOfAccountsCode);
        m.put("accountNumber", this.accountNumber);
        m.put("subAccountNumber", this.subAccountNumber);
        return m;
    }

}
