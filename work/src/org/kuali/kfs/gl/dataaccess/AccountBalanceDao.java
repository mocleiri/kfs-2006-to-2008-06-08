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

import java.util.Iterator;
import java.util.Map;

import org.kuali.module.gl.bo.AccountBalance;
import org.kuali.module.gl.bo.Transaction;

/**
 * @author jsissom
 *  
 */
public interface AccountBalanceDao {

    public AccountBalance getByTransaction(Transaction t);

    public void save(AccountBalance ab);

    /**
     * This method finds the available account balances according to input fields and values
     * 
     * @param fieldValues the input fields and values
     * @param isConsolidated determine whether the search results are consolidated
     * @return the summary records of balance entries
     */
    public Iterator findAvailableAccountBalance(Map fieldValues, boolean isConsolidated);

    /**
     * This method finds the available account balances according to input fields and values
     * 
     * @param fieldValues the input fields and values
     * @param isCostShareInclusive determine whether the account balance entries with cost share is included
     * @param isConsolidated determine whether the search results are consolidated
     * @return the summary records of account balance entries
     */
    public Iterator findAccountBalanceByConsolidation(Map fieldValues, boolean isCostShareInclusive, boolean isConsolidated);
    
    /**
     * This method finds the available account balances according to input fields and values
     * 
     * @param fieldValues the input fields and values
     * @param isCostShareInclusive determine whether the account balance entries with cost share is included
     * @param isConsolidated determine whether the search results are consolidated
     * @return the summary records of account balance entries
     */
    public Iterator findAccountBalanceByLevel(Map fieldValues, boolean isCostShareInclusive, boolean isConsolidated); 
    
    /**
     * This method finds the available account balances according to input fields and values
     * 
     * @param fieldValues the input fields and values
     * @param isCostShareInclusive determine whether the account balance entries with cost share is included
     * @param isConsolidated determine whether the search results are consolidated
     * @return the summary records of account balance entries
     */
    public Iterator findAccountBalanceByObject(Map fieldValues, boolean isCostShareInclusive, boolean isConsolidated);    
}