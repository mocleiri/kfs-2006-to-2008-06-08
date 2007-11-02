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
package org.kuali.module.gl.dao;

import java.util.List;

import org.kuali.core.util.KualiDecimal;

public interface SufficientFundsDao {
    /**
     * fp_sasfc:49-2...54-3 m113 checking:calculates pfyr_budget
     * 
     * @param universityFiscalYear the university fiscal year of sufficient funds balances that will be summarized
     * @param chartOfAccountCode the chart of accounts code of sufficient fund balance records that will be summarized
     * @param accountNumber the account number of sufficient fund balances that will be summarized
     * @return the sum of 
     */
    public KualiDecimal calculateM113PfyrBudget(Integer universityFiscalYear, String chartOfAccountsCode, String accountNumber);

    /**
     * fp_sasfc:55-2...60-3 m113 checking: calculates pfyr_encum
     * 
     * @param universityFiscalYear the university fiscal year of sufficient funds balances to summarize
     * @param chartOfAccountsCode the chart of accounts code of sufficient funds balances to summarize
     * @param accountNumber the account number of sufficient fund balances to summarize
     * @return
     */
    public KualiDecimal calculateM113PfyrEncum(Integer universityFiscalYear, String chartOfAccountsCode, String accountNumber);

    /**
     * fp_sasfc:61-2...78-3 m113 checking: calculate pend_actual
     * 
     * @param universityFiscalYear the university fiscal year of sufficient funds balances to summarize
     * @param chartOfAccountsCode the chart of accounts code of sufficient funds balances to summarize
     * @param accountNumber the account number of sufficient fund balances to summarize
     * @param specialFinancialObjectCodes
     * @param financialObjectCodeForCashInBank TODO
     * @return
     */
    public KualiDecimal calculateM113PendActual(boolean financialBeginBalanceLoadInd, Integer universityFiscalYear, String chartOfAccountsCode, String accountNumber, List specialFinancialObjectCodes, String financialObjectCodeForCashInBank);

    /**
     * fp_sasfc:99-1...125-2
     * 
     * @param isYearEndDocument
     * @param actualFinancialBalanceTypeCd
     * @param universityFiscalYear the university fiscal year of sufficient funds balances to summarize
     * @param chartOfAccountsCode the chart of accounts code of sufficient funds balances to summarize
     * @param accountNumber the account number of sufficient fund balances to summarize
     * @param acctSufficientFundsFinObjCd
     * @param expenditureCodes
     * @return
     */
    public KualiDecimal calculatePendActual(boolean isYearEndDocument, String actualFinancialBalanceTypeCd, Integer universityFiscalYear, String chartOfAccountsCode, String accountNumber, String acctSufficientFundsFinObjCd, List expenditureCodes);

    /**
     * fp_sasfc: 126-2...140-2
     * 
     * @param isYearEndDocument
     * @param budgetCheckingBalanceTypeCd
     * @param universityFiscalYear the university fiscal year of sufficient funds balances to summarize
     * @param chartOfAccountsCode the chart of accounts code of sufficient funds balances to summarize
     * @param accountNumber the account number of sufficient fund balances to summarize
     * @param acctSufficientFundsFinObjCd
     * @param expenditureCodes
     * @return
     */
    public KualiDecimal calculatePendBudget(boolean isYearEndDocument, String budgetCheckingBalanceTypeCd, Integer universityFiscalYear, String chartOfAccountsCode, String accountNumber, String acctSufficientFundsFinObjCd, List expenditureCodes);

    /**
     * fp_sasfc: 141-2-166-2
     * 
     * @param isYearEndDocument
     * @param extrnlEncumFinBalanceTypCd
     * @param intrnlEncumFinBalanceTypCd
     * @param preencumbranceFinBalTypeCd
     * @param universityFiscalYear the university fiscal year of sufficient funds balances to summarize
     * @param chartOfAccountsCode the chart of accounts code of sufficient funds balances to summarize
     * @param accountNumber the account number of sufficient fund balances to summarize
     * @param acctSufficientFundsFinObjCd
     * @param expenditureCodes
     * @return
     */
    public KualiDecimal calculatePendEncum(boolean isYearEndDocument, String extrnlEncumFinBalanceTypCd, String intrnlEncumFinBalanceTypCd, String preencumbranceFinBalTypeCd, Integer universityFiscalYear, String chartOfAccountsCode, String accountNumber, String acctSufficientFundsFinObjCd, List expenditureCodes);

    /**
     * Purge table by year/chart
     * 
     * @param chart the chart of sufficient fund records to purge
     * @param year the year of sufficient fund records to purge
     */
    public void purgeYearByChart(String chart, int year);
}