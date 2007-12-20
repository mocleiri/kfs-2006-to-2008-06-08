/*
 * Copyright 2006-2007 The Kuali Foundation.
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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.util.KualiInteger;
import org.kuali.core.util.TypedArrayList;
import org.kuali.kfs.KFSPropertyConstants;
import org.kuali.kfs.context.SpringContext;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.Chart;
import org.kuali.module.chart.bo.ObjectCode;
import org.kuali.module.chart.bo.ObjectType;
import org.kuali.module.chart.bo.SubAccount;
import org.kuali.module.chart.bo.SubObjCd;
import org.kuali.module.chart.bo.codes.BalanceTyp;
import org.kuali.module.labor.bo.LaborObject;
import org.kuali.module.labor.bo.PositionObjectBenefit;


/**
 * 
 */
public class PendingBudgetConstructionGeneralLedger extends PersistableBusinessObjectBase {

    private String documentNumber;
    private Integer universityFiscalYear;
    private String chartOfAccountsCode;
    private String accountNumber;
    private String subAccountNumber;
    private String financialObjectCode;
    private String financialSubObjectCode;
    private String financialBalanceTypeCode;
    private String financialObjectTypeCode;
    private KualiInteger accountLineAnnualBalanceAmount;
    private KualiInteger financialBeginningBalanceLineAmount;

    private BudgetConstructionHeader budgetConstructionHeader;
    private ObjectCode financialObject;
    private Chart chartOfAccounts;
    private Account account;
    private SubAccount subAccount;
    private SubObjCd financialSubObject;
    private BalanceTyp balanceType;
    private ObjectType objectType;

    private List budgetConstructionMonthly;

    // TODO These are only used by PBGLExpenditureLines so should probably put these in an extension class
    // These are not defined under ojb since not all expenditure line objects have these
    private LaborObject laborObject;
    private List<PositionObjectBenefit> positionObjectBenefit;

    private KualiDecimal percentChange;

    /**
     * Default constructor.
     */
    public PendingBudgetConstructionGeneralLedger() {
        setBudgetConstructionMonthly(new TypedArrayList(BudgetConstructionMonthly.class));
        setPercentChange(null);

    }

    /**
     * Gets(sets) the percentChange based on the current values of base and request amounts
     * 
     * @return Returns percentChange
     */
    public KualiDecimal getPercentChange() {

        if (financialBeginningBalanceLineAmount == null || financialBeginningBalanceLineAmount.isZero()) {
            setPercentChange(null);
        }
        else {
            BigDecimal diffRslt = (accountLineAnnualBalanceAmount.bigDecimalValue().setScale(4)).subtract(financialBeginningBalanceLineAmount.bigDecimalValue().setScale(4));
            BigDecimal divRslt = diffRslt.divide((financialBeginningBalanceLineAmount.bigDecimalValue().setScale(4)), KualiDecimal.ROUND_BEHAVIOR);
            setPercentChange(new KualiDecimal(divRslt.multiply(BigDecimal.valueOf(100)).setScale(2)));
        }
        return percentChange;
    }

    /**
     * Sets the percentChange attribute value.
     * 
     * @param percentChange The percentChange to set.
     * @deprecated
     */
    public void setPercentChange(KualiDecimal percentChange) {
        this.percentChange = percentChange;
    }

    /**
     * Gets the documentNumber attribute.
     * 
     * @return Returns the documentNumber
     */
    public String getDocumentNumber() {
        return documentNumber;
    }

