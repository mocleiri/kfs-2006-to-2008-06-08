package org.kuali.module.cams.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.Campus;
import org.kuali.core.bo.DocumentHeader;
import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.kfs.bo.Building;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.Chart;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class AssetHeader extends PersistableBusinessObjectBase {

	private String documentNumber;
	private Long capitalAssetNumber;
	private String representativeUniversalIdentifier;
	private String organizationOwnerChartOfAccountsCode;
	private String organizationOwnerAccountNumber;
	private String agencyNumber;
	private String campusCode;
	private String buildingCode;
	private Integer nextCapitalAssetPaymentLineNumber;

    private DocumentHeader documentHeader;
	private Account organizationOwnerAccount;
	private Chart organizationOwnerChartOfAccounts;
	private Campus campus;
    private Building building;
    
	/**
	 * Default constructor.
	 */
	public AssetHeader() {

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
	 * Gets the agencyNumber attribute.
	 * 
	 * @return Returns the agencyNumber
	 * 
	 */
	public String getAgencyNumber() { 
		return agencyNumber;
	}

	/**
	 * Sets the agencyNumber attribute.
	 * 
	 * @param agencyNumber The agencyNumber to set.
	 * 
	 */
	public void setAgencyNumber(String agencyNumber) {
		this.agencyNumber = agencyNumber;
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
	 * Gets the nextCapitalAssetPaymentLineNumber attribute.
	 * 
	 * @return Returns the nextCapitalAssetPaymentLineNumber
	 * 
	 */
	public Integer getNextCapitalAssetPaymentLineNumber() { 
		return nextCapitalAssetPaymentLineNumber;
	}

	/**
	 * Sets the nextCapitalAssetPaymentLineNumber attribute.
	 * 
	 * @param nextCapitalAssetPaymentLineNumber The nextCapitalAssetPaymentLineNumber to set.
	 * 
	 */
	public void setNextCapitalAssetPaymentLineNumber(Integer nextCapitalAssetPaymentLineNumber) {
		this.nextCapitalAssetPaymentLineNumber = nextCapitalAssetPaymentLineNumber;
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
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();      
        m.put("documentNumber", this.documentNumber);
        return m;
    }

}
