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
package org.kuali.module.labor.document;

import java.util.Iterator;

import org.kuali.core.document.AmountTotaling;
import org.kuali.core.service.DateTimeService;
import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.util.SpringServiceLocator;
import org.kuali.module.gl.bo.OriginEntryGroup;
import org.kuali.module.gl.bo.OriginEntrySource;
import org.kuali.module.gl.document.CorrectionDocument;
import org.kuali.module.labor.service.LaborCorrectionDocumentService;
import org.kuali.module.gl.service.OriginEntryGroupService;
import org.kuali.module.labor.bo.LaborOriginEntry;
import org.kuali.module.labor.service.LaborCorrectionDocumentService;
import org.kuali.module.labor.service.LaborOriginEntryService;
import org.kuali.module.labor.service.LaborReportService;
import org.kuali.module.labor.service.LaborScrubberService;
import org.kuali.module.labor.util.ReportRegistry;

import edu.iu.uis.eden.clientapp.vo.DocumentRouteLevelChangeVO;

public class LaborCorrectionDocument extends CorrectionDocument implements AmountTotaling {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(LaborCorrectionDocument.class);
    public LaborCorrectionDocument() {
        super();
    }
    
    /**
     * Constant for the workgroup approval routing level
     */
    private static final Integer WORKGROUP_APPROVAL_ROUTE_LEVEL = new Integer(1);
    
    /**
     * @see org.kuali.core.document.DocumentBase#handleRouteLevelChange(edu.iu.uis.eden.clientapp.vo.DocumentRouteLevelChangeVO)
     */
    @Override
    public void handleRouteLevelChange(DocumentRouteLevelChangeVO change) {
        
        if(WORKGROUP_APPROVAL_ROUTE_LEVEL.equals(change.getNewRouteLevel())) {
            String correctionType = getCorrectionTypeCode();
            if (LaborCorrectionDocumentService.CORRECTION_TYPE_MANUAL.equals(correctionType) || LaborCorrectionDocumentService.CORRECTION_TYPE_CRITERIA.equals(correctionType)){
                String docId = getDocumentHeader().getDocumentNumber();
                // this code is performed asynchronously
                
                // First, save the origin entries to the origin entry table
                DateTimeService dateTimeService = SpringServiceLocator.getDateTimeService();
                LaborOriginEntryService laborOriginEntryService = SpringServiceLocator.getLaborOriginEntryService();
                LaborCorrectionDocumentService laborCorrectionDocumentService = SpringServiceLocator.getLaborCorrectionDocumentService();
                
                Iterator<LaborOriginEntry> outputEntries = laborCorrectionDocumentService.retrievePersistedOutputOriginEntriesAsIterator(this);
                
                // Create output group
                java.sql.Date today = dateTimeService.getCurrentSqlDate();
                // Scrub is set to false when the document is initiated. When the document is final, it will be changed to true
                OriginEntryGroup oeg = laborOriginEntryService.copyEntries(today, OriginEntrySource.LABOR_CORRECTION_PROCESS_EDOC, true, false, true, outputEntries);
                
                // Now, run the reports
                LaborReportService reportService = SpringServiceLocator.getLaborReportService();
                LaborScrubberService laborScrubberService = SpringServiceLocator.getLaborScrubberService();
                
                setCorrectionOutputGroupId(oeg.getId());
                // not using the document service to save because it touches workflow, just save the doc BO as a regular BO
                SpringServiceLocator.getBusinessObjectService().save(this);
                
                LOG.debug("handleRouteStatusChange() Run reports");
    
                String reportsDirectory = ReportRegistry.getReportsDirectory();
                
                reportService.correctionOnlineReport(this, reportsDirectory, today);
    
                // Run the scrubber on this group to generate a bunch of reports. The scrubber won't save anything when running it
                // this way.
                laborScrubberService.scrubGroupReportOnly(oeg, docId);
            }
        }
    }
    
    /**
     * If the document final, change the process flag on the output origin entry group (if necessary)
     * 
     * @see org.kuali.core.document.DocumentBase#handleRouteStatusChange()
     */
    @Override
    public void handleRouteStatusChange() {
        LOG.debug("handleRouteStatusChange() started");
        
        if (getDocumentHeader().getWorkflowDocument().stateIsCanceled()) {
            getDocumentHeader().setFinancialDocumentStatusCode(KFSConstants.DocumentStatusCodes.CANCELLED);
        }
        else if (getDocumentHeader().getWorkflowDocument().stateIsEnroute()) {
            getDocumentHeader().setFinancialDocumentStatusCode(KFSConstants.DocumentStatusCodes.ENROUTE);
        }
        if (getDocumentHeader().getWorkflowDocument().stateIsDisapproved()) {
            getDocumentHeader().setFinancialDocumentStatusCode(KFSConstants.DocumentStatusCodes.DISAPPROVED);
        }
        if (getDocumentHeader().getWorkflowDocument().stateIsProcessed()) {
            getDocumentHeader().setFinancialDocumentStatusCode(KFSConstants.DocumentStatusCodes.APPROVED);
        }
        
        LaborCorrectionDocumentService laborCorrectionDocumentService = SpringServiceLocator.getLaborCorrectionDocumentService();
        OriginEntryGroupService originEntryGroupService = SpringServiceLocator.getOriginEntryGroupService();

        String docId = getDocumentHeader().getDocumentNumber();
        LaborCorrectionDocument doc = laborCorrectionDocumentService.findByCorrectionDocumentHeaderId(docId);

        if (getDocumentHeader().getWorkflowDocument().stateIsFinal()) {
            String correctionType = doc.getCorrectionTypeCode();
            if (LaborCorrectionDocumentService.CORRECTION_TYPE_REMOVE_GROUP_FROM_PROCESSING.equals(correctionType)) {
                SpringServiceLocator.getOriginEntryGroupService().dontProcessGroup(doc.getCorrectionInputGroupId());
            }
            else if (LaborCorrectionDocumentService.CORRECTION_TYPE_MANUAL.equals(correctionType) || LaborCorrectionDocumentService.CORRECTION_TYPE_CRITERIA.equals(correctionType)){
                OriginEntryGroup outputGroup = originEntryGroupService.getExactMatchingEntryGroup(doc.getCorrectionOutputGroupId().intValue());
                if (!doc.getCorrectionFileDelete()) {
                    LOG.debug("handleRouteStatusChange() Mark group as to be processed");
                    outputGroup.setProcess(true);
                    originEntryGroupService.save(outputGroup);
                }
            }
            else {
                LOG.error("GLCP doc " + doc.getDocumentNumber() + " has an unknown correction type code: " + correctionType);
            }
        }
    }
    
}
