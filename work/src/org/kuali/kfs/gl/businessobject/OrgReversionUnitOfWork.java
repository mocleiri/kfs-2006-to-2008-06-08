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
package org.kuali.module.gl.bo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.core.util.KualiDecimal;
import org.kuali.module.chart.bo.OrganizationReversionCategory;

public class OrgReversionUnitOfWork extends PersistableBusinessObjectBase {
    public String chartOfAccountsCode = "";
    public String accountNumber = "";
    public String subAccountNumber = "";
    public Map<String, OrgReversionUnitOfWorkCategoryAmount> amounts;
    private KualiDecimal totalReversion;
    private KualiDecimal totalCarryForward;
    private KualiDecimal totalAvailable;
    private KualiDecimal totalCash;

    public OrgReversionUnitOfWork() {
        amounts = new HashMap<String, OrgReversionUnitOfWorkCategoryAmount>();
    }

    public OrgReversionUnitOfWork(String chart, String acct, String subAcct) {
        this();
        chartOfAccountsCode = chart;
        accountNumber = acct;
        subAccountNumber = subAcct;
    }

    public void setFields(String chart, String acct, String subAcct) {
        chartOfAccountsCode = chart;
        accountNumber = acct;
        subAccountNumber = subAcct;
        clearAmounts();
    }

    public void setCategories(List<OrganizationReversionCategory> cats) {
        for (Iterator<OrganizationReversionCategory> iter = cats.iterator(); iter.hasNext();) {
            OrganizationReversionCategory element = iter.next();
            OrgReversionUnitOfWorkCategoryAmount ca = new OrgReversionUnitOfWorkCategoryAmount(this.chartOfAccountsCode, this.accountNumber, this.subAccountNumber, element.getOrganizationReversionCategoryCode());
            amounts.put(element.getOrganizationReversionCategoryCode(), ca);
        }
    }

    public void addActualAmount(String categoryCode, KualiDecimal amount) {
        OrgReversionUnitOfWorkCategoryAmount ca = amounts.get(categoryCode);
        ca.setActual(ca.getActual().add(amount));
    }

    public void addBudgetAmount(String categoryCode, KualiDecimal amount) {
        OrgReversionUnitOfWorkCategoryAmount ca = amounts.get(categoryCode);
        ca.setBudget(ca.getBudget().add(amount));
    }

    public void addEncumbranceAmount(String categoryCode, KualiDecimal amount) {
        OrgReversionUnitOfWorkCategoryAmount ca = amounts.get(categoryCode);
        ca.setEncumbrance(ca.getEncumbrance().add(amount));
    }

    public void addCarryForwardAmount(String categoryCode, KualiDecimal amount) {
        OrgReversionUnitOfWorkCategoryAmount ca = amounts.get(categoryCode);
        ca.setCarryForward(ca.getCarryForward().add(amount));
    }

    public void clearAmounts() {
        totalAvailable = KualiDecimal.ZERO;
        totalCarryForward = KualiDecimal.ZERO;
        totalCash = KualiDecimal.ZERO;
        totalReversion = KualiDecimal.ZERO;

        for (Iterator<OrgReversionUnitOfWorkCategoryAmount> iter = amounts.values().iterator(); iter.hasNext();) {
            OrgReversionUnitOfWorkCategoryAmount element = iter.next();
            element.setActual(KualiDecimal.ZERO);
            element.setBudget(KualiDecimal.ZERO);
            element.setEncumbrance(KualiDecimal.ZERO);
        }
    }

    public boolean isSame(String chart, String acct, String subAcct) {
        return (chartOfAccountsCode.equals(chart) && accountNumber.equals(acct) && subAccountNumber.equals(subAcct));
    }

    public KualiDecimal getTotalAccountAvailable() {
        KualiDecimal amount = KualiDecimal.ZERO;
        for (Iterator<OrgReversionUnitOfWorkCategoryAmount> iter = amounts.values().iterator(); iter.hasNext();) {
            OrgReversionUnitOfWorkCategoryAmount element = iter.next();
            amount = amount.add(element.getAvailable());
        }
        return amount;
    }

    public KualiDecimal getTotalCarryForward() {
        return totalCarryForward;
    }

    public void setTotalCarryForward(KualiDecimal totalCarryForward) {
        this.totalCarryForward = totalCarryForward;
    }

    public KualiDecimal getTotalReversion() {
        return totalReversion;
    }

    public void addTotalCarryForward(KualiDecimal amount) {
        totalCarryForward = totalCarryForward.add(amount);
    }

    public void setTotalReversion(KualiDecimal totalReversion) {
        this.totalReversion = totalReversion;
    }

    public void addTotalReversion(KualiDecimal amount) {
        totalReversion = totalReversion.add(amount);
    }

    public KualiDecimal getTotalAvailable() {
        return totalAvailable;
    }

    public void addTotalAvailable(KualiDecimal amount) {
        totalAvailable = totalAvailable.add(amount);
    }

    public void setTotalAvailable(KualiDecimal totalAvailable) {
        this.totalAvailable = totalAvailable;
    }

    public void addTotalCash(KualiDecimal amount) {
        totalCash = totalCash.add(amount);
    }

    public KualiDecimal getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(KualiDecimal totalCash) {
        this.totalCash = totalCash;
    }

    public Map<String, OrgReversionUnitOfWorkCategoryAmount> getCategoryAmounts() {
        return amounts;
    }
    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    public LinkedHashMap toStringMapper() {
        LinkedHashMap pkMap = new LinkedHashMap();
        pkMap.put("chartOfAccountsCode", this.chartOfAccountsCode);
        pkMap.put("accountNbr", this.accountNumber);
        pkMap.put("subAccountNbr", this.subAccountNumber);
        return pkMap;
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
    
}
