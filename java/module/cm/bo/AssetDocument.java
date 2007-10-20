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
public class AssetDocument extends PersistableBusinessObjectBase {

	private String documentNumber;
	private String buildingRoomNumber;
	private String buildingSubRoomNumber;
	private String campusTagNumber;
	private String acquisitionTypeCode;
	private String capitalAssetDescription;
	private String inventoryStatusCode;
	private String conditionCode;
	private String primaryDepreciationMethodCode;
	private KualiDecimal primaryDepreciationBaseAmount;
	private String organizationText;
	private String organizationInventoryName;
	private String acquisitionSourceName;
	private String capitalAssetTypeCode;
	private String organizationCapitalAssetTypeIdentifier;
	private String serialNumber;
	private String manufacturerName;
	private String manufacturerModelNumber;
	private Date capitalizationDate;
	private KualiDecimal totalCostAmount;
	private String offCampusAddress;
	private String offCampusCityName;
	private String offCampusStateCode;
	private String offCampusZipCode;

    private DocumentHeader documentHeader;
    private State offCampusState;
    
	/**
	 * Default constructor.
	 */
	public AssetDocument() {

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
	 * Gets the campusTagNumber attribute.
	 * 
	 * @return Returns the campusTagNumber
	 * 
	 */
	public String getCampusTagNumber() { 
		return campusTagNumber;
	}

	/**
	 * Sets the campusTagNumber attribute.
	 * 
	 * @param campusTagNumber The campusTagNumber to set.
	 * 
	 */
	public void setCampusTagNumber(String campusTagNumber) {
		this.campusTagNumber = campusTagNumber;
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
	 * Gets the serialNumber attribute.
	 * 
	 * @return Returns the serialNumber
	 * 
	 */
	public String getSerialNumber() { 
		return serialNumber;
	}

	/**
	 * Sets the serialNumber attribute.
	 * 
	 * @param serialNumber The serialNumber to set.
	 * 
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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
