package org.kuali.module.ar.document;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.core.bo.DocumentHeader;
import org.kuali.kfs.bo.GeneralLedgerPendingEntrySourceDetail;
import org.kuali.kfs.document.AccountingDocumentBase;
import org.kuali.module.ar.ArConstants;
import org.kuali.module.ar.bo.CustomerCreditMemoDetail;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class CustomerCreditMemoDocument extends AccountingDocumentBase {
    
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CustomerCreditMemoDocument.class);
    
    private String statusCode;
    
    private String documentNumber;
    private Integer financialDocumentPostingYear;
    private String financialDocumentReferenceInvoiceNumber;
    
    private DocumentHeader documentHeader;

    private CustomerInvoiceDocument invoice;
    private List<CustomerCreditMemoDetail> creditMemoDetails;
    
    public CustomerCreditMemoDocument(){
        super();
        creditMemoDetails = new ArrayList<CustomerCreditMemoDetail>();
    }

    /**
     * Gets the creditMemoDetails attribute. 
     * @return Returns the creditMemoDetails.
     */
    public List<CustomerCreditMemoDetail> getCreditMemoDetails() {
        return creditMemoDetails;
    }


    /**
     * Sets the creditMemoDetails attribute value.
     * @param creditMemoDetails The creditMemoDetails to set.
     */
    public void setCreditMemoDetails(List<CustomerCreditMemoDetail> creditMemoDetails) {
        this.creditMemoDetails = creditMemoDetails;
    }


    /**
     * Gets the documentHeader attribute. 
     * @return Returns the documentHeader.
     */
    public DocumentHeader getDocumentHeader() {
        return documentHeader;
    }


    /**
     * Sets the documentHeader attribute value.
     * @param documentHeader The documentHeader to set.
     */
    public void setDocumentHeader(DocumentHeader documentHeader) {
        this.documentHeader = documentHeader;
    }


    /**
     * Gets the documentNumber attribute. 
     * @return Returns the documentNumber.
     */
    public String getDocumentNumber() {
        return documentNumber;
    }


    /**
     * Sets the documentNumber attribute value.
     * @param documentNumber The documentNumber to set.
     */
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }


    /**
     * Gets the financialDocumentPostingYear attribute. 
     * @return Returns the financialDocumentPostingYear.
     */
    public Integer getFinancialDocumentPostingYear() {
        return financialDocumentPostingYear;
    }


    /**
     * Sets the financialDocumentPostingYear attribute value.
     * @param financialDocumentPostingYear The financialDocumentPostingYear to set.
     */
    public void setFinancialDocumentPostingYear(Integer financialDocumentPostingYear) {
        this.financialDocumentPostingYear = financialDocumentPostingYear;
    }


    /**
     * Gets the financialDocumentReferenceInvoiceNumber attribute. 
     * @return Returns the financialDocumentReferenceInvoiceNumber.
     */
    public String getFinancialDocumentReferenceInvoiceNumber() {
        return financialDocumentReferenceInvoiceNumber;
    }

    /**
     * Sets the financialDocumentReferenceInvoiceNumber attribute value.
     * @param financialDocumentReferenceInvoiceNumber The financialDocumentReferenceInvoiceNumber to set.
     */
    public void setFinancialDocumentReferenceInvoiceNumber(String financialDocumentReferenceInvoiceNumber) {
        if (financialDocumentReferenceInvoiceNumber != null) {
            financialDocumentReferenceInvoiceNumber = financialDocumentReferenceInvoiceNumber.toUpperCase();
        }
        this.financialDocumentReferenceInvoiceNumber = financialDocumentReferenceInvoiceNumber;
    }
    
    /**
     * Gets the invoice attribute. 
     * @return Returns the invoice.
     */
    public CustomerInvoiceDocument getInvoice() {
        return invoice;
    }

    /**
     * Sets the invoice attribute value.
     * @param invoice The invoice to set.
     */
    public void setInvoice(CustomerInvoiceDocument invoice) {
        this.invoice = invoice;
    }
    
    /**
     * Gets the statusCode attribute. 
     * @return Returns the statusCode.
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the statusCode attribute value.
     * @param statusCode The statusCode to set.
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    
    /**
     * Initializes the values for a new document.
     */
    public void initiateDocument() {
        LOG.debug("initiateDocument() started");
        setStatusCode(ArConstants.CustomerCreditMemoStatuses.INITIATE);
    }
    
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();      
        m.put("documentNumber", this.documentNumber);
        return m;
    }
    
    /**
     * Clear out the initially populated fields.
     */
    public void clearInitFields() {
        LOG.debug("clearDocument() started");

        // Clearing document Init fields
        setFinancialDocumentReferenceInvoiceNumber(null);
    }


    @Override
    public boolean isDebit(GeneralLedgerPendingEntrySourceDetail postable) {
        // TODO Auto-generated method stub
        return false;
    }

}
