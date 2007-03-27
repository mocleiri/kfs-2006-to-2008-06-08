/*
 * Copyright (c) 2004, 2005 The National Association of College and University Business Officers,
 * Cornell University, Trustees of Indiana University, Michigan State University Board of Trustees,
 * Trustees of San Joaquin Delta College, University of Hawai'i, The Arizona Board of Regents on
 * behalf of the University of Arizona, and the r*smart group.
 * 
 * Licensed under the Educational Community License Version 1.0 (the "License"); By obtaining,
 * using and/or copying this Original Work, you agree that you have read, understand, and will
 * comply with the terms and conditions of the Educational Community License.
 * 
 * You may obtain a copy of the License at:
 * 
 * http://kualiproject.org/license.html
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.kuali.bo;

import java.io.Serializable;
import java.sql.Timestamp;

import org.kuali.exceptions.IllegalObjectStateException;
import org.kuali.util.KualiDecimal;
import org.kuali.validation.ValidationErrorList;

public class GeneralLedgerPendingEntry extends BusinessObjectBase implements Serializable, Cloneable {

    private Long documentHeaderId;
    
    private Integer transactionEntrySequenceId;

    private String chartOfAccountsCode;

    private String accountNumber;

    private String subAccountNumber;

    private String objectCode;

    private String subObjectCode;

    private String balanceTypeCode;

    private String objectTypeCode;

    private Integer universityFiscalYear;

    private String universityFiscalAccountingPeriod;

    private String transactionLedgerEntryDescription;

    private KualiDecimal transactionLedgerEntryAmount;

    private String debitOrCreditCode;

    private Timestamp transactionDate;

    private String documentTypeCode;

    private String organizationDocumentNumber;

    private String projectCode;

    private String organizationReferenceId;

    private String referenceDocumentTypeCode;

    private String referenceOriginCode;

    private String referenceDocumentNumber;

    private Timestamp documentReversalDate;

    private String encumbranceUpdateCode;

    private String approvedCode;

    private String accountSufficientFundsObjectCode;

    private String transactionEntryOffsetCode;

    private Timestamp transactionEntryProcessed;


    /**
     * @return Returns the accountNumber.
     */
    public String getAccountNumber() {
        return accountNumber;
    }
    /**
     * @param accountNumber The accountNumber to set.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    /**
     * @return Returns the accountSufficientFundsObjectCode.
     */
    public String getAccountSufficientFundsObjectCode() {
        return accountSufficientFundsObjectCode;
    }
    /**
     * @param accountSufficientFundsObjectCode The accountSufficientFundsObjectCode to set.
     */
    public void setAccountSufficientFundsObjectCode(
            String accountSufficientFundsObjectCode) {
        this.accountSufficientFundsObjectCode = accountSufficientFundsObjectCode;
    }
    /**
     * @return Returns the approvedCode.
     */
    public String getApprovedCode() {
        return approvedCode;
    }
    /**
     * @param approvedCode The approvedCode to set.
     */
    public void setApprovedCode(String approvedCode) {
        this.approvedCode = approvedCode;
    }
    /**
     * @return Returns the balanceTypeCode.
     */
    public String getBalanceTypeCode() {
        return balanceTypeCode;
    }
    /**
     * @param balanceTypeCode The balanceTypeCode to set.
     */
    public void setBalanceTypeCode(String balanceTypeCode) {
        this.balanceTypeCode = balanceTypeCode;
    }
    /**
     * @return Returns the chartOfAccountsCode.
     */
    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }
    /**
     * @param chartOfAccountsCode The chartOfAccountsCode to set.
     */
    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }
    /**
     * @return Returns the debitOrCreditCode.
     */
    public String getDebitOrCreditCode() {
        return debitOrCreditCode;
    }
    /**
     * @param debitOrCreditCode The debitOrCreditCode to set.
     */
    public void setDebitOrCreditCode(String debitOrCreditCode) {
        this.debitOrCreditCode = debitOrCreditCode;
    }
    /**
     * @return Returns the documentHeaderId.
     */
    public Long getDocumentHeaderId() {
        return documentHeaderId;
    }
    /**
     * @param documentHeaderId The documentHeaderId to set.
     */
    public void setDocumentHeaderId(Long documentHeaderId) {
        this.documentHeaderId = documentHeaderId;
    }
    /**
     * @return Returns the documentReversalDate.
     */
    public Timestamp getDocumentReversalDate() {
        return documentReversalDate;
        
    }
    /**
     * @param documentReversalDate The documentReversalDate to set.
     */
    public void setDocumentReversalDate(Timestamp documentReversalDate) {
        this.documentReversalDate = documentReversalDate;
    }
    /**
     * @return Returns the documentTypeCode.
     */
    public String getDocumentTypeCode() {
        return documentTypeCode;
    }
    /**
     * @param documentTypeCode The documentTypeCode to set.
     */
    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }
    /**
     * @return Returns the encumbranceUpdateCode.
     */
    public String getEncumbranceUpdateCode() {
        return encumbranceUpdateCode;
    }
    /**
     * @param encumbranceUpdateCode The encumbranceUpdateCode to set.
     */
    public void setEncumbranceUpdateCode(String encumbranceUpdateCode) {
        this.encumbranceUpdateCode = encumbranceUpdateCode;
    }
    /**
     * @return Returns the objectCode.
     */
    public String getObjectCode() {
        return objectCode;
    }
    /**
     * @param objectCode The objectCode to set.
     */
    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }
    /**
     * @return Returns the objectTypeCode.
     */
    public String getObjectTypeCode() {
        return objectTypeCode;
    }
    /**
     * @param objectTypeCode The objectTypeCode to set.
     */
    public void setObjectTypeCode(String objectTypeCode) {
        this.objectTypeCode = objectTypeCode;
    }
    /**
     * @return Returns the organizationDocumentNumber.
     */
    public String getOrganizationDocumentNumber() {
        return organizationDocumentNumber;
    }
    /**
     * @param organizationDocumentNumber The organizationDocumentNumber to set.
     */
    public void setOrganizationDocumentNumber(String organizationDocumentNumber) {
        this.organizationDocumentNumber = organizationDocumentNumber;
    }
    /**
     * @return Returns the organizationReferenceId.
     */
    public String getOrganizationReferenceId() {
        return organizationReferenceId;
    }
    /**
     * @param organizationReferenceId The organizationReferenceId to set.
     */
    public void setOrganizationReferenceId(String organizationReferenceId) {
        this.organizationReferenceId = organizationReferenceId;
    }
    /**
     * @return Returns the projectCode.
     */
    public String getProjectCode() {
        return projectCode;
    }
    /**
     * @param projectCode The projectCode to set.
     */
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }
    /**
     * @return Returns the referenceDocumentNumber.
     */
    public String getReferenceDocumentNumber() {
        return referenceDocumentNumber;
    }
    /**
     * @param referenceDocumentNumber The referenceDocumentNumber to set.
     */
    public void setReferenceDocumentNumber(String referenceDocumentNumber) {
        this.referenceDocumentNumber = referenceDocumentNumber;
    }
    /**
     * @return Returns the referenceDocumentTypeCode.
     */
    public String getReferenceDocumentTypeCode() {
        return referenceDocumentTypeCode;
    }
    /**
     * @param referenceDocumentTypeCode The referenceDocumentTypeCode to set.
     */
    public void setReferenceDocumentTypeCode(String referenceDocumentTypeCode) {
        this.referenceDocumentTypeCode = referenceDocumentTypeCode;
    }
    /**
     * @return Returns the referenceOriginCode.
     */
    public String getReferenceOriginCode() {
        return referenceOriginCode;
    }
    /**
     * @param referenceOriginCode The referenceOriginCode to set.
     */
    public void setReferenceOriginCode(String referenceOriginCode) {
        this.referenceOriginCode = referenceOriginCode;
    }
    /**
     * @return Returns the subAccountNumber.
     */
    public String getSubAccountNumber() {
        return subAccountNumber;
    }
    /**
     * @param subAccountNumber The subAccountNumber to set.
     */
    public void setSubAccountNumber(String subAccountNumber) {
        this.subAccountNumber = subAccountNumber;
    }
    /**
     * @return Returns the subObjectCode.
     */
    public String getSubObjectCode() {
        return subObjectCode;
    }
    /**
     * @param subObjectCode The subObjectCode to set.
     */
    public void setSubObjectCode(String subObjectCode) {
        this.subObjectCode = subObjectCode;
    }
    /**
     * @return Returns the transactionDate.
     */
    public Timestamp getTransactionDate() {
        return transactionDate;
    }
    /**
     * @param transactionDate The transactionDate to set.
     */
    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }
    /**
     * @return Returns the transactionEntryOffsetCode.
     */
    public String getTransactionEntryOffsetCode() {
        return transactionEntryOffsetCode;
    }
    /**
     * @param transactionEntryOffsetCode The transactionEntryOffsetCode to set.
     */
    public void setTransactionEntryOffsetCode(String transactionEntryOffsetCode) {
        this.transactionEntryOffsetCode = transactionEntryOffsetCode;
    }
    /**
     * @return Returns the transactionEntryProcessed.
     */
    public Timestamp getTransactionEntryProcessed() {
        return transactionEntryProcessed;
    }
    /**
     * @param transactionEntryProcessed The transactionEntryProcessed to set.
     */
    public void setTransactionEntryProcessed(Timestamp transactionEntryProcessedTimestamp) {
        this.transactionEntryProcessed = transactionEntryProcessedTimestamp;
    }
    /**
     * @return Returns the transactionEntrySequenceId.
     */
    public Integer getTransactionEntrySequenceId() {
        return transactionEntrySequenceId;
    }
    /**
     * @param transactionEntrySequenceId The transactionEntrySequenceId to set.
     */
    public void setTransactionEntrySequenceId(Integer transactionEntrySequenceId) {
        this.transactionEntrySequenceId = transactionEntrySequenceId;
    }
    /**
     * @return Returns the transactionLedgerEntryAmount.
     */
    public KualiDecimal getTransactionLedgerEntryAmount() {
        return transactionLedgerEntryAmount;
    }
    /**
     * @param transactionLedgerEntryAmount The transactionLedgerEntryAmount to set.
     */
    public void setTransactionLedgerEntryAmount(
            KualiDecimal transactionLedgerEntryAmount) {
        this.transactionLedgerEntryAmount = transactionLedgerEntryAmount;
    }
    /**
     * @return Returns the transactionLedgerEntryDescription.
     */
    public String getTransactionLedgerEntryDescription() {
        return transactionLedgerEntryDescription;
    }
    /**
     * @param transactionLedgerEntryDescription The transactionLedgerEntryDescription to set.
     */
    public void setTransactionLedgerEntryDescription(
            String transactionLedgerEntryDescription) {
        this.transactionLedgerEntryDescription = transactionLedgerEntryDescription;
    }
    /**
     * @return Returns the universityFiscalAccountingPeriod.
     */
    public String getUniversityFiscalAccountingPeriod() {
        return universityFiscalAccountingPeriod;
    }
    /**
     * @param universityFiscalAccountingPeriod The universityFiscalAccountingPeriod to set.
     */
    public void setUniversityFiscalAccountingPeriod(
            String universityFiscalAccountingPeriod) {
        this.universityFiscalAccountingPeriod = universityFiscalAccountingPeriod;
    }
    /**
     * @return Returns the universityFiscalYear.
     */
    public Integer getUniversityFiscalYear() {
        return universityFiscalYear;
    }
    /**
     * @param universityFiscalYear The universityFiscalYear to set.
     */
    public void setUniversityFiscalYear(Integer universityFiscalYear) {
        this.universityFiscalYear = universityFiscalYear;
    }

    /* (non-Javadoc)
     * @see org.kuali.bo.BusinessObject#validate()
     */
    public void validate(ValidationErrorList errors) throws ValidationErrorList, IllegalObjectStateException {
        validationNumber = errors.getNextValidationNumber();

        if (transactionEntrySequenceId==null) {
            throw new IllegalObjectStateException("Transaction Sequence Id is null");        }

        if (documentHeaderId==null)
            throw new IllegalObjectStateException("Document Header Id is null");

        if (versionNumber==null)
            throw new IllegalObjectStateException("Version number is null");

        if (chartOfAccountsCode==null || "".equals(chartOfAccountsCode))
            throw new IllegalObjectStateException("COA Code is null");

        if (objectCode==null || "".equals(objectCode))
            throw new IllegalObjectStateException("Object Code is null");

        if (balanceTypeCode==null || "".equals(balanceTypeCode))
            throw new IllegalObjectStateException("Balance Type Code is null");

        if (objectTypeCode==null || "".equals(objectTypeCode))
            throw new IllegalObjectStateException("Object Type Code is null");

        if (universityFiscalYear==null)
            throw new IllegalObjectStateException("Fiscal Year is null");

/*        if (universityFiscalAccountingPeriod==null || "".equals(universityFiscalAccountingPeriod))
            throw new IllegalObjectStateException("Fiscal Accounting Period is null");
TODO - Can't this be null?
*/
        if (transactionLedgerEntryAmount==null)
            throw new IllegalObjectStateException("Transaction Entry Amount is null");

        if (transactionLedgerEntryDescription==null || "".equals(transactionLedgerEntryDescription))
            throw new IllegalObjectStateException("Transaction Entry Description is null");

        if (debitOrCreditCode==null || "".equals(debitOrCreditCode))
            throw new IllegalObjectStateException("Debit or Credit is null");

        if (transactionDate==null)
            throw new IllegalObjectStateException("Transaction Date is null");

        if (documentTypeCode==null || "".equals(documentTypeCode))
            throw new IllegalObjectStateException("Document Type Code is null");

        //check to see if any errors were found and deal with them
        errors.throwErrors();
    }

    protected Object clone() {
        try {
            GeneralLedgerPendingEntry glpe = (GeneralLedgerPendingEntry)super.clone(); //clone the stack
//            s.items = (Object)items.clone(); //clone the array
            return glpe; // return the clone
        } catch (CloneNotSupportedException e) {
           //This shouldn't happen because Stack is Cloneable.
            throw new InternalError();
        }
    }
}
