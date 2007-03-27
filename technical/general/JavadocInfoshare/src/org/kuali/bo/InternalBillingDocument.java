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

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.kuali.Constants;
import org.kuali.exceptions.IllegalObjectStateException;
import org.kuali.util.KualiDecimal;
import org.kuali.util.SpringServiceLocator;

import edu.iu.uis.eden.routetemplate.FlexDoc;


/**
 * This is the business object that represents the InternalBillingDocument in Kuali. This 
 * is a transactional document that will eventually post transactions to the G/L.  It 
 * integrates with workflow and also contains two groupings of accounting lines: 
 * Expense and target.  Expense is the expense and target is the income lines. * 
 * @author jbmorris
 * @author Aaron Godert (ag266@cornell.edu) - added in the refactored accounting line groups.
 */
public class InternalBillingDocument extends TransactionalDocumentBase implements PendingEntryGenerator {
	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(InternalBillingDocument.class);
	
	private Date billingDate;
	private String customerName;
	private String customerNumber;
	
	private List items;
	private Integer nextItemLineNumber;
	
	/**
	 * Initializes the array lists and some basic info.
	 */
	public InternalBillingDocument() {
		super();
		setItems(new ArrayList());
		this.nextItemLineNumber = new Integer(1);
	}

	/**
	 * Adds a new item to the item list.
	 * @param item
	 */
	public void addItem(InternalBillingItem item) {
		item.setItemSequenceId(this.getNextItemLineNumber());
		this.items.add(item);
		this.nextItemLineNumber = new Integer(this.nextItemLineNumber
				.intValue() + 1);
	}

	/**
	 * @return
	 */
	public Date getBillingDate() {
		return this.billingDate;
	}

	/**
	 * @return
	 */
	public String getCustomerName() {
		return this.customerName;
	}

	/**
	 * @return
	 */
	public String getCustomerNumber() {
		return this.customerNumber;
	}
	
	/**
	 * Retrieve a particular item at a given index in the list of items.
	 * @param index
	 * @return
	 */
	public InternalBillingItem getItem(int index) {
		while(getItems().size() <= index) {
			getItems().add(new InternalBillingItem());
		}
		return (InternalBillingItem)getItems().get(index);
	}

	/**
	 * @return Returns the items.
	 */
	public List getItems() {
		return items;
	}
	
	/**
	 * Iterates through the list of items and sums up their totals.
	 * @return
	 */
	public KualiDecimal getItemTotal() {
		KualiDecimal total = new KualiDecimal(0);
		InternalBillingItem item = null;
		Iterator iter = items.iterator();
		while(iter.hasNext()) {
			item = (InternalBillingItem)iter.next();
			total = total.add(item.getTotal());
		}

		return total;
	}

	/**
	 * Retrieves the next item line number and increments for the 
	 * next call.
	 * @return The next available item line number
	 */
	public Integer getNextItemLineNumber() {
		return this.nextItemLineNumber;
	}

	/**
	 * @param param
	 */
	public void setBillingDate(Date param) {
		this.billingDate = param;
	}

	/**
	 * @param param
	 */
	public void setCustomerName(String param) {
		this.customerName = param;
	}

	/**
	 * @param param
	 */
	public void setCustomerNumber(String param) {
		this.customerNumber = param;
	}

	/**
	 * @param items
	 */
	public void setItems(List items) {
		this.items = items;
	}

	/**
	 * @param param
	 */
	public void setNextItemLineNumber(Integer param) {
		this.nextItemLineNumber = param;
	}

	/**
	 * 
	 */
	public String toString() {
		return "";
		/*		return " [DOC_HDR_ID] " + documentHeader.getDocumentHeaderId()
		 + " [FDOC_BILL_DT] " + billingDate
		 + " [FDOC_CUSTOMER_NBR] " + customerNumber
		 + " [FDOC_CUSTOMER_NM] " + customerName
		 + " [FDOC_EXPLAIN_TXT] " + documentExplanation
		 + " [FDOC_POST_YR] " + postingYear + " [VER_NBR] "
		 + versionNumber;
		 */
	}
	
//	workflow related methods
	/**
	 * Needed to fill in the FlexDoc field for routing.  This should mean something 
	 * because it will end up being the summary for an IB document and will be 
	 * searchable.
	 */
	public String getDocumentTitle() {
		//TODO - eDoc group needs to figure out exactly what needs to go in here for real
		return "New Internal Billing Document - " + this.getDocumentHeader().getDocumentDescription();
	}
	
