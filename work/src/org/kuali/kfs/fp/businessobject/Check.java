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
package org.kuali.module.financial.bo;

import java.sql.Timestamp;

import org.kuali.core.bo.BusinessObject;
import org.kuali.core.util.KualiDecimal;


public interface Check extends BusinessObject {

    /**
     * Gets the checkDate attribute.
     * 
     * @return Returns the checkDate.
     */
    public Timestamp getCheckDate();

    /**
     * Sets the checkDate attribute value.
     * 
     * @param checkDate The checkDate to set.
     */
    public void setCheckDate(Timestamp checkDate);

    /**
     * Gets the checkNumber attribute.
     * 
     * @return Returns the checkNumber.
     */
    public String getCheckNumber();

    /**
     * Sets the checkNumber attribute value.
     * 
     * @param checkNumber The checkNumber to set.
     */
    public void setCheckNumber(String checkNumber);

    /**
     * Gets the description attribute.
     * 
     * @return Returns the description.
     */
    public String getDescription();

    /**
     * Sets the description attribute value.
     * 
     * @param description The description to set.
     */
    public void setDescription(String description);


    /**
     * Gets the sequenceId attribute.
     * 
     * @return Returns the sequenceId.
     */
    public Integer getNextCheckLineNumber();

    /**
     * Sets the sequenceId attribute value.
     * 
     * @param sequenceId The sequenceId to set.
     */
    public void setNextCheckLineNumber(Integer sequenceId);


    /**
     * Gets the amount attribute.
     * 
     * @return Returns the amount.
     */
    public KualiDecimal getAmount();

    /**
     * Sets the amount attribute value.
     * 
     * @param amount The amount to set.
     */
    public void setAmount(KualiDecimal amount);

    /**
     * Gets the financialDocumentNumber attribute.
     * 
     * @return Returns the financialDocumentNumber.
     */
    public String getFinancialDocumentNumber();

    /**
     * Sets the financialDocumentNumber attribute value.
     * 
     * @param financialDocumentNumber The financialDocumentNumber to set.
     */
    public void setFinancialDocumentNumber(String financialDocumentNumber);

}