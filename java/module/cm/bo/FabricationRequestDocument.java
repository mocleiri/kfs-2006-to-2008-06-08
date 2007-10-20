package org.kuali.module.cams.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.core.bo.DocumentHeader;
import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kfs.bo.State;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class FabricationRequestDocument extends PersistableBusinessObjectBase {

	private String documentNumber;
	private String capitalAssetDescription;
	private String organizationInventoryName;
	private String inventoryStatusCode;
	private String conditionCode;
	private String capitalAssetTypeCode;
	private String organizationCapitalAssetTypeIdentifier;
	private Date fabricationApprovalDate;
	private Date estimatedFabricationCompletionDate;
	private KualiDecimal fabricationEstimatedTotalAmount;
	private Integer estimatedLifetimeLimit;
	private String organizationText;
	private Date receiveDate;
	private String acquisitionSourceName;
	private String acquisitionTypeCode;
	private String manufacturerName;
	private String buildingRoomNumber;
	private String buildingSubRoomNumber;
	private String primaryDepreciationMethodCode;
	private String offCampusAddress;
	private String offCampusCityName;
	private String offCampusStateCode;
	private String offCampusZipCode;

    private DocumentHeader documentHeader;
    private AssetType capitalAssetType;
    private State offCampusState;

	/**
	 * Default constructor.
	 */
	public FabricationRequestDocument() {

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
	 * Gets the organizationInventoryName attribute.
	 * 
	 * @return Returns the organizationInventoryName
	 * 
	 */
	public String getOrganizationInventoryName() { 
		return organizationInventoryName;
	}

	/**
	 * Sets the organizationInventoryName attribute.
	 * 
	 * @param organizationInventoryName The organizationInventoryName to set.
	 * 
	 */
	public void setOrganizationInventoryName(String organizationInventoryName) {
		this.organizationInventoryName = organizationInventoryName;
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
	 * Gets the organizationCapitalAssetTypeIdentifier attribute.
	 * 
	 * @return Returns the organizationCapitalAssetTypeIdentifier
	 * 
	 */
	public String getOrganizationCapitalAssetTypeIdentifier() { 
		return organizationCapitalAssetTypeIdentifier;
	}

	/**
	 * Sets the organizationCapitalAssetTypeIdentifier attribute.
	 * 
	 * @param organizationCapitalAssetTypeIdentifier The organizationCapitalAssetTypeIdentifier to set.
	 * 
	 */
	public void setOrganizationCapitalAssetTypeIdentifier(String organizationCapitalAssetTypeIdentifier) {
		this.organizationCapitalAssetTypeIdentifier = organizationCapitalAssetTypeIdentifier;
	}


	/**
	 * Gets the fabricationApprovalDate attribute.
	 * 
	 * @return Returns the fabricationApprovalDate
	 * 
	 */
	public Date getFabricationApprovalDate() { 
		return fabricationApprovalDate;
	}

	/**
	 * Sets the fabricationApprovalDate attribute.
	 * 
	 * @param fabricationApprovalDate The fabricationApprovalDate to set.
	 * 
	 */
	public void setFabricationApprovalDate(Date fabricationApprovalDate) {
		this.fabricationApprovalDate = fabricationApprovalDate;
	}


	/**
	 * Gets the estimatedFabricationCompletionDate attribute.
	 * 
	 * @return Returns the estimatedFabricationCompletionDate
	 * 
	 */
	public Date getEstimatedFabricationCompletionDate() { 
		return estimatedFabricationCompletionDate;
	}

	/**
	 * Sets the estimatedFabricationCompletionDate attribute.
	 * 
	 * @param estimatedFabricationCompletionDate The estimatedFabricationCompletionDate to set.
	 * 
	 */
	public void setEstimatedFabricationCompletionDate(Date estimatedFabricationCompletionDate) {
		this.estimatedFabricationCompletionDate = estimatedFabricationCompletionDate;
	}


	/**
	 * Gets the fabricationEstimatedTotalAmount attribute.
	 * 
	 * @return Returns the fabricationEstimatedTotalAmount
	 * 
	 */
	public KualiDecimal getFabricationEstimatedTotalAmount() { 
		return fabricationEstimatedTotalAmount;
	}

	/**
	 * Sets the fabricationEstimatedTotalAmount attribute.
	 * 
	 * @param fabricationEstimatedTotalAmount The fabricationEstimatedTotalAmount to set.
	 * 
	 */
	public void setFabricationEstimatedTotalAmount(KualiDecimal fabricationEstimatedTotalAmount) {
		this.fabricationEstimatedTotalAmount = fabricationEstimatedTotalAmount;
	}


	/**
	 * Gets the estimatedLifetimeLimit attribute.
	 * 
	 * @return Returns the estimatedLifetimeLimit
	 * 
	 */
	public Integer getEstimatedLifetimeLimit() { 
		return estimatedLifetimeLimit;
	}

	/**
	 * Sets the estimatedLifetimeLimit attribute.
	 * 
	 * @param estimatedLifetimeLimit The estimatedLifetimeLimit to set.
	 * 
	 */
	public void setEstimatedLifetimeLimit(Integer estimatedLifetimeLimit) {
		this.estimatedLifetimeLimit = estimatedLifetimeLimit;
	}


	/**
	 * Gets the organizationText attribute.
	 * 
	 * @return Returns the organizationText
	 * 
	 */
	public String getOrganizationText() { 
		return organizationText;
	}

	/**
	 * Sets the organizationText attribute.
	 * 
	 * @param organizationText The organizationText to set.
	 * 
	 */
	public void setOrganizationText(String organizationText) {
		this.organizationText = organizationText;
	}


	/**
	 * Gets the receiveDate attribute.
	 * 
	 * @return Returns the receiveDate
	 * 
	 */
	public Date getReceiveDate() { 
		return receiveDate;
	}

	/**
	 * Sets the receiveDate attribute.
	 * 
	 * @param receiveDate The receiveDate to set.
	 * 
	 */
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
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
	 * Gets the buildingRoomNumber attribute.
	 * 
	 * @return Returns the buildingRoomNumber
	 * 
	 */
	public String getBuildingRoomNumber() { 
		return buildingRoomNumber;
	}

	/**
	 * Sets the buildingRoomNumber attribute.
	 * 
	 * @param buildingRoomNumber The buildingRoomNumber to set.
	 * 
	 */
	public void setBuildingRoomNumber(String buildingRoomNumber) {
		this.buildingRoomNumber = buildingRoomNumber;
	}


	/**
	 * Gets the buildingSubRoomNumber attribute.
	 * 
	 * @return Returns the buildingSubRoomNumber
	 * 
	 */
	public String getBuildingSubRoomNumber() { 
		return buildingSubRoomNumber;
	}

	/**
	 * Sets the buildingSubRoomNumber attribute.
	 * 
	 * @param buildingSubRoomNumber The buildingSubRoomNumber to set.
	 * 
	 */
	public void setBuildingSubRoomNumber(String buildingSubRoomNumber) {
		this.buildingSubRoomNumber = buildingSubRoomNumber;
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
	 * Gets the offCampusAddress attribute.
	 * 
	 * @return Returns the offCampusAddress
	 * 
	 */
	public String getOffCampusAddress() { 
		return offCampusAddress;
	}

	/**
	 * Sets the offCampusAddress attribute.
	 * 
	 * @param offCampusAddress The offCampusAddress to set.
	 * 
	 */
	public void setOffCampusAddress(String offCampusAddress) {
		this.offCampusAddress = offCampusAddress;
	}


	/**
	 * Gets the offCampusCityName attribute.
	 * 
	 * @return Returns the offCampusCityName
	 * 
	 */
	public String getOffCampusCityName() { 
		return offCampusCityName;
	}

	/**
	 * Sets the offCampusCityName attribute.
	 * 
	 * @param offCampusCityName The offCampusCityName to set.
	 * 
	 */
	public void setOffCampusCityName(String offCampusCityName) {
		this.offCampusCityName = offCampusCityName;
	}


	/**
	 * Gets the offCampusStateCode attribute.
	 * 
	 * @return Returns the offCampusStateCode
	 * 
	 */
	public String getOffCampusStateCode() { 
		return offCampusStateCode;
	}

	/**
	 * Sets the offCampusStateCode attribute.
	 * 
	 * @param offCampusStateCode The offCampusStateCode to set.
	 * 
	 */
	public void setOffCampusStateCode(String offCampusStateCode) {
		this.offCampusStateCode = offCampusStateCode;
	}


	/**
	 * Gets the offCampusZipCode attribute.
	 * 
	 * @return Returns the offCampusZipCode
	 * 
	 */
	public String getOffCampusZipCode() { 
		return offCampusZipCode;
	}

	/**
	 * Sets the offCampusZipCode attribute.
	 * 
	 * @param offCampusZipCode The offCampusZipCode to set.
	 * 
	 */
	public void setOffCampusZipCode(String offCampusZipCode) {
		this.offCampusZipCode = offCampusZipCode;
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
     * Gets the capitalAssetType attribute. 
     * @return Returns the capitalAssetType.
     */
    public AssetType getCapitalAssetType() {
        return capitalAssetType;
    }

    /**
     * Sets the capitalAssetType attribute value.
     * @param capitalAssetType The capitalAssetType to set.
     * @deprecated
     */
    public void setCapitalAssetType(AssetType capitalAssetType) {
        this.capitalAssetType = capitalAssetType;
    }

    /**
     * Gets the offCampusState attribute. 
     * @return Returns the offCampusState.
     */
    public State getOffCampusState() {
        return offCampusState;
    }

    /**
     * Sets the offCampusState attribute value.
     * @param offCampusState The offCampusState to set.
     * @deprecated
     */
    public void setOffCampusState(State offCampusState) {
        this.offCampusState = offCampusState;
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
