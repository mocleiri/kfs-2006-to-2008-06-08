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
public class Carrier extends PersistableBusinessObjectBase {

	private String carrierCode;
	private String carrierDescription;
	private boolean active;

	/**
	 * Default constructor.
	 */
	public Carrier() {

	}

	/**
	 * Gets the carrierCode attribute.
	 * 
	 * @return Returns the carrierCode
	 * 
	 */
	public String getCarrierCode() { 
		return carrierCode;
	}

	/**
	 * Sets the carrierCode attribute.
	 * 
	 * @param carrierCode The carrierCode to set.
	 * 
	 */
	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}


	/**
	 * Gets the carrierDescription attribute.
	 * 
	 * @return Returns the carrierDescription
	 * 
	 */
	public String getCarrierDescription() { 
		return carrierDescription;
	}

	/**
	 * Sets the carrierDescription attribute.
	 * 
	 * @param carrierDescription The carrierDescription to set.
	 * 
	 */
	public void setCarrierDescription(String carrierDescription) {
		this.carrierDescription = carrierDescription;
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
        m.put("carrierCode", this.carrierCode);
	    return m;
    }
}
