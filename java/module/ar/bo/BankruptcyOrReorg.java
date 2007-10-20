package org.kuali.module.ar.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class BankruptcyOrReorg extends PersistableBusinessObjectBase {

	private String customerBankrptcyOrReorgCode;
	private String customerBankrptcyOrReorgDescription;
	private boolean active;

	/**
	 * Default constructor.
	 */
	public BankruptcyOrReorg() {

	}

	/**
	 * Gets the customerBankrptcyOrReorgCode attribute.
	 * 
	 * @return Returns the customerBankrptcyOrReorgCode
	 * 
	 */
	public String getCustomerBankrptcyOrReorgCode() { 
		return customerBankrptcyOrReorgCode;
	}

	/**
	 * Sets the customerBankrptcyOrReorgCode attribute.
	 * 
	 * @param customerBankrptcyOrReorgCode The customerBankrptcyOrReorgCode to set.
	 * 
	 */
	public void setCustomerBankrptcyOrReorgCode(String customerBankrptcyOrReorgCode) {
		this.customerBankrptcyOrReorgCode = customerBankrptcyOrReorgCode;
	}


	/**
	 * Gets the customerBankrptcyOrReorgDescription attribute.
	 * 
	 * @return Returns the customerBankrptcyOrReorgDescription
	 * 
	 */
	public String getCustomerBankrptcyOrReorgDescription() { 
		return customerBankrptcyOrReorgDescription;
	}

	/**
	 * Sets the customerBankrptcyOrReorgDescription attribute.
	 * 
	 * @param customerBankrptcyOrReorgDescription The customerBankrptcyOrReorgDescription to set.
	 * 
	 */
	public void setCustomerBankrptcyOrReorgDescription(String customerBankrptcyOrReorgDescription) {
		this.customerBankrptcyOrReorgDescription = customerBankrptcyOrReorgDescription;
	}


	/**
	 * Gets the active attribute.
	 * 
	 * @return Returns the active
	 * 
	 */
	public boolean isActive() { 
		return active;
	}

	/**
	 * Sets the active attribute.
	 * 
	 * @param active The active to set.
	 * 
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
	    LinkedHashMap m = new LinkedHashMap();	    
        m.put("customerBankrptcyOrReorgCode", this.customerBankrptcyOrReorgCode);
	    return m;
    }
}
