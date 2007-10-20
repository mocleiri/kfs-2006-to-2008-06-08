package org.kuali.module.cams.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.core.bo.PersistableBusinessObjectBase;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class TaggingUploadHeader extends PersistableBusinessObjectBase {

	private String uploaderUniversalIdentifier;
	private Long uploaderSequenceNumber;
	private Timestamp tagUploadTimestamp;

    private List<TaggingUploadDetail> taggingUploadDetails;

	/**
	 * Default constructor.
	 */
	public TaggingUploadHeader() {
        taggingUploadDetails = new ArrayList<TaggingUploadDetail>();
        
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
	 * Gets the tagUploadTimestamp attribute.
	 * 
	 * @return Returns the tagUploadTimestamp
	 * 
	 */
	public Timestamp getTagUploadTimestamp() { 
		return tagUploadTimestamp;
	}

	/**
	 * Sets the tagUploadTimestamp attribute.
	 * 
	 * @param tagUploadTimestamp The tagUploadTimestamp to set.
	 * 
	 */
	public void setTagUploadTimestamp(Timestamp tagUploadTimestamp) {
		this.tagUploadTimestamp = tagUploadTimestamp;
	}

    /**
     * Gets the taggingUploadDetails attribute. 
     * @return Returns the taggingUploadDetails.
     */
    public List<TaggingUploadDetail> getTaggingUploadDetails() {
        return taggingUploadDetails;
    }

    /**
     * Sets the taggingUploadDetails attribute value.
     * @param taggingUploadDetails The taggingUploadDetails to set.
     */
    public void setTaggingUploadDetails(List<TaggingUploadDetail> taggingUploadDetails) {
        this.taggingUploadDetails = taggingUploadDetails;
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
