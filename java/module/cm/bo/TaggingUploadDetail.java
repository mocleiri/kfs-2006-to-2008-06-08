package org.kuali.module.cams.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class TaggingUploadDetail extends PersistableBusinessObjectBase {

	private String uploaderUniversalIdentifier;
	private Long uploaderSequenceNumber;
	private Long uploadRowNumber;
	private String uploadRowText;
	private boolean uploadErrorIndicator;

	/**
	 * Default constructor.
	 */
	public TaggingUploadDetail() {

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
	 * Gets the uploadRowText attribute.
	 * 
	 * @return Returns the uploadRowText
	 * 
	 */
	public String getUploadRowText() { 
		return uploadRowText;
	}

	/**
	 * Sets the uploadRowText attribute.
	 * 
	 * @param uploadRowText The uploadRowText to set.
	 * 
	 */
	public void setUploadRowText(String uploadRowText) {
		this.uploadRowText = uploadRowText;
	}


	/**
	 * Gets the uploadErrorIndicator attribute.
	 * 
	 * @return Returns the uploadErrorIndicator
	 * 
	 */
	public boolean isUploadErrorIndicator() { 
		return uploadErrorIndicator;
	}

	/**
	 * Sets the uploadErrorIndicator attribute.
	 * 
	 * @param uploadErrorIndicator The uploadErrorIndicator to set.
	 * 
	 */
	public void setUploadErrorIndicator(boolean uploadErrorIndicator) {
		this.uploadErrorIndicator = uploadErrorIndicator;
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
