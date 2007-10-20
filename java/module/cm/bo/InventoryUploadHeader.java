package org.kuali.module.cams.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.core.bo.PersistableBusinessObjectBase;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class InventoryUploadHeader extends PersistableBusinessObjectBase {

	private String uploaderUniversalIdentifier;
	private Long uploaderSequenceNumber;
	private Timestamp inventoryUploadTimestamp;

    private List<InventoryUploadDetail> inventoryUploadDetails;

	/**
	 * Default constructor.
	 */
	public InventoryUploadHeader() {
        inventoryUploadDetails = new ArrayList<InventoryUploadDetail>();
        
	}

	/**
	 * Gets the uploaderUniversalIdentifier attribute.
	 * 
	 * @return Returns the uploaderUniversalIdentifier
	 * 
	 */
	public String getUploaderUniversalIdentifier() { 
		return uploaderUniversalIdentifier;
	}

	/**
	 * Sets the uploaderUniversalIdentifier attribute.
	 * 
	 * @param uploaderUniversalIdentifier The uploaderUniversalIdentifier to set.
	 * 
	 */
	public void setUploaderUniversalIdentifier(String uploaderUniversalIdentifier) {
		this.uploaderUniversalIdentifier = uploaderUniversalIdentifier;
	}


	/**
	 * Gets the uploaderSequenceNumber attribute.
	 * 
	 * @return Returns the uploaderSequenceNumber
	 * 
	 */
	public Long getUploaderSequenceNumber() { 
		return uploaderSequenceNumber;
	}

	/**
	 * Sets the uploaderSequenceNumber attribute.
	 * 
	 * @param uploaderSequenceNumber The uploaderSequenceNumber to set.
	 * 
	 */
	public void setUploaderSequenceNumber(Long uploaderSequenceNumber) {
		this.uploaderSequenceNumber = uploaderSequenceNumber;
	}


	/**
	 * Gets the inventoryUploadTimestamp attribute.
	 * 
	 * @return Returns the inventoryUploadTimestamp
	 * 
	 */
	public Timestamp getInventoryUploadTimestamp() { 
		return inventoryUploadTimestamp;
	}

	/**
	 * Sets the inventoryUploadTimestamp attribute.
	 * 
	 * @param inventoryUploadTimestamp The inventoryUploadTimestamp to set.
	 * 
	 */
	public void setInventoryUploadTimestamp(Timestamp inventoryUploadTimestamp) {
		this.inventoryUploadTimestamp = inventoryUploadTimestamp;
	}

    /**
     * Gets the inventoryUploadDetails attribute. 
     * @return Returns the inventoryUploadDetails.
     */
    public List<InventoryUploadDetail> getInventoryUploadDetails() {
        return inventoryUploadDetails;
    }

    /**
     * Sets the inventoryUploadDetails attribute value.
     * @param inventoryUploadDetails The inventoryUploadDetails to set.
     */
    public void setInventoryUploadDetails(List<InventoryUploadDetail> inventoryUploadDetails) {
        this.inventoryUploadDetails = inventoryUploadDetails;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();      
        m.put("uploaderUniversalIdentifier", this.uploaderUniversalIdentifier);
        if (this.uploaderSequenceNumber != null) {
            m.put("uploaderSequenceNumber", this.uploaderSequenceNumber.toString());
        }
        return m;
    }

}
