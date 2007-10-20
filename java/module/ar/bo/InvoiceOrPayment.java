package org.kuali.module.ar.bo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.core.util.KualiDecimal;
import org.kuali.module.chart.bo.Chart;
import org.kuali.module.chart.bo.Org;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class InvoiceOrPayment extends PersistableBusinessObjectBase {

	private String documentNumber;
	private String customerChartOfAccountCode;
	private String customerOrganizationCode;
	private String customerNumber;
	private String processingChartOfAccountCode;
	private String processingOrganizationCode;
	private Date entryDate;
	private String documentTypeCode;
	private String invoiceHeaderText;
	private String invoiceAttentionLineText;
	private Date invoiceDueDate;
	private Date billingDate;
	private String invoiceTermsText;
	private String organizationInvoiceNumber;
	private boolean writeoffIndicator;
	private String customerPurchaseOrderNumber;
	private boolean printInvoiceIndicator;
	private boolean printInvoiceDetailIndicator;
	private String customerCheckNumber;
	private String customerPaymentMediumCode;
	private KualiDecimal invoiceTotalAmount;
	private KualiDecimal invoicePaidOrAppliedAmount;
	private KualiDecimal invoiceWriteoffAmount;
	private String invoicePaymentDescription;
	private Date customerPurchaseOrderDate;

	private Org customerOrganization;
	private Chart customerChartOfAccount;
	private OrganizationCustomer customer;
	private Chart processingChartOfAccount;
	private Org processingOrganization;

    private List<InvoiceOrPaymentItem> invoiceOrPaymentItems;
    
	/**
	 * Default constructor.
	 */
	public InvoiceOrPayment() {
        invoiceOrPaymentItems = new ArrayList<InvoiceOrPaymentItem>();
        
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
	 * Gets the processingChartOfAccountCode attribute.
	 * 
	 * @return Returns the processingChartOfAccountCode
	 * 
	 */
	public String getProcessingChartOfAccountCode() { 
		return processingChartOfAccountCode;
	}

	/**
	 * Sets the processingChartOfAccountCode attribute.
	 * 
	 * @param processingChartOfAccountCode The processingChartOfAccountCode to set.
	 * 
	 */
	public void setProcessingChartOfAccountCode(String processingChartOfAccountCode) {
		this.processingChartOfAccountCode = processingChartOfAccountCode;
	}


	/**
	 * Gets the processingOrganizationCode attribute.
	 * 
	 * @return Returns the processingOrganizationCode
	 * 
	 */
	public String getProcessingOrganizationCode() { 
		return processingOrganizationCode;
	}

	/**
	 * Sets the processingOrganizationCode attribute.
	 * 
	 * @param processingOrganizationCode The processingOrganizationCode to set.
	 * 
	 */
	public void setProcessingOrganizationCode(String processingOrganizationCode) {
		this.processingOrganizationCode = processingOrganizationCode;
	}


	/**
	 * Gets the entryDate attribute.
	 * 
	 * @return Returns the entryDate
	 * 
	 */
	public Date getEntryDate() { 
		return entryDate;
	}

	/**
	 * Sets the entryDate attribute.
	 * 
	 * @param entryDate The entryDate to set.
	 * 
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}


	/**
	 * Gets the documentTypeCode attribute.
	 * 
	 * @return Returns the documentTypeCode
	 * 
	 */
	public String getDocumentTypeCode() { 
		return documentTypeCode;
	}

	/**
	 * Sets the documentTypeCode attribute.
	 * 
	 * @param documentTypeCode The documentTypeCode to set.
	 * 
	 */
	public void setDocumentTypeCode(String documentTypeCode) {
		this.documentTypeCode = documentTypeCode;
	}


	/**
	 * Gets the invoiceHeaderText attribute.
	 * 
	 * @return Returns the invoiceHeaderText
	 * 
	 */
	public String getInvoiceHeaderText() { 
		return invoiceHeaderText;
	}

	/**
	 * Sets the invoiceHeaderText attribute.
	 * 
	 * @param invoiceHeaderText The invoiceHeaderText to set.
	 * 
	 */
	public void setInvoiceHeaderText(String invoiceHeaderText) {
		this.invoiceHeaderText = invoiceHeaderText;
	}


	/**
	 * Gets the invoiceAttentionLineText attribute.
	 * 
	 * @return Returns the invoiceAttentionLineText
	 * 
	 */
	public String getInvoiceAttentionLineText() { 
		return invoiceAttentionLineText;
	}

	/**
	 * Sets the invoiceAttentionLineText attribute.
	 * 
	 * @param invoiceAttentionLineText The invoiceAttentionLineText to set.
	 * 
	 */
	public void setInvoiceAttentionLineText(String invoiceAttentionLineText) {
		this.invoiceAttentionLineText = invoiceAttentionLineText;
	}


	/**
	 * Gets the invoiceDueDate attribute.
	 * 
	 * @return Returns the invoiceDueDate
	 * 
	 */
	public Date getInvoiceDueDate() { 
		return invoiceDueDate;
	}

	/**
	 * Sets the invoiceDueDate attribute.
	 * 
	 * @param invoiceDueDate The invoiceDueDate to set.
	 * 
	 */
	public void setInvoiceDueDate(Date invoiceDueDate) {
		this.invoiceDueDate = invoiceDueDate;
	}


	/**
	 * Gets the billingDate attribute.
	 * 
	 * @return Returns the billingDate
	 * 
	 */
	public Date getBillingDate() { 
		return billingDate;
	}

	/**
	 * Sets the billingDate attribute.
	 * 
	 * @param billingDate The billingDate to set.
	 * 
	 */
	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}


	/**
	 * Gets the invoiceTermsText attribute.
	 * 
	 * @return Returns the invoiceTermsText
	 * 
	 */
	public String getInvoiceTermsText() { 
		return invoiceTermsText;
	}

	/**
	 * Sets the invoiceTermsText attribute.
	 * 
	 * @param invoiceTermsText The invoiceTermsText to set.
	 * 
	 */
	public void setInvoiceTermsText(String invoiceTermsText) {
		this.invoiceTermsText = invoiceTermsText;
	}


	/**
	 * Gets the organizationInvoiceNumber attribute.
	 * 
	 * @return Returns the organizationInvoiceNumber
	 * 
	 */
	public String getOrganizationInvoiceNumber() { 
		return organizationInvoiceNumber;
	}

	/**
	 * Sets the organizationInvoiceNumber attribute.
	 * 
	 * @param organizationInvoiceNumber The organizationInvoiceNumber to set.
	 * 
	 */
	public void setOrganizationInvoiceNumber(String organizationInvoiceNumber) {
		this.organizationInvoiceNumber = organizationInvoiceNumber;
	}


	/**
	 * Gets the writeoffIndicator attribute.
	 * 
	 * @return Returns the writeoffIndicator
	 * 
	 */
	public boolean isWriteoffIndicator() { 
		return writeoffIndicator;
	}

	/**
	 * Sets the writeoffIndicator attribute.
	 * 
	 * @param writeoffIndicator The writeoffIndicator to set.
	 * 
	 */
	public void setWriteoffIndicator(boolean writeoffIndicator) {
		this.writeoffIndicator = writeoffIndicator;
	}


	/**
	 * Gets the customerPurchaseOrderNumber attribute.
	 * 
	 * @return Returns the customerPurchaseOrderNumber
	 * 
	 */
	public String getCustomerPurchaseOrderNumber() { 
		return customerPurchaseOrderNumber;
	}

	/**
	 * Sets the customerPurchaseOrderNumber attribute.
	 * 
	 * @param customerPurchaseOrderNumber The customerPurchaseOrderNumber to set.
	 * 
	 */
	public void setCustomerPurchaseOrderNumber(String customerPurchaseOrderNumber) {
		this.customerPurchaseOrderNumber = customerPurchaseOrderNumber;
	}


	/**
	 * Gets the printInvoiceIndicator attribute.
	 * 
	 * @return Returns the printInvoiceIndicator
	 * 
	 */
	public boolean isPrintInvoiceIndicator() { 
		return printInvoiceIndicator;
	}

	/**
	 * Sets the printInvoiceIndicator attribute.
	 * 
	 * @param printInvoiceIndicator The printInvoiceIndicator to set.
	 * 
	 */
	public void setPrintInvoiceIndicator(boolean printInvoiceIndicator) {
		this.printInvoiceIndicator = printInvoiceIndicator;
	}


	/**
	 * Gets the printInvoiceDetailIndicator attribute.
	 * 
	 * @return Returns the printInvoiceDetailIndicator
	 * 
	 */
	public boolean isPrintInvoiceDetailIndicator() { 
		return printInvoiceDetailIndicator;
	}

	/**
	 * Sets the printInvoiceDetailIndicator attribute.
	 * 
	 * @param printInvoiceDetailIndicator The printInvoiceDetailIndicator to set.
	 * 
	 */
	public void setPrintInvoiceDetailIndicator(boolean printInvoiceDetailIndicator) {
		this.printInvoiceDetailIndicator = printInvoiceDetailIndicator;
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
	 * Gets the customerPaymentMediumCode attribute.
	 * 
	 * @return Returns the customerPaymentMediumCode
	 * 
	 */
	public String getCustomerPaymentMediumCode() { 
		return customerPaymentMediumCode;
	}

	/**
	 * Sets the customerPaymentMediumCode attribute.
	 * 
	 * @param customerPaymentMediumCode The customerPaymentMediumCode to set.
	 * 
	 */
	public void setCustomerPaymentMediumCode(String customerPaymentMediumCode) {
		this.customerPaymentMediumCode = customerPaymentMediumCode;
	}


	/**
	 * Gets the invoiceTotalAmount attribute.
	 * 
	 * @return Returns the invoiceTotalAmount
	 * 
	 */
	public KualiDecimal getInvoiceTotalAmount() { 
		return invoiceTotalAmount;
	}

	/**
	 * Sets the invoiceTotalAmount attribute.
	 * 
	 * @param invoiceTotalAmount The invoiceTotalAmount to set.
	 * 
	 */
	public void setInvoiceTotalAmount(KualiDecimal invoiceTotalAmount) {
		this.invoiceTotalAmount = invoiceTotalAmount;
	}


	/**
	 * Gets the invoicePaidOrAppliedAmount attribute.
	 * 
	 * @return Returns the invoicePaidOrAppliedAmount
	 * 
	 */
	public KualiDecimal getInvoicePaidOrAppliedAmount() { 
		return invoicePaidOrAppliedAmount;
	}

	/**
	 * Sets the invoicePaidOrAppliedAmount attribute.
	 * 
	 * @param invoicePaidOrAppliedAmount The invoicePaidOrAppliedAmount to set.
	 * 
	 */
	public void setInvoicePaidOrAppliedAmount(KualiDecimal invoicePaidOrAppliedAmount) {
		this.invoicePaidOrAppliedAmount = invoicePaidOrAppliedAmount;
	}


	/**
	 * Gets the invoiceWriteoffAmount attribute.
	 * 
	 * @return Returns the invoiceWriteoffAmount
	 * 
	 */
	public KualiDecimal getInvoiceWriteoffAmount() { 
		return invoiceWriteoffAmount;
	}

	/**
	 * Sets the invoiceWriteoffAmount attribute.
	 * 
	 * @param invoiceWriteoffAmount The invoiceWriteoffAmount to set.
	 * 
	 */
	public void setInvoiceWriteoffAmount(KualiDecimal invoiceWriteoffAmount) {
		this.invoiceWriteoffAmount = invoiceWriteoffAmount;
	}


	/**
	 * Gets the invoicePaymentDescription attribute.
	 * 
	 * @return Returns the invoicePaymentDescription
	 * 
	 */
	public String getInvoicePaymentDescription() { 
		return invoicePaymentDescription;
	}

	/**
	 * Sets the invoicePaymentDescription attribute.
	 * 
	 * @param invoicePaymentDescription The invoicePaymentDescription to set.
	 * 
	 */
	public void setInvoicePaymentDescription(String invoicePaymentDescription) {
		this.invoicePaymentDescription = invoicePaymentDescription;
	}


	/**
	 * Gets the customerPurchaseOrderDate attribute.
	 * 
	 * @return Returns the customerPurchaseOrderDate
	 * 
	 */
	public Date getCustomerPurchaseOrderDate() { 
		return customerPurchaseOrderDate;
	}

	/**
	 * Sets the customerPurchaseOrderDate attribute.
	 * 
	 * @param customerPurchaseOrderDate The customerPurchaseOrderDate to set.
	 * 
	 */
	public void setCustomerPurchaseOrderDate(Date customerPurchaseOrderDate) {
		this.customerPurchaseOrderDate = customerPurchaseOrderDate;
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
	 * Gets the customer attribute.
	 * 
	 * @return Returns the customer
	 * 
	 */
	public OrganizationCustomer getCustomer() { 
		return customer;
	}

	/**
	 * Sets the customer attribute.
	 * 
	 * @param customer The customer to set.
	 * @deprecated
	 */
	public void setCustomer(OrganizationCustomer customer) {
		this.customer = customer;
	}

	/**
	 * Gets the processingChartOfAccount attribute.
	 * 
	 * @return Returns the processingChartOfAccount
	 * 
	 */
	public Chart getProcessingChartOfAccount() { 
		return processingChartOfAccount;
	}

	/**
	 * Sets the processingChartOfAccount attribute.
	 * 
	 * @param processingChartOfAccount The processingChartOfAccount to set.
	 * @deprecated
	 */
	public void setProcessingChartOfAccount(Chart processingChartOfAccount) {
		this.processingChartOfAccount = processingChartOfAccount;
	}

	/**
	 * Gets the processingOrganization attribute.
	 * 
	 * @return Returns the processingOrganization
	 * 
	 */
	public Org getProcessingOrganization() { 
		return processingOrganization;
	}

	/**
	 * Sets the processingOrganization attribute.
	 * 
	 * @param processingOrganization The processingOrganization to set.
	 * @deprecated
	 */
	public void setProcessingOrganization(Org processingOrganization) {
		this.processingOrganization = processingOrganization;
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
