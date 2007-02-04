/*
 * Copyright 2005-2007 The Kuali Foundation.
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

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.Constants;
import org.kuali.PropertyConstants;
import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kfs.bo.Options;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.Chart;
import org.kuali.module.chart.bo.ObjectCode;
import org.kuali.module.chart.bo.ObjectType;
import org.kuali.module.chart.bo.SubAccount;
import org.kuali.module.chart.bo.SubObjCd;
import org.kuali.module.chart.bo.codes.BalanceTyp;

/**
 * 
 * 
 */
public class Balance extends PersistableBusinessObjectBase {
    static final long serialVersionUID = 6581797610149985575L;

    private Integer universityFiscalYear;
    private String chartOfAccountsCode;
    private String accountNumber;
    private String subAccountNumber;
    private String objectCode;
    private String subObjectCode;
    private String balanceTypeCode;
    private String objectTypeCode;
    private KualiDecimal accountLineAnnualBalanceAmount;
    private KualiDecimal beginningBalanceLineAmount;
    private KualiDecimal contractsGrantsBeginningBalanceAmount;
    private KualiDecimal month1Amount;
    private KualiDecimal month2Amount;
    private KualiDecimal month3Amount;
    private KualiDecimal month4Amount;
    private KualiDecimal month5Amount;
    private KualiDecimal month6Amount;
    private KualiDecimal month7Amount;
    private KualiDecimal month8Amount;
    private KualiDecimal month9Amount;
    private KualiDecimal month10Amount;
    private KualiDecimal month11Amount;
    private KualiDecimal month12Amount;
    private KualiDecimal month13Amount;
    private Date timestamp;

    private Chart chart;
    private Account account;
    private ObjectCode financialObject;
    private SubObjCd financialSubObject;
    private SubAccount subAccount;
    private BalanceTyp balanceType;
    private ObjectType objectType;

    private TransientBalanceInquiryAttributes dummyBusinessObject;
    private Options option;

    /**
     * @return Returns the options.
     */
    public Options getOption() {
        return option;
    }

    /**
     * @param options The options to set.
     */
    public void setOption(Options opt) {
        this.option = opt;
    }

    public Balance() {
        accountLineAnnualBalanceAmount = new KualiDecimal(0);
        beginningBalanceLineAmount = new KualiDecimal(0);
        contractsGrantsBeginningBalanceAmount = new KualiDecimal(0);
        month1Amount = new KualiDecimal(0);
        month2Amount = new KualiDecimal(0);
        month3Amount = new KualiDecimal(0);
        month4Amount = new KualiDecimal(0);
        month5Amount = new KualiDecimal(0);
        month6Amount = new KualiDecimal(0);
        month7Amount = new KualiDecimal(0);
        month8Amount = new KualiDecimal(0);
        month9Amount = new KualiDecimal(0);
        month10Amount = new KualiDecimal(0);
        month11Amount = new KualiDecimal(0);
        month12Amount = new KualiDecimal(0);
        month13Amount = new KualiDecimal(0);
        this.dummyBusinessObject = new TransientBalanceInquiryAttributes();
    }

    public Balance(Transaction t) {
        this();
        setUniversityFiscalYear(t.getUniversityFiscalYear());
        setChartOfAccountsCode(t.getChartOfAccountsCode());
        setAccountNumber(t.getAccountNumber());
        setSubAccountNumber(t.getSubAccountNumber());
        setObjectCode(t.getFinancialObjectCode());
        setSubObjectCode(t.getFinancialSubObjectCode());
        setBalanceTypeCode(t.getFinancialBalanceTypeCode());
        setObjectTypeCode(t.getFinancialObjectTypeCode());
    }

    protected LinkedHashMap toStringMapper() {
        LinkedHashMap map = new LinkedHashMap();
        map.put(PropertyConstants.UNIVERSITY_FISCAL_YEAR, getUniversityFiscalYear());
        map.put(PropertyConstants.CHART_OF_ACCOUNTS_CODE, getChartOfAccountsCode());
        map.put(PropertyConstants.ACCOUNT_NUMBER, getAccountNumber());
        map.put(PropertyConstants.SUB_ACCOUNT_NUMBER, getSubAccountNumber());
        map.put(PropertyConstants.OBJECT_CODE, getObjectCode());
        map.put(PropertyConstants.SUB_OBJECT_CODE, getSubObjectCode());
        map.put(PropertyConstants.BALANCE_TYPE_CODE, getBalanceTypeCode());
        map.put(PropertyConstants.OBJECT_TYPE_CODE, getObjectTypeCode());
        return map;
    }

