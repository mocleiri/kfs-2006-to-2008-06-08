/*
 * Copyright (c) 2004, 2005 The National Association of College and University 
 * Business Officers, Cornell University, Trustees of Indiana University, 
 * Michigan State University Board of Trustees, Trustees of San Joaquin Delta 
 * College, University of Hawai'i, The Arizona Board of Regents on behalf of the 
 * University of Arizona, and the r*smart group.
 * 
 * Licensed under the Educational Community License Version 1.0 (the "License"); 
 * By obtaining, using and/or copying this Original Work, you agree that you 
 * have read, understand, and will comply with the terms and conditions of the 
 * Educational Community License.
 * 
 * You may obtain a copy of the License at:
 * 
 * http://kualiproject.org/license.html
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,  DAMAGES OR OTHER 
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN 
 * THE SOFTWARE.
 */

package org.kuali.module.financial.bo;

import java.util.LinkedHashMap;

import org.apache.commons.lang.StringUtils;
import org.kuali.Constants;
import org.kuali.core.bo.BusinessObjectBase;
import org.kuali.core.util.KualiDecimalMoney;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class CashDrawer extends BusinessObjectBase {
    private String workgroupName;
    private String statusCode;

    private KualiDecimalMoney cashDrawerTotalAmount;

    private KualiDecimalMoney financialDocumentHundredDollarAmount;
    private KualiDecimalMoney financialDocumentFiftyDollarAmount;
    private KualiDecimalMoney financialDocumentTwentyDollarAmount;
    private KualiDecimalMoney financialDocumentTenDollarAmount;
    private KualiDecimalMoney financialDocumentFiveDollarAmount;
    private KualiDecimalMoney financialDocumentTwoDollarAmount;
    private KualiDecimalMoney financialDocumentOneDollarAmount;
    private KualiDecimalMoney financialDocumentOtherDollarAmount;

    private KualiDecimalMoney financialDocumentHundredCentAmount;
    private KualiDecimalMoney financialDocumentFiftyCentAmount;
    private KualiDecimalMoney financialDocumentTwentyFiveCentAmount;
    private KualiDecimalMoney financialDocumentTenCentAmount;
    private KualiDecimalMoney financialDocumentFiveCentAmount;
    private KualiDecimalMoney financialDocumentOneCentAmount;
    private KualiDecimalMoney financialDocumentOtherCentAmount;

    private KualiDecimalMoney financialDocumentMiscellaneousAdvanceAmount;

    private String referenceFinancialDocumentNumber;

    /**
     * Default constructor.
     */
    public CashDrawer() {

    }

    /**
     * This method returns true if the cash drawer is open.
     * 
     * @return boolean
     */
    public boolean isOpen() {
        return StringUtils.equals(Constants.CashDrawerConstants.STATUS_OPEN, statusCode);
    }

    /**
     * This method returns true if the cash drawer is closed.
     * 
     * @return boolean
     */
    public boolean isClosed() {
        return StringUtils.equals(Constants.CashDrawerConstants.STATUS_CLOSED, statusCode);
    }

    /**
     * This method returns true if the cash drawer is locked.
     * 
     * @return boolean
     */
    public boolean isLocked() {
        return StringUtils.equals(Constants.CashDrawerConstants.STATUS_LOCKED, statusCode);
    }


    /**
     * Gets the workgroupName attribute.
     * 
     * @return - Returns the workgroupName
     * 
     */
    public String getWorkgroupName() {
        return workgroupName;
    }

    /**
     * Sets the workgroupName attribute.
     * 
     * @param - workgroupName The workgroupName to set.
     * 
     */
    public void setWorkgroupName(String workgroupName) {
        this.workgroupName = workgroupName;
    }


    /**
     * Gets the statusCode attribute.
     * 
     * @return - Returns the statusCode
     * 
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the statusCode attribute.
     * 
     * @param - statusCode The statusCode to set.
     * 
     */
    public void setStatusCode(String financialDocumentOpenDepositCode) {
        this.statusCode = financialDocumentOpenDepositCode;
    }


    /**
     * Gets the cashDrawerTotalAmount attribute.
     * 
     * @return - Returns the cashDrawerTotalAmount
     * 
     */
    public KualiDecimalMoney getCashDrawerTotalAmount() {
        return cashDrawerTotalAmount;
    }

    /**
     * Sets the cashDrawerTotalAmount attribute.
     * 
     * @param - cashDrawerTotalAmount The cashDrawerTotalAmount to set.
     * 
     */
    public void setCashDrawerTotalAmount(KualiDecimalMoney cashDrawerTotalAmount) {
        this.cashDrawerTotalAmount = cashDrawerTotalAmount;
    }


    /**
     * Gets the financialDocumentHundredDollarAmount attribute.
     * 
     * @return - Returns the financialDocumentHundredDollarAmount
     * 
     */
    public KualiDecimalMoney getFinancialDocumentHundredDollarAmount() {
        return financialDocumentHundredDollarAmount;
    }

    /**
     * Sets the financialDocumentHundredDollarAmount attribute.
     * 
     * @param - financialDocumentHundredDollarAmount The financialDocumentHundredDollarAmount to set.
     * 
     */
    public void setFinancialDocumentHundredDollarAmount(KualiDecimalMoney financialDocumentHundredDollarAmount) {
        this.financialDocumentHundredDollarAmount = financialDocumentHundredDollarAmount;
    }


    /**
     * Gets the financialDocumentFiftyDollarAmount attribute.
     * 
     * @return - Returns the financialDocumentFiftyDollarAmount
     * 
     */
    public KualiDecimalMoney getFinancialDocumentFiftyDollarAmount() {
        return financialDocumentFiftyDollarAmount;
    }

    /**
     * Sets the financialDocumentFiftyDollarAmount attribute.
     * 
     * @param - financialDocumentFiftyDollarAmount The financialDocumentFiftyDollarAmount to set.
     * 
     */
    public void setFinancialDocumentFiftyDollarAmount(KualiDecimalMoney financialDocumentFiftyDollarAmount) {
        this.financialDocumentFiftyDollarAmount = financialDocumentFiftyDollarAmount;
    }


    /**
     * Gets the financialDocumentTwentyDollarAmount attribute.
     * 
     * @return - Returns the financialDocumentTwentyDollarAmount
     * 
     */
    public KualiDecimalMoney getFinancialDocumentTwentyDollarAmount() {
        return financialDocumentTwentyDollarAmount;
    }

    /**
     * Sets the financialDocumentTwentyDollarAmount attribute.
     * 
     * @param - financialDocumentTwentyDollarAmount The financialDocumentTwentyDollarAmount to set.
     * 
     */
    public void setFinancialDocumentTwentyDollarAmount(KualiDecimalMoney financialDocumentTwentyDollarAmount) {
        this.financialDocumentTwentyDollarAmount = financialDocumentTwentyDollarAmount;
    }


    /**
     * Gets the financialDocumentTenDollarAmount attribute.
     * 
     * @return - Returns the financialDocumentTenDollarAmount
     * 
     */
    public KualiDecimalMoney getFinancialDocumentTenDollarAmount() {
        return financialDocumentTenDollarAmount;
    }

    /**
     * Sets the financialDocumentTenDollarAmount attribute.
     * 
     * @param - financialDocumentTenDollarAmount The financialDocumentTenDollarAmount to set.
     * 
     */
    public void setFinancialDocumentTenDollarAmount(KualiDecimalMoney financialDocumentTenDollarAmount) {
        this.financialDocumentTenDollarAmount = financialDocumentTenDollarAmount;
    }


    /**
     * Gets the financialDocumentFiveDollarAmount attribute.
     * 
     * @return - Returns the financialDocumentFiveDollarAmount
     * 
     */
    public KualiDecimalMoney getFinancialDocumentFiveDollarAmount() {
        return financialDocumentFiveDollarAmount;
    }

    /**
     * Sets the financialDocumentFiveDollarAmount attribute.
     * 
     * @param - financialDocumentFiveDollarAmount The financialDocumentFiveDollarAmount to set.
     * 
     */
    public void setFinancialDocumentFiveDollarAmount(KualiDecimalMoney financialDocumentFiveDollarAmount) {
        this.financialDocumentFiveDollarAmount = financialDocumentFiveDollarAmount;
    }


    /**
     * Gets the financialDocumentTwoDollarAmount attribute.
     * 
     * @return - Returns the financialDocumentTwoDollarAmount
     * 
     */
    public KualiDecimalMoney getFinancialDocumentTwoDollarAmount() {
        return financialDocumentTwoDollarAmount;
    }

    /**
     * Sets the financialDocumentTwoDollarAmount attribute.
     * 
     * @param - financialDocumentTwoDollarAmount The financialDocumentTwoDollarAmount to set.
     * 
     */
    public void setFinancialDocumentTwoDollarAmount(KualiDecimalMoney financialDocumentTwoDollarAmount) {
        this.financialDocumentTwoDollarAmount = financialDocumentTwoDollarAmount;
    }


    /**
     * Gets the financialDocumentOneDollarAmount attribute.
     * 
     * @return - Returns the financialDocumentOneDollarAmount
     * 
     */
    public KualiDecimalMoney getFinancialDocumentOneDollarAmount() {
        return financialDocumentOneDollarAmount;
    }

    /**
     * Sets the financialDocumentOneDollarAmount attribute.
     * 
     * @param - financialDocumentOneDollarAmount The financialDocumentOneDollarAmount to set.
     * 
     */
    public void setFinancialDocumentOneDollarAmount(KualiDecimalMoney financialDocumentOneDollarAmount) {
        this.financialDocumentOneDollarAmount = financialDocumentOneDollarAmount;
    }


    /**
     * Gets the financialDocumentOtherDollarAmount attribute.
     * 
     * @return - Returns the financialDocumentOtherDollarAmount
     * 
     */
    public KualiDecimalMoney getFinancialDocumentOtherDollarAmount() {
        return financialDocumentOtherDollarAmount;
    }

    /**
     * Sets the financialDocumentOtherDollarAmount attribute.
     * 
     * @param - financialDocumentOtherDollarAmount The financialDocumentOtherDollarAmount to set.
     * 
     */
    public void setFinancialDocumentOtherDollarAmount(KualiDecimalMoney financialDocumentOtherDollarAmount) {
        this.financialDocumentOtherDollarAmount = financialDocumentOtherDollarAmount;
    }


    /**
     * Gets the financialDocumentFiftyCentAmount attribute.
     * 
     * @return - Returns the financialDocumentFiftyCentAmount
     * 
     */
    public KualiDecimalMoney getFinancialDocumentFiftyCentAmount() {
        return financialDocumentFiftyCentAmount;
    }

    /**
     * Sets the financialDocumentFiftyCentAmount attribute.
     * 
     * @param - financialDocumentFiftyCentAmount The financialDocumentFiftyCentAmount to set.
     * 
     */
    public void setFinancialDocumentFiftyCentAmount(KualiDecimalMoney financialDocumentFiftyCentAmount) {
        this.financialDocumentFiftyCentAmount = financialDocumentFiftyCentAmount;
    }


    /**
     * Gets the financialDocumentTwentyFiveCentAmount attribute.
     * 
     * @return - Returns the financialDocumentTwentyFiveCentAmount
     * 
     */
    public KualiDecimalMoney getFinancialDocumentTwentyFiveCentAmount() {
        return financialDocumentTwentyFiveCentAmount;
    }

    /**
     * Sets the financialDocumentTwentyFiveCentAmount attribute.
     * 
     * @param - financialDocumentTwentyFiveCentAmount The financialDocumentTwentyFiveCentAmount to set.
     * 
     */
    public void setFinancialDocumentTwentyFiveCentAmount(KualiDecimalMoney financialDocumentTwentyFiveCentAmount) {
        this.financialDocumentTwentyFiveCentAmount = financialDocumentTwentyFiveCentAmount;
    }


    /**
     * Gets the financialDocumentTenCentAmount attribute.
     * 
     * @return - Returns the financialDocumentTenCentAmount
     * 
     */
    public KualiDecimalMoney getFinancialDocumentTenCentAmount() {
        return financialDocumentTenCentAmount;
    }

    /**
     * Sets the financialDocumentTenCentAmount attribute.
     * 
     * @param - financialDocumentTenCentAmount The financialDocumentTenCentAmount to set.
     * 
     */
    public void setFinancialDocumentTenCentAmount(KualiDecimalMoney financialDocumentTenCentAmount) {
        this.financialDocumentTenCentAmount = financialDocumentTenCentAmount;
    }


    /**
     * Gets the financialDocumentFiveCentAmount attribute.
     * 
     * @return - Returns the financialDocumentFiveCentAmount
     * 
     */
    public KualiDecimalMoney getFinancialDocumentFiveCentAmount() {
        return financialDocumentFiveCentAmount;
    }

    /**
     * Sets the financialDocumentFiveCentAmount attribute.
     * 
     * @param - financialDocumentFiveCentAmount The financialDocumentFiveCentAmount to set.
     * 
     */
    public void setFinancialDocumentFiveCentAmount(KualiDecimalMoney financialDocumentFiveCentAmount) {
        this.financialDocumentFiveCentAmount = financialDocumentFiveCentAmount;
    }


    /**
     * Gets the financialDocumentOneCentAmount attribute.
     * 
     * @return - Returns the financialDocumentOneCentAmount
     * 
     */
    public KualiDecimalMoney getFinancialDocumentOneCentAmount() {
        return financialDocumentOneCentAmount;
    }

    /**
     * Sets the financialDocumentOneCentAmount attribute.
     * 
     * @param - financialDocumentOneCentAmount The financialDocumentOneCentAmount to set.
     * 
     */
    public void setFinancialDocumentOneCentAmount(KualiDecimalMoney financialDocumentOneCentAmount) {
        this.financialDocumentOneCentAmount = financialDocumentOneCentAmount;
    }


    /**
     * Gets the financialDocumentOtherCentAmount attribute.
     * 
     * @return - Returns the financialDocumentOtherCentAmount
     * 
     */
    public KualiDecimalMoney getFinancialDocumentOtherCentAmount() {
        return financialDocumentOtherCentAmount;
    }

    /**
     * Sets the financialDocumentOtherCentAmount attribute.
     * 
     * @param - financialDocumentOtherCentAmount The financialDocumentOtherCentAmount to set.
     * 
     */
    public void setFinancialDocumentOtherCentAmount(KualiDecimalMoney financialDocumentOtherCentAmount) {
        this.financialDocumentOtherCentAmount = financialDocumentOtherCentAmount;
    }


    /**
     * Gets the financialDocumentHundredCentAmount attribute.
     * 
     * @return - Returns the financialDocumentHundredCentAmount
     * 
     */
    public KualiDecimalMoney getFinancialDocumentHundredCentAmount() {
        return financialDocumentHundredCentAmount;
    }

    /**
     * Sets the financialDocumentHundredCentAmount attribute.
     * 
     * @param - financialDocumentHundredCentAmount The financialDocumentHundredCentAmount to set.
     * 
     */
    public void setFinancialDocumentHundredCentAmount(KualiDecimalMoney financialDocumentHundredCentAmount) {
        this.financialDocumentHundredCentAmount = financialDocumentHundredCentAmount;
    }


    /**
     * Gets the financialDocumentMiscellaneousAdvanceAmount attribute.
     * 
     * @return - Returns the financialDocumentMiscellaneousAdvanceAmount
     * 
     */
    public KualiDecimalMoney getFinancialDocumentMiscellaneousAdvanceAmount() {
        return financialDocumentMiscellaneousAdvanceAmount;
    }

    /**
     * Sets the financialDocumentMiscellaneousAdvanceAmount attribute.
     * 
     * @param - financialDocumentMiscellaneousAdvanceAmount The financialDocumentMiscellaneousAdvanceAmount to set.
     * 
     */
    public void setFinancialDocumentMiscellaneousAdvanceAmount(KualiDecimalMoney financialDocumentMiscellaneousAdvanceAmount) {
        this.financialDocumentMiscellaneousAdvanceAmount = financialDocumentMiscellaneousAdvanceAmount;
    }


    /**
     * Gets the referenceFinancialDocumentNumber attribute.
     * 
     * @return - Returns the referenceFinancialDocumentNumber
     * 
     */
    public String getReferenceFinancialDocumentNumber() {
        return referenceFinancialDocumentNumber;
    }

    /**
     * Sets the referenceFinancialDocumentNumber attribute.
     * 
     * @param - referenceFinancialDocumentNumber The referenceFinancialDocumentNumber to set.
     * 
     */
    public void setReferenceFinancialDocumentNumber(String referenceFinancialDocumentNumber) {
        this.referenceFinancialDocumentNumber = referenceFinancialDocumentNumber;
    }

    /**
     * @see org.kuali.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("workgroupName", this.workgroupName);
        return m;
    }
}
