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

import org.kuali.Constants;

/**
 * This helper class works in conjunction with the DepositWizardForm to help build the UI for the Deposit Wizard.
 * 
 * 
 */
public class DepositWizardHelper {
    private String selectedValue;
    private Timestamp cashReceiptCreateDate;

    /**
     * Constructs a JournalVoucherAccountingLineHelper.java.
     */
    public DepositWizardHelper() {
        selectedValue = Constants.ParameterValues.NO;
        cashReceiptCreateDate = new Timestamp(0);
    }

    /**
     * Gets the selectedValue attribute.
     * 
     * @return Returns the selectedValue.
     */
    public String getSelectedValue() {
        return selectedValue;
    }

    /**
     * Sets the selectedValue attribute value.
     * 
     * @param selectedValue The selectedValue to set.
     */
    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }

    /**
     * @return current value of cashReceiptCreateDate.
     */
    public Timestamp getCashReceiptCreateDate() {
        return cashReceiptCreateDate;
    }

    /**
     * Sets the cashReceiptCreateDate attribute value.
     * 
     * @param cashReceiptCreateDate The cashReceiptCreateDate to set.
     */
    public void setCashReceiptCreateDate(Timestamp cashReceiptCreateDate) {
        this.cashReceiptCreateDate = cashReceiptCreateDate;
    }
}
