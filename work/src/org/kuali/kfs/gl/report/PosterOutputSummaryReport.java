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
package org.kuali.module.gl.util;

import java.awt.Color;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.kuali.core.util.KualiDecimal;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PosterOutputSummaryReport {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PosterOutputSummaryReport.class);
    public static final String PDF_FILE_EXTENSION = ".pdf";

    private Font headerFont = FontFactory.getFont(FontFactory.COURIER, Font.DEFAULTSIZE, Font.BOLD);
    private Font textFont = FontFactory.getFont(FontFactory.COURIER, Font.DEFAULTSIZE, Font.NORMAL);
    private Font hiddenFieldFont = FontFactory.getFont(FontFactory.COURIER, 1, Font.NORMAL, new Color(0xFF, 0xFF, 0xFF));

    private static final int TYPE_DETAIL = 1;
    private static final int TYPE_YEAR_BALANCE_SUBTOTAL = 2;
    private static final int TYPE_BALANCE_SUBTOTAL = 3;
    private static final int TYPE_TOTAL = 4;

    /**
     * This method generates report based on the given map of entries
     * 
     * @param posterInputSummaryEntryHolder the given entry map
     * @param reportingDate the reporting date
     * @param title the report title
     * @param fileprefix the prefix of the generated report file
     * @param destinationDirectory the directory where the report is located
     */
    public void generateReport(Map<String,PosterOutputSummaryEntry> data, Date reportingDate, String title, String fileprefix, String destinationDirectory) {
        LOG.debug("generateReport() started");
        Document document = new Document(PageSize.A4.rotate());

        PDFPageHelper pageHelper = new PDFPageHelper();
        pageHelper.setRunDate(reportingDate);
        pageHelper.setHeaderFont(headerFont);
        pageHelper.setTitle(title);

        try {
            String filename = destinationDirectory + "/" + fileprefix + "_";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            filename = filename + sdf.format(reportingDate);
            filename = filename + PDF_FILE_EXTENSION;

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
            writer.setPageEvent(pageHelper);

            document.open();

            if ( data.size() == 0 ) {
                document.add(buildEmptyTable());
            } else {
                printReport(data,document);
            }
        }
        catch (Exception de) {
            LOG.error("generateReport() Error creating PDF report", de);
            de.printStackTrace();
            throw new RuntimeException("Report Generation Failed");
        }
        finally {
            closeDocument(document);
        }
    }

    private PdfPTable newTable() {
        PdfPTable entryTable = new PdfPTable(new float[] { 5, 5, 5, 7, 7, 7, 7 });
        entryTable.setHeaderRows(1);
        entryTable.setWidthPercentage(100);

        addHeader(entryTable, headerFont);
        return entryTable;
    }

    private void printReport(Map<String,PosterOutputSummaryEntry> data,Document document) throws DocumentException {

        PdfPTable entryTable = newTable();

        PosterOutputSummaryEntry subTotalBalanceYear = new PosterOutputSummaryEntry();
        PosterOutputSummaryEntry subTotalBalance = new PosterOutputSummaryEntry();
        PosterOutputSummaryEntry total = new PosterOutputSummaryEntry();

        Set sortedSet = new TreeSet(data.keySet());

        boolean first = true;
        for (Iterator reportIter = sortedSet.iterator(); reportIter.hasNext();) {
            String key = (String)reportIter.next();
            PosterOutputSummaryEntry entry = data.get(key);

            if ( first ) {
                first = false;
                subTotalBalanceYear.setUniversityFiscalYear(entry.getUniversityFiscalYear());
                subTotalBalanceYear.setBalanceTypeCode(entry.getBalanceTypeCode());

                subTotalBalance.setBalanceTypeCode(entry.getBalanceTypeCode());
            }

            // Do we need to print a subtotal?
            String balanceTypeCode  = entry.getBalanceTypeCode();
            Integer fiscalYear = entry.getUniversityFiscalYear();            

            if ( (! balanceTypeCode.equals(subTotalBalanceYear.getBalanceTypeCode())) || (! fiscalYear.equals(subTotalBalanceYear.getUniversityFiscalYear())) ) {
                addRow(entryTable, subTotalBalanceYear, headerFont, PosterOutputSummaryReport.TYPE_YEAR_BALANCE_SUBTOTAL);
                subTotalBalanceYear = new PosterOutputSummaryEntry();
                subTotalBalanceYear.setUniversityFiscalYear(fiscalYear);
                subTotalBalanceYear.setBalanceTypeCode(balanceTypeCode);
            }
            if ( ! balanceTypeCode.equals(subTotalBalance.getBalanceTypeCode()) ) {
                addRow(entryTable, subTotalBalance, headerFont, PosterOutputSummaryReport.TYPE_BALANCE_SUBTOTAL);
                subTotalBalance = new PosterOutputSummaryEntry();
                subTotalBalance.setBalanceTypeCode(balanceTypeCode);
                document.add(entryTable);
                document.add(Chunk.NEXTPAGE);
                entryTable = newTable();
            }

            addRow(entryTable, entry, textFont, PosterOutputSummaryReport.TYPE_DETAIL);
            total.add(entry);
            subTotalBalance.add(entry);
            subTotalBalanceYear.add(entry);
        }

        addRow(entryTable, subTotalBalanceYear, headerFont, PosterOutputSummaryReport.TYPE_YEAR_BALANCE_SUBTOTAL);
        addRow(entryTable, subTotalBalance, headerFont, PosterOutputSummaryReport.TYPE_BALANCE_SUBTOTAL);
        addRow(entryTable, total, headerFont, PosterOutputSummaryReport.TYPE_TOTAL);

        document.add(entryTable);
    }

    // draw a table with an informative messge, instead of data
    private PdfPTable buildEmptyTable() {
        float[] tableWidths = { 100 };

        PdfPTable ledgerEntryTable = new PdfPTable(tableWidths);
        ledgerEntryTable.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase("No entries found!", headerFont));
        ledgerEntryTable.addCell(cell);

        return ledgerEntryTable;
    }

    // add a table header
    private void addHeader(PdfPTable entryTable, Font headerFont) {

        PdfPCell cell = new PdfPCell(new Phrase("Fiscal Year", headerFont));
        entryTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Period Code", headerFont));
        entryTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Fund Group", headerFont));
        entryTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Debit Amount", headerFont));
        entryTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Credit Amount", headerFont));
        entryTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Budget Amount", headerFont));
        entryTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Net Amount", headerFont));
        entryTable.addCell(cell);
    }

    // add a row with the given ledger entry into PDF table
    private void addRow(PdfPTable entryTable, PosterOutputSummaryEntry posterOutputSummaryEntry, Font textFont, int type) {
        PdfPCell cell = null;

        if ( type == PosterOutputSummaryReport.TYPE_YEAR_BALANCE_SUBTOTAL ) {
            cell = new PdfPCell(new Phrase("Subtotal(" + posterOutputSummaryEntry.getUniversityFiscalYear() + ", " + posterOutputSummaryEntry.getBalanceTypeCode() + ")", textFont));
            cell.setColspan(3);
            entryTable.addCell(cell);
        }
        else if ( type == PosterOutputSummaryReport.TYPE_BALANCE_SUBTOTAL ) {
            cell = new PdfPCell(new Phrase("Subtotal(" + posterOutputSummaryEntry.getBalanceTypeCode() + ")", textFont));
            cell.setColspan(3);
            entryTable.addCell(cell);
        }
        else if ( type == PosterOutputSummaryReport.TYPE_TOTAL ) {
            cell = new PdfPCell(new Phrase("Total", textFont));
            cell.setColspan(3);
            entryTable.addCell(cell);            
        }
        else if ( type == PosterOutputSummaryReport.TYPE_DETAIL) {
            Integer fiscalYear = posterOutputSummaryEntry.getUniversityFiscalYear();
            String stringFiscalYear = (fiscalYear != null) ? fiscalYear.toString() : "";
            cell = new PdfPCell(new Phrase(stringFiscalYear, textFont));
            entryTable.addCell(cell);

            cell = new PdfPCell(new Phrase(posterOutputSummaryEntry.getFiscalPeriodCode(), textFont));
            entryTable.addCell(cell);

            cell = new PdfPCell(new Phrase(posterOutputSummaryEntry.getFundGroup(), textFont));
            entryTable.addCell(cell);
        }

        cell = new PdfPCell(new Phrase(this.formatNumber(posterOutputSummaryEntry.getDebitAmount()), textFont));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        entryTable.addCell(cell);

        cell = new PdfPCell(new Phrase(this.formatNumber(posterOutputSummaryEntry.getCreditAmount()), textFont));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        entryTable.addCell(cell);

        cell = new PdfPCell(new Phrase(this.formatNumber(posterOutputSummaryEntry.getBudgetAmount()), textFont));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        entryTable.addCell(cell);

        cell = new PdfPCell(new Phrase(this.formatNumber(posterOutputSummaryEntry.getNetAmount()), textFont));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        entryTable.addCell(cell);
    }

    // format the given number based on its type: Integer or BigDecimal
    private String formatNumber(Number number) {
        DecimalFormat decimalFormat = new DecimalFormat();

        if (number instanceof Integer) {
            decimalFormat.applyPattern("###,###");
        }
        else if (number instanceof KualiDecimal) {
            decimalFormat.applyPattern("###,###,###,##0.00");
        }
        return decimalFormat.format(number);
    }

    // close the document and release the resource
    private void closeDocument(Document document) {
        try {
            if ((document != null) && document.isOpen()) {
                document.close();
            }
        }
        catch (Throwable t) {
            LOG.error("generateReport() Exception closing report", t);
        }
    }
}
