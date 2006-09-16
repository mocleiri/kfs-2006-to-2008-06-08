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

import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.kuali.Constants;
import org.kuali.core.bo.user.Options;
import org.kuali.core.service.DateTimeService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.service.OptionsService;
import org.kuali.core.service.PersistenceService;
import org.kuali.core.util.KualiDecimal;
import org.kuali.module.gl.batch.poster.PostTransaction;
import org.kuali.module.gl.bo.OriginEntry;
import org.kuali.module.gl.bo.OriginEntryGroup;
import org.kuali.module.gl.bo.OriginEntrySource;
import org.kuali.module.gl.bo.SufficientFundRebuild;
import org.kuali.module.gl.bo.Transaction;
import org.kuali.module.gl.service.BalanceService;
import org.kuali.module.gl.service.OriginEntryGroupService;
import org.kuali.module.gl.service.OriginEntryService;
import org.kuali.module.gl.service.PosterService;
import org.kuali.module.gl.service.ReportService;
import org.kuali.module.gl.service.ReversalService;
import org.kuali.module.gl.service.impl.scrubber.DemergerReportData;
import org.kuali.module.gl.service.impl.scrubber.Message;
import org.kuali.module.gl.service.impl.scrubber.ScrubberReportData;
import org.kuali.module.gl.util.BalanceEncumbranceReport;
import org.kuali.module.gl.util.BalanceReport;
import org.kuali.module.gl.util.LedgerEntryHolder;
import org.kuali.module.gl.util.LedgerReport;
import org.kuali.module.gl.util.NominalActivityClosingTransactionReport;
import org.kuali.module.gl.util.Summary;
import org.kuali.module.gl.util.TransactionListingReport;
import org.kuali.module.gl.util.TransactionReport;

import com.lowagie.text.Document;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author Laran Evans <lc278@cornell.edu>
 * @version $Id$
 */
