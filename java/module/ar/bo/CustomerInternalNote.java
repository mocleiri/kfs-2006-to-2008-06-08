package org.kuali.module.ar.bo;

import java.sql.Timestamp;
import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class CustomerInternalNote extends PersistableBusinessObjectBase {

	private String customerNumber;
	private Timestamp customerInternalNoteCreateTimestamp;
	private String customerInternalNoteAuthorIdentifier;
	private String customerInternalNoteText;

	/**
	 * Default constructor.
	 */
	public CustomerInternalNote() {

	}

	/**
	 * Gets the customerNumber attribute.
	 * 
	 * @return Returns the customerNumber
	 * 
	 */
	public String getCustomerNumber() { 
		return customerNumber;
	}

	/**
	 * Sets the customerNumber attribute.
	 * 
	 * @param customerNumber The customerNumber to set.
	 * 
	 */
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}


	/**
	 * Gets the customerInternalNoteCreateTimestamp attribute.
	 * 
	 * @return Returns the customerInternalNoteCreateTimestamp
	 * 
	 */
	public Timestamp getCustomerInternalNoteCreateTimestamp() { 
		return customerInternalNoteCreateTimestamp;
	}

	/**
	 * Sets the customerInternalNoteCreateTimestamp attribute.
	 * 
	 * @param customerInternalNoteCreateTimestamp The customerInternalNoteCreateTimestamp to set.
	 * 
	 */
	public void setCustomerInternalNoteCreateTimestamp(Timestamp customerInternalNoteCreateTimestamp) {
		this.customerInternalNoteCreateTimestamp = customerInternalNoteCreateTimestamp;
	}


	/**
	 * Gets the customerInternalNoteAuthorIdentifier attribute.
	 * 
	 * @return Returns the customerInternalNoteAuthorIdentifier
	 * 
	 */
	public String getCustomerInternalNoteAuthorIdentifier() { 
		return customerInternalNoteAuthorIdentifier;
	}

	/**
	 * Sets the customerInternalNoteAuthorIdentifier attribute.
	 * 
	 * @param customerInternalNoteAuthorIdentifier The customerInternalNoteAuthorIdentifier to set.
	 * 
	 */
	public void setCustomerInternalNoteAuthorIdentifier(String customerInternalNoteAuthorIdentifier) {
		this.customerInternalNoteAuthorIdentifier = customerInternalNoteAuthorIdentifier;
	}


	/**
	 * Gets the customerInternalNoteText attribute.
	 * 
	 * @return Returns the customerInternalNoteText
	 * 
	 */
	public String getCustomerInternalNoteText() { 
		return customerInternalNoteText;
	}

	/**
	 * Sets the customerInternalNoteText attribute.
	 * 
	 * @param customerInternalNoteText The customerInternalNoteText to set.
	 * 
	 */
	public void setCustomerInternalNoteText(String customerInternalNoteText) {
		this.customerInternalNoteText = customerInternalNoteText;
	}


	/**
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
	    LinkedHashMap m = new LinkedHashMap();	    
        m.put("customerNumber", this.customerNumber);
        if (this.customerInternalNoteCreateTimestamp != null) {
            m.put("customerInternalNoteCreateTimestamp", this.customerInternalNoteCreateTimestamp.toString());
        }
	    return m;
    }
}
