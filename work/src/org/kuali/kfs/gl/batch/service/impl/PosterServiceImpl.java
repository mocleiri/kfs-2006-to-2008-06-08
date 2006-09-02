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

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.kuali.Constants;
import org.kuali.KeyConstants;
import org.kuali.core.service.DateTimeService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.service.PersistenceService;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.module.chart.bo.AccountingPeriod;
import org.kuali.module.chart.bo.IcrAutomatedEntry;
import org.kuali.module.chart.bo.ObjectCode;
import org.kuali.module.chart.dao.IcrAutomatedEntryDao;
import org.kuali.module.chart.service.AccountingPeriodService;
import org.kuali.module.chart.service.ObjectCodeService;
import org.kuali.module.gl.GLConstants;
import org.kuali.module.financial.exceptions.InvalidFlexibleOffsetException;
import org.kuali.module.financial.service.FlexibleOffsetAccountService;
import org.kuali.module.gl.batch.poster.PostTransaction;
import org.kuali.module.gl.batch.poster.VerifyTransaction;
import org.kuali.module.gl.bo.ExpenditureTransaction;
import org.kuali.module.gl.bo.OriginEntry;
import org.kuali.module.gl.bo.OriginEntryGroup;
import org.kuali.module.gl.bo.OriginEntrySource;
import org.kuali.module.gl.bo.Reversal;
import org.kuali.module.gl.bo.Transaction;
import org.kuali.module.gl.bo.UniversityDate;
import org.kuali.module.gl.dao.ExpenditureTransactionDao;
import org.kuali.module.gl.dao.ReversalDao;
import org.kuali.module.gl.dao.UniversityDateDao;
import org.kuali.module.gl.service.OriginEntryGroupService;
import org.kuali.module.gl.service.OriginEntryService;
import org.kuali.module.gl.service.PosterService;
import org.kuali.module.gl.service.ReportService;
import org.kuali.module.gl.service.impl.ScrubberProcess.TransactionError;
import org.kuali.module.gl.service.impl.scrubber.Message;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @author jsissom
 * @version $Id: PosterServiceImpl.java,v 1.39 2006-09-02 00:36:28 jsissom Exp $
 */
public class PosterServiceImpl implements PosterService, BeanFactoryAware {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PosterServiceImpl.class);

    public static final String INSERT_CODE = "I";
    public static final String UPDATE_CODE = "U";
    public static final String DELETE_CODE = "D";
    public static final String SELECT_CODE = "S";

    public static final KualiDecimal warningMaxDifference = new KualiDecimal("0.05");
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    private BeanFactory beanFactory;
    private List transactionPosters;
    private VerifyTransaction verifyTransaction;
    private OriginEntryService originEntryService;
    private OriginEntryGroupService originEntryGroupService;
    private DateTimeService dateTimeService;
    private ReversalDao reversalDao;
    private UniversityDateDao universityDateDao;
    private AccountingPeriodService accountingPeriodService;
    private ExpenditureTransactionDao expenditureTransactionDao;
    private IcrAutomatedEntryDao icrAutomatedEntryDao;
    private ObjectCodeService objectCodeService;
    private ReportService reportService;
    private KualiConfigurationService kualiConfigurationService;
    private FlexibleOffsetAccountService flexibleOffsetAccountService;

    /**
     * 
     */
    public PosterServiceImpl() {
        super();
    }

    /**
     * Post scrubbed GL entries to GL tables.
     */
    public void postMainEntries() {
        LOG.debug("postMainEntries() started");
        postEntries(PosterService.MODE_ENTRIES);
    }

    /**
     * Post reversal GL entries to GL tables.
     */
    public void postReversalEntries() {
        LOG.debug("postReversalEntries() started");
        postEntries(PosterService.MODE_REVERSAL);
    }

    /**
     * Post ICR GL entries to GL tables.
     */
    public void postIcrEntries() {
        LOG.debug("postIcrEntries() started");
        postEntries(PosterService.MODE_ICR);
    }

    /*
     * Actually post the entries. The mode variable decides which entries to post.
     */
    private void postEntries(int mode) {
        LOG.debug("postEntries() started");

        String validEntrySourceCode = OriginEntrySource.MAIN_POSTER_VALID;
        String invalidEntrySourceCode = OriginEntrySource.MAIN_POSTER_ERROR;
        OriginEntryGroup validGroup = null;
        OriginEntryGroup invalidGroup = null;

        Date runDate = new Date(dateTimeService.getCurrentDate().getTime());
        UniversityDate runUniversityDate = universityDateDao.getByPrimaryKey(runDate);

        Collection groups = null;
        Iterator reversalTransactions = null;
        switch (mode) {
            case PosterService.MODE_ENTRIES:
                validEntrySourceCode = OriginEntrySource.MAIN_POSTER_VALID;
                invalidEntrySourceCode = OriginEntrySource.MAIN_POSTER_ERROR;
                groups = originEntryGroupService.getGroupsToPost();
                reportService.generatePosterMainLedgerSummaryReport(runDate, groups);
                break;
            case PosterService.MODE_REVERSAL:
                validEntrySourceCode = OriginEntrySource.REVERSAL_POSTER_VALID;
                invalidEntrySourceCode = OriginEntrySource.REVERSAL_POSTER_ERROR;
                reversalTransactions = reversalDao.getByDate(runDate);
                // TODO Reversal Report
                break;
            case PosterService.MODE_ICR:
                validEntrySourceCode = OriginEntrySource.ICR_POSTER_VALID;
                invalidEntrySourceCode = OriginEntrySource.ICR_POSTER_ERROR;
                groups = originEntryGroupService.getIcrGroupsToPost();
                reportService.generatePosterIcrLedgerSummaryReport(runDate, groups);
                break;
            default:
                throw new IllegalArgumentException("Invalid poster mode " + mode);
        }

        // Create new Groups for output transactions
        validGroup = originEntryGroupService.createGroup(runDate, validEntrySourceCode, true, true, false);
        invalidGroup = originEntryGroupService.createGroup(runDate, invalidEntrySourceCode, false, true, false);

        Map reportError = new HashMap();

        // Build the summary map so all the possible combinations of destination &
        // operation
        // are included in the summary part of the report.
        Map reportSummary = new HashMap();
        for (Iterator posterIter = transactionPosters.iterator(); posterIter.hasNext();) {
            PostTransaction poster = (PostTransaction) posterIter.next();
            reportSummary.put(poster.getDestinationName() + "," + PosterServiceImpl.DELETE_CODE, new Integer(0));
            reportSummary.put(poster.getDestinationName() + "," + PosterServiceImpl.INSERT_CODE, new Integer(0));
            reportSummary.put(poster.getDestinationName() + "," + PosterServiceImpl.UPDATE_CODE, new Integer(0));
        }

        int ecount = 0;
        if ( (mode == PosterService.MODE_ENTRIES) || (mode == PosterService.MODE_ICR) ) {
            LOG.debug("postEntries() Processing groups");
            for (Iterator iter = groups.iterator(); iter.hasNext();) {
                OriginEntryGroup group = (OriginEntryGroup) iter.next();

                Iterator entries = originEntryService.getEntriesByGroup(group);
                while (entries.hasNext()) {
                    Transaction tran = (Transaction) entries.next();

                    postTransaction(tran, mode, reportSummary, reportError, invalidGroup, validGroup, runUniversityDate);
                    LOG.info("postEntries() Posted Entry "+ (++ecount));
                }

                // Mark this group so we don't process it again next time the poster runs
                group.setProcess(Boolean.FALSE);
                originEntryGroupService.save(group);
            }
        }
        else {
            LOG.debug("postEntries() Processing reversal transactions");
            while (reversalTransactions.hasNext()) {
                Transaction tran = (Transaction) reversalTransactions.next();
                addReporting(reportSummary, "GL_REVERSAL_T", PosterServiceImpl.SELECT_CODE);

                postTransaction(tran, mode, reportSummary, reportError, invalidGroup, validGroup, runUniversityDate);

                LOG.info("postEntries() Posted Entry "+ (++ecount));
            }
        }

        // Generate the report
        reportService.generatePosterStatisticsReport(runDate, reportSummary, transactionPosters, reportError, mode);
    }

    private void postTransaction(Transaction tran, int mode, Map reportSummary, Map reportError, OriginEntryGroup invalidGroup, OriginEntryGroup validGroup, UniversityDate runUniversityDate) {

        List errors = new ArrayList();

        Transaction originalTransaction = tran;

        // Update select count in the report
        if (mode == PosterService.MODE_ENTRIES) {
            addReporting(reportSummary, "GL_ORIGIN_ENTRY_T", PosterServiceImpl.SELECT_CODE);
        }
        else {
            addReporting(reportSummary, "GL_ORIGIN_ENTRY_T (ICR)", PosterServiceImpl.SELECT_CODE);
        }

        // If these are reversal entries, we need to reverse the entry and
        // modify a few fields
        if (mode == PosterService.MODE_REVERSAL) {
            Reversal reversal = new Reversal(tran);

            // Reverse the debit/credit code
            if (Constants.GL_DEBIT_CODE.equals(reversal.getTransactionDebitCreditCode())) {
                reversal.setTransactionDebitCreditCode(Constants.GL_CREDIT_CODE);
            }
            else if (Constants.GL_CREDIT_CODE.equals(reversal.getTransactionDebitCreditCode())) {
                reversal.setTransactionDebitCreditCode(Constants.GL_DEBIT_CODE);
            }

            UniversityDate udate = universityDateDao.getByPrimaryKey(reversal.getFinancialDocumentReversalDate());
            if (udate != null) {
                reversal.setUniversityFiscalYear(udate.getUniversityFiscalYear());
                reversal.setUniversityFiscalPeriodCode(udate.getUniversityFiscalAccountingPeriod());

                AccountingPeriod ap = accountingPeriodService.getByPeriod(reversal.getUniversityFiscalPeriodCode(), reversal.getUniversityFiscalYear());
                if (ap != null) {
                    if (Constants.ACCOUNTING_PERIOD_STATUS_CLOSED.equals(ap.getUniversityFiscalPeriodStatusCode())) {
                        reversal.setUniversityFiscalYear(runUniversityDate.getUniversityFiscalYear());
                        reversal.setUniversityFiscalPeriodCode(runUniversityDate.getUniversityFiscalAccountingPeriod());
                    }
                    reversal.setFinancialDocumentReversalDate(null);
                    String newDescription = Constants.GL_REVERSAL_DESCRIPTION_PREFIX + reversal.getTransactionLedgerEntryDescription();
                    if (newDescription.length() > 40) {
                        newDescription = newDescription.substring(0, 40);
                    }
                    reversal.setTransactionLedgerEntryDescription(newDescription);
                }
                else {
                    errors.add(kualiConfigurationService.getPropertyString(KeyConstants.ERROR_UNIV_DATE_NOT_IN_ACCOUNTING_PERIOD_TABLE));
                }
            }
            else {
                errors.add(kualiConfigurationService.getPropertyString(KeyConstants.ERROR_REVERSAL_DATE_NOT_IN_UNIV_DATE_TABLE));
            }

            PersistenceService ps = SpringServiceLocator.getPersistenceService();
            ps.retrieveNonKeyFields(reversal);
            tran = reversal;
        }

        if (errors.size() == 0) {
            errors = verifyTransaction.verifyTransaction(tran);
        }

        // Now check each poster to see if it needs to verify the transaction. If
        // it returns errors, we won't post it
        for (Iterator posterIter = transactionPosters.iterator(); posterIter.hasNext();) {
            PostTransaction poster = (PostTransaction) posterIter.next();
            if (poster instanceof VerifyTransaction) {
                VerifyTransaction vt = (VerifyTransaction) poster;

                errors.addAll(vt.verifyTransaction(tran));
            }
        }

        if (errors.size() > 0) {
            // Error on this transaction
            reportError.put(tran, errors);
            addReporting(reportSummary, "WARNING", PosterServiceImpl.SELECT_CODE);

            originEntryService.createEntry(tran, invalidGroup);
        }
        else {
            // No error so post it
            for (Iterator posterIter = transactionPosters.iterator(); posterIter.hasNext();) {
                PostTransaction poster = (PostTransaction) posterIter.next();
                String actionCode = poster.post(tran, mode, runUniversityDate.getUniversityDate());

                if (actionCode.startsWith("E")) {
                    errors = new ArrayList();
                    errors.add(actionCode);
                    reportError.put(tran, errors);
                }
                else if (actionCode.indexOf(PosterServiceImpl.INSERT_CODE) >= 0) {
                    addReporting(reportSummary, poster.getDestinationName(), PosterServiceImpl.INSERT_CODE);
                }
                else if (actionCode.indexOf(PosterServiceImpl.UPDATE_CODE) >= 0) {
                    addReporting(reportSummary, poster.getDestinationName(), PosterServiceImpl.UPDATE_CODE);
                }
                else if (actionCode.indexOf(PosterServiceImpl.DELETE_CODE) >= 0) {
                    addReporting(reportSummary, poster.getDestinationName(), PosterServiceImpl.DELETE_CODE);
                }
                else if (actionCode.indexOf(PosterServiceImpl.SELECT_CODE) >= 0) {
                    addReporting(reportSummary, poster.getDestinationName(), PosterServiceImpl.SELECT_CODE);
                }
            }

            if (errors.size() == 0) {
                originEntryService.createEntry(tran, validGroup);

                // Delete the reversal entry
                if (mode == PosterService.MODE_REVERSAL) {
                    reversalDao.delete((Reversal) originalTransaction);
                    addReporting(reportSummary, "GL_REVERSAL_T", PosterServiceImpl.DELETE_CODE);
                }
            }
        }
    }

    /**
     * This step reads the expenditure table and uses the data to generate Indirect Cost Recovery transactions.
     */
    public void generateIcrTransactions() {
        LOG.debug("generateIcrTransactions() started");

        Date runDate = new Date(dateTimeService.getCurrentDate().getTime());

        OriginEntryGroup group = originEntryGroupService.createGroup(runDate, OriginEntrySource.ICR_TRANSACTIONS, true, true, false);

        Map reportErrors = new HashMap();

        int reportExpendTranRetrieved = 0;
        int reportExpendTranDeleted = 0;
        int reportExpendTranKept = 0;
        int reportOriginEntryGenerated = 0;

        Iterator expenditureTransactions = expenditureTransactionDao.getAllExpenditureTransactions();
        while (expenditureTransactions.hasNext()) {
            ExpenditureTransaction et = (ExpenditureTransaction) expenditureTransactions.next();
            reportExpendTranRetrieved++;

            KualiDecimal transactionAmount = et.getAccountObjectDirectCostAmount();
            KualiDecimal distributionPercent = KualiDecimal.ZERO;
            KualiDecimal distributionAmount = KualiDecimal.ZERO;
            KualiDecimal distributedAmount = KualiDecimal.ZERO;

            Collection automatedEntries = icrAutomatedEntryDao.getEntriesBySeries(et.getUniversityFiscalYear(), et.getAccount().getFinancialIcrSeriesIdentifier(), et.getBalanceTypeCode());
            int automatedEntriesCount = automatedEntries.size();

            if (automatedEntriesCount > 0) {
                int count = 0;
                for (Iterator icrIter = automatedEntries.iterator(); icrIter.hasNext();) {
                    IcrAutomatedEntry icrEntry = (IcrAutomatedEntry) icrIter.next();
                    count++;

                    KualiDecimal generatedTransactionAmount = null;

                    if (icrEntry.getAwardIndrCostRcvyEntryNbr().intValue() == 1) {
                        // Line 1 must have the total percentage of the transaction to distribute
                        distributionPercent = icrEntry.getAwardIndrCostRcvyRatePct();
                        distributionAmount = getPercentage(transactionAmount, distributionPercent.bigDecimalValue());

                        generatedTransactionAmount = distributionAmount;
                    }
                    else {
                        if ( automatedEntriesCount != count ) {
                            generatedTransactionAmount = getPercentage(transactionAmount,icrEntry.getAwardIndrCostRcvyRatePct().bigDecimalValue());
                            distributedAmount = distributedAmount.add(generatedTransactionAmount);
                        } else {
                            // Distribute the remaining amount on the last one
                            generatedTransactionAmount = distributionAmount.subtract(distributedAmount);
                        }

//                            if (difference.compareTo(KualiDecimal.ZERO) != 0) {
//                                if (difference.abs().compareTo(warningMaxDifference) >= 0) {
//                                    // TODO Rounding warning
//                                }
//                                distributedAmount.add(difference);
//                            }

                    }

                    generateTransactions(et, icrEntry, generatedTransactionAmount, runDate, group, reportErrors);
                    reportOriginEntryGenerated = reportOriginEntryGenerated + 2;
                }
            }

            // Delete expenditure record
            expenditureTransactionDao.delete(et);
            reportExpendTranDeleted++;
        }

        reportService.generatePosterIcrStatisticsReport(runDate, reportErrors, reportExpendTranRetrieved, reportExpendTranDeleted, reportExpendTranKept, reportOriginEntryGenerated);
    }

    /**
     * Generate a transfer transaction and an offset transaction
     * 
     * @param et
     * @param icrEntry
     * @param generatedTransactionAmount
     * @param runDate
     * @param group
     */
    private void generateTransactions(ExpenditureTransaction et, IcrAutomatedEntry icrEntry, KualiDecimal generatedTransactionAmount, Date runDate, OriginEntryGroup group, Map reportErrors) {
        BigDecimal pct = new BigDecimal(icrEntry.getAwardIndrCostRcvyRatePct().toString());
        pct = pct.divide(BDONEHUNDRED);

        OriginEntry e = new OriginEntry();
        e.setTransactionLedgerEntrySequenceNumber(0);

        // @ means we use the field from the expenditure entry, # means we use the ICR field from the account record, otherwise, use
        // the field in the icrEntry
        if ("@".equals(icrEntry.getFinancialObjectCode()) || "#".equals(icrEntry.getFinancialObjectCode())) {
            e.setFinancialObjectCode(et.getObjectCode());
            e.setFinancialSubObjectCode(et.getSubObjectCode());
        }
        else {
            e.setFinancialObjectCode(icrEntry.getFinancialObjectCode());
            if ("@".equals(icrEntry.getFinancialSubObjectCode())) {
                e.setFinancialSubObjectCode(et.getSubObjectCode());
            }
            else {
                e.setFinancialSubObjectCode(icrEntry.getFinancialSubObjectCode());
            }
        }

        if ("@".equals(icrEntry.getAccountNumber())) {
            e.setAccountNumber(et.getAccountNumber());
            e.setChartOfAccountsCode(et.getChartOfAccountsCode());
            e.setSubAccountNumber(et.getSubAccountNumber());
        }
        else if ("#".equals(icrEntry.getAccountNumber())) {
            e.setAccountNumber(et.getAccount().getIndirectCostRecoveryAcctNbr());
            e.setChartOfAccountsCode(et.getAccount().getChartOfAccountsCode());
            e.setSubAccountNumber(Constants.DASHES_SUB_ACCOUNT_NUMBER);
        }
        else {
            e.setAccountNumber(icrEntry.getAccountNumber());
            e.setSubAccountNumber(icrEntry.getSubAccountNumber());
            e.setChartOfAccountsCode(icrEntry.getChartOfAccountsCode());
            // TODO Reporting thing line 1946
        }

        e.setFinancialDocumentTypeCode(kualiConfigurationService.getApplicationParameterValue(Constants.ParameterGroups.SYSTEM, Constants.SystemGroupParameterNames.GL_INDIRECT_COST_RECOVERY));
        e.setFinancialSystemOriginationCode(kualiConfigurationService.getApplicationParameterValue(Constants.ParameterGroups.SYSTEM, Constants.SystemGroupParameterNames.GL_ORIGINATION_CODE));
        e.setFinancialDocumentNumber(sdf.format(runDate));
        if (Constants.GL_DEBIT_CODE.equals(icrEntry.getTransactionDebitIndicator())) {
            e.setTransactionLedgerEntryDescription(getChargeDescription(pct, et.getObjectCode(), et.getAccount().getAcctIndirectCostRcvyTypeCd(), et.getAccountObjectDirectCostAmount().abs()));
        }
        else {
            e.setTransactionLedgerEntryDescription(getOffsetDescription(pct, et.getAccountObjectDirectCostAmount().abs(), et.getChartOfAccountsCode(), et.getAccountNumber()));
        }
        e.setTransactionDate(new java.sql.Date(runDate.getTime()));
        e.setTransactionDebitCreditCode(icrEntry.getTransactionDebitIndicator());
        e.setFinancialBalanceTypeCode(et.getBalanceTypeCode());
        e.setUniversityFiscalYear(et.getUniversityFiscalYear());
        e.setUniversityFiscalPeriodCode(et.getUniversityFiscalAccountingPeriod());

        ObjectCode oc = objectCodeService.getByPrimaryId(e.getUniversityFiscalYear(), e.getChartOfAccountsCode(), e.getFinancialObjectCode());
        if (oc == null) {
            // TODO This should be a report thing, not an exception
            throw new IllegalArgumentException(kualiConfigurationService.getPropertyString(KeyConstants.ERROR_OBJECT_CODE_NOT_FOUND_FOR) + e.getUniversityFiscalYear() + "," + e.getChartOfAccountsCode() + "," + e.getFinancialObjectCode());
        }
        e.setFinancialObjectTypeCode(oc.getFinancialObjectTypeCode());

        if ( generatedTransactionAmount.isNegative() ) {
            if ( Constants.GL_DEBIT_CODE.equals(icrEntry.getTransactionDebitIndicator()) ) {
                e.setTransactionDebitCreditCode(Constants.GL_CREDIT_CODE);
            } else {
                e.setTransactionDebitCreditCode(Constants.GL_DEBIT_CODE);
            }
            e.setTransactionLedgerEntryAmount(generatedTransactionAmount.negated());
        } else {
            e.setTransactionLedgerEntryAmount(generatedTransactionAmount);
        }

        if (et.getBalanceTypeCode().equals(et.getOption().getExtrnlEncumFinBalanceTypCd()) || et.getBalanceTypeCode().equals(et.getOption().getIntrnlEncumFinBalanceTypCd()) || et.getBalanceTypeCode().equals(et.getOption().getPreencumbranceFinBalTypeCd()) || et.getBalanceTypeCode().equals(et.getOption().getCostShareEncumbranceBalanceTypeCode())) {
            e.setFinancialDocumentNumber(kualiConfigurationService.getApplicationParameterValue(Constants.ParameterGroups.SYSTEM, Constants.SystemGroupParameterNames.GL_INDIRECT_COST_RECOVERY));
        }
        e.setProjectCode(et.getProjectCode());
        if (GLConstants.DASH_ORGANIZATION_REFERENCE_ID.equals(et.getOrganizationReferenceId())) {
            e.setOrganizationReferenceId(null);
        }
        else {
            e.setOrganizationReferenceId(et.getOrganizationReferenceId());
        }

        // TODO 2031-2039
        originEntryService.createEntry(e, group);

        // Now generate Offset
        e = new OriginEntry(e);
        if (Constants.GL_DEBIT_CODE.equals(e.getTransactionDebitCreditCode())) {
            e.setTransactionDebitCreditCode(Constants.GL_CREDIT_CODE);
        }
        else {
            e.setTransactionDebitCreditCode(Constants.GL_DEBIT_CODE);
        }
        e.setFinancialSubObjectCode(Constants.DASHES_SUB_OBJECT_CODE);
        e.setFinancialObjectCode(icrEntry.getOffsetBalanceSheetObjectCodeNumber());

        ObjectCode balSheetObjectCode = objectCodeService.getByPrimaryId(icrEntry.getUniversityFiscalYear(), e.getChartOfAccountsCode(), icrEntry.getOffsetBalanceSheetObjectCodeNumber());
        if ( balSheetObjectCode == null ) {
            List warnings = new ArrayList(); 
            warnings.add(kualiConfigurationService.getPropertyString(KeyConstants.ERROR_INVALID_OFFSET_OBJECT_CODE) + icrEntry.getUniversityFiscalYear() + "-" + e.getChartOfAccountsCode() + "-" + icrEntry.getOffsetBalanceSheetObjectCodeNumber());
            reportErrors.put(e,warnings);
        } else {
            e.setFinancialObjectTypeCode(balSheetObjectCode.getFinancialObjectTypeCode());
        }

        if (Constants.GL_DEBIT_CODE.equals(icrEntry.getTransactionDebitIndicator())) {
            e.setTransactionLedgerEntryDescription(getChargeDescription(pct, et.getObjectCode(), et.getAccount().getAcctIndirectCostRcvyTypeCd(), et.getAccountObjectDirectCostAmount().abs()));
        }
        else {
            e.setTransactionLedgerEntryDescription(getOffsetDescription(pct, et.getAccountObjectDirectCostAmount().abs(), et.getChartOfAccountsCode(), et.getAccountNumber()));
        }

        try {
            flexibleOffsetAccountService.updateOffset(e);
        }
        catch (InvalidFlexibleOffsetException ex) {
            // TODO This should be a report thing, not an exception
            throw new IllegalArgumentException(ex.getMessage());
        }

        originEntryService.createEntry(e, group);
    }

    private static KualiDecimal ONEHUNDRED = new KualiDecimal("100");
    private static DecimalFormat DFPCT = new DecimalFormat("#0.000");
    private static DecimalFormat DFAMT = new DecimalFormat("##########.00");
    private static BigDecimal BDONEHUNDRED = new BigDecimal("100");

    private KualiDecimal getPercentage(KualiDecimal amount,BigDecimal percent) {
        BigDecimal result = amount.bigDecimalValue().multiply(percent).divide(BDONEHUNDRED,2,BigDecimal.ROUND_DOWN);
        return new KualiDecimal(result);
    }

    private String getChargeDescription(BigDecimal rate, String objectCode, String type, KualiDecimal amount) {
        BigDecimal newRate = rate.multiply(PosterServiceImpl.BDONEHUNDRED);

        StringBuffer desc = new StringBuffer("CHG ");
        if ( newRate.doubleValue() < 10 ) {
            desc.append(" ");
        }
        desc.append(DFPCT.format(newRate));
        desc.append("% ON ");
        desc.append(objectCode);
        desc.append(" (");
        desc.append(type);
        desc.append(")  ");
        String amt = DFAMT.format(amount);
        while ( amt.length() < 13 ) {
            amt = " " + amt;
        }
        desc.append(amt);
        return desc.toString();
    }

    private String getOffsetDescription(BigDecimal rate, KualiDecimal amount, String chartOfAccountsCode, String accountNumber) {
        BigDecimal newRate = rate.multiply(PosterServiceImpl.BDONEHUNDRED);

        StringBuffer desc = new StringBuffer("RCV ");
        if ( newRate.doubleValue() < 10 ) {
            desc.append(" ");
        }
        desc.append(DFPCT.format(newRate));
        desc.append("% ON ");
        String amt = DFAMT.format(amount);
        while ( amt.length() < 13 ) {
            amt = " " + amt;
        }
        desc.append(amt);
        desc.append(" FRM ");
//        desc.append(chartOfAccountsCode);
//        desc.append("-");
        desc.append(accountNumber);
        return desc.toString();
    }

    private void addReporting(Map reporting, String destination, String operation) {
        String key = destination + "," + operation;
        if (reporting.containsKey(key)) {
            Integer c = (Integer) reporting.get(key);
            reporting.put(key, new Integer(c.intValue() + 1));
        }
        else {
            reporting.put(key, new Integer(1));
        }
    }

    public void setVerifyTransaction(VerifyTransaction vt) {
        verifyTransaction = vt;
    }

    public void setTransactionPosters(List p) {
        transactionPosters = p;
    }

    public void setOriginEntryService(OriginEntryService oes) {
        originEntryService = oes;
    }

    public void setOriginEntryGroupService(OriginEntryGroupService oes) {
        originEntryGroupService = oes;
    }

    public void setDateTimeService(DateTimeService dts) {
        dateTimeService = dts;
    }

    public void setReversalDao(ReversalDao red) {
        reversalDao = red;
    }

    public void setUniversityDateDao(UniversityDateDao udd) {
        universityDateDao = udd;
    }

    public void setAccountingPeriodService(AccountingPeriodService aps) {
        accountingPeriodService = aps;
    }

    public void setExpenditureTransactionDao(ExpenditureTransactionDao etd) {
        expenditureTransactionDao = etd;
    }

    public void setIcrAutomatedEntryDao(IcrAutomatedEntryDao iaed) {
        icrAutomatedEntryDao = iaed;
    }

    public void setObjectCodeService(ObjectCodeService ocs) {
        objectCodeService = ocs;
    }

    public void setReportService(ReportService rs) {
        reportService = rs;
    }

    public void setBeanFactory(BeanFactory bf) throws BeansException {
        beanFactory = bf;
    }

    public void init() {
        LOG.debug("init() started");

        // If we are in test mode
        if (beanFactory.containsBean("testDateTimeService")) {
            dateTimeService = (DateTimeService) beanFactory.getBean("testDateTimeService");
        }
    }

    /**
     * Sets the kualiConfigurationService attribute value.
     * @param kualiConfigurationService The kualiConfigurationService to set.
     */
    public void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
}
}