	/**
	 * Makes sure the document header is prepared for account and organization 
	 * based routing.
	 */
	public void populateDocumentForRouting() {
		FlexDoc flexDoc = getDocumentHeader().getFlexDoc();

    	addAttributeDefinitionsForAccountingLines(flexDoc, sourceAccountingLines);
	}
	
    
	/**
	 * @param newRouteStatus
	 */
    public void handleRouteStatusChange(String newRouteStatus) {
        // TODO fix this sometime so that we are handling Approved status
    }

    /**
     * @param line
     * @param document
     * @param isSource
     */
    public void generatePEsfromAL(AccountingLineBase line, boolean isSource, int counter) throws IllegalObjectStateException {
        if (line !=null) {
            KualiDecimal pendingEntryAmount = new KualiDecimal(0);
            String curLineObjectTypeCode = line.getObjectCode().getObjectTypeCode().getCode(); 
            String debitOrCreditCode = null;
            String offsetDebitOrCreditCode = null;
            //TODO - read this information dynamically!
            if ("EX".equals( curLineObjectTypeCode)||"EE".equals(curLineObjectTypeCode)||"ES".equals(curLineObjectTypeCode)||"AS".equals( curLineObjectTypeCode)||"IN".equals( curLineObjectTypeCode)||"IC".equals( curLineObjectTypeCode)||"CH".equals( curLineObjectTypeCode)||"LI".equals( curLineObjectTypeCode)) {
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
            } else {
                throw new IllegalStateException();//TODO: finish this
            }

            // create explicit entry
            GeneralLedgerPendingEntry explicitEntry = new GeneralLedgerPendingEntry();
            explicitEntry.setAccountNumber(line.getAccount().getAccountNumber());
            explicitEntry.setApprovedCode(null); // ok to be null ?
            explicitEntry.setAccountSufficientFundsObjectCode(getSufficientFundsObjectCode(line));
            explicitEntry.setVersionNumber(new Long(1));
            explicitEntry.setBalanceTypeCode("AC"); //TODO - where is this? need to NOT be hard coded
            explicitEntry.setChartOfAccountsCode(line.getChart().getChartOfAccountsCode());
            explicitEntry.setDebitOrCreditCode(debitOrCreditCode);
            explicitEntry.setDocumentHeaderId(line.getDocumentHeaderId());
            explicitEntry.setDocumentReversalDate(null); // todo: null for IB, but MAY BE used in other docs. date that you want it reversed
            explicitEntry.setDocumentTypeCode("IB");
            explicitEntry.setEncumbranceUpdateCode(null); //TODO - null for many docs, but used for some, how to handle?
            explicitEntry.setObjectCode(line.getObjectCode().getFinancialObjectCode());
            explicitEntry.setObjectTypeCode(line.getObjectCode().getFinancialObjectTypeCode());
            explicitEntry.setOrganizationDocumentNumber(getDocumentHeader().getOrganizationDocumentNumber());
            explicitEntry.setOrganizationReferenceId(line.getOrganizationReferenceId());
            explicitEntry.setProjectCode(line.getProjectCode().getCode());
            explicitEntry.setReferenceDocumentNumber(line.getReferenceNumber());
            explicitEntry.setReferenceDocumentTypeCode(line.getReferenceTypeCode());
            explicitEntry.setReferenceOriginCode(line.getReferenceOriginCode());
            explicitEntry.setSubAccountNumber(line.getSubAccount().getSubAccountNumber());
            explicitEntry.setSubObjectCode(line.getSubObjectCode().getFinancialSubObjectCode());
            explicitEntry.setTransactionDate(new Timestamp(SpringServiceLocator.getDateTimeService().getCurrentDate().getTime())); //todo: add this to service
            explicitEntry.setTransactionEntryOffsetCode("N"); // N - is not offset, Y - IS offset - TODO - for budget entries, this needs to be different
            explicitEntry.setTransactionEntryProcessed(null); // left null at creation, filled in during batch TODO - is this correct for all?
            explicitEntry.setTransactionEntrySequenceId(new Integer(counter));
            explicitEntry.setTransactionLedgerEntryAmount(pendingEntryAmount);
            explicitEntry.setTransactionLedgerEntryDescription(getDocumentHeader().getDocumentDescription());
            explicitEntry.setUniversityFiscalAccountingPeriod(null); // null here, is assigned during batch TODO - is this correct for all?
            explicitEntry.setUniversityFiscalYear(getPostingYear()); //TODO - this is fine for IB, but not ALL documents
            //add to document now
            addGeneralLedgerPendingEntryLine(explicitEntry);
            
            //lookup offset object info
            OffsetDefinition offsetDefinition = SpringServiceLocator.getOffsetDefinitionService().getByPrimaryId(getPostingYear(), line.getAccount().getChartOfAccountsCode(),
                    "IB", "AC" ); //TODO - HACK ALERT!!!
                        
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