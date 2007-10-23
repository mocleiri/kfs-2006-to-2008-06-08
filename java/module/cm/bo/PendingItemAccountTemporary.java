package org.kuali.module.cams.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.core.util.KualiDecimal;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.AccountingPeriod;
import org.kuali.module.chart.bo.Chart;
import org.kuali.module.chart.bo.ObjectCode;
import org.kuali.module.chart.bo.ProjectCode;
import org.kuali.module.chart.bo.SubAccount;
import org.kuali.module.chart.bo.SubObjCd;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class PendingItemAccountTemporary extends PersistableBusinessObjectBase {

	private String purchaseOrderNumber;
	private String invoiceNumber;
	private Long lineItemNumber;
	private String chartOfAccountsCode;
	private String accountNumber;
	private String subAccountNumber;
	private String accountObjectCode;
	private String financialSubObjectCode;
	private String projectCode;
	private String organizationReferenceId;
	private String referencePurchaseOrderNumber;
	private String referenceInvoiceNumber;
	private KualiDecimal invoiceItemPurchasedAmount;
	private KualiDecimal invoiceItemFederalContractAmount;
	private KualiDecimal payableInvoiceItemAccountAmount;
	private String accountsPayableDeferredCode;
	private String referenceDocumentNumber;
	private String referenceDocumentTypeCode;
	private Integer universityFiscalYear;
	private String universityFiscalPeriodCode;
	private String referenceRequisitionNumber;
	private KualiDecimal referenceDocumentAmount;
	private String referenceDocumentDate;

    private SubObjCd financialSubObject;
	private SubAccount subAccount;
	private Chart chartOfAccounts;
	private ProjectCode project;
	private AccountingPeriod universityFiscalPeriod;
    private Account account;
    private ObjectCode financialObject;
    
	/**
	 * Default constructor.
	 */
	public PendingItemAccountTemporary() {

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
	 * Gets the lineItemNumber attribute.
	 * 
	 * @return Returns the lineItemNumber
	 * 
	 */
	public Long getLineItemNumber() { 
		return lineItemNumber;
	}

	/**
	 * Sets the lineItemNumber attribute.
	 * 
	 * @param lineItemNumber The lineItemNumber to set.
	 * 
	 */
	public void setLineItemNumber(Long lineItemNumber) {
		this.lineItemNumber = lineItemNumber;
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
	 * Gets the referencePurchaseOrderNumber attribute.
	 * 
	 * @return Returns the referencePurchaseOrderNumber
	 * 
	 */
	public String getReferencePurchaseOrderNumber() { 
		return referencePurchaseOrderNumber;
	}

	/**
	 * Sets the referencePurchaseOrderNumber attribute.
	 * 
	 * @param referencePurchaseOrderNumber The referencePurchaseOrderNumber to set.
	 * 
	 */
	public void setReferencePurchaseOrderNumber(String referencePurchaseOrderNumber) {
		this.referencePurchaseOrderNumber = referencePurchaseOrderNumber;
	}


	/**
	 * Gets the referenceInvoiceNumber attribute.
	 * 
	 * @return Returns the referenceInvoiceNumber
	 * 
	 */
	public String getReferenceInvoiceNumber() { 
		return referenceInvoiceNumber;
	}

	/**
	 * Sets the referenceInvoiceNumber attribute.
	 * 
	 * @param referenceInvoiceNumber The referenceInvoiceNumber to set.
	 * 
	 */
	public void setReferenceInvoiceNumber(String referenceInvoiceNumber) {
		this.referenceInvoiceNumber = referenceInvoiceNumber;
	}


	/**
	 * Gets the invoiceItemPurchasedAmount attribute.
	 * 
	 * @return Returns the invoiceItemPurchasedAmount
	 * 
	 */
	public KualiDecimal getInvoiceItemPurchasedAmount() { 
		return invoiceItemPurchasedAmount;
	}

	/**
	 * Sets the invoiceItemPurchasedAmount attribute.
	 * 
	 * @param invoiceItemPurchasedAmount The invoiceItemPurchasedAmount to set.
	 * 
	 */
	public void setInvoiceItemPurchasedAmount(KualiDecimal invoiceItemPurchasedAmount) {
		this.invoiceItemPurchasedAmount = invoiceItemPurchasedAmount;
	}


	/**
	 * Gets the invoiceItemFederalContractAmount attribute.
	 * 
	 * @return Returns the invoiceItemFederalContractAmount
	 * 
	 */
	public KualiDecimal getInvoiceItemFederalContractAmount() { 
		return invoiceItemFederalContractAmount;
	}

	/**
	 * Sets the invoiceItemFederalContractAmount attribute.
	 * 
	 * @param invoiceItemFederalContractAmount The invoiceItemFederalContractAmount to set.
	 * 
	 */
	public void setInvoiceItemFederalContractAmount(KualiDecimal invoiceItemFederalContractAmount) {
		this.invoiceItemFederalContractAmount = invoiceItemFederalContractAmount;
	}


	/**
	 * Gets the payableInvoiceItemAccountAmount attribute.
	 * 
	 * @return Returns the payableInvoiceItemAccountAmount
	 * 
	 */
	public KualiDecimal getPayableInvoiceItemAccountAmount() { 
		return payableInvoiceItemAccountAmount;
	}

	/**
	 * Sets the payableInvoiceItemAccountAmount attribute.
	 * 
	 * @param payableInvoiceItemAccountAmount The payableInvoiceItemAccountAmount to set.
	 * 
	 */
	public void setPayableInvoiceItemAccountAmount(KualiDecimal payableInvoiceItemAccountAmount) {
		this.payableInvoiceItemAccountAmount = payableInvoiceItemAccountAmount;
	}


	/**
	 * Gets the accountsPayableDeferredCode attribute.
	 * 
	 * @return Returns the accountsPayableDeferredCode
	 * 
	 */
	public String getAccountsPayableDeferredCode() { 
		return accountsPayableDeferredCode;
	}

	/**
	 * Sets the accountsPayableDeferredCode attribute.
	 * 
	 * @param accountsPayableDeferredCode The accountsPayableDeferredCode to set.
	 * 
	 */
	public void setAccountsPayableDeferredCode(String accountsPayableDeferredCode) {
		this.accountsPayableDeferredCode = accountsPayableDeferredCode;
	}


	/**
	 * Gets the referenceDocumentNumber attribute.
	 * 
	 * @return Returns the referenceDocumentNumber
	 * 
	 */
	public String getReferenceDocumentNumber() { 
		return referenceDocumentNumber;
	}

	/**
	 * Sets the referenceDocumentNumber attribute.
	 * 
	 * @param referenceDocumentNumber The referenceDocumentNumber to set.
	 * 
	 */
	public void setReferenceDocumentNumber(String referenceDocumentNumber) {
		this.referenceDocumentNumber = referenceDocumentNumber;
	}


	/**
	 * Gets the referenceDocumentTypeCode attribute.
	 * 
	 * @return Returns the referenceDocumentTypeCode
	 * 
	 */
	public String getReferenceDocumentTypeCode() { 
		return referenceDocumentTypeCode;
	}

	/**
	 * Sets the referenceDocumentTypeCode attribute.
	 * 
	 * @param referenceDocumentTypeCode The referenceDocumentTypeCode to set.
	 * 
	 */
	public void setReferenceDocumentTypeCode(String referenceDocumentTypeCode) {
		this.referenceDocumentTypeCode = referenceDocumentTypeCode;
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
	 * Gets the universityFiscalPeriodCode attribute.
	 * 
	 * @return Returns the universityFiscalPeriodCode
	 * 
	 */
	public String getUniversityFiscalPeriodCode() { 
		return universityFiscalPeriodCode;
	}

	/**
	 * Sets the universityFiscalPeriodCode attribute.
	 * 
	 * @param universityFiscalPeriodCode The universityFiscalPeriodCode to set.
	 * 
	 */
	public void setUniversityFiscalPeriodCode(String universityFiscalPeriodCode) {
		this.universityFiscalPeriodCode = universityFiscalPeriodCode;
	}


	/**
	 * Gets the referenceRequisitionNumber attribute.
	 * 
	 * @return Returns the referenceRequisitionNumber
	 * 
	 */
	public String getReferenceRequisitionNumber() { 
		return referenceRequisitionNumber;
	}

	/**
	 * Sets the referenceRequisitionNumber attribute.
	 * 
	 * @param referenceRequisitionNumber The referenceRequisitionNumber to set.
	 * 
	 */
	public void setReferenceRequisitionNumber(String referenceRequisitionNumber) {
		this.referenceRequisitionNumber = referenceRequisitionNumber;
	}


	/**
	 * Gets the referenceDocumentAmount attribute.
	 * 
	 * @return Returns the referenceDocumentAmount
	 * 
	 */
	public KualiDecimal getReferenceDocumentAmount() { 
		return referenceDocumentAmount;
	}

	/**
	 * Sets the referenceDocumentAmount attribute.
	 * 
	 * @param referenceDocumentAmount The referenceDocumentAmount to set.
	 * 
	 */
	public void setReferenceDocumentAmount(KualiDecimal referenceDocumentAmount) {
		this.referenceDocumentAmount = referenceDocumentAmount;
	}


	/**
	 * Gets the referenceDocumentDate attribute.
	 * 
	 * @return Returns the referenceDocumentDate
	 * 
	 */
	public String getReferenceDocumentDate() { 
		return referenceDocumentDate;
	}

	/**
	 * Sets the referenceDocumentDate attribute.
	 * 
	 * @param referenceDocumentDate The referenceDocumentDate to set.
	 * 
	 */
	public void setReferenceDocumentDate(String referenceDocumentDate) {
		this.referenceDocumentDate = referenceDocumentDate;
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
	 * Gets the universityFiscalPeriod attribute.
	 * 
	 * @return Returns the universityFiscalPeriod
	 * 
	 */
	public AccountingPeriod getUniversityFiscalPeriod() { 
		return universityFiscalPeriod;
	}

	/**
	 * Sets the universityFiscalPeriod attribute.
	 * 
	 * @param universityFiscalPeriod The universityFiscalPeriod to set.
	 * @deprecated
	 */
	public void setUniversityFiscalPeriod(AccountingPeriod universityFiscalPeriod) {
		this.universityFiscalPeriod = universityFiscalPeriod;
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
     * Gets the financialObject attribute. 
     * @return Returns the financialObject.
     */
    public ObjectCode getFinancialObject() {
        return financialObject;
    }

    /**
     * Sets the financialObject attribute value.
     * @param financialObject The financialObject to set.
     * @deprecated
     */
    public void setFinancialObject(ObjectCode financialObject) {
        this.financialObject = financialObject;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();      
        m.put("purchaseOrderNumber", this.purchaseOrderNumber);
        m.put("invoiceNumber", this.invoiceNumber);
        if (this.lineItemNumber != null) {
            m.put("lineItemNumber", this.lineItemNumber.toString());
        }
        m.put("chartOfAccountsCode", this.chartOfAccountsCode);
        m.put("accountNumber", this.accountNumber);
        m.put("subAccountNumber", this.subAccountNumber);
        m.put("accountObjectCode", this.accountObjectCode);
        m.put("financialSubObjectCode", this.financialSubObjectCode);
        m.put("projectCode", this.projectCode);
        m.put("organizationReferenceId", this.organizationReferenceId);
        m.put("referencePurchaseOrderNumber", this.referencePurchaseOrderNumber);
        m.put("referenceInvoiceNumber", this.referenceInvoiceNumber);
        return m;
    }

}
