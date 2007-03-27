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
 * @author jbmorris
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AccountDelegate extends BusinessObjectBase {
	private String chartOfAccountsCode;
	private String accountNumber;
	private String documentTypeCode;
	private Long accountDelegateSystemId;
	private KualiDecimal documentApprovalFromAmount;
	private Boolean accountDelegatePrimaryRoute;
	private Boolean accountDelegateActive;
	private Timestamp accountDelegateStartDate;
	private KualiDecimal documentApprovalToAmount;

	/**
	 * 
	 * @return
	 */
    public boolean isAccountDelegateActive() {
        return accountDelegateActive.booleanValue();
    }
    
    /**
     * 
     * @param accountDelegateActive
     */
    public void setAccountDelegateActive(boolean accountDelegateActive) {
        this.accountDelegateActive = Boolean.valueOf(accountDelegateActive);
    }
    
    /**
     * 
     * @return
     */
    public boolean getAccountDelegatePrimaryRoute() {
        return accountDelegatePrimaryRoute.booleanValue();
    }
    
    /**
     * 
     * @param accountDelegatePrimaryRoute
     */
    public void setAccountDelegatePrimaryRoute(
            boolean accountDelegatePrimaryRoute) {
        this.accountDelegatePrimaryRoute = Boolean.valueOf( accountDelegatePrimaryRoute);
    }
    
    /**
     * 
     * @return
     */
    public Timestamp getAccountDelegateStartDate() {
        return accountDelegateStartDate;
    }
    
    /**
     * 
     * @param accountDelegateStartDate
     */
    public void setAccountDelegateStartDate(Timestamp accountDelegateStartDate) {
        this.accountDelegateStartDate = accountDelegateStartDate;
    }
    
    /**
     * 
     * @return
     */
    public Long getAccountDelegateSystemId() {
        return accountDelegateSystemId;
    }
    
    /**
     * 
     * @param accountDelegateSystemId
     */
    public void setAccountDelegateSystemId(Long accountDelegateSystemId) {
        this.accountDelegateSystemId = accountDelegateSystemId;
    }
    
    /**
     * 
     * @return
     */
    public String getAccountNumber() {
        return accountNumber;
    }
    
    /**
     * 
     * @param accountNumber
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    /**
     * 
     * @return
     */
    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }
    
    /**
     * 
     * @param chartOfAccountsCode
     */
    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }
    
    /**
     * 
     * @return
     */
    public KualiDecimal getDocumentApprovalFromAmount() {
        return documentApprovalFromAmount;
    }
    
    /**
     * 
     * @param documentApprovalFromAmount
     */
    public void setDocumentApprovalFromAmount(
            KualiDecimal documentApprovalFromAmount) {
        this.documentApprovalFromAmount = documentApprovalFromAmount;
    }
    
    /**
     * 
     * @return
     */
    public KualiDecimal getDocumentApprovalToAmount() {
        return documentApprovalToAmount;
    }
    
    /**
     * 
     * @param documentApprovalToAmount
     */
    public void setDocumentApprovalToAmount(KualiDecimal documentApprovalToAmount) {
        this.documentApprovalToAmount = documentApprovalToAmount;
    }
    
    /**
     * 
     * @return
     */
    public String getDocumentTypeCode() {
        return documentTypeCode;
    }
    
    /**
     * 
     * @param documentTypeCode
     */
    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }
    

    /* (non-Javadoc)
     * @see org.kuali.bo.BusinessObject#validate(org.kuali.util.ValidationErrorCollector)
     */
    public void validate(ValidationErrorList errors) throws ValidationErrorList {
        // TODO Auto-generated method stub
        
    }
}