    public KualiDecimal getAmount(String period) {
        if (Constants.ANNUAL_BALANCE.equals(period)) {
            return getAccountLineAnnualBalanceAmount();
        }
        else if (Constants.BEGINNING_BALANCE.equals(period)) {
            return getBeginningBalanceLineAmount();
        }
        else if (Constants.CG_BEGINNING_BALANCE.equals(period)) {
            return getContractsGrantsBeginningBalanceAmount();
        }
        else if (Constants.MONTH1.equals(period)) {
            return getMonth1Amount();
        }
        else if (Constants.MONTH2.equals(period)) {
            return getMonth2Amount();
        }
        else if (Constants.MONTH3.equals(period)) {
            return getMonth3Amount();
        }
        else if (Constants.MONTH4.equals(period)) {
            return getMonth4Amount();
        }
        else if (Constants.MONTH5.equals(period)) {
            return getMonth5Amount();
        }
        else if (Constants.MONTH6.equals(period)) {
            return getMonth6Amount();
        }
        else if (Constants.MONTH7.equals(period)) {
            return getMonth7Amount();
        }
        else if (Constants.MONTH8.equals(period)) {
            return getMonth8Amount();
        }
        else if (Constants.MONTH9.equals(period)) {
            return getMonth9Amount();
        }
        else if (Constants.MONTH10.equals(period)) {
            return getMonth10Amount();
        }
        else if (Constants.MONTH11.equals(period)) {
            return getMonth11Amount();
        }
        else if (Constants.MONTH12.equals(period)) {
            return getMonth12Amount();
        }
        else if (Constants.MONTH13.equals(period)) {
            return getMonth13Amount();
        }
        else {
            return null;
        }
    }

    public void addAmount(String period, KualiDecimal amount) {

        if (Constants.ANNUAL_BALANCE.equals(period)) {
            accountLineAnnualBalanceAmount = accountLineAnnualBalanceAmount.add(amount);
        }
        else if (Constants.BEGINNING_BALANCE.equals(period)) {
            beginningBalanceLineAmount = beginningBalanceLineAmount.add(amount);
        }
        else if (Constants.CG_BEGINNING_BALANCE.equals(period)) {
            contractsGrantsBeginningBalanceAmount = contractsGrantsBeginningBalanceAmount.add(amount);
        }
        else if (Constants.MONTH1.equals(period)) {
            month1Amount = month1Amount.add(amount);
            accountLineAnnualBalanceAmount = accountLineAnnualBalanceAmount.add(amount);
        }
        else if (Constants.MONTH2.equals(period)) {
            month2Amount = month2Amount.add(amount);
            accountLineAnnualBalanceAmount = accountLineAnnualBalanceAmount.add(amount);
        }
        else if (Constants.MONTH3.equals(period)) {
            month3Amount = month3Amount.add(amount);
            accountLineAnnualBalanceAmount = accountLineAnnualBalanceAmount.add(amount);
        }
        else if (Constants.MONTH4.equals(period)) {
            month4Amount = month4Amount.add(amount);
            accountLineAnnualBalanceAmount = accountLineAnnualBalanceAmount.add(amount);
        }
        else if (Constants.MONTH5.equals(period)) {
            month5Amount = month5Amount.add(amount);
            accountLineAnnualBalanceAmount = accountLineAnnualBalanceAmount.add(amount);
        }
        else if (Constants.MONTH6.equals(period)) {
            month6Amount = month6Amount.add(amount);
            accountLineAnnualBalanceAmount = accountLineAnnualBalanceAmount.add(amount);
        }
        else if (Constants.MONTH7.equals(period)) {
            month7Amount = month7Amount.add(amount);
            accountLineAnnualBalanceAmount = accountLineAnnualBalanceAmount.add(amount);
        }
        else if (Constants.MONTH8.equals(period)) {
            month8Amount = month8Amount.add(amount);
            accountLineAnnualBalanceAmount = accountLineAnnualBalanceAmount.add(amount);
        }
        else if (Constants.MONTH9.equals(period)) {
            month9Amount = month9Amount.add(amount);
            accountLineAnnualBalanceAmount = accountLineAnnualBalanceAmount.add(amount);
        }
        else if (Constants.MONTH10.equals(period)) {
            month10Amount = month10Amount.add(amount);
            accountLineAnnualBalanceAmount = accountLineAnnualBalanceAmount.add(amount);
        }
        else if (Constants.MONTH11.equals(period)) {
            month11Amount = month11Amount.add(amount);
            accountLineAnnualBalanceAmount = accountLineAnnualBalanceAmount.add(amount);
        }
        else if (Constants.MONTH12.equals(period)) {
            month12Amount = month12Amount.add(amount);
            accountLineAnnualBalanceAmount = accountLineAnnualBalanceAmount.add(amount);
        }
        else if (Constants.MONTH13.equals(period)) {
            month13Amount = month13Amount.add(amount);
            accountLineAnnualBalanceAmount = accountLineAnnualBalanceAmount.add(amount);
        }
    }

