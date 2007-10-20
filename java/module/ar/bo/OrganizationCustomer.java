package org.kuali.module.ar.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kfs.bo.State;
import org.kuali.module.chart.bo.Chart;
import org.kuali.module.chart.bo.Org;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class OrganizationCustomer extends PersistableBusinessObjectBase {

	private String chartOfAccountsCode;
	private String organizationCode;
	private String customerNumber;
	private String customerName;
	private String customerLocationAddressName;
	private String customerLocationLine1Address;
	private String customerLocationLine2Address;
	private String customerLocationCityName;
	private String customerLocationStateCode;
	private String customerLocationZipCode;
	private String customerBillingAddressName;
	private String customerBillingLine1Address;
	private String customerBillingLine2Address;
	private String customerBillingCityName;
	private String customerBillingStateCode;
	private String customerBillingZipCode;
	private String customerPreviousAddressName;
	private String customerPreviousLine1Address;
	private String customerPreviousLine2Address;
	private String customerPreviousCityName;
	private String customerPreviousStateCode;
	private String customerPreviousZipCode;
	private Date customerAddressChangeDate;
	private String customerFederalEmployerOrSsnIdentifier;
	private String customerActiveCode;
	private Date customerLastActivityDate;
	private String customerPhoneNumber;
	private String customer800PhoneNumber;
	private String customerContactName;
	private String customerContactPhoneNumber;
	private String customerFaxNumber;
	private Date customerBirthDate;
	private String customerStopBillingCode;
	private boolean customerTaxExemptIndicator;
	private String customerCollectionStatusCode;
	private String collectorChartOfAccountCode;
	private String collectorOrganizationCode;
	private String organizationAssignmentCollectorIdentifier;
	private Date customerCollectionReviewDate;
	private String customerBankruptcyOrReorgCode;
	private Date customerBankruptcyReorgDate;
	private Date customerLastBillingStatementDate;
	private String customerBankNumber;
	private String customerCheckNumber;
	private String customerBankAccountTypeCode;
	private KualiDecimal customerCreditLimitAmount;
	private String customerCreditApprovedByName;

    private Org organization;
	private Chart chartOfAccounts;
	private StopBilling customerStopBilling;
	private CollectionStatus customerCollectionStatus;
	private Org collectorOrganization;
	private Chart collectorChartOfAccount;
	private Collector organizationAssignmentCollector;
	private BankruptcyOrReorg customerBankruptcyOrReorg;
	private BankAccountType customerBankAccountType;
    private State customerLocationState;
    private State customerBillingState;
    private State customerPreviousState;
    
	/**
	 * Default constructor.
	 */
	public OrganizationCustomer() {

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
	 * Gets the customerNumber attribute.
	 * 
	 * @return Returns the customerNumber
	 * 
	 */
	public String getCustomerNumber() { 
		return customerNumber;
	}

	/**
	 * Sets the customerNumber attribute.
	 * 
	 * @param customerNumber The customerNumber to set.
	 * 
	 */
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}


	/**
	 * Gets the customerName attribute.
	 * 
	 * @return Returns the customerName
	 * 
	 */
	public String getCustomerName() { 
		return customerName;
	}

	/**
	 * Sets the customerName attribute.
	 * 
	 * @param customerName The customerName to set.
	 * 
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	/**
	 * Gets the customerLocationAddressName attribute.
	 * 
	 * @return Returns the customerLocationAddressName
	 * 
	 */
	public String getCustomerLocationAddressName() { 
		return customerLocationAddressName;
	}

	/**
	 * Sets the customerLocationAddressName attribute.
	 * 
	 * @param customerLocationAddressName The customerLocationAddressName to set.
	 * 
	 */
	public void setCustomerLocationAddressName(String customerLocationAddressName) {
		this.customerLocationAddressName = customerLocationAddressName;
	}


	/**
	 * Gets the customerLocationLine1Address attribute.
	 * 
	 * @return Returns the customerLocationLine1Address
	 * 
	 */
	public String getCustomerLocationLine1Address() { 
		return customerLocationLine1Address;
	}

	/**
	 * Sets the customerLocationLine1Address attribute.
	 * 
	 * @param customerLocationLine1Address The customerLocationLine1Address to set.
	 * 
	 */
	public void setCustomerLocationLine1Address(String customerLocationLine1Address) {
		this.customerLocationLine1Address = customerLocationLine1Address;
	}


	/**
	 * Gets the customerLocationLine2Address attribute.
	 * 
	 * @return Returns the customerLocationLine2Address
	 * 
	 */
	public String getCustomerLocationLine2Address() { 
		return customerLocationLine2Address;
	}

	/**
	 * Sets the customerLocationLine2Address attribute.
	 * 
	 * @param customerLocationLine2Address The customerLocationLine2Address to set.
	 * 
	 */
	public void setCustomerLocationLine2Address(String customerLocationLine2Address) {
		this.customerLocationLine2Address = customerLocationLine2Address;
	}


	/**
	 * Gets the customerLocationCityName attribute.
	 * 
	 * @return Returns the customerLocationCityName
	 * 
	 */
	public String getCustomerLocationCityName() { 
		return customerLocationCityName;
	}

	/**
	 * Sets the customerLocationCityName attribute.
	 * 
	 * @param customerLocationCityName The customerLocationCityName to set.
	 * 
	 */
	public void setCustomerLocationCityName(String customerLocationCityName) {
		this.customerLocationCityName = customerLocationCityName;
	}


	/**
	 * Gets the customerLocationStateCode attribute.
	 * 
	 * @return Returns the customerLocationStateCode
	 * 
	 */
	public String getCustomerLocationStateCode() { 
		return customerLocationStateCode;
	}

	/**
	 * Sets the customerLocationStateCode attribute.
	 * 
	 * @param customerLocationStateCode The customerLocationStateCode to set.
	 * 
	 */
	public void setCustomerLocationStateCode(String customerLocationStateCode) {
		this.customerLocationStateCode = customerLocationStateCode;
	}


	/**
	 * Gets the customerLocationZipCode attribute.
	 * 
	 * @return Returns the customerLocationZipCode
	 * 
	 */
	public String getCustomerLocationZipCode() { 
		return customerLocationZipCode;
	}

	/**
	 * Sets the customerLocationZipCode attribute.
	 * 
	 * @param customerLocationZipCode The customerLocationZipCode to set.
	 * 
	 */
	public void setCustomerLocationZipCode(String customerLocationZipCode) {
		this.customerLocationZipCode = customerLocationZipCode;
	}


	/**
	 * Gets the customerBillingAddressName attribute.
	 * 
	 * @return Returns the customerBillingAddressName
	 * 
	 */
	public String getCustomerBillingAddressName() { 
		return customerBillingAddressName;
	}

	/**
	 * Sets the customerBillingAddressName attribute.
	 * 
	 * @param customerBillingAddressName The customerBillingAddressName to set.
	 * 
	 */
	public void setCustomerBillingAddressName(String customerBillingAddressName) {
		this.customerBillingAddressName = customerBillingAddressName;
	}


	/**
	 * Gets the customerBillingLine1Address attribute.
	 * 
	 * @return Returns the customerBillingLine1Address
	 * 
	 */
	public String getCustomerBillingLine1Address() { 
		return customerBillingLine1Address;
	}

	/**
	 * Sets the customerBillingLine1Address attribute.
	 * 
	 * @param customerBillingLine1Address The customerBillingLine1Address to set.
	 * 
	 */
	public void setCustomerBillingLine1Address(String customerBillingLine1Address) {
		this.customerBillingLine1Address = customerBillingLine1Address;
	}


	/**
	 * Gets the customerBillingLine2Address attribute.
	 * 
	 * @return Returns the customerBillingLine2Address
	 * 
	 */
	public String getCustomerBillingLine2Address() { 
		return customerBillingLine2Address;
	}

	/**
	 * Sets the customerBillingLine2Address attribute.
	 * 
	 * @param customerBillingLine2Address The customerBillingLine2Address to set.
	 * 
	 */
	public void setCustomerBillingLine2Address(String customerBillingLine2Address) {
		this.customerBillingLine2Address = customerBillingLine2Address;
	}


	/**
	 * Gets the customerBillingCityName attribute.
	 * 
	 * @return Returns the customerBillingCityName
	 * 
	 */
	public String getCustomerBillingCityName() { 
		return customerBillingCityName;
	}

	/**
	 * Sets the customerBillingCityName attribute.
	 * 
	 * @param customerBillingCityName The customerBillingCityName to set.
	 * 
	 */
	public void setCustomerBillingCityName(String customerBillingCityName) {
		this.customerBillingCityName = customerBillingCityName;
	}


	/**
	 * Gets the customerBillingStateCode attribute.
	 * 
	 * @return Returns the customerBillingStateCode
	 * 
	 */
	public String getCustomerBillingStateCode() { 
		return customerBillingStateCode;
	}

	/**
	 * Sets the customerBillingStateCode attribute.
	 * 
	 * @param customerBillingStateCode The customerBillingStateCode to set.
	 * 
	 */
	public void setCustomerBillingStateCode(String customerBillingStateCode) {
		this.customerBillingStateCode = customerBillingStateCode;
	}


	/**
	 * Gets the customerBillingZipCode attribute.
	 * 
	 * @return Returns the customerBillingZipCode
	 * 
	 */
	public String getCustomerBillingZipCode() { 
		return customerBillingZipCode;
	}

	/**
	 * Sets the customerBillingZipCode attribute.
	 * 
	 * @param customerBillingZipCode The customerBillingZipCode to set.
	 * 
	 */
	public void setCustomerBillingZipCode(String customerBillingZipCode) {
		this.customerBillingZipCode = customerBillingZipCode;
	}


	/**
	 * Gets the customerPreviousAddressName attribute.
	 * 
	 * @return Returns the customerPreviousAddressName
	 * 
	 */
	public String getCustomerPreviousAddressName() { 
		return customerPreviousAddressName;
	}

	/**
	 * Sets the customerPreviousAddressName attribute.
	 * 
	 * @param customerPreviousAddressName The customerPreviousAddressName to set.
	 * 
	 */
	public void setCustomerPreviousAddressName(String customerPreviousAddressName) {
		this.customerPreviousAddressName = customerPreviousAddressName;
	}


	/**
	 * Gets the customerPreviousLine1Address attribute.
	 * 
	 * @return Returns the customerPreviousLine1Address
	 * 
	 */
	public String getCustomerPreviousLine1Address() { 
		return customerPreviousLine1Address;
	}

	/**
	 * Sets the customerPreviousLine1Address attribute.
	 * 
	 * @param customerPreviousLine1Address The customerPreviousLine1Address to set.
	 * 
	 */
	public void setCustomerPreviousLine1Address(String customerPreviousLine1Address) {
		this.customerPreviousLine1Address = customerPreviousLine1Address;
	}


	/**
	 * Gets the customerPreviousLine2Address attribute.
	 * 
	 * @return Returns the customerPreviousLine2Address
	 * 
	 */
	public String getCustomerPreviousLine2Address() { 
		return customerPreviousLine2Address;
	}

	/**
	 * Sets the customerPreviousLine2Address attribute.
	 * 
	 * @param customerPreviousLine2Address The customerPreviousLine2Address to set.
	 * 
	 */
	public void setCustomerPreviousLine2Address(String customerPreviousLine2Address) {
		this.customerPreviousLine2Address = customerPreviousLine2Address;
	}


	/**
	 * Gets the customerPreviousCityName attribute.
	 * 
	 * @return Returns the customerPreviousCityName
	 * 
	 */
	public String getCustomerPreviousCityName() { 
		return customerPreviousCityName;
	}

	/**
	 * Sets the customerPreviousCityName attribute.
	 * 
	 * @param customerPreviousCityName The customerPreviousCityName to set.
	 * 
	 */
	public void setCustomerPreviousCityName(String customerPreviousCityName) {
		this.customerPreviousCityName = customerPreviousCityName;
	}


	/**
	 * Gets the customerPreviousStateCode attribute.
	 * 
	 * @return Returns the customerPreviousStateCode
	 * 
	 */
	public String getCustomerPreviousStateCode() { 
		return customerPreviousStateCode;
	}

	/**
	 * Sets the customerPreviousStateCode attribute.
	 * 
	 * @param customerPreviousStateCode The customerPreviousStateCode to set.
	 * 
	 */
	public void setCustomerPreviousStateCode(String customerPreviousStateCode) {
		this.customerPreviousStateCode = customerPreviousStateCode;
	}


	/**
	 * Gets the customerPreviousZipCode attribute.
	 * 
	 * @return Returns the customerPreviousZipCode
	 * 
	 */
	public String getCustomerPreviousZipCode() { 
		return customerPreviousZipCode;
	}

	/**
	 * Sets the customerPreviousZipCode attribute.
	 * 
	 * @param customerPreviousZipCode The customerPreviousZipCode to set.
	 * 
	 */
	public void setCustomerPreviousZipCode(String customerPreviousZipCode) {
		this.customerPreviousZipCode = customerPreviousZipCode;
	}


	/**
	 * Gets the customerAddressChangeDate attribute.
	 * 
	 * @return Returns the customerAddressChangeDate
	 * 
	 */
	public Date getCustomerAddressChangeDate() { 
		return customerAddressChangeDate;
	}

	/**
	 * Sets the customerAddressChangeDate attribute.
	 * 
	 * @param customerAddressChangeDate The customerAddressChangeDate to set.
	 * 
	 */
	public void setCustomerAddressChangeDate(Date customerAddressChangeDate) {
		this.customerAddressChangeDate = customerAddressChangeDate;
	}


	/**
	 * Gets the customerFederalEmployerOrSsnIdentifier attribute.
	 * 
	 * @return Returns the customerFederalEmployerOrSsnIdentifier
	 * 
	 */
	public String getCustomerFederalEmployerOrSsnIdentifier() { 
		return customerFederalEmployerOrSsnIdentifier;
	}

	/**
	 * Sets the customerFederalEmployerOrSsnIdentifier attribute.
	 * 
	 * @param customerFederalEmployerOrSsnIdentifier The customerFederalEmployerOrSsnIdentifier to set.
	 * 
	 */
	public void setCustomerFederalEmployerOrSsnIdentifier(String customerFederalEmployerOrSsnIdentifier) {
		this.customerFederalEmployerOrSsnIdentifier = customerFederalEmployerOrSsnIdentifier;
	}


	/**
	 * Gets the customerActiveCode attribute.
	 * 
	 * @return Returns the customerActiveCode
	 * 
	 */
	public String getCustomerActiveCode() { 
		return customerActiveCode;
	}

	/**
	 * Sets the customerActiveCode attribute.
	 * 
	 * @param customerActiveCode The customerActiveCode to set.
	 * 
	 */
	public void setCustomerActiveCode(String customerActiveCode) {
		this.customerActiveCode = customerActiveCode;
	}


	/**
	 * Gets the customerLastActivityDate attribute.
	 * 
	 * @return Returns the customerLastActivityDate
	 * 
	 */
	public Date getCustomerLastActivityDate() { 
		return customerLastActivityDate;
	}

	/**
	 * Sets the customerLastActivityDate attribute.
	 * 
	 * @param customerLastActivityDate The customerLastActivityDate to set.
	 * 
	 */
	public void setCustomerLastActivityDate(Date customerLastActivityDate) {
		this.customerLastActivityDate = customerLastActivityDate;
	}


	/**
	 * Gets the customerPhoneNumber attribute.
	 * 
	 * @return Returns the customerPhoneNumber
	 * 
	 */
	public String getCustomerPhoneNumber() { 
		return customerPhoneNumber;
	}

	/**
	 * Sets the customerPhoneNumber attribute.
	 * 
	 * @param customerPhoneNumber The customerPhoneNumber to set.
	 * 
	 */
	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}


	/**
	 * Gets the customer800PhoneNumber attribute.
	 * 
	 * @return Returns the customer800PhoneNumber
	 * 
	 */
	public String getCustomer800PhoneNumber() { 
		return customer800PhoneNumber;
	}

	/**
	 * Sets the customer800PhoneNumber attribute.
	 * 
	 * @param customer800PhoneNumber The customer800PhoneNumber to set.
	 * 
	 */
	public void setCustomer800PhoneNumber(String customer800PhoneNumber) {
		this.customer800PhoneNumber = customer800PhoneNumber;
	}


	/**
	 * Gets the customerContactName attribute.
	 * 
	 * @return Returns the customerContactName
	 * 
	 */
	public String getCustomerContactName() { 
		return customerContactName;
	}

	/**
	 * Sets the customerContactName attribute.
	 * 
	 * @param customerContactName The customerContactName to set.
	 * 
	 */
	public void setCustomerContactName(String customerContactName) {
		this.customerContactName = customerContactName;
	}


	/**
	 * Gets the customerContactPhoneNumber attribute.
	 * 
	 * @return Returns the customerContactPhoneNumber
	 * 
	 */
	public String getCustomerContactPhoneNumber() { 
		return customerContactPhoneNumber;
	}

	/**
	 * Sets the customerContactPhoneNumber attribute.
	 * 
	 * @param customerContactPhoneNumber The customerContactPhoneNumber to set.
	 * 
	 */
	public void setCustomerContactPhoneNumber(String customerContactPhoneNumber) {
		this.customerContactPhoneNumber = customerContactPhoneNumber;
	}


	/**
	 * Gets the customerFaxNumber attribute.
	 * 
	 * @return Returns the customerFaxNumber
	 * 
	 */
	public String getCustomerFaxNumber() { 
		return customerFaxNumber;
	}

	/**
	 * Sets the customerFaxNumber attribute.
	 * 
	 * @param customerFaxNumber The customerFaxNumber to set.
	 * 
	 */
	public void setCustomerFaxNumber(String customerFaxNumber) {
		this.customerFaxNumber = customerFaxNumber;
	}


	/**
	 * Gets the customerBirthDate attribute.
	 * 
	 * @return Returns the customerBirthDate
	 * 
	 */
	public Date getCustomerBirthDate() { 
		return customerBirthDate;
	}

	/**
	 * Sets the customerBirthDate attribute.
	 * 
	 * @param customerBirthDate The customerBirthDate to set.
	 * 
	 */
	public void setCustomerBirthDate(Date customerBirthDate) {
		this.customerBirthDate = customerBirthDate;
	}


	/**
	 * Gets the customerStopBillingCode attribute.
	 * 
	 * @return Returns the customerStopBillingCode
	 * 
	 */
	public String getCustomerStopBillingCode() { 
		return customerStopBillingCode;
	}

	/**
	 * Sets the customerStopBillingCode attribute.
	 * 
	 * @param customerStopBillingCode The customerStopBillingCode to set.
	 * 
	 */
	public void setCustomerStopBillingCode(String customerStopBillingCode) {
		this.customerStopBillingCode = customerStopBillingCode;
	}


	/**
	 * Gets the customerTaxExemptIndicator attribute.
	 * 
	 * @return Returns the customerTaxExemptIndicator
	 * 
	 */
	public boolean isCustomerTaxExemptIndicator() { 
		return customerTaxExemptIndicator;
	}

	/**
	 * Sets the customerTaxExemptIndicator attribute.
	 * 
	 * @param customerTaxExemptIndicator The customerTaxExemptIndicator to set.
	 * 
	 */
	public void setCustomerTaxExemptIndicator(boolean customerTaxExemptIndicator) {
		this.customerTaxExemptIndicator = customerTaxExemptIndicator;
	}


	/**
	 * Gets the customerCollectionStatusCode attribute.
	 * 
	 * @return Returns the customerCollectionStatusCode
	 * 
	 */
	public String getCustomerCollectionStatusCode() { 
		return customerCollectionStatusCode;
	}

	/**
	 * Sets the customerCollectionStatusCode attribute.
	 * 
	 * @param customerCollectionStatusCode The customerCollectionStatusCode to set.
	 * 
	 */
	public void setCustomerCollectionStatusCode(String customerCollectionStatusCode) {
		this.customerCollectionStatusCode = customerCollectionStatusCode;
	}


	/**
	 * Gets the collectorChartOfAccountCode attribute.
	 * 
	 * @return Returns the collectorChartOfAccountCode
	 * 
	 */
	public String getCollectorChartOfAccountCode() { 
		return collectorChartOfAccountCode;
	}

	/**
	 * Sets the collectorChartOfAccountCode attribute.
	 * 
	 * @param collectorChartOfAccountCode The collectorChartOfAccountCode to set.
	 * 
	 */
	public void setCollectorChartOfAccountCode(String collectorChartOfAccountCode) {
		this.collectorChartOfAccountCode = collectorChartOfAccountCode;
	}


	/**
	 * Gets the collectorOrganizationCode attribute.
	 * 
	 * @return Returns the collectorOrganizationCode
	 * 
	 */
	public String getCollectorOrganizationCode() { 
		return collectorOrganizationCode;
	}

	/**
	 * Sets the collectorOrganizationCode attribute.
	 * 
	 * @param collectorOrganizationCode The collectorOrganizationCode to set.
	 * 
	 */
	public void setCollectorOrganizationCode(String collectorOrganizationCode) {
		this.collectorOrganizationCode = collectorOrganizationCode;
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
	 * Gets the customerCollectionReviewDate attribute.
	 * 
	 * @return Returns the customerCollectionReviewDate
	 * 
	 */
	public Date getCustomerCollectionReviewDate() { 
		return customerCollectionReviewDate;
	}

	/**
	 * Sets the customerCollectionReviewDate attribute.
	 * 
	 * @param customerCollectionReviewDate The customerCollectionReviewDate to set.
	 * 
	 */
	public void setCustomerCollectionReviewDate(Date customerCollectionReviewDate) {
		this.customerCollectionReviewDate = customerCollectionReviewDate;
	}


	/**
	 * Gets the customerBankruptcyOrReorgCode attribute.
	 * 
	 * @return Returns the customerBankruptcyOrReorgCode
	 * 
	 */
	public String getCustomerBankruptcyOrReorgCode() { 
		return customerBankruptcyOrReorgCode;
	}

	/**
	 * Sets the customerBankruptcyOrReorgCode attribute.
	 * 
	 * @param customerBankruptcyOrReorgCode The customerBankruptcyOrReorgCode to set.
	 * 
	 */
	public void setCustomerBankruptcyOrReorgCode(String customerBankruptcyOrReorgCode) {
		this.customerBankruptcyOrReorgCode = customerBankruptcyOrReorgCode;
	}


	/**
	 * Gets the customerBankruptcyReorgDate attribute.
	 * 
	 * @return Returns the customerBankruptcyReorgDate
	 * 
	 */
	public Date getCustomerBankruptcyReorgDate() { 
		return customerBankruptcyReorgDate;
	}

	/**
	 * Sets the customerBankruptcyReorgDate attribute.
	 * 
	 * @param customerBankruptcyReorgDate The customerBankruptcyReorgDate to set.
	 * 
	 */
	public void setCustomerBankruptcyReorgDate(Date customerBankruptcyReorgDate) {
		this.customerBankruptcyReorgDate = customerBankruptcyReorgDate;
	}


	/**
	 * Gets the customerLastBillingStatementDate attribute.
	 * 
	 * @return Returns the customerLastBillingStatementDate
	 * 
	 */
	public Date getCustomerLastBillingStatementDate() { 
		return customerLastBillingStatementDate;
	}

	/**
	 * Sets the customerLastBillingStatementDate attribute.
	 * 
	 * @param customerLastBillingStatementDate The customerLastBillingStatementDate to set.
	 * 
	 */
	public void setCustomerLastBillingStatementDate(Date customerLastBillingStatementDate) {
		this.customerLastBillingStatementDate = customerLastBillingStatementDate;
	}


	/**
	 * Gets the customerBankNumber attribute.
	 * 
	 * @return Returns the customerBankNumber
	 * 
	 */
	public String getCustomerBankNumber() { 
		return customerBankNumber;
	}

	/**
	 * Sets the customerBankNumber attribute.
	 * 
	 * @param customerBankNumber The customerBankNumber to set.
	 * 
	 */
	public void setCustomerBankNumber(String customerBankNumber) {
		this.customerBankNumber = customerBankNumber;
	}


	/**
	 * Gets the customerCheckNumber attribute.
	 * 
	 * @return Returns the customerCheckNumber
	 * 
	 */
	public String getCustomerCheckNumber() { 
		return customerCheckNumber;
	}

	/**
	 * Sets the customerCheckNumber attribute.
	 * 
	 * @param customerCheckNumber The customerCheckNumber to set.
	 * 
	 */
	public void setCustomerCheckNumber(String customerCheckNumber) {
		this.customerCheckNumber = customerCheckNumber;
	}


	/**
	 * Gets the customerBankAccountTypeCode attribute.
	 * 
	 * @return Returns the customerBankAccountTypeCode
	 * 
	 */
	public String getCustomerBankAccountTypeCode() { 
		return customerBankAccountTypeCode;
	}

	/**
	 * Sets the customerBankAccountTypeCode attribute.
	 * 
	 * @param customerBankAccountTypeCode The customerBankAccountTypeCode to set.
	 * 
	 */
	public void setCustomerBankAccountTypeCode(String customerBankAccountTypeCode) {
		this.customerBankAccountTypeCode = customerBankAccountTypeCode;
	}


	/**
	 * Gets the customerCreditLimitAmount attribute.
	 * 
	 * @return Returns the customerCreditLimitAmount
	 * 
	 */
	public KualiDecimal getCustomerCreditLimitAmount() { 
		return customerCreditLimitAmount;
	}

	/**
	 * Sets the customerCreditLimitAmount attribute.
	 * 
	 * @param customerCreditLimitAmount The customerCreditLimitAmount to set.
	 * 
	 */
	public void setCustomerCreditLimitAmount(KualiDecimal customerCreditLimitAmount) {
		this.customerCreditLimitAmount = customerCreditLimitAmount;
	}


	/**
	 * Gets the customerCreditApprovedByName attribute.
	 * 
	 * @return Returns the customerCreditApprovedByName
	 * 
	 */
	public String getCustomerCreditApprovedByName() { 
		return customerCreditApprovedByName;
	}

	/**
	 * Sets the customerCreditApprovedByName attribute.
	 * 
	 * @param customerCreditApprovedByName The customerCreditApprovedByName to set.
	 * 
	 */
	public void setCustomerCreditApprovedByName(String customerCreditApprovedByName) {
		this.customerCreditApprovedByName = customerCreditApprovedByName;
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
	 * Gets the customerStopBilling attribute.
	 * 
	 * @return Returns the customerStopBilling
	 * 
	 */
	public StopBilling getCustomerStopBilling() { 
		return customerStopBilling;
	}

	/**
	 * Sets the customerStopBilling attribute.
	 * 
	 * @param customerStopBilling The customerStopBilling to set.
	 * @deprecated
	 */
	public void setCustomerStopBilling(StopBilling customerStopBilling) {
		this.customerStopBilling = customerStopBilling;
	}

	/**
	 * Gets the customerCollectionStatus attribute.
	 * 
	 * @return Returns the customerCollectionStatus
	 * 
	 */
	public CollectionStatus getCustomerCollectionStatus() { 
		return customerCollectionStatus;
	}

	/**
	 * Sets the customerCollectionStatus attribute.
	 * 
	 * @param customerCollectionStatus The customerCollectionStatus to set.
	 * @deprecated
	 */
	public void setCustomerCollectionStatus(CollectionStatus customerCollectionStatus) {
		this.customerCollectionStatus = customerCollectionStatus;
	}

	/**
	 * Gets the collectorOrganization attribute.
	 * 
	 * @return Returns the collectorOrganization
	 * 
	 */
	public Org getCollectorOrganization() { 
		return collectorOrganization;
	}

	/**
	 * Sets the collectorOrganization attribute.
	 * 
	 * @param collectorOrganization The collectorOrganization to set.
	 * @deprecated
	 */
	public void setCollectorOrganization(Org collectorOrganization) {
		this.collectorOrganization = collectorOrganization;
	}

	/**
	 * Gets the collectorChartOfAccount attribute.
	 * 
	 * @return Returns the collectorChartOfAccount
	 * 
	 */
	public Chart getCollectorChartOfAccount() { 
		return collectorChartOfAccount;
	}

	/**
	 * Sets the collectorChartOfAccount attribute.
	 * 
	 * @param collectorChartOfAccount The collectorChartOfAccount to set.
	 * @deprecated
	 */
	public void setCollectorChartOfAccount(Chart collectorChartOfAccount) {
		this.collectorChartOfAccount = collectorChartOfAccount;
	}

	/**
	 * Gets the organizationAssignmentCollector attribute.
	 * 
	 * @return Returns the organizationAssignmentCollector
	 * 
	 */
	public Collector getOrganizationAssignmentCollector() { 
		return organizationAssignmentCollector;
	}

	/**
	 * Sets the organizationAssignmentCollector attribute.
	 * 
	 * @param organizationAssignmentCollector The organizationAssignmentCollector to set.
	 * @deprecated
	 */
	public void setOrganizationAssignmentCollector(Collector organizationAssignmentCollector) {
		this.organizationAssignmentCollector = organizationAssignmentCollector;
	}

	/**
	 * Gets the customerBankruptcyOrReorg attribute.
	 * 
	 * @return Returns the customerBankruptcyOrReorg
	 * 
	 */
	public BankruptcyOrReorg getCustomerBankruptcyOrReorg() { 
		return customerBankruptcyOrReorg;
	}

	/**
	 * Sets the customerBankruptcyOrReorg attribute.
	 * 
	 * @param customerBankruptcyOrReorg The customerBankruptcyOrReorg to set.
	 * @deprecated
	 */
	public void setCustomerBankruptcyOrReorg(BankruptcyOrReorg customerBankruptcyOrReorg) {
		this.customerBankruptcyOrReorg = customerBankruptcyOrReorg;
	}

	/**
	 * Gets the customerBankAccountType attribute.
	 * 
	 * @return Returns the customerBankAccountType
	 * 
	 */
	public BankAccountType getCustomerBankAccountType() { 
		return customerBankAccountType;
	}

	/**
	 * Sets the customerBankAccountType attribute.
	 * 
	 * @param customerBankAccountType The customerBankAccountType to set.
	 * @deprecated
	 */
	public void setCustomerBankAccountType(BankAccountType customerBankAccountType) {
		this.customerBankAccountType = customerBankAccountType;
	}

    /**
     * Gets the customerBillingState attribute. 
     * @return Returns the customerBillingState.
     */
    public State getCustomerBillingState() {
        return customerBillingState;
    }

    /**
     * Sets the customerBillingState attribute value.
     * @param customerBillingState The customerBillingState to set.
     * @deprecated
     */
    public void setCustomerBillingState(State customerBillingState) {
        this.customerBillingState = customerBillingState;
    }

    /**
     * Gets the customerLocationState attribute. 
     * @return Returns the customerLocationState.
     */
    public State getCustomerLocationState() {
        return customerLocationState;
    }

    /**
     * Sets the customerLocationState attribute value.
     * @param customerLocationState The customerLocationState to set.
     * @deprecated
     */
    public void setCustomerLocationState(State customerLocationState) {
        this.customerLocationState = customerLocationState;
    }

    /**
     * Gets the customerPreviousState attribute. 
     * @return Returns the customerPreviousState.
     */
    public State getCustomerPreviousState() {
        return customerPreviousState;
    }

    /**
     * Sets the customerPreviousState attribute value.
     * @param customerPreviousState The customerPreviousState to set.
     * @deprecated
     */
    public void setCustomerPreviousState(State customerPreviousState) {
        this.customerPreviousState = customerPreviousState;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();      
        m.put("chartOfAccountsCode", this.chartOfAccountsCode);
        m.put("organizationCode", this.organizationCode);
        m.put("customerNumber", this.customerNumber);
        return m;
    }

}
