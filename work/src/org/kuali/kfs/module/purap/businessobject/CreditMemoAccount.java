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

package org.kuali.module.purap.bo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import org.kuali.core.bo.BusinessObjectBase;
import org.kuali.core.util.KualiDecimalMoney;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.Chart;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class CreditMemoAccount extends BusinessObjectBase {

	private Integer creditMemoAccountIdentifier;
	private Integer creditMemoIdentifier;
	private Integer itemLineNumber;
	private String chartOfAccountsCode;
	private String accountNumber;
	private String financialObjectCode;
	private String subAccountNumber;
	private String financialSubObjectCode;
	private String projectCode;
	private String organizationReferenceId;
	private KualiDecimalMoney itemAccountTotalAmount;
	private BigDecimal accountLinePercent;

    private CreditMemoItem itemLine;
	private Chart chartOfAccounts;
	private Account account;

	/**
	 * Default constructor.
	 */
	public CreditMemoAccount() {

	}

	/**
	 * Gets the creditMemoAccountIdentifier attribute.
	 * 
	 * @return - Returns the creditMemoAccountIdentifier
	 * 
	 */
	public Integer getCreditMemoAccountIdentifier() { 
		return creditMemoAccountIdentifier;
	}

	/**
	 * Sets the creditMemoAccountIdentifier attribute.
	 * 
	 * @param - creditMemoAccountIdentifier The creditMemoAccountIdentifier to set.
	 * 
	 */
	public void setCreditMemoAccountIdentifier(Integer creditMemoAccountIdentifier) {
		this.creditMemoAccountIdentifier = creditMemoAccountIdentifier;
	}


	/**
	 * Gets the creditMemoIdentifier attribute.
	 * 
	 * @return - Returns the creditMemoIdentifier
	 * 
	 */
	public Integer getCreditMemoIdentifier() { 
		return creditMemoIdentifier;
	}

	/**
	 * Sets the creditMemoIdentifier attribute.
	 * 
	 * @param - creditMemoIdentifier The creditMemoIdentifier to set.
	 * 
	 */
	public void setCreditMemoIdentifier(Integer creditMemoIdentifier) {
		this.creditMemoIdentifier = creditMemoIdentifier;
	}


	/**
	 * Gets the itemLineNumber attribute.
	 * 
	 * @return - Returns the itemLineNumber
	 * 
	 */
	public Integer getItemLineNumber() { 
		return itemLineNumber;
	}

	/**
	 * Sets the itemLineNumber attribute.
	 * 
	 * @param - itemLineNumber The itemLineNumber to set.
	 * 
	 */
	public void setItemLineNumber(Integer itemLineNumber) {
		this.itemLineNumber = itemLineNumber;
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
	 * @param - chartOfAccountsCode The chartOfAccountsCode to set.
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
	 * @param - accountNumber The accountNumber to set.
	 * 
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	/**
	 * Gets the financialObjectCode attribute.
	 * 
	 * @return - Returns the financialObjectCode
	 * 
	 */
	public String getFinancialObjectCode() { 
		return financialObjectCode;
	}

	/**
	 * Sets the financialObjectCode attribute.
	 * 
	 * @param - financialObjectCode The financialObjectCode to set.
	 * 
	 */
	public void setFinancialObjectCode(String financialObjectCode) {
		this.financialObjectCode = financialObjectCode;
	}


	/**
	 * Gets the subAccountNumber attribute.
	 * 
	 * @return - Returns the subAccountNumber
	 * 
	 */
	public String getSubAccountNumber() { 
		return subAccountNumber;
	}

	/**
	 * Sets the subAccountNumber attribute.
	 * 
	 * @param - subAccountNumber The subAccountNumber to set.
	 * 
	 */
	public void setSubAccountNumber(String subAccountNumber) {
		this.subAccountNumber = subAccountNumber;
	}


	/**
	 * Gets the financialSubObjectCode attribute.
	 * 
	 * @return - Returns the financialSubObjectCode
	 * 
	 */
	public String getFinancialSubObjectCode() { 
		return financialSubObjectCode;
	}

	/**
	 * Sets the financialSubObjectCode attribute.
	 * 
	 * @param - financialSubObjectCode The financialSubObjectCode to set.
	 * 
	 */
	public void setFinancialSubObjectCode(String financialSubObjectCode) {
		this.financialSubObjectCode = financialSubObjectCode;
	}


	/**
	 * Gets the projectCode attribute.
	 * 
	 * @return - Returns the projectCode
	 * 
	 */
	public String getProjectCode() { 
		return projectCode;
	}

	/**
	 * Sets the projectCode attribute.
	 * 
	 * @param - projectCode The projectCode to set.
	 * 
	 */
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}


	/**
	 * Gets the organizationReferenceId attribute.
	 * 
	 * @return - Returns the organizationReferenceId
	 * 
	 */
	public String getOrganizationReferenceId() { 
		return organizationReferenceId;
	}

	/**
	 * Sets the organizationReferenceId attribute.
	 * 
	 * @param - organizationReferenceId The organizationReferenceId to set.
	 * 
	 */
	public void setOrganizationReferenceId(String organizationReferenceId) {
		this.organizationReferenceId = organizationReferenceId;
	}


	/**
	 * Gets the itemAccountTotalAmount attribute.
	 * 
	 * @return - Returns the itemAccountTotalAmount
	 * 
	 */
	public KualiDecimalMoney getItemAccountTotalAmount() { 
		return itemAccountTotalAmount;
	}

	/**
	 * Sets the itemAccountTotalAmount attribute.
	 * 
	 * @param - itemAccountTotalAmount The itemAccountTotalAmount to set.
	 * 
	 */
	public void setItemAccountTotalAmount(KualiDecimalMoney itemAccountTotalAmount) {
		this.itemAccountTotalAmount = itemAccountTotalAmount;
	}


	/**
	 * Gets the accountLinePercent attribute.
	 * 
	 * @return - Returns the accountLinePercent
	 * 
	 */
	public BigDecimal getAccountLinePercent() { 
		return accountLinePercent;
	}

	/**
	 * Sets the accountLinePercent attribute.
	 * 
	 * @param - accountLinePercent The accountLinePercent to set.
	 * 
	 */
	public void setAccountLinePercent(BigDecimal accountLinePercent) {
		this.accountLinePercent = accountLinePercent;
	}


	/**
	 * Gets the itemLine attribute.
	 * 
	 * @return - Returns the itemLine
	 * 
	 */
	public CreditMemoItem getItemLine() { 
		return itemLine;
	}

	/**
	 * Sets the itemLine attribute.
	 * 
	 * @param - itemLine The itemLine to set.
	 * @deprecated
	 */
	public void setItemLine(CreditMemoItem itemLine) {
		this.itemLine = itemLine;
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
	 * @param - chartOfAccounts The chartOfAccounts to set.
	 * @deprecated
	 */
	public void setChartOfAccounts(Chart chartOfAccounts) {
		this.chartOfAccounts = chartOfAccounts;
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
	 * @param - account The account to set.
	 * @deprecated
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	/**
	 * @see org.kuali.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
	    LinkedHashMap m = new LinkedHashMap();	    
        if (this.creditMemoAccountIdentifier != null) {
            m.put("creditMemoAccountIdentifier", this.creditMemoAccountIdentifier.toString());
        }
	    return m;
    }
}
