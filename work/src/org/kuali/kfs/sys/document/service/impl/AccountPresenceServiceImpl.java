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
package org.kuali.module.financial.service.impl;

import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.ObjectCode;
import org.kuali.module.financial.service.AccountPresenceService;
import org.kuali.module.gl.bo.Balance;
import org.kuali.module.gl.dao.BalanceDao;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AccountPresenceServiceImpl implements AccountPresenceService {
    private BalanceDao balanceDao;

    /**
     * @see org.kuali.module.financial.service.AccountPresenceService#isObjectCodeBudgetedForAccountPresence(org.kuali.module.chart.bo.Account,
     *      org.kuali.module.chart.bo.ObjectCode)
     */
    public boolean isObjectCodeBudgetedForAccountPresence(Account account, ObjectCode objectCode) {
        boolean objectCodeValid = true;

        /*
         * first check if account has presence control turned on, if not no checks need to take place on object code budgeting
         */
        if (account.isFinancialObjectivePrsctrlIndicator()) {
            /*
             * can have budgeting record for object code, it's consolidation code, or object level
             */

            // try to find budget record for object code
            Balance foundBalance = (Balance) balanceDao.getCurrentBudgetForObjectCode(objectCode.getUniversityFiscalYear(), account.getChartOfAccountsCode(), account.getAccountNumber(), objectCode.getFinancialObjectCode());

            // if object code budget not found, try consolidation object code
            if (foundBalance == null) {
                foundBalance = (Balance) balanceDao.getCurrentBudgetForObjectCode(objectCode.getUniversityFiscalYear(), account.getChartOfAccountsCode(), account.getAccountNumber(), objectCode.getFinancialObjectLevel().getConsolidatedObjectCode());

                // if consolidation object code budget not found, try object level
                if (foundBalance == null) {
                    foundBalance = (Balance) balanceDao.getCurrentBudgetForObjectCode(objectCode.getUniversityFiscalYear(), account.getChartOfAccountsCode(), account.getAccountNumber(), objectCode.getFinancialObjectLevelCode());

                    // object not budgeted
                    if (foundBalance == null) {
                        objectCodeValid = false;
                    }
                }
            }
        }

        return objectCodeValid;
    }

    /**
     * @return Returns the balanceDao.
     */
    public BalanceDao getBalanceDao() {
        return balanceDao;
    }

    /**
     * @param balanceDao The balanceDao to set.
     */
    public void setBalanceDao(BalanceDao balanceDao) {
        this.balanceDao = balanceDao;
    }
}