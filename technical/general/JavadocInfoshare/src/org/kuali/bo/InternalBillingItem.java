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

import java.sql.Timestamp;

import org.kuali.util.KualiDecimal;
import org.kuali.validation.ValidationErrorList;

/**
 * Represents Internal Billing Item from the FIS table FP_INT_BILL_INC_T
 * 
 * @author tdurkin
 * 
 */
public class InternalBillingItem extends BusinessObjectBase {
    
    private Long documentHeaderId;
    
    private Integer itemSequenceId;
    
    private String itemStockNumber;
    
    private String itemStockDescription;

    private Timestamp itemServiceDate;
    
    private Integer itemQuantity;

    private KualiDecimal itemUnitAmount;

    private String unitOfMeasureCode;

    public InternalBillingItem() {
        setItemUnitAmount(new KualiDecimal(0));
        setItemQuantity(new Integer(1));
    }

    public Integer getItemQuantity() {
        return this.itemQuantity;
    }

    public void setItemQuantity(Integer param) {
        this.itemQuantity = param;
    }

    public Timestamp getItemServiceDate() {
        return this.itemServiceDate;
    }

    public void setItemServiceDate(Timestamp itemServiceDate) {
        this.itemServiceDate = itemServiceDate;
    }
    
    public String getItemStockDescription() {
        return this.itemStockDescription;
    }

    public void setItemStockDescription(String param) {
        this.itemStockDescription = param;
    }

    public String getItemStockNumber() {
        return this.itemStockNumber;
    }

    public void setItemStockNumber(String param) {
        this.itemStockNumber = param;
    }

    public KualiDecimal getItemUnitAmount() {
        return this.itemUnitAmount;
    }

    public void setItemUnitAmount(KualiDecimal param) {
        this.itemUnitAmount = param;
    }

    public String getUnitOfMeasureCode() {
        return this.unitOfMeasureCode;
    }

    public void setUnitOfMeasureCode(String param) {
        this.unitOfMeasureCode = param;
    }

    public Integer getItemSequenceId() {
        return this.itemSequenceId;
    }

    public void setItemSequenceId(Integer param) {
        this.itemSequenceId = param;
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
    
    /*
     * 
     */
    public KualiDecimal getTotal() {
        KualiDecimal total = new KualiDecimal(itemQuantity.toString());
        return total.multiply(itemUnitAmount);
    }

    /* (non-Javadoc)
     * @see org.kuali.bo.BusinessObject#validate()
     */
    public void validate(ValidationErrorList errors) throws ValidationErrorList {
        // TODO Implement validation based on existing FIS business rules
        
    }
}