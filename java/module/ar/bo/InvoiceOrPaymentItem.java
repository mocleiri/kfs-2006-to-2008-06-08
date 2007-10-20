package org.kuali.module.ar.bo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.core.util.KualiDecimal;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.Chart;
import org.kuali.module.chart.bo.ObjectCode;
import org.kuali.module.chart.bo.Org;
import org.kuali.module.chart.bo.ProjectCode;
import org.kuali.module.chart.bo.SubAccount;
import org.kuali.module.chart.bo.SubObjCd;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class InvoiceOrPaymentItem extends PersistableBusinessObjectBase {

	private String documentNumber;
	private Integer invoiceItemNumber;
	private KualiDecimal itemPaidOrAppliedAmount;
	private KualiDecimal accountingItemPaidAmount;
	private Integer universityFiscalYear;
	private String chartOfAccountsCode;
	private String accountNumber;
	private String subAccountNumber;
	private String financialObjectCode;
	private String financialSubObjectCode;
	private String projectCode;
	private String organizationReferenceId;
	private String accountsReceivableObjectCode;
	private String accountsReceivableSubObjectCode;
	private BigDecimal invoiceItemQuantity;
	private String invoiceItemUnitOfMeasureCode;
	private KualiDecimal invoiceItemUnitPrice;
	private KualiDecimal invoiceItemTotalAmount;
	private Date invoiceItemServiceDate;
	private String invoiceItemCode;
	private String invoiceItemDescription;
	private String customerChartOfAccountCode;
	private String customerOrganizationCode;
	private String financialDocumentReferenceInvoiceNumber;

    private ObjectCode accountsReceivableObject;
	private SubObjCd financialSubObject;
	private ObjectCode financialObject;
	private SubObjCd accountsReceivableSubObject;
	private Chart chartOfAccounts;
	private SubAccount subAccount;
	private Account account;
	private ProjectCode project;
	private Org customerOrganization;
	private Chart customerChartOfAccount;

	/**
	 * Default constructor.
	 */
	public InvoiceOrPaymentItem() {

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
	 * Gets the invoiceItemNumber attribute.
	 * 
	 * @return Returns the invoiceItemNumber
	 * 
	 */
	public Integer getInvoiceItemNumber() { 
		return invoiceItemNumber;
	}

	/**
	 * Sets the invoiceItemNumber attribute.
	 * 
	 * @param invoiceItemNumber The invoiceItemNumber to set.
	 * 
	 */
	public void setInvoiceItemNumber(Integer invoiceItemNumber) {
		this.invoiceItemNumber = invoiceItemNumber;
	}


	/**
	 * Gets the itemPaidOrAppliedAmount attribute.
	 * 
	 * @return Returns the itemPaidOrAppliedAmount
	 * 
	 */
	public KualiDecimal getItemPaidOrAppliedAmount() { 
		return itemPaidOrAppliedAmount;
	}

	/**
	 * Sets the itemPaidOrAppliedAmount attribute.
	 * 
	 * @param itemPaidOrAppliedAmount The itemPaidOrAppliedAmount to set.
	 * 
	 */
	public void setItemPaidOrAppliedAmount(KualiDecimal itemPaidOrAppliedAmount) {
		this.itemPaidOrAppliedAmount = itemPaidOrAppliedAmount;
	}


	/**
	 * Gets the accountingItemPaidAmount attribute.
	 * 
	 * @return Returns the accountingItemPaidAmount
	 * 
	 */
	public KualiDecimal getAccountingItemPaidAmount() { 
		return accountingItemPaidAmount;
	}

	/**
	 * Sets the accountingItemPaidAmount attribute.
	 * 
	 * @param accountingItemPaidAmount The accountingItemPaidAmount to set.
	 * 
	 */
	public void setAccountingItemPaidAmount(KualiDecimal accountingItemPaidAmount) {
		this.accountingItemPaidAmount = accountingItemPaidAmount;
	}


	/**
	 * Gets the universityFiscalYear attribute.
	 * 
	 * @return Returns the universityFiscalYear
	 * 
	 */
	public Integer getUniversityFiscalYear() { 
		return universityFiscalYear;
	}

	/**
	 * Sets the universityFiscalYear attribute.
	 * 
	 * @param universityFiscalYear The universityFiscalYear to set.
	 * 
	 */
	public void setUniversityFiscalYear(Integer universityFiscalYear) {
		this.universityFiscalYear = universityFiscalYear;
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
	 * Gets the accountNumber attribute.
	 * 
	 * @return Returns the accountNumber
	 * 
	 */
	public String getAccountNumber() { 
		return accountNumber;
	}

	/**
	 * Sets the accountNumber attribute.
	 * 
	 * @param accountNumber The accountNumber to set.
	 * 
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	/**
	 * Gets the subAccountNumber attribute.
	 * 
	 * @return Returns the subAccountNumber
	 * 
	 */
	public String getSubAccountNumber() { 
		return subAccountNumber;
	}

	/**
	 * Sets the subAccountNumber attribute.
	 * 
	 * @param subAccountNumber The subAccountNumber to set.
	 * 
	 */
	public void setSubAccountNumber(String subAccountNumber) {
		this.subAccountNumber = subAccountNumber;
	}


	/**
	 * Gets the financialObjectCode attribute.
	 * 
	 * @return Returns the financialObjectCode
	 * 
	 */
	public String getFinancialObjectCode() { 
		return financialObjectCode;
	}

	/**
	 * Sets the financialObjectCode attribute.
	 * 
	 * @param financialObjectCode The financialObjectCode to set.
	 * 
	 */
	public void setFinancialObjectCode(String financialObjectCode) {
		this.financialObjectCode = financialObjectCode;
	}


	/**
	 * Gets the financialSubObjectCode attribute.
	 * 
	 * @return Returns the financialSubObjectCode
	 * 
	 */
	public String getFinancialSubObjectCode() { 
		return financialSubObjectCode;
	}

	/**
	 * Sets the financialSubObjectCode attribute.
	 * 
	 * @param financialSubObjectCode The financialSubObjectCode to set.
	 * 
	 */
	public void setFinancialSubObjectCode(String financialSubObjectCode) {
		this.financialSubObjectCode = financialSubObjectCode;
	}


	/**
	 * Gets the projectCode attribute.
	 * 
	 * @return Returns the projectCode
	 * 
	 */
	public String getProjectCode() { 
		return projectCode;
	}

	/**
	 * Sets the projectCode attribute.
	 * 
	 * @param projectCode The projectCode to set.
	 * 
	 */
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}


	/**
	 * Gets the organizationReferenceId attribute.
	 * 
	 * @return Returns the organizationReferenceId
	 * 
	 */
	public String getOrganizationReferenceId() { 
		return organizationReferenceId;
	}

	/**
	 * Sets the organizationReferenceId attribute.
	 * 
	 * @param organizationReferenceId The organizationReferenceId to set.
	 * 
	 */
	public void setOrganizationReferenceId(String organizationReferenceId) {
		this.organizationReferenceId = organizationReferenceId;
	}


	/**
	 * Gets the accountsReceivableObjectCode attribute.
	 * 
	 * @return Returns the accountsReceivableObjectCode
	 * 
	 */
	public String getAccountsReceivableObjectCode() { 
		return accountsReceivableObjectCode;
	}

	/**
	 * Sets the accountsReceivableObjectCode attribute.
	 * 
	 * @param accountsReceivableObjectCode The accountsReceivableObjectCode to set.
	 * 
	 */
	public void setAccountsReceivableObjectCode(String accountsReceivableObjectCode) {
		this.accountsReceivableObjectCode = accountsReceivableObjectCode;
	}


	/**
	 * Gets the accountsReceivableSubObjectCode attribute.
	 * 
	 * @return Returns the accountsReceivableSubObjectCode
	 * 
	 */
	public String getAccountsReceivableSubObjectCode() { 
		return accountsReceivableSubObjectCode;
	}

	/**
	 * Sets the accountsReceivableSubObjectCode attribute.
	 * 
	 * @param accountsReceivableSubObjectCode The accountsReceivableSubObjectCode to set.
	 * 
	 */
	public void setAccountsReceivableSubObjectCode(String accountsReceivableSubObjectCode) {
		this.accountsReceivableSubObjectCode = accountsReceivableSubObjectCode;
	}


	/**
	 * Gets the invoiceItemQuantity attribute.
	 * 
	 * @return Returns the invoiceItemQuantity
	 * 
	 */
	public BigDecimal getInvoiceItemQuantity() { 
		return invoiceItemQuantity;
	}

	/**
	 * Sets the invoiceItemQuantity attribute.
	 * 
	 * @param invoiceItemQuantity The invoiceItemQuantity to set.
	 * 
	 */
	public void setInvoiceItemQuantity(BigDecimal invoiceItemQuantity) {
		this.invoiceItemQuantity = invoiceItemQuantity;
	}


	/**
	 * Gets the invoiceItemUnitOfMeasureCode attribute.
	 * 
	 * @return Returns the invoiceItemUnitOfMeasureCode
	 * 
	 */
	public String getInvoiceItemUnitOfMeasureCode() { 
		return invoiceItemUnitOfMeasureCode;
	}

	/**
	 * Sets the invoiceItemUnitOfMeasureCode attribute.
	 * 
	 * @param invoiceItemUnitOfMeasureCode The invoiceItemUnitOfMeasureCode to set.
	 * 
	 */
	public void setInvoiceItemUnitOfMeasureCode(String invoiceItemUnitOfMeasureCode) {
		this.invoiceItemUnitOfMeasureCode = invoiceItemUnitOfMeasureCode;
	}


	/**
	 * Gets the invoiceItemUnitPrice attribute.
	 * 
	 * @return Returns the invoiceItemUnitPrice
	 * 
	 */
	public KualiDecimal getInvoiceItemUnitPrice() { 
		return invoiceItemUnitPrice;
	}

	/**
	 * Sets the invoiceItemUnitPrice attribute.
	 * 
	 * @param invoiceItemUnitPrice The invoiceItemUnitPrice to set.
	 * 
	 */
	public void setInvoiceItemUnitPrice(KualiDecimal invoiceItemUnitPrice) {
		this.invoiceItemUnitPrice = invoiceItemUnitPrice;
	}


	/**
	 * Gets the invoiceItemTotalAmount attribute.
	 * 
	 * @return Returns the invoiceItemTotalAmount
	 * 
	 */
	public KualiDecimal getInvoiceItemTotalAmount() { 
		return invoiceItemTotalAmount;
	}

	/**
	 * Sets the invoiceItemTotalAmount attribute.
	 * 
	 * @param invoiceItemTotalAmount The invoiceItemTotalAmount to set.
	 * 
	 */
	public void setInvoiceItemTotalAmount(KualiDecimal invoiceItemTotalAmount) {
		this.invoiceItemTotalAmount = invoiceItemTotalAmount;
	}


	/**
	 * Gets the invoiceItemServiceDate attribute.
	 * 
	 * @return Returns the invoiceItemServiceDate
	 * 
	 */
	public Date getInvoiceItemServiceDate() { 
		return invoiceItemServiceDate;
	}

	/**
	 * Sets the invoiceItemServiceDate attribute.
	 * 
	 * @param invoiceItemServiceDate The invoiceItemServiceDate to set.
	 * 
	 */
	public void setInvoiceItemServiceDate(Date invoiceItemServiceDate) {
		this.invoiceItemServiceDate = invoiceItemServiceDate;
	}


	/**
	 * Gets the invoiceItemCode attribute.
	 * 
	 * @return Returns the invoiceItemCode
	 * 
	 */
	public String getInvoiceItemCode() { 
		return invoiceItemCode;
	}

	/**
	 * Sets the invoiceItemCode attribute.
	 * 
	 * @param invoiceItemCode The invoiceItemCode to set.
	 * 
	 */
	public void setInvoiceItemCode(String invoiceItemCode) {
		this.invoiceItemCode = invoiceItemCode;
	}


	/**
	 * Gets the invoiceItemDescription attribute.
	 * 
	 * @return Returns the invoiceItemDescription
	 * 
	 */
	public String getInvoiceItemDescription() { 
		return invoiceItemDescription;
	}

	/**
	 * Sets the invoiceItemDescription attribute.
	 * 
	 * @param invoiceItemDescription The invoiceItemDescription to set.
	 * 
	 */
	public void setInvoiceItemDescription(String invoiceItemDescription) {
		this.invoiceItemDescription = invoiceItemDescription;
	}


	/**
	 * Gets the customerChartOfAccountCode attribute.
	 * 
	 * @return Returns the customerChartOfAccountCode
	 * 
	 */
	public String getCustomerChartOfAccountCode() { 
		return customerChartOfAccountCode;
	}

	/**
	 * Sets the customerChartOfAccountCode attribute.
	 * 
	 * @param customerChartOfAccountCode The customerChartOfAccountCode to set.
	 * 
	 */
	public void setCustomerChartOfAccountCode(String customerChartOfAccountCode) {
		this.customerChartOfAccountCode = customerChartOfAccountCode;
	}


	/**
	 * Gets the customerOrganizationCode attribute.
	 * 
	 * @return Returns the customerOrganizationCode
	 * 
	 */
	public String getCustomerOrganizationCode() { 
		return customerOrganizationCode;
	}

	/**
	 * Sets the customerOrganizationCode attribute.
	 * 
	 * @param customerOrganizationCode The customerOrganizationCode to set.
	 * 
	 */
	public void setCustomerOrganizationCode(String customerOrganizationCode) {
		this.customerOrganizationCode = customerOrganizationCode;
	}


	/**
	 * Gets the financialDocumentReferenceInvoiceNumber attribute.
	 * 
	 * @return Returns the financialDocumentReferenceInvoiceNumber
	 * 
	 */
	public String getFinancialDocumentReferenceInvoiceNumber() { 
		return financialDocumentReferenceInvoiceNumber;
	}

	/**
	 * Sets the financialDocumentReferenceInvoiceNumber attribute.
	 * 
	 * @param financialDocumentReferenceInvoiceNumber The financialDocumentReferenceInvoiceNumber to set.
	 * 
	 */
	public void setFinancialDocumentReferenceInvoiceNumber(String financialDocumentReferenceInvoiceNumber) {
		this.financialDocumentReferenceInvoiceNumber = financialDocumentReferenceInvoiceNumber;
	}


	/**
	 * Gets the accountsReceivableObject attribute.
	 * 
	 * @return Returns the accountsReceivableObject
	 * 
	 */
	public ObjectCode getAccountsReceivableObject() { 
		return accountsReceivableObject;
	}

	/**
	 * Sets the accountsReceivableObject attribute.
	 * 
	 * @param accountsReceivableObject The accountsReceivableObject to set.
	 * @deprecated
	 */
	public void setAccountsReceivableObject(ObjectCode accountsReceivableObject) {
		this.accountsReceivableObject = accountsReceivableObject;
	}

	/**
	 * Gets the financialSubObject attribute.
	 * 
	 * @return Returns the financialSubObject
	 * 
	 */
	public SubObjCd getFinancialSubObject() { 
		return financialSubObject;
	}

	/**
	 * Sets the financialSubObject attribute.
	 * 
	 * @param financialSubObject The financialSubObject to set.
	 * @deprecated
	 */
	public void setFinancialSubObject(SubObjCd financialSubObject) {
		this.financialSubObject = financialSubObject;
	}

	/**
	 * Gets the financialObject attribute.
	 * 
	 * @return Returns the financialObject
	 * 
	 */
	public ObjectCode getFinancialObject() { 
		return financialObject;
	}

	/**
	 * Sets the financialObject attribute.
	 * 
	 * @param financialObject The financialObject to set.
	 * @deprecated
	 */
	public void setFinancialObject(ObjectCode financialObject) {
		this.financialObject = financialObject;
	}

	/**
	 * Gets the accountsReceivableSubObject attribute.
	 * 
	 * @return Returns the accountsReceivableSubObject
	 * 
	 */
	public SubObjCd getAccountsReceivableSubObject() { 
		return accountsReceivableSubObject;
	}

	/**
	 * Sets the accountsReceivableSubObject attribute.
	 * 
	 * @param accountsReceivableSubObject The accountsReceivableSubObject to set.
	 * @deprecated
	 */
	public void setAccountsReceivableSubObject(SubObjCd accountsReceivableSubObject) {
		this.accountsReceivableSubObject = accountsReceivableSubObject;
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
	 * Gets the subAccount attribute.
	 * 
	 * @return Returns the subAccount
	 * 
	 */
	public SubAccount getSubAccount() { 
		return subAccount;
	}

	/**
	 * Sets the subAccount attribute.
	 * 
	 * @param subAccount The subAccount to set.
	 * @deprecated
	 */
	public void setSubAccount(SubAccount subAccount) {
		this.subAccount = subAccount;
	}

	/**
	 * Gets the account attribute.
	 * 
	 * @return Returns the account
	 * 
	 */
	public Account getAccount() { 
		return account;
	}

	/**
	 * Sets the account attribute.
	 * 
	 * @param account The account to set.
	 * @deprecated
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	/**
	 * Gets the project attribute.
	 * 
	 * @return Returns the project
	 * 
	 */
	public ProjectCode getProject() { 
		return project;
	}

	/**
	 * Sets the project attribute.
	 * 
	 * @param project The project to set.
	 * @deprecated
	 */
	public void setProject(ProjectCode project) {
		this.project = project;
	}

	/**
	 * Gets the customerOrganization attribute.
	 * 
	 * @return Returns the customerOrganization
	 * 
	 */
	public Org getCustomerOrganization() { 
		return customerOrganization;
	}

	/**
	 * Sets the customerOrganization attribute.
	 * 
	 * @param customerOrganization The customerOrganization to set.
	 * @deprecated
	 */
	public void setCustomerOrganization(Org customerOrganization) {
		this.customerOrganization = customerOrganization;
	}

	/**
	 * Gets the customerChartOfAccount attribute.
	 * 
	 * @return Returns the customerChartOfAccount
	 * 
	 */
	public Chart getCustomerChartOfAccount() { 
		return customerChartOfAccount;
	}

	/**
	 * Sets the customerChartOfAccount attribute.
	 * 
	 * @param customerChartOfAccount The customerChartOfAccount to set.
	 * @deprecated
	 */
	public void setCustomerChartOfAccount(Chart customerChartOfAccount) {
		this.customerChartOfAccount = customerChartOfAccount;
	}

	/**
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
	    LinkedHashMap m = new LinkedHashMap();	    
        m.put("documentNumber", this.documentNumber);
        if (this.invoiceItemNumber != null) {
            m.put("invoiceItemNumber", this.invoiceItemNumber.toString());
        }
	    return m;
    }
}
