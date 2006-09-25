/*
 * Copyright (c) 2004, 2005 The National Association of College and University 
 * Business Officers, Cornell University, Trustees of Indiana University, 
 * Michigan State University Board of Trustees, Trustees of San Joaquin Delta 
 * College, University of Hawai'i, The Arizona Board of Regents on behalf of the 
 * University of Arizona, and the r*smart group.
 * 
 * Licensed under the Educational Community License Version 1.0 (the "License"); 
 * By obtaining, using and/or copying this Original Work, you agree that you 
 * have read, understand, and will comply with the terms and conditions of the 
 * Educational Community License.
 * 
 * You may obtain a copy of the License at:
 * 
 * http://kualiproject.org/license.html
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,  DAMAGES OR OTHER 
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN 
 * THE SOFTWARE.
 */

package org.kuali.module.gl.document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.core.document.DocumentBase;
import org.kuali.core.exceptions.ValidationException;
import org.kuali.core.service.DictionaryValidationService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.module.gl.bo.CorrectionChange;
import org.kuali.module.gl.bo.CorrectionChangeGroup;
import org.kuali.module.gl.bo.CorrectionCriteria;
import org.kuali.module.gl.bo.OriginEntryGroup;
import org.kuali.module.gl.service.CorrectionDocumentService;
import org.kuali.module.gl.service.OriginEntryGroupService;
import org.kuali.module.gl.service.ScrubberService;

/**
 * @author Kuali Nervous System Team ()
 */