    /**
     * Sets the documentNumber attribute.
     * 
     * @param documentNumber The documentNumber to set.
     */
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }


    /**
     * Gets the universityFiscalYear attribute.
     * 
     * @return Returns the universityFiscalYear
     */
    public Integer getUniversityFiscalYear() {
        return universityFiscalYear;
    }

    /**
     * Sets the universityFiscalYear attribute.
     * 
     * @param universityFiscalYear The universityFiscalYear to set.
     */
    public void setUniversityFiscalYear(Integer universityFiscalYear) {
        this.universityFiscalYear = universityFiscalYear;
    }


    /**
     * Gets the chartOfAccountsCode attribute.
     * 
     * @return Returns the chartOfAccountsCode
     */
    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }

    /**
     * Sets the chartOfAccountsCode attribute.
     * 
     * @param chartOfAccountsCode The chartOfAccountsCode to set.
     */
    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }


    /**
     * Gets the accountNumber attribute.
     * 
     * @return Returns the accountNumber
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the accountNumber attribute.
     * 
     * @param accountNumber The accountNumber to set.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the subAccountNumber attribute.
     * 
     * @return Returns the subAccountNumber
     */
    public String getSubAccountNumber() {
        return subAccountNumber;
    }

    /**
     * Sets the subAccountNumber attribute.
     * 
     * @param subAccountNumber The subAccountNumber to set.
     */
    public void setSubAccountNumber(String subAccountNumber) {
        this.subAccountNumber = subAccountNumber;
    }


    /**
     * Gets the financialObjectCode attribute.
     * 
     * @return Returns the financialObjectCode
     */
    public String getFinancialObjectCode() {
        return financialObjectCode;
    }

    /**
     * Sets the financialObjectCode attribute.
     * 
     * @param financialObjectCode The financialObjectCode to set.
     */
    public void setFinancialObjectCode(String financialObjectCode) {
        this.financialObjectCode = financialObjectCode;
    }


    /**
     * Gets the financialSubObjectCode attribute.
     * 
     * @return Returns the financialSubObjectCode
     */
    public String getFinancialSubObjectCode() {
        return financialSubObjectCode;
    }

    /**
     * Sets the financialSubObjectCode attribute.
     * 
     * @param financialSubObjectCode The financialSubObjectCode to set.
     */
    public void setFinancialSubObjectCode(String financialSubObjectCode) {
        this.financialSubObjectCode = financialSubObjectCode;
    }


    /**
     * Gets the financialBalanceTypeCode attribute.
     * 
     * @return Returns the financialBalanceTypeCode
     */
    public String getFinancialBalanceTypeCode() {
        return financialBalanceTypeCode;
    }

    /**
     * Sets the financialBalanceTypeCode attribute.
     * 
     * @param financialBalanceTypeCode The financialBalanceTypeCode to set.
     */
    public void setFinancialBalanceTypeCode(String financialBalanceTypeCode) {
        this.financialBalanceTypeCode = financialBalanceTypeCode;
    }


    /**
     * Gets the financialObjectTypeCode attribute.
     * 
     * @return Returns the financialObjectTypeCode
     */
    public String getFinancialObjectTypeCode() {
        return financialObjectTypeCode;
    }

    /**
     * Sets the financialObjectTypeCode attribute.
     * 
     * @param financialObjectTypeCode The financialObjectTypeCode to set.
     */
    public void setFinancialObjectTypeCode(String financialObjectTypeCode) {
        this.financialObjectTypeCode = financialObjectTypeCode;
    }


    /**
     * Gets the accountLineAnnualBalanceAmount attribute.
     * 
     * @return Returns the accountLineAnnualBalanceAmount.
     */
    public KualiInteger getAccountLineAnnualBalanceAmount() {
        return accountLineAnnualBalanceAmount;
    }

    /**
     * Sets the accountLineAnnualBalanceAmount attribute value.
     * 
     * @param accountLineAnnualBalanceAmount The accountLineAnnualBalanceAmount to set.
     */
    public void setAccountLineAnnualBalanceAmount(KualiInteger accountLineAnnualBalanceAmount) {
        this.accountLineAnnualBalanceAmount = accountLineAnnualBalanceAmount;
    }

    /**
     * Gets the financialBeginningBalanceLineAmount attribute.
     * 
     * @return Returns the financialBeginningBalanceLineAmount.
     */
    public KualiInteger getFinancialBeginningBalanceLineAmount() {
        return financialBeginningBalanceLineAmount;
    }

    /**
     * Sets the financialBeginningBalanceLineAmount attribute value.
     * 
     * @param financialBeginningBalanceLineAmount The financialBeginningBalanceLineAmount to set.
     */
    public void setFinancialBeginningBalanceLineAmount(KualiInteger financialBeginningBalanceLineAmount) {
        this.financialBeginningBalanceLineAmount = financialBeginningBalanceLineAmount;
    }

    /**
     * Gets the budgetConstructionMonthly attribute.
     * 
     * @return Returns the budgetConstructionMonthly
     */
    public List<BudgetConstructionMonthly> getBudgetConstructionMonthly() {
        return budgetConstructionMonthly;
    }

    /**
     * Sets the budgetConstructionMonthly attribute.
     * 
     * @param budgetConstructionMonthly The budgetConstructionMonthly to set.
     * @deprecated
     */
    public void setBudgetConstructionMonthly(List<BudgetConstructionMonthly> budgetConstructionMonthly) {
        this.budgetConstructionMonthly = budgetConstructionMonthly;
    }

    /**
     * Gets the financialObject attribute.
     * 
     * @return Returns the financialObject
     */
    public ObjectCode getFinancialObject() {
        return financialObject;
    }

    /**
     * Sets the financialObject attribute.
     * 
     * @param financialObject The financialObject to set.
     * @deprecated
     */
    public void setFinancialObject(ObjectCode financialObject) {
        this.financialObject = financialObject;
    }

    /**
     * Gets the chartOfAccounts attribute.
     * 
     * @return Returns the chartOfAccounts
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
     * Gets the balanceType attribute.
     * 
     * @return Returns the balanceType.
     */
    public BalanceTyp getBalanceType() {
        return balanceType;
    }

    /**
     * Sets the balanceType attribute value.
     * 
     * @param balanceType The balanceType to set.
     * @deprecated
     */
    public void setBalanceType(BalanceTyp balanceType) {
        this.balanceType = balanceType;
    }

    /**
     * Gets the objectType attribute.
     * 
     * @return Returns the objectType.
     */
    public ObjectType getObjectType() {
        return objectType;
    }

    /**
     * Sets the objectType attribute value.
     * 
     * @param objectType The objectType to set.
     * @deprecated
     */
    public void setObjectType(ObjectType objectType) {
        this.objectType = objectType;
    }

    /**
     * Gets the budgetConstructionHeader attribute.
     * 
     * @return Returns the budgetConstructionHeader.
     */
    public BudgetConstructionHeader getBudgetConstructionHeader() {
        return budgetConstructionHeader;
    }

    /**
     * Sets the budgetConstructionHeader attribute value.
     * 
     * @param budgetConstructionHeader The budgetConstructionHeader to set.
     * @deprecated
     */
    public void setBudgetConstructionHeader(BudgetConstructionHeader budgetConstructionHeader) {
        this.budgetConstructionHeader = budgetConstructionHeader;
    }

    /**
     * Gets the laborObject attribute.
     * 
     * @return Returns the laborObject.
     */
    public LaborObject getLaborObject() {
        if (laborObject == null) {
            Map pkeys = new HashMap();
            pkeys.put("universityFiscalYear", getUniversityFiscalYear());
            pkeys.put("chartOfAccountsCode", getChartOfAccountsCode());
            pkeys.put("financialObjectCode", getFinancialObjectCode());

            setLaborObject((LaborObject) SpringContext.getBean(BusinessObjectService.class).findByPrimaryKey(LaborObject.class, pkeys));

        }
        return laborObject;
    }

    /**
     * Sets the laborObject attribute value.
     * 
     * @param laborObject The laborObject to set.
     */
    public void setLaborObject(LaborObject laborObject) {
        this.laborObject = laborObject;
    }

    /**
     * Gets the positionObjectBenefit attribute.
     * 
     * @return Returns the positionObjectBenefit.
     */
    public List<PositionObjectBenefit> getPositionObjectBenefit() {
        if (positionObjectBenefit == null) {
            Map fieldValues = new HashMap();
            fieldValues.put("universityFiscalYear", getUniversityFiscalYear());
            fieldValues.put("chartOfAccountsCode", getChartOfAccountsCode());
            fieldValues.put("financialObjectCode", getFinancialObjectCode());

            setPositionObjectBenefit((List<PositionObjectBenefit>) SpringContext.getBean(BusinessObjectService.class).findMatching(PositionObjectBenefit.class, fieldValues));

        }
        return positionObjectBenefit;
    }

    /**
     * Sets the positionObjectBenefit attribute value.
     * 
     * @param positionObjectBenefit The positionObjectBenefit to set.
     */
    public void setPositionObjectBenefit(List<PositionObjectBenefit> positionObjectBenefit) {
        this.positionObjectBenefit = positionObjectBenefit;
    }


    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();
        m.put(KFSPropertyConstants.DOCUMENT_NUMBER, this.documentNumber);
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

    /**
     * Returns a map with the primitive field names as the key and the primitive values as the map value.
     * 
     * @return Map
     */
    public Map getValuesMap() {
        Map simpleValues = new HashMap();

        simpleValues.put(KFSPropertyConstants.DOCUMENT_NUMBER, getDocumentNumber());
        simpleValues.put("universityFiscalYear", getUniversityFiscalYear());
        simpleValues.put("chartOfAccountsCode", getChartOfAccountsCode());
        simpleValues.put("accountNumber", getAccountNumber());
        simpleValues.put("subAccountNumber", getSubAccountNumber());
        simpleValues.put("financialObjectCode", getFinancialObjectCode());
        simpleValues.put("financialSubObjectCode", getFinancialSubObjectCode());
        simpleValues.put("financialBalanceTypeCode", getFinancialBalanceTypeCode());
        simpleValues.put("financialObjectTypeCode", getFinancialObjectTypeCode());

        return simpleValues;
    }

}