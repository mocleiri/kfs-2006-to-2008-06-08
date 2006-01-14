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
package org.kuali.module.chart.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.BusinessObjectBase;

/**
 * Reporting Codes Business Object
 * 
 * @author Cathy Tan (tanc@ais.msu.edu)
 */
public class ReportingCodes extends BusinessObjectBase {

	private static final long serialVersionUID = -1585612121519839488L;
    private String chartOfAccountsCode;
    private String organizationCode;
    private String financialReportingCode;
    private String financialReportingCodeDescription;
    private String financialReportingCodeMgrId;
    private String financialReportsToReportingCode;

	/**
	 * @return Returns the chartOfAccountsCode.
	 */
	public String getChartOfAccountsCode() {
		return chartOfAccountsCode;
	}
	
	/**
	 * @param chartOfAccountsCode The chartOfAccountsCode to set.
	 */
	public void setChartOfAccountsCode(
			String chartOfAccountsCode) {
		this.chartOfAccountsCode = chartOfAccountsCode;
	}
	
	/**
	 * @return Returns the financialReportingCode.
	 */
	public String getFinancialReportingCode() {
		return financialReportingCode;
	}
	
	/**
	 * @param financialReportingCode The financialReportingCode to set.
	 */
	public void setFinancialReportingCode(String financialReportingCode) {
		this.financialReportingCode = financialReportingCode;
	}
	
	/**
	 * @return Returns the financialReportingCodeDescription.
	 */
	public String getFinancialReportingCodeDescription() {
		return financialReportingCodeDescription;
	}
	
	/**
	 * @param financialReportingCodeDescription The financialReportingCodeDescription to set.
	 */
	public void setFinancialReportingCodeDescription(
			String financialReportingCodeDescription) {
		this.financialReportingCodeDescription = financialReportingCodeDescription;
	}
	
	/**
	 * @return Returns the financialReportingCodeMgrId.
	 */
	public String getFinancialReportingCodeMgrId() {
		return financialReportingCodeMgrId;
	}
	
	/**
	 * @param financialReportingCodeMgrId The financialReportingCodeMgrId to set.
	 */
	public void setFinancialReportingCodeMgrId(
			String financialReportingCodeMgrId) {
		this.financialReportingCodeMgrId = financialReportingCodeMgrId;
	}
	
	/**
	 * @return Returns the organizationCode.
	 */
	public String getOrganizationCode() {
		return organizationCode;
	}
	
	/**
	 * @param organizationCode The organizationCode to set.
	 */
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
	
	/**
	 * @return Returns the financialReportsToReportingCode.
	 */
	public String getFinancialReportsToReportingCode() {
		return financialReportsToReportingCode;
	}
	
	/**
	 * @param financialReportsToReportingCode The financialReportsToReportingCode to set.
	 */
	public void setFinancialReportsToReportingCode(
			String financialReportsToReportingCode) {
		this.financialReportsToReportingCode = financialReportsToReportingCode;
	}
    
	/**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("chartOfAccountsCode", this.chartOfAccountsCode);
        m.put("organizationCode", this.organizationCode);
        m.put("financialReportingCode", this.financialReportingCode);
        m.put("financialReportingCodeDescription", this.financialReportingCodeDescription);
        m.put("financialReportingCodeMgrId", this.financialReportingCodeMgrId);
        m.put("financialReportsToReportingCode", this.financialReportsToReportingCode);
        return m;
    }
}