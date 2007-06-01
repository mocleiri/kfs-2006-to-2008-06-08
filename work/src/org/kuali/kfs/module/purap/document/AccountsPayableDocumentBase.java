/*
 * Copyright 2006-2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.module.purap.document;

import java.sql.Date;

import org.kuali.core.bo.Campus;


/**
 * Accounts Payable Document Base
 * 
 */
public abstract class AccountsPayableDocumentBase extends PurchasingAccountsPayableDocumentBase implements AccountsPayableDocument {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(AccountsPayableDocumentBase.class);
    
    // SHARED FIELDS BETWEEN PAYMENT REQUEST AND CREDIT MEMO
    private Date accountsPayableApprovalDate;
    private String accountsPayableHoldIdentifier;
    private String accountsPayableProcessorIdentifier;
    private boolean holdIndicator;
    private Date extractedDate;
    private Integer purchaseOrderIdentifier;
    private String processingCampusCode;
    private String noteLine1Text;
    private String noteLine2Text;
    private String noteLine3Text;

    // REFERENCE OBJECTS
    private Campus processingCampus;
    private PurchaseOrderDocument purchaseOrderDocument;

    public AccountsPayableDocumentBase() {
        super();
    }
    
    /**
     * Retrieve all references common to AccountsPayable
     */
    @Override
    public void refreshAllReferences() {
        super.refreshAllReferences();
        this.refreshReferenceObject("processingCampus");
        this.refreshReferenceObject("purchaseOrderDocument");
    }

    public boolean requiresAccountsPayableReview() {
        // TODO delyea - IMPLEMENT THIS
        return true;
    }
    
    // GETTERS AND SETTERS    
    public Integer getPurchaseOrderIdentifier() {
        return purchaseOrderIdentifier;
    }

    public void setPurchaseOrderIdentifier(Integer purchaseOrderIdentifier) {
        this.purchaseOrderIdentifier = purchaseOrderIdentifier;
    }

    public String getAccountsPayableProcessorIdentifier() { 
        return accountsPayableProcessorIdentifier;
    }

    public void setAccountsPayableProcessorIdentifier(String accountsPayableProcessorIdentifier) {
        this.accountsPayableProcessorIdentifier = accountsPayableProcessorIdentifier;
    }

    public String getAccountsPayableHoldIdentifier() { 
        return accountsPayableHoldIdentifier;
    }

    public void setAccountsPayableHoldIdentifier(String accountsPayableHoldIdentifier) {
        this.accountsPayableHoldIdentifier = accountsPayableHoldIdentifier;
    }

    public String getProcessingCampusCode() { 
        return processingCampusCode;
    }

    public void setProcessingCampusCode(String processingCampusCode) {
        this.processingCampusCode = processingCampusCode;
    }

    public Date getAccountsPayableApprovalDate() { 
        return accountsPayableApprovalDate;
    }

    public void setAccountsPayableApprovalDate(Date accountsPayableApprovalDate) {
        this.accountsPayableApprovalDate = accountsPayableApprovalDate;
    }

    public Date getExtractedDate() {
        return extractedDate;
    }

    public void setExtractedDate(Date extractedDate) {
        this.extractedDate = extractedDate;
    }

    public boolean isHoldIndicator() {
        return holdIndicator;
    }

    public void setHoldIndicator(boolean holdIndicator) {
        this.holdIndicator = holdIndicator;
    }

    public String getNoteLine1Text() {
        return noteLine1Text;
    }

    public void setNoteLine1Text(String noteLine1Text) {
        this.noteLine1Text = noteLine1Text;
    }

    public String getNoteLine2Text() {
        return noteLine2Text;
    }

    public void setNoteLine2Text(String noteLine2Text) {
        this.noteLine2Text = noteLine2Text;
    }

    public String getNoteLine3Text() {
        return noteLine3Text;
    }

    public void setNoteLine3Text(String noteLine3Text) {
        this.noteLine3Text = noteLine3Text;
    }

    public Campus getProcessingCampus() { 
        return processingCampus;
    }

    public PurchaseOrderDocument getPurchaseOrderDocument() {
        return purchaseOrderDocument;
    }

}
