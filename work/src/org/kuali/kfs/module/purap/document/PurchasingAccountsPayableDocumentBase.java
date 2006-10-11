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
package org.kuali.module.purap.document;

import java.util.List;

import org.kuali.Constants;
import org.kuali.core.document.TransactionalDocumentBase;
import org.kuali.core.util.KualiDecimal;
import org.kuali.module.purap.bo.Status;
import org.kuali.module.purap.bo.StatusHistory;

/**
 * Purchasing-Accounts Payable Document Base
 * 
 * @author PURAP (kualidev@oncourse.iu.edu)
 */
public abstract class PurchasingAccountsPayableDocumentBase extends TransactionalDocumentBase implements PurchasingAccountsPayableDocument {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PurchasingAccountsPayableDocumentBase.class);

    // SHARED FIELDS BETWEEN REQUISITION, PURCHASE ORDER, PAYMENT REQUEST, AND CREDIT MEMO
    private Integer identifier;
    private String statusCode;
    private Integer vendorHeaderGeneratedIdentifier;
    private Integer vendorDetailAssignedIdentifier;
    private String vendorCustomerNumber;

    // COMMON ELEMENTS
    private List statusHistory;

    // REFERENCE OBJECTS
    private Status status;

    // OVERRIDEN METHODS
    @Override
    public KualiDecimal getTotalDollarAmount() {
        return Constants.ZERO;
    }

    /**
     * Retrieve all references common to purchasing and ap
     */
    public void refreshAllReferences() {
        this.refreshReferenceObject("status");
    }

    // GETTERS AND SETTERS
    /**
     * Gets the vendorHeaderGeneratedIdentifier attribute.
     * 
     * @return - Returns the vendorHeaderGeneratedIdentifier
     * 
     */
    public Integer getVendorHeaderGeneratedIdentifier() { 
        return vendorHeaderGeneratedIdentifier;
    }

    /**
     * Sets the vendorHeaderGeneratedIdentifier attribute.
     * 
     * @param - vendorHeaderGeneratedIdentifier The vendorHeaderGeneratedIdentifier to set.
     * 
     */
    public void setVendorHeaderGeneratedIdentifier(Integer vendorHeaderGeneratedIdentifier) {
        this.vendorHeaderGeneratedIdentifier = vendorHeaderGeneratedIdentifier;
    }


    /**
     * Gets the vendorDetailAssignedIdentifier attribute.
     * 
     * @return - Returns the vendorDetailAssignedIdentifier
     * 
     */
    public Integer getVendorDetailAssignedIdentifier() { 
        return vendorDetailAssignedIdentifier;
    }

    /**
     * Sets the vendorDetailAssignedIdentifier attribute.
     * 
     * @param - vendorDetailAssignedIdentifier The vendorDetailAssignedIdentifier to set.
     * 
     */
    public void setVendorDetailAssignedIdentifier(Integer vendorDetailAssignedIdentifier) {
        this.vendorDetailAssignedIdentifier = vendorDetailAssignedIdentifier;
    }

    /**
     * Gets the vendorCustomerNumber attribute.
     * 
     * @return - Returns the vendorCustomerNumber
     * 
     */
    public String getVendorCustomerNumber() { 
        return vendorCustomerNumber;
    }

    /**
     * Sets the vendorCustomerNumber attribute.
     * 
     * @param - vendorCustomerNumber The vendorCustomerNumber to set.
     * 
     */
    public void setVendorCustomerNumber(String vendorCustomerNumber) {
        this.vendorCustomerNumber = vendorCustomerNumber;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List getStatusHistory() {
        return statusHistory;
    }

    public void setStatusHistory(List statusHistory) {
        this.statusHistory = statusHistory;
    }

}
