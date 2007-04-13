/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.module.labor.service.impl;

import static org.kuali.Constants.ParameterGroups.SYSTEM;
import static org.kuali.Constants.SystemGroupParameterNames.LABOR_POSTER_BALANCE_TYPES_NOT_PROCESSED;
import static org.kuali.Constants.SystemGroupParameterNames.LABOR_POSTER_OBJECT_CODES_NOT_PROCESSED;
import static org.kuali.Constants.SystemGroupParameterNames.LABOR_POSTER_PERIOD_CODES_NOT_PROCESSED;
import static org.kuali.module.gl.bo.OriginEntrySource.LABOR_MAIN_POSTER_ERROR;
import static org.kuali.module.gl.bo.OriginEntrySource.LABOR_MAIN_POSTER_VALID;
import static org.kuali.module.gl.bo.OriginEntrySource.LABOR_SCRUBBER_VALID;
import static org.kuali.module.labor.LaborConstants.DestinationNames.ORIGN_ENTRY;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.kuali.Constants;
import org.kuali.Constants.OperationType;
import org.kuali.core.service.DateTimeService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.module.gl.batch.poster.PostTransaction;
import org.kuali.module.gl.batch.poster.VerifyTransaction;
import org.kuali.module.gl.bo.OriginEntryGroup;
import org.kuali.module.gl.bo.Transaction;
import org.kuali.module.gl.service.OriginEntryGroupService;
import org.kuali.module.gl.util.Message;
import org.kuali.module.gl.util.Summary;
import org.kuali.module.labor.bo.LaborOriginEntry;
import org.kuali.module.labor.rules.TransactionFieldValidator;
import org.kuali.module.labor.service.LaborOriginEntryService;
import org.kuali.module.labor.service.LaborPosterService;
import org.kuali.module.labor.service.LaborReportService;
import org.kuali.module.labor.util.MessageBuilder;
import org.kuali.module.labor.util.ReportRegistry;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Labor Ledger Poster accepts pending entries generated by Labor Ledger e-docs (such as Salary Expense Transfer and Benefit
 * Expense Transfer), and combines them with entries from external systems. It edits the entries for validity. Invalid entries can
 * be marked for Labor Ledger Error Correction process. The Poster writes valid entries to the Labor Ledger Entry table, updates
 * balances in the Labor Ledger Balance table, and summarizes the entries for posting to the General Ledger.
 */
@Transactional
public class LaborPosterServiceImpl implements LaborPosterService {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(LaborPosterServiceImpl.class);

    private LaborOriginEntryService laborOriginEntryService;
    private OriginEntryGroupService originEntryGroupService;

    private LaborReportService laborReportService;
    private DateTimeService dateTimeService;
    private VerifyTransaction laborPosterTransactionValidator;
    private KualiConfigurationService kualiConfigurationService;

    private PostTransaction laborLedgerEntryPoster;
    private PostTransaction laborLedgerBalancePoster;
    private PostTransaction laborGLLedgerEntryPoster;

    private final static int STEP = 1;
    private final static int LINE_INTERVAL = 2;

    /**
     * @see org.kuali.module.labor.service.LaborPosterService#postMainEntries()
     */
    public void postMainEntries() {
        Date runDate = dateTimeService.getCurrentSqlDate();

        OriginEntryGroup validGroup = originEntryGroupService.createGroup(runDate, LABOR_MAIN_POSTER_VALID, true, false, false);
        OriginEntryGroup invalidGroup = originEntryGroupService.createGroup(runDate, LABOR_MAIN_POSTER_ERROR, false, true, false);

        this.postLaborLedgerEntries(validGroup, invalidGroup, runDate);
        this.postLaborGLEntries(validGroup, runDate);
    }
 
