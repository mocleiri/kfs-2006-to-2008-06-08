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

import org.apache.commons.lang.ArrayUtils;
import org.kuali.Constants;
import org.kuali.core.util.KualiDecimal;

/**
 * This class...
 */
public class PosterOutputSummaryEntry implements Comparable{
    private Integer universityFiscalYear;
    private String fiscalPeriodCode;
    private String balanceTypeCode;
    private String fundGroup;
    private String objectTypeCode;
    private KualiDecimal creditAmount;
    private KualiDecimal debitAmount;
    private KualiDecimal budgetAmount;
    private KualiDecimal netAmount;

    /**
     * Constructs a PosterOutputSummaryEntry.java.
     */
    public PosterOutputSummaryEntry() {
        creditAmount = KualiDecimal.ZERO;
        debitAmount = KualiDecimal.ZERO;
        budgetAmount = KualiDecimal.ZERO;
        netAmount = KualiDecimal.ZERO;
    }

    /**
     * Gets the balanceTypeCode attribute.
     * 
     * @return Returns the balanceTypeCode.
     */
    public String getBalanceTypeCode() {
        return balanceTypeCode;
    }

    /**
     * Sets the balanceTypeCode attribute value.
     * 
     * @param balanceTypeCode The balanceTypeCode to set.
     */
    public void setBalanceTypeCode(String balanceTypeCode) {
        this.balanceTypeCode = balanceTypeCode;
    }

    /**
     * Gets the budgetAmount attribute.
     * 
     * @return Returns the budgetAmount.
     */
    public KualiDecimal getBudgetAmount() {
        return budgetAmount;
    }

    /**
     * Sets the budgetAmount attribute value.
     * 
     * @param budgetAmount The budgetAmount to set.
     */
    public void setBudgetAmount(KualiDecimal budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    /**
     * Gets the creditAmount attribute.
     * 
     * @return Returns the creditAmount.
     */
    public KualiDecimal getCreditAmount() {
        return creditAmount;
    }

    /**
     * Sets the creditAmount attribute value.
     * 
     * @param creditAmount The creditAmount to set.
     */
    public void setCreditAmount(KualiDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    /**
     * Gets the deditAmount attribute.
     * 
     * @return Returns the deditAmount.
     */
    public KualiDecimal getDebitAmount() {
        return debitAmount;
    }

    /**
     * Sets the deditAmount attribute value.
     * 
     * @param deditAmount The deditAmount to set.
     */
    public void setDebitAmount(KualiDecimal debitAmount) {
        this.debitAmount = debitAmount;
    }

    /**
     * Gets the fundGroup attribute.
     * 
     * @return Returns the fundGroup.
     */
    public String getFundGroup() {
        return fundGroup;
    }

    /**
     * Sets the fundGroup attribute value.
     * 
     * @param fundGroup The fundGroup to set.
     */
    public void setFundGroup(String fundGroup) {
        this.fundGroup = fundGroup;
    }

    /**
     * Gets the objectTypeCode attribute.
     * 
     * @return Returns the objectTypeCode.
     */
    public String getObjectTypeCode() {
        return objectTypeCode;
    }

    /**
     * Sets the objectTypeCode attribute value.
     * 
     * @param objectTypeCode The objectTypeCode to set.
     */
    public void setObjectTypeCode(String objectTypeCode) {
        this.objectTypeCode = objectTypeCode;
    }

    /**
     * Gets the universityFiscalYear attribute.
     * 
     * @return Returns the universityFiscalYear.
     */
    public Integer getUniversityFiscalYear() {
        return universityFiscalYear;
    }

    /**
     * Sets the universityFiscalYear attribute value.
     * 
     * @param universityFiscalYear The universityFiscalYear to set.
     */
    public void setUniversityFiscalYear(Integer universityFiscalYear) {
        this.universityFiscalYear = universityFiscalYear;
    }

    /**
     * Gets the netAmount attribute.
     * 
     * @return Returns the netAmount.
     */
    public KualiDecimal getNetAmount() {
        return netAmount;
    }

    /**
     * Sets the netAmount attribute value.
     * 
     * @param netAmount The netAmount to set.
     */
    public void setNetAmount(KualiDecimal netAmount) {
        this.netAmount = netAmount;
    }

    /**
     * Calculate the net amount
     */
    public void calculateNetAmount() {
        setNetAmount(creditAmount.subtract(debitAmount));
    }

    /**
     * add the amounts of two summary entries
     */
    public void add(PosterOutputSummaryEntry posterInputSummaryEntry) {
        // calculate the credit amount
        setCreditAmount(creditAmount.add(posterInputSummaryEntry.getCreditAmount()));

        // calculate the debit amount
        setDebitAmount(debitAmount.add(posterInputSummaryEntry.getDebitAmount()));

        // calculate the budget amount
        setBudgetAmount(budgetAmount.add(posterInputSummaryEntry.getBudgetAmount()));

        // calculate the net amount
        setNetAmount(creditAmount.subtract(debitAmount));
    }

    /**
     * add the amounts of two summary entries
     */
    public void setAmount(String debitCreditCode, String objectTypeCode, KualiDecimal amount) {
        String[] objectTypeCodeListCredit = new String[] { "AS", "EE", "ES", "EX", "TE" };
        String[] objectTypeCodeListDebit = new String[] { "CH", "FB", "TI", "IC", "IN", "LI" };

        if (Constants.GL_CREDIT_CODE.equals(debitCreditCode)) {
            boolean isObjectTypeCodeInList = ArrayUtils.contains(objectTypeCodeListCredit, objectTypeCode);
            if (isObjectTypeCodeInList) {
                setDebitAmount(amount);
            }
            else {
                setCreditAmount(amount);
            }
        }
        else if (Constants.GL_DEBIT_CODE.equals(debitCreditCode)) {
            boolean isObjectTypeCodeInList = ArrayUtils.contains(objectTypeCodeListDebit, objectTypeCode);
            if (isObjectTypeCodeInList) {
                setCreditAmount(amount);
            }
            else {
                setDebitAmount(amount);
            }
        }
        else {
            setBudgetAmount(amount);
        }

        // calculate the net amount
        setNetAmount(creditAmount.subtract(debitAmount));
    }

    /**
     * Gets the fiscalPeriodCode attribute. 
     * @return Returns the fiscalPeriodCode.
     */
    public String getFiscalPeriodCode() {
        return fiscalPeriodCode;
    }

    /**
     * Sets the fiscalPeriodCode attribute value.
     * @param fiscalPeriodCode The fiscalPeriodCode to set.
     */
    public void setFiscalPeriodCode(String fiscalPeriodCode) {
        this.fiscalPeriodCode = fiscalPeriodCode;
    }
    
    public String toString(){
       String posterOutputSummaryEntry = "";
       posterOutputSummaryEntry += "[UniversityFiscalYear: " + this.getUniversityFiscalYear();
       posterOutputSummaryEntry += ", FiscalPeriodCode: " + this.getFiscalPeriodCode();
       posterOutputSummaryEntry += ", BalanceTypeCode:" + this.getBalanceTypeCode();
       posterOutputSummaryEntry += ", FundGroup: " + this.getFundGroup();
       posterOutputSummaryEntry += ", ObjectTypeCode: " + this.getObjectTypeCode();
       posterOutputSummaryEntry += ", CreditAmount: " + this.getCreditAmount();
       posterOutputSummaryEntry += ", DebitAmount: " + this.getDebitAmount();
       posterOutputSummaryEntry += ", BudgetAmount: " + this.getBudgetAmount();
       posterOutputSummaryEntry += ", NetAmount: " + this.getNetAmount();
       posterOutputSummaryEntry += "]";
        
        return posterOutputSummaryEntry;
    }

    public int compareTo(Object anotherPosterOutputSummaryEntry) {
        int comparisonResult = 0;
        
        if (!(anotherPosterOutputSummaryEntry instanceof PosterOutputSummaryEntry)){
            throw new ClassCastException("A PosterOutputSummaryEntry object expected.");
        }
        
        if(anotherPosterOutputSummaryEntry == null){
            return 1;
        }
        
        PosterOutputSummaryEntry tempPosterOutputSummaryEntry = (PosterOutputSummaryEntry)anotherPosterOutputSummaryEntry;
        comparisonResult = this.getUniversityFiscalYear().compareTo(tempPosterOutputSummaryEntry.getUniversityFiscalYear());
        if(comparisonResult != 0){
            return comparisonResult;
        }
        
        comparisonResult = this.getFiscalPeriodCode().compareTo(tempPosterOutputSummaryEntry.getFiscalPeriodCode());
        if(comparisonResult != 0){
            return comparisonResult;
        }        

        comparisonResult = this.getBalanceTypeCode().compareTo(tempPosterOutputSummaryEntry.getBalanceTypeCode());
        if(comparisonResult != 0){
            return comparisonResult;
        }
        
        comparisonResult = this.getFundGroup().compareTo(tempPosterOutputSummaryEntry.getFundGroup());
        if(comparisonResult != 0){
            return comparisonResult;
        }        

        comparisonResult = this.getObjectTypeCode().compareTo(tempPosterOutputSummaryEntry.getObjectTypeCode());
        if(comparisonResult != 0){
            return comparisonResult;
        }                
        return 0;
    }
}
