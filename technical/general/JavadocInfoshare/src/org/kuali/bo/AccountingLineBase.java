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

import java.io.Serializable;

import org.kuali.exceptions.IllegalObjectStateException;
import org.kuali.service.AccountingCodeService;
import org.kuali.service.ObjectLevelService;
import org.kuali.util.KualiDecimal;
import org.kuali.validation.ValidationErrorList;


/**
 * This is the generic class which contains all the elements on a 
 * typical line of accounting elements.  These are all the accounting 
 * items necessary to create a pending entry to the G/L.  All transaction 
 * documents will use this business object inherently.  Specific 
 * accounting line business rules should should exist not in this structure, but 
 * rather in the document business object that uses it.
 * @author ahollamona
 * @author Aaron Godert (ag266@cornell.edu) - refactored for OJB polymorphism
 */
public abstract class AccountingLineBase extends BusinessObjectBase implements Serializable, AccountingLine {
	private Long documentHeaderId;
	private Integer sequenceNumber; //relative to the grouping of acctng lines
	private Integer postingYear;
	private String balanceTypeCode;
	private KualiDecimal amount;
	private String referenceOriginCode;
	private String referenceNumber;
	private String referenceTypeCode;
	private String overrideCode;
	private String organizationReferenceId;
	protected String ojbConcreteClass; //attribute needed for OJB polymorphism - do not alter!

	//bo references
	private Chart chart;
	private Account account;
	private ObjectCode objectCode;
	private SubAccount subAccount;
	private SubObjectCode subObjectCode;
	private ProjectCode projectCode;
	
	//service references
	private AccountingCodeService accountingCodeService;
	private ObjectLevelService objectLevelService;

	/**
	 * This constructor sets up empty instances 
	 * for the dependent objects.
	 */
	public AccountingLineBase() {
		setAmount(new KualiDecimal(0));
		chart = new Chart();
		account = new Account();
		objectCode = new ObjectCode();
		subAccount = new SubAccount();
		subObjectCode = new SubObjectCode();
		projectCode = new ProjectCode();
	}

    /**
     * This method instructs the object to validate itself and 
     * all its dependent components.
     * 
     * In the specific case of the AccountingLineBase, all this does is 
     * call the validate() methods on all its component objects.
     * @throws IllegalObjectStateException
     */
    public void validate(ValidationErrorList errors) throws ValidationErrorList, IllegalObjectStateException {
        validationNumber = errors.getNextValidationNumber();
        
        //	do validate() callbacks on all dependent objects
        errors.accumulateValidationErrors(chart,false,errors);
        errors.accumulateValidationErrors(account,false,errors);
        errors.accumulateValidationErrors(subAccount,true,errors);
        errors.accumulateValidationErrors(objectCode,false,errors);
        errors.accumulateValidationErrors(subObjectCode,true,errors);
        errors.accumulateValidationErrors(projectCode,true,errors);
        
        //	throw any outstanding errors
        errors.throwErrors();
        
    }
    
    public void validateForPersistence() throws ValidationErrorList {
        
        //	Since this object is not directly persistable, nothing 
        // is done here other than to cascade the validateForPersistence down
        // to sub-objects.
        return;
        
    }

	/**
	 * @return Returns the account.
	 */
	public Account getAccount() {
	    if (this.account == null) {
	        this.account = new Account();
	    }
		return account;
	}

	/**
	 * @param account The account to set.
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	/**
	 * @return Returns the chartOfAccountsCode.
	 */
	public Chart getChart() {
	    if (this.chart == null) {
	        this.chart = new Chart();
	    }
		return chart;
	}

	/**
	 * @param chartOfAccountsCode The chartOfAccountsCode to set.
	 */
	public void setChart(Chart chart) {
		this.chart = chart;
	}

	/**
	 * @return Returns the documentHeaderId.
	 */
	public Long getDocumentHeaderId() {
		return documentHeaderId;
	}

