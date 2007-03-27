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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.xpath.functions.WrongNumberArgsException;
import org.kuali.Constants;
import org.kuali.exceptions.IllegalObjectStateException;
import org.kuali.util.KualiDecimal;
import org.kuali.util.SpringServiceLocator;
import org.kuali.validation.ValidationErrorGroup;
import org.kuali.validation.ValidationErrorList;
import org.kuali.workflow.definition.KualiAccountDefinition;
import org.kuali.workflow.definition.KualiOrgReviewDefinition;

import edu.iu.uis.eden.routetemplate.FlexDoc;

/**
 * This is the parent class for all documents that will contain accounting line information that will turn into G/L entries.
 * 
 * @author aapotts
 * @author Aaron Godert (ag266@cornell.edu) - continued adding in accounting line abstractions
 */
public abstract class TransactionalDocumentBase extends DocumentBase implements TransactionalDocument {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(TransactionalDocumentBase.class);

    protected Integer postingYear;
    protected Integer nextSourceLineNumber;
    protected Integer nextTargetLineNumber;

    protected List sourceAccountingLines;
    protected List targetAccountingLines;
    protected List pendingEntries;

    /**
     * Sets up the collection instances and common document attributes.
     */
    public TransactionalDocumentBase() {
        super();
        this.nextSourceLineNumber = new Integer(1);
        this.nextTargetLineNumber = new Integer(1);
        this.postingYear = SpringServiceLocator.getDateTimeService().getCurrentFiscalYear();
        setSourceAccountingLines(new ArrayList());
        setTargetAccountingLines(new ArrayList());
        setPendingEntries(new ArrayList());
    }

    /**
     * 
     * @return
     */
    public Integer getPostingYear() {
        return this.postingYear;
    }

    /**
     * 
     * @param postingYear
     */
    public void setPostingYear(Integer postingYear) {
        this.postingYear = postingYear;
    }

    /**
     * 
     * @return
     */
    public List getSourceAccountingLines() {
        return this.sourceAccountingLines;
    }

    /**
     * 
     * @param sourceAccountingLines
     */
    public void setSourceAccountingLines(List sourceLines) {
        this.sourceAccountingLines = sourceLines;
    }

    /**
     * 
     * @return
     */
    public List getTargetAccountingLines() {
        return this.targetAccountingLines;
    }

    /**
     * 
     * @param targetAccountingLines
     */
    public void setTargetAccountingLines(List targetLines) {
        this.targetAccountingLines = targetLines;
    }

    /**
     * Adds a new accounting line (source) to the list of source lines.
     * 
     * @param line
     */
    public void addSourceAccountingLine(SourceAccountingLine line) {
        line.setSequenceNumber(this.getNextSourceLineNumber());
        this.sourceAccountingLines.add(line);
        this.nextSourceLineNumber = new Integer(this.nextSourceLineNumber.intValue() + 1);
    }

    /**
     * Adds a new accounting line (target) to the list of target lines.
     * 
     * @param line
     */
    public void addTargetAccountingLine(TargetAccountingLine line) {
        line.setSequenceNumber(this.getNextTargetLineNumber());
        this.targetAccountingLines.add(line);
        this.nextTargetLineNumber = new Integer(this.nextTargetLineNumber.intValue() + 1);
    }

    /**
     * Adds a new general ledger pending entry line to the list of pending entry lines.
     * 
     * @param pendingEntryLine
     */
    public void addGeneralLedgerPendingEntryLine(GeneralLedgerPendingEntry pendingEntryLine) {
        this.pendingEntries.add(pendingEntryLine);
    }

    /**
     * Retrieves a SourceAccountingLine at a particular index.
     * 
     * @param index
     * @return
     */
    public SourceAccountingLine getSourceAccountingLine(int index) {
        while (sourceAccountingLines.size() <= index) {
            sourceAccountingLines.add(new SourceAccountingLine());
        }
        return (SourceAccountingLine) sourceAccountingLines.get(index);
    }

    /**
     * Retrieves a TargetAccountingLine at a particular index.
     * 
     * @param index
     * @return
     */
    public TargetAccountingLine getTargetAccountingLine(int index) {
        while (targetAccountingLines.size() <= index) {
            targetAccountingLines.add(new TargetAccountingLine());
        }
        return (TargetAccountingLine) targetAccountingLines.get(index);
    }

