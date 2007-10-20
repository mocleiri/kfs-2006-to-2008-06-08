package org.kuali.module.cams.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class AssetDocumentType extends PersistableBusinessObjectBase {

	private String financialDocumentTypeCode;
	private String financialDocumentName;
	private boolean active;

	/**
	 * Default constructor.
	 */
	public AssetDocumentType() {

	}

	/**
	 * Gets the financialDocumentTypeCode attribute.
	 * 
	 * @return Returns the financialDocumentTypeCode
	 * 
	 */
	public String getFinancialDocumentTypeCode() { 
		return financialDocumentTypeCode;
	}

	/**
	 * Sets the financialDocumentTypeCode attribute.
	 * 
	 * @param financialDocumentTypeCode The financialDocumentTypeCode to set.
	 * 
	 */
	public void setFinancialDocumentTypeCode(String financialDocumentTypeCode) {
		this.financialDocumentTypeCode = financialDocumentTypeCode;
	}


	/**
	 * Gets the financialDocumentName attribute.
	 * 
	 * @return Returns the financialDocumentName
	 * 
	 */
	public String getFinancialDocumentName() { 
		return financialDocumentName;
	}

	/**
	 * Sets the financialDocumentName attribute.
	 * 
	 * @param financialDocumentName The financialDocumentName to set.
	 * 
	 */
	public void setFinancialDocumentName(String financialDocumentName) {
		this.financialDocumentName = financialDocumentName;
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
        m.put("financialDocumentTypeCode", this.financialDocumentTypeCode);
	    return m;
    }
}