    /**
     * post the qualified origin entries into Labor Ledger tables
     * @param validGroup the origin entry group that holds the valid transactions
     * @param invalidGroup the origin entry group that holds the invalid transactions
     * @param runDate the data when the process is running
     */
    private void postLaborLedgerEntries(OriginEntryGroup validGroup, OriginEntryGroup invalidGroup, Date runDate) {
        String reportsDirectory = ReportRegistry.getReportsDirectory();
        Map<Transaction, List<Message>> errorMap = new HashMap<Transaction, List<Message>>();
        List<Summary> reportSummary = this.buildReportSummaryForLaborLedgerPosting();

        Collection<OriginEntryGroup> postingGroups = originEntryGroupService.getGroupsToPost(LABOR_SCRUBBER_VALID);
        int numberOfOriginEntry = laborOriginEntryService.getCountOfEntriesInGroups(postingGroups);
        int numberOfSelectedOriginEntry = 0;
        
        for (OriginEntryGroup entryGroup : postingGroups) {
            Iterator<LaborOriginEntry> entries = laborOriginEntryService.getEntriesByGroup(entryGroup);
            while (entries != null && entries.hasNext()) {
                LaborOriginEntry originEntry = entries.next();
                if (postSingleEntryIntoLaborLedger(originEntry, reportSummary, errorMap, validGroup, invalidGroup, runDate)) {
                    numberOfSelectedOriginEntry++;
                    originEntry = null;
                }
            }
            // reset the process flag of the group so that it cannot be handled any more
            entryGroup.setProcess(Boolean.FALSE);
            originEntryGroupService.save(entryGroup);
        }
        Summary.updateReportSummary(reportSummary, ORIGN_ENTRY, Constants.OperationType.READ, numberOfOriginEntry, 0);
        Summary.updateReportSummary(reportSummary, ORIGN_ENTRY, Constants.OperationType.SELECT, numberOfSelectedOriginEntry, 0);
        Summary.updateReportSummary(reportSummary, ORIGN_ENTRY, Constants.OperationType.REPORT_ERROR, errorMap.size(), 0);

        laborReportService.generateStatisticsReport(reportSummary, errorMap, ReportRegistry.LABOR_POSTER_STATISTICS, reportsDirectory, runDate);
        laborReportService.generateInputSummaryReport(postingGroups, ReportRegistry.LABOR_POSTER_INPUT, reportsDirectory, runDate);
        laborReportService.generateOutputSummaryReport(validGroup, ReportRegistry.LABOR_POSTER_OUTPUT, reportsDirectory, runDate);
        laborReportService.generateErrorTransactionListing(invalidGroup, ReportRegistry.LABOR_POSTER_ERROR, reportsDirectory, runDate);
    }

    /**
     * post the given entry into the labor ledger tables if the entry is qualified; otherwise report error
     * @param originEntry the given origin entry, a transaction
     * @param reportSummary the report summary object that need to be update when a transaction is posted
     * @param errorMap a map that holds the invalid transaction and corresponding error message
     * @param validGroup the origin entry group that holds the valid transactions
     * @param invalidGroup the origin entry group that holds the invalid transactions
     * @param runDate the data when the process is running
     * @return true if the given transaction is posted into ledger tables; otherwise, return false
     */
    private boolean postSingleEntryIntoLaborLedger(LaborOriginEntry originEntry, List<Summary> reportSummary, Map<Transaction, List<Message>> errorMap, OriginEntryGroup validGroup, OriginEntryGroup invalidGroup, Date runDate) {
        try {
            // reject the entry that is not postable
            if (!isPostableEntry(originEntry)) {
                return false;
            }

            // reject the invalid entry so that it can be available for error correction
            List<Message> errors = this.validateEntry(originEntry);
            if (errors!=null && !errors.isEmpty()) {
                errorMap.put(originEntry, errors);
                postAsProcessedOriginEntry(originEntry, invalidGroup, runDate);
                return false;
            }

            // post the current origin entry as a valid origin entry, ledger entry and ledger balance
            postAsProcessedOriginEntry(originEntry, validGroup, runDate);

            String operationOnLedgerEntry = postAsLedgerEntry(originEntry, runDate);
            Summary.updateReportSummary(reportSummary, laborLedgerEntryPoster.getDestinationName(), operationOnLedgerEntry, STEP, 0);

            String operationOnLedgerBalance = updateLedgerBalance(originEntry, runDate);
            Summary.updateReportSummary(reportSummary, laborLedgerBalancePoster.getDestinationName(), operationOnLedgerBalance, STEP, 0);
        }
        catch (Exception e) {
            LOG.error(e);
            return false;
        }
        return true;
    }

    /**
     * determine if the given origin entry need to be posted
     * @param originEntry the given origin entry, a transcation
     * @return true if the transaction is eligible for poster process; otherwise; return false
     */
    private boolean isPostableEntry(LaborOriginEntry originEntry) {
        if (TransactionFieldValidator.checkZeroTotalAmount(originEntry) != null) {
            return false;
        }
        else if (TransactionFieldValidator.checkPostableObjectCode(originEntry, this.getObjectsNotProcessed()) != null) {
            return false;
        }
        return true;
    }

    /**
     * validate the given entry, and generate an error list if the entry cannot meet the business rules
     * @param originEntry the given origin entry, a transcation
     * @return error message list. If the given transaction is invalid, the list has message(s); otherwise, it is empty
     */
    private List<Message> validateEntry(LaborOriginEntry originEntry) {
        return laborPosterTransactionValidator.verifyTransaction(originEntry);
    }