    /**
     * Since one side of the document should match the other and the document should balance, the total dollar amount for the
     * document should either be the expense line or the income line.
     */
    public KualiDecimal getTotalDollarAmount() {
        return getTargetTotal().equals(new KualiDecimal(0)) ? getSourceTotal() : getTargetTotal();
    }

    /**
     * Sums up the amounts of all of the source accounting lines.
     * 
     * @return
     */
    public KualiDecimal getSourceTotal() {
        KualiDecimal total = new KualiDecimal(0);
        AccountingLineBase al = null;
        Iterator iter = sourceAccountingLines.iterator();
        while (iter.hasNext()) {
            al = (AccountingLineBase) iter.next();
            total = total.add(al.getAmount());
        }
        return total;
    }

    /**
     * Sums up the amounts of all of the target accounting lines.
     * 
     * @return
     */
    public KualiDecimal getTargetTotal() {
        KualiDecimal total = new KualiDecimal(0);
        AccountingLineBase al = null;
        Iterator iter = targetAccountingLines.iterator();
        while (iter.hasNext()) {
            al = (AccountingLineBase) iter.next();
            total = total.add(al.getAmount());
        }
        return total;
    }

    /**
     * Retrieves the next from line number and increments for the next call.
     * 
     * @return The next available source line number.
     */
    public Integer getNextSourceLineNumber() {
        Integer returnVal = this.nextSourceLineNumber;
        this.nextSourceLineNumber = new Integer(this.nextSourceLineNumber.intValue() + 1);
        return returnVal;
    }

    /**
     * @param nextLineNumber
     */
    public void setNextSourceLineNumber(Integer nextLineNumber) {
        this.nextSourceLineNumber = nextLineNumber;
    }

    /**
     * Retrieves the next from line number and increments for the next call.
     * 
     * @return The next available target line number.
     */
    public Integer getNextTargetLineNumber() {
        Integer returnVal = this.nextTargetLineNumber;
        this.nextTargetLineNumber = new Integer(this.nextTargetLineNumber.intValue() + 1);
        return returnVal;
    }

    /**
     * @param nextLineNumber
     */
    public void setNextTargetLineNumber(Integer nextLineNumber) {
        this.nextTargetLineNumber = nextLineNumber;
    }

    /**
     * 
     * @return
     */
    public List getPendingEntries() {
        return this.pendingEntries;
    }

    /**
     * 
     * @param pendingEntries
     */
    public void setPendingEntries(List pendingEntries) {
        this.pendingEntries = pendingEntries;
    }

    /**
     * Retrieves a PendingEntry at a particular index.
     * 
     * @param index
     * @return
     */
    public GeneralLedgerPendingEntry getPendingEntry(int index) {
        while (pendingEntries.size() <= index) {
            pendingEntries.add(new GeneralLedgerPendingEntry());
        }
        return (GeneralLedgerPendingEntry) pendingEntries.get(index);
    }

    /**
     * This method should be called before the document is persisted. It should be called by the child document's
     * validateForSaveMethod.
     * 
     * @throws IllegalObjectStateException
     */
    public void validateDocumentHeaderForSave(ValidationErrorList documentErrors) throws IllegalObjectStateException {
        ValidationErrorGroup documentErrorGroup = new ValidationErrorGroup(Constants.DOCUMENT_ERRORS);
        documentErrors.accumulateValidationErrors(getDocumentHeader(), false, documentErrors);
        documentErrors.addValidationGroup(documentErrorGroup);
    }

    /**
     * This method should be called before the document is persisted. It should be overridden, but should still be called with
     * super(documentErrors);
     * 
     * @throws IllegalObjectStateException
     */
    public void validateForSave(ValidationErrorList documentErrors) throws IllegalObjectStateException {
        validateSourceAccountingLines(documentErrors);
        validateTargetAccountingLines(documentErrors);
    }

    /**
     * This method should be called before the document is persisted.
     * 
     * @throws IllegalObjectStateException
     */
    public void validateDocument(ValidationErrorList errors) throws ValidationErrorList, IllegalObjectStateException {
        super.validateDocument(errors);

        //validates information associated with the document header (i.e. description,
        //explanation, etc)
        validateDocumentHeaderForSave(errors);

        //validates accounting lines by iterating through each and calling .validate()
        //on each dependent business object
        validateForSave(errors);

        errors.throwErrors();
    }

