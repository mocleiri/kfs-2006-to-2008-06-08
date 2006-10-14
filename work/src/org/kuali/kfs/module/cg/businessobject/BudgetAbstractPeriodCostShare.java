/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source$
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

package org.kuali.module.kra.budget.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.BusinessObjectBase;
import org.kuali.core.util.KualiInteger;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public abstract class BudgetAbstractPeriodCostShare extends BusinessObjectBase {

    protected String documentHeaderId;
    protected Integer budgetPeriodSequenceNumber;
    protected Integer budgetCostShareSequenceNumber;
    protected KualiInteger budgetCostShareAmount;

    /**
     * Default no-arg constructor.
     */
    public BudgetAbstractPeriodCostShare() {

    }

    /**
     * Gets the documentHeaderId attribute.
     * 
     * @return - Returns the documentHeaderId
     * 
     */
    public String getDocumentHeaderId() {
        return documentHeaderId;
    }

    /**
     * Sets the documentHeaderId attribute.
     * 
     * @param documentHeaderId The documentHeaderId to set.
     * 
     */
    public void setDocumentHeaderId(String documentHeaderId) {
        this.documentHeaderId = documentHeaderId;
    }

    /**
     * Gets the budgetThirdPartyCostShareAmount attribute.
     * 
     * @return - Returns the budgetThirdPartyCostShareAmount
     * 
     */
    public Integer getBudgetPeriodSequenceNumber() {
        return budgetPeriodSequenceNumber;
    }

    /**
     * Sets the budgetThirdPartyCostShareAmount attribute.
     * 
     * @param budgetThirdPartyCostShareAmount The budgetThirdPartyCostShareAmount to set.
     * 
     */
    public void setBudgetPeriodSequenceNumber(Integer budgetPeriodSequenceNumber) {
        this.budgetPeriodSequenceNumber = budgetPeriodSequenceNumber;
    }

    /**
     * Gets the budgetThirdPartyCostShareAmount attribute.
     * 
     * @return - Returns the budgetThirdPartyCostShareAmount
     * 
     */
    public Integer getBudgetCostShareSequenceNumber() {
        return budgetCostShareSequenceNumber;
    }

    /**
     * Sets the budgetThirdPartyCostShareAmount attribute.
     * 
     * @param budgetThirdPartyCostShareAmount The budgetThirdPartyCostShareAmount to set.
     * 
     */
    public void setBudgetCostShareSequenceNumber(Integer budgetThirdPartyCostShareSequenceNumber) {
        this.budgetCostShareSequenceNumber = budgetThirdPartyCostShareSequenceNumber;
    }

    /**
     * Gets the budgetThirdPartyCostShareAmount attribute.
     * 
     * @return - Returns the budgetThirdPartyCostShareAmount
     * 
     */
    public KualiInteger getBudgetCostShareAmount() {
        return budgetCostShareAmount;
    }

    /**
     * Sets the budgetThirdPartyCostShareAmount attribute.
     * 
     * @param budgetThirdPartyCostShareAmount The budgetThirdPartyCostShareAmount to set.
     * 
     */
    public void setBudgetCostShareAmount(KualiInteger budgetThirdPartyCostShareAmount) {
        this.budgetCostShareAmount = budgetThirdPartyCostShareAmount;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();

        m.put("documentHeaderId", this.documentHeaderId);
        m.put("budgetPeriodSequenceNumber", this.budgetPeriodSequenceNumber);
        m.put("budgetCostShareSequenceNumber", this.budgetCostShareSequenceNumber);

        return m;
    }
}