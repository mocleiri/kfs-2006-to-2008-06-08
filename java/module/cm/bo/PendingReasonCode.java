package org.kuali.module.cams.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class PendingReasonCode extends PersistableBusinessObjectBase {

	private String reasonPendingCode;
	private String reasonPendingDescription;
	private boolean active;

	/**
	 * Default constructor.
	 */
	public PendingReasonCode() {

	}

	/**
	 * Gets the reasonPendingCode attribute.
	 * 
	 * @return Returns the reasonPendingCode
	 * 
	 */
	public String getReasonPendingCode() { 
		return reasonPendingCode;
	}

	/**
	 * Sets the reasonPendingCode attribute.
	 * 
	 * @param reasonPendingCode The reasonPendingCode to set.
	 * 
	 */
	public void setReasonPendingCode(String reasonPendingCode) {
		this.reasonPendingCode = reasonPendingCode;
	}


	/**
	 * Gets the reasonPendingDescription attribute.
	 * 
	 * @return Returns the reasonPendingDescription
	 * 
	 */
	public String getReasonPendingDescription() { 
		return reasonPendingDescription;
	}

	/**
	 * Sets the reasonPendingDescription attribute.
	 * 
	 * @param reasonPendingDescription The reasonPendingDescription to set.
	 * 
	 */
	public void setReasonPendingDescription(String reasonPendingDescription) {
		this.reasonPendingDescription = reasonPendingDescription;
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
        m.put("reasonPendingCode", this.reasonPendingCode);
	    return m;
    }
}