    /**
     * This method should be called before the document is routed.
     * 
     * @throws IllegalObjectStateException
     */
    public void validateForRouting(ValidationErrorList errors) throws ValidationErrorList, IllegalObjectStateException {
        super.validateForRouting(errors);
        //validates information associated with the document header (i.e. description,
        //explanation, etc)
        validateDocumentHeaderForSave(errors);

        //validates accounting lines by iterating through each and calling .validate()
        //on each dependent business object
        validateForSave(errors);

        //makes sure at least one source line exists
        validateSourceAccountingLinesExist(errors);

        //makes sure at least one target line exists
        validateTargetAccountingLinesExist(errors);

        //makes sure the document is in balance - expense lines balance with income lines
        validateInBalance(errors);

        errors.throwErrors();
    }

    /**
     * This method checks to see if the document is in balance by comparing the totals of the source lines with the totals of the
     * income lines.
     * 
     * @param documentErrors
     */
    public void validateInBalance(ValidationErrorList errors) {
        ValidationErrorGroup documentErrors = new ValidationErrorGroup(Constants.DOCUMENT_ERRORS);
        if (getSourceTotal().compareTo(getTargetTotal()) != 0) {
            documentErrors.addError(Constants.ERROR_DOCUMENT_BALANCE);
        }
        errors.addValidationGroup(documentErrors);
    }

    /**
     * Validate the source accounting lines.
     * 
     * @param documentErrors Collects all the errors that are thrown
     * @throws IllegalObjectStateException
     */
    private void validateSourceAccountingLines(ValidationErrorList errors) throws IllegalObjectStateException {
        ValidationErrorGroup documentErrors = new ValidationErrorGroup(Constants.SOURCE_ERRORS);
        for (Iterator iter = sourceAccountingLines.iterator(); iter.hasNext();) {
            SourceAccountingLine line = (SourceAccountingLine) iter.next();
            documentErrors.accumulateValidationErrors(line, false, errors);
        }
        errors.addValidationGroup(documentErrors);
    }

    /**
     * Makes sure that at least one source accounting line exists.
     * 
     * @param errors
     * @throws IllegalObjectStateException
     */
    public void validateSourceAccountingLinesExist(ValidationErrorList errors) throws IllegalObjectStateException {
        ValidationErrorGroup documentErrors = new ValidationErrorGroup(Constants.SOURCE_ERRORS);

        if (sourceAccountingLines.size() == 0) {
            documentErrors.addError(Constants.ERRORS_DOCUMENT_NO_SOURCE_LINES);
        }

        errors.addValidationGroup(documentErrors);
    }

    /**
     * Validate the target accounting lines.
     * 
     * @param documentErrors Collects all the errors that are thrown
     * @throws IllegalObjectStateException
     */
    private void validateTargetAccountingLines(ValidationErrorList errors) throws IllegalObjectStateException {
        ValidationErrorGroup documentErrors = new ValidationErrorGroup(Constants.TARGET_ERRORS);
        for (Iterator iter = targetAccountingLines.iterator(); iter.hasNext();) {
            TargetAccountingLine line = (TargetAccountingLine) iter.next();
            documentErrors.accumulateValidationErrors(line, false, errors);
        }
        errors.addValidationGroup(documentErrors);
    }

    /**
     * Makes sure that at least one target accounting line exists.
     * 
     * @param errors
     * @throws IllegalObjectStateException
     */
    public void validateTargetAccountingLinesExist(ValidationErrorList errors) throws IllegalObjectStateException {
        ValidationErrorGroup documentErrors = new ValidationErrorGroup(Constants.TARGET_ERRORS);

        if (targetAccountingLines.size() == 0) {
            documentErrors.addError(Constants.ERRORS_DOCUMENT_NO_TARGET_LINES);
        }

        errors.addValidationGroup(documentErrors);
    }

    //helper methods
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer b = new StringBuffer("TransactionalDocumentBase( ");

        b.append(documentHeader + ",");
        b.append(explanation + ",");
        b.append(versionNumber + ",");
        b.append(postingYear + ",");
        b.append(nextSourceLineNumber + ",");
        b.append(nextTargetLineNumber + ",");

        return b.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.kuali.service.PendingEntryGenerator#generateEntries(org.kuali.bo.TransactionalDocumentBase)
     */
    public void generateEntries() throws IllegalObjectStateException, ValidationErrorList {
        /*
         * NOTE: This method should only be called upon document approval
         * 
         * 1. step through list of source accounting lines if obj_type = expense if amount > 0 then its a credit and offset is a
         * debit else its a debit, multiple by -1 and set offset to a credit if obj_type = revenue if amount > 0 then its a credit
         * and offset is a debit else its a debit, multiple by -1 and set offset to be a debit check & update funds? 2. generate
         * explicit entry 3. save explicity entry 4. get offset object code 5. lookup object code 6. generate offset entry 7. save
         * offset entry
         * 
         * for target accounting lines, follow same path, but reverse logic on determining credit/debit
         * 
         * ISSUES: - not all accounting lines generate dual PEs - hard coded strings need to be retrieved from table or constants or
         * properties (basic rules engine) - many fields that are null for some doc types NEED data for other doc types - should the
         * doc itself have the responsibility of returning that data? - Budget Encumberances, in particular, are quite different
         * than IB or DistIncExp -
         */
        ValidationErrorList errors = new ValidationErrorList();

        //TODO: NEED TO DELETE THEM IF WE ARE GOING TO BE ABLE TO PERSIST MULTIPLE TIMES
        // BUT WE MAY want to do this selectively or switch to updating them intelligently
        // instead of regeneration all of the time.

        this.getPendingEntries().clear();
        SpringServiceLocator.getGeneralLedgerPendingEntryService().delete(this.documentHeaderId);

        int counter = 1;

        if (this.getSourceAccountingLines() != null) {
            for (Iterator iter = this.getSourceAccountingLines().iterator(); iter.hasNext();) {
                AccountingLineBase sourceLine = (AccountingLineBase) iter.next();
                generateSourcePEsfromAL(sourceLine, counter);
                counter += 2;
            }
        }

        if (this.getTargetAccountingLines() != null) {
            for (Iterator iter = this.getTargetAccountingLines().iterator(); iter.hasNext();) {
                AccountingLineBase targetLine = (AccountingLineBase) iter.next();
                generateTargetPEsfromAL(targetLine, counter);
                counter += 2;
            }
        }
    }

    /**
     * 
     * @param line
     * @param document
     * @param counter
     * @throws IllegalObjectStateException
     * 
     * helper method to simplify pending entry creation
     */
    public void generateSourcePEsfromAL(AccountingLineBase line, int counter) throws IllegalObjectStateException {
        generatePEsfromAL(line, true, counter);
    }

    /**
     * 
     * @param line
     * @param document
     * @param counter
     * @throws IllegalObjectStateException
     * 
     * helper method to simplify pending entry creation
     */
    public void generateTargetPEsfromAL(AccountingLineBase line, int counter) throws IllegalObjectStateException {
        generatePEsfromAL(line, false, counter);
    }

    /**
     * @param line
     * @param document
     * @param isSource
     * 
     * this method MUST be overriden by the individual documents and will take an accounting line and figure out how to turn it into
     * a pair of pending entries
     */
    public abstract void generatePEsfromAL(AccountingLineBase line, boolean isSource, int counter)
            throws IllegalObjectStateException;

    /**
     * @param accountingLine
     * @return
     * @throws Exception
     * 
     * Method to decide which object code/level/consolidation to use based on the AccountSufficientFundsCode
     */
    public String getSufficientFundsObjectCode(AccountingLineBase accountingLine) {
        String sfObjectCode = Constants.NOT_AVAILABLE_STRING;
        String curSFCode = accountingLine.getAccount().getAccountSufficientFundsCode();
        if ("O".equals(curSFCode)) {
            sfObjectCode = accountingLine.getObjectCode().getFinancialObjectCode();
        }
        else if ("L".equals(curSFCode)) {
            sfObjectCode = accountingLine.getObjectCode().getFinancialObjectLevelCode();
        }
        else if ("C".equals(curSFCode)) {
            ObjectLevel objectLevel = SpringServiceLocator.getObjectLevelService().getByPrimaryId(
                    accountingLine.getChart().getChartOfAccountsCode(),
                    accountingLine.getObjectCode().getFinancialObjectLevelCode());
            if (objectLevel != null) {
                sfObjectCode = objectLevel.getConsolidatedObjectCode();
            }
        }
        return sfObjectCode;
    }

