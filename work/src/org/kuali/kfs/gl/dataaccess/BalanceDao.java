/*
 * Copyright (c) 2004, 2005 The National Association of College and University Business Officers,
 * Cornell University, Trustees of Indiana University, Michigan State University Board of Trustees,
 * Trustees of San Joaquin Delta College, University of Hawai'i, The Arizona Board of Regents on
 * behalf of the University of Arizona, and the r*smart group.
 * 
 * Licensed under the Educational Community License Version 1.0 (the "License"); By obtaining,
 * using and/or copying this Original Work, you agree that you have read, understand, and will
 * comply with the terms and conditions of the Educational Community License.
 * 
 * You may obtain a copy of the License at:
 * 
 * http://kualiproject.org/license.html
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.kuali.module.gl.dao;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.kuali.module.chart.bo.Account;
import org.kuali.module.gl.bo.Balance;
import org.kuali.module.gl.bo.Transaction;

/**
 * @author jsissom
 *  
 */
public interface BalanceDao {

    public Balance getBalanceByTransaction(Transaction t);

    public Balance getBalanceByPrimaryId(Integer universityFiscalYear, String chartOfAccountsCode, String accountNumber);

    public Iterator findBalances(Account account, Integer fiscalYear, Collection includedObjectCodes,
            Collection excludedObjectCodes, Collection objectTypeCodes, Collection balanceTypeCodes);

    public void save(Balance b);

    /**
     * This method finds the cash balance entries according to input fields and values
     * 
     * @param fieldValues the input fields and values
     * @param isConsolidated consolidation option is applied or not
     * @return the records of cash balance entries
     */
    public Iterator findCashBalance(Map fieldValues, boolean isConsolidated);

    /**
     * Returns the balance entries for the given year, chart, and account.
     * @param universityFiscalYear
     * @param chartOfAccountsCode
     * @param accountNumber
     * @return balance entries matching above
     */
    public Iterator findAccountBalances(Integer universityFiscalYear, String chartOfAccountsCode, String accountNumber);

    /**
     * This method finds the summary records of balance entries according to input fields and values
     * 
     * @param fieldValues the input fields and values
     * @param isConsolidated consolidation option is applied or not
     * @return the summary records of balance entries
     */
    public Iterator findBalance(Map fieldValues, boolean isConsolidated);


    /**
     * Returns the CB (current budget) record for the given year, chart, account, and
     * object code if one is found.
     * @param universityFiscalYear
     * @param chartOfAccountsCode
     * @param accountNumber
     * @param objectCode
     * @return the CB Balance record
     */
    public Balance getCurrentBudgetForObjectCode(Integer universityFiscalYear, String chartOfAccountsCode, String accountNumber,
            String objectCode);
}