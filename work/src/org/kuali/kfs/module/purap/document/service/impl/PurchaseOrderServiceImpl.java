/*
 * Copyright 2006-2007 The Kuali Foundation.
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
package org.kuali.module.purap.service.impl;

import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.RicePropertyConstants;
import org.kuali.core.bo.AdHocRouteRecipient;
import org.kuali.core.bo.Note;
import org.kuali.core.document.DocumentBase;
import org.kuali.core.exceptions.UserNotFoundException;
import org.kuali.core.exceptions.ValidationException;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DateTimeService;
import org.kuali.core.service.DocumentService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.service.KualiRuleService;
import org.kuali.core.service.NoteService;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.util.ObjectUtils;
import org.kuali.core.util.TypedArrayList;
import org.kuali.core.workflow.service.KualiWorkflowDocument;
import org.kuali.core.workflow.service.KualiWorkflowInfo;
import org.kuali.core.workflow.service.WorkflowDocumentService;
import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.context.SpringContext;
import org.kuali.kfs.rule.event.DocumentSystemSaveEvent;
import org.kuali.module.purap.PurapConstants;
import org.kuali.module.purap.PurapKeyConstants;
import org.kuali.module.purap.PurapConstants.POTransmissionMethods;
import org.kuali.module.purap.PurapConstants.PurchaseOrderDocTypes;
import org.kuali.module.purap.PurapConstants.PurchaseOrderStatuses;
import org.kuali.module.purap.PurapConstants.RequisitionSources;
import org.kuali.module.purap.PurapConstants.VendorChoice;
import org.kuali.module.purap.PurapWorkflowConstants.PurchaseOrderDocument.NodeDetailEnum;
import org.kuali.module.purap.bo.PurApItem;
import org.kuali.module.purap.bo.PurchaseOrderItem;
import org.kuali.module.purap.bo.PurchaseOrderQuoteStatus;
import org.kuali.module.purap.bo.PurchaseOrderVendorQuote;
import org.kuali.module.purap.dao.PurchaseOrderDao;
import org.kuali.module.purap.document.PurchaseOrderDocument;
import org.kuali.module.purap.document.RequisitionDocument;
import org.kuali.module.purap.service.LogicContainer;
import org.kuali.module.purap.service.PrintService;
import org.kuali.module.purap.service.PurApWorkflowIntegrationService;
import org.kuali.module.purap.service.PurapService;
import org.kuali.module.purap.service.PurchaseOrderService;
import org.kuali.module.purap.service.RequisitionService;
import org.kuali.module.purap.util.PurApObjectUtils;
import org.kuali.module.vendor.bo.VendorDetail;
import org.kuali.module.vendor.service.VendorService;
import org.springframework.transaction.annotation.Transactional;

import edu.iu.uis.eden.clientapp.vo.ActionRequestVO;
import edu.iu.uis.eden.exception.WorkflowException;

@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PurchaseOrderServiceImpl.class);

    private BusinessObjectService businessObjectService;
    private DateTimeService dateTimeService;
    private DocumentService documentService;
    private NoteService noteService;
    private PurapService purapService;
    private PrintService printService;
    private PurchaseOrderDao purchaseOrderDao;
    private WorkflowDocumentService workflowDocumentService;
    private KualiConfigurationService kualiConfigurationService;
    private KualiRuleService kualiRuleService;
    private VendorService vendorService;
    private RequisitionService requisitionService;

    public void setBusinessObjectService(BusinessObjectService boService) {
        this.businessObjectService = boService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }

    public void setPurapService(PurapService purapService) {
        this.purapService = purapService;
    }

    public void setPrintService(PrintService printService) {
        this.printService = printService;
    }

    public void setPurchaseOrderDao(PurchaseOrderDao purchaseOrderDao) {
        this.purchaseOrderDao = purchaseOrderDao;
    }

    public void setWorkflowDocumentService(WorkflowDocumentService workflowDocumentService) {
        this.workflowDocumentService = workflowDocumentService;
    }

    public void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }

    public void setKualiRuleService(KualiRuleService kualiRuleService) {
        this.kualiRuleService = kualiRuleService;
    }

    public void setVendorService(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    public void setRequisitionService(RequisitionService requisitionService) {
        this.requisitionService = requisitionService;
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#saveDocumentNoValidation(org.kuali.module.purap.document.PurchaseOrderDocument)
     */
    public void saveDocumentNoValidation(PurchaseOrderDocument document) {
        try {
            documentService.saveDocument(document, DocumentSystemSaveEvent.class);
        }
        catch (WorkflowException we) {
            String errorMsg = "Workflow Error saving document # " + document.getDocumentHeader().getDocumentNumber() + " " + we.getMessage();
            LOG.error(errorMsg, we);
            throw new RuntimeException(errorMsg, we);
        }
    }

    /**
     * Sets the error map to a new, empty error map before calling saveDocumentNoValidation to save the document.
     * 
     * @param document The purchase order document to be saved.
     */
    private void saveDocumentNoValidationUsingClearErrorMap(PurchaseOrderDocument document) {
        ErrorMap errorHolder = GlobalVariables.getErrorMap();
        GlobalVariables.setErrorMap(new ErrorMap());
        try {
            saveDocumentNoValidation(document);
        }
        finally {
            GlobalVariables.setErrorMap(errorHolder);
        }
    }

    /**
     * Calls the saveDocument method of documentService to save the document.
     * 
     * @param document The document to be saved.
     */
    private void saveDocumentStandardSave(PurchaseOrderDocument document) {
        try {
            documentService.saveDocument(document);
        }
        catch (WorkflowException we) {
            String errorMsg = "Workflow Error saving document # " + document.getDocumentHeader().getDocumentNumber() + " " + we.getMessage();
            LOG.error(errorMsg, we);
            throw new RuntimeException(errorMsg, we);
        }
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#createAutomaticPurchaseOrderDocument(org.kuali.module.purap.document.RequisitionDocument)
     */
    public void createAutomaticPurchaseOrderDocument(RequisitionDocument reqDocument) {
        String newSessionUserId = KFSConstants.SYSTEM_USER;
        try {
            LogicContainer logicToRun = new LogicContainer() {
                public Object runLogic(Object[] objects) throws Exception {
                    RequisitionDocument doc = (RequisitionDocument) objects[0];
                    // update REQ data
                    doc.setPurchaseOrderAutomaticIndicator(Boolean.TRUE);
                    // create PO and populate with default data
                    PurchaseOrderDocument po = generatePurchaseOrderFromRequisition(doc);
                    po.setDefaultValuesForAPO();
                    po.setContractManagerCode(PurapConstants.APO_CONTRACT_MANAGER);
                    documentService.routeDocument(po, null, null);
                    return null;
                }
            };
            purapService.performLogicWithFakedUserSession(newSessionUserId, logicToRun, new Object[] { reqDocument });
        }
        catch (WorkflowException e) {
            String errorMsg = "Workflow Exception caught: " + e.getLocalizedMessage();
            LOG.error(errorMsg, e);
            throw new RuntimeException(errorMsg, e);
        }
        catch (UserNotFoundException e) {
            String errorMsg = "User not found for PersonUserIdentifier '" + newSessionUserId + "'";
            LOG.error(errorMsg, e);
            throw new RuntimeException(errorMsg, e);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#createPurchaseOrderDocument(org.kuali.module.purap.document.RequisitionDocument,
     *      java.lang.String, java.lang.Integer)
     */
    public PurchaseOrderDocument createPurchaseOrderDocument(RequisitionDocument reqDocument, String newSessionUserId, Integer contractManagerCode) {
        try {
            LogicContainer logicToRun = new LogicContainer() {
                public Object runLogic(Object[] objects) throws Exception {
                    RequisitionDocument doc = (RequisitionDocument) objects[0];
                    PurchaseOrderDocument po = generatePurchaseOrderFromRequisition(doc);
                    Integer cmCode = (Integer) objects[1];
                    po.setContractManagerCode(cmCode);
                    saveDocumentNoValidation(po);
                    return po;
                }
            };
            return (PurchaseOrderDocument) purapService.performLogicWithFakedUserSession(newSessionUserId, logicToRun, new Object[] { reqDocument, contractManagerCode });
        }
        catch (WorkflowException e) {
            String errorMsg = "Workflow Exception caught: " + e.getLocalizedMessage();
            LOG.error(errorMsg, e);
            throw new RuntimeException(errorMsg, e);
        }
        catch (UserNotFoundException e) {
            String errorMsg = "User not found for PersonUserIdentifier '" + newSessionUserId + "'";
            LOG.error(errorMsg, e);
            throw new RuntimeException(errorMsg, e);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Create Purchase Order and populate with data from Requisition and other default data
     * 
     * @param reqDocument The requisition document from which we create the purchase order document.
     * @return The purchase order document created by this method.
     * @throws WorkflowException
     */
    private PurchaseOrderDocument generatePurchaseOrderFromRequisition(RequisitionDocument reqDocument) throws WorkflowException {
        PurchaseOrderDocument poDocument = null;
        poDocument = (PurchaseOrderDocument) documentService.getNewDocument(PurchaseOrderDocTypes.PURCHASE_ORDER_DOCUMENT);
        poDocument.populatePurchaseOrderFromRequisition(reqDocument);
        poDocument.setStatusCode(PurchaseOrderStatuses.IN_PROCESS);
        poDocument.setPurchaseOrderCurrentIndicator(true);
        poDocument.setPendingActionIndicator(false);

        if (RequisitionSources.B2B.equals(poDocument.getRequisitionSourceCode())) {
            poDocument.setPurchaseOrderVendorChoiceCode(VendorChoice.CONTRACTED_PRICE);
        }

        if (ObjectUtils.isNotNull(poDocument.getVendorContract())) {
            poDocument.setVendorPaymentTermsCode(poDocument.getVendorContract().getVendorPaymentTermsCode());
            poDocument.setVendorShippingPaymentTermsCode(poDocument.getVendorContract().getVendorShippingPaymentTermsCode());
            poDocument.setVendorShippingTitleCode(poDocument.getVendorContract().getVendorShippingTitleCode());
        }
        else {
            VendorDetail vendor = vendorService.getVendorDetail(poDocument.getVendorHeaderGeneratedIdentifier(), poDocument.getVendorDetailAssignedIdentifier());
            if (ObjectUtils.isNotNull(vendor)) {
                poDocument.setVendorPaymentTermsCode(vendor.getVendorPaymentTermsCode());
                poDocument.setVendorShippingPaymentTermsCode(vendor.getVendorShippingPaymentTermsCode());
                poDocument.setVendorShippingTitleCode(vendor.getVendorShippingTitleCode());
            }
        }

        purapService.addBelowLineItems(poDocument);

        return poDocument;
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#getInternalPurchasingDollarLimit(org.kuali.module.purap.document.PurchaseOrderDocument)
     */
    public KualiDecimal getInternalPurchasingDollarLimit(PurchaseOrderDocument document) {
        if ((document.getVendorContract() != null) && (document.getContractManager() != null)) {
            KualiDecimal contractDollarLimit = vendorService.getApoLimitFromContract(document.getVendorContract().getVendorContractGeneratedIdentifier(), document.getChartOfAccountsCode(), document.getOrganizationCode());
            KualiDecimal contractManagerLimit = document.getContractManager().getContractManagerDelegationDollarLimit();
            if ((contractDollarLimit != null) && (contractManagerLimit != null)) {
                if (contractDollarLimit.compareTo(contractManagerLimit) > 0) {
                    return contractDollarLimit;
                }
                else {
                    return contractManagerLimit;
                }
            }
            else if (contractDollarLimit != null) {
                return contractDollarLimit;
            }
            else {
                return contractManagerLimit;
            }
        }
        else if ((document.getVendorContract() == null) && (document.getContractManager() != null)) {
            return document.getContractManager().getContractManagerDelegationDollarLimit();
        }
        else if ((document.getVendorContract() != null) && (document.getContractManager() == null)) {
            return purapService.getApoLimit(document.getVendorContract().getVendorContractGeneratedIdentifier(), document.getChartOfAccountsCode(), document.getOrganizationCode());
        }
        else {
            String errorMsg = "No internal purchase order dollar limit found for purchase order '" + document.getPurapDocumentIdentifier() + "'.";
            LOG.warn(errorMsg);
            return null;
        }
    }

    /**
     * Loops through the collection of error messages and adding each of them to the error map.
     * 
     * @param errorKey The resource key used to retrieve the error text from the error message resource bundle.
     * @param errors The collection of error messages.
     */
    private void addStringErrorMessagesToErrorMap(String errorKey, Collection<String> errors) {
        if (ObjectUtils.isNotNull(errors)) {
            for (String error : errors) {
                LOG.error("Adding error message using error key '" + errorKey + "' with text '" + error + "'");
                GlobalVariables.getErrorMap().putError(KFSConstants.GLOBAL_ERRORS, errorKey, error);
            }
        }
    }


    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#printPurchaseOrderQuoteRequestsListPDF(org.kuali.module.purap.document.PurchaseOrderDocument,
     *      java.io.ByteArrayOutputStream)
     */
    public boolean printPurchaseOrderQuoteRequestsListPDF(PurchaseOrderDocument po, ByteArrayOutputStream baosPDF) {
        String environment = kualiConfigurationService.getPropertyString(KFSConstants.ENVIRONMENT_KEY);
        Collection<String> generatePDFErrors = printService.generatePurchaseOrderQuoteRequestsListPdf(po, baosPDF);

        if (generatePDFErrors.size() > 0) {
            addStringErrorMessagesToErrorMap(PurapKeyConstants.ERROR_PURCHASE_ORDER_PDF, generatePDFErrors);
            return false;
        }
        else {
            saveDocumentStandardSave(po);
            return true;
        }
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#printPurchaseOrderQuotePDF(org.kuali.module.purap.document.PurchaseOrderDocument,
     *      org.kuali.module.purap.bo.PurchaseOrderVendorQuote, java.io.ByteArrayOutputStream)
     */
    public boolean printPurchaseOrderQuotePDF(PurchaseOrderDocument po, PurchaseOrderVendorQuote povq, ByteArrayOutputStream baosPDF) {

        String environment = kualiConfigurationService.getPropertyString(KFSConstants.ENVIRONMENT_KEY);
        Collection<String> generatePDFErrors = printService.generatePurchaseOrderQuotePdf(po, povq, baosPDF, environment);

        if (generatePDFErrors.size() > 0) {
            addStringErrorMessagesToErrorMap(PurapKeyConstants.ERROR_PURCHASE_ORDER_PDF, generatePDFErrors);
            return false;
        }
        else {
            saveDocumentStandardSave(po);
            return true;
        }
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#performPurchaseOrderFirstTransmitViaPrinting(java.lang.String,
     *      java.io.ByteArrayOutputStream)
     */
    public void performPurchaseOrderFirstTransmitViaPrinting(String documentNumber, ByteArrayOutputStream baosPDF) {
        PurchaseOrderDocument po = getPurchaseOrderByDocumentNumber(documentNumber);
        String environment = kualiConfigurationService.getPropertyString(KFSConstants.ENVIRONMENT_KEY);
        Collection<String> generatePDFErrors = printService.generatePurchaseOrderPdf(po, baosPDF, environment, null);
        if (!generatePDFErrors.isEmpty()) {
            addStringErrorMessagesToErrorMap(PurapKeyConstants.ERROR_PURCHASE_ORDER_PDF, generatePDFErrors);
            throw new ValidationException("printing purchase order for first transmission failed");
        }
        if (ObjectUtils.isNotNull(po.getPurchaseOrderFirstTransmissionDate())) {
            // should not call this method for first transmission if document has already been transmitted
            String errorMsg = "Method to perform first transmit was called on document (doc id " + documentNumber + ") with already filled in 'first transmit date'";
            LOG.error(errorMsg);
            throw new RuntimeException(errorMsg);
        }
        Date currentDate = dateTimeService.getCurrentSqlDate();
        po.setPurchaseOrderFirstTransmissionDate(currentDate);
        po.setPurchaseOrderLastTransmitDate(currentDate);
        po.setOverrideWorkflowButtons(Boolean.FALSE);
        boolean performedAction = SpringContext.getBean(PurApWorkflowIntegrationService.class).takeAllActionsForGivenCriteria(po, "Action taken automatically as part of document initial print transmission", NodeDetailEnum.DOCUMENT_TRANSMISSION.getName(), GlobalVariables.getUserSession().getUniversalUser(), null);
        if (!performedAction) {
            SpringContext.getBean(PurApWorkflowIntegrationService.class).takeAllActionsForGivenCriteria(po, "Action taken automatically as part of document initial print transmission by user " + GlobalVariables.getUserSession().getUniversalUser().getPersonName(), NodeDetailEnum.DOCUMENT_TRANSMISSION.getName(), null, KFSConstants.SYSTEM_USER);
        }
        po.setOverrideWorkflowButtons(Boolean.TRUE);
        if (po.getStatusCode().equals(PurapConstants.PurchaseOrderStatuses.OPEN)) {
            saveDocumentNoValidation(po);    
        }
        else {
            attemptSetupOfInitialOpenOfDocument(po);
        }
    }
    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#performPrintPurchaseOrderPDFOnly(java.lang.String,
     *      java.io.ByteArrayOutputStream)
     */
    public void performPrintPurchaseOrderPDFOnly(String documentNumber, ByteArrayOutputStream baosPDF) {
        PurchaseOrderDocument po = getPurchaseOrderByDocumentNumber(documentNumber);
        String environment = kualiConfigurationService.getPropertyString(KFSConstants.ENVIRONMENT_KEY);
        Collection<String> generatePDFErrors = printService.generatePurchaseOrderPdf(po, baosPDF, environment, null);
        if (!generatePDFErrors.isEmpty()) {
            addStringErrorMessagesToErrorMap(PurapKeyConstants.ERROR_PURCHASE_ORDER_PDF, generatePDFErrors);
            throw new ValidationException("printing purchase order for first transmission failed");
        }
    }

    /**
     * Invokes the documentService to perform the appropriate workflow actions depending on the boolean flags on the
     * workflowDocument of the documentHeader of the purchase order.
     * 
     * @param po The purchase order document upon which the workflow actions would be taken
     * @param annotation The annotation String that we'll pass to workflow when we invoke superUserActionRequestApprove.
     */
    private void takeWorkflowActionsForDocumentTransmission(PurchaseOrderDocument po, String annotation) {
        try {
            List<ActionRequestVO> docTransRequests = new ArrayList<ActionRequestVO>();
            ActionRequestVO[] actionRequests = SpringContext.getBean(KualiWorkflowInfo.class).getActionRequests(Long.valueOf(po.getDocumentNumber()));
            for (ActionRequestVO actionRequestVO : actionRequests) {
                if (actionRequestVO.isActivated()) {
                    if (StringUtils.equals(actionRequestVO.getNodeName(), NodeDetailEnum.DOCUMENT_TRANSMISSION.getName())) {
                        docTransRequests.add(actionRequestVO);
                    }
                }
            }
            if (!docTransRequests.isEmpty()) {
                for (ActionRequestVO actionRequest : docTransRequests) {
                    po.getDocumentHeader().getWorkflowDocument().superUserActionRequestApprove(actionRequest.getActionRequestId(), annotation);
                }
            }
            if (po.getDocumentHeader().getWorkflowDocument().isApprovalRequested()) {
                SpringContext.getBean(DocumentService.class).approveDocument(po, null, new ArrayList());
            }
            else if (po.getDocumentHeader().getWorkflowDocument().isAcknowledgeRequested()) {
                SpringContext.getBean(DocumentService.class).acknowledgeDocument(po, null, new ArrayList());
            }
            else if (po.getDocumentHeader().getWorkflowDocument().isFYIRequested()) {
                SpringContext.getBean(DocumentService.class).clearDocumentFyi(po, new ArrayList());
            }
        }
        catch (NumberFormatException nfe) {
            String errorMsg = "Exception trying to convert '" + po.getDocumentNumber() + "' into a number (Long)";
            LOG.error(errorMsg, nfe);
            throw new RuntimeException(errorMsg, nfe);
        }
        catch (WorkflowException we) {
            String errorMsg = "Workflow Exception caught trying to take actions for document transmission node: " + we.getLocalizedMessage();
            LOG.error(errorMsg, we);
            throw new RuntimeException(errorMsg, we);
        }
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#retransmitPurchaseOrderPDF(org.kuali.module.purap.document.PurchaseOrderDocument,
     *      java.io.ByteArrayOutputStream)
     */
    public void retransmitPurchaseOrderPDF(PurchaseOrderDocument po, ByteArrayOutputStream baosPDF) {

        String environment = kualiConfigurationService.getPropertyString(KFSConstants.ENVIRONMENT_KEY);
        List<PurchaseOrderItem> items = po.getItems();
        List<PurchaseOrderItem> retransmitItems = new ArrayList<PurchaseOrderItem>();
        for (PurchaseOrderItem item : items) {
            if (item.isItemSelectedForRetransmitIndicator()) {
                retransmitItems.add(item);
            }
        }
        Collection<String> generatePDFErrors = printService.generatePurchaseOrderPdfForRetransmission(po, baosPDF, environment, retransmitItems);

        if (generatePDFErrors.size() > 0) {
            addStringErrorMessagesToErrorMap(PurapKeyConstants.ERROR_PURCHASE_ORDER_PDF, generatePDFErrors);
            throw new ValidationException("found errors while trying to print po with doc id " + po.getDocumentNumber());
        }
        po.setPurchaseOrderLastTransmitDate(dateTimeService.getCurrentSqlDate());
        saveDocumentNoValidation(po);
    }

    /**
     * This method creates a new Purchase Order Document using the given document type based off the given source document. This
     * method will return null if the source document given is null.<br>
     * <br> ** THIS METHOD DOES NOT SAVE EITHER THE GIVEN SOURCE DOCUMENT OR THE NEW DOCUMENT CREATED
     * 
     * @param sourceDocument - document the new Purchase Order Document should be based off of in terms of data
     * @param docType - document type of the potential new Purchase Order Document
     * @return the new Purchase Order Document of the given document type or null if the given source document is null
     * @throws WorkflowException if a new document cannot be created using the given type
     */
    private PurchaseOrderDocument createPurchaseOrderDocumentFromSourceDocument(PurchaseOrderDocument sourceDocument, String docType) throws WorkflowException {
        if (ObjectUtils.isNull(sourceDocument)) {
            String errorMsg = "Attempting to create new PO of type '" + docType + "' from source PO doc that is null";
            LOG.error(errorMsg);
            throw new RuntimeException(errorMsg);
        }

        PurchaseOrderDocument newPurchaseOrderChangeDocument = (PurchaseOrderDocument) documentService.getNewDocument(docType);

        Set classesToExclude = new HashSet();
        Class sourceObjectClass = DocumentBase.class;
        classesToExclude.add(sourceObjectClass);
        while (sourceObjectClass.getSuperclass() != null) {
            sourceObjectClass = sourceObjectClass.getSuperclass();
            classesToExclude.add(sourceObjectClass);
        }
        PurApObjectUtils.populateFromBaseWithSuper(sourceDocument, newPurchaseOrderChangeDocument, PurapConstants.UNCOPYABLE_FIELDS_FOR_PO, classesToExclude);
        newPurchaseOrderChangeDocument.getDocumentHeader().setFinancialDocumentDescription(sourceDocument.getDocumentHeader().getFinancialDocumentDescription());
        newPurchaseOrderChangeDocument.getDocumentHeader().setOrganizationDocumentNumber(sourceDocument.getDocumentHeader().getOrganizationDocumentNumber());

        newPurchaseOrderChangeDocument.setPurchaseOrderCurrentIndicator(false);
        newPurchaseOrderChangeDocument.setPendingActionIndicator(false);

        // Need to find a way to make the ManageableArrayList to expand and populating the items and
        // accounts, otherwise it will complain about the account on item 1 is missing.
        for (PurApItem item : (List<PurApItem>) newPurchaseOrderChangeDocument.getItems()) {
            item.getSourceAccountingLines().iterator();
            // we only need to do this once to apply to all items, so we can break out of the loop now
            break;
        }

        newPurchaseOrderChangeDocument.refreshNonUpdateableReferences();
        return newPurchaseOrderChangeDocument;
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#createAndSavePotentialChangeDocument(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public PurchaseOrderDocument createAndSavePotentialChangeDocument(String documentNumber, String docType, String currentDocumentStatusCode) {
        PurchaseOrderDocument currentDocument = SpringContext.getBean(PurchaseOrderService.class).getPurchaseOrderByDocumentNumber(documentNumber);
        try {
            PurchaseOrderDocument newDocument = createPurchaseOrderDocumentFromSourceDocument(currentDocument, docType);
            if (ObjectUtils.isNotNull(newDocument)) {
                newDocument.setStatusCode(PurchaseOrderStatuses.CHANGE_IN_PROCESS);
                // set status if needed
                if (StringUtils.isNotBlank(currentDocumentStatusCode)) {
                    purapService.updateStatus(currentDocument, currentDocumentStatusCode);
                }
                try {
                    documentService.saveDocument(newDocument);
                }
                // if we catch a ValidationException it means the new PO doc found errors
                catch (ValidationException ve) {
                    throw ve;
                }
                // if no validation exception was thrown then rules have passed and we are ok to edit the current PO
                currentDocument.setPendingActionIndicator(true);
                saveDocumentNoValidationUsingClearErrorMap(currentDocument);
                return newDocument;
            }
            else {
                String errorMsg = "Attempting to create new PO of type '" + docType + "' from source PO doc id " + documentNumber + " returned null for new document";
                LOG.error(errorMsg);
                throw new RuntimeException(errorMsg);
            }
        }
        catch (WorkflowException we) {
            String errorMsg = "Workflow Exception caught trying to create and save PO document of type '" + docType + "' using source document with doc id '" + documentNumber + "'";
            LOG.error(errorMsg, we);
            throw new RuntimeException(errorMsg, we);
        }
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#createAndRoutePotentialChangeDocument(java.lang.String,
     *      java.lang.String, java.lang.String, java.util.List, java.lang.String)
     */
    public PurchaseOrderDocument createAndRoutePotentialChangeDocument(String documentNumber, String docType, String annotation, List adhocRoutingRecipients, String currentDocumentStatusCode) {
        PurchaseOrderDocument currentDocument = getPurchaseOrderByDocumentNumber(documentNumber);

        purapService.updateStatus(currentDocument, currentDocumentStatusCode);
        try {
            PurchaseOrderDocument newDocument = createPurchaseOrderDocumentFromSourceDocument(currentDocument, docType);
            newDocument.setStatusCode(PurchaseOrderStatuses.CHANGE_IN_PROCESS);
            if (ObjectUtils.isNotNull(newDocument)) {
                try {
                    documentService.routeDocument(newDocument, annotation, adhocRoutingRecipients);
                }
                // if we catch a ValidationException it means the new PO doc found errors
                catch (ValidationException ve) {
                    throw ve;
                }
                // if no validation exception was thrown then rules have passed and we are ok to edit the current PO
                currentDocument.setPendingActionIndicator(true);
                saveDocumentNoValidationUsingClearErrorMap(currentDocument);
                return newDocument;
            }
            else {
                String errorMsg = "Attempting to create new PO of type '" + docType + "' from source PO doc id " + documentNumber + " returned null for new document";
                LOG.error(errorMsg);
                throw new RuntimeException(errorMsg);
            }
        }
        catch (WorkflowException we) {
            String errorMsg = "Workflow Exception caught trying to create and route PO document of type '" + docType + "' using source document with doc id '" + documentNumber + "'";
            LOG.error(errorMsg, we);
            throw new RuntimeException(errorMsg, we);
        }
    }

    /**
     * Returns the current route node name.
     * 
     * @param wd The KualiWorkflowDocument object whose current route node we're trying to get.
     * @return The current route node name.
     * @throws WorkflowException
     */
    private String getCurrentRouteNodeName(KualiWorkflowDocument wd) throws WorkflowException {
        String[] nodeNames = wd.getNodeNames();
        if ((nodeNames == null) || (nodeNames.length == 0)) {
            return null;
        }
        else {
            return nodeNames[0];
        }
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#completePurchaseOrder(org.kuali.module.purap.document.PurchaseOrderDocument)
     */
    public void completePurchaseOrder(PurchaseOrderDocument po) {
        LOG.debug("completePurchaseOrder() started");
        setCurrentAndPendingIndicatorsForApprovedPODocuments(po);
        // if the document is set in a Pending Transmission status then don't OPEN the PO just leave it as is
        if (!PurchaseOrderStatuses.STATUSES_BY_TRANSMISSION_TYPE.values().contains(po.getStatusCode())) {
            attemptSetupOfInitialOpenOfDocument(po);
        }
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#setupDocumentForPendingFirstTransmission(org.kuali.module.purap.document.PurchaseOrderDocument,
     *      boolean)
     */
    public void setupDocumentForPendingFirstTransmission(PurchaseOrderDocument po, boolean hasActionRequestForDocumentTransmission) {
        if (POTransmissionMethods.PRINT.equals(po.getPurchaseOrderTransmissionMethodCode())) {
            String newStatusCode = PurchaseOrderStatuses.STATUSES_BY_TRANSMISSION_TYPE.get(po.getPurchaseOrderTransmissionMethodCode());
            LOG.debug("setupDocumentForPendingFirstTransmission() Purchase Order Transmission Type is '" + po.getPurchaseOrderTransmissionMethodCode() + "' setting status to '" + newStatusCode + "'");
            purapService.updateStatus(po, newStatusCode);
        }
        else {
            if (hasActionRequestForDocumentTransmission) {
                /*
                 * here we error out because the document generated a request for the doc transmission route level but the default
                 * status to set is open... this prevents Open purchase orders that could be awaiting transmission by a valid method
                 * (via the generated request)
                 */
                String errorMessage = "An action request was generated for document id " + po.getDocumentNumber() + " with an unhandled transmission type '" + po.getPurchaseOrderTransmissionMethodCode() + "'";
                LOG.error(errorMessage);
                throw new RuntimeException(errorMessage);
            }
            LOG.info("setupDocumentForPendingFirstTransmission() Unhandled Transmission Status: " + po.getPurchaseOrderTransmissionMethodCode() + " -- Defaulting Status to '" + PurchaseOrderStatuses.OPEN + "'");
            attemptSetupOfInitialOpenOfDocument(po);
        }
    }

    /**
     * If the status of the purchase order is not OPEN and the initial open date is null, sets the initial open date to current date
     * and update the status to OPEN, then save the purchase order.
     * 
     * @param po The purchase order document whose initial open date and status we want to update.
     * @return boolean false if the initial open date, status update and the saving of the purchase order did not occur.
     */
    private boolean attemptSetupOfInitialOpenOfDocument(PurchaseOrderDocument po) {
        LOG.debug("attemptSetupOfInitialOpenOfDocument() started using document with doc id " + po.getDocumentNumber());
        boolean documentWasSaved = false;
        if (!PurchaseOrderStatuses.OPEN.equals(po.getStatusCode())) {
            if (ObjectUtils.isNull(po.getPurchaseOrderInitialOpenDate())) {
                LOG.debug("attemptSetupOfInitialOpenOfDocument() setting initial open date on document");
                po.setPurchaseOrderInitialOpenDate(dateTimeService.getCurrentSqlDate());
            }
            else {
                throw new RuntimeException("Document does not have status code '" + PurchaseOrderStatuses.OPEN + "' on it but value of initial open date is " + po.getPurchaseOrderInitialOpenDate());
            }
            LOG.info("attemptSetupOfInitialOpenOfDocument() Setting po document id " + po.getDocumentNumber() + " status from '" + po.getStatusCode() + "' to '" + PurchaseOrderStatuses.OPEN + "'");
            purapService.updateStatus(po, PurchaseOrderStatuses.OPEN);
            saveDocumentNoValidation(po);
            documentWasSaved = true;
        }
        else {
            LOG.info("attemptSetupOfInitialOpenOfDocument() Found document already in '" + PurchaseOrderStatuses.OPEN + "' status... will not change or update");
        }
        return documentWasSaved;
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#getCurrentPurchaseOrder(java.lang.Integer)
     */
    public PurchaseOrderDocument getCurrentPurchaseOrder(Integer id) {
        return getPurchaseOrderByDocumentNumber(purchaseOrderDao.getDocumentNumberForCurrentPurchaseOrder(id));
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#getPurchaseOrderByDocumentNumber(java.lang.String)
     */
    public PurchaseOrderDocument getPurchaseOrderByDocumentNumber(String documentNumber) {
        if (ObjectUtils.isNotNull(documentNumber)) {
            try {
                PurchaseOrderDocument doc = (PurchaseOrderDocument) documentService.getByDocumentHeaderId(documentNumber);
                if (ObjectUtils.isNotNull(doc)) {
                    KualiWorkflowDocument workflowDocument = doc.getDocumentHeader().getWorkflowDocument();
                    doc.refreshReferenceObject(RicePropertyConstants.DOCUMENT_HEADER);
                    doc.getDocumentHeader().setWorkflowDocument(workflowDocument);
                }
                return doc;
            }
            catch (WorkflowException e) {
                String errorMessage = "Error getting purchase order document from document service";
                LOG.error("getPurchaseOrderByDocumentNumber() " + errorMessage, e);
                throw new RuntimeException(errorMessage, e);
            }
        }
        return null;
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#getOldestPurchaseOrder(org.kuali.module.purap.document.PurchaseOrderDocument,
     *      org.kuali.module.purap.document.PurchaseOrderDocument)
     */
    public PurchaseOrderDocument getOldestPurchaseOrder(PurchaseOrderDocument po, PurchaseOrderDocument documentBusinessObject) {
        LOG.debug("entering getOldestPO(PurchaseOrderDocument)");
        if (ObjectUtils.isNotNull(po)) {
            String oldestDocumentNumber = purchaseOrderDao.getOldestPurchaseOrderDocumentNumber(po.getPurapDocumentIdentifier());
            if (StringUtils.equals(oldestDocumentNumber, po.getDocumentNumber())) {
                // manually set bo notes - this is mainly done for performance reasons (preferably we could call
                // retrieve doc notes in PersistableBusinessObjectBase but that is private)
                updateNotes(po, documentBusinessObject);
                LOG.debug("exiting getOldestPO(PurchaseOrderDocument)");
                return po;
            }
            else {
                PurchaseOrderDocument oldestPurchaseOrder = getPurchaseOrderByDocumentNumber(oldestDocumentNumber);
                updateNotes(oldestPurchaseOrder, documentBusinessObject);
                LOG.debug("exiting getOldestPO(PurchaseOrderDocument)");
                return oldestPurchaseOrder;
            }
        }
        return null;
    }

    /**
     * If the purchase order's object id is not null (I think this means if it's an existing purchase order that had already been
     * saved to the db previously), get the notes of the purchase order from the database, fix the notes' fields by calling the
     * fixDbNoteFields, then set the notes to the purchase order. Otherwise (I think this means if it's a new purchase order), set
     * the notes of this purchase order to be the notes of the documentBusinessObject.
     * 
     * @param po The current purchase order.
     * @param documentBusinessObject The oldest purchase order whose purapDocumentIdentifier is the same as the po's
     *        purapDocumentIdentifier.
     */
    private void updateNotes(PurchaseOrderDocument po, PurchaseOrderDocument documentBusinessObject) {
        if (ObjectUtils.isNotNull(documentBusinessObject)) {
            if (ObjectUtils.isNotNull(po.getObjectId())) {
                List<Note> dbNotes = SpringContext.getBean(NoteService.class).getByRemoteObjectId(po.getObjectId());
                // need to set fields that are not ojb managed (i.e. the notes on the documentBusinessObject may have been modified
                // independently of the ones in the db)
                fixDbNoteFields(documentBusinessObject, dbNotes);
                po.setBoNotes(dbNotes);
            }
            else {
                po.setBoNotes(documentBusinessObject.getBoNotes());
            }
        }
    }

    /**
     * This method fixes non ojb managed missing fields from the db
     * 
     * @param documentBusinessObject The oldest purchase order whose purapDocumentIdentifier is the same as the po's
     *        purapDocumentIdentifier.
     * @param dbNotes The notes of the purchase order obtained from the database.
     */
    private void fixDbNoteFields(PurchaseOrderDocument documentBusinessObject, List<Note> dbNotes) {
        for (int i = 0; i < dbNotes.size(); i++) {
            Note dbNote = dbNotes.get(i);
            List<Note> currentNotes = (List<Note>) documentBusinessObject.getBoNotes();
            if (i < currentNotes.size()) {
                Note currentNote = (currentNotes).get(i);
                // set the fyi from the current note if not empty
                AdHocRouteRecipient fyiNoteRecipient = currentNote.getAdHocRouteRecipient();
                if (ObjectUtils.isNotNull(fyiNoteRecipient)) {
                    dbNote.setAdHocRouteRecipient(fyiNoteRecipient);
                }
            }
        }
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#getPurchaseOrderNotes(java.lang.Integer)
     */
    public ArrayList<Note> getPurchaseOrderNotes(Integer id) {
        ArrayList notes = new TypedArrayList(Note.class);
        PurchaseOrderDocument po = getPurchaseOrderByDocumentNumber(purchaseOrderDao.getOldestPurchaseOrderDocumentNumber(id));
        if (ObjectUtils.isNotNull(po)) {
            notes = noteService.getByRemoteObjectId(po.getObjectId());
        }
        return notes;
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#setCurrentAndPendingIndicatorsForApprovedPODocuments(org.kuali.module.purap.document.PurchaseOrderDocument)
     */
    public void setCurrentAndPendingIndicatorsForApprovedPODocuments(PurchaseOrderDocument newPO) {
        // Get the "current PO" that's in the database, i.e. the PO row that contains current indicator = Y
        PurchaseOrderDocument oldPO = getCurrentPurchaseOrder(newPO.getPurapDocumentIdentifier());

        // If the document numbers between the oldPO and the newPO are different, then this is a PO change document.
        if (!oldPO.getDocumentNumber().equals(newPO.getDocumentNumber())) {
            // First, we set the indicators for the oldPO to : Current = N and Pending = N
            oldPO.setPurchaseOrderCurrentIndicator(false);
            oldPO.setPendingActionIndicator(false);

            // set the status and status history of the oldPO to retired version
            purapService.updateStatus(oldPO, PurapConstants.PurchaseOrderStatuses.RETIRED_VERSION);

            saveDocumentNoValidationUsingClearErrorMap(oldPO);
        }

        // Now, we set the "new PO" indicators so that Current = Y and Pending = N
        newPO.setPurchaseOrderCurrentIndicator(true);
        newPO.setPendingActionIndicator(false);
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#setCurrentAndPendingIndicatorsForDisapprovedChangePODocuments(org.kuali.module.purap.document.PurchaseOrderDocument)
     */
    public void setCurrentAndPendingIndicatorsForDisapprovedChangePODocuments(PurchaseOrderDocument newPO) {
        updateCurrentDocumentForNoPendingAction(newPO, PurapConstants.PurchaseOrderStatuses.DISAPPROVED_CHANGE, PurapConstants.PurchaseOrderStatuses.OPEN);
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#setCurrentAndPendingIndicatorsForCancelledChangePODocuments(org.kuali.module.purap.document.PurchaseOrderDocument)
     */
    public void setCurrentAndPendingIndicatorsForCancelledChangePODocuments(PurchaseOrderDocument newPO) {
        updateCurrentDocumentForNoPendingAction(newPO, PurapConstants.PurchaseOrderStatuses.CANCELLED_CHANGE, PurapConstants.PurchaseOrderStatuses.OPEN);
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#setCurrentAndPendingIndicatorsForCancelledReopenPODocuments(org.kuali.module.purap.document.PurchaseOrderDocument)
     */
    public void setCurrentAndPendingIndicatorsForCancelledReopenPODocuments(PurchaseOrderDocument newPO) {
        updateCurrentDocumentForNoPendingAction(newPO, PurapConstants.PurchaseOrderStatuses.CANCELLED_CHANGE, PurapConstants.PurchaseOrderStatuses.CLOSED);
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#setCurrentAndPendingIndicatorsForDisapprovedReopenPODocuments(org.kuali.module.purap.document.PurchaseOrderDocument)
     */
    public void setCurrentAndPendingIndicatorsForDisapprovedReopenPODocuments(PurchaseOrderDocument newPO) {
        updateCurrentDocumentForNoPendingAction(newPO, PurapConstants.PurchaseOrderStatuses.DISAPPROVED_CHANGE, PurapConstants.PurchaseOrderStatuses.CLOSED);
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#setCurrentAndPendingIndicatorsForCancelledRemoveHoldPODocuments(org.kuali.module.purap.document.PurchaseOrderDocument)
     */
    public void setCurrentAndPendingIndicatorsForCancelledRemoveHoldPODocuments(PurchaseOrderDocument newPO) {
        updateCurrentDocumentForNoPendingAction(newPO, PurapConstants.PurchaseOrderStatuses.CANCELLED_CHANGE, PurapConstants.PurchaseOrderStatuses.PAYMENT_HOLD);
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#setCurrentAndPendingIndicatorsForDisapprovedRemoveHoldPODocuments(org.kuali.module.purap.document.PurchaseOrderDocument)
     */
    public void setCurrentAndPendingIndicatorsForDisapprovedRemoveHoldPODocuments(PurchaseOrderDocument newPO) {
        updateCurrentDocumentForNoPendingAction(newPO, PurapConstants.PurchaseOrderStatuses.DISAPPROVED_CHANGE, PurapConstants.PurchaseOrderStatuses.PAYMENT_HOLD);
    }

    /**
     * Update the statuses of both the old purchase order and the new purchase orders, then save the old and the new purchase
     * orders.
     * 
     * @param newPO The new change purchase order document (e.g. the PurchaseOrderAmendmentDocument that was resulted from the user
     *        clicking on the amend button).
     * @param newPOStatus The status to be set on the new change purchase order document.
     * @param oldPOStatus The status to be set on the existing (old) purchase order document.
     */
    private void updateCurrentDocumentForNoPendingAction(PurchaseOrderDocument newPO, String newPOStatus, String oldPOStatus) {
        // Get the "current PO" that's in the database, i.e. the PO row that contains current indicator = Y
        PurchaseOrderDocument oldPO = getCurrentPurchaseOrder(newPO.getPurapDocumentIdentifier());
        // Set the Pending indicator for the oldPO to N
        oldPO.setPendingActionIndicator(false);
        purapService.updateStatus(oldPO, oldPOStatus);
        purapService.updateStatus(newPO, newPOStatus);
        saveDocumentNoValidationUsingClearErrorMap(oldPO);
        saveDocumentNoValidationUsingClearErrorMap(newPO);
    }

    public ArrayList<PurchaseOrderQuoteStatus> getPurchaseOrderQuoteStatusCodes() {
        ArrayList poQuoteStatuses = new TypedArrayList(PurchaseOrderQuoteStatus.class);
        poQuoteStatuses = (ArrayList) businessObjectService.findAll(PurchaseOrderQuoteStatus.class);
        return poQuoteStatuses;
    }

}