	/**
	 * @return Returns the amount.
	 */
	public KualiDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount The amount to set.
	 */
	public void setAmount(KualiDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return Returns the balanceTypeCode.
	 */
	public String getBalanceTypeCode() {
		return balanceTypeCode;
	}

	/**
	 * @param balanceTypeCode The balanceTypeCode to set.
	 */
	public void setBalanceTypeCode(String balanceTypeCode) {
		this.balanceTypeCode = balanceTypeCode;
	}

	/**
	 * @return Returns the objectCode.
	 */
	public ObjectCode getObjectCode() {
		return objectCode;
	}

	/**
	 * @param objectCode The objectCode to set.
	 */
	public void setObjectCode(ObjectCode objectCode) {
		this.objectCode = objectCode;
	}

	/**
	 * @return Returns the referenceOriginCode.
	 */
	public String getReferenceOriginCode() {
		return referenceOriginCode;
	}

	/**
	 * @param referenceOriginCode The referenceOriginCode to set.
	 */
	public void setReferenceOriginCode(String originCode) {
		this.referenceOriginCode = originCode;
	}

	/**
	 * @return Returns the organizationReferenceId.
	 */
	public String getOrganizationReferenceId() {
		return organizationReferenceId;
	}
	
	/**
	 * @param organizationReferenceId The organizationReferenceId to set.
	 */
	public void setOrganizationReferenceId(String organizationReferenceId) {
		this.organizationReferenceId = organizationReferenceId;
	}
	
	/**
	 * @return Returns the overrideCode.
	 */
	public String getOverrideCode() {
		return overrideCode;
	}

	/**
	 * @param overrideCode The overrideCode to set.
	 */
	public void setOverrideCode(String overrideCode) {
		this.overrideCode = overrideCode;
	}

	/**
	 * @return Returns the postingYear.
	 */
	public Integer getPostingYear() {
		return postingYear;
	}

	/**
	 * @param postingYear The postingYear to set.
	 */
	public void setPostingYear(Integer postingYear) {
		this.postingYear = postingYear;
	}

	/**
	 * @return Returns the projectCode.
	 */
	public ProjectCode getProjectCode() {
	    if (this.projectCode == null) {
	        this.projectCode = new ProjectCode();
	    }
        return projectCode;
	}

	/**
	 * @param projectCode The projectCode to set.
	 */
	public void setProjectCode(ProjectCode projectCode) {
		this.projectCode = projectCode;
	}

	/**
	 * @return Returns the referenceNumber.
	 */
	public String getReferenceNumber() {
		return referenceNumber;
	}

	/**
	 * @param referenceNumber The referenceNumber to set.
	 */
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	/**
	 * @return Returns the referenceTypeCode.
	 */
	public String getReferenceTypeCode() {
		return referenceTypeCode;
	}

	/**
	 * @param referenceTypeCode The referenceTypeCode to set.
	 */
	public void setReferenceTypeCode(String referenceTypeCode) {
		this.referenceTypeCode = referenceTypeCode;
	}

	/**
	 * @return Returns the sequenceNumber.
	 */
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * @param sequenceNumber The sequenceNumber to set.
	 */
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	/**
	 * @return Returns the subAccount.
	 */
	public SubAccount getSubAccount() {
	    if (this.subAccount == null) {
	        this.subAccount = new SubAccount();
	    }
		return subAccount;
	}

	/**
	 * @param subAccount The subAccount to set.
	 */
	public void setSubAccount(SubAccount subAccount) {
		this.subAccount = subAccount;
	}

	/**
	 * @return Returns the subObjectCode.
	 */
	public SubObjectCode getSubObjectCode() {
	    if (this.subObjectCode == null) {
	        this.subObjectCode = new SubObjectCode();
	    }
		return subObjectCode;
	}

	/**
	 * @param subObjectCode The subObjectCode to set.
	 */
	public void setSubObjectCode(SubObjectCode subObjectCode) {
		this.subObjectCode = subObjectCode;
	}

	/**
	 * @param documentHeaderId The documentHeaderId to set.
	 */
	public void setDocumentHeaderId(Long documentHeaderId) {
		this.documentHeaderId = documentHeaderId;
	}
	
	/**
	 * @return Returns the accountingCodeService.
	 */
	private AccountingCodeService getAccountingCodeService() {
		return accountingCodeService;
	}
	
	/**
	 * @param accountingCodeService The accountingCodeService to set.
	 */
	private void setAccountingCodeService(
			AccountingCodeService accountingCodeService) {
		this.accountingCodeService = accountingCodeService;
	}
	
	/**
	 * @return Returns the objectLevelService.
	 */
	private ObjectLevelService getObjectLevelService() {
		return objectLevelService;
	}
	
	/**
	 * @param objectLevelService The objectLevelService to set.
	 */
	private void setObjectLevelService(ObjectLevelService objectLevelService) {
		this.objectLevelService = objectLevelService;
	}

	/**
	 * This method instructs the object to validate itself and 
	 * all its dependent components.
	 * 
	 * In the specific case of the AccountingLineBase, all this does is 
	 * call the validate() methods on all its component objects.
	 */
	public void validate() throws ValidationErrorList {

		ValidationErrorList errors = new ValidationErrorList();

		//	do validate() callbacks on all dependent objects
		/**
		errors.accumulateValidationErrors(chart);
		errors.accumulateValidationErrors(account);
		errors.accumulateValidationErrors(subAccount, true);
		errors.accumulateValidationErrors(objectCode);
		errors.accumulateValidationErrors(subObjectCode, true);
		errors.accumulateValidationErrors(projectCode, true);
		*/

		//	throw any outstanding errors
		errors.throwErrors();

	}
	
	/**
	 * This method instructs the object to validate itself and 
	 * all its dependent components with the scope of saving in mind. 
	 * In the specific case of the AccountingLineBase, all this does is 
	 * call the validate() methods on all its component objects.
	 * @throws ValidationErrorList
	 */
	public void validateForSave() throws ValidationErrorList {
		ValidationErrorList errors = new ValidationErrorList();

		//	do validate() callbacks on all dependent objects
		/**
		errors.accumulateValidationErrors(chart);
		errors.accumulateValidationErrors(account);
		errors.accumulateValidationErrors(subAccount, true);
		errors.accumulateValidationErrors(objectCode);
		errors.accumulateValidationErrors(subObjectCode, true);
		errors.accumulateValidationErrors(projectCode, true);
		*/

		//	throw any outstanding errors
		errors.throwErrors();
	}
}