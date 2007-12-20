/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.module.budget.dao;

public interface GeneralLedgerBudgetLoadDao {
/*
 *   param: fiscal year (or budget period key)
 *   this method loads the general ledger from the budget to the accounting general ledger
 */
    public void loadGeneralLedgerFromBudget(Integer fiscalYear);
    
    //@@TODO: remove this test method
    public void unitTestRoutine(Integer fiscalYear);
}