    public void uploadAccountingLines(boolean isSource, InputStream inputStream) throws FileNotFoundException, IOException,
            WrongNumberArgsException {
        int EXPECTED_FIELDS = 8;
        Reader r = new BufferedReader(new InputStreamReader(inputStream));
        StreamTokenizer tokenized = new StreamTokenizer(r);
        tokenized.eolIsSignificant(true);
        String[] accountingLineData = new String[8];
        int fieldCount = 0;
        while (tokenized.nextToken() != StreamTokenizer.TT_EOF) {
            switch (tokenized.ttype) {
                case StreamTokenizer.TT_WORD: {
                    accountingLineData[fieldCount] = tokenized.sval;
                    ++fieldCount;
                    break;
                }
                case StreamTokenizer.TT_NUMBER: {
                    if (fieldCount + 1 != EXPECTED_FIELDS) {
                        accountingLineData[fieldCount] = Long.toString(Math.round(tokenized.nval));
                    }
                    else {
                        accountingLineData[fieldCount] = Double.toString(tokenized.nval);
                    }
                    ++fieldCount;
                    break;
                }
                case StreamTokenizer.TT_EOL: {
                    if (fieldCount < EXPECTED_FIELDS) {
                        throw new IllegalStateException("Number of fields in csv (" + fieldCount
                                + ") does not match EXPECTED_FIELDS (" + EXPECTED_FIELDS + ")");
                    }
                    // add current accounting line to list, reset base one - call new method
                    AccountingLineBase newLine = null;
                    if (isSource) {
                        newLine = new SourceAccountingLine();
                    }
                    else {
                        newLine = new TargetAccountingLine();
                    }

                    Integer fiscalYear = new Integer(accountingLineData[0]);
                    String coa = accountingLineData[1];
                    String account = accountingLineData[2];
                    String subAccount = accountingLineData[3];
                    String objectCode = accountingLineData[4];
                    String subObjectCode = accountingLineData[5];
                    String projectCode = accountingLineData[6];
                    KualiDecimal amount = new KualiDecimal(accountingLineData[7]);

                    if (isSource) {
                        newLine.setSequenceNumber(this.getNextSourceLineNumber());
                    }
                    else {
                        newLine.setSequenceNumber(this.getNextTargetLineNumber());
                    }
                    newLine.setDocumentHeaderId(this.getDocumentHeaderId());
                    newLine.setPostingYear(fiscalYear);
                    newLine.setChart(SpringServiceLocator.getChartService().getByPrimaryId(coa));
                    newLine.setAccount(SpringServiceLocator.getAccountService().getByPrimaryId(coa, account));
                    newLine.setSubAccount(SpringServiceLocator.getSubAccountService().getByPrimaryId(coa, account, subAccount));
                    newLine.setObjectCode(SpringServiceLocator.getObjectCodeService().getByPrimaryId(fiscalYear, coa, objectCode));
                    newLine.setSubObjectCode(SpringServiceLocator.getSubObjectCodeService().getByPrimaryId(fiscalYear, coa,
                            account, objectCode, subObjectCode));
                    newLine.setProjectCode(SpringServiceLocator.getProjectCodeService().getByPrimaryId(projectCode));
                    newLine.setAmount(amount);

                    if (isSource) {
                        this.getSourceAccountingLines().add(newLine);
                    }
                    else {
                        this.getTargetAccountingLines().add(newLine);
                    }

                    // now reset counters and line list
                    accountingLineData = new String[8];
                    fieldCount = 0;
                }
            }
        }
    }

    /**
     * For each source accounting line (income line), populate the document header with account information and organization
     * information.
     * 
     * @param flexDoc
     * @param List A list of the source lines for this document.
     */
    protected void addAttributeDefinitionsForAccountingLines(FlexDoc flexDoc, List accountingLines) {
        if (accountingLines != null) {
            Iterator accountingLinesIterator = accountingLines.iterator();
            while (accountingLinesIterator.hasNext()) {
                AccountingLineBase accountingLine = (AccountingLineBase) accountingLinesIterator.next();

                Account fetchedAccount = SpringServiceLocator.getAccountService().getByPrimaryId(
                        accountingLine.getAccount().getChartOfAccountsCode(), accountingLine.getAccount().getAccountNumber());
                flexDoc.addAttributeDefinition(new KualiAccountDefinition(fetchedAccount.getChartOfAccountsCode(), fetchedAccount
                        .getAccountNumber(), getTotalDollarAmount().toString()));
                flexDoc.addAttributeDefinition(new KualiOrgReviewDefinition(fetchedAccount.getChartOfAccountsCode(), fetchedAccount
                        .getOrganizationCode()));
            }
        }
    }

