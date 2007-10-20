package org.kuali.module.cams.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.DocumentHeader;
import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.module.chart.bo.Chart;
import org.kuali.module.chart.bo.Org;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class SecurityDocument extends PersistableBusinessObjectBase {

	private String documentNumber;
	private String chartOfAccountsCode;
	private String organizationCode;
	private String securityActionCode;
	private String securityTypeCode;
	private String securityChartOfAccountsCode;
	private String securityOrganizationCode;

    private DocumentHeader documentHeader;
	private Org organization;
	private Chart chartOfAccounts;
	private Org securityOrganization;
	private Chart securityChartOfAccounts;

	/**
	 * Default constructor.
	 */
	public SecurityDocument() {

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
	 * Gets the securityActionCode attribute.
	 * 
	 * @return Returns the securityActionCode
	 * 
	 */
	public String getSecurityActionCode() { 
		return securityActionCode;
	}

	/**
	 * Sets the securityActionCode attribute.
	 * 
	 * @param securityActionCode The securityActionCode to set.
	 * 
	 */
	public void setSecurityActionCode(String securityActionCode) {
		this.securityActionCode = securityActionCode;
	}


	/**
	 * Gets the securityTypeCode attribute.
	 * 
	 * @return Returns the securityTypeCode
	 * 
	 */
	public String getSecurityTypeCode() { 
		return securityTypeCode;
	}

	/**
	 * Sets the securityTypeCode attribute.
	 * 
	 * @param securityTypeCode The securityTypeCode to set.
	 * 
	 */
	public void setSecurityTypeCode(String securityTypeCode) {
		this.securityTypeCode = securityTypeCode;
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
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
	    LinkedHashMap m = new LinkedHashMap();	    
        m.put("documentNumber", this.documentNumber);
	    return m;
    }
}
