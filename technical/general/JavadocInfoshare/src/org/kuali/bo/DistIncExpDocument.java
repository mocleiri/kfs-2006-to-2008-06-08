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

import org.kuali.Constants;
import org.kuali.exceptions.IllegalObjectStateException;
import org.kuali.util.KualiDecimal;
import org.kuali.util.SpringServiceLocator;
import org.kuali.workflow.definition.KualiAccountDefinition;
import org.kuali.workflow.definition.KualiOrgReviewDefinition;

import edu.iu.uis.eden.routetemplate.FlexDoc;

public class DistIncExpDocument extends TransactionalDocumentBase implements PendingEntryGenerator {

	public DistIncExpDocument() {
		super();
	}

	public String toString() {
		return " [DOC_HDR_ID] " + documentHeader.getDocumentHeaderId()
				+ " [FDOC_POST_YR] " + postingYear + " [VER_NBR] ";
	}

	/**
	 * Needed for workflow header population.
	 */
	public String getDocumentTitle() {
		//TODO - fill me in with something good for real
		return "New Distribution of Expencse eDoc - " + this.getDocumentHeader().getDocumentDescription();
	}

	//workflow related methods
	/* (non-Javadoc)
	 * @see org.kuali.bo.Document#populateDocumentForRouting()
	 */
	public void populateDocumentForRouting() {
		FlexDoc flexDoc = getDocumentHeader().getFlexDoc();
		
    	addAttributeDefinitionsForAccountingLines(flexDoc, targetAccountingLines);
	}

	/**
	 * @param flexDoc
	 * @param sourceAccountingLines
	 */
	private void addAttributeDefinitions(FlexDoc flexDoc, List accountingLines) {
		Iterator accountingLinesIterator = accountingLines.iterator();
		while (accountingLinesIterator.hasNext()) {
			AccountingLineBase accountingLine = (AccountingLineBase)accountingLinesIterator.next();
			
			Account fetchedAccount = SpringServiceLocator.getAccountService().getByPrimaryId(accountingLine.getAccount().getChartOfAccountsCode(), accountingLine.getAccount().getAccountNumber());
			flexDoc.addAttributeDefinition(new KualiAccountDefinition(fetchedAccount.getChartOfAccountsCode(), fetchedAccount.getAccountNumber(), getTotalDollarAmount().toString()));
			flexDoc.addAttributeDefinition(new KualiOrgReviewDefinition(fetchedAccount.getChartOfAccountsCode(), fetchedAccount.getOrganizationCode()));
		}
	}

	/**
	 * @param newRouteStatus
	 */
    public void handleRouteStatusChange(String newRouteStatus) {
        // TODO fix this sometime so that we are handling Approved status
    }

