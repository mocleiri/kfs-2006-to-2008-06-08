package org.kuali.module.cams.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.module.chart.bo.Chart;
import org.kuali.module.chart.bo.Org;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class AssetTaggerSecurity extends PersistableBusinessObjectBase {

	private String chartOfAccountsCode;
	private String organizationCode;
	private String userChartOfAccountsCode;
	private String userOrganizationCode;
	private String securityChartOfAccountsCode;
	private String securityOrganizationCode;
	private String reportChartOfAccountsCode;
	private String reportOrganizationCode;

    private Chart chartOfAccounts;
	private Org organization;
	private Chart userChartOfAccounts;
	private Org userOrganization;
	private Chart securityChartOfAccounts;
	private Org securityOrganization;
	private Org reportOrganization;
	private Chart reportChartOfAccounts;

	/**
	 * Default constructor.
	 */
	public AssetTaggerSecurity() {

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
	 * Gets the userChartOfAccountsCode attribute.
	 * 
	 * @return Returns the userChartOfAccountsCode
	 * 
	 */
	public String getUserChartOfAccountsCode() { 
		return userChartOfAccountsCode;
	}

	/**
	 * Sets the userChartOfAccountsCode attribute.
	 * 
	 * @param userChartOfAccountsCode The userChartOfAccountsCode to set.
	 * 
	 */
	public void setUserChartOfAccountsCode(String userChartOfAccountsCode) {
		this.userChartOfAccountsCode = userChartOfAccountsCode;
	}


	/**
	 * Gets the userOrganizationCode attribute.
	 * 
	 * @return Returns the userOrganizationCode
	 * 
	 */
	public String getUserOrganizationCode() { 
		return userOrganizationCode;
	}

	/**
	 * Sets the userOrganizationCode attribute.
	 * 
	 * @param userOrganizationCode The userOrganizationCode to set.
	 * 
	 */
	public void setUserOrganizationCode(String userOrganizationCode) {
		this.userOrganizationCode = userOrganizationCode;
	}


	/**
	 * Gets the securityChartOfAccountsCode attribute.
	 * 
	 * @return Returns the securityChartOfAccountsCode
	 * 
	 */
	public String getSecurityChartOfAccountsCode() { 
		return securityChartOfAccountsCode;
	}

	/**
	 * Sets the securityChartOfAccountsCode attribute.
	 * 
	 * @param securityChartOfAccountsCode The securityChartOfAccountsCode to set.
	 * 
	 */
	public void setSecurityChartOfAccountsCode(String securityChartOfAccountsCode) {
		this.securityChartOfAccountsCode = securityChartOfAccountsCode;
	}


	/**
	 * Gets the securityOrganizationCode attribute.
	 * 
	 * @return Returns the securityOrganizationCode
	 * 
	 */
	public String getSecurityOrganizationCode() { 
		return securityOrganizationCode;
	}

	/**
	 * Sets the securityOrganizationCode attribute.
	 * 
	 * @param securityOrganizationCode The securityOrganizationCode to set.
	 * 
	 */
	public void setSecurityOrganizationCode(String securityOrganizationCode) {
		this.securityOrganizationCode = securityOrganizationCode;
	}


	/**
	 * Gets the reportChartOfAccountsCode attribute.
	 * 
	 * @return Returns the reportChartOfAccountsCode
	 * 
	 */
	public String getReportChartOfAccountsCode() { 
		return reportChartOfAccountsCode;
	}

	/**
	 * Sets the reportChartOfAccountsCode attribute.
	 * 
	 * @param reportChartOfAccountsCode The reportChartOfAccountsCode to set.
	 * 
	 */
	public void setReportChartOfAccountsCode(String reportChartOfAccountsCode) {
		this.reportChartOfAccountsCode = reportChartOfAccountsCode;
	}


	/**
	 * Gets the reportOrganizationCode attribute.
	 * 
	 * @return Returns the reportOrganizationCode
	 * 
	 */
	public String getReportOrganizationCode() { 
		return reportOrganizationCode;
	}

	/**
	 * Sets the reportOrganizationCode attribute.
	 * 
	 * @param reportOrganizationCode The reportOrganizationCode to set.
	 * 
	 */
	public void setReportOrganizationCode(String reportOrganizationCode) {
		this.reportOrganizationCode = reportOrganizationCode;
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
	 * Gets the userChartOfAccounts attribute.
	 * 
	 * @return Returns the userChartOfAccounts
	 * 
	 */
	public Chart getUserChartOfAccounts() { 
		return userChartOfAccounts;
	}

	/**
	 * Sets the userChartOfAccounts attribute.
	 * 
	 * @param userChartOfAccounts The userChartOfAccounts to set.
	 * @deprecated
	 */
	public void setUserChartOfAccounts(Chart userChartOfAccounts) {
		this.userChartOfAccounts = userChartOfAccounts;
	}

	/**
	 * Gets the userOrganization attribute.
	 * 
	 * @return Returns the userOrganization
	 * 
	 */
	public Org getUserOrganization() { 
		return userOrganization;
	}

	/**
	 * Sets the userOrganization attribute.
	 * 
	 * @param userOrganization The userOrganization to set.
	 * @deprecated
	 */
	public void setUserOrganization(Org userOrganization) {
		this.userOrganization = userOrganization;
	}

	/**
	 * Gets the securityChartOfAccounts attribute.
	 * 
	 * @return Returns the securityChartOfAccounts
	 * 
	 */
	public Chart getSecurityChartOfAccounts() { 
		return securityChartOfAccounts;
	}

	/**
	 * Sets the securityChartOfAccounts attribute.
	 * 
	 * @param securityChartOfAccounts The securityChartOfAccounts to set.
	 * @deprecated
	 */
	public void setSecurityChartOfAccounts(Chart securityChartOfAccounts) {
		this.securityChartOfAccounts = securityChartOfAccounts;
	}

	/**
	 * Gets the securityOrganization attribute.
	 * 
	 * @return Returns the securityOrganization
	 * 
	 */
	public Org getSecurityOrganization() { 
		return securityOrganization;
	}

	/**
	 * Sets the securityOrganization attribute.
	 * 
	 * @param securityOrganization The securityOrganization to set.
	 * @deprecated
	 */
	public void setSecurityOrganization(Org securityOrganization) {
		this.securityOrganization = securityOrganization;
	}

	/**
	 * Gets the reportOrganization attribute.
	 * 
	 * @return Returns the reportOrganization
	 * 
	 */
	public Org getReportOrganization() { 
		return reportOrganization;
	}

	/**
	 * Sets the reportOrganization attribute.
	 * 
	 * @param reportOrganization The reportOrganization to set.
	 * @deprecated
	 */
	public void setReportOrganization(Org reportOrganization) {
		this.reportOrganization = reportOrganization;
	}

	/**
	 * Gets the reportChartOfAccounts attribute.
	 * 
	 * @return Returns the reportChartOfAccounts
	 * 
	 */
	public Chart getReportChartOfAccounts() { 
		return reportChartOfAccounts;
	}

	/**
	 * Sets the reportChartOfAccounts attribute.
	 * 
	 * @param reportChartOfAccounts The reportChartOfAccounts to set.
	 * @deprecated
	 */
	public void setReportChartOfAccounts(Chart reportChartOfAccounts) {
		this.reportChartOfAccounts = reportChartOfAccounts;
	}

	/**
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
	    LinkedHashMap m = new LinkedHashMap();	    
        m.put("chartOfAccountsCode", this.chartOfAccountsCode);
        m.put("organizationCode", this.organizationCode);
	    return m;
    }
}