public class CorrectionDocument extends DocumentBase {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CorrectionDocument.class);

    private String correctionTypeCode;                      // CorrectionDocumentService.CORRECTION_TYPE_MANUAL or CorrectionDocumentService.CORRECTION_TYPE_CRITERIA
    private boolean correctionSelection;                    // false if all input rows should be in the output, true if only selected rows should be in the output
    private boolean correctionFileDelete;                   // false if the file should be processed by scrubber, true if the file should not be processed by scrubber
    private Integer correctionRowCount;                     // Row count in output group
    private KualiDecimal correctionDebitTotalAmount;        // Debit/Budget amount total in output group
    private KualiDecimal correctionCreditTotalAmount;       // Credit amount total in output group
    private String correctionInputFileName;                 // File name if uploaded
    private String correctionOutputFileName;                // Not used
    private String correctionScriptText;                    // Not used
    private Integer correctionInputGroupId;                 // Group ID that has input data
    private Integer correctionOutputGroupId;                // Group ID that has output data
    private Integer correctionChangeGroupNextLineNumber;

    private List correctionChangeGroup;

    public CorrectionDocument() {
        super();
        correctionChangeGroupNextLineNumber = new Integer(0);

        correctionChangeGroup = new ArrayList();
        addCorrectionChangeGroup(new CorrectionChangeGroup());
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("financialDocumentNumber", this.financialDocumentNumber);
        return m;
    }

    public String getMethod() {
        if ( CorrectionDocumentService.CORRECTION_TYPE_MANUAL.equals(correctionTypeCode) ) {
            return "Manual Edit";
        } else {
            return "Using Criteria";
        }
    }

    public String getSystem() {
        if (correctionInputFileName != null) {
            return "File Upload";
        } else {
            return "Database";
        }
    }

    public void addCorrectionChangeGroup(CorrectionChangeGroup ccg) {
        ccg.setFinancialDocumentNumber(financialDocumentNumber);
        ccg.setCorrectionChangeGroupLineNumber(correctionChangeGroupNextLineNumber++);
        correctionChangeGroup.add(ccg);
    }

    public void removeCorrectionChangeGroup(int changeNumber) {
        for (Iterator iter = correctionChangeGroup.iterator(); iter.hasNext();) {
            CorrectionChangeGroup element = (CorrectionChangeGroup)iter.next();
            if ( changeNumber == element.getCorrectionChangeGroupLineNumber().intValue() ) {
                iter.remove();
            }
        }
    }

    public CorrectionChangeGroup getCorrectionChangeGroupItem(int groupNumber) {
        for (Iterator iter = correctionChangeGroup.iterator(); iter.hasNext();) {
            CorrectionChangeGroup element = (CorrectionChangeGroup) iter.next();
            if ( groupNumber == element.getCorrectionChangeGroupLineNumber().intValue() ) {
                return element;
            }
        }

        CorrectionChangeGroup ccg = new CorrectionChangeGroup(financialDocumentNumber,groupNumber);
        correctionChangeGroup.add(ccg);

        return ccg;
    }

    public void handleRouteStatusChange() {
        super.handleRouteStatusChange();
        if (getDocumentHeader().getWorkflowDocument().stateIsApproved()) {
            String docId = getDocumentHeader().getFinancialDocumentNumber();
            CorrectionDocumentService correctionDocumentService = (CorrectionDocumentService) SpringServiceLocator.getBeanFactory().getBean("glCorrectionDocumentService");
            CorrectionDocument oldDoc = correctionDocumentService.findByCorrectionDocumentHeaderId(docId);

            OriginEntryGroupService originEntryGroupService = (OriginEntryGroupService) SpringServiceLocator.getBeanFactory().getBean("glOriginEntryGroupService");
            OriginEntryGroup approvedGLCP = originEntryGroupService.getExactMatchingEntryGroup(oldDoc.getCorrectionOutputGroupId().intValue());

            // Set this group to be processed by the nightly batch if the user wants it to be processed
            if ( ! oldDoc.getCorrectionFileDelete() ) {
                approvedGLCP.setScrub(true);
                originEntryGroupService.save(approvedGLCP);
            }

            ScrubberService scrubberService = (ScrubberService) SpringServiceLocator.getBeanFactory().getBean("glScrubberService");

            // Run the scrubber on this group to generate a bunch of reports.  The scrubber won't save anything when running it this way.
            scrubberService.scrubGroupReportOnly(approvedGLCP);
        }
    }

    /**
     * We need to make sure this is set on all the child objects also
     * 
     * @see org.kuali.core.document.DocumentBase#setFinancialDocumentNumber(java.lang.String)
     */
    @Override
    public void setFinancialDocumentNumber(String financialDocumentNumber) {
        super.setFinancialDocumentNumber(financialDocumentNumber);

        for (Iterator iter = correctionChangeGroup.iterator(); iter.hasNext();) {
            CorrectionChangeGroup element = (CorrectionChangeGroup)iter.next();
            element.setFinancialDocumentNumber(financialDocumentNumber);
        }
    }

    public String getCorrectionTypeCode() {
        return correctionTypeCode;
    }

    public void setCorrectionTypeCode(String correctionTypeCode) {
        this.correctionTypeCode = correctionTypeCode;
    }

    public boolean getCorrectionSelection() {
        return correctionSelection;
    }

    public void setCorrectionSelection(boolean correctionSelection) {
        this.correctionSelection = correctionSelection;
    }

    public boolean getCorrectionFileDelete() {
        return correctionFileDelete;
    }

    public void setCorrectionFileDelete(boolean correctionFileDelete) {
        this.correctionFileDelete = correctionFileDelete;
    }

    public Integer getCorrectionRowCount() {
        return correctionRowCount;
    }

    public void setCorrectionRowCount(Integer correctionRowCount) {
        this.correctionRowCount = correctionRowCount;
    }

    public Integer getCorrectionChangeGroupNextLineNumber() {
        return correctionChangeGroupNextLineNumber;
    }

    public void setCorrectionChangeGroupNextLineNumber(Integer correctionChangeGroupNextLineNumber) {
        this.correctionChangeGroupNextLineNumber = correctionChangeGroupNextLineNumber;
    }

    public KualiDecimal getCorrectionDebitTotalAmount() {
        return correctionDebitTotalAmount;
    }

    public void setCorrectionDebitTotalAmount(KualiDecimal correctionDebitTotalAmount) {
        this.correctionDebitTotalAmount = correctionDebitTotalAmount;
    }

    public KualiDecimal getCorrectionCreditTotalAmount() {
        return correctionCreditTotalAmount;
    }

    public void setCorrectionCreditTotalAmount(KualiDecimal correctionCreditTotalAmount) {
        this.correctionCreditTotalAmount = correctionCreditTotalAmount;
    }

    public String getCorrectionInputFileName() {
        return correctionInputFileName;
    }

    public void setCorrectionInputFileName(String correctionInputFileName) {
        this.correctionInputFileName = correctionInputFileName;
    }

    public String getCorrectionOutputFileName() {
        return correctionOutputFileName;
    }

    public void setCorrectionOutputFileName(String correctionOutputFileName) {
        this.correctionOutputFileName = correctionOutputFileName;
    }

    public List getCorrectionChangeGroup() {
        Collections.sort(correctionChangeGroup);
        return correctionChangeGroup;
    }

    public void setCorrectionChangeGroup(List correctionChangeGroup) {
        this.correctionChangeGroup = correctionChangeGroup;
    }

    public Integer getCorrectionInputGroupId() {
        return correctionInputGroupId;
    }

    public void setCorrectionInputGroupId(Integer correctionInputGroupId) {
        this.correctionInputGroupId = correctionInputGroupId;
    }

    public Integer getCorrectionOutputGroupId() {
        return correctionOutputGroupId;
    }

    public void setCorrectionOutputGroupId(Integer correctionOutputGroupId) {
        this.correctionOutputGroupId = correctionOutputGroupId;
    }
}