    public void generatePEsfromAL(AccountingLineBase line, boolean isSource, int counter) throws IllegalObjectStateException {
        if (line !=null) {
            KualiDecimal pendingEntryAmount = new KualiDecimal(0);
            String curLineObjectTypeCode = line.getObjectCode().getObjectTypeCode().getCode(); 
            String debitOrCreditCode = null;
            String offsetDebitOrCreditCode = null;
            //TODO - read this information dynamically!
            if ("EX".equals( curLineObjectTypeCode)||"EE".equals(curLineObjectTypeCode)||"ES".equals(curLineObjectTypeCode)||"AS".equals( curLineObjectTypeCode)) {
                if (isSource) { //TODO: CHECK THIS!!! not sure of this logic
	                if (line.getAmount().doubleValue() >= 0.0) {
		                pendingEntryAmount = line.getAmount();
		                debitOrCreditCode = "C";
		                offsetDebitOrCreditCode = "D";
		            } else {
		                pendingEntryAmount = line.getAmount().multiply(new KualiDecimal(-1));
		                debitOrCreditCode = "D";
		                offsetDebitOrCreditCode = "C";
		            }
                } else {
	                if (line.getAmount().doubleValue() >= 0.0) {
		                pendingEntryAmount = line.getAmount();
		                debitOrCreditCode = "D";
		                offsetDebitOrCreditCode = "C";
		            } else {
		                pendingEntryAmount = line.getAmount().multiply(new KualiDecimal(-1));
		                debitOrCreditCode = "C";
		                offsetDebitOrCreditCode = "D";
		            }
                }
            } else if ("IN".equals( curLineObjectTypeCode)||"IC".equals( curLineObjectTypeCode)||"CH".equals( curLineObjectTypeCode)||"LI".equals( curLineObjectTypeCode)){
                if (isSource) { //TODO: CHECK THIS!!! not sure of this logic
	                if (line.getAmount().doubleValue() >= 0.0) {
		                pendingEntryAmount = line.getAmount();
		                debitOrCreditCode = "D";
		                offsetDebitOrCreditCode = "C";
		            } else {
		                pendingEntryAmount = line.getAmount().multiply(new KualiDecimal(-1));
		                debitOrCreditCode = "C";
		                offsetDebitOrCreditCode = "D";
		            }
                } else {
	                if (line.getAmount().doubleValue() >= 0.0) {
		                pendingEntryAmount = line.getAmount();
		                debitOrCreditCode = "C";
		                offsetDebitOrCreditCode = "D";
		            } else {
		                pendingEntryAmount = line.getAmount().multiply(new KualiDecimal(-1));
		                debitOrCreditCode = "D";
		                offsetDebitOrCreditCode = "C";
		            }
                }
            } else {
                throw new IllegalStateException("Invalid Object Type: " + curLineObjectTypeCode);
            }

            // create explicit entry
            GeneralLedgerPendingEntry explicitEntry = new GeneralLedgerPendingEntry();
            explicitEntry.setReferenceOriginCode(line.getReferenceOriginCode());
            explicitEntry.setDocumentHeaderId(line.getDocumentHeaderId());
            explicitEntry.setTransactionEntrySequenceId(new Integer(counter));
            explicitEntry.setVersionNumber(new Long(1));
            explicitEntry.setChartOfAccountsCode(line.getChart().getChartOfAccountsCode());
            explicitEntry.setAccountNumber(line.getAccount().getAccountNumber());
            explicitEntry.setSubAccountNumber(line.getSubAccount().getSubAccountNumber());
            explicitEntry.setObjectCode(line.getObjectCode().getFinancialObjectCode());
            explicitEntry.setSubObjectCode(line.getSubObjectCode().getFinancialSubObjectCode());
            explicitEntry.setBalanceTypeCode("AC"); //TODO - where is this? need to NOT be hard coded
            explicitEntry.setObjectTypeCode(line.getObjectCode().getFinancialObjectTypeCode());
            explicitEntry.setUniversityFiscalYear(getPostingYear()); //TODO - this is fine for DI, but not ALL documents
            explicitEntry.setUniversityFiscalAccountingPeriod(null); // null here, is assigned during batch TODO - is this correct for all?
            explicitEntry.setTransactionLedgerEntryDescription(getDocumentHeader().getDocumentDescription());
            explicitEntry.setTransactionLedgerEntryAmount(pendingEntryAmount);
            explicitEntry.setDebitOrCreditCode(debitOrCreditCode);
            explicitEntry.setTransactionDate(new Timestamp(SpringServiceLocator.getDateTimeService().getCurrentDate().getTime())); //todo: add this to service
            explicitEntry.setDocumentTypeCode("DI");
            explicitEntry.setOrganizationDocumentNumber(getDocumentHeader().getOrganizationDocumentNumber());
            explicitEntry.setProjectCode(line.getProjectCode().getCode());
            explicitEntry.setOrganizationReferenceId(line.getOrganizationReferenceId());
            explicitEntry.setAccountSufficientFundsObjectCode(getSufficientFundsObjectCode(line));
            explicitEntry.setTransactionEntryOffsetCode("N"); // N - is not offset, Y - IS offset - TODO - for budget entries, this needs to be different
            explicitEntry.setDocumentReversalDate(null); // todo: null for DI, but MAY BE used in other docs. date that you want it reversed

            explicitEntry.setApprovedCode(null); // ok to be null ?
            explicitEntry.setEncumbranceUpdateCode(null); //TODO - null for many docs, but used for some, how to handle?
            explicitEntry.setReferenceDocumentNumber(line.getReferenceNumber());
            explicitEntry.setReferenceDocumentTypeCode(line.getReferenceTypeCode());
            explicitEntry.setTransactionEntryProcessed(null); // left null at creation, filled in during batch TODO - is this correct for all?
            //add to document now
            addGeneralLedgerPendingEntryLine(explicitEntry);
            
            //lookup offset object info
            OffsetDefinition offsetDefinition = SpringServiceLocator.getOffsetDefinitionService().getByPrimaryId(getPostingYear(), line.getAccount().getChartOfAccountsCode(),
                    "DI", "AC" ); //TODO - HACK ALERT!!!
                        
            // create offset entry
            GeneralLedgerPendingEntry offsetEntry = (GeneralLedgerPendingEntry) explicitEntry.clone();
            offsetEntry.setTransactionEntryOffsetCode("Y");
            offsetEntry.setObjectCode(offsetDefinition.getObjectCode().getFinancialObjectCode()); //TODO - is this right?
            offsetEntry.setObjectTypeCode(offsetDefinition.getObjectCode().getFinancialObjectTypeCode()); //TODO - is this right?
            offsetEntry.setSubObjectCode(offsetDefinition.getSubObjectCode()); //TODO - is this right?
            offsetEntry.setDebitOrCreditCode(offsetDebitOrCreditCode);
            int offsetSeqId = counter+1;
            offsetEntry.setTransactionEntrySequenceId(new Integer(offsetSeqId));
            offsetEntry.setAccountSufficientFundsObjectCode(Constants.NOT_AVAILABLE_STRING);
            offsetEntry.setProjectCode(Constants.BLANK_PROJECT_STRING);
            offsetEntry.setTransactionLedgerEntryDescription(Constants.GL_PE_OFFSET_STRING);
            //add offset to document now
            addGeneralLedgerPendingEntryLine(offsetEntry);
        }        
    }
}
