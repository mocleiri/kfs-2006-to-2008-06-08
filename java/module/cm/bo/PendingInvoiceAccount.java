package org.kuali.module.cams.bo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.core.util.KualiDecimal;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.Chart;
import org.kuali.module.chart.bo.ProjectCode;
import org.kuali.module.chart.bo.SubAccount;
import org.kuali.module.chart.bo.SubObjCd;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class PendingInvoiceAccount extends PersistableBusinessObjectBase {

	private String purchaseOrderNumber;
	private String invoiceNumber;
	private String chartOfAccountsCode;
	private String accountNumber;
	private String subAccountNumber;
	private String accountObjectCode;
	private String financialSubObjectCode;
	private String projectCode;
	private String organizationReferenceId;
	private KualiDecimal payableInvoiceAccountAmount;
	private KualiDecimal payableInvoiceAccountDiscount;
	private BigDecimal payableInvoiceAccountPercent;
	private String fiscalCampus;

    private PendingItemAccount organizationReference;
	private Chart chartOfAccounts;
	private SubAccount subAccount;
	private ProjectCode project;
    private Account account;
    
	/**
	 * Default constructor.
	 */
	public PendingInvoiceAccount() {

	}

	/**
	 * Gets the purchaseOrderNumber attribute.
	 * 
	 * @return Returns the purchaseOrderNumber
	 * 
	 */
	public String getPurchaseOrderNumber() { 
		return purchaseOrderNumber;
	}

	/**
	 * Sets the purchaseOrderNumber attribute.
	 * 
	 * @param purchaseOrderNumber The purchaseOrderNumber to set.
	 * 
	 */
	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}


	/**
	 * Gets the invoiceNumber attribute.
	 * 
	 * @return Returns the invoiceNumber
	 * 
	 */
	public String getInvoiceNumber() { 
		return invoiceNumber;
	}

	/**
	 * Sets the invoiceNumber attribute.
	 * 
	 * @param invoiceNumber The invoiceNumber to set.
	 * 
	 */
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
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
	 * Gets the accountObjectCode attribute.
	 * 
	 * @return Returns the accountObjectCode
	 * 
	 */
	public String getAccountObjectCode() { 
		return accountObjectCode;
	}

	/**
	 * Sets the accountObjectCode attribute.
	 * 
	 * @param accountObjectCode The accountObjectCode to set.
	 * 
	 */
	public void setAccountObjectCode(String accountObjectCode) {
		this.accountObjectCode = accountObjectCode;
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
	 * Gets the payableInvoiceAccountAmount attribute.
	 * 
	 * @return Returns the payableInvoiceAccountAmount
	 * 
	 */
	public KualiDecimal getPayableInvoiceAccountAmount() { 
		return payableInvoiceAccountAmount;
	}

	/**
	 * Sets the payableInvoiceAccountAmount attribute.
	 * 
	 * @param payableInvoiceAccountAmount The payableInvoiceAccountAmount to set.
	 * 
	 */
	public void setPayableInvoiceAccountAmount(KualiDecimal payableInvoiceAccountAmount) {
		this.payableInvoiceAccountAmount = payableInvoiceAccountAmount;
	}


	/**
	 * Gets the payableInvoiceAccountDiscount attribute.
	 * 
	 * @return Returns the payableInvoiceAccountDiscount
	 * 
	 */
	public KualiDecimal getPayableInvoiceAccountDiscount() { 
		return payableInvoiceAccountDiscount;
	}

	/**
	 * Sets the payableInvoiceAccountDiscount attribute.
	 * 
	 * @param payableInvoiceAccountDiscount The payableInvoiceAccountDiscount to set.
	 * 
	 */
	public void setPayableInvoiceAccountDiscount(KualiDecimal payableInvoiceAccountDiscount) {
		this.payableInvoiceAccountDiscount = payableInvoiceAccountDiscount;
	}


	/**
	 * Gets the payableInvoiceAccountPercent attribute.
	 * 
	 * @return Returns the payableInvoiceAccountPercent
	 * 
	 */
	public BigDecimal getPayableInvoiceAccountPercent() { 
		return payableInvoiceAccountPercent;
	}

	/**
	 * Sets the payableInvoiceAccountPercent attribute.
	 * 
	 * @param payableInvoiceAccountPercent The payableInvoiceAccountPercent to set.
	 * 
	 */
	public void setPayableInvoiceAccountPercent(BigDecimal payableInvoiceAccountPercent) {
		this.payableInvoiceAccountPercent = payableInvoiceAccountPercent;
	}


	/**
	 * Gets the fiscalCampus attribute.
	 * 
	 * @return Returns the fiscalCampus
	 * 
	 */
	public String getFiscalCampus() { 
		return fiscalCampus;
	}

	/**
	 * Sets the fiscalCampus attribute.
	 * 
	 * @param fiscalCampus The fiscalCampus to set.
	 * 
	 */
	public void setFiscalCampus(String fiscalCampus) {
		this.fiscalCampus = fiscalCampus;
	}


	/**
	 * Gets the organizationReference attribute.
	 * 
	 * @return Returns the organizationReference
	 * 
	 */
	public PendingItemAccount getOrganizationReference() { 
		return organizationReference;
	}

	/**
	 * Sets the organizationReference attribute.
	 * 
	 * @param organizationReference The organizationReference to set.
	 * @deprecated
	 */
	public void setOrganizationReference(PendingItemAccount organizationReference) {
		this.organizationReference = organizationReference;
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
     * Gets the account attribute. 
     * @return Returns the account.
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Sets the account attribute value.
     * @param account The account to set.
     * @deprecated
     */
    public void setAccount(Account account) {
        this.account = account;
    }    
    
	/**
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
	    LinkedHashMap m = new LinkedHashMap();	    
        m.put("purchaseOrderNumber", this.purchaseOrderNumber);
        m.put("invoiceNumber", this.invoiceNumber);
        m.put("chartOfAccountsCode", this.chartOfAccountsCode);
        m.put("accountNumber", this.accountNumber);
        m.put("subAccountNumber", this.subAccountNumber);
        m.put("accountObjectCode", this.accountObjectCode);
        m.put("financialSubObjectCode", this.financialSubObjectCode);
        m.put("projectCode", this.projectCode);
        m.put("organizationReferenceId", this.organizationReferenceId);
	    return m;
    }

}
