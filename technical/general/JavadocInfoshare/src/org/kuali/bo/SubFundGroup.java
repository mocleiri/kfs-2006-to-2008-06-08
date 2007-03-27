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

import org.kuali.bo.codes.AccountingCode;
import org.kuali.validation.ValidationErrorList;

/**
 * 
 * @author hesterd, ahollamo
 *
 * Generic SubFundGroup Pojo to access Sub Fund Group information.
 * 
 * Getters/setters for CA_SUB_FUND_GRP_T as specified in OJB mapping.
 *
 */
public class SubFundGroup extends AccountingCode {

    private String financialReportSortCode;
    private String fundGroupCode;
    private String subFundGroupActiveCode;
    private String subFundGroupTypeCode;
    private String subFundGroupWageIndicator;

	/**
	 * @return Returns the financialReportSortCode.
	 */
	public String getFinancialReportSortCode() {
	    return financialReportSortCode;
	}
	/**
	 * @param financialReportSortCode The financialReportSortCode to set.
	 */
	public void setFinancialReportSortCode(String financialReportSortCode) {
	    this.financialReportSortCode = financialReportSortCode;
	}
	/**
	 * @return Returns the fundGroupCode.
	 */
	public String getFundGroupCode() {
	    return fundGroupCode;
	}
	/**
	 * @param fundGroupCode The fundGroupCode to set.
	 */
	public void setFundGroupCode(String fundGroupCode) {
	    this.fundGroupCode = fundGroupCode;
	}
	/**
	 * @return Returns the subFundGroupActiveCode.
	 */
	public String getSubFundGroupActiveCode() {
	    return subFundGroupActiveCode;
	}
	/**
	 * @param subFundGroupActiveCode The subFundGroupActiveCode to set.
	 */
	public void setSubFundGroupActiveCode(String subFundGroupActiveCode) {
	    this.subFundGroupActiveCode = subFundGroupActiveCode;
	}
	/**
	 * @return Returns the subFundGroupTypeCode.
	 */
	public String getSubFundGroupTypeCode() {
	    return subFundGroupTypeCode;
	}
	/**
	 * @param subFundGroupTypeCode The subFundGroupTypeCode to set.
	 */
	public void setSubFundGroupTypeCode(String subFundGroupTypeCode) {
	    this.subFundGroupTypeCode = subFundGroupTypeCode;
	}
	/**
	 * @return Returns the subFundGroupWageIndicator.
	 */
	public String getSubFundGroupWageIndicator() {
	    return subFundGroupWageIndicator;
	}
	/**
	 * @param subFundGroupWageIndicator The subFundGroupWageIndicator to set.
	 */
	public void setSubFundGroupWageIndicator(String subFundGroupWageIndicator) {
	    this.subFundGroupWageIndicator = subFundGroupWageIndicator;
	}
    /* (non-Javadoc)
     * @see org.kuali.bo.BusinessObject#validate(org.kuali.util.ValidationErrorList)
     */
    public void validate(ValidationErrorList errors) throws ValidationErrorList {
        // TODO Auto-generated method stub
        
    }
}
