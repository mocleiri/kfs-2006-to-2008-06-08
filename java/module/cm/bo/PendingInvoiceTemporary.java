package org.kuali.module.cams.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.core.bo.Campus;
import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.core.util.KualiDecimal;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class PendingInvoiceTemporary extends PersistableBusinessObjectBase {

	private String purchaseOrderNumber;
	private String invoiceNumber;
	private KualiDecimal documentAmount;
	private Date documentDate;
	private String documentType;
	private String documentNumber;
	private KualiDecimal invoiceAdditionalCharge;
	private KualiDecimal invoiceDiscount;
	private String vendorName;
	private Date transactionDate;
	private String campusCode;
	private String lastActionCode;
	private String paymentRequestStatusCode;

    private Campus campus;
    
	/**
	 * Default constructor.
	 */
	public PendingInvoiceTemporary() {

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
	 * Gets the documentAmount attribute.
	 * 
	 * @return Returns the documentAmount
	 * 
	 */
	public KualiDecimal getDocumentAmount() { 
		return documentAmount;
	}

	/**
	 * Sets the documentAmount attribute.
	 * 
	 * @param documentAmount The documentAmount to set.
	 * 
	 */
	public void setDocumentAmount(KualiDecimal documentAmount) {
		this.documentAmount = documentAmount;
	}


	/**
	 * Gets the documentDate attribute.
	 * 
	 * @return Returns the documentDate
	 * 
	 */
	public Date getDocumentDate() { 
		return documentDate;
	}

	/**
	 * Sets the documentDate attribute.
	 * 
	 * @param documentDate The documentDate to set.
	 * 
	 */
	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}


	/**
	 * Gets the documentType attribute.
	 * 
	 * @return Returns the documentType
	 * 
	 */
	public String getDocumentType() { 
		return documentType;
	}

	/**
	 * Sets the documentType attribute.
	 * 
	 * @param documentType The documentType to set.
	 * 
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
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
	 * Gets the invoiceAdditionalCharge attribute.
	 * 
	 * @return Returns the invoiceAdditionalCharge
	 * 
	 */
	public KualiDecimal getInvoiceAdditionalCharge() { 
		return invoiceAdditionalCharge;
	}

	/**
	 * Sets the invoiceAdditionalCharge attribute.
	 * 
	 * @param invoiceAdditionalCharge The invoiceAdditionalCharge to set.
	 * 
	 */
	public void setInvoiceAdditionalCharge(KualiDecimal invoiceAdditionalCharge) {
		this.invoiceAdditionalCharge = invoiceAdditionalCharge;
	}


	/**
	 * Gets the invoiceDiscount attribute.
	 * 
	 * @return Returns the invoiceDiscount
	 * 
	 */
	public KualiDecimal getInvoiceDiscount() { 
		return invoiceDiscount;
	}

	/**
	 * Sets the invoiceDiscount attribute.
	 * 
	 * @param invoiceDiscount The invoiceDiscount to set.
	 * 
	 */
	public void setInvoiceDiscount(KualiDecimal invoiceDiscount) {
		this.invoiceDiscount = invoiceDiscount;
	}


	/**
	 * Gets the vendorName attribute.
	 * 
	 * @return Returns the vendorName
	 * 
	 */
	public String getVendorName() { 
		return vendorName;
	}

	/**
	 * Sets the vendorName attribute.
	 * 
	 * @param vendorName The vendorName to set.
	 * 
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}


	/**
	 * Gets the transactionDate attribute.
	 * 
	 * @return Returns the transactionDate
	 * 
	 */
	public Date getTransactionDate() { 
		return transactionDate;
	}

	/**
	 * Sets the transactionDate attribute.
	 * 
	 * @param transactionDate The transactionDate to set.
	 * 
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
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
	 * Gets the lastActionCode attribute.
	 * 
	 * @return Returns the lastActionCode
	 * 
	 */
	public String getLastActionCode() { 
		return lastActionCode;
	}

	/**
	 * Sets the lastActionCode attribute.
	 * 
	 * @param lastActionCode The lastActionCode to set.
	 * 
	 */
	public void setLastActionCode(String lastActionCode) {
		this.lastActionCode = lastActionCode;
	}


	/**
	 * Gets the paymentRequestStatusCode attribute.
	 * 
	 * @return Returns the paymentRequestStatusCode
	 * 
	 */
	public String getPaymentRequestStatusCode() { 
		return paymentRequestStatusCode;
	}

	/**
	 * Sets the paymentRequestStatusCode attribute.
	 * 
	 * @param paymentRequestStatusCode The paymentRequestStatusCode to set.
	 * 
	 */
	public void setPaymentRequestStatusCode(String paymentRequestStatusCode) {
		this.paymentRequestStatusCode = paymentRequestStatusCode;
	}

    /**
     * Gets the campus attribute. 
     * @return Returns the campus.
     */
    public Campus getCampus() {
        return campus;
    }

    /**
     * Sets the campus attribute value.
     * @param campus The campus to set.
     * @deprecated
     */
    public void setCampus(Campus campus) {
        this.campus = campus;
    }    

	/**
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
	    LinkedHashMap m = new LinkedHashMap();	    
        m.put("purchaseOrderNumber", this.purchaseOrderNumber);
        m.put("invoiceNumber", this.invoiceNumber);
	    return m;
    }

}
