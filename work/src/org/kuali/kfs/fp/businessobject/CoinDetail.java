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

import org.kuali.core.bo.BusinessObjectBase;
import org.kuali.core.util.KualiDecimalMoney;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class CoinDetail extends BusinessObjectBase {

    private String financialDocumentNumber;
    private String financialDocumentTypeCode;
    private String financialDocumentColumnTypeCode;
    private KualiDecimalMoney financialDocumentFiftyCentAmount;
    private KualiDecimalMoney financialDocumentTwentyFiveCentAmount;
    private KualiDecimalMoney financialDocumentTenCentAmount;
    private KualiDecimalMoney financialDocumentFiveCentAmount;
    private KualiDecimalMoney financialDocumentOneCentAmount;
    private KualiDecimalMoney financialDocumentOtherCentAmount;
    private KualiDecimalMoney financialDocumentHundredCentAmount;

    /**
     * Default constructor.
     */
    public CoinDetail() {

    }

    /**
     * Gets the financialDocumentNumber attribute.
     * 
     * @return - Returns the financialDocumentNumber
     * 
     */
    public String getFinancialDocumentNumber() {
        return financialDocumentNumber;
    }

    /**
     * Sets the financialDocumentNumber attribute.
     * 
     * @param - financialDocumentNumber The financialDocumentNumber to set.
     * 
     */
    public void setFinancialDocumentNumber(String financialDocumentNumber) {
        this.financialDocumentNumber = financialDocumentNumber;
    }


    /**
     * Gets the financialDocumentTypeCode attribute.
     * 
     * @return - Returns the financialDocumentTypeCode
     * 
     */
    public String getFinancialDocumentTypeCode() {
        return financialDocumentTypeCode;
    }

    /**
     * Sets the financialDocumentTypeCode attribute.
     * 
     * @param - financialDocumentTypeCode The financialDocumentTypeCode to set.
     * 
     */
    public void setFinancialDocumentTypeCode(String financialDocumentTypeCode) {
        this.financialDocumentTypeCode = financialDocumentTypeCode;
    }


    /**
     * Gets the financialDocumentColumnTypeCode attribute.
     * 
     * @return - Returns the financialDocumentColumnTypeCode
     * 
     */
    public String getFinancialDocumentColumnTypeCode() {
        return financialDocumentColumnTypeCode;
    }

    /**
     * Sets the financialDocumentColumnTypeCode attribute.
     * 
     * @param - financialDocumentColumnTypeCode The financialDocumentColumnTypeCode to set.
     * 
     */
    public void setFinancialDocumentColumnTypeCode(String financialDocumentColumnTypeCode) {
        this.financialDocumentColumnTypeCode = financialDocumentColumnTypeCode;
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
     * @see org.kuali.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("financialDocumentNumber", this.financialDocumentNumber);
        m.put("financialDocumentTypeCode", this.financialDocumentTypeCode);
        m.put("financialDocumentColumnTypeCode", this.financialDocumentColumnTypeCode);
        return m;
    }
}
