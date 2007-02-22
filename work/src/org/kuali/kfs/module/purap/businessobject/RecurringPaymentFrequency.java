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

package org.kuali.module.purap.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;

/**
 * 
 */
public class RecurringPaymentFrequency extends PersistableBusinessObjectBase {

	private String recurringPaymentFrequencyCode;
	private String recurringPaymentFrequencyDescription;
	private boolean active;

	/**
	 * Default constructor.
	 */
	public RecurringPaymentFrequency() {

	}

	/**
	 * Gets the recurringPaymentFrequencyCode attribute.
	 * 
	 * @return Returns the recurringPaymentFrequencyCode
	 * 
	 */
	public String getRecurringPaymentFrequencyCode() { 
		return recurringPaymentFrequencyCode;
	}

	/**
	 * Sets the recurringPaymentFrequencyCode attribute.
	 * 
	 * @param recurringPaymentFrequencyCode The recurringPaymentFrequencyCode to set.
	 * 
	 */
	public void setRecurringPaymentFrequencyCode(String recurringPaymentFrequencyCode) {
		this.recurringPaymentFrequencyCode = recurringPaymentFrequencyCode;
	}


	/**
	 * Gets the recurringPaymentFrequencyDescription attribute.
	 * 
	 * @return Returns the recurringPaymentFrequencyDescription
	 * 
	 */
	public String getRecurringPaymentFrequencyDescription() { 
		return recurringPaymentFrequencyDescription;
	}

	/**
	 * Sets the recurringPaymentFrequencyDescription attribute.
	 * 
	 * @param recurringPaymentFrequencyDescription The recurringPaymentFrequencyDescription to set.
	 * 
	 */
	public void setRecurringPaymentFrequencyDescription(String recurringPaymentFrequencyDescription) {
		this.recurringPaymentFrequencyDescription = recurringPaymentFrequencyDescription;
	}


	/**
     * Gets the active attribute. 
     * @return Returns the active.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the active attribute value.
     * @param active The active to set.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
	    LinkedHashMap m = new LinkedHashMap();	    
        m.put("recurringPaymentFrequencyCode", this.recurringPaymentFrequencyCode);
	    return m;
    }
}