    /**
     * @return Returns the accountLineAnnualBalanceAmount.
     */
    public KualiDecimal getAccountLineAnnualBalanceAmount() {
        return accountLineAnnualBalanceAmount;
    }

    /**
     * @param accountLineAnnualBalanceAmount The accountLineAnnualBalanceAmount to set.
     */
    public void setAccountLineAnnualBalanceAmount(KualiDecimal accountLineAnnualBalanceAmount) {
        this.accountLineAnnualBalanceAmount = accountLineAnnualBalanceAmount;
    }

    /**
     * @return Returns the accountNumber.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber The accountNumber to set.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return Returns the balanceTypeCode.
     */
    public String getBalanceTypeCode() {
        return balanceTypeCode;
    }

    /**
     * @param balanceTypeCode The balanceTypeCode to set.
     */
    public void setBalanceTypeCode(String balanceTypeCode) {
        this.balanceTypeCode = balanceTypeCode;
    }

    /**
     * @return Returns the beginningBalanceLineAmount.
     */
    public KualiDecimal getBeginningBalanceLineAmount() {
        return beginningBalanceLineAmount;
    }

    /**
     * @param beginningBalanceLineAmount The beginningBalanceLineAmount to set.
     */
    public void setBeginningBalanceLineAmount(KualiDecimal beginningBalanceLineAmount) {
        this.beginningBalanceLineAmount = beginningBalanceLineAmount;
    }

