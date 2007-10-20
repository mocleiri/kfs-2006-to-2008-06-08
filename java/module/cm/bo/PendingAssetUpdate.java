package org.kuali.module.cams.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.DocumentHeader;
import org.kuali.core.bo.PersistableBusinessObjectBase;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class PendingAssetUpdate extends PersistableBusinessObjectBase {

	private String financialDocumentTypeCode;
	private String documentNumber;
	private Long capitalAssetNumber;
	private Long lineItemNumber;

    private DocumentHeader documentHeader;
    private Asset asset;
    
	/**
	 * Default constructor.
	 */
	public PendingAssetUpdate() {

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
	 * Gets the capitalAssetNumber attribute.
	 * 
	 * @return Returns the capitalAssetNumber
	 * 
	 */
	public Long getCapitalAssetNumber() { 
		return capitalAssetNumber;
	}

	/**
	 * Sets the capitalAssetNumber attribute.
	 * 
	 * @param capitalAssetNumber The capitalAssetNumber to set.
	 * 
	 */
	public void setCapitalAssetNumber(Long capitalAssetNumber) {
		this.capitalAssetNumber = capitalAssetNumber;
	}


	/**
	 * Gets the lineItemNumber attribute.
	 * 
	 * @return Returns the lineItemNumber
	 * 
	 */
	public Long getLineItemNumber() { 
		return lineItemNumber;
	}

	/**
	 * Sets the lineItemNumber attribute.
	 * 
	 * @param lineItemNumber The lineItemNumber to set.
	 * 
	 */
	public void setLineItemNumber(Long lineItemNumber) {
		this.lineItemNumber = lineItemNumber;
	}

    /**
     * Gets the asset attribute. 
     * @return Returns the asset.
     */
    public Asset getAsset() {
        return asset;
    }

    /**
     * Sets the asset attribute value.
     * @param asset The asset to set.
     * @deprecated
     */
    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    /**
     * Gets the documentHeader attribute. 
     * @return Returns the documentHeader.
     */
    public DocumentHeader getDocumentHeader() {
        return documentHeader;
    }

    /**
     * Sets the documentHeader attribute value.
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
        m.put("financialDocumentTypeCode", this.financialDocumentTypeCode);
        m.put("documentNumber", this.documentNumber);
        if (this.capitalAssetNumber != null) {
            m.put("capitalAssetNumber", this.capitalAssetNumber.toString());
        }
        if (this.lineItemNumber != null) {
            m.put("lineItemNumber", this.lineItemNumber.toString());
        }
        return m;
    }

}