    /**
     * post the processed entry into the approperiate group, either valid or invalid group
     * @param originEntry the given origin entry, a transaction
     * @param entryGroup the origin entry group that the transaction will be assigned
     * @param postDate the data when the transaction is processes
     */
    private void postAsProcessedOriginEntry(LaborOriginEntry originEntry, OriginEntryGroup entryGroup, Date postDate) {
        originEntry.setEntryGroupId(entryGroup.getId());
        originEntry.setTransactionPostingDate(postDate);
        laborOriginEntryService.save(originEntry);
    }

    /**
     * post the given entry to the labor entry table
     * @param originEntry the given origin entry, a transaction
     * @param postDate the data when the transaction is processes
     * return the operation type of the process
     */
    private String postAsLedgerEntry(LaborOriginEntry originEntry, Date postDate) {
        return laborLedgerEntryPoster.post(originEntry, 0, postDate);
    }

    /**
     * update the labor ledger balance for the given entry
     * @param originEntry the given origin entry, a transaction
     * @param postDate the data when the transaction is processes
     * return the operation type of the process
     */
    private String updateLedgerBalance(LaborOriginEntry originEntry, Date postDate) {
        return laborLedgerBalancePoster.post(originEntry, 0, postDate);
    }

    /**
     * post the valid origin entries in the given group into General Ledger
     * @param validGroup the origin entry group that contains the valid transactions determined in the Labor Poster
     * @param runDate the data when the process is running
     */
    private void postLaborGLEntries(OriginEntryGroup validGroup, Date runDate) {
        String reportsDirectory = ReportRegistry.getReportsDirectory();
        List<Summary> reportSummary = this.buildReportSummaryForLaborGLPosting();
        Map<Transaction, List<Message>> errorMap = new HashMap<Transaction, List<Message>>();

        Collection<LaborOriginEntry> entries = laborOriginEntryService.getConsolidatedEntryCollectionByGroup(validGroup);
        int numberOfOriginEntry = entries.size();
        int numberOfSelectedOriginEntry = 0;
        
        for (LaborOriginEntry originEntry : entries) {

            List<Message> errors = this.isPostableForLaborGLEntry(originEntry);
            if (!errors.isEmpty()) {
                errorMap.put(originEntry, errors);
                continue;
            }
            String operationType = laborGLLedgerEntryPoster.post(originEntry, 0, runDate);
            Summary.updateReportSummary(reportSummary, laborGLLedgerEntryPoster.getDestinationName(), operationType, STEP, 0);

            numberOfSelectedOriginEntry++;
        }
        Summary.updateReportSummary(reportSummary, ORIGN_ENTRY, Constants.OperationType.READ, numberOfOriginEntry, 0);
        Summary.updateReportSummary(reportSummary, ORIGN_ENTRY, Constants.OperationType.SELECT, numberOfSelectedOriginEntry, 0);
        Summary.updateReportSummary(reportSummary, ORIGN_ENTRY, Constants.OperationType.REPORT_ERROR, errorMap.size(), 0);
        laborReportService.generateStatisticsReport(reportSummary, errorMap, ReportRegistry.LABOR_POSTER_GL_SUMMARY, reportsDirectory, runDate);
    }

    /**
     * determine if the given origin entry can be posted back to Labor GL entry
     * @param originEntry the given origin entry, atransaction
     * @return a message list. The list has message(s) if the given origin entry cannot be posted back to Labor GL entry; otherwise, it is empty
     */
    private List<Message> isPostableForLaborGLEntry(LaborOriginEntry originEntry) {
        List<Message> errors = new ArrayList<Message>();
        MessageBuilder.addMessageIntoList(errors, TransactionFieldValidator.checkPostablePeridCode(originEntry, getPeriodCodesNotProcessed()));
        MessageBuilder.addMessageIntoList(errors, TransactionFieldValidator.checkPostableBalanceTypeCode(originEntry, getBalanceTypesNotProcessed()));
        MessageBuilder.addMessageIntoList(errors, TransactionFieldValidator.checkZeroTotalAmount(originEntry));
        return errors;
    }

    /**
     * build a report summary list for labor ledger posting
     * @return a report summary list for labor ledger posting
     */
    private List<Summary> buildReportSummaryForLaborLedgerPosting() {
        List<Summary> reportSummary = new ArrayList<Summary>();
        
        String destination = laborLedgerEntryPoster.getDestinationName();
        reportSummary.add(new Summary(reportSummary.size() + LINE_INTERVAL, "", 0));
        reportSummary.addAll(Summary.buildDefualtReportSummary(destination, reportSummary.size() + LINE_INTERVAL));

        destination = laborLedgerBalancePoster.getDestinationName();
        reportSummary.add(new Summary(reportSummary.size() + LINE_INTERVAL, "", 0));
        reportSummary.addAll(Summary.buildDefualtReportSummary(destination, reportSummary.size() + LINE_INTERVAL));

        return reportSummary;
    }

