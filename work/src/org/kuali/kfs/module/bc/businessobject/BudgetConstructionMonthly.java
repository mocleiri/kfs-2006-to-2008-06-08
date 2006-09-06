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

import java.util.LinkedHashMap;

import org.kuali.core.bo.BusinessObjectBase;
import org.kuali.core.util.KualiDecimal;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.Chart;
import org.kuali.module.chart.bo.ObjectCode;
import org.kuali.module.chart.bo.SubAccount;
import org.kuali.module.chart.bo.SubObjCd;
import org.kuali.module.gl.bo.Balance;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class BudgetConstructionMonthly extends BusinessObjectBase {

    private String financialDocumentNumber;
    private Integer universityFiscalYear;
    private String chartOfAccountsCode;
    private String accountNumber;
    private String subAccountNumber;
    private String financialObjectCode;
    private String financialSubObjectCode;
    private String financialBalanceTypeCode;
    private String financialObjectTypeCode;
    private KualiDecimal financialDocumentMonth1LineAmount;
    private KualiDecimal financialDocumentMonth2LineAmount;
    private KualiDecimal financialDocumentMonth3LineAmount;
    private KualiDecimal financialDocumentMonth4LineAmount;
    private KualiDecimal financialDocumentMonth5LineAmount;
    private KualiDecimal financialDocumentMonth6LineAmount;
    private KualiDecimal financialDocumentMonth7LineAmount;
    private KualiDecimal financialDocumentMonth8LineAmount;
    private KualiDecimal financialDocumentMonth9LineAmount;
    private KualiDecimal financialDocumentMonth10LineAmount;
    private KualiDecimal financialDocumentMonth11LineAmount;
    private KualiDecimal financialDocumentMonth12LineAmount;

    private PendingBudgetConstructionGl pendingBudgetConstructionGl;
    private Balance financialBalanceType;
    private ObjectCode financialObject;
    private Account account;
    private Chart chartOfAccounts;
    private SubAccount subAccount;
    private SubObjCd financialSubObject;

    /**
     * Default constructor.
     */
    public BudgetConstructionMonthly() {

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
     * @param - financialDocumentNumber The financialDocumentNumber to set.
     * 
     */
    public void setFinancialDocumentNumber(String financialDocumentNumber) {
        this.financialDocumentNumber = financialDocumentNumber;
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
     * Gets the financialBalanceTypeCode attribute.
     * 
     * @return - Returns the financialBalanceTypeCode
     * 
     */
    public String getFinancialBalanceTypeCode() {
        return financialBalanceTypeCode;
    }

    /**
     * Sets the financialBalanceTypeCode attribute.
     * 
     * @param - financialBalanceTypeCode The financialBalanceTypeCode to set.
     * 
     */
    public void setFinancialBalanceTypeCode(String financialBalanceTypeCode) {
        this.financialBalanceTypeCode = financialBalanceTypeCode;
    }


    /**
     * Gets the financialObjectTypeCode attribute.
     * 
     * @return - Returns the financialObjectTypeCode
     * 
     */
    public String getFinancialObjectTypeCode() {
        return financialObjectTypeCode;
    }

    /**
     * Sets the financialObjectTypeCode attribute.
     * 
     * @param - financialObjectTypeCode The financialObjectTypeCode to set.
     * 
     */
    public void setFinancialObjectTypeCode(String financialObjectTypeCode) {
        this.financialObjectTypeCode = financialObjectTypeCode;
    }


    /**
     * Gets the financialDocumentMonth1LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth1LineAmount
     * 
     */
    public KualiDecimal getFinancialDocumentMonth1LineAmount() {
        return financialDocumentMonth1LineAmount;
    }

    /**
     * Sets the financialDocumentMonth1LineAmount attribute.
     * 
     * @param - financialDocumentMonth1LineAmount The financialDocumentMonth1LineAmount to set.
     * 
     */
    public void setFinancialDocumentMonth1LineAmount(KualiDecimal financialDocumentMonth1LineAmount) {
        this.financialDocumentMonth1LineAmount = financialDocumentMonth1LineAmount;
    }


    /**
     * Gets the financialDocumentMonth2LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth2LineAmount
     * 
     */
    public KualiDecimal getFinancialDocumentMonth2LineAmount() {
        return financialDocumentMonth2LineAmount;
    }

    /**
     * Sets the financialDocumentMonth2LineAmount attribute.
     * 
     * @param - financialDocumentMonth2LineAmount The financialDocumentMonth2LineAmount to set.
     * 
     */
    public void setFinancialDocumentMonth2LineAmount(KualiDecimal financialDocumentMonth2LineAmount) {
        this.financialDocumentMonth2LineAmount = financialDocumentMonth2LineAmount;
    }


    /**
     * Gets the financialDocumentMonth3LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth3LineAmount
     * 
     */
    public KualiDecimal getFinancialDocumentMonth3LineAmount() {
        return financialDocumentMonth3LineAmount;
    }

    /**
     * Sets the financialDocumentMonth3LineAmount attribute.
     * 
     * @param - financialDocumentMonth3LineAmount The financialDocumentMonth3LineAmount to set.
     * 
     */
    public void setFinancialDocumentMonth3LineAmount(KualiDecimal financialDocumentMonth3LineAmount) {
        this.financialDocumentMonth3LineAmount = financialDocumentMonth3LineAmount;
    }


    /**
     * Gets the financialDocumentMonth4LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth4LineAmount
     * 
     */
    public KualiDecimal getFinancialDocumentMonth4LineAmount() {
        return financialDocumentMonth4LineAmount;
    }

    /**
     * Sets the financialDocumentMonth4LineAmount attribute.
     * 
     * @param - financialDocumentMonth4LineAmount The financialDocumentMonth4LineAmount to set.
     * 
     */
    public void setFinancialDocumentMonth4LineAmount(KualiDecimal financialDocumentMonth4LineAmount) {
        this.financialDocumentMonth4LineAmount = financialDocumentMonth4LineAmount;
    }


    /**
     * Gets the financialDocumentMonth5LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth5LineAmount
     * 
     */
    public KualiDecimal getFinancialDocumentMonth5LineAmount() {
        return financialDocumentMonth5LineAmount;
    }

    /**
     * Sets the financialDocumentMonth5LineAmount attribute.
     * 
     * @param - financialDocumentMonth5LineAmount The financialDocumentMonth5LineAmount to set.
     * 
     */
    public void setFinancialDocumentMonth5LineAmount(KualiDecimal financialDocumentMonth5LineAmount) {
        this.financialDocumentMonth5LineAmount = financialDocumentMonth5LineAmount;
    }


    /**
     * Gets the financialDocumentMonth6LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth6LineAmount
     * 
     */
    public KualiDecimal getFinancialDocumentMonth6LineAmount() {
        return financialDocumentMonth6LineAmount;
    }

    /**
     * Sets the financialDocumentMonth6LineAmount attribute.
     * 
     * @param - financialDocumentMonth6LineAmount The financialDocumentMonth6LineAmount to set.
     * 
     */
    public void setFinancialDocumentMonth6LineAmount(KualiDecimal financialDocumentMonth6LineAmount) {
        this.financialDocumentMonth6LineAmount = financialDocumentMonth6LineAmount;
    }


    /**
     * Gets the financialDocumentMonth7LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth7LineAmount
     * 
     */
    public KualiDecimal getFinancialDocumentMonth7LineAmount() {
        return financialDocumentMonth7LineAmount;
    }

    /**
     * Sets the financialDocumentMonth7LineAmount attribute.
     * 
     * @param - financialDocumentMonth7LineAmount The financialDocumentMonth7LineAmount to set.
     * 
     */
    public void setFinancialDocumentMonth7LineAmount(KualiDecimal financialDocumentMonth7LineAmount) {
        this.financialDocumentMonth7LineAmount = financialDocumentMonth7LineAmount;
    }


    /**
     * Gets the financialDocumentMonth8LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth8LineAmount
     * 
     */
    public KualiDecimal getFinancialDocumentMonth8LineAmount() {
        return financialDocumentMonth8LineAmount;
    }

    /**
     * Sets the financialDocumentMonth8LineAmount attribute.
     * 
     * @param - financialDocumentMonth8LineAmount The financialDocumentMonth8LineAmount to set.
     * 
     */
    public void setFinancialDocumentMonth8LineAmount(KualiDecimal financialDocumentMonth8LineAmount) {
        this.financialDocumentMonth8LineAmount = financialDocumentMonth8LineAmount;
    }


    /**
     * Gets the financialDocumentMonth9LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth9LineAmount
     * 
     */
    public KualiDecimal getFinancialDocumentMonth9LineAmount() {
        return financialDocumentMonth9LineAmount;
    }

    /**
     * Sets the financialDocumentMonth9LineAmount attribute.
     * 
     * @param - financialDocumentMonth9LineAmount The financialDocumentMonth9LineAmount to set.
     * 
     */
    public void setFinancialDocumentMonth9LineAmount(KualiDecimal financialDocumentMonth9LineAmount) {
        this.financialDocumentMonth9LineAmount = financialDocumentMonth9LineAmount;
    }


    /**
     * Gets the financialDocumentMonth10LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth10LineAmount
     * 
     */
    public KualiDecimal getFinancialDocumentMonth10LineAmount() {
        return financialDocumentMonth10LineAmount;
    }

    /**
     * Sets the financialDocumentMonth10LineAmount attribute.
     * 
     * @param - financialDocumentMonth10LineAmount The financialDocumentMonth10LineAmount to set.
     * 
     */
    public void setFinancialDocumentMonth10LineAmount(KualiDecimal financialDocumentMonth10LineAmount) {
        this.financialDocumentMonth10LineAmount = financialDocumentMonth10LineAmount;
    }


    /**
     * Gets the financialDocumentMonth11LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth11LineAmount
     * 
     */
    public KualiDecimal getFinancialDocumentMonth11LineAmount() {
        return financialDocumentMonth11LineAmount;
    }

    /**
     * Sets the financialDocumentMonth11LineAmount attribute.
     * 
     * @param - financialDocumentMonth11LineAmount The financialDocumentMonth11LineAmount to set.
     * 
     */
    public void setFinancialDocumentMonth11LineAmount(KualiDecimal financialDocumentMonth11LineAmount) {
        this.financialDocumentMonth11LineAmount = financialDocumentMonth11LineAmount;
    }


    /**
     * Gets the financialDocumentMonth12LineAmount attribute.
     * 
     * @return - Returns the financialDocumentMonth12LineAmount
     * 
     */
    public KualiDecimal getFinancialDocumentMonth12LineAmount() {
        return financialDocumentMonth12LineAmount;
    }

    /**
     * Sets the financialDocumentMonth12LineAmount attribute.
     * 
     * @param - financialDocumentMonth12LineAmount The financialDocumentMonth12LineAmount to set.
     * 
     */
    public void setFinancialDocumentMonth12LineAmount(KualiDecimal financialDocumentMonth12LineAmount) {
        this.financialDocumentMonth12LineAmount = financialDocumentMonth12LineAmount;
    }


    /**
     * Gets the pendingBudgetConstructionGl attribute.
     * 
     * @return - Returns the pendingBudgetConstructionGl
     * 
     */
    public PendingBudgetConstructionGl getPendingBudgetConstructionGl() {
        return pendingBudgetConstructionGl;
    }

    /**
     * Sets the pendingBudgetConstructionGl attribute.
     * 
     * @param - pendingBudgetConstructionGl The pendingBudgetConstructionGl to set.
     * @deprecated
     */
    public void setPendingBudgetConstructionGl(PendingBudgetConstructionGl pendingBudgetConstructionGl) {
        this.pendingBudgetConstructionGl = pendingBudgetConstructionGl;
    }

    /**
     * Gets the financialBalanceType attribute.
     * 
     * @return - Returns the financialBalanceType
     * 
     */
    public Balance getFinancialBalanceType() {
        return financialBalanceType;
    }

    /**
     * Sets the financialBalanceType attribute.
     * 
     * @param - financialBalanceType The financialBalanceType to set.
     * @deprecated
     */
    public void setFinancialBalanceType(Balance financialBalanceType) {
        this.financialBalanceType = financialBalanceType;
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
     * 
     * @return Returns the financialSubObject.
     */
    public SubObjCd getFinancialSubObject() {
        return financialSubObject;
    }

    /**
     * Sets the financialSubObject attribute value.
     * 
     * @param financialSubObject The financialSubObject to set.
     * @deprecated
     */
    public void setFinancialSubObject(SubObjCd financialSubObject) {
        this.financialSubObject = financialSubObject;
    }

    /**
     * Gets the subAccount attribute.
     * 
     * @return Returns the subAccount.
     */
    public SubAccount getSubAccount() {
        return subAccount;
    }

    /**
     * Sets the subAccount attribute value.
     * 
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
        m.put("financialDocumentNumber", this.financialDocumentNumber);
        if (this.universityFiscalYear != null) {
            m.put("universityFiscalYear", this.universityFiscalYear.toString());
        }
        m.put("chartOfAccountsCode", this.chartOfAccountsCode);
        m.put("accountNumber", this.accountNumber);
        m.put("subAccountNumber", this.subAccountNumber);
        m.put("financialObjectCode", this.financialObjectCode);
        m.put("financialSubObjectCode", this.financialSubObjectCode);
        m.put("financialBalanceTypeCode", this.financialBalanceTypeCode);
        m.put("financialObjectTypeCode", this.financialObjectTypeCode);
        return m;
    }


}
