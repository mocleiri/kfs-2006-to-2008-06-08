package org.kuali.module.cams.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.DocumentHeader;
import org.kuali.core.bo.PersistableBusinessObjectBase;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class InventoryUploadErrorDocument extends PersistableBusinessObjectBase {

	private String documentNumber;
	private String uploaderUniversalIdentifier;
	private Long uploaderSequenceNumber;

    private DocumentHeader documentHeader;

	/**
	 * Default constructor.
	 */
	public InventoryUploadErrorDocument() {

	}

	/**
	 * Gets the documentNumber attribute.
	 * 
	 * @return Returns the documentNumber
	 * 
	 */
	public String getDocumentNumber() { 
		return documentNumber;
	}

	/**
	 * Sets the documentNumber attribute.
	 * 
	 * @param documentNumber The documentNumber to set.
	 * 
	 */
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
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
	 * Gets the documentHeader attribute.
	 * 
	 * @return Returns the documentHeader
	 * 
	 */
	public DocumentHeader getDocumentHeader() { 
		return documentHeader;
	}

	/**
	 * Sets the documentHeader attribute.
	 * 
	 * @param documentHeader The documentHeader to set.
	 * @deprecated
	 */
	public void setDocumentHeader(DocumentHeader documentHeader) {
		this.documentHeader = documentHeader;
	}

	/**
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
	    LinkedHashMap m = new LinkedHashMap();	    
        m.put("documentNumber", this.documentNumber);
	    return m;
    }
}
