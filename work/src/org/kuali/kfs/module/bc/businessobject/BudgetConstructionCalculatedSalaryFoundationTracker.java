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

package org.kuali.module.budget.bo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import org.kuali.core.bo.BusinessObjectBase;
import org.kuali.core.util.KualiDecimal;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.Chart;
import org.kuali.module.chart.bo.ObjectCode;
import org.kuali.module.chart.bo.SubAccount;
import org.kuali.module.chart.bo.SubObjCd;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class BudgetConstructionCalculatedSalaryFoundationTracker extends BusinessObjectBase {

	private Integer universityFiscalYear;
	private String chartOfAccountsCode;
	private String accountNumber;
	private String subAccountNumber;
	private String financialObjectCode;
	private String financialSubObjectCode;
	private String positionNumber;
	private String emplid;
	private KualiDecimal csfAmount;
	private BigDecimal csfFullTimeEmploymentQuantity;
	private BigDecimal csfTimePercent;
	private String csfFundingStatusCode;

    private ObjectCode financialObject;
	private PendingBudgetConstructionAppointmentFunding pendingAppointmentFunding;
	private Account account;
	private Chart chartOfAccounts;
    private SubAccount subAccount;
    private SubObjCd financialSubObject;
    
	/**
	 * Default constructor.
	 */
	public BudgetConstructionCalculatedSalaryFoundationTracker() {

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
	 * @param - universityFiscalYear The universityFiscalYear to set.
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
	 * Gets the positionNumber attribute.
	 * 
	 * @return - Returns the positionNumber
	 * 
	 */
	public String getPositionNumber() { 
		return positionNumber;
	}

	/**
	 * Sets the positionNumber attribute.
	 * 
	 * @param - positionNumber The positionNumber to set.
	 * 
	 */
	public void setPositionNumber(String positionNumber) {
		this.positionNumber = positionNumber;
	}


	/**
	 * Gets the emplid.
	 * 
	 * @return - Returns the emplid
	 * 
	 */
	public String getEmplid() { 
		return emplid;
	}

	/**
	 * Sets the emplid attribute.
	 * 
	 * @param - emplid The emplid to set.
	 * 
	 */
	public void setEmplid(String emplid) {
		this.emplid = emplid;
	}


	/**
	 * Gets the csfAmount attribute.
	 * 
	 * @return - Returns the csfAmount
	 * 
	 */
	public KualiDecimal getCsfAmount() { 
		return csfAmount;
	}

	/**
	 * Sets the csfAmount attribute.
	 * 
	 * @param - csfAmount The csfAmount to set.
	 * 
	 */
	public void setCsfAmount(KualiDecimal csfAmount) {
		this.csfAmount = csfAmount;
	}


	/**
	 * Gets the csfFullTimeEmploymentQuantity attribute.
	 * 
	 * @return - Returns the csfFullTimeEmploymentQuantity
	 * 
	 */
	public BigDecimal getCsfFullTimeEmploymentQuantity() { 
		return csfFullTimeEmploymentQuantity;
	}

	/**
	 * Sets the csfFullTimeEmploymentQuantity attribute.
	 * 
	 * @param - csfFullTimeEmploymentQuantity The csfFullTimeEmploymentQuantity to set.
	 * 
	 */
	public void setCsfFullTimeEmploymentQuantity(BigDecimal csfFullTimeEmploymentQuantity) {
		this.csfFullTimeEmploymentQuantity = csfFullTimeEmploymentQuantity;
	}


	/**
	 * Gets the csfTimePercent attribute.
	 * 
	 * @return - Returns the csfTimePercent
	 * 
	 */
	public BigDecimal getCsfTimePercent() { 
		return csfTimePercent;
	}

	/**
	 * Sets the csfTimePercent attribute.
	 * 
	 * @param - csfTimePercent The csfTimePercent to set.
	 * 
	 */
	public void setCsfTimePercent(BigDecimal csfTimePercent) {
		this.csfTimePercent = csfTimePercent;
	}


	/**
	 * Gets the csfFundingStatusCode attribute.
	 * 
	 * @return - Returns the csfFundingStatusCode
	 * 
	 */
	public String getCsfFundingStatusCode() { 
		return csfFundingStatusCode;
	}

	/**
	 * Sets the csfFundingStatusCode attribute.
	 * 
	 * @param - csfFundingStatusCode The csfFundingStatusCode to set.
	 * 
	 */
	public void setCsfFundingStatusCode(String csfFundingStatusCode) {
		this.csfFundingStatusCode = csfFundingStatusCode;
	}


	/**
	 * Gets the financialObject attribute.
	 * 
	 * @return - Returns the financialObject
	 * 
	 */
	public ObjectCode getFinancialObject() { 
		return financialObject;
	}

	/**
	 * Sets the financialObject attribute.
	 * 
	 * @param - financialObject The financialObject to set.
	 * @deprecated
	 */
	public void setFinancialObject(ObjectCode financialObject) {
		this.financialObject = financialObject;
	}

	/**
	 * Gets the pendingAppointmentFunding attribute.
	 * 
	 * @return - Returns the pendingAppointmentFunding
	 * 
	 */
	public PendingBudgetConstructionAppointmentFunding getPendingAppointmentFunding() { 
		return pendingAppointmentFunding;
	}

	/**
	 * Sets the pendingAppointmentFunding attribute.
	 * 
	 * @param - pendingAppointmentFunding The pendingAppointmentFunding to set.
	 * @deprecated
	 */
	public void setPendingAppointmentFunding(PendingBudgetConstructionAppointmentFunding pendingAppointmentFunding) {
		this.pendingAppointmentFunding = pendingAppointmentFunding;
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
     * Gets the financialSubObject attribute. 
     * @return Returns the financialSubObject.
     */
    public SubObjCd getFinancialSubObject() {
        return financialSubObject;
    }

    /**
     * Sets the financialSubObject attribute value.
     * @param financialSubObject The financialSubObject to set.
     * @deprecated
     */
    public void setFinancialSubObject(SubObjCd financialSubObject) {
        this.financialSubObject = financialSubObject;
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
     * @see org.kuali.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();      
        if (this.universityFiscalYear != null) {
            m.put("universityFiscalYear", this.universityFiscalYear.toString());
        }
        m.put("chartOfAccountsCode", this.chartOfAccountsCode);
        m.put("accountNumber", this.accountNumber);
        m.put("subAccountNumber", this.subAccountNumber);
        m.put("financialObjectCode", this.financialObjectCode);
        m.put("financialSubObjectCode", this.financialSubObjectCode);
        m.put("positionNumber", this.positionNumber);
        m.put("emplid", this.emplid);
        return m;
    }

}