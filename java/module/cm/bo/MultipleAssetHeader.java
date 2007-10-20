package org.kuali.module.cams.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.DocumentHeader;
import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.Chart;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class MultipleAssetHeader extends PersistableBusinessObjectBase {

	private String documentNumber;
	private Long startingCapitalAssetNumber;
	private Integer newCapitalAssetCreateCount;
	private String representativeUniversalIdentifier;
	private String organizationOwnerChartOfAccountsCode;
	private String organizationOwnerAccountNumber;
	private String agencyNumber;
	private Integer nextCapitalAssetPaymentLineNumber;

    private DocumentHeader documentHeader;
	private Chart organizationOwnerChartOfAccounts;
	private Account organizationOwnerAccount;

	/**
	 * Default constructor.
	 */
	public MultipleAssetHeader() {

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
	 * Gets the startingCapitalAssetNumber attribute.
	 * 
	 * @return Returns the startingCapitalAssetNumber
	 * 
	 */
	public Long getStartingCapitalAssetNumber() { 
		return startingCapitalAssetNumber;
	}

	/**
	 * Sets the startingCapitalAssetNumber attribute.
	 * 
	 * @param startingCapitalAssetNumber The startingCapitalAssetNumber to set.
	 * 
	 */
	public void setStartingCapitalAssetNumber(Long startingCapitalAssetNumber) {
		this.startingCapitalAssetNumber = startingCapitalAssetNumber;
	}


	/**
	 * Gets the newCapitalAssetCreateCount attribute.
	 * 
	 * @return Returns the newCapitalAssetCreateCount
	 * 
	 */
	public Integer getNewCapitalAssetCreateCount() { 
		return newCapitalAssetCreateCount;
	}

	/**
	 * Sets the newCapitalAssetCreateCount attribute.
	 * 
	 * @param newCapitalAssetCreateCount The newCapitalAssetCreateCount to set.
	 * 
	 */
	public void setNewCapitalAssetCreateCount(Integer newCapitalAssetCreateCount) {
		this.newCapitalAssetCreateCount = newCapitalAssetCreateCount;
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
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
	    LinkedHashMap m = new LinkedHashMap();	    
        m.put("documentNumber", this.documentNumber);
	    return m;
    }
}
