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
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.kuali.core.util.KualiDecimal;

import com.lowagie.text.Chapter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Section;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PosterOutputSummaryReport {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PosterOutputSummaryReport.class);
    public static final String PDF_FILE_EXTENSION = ".pdf";

    private Font headerFont = FontFactory.getFont(FontFactory.COURIER, Font.DEFAULTSIZE, Font.BOLD);
    private Font textFont = FontFactory.getFont(FontFactory.COURIER, Font.DEFAULTSIZE, Font.NORMAL);
    private Font hiddenFieldFont = FontFactory.getFont(FontFactory.COURIER, 1, Font.NORMAL, new Color(0xFF, 0xFF, 0xFF));
    
    private final float[] columnWidths = { 5, 5, 5, 7, 7, 7, 7 };

    /**
     * This method generates report based on the given map of entries
     * 
     * @param posterInputSummaryEntryHolder the given entry map
     * @param reportingDate the reporting date
     * @param title the report title
     * @param fileprefix the prefix of the generated report file
     * @param destinationDirectory the directory where the report is located
     */
    public void generateReport(Map posterOutputSummaryEntryHolder, Date runDate, String title, String fileprefix, String destinationDirectory) {
        LOG.debug("generateReport() started");
        this.generatePDFReport(posterOutputSummaryEntryHolder, runDate, title, fileprefix, destinationDirectory);
    }

    // generate the PDF report with the given information
    private void generatePDFReport(Map posterOutputSummaryEntryHolder, Date reportingDate, String title, String fileprefix, String destinationDirectory) {
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
            Chapter chapter = this.populateDocument(posterOutputSummaryEntryHolder);
            document.add(chapter);
            
        }
        catch (Exception de) {
            LOG.error("generateReport() Error creating PDF report", de);
            de.printStackTrace();
            throw new RuntimeException("Report Generation Failed");
        }
        finally {
            this.closeDocument(document);
        }
    }
    
    private Chapter populateDocument(Map posterOutputSummaryEntryHolder){        
        Paragraph title = new Paragraph("Poster Inout Transaction Summary", hiddenFieldFont);
        Chapter chapter = new Chapter(title, 1);
        
        Paragraph blankParagraph = new Paragraph("", textFont);
        blankParagraph.setSpacingAfter(10);
        
        SortedSet sortedkeySet = new TreeSet(posterOutputSummaryEntryHolder.keySet());
        for(Iterator keyIterator=sortedkeySet.iterator(); keyIterator.hasNext();){
            String key = (String)keyIterator.next();
            Collection collection = (Collection)posterOutputSummaryEntryHolder.get(key); 
            
            try{
                Paragraph paragraph = new Paragraph("Balance Type Code: " + key, textFont);
                paragraph.setSpacingAfter(10);
                
                Section section = chapter.addSection(paragraph);
                section.add(blankParagraph);
                
                PdfPTable pdfTable = this.buildPdfTable(collection); 
                section.add(pdfTable);
                section.add(Chunk.NEXTPAGE);
            }
            catch(Exception e){
            }
        }
        return chapter;
    }

    // draw a PDF table populated with the data held by entry holder
    private PdfPTable drawPdfTable(Collection posterOutputSummaryEntryHolder) {
        PdfPTable entryTable = null;
        if (posterOutputSummaryEntryHolder != null) {
            entryTable = this.buildPdfTable(posterOutputSummaryEntryHolder);
        }
        return entryTable;
    }

    // draw a PDF table from a collection
    private PdfPTable buildPdfTable(Collection entryCollection) {
        if (entryCollection == null || entryCollection.size() <= 0) {
            return this.buildEmptyTable();
        }

        PdfPTable entryTable = new PdfPTable(columnWidths);
        entryTable.setHeaderRows(1);
        entryTable.setWidthPercentage(100);

        this.addHeader(entryTable, headerFont);

        for (Iterator reportIter = entryCollection.iterator(); reportIter.hasNext();) {
            PosterOutputSummaryEntry posterOutputSummaryEntry = (PosterOutputSummaryEntry) reportIter.next();
            this.addRow(entryTable, posterOutputSummaryEntry, textFont);
        }

        return entryTable;
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
    private void addRow(PdfPTable entryTable, PosterOutputSummaryEntry posterOutputSummaryEntry, Font textFont) {
        PdfPCell cell = null;
        
        Integer fiscalYear = posterOutputSummaryEntry.getUniversityFiscalYear();
        String stringFiscalYear = (fiscalYear != null) ? fiscalYear.toString() : "";
        cell = new PdfPCell(new Phrase(stringFiscalYear, textFont));
        entryTable.addCell(cell);

        cell = new PdfPCell(new Phrase(posterOutputSummaryEntry.getFiscalPeriodCode(), textFont));
        entryTable.addCell(cell);
        
        cell = new PdfPCell(new Phrase(posterOutputSummaryEntry.getFundGroup(), textFont));
        entryTable.addCell(cell);
        
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
