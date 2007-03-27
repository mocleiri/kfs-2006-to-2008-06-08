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
import java.util.Iterator;
import java.util.List;

import org.kuali.exceptions.IllegalObjectStateException;
import org.kuali.util.KualiDecimal;
import org.kuali.util.SpringServiceLocator;
import org.kuali.workflow.definition.KualiAccountDefinition;
import org.kuali.workflow.definition.KualiOrgReviewDefinition;

import edu.iu.uis.eden.routetemplate.FlexDoc;

public class JournalVoucherDocument extends TransactionalDocumentBase {
//    private Long documentHeaderId;
//	private DocumentHeader documentHeader;

//   private Long versionNumber;

//    private Integer postingYear;

    private String documentPostingAccountingPeriod;

//    private String documentExplanation;

    private Timestamp documentReversalDate;

    private String documentBalanceTypeCode;

    private List detailLines;
    
    public String getDocumentBalanceTypeCode() {
        return documentBalanceTypeCode;
    }
    public void setDocumentBalanceTypeCode(String documentBalanceTypeCode) {
        this.documentBalanceTypeCode = documentBalanceTypeCode;
    }
    public String getDocumentPostingAccountingPeriod() {
        return documentPostingAccountingPeriod;
    }
    public void setDocumentPostingAccountingPeriod(
            String documentPostingAccountingPeriod) {
        this.documentPostingAccountingPeriod = documentPostingAccountingPeriod;
    }
    public Timestamp getDocumentReversalDate() {
        return documentReversalDate;
    }
    public void setDocumentReversalDate(Timestamp documentReversalDate) {
        this.documentReversalDate = documentReversalDate;
    }
    public List getDetailLines() {
        return detailLines;
    }
    public void setDetailLines(List detailLines) {
        this.detailLines = detailLines;
    }
    public void addDetailLine(JournalVoucherDetailLine detailLine) {
        detailLines.add(detailLine);
    }
    
	
	/**
	 * TODO - not yet implemented
	 */
	public KualiDecimal getTotalDollarAmount() {
	    return new KualiDecimal(0);
	}
	
    /**
     * TODO - not yet implemented
     */
    public KualiDecimal getExpenseTotal()
    {
      return new KualiDecimal(0);
    }
    /**
     * TODO - not yet implemented
     */
    public KualiDecimal getIncomeTotal()
    {
      return new KualiDecimal(0);
    }

    /* (non-Javadoc)
     * @see org.kuali.bo.Document#validateDocument(org.kuali.validation.ValidationErrorList)
     */
    public void validateDocument(org.kuali.validation.ValidationErrorList errors) throws org.kuali.validation.ValidationErrorList, IllegalObjectStateException {
        // TODO Auto-generated method stub
        
    }
    /* (non-Javadoc)
     * @see org.kuali.bo.Document#validateForRouting(org.kuali.validation.ValidationErrorList)
     */
    public void validateForRouting(org.kuali.validation.ValidationErrorList errors) throws org.kuali.validation.ValidationErrorList, IllegalObjectStateException {
        // TODO Auto-generated method stub
        
    }
    
//  workflow related methods
    /**
     * Needed to fill in the FlexDoc field for routing.  This should mean something 
	 * because it will end up being the summary for an IB document and will be 
	 * searchable.
     */
    public String getDocumentTitle() {
    	//TODO - eDoc group needs to fill in with something important
    	return "New Journal Voucher Document - " + this.getDocumentHeader().getDocumentDescription();
    }
    
	/**
	 * Makes sure the document header is prepared for account and organization 
	 * based routing.
	 */
	public void populateDocumentForRouting() {
		FlexDoc flexDoc = getDocumentHeader().getFlexDoc();

    	addAttributeDefinitions(flexDoc, sourceAccountingLines);
	}
	
    
	/**
	 * For each source accounting line (income line), populate the document header 
	 * with account information and organization information.
	 * @param flexDoc
	 * @param List A list of the source lines for this document.
	 */
	private void addAttributeDefinitions(FlexDoc flexDoc, List accountingLines) {
		if(accountingLines != null) {
			Iterator accountingLinesIterator = accountingLines.iterator();
			while(accountingLinesIterator.hasNext()) {
				AccountingLineBase accountingLine = (AccountingLineBase)accountingLinesIterator
						.next();

				Account fetchedAccount = SpringServiceLocator.getAccountService().getByPrimaryId(
						accountingLine.getAccount().getChartOfAccountsCode(),
						accountingLine.getAccount().getAccountNumber());
				flexDoc.addAttributeDefinition(new KualiAccountDefinition(
						fetchedAccount.getChartOfAccountsCode(), fetchedAccount
								.getAccountNumber(), getTotalDollarAmount()
								.toString()));
				flexDoc.addAttributeDefinition(new KualiOrgReviewDefinition(
						fetchedAccount.getChartOfAccountsCode(), fetchedAccount
								.getOrganizationCode()));
			}
		}
	}
	
	/**
	 * @param newRouteStatus
	 */
    public void handleRouteStatusChange(String newRouteStatus) {
        // TODO fix this sometime so that we are handling Approved status
    }

    public void generatePEsfromAL(AccountingLineBase line, boolean isSource, int counter) throws IllegalObjectStateException {
        
    }
}
