package org.kuali.module.ar.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class StopBilling extends PersistableBusinessObjectBase {

	private String customerStopBillingCode;
	private String customerStopBillingDescription;
	private boolean active;

	/**
	 * Default constructor.
	 */
	public StopBilling() {

	}

	/**
	 * Gets the customerStopBillingCode attribute.
	 * 
	 * @return Returns the customerStopBillingCode
	 * 
	 */
	public String getCustomerStopBillingCode() { 
		return customerStopBillingCode;
	}

	/**
	 * Sets the customerStopBillingCode attribute.
	 * 
	 * @param customerStopBillingCode The customerStopBillingCode to set.
	 * 
	 */
	public void setCustomerStopBillingCode(String customerStopBillingCode) {
		this.customerStopBillingCode = customerStopBillingCode;
	}


	/**
	 * Gets the customerStopBillingDescription attribute.
	 * 
	 * @return Returns the customerStopBillingDescription
	 * 
	 */
	public String getCustomerStopBillingDescription() { 
		return customerStopBillingDescription;
	}

	/**
	 * Sets the customerStopBillingDescription attribute.
	 * 
	 * @param customerStopBillingDescription The customerStopBillingDescription to set.
	 * 
	 */
	public void setCustomerStopBillingDescription(String customerStopBillingDescription) {
		this.customerStopBillingDescription = customerStopBillingDescription;
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
        m.put("customerStopBillingCode", this.customerStopBillingCode);
	    return m;
    }
}