    /**
     * build a report summary list for labor general ledger posting
     * @return a report summary list for labor general ledger posting
     */
    private List<Summary> buildReportSummaryForLaborGLPosting() {
        List<Summary> reportSummary = new ArrayList<Summary>();

        String destination = laborGLLedgerEntryPoster.getDestinationName();
        reportSummary.add(new Summary(reportSummary.size() + LINE_INTERVAL, "", 0));
        reportSummary.addAll(Summary.buildDefualtReportSummary(destination, reportSummary.size() + LINE_INTERVAL));

        return reportSummary;
    }

    /**
     * Get a set of the balance type codes that are bypassed by Labor Poster
     * @return a set of the balance type codes that are bypassed by Labor Poster
     */
    public String[] getBalanceTypesNotProcessed() {
        return kualiConfigurationService.getApplicationParameterValues(SYSTEM, LABOR_POSTER_BALANCE_TYPES_NOT_PROCESSED);
    }

    /**
     * Get a set of the object codes that are bypassed by Labor Poster
     * @return a set of the object codes that are bypassed by Labor Poster
     */
    public String[] getObjectsNotProcessed() {
        return kualiConfigurationService.getApplicationParameterValues(SYSTEM, LABOR_POSTER_OBJECT_CODES_NOT_PROCESSED);
    }

    /**
     * Get a set of the fiscal period codes that are bypassed by Labor Poster
     * @return a set of the fiscal period codes that are bypassed by Labor Poster
     */
    public String[] getPeriodCodesNotProcessed() {
        return kualiConfigurationService.getApplicationParameterValues(SYSTEM, LABOR_POSTER_PERIOD_CODES_NOT_PROCESSED);
    }

    /**
     * Sets the dateTimeService attribute value.
     * 
     * @param dateTimeService The dateTimeService to set.
     */
    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    /**
     * Sets the kualiConfigurationService attribute value.
     * 
     * @param kualiConfigurationService The kualiConfigurationService to set.
     */
    public void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }

    /**
     * Sets the laborLedgerBalancePoster attribute value.
     * 
     * @param laborLedgerBalancePoster The laborLedgerBalancePoster to set.
     */
    public void setLaborLedgerBalancePoster(PostTransaction laborLedgerBalancePoster) {
        this.laborLedgerBalancePoster = laborLedgerBalancePoster;
    }

    /**
     * Sets the laborGLLedgerEntryPoster attribute value.
     * 
     * @param laborGLLedgerEntryPoster The laborGLLedgerEntryPoster to set.
     */
    public void setLaborGLLedgerEntryPoster(PostTransaction laborGLLedgerEntryPoster) {
        this.laborGLLedgerEntryPoster = laborGLLedgerEntryPoster;
    }

    /**
     * Sets the laborLedgerEntryPoster attribute value.
     * 
     * @param laborLedgerEntryPoster The laborLedgerEntryPoster to set.
     */
    public void setLaborLedgerEntryPoster(PostTransaction laborLedgerEntryPoster) {
        this.laborLedgerEntryPoster = laborLedgerEntryPoster;
    }

    /**
     * Sets the laborOriginEntryService attribute value.
     * 
     * @param laborOriginEntryService The laborOriginEntryService to set.
     */
    public void setLaborOriginEntryService(LaborOriginEntryService laborOriginEntryService) {
        this.laborOriginEntryService = laborOriginEntryService;
    }

    /**
     * Sets the originEntryGroupService attribute value.
     * 
     * @param originEntryGroupService The originEntryGroupService to set.
     */
    public void setOriginEntryGroupService(OriginEntryGroupService originEntryGroupService) {
        this.originEntryGroupService = originEntryGroupService;
    }

    /**
     * Sets the laborReportService attribute value.
     * @param laborReportService The laborReportService to set.
     */
    public void setLaborReportService(LaborReportService laborReportService) {
        this.laborReportService = laborReportService;
    }
    
    /**
     * Sets the laborPosterTransactionValidator attribute value.
     * 
     * @param laborPosterTransactionValidator The laborPosterTransactionValidator to set.
     */
    public void setLaborPosterTransactionValidator(VerifyTransaction laborPosterTransactionValidator) {
        this.laborPosterTransactionValidator = laborPosterTransactionValidator;
    }
}