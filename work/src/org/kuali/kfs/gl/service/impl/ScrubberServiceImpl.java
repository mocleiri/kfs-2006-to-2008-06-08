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
package org.kuali.module.gl.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.kuali.Constants;
import org.kuali.KeyConstants;
import org.kuali.core.service.DateTimeService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.util.ObjectUtils;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.module.chart.bo.AccountingPeriod;
import org.kuali.module.chart.bo.ObjectCode;
import org.kuali.module.chart.bo.OffsetDefinition;
import org.kuali.module.chart.bo.ProjectCode;
import org.kuali.module.chart.bo.SubObjCd;
import org.kuali.module.chart.bo.codes.BalanceTyp;
import org.kuali.module.chart.service.AccountService;
import org.kuali.module.chart.service.ChartService;
import org.kuali.module.chart.service.ObjectCodeService;
import org.kuali.module.chart.service.OffsetDefinitionService;
import org.kuali.module.gl.bo.OriginEntry;
import org.kuali.module.gl.bo.OriginEntryGroup;
import org.kuali.module.gl.bo.OriginEntrySource;
import org.kuali.module.gl.bo.UniversityDate;
import org.kuali.module.gl.dao.UniversityDateDao;
import org.kuali.module.gl.service.OriginEntryService;
import org.kuali.module.gl.service.ScrubberService;
import org.springframework.util.StringUtils;

/**
 * This class does all that and more... 
 * 
 * @author Anthony Potts
 */
public class ScrubberServiceImpl implements ScrubberService {

    private OriginEntryService originEntryService;
    private DateTimeService dateTimeService;
    private OffsetDefinitionService offsetDefinitionService;
    private ObjectCodeService objectCodeService;
    private ChartService chartService;
    private AccountService accountService;
    private KualiConfigurationService kualiConfigurationService;
    private UniversityDateDao universityDateDao;
    private Date runDate;
    private Calendar runCal;
    UniversityDate univRunDate;
    Collection groupsToScrub;
    OriginEntryGroup validGroup;
    OriginEntryGroup errorGroup;
    OriginEntryGroup expiredGroup;
    Map documentError;
    Map reportSummary;
    List transactionErrors;

    private OriginEntry previousEntry;
    private Calendar wsPreviousCal;
    private String wsAccountChange;
    private String wsFundGroupCode;
    private String wsSubAcctTypeCode;
    private String wsExpiredChart;
    private String wsExpiredAccount;
    private boolean eof;
    
    private int writeSwitchStatusCD = 0;

    private ScrubberUtil scrubberUtil = new ScrubberUtil();

    
//    MessageResources messages = getResources(SpringServiceLocator.KUALI_CONFIGURATION_SERVICE);
    
    public void scrubEntries() {
        documentError = new HashMap();
        reportSummary = new HashMap();
        
        // setup an object to hold the "default" date information
        runDate = new Date(dateTimeService.getCurrentDate().getTime());
        univRunDate = universityDateDao.getByPrimaryKey(runDate);
        runCal = Calendar.getInstance();
        runCal.setTime(runDate);
        checkGLObject(univRunDate, kualiConfigurationService.getPropertyString(KeyConstants.ERROR_UNIV_DATE_NOT_FOUND));

        // Create the groups that will store the valid and error entries that come out of the scrubber
        validGroup = originEntryService.createGroup(runDate, OriginEntrySource.SCRUBBER_VALID, true, false, false);
        errorGroup = originEntryService.createGroup(runDate, OriginEntrySource.SCRUBBER_ERROR, false, false, false);
        expiredGroup = originEntryService.createGroup(runDate, OriginEntrySource.SCRUBBER_ERROR, false, false, false);

        groupsToScrub = originEntryService.getGroupsToScrub(runDate);

        for (Iterator groupIterator = groupsToScrub.iterator(); groupIterator.hasNext();) {
            OriginEntryGroup grp = (OriginEntryGroup) groupIterator.next();

            for (Iterator entryIterator = originEntryService.getEntriesByGroup(grp); entryIterator.hasNext();) {
                ++scrubberUtil.originCount;
                eof = entryIterator.hasNext();
                OriginEntry entry = (OriginEntry) entryIterator.next();
                processUnitOfWork(entry, previousEntry);
            }

        }

        // write out report and errors
        // PRINT_STATISTICS
        
/*      
 *      print out final error that says what errors were found:
 *      if WS-fatal-errors-yes = yes then... "HIGHEST SEVERITY ERRORS WERE FATAL"
 *      
 *      if WS-NON-FATAL-ERRORS-YES = yes then... "HIGHEST SEVERITY ERRORS WERE NON-FATAL"
 *      
 *      if WS-WARNING-YES = yes then... "HIGHEST SEVERITY ERRORS WERE WARNINGS"
*/
        }

    private OriginEntry processUnitOfWork(OriginEntry originEntry, OriginEntry previousEntry) { /* 2500-process-unit-of-work */
        transactionErrors = new ArrayList();

        OriginEntry workingEntry = null;
        
        // first, need to see if the key fields are the same as last unit of
        // work (for performance reasons)
        checkUnitOfWork(originEntry, workingEntry);

        /*
         * //set base values set noErrors = true; set scrb_error_code = null;
         */
        workingEntry = new OriginEntry();

        workingEntry.setSubAccountNumber(replaceNullWithDashes(originEntry.getSubAccountNumber()));

        if (StringUtils.hasText(originEntry.getFinancialSubObjectCode()) && (checkGLObject(originEntry.getFinancialSubObject(), kualiConfigurationService.getPropertyString(KeyConstants.ERROR_SUB_OBJECT_CODE_NOT_FOUND)))) {
            workingEntry.setFinancialSubObjectCode(originEntry.getFinancialSubObjectCode());
            workingEntry.setFinancialSubObject(originEntry.getFinancialSubObject());
        } else {
        	workingEntry.setFinancialSubObjectCode(replaceNullWithDashes(originEntry.getFinancialSubObjectCode())); //todo: change to putDashes
        }

//WORKING ENTRY REDO        
        if (StringUtils.hasText(originEntry.getProjectCode())) {
            checkGLObject(originEntry.getProject(), kualiConfigurationService.getPropertyString(KeyConstants.ERROR_PROJECT_CODE_NOT_FOUND));
        } else {
        	workingEntry.setProjectCode(replaceNullWithDashes(originEntry.getProjectCode()));
        }

        if (originEntry.getTransactionDate() == null) {
            workingEntry.setTransactionDate(new Date(runDate.getTime()));
        } else {
            workingEntry.setTransactionDate(originEntry.getTransactionDate());
        }

        // Check transaction date against the date in univDate
        // if not equal read the univDate table with this trnsaction date.
        // if found:
        //      workEntry.setTransactionDt(univDate.getUnivDt())
        //      wsHoldPrevDate = univDate.getUnivDt();
        Calendar workingCal = Calendar.getInstance();
        workingCal.setTimeInMillis(workingEntry.getTransactionDate().getTime());
        Calendar univDateCal = Calendar.getInstance();
        univDateCal.setTimeInMillis(univRunDate.getUniversityDate().getTime());
        if (!workingCal.equals(wsPreviousCal)) {
            UniversityDate tmpDate = universityDateDao.getByPrimaryKey(workingEntry.getTransactionDate());
            if (tmpDate == null) {
                tmpDate = univRunDate;
            }
            workingEntry.setUniversityFiscalYear(tmpDate.getUniversityFiscalYear());
            workingEntry.setUniversityFiscalPeriodCode(tmpDate.getUniversityFiscalAccountingPeriod());
            wsPreviousCal = workingCal;
            // TODO: refresh option table for workingEntry
        } // TODO: what should the else do?

        if (originEntry.getUniversityFiscalYear() == null) {
            workingEntry.setUniversityFiscalYear(univRunDate.getUniversityFiscalYear());
        } else {
            workingEntry.setUniversityFiscalYear(originEntry.getUniversityFiscalYear());
        }

        if (checkGLObject(originEntry.getDocumentType(), kualiConfigurationService.getPropertyString(KeyConstants.ERROR_DOCUMENT_TYPE_NOT_FOUND))) {
            workingEntry.setFinancialDocumentTypeCode(originEntry.getFinancialDocumentTypeCode());
            workingEntry.setDocumentType(originEntry.getDocumentType());
        }

        checkGLObject(originEntry.getOrigination(), kualiConfigurationService.getPropertyString(KeyConstants.ERROR_ORIGIN_CODE_NOT_FOUND));

        if (!StringUtils.hasText(originEntry.getFinancialDocumentNumber())) {
            transactionErrors.add(kualiConfigurationService.getPropertyString(KeyConstants.ERROR_DOCUMENT_NUMBER_REQUIRED));
        }

        checkGLObject(originEntry.getChart(), kualiConfigurationService.getPropertyString(KeyConstants.ERROR_CHART_NOT_FOUND));

        if (!originEntry.getChart().isFinChartOfAccountActiveIndicator()) {
            transactionErrors.add(kualiConfigurationService.getPropertyString(KeyConstants.ERROR_CHART_NOT_ACTIVE));
        }

        checkGLObject(originEntry.getAccount(), kualiConfigurationService.getPropertyString(KeyConstants.ERROR_ACCOUNT_NOT_FOUND));

        
        if ("ACLO".equals(originEntry.getFinancialDocumentTypeCode())) { // TODO: move to properties ANNUAL_CLOSING
            resolveAccount(originEntry, workingEntry);
        } else {
            wsAccountChange = originEntry.getAccountNumber(); 
        }
        
        if (!wsAccountChange.equals(workingEntry.getAccountNumber())) {
            if (originEntry.getAccount().isAccountClosedIndicator()) {
                transactionErrors.add(kualiConfigurationService.getPropertyString(KeyConstants.ERROR_ACCOUNT_CLOSED));
            } else {
                transactionErrors.add(kualiConfigurationService.getPropertyString(KeyConstants.ERROR_ACCOUNT_EXPIRED));
            }
            
        }

        // TODO: need to specifically check for the correct number of dashes
        if (originEntry.getSubAccountNumber().indexOf("-") == 0) { // TODO: define this in constants
            checkGLObject(originEntry.getSubAccount(), kualiConfigurationService.getPropertyString(KeyConstants.ERROR_SUB_ACCOUNT_NOT_FOUND));
        }

        if (workingEntry.getSubAccount() != null && "ACLO".equals(originEntry.getFinancialDocumentTypeCode())
                && !workingEntry.getSubAccount().isSubAccountActiveIndicator()) {
            transactionErrors.add(kualiConfigurationService.getPropertyString(KeyConstants.ERROR_SUB_ACCOUNT_NOT_ACTIVE));
        }

        workingEntry.setFinancialObjectCode(replaceNullWithDashes(originEntry.getFinancialObjectCode()));
        if (StringUtils.hasText(originEntry.getFinancialObjectCode())) {
            checkGLObject(originEntry.getFinancialObject(), kualiConfigurationService.getPropertyString(KeyConstants.ERROR_OBJECT_CODE_NOT_FOUND));
        }
        
        if (originEntry.getFinancialObject() != null && StringUtils.hasText(originEntry.getFinancialObject().getFinancialObjectTypeCode())) {
            checkGLObject(originEntry.getFinancialObject().getFinancialObjectType(), kualiConfigurationService.getPropertyString(KeyConstants.ERROR_OBJECT_TYPE_NOT_FOUND));
        }

        if (StringUtils.hasText(originEntry.getFinancialSubObjectCode())) {
            checkGLObject(originEntry.getFinancialSubObject(),kualiConfigurationService.getPropertyString(KeyConstants.ERROR_SUB_OBJECT_CODE_NOT_FOUND));
            if (originEntry.getFinancialSubObject() != null && !originEntry.getFinancialSubObject().isFinancialSubObjectActiveIndicator()) {
                // if NOT active, set it to dashes
                workingEntry.setFinancialSubObjectCode("---"); // TODO: replace with constants
                workingEntry.setFinancialSubObject(null);
            }
        }

        if (StringUtils.hasText(originEntry.getFinancialBalanceTypeCode())) {
            // now validate balance type against balance type table (free)
            if (checkGLObject(originEntry.getBalanceType(), kualiConfigurationService.getPropertyString(KeyConstants.ERROR_BALANCE_TYPE_NOT_FOUND))) {
                // workingEntry.
            }
        } else {
            // if balance type of originEntry is null, get it from option table
            workingEntry.setFinancialBalanceTypeCode(originEntry.getOption().getActualFinancialBalanceTypeCd());
            // TODO: need option object to have objects not just primitives
            workingEntry.getBalanceType().setCode(originEntry.getOption().getActualFinancialBalanceTypeCd());
            workingEntry.getBalanceType().refreshReferenceObject("balanceType");
            checkGLObject(workingEntry.getBalanceType(), kualiConfigurationService.getPropertyString(KeyConstants.ERROR_BALANCE_TYPE_NOT_FOUND));
        }

        // validate fiscalperiod against sh_acct_period_t (free)
        if (StringUtils.hasText(originEntry.getUniversityFiscalPeriodCode())) {
            checkGLObject(originEntry.getAccountingPeriod(), kualiConfigurationService.getPropertyString(KeyConstants.ERROR_ACCOUNTING_PERIOD_NOT_FOUND));
        }

        // validate that the fiscalperiod is open "fiscal period closed"
        if (originEntry.getAccountingPeriod() != null && originEntry.getAccountingPeriod().getUniversityFiscalPeriodStatusCode() == Constants.ACCOUNTING_PERIOD_STATUS_CLOSED) {
            transactionErrors.add(kualiConfigurationService.getPropertyString(KeyConstants.ERROR_FISCAL_PERIOD_CLOSED));
        }

        if(originEntry.getBalanceType().isFinancialOffsetGenerationIndicator() &&
                originEntry.getTransactionLedgerEntryAmount().isNegative()) {
            transactionErrors.add(kualiConfigurationService.getPropertyString(KeyConstants.ERROR_TRANS_CANNOT_BE_NEGATIVE_IF_OFFSET));
        }
  
        // if offsetGenerationCode = "N" and debitCreditCode != null then error
        // "debit or credit indicator must be empty(space)"
        // if offsetGenerationCode = "Y" and debitCreditCode == null then error
        // "debit or credit indicator must be 'D' or 'C'"
        if(!originEntry.getBalanceType().isFinancialOffsetGenerationIndicator()) {
            if(!" ".equals(originEntry.getTransactionDebitCreditCode())) { // todo: move space to constant
                transactionErrors.add(kualiConfigurationService.getPropertyString(KeyConstants.ERROR_DC_INDICATOR_MUST_BE_EMPTY));
            }
        } else {
            if(!originEntry.isCredit() && !originEntry.isDebit()) {
                transactionErrors.add(kualiConfigurationService.getPropertyString(KeyConstants.ERROR_DC_INDICATOR_MUST_BE_D_OR_C));
            }
        }

        // if ProjectCode is inactive then error - "Project Code must be active"
        if (originEntry.getProject() != null && !originEntry.getProject().isActive()) {
            transactionErrors.add(kualiConfigurationService.getPropertyString(KeyConstants.ERROR_PROJECT_CODE_MUST_BE_ACTIVE));
        }

        // if DocReferenceNumber == null then
        // documentReferenceTypeCode = null
        // FSReferenceOriginCode = null
        // if transactionEncumbranceUpdateCode = "R" then
        // error "reference document number cannot be null if update code is 'R'"
        // else
        // validate documentReferenceNumber - "documentreference number is not in DB"
        // validate documentReferenceTypeCode - "reference document type is not
        // in document type table"
        // validate FSRefOriginCode - "FS origin code is not in DB"
        if (StringUtils.hasText(originEntry.getFinancialDocumentReferenceNbr())) {
            if (checkGLObject(originEntry.getReferenceDocumentType(), kualiConfigurationService.getPropertyString(KeyConstants.ERROR_REFERENCE_DOCUMENT_TYPE_NOT_FOUND))) {
                workingEntry.setReferenceFinDocumentTypeCode(originEntry.getReferenceFinDocumentTypeCode());
                workingEntry.setReferenceDocumentType(originEntry.getReferenceDocumentType());
            }
            if (checkGLObject(originEntry.getFinSystemRefOriginationCode(), kualiConfigurationService.getPropertyString(KeyConstants.ERROR_REFERENCE_ORIGINATION_CODE_NOT_FOUND))) {
                workingEntry.setFinSystemRefOriginationCode(originEntry.getFinancialSystemOriginationCode());
            }
        } else {
            workingEntry.setFinancialDocumentReferenceNbr(null);
            workingEntry.setReferenceFinDocumentTypeCode(null);
            workingEntry.setReferenceDocumentType(null);
            workingEntry.setFinSystemRefOriginationCode(null);

            if (originEntry.getTransactionEncumbranceUpdtCd().equals("R")) { // TODO: change to constant
                transactionErrors.add(kualiConfigurationService.getPropertyString(KeyConstants.ERROR_REFERENCE_DOC_NUMBER_CANNOT_BE_NULL_IF_UPDATE_CODE_IS_R));
            }
        }

        // if reversalDate != null
        // validate it against sh_univ_date_t - error "reversal date not in table"
        if (originEntry.getFinancialDocumentReversalDate() != null) {
            if (checkGLObject(originEntry.getReversalDate(), kualiConfigurationService.getPropertyString(KeyConstants.ERROR_REVERSAL_DATE_NOT_FOUND))) {
                workingEntry.setFinancialDocumentReversalDate(originEntry.getFinancialDocumentReversalDate());
                workingEntry.setReversalDate(originEntry.getReversalDate());
            }
        }

        // if balanceTypeEncumberanceCode = "Y" AND fundBalanceCode != "Y" AND
        // (encumberanceUpdateCode != "D" and "R" and "N")
        // "The encumberance update code is not equal D R or N"
        if (originEntry.getBalanceType() != null && originEntry.getBalanceType().isFinBalanceTypeEncumIndicator() &&
                !originEntry.getObjectType().isFundBalanceIndicator()) {
            if (originEntry.getTransactionEncumbranceUpdtCd().equals("D") ||
                        originEntry.getTransactionEncumbranceUpdtCd().equals("R") ||
                        originEntry.getTransactionEncumbranceUpdtCd().equals("N")) {
                workingEntry.setTransactionEncumbranceUpdtCd(originEntry.getTransactionEncumbranceUpdtCd());
            } else {
                transactionErrors.add(kualiConfigurationService.getPropertyString(KeyConstants.ERROR_ENC_UPDATE_CODE_NOT_DRN));
            }
        }
        
        // if (ObjectTypeCode = "EE" or "EX" or "ES" or "TE") AND
        // (BalanceTypeCode = "AC" or "EX" or "IE" or "PE") //todo: move to properties
        //  retrieve and validate that subFundGroup exists - "subFundGroup not in table"
        //  (put into working storage field)
        if ((originEntry.getFinancialObjectTypeCode().equals(originEntry.getOption().getFinObjTypeExpenditureexpCd()) ||
                originEntry.getFinancialObjectTypeCode().equals(originEntry.getOption().getFinObjTypeExpNotExpendCode()) ||
                originEntry.getFinancialObjectTypeCode().equals(originEntry.getOption().getFinObjTypeExpendNotExpCode()) ||
                originEntry.getFinancialObjectTypeCode().equals("TE")) &&
                (originEntry.getFinancialBalanceTypeCode().equals(originEntry.getOption().getActualFinancialBalanceTypeCd()) ||
                originEntry.getFinancialBalanceTypeCode().equals(originEntry.getOption().getExtrnlEncumFinBalanceTypCd()) ||
                originEntry.getFinancialBalanceTypeCode().equals(originEntry.getOption().getIntrnlEncumFinBalanceTypCd()) ||
                originEntry.getFinancialBalanceTypeCode().equals(originEntry.getOption().getPreencumbranceFinBalTypeCd()))) {
            if (checkGLObject(originEntry.getAccount().getSubFundGroup(), kualiConfigurationService.getPropertyString(KeyConstants.ERROR_SUB_FUND_GROUP_NOT_FOUND))) {
                wsFundGroupCode = originEntry.getAccount().getSubFundGroupCode();
            }

            // if (workingSubFundGroupCode = "CG") //todo: move to properties
            //  retrieve and validate that ca_a21_sub_acct_t exists else nulls it out
            if ("CG".equals(originEntry.getAccount().getSubFundGroupCode())) { // TODO: move to constant
/*                if (checkGLObject(originEntry.getA21(), "a21 not found in table")) {
                    wsSubAcctTypeCode = originEntry.getA21().getSubAcctTypeCode();
                    wsCostChareAcctCode = workingEntry.getAccountNumber();
                } else {
                    wsSubAcctTypeCode = null;
                }
*/          
            } else {
                wsSubAcctTypeCode = null;
            }
        } else {
            wsFundGroupCode = null;
        }
        
        
        // clone originEntry into costSharingEntry
        
        // if offsetGenerationCode = "Y" AND DocumentType = "ACLO" (annual
        // closing) AND UniversityFiscalPeriod != "BB" (beginning balance) AND
        // UniversityFiscalPeriod != "CB" (contract balance)
        // if TransactionDebitCreditCode = "D"
        //  add amount to offsetAmountAccumulator
        //  add to debitAmountAccumulator
        // else
        //  subtract amount from offsetAmountAccumulator
        //  add to creditAmountAccumulator
        if (originEntry.getBalanceType().isFinancialOffsetGenerationIndicator() &&
                !"BB".equals(originEntry.getFinancialDocumentTypeCode()) &&
                !"CB".equals(originEntry.getUniversityFiscalPeriodCode()) &&
                !originEntry.getFinancialDocumentTypeCode().equals("ACLO")) {
            if (originEntry.isDebit()) {
                scrubberUtil.offsetAmountAccumulator.add(originEntry.getTransactionLedgerEntryAmount());
            } else {
                scrubberUtil.offsetAmountAccumulator.subtract(originEntry.getTransactionLedgerEntryAmount());
            }
        }
        if (originEntry.isDebit()) {
            scrubberUtil.debitAmountAccumulator.add(originEntry.getTransactionLedgerEntryAmount());
        } else {
            scrubberUtil.creditAmountAccumulator.add(originEntry.getTransactionLedgerEntryAmount());
        }
        
        // if (ObjectTypeCode = "EE" or "EX" or "ES" or "TE") AND
        //  (BalanceTypeCode = "EX" or "IE" or "PE") AND (holdFundGroupCD = "CG")
        //  AND (holdSubAccountTypeCD == "CS") AND UniversityFiscalPeriod != "BB"
        //  (beginning balance) AND UniversityFiscalPeriod != "CB" (contract
        //  balance) AND DocumentTypeCD != "JV" and != "AA" //todo: move to properties
        //      DO COST SHARING! (move into separate method)
        if ((originEntry.getOption().getFinObjTypeExpenditureexpCd().equals(workingEntry.getFinancialObjectTypeCode()) ||
        		originEntry.getOption().getFinObjTypeExpNotExpendCode().equals(workingEntry.getFinancialObjectTypeCode()) ||
        		originEntry.getOption().getFinObjTypeExpendNotExpCode().equals(workingEntry.getFinancialObjectTypeCode()) ||
                "TE".equals(workingEntry.getFinancialObjectTypeCode())) &&
                "CG".equals(wsFundGroupCode) &&
                "CS".equals(wsSubAcctTypeCode) &&
                !"BB".equals(originEntry.getUniversityFiscalPeriodCode()) &&
                !"CB".equals(originEntry.getUniversityFiscalPeriodCode()) &&
                !"JV".equals(originEntry.getFinancialDocumentTypeCode()) &&
                !"AA".equals(originEntry.getFinancialDocumentTypeCode())) {
            if (originEntry.getOption().getExtrnlEncumFinBalanceTypCd().equals(workingEntry.getFinancialBalanceTypeCode()) ||
            		originEntry.getOption().getIntrnlEncumFinBalanceTypCd().equals(workingEntry.getFinancialBalanceTypeCode()) ||
            		originEntry.getOption().getPreencumbranceFinBalTypeCd().equals(workingEntry.getFinancialBalanceTypeCode())) {
                // Do cost sharing!
                costShareEncumbrance(originEntry);
            }
            // if (ObjectTypeCode = "EE" or "EX" or "ES" or "TE") AND
            // (BalanceTypeCode = "AC") AND (holdFundGroupCD = "CG") AND
            // (holdSubAccountTypeCD == "CS") AND UniversityFiscalPeriod != "BB"
            // (beginning balance) AND UniversityFiscalPeriod != "CB" (contract
            // balance) AND DocumentTypeCD != "JV" and != "AA" //todo: move to properties
            //  if (debitCreditCD = "D")
            //      subtract amount from costSharingAccumulator
            //  else
            //      add amount to costSharingAccumulator
            if (originEntry.getOption().getActualFinancialBalanceTypeCd().equals(workingEntry.getFinancialBalanceTypeCode())) {
                if (workingEntry.isDebit()) {
                    scrubberUtil.costSharingAccumulator.subtract(originEntry.getTransactionLedgerEntryAmount());
                } else {
                    scrubberUtil.costSharingAccumulator.add(originEntry.getTransactionLedgerEntryAmount());
                }
            }
            
        }


        // if no entry errors
        //  write out the workingEntry to table
        // if no document errors and DocumentTypeCd != "JV"
        //  3000-user-processing
        // if expiredFinCOACd != null //where is this from!?
        // 
        // if costSharingAccumulator != 0
        //  3000-cost-share
        // else
        //  write out errors
        // return!
        
        this.writeSwitchStatusCD = ScrubberUtil.FROM_WRITE;
        if (transactionErrors.size() > 0) {
            // copy all the errors for this entry to the main error list
            documentError.put(originEntry, transactionErrors);

            // write this entry as a scrubber error
            createOutputEntry(workingEntry, errorGroup);

            writeErrors();
        } else {
            // write this entry as a scrubber valid
            createOutputEntry(workingEntry, validGroup);

            if (documentError.size() == 0 && !"JV".equals(workingEntry.getFinancialDocumentTypeCode())) {
                userProcessing(workingEntry);
            }

            if (wsExpiredAccount != null) {
                OriginEntry expiredEntry = (OriginEntry)ObjectUtils.deepCopy(workingEntry);
                expiredEntry.setChartOfAccountsCode(wsExpiredChart);
                expiredEntry.setAccountNumber(wsExpiredAccount);
                createOutputEntry(expiredEntry, expiredGroup);
            }
            
            if (scrubberUtil.costSharingAccumulator.isNegative() || scrubberUtil.costSharingAccumulator.isPositive()) {
                costShare(workingEntry);
            }
        }
        return workingEntry;
    }// End of method
    
    private String replaceNullWithDashes(String input) {
        return (!StringUtils.hasText(input)) ? "---" : input;
        // todo - get the dashes text from the settings file
    }

    private boolean checkGLObject(Object glObject, String errorMessage) {
        if (glObject == null) {
            if (StringUtils.hasText(errorMessage)) {
                transactionErrors.add(errorMessage);
            } else {
                transactionErrors.add("The following object is null: " + glObject.getClass().getName());
            }
            return false;
        }
        return true;
    }

    private void resolveAccount(OriginEntry originEntry, OriginEntry workingEntry) { /* (2100-edit-account) */

        scrubberUtil.wsAccount = originEntry.getAccount();
        
        // Assume we have the current CA_ACCOUNT_T row.
        // if(account.getAcctExpirationDt() == null &&
        //           !account.getAcctClosedInd.equals("Y")) {
        if (scrubberUtil.wsAccount.getAccountExpirationDate() == null &&
           !scrubberUtil.wsAccount.isAccountClosedIndicator()) {
            workingEntry.setAccountNumber(scrubberUtil.wsAccount.getAccountNumber());
            return;
        }

        // Check to see if the FS-ORIGIN-CD is numeric or equal to EU or equal to PL
        // and the Account is not closed.
        if ((org.apache.commons.lang.StringUtils.isNumeric(workingEntry.getFinancialSystemOriginationCode()) ||
                "EU".equals(workingEntry.getFinancialSystemOriginationCode()) ||
                "PL".equals(workingEntry.getFinancialSystemOriginationCode())) &&
                scrubberUtil.wsAccount.isAccountClosedIndicator()) {
            workingEntry.setAccountNumber(originEntry.getAccountNumber());
            wsAccountChange = workingEntry.getAccountNumber();
            transactionErrors.add(kualiConfigurationService.getPropertyString(KeyConstants.ERROR_ORIGIN_CODE_CANNOT_HAVE_CLOSED_ACCOUNT));
            return;
        }

        // TODO: BalanceType and ObjectType are in a table based on fiscal year!!! use that
        if ((org.apache.commons.lang.StringUtils.isNumeric(workingEntry.getFinancialSystemOriginationCode()) ||
                "EU".equals(workingEntry.getFinancialSystemOriginationCode()) ||
                "PL".equals(workingEntry.getFinancialSystemOriginationCode()) ||
                originEntry.getOption().getExtrnlEncumFinBalanceTypCd().equals(workingEntry.getFinancialBalanceTypeCode()) ||
                originEntry.getOption().getIntrnlEncumFinBalanceTypCd().equals(workingEntry.getFinancialBalanceTypeCode()) ||
                originEntry.getOption().getPreencumbranceFinBalTypeCd().equals(workingEntry.getFinancialBalanceTypeCode()) ||
                "TOPS".equals(workingEntry.getFinancialDocumentTypeCode()) ||
                "CD".equals(workingEntry.getFinancialDocumentTypeCode().trim()) ||
                "LOCR".equals(workingEntry.getFinancialDocumentTypeCode())) &&
                !scrubberUtil.wsAccount.isAccountClosedIndicator()) {
            workingEntry.setAccountNumber(originEntry.getAccountNumber());
            return;
        }
        
        Calendar tmpCal = Calendar.getInstance();
        tmpCal.setTimeInMillis(scrubberUtil.wsAccount.getAccountExpirationDate().getTime());
        
        if(tmpCal.get(Calendar.DAY_OF_YEAR) <= runCal.get(Calendar.DAY_OF_YEAR) ||
                scrubberUtil.wsAccount.isAccountClosedIndicator()) {
            testExpiredCg(originEntry, workingEntry);
        } else {
            workingEntry.setAccountNumber(originEntry.getAccountNumber());
        }
    }// End of method

    /**
     * The purpose of this method is to determine wether or not an offset entry should
     * be generated. It uses the "unit of work to make this decision. The unit of work
     * is made up of the following fields document type code, origin code, document number, 
     * chart of accounts code, accountnumber, subaccount number, balance type, dcoument 
     * reversal date, fiscal period. If the unit of work for the current transaction is
     * different than the unit of work of the previous transaction and the offset
     * accumulator is not equal to zero then an offset should be generated. Note, offsets
     * will not be generated for journal vouchers or if there were document errors. The
     * actual offset transaction is built in the method 3000-offset and then written
     * to the database in this method.
     *  
     * This method is also responsible for initializing any work field used in the scrubber.
     * 
     * @param originEntry
     * @param workingEntry
     */
    private void checkUnitOfWork(OriginEntry originEntry, OriginEntry workingEntry) { /* 2510-check-uow */
        //See if the unit of work has changed, if not return.

        if (previousEntry == null) {
            previousEntry = new OriginEntry();
        }
        
        if(!eof && originEntry.getFinancialDocumentTypeCode().equals(previousEntry.getFinancialDocumentTypeCode()) &&
                    originEntry.getFinancialSystemOriginationCode().equals(previousEntry.getFinancialSystemOriginationCode()) &&
                    originEntry.getFinancialDocumentNumber().equals(previousEntry.getFinancialDocumentNumber()) &&
                    originEntry.getChartOfAccountsCode().equals(previousEntry.getChartOfAccountsCode()) &&
                    originEntry.getAccountNumber().equals(previousEntry.getAccountNumber()) &&
                    originEntry.getSubAccountNumber().equals(previousEntry.getSubAccountNumber()) &&
                    originEntry.getFinancialBalanceTypeCode().equals(previousEntry.getFinancialBalanceTypeCode()) &&
                    originEntry.getFinancialDocumentReversalDate().equals(previousEntry.getFinancialDocumentReversalDate()) &&
                    originEntry.getUniversityFiscalPeriodCode().equals(previousEntry.getUniversityFiscalPeriodCode())) {
            return;
        }

        if (scrubberUtil.originCount == 1) {
            copyPrimaryFields(originEntry, previousEntry);
            initScrubberValues();
            return;
        }

        // TODO: Address claim on cash here
        
        // Check scrbOffsetAmount to see if an offset needs to be generated.
        if(scrubberUtil.offsetAmountAccumulator.isNegative() && scrubberUtil.offsetAmountAccumulator.isPositive() &&
                documentError.size() == 0 &&
                !"JV".equals(workingEntry.getFinancialDocumentTypeCode())) {
            generateOffset(workingEntry);
            this.writeSwitchStatusCD = ScrubberUtil.FROM_OFFSET;
            
            if (transactionErrors.size() == 0) {
                createOutputEntry(workingEntry, validGroup);                
            } else {
                createOutputEntry(workingEntry, errorGroup);
                writeErrors();
            }
            
            initScrubberValues();
            
            return;
        }

    }// End of method

    private void copyPrimaryFields(OriginEntry fromEntry, OriginEntry toEntry) {
        toEntry.setChartOfAccountsCode(fromEntry.getChartOfAccountsCode());
        toEntry.setFinancialDocumentTypeCode(fromEntry.getFinancialDocumentTypeCode());
        toEntry.setFinancialSystemOriginationCode(fromEntry.getFinancialSystemOriginationCode());
        toEntry.setFinancialDocumentNumber(fromEntry.getFinancialDocumentNumber());
        toEntry.setAccountNumber(fromEntry.getAccountNumber());
        toEntry.setSubAccountNumber(fromEntry.getSubAccountNumber());
        toEntry.setFinancialBalanceTypeCode(fromEntry.getFinancialBalanceTypeCode());
        toEntry.setFinancialDocumentReversalDate(fromEntry.getFinancialDocumentReversalDate());
        toEntry.setUniversityFiscalPeriodCode(fromEntry.getUniversityFiscalPeriodCode());
    }

    private void userProcessing(OriginEntry workingEntry) { // 3000-USER-PROCESSING
        String tmpObjectCode = workingEntry.getFinancialObjectCode();
        String tmpObjectTypeCode = workingEntry.getFinancialObjectTypeCode();
        String tmpDebitOrCreditCode = workingEntry.getTransactionDebitCreditCode();
        String tmpDescription = workingEntry.getTransactionLedgerEntryDesc();
        String tmpAccountNumber = workingEntry.getAccountNumber();
        String tmpSubAccountNumber = workingEntry.getSubAccountNumber();
        String tmpCOA = workingEntry.getChartOfAccountsCode();

        boolean performCap = true;
        boolean performLiability = true;
        boolean performPlant = true;
        
        if ( workingEntry.getFinancialBalanceTypeCode().equals(workingEntry.getOption().getActualFinancialBalanceTypeCd())
                && performCap
                && !"TF".equals(workingEntry.getFinancialDocumentTypeCode())
                && !"YETF".equals(workingEntry.getFinancialDocumentTypeCode())
                && !"AV".equals(workingEntry.getFinancialDocumentTypeCode())
                && !"AVAC".equals(workingEntry.getFinancialDocumentTypeCode())
                && !"AVAE".equals(workingEntry.getFinancialDocumentTypeCode())
                && !"AVRC".equals(workingEntry.getFinancialDocumentTypeCode())
                && (!"BB".equals(workingEntry.getUniversityFiscalPeriodCode())
                        && !"CB".equals(workingEntry.getUniversityFiscalPeriodCode())
                        && !"ACLO".equals(workingEntry.getFinancialDocumentTypeCode()))
                && ("AM".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                        || "AF".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                        || "BD".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                        || "BF".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                        || "BI".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                        || "BR".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                        || "BX".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                        || "BY".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                        || "CM".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                        || "CF".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                        || "C1".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                        || "C2".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                        || "C3".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                        || "ES".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                        || "IF".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                        || "LA".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                        || "LE".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                        || "LF".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                        || "LI".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                        || "LR".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                        || "UC".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                        || "UF".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode()))
                && !"EXTAGY".equals(workingEntry.getAccount().getSubFundGroupCode())
                && !"HO".equals(workingEntry.getChartOfAccountsCode())) {
            this.writeSwitchStatusCD = ScrubberUtil.FROM_CAMS;
            if ("AM".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())) { // ART_AND_MUSEUM
                workingEntry.setFinancialObjectCode("8615"); // ART_AND_MUSEUM_OBJECTS
/*
 *      REMOVED PER STERLINGS OK
 *      
 *             } else if ("AF".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())) {
                workingEntry.setObjectCode("8616"); // ???

*/
            } else if ("BD".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())) { // BUILDING
                workingEntry.setFinancialObjectCode("8601"); // INSTITUTIONAL_PLANT_BLDG
            } else if ("BF".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())) { // BUILDING_AND_ATTACHED_FIXT_FEDERAL_FUNDED
                workingEntry.setFinancialObjectCode("8605"); // PLANT_BUILDING_FEDERAL_FUNDED
            } else if ("BI".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())) { // BOND_INSURANCE
                workingEntry.setFinancialObjectCode("8629"); // BOND_ISSUANCE_EXPENSE
            } else if ("BR".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())) { // BUILDING_IMPROVEMENTS_AND_RENOVATIONS
                workingEntry.setFinancialObjectCode("8601"); // INSTITUTIONAL_PLANT_BLDG
            } else if ("BX".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())) { // BUILDING_IMPROVEMENTS_AND RENOVATIONS_FEDERALLY_FUNDED
                workingEntry.setFinancialObjectCode("8640"); // ???
            } else if ("BY".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())) { // BUILDING_IMPROVEMENTS_AND RENOVATIONS_FEDERALLY_OWNED
                workingEntry.setFinancialObjectCode("8641"); // ???
            } else if ("CM".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())) { // CAPITAL_MOVEABLE_EQUIPMENT
                workingEntry.setFinancialObjectCode("8610"); // CAPITAL_EQUIPMENT
            } else if ("CF".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())) { // CAPITAL_MOVEABLE_EQUIPMENT_FEDERALLY_FUNDED
                workingEntry.setFinancialObjectCode("8611"); // CAP_EQUIP_FED_FUNDING
            } else if ("C1".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())) { // CAPITAL_LEASE_CAPITAL_THREASHOLD_1
                workingEntry.setFinancialObjectCode("8627"); // ?
            } else if ("C2".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())) { // CAPITAL_LEASE_CAPITAL_THREASHOLD_2
                workingEntry.setFinancialObjectCode("8628"); // BOND_ISSUANCE_EXPENSE
            } else if ("C3".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())) { // CAPITAL_LEASE_DEBT_BELOW_CAPITAL_THRESHHOLD
                workingEntry.setFinancialObjectCode("9607"); // BOND_ISSUANCE_EXPENSE
            } else if ("ES".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())) { // EQUIPMENT_STARTUP_COSTS
                workingEntry.setFinancialObjectCode("8630"); // EQUIPMENT_START_UP
            } else if ("IF".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())) { // INFRASTRUCTURE
                workingEntry.setFinancialObjectCode("8604"); // INST_PLANT_INFRASTRUCTURE
            } else if ("LA".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())) { // LAND
                workingEntry.setFinancialObjectCode("8603"); // INSTITUTIONAL_PLANT_LAND
            } else if ("LE".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())) { // LEASEHOLD_IMPROVEMENTS
                workingEntry.setFinancialObjectCode("8608"); // LEASEHOLD_IMPROVEMENTS_OBJ
            } else if ("LF".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())) { // LIBRARY_AQUISITIONS_FEDERALLY_FUNDED
                workingEntry.setFinancialObjectCode("8614"); // LIBRARY_FED_FUNDING
            } else if ("LI".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())) { // LIBRARY_ACQUISITIONS
                workingEntry.setFinancialObjectCode("8613"); // LIBRARY
            } else if ("LR".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())) { // LAND_IMPROVEMENTS
                workingEntry.setFinancialObjectCode("8665"); // ???
            } else if ("UC".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())) { // UNIVERSITY_CONSTRUCTED
                workingEntry.setFinancialObjectCode("8618"); // UNIVER_EQUIP_UNDER_CONST
            } else if ("UF".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())) { // UNIVERSITY_CONSTRUCTION_FEDERALLY_FUNDED
                workingEntry.setFinancialObjectCode("8619"); // EQUIP_UNDER_CONST_FED_FUNDE
            }
            workingEntry.setFinancialObjectTypeCode("AS"); // TODO: constant 
            workingEntry.setTransactionLedgerEntryDesc("GENERATED CAPITALIZATION"); // TODO: move to parameter 
            plantFundAccountLookup(workingEntry, tmpCOA, tmpAccountNumber);
            createOutputEntry(workingEntry, validGroup);
            
            workingEntry.setFinancialObjectCode("9899"); // FUND_BALANCE TODO: constant
            workingEntry.setFinancialObjectTypeCode("FB"); // FUND_BALANCE TODO: constant
            
            if (workingEntry.isDebit()) {
                workingEntry.setTransactionDebitCreditCode(Constants.GL_CREDIT_CODE);
            } else {
                workingEntry.setTransactionDebitCreditCode(Constants.GL_DEBIT_CODE);
            }
            createOutputEntry(workingEntry, validGroup);
        }
        
        if ( workingEntry.getFinancialBalanceTypeCode().equals(workingEntry.getOption().getActualFinancialBalanceTypeCd())
                && performLiability
                && !"TF".equals(workingEntry.getFinancialDocumentTypeCode())
                && !"YETF".equals(workingEntry.getFinancialDocumentTypeCode())
                && !"AV".equals(workingEntry.getFinancialDocumentTypeCode())
                && !"AVAC".equals(workingEntry.getFinancialDocumentTypeCode())
                && !"AVAE".equals(workingEntry.getFinancialDocumentTypeCode())
                && !"AVRC".equals(workingEntry.getFinancialDocumentTypeCode())
                && (!"BB".equals(workingEntry.getUniversityFiscalPeriodCode())
                        && !"CB".equals(workingEntry.getUniversityFiscalPeriodCode())
                        && !"ACLO".equals(workingEntry.getFinancialDocumentTypeCode()))
                && "CL".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                && !"EXTAGY".equals(workingEntry.getAccount().getSubFundGroupCode())
                && !"HO".equals(workingEntry.getChartOfAccountsCode())) {
            workingEntry.setFinancialObjectCode("9603"); // NOTES_PAYABLE_CAPITAL_LEASE TODO: constant
            this.writeSwitchStatusCD = ScrubberUtil.FROM_LIAB;
            workingEntry.setFinancialObjectTypeCode("LI"); // LIABILITY TODO: constant
            workingEntry.setTransactionDebitCreditCode(tmpDebitOrCreditCode);
            workingEntry.setTransactionLedgerEntryDesc("GENERATED LIABILITY"); // TODO: constant
            plantFundAccountLookup(workingEntry, tmpCOA, tmpAccountNumber);
            createOutputEntry(workingEntry, validGroup);
            
            workingEntry.setFinancialObjectCode("9899"); // FUND_BALANCE TODO: constant
            workingEntry.setFinancialObjectTypeCode("FB"); // FUND_BALANCE TODO: constant
            
            if (workingEntry.isDebit()) {
                workingEntry.setTransactionDebitCreditCode(Constants.GL_CREDIT_CODE);
            } else {
                workingEntry.setTransactionDebitCreditCode(Constants.GL_DEBIT_CODE);
            }
            createOutputEntry(workingEntry, validGroup);
        }
        
        workingEntry.setFinancialObjectCode(tmpObjectCode);
        workingEntry.setFinancialObjectTypeCode(tmpObjectTypeCode);
        workingEntry.setTransactionDebitCreditCode(tmpDebitOrCreditCode);
        workingEntry.setTransactionLedgerEntryDesc(tmpDescription);
        workingEntry.setAccountNumber(tmpAccountNumber);
        workingEntry.setSubAccountNumber(tmpSubAccountNumber);

        if (workingEntry.getFinancialBalanceTypeCode().equals(workingEntry.getOption().getFinObjectTypeFundBalanceCd())
                && ("PFCMR".equals(workingEntry.getAccount().getSubFundGroupCode())
                        || "PFRI".equals(workingEntry.getAccount().getSubFundGroupCode()))
                && "PI".equals(workingEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                && performPlant) {
            this.writeSwitchStatusCD = ScrubberUtil.FROM_PLANT_INDEBTEDNESS;

            workingEntry.setTransactionLedgerEntryDesc("GENERATED TRANSFER TO NET PLANT");
            if (workingEntry.isDebit()) {
                workingEntry.setTransactionDebitCreditCode(Constants.GL_CREDIT_CODE);
            } else {
                workingEntry.setTransactionDebitCreditCode(Constants.GL_DEBIT_CODE);
            }
            createOutputEntry(workingEntry, validGroup);
            
            workingEntry.setFinancialObjectCode("9899"); // FUND_BALANCE TODO: constant
            workingEntry.setFinancialObjectTypeCode("FB"); // FUND_BALANCE TODO: constant
            workingEntry.setTransactionDebitCreditCode(tmpDebitOrCreditCode);
            createOutputEntry(workingEntry, validGroup);

            workingEntry.setFinancialObjectCode(tmpObjectCode);
            workingEntry.setFinancialObjectTypeCode(tmpObjectTypeCode);
            workingEntry.setTransactionDebitCreditCode(tmpDebitOrCreditCode);
            workingEntry.setTransactionLedgerEntryDesc(tmpDescription);
            workingEntry.setAccountNumber(tmpAccountNumber);
            workingEntry.setSubAccountNumber(tmpSubAccountNumber);
            
            // TODO: do we need to refresh this object first?
            if (checkGLObject(workingEntry.getAccount().getOrganization(), kualiConfigurationService.getPropertyString(KeyConstants.ERROR_INVALID_ORG_CODE_FOR_PLANT_FUND))) {
                workingEntry.setAccountNumber(workingEntry.getAccount().getOrganization().getCampusPlantAccountNumber());
                workingEntry.setChartOfAccountsCode(workingEntry.getAccount().getOrganization().getCampusPlantChartCode());
            }

            workingEntry.setSubAccountNumber("----"); // TODO: constant
            workingEntry.setTransactionLedgerEntryDesc(tmpCOA + tmpAccountNumber + "GENERATED PLANT FUND TRANSFER");
            createOutputEntry(workingEntry, validGroup);

            workingEntry.setFinancialObjectCode("9899"); // FUND_BALANCE TODO: constant
            workingEntry.setFinancialObjectTypeCode("FB"); // FUND_BALANCE TODO: constant
            
            if (workingEntry.isDebit()) {
                workingEntry.setTransactionDebitCreditCode(Constants.GL_CREDIT_CODE);
            } else {
                workingEntry.setTransactionDebitCreditCode(Constants.GL_DEBIT_CODE);
            }
            createOutputEntry(workingEntry, validGroup);

        }

        workingEntry.setFinancialObjectCode(tmpObjectCode);
        workingEntry.setFinancialObjectTypeCode(tmpObjectTypeCode);
        workingEntry.setTransactionDebitCreditCode(tmpDebitOrCreditCode);
        workingEntry.setTransactionLedgerEntryDesc(tmpDescription);
        workingEntry.setAccountNumber(tmpAccountNumber);
        workingEntry.setSubAccountNumber(tmpSubAccountNumber);
        workingEntry.setChartOfAccountsCode(tmpCOA);
        
    }// End of method

    private void plantFundAccountLookup(OriginEntry inputEntry, String tmpChart, String tmpAccount) { // 4000-PLANT_FUND_ACCOUNT
        inputEntry.setSubAccountNumber("----"); //TODO: constant
        if (inputEntry.getChartOfAccountsCode().equals(inputEntry.getAccount().getOrganization().getChartOfAccountsCode())
                && inputEntry.getAccount().getOrganizationCode() == inputEntry.getAccount().getOrganization().getOrganizationCode()
                && tmpChart.equals(inputEntry.getAccount().getChartOfAccountsCode())
                && tmpAccount.equals(inputEntry.getAccount().getAccountNumber())) {
            if ("AM".equals(inputEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                    || "AF".equals(inputEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                    || "BD".equals(inputEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                    || "BF".equals(inputEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                    || "BI".equals(inputEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                    || "BR".equals(inputEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                    || "BX".equals(inputEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                    || "BY".equals(inputEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                    || "IF".equals(inputEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                    || "LA".equals(inputEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                    || "LE".equals(inputEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                    || "LF".equals(inputEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                    || "LI".equals(inputEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                    || "LR".equals(inputEntry.getFinancialObject().getFinancialObjectSubTypeCode())) {
                inputEntry.setAccountNumber(inputEntry.getAccount().getOrganization().getCampusPlantAccountNumber());
                inputEntry.setChartOfAccountsCode(inputEntry.getAccount().getOrganization().getCampusPlantChartCode());
            } else if ("CL".equals(inputEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                    || "CM".equals(inputEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                    || "CF".equals(inputEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                    || "C1".equals(inputEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                    || "C2".equals(inputEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                    || "C3".equals(inputEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                    || "ES".equals(inputEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                    || "UC".equals(inputEntry.getFinancialObject().getFinancialObjectSubTypeCode())
                    || "UF".equals(inputEntry.getFinancialObject().getFinancialObjectSubTypeCode())) {
                inputEntry.setAccountNumber(inputEntry.getAccount().getOrganization().getOrganizationPlantAccountNumber());
                inputEntry.setChartOfAccountsCode(inputEntry.getAccount().getOrganization().getOrganizationPlantChartCode());
            }
        }
        // In COBOL, the CA_ORG_T table is read at this time to reset the org information. I dont think this is necessary
    }// End of method
    
    private void checkCg() {
        if(scrubberUtil.wsAccount.isAccountClosedIndicator()) {
            return;
        }

        if (checkGLObject(scrubberUtil.wsAccount.getSubFundGroup(), kualiConfigurationService.getPropertyString(KeyConstants.ERROR_SUB_FUND_GROUP_NOT_FOUND))) {
            if ("CG".equals(scrubberUtil.wsAccount.getSubFundGroupCode())) {
                changeExpiration();
            }
        }
    }
    
    private void testExpiredCg(OriginEntry originEntry, OriginEntry workingEntry) {
        checkCg();

        Calendar tmpCal = Calendar.getInstance();
        tmpCal.setTimeInMillis(scrubberUtil.wsAccount.getAccountExpirationDate().getTime());
        if(tmpCal.get(Calendar.DAY_OF_YEAR) > runCal.get(Calendar.DAY_OF_YEAR) &&
                !workingEntry.getAccount().isAccountClosedIndicator()) {
            workingEntry.setAccountNumber(scrubberUtil.wsAccount.getAccountNumber());
        } else {
            getUnexpiredAccount(originEntry, workingEntry);
        }
    }

    private void getUnexpiredAccount(OriginEntry originEntry, OriginEntry workingEntry) {
        if (scrubberUtil.wsAccount != null) {
            wsExpiredChart = scrubberUtil.wsAccount.getChartOfAccountsCode(); 
            wsExpiredAccount = scrubberUtil.wsAccount.getAccountNumber(); 
        }
        
        int retValue = accountExpiration(originEntry);

        if (retValue == ScrubberUtil.ACCOUNT_LIMIT) {
            transactionErrors.add(kualiConfigurationService.getPropertyString(KeyConstants.ERROR_CONTINUATION_ACCOUNT_LIMIT_REACHED));
            return;
        }

        if (retValue == ScrubberUtil.ACCOUNT_ERROR) {
            transactionErrors.add("CONTINUATION ACCT NOT IN ACCT");
            return;
        }

        //FOUND
        workingEntry.setChartOfAccountsCode(scrubberUtil.wsAccount.getChartOfAccountsCode());
        workingEntry.setAccountNumber(scrubberUtil.wsAccount.getAccountNumber());
        originEntry.setTransactionLedgerEntryDesc("AUTO FR " + originEntry.getChartOfAccountsCode() + " " + originEntry.getAccountNumber() + " " + originEntry.getTransactionLedgerEntryDesc());

        if (!originEntry.getChartOfAccountsCode().equals(workingEntry.getChartOfAccountsCode())) {
            workingEntry.setChart(chartService.getByPrimaryId(scrubberUtil.wsAccount.getChartOfAccountsCode()));
            checkGLObject(workingEntry.getChart(), "Contiunation chart not found in table");
        }
        
     }
    
    private int accountExpiration(OriginEntry originEntry) {
        String wsContinuationChart = originEntry.getAccount().getContinuationFinChrtOfAcctCd();
        String wsContinuationAccount = originEntry.getAccount().getContinuationAccountNumber();
                
        for(int i = 0; i < 10; ++i) {
            scrubberUtil.wsAccount = accountService.getByPrimaryId(wsContinuationChart, wsContinuationAccount);

            if(scrubberUtil.wsAccount != null) {
                if(scrubberUtil.wsAccount.getAccountExpirationDate() == null) {
                    return ScrubberUtil.ACCOUNT_FOUND;
                } else {
                    checkCg();
                    Calendar tmpCal = Calendar.getInstance();
                    tmpCal.setTimeInMillis(scrubberUtil.wsAccount.getAccountExpirationDate().getTime());
                    
                    if(tmpCal.get(Calendar.DAY_OF_YEAR) <= runCal.get(Calendar.DAY_OF_YEAR)) {
                        wsContinuationChart = scrubberUtil.wsAccount.getContinuationFinChrtOfAcctCd();
                        wsContinuationAccount = scrubberUtil.wsAccount.getContinuationAccountNumber();
                    } else {
                        return ScrubberUtil.ACCOUNT_FOUND;
                    }
                }
            } else {
                return ScrubberUtil.ACCOUNT_ERROR;
            }
        }

        return ScrubberUtil.ACCOUNT_LIMIT;
    }
    
    private void changeExpiration() {
        Calendar tempCal = Calendar.getInstance();
        tempCal.setTimeInMillis(scrubberUtil.wsAccount.getAccountExpirationDate().getTime());
        tempCal.add(Calendar.MONTH, 3); //TODO: make this configurable
        scrubberUtil.wsAccount.setAccountExpirationDate(new Timestamp(tempCal.getTimeInMillis()));
    }

    private void costShare(OriginEntry workingEntry) {

        OriginEntry csEntry = (OriginEntry)ObjectUtils.deepCopy(workingEntry);

        this.writeSwitchStatusCD = ScrubberUtil.FROM_COST_SHARE;
        csEntry.setFinancialObjectCode("9915"); //TODO: TRSFRS_OF_FUNDS_REVENUE constant
        csEntry.setFinancialSubObjectCode("----"); //TODO: constant
        csEntry.setFinancialObjectTypeCode("TE"); //TODO: constant
        csEntry.setTrnEntryLedgerSequenceNumber(new Integer(0));
        csEntry.setTransactionLedgerEntryDesc("COSTSHARE_DESCRIPTION" + "***" + runCal.get(Calendar.MONTH) + "/" + runCal.get(Calendar.DAY_OF_MONTH)); // TODO: change to constant
        if (scrubberUtil.costSharingAccumulator.isPositive()) {
            csEntry.setTransactionDebitCreditCode(Constants.GL_DEBIT_CODE);
            csEntry.setTransactionLedgerEntryAmount(scrubberUtil.costSharingAccumulator);
        } else {
            csEntry.setTransactionDebitCreditCode(Constants.GL_CREDIT_CODE);
            csEntry.setTransactionLedgerEntryAmount(scrubberUtil.costSharingAccumulator.negated());
        }
        csEntry.setTransactionDate(runDate);
        csEntry.setOrganizationDocumentNumber("");
        csEntry.setProjectCode("----"); // constant
        csEntry.setOrganizationReferenceId("");
        csEntry.setReferenceFinDocumentTypeCode("");
        csEntry.setFinancialSystemOriginationCode("");
        csEntry.setFinancialDocumentReferenceNbr("");
        csEntry.setReversalDate(null);
        csEntry.setTransactionEncumbranceUpdtCd("");

        createOutputEntry(csEntry, validGroup);

        csEntry.setTransactionLedgerEntryDesc("OFFSET_DESCRIPTION" + "***" + runCal.get(Calendar.MONTH) + "/" + runCal.get(Calendar.DAY_OF_MONTH)); // TODO: change to constant

        OffsetDefinition offset = offsetDefinitionService.getByPrimaryId(
                csEntry.getUniversityFiscalYear(), csEntry.getChartOfAccountsCode(),
                "TF", csEntry.getFinancialBalanceTypeCode());
        if (offset == null) {
            transactionErrors.add("OFFSET DEFINITION NOT FOUND");
        } else {
            csEntry.setFinancialObjectCode(offset.getFinancialObjectCode());
            if(offset.getFinancialSubObjectCode() == null) {
                csEntry.setFinancialSubObjectCode("----"); // TODO: constant
            } else {
                csEntry.setFinancialSubObjectCode(offset.getFinancialSubObjectCode());
            }
        }

        ObjectCode objectCode = objectCodeService.getByPrimaryId(
                csEntry.getUniversityFiscalYear(), csEntry.getChartOfAccountsCode(),
                csEntry.getFinancialObjectCode());
        if (objectCode == null) {
            transactionErrors.add("NO OBJECT FOR OBJECT ON OFSD");
        } else {
            csEntry.setFinancialObjectTypeCode(objectCode.getFinancialObjectTypeCode());
            if(csEntry.isCredit()) {
                csEntry.setTransactionDebitCreditCode(Constants.GL_DEBIT_CODE);
            } else {
                csEntry.setTransactionDebitCreditCode(Constants.GL_CREDIT_CODE);
            }
        }

        createOutputEntry(csEntry, validGroup);
        
        csEntry.setTransactionLedgerEntryDesc("COSTSHARE_DESCRIPTION" + "***" + runCal.get(Calendar.MONTH) + "/" + runCal.get(Calendar.DAY_OF_MONTH)); // TODO: change to constant
        csEntry.setChartOfAccountsCode("");

        
/*  WAITING FOR CHART TEAM TO FINISH THIS SERVICE
        csEntry.setChartOfAccountsCode(a21SubAcct.getCstShrCoaCd());
        costShare.SetAccountNbr(a21SubAcct.getCstShrsrcacctNbr());
 */
        lookupObjectCode(csEntry);

/*
        if(a21SubAcct.getCstScrsubacctNbr() == null) {
            costShare.setSubAcctNbr(subAcctNbrDashes); // TODO: Dashes
        } else {
            costShare.setSubAcctNbr(a21SubAcct.getCstScrsubacctNbr());
        }
*/
        
        csEntry.setFinancialSubObjectCode("----"); //TODO: move into constants
        csEntry.setFinancialObjectTypeCode("TE"); //TODO: move into constants
        csEntry.setTrnEntryLedgerSequenceNumber(new Integer(0));
        csEntry.setTransactionLedgerEntryDesc("COSTSHARE_DESCRIPTION" + "***" + runCal.get(Calendar.MONTH) + "/" + runCal.get(Calendar.DAY_OF_MONTH)); // TODO: change to constant

        if (scrubberUtil.costSharingAccumulator.isPositive()) {
            csEntry.setTransactionDebitCreditCode(Constants.GL_CREDIT_CODE);
            csEntry.setTransactionLedgerEntryAmount(scrubberUtil.costSharingAccumulator);
        } else {
            csEntry.setTransactionDebitCreditCode(Constants.GL_DEBIT_CODE);
            csEntry.setTransactionLedgerEntryAmount(scrubberUtil.costSharingAccumulator.negated());
        }

        csEntry.setTransactionDate(runDate);
        csEntry.setOrganizationDocumentNumber("");
        csEntry.setProjectCode("----"); // constant
        csEntry.setOrganizationReferenceId("");
        csEntry.setReferenceFinDocumentTypeCode("");
        csEntry.setFinancialSystemOriginationCode("");
        csEntry.setFinancialDocumentReferenceNbr("");
        csEntry.setReversalDate(null);
        csEntry.setTransactionEncumbranceUpdtCd("");

        createOutputEntry(csEntry, validGroup);

        csEntry.setTransactionLedgerEntryDesc("OFFSET_DESCRIPTION" + "***" + runCal.get(Calendar.MONTH) + "/" + runCal.get(Calendar.DAY_OF_MONTH)); // TODO: change to constant

        if (offset == null) {
            transactionErrors.add("OFFSET DEFINITION NOT FOUND");
        } else {
            csEntry.setFinancialObjectCode(offset.getFinancialObjectCode());
            if(offset.getFinancialSubObjectCode() == null) {
                csEntry.setFinancialSubObjectCode("----"); // TODO: constant
            } else {
                csEntry.setFinancialSubObjectCode(offset.getFinancialSubObjectCode());
            }
        }

        objectCode = objectCodeService.getByPrimaryId(
                csEntry.getUniversityFiscalYear(), csEntry.getChartOfAccountsCode(),
                csEntry.getFinancialObjectCode());
        if (objectCode == null) {
            transactionErrors.add("NO OBJECT FOR OBJECT ON OFSD");
        } else {
            csEntry.setFinancialObjectTypeCode(objectCode.getFinancialObjectTypeCode());
            if(csEntry.isCredit()) {
                csEntry.setTransactionDebitCreditCode(Constants.GL_DEBIT_CODE);
            } else {
                csEntry.setTransactionDebitCreditCode(Constants.GL_CREDIT_CODE);
            }
        }

        createOutputEntry(csEntry, validGroup);
        scrubberUtil.costSharingAccumulator = KualiDecimal.ZERO;
    }// End of method

    private void costShareEncumbrance(OriginEntry inputEntry) {

        OriginEntry csEntry = (OriginEntry) ObjectUtils.deepCopy(inputEntry);
        this.writeSwitchStatusCD = ScrubberUtil.FROM_COST_SHARE_ENCUMBRANCE;

        csEntry.setTransactionLedgerEntryDesc(csEntry.getTransactionLedgerEntryDesc().substring(0, 29) +
                "FR" + csEntry.getChartOfAccountsCode()+ csEntry.getAccountNumber());

        //TODO: a21 service call here
/*        csEntry.setChartOfAccountsCode(a21SubAcct.getCstShrCoaCd());
        csEntry.setAccountNbr(a21SubAcct.getCstShrsrcacctNbr());
        csEntry.setSubAcctNbr(a21SubAcct.getCstSrcsubacctNbr());
*/
        if(!StringUtils.hasText(csEntry.getSubAccountNumber())) {
            csEntry.setSubAccountNumber("----"); // TODO: constant
        }

        csEntry.setFinancialBalanceTypeCode("CE"); // TODO: constant
        csEntry.setFinancialSubObjectCode("----"); //TODO: constant
        csEntry.setTrnEntryLedgerSequenceNumber(new Integer(0));
        
        if (StringUtils.hasText(inputEntry.getTransactionDebitCreditCode())) {
            if (inputEntry.getTransactionLedgerEntryAmount().isPositive()) {
                csEntry.setTransactionDebitCreditCode(Constants.GL_DEBIT_CODE);
            } else {
                csEntry.setTransactionDebitCreditCode(Constants.GL_CREDIT_CODE);
                csEntry.setTransactionLedgerEntryAmount(inputEntry.getTransactionLedgerEntryAmount().negated());
            }
        }

        csEntry.setTransactionDate(runDate);

        lookupObjectCode(csEntry);
        createOutputEntry(csEntry, validGroup);

        csEntry.setTransactionLedgerEntryDesc("GENERATED OFFSET"); //TODO: constant
        
        OffsetDefinition offset = offsetDefinitionService.getByPrimaryId(
                csEntry.getUniversityFiscalYear(), csEntry.getChartOfAccountsCode(),
                csEntry.getFinancialDocumentTypeCode(), csEntry.getFinancialBalanceTypeCode());
        if (offset == null) {
            transactionErrors.add("OFFSET DEFINITION NOT FOUND");
        } else {
            csEntry.setFinancialObjectCode(offset.getFinancialObjectCode());
            if(offset.getFinancialSubObjectCode() == null) {
                csEntry.setFinancialSubObjectCode("----"); // TODO: constant
            } else {
                csEntry.setFinancialSubObjectCode(offset.getFinancialSubObjectCode());
            }
        }

        ObjectCode objectCode = objectCodeService.getByPrimaryId(
                csEntry.getUniversityFiscalYear(), csEntry.getChartOfAccountsCode(),
                csEntry.getFinancialObjectCode());
        if (objectCode == null) {
            transactionErrors.add("NO OBJECT FOR OBJECT ON OFSD");
        } else {
            csEntry.setFinancialObjectTypeCode(objectCode.getFinancialObjectTypeCode());
            if(csEntry.isCredit()) {
                csEntry.setTransactionDebitCreditCode(Constants.GL_DEBIT_CODE);
            } else {
                csEntry.setTransactionDebitCreditCode(Constants.GL_CREDIT_CODE);
            }
        }

        csEntry.setTransactionDate(runDate);
        csEntry.setOrganizationDocumentNumber("");
        csEntry.setProjectCode("----"); // constant
        csEntry.setOrganizationReferenceId("");
        csEntry.setReferenceFinDocumentTypeCode("");
        csEntry.setFinancialSystemOriginationCode("");
        csEntry.setFinancialDocumentReferenceNbr("");
        csEntry.setReversalDate(null);
        csEntry.setTransactionEncumbranceUpdtCd("");

        createOutputEntry(csEntry, validGroup);
    }// End of method

    private void lookupObjectCode(OriginEntry inputEntry) { // SET-OBECT-2004

        // TODO: cant we just do an inputEntry
        inputEntry.getFinancialObject().refresh();

        if (inputEntry.getFinancialObject() == null) {
            transactionErrors.add("NO OBJECT FOR OBJECT ON OFSD");
        }

        String objectCode = "";
        String inputObjectLevelCode = inputEntry.getFinancialObject().getFinancialObjectLevelCode();
        String inputObjectCode = inputEntry.getFinancialObjectCode();

        // TODO: MOVE ALL THIS TO CONSTANTS
        if("ACSA".equals(inputObjectLevelCode)) { //ACADEMIC SALARIES
            objectCode = "9920"; //TRSFRS_OF_FUNDS_ACAD_SAL
        } else if("BASE".equals(inputObjectLevelCode)) { //ASSESMENTS_EXPENDITURES
            objectCode = "9959"; //TRANSFER_OUT_20_REALLOCATION
        } else if("BENF".equals(inputObjectLevelCode) &&
                ("9956".equals(inputObjectCode) || "5700".compareTo(inputObjectCode) < 0 )) { //BENEFITS
            objectCode = "9956"; //TRSFRS_OF_FUNDS_FRINGE_BENF
        } else if("BENF".equals(inputObjectLevelCode)) { //BENEFITS
            objectCode = "9957"; //TRSFRS_OF_FUNDS_RETIREMENT 
        } else if("BISA".equals(inputObjectLevelCode)) { //BI-WEEKLY_SALARY
            objectCode = "9925"; //TRSFRS_OF_FUNDS_CLER_SAL 
        } else if("CAP".equals(inputObjectLevelCode)) { //CAPITAL_ASSETS
            objectCode = "9970"; //TRSFRS_OF_FUNDS_CAPITAL  
        } else if("CORE".equals(inputObjectLevelCode)) { //ALLOTMENTS_AND_CHARGES_OUT
            // Do nothing
        } else if("CORI".equals(inputObjectLevelCode)) { //ALLOTMENTS_AND_CHARGES_IN
            // Do nothing
        } else if("FINA".equals(inputObjectLevelCode) &&
                ("9954".equals(inputObjectCode) || "5400".equals(inputObjectCode))) { //STUDENT_FINANCIAL_AID - TRSFRS_OF_FUNDS_FEE_REM  - GRADUATE_FEE_REMISSIONS
            objectCode = "9954"; //TRSFRS_OF_FUNDS_CAPITAL  
        } else if("FINA".equals(inputObjectLevelCode)) { //STUDENT_FINANCIAL_AID
            objectCode = "9958"; //TRSFRS_OF_FUNDS_FELL_AND_SCHO 
        } else if("HRCO".equals(inputObjectLevelCode)) { //HOURLY_COMPENSATION
            objectCode = "9930"; //TRSFRS_OF_FUNDS_WAGES 
        } else if("ICOE".equals(inputObjectLevelCode)) { //INDIRECT_COST_RECOVERY_EXPENSE
            objectCode = "9955"; //TRSFRS_OF_FUNDS_INDRCT_COST 
        } else if("PART".equals(inputObjectLevelCode)) { //PART_TIME_INSTRUCTION_NON_STUDENT
            objectCode = "9923"; //TRSFRS_OF_FUNDS_ACAD_ASSIST 
        } else if("PRSA".equals(inputObjectLevelCode)) { //PROFESSIONAL_SALARIES
            objectCode = "9924"; //TRSF_OF_FUNDS_PROF_SAL 
        } else if("RESV".equals(inputObjectLevelCode)) { //RESERVES
            objectCode = "9979"; //TRSFRS_OF_FUNDS_UNAPP_BAL
        } else if("SAAP".equals(inputObjectLevelCode)) { //SALARY_ACCRUAL_EXPENSE
            objectCode = "9923"; //TRSFRS_OF_FUNDS_ACAD_ASSIST
        } else if("TRAN".equals(inputObjectLevelCode)) { //TRANSFER_EXPENSE
            objectCode = "9959"; //TRANSFER_OUT_20_REALLOCATION
        } else if("TRAV".equals(inputObjectLevelCode)) { //TRAVEL
            objectCode = "9960"; //TRSFRS_OF_FUNDS_TRAVEL
        } else if("TREX".equals(inputObjectLevelCode)) { //TRANSFER_5199_EXPENSE
            objectCode = "9959"; //TRANSFER_OUT_20_REALLOCATION
        } else if("TRIN".equals(inputObjectLevelCode)) { //TRANSFER_1699_INCOME
            objectCode = "9915"; //TRSFRS_OF_FUNDS_REVENUE  
        } else {
            objectCode = "9940"; //TRSFRS_OF_FUNDS_SUP_AND_EXP 
        }
        
        inputEntry.setFinancialObjectCode(objectCode);
        inputEntry.getFinancialObject().refresh(); // TODO: this needs to be checked!

        if (inputEntry.getFinancialObject() == null) {
            transactionErrors.add(kualiConfigurationService.getPropertyString(KeyConstants.ERROR_COST_SHARE_OBJECT_NOT_FOUND));
        } else {
            inputEntry.setFinancialObjectTypeCode(inputEntry.getFinancialObject().getFinancialObjectTypeCode());
        }
    }// End of method

    private void generateOffset(OriginEntry workingEntry) {
        workingEntry.setTransactionLedgerEntryDesc("OFFSET_DESCRIPTION"); // TODO: get from property

        // See if we have the offset definition table assoicated with
        // this transaction. We need the offset object code from it.

        OffsetDefinition offsetDefinition = offsetDefinitionService.getByPrimaryId(workingEntry.getUniversityFiscalYear(), workingEntry.getChartOfAccountsCode(), workingEntry.getFinancialDocumentTypeCode(), workingEntry.getFinancialBalanceTypeCode());
        
        if(checkGLObject(offsetDefinition, kualiConfigurationService.getPropertyString(KeyConstants.ERROR_OFFSET_DEFINITION_NOT_FOUND))) {
            workingEntry.setFinancialObjectCode(offsetDefinition.getFinancialObjectCode());
            if (offsetDefinition.getFinancialSubObject() == null) {
                workingEntry.setFinancialSubObjectCode(replaceNullWithDashes(workingEntry.getFinancialSubObjectCode()));
            } else {
                workingEntry.setFinancialSubObjectCode(offsetDefinition.getFinancialSubObjectCode());
            }
            
            if (checkGLObject(offsetDefinition.getFinancialObject(), kualiConfigurationService.getPropertyString(KeyConstants.ERROR_OFFSET_DEFINITION_OBJECT_CODE_NOT_FOUND))) {
                workingEntry.setFinancialObjectTypeCode(offsetDefinition.getFinancialObject().getFinancialObjectTypeCode());
            }
            
        }

        workingEntry.setTransactionLedgerEntryAmount(scrubberUtil.offsetAmountAccumulator);
        
        if (scrubberUtil.offsetAmountAccumulator.isPositive()) {
            workingEntry.setTransactionDebitCreditCode(Constants.GL_CREDIT_CODE);
        } else {
            workingEntry.setTransactionDebitCreditCode(Constants.GL_DEBIT_CODE);
            workingEntry.setTransactionLedgerEntryAmount(workingEntry.getTransactionLedgerEntryAmount().multiply(new KualiDecimal(-1)));
        }
        
        workingEntry.setOrganizationDocumentNumber(null);
        workingEntry.setOrganizationReferenceId(null);
        workingEntry.setReferenceFinDocumentTypeCode(null);
        workingEntry.setFinSystemRefOriginationCode(null);
        workingEntry.setFinancialDocumentReferenceNbr(null);
        workingEntry.setTransactionEncumbranceUpdtCd(null);
        workingEntry.setProjectCode(replaceNullWithDashes(null));
        workingEntry.setTransactionDate(runDate);
    }// End of method

    private void initScrubberValues() { /* 2520-init-SRCbArea */
        wsExpiredAccount = null;
        wsExpiredChart = null;
        scrubberUtil.offsetAmountAccumulator = new KualiDecimal(0.0);
        scrubberUtil.creditAmountAccumulator = new KualiDecimal(0.0);
        scrubberUtil.debitAmountAccumulator = new KualiDecimal(0.0);
    }

    public void setOriginEntryService(OriginEntryService oes) {
        this.originEntryService = oes;
    }

    public void setDateTimeService(DateTimeService dts) {
        this.dateTimeService = dts;
    }

    public void setUniversityDateDao(UniversityDateDao universityDateDao) {
        this.universityDateDao = universityDateDao;
    }

    /**
     * Sets the offsetDefinitionService attribute value.
     * @param offsetDefinitionService The offsetDefinitionService to set.
     */
    public void setOffsetDefinitionService(OffsetDefinitionService offsetDefinitionService) {
        this.offsetDefinitionService = offsetDefinitionService;
    }

    /**
     * Sets the objectCodeService attribute value.
     * @param objectCodeService The objectCodeService to set.
     */
    public void setObjectCodeService(ObjectCodeService objectCodeService) {
        this.objectCodeService = objectCodeService;
    }

    public void setChartService(ChartService chartService) {
        this.chartService = chartService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }
    
    private void createOutputEntry(OriginEntry inputEntry, OriginEntryGroup group) {
        originEntryService.createEntry(inputEntry, group);

        switch (writeSwitchStatusCD) {
        case ScrubberUtil.FROM_WRITE:
            ++scrubberUtil.writeCount;
            break;
        case ScrubberUtil.FROM_OFFSET:
            ++scrubberUtil.offsetCount;
            break;
        case ScrubberUtil.FROM_CAMS:
            ++scrubberUtil.camsCount;
            break;
        case ScrubberUtil.FROM_LIAB:
            ++scrubberUtil.liabCount;
            break;
        case ScrubberUtil.FROM_PLANT_INDEBTEDNESS:
            ++scrubberUtil.plantIndebtednessCount;
            break;
        case ScrubberUtil.FROM_COST_SHARE:
            ++scrubberUtil.costShareCount;
            break;
        case ScrubberUtil.FROM_COST_SHARE_ENCUMBRANCE:
            ++scrubberUtil.costShareEncCount;
            break;
        default:
            break;
        }
    }
    
    private void writeErrors() { // 8220-WRITE-ERRORS
        switch (writeSwitchStatusCD) {
        case ScrubberUtil.FROM_WRITE:
            ++scrubberUtil.errorCount;
            break;
        case ScrubberUtil.FROM_OFFSET:
            ++scrubberUtil.errorCount;
            break;
        case ScrubberUtil.FROM_COST_SHARE:
            ++scrubberUtil.errorCountCostShare;
            break;
        case ScrubberUtil.FROM_COST_SHARE_ENCUMBRANCE:
            ++scrubberUtil.errorCountCostShareEnc;
            break;
        default:
            break;
        }
    }
}
