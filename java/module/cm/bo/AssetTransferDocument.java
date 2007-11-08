package org.kuali.module.cams.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.Campus;
import org.kuali.core.bo.DocumentHeader;
import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.kfs.bo.Building;
import org.kuali.kfs.bo.OriginationCode;
import org.kuali.kfs.bo.Room;
import org.kuali.kfs.bo.State;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.Chart;
import org.kuali.module.chart.bo.Org;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class AssetTransferDocument extends PersistableBusinessObjectBase {

	private String documentNumber;
	private String representativeUniversalIdentifier;
	private String campusCode;
	private String buildingCode;
	private String buildingRoomNumber;
	private String buildingSubRoomNumber;
	private String organizationTagNumber;
	private String organizationOwnerChartOfAccountsCode;
	private String organizationOwnerAccountNumber;
	private String organizationText;
	private String organizationInventoryName;
	private String transferOfFundsFinancialDocumentNumber;
	private String organizationCode;
	private String offCampusAddress;
	private String offCampusCityName;
	private String offCampusStateCode;
	private String offCampusZipCode;
	private boolean interdepartmentalSalesIndicator;

    private DocumentHeader documentHeader;
	private Campus campus;
	private Account organizationOwnerAccount;
	private Chart organizationOwnerChartOfAccounts;
	private Org organization;
    private DocumentHeader transferOfFundsFinancialDocument;
    private State offCampusState;
    private Building building;
    private Room buildingRoom;
    
	/**
	 * Default constructor.
	 */
	public AssetTransferDocument() {

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
	 * Gets the representativeUniversalIdentifier attribute.
	 * 
	 * @return Returns the representativeUniversalIdentifier
	 * 
	 */
	public String getRepresentativeUniversalIdentifier() { 
		return representativeUniversalIdentifier;
	}

	/**
	 * Sets the representativeUniversalIdentifier attribute.
	 * 
	 * @param representativeUniversalIdentifier The representativeUniversalIdentifier to set.
	 * 
	 */
	public void setRepresentativeUniversalIdentifier(String representativeUniversalIdentifier) {
		this.representativeUniversalIdentifier = representativeUniversalIdentifier;
	}


	/**
	 * Gets the campusCode attribute.
	 * 
	 * @return Returns the campusCode
	 * 
	 */
	public String getCampusCode() { 
		return campusCode;
	}

	/**
	 * Sets the campusCode attribute.
	 * 
	 * @param campusCode The campusCode to set.
	 * 
	 */
	public void setCampusCode(String campusCode) {
		this.campusCode = campusCode;
	}


	/**
	 * Gets the buildingCode attribute.
	 * 
	 * @return Returns the buildingCode
	 * 
	 */
	public String getBuildingCode() { 
		return buildingCode;
	}

	/**
	 * Sets the buildingCode attribute.
	 * 
	 * @param buildingCode The buildingCode to set.
	 * 
	 */
	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
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
	 * Gets the organizationTagNumber attribute.
	 * 
	 * @return Returns the organizationTagNumber
	 * 
	 */
	public String getOrganizationTagNumber() { 
		return organizationTagNumber;
	}

	/**
	 * Sets the organizationTagNumber attribute.
	 * 
	 * @param organizationTagNumber The organizationTagNumber to set.
	 * 
	 */
	public void setOrganizationTagNumber(String organizationTagNumber) {
		this.organizationTagNumber = organizationTagNumber;
	}


	/**
	 * Gets the organizationOwnerChartOfAccountsCode attribute.
	 * 
	 * @return Returns the organizationOwnerChartOfAccountsCode
	 * 
	 */
	public String getOrganizationOwnerChartOfAccountsCode() { 
		return organizationOwnerChartOfAccountsCode;
	}

	/**
	 * Sets the organizationOwnerChartOfAccountsCode attribute.
	 * 
	 * @param organizationOwnerChartOfAccountsCode The organizationOwnerChartOfAccountsCode to set.
	 * 
	 */
	public void setOrganizationOwnerChartOfAccountsCode(String organizationOwnerChartOfAccountsCode) {
		this.organizationOwnerChartOfAccountsCode = organizationOwnerChartOfAccountsCode;
	}


	/**
	 * Gets the organizationOwnerAccountNumber attribute.
	 * 
	 * @return Returns the organizationOwnerAccountNumber
	 * 
	 */
	public String getOrganizationOwnerAccountNumber() { 
		return organizationOwnerAccountNumber;
	}

	/**
	 * Sets the organizationOwnerAccountNumber attribute.
	 * 
	 * @param organizationOwnerAccountNumber The organizationOwnerAccountNumber to set.
	 * 
	 */
	public void setOrganizationOwnerAccountNumber(String organizationOwnerAccountNumber) {
		this.organizationOwnerAccountNumber = organizationOwnerAccountNumber;
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
	 * Gets the transferOfFundsFinancialDocumentNumber attribute.
	 * 
	 * @return Returns the transferOfFundsFinancialDocumentNumber
	 * 
	 */
	public String getTransferOfFundsFinancialDocumentNumber() { 
		return transferOfFundsFinancialDocumentNumber;
	}

	/**
	 * Sets the transferOfFundsFinancialDocumentNumber attribute.
	 * 
	 * @param transferOfFundsFinancialDocumentNumber The transferOfFundsFinancialDocumentNumber to set.
	 * 
	 */
	public void setTransferOfFundsFinancialDocumentNumber(String transferOfFundsFinancialDocumentNumber) {
		this.transferOfFundsFinancialDocumentNumber = transferOfFundsFinancialDocumentNumber;
	}


	/**
	 * Gets the organizationCode attribute.
	 * 
	 * @return Returns the organizationCode
	 * 
	 */
	public String getOrganizationCode() { 
		return organizationCode;
	}

	/**
	 * Sets the organizationCode attribute.
	 * 
	 * @param organizationCode The organizationCode to set.
	 * 
	 */
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
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
	 * Gets the interdepartmentalSalesIndicator attribute.
	 * 
	 * @return Returns the interdepartmentalSalesIndicator
	 * 
	 */
	public boolean isInterdepartmentalSalesIndicator() { 
		return interdepartmentalSalesIndicator;
	}

	/**
	 * Sets the interdepartmentalSalesIndicator attribute.
	 * 
	 * @param interdepartmentalSalesIndicator The interdepartmentalSalesIndicator to set.
	 * 
	 */
	public void setInterdepartmentalSalesIndicator(boolean interdepartmentalSalesIndicator) {
		this.interdepartmentalSalesIndicator = interdepartmentalSalesIndicator;
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
	 * Gets the campus attribute.
	 * 
	 * @return Returns the campus
	 * 
	 */
	public Campus getCampus() { 
		return campus;
	}

	/**
	 * Sets the campus attribute.
	 * 
	 * @param campus The campus to set.
	 * @deprecated
	 */
	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	/**
	 * Gets the organizationOwnerAccount attribute.
	 * 
	 * @return Returns the organizationOwnerAccount
	 * 
	 */
	public Account getOrganizationOwnerAccount() { 
		return organizationOwnerAccount;
	}

	/**
	 * Sets the organizationOwnerAccount attribute.
	 * 
	 * @param organizationOwnerAccount The organizationOwnerAccount to set.
	 * @deprecated
	 */
	public void setOrganizationOwnerAccount(Account organizationOwnerAccount) {
		this.organizationOwnerAccount = organizationOwnerAccount;
	}

	/**
	 * Gets the organizationOwnerChartOfAccounts attribute.
	 * 
	 * @return Returns the organizationOwnerChartOfAccounts
	 * 
	 */
	public Chart getOrganizationOwnerChartOfAccounts() { 
		return organizationOwnerChartOfAccounts;
	}

	/**
	 * Sets the organizationOwnerChartOfAccounts attribute.
	 * 
	 * @param organizationOwnerChartOfAccounts The organizationOwnerChartOfAccounts to set.
	 * @deprecated
	 */
	public void setOrganizationOwnerChartOfAccounts(Chart organizationOwnerChartOfAccounts) {
		this.organizationOwnerChartOfAccounts = organizationOwnerChartOfAccounts;
	}

	/**
	 * Gets the organization attribute.
	 * 
	 * @return Returns the organization
	 * 
	 */
	public Org getOrganization() { 
		return organization;
	}

	/**
	 * Sets the organization attribute.
	 * 
	 * @param organization The organization to set.
	 * @deprecated
	 */
	public void setOrganization(Org organization) {
		this.organization = organization;
	}

    /**
     * Gets the building attribute. 
     * @return Returns the building.
     */
    public Building getBuilding() {
        return building;
    }

    /**
     * Sets the building attribute value.
     * @param building The building to set.
     * @deprecated
     */
    public void setBuilding(Building building) {
        this.building = building;
    }

    /**
     * Gets the buildingRoom attribute. 
     * @return Returns the buildingRoom.
     */
    public Room getBuildingRoom() {
        return buildingRoom;
    }

    /**
     * Sets the buildingRoom attribute value.
     * @param buildingRoom The buildingRoom to set.
     * @deprecated
     */
    public void setBuildingRoom(Room buildingRoom) {
        this.buildingRoom = buildingRoom;
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
     * Gets the transferOfFundsFinancialDocument attribute. 
     * @return Returns the transferOfFundsFinancialDocument.
     */
    public DocumentHeader getTransferOfFundsFinancialDocument() {
        return transferOfFundsFinancialDocument;
    }

    /**
     * Sets the transferOfFundsFinancialDocument attribute value.
     * @param transferOfFundsFinancialDocument The transferOfFundsFinancialDocument to set.
     * @deprecated
     */
    public void setTransferOfFundsFinancialDocument(DocumentHeader transferOfFundsFinancialDocument) {
        this.transferOfFundsFinancialDocument = transferOfFundsFinancialDocument;
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
