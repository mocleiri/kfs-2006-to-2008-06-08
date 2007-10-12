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
package org.kuali.module.chart.lookup.keyvalues;

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.web.ui.KeyLabelPair;

/**
 * This class returns list containg E = Employee
 *                                  F = FEIN
 *                                  P = DV Payee
 *                                  S = SSN
 *                                  V = Vendor
 * 
 */
public class PayeeTypeCodeValuesFinder extends KeyValuesBase {

    /*
     * @see org.kuali.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List getKeyValues() {
        List keyValues = new ArrayList();
        keyValues.add(new KeyLabelPair("E", "Employee"));
        keyValues.add(new KeyLabelPair("F", "FEIN"));
        keyValues.add(new KeyLabelPair("P", "DV Payee"));
        keyValues.add(new KeyLabelPair("S", "SSN"));
        keyValues.add(new KeyLabelPair("V", "Vendor"));
        
        return keyValues;
    }

}
