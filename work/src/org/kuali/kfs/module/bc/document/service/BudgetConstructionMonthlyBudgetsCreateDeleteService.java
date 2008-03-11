/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.module.budget.service;

/**
 * 
 * distribute the request amount for a set of budget construction general ledger rows evenly among twelve monthly periods in a budget construction 
 * monthly budget row with the same key.  if the amount to be spread is not divisible by 12, the adjustment will be added or subtracted from the
 * amount in the first monthly period.
 */

public interface BudgetConstructionMonthlyBudgetsCreateDeleteService {

  /**
   * 
   * remove the existing revenue monthly budgets for this key
   * @param documentNumber  the budget construction document number
   * @param fiscalYear      the fiscal year for which the budget is being built.  this and the fields below are the key to a budget construction document
   * @param chartCode
   * @param accountNumber
   * @param subAccountNumber
   */
    public void deleteBudgetConstructionMonthlyBudgetsRevenue(String documentNumber, Integer fiscalYear, String chartCode, String accountNumber, String subAccountNumber);
    
   /**
    * 
    * remove the existing expenditure monthly budgets for this key
    * @param documentNumber  the budget construction document number
    * @param fiscalYear      the fiscal year for which the budget is being built.  this and the fields below are the key to a budget construction document
    * @param chartCode
    * @param accountNumber
    * @param subAccountNumber
    */ 
    public void deleteBudgetConstructionMonthlyBudgetsExpenditure(String documentNumber, Integer fiscalYear, String chartCode, String accountNumber, String subAccountNumber);

    /**
     * 
     * spread the revenue for this key evenly over 12 months, with any remainder mod 12 added to the first month
     * @param documentNumber  the budget construction document number
     * @param fiscalYear      the fiscal year for which the budget is being built.  this and the fields below are the key to a budget construction document
     * @param chartCode
     * @param accountNumber
     * @param subAccountNumber
     * @return false--no recalculation of benefits is required for revenue
     */
    public boolean spreadBudgetConstructionMonthlyBudgetsRevenue(String documentNumber, Integer fiscalYear, String chartCode, String accountNumber, String subAccountNumber);

    /**
     * 
     * spread the expenditures for this key evenly over 12 months, with any reaminder mod 12 added to the first month 
     * @param documentNumber  the budget construction document number
     * @param fiscalYear      the fiscal year for which the budget is being built.  this and the fields below are the key to a budget construction document
     * @param chartCode
     * @param accountNumber
     * @param subAccountNumber
     * @return true if benefits object classes were among those distributed (benefits recaluclation required), false otherwise
     */
    public boolean spreadBudgetConstructionMonthlyBudgetsExpenditure(String documentNumber, Integer fiscalYear, String chartCode, String accountNumber, String subAccountNumber);
    
    /**
     * 
     * TODO: remove this method
     */
    public void testMethod(String document, Integer fiscalYear, String chartCode, String accountNumber, String subAccountNumber);

    
}
