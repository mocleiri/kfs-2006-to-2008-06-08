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
package org.kuali.module.kra.budget.service;

import java.util.List;

import org.kuali.module.kra.budget.bo.Budget;
import org.kuali.module.kra.budget.bo.BudgetBaseCode;
import org.kuali.module.kra.budget.document.BudgetDocument;

public interface BudgetIndirectCostService {

    public void reconcileIndirectCost(BudgetDocument budget);

    public void refreshIndirectCost(BudgetDocument budget);

    /**
     * Sets up active IndirectCostRate objects.
     */
    public void setupIndirectCostRates(Budget budget);

    /**
     * Returns active BudgetBaseCodes.
     * 
     * @return active BudgetBaseCode objects
     */
    public List<BudgetBaseCode> getDefaultBudgetBaseCodeValues();
}