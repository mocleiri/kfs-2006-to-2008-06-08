package org.kuali.module.cams.bo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.core.bo.DocumentHeader;
import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.core.util.KualiDecimal;
import org.kuali.module.ar.bo.CashControlDetail;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class MultipleAssetDocument extends PersistableBusinessObjectBase {

	private String documentNumber;
	private Integer financialDocumentNextLineNumber;
	private String acquisitionTypeCode;
	private String capitalAssetDescription;
	private String inventoryStatusCode;
	private String conditionCode;
	private String primaryDepreciationMethodCode;
	private KualiDecimal primaryDepreciationBaseAmount;
	private String acquisitionSourceName;
	private String capitalAssetTypeCode;
	private String manufacturerName;
	private String manufacturerModelNumber;
	private Date capitalizationDate;
	private KualiDecimal totalCostAmount;

    private DocumentHeader documentHeader;
    private AssetStatus inventoryStatus;

    private List<MultipleAssetDetail> multipleAssetDetails;
    
	/**
	 * Default constructor.
	 */
	public MultipleAssetDocument() {
        multipleAssetDetails = new ArrayList<MultipleAssetDetail>();
        
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
	 * Gets the financialDocumentNextLineNumber attribute.
	 * 
	 * @return Returns the financialDocumentNextLineNumber
	 * 
	 */
	public Integer getFinancialDocumentNextLineNumber() { 
		return financialDocumentNextLineNumber;
	}

	/**
	 * Sets the financialDocumentNextLineNumber attribute.
	 * 
	 * @param financialDocumentNextLineNumber The financialDocumentNextLineNumber to set.
	 * 
	 */
	public void setFinancialDocumentNextLineNumber(Integer financialDocumentNextLineNumber) {
		this.financialDocumentNextLineNumber = financialDocumentNextLineNumber;
	}


	/**
	 * Gets the acquisitionTypeCode attribute.
	 * 
	 * @return Returns the acquisitionTypeCode
	 * 
	 */
	public String getAcquisitionTypeCode() { 
		return acquisitionTypeCode;
	}

	/**
	 * Sets the acquisitionTypeCode attribute.
	 * 
	 * @param acquisitionTypeCode The acquisitionTypeCode to set.
	 * 
	 */
	public void setAcquisitionTypeCode(String acquisitionTypeCode) {
		this.acquisitionTypeCode = acquisitionTypeCode;
	}


	/**
	 * Gets the capitalAssetDescription attribute.
	 * 
	 * @return Returns the capitalAssetDescription
	 * 
	 */
	public String getCapitalAssetDescription() { 
		return capitalAssetDescription;
	}

	/**
	 * Sets the capitalAssetDescription attribute.
	 * 
	 * @param capitalAssetDescription The capitalAssetDescription to set.
	 * 
	 */
	public void setCapitalAssetDescription(String capitalAssetDescription) {
		this.capitalAssetDescription = capitalAssetDescription;
	}


	/**
	 * Gets the inventoryStatusCode attribute.
	 * 
	 * @return Returns the inventoryStatusCode
	 * 
	 */
	public String getInventoryStatusCode() { 
		return inventoryStatusCode;
	}

	/**
	 * Sets the inventoryStatusCode attribute.
	 * 
	 * @param inventoryStatusCode The inventoryStatusCode to set.
	 * 
	 */
	public void setInventoryStatusCode(String inventoryStatusCode) {
		this.inventoryStatusCode = inventoryStatusCode;
	}


	/**
	 * Gets the conditionCode attribute.
	 * 
	 * @return Returns the conditionCode
	 * 
	 */
	public String getConditionCode() { 
		return conditionCode;
	}

	/**
	 * Sets the conditionCode attribute.
	 * 
	 * @param conditionCode The conditionCode to set.
	 * 
	 */
	public void setConditionCode(String conditionCode) {
		this.conditionCode = conditionCode;
	}


	/**
	 * Gets the primaryDepreciationMethodCode attribute.
	 * 
	 * @return Returns the primaryDepreciationMethodCode
	 * 
	 */
	public String getPrimaryDepreciationMethodCode() { 
		return primaryDepreciationMethodCode;
	}

	/**
	 * Sets the primaryDepreciationMethodCode attribute.
	 * 
	 * @param primaryDepreciationMethodCode The primaryDepreciationMethodCode to set.
	 * 
	 */
	public void setPrimaryDepreciationMethodCode(String primaryDepreciationMethodCode) {
		this.primaryDepreciationMethodCode = primaryDepreciationMethodCode;
	}


	/**
	 * Gets the primaryDepreciationBaseAmount attribute.
	 * 
	 * @return Returns the primaryDepreciationBaseAmount
	 * 
	 */
	public KualiDecimal getPrimaryDepreciationBaseAmount() { 
		return primaryDepreciationBaseAmount;
	}

	/**
	 * Sets the primaryDepreciationBaseAmount attribute.
	 * 
	 * @param primaryDepreciationBaseAmount The primaryDepreciationBaseAmount to set.
	 * 
	 */
	public void setPrimaryDepreciationBaseAmount(KualiDecimal primaryDepreciationBaseAmount) {
		this.primaryDepreciationBaseAmount = primaryDepreciationBaseAmount;
	}


	/**
	 * Gets the acquisitionSourceName attribute.
	 * 
	 * @return Returns the acquisitionSourceName
	 * 
	 */
	public String getAcquisitionSourceName() { 
		return acquisitionSourceName;
	}

	/**
	 * Sets the acquisitionSourceName attribute.
	 * 
	 * @param acquisitionSourceName The acquisitionSourceName to set.
	 * 
	 */
	public void setAcquisitionSourceName(String acquisitionSourceName) {
		this.acquisitionSourceName = acquisitionSourceName;
	}


	/**
	 * Gets the capitalAssetTypeCode attribute.
	 * 
	 * @return Returns the capitalAssetTypeCode
	 * 
	 */
	public String getCapitalAssetTypeCode() { 
		return capitalAssetTypeCode;
	}

	/**
	 * Sets the capitalAssetTypeCode attribute.
	 * 
	 * @param capitalAssetTypeCode The capitalAssetTypeCode to set.
	 * 
	 */
	public void setCapitalAssetTypeCode(String capitalAssetTypeCode) {
		this.capitalAssetTypeCode = capitalAssetTypeCode;
	}


	/**
	 * Gets the manufacturerName attribute.
	 * 
	 * @return Returns the manufacturerName
	 * 
	 */
	public String getManufacturerName() { 
		return manufacturerName;
	}

	/**
	 * Sets the manufacturerName attribute.
	 * 
	 * @param manufacturerName The manufacturerName to set.
	 * 
	 */
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}


	/**
	 * Gets the manufacturerModelNumber attribute.
	 * 
	 * @return Returns the manufacturerModelNumber
	 * 
	 */
	public String getManufacturerModelNumber() { 
		return manufacturerModelNumber;
	}

	/**
	 * Sets the manufacturerModelNumber attribute.
	 * 
	 * @param manufacturerModelNumber The manufacturerModelNumber to set.
	 * 
	 */
	public void setManufacturerModelNumber(String manufacturerModelNumber) {
		this.manufacturerModelNumber = manufacturerModelNumber;
	}


	/**
	 * Gets the capitalizationDate attribute.
	 * 
	 * @return Returns the capitalizationDate
	 * 
	 */
	public Date getCapitalizationDate() { 
		return capitalizationDate;
	}

	/**
	 * Sets the capitalizationDate attribute.
	 * 
	 * @param capitalizationDate The capitalizationDate to set.
	 * 
	 */
	public void setCapitalizationDate(Date capitalizationDate) {
		this.capitalizationDate = capitalizationDate;
	}


	/**
	 * Gets the totalCostAmount attribute.
	 * 
	 * @return Returns the totalCostAmount
	 * 
	 */
	public KualiDecimal getTotalCostAmount() { 
		return totalCostAmount;
	}

	/**
	 * Sets the totalCostAmount attribute.
	 * 
	 * @param totalCostAmount The totalCostAmount to set.
	 * 
	 */
	public void setTotalCostAmount(KualiDecimal totalCostAmount) {
		this.totalCostAmount = totalCostAmount;
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
     * Gets the inventoryStatus attribute. 
     * @return Returns the inventoryStatus.
     */
    public AssetStatus getInventoryStatus() {
        return inventoryStatus;
    }

    /**
     * Sets the inventoryStatus attribute value.
     * @param inventoryStatus The inventoryStatus to set.
     * @deprecated
     */
    public void setInventoryStatus(AssetStatus inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    /**
     * Gets the multipleAssetDetails attribute. 
     * @return Returns the multipleAssetDetails.
     */
    public List<MultipleAssetDetail> getMultipleAssetDetails() {
        return multipleAssetDetails;
    }

    /**
     * Sets the multipleAssetDetails attribute value.
     * @param multipleAssetDetails The multipleAssetDetails to set.
     */
    public void setMultipleAssetDetails(List<MultipleAssetDetail> multipleAssetDetails) {
        this.multipleAssetDetails = multipleAssetDetails;
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
