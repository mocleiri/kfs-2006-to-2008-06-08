/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/work/src/org/kuali/kfs/fp/businessobject/MessageOfTheDay.java,v $
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

package org.kuali.module.financial.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.BusinessObjectBase;
import org.kuali.core.bo.OriginationCode;

/**
 * 
 */
public class MessageOfTheDay extends BusinessObjectBase {

    private String financialSystemOriginationCode;
    private String financialSystemMessageOfTheDayText;

    private OriginationCode financialSystemOrigination;
    
    /**
     * Default constructor.
     */
    public MessageOfTheDay() {

    }

    /**
     * Gets the financialSystemOriginationCode attribute.
     * 
     * @return Returns the financialSystemOriginationCode
     * 
     */
    public String getFinancialSystemOriginationCode() {
        return financialSystemOriginationCode;
    }

    /**
     * Sets the financialSystemOriginationCode attribute.
     * 
     * @param financialSystemOriginationCode The financialSystemOriginationCode to set.
     * 
     */
    public void setFinancialSystemOriginationCode(String financialSystemOriginationCode) {
        this.financialSystemOriginationCode = financialSystemOriginationCode;
    }


    /**
     * Gets the financialSystemMessageOfTheDayText attribute.
     * 
     * @return Returns the financialSystemMessageOfTheDayText
     * 
     */
    public String getFinancialSystemMessageOfTheDayText() {
        return financialSystemMessageOfTheDayText;
    }

    /**
     * Sets the financialSystemMessageOfTheDayText attribute.
     * 
     * @param financialSystemMessageOfTheDayText The financialSystemMessageOfTheDayText to set.
     * 
     */
    public void setFinancialSystemMessageOfTheDayText(String financialSystemMessageOfTheDayText) {
        this.financialSystemMessageOfTheDayText = financialSystemMessageOfTheDayText;
    }

    /**
     * Gets the financialSystemOrigination attribute. 
     * @return Returns the financialSystemOrigination.
     */
    public OriginationCode getFinancialSystemOrigination() {
        return financialSystemOrigination;
    }

    /**
     * Sets the financialSystemOrigination attribute value.
     * @param financialSystemOrigination The financialSystemOrigination to set.
     * @deprecated
     */
    public void setFinancialSystemOrigination(OriginationCode financialSystemOrigination) {
        this.financialSystemOrigination = financialSystemOrigination;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("financialSystemOriginationCode", this.financialSystemOriginationCode);
        return m;
    }

}
