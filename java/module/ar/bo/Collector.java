package org.kuali.module.ar.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.module.chart.bo.Chart;
import org.kuali.module.chart.bo.Org;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class Collector extends PersistableBusinessObjectBase {

	private String chartOfAccountsCode;
	private String organizationCode;
	private String organizationAssignmentCollectorIdentifier;
	private String collectorName;
	private String collectorTitle;
	private String collectorCampusBuildingRoomAddress;
	private String collectorCampusStreetAddress;
	private String collectorCampusCityName;
	private String collectorCampusStateCode;
	private String collectorCampusZipCode;
	private String collectorPhoneNumber;
	private String collectorRemitLine1Address;
	private String collectorRemitLine2Address;
	private String collectorRemitCityName;
	private String collectorRemitStateCode;
	private String collectorRemitZipCode;
	private String collectorUniversalIdentifier;
	private String collectorFaxNumber;
	private String collector800PhoneNumber;

    private Org organization;
	private Chart chartOfAccounts;

	/**
	 * Default constructor.
	 */
	public Collector() {

	}

	/**
	 * Gets the chartOfAccountsCode attribute.
	 * 
	 * @return Returns the chartOfAccountsCode
	 * 
	 */
	public String getChartOfAccountsCode() { 
		return chartOfAccountsCode;
	}

	/**
	 * Sets the chartOfAccountsCode attribute.
	 * 
	 * @param chartOfAccountsCode The chartOfAccountsCode to set.
	 * 
	 */
	public void setChartOfAccountsCode(String chartOfAccountsCode) {
		this.chartOfAccountsCode = chartOfAccountsCode;
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
	 * Gets the organizationAssignmentCollectorIdentifier attribute.
	 * 
	 * @return Returns the organizationAssignmentCollectorIdentifier
	 * 
	 */
	public String getOrganizationAssignmentCollectorIdentifier() { 
		return organizationAssignmentCollectorIdentifier;
	}

	/**
	 * Sets the organizationAssignmentCollectorIdentifier attribute.
	 * 
	 * @param organizationAssignmentCollectorIdentifier The organizationAssignmentCollectorIdentifier to set.
	 * 
	 */
	public void setOrganizationAssignmentCollectorIdentifier(String organizationAssignmentCollectorIdentifier) {
		this.organizationAssignmentCollectorIdentifier = organizationAssignmentCollectorIdentifier;
	}


	/**
	 * Gets the collectorName attribute.
	 * 
	 * @return Returns the collectorName
	 * 
	 */
	public String getCollectorName() { 
		return collectorName;
	}

	/**
	 * Sets the collectorName attribute.
	 * 
	 * @param collectorName The collectorName to set.
	 * 
	 */
	public void setCollectorName(String collectorName) {
		this.collectorName = collectorName;
	}


	/**
	 * Gets the collectorTitle attribute.
	 * 
	 * @return Returns the collectorTitle
	 * 
	 */
	public String getCollectorTitle() { 
		return collectorTitle;
	}

	/**
	 * Sets the collectorTitle attribute.
	 * 
	 * @param collectorTitle The collectorTitle to set.
	 * 
	 */
	public void setCollectorTitle(String collectorTitle) {
		this.collectorTitle = collectorTitle;
	}


	/**
	 * Gets the collectorCampusBuildingRoomAddress attribute.
	 * 
	 * @return Returns the collectorCampusBuildingRoomAddress
	 * 
	 */
	public String getCollectorCampusBuildingRoomAddress() { 
		return collectorCampusBuildingRoomAddress;
	}

	/**
	 * Sets the collectorCampusBuildingRoomAddress attribute.
	 * 
	 * @param collectorCampusBuildingRoomAddress The collectorCampusBuildingRoomAddress to set.
	 * 
	 */
	public void setCollectorCampusBuildingRoomAddress(String collectorCampusBuildingRoomAddress) {
		this.collectorCampusBuildingRoomAddress = collectorCampusBuildingRoomAddress;
	}


	/**
	 * Gets the collectorCampusStreetAddress attribute.
	 * 
	 * @return Returns the collectorCampusStreetAddress
	 * 
	 */
	public String getCollectorCampusStreetAddress() { 
		return collectorCampusStreetAddress;
	}

	/**
	 * Sets the collectorCampusStreetAddress attribute.
	 * 
	 * @param collectorCampusStreetAddress The collectorCampusStreetAddress to set.
	 * 
	 */
	public void setCollectorCampusStreetAddress(String collectorCampusStreetAddress) {
		this.collectorCampusStreetAddress = collectorCampusStreetAddress;
	}


	/**
	 * Gets the collectorCampusCityName attribute.
	 * 
	 * @return Returns the collectorCampusCityName
	 * 
	 */
	public String getCollectorCampusCityName() { 
		return collectorCampusCityName;
	}

	/**
	 * Sets the collectorCampusCityName attribute.
	 * 
	 * @param collectorCampusCityName The collectorCampusCityName to set.
	 * 
	 */
	public void setCollectorCampusCityName(String collectorCampusCityName) {
		this.collectorCampusCityName = collectorCampusCityName;
	}


	/**
	 * Gets the collectorCampusStateCode attribute.
	 * 
	 * @return Returns the collectorCampusStateCode
	 * 
	 */
	public String getCollectorCampusStateCode() { 
		return collectorCampusStateCode;
	}

	/**
	 * Sets the collectorCampusStateCode attribute.
	 * 
	 * @param collectorCampusStateCode The collectorCampusStateCode to set.
	 * 
	 */
	public void setCollectorCampusStateCode(String collectorCampusStateCode) {
		this.collectorCampusStateCode = collectorCampusStateCode;
	}


	/**
	 * Gets the collectorCampusZipCode attribute.
	 * 
	 * @return Returns the collectorCampusZipCode
	 * 
	 */
	public String getCollectorCampusZipCode() { 
		return collectorCampusZipCode;
	}

	/**
	 * Sets the collectorCampusZipCode attribute.
	 * 
	 * @param collectorCampusZipCode The collectorCampusZipCode to set.
	 * 
	 */
	public void setCollectorCampusZipCode(String collectorCampusZipCode) {
		this.collectorCampusZipCode = collectorCampusZipCode;
	}


	/**
	 * Gets the collectorPhoneNumber attribute.
	 * 
	 * @return Returns the collectorPhoneNumber
	 * 
	 */
	public String getCollectorPhoneNumber() { 
		return collectorPhoneNumber;
	}

	/**
	 * Sets the collectorPhoneNumber attribute.
	 * 
	 * @param collectorPhoneNumber The collectorPhoneNumber to set.
	 * 
	 */
	public void setCollectorPhoneNumber(String collectorPhoneNumber) {
		this.collectorPhoneNumber = collectorPhoneNumber;
	}


	/**
	 * Gets the collectorRemitLine1Address attribute.
	 * 
	 * @return Returns the collectorRemitLine1Address
	 * 
	 */
	public String getCollectorRemitLine1Address() { 
		return collectorRemitLine1Address;
	}

	/**
	 * Sets the collectorRemitLine1Address attribute.
	 * 
	 * @param collectorRemitLine1Address The collectorRemitLine1Address to set.
	 * 
	 */
	public void setCollectorRemitLine1Address(String collectorRemitLine1Address) {
		this.collectorRemitLine1Address = collectorRemitLine1Address;
	}


	/**
	 * Gets the collectorRemitLine2Address attribute.
	 * 
	 * @return Returns the collectorRemitLine2Address
	 * 
	 */
	public String getCollectorRemitLine2Address() { 
		return collectorRemitLine2Address;
	}

	/**
	 * Sets the collectorRemitLine2Address attribute.
	 * 
	 * @param collectorRemitLine2Address The collectorRemitLine2Address to set.
	 * 
	 */
	public void setCollectorRemitLine2Address(String collectorRemitLine2Address) {
		this.collectorRemitLine2Address = collectorRemitLine2Address;
	}


	/**
	 * Gets the collectorRemitCityName attribute.
	 * 
	 * @return Returns the collectorRemitCityName
	 * 
	 */
	public String getCollectorRemitCityName() { 
		return collectorRemitCityName;
	}

	/**
	 * Sets the collectorRemitCityName attribute.
	 * 
	 * @param collectorRemitCityName The collectorRemitCityName to set.
	 * 
	 */
	public void setCollectorRemitCityName(String collectorRemitCityName) {
		this.collectorRemitCityName = collectorRemitCityName;
	}


	/**
	 * Gets the collectorRemitStateCode attribute.
	 * 
	 * @return Returns the collectorRemitStateCode
	 * 
	 */
	public String getCollectorRemitStateCode() { 
		return collectorRemitStateCode;
	}

	/**
	 * Sets the collectorRemitStateCode attribute.
	 * 
	 * @param collectorRemitStateCode The collectorRemitStateCode to set.
	 * 
	 */
	public void setCollectorRemitStateCode(String collectorRemitStateCode) {
		this.collectorRemitStateCode = collectorRemitStateCode;
	}


	/**
	 * Gets the collectorRemitZipCode attribute.
	 * 
	 * @return Returns the collectorRemitZipCode
	 * 
	 */
	public String getCollectorRemitZipCode() { 
		return collectorRemitZipCode;
	}

	/**
	 * Sets the collectorRemitZipCode attribute.
	 * 
	 * @param collectorRemitZipCode The collectorRemitZipCode to set.
	 * 
	 */
	public void setCollectorRemitZipCode(String collectorRemitZipCode) {
		this.collectorRemitZipCode = collectorRemitZipCode;
	}


	/**
	 * Gets the collectorUniversalIdentifier attribute.
	 * 
	 * @return Returns the collectorUniversalIdentifier
	 * 
	 */
	public String getCollectorUniversalIdentifier() { 
		return collectorUniversalIdentifier;
	}

	/**
	 * Sets the collectorUniversalIdentifier attribute.
	 * 
	 * @param collectorUniversalIdentifier The collectorUniversalIdentifier to set.
	 * 
	 */
	public void setCollectorUniversalIdentifier(String collectorUniversalIdentifier) {
		this.collectorUniversalIdentifier = collectorUniversalIdentifier;
	}


	/**
	 * Gets the collectorFaxNumber attribute.
	 * 
	 * @return Returns the collectorFaxNumber
	 * 
	 */
	public String getCollectorFaxNumber() { 
		return collectorFaxNumber;
	}

	/**
	 * Sets the collectorFaxNumber attribute.
	 * 
	 * @param collectorFaxNumber The collectorFaxNumber to set.
	 * 
	 */
	public void setCollectorFaxNumber(String collectorFaxNumber) {
		this.collectorFaxNumber = collectorFaxNumber;
	}


	/**
	 * Gets the collector800PhoneNumber attribute.
	 * 
	 * @return Returns the collector800PhoneNumber
	 * 
	 */
	public String getCollector800PhoneNumber() { 
		return collector800PhoneNumber;
	}

	/**
	 * Sets the collector800PhoneNumber attribute.
	 * 
	 * @param collector800PhoneNumber The collector800PhoneNumber to set.
	 * 
	 */
	public void setCollector800PhoneNumber(String collector800PhoneNumber) {
		this.collector800PhoneNumber = collector800PhoneNumber;
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
	 * Gets the chartOfAccounts attribute.
	 * 
	 * @return Returns the chartOfAccounts
	 * 
	 */
	public Chart getChartOfAccounts() { 
		return chartOfAccounts;
	}

	/**
	 * Sets the chartOfAccounts attribute.
	 * 
	 * @param chartOfAccounts The chartOfAccounts to set.
	 * @deprecated
	 */
	public void setChartOfAccounts(Chart chartOfAccounts) {
		this.chartOfAccounts = chartOfAccounts;
	}

	/**
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
	    LinkedHashMap m = new LinkedHashMap();	    
        m.put("chartOfAccountsCode", this.chartOfAccountsCode);
        m.put("organizationCode", this.organizationCode);
        m.put("organizationAssignmentCollectorIdentifier", this.organizationAssignmentCollectorIdentifier);
	    return m;
    }
}