public class ReportServiceImpl implements ReportService {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ReportServiceImpl.class);

    String batchReportsDirectory;
    String onlineReportsDirectory;
    private OriginEntryService originEntryService;
    private OriginEntryGroupService originEntryGroupService;
    private DateTimeService dateTimeService;
    private BalanceService balanceService;
    private OptionsService optionsService;
    private ReversalService reversalService;
    private KualiConfigurationService kualiConfigurationService;
    private PersistenceService persistenceService;

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");

    public ReportServiceImpl() {
        super();
    }

    public void init() {
        batchReportsDirectory = kualiConfigurationService.getPropertyString(Constants.BATCH_REPORTS_DIRECTORY);
        onlineReportsDirectory = kualiConfigurationService.getPropertyString(Constants.ONLINE_REPORTS_DIRECTORY);
    }

    /**
     * @see org.kuali.module.gl.service.ReportService#generatePendingEntryReport(java.util.Date)
     */
    public void generatePendingEntryReport() {
        
        Date runDate = dateTimeService.getCurrentDate();
        String title = "PENDING LEDGER ENTRY TABLE";
        String filePrefix = "glpe_ledger";
        
        Font headerFont = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD);
        Font textFont = FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL);
        
        Document document = new Document(PageSize.A4.rotate());
        
        TransactionReport.PageHelper helper = new TransactionReport.PageHelper();
        
        helper.runDate = runDate;
        helper.headerFont = headerFont;
        helper.title = title;
        
        try {
            String filename = batchReportsDirectory + "/" + filePrefix + "_";
            
            filename = filename + sdf.format(runDate);
            filename = filename + ".pdf";
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
            writer.setPageEvent(helper);
            
            document.open();
            
            float[] columnWidths = new float[] {10, 10, 10, 10, 10, 10, 10, 10, 10};
            
            PdfPTable dataTable = new PdfPTable(columnWidths);
            dataTable.setHeaderRows(1);
            dataTable.setWidthPercentage(100);
            
            String[] columnHeaders = new String[] {
                    "Doc Type", "Document Number", "Bal Type", "COA Code",
                    "Account Number", "Object Code", "Debit", "Credit", "Blank" };
            
            for(int x = 0; x < columnHeaders.length; x++) {
                PdfPCell cell = new PdfPCell(new Phrase(columnHeaders[x], headerFont));
                dataTable.addCell(cell);
            }
            
            document.add(dataTable);
            
            Collection groups = originEntryGroupService.getGroupsFromSourceForDate(OriginEntrySource.GENERATE_BY_EDOC, new java.sql.Date(runDate.getTime()));
            
            // We use the collection/iterator out of necessity. But there should only be one group in the collection.
            String previousDocumentType = "-1";
            String previousDocumentNumber = "-1";
            
            for(Iterator groupIterator = groups.iterator(); groupIterator.hasNext();) {
                
                OriginEntryGroup originEntryGroup = (OriginEntryGroup) groupIterator.next();
                
//                PdfPTable dataTable = new PdfPTable(columnWidths);
//                dataTable.setWidthPercentage(100);
                
                int countForDocumentType = 0;
                
                KualiDecimal debitClusterTotal = new KualiDecimal(0);
                KualiDecimal creditClusterTotal = new KualiDecimal(0);
                KualiDecimal unassignedClusterTotal = new KualiDecimal(0);
                
                KualiDecimal documentTypeCreditTotal = new KualiDecimal(0);
                KualiDecimal documentTypeDebitTotal = new KualiDecimal(0);
                KualiDecimal documentTypeUnassignedTotal = new KualiDecimal(0);
                
                PdfPCell column = null;
                
                for(Iterator entries = originEntryService.getEntriesByGroupReportOrder(originEntryGroup); entries.hasNext();) {
                    
                    OriginEntry entry = (OriginEntry) entries.next();
                    persistenceService.retrieveNonKeyFields(entry);
                    
                    if(!entry.getFinancialDocumentNumber().equals(previousDocumentNumber) && !"-1".equals(previousDocumentNumber)) {
                        
                        column = new PdfPCell(new Phrase("Totals:", textFont));
                        column.setColspan(6);
                        column.setPaddingTop(10.0F);
                        column.setPaddingBottom(10.0F);
                        column.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                        dataTable.addCell(column);
                        
                        String s = debitClusterTotal.toString();
                        column = new PdfPCell(new Phrase("".equals(s.trim()) ? "0.00" : s, textFont));
                        column.setPaddingTop(10.0F);
                        column.setPaddingBottom(10.0F);
                        column.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                        dataTable.addCell(column);
                        
                        s = creditClusterTotal.toString();
                        column = new PdfPCell(new Phrase("".equals(s.trim()) ? "0.00" : s, textFont));
                        column.setPaddingTop(10.0F);
                        column.setPaddingBottom(10.0F);
                        column.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                        dataTable.addCell(column);
                        
                        s = unassignedClusterTotal.toString();
                        column = new PdfPCell(new Phrase("".equals(s.trim()) ? "0.00" : s, textFont));
                        column.setPaddingTop(10.0F);
                        column.setPaddingBottom(10.0F);
                        column.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                        dataTable.addCell(column);
                        
                        // reset totals for the new cluster
                        debitClusterTotal = new KualiDecimal(0);
                        creditClusterTotal = new KualiDecimal(0);
                        unassignedClusterTotal = new KualiDecimal(0);
                        
                    }
                    
                    // Show doc type totals.
                    if(!entry.getFinancialDocumentTypeCode().equals(previousDocumentType) && !"-1".equals(previousDocumentType)) {
                        
                        column = new PdfPCell(new Phrase("Totals for Document Type " + previousDocumentType + " Cnt: " + countForDocumentType, textFont));
                        column.setColspan(6);
                        column.setPaddingTop(10.0F);
                        column.setPaddingBottom(10.0F);
                        column.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                        dataTable.addCell(column);
                        
                        String s = documentTypeDebitTotal.toString();
                        column = new PdfPCell(new Phrase("".equals(s.trim()) ? "0.00" : s, textFont));
                        column.setPaddingTop(10.0F);
                        column.setPaddingBottom(10.0F);
                        column.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                        dataTable.addCell(column);
                        
                        s = documentTypeCreditTotal.toString();
                        column = new PdfPCell(new Phrase("".equals(s.trim()) ? "0.00" : s, textFont));
                        column.setPaddingTop(10.0F);
                        column.setPaddingBottom(10.0F);
                        column.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                        dataTable.addCell(column);
                        
                        s = documentTypeUnassignedTotal.toString();
                        column = new PdfPCell(new Phrase("".equals(s.trim()) ? "0.00" : s, textFont));
                        column.setPaddingTop(10.0F);
                        column.setPaddingBottom(10.0F);
                        column.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                        dataTable.addCell(column);
                        
                        documentTypeCreditTotal = new KualiDecimal(0);
                        documentTypeDebitTotal = new KualiDecimal(0);
                        documentTypeUnassignedTotal = new KualiDecimal(0);
                        
                    }
                    
                    if(!entry.getFinancialDocumentNumber().equals(previousDocumentNumber)) {
                        
                        column = new PdfPCell(new Phrase(entry.getFinancialDocumentTypeCode(), textFont));
                        column.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                        dataTable.addCell(column);
                        
                        column = new PdfPCell(new Phrase(entry.getFinancialDocumentNumber(), textFont));
                        column.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                        dataTable.addCell(column);
                        
                        column = new PdfPCell(new Phrase(entry.getFinancialBalanceTypeCode(), textFont));
                        column.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                        dataTable.addCell(column);
                        
                        if(!entry.getFinancialDocumentTypeCode().equals(previousDocumentType)) {
                            
                            previousDocumentType = entry.getFinancialDocumentTypeCode();
                            countForDocumentType = 0;
                            
                        }
                        
                        countForDocumentType++;
                        
                        previousDocumentNumber = entry.getFinancialDocumentNumber();
                        
                    } else {
                        
                        column = new PdfPCell(new Phrase(" ", textFont));
                        column.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                        dataTable.addCell(column);
                        
                        column = new PdfPCell(new Phrase(" ", textFont));
                        column.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                        dataTable.addCell(column);
                        
                        column = new PdfPCell(new Phrase(" ", textFont));
                        column.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                        dataTable.addCell(column);
                        
                    }
                    
                    column = new PdfPCell(new Phrase(entry.getChartOfAccountsCode(), textFont));
                    column.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    dataTable.addCell(column);
                    
                    column = new PdfPCell(new Phrase(entry.getAccountNumber(), textFont));
                    column.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    dataTable.addCell(column);
                    
                    column = new PdfPCell(new Phrase(entry.getFinancialObjectCode(), textFont));
                    column.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    dataTable.addCell(column);
                    
                    KualiDecimal amount = null;
                    
                    if(Constants.GL_DEBIT_CODE.equalsIgnoreCase(entry.getTransactionDebitCreditCode())) {
                        amount = entry.getTransactionLedgerEntryAmount();
                        debitClusterTotal = debitClusterTotal.add(amount);
                        documentTypeDebitTotal = documentTypeDebitTotal.add(amount);
                    }
                    column = new PdfPCell(new Phrase(null == amount ? " " : amount.toString(), textFont));
                    column.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    dataTable.addCell(column);
                    
                    amount = null;
                    if(Constants.GL_CREDIT_CODE.equalsIgnoreCase(entry.getTransactionDebitCreditCode())) {
                        amount = entry.getTransactionLedgerEntryAmount();
                        creditClusterTotal = creditClusterTotal.add(amount);
                        documentTypeCreditTotal = documentTypeCreditTotal.add(amount);
                    }
                    column = new PdfPCell(new Phrase(null == amount ? " " : amount.toString(), textFont));
                    column.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    dataTable.addCell(column);
                    
                    amount = null;
                    if(!Constants.GL_CREDIT_CODE.equalsIgnoreCase(entry.getTransactionDebitCreditCode()) && !Constants.GL_DEBIT_CODE.equals(entry.getTransactionDebitCreditCode())) {
                        amount = entry.getTransactionLedgerEntryAmount();
                        unassignedClusterTotal = unassignedClusterTotal.add(amount);
                        documentTypeUnassignedTotal = documentTypeUnassignedTotal.add(amount);
                    }
                    column = new PdfPCell(new Phrase(null == amount ? " " : amount.toString(), textFont));
                    column.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    dataTable.addCell(column);
                    
                }
                
                // If there were entries, write the totals after the last entry in the group.
                
                if(!"-1".equals(previousDocumentNumber)) {
                    
                    column = new PdfPCell(new Phrase("Totals:", textFont));
                    column.setColspan(6);
                    column.setPaddingTop(10.0F);
                    column.setPaddingBottom(10.0F);
                    column.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    dataTable.addCell(column);
                    
                    String s = debitClusterTotal.toString();
                    column = new PdfPCell(new Phrase("".equals(s.trim()) ? "0.00" : s, textFont));
                    column.setPaddingTop(10.0F);
                    column.setPaddingBottom(10.0F);
                    column.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    dataTable.addCell(column);
                    
                    s = creditClusterTotal.toString();
                    column = new PdfPCell(new Phrase("".equals(s.trim()) ? "0.00" : s, textFont));
                    column.setPaddingTop(10.0F);
                    column.setPaddingBottom(10.0F);
                    column.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    dataTable.addCell(column);
                    
                    s = unassignedClusterTotal.toString();
                    column = new PdfPCell(new Phrase("".equals(s.trim()) ? "0.00" : s, textFont));
                    column.setPaddingTop(10.0F);
                    column.setPaddingBottom(10.0F);
                    column.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    dataTable.addCell(column);
                    
                    // reset totals for the new cluster
                    debitClusterTotal = new KualiDecimal(0);
                    creditClusterTotal = new KualiDecimal(0);
                    unassignedClusterTotal = new KualiDecimal(0);
                    
                }
                
                // Show doc type totals.
                if(!"-1".equals(previousDocumentType)) {
                    
                    column = new PdfPCell(new Phrase("Totals for Document Type " + previousDocumentType + " Cnt: " + countForDocumentType, textFont));
                    column.setColspan(6);
                    column.setPaddingTop(10.0F);
                    column.setPaddingBottom(10.0F);
                    column.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    dataTable.addCell(column);
                    
                    String s = documentTypeDebitTotal.toString();
                    column = new PdfPCell(new Phrase("".equals(s.trim()) ? "0.00" : s, textFont));
                    column.setPaddingTop(10.0F);
                    column.setPaddingBottom(10.0F);
                    column.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    dataTable.addCell(column);
                    
                    s = documentTypeCreditTotal.toString();
                    column = new PdfPCell(new Phrase("".equals(s.trim()) ? "0.00" : s, textFont));
                    column.setPaddingTop(10.0F);
                    column.setPaddingBottom(10.0F);
                    column.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    dataTable.addCell(column);
                    
                    s = documentTypeUnassignedTotal.toString();
                    column = new PdfPCell(new Phrase("".equals(s.trim()) ? "0.00" : s, textFont));
                    column.setPaddingTop(10.0F);
                    column.setPaddingBottom(10.0F);
                    column.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    dataTable.addCell(column);
                    
                    documentTypeCreditTotal = new KualiDecimal(0);
                    documentTypeDebitTotal = new KualiDecimal(0);
                    documentTypeUnassignedTotal = new KualiDecimal(0);
                    
                }                
                
                document.add(dataTable);
                
            }
            
        }
        catch (Exception de) {
            LOG.error("generateReport() Error creating PDF report", de);
            throw new RuntimeException("Report Generation Failed");
        }
        
        document.close();
    }
        
    /**
     * 
     * @see org.kuali.module.gl.service.ReportService#generateSufficientFundsReport(java.util.Map, java.util.List, java.util.Date,
     *      int)
     */
    public void generateSufficientFundsReport(Map reportErrors, List reportSummary, Date runDate, int mode) {
        LOG.debug("generateReport() started");

        String title = "Sufficient Funds Report ";
        String fileprefix = "sufficientFunds";

        Font headerFont = FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD);
        Font textFont = FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL);

        Document document = new Document(PageSize.A4.rotate());

        SfPageHelper helper = new SfPageHelper();
        helper.runDate = runDate;
        helper.headerFont = headerFont;
        helper.title = title;

        try {
            String filename = batchReportsDirectory + "/" + fileprefix + "_";

            filename = filename + sdf.format(runDate);
            filename = filename + ".pdf";
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
            writer.setPageEvent(helper);

            document.open();

            // Sort what we get
            Collections.sort(reportSummary);

            float[] summaryWidths = { 90, 10 };
            PdfPTable summary = new PdfPTable(summaryWidths);
            summary.setWidthPercentage(40);
            PdfPCell cell = new PdfPCell(new Phrase("S T A T I S T I C S", headerFont));
            cell.setColspan(2);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            summary.addCell(cell);

            for (Iterator iter = reportSummary.iterator(); iter.hasNext();) {
                Summary s = (Summary) iter.next();

                cell = new PdfPCell(new Phrase(s.getDescription(), textFont));
                cell.setBorder(Rectangle.NO_BORDER);
                summary.addCell(cell);

                if ("".equals(s.getDescription())) {
                    cell = new PdfPCell(new Phrase("", textFont));
                    cell.setBorder(Rectangle.NO_BORDER);
                    summary.addCell(cell);
                }
                else {
                    DecimalFormat nf = new DecimalFormat("###,###,##0");
                    cell = new PdfPCell(new Phrase(nf.format(s.getCount()), textFont));
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    summary.addCell(cell);
                }
            }
            cell = new PdfPCell(new Phrase(""));
            cell.setColspan(2);
            cell.setBorder(Rectangle.NO_BORDER);
            summary.addCell(cell);

            document.add(summary);

            if (reportErrors != null && reportErrors.size() > 0) {
                float[] warningWidths = { 5, 12, 12, 53 };
                PdfPTable warnings = new PdfPTable(warningWidths);
                warnings.setHeaderRows(2);
                warnings.setWidthPercentage(100);
                cell = new PdfPCell(new Phrase("W A R N I N G S", headerFont));
                cell.setColspan(4);
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                warnings.addCell(cell);

                // Add headers
                cell = new PdfPCell(new Phrase("COA", headerFont));
                warnings.addCell(cell);
                cell = new PdfPCell(new Phrase("Account/Object Code", headerFont));
                warnings.addCell(cell);
                cell = new PdfPCell(new Phrase("Account/Object Type", headerFont));
                warnings.addCell(cell);
                cell = new PdfPCell(new Phrase("Warning", headerFont));
                warnings.addCell(cell);

                for (Iterator errorIter = reportErrors.keySet().iterator(); errorIter.hasNext();) {
                    SufficientFundRebuild sfrb = (SufficientFundRebuild) errorIter.next();
                    boolean first = true;

                    List errors = (List) reportErrors.get(sfrb);
                    for (Iterator listIter = errors.iterator(); listIter.hasNext();) {
                        String msg = (String) listIter.next();

                        if (first) {
                            first = false;
                            cell = new PdfPCell(new Phrase(sfrb.getChartOfAccountsCode(), textFont));
                            warnings.addCell(cell);
                            cell = new PdfPCell(new Phrase(sfrb.getAccountNumberFinancialObjectCode(), textFont));
                            warnings.addCell(cell);
                            cell = new PdfPCell(new Phrase(sfrb.getAccountFinancialObjectTypeCode(), textFont));
                            warnings.addCell(cell);
                        }
                        else {
                            cell = new PdfPCell(new Phrase("", textFont));
                            cell.setColspan(3);
                            warnings.addCell(cell);
                        }
                        cell = new PdfPCell(new Phrase(msg, textFont));
                        warnings.addCell(cell);
                    }
                }
                document.add(warnings);
            }
        }
        catch (Exception de) {
            LOG.error("generateReport() Error creating PDF report", de);
            throw new RuntimeException("Report Generation Failed");
        }

        document.close();
    }

    /**
     * 
     * @see org.kuali.module.gl.service.ReportService#generatePosterStatisticsReport(java.util.Date, java.util.Map, java.util.Map,
     *      int)
     */
    public void generatePosterStatisticsReport(Date runDate, Map<String, Integer> reportSummary, List<PostTransaction> transactionPosters, Map<Transaction, List<Message>> reportErrors, int mode) {
        LOG.debug("generatePosterStatisticsReport() started");

        // Convert our summary to a list of items for the report
        List summary = new ArrayList();
        if (mode == PosterService.MODE_REVERSAL) {
            summary.add(new Summary(1, "Number of GL_REVERSAL_T records selected:", (Integer) reportSummary.get("GL_REVERSAL_T,S")));
        }
        else {
            summary.add(new Summary(1, "Number of GL_ORIGIN_ENTRY_T records selected:", (Integer) reportSummary.get("GL_ORIGIN_ENTRY_T,S")));
        }
        summary.add(new Summary(2, "", 0));

        int count = 10;
        for (Iterator posterIter = transactionPosters.iterator(); posterIter.hasNext();) {
            PostTransaction poster = (PostTransaction) posterIter.next();
            String table = poster.getDestinationName();
            summary.add(new Summary(count++, "Number of " + table + " records deleted:", (Integer) reportSummary.get(table + ",D")));
            summary.add(new Summary(count++, "Number of " + table + " records inserted:", (Integer) reportSummary.get(table + ",I")));
            summary.add(new Summary(count++, "Number of " + table + " records updated:", (Integer) reportSummary.get(table + ",U")));
            summary.add(new Summary(count++, "", 0));
        }

        summary.add(new Summary(10000, "", 0));
        summary.add(new Summary(10001, "Number of WARNING records selected:", (Integer) reportSummary.get("WARNING,S")));

        TransactionReport tr = new TransactionReport();

        String title = "Poster Report ";
        String filename;
        if (mode == PosterService.MODE_ENTRIES) {
            title = title + "(Post pending entries)";
            filename = "poster_main";
        }
        else if (mode == PosterService.MODE_ICR) {
            title = title + "(Post ICR entries)";
            filename = "poster_icr";
        }
        else {
            title = title + "(Post reversal entries)";
            filename = "poster_reversal";
        }

        tr.generateReport(reportErrors, summary, runDate, title, filename, batchReportsDirectory);
    }

    /**
     * 
     * @see org.kuali.module.gl.service.ReportService#generateIcrEncumrbanceStatisticsReport(java.util.Date, int, int)
     */
    public void generateIcrEncumbranceStatisticsReport(Date runDate, int totalOfIcrEncumbrances, int totalOfEntriesGenerated) {
        LOG.debug("generateIcrEncumrbanceStatisticsReport() started");

        List reportSummaryList = new ArrayList();
        reportSummaryList.add(new Summary(1, "Number of ICR Encumbrances retrived:", totalOfIcrEncumbrances));
        reportSummaryList.add(new Summary(2, "Number of Origin Entries generated:", totalOfEntriesGenerated));

        Map<Transaction, List<Message>> errors = new HashMap<Transaction, List<Message>>();

        TransactionReport tr = new TransactionReport();
        tr.generateReport(errors, reportSummaryList, runDate, "ICR Encumbrance Report", "icr_encumbrance", batchReportsDirectory);
    }

    /**
     * 
     * @see org.kuali.module.gl.service.ReportService#generatePosterIcrStatisticsReport(java.util.Date, java.util.Map, int, int,
     *      int, int)
     */
    public void generatePosterIcrStatisticsReport(Date runDate, Map<Transaction, List<Message>> reportErrors, int reportExpendTranRetrieved, int reportExpendTranDeleted, int reportExpendTranKept, int reportOriginEntryGenerated) {
        LOG.debug("generatePosterIcrStatisticsReport() started");

        List summary = new ArrayList();
        summary.add(new Summary(1, "Number of GL_EXPEND_TRAN_T records retrieved:", reportExpendTranRetrieved));
        summary.add(new Summary(2, "Number of GL_EXPEND_TRAN_T records deleted:", reportExpendTranDeleted));
        summary.add(new Summary(3, "Number of GL_EXPEND_TRAN_T records kept due to errors:", reportExpendTranKept));
        summary.add(new Summary(4, "", 0));
        summary.add(new Summary(3, "Number of GL_ORIGIN_ENTRY_T records generated:", reportOriginEntryGenerated));

        TransactionReport tr = new TransactionReport();
        tr.generateReport(reportErrors, summary, runDate, "ICR Generation Report", "icr_generation", batchReportsDirectory);
    }

    /**
     * 
     * @see org.kuali.module.gl.service.ReportService#generateScrubberLedgerSummaryReportBatch(java.util.Date, java.util.Collection)
     */
    public void generateScrubberLedgerSummaryReportBatch(Date runDate, Collection groups) {
        LOG.debug("generateScrubberLedgerSummaryReport() started");

        LedgerReport ledgerReport = new LedgerReport();
        LedgerEntryHolder ledgerEntries = new LedgerEntryHolder();
        if (groups.size() > 0) {
            ledgerEntries = originEntryService.getSummaryByGroupId(groups);
        }

        ledgerReport.generateReport(ledgerEntries, runDate, "Ledger Report", "scrubber_ledger", batchReportsDirectory);
    }

    /**
     * 
     * @see org.kuali.module.gl.service.ReportService#generateScrubberLedgerSummaryReportBatch(java.util.Date, java.util.Collection)
     */
    public void generateLedgerSummaryReport(Date runDate, Collection groups) {
        LOG.debug("generateLedgerSummaryReport() started");

        LedgerReport ledgerReport = new LedgerReport();
        LedgerEntryHolder ledgerEntries = new LedgerEntryHolder();
        if (groups.size() > 0) {
            ledgerEntries = originEntryService.getSummaryByGroupId(groups);
        }

        ledgerReport.generateReport(ledgerEntries, runDate, "GLPE Statistics Report", "glpe_statistics", batchReportsDirectory);
    }
    
    /**
     * 
     * @see org.kuali.module.gl.service.ReportService#generateScrubberLedgerSummaryReportOnline(java.util.Date, org.kuali.module.gl.bo.OriginEntryGroup)
     */
    public void generateScrubberLedgerSummaryReportOnline(Date runDate, OriginEntryGroup group) {
        LOG.debug("generateScrubberLedgerSummaryReport() started");

        LedgerReport ledgerReport = new LedgerReport();
        LedgerEntryHolder ledgerEntries = new LedgerEntryHolder();

        Collection g = new ArrayList();
        g.add(group);

        ledgerEntries = originEntryService.getSummaryByGroupId(g);

        ledgerReport.generateReport(ledgerEntries, runDate, "Ledger Report", "scrubber_ledger_" + group.getId(), onlineReportsDirectory);
    }

    /**
     * 
     * @see org.kuali.module.gl.service.ReportService#generateScrubberStatisticsReport(java.util.Date,
     *      org.kuali.module.gl.service.impl.scrubber.ScrubberReportData, java.util.Map)
     */
    public void generateBatchScrubberStatisticsReport(Date runDate, ScrubberReportData scrubberReport, Map<Transaction, List<Message>> scrubberReportErrors) {
        LOG.debug("generateScrubberStatisticsReport() started");

        List tranKeys = new ArrayList();
        tranKeys.addAll(scrubberReportErrors.keySet());

        Collections.sort(tranKeys, new Comparator<Transaction>() {
            public int compare(Transaction t1, Transaction t2) {
                StringBuffer sb1 = new StringBuffer();
                sb1.append(t1.getFinancialDocumentTypeCode());
                sb1.append(t1.getFinancialSystemOriginationCode());
                sb1.append(t1.getFinancialDocumentNumber());
                sb1.append(t1.getChartOfAccountsCode());
                sb1.append(t1.getAccountNumber());
                sb1.append(t1.getSubAccountNumber());
                sb1.append(t1.getFinancialBalanceTypeCode());

                StringBuffer sb2 = new StringBuffer();
                sb2.append(t2.getFinancialDocumentTypeCode());
                sb2.append(t2.getFinancialSystemOriginationCode());
                sb2.append(t2.getFinancialDocumentNumber());
                sb2.append(t2.getChartOfAccountsCode());
                sb2.append(t2.getAccountNumber());
                sb2.append(t2.getSubAccountNumber());
                sb2.append(t2.getFinancialBalanceTypeCode());
                return sb1.toString().compareTo(sb2.toString());
            }
        });

        List summary = buildScrubberReportSummary(scrubberReport);

        TransactionReport transactionReport = new TransactionReport();
        transactionReport.generateReport(tranKeys, scrubberReportErrors, summary, runDate, "Scrubber Report ", "scrubber", batchReportsDirectory);
    }

    /**
     * 
     * @see org.kuali.module.gl.service.ReportService#generateScrubberStatisticsReport(java.util.Date,
     *      org.kuali.module.gl.service.impl.scrubber.ScrubberReportData, java.util.Map)
     */
    public void generateOnlineScrubberStatisticsReport(Integer groupId, Date runDate, ScrubberReportData scrubberReport, Map<Transaction, List<Message>> scrubberReportErrors) {
        LOG.debug("generateScrubberStatisticsReport() started");

        List summary = buildScrubberReportSummary(scrubberReport);

        TransactionReport transactionReport = new TransactionReport();
        transactionReport.generateReport(scrubberReportErrors, summary, runDate, "Scrubber Report ", "scrubber_" + groupId.toString(), onlineReportsDirectory);
    }

    /**
     * 
     * @see org.kuali.module.gl.service.ReportService#generateScrubberDemergerStatisticsReports(java.util.Date,
     *      org.kuali.module.gl.service.impl.scrubber.DemergerReportData)
     */
    public void generateScrubberDemergerStatisticsReports(Date runDate, DemergerReportData demergerReport) {
        LOG.debug("generateScrubberDemergerStatisticsReports() started");

        List summary = buildDemergerReportSummary(demergerReport);

        Map<Transaction, List<Message>> empty = new HashMap<Transaction, List<Message>>();

        TransactionReport transactionReport = new TransactionReport();
        transactionReport.generateReport(empty, summary, runDate, "Demerger Report ", "demerger", batchReportsDirectory);
    }

    /**
     * 
     * @see org.kuali.module.gl.service.ReportService#generateScrubberBadBalanceTypeListingReport(java.util.Date,
     *      java.util.Collection)
     */
    public void generateScrubberBadBalanceTypeListingReport(Date runDate, Collection groups) {
        LOG.debug("generateScrubberBadBalanceTypeListingReport() started");

        Iterator i = null;
        if (groups.size() > 0) {
            i = originEntryService.getBadBalanceEntries(groups);
        }

        TransactionListingReport rept = new TransactionListingReport();
        rept.generateReport(i, runDate, "Scrubber Input Transactions with Bad Balance Types", "scrubber_badbal", batchReportsDirectory);
    }

    public void generateScrubberTransactionsOnline(Date runDate, OriginEntryGroup validGroup) {
        LOG.debug("generateScrubberTransactionsOnline() started");

        Iterator ti = originEntryService.getEntriesByGroupAccountOrder(validGroup);

        TransactionListingReport rept = new TransactionListingReport();
        rept.generateReport(ti, runDate, "Output Transaction Listing From the Scrubber", "scrubber_listing_" + validGroup, onlineReportsDirectory);        
    }

    /**
     * 
     * @see org.kuali.module.gl.service.ReportService#generateScrubberRemovedTransactions(java.util.Date,
     *      org.kuali.module.gl.bo.OriginEntryGroup)
     */
    public void generateScrubberRemovedTransactions(Date runDate, OriginEntryGroup errorGroup) {
        LOG.debug("generateScrubberRemovedTransactions() started");

        Iterator ti = originEntryService.getEntriesByGroupAccountOrder(errorGroup);

        TransactionListingReport rept = new TransactionListingReport();
        rept.generateReport(ti, runDate, "Error Listing - Transactions Remove From the Scrubber", "scrubber_errors", batchReportsDirectory);
    }

    /**
     * 
     * @see org.kuali.module.gl.service.ReportService#generateGlSummary(java.util.Date, int, java.util.List)
     */
    public void generateGlSummary(Date runDate, Options year, String reportType) {
        LOG.debug("generateGlSummary() started");

        List balanceTypeCodes = new ArrayList();
        if ("act".equals(reportType)) {
            balanceTypeCodes.add(year.getActualFinancialBalanceTypeCd());
        }
        else {
            balanceTypeCodes.add(year.getBudgetCheckingBalanceTypeCd());

            // TODO these may need fields in the fs_option_t table
            balanceTypeCodes.add("BB");
            balanceTypeCodes.add("MB");
        }

        List balances = balanceService.getGlSummary(year.getUniversityFiscalYear(), balanceTypeCodes);

        BalanceReport rept = new BalanceReport();
        rept.generateReport(runDate, balances, year.getUniversityFiscalYearName(), balanceTypeCodes, "glsummary_" + year.getUniversityFiscalYear() + "_" + reportType, batchReportsDirectory);
    }

    /**
     * 
     * @see org.kuali.module.gl.service.ReportService#generateGlEncumbranceSummary(java.util.Date, int, java.util.List,
     *      java.lang.String)
     */
    public void generateGlEncumbranceSummary(Date runDate, Options year, String reportType) {
        LOG.debug("generateGlEncumbranceSummary() started");

        List balanceTypeCodes = new ArrayList();
        balanceTypeCodes.add(year.getExtrnlEncumFinBalanceTypCd());
        balanceTypeCodes.add(year.getIntrnlEncumFinBalanceTypCd());
        balanceTypeCodes.add(year.getPreencumbranceFinBalTypeCd());
        balanceTypeCodes.add(year.getCostShareEncumbranceBalanceTypeCode());

        List balances = balanceService.getGlSummary(year.getUniversityFiscalYear(), balanceTypeCodes);

        BalanceEncumbranceReport rept = new BalanceEncumbranceReport();
        rept.generateReport(runDate, balances, year.getUniversityFiscalYearName(), balanceTypeCodes, "glsummary_" + year.getUniversityFiscalYear() + "_" + reportType, batchReportsDirectory);
    }

    public void generateYearEndEncumbranceForwardReports(Date runDate, List reportSummary, Map reportErrors, Map ledgerEntries) {
        LOG.debug("Entering generateYearEndEncumbranceReports()");
        TransactionReport transactionReport = new TransactionReport();
        String title = "Encumbrance Closing Report ";
        transactionReport.generateReport(null, reportSummary, runDate, title, "year_end_encumbrance_closing", batchReportsDirectory);
    }

    public void generateYearEndBalanceForwardReports(Date runDate, List reportSummary, Map reportErrors, Map ledgerEntries) {
        LOG.debug("Entering generateYearEndBalanceForwardReports()");
        TransactionReport transactionReport = new TransactionReport();
        String title = "Balance Forward Report ";
        transactionReport.generateReport(null, reportSummary, runDate, title, "year_end_balance_forward", batchReportsDirectory);
    }

    public void generatePosterMainLedgerSummaryReport(Date runDate, Collection groups) {
        LOG.debug("generatePosterMainLedgerSummaryReport() started");

        LedgerEntryHolder ledgerEntries = new LedgerEntryHolder();
        if (groups.size() > 0) {
            ledgerEntries = originEntryService.getSummaryByGroupId(groups);
        }

        LedgerReport ledgerReport = new LedgerReport();
        ledgerReport.generateReport(ledgerEntries, runDate, "Main Poster Input Transactions", "poster_main_ledger", batchReportsDirectory);
    }

    public void generatePosterIcrLedgerSummaryReport(Date runDate, Collection groups) {
        LOG.debug("generatePosterIcrLedgerSummaryReport() started");

        LedgerEntryHolder ledgerEntries = new LedgerEntryHolder();
        if (groups.size() > 0) {
            ledgerEntries = originEntryService.getSummaryByGroupId(groups);
        }

        LedgerReport ledgerReport = new LedgerReport();
        ledgerReport.generateReport(ledgerEntries, runDate, "Icr Poster Input Transactions", "poster_icr_ledger", batchReportsDirectory);
    }

    /**
     * 
     * @see org.kuali.module.gl.service.ReportService#generatePosterReversalLedgerSummaryReport(java.util.Date,
     *      java.util.Iterator)
     */
    public void generatePosterReversalLedgerSummaryReport(Date runDate, Iterator reversals) {
        LOG.debug("generatePosterReversalLedgerSummaryReport() started");

        LedgerEntryHolder ledgerEntries = new LedgerEntryHolder();
        if (reversals.hasNext()) {
            ledgerEntries = reversalService.getSummaryByDate(runDate);
        }

        LedgerReport ledgerReport = new LedgerReport();
        ledgerReport.generateReport(ledgerEntries, runDate, "Reversal Poster Input Transactions", "poster_reversal_ledger", batchReportsDirectory);
    }

    /**
     * 
     * @see org.kuali.module.gl.service.ReportService#generateBalanceForwardStatisticsReport(java.util.List, java.util.Date)
     */
    public void generateBalanceForwardStatisticsReport(List reportSummary, Date runDate) {
        LOG.debug("generateBalanceForwardStatisticsReport() started");

        TransactionReport transactionReport = new TransactionReport();
        String title = "Balance Forward Report ";
        transactionReport.generateReport(new HashMap<Transaction, List<Message>>(), reportSummary, runDate, title, "year_end_balance_forward", batchReportsDirectory);
    }

    /**
     * 
     * @see org.kuali.module.gl.service.ReportService#generateEncumbranceClosingStatisticsReport(java.util.List, java.util.Date)
     */
    public void generateEncumbranceClosingStatisticsReport(List reportSummary, Date runDate) {
        LOG.debug("generateEncumbranceForwardStatisticsReport() started");

        TransactionReport transactionReport = new TransactionReport();
        String title = "Encumbrance Closing Report ";
        transactionReport.generateReport(new HashMap<Transaction, List<Message>>(), reportSummary, runDate, title, "year_end_encumbrance_closing", batchReportsDirectory);
    }

    /**
     * 
     * @see org.kuali.module.gl.service.ReportService#generateNominalActivityClosingStatisticsReport(java.util.Map, java.util.List,
     *      java.util.Date)
     */
    public void generateNominalActivityClosingStatisticsReport(Map jobParameters, List reportSummary, Date runDate) {
        LOG.debug("generateNominalActivityClosingStatisticsReport() started");

        NominalActivityClosingTransactionReport transactionReport = new NominalActivityClosingTransactionReport();
        String title = "Nominal Activity Closing Report ";
        transactionReport.generateReport(jobParameters, null, reportSummary, runDate, title, "year_end_nominal_activity_closing", batchReportsDirectory);
    }

    /**
     * Generate the header for the scrubber status report.
     * 
     * @param scrubberReport
     * @return list of report summaries to be printed
     */
    private List<Summary> buildScrubberReportSummary(ScrubberReportData scrubberReport) {
        List<Summary> reportSummary = new ArrayList<Summary>();

        reportSummary.add(new Summary(2, "UNSCRUBBED RECORDS READ", new Integer(scrubberReport.getNumberOfUnscrubbedRecordsRead())));
        reportSummary.add(new Summary(3, "SCRUBBED RECORDS WRITTEN", new Integer(scrubberReport.getNumberOfScrubbedRecordsWritten())));
        reportSummary.add(new Summary(4, "ERROR RECORDS WRITTEN", new Integer(scrubberReport.getNumberOfErrorRecordsWritten())));
        reportSummary.add(new Summary(5, "OFFSET ENTRIES GENERATED", new Integer(scrubberReport.getNumberOfOffsetEntriesGenerated())));
        reportSummary.add(new Summary(6, "CAPITALIZATION ENTRIES GENERATED", new Integer(scrubberReport.getNumberOfCapitalizationEntriesGenerated())));
        reportSummary.add(new Summary(7, "LIABILITY ENTRIES GENERATED", new Integer(scrubberReport.getNumberOfLiabilityEntriesGenerated())));
        reportSummary.add(new Summary(8, "PLANT INDEBTEDNESS ENTRIES GENERATED", new Integer(scrubberReport.getNumberOfPlantIndebtednessEntriesGenerated())));
        reportSummary.add(new Summary(9, "COST SHARE ENTRIES GENERATED", new Integer(scrubberReport.getNumberOfCostShareEntriesGenerated())));
        reportSummary.add(new Summary(10, "COST SHARE ENC ENTRIES GENERATED", new Integer(scrubberReport.getNumberOfCostShareEncumbrancesGenerated())));
        reportSummary.add(new Summary(11, "TOTAL OUTPUT RECORDS WRITTEN", new Integer(scrubberReport.getTotalNumberOfRecordsWritten())));
        reportSummary.add(new Summary(12, "EXPIRED ACCOUNTS FOUND", new Integer(scrubberReport.getNumberOfExpiredAccountsFound())));

        return reportSummary;
    }

    /**
     * Generate the header for the demerger status report.
     * 
     * @param demergerReport
     * @return list of report summaries to be printed
     */
    private List<Summary> buildDemergerReportSummary(DemergerReportData demergerReport) {
        List<Summary> reportSummary = new ArrayList<Summary>();

        reportSummary.add(new Summary(1, "SCRUBBER ERROR TRANSACTIONS READ", new Integer(demergerReport.getErrorTransactionsRead())));
        reportSummary.add(new Summary(3, "DEMERGER ERRORS SAVED", new Integer(demergerReport.getErrorTransactionsSaved())));
        reportSummary.add(new Summary(4, "DEMERGER VALID TRANSACTIONS SAVED", new Integer(demergerReport.getValidTransactionsSaved())));
        reportSummary.add(new Summary(5, "OFFSET TRANSACTIONS BYPASSED", new Integer(demergerReport.getOffsetTransactionsBypassed())));
        reportSummary.add(new Summary(6, "CAPITALIZATION TRANSACTIONS BYPASSED", new Integer(demergerReport.getCapitalizationTransactionsBypassed())));
        reportSummary.add(new Summary(7, "LIABILITY TRANSACTIONS BYPASSED", new Integer(demergerReport.getLiabilityTransactionsBypassed())));
        reportSummary.add(new Summary(8, "TRANSFER TRANSACTIONS BYPASSED", new Integer(demergerReport.getTransferTransactionsBypassed())));
        reportSummary.add(new Summary(9, "COST SHARE TRANSACTIONS BYPASSED", new Integer(demergerReport.getCostShareTransactionsBypassed())));
        reportSummary.add(new Summary(10, "COST SHARE ENC TRANSACTIONS BYPASSED", new Integer(demergerReport.getCostShareEncumbranceTransactionsBypassed())));

        return reportSummary;
    }

    class SfPageHelper extends PdfPageEventHelper {
        public Date runDate;
        public Font headerFont;
        public String title;

        public void onEndPage(PdfWriter writer, Document document) {
            try {
                Rectangle page = document.getPageSize();
                PdfPTable head = new PdfPTable(3);
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                PdfPCell cell = new PdfPCell(new Phrase(sdf.format(runDate), headerFont));
                cell.setBorder(Rectangle.NO_BORDER);
                head.addCell(cell);

                cell = new PdfPCell(new Phrase(title, headerFont));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                head.addCell(cell);

                cell = new PdfPCell(new Phrase("Page: " + new Integer(writer.getPageNumber()), headerFont));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                head.addCell(cell);

                head.setTotalWidth(page.width() - document.leftMargin() - document.rightMargin());
                head.writeSelectedRows(0, -1, document.leftMargin(), page.height() - document.topMargin() + head.getTotalHeight(), writer.getDirectContent());
            }
            catch (Exception e) {
                throw new ExceptionConverter(e);
            }
        }
    }
    
    /**
     * @see org.kuali.module.gl.service.ReportService#generatePosterReversalTransactionsListing(java.util.Date, org.kuali.module.gl.bo.OriginEntryGroup)
     */
    public void generatePosterReversalTransactionsListing(Date runDate, OriginEntryGroup originGroup) {
        LOG.debug("generatePosterReversalTransactionsListing() started");

        Iterator ti = originEntryService.getEntriesByGroupAccountOrder(originGroup);

        TransactionListingReport report = new TransactionListingReport();
        report.generateReport(ti, runDate, "Reversal Poster Transaction Listing", "poster_reversal_list", batchReportsDirectory);     
    }

    /**
     * 
     * @see org.kuali.module.gl.service.ReportService#generatePosterErrorTransactionListing(java.util.Date, org.kuali.module.gl.bo.OriginEntryGroup, int)
     */
    public void generatePosterErrorTransactionListing(Date runDate, OriginEntryGroup group, int posterMode) {
        LOG.debug("generatePosterErrorTransactionListing() started");

        Iterator ti = originEntryService.getEntriesByGroupAccountOrder(group);

        TransactionListingReport report = new TransactionListingReport();
        if ( posterMode == PosterService.MODE_ENTRIES ) {
            report.generateReport(ti, runDate, "Main Poster Error Transaction Listing", "poster_main_error_list", batchReportsDirectory);
        } else if ( posterMode == PosterService.MODE_ICR ) {
            report.generateReport(ti, runDate, "ICR Poster Error Transaction Listing", "poster_icr_error_list", batchReportsDirectory);
        } else if ( posterMode == PosterService.MODE_REVERSAL ) {
            report.generateReport(ti, runDate, "Reversal Poster Error Transaction Listing", "poster_reversal_error_list", batchReportsDirectory);            
        }
    }

    public void setOriginEntryService(OriginEntryService originEntryService) {
        this.originEntryService = originEntryService;
    }

    public void setBalanceService(BalanceService bs) {
        balanceService = bs;
    }

    public void setOptionsService(OptionsService os) {
        optionsService = os;
    }

    public void setDateTimeService(DateTimeService dts) {
        dateTimeService = dts;
    }

    public void setKualiConfigurationService(KualiConfigurationService kcs) {
        kualiConfigurationService = kcs;
    }

    public void setOriginEntryGroupService(OriginEntryGroupService originEntryGroupService) {
        this.originEntryGroupService = originEntryGroupService;
    }
    
    public void setReversalService(ReversalService rs) {
        reversalService = rs;
    }

    public PersistenceService getPersistenceService() {
        return persistenceService;
    }

    public void setPersistenceService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }
}
