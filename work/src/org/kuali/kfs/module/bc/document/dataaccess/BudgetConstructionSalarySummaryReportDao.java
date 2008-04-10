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

import org.kuali.core.util.KualiDecimal;

public interface BudgetConstructionSalarySummaryReportDao {



    /**
     * 
     * lists all salaries, or only those flagged with a reason for increase
     * @param personUserIdentifier--the user requesting the report
     * @param listSalariesWithReasonCodes--true if only salaries associated with a reason for increase are to be listed, false if all salaries are to be listed
     */
    public void reasonSummaryReports(String personUserIdentifier, boolean listSalariesWithReasonCodes);
    
    /**
     * 
     * lists salaries with increases at or above a threshold, or with increases at or below a threshold
     * @param personUserIdentifier--the user requesting the report
     * @param previousFiscalYear--the fiscal year preceding the one for which the budget is being built
     * @param reportGreaterThanOrEqualToThreshold--true if salaries increased at or above the threshold percentage are listed, false if salaries increased at or below the threshold percentage are listed
     * @param threshold--the threshold percentage (a fraction times 100)
     */
    public void salarySummaryReports(String personUserIdentifier, Integer previousFiscalYear, boolean reportGreaterThanOrEqualToThreshold, KualiDecimal threshold);

}
