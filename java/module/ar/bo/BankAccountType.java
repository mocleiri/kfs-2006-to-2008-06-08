package org.kuali.module.ar.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class BankAccountType extends PersistableBusinessObjectBase {

	private String customerBankAccountTypeCode;
	private String customerBankAccountTypeName;
	private boolean active;

	/**
	 * Default constructor.
	 */
	public BankAccountType() {

	}

	/**
	 * Gets the customerBankAccountTypeCode attribute.
	 * 
	 * @return Returns the customerBankAccountTypeCode
	 * 
	 */
	public String getCustomerBankAccountTypeCode() { 
		return customerBankAccountTypeCode;
	}

	/**
	 * Sets the customerBankAccountTypeCode attribute.
	 * 
	 * @param customerBankAccountTypeCode The customerBankAccountTypeCode to set.
	 * 
	 */
	public void setCustomerBankAccountTypeCode(String customerBankAccountTypeCode) {
		this.customerBankAccountTypeCode = customerBankAccountTypeCode;
	}


	/**
	 * Gets the customerBankAccountTypeName attribute.
	 * 
	 * @return Returns the customerBankAccountTypeName
	 * 
	 */
	public String getCustomerBankAccountTypeName() { 
		return customerBankAccountTypeName;
	}

	/**
	 * Sets the customerBankAccountTypeName attribute.
	 * 
	 * @param customerBankAccountTypeName The customerBankAccountTypeName to set.
	 * 
	 */
	public void setCustomerBankAccountTypeName(String customerBankAccountTypeName) {
		this.customerBankAccountTypeName = customerBankAccountTypeName;
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
        m.put("customerBankAccountTypeCode", this.customerBankAccountTypeCode);
	    return m;
    }
}
