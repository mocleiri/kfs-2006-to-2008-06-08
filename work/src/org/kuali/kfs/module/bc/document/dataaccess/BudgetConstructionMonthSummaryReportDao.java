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

public interface BudgetConstructionMonthSummaryReportDao {

    /**
     *  cleans month summary table.
     * 
     * @param personUserIdentifier
     * @return
     */
    public void cleanReportsMonthSummaryTable(String personUserIdentifier);

    /**
     * 
     * insert rows into the monthly summary report table for this user
     * @param personUserIdentifier--the user requesting the report
     * @param consolidateToObjectCodeLevel--true if sub object codes are to be consolidated, false if sub-object detail is desired
     */
    public void updateReportsMonthSummaryTable(String personUserIdentifier, boolean consolidateToObjectCodeLevel);

}