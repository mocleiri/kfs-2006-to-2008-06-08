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
package org.kuali.module.kra.budget.lookup.keyvalues;

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.module.kra.budget.bo.AppointmentType;
import org.kuali.module.kra.budget.bo.BudgetBaseCode;

public class BudgetBaseCodeValuesFinder extends KeyValuesBase {

    public BudgetBaseCodeValuesFinder() {
        super();
    }

    public List getKeyValues() {
        
        List<BudgetBaseCode> baseCodes = new ArrayList(SpringServiceLocator.getBudgetIndirectCostService().getDefaultBudgetBaseCodeValues());
        List baseCodeKeyLabelPairList = new ArrayList();
        for (BudgetBaseCode element: baseCodes) {
            baseCodeKeyLabelPairList.add(new KeyLabelPair(element.getBudgetBaseCode(), element.getBudgetBaseDescription()));
        }

        return baseCodeKeyLabelPairList;
    }
}