    /**
     * @return Returns the chartOfAccountsCode.
     */
    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }

    /**
     * @param chartOfAccountsCode The chartOfAccountsCode to set.
     */
    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }

    /**
     * @return Returns the contractsGrantsBeginningBalanceAmount.
     */
    public KualiDecimal getContractsGrantsBeginningBalanceAmount() {
        return contractsGrantsBeginningBalanceAmount;
    }

    /**
     * @param contractsGrantsBeginningBalanceAmount The contractsGrantsBeginningBalanceAmount to set.
     */
    public void setContractsGrantsBeginningBalanceAmount(KualiDecimal contractsGrantsBeginningBalanceAmount) {
        this.contractsGrantsBeginningBalanceAmount = contractsGrantsBeginningBalanceAmount;
    }

    /**
     * @return Returns the month10Amount.
     */
    public KualiDecimal getMonth10Amount() {
        return month10Amount;
    }

    /**
     * @param month10Amount The month10Amount to set.
     */
    public void setMonth10Amount(KualiDecimal month10Amount) {
        this.month10Amount = month10Amount;
    }

    /**
     * @return Returns the month11Amount.
     */
    public KualiDecimal getMonth11Amount() {
        return month11Amount;
    }

    /**
     * @param month11Amount The month11Amount to set.
     */
    public void setMonth11Amount(KualiDecimal month11Amount) {
        this.month11Amount = month11Amount;
    }

    /**
     * @return Returns the month12Amount.
     */
    public KualiDecimal getMonth12Amount() {
        return month12Amount;
    }

    /**
     * @param month12Amount The month12Amount to set.
     */
    public void setMonth12Amount(KualiDecimal month12Amount) {
        this.month12Amount = month12Amount;
    }

    /**
     * @return Returns the month13Amount.
     */
    public KualiDecimal getMonth13Amount() {
        return month13Amount;
    }

    /**
     * @param month13Amount The month13Amount to set.
     */
    public void setMonth13Amount(KualiDecimal month13Amount) {
        this.month13Amount = month13Amount;
    }

    /**
     * @return Returns the month1Amount.
     */
    public KualiDecimal getMonth1Amount() {
        return month1Amount;
    }

    /**
     * @param month1Amount The month1Amount to set.
     */
    public void setMonth1Amount(KualiDecimal month1Amount) {
        this.month1Amount = month1Amount;
    }

    /**
     * @return Returns the month2Amount.
     */
    public KualiDecimal getMonth2Amount() {
        return month2Amount;
    }

    /**
     * @param month2Amount The month2Amount to set.
     */
    public void setMonth2Amount(KualiDecimal month2Amount) {
        this.month2Amount = month2Amount;
    }

    /**
     * @return Returns the month3Amount.
     */
    public KualiDecimal getMonth3Amount() {
        return month3Amount;
    }

    /**
     * @param month3Amount The month3Amount to set.
     */
    public void setMonth3Amount(KualiDecimal month3Amount) {
        this.month3Amount = month3Amount;
    }

    /**
     * @return Returns the month4Amount.
     */
    public KualiDecimal getMonth4Amount() {
        return month4Amount;
    }

    /**
     * @param month4Amount The month4Amount to set.
     */
    public void setMonth4Amount(KualiDecimal month4Amount) {
        this.month4Amount = month4Amount;
    }

    /**
     * @return Returns the month5Amount.
     */
    public KualiDecimal getMonth5Amount() {
        return month5Amount;
    }

    /**
     * @param month5Amount The month5Amount to set.
     */
    public void setMonth5Amount(KualiDecimal month5Amount) {
        this.month5Amount = month5Amount;
    }

    /**
     * @return Returns the month6Amount.
     */
    public KualiDecimal getMonth6Amount() {
        return month6Amount;
    }

    /**
     * @param month6Amount The month6Amount to set.
     */
    public void setMonth6Amount(KualiDecimal month6Amount) {
        this.month6Amount = month6Amount;
    }

    /**
     * @return Returns the month7Amount.
     */
    public KualiDecimal getMonth7Amount() {
        return month7Amount;
    }

    /**
     * @param month7Amount The month7Amount to set.
     */
    public void setMonth7Amount(KualiDecimal month7Amount) {
        this.month7Amount = month7Amount;
    }

    /**
     * @return Returns the month8Amount.
     */
    public KualiDecimal getMonth8Amount() {
        return month8Amount;
    }

    /**
     * @param month8Amount The month8Amount to set.
     */
    public void setMonth8Amount(KualiDecimal month8Amount) {
        this.month8Amount = month8Amount;
    }

    /**
     * @return Returns the month9Amount.
     */
    public KualiDecimal getMonth9Amount() {
        return month9Amount;
    }

    /**
     * @param month9Amount The month9Amount to set.
     */
    public void setMonth9Amount(KualiDecimal month9Amount) {
        this.month9Amount = month9Amount;
    }

    /**
     * @return Returns the objectCode.
     */
    public String getObjectCode() {
        return objectCode;
    }

    /**
     * @param objectCode The objectCode to set.
     */
    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    /**
     * @return Returns the objectTypeCode.
     */
    public String getObjectTypeCode() {
        return objectTypeCode;
    }

    /**
     * @param objectTypeCode The objectTypeCode to set.
     */
    public void setObjectTypeCode(String objectTypeCode) {
        this.objectTypeCode = objectTypeCode;
    }

    /**
     * @return Returns the subAccountNumber.
     */
    public String getSubAccountNumber() {
        return subAccountNumber;
    }

    /**
     * @param subAccountNumber The subAccountNumber to set.
     */
    public void setSubAccountNumber(String subAccountNumber) {
        this.subAccountNumber = subAccountNumber;
    }

    /**
     * @return Returns the subObjectCode.
     */
    public String getSubObjectCode() {
        return subObjectCode;
    }

    /**
     * @param subObjectCode The subObjectCode to set.
     */
    public void setSubObjectCode(String subObjectCode) {
        this.subObjectCode = subObjectCode;
    }

    /**
     * @return Returns the timestamp.
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp The timestamp to set.
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return Returns the universityFiscalYear.
     */
    public Integer getUniversityFiscalYear() {
        return universityFiscalYear;
    }

    /**
     * @param universityFiscalYear The universityFiscalYear to set.
     */
    public void setUniversityFiscalYear(Integer universityFiscalYear) {
        this.universityFiscalYear = universityFiscalYear;
    }

    /**
     * Gets the chart attribute.
     * 
     * @return Returns the chart.
     */
    public Chart getChart() {
        return chart;
    }

    /**
     * Sets the chart attribute value.
     * 
     * @param chart The chart to set.
     */
    public void setChart(Chart chart) {
        this.chart = chart;
    }

    /**
     * Gets the account attribute.
     * 
     * @return Returns the account.
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Sets the account attribute value.
     * 
     * @param account The account to set.
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Gets the dummyBusinessObject attribute.
     * 
     * @return Returns the dummyBusinessObject.
     */
    public TransientBalanceInquiryAttributes getDummyBusinessObject() {
        return dummyBusinessObject;
    }

    /**
     * Sets the dummyBusinessObject attribute value.
     * 
     * @param dummyBusinessObject The dummyBusinessObject to set.
     */
    public void setDummyBusinessObject(TransientBalanceInquiryAttributes dummyBusinessObject) {
        this.dummyBusinessObject = dummyBusinessObject;
    }

    /**
     * Gets the financialObject attribute.
     * 
     * @return Returns the financialObject.
     */
    public ObjectCode getFinancialObject() {
        return financialObject;
    }

    /**
     * Sets the financialObject attribute value.
     * 
     * @param financialObject The financialObject to set.
     */
    public void setFinancialObject(ObjectCode financialObject) {
        this.financialObject = financialObject;
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
     */
    public void setBalanceType(BalanceTyp balanceType) {
        this.balanceType = balanceType;
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
     */
    public void setSubAccount(SubAccount subAccount) {
        this.subAccount = subAccount;
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
     */
    public void setObjectType(ObjectType objectType) {
        this.objectType = objectType;
    }
}
