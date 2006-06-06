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
package org.kuali.module.gl.util;

import org.kuali.core.util.KualiDecimal;

/**
 * @author Anthony Potts
 *
 */
public class LedgerEntry {
    
    // FIXME: public data members?  
    public String balanceType;
    public String originCode;
    public Integer fiscalYear;
    public String period;
    public int recordCount;
    public KualiDecimal debitAmount;
    public int debitCount;
    public KualiDecimal creditAmount;
    public int creditCount;
    public KualiDecimal noDCAmount;
    public int noDCCount;
    
    /**
     * Constructs a LedgerEntry.java.
     */
    public LedgerEntry() {
        this(new Integer(0), KualiDecimal.ZERO, KualiDecimal.ZERO, KualiDecimal.ZERO);
    }

    /**
     * Constructs a LedgerEntry.java.
     * @param fiscalYear
     * @param creditCount
     * @param noDCAmount
     * @param noDCCount
     */
    public LedgerEntry(Integer fiscalYear, KualiDecimal debitAmount, KualiDecimal creditAmount, KualiDecimal noDCAmount) {
        this.fiscalYear = fiscalYear;
        this.debitAmount = debitAmount;
        this.creditAmount = creditAmount;
        this.noDCAmount = noDCAmount;
    }

    /**
     * Gets the balanceType attribute. 
     * @return Returns the balanceType.
     */
    public String getBalanceType() {
        return balanceType;
    }

    /**
     * Sets the balanceType attribute value.
     * @param balanceType The balanceType to set.
     */
    public void setBalanceType(String balanceType) {
        this.balanceType = balanceType;
    }

    /**
     * Gets the creditAmount attribute. 
     * @return Returns the creditAmount.
     */
    public KualiDecimal getCreditAmount() {
        return creditAmount;
    }

    /**
     * Sets the creditAmount attribute value.
     * @param creditAmount The creditAmount to set.
     */
    public void setCreditAmount(KualiDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    /**
     * Gets the creditCount attribute. 
     * @return Returns the creditCount.
     */
    public int getCreditCount() {
        return creditCount;
    }

    /**
     * Sets the creditCount attribute value.
     * @param creditCount The creditCount to set.
     */
    public void setCreditCount(int creditCount) {
        this.creditCount = creditCount;
    }

    /**
     * Gets the debitAmount attribute. 
     * @return Returns the debitAmount.
     */
    public KualiDecimal getDebitAmount() {
        return debitAmount;
    }

    /**
     * Sets the debitAmount attribute value.
     * @param debitAmount The debitAmount to set.
     */
    public void setDebitAmount(KualiDecimal debitAmount) {
        this.debitAmount = debitAmount;
    }

    /**
     * Gets the debitCount attribute. 
     * @return Returns the debitCount.
     */
    public int getDebitCount() {
        return debitCount;
    }

    /**
     * Sets the debitCount attribute value.
     * @param debitCount The debitCount to set.
     */
    public void setDebitCount(int debitCount) {
        this.debitCount = debitCount;
    }

    /**
     * Gets the fiscalYear attribute. 
     * @return Returns the fiscalYear.
     */
    public Integer getFiscalYear() {
        return fiscalYear;
    }

    /**
     * Sets the fiscalYear attribute value.
     * @param fiscalYear The fiscalYear to set.
     */
    public void setFiscalYear(Integer fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    /**
     * Gets the noDCAmount attribute. 
     * @return Returns the noDCAmount.
     */
    public KualiDecimal getNoDCAmount() {
        return noDCAmount;
    }

    /**
     * Sets the noDCAmount attribute value.
     * @param noDCAmount The noDCAmount to set.
     */
    public void setNoDCAmount(KualiDecimal noDCAmount) {
        this.noDCAmount = noDCAmount;
    }

    /**
     * Gets the noDCCount attribute. 
     * @return Returns the noDCCount.
     */
    public int getNoDCCount() {
        return noDCCount;
    }

    /**
     * Sets the noDCCount attribute value.
     * @param noDCCount The noDCCount to set.
     */
    public void setNoDCCount(int noDCCount) {
        this.noDCCount = noDCCount;
    }

    /**
     * Gets the originCode attribute. 
     * @return Returns the originCode.
     */
    public String getOriginCode() {
        return originCode;
    }

    /**
     * Sets the originCode attribute value.
     * @param originCode The originCode to set.
     */
    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    /**
     * Gets the period attribute. 
     * @return Returns the period.
     */
    public String getPeriod() {
        return period;
    }

    /**
     * Sets the period attribute value.
     * @param period The period to set.
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * Gets the recordCount attribute. 
     * @return Returns the recordCount.
     */
    public int getRecordCount() {
        return recordCount;
    }

    /**
     * Sets the recordCount attribute value.
     * @param recordCount The recordCount to set.
     */
    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }
    
    /**
     * @see java.lang.Object#toString()
     */
    public String toString(){
        StringBuffer ledgerEntryDescription = new StringBuffer();
        
        ledgerEntryDescription.append(fiscalYear + "\t");
        ledgerEntryDescription.append(period + "\t");   
        ledgerEntryDescription.append(balanceType + "\t");
        ledgerEntryDescription.append(originCode + "\t");       
        ledgerEntryDescription.append(recordCount + "\t");
        
        ledgerEntryDescription.append(debitAmount + "\t");
        ledgerEntryDescription.append(debitCount + "\t");
        
        ledgerEntryDescription.append(creditAmount + "\t");
        ledgerEntryDescription.append(creditCount + "\t");
        
        ledgerEntryDescription.append(noDCAmount + "\t");
        ledgerEntryDescription.append(noDCCount + "\t");
        
        return ledgerEntryDescription.toString();
    }
}
