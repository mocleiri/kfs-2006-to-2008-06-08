package org.kuali.module.cams.bo;

import java.sql.Timestamp;
import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class InventoryUploadErrorDetail extends PersistableBusinessObjectBase {

	private String uploaderUniversalIdentifier;
	private Long uploaderSequenceNumber;
	private Long uploadRowNumber;
	private String errorCorrectionStatusCode;
	private String correctedRowText;
	private String correctorUniversalIdentifier;
	private Timestamp inventoryCorrectionTimestamp;

    private InventoryUploadDetail inventoryUploadDetail;

	/**
	 * Default constructor.
	 */
	public InventoryUploadErrorDetail() {

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
	 * Gets the uploadRowNumber attribute.
	 * 
	 * @return Returns the uploadRowNumber
	 * 
	 */
	public Long getUploadRowNumber() { 
		return uploadRowNumber;
	}

	/**
	 * Sets the uploadRowNumber attribute.
	 * 
	 * @param uploadRowNumber The uploadRowNumber to set.
	 * 
	 */
	public void setUploadRowNumber(Long uploadRowNumber) {
		this.uploadRowNumber = uploadRowNumber;
	}


	/**
	 * Gets the errorCorrectionStatusCode attribute.
	 * 
	 * @return Returns the errorCorrectionStatusCode
	 * 
	 */
	public String getErrorCorrectionStatusCode() { 
		return errorCorrectionStatusCode;
	}

	/**
	 * Sets the errorCorrectionStatusCode attribute.
	 * 
	 * @param errorCorrectionStatusCode The errorCorrectionStatusCode to set.
	 * 
	 */
	public void setErrorCorrectionStatusCode(String errorCorrectionStatusCode) {
		this.errorCorrectionStatusCode = errorCorrectionStatusCode;
	}


	/**
	 * Gets the correctedRowText attribute.
	 * 
	 * @return Returns the correctedRowText
	 * 
	 */
	public String getCorrectedRowText() { 
		return correctedRowText;
	}

	/**
	 * Sets the correctedRowText attribute.
	 * 
	 * @param correctedRowText The correctedRowText to set.
	 * 
	 */
	public void setCorrectedRowText(String correctedRowText) {
		this.correctedRowText = correctedRowText;
	}


	/**
	 * Gets the correctorUniversalIdentifier attribute.
	 * 
	 * @return Returns the correctorUniversalIdentifier
	 * 
	 */
	public String getCorrectorUniversalIdentifier() { 
		return correctorUniversalIdentifier;
	}

	/**
	 * Sets the correctorUniversalIdentifier attribute.
	 * 
	 * @param correctorUniversalIdentifier The correctorUniversalIdentifier to set.
	 * 
	 */
	public void setCorrectorUniversalIdentifier(String correctorUniversalIdentifier) {
		this.correctorUniversalIdentifier = correctorUniversalIdentifier;
	}


	/**
	 * Gets the inventoryCorrectionTimestamp attribute.
	 * 
	 * @return Returns the inventoryCorrectionTimestamp
	 * 
	 */
	public Timestamp getInventoryCorrectionTimestamp() { 
		return inventoryCorrectionTimestamp;
	}

	/**
	 * Sets the inventoryCorrectionTimestamp attribute.
	 * 
	 * @param inventoryCorrectionTimestamp The inventoryCorrectionTimestamp to set.
	 * 
	 */
	public void setInventoryCorrectionTimestamp(Timestamp inventoryCorrectionTimestamp) {
		this.inventoryCorrectionTimestamp = inventoryCorrectionTimestamp;
	}


	/**
	 * Gets the inventoryUploadDetail attribute.
	 * 
	 * @return Returns the inventoryUploadDetail
	 * 
	 */
	public InventoryUploadDetail getInventoryUploadDetail() { 
		return inventoryUploadDetail;
	}

	/**
	 * Sets the inventoryUploadDetail attribute.
	 * 
	 * @param inventoryUploadDetail The inventoryUploadDetail to set.
	 * @deprecated
	 */
	public void setInventoryUploadDetail(InventoryUploadDetail inventoryUploadDetail) {
		this.inventoryUploadDetail = inventoryUploadDetail;
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
        if (this.uploadRowNumber != null) {
            m.put("uploadRowNumber", this.uploadRowNumber.toString());
        }
	    return m;
    }
}