    /**
     * method to create copy of current document with option to invert accounting line amounts
     * 
     * @param isErrorCorrection
     */
    public void makeCopy(boolean isErrorCorrection) throws Exception {
        /*
         * get a new doc header update doc header in document as well as children (as needed) clear pending entries clear notes
         * reset version number in doc as well as accounting lines
         */

        if (isErrorCorrection
                && !(this.getDocumentHeader().getFlexDoc().getRouteHeader().getDocRouteStatus() == Constants.ROUTE_HEADER_FINAL_CD && this
                        .getDocumentHeader().getFlexDoc().getRouteHeader().getDocRouteStatus() == Constants.ROUTE_HEADER_APPROVED_CD)) {
            // throw exception, this should never be called with a doc that isnt final or approved
            throw new IllegalStateException("Error Correction was attempted without a final or approved document");
        }

        String tmpType = SpringServiceLocator.getDocumentTypeService().getNameById(
                this.getDocumentHeader().getFlexDoc().getDocumentType());
        TransactionalDocument tmpDoc = (TransactionalDocument) SpringServiceLocator.getDocumentService().getNewDocument(tmpType);
        tmpDoc.getDocumentHeader().setDocumentDescription(this.getDocumentHeader().getDocumentDescription());
        tmpDoc.getDocumentHeader().setOrganizationDocumentNumber(this.getDocumentHeader().getOrganizationDocumentNumber());
        this.documentHeader = tmpDoc.getDocumentHeader();
        this.pendingEntries.clear();
        this.documentHeader.getNotes().clear();

        if (this.getSourceAccountingLines() != null) {
            for (Iterator iter = this.getSourceAccountingLines().iterator(); iter.hasNext();) {
                AccountingLineBase sourceLine = (AccountingLineBase) iter.next();
                sourceLine.setDocumentHeaderId(getDocumentHeaderId());
                sourceLine.setVersionNumber(new Long(1));
                if (isErrorCorrection) {
                    sourceLine.setAmount(sourceLine.getAmount().multiply(new KualiDecimal(-1)));
                }
            }
        }

        if (this.getTargetAccountingLines() != null) {
            for (Iterator iter = this.getTargetAccountingLines().iterator(); iter.hasNext();) {
                AccountingLineBase targetLine = (AccountingLineBase) iter.next();
                targetLine.setDocumentHeaderId(getDocumentHeaderId());
                targetLine.setVersionNumber(new Long(1));
                if (isErrorCorrection) {
                    targetLine.setAmount(targetLine.getAmount().multiply(new KualiDecimal(-1)));
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.kuali.bo.TransactionalDocument#getAllowsErrorCorrection()
     */
    public boolean getAllowsErrorCorrection() {
        //      TODO: add check in here base on data dictionary lookup - and make sure it works!!!
        if (SpringServiceLocator.getTransactionalDocumentDictionaryService().getAllowsErrorCorrection(this).booleanValue()
                && (getDocumentHeader().getKualiDocumentStatusCode().equals(Constants.ROUTE_HEADER_APPROVED_CD) || getDocumentHeader()
                        .getKualiDocumentStatusCode().equals(Constants.ROUTE_HEADER_FINAL_CD))) {
            return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.kuali.bo.TransactionalDocument#getAllowsCopy()
     */
    public boolean getAllowsCopy() {
        //      TODO: add check in here base on data dictionary lookup - and make sure it works!!!
        if (SpringServiceLocator.getTransactionalDocumentDictionaryService().getAllowsCopy(this).booleanValue()
                && (getDocumentHeader().getKualiDocumentStatusCode().equals(Constants.ROUTE_HEADER_APPROVED_CD) || getDocumentHeader()
                        .getKualiDocumentStatusCode().equals(Constants.ROUTE_HEADER_FINAL_CD))) {
            return true;
        }
        return false;
    }
}

