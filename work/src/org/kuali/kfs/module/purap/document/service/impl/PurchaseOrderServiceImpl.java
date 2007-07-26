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
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.bo.Note;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.rule.event.RouteDocumentEvent;
import org.kuali.core.rule.event.SaveDocumentEvent;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DateTimeService;
import org.kuali.core.service.DocumentService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.service.KualiRuleService;
import org.kuali.core.service.NoteService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.util.ObjectUtils;
import org.kuali.core.util.TypedArrayList;
import org.kuali.core.web.struts.form.KualiDocumentFormBase;
import org.kuali.core.workflow.service.KualiWorkflowDocument;
import org.kuali.core.workflow.service.WorkflowDocumentService;
import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.util.SpringServiceLocator;
import org.kuali.module.purap.PurapConstants;
import org.kuali.module.purap.PurapKeyConstants;
import org.kuali.module.purap.PurapConstants.POTransmissionMethods;
import org.kuali.module.purap.PurapConstants.PurchaseOrderDocTypes;
import org.kuali.module.purap.PurapConstants.PurchaseOrderStatuses;
import org.kuali.module.purap.PurapConstants.RequisitionSources;
import org.kuali.module.purap.PurapConstants.VendorChoice;
import org.kuali.module.purap.bo.PurchaseOrderItem;
import org.kuali.module.purap.bo.PurchaseOrderQuoteStatus;
import org.kuali.module.purap.bo.PurchaseOrderVendorQuote;
import org.kuali.module.purap.dao.PurchaseOrderDao;
import org.kuali.module.purap.document.PurchaseOrderDocument;
import org.kuali.module.purap.document.PurchasingDocumentBase;
import org.kuali.module.purap.document.RequisitionDocument;
import org.kuali.module.purap.service.GeneralLedgerService;
import org.kuali.module.purap.service.PrintService;
import org.kuali.module.purap.service.PurapService;
import org.kuali.module.purap.service.PurchaseOrderPostProcessorService;
import org.kuali.module.purap.service.PurchaseOrderService;
import org.kuali.module.purap.service.RequisitionService;
import org.kuali.module.purap.util.PurApObjectUtils;
import org.kuali.module.vendor.bo.VendorDetail;
import org.kuali.module.vendor.service.VendorService;
import org.springframework.transaction.annotation.Transactional;
import org.springmodules.orm.ojb.OjbOperationException;

import edu.iu.uis.eden.exception.WorkflowException;

@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PurchaseOrderServiceImpl.class);

    private BusinessObjectService businessObjectService;
    private DateTimeService dateTimeService;
    private DocumentService documentService;
    private NoteService noteService;
    private GeneralLedgerService generalLedgerService;
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

    public void setGeneralLedgerService(GeneralLedgerService generalLedgerService) {
        this.generalLedgerService = generalLedgerService;
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

    public void saveDocumentWithoutValidation(PurchaseOrderDocument purchaseOrderDocument) {
        try {
//            purchaseOrderDocument.getDocumentHeader().getWorkflowDocument().saveRoutingData();
//            documentService.validateAndPersistDocument(purchaseOrderDocument, new SaveOnlyDocumentEvent(purchaseOrderDocument));
            documentService.saveDocumentWithoutRunningValidation(purchaseOrderDocument);
        }
        catch (WorkflowException we) {
            String errorMsg = "Error saving document # " + purchaseOrderDocument.getDocumentHeader().getDocumentNumber() + " " + we.getMessage(); 
            LOG.error(errorMsg, we);
            throw new RuntimeException(errorMsg, we);
        }
//        catch (ValidationException ve) {
//            String errorMsg = "Error saving document # " + purchaseOrderDocument.getDocumentHeader().getDocumentNumber() + " " + ve.getMessage(); 
//            LOG.error(errorMsg, ve);
//            throw new RuntimeException(errorMsg, ve);
//        }
    }

    /**
     * Creates an automatic PurchaseOrderDocument from given RequisitionDocument. Both documents need to be saved after this method
     * is called.
     * 
     * @param reqDocument - RequisitionDocument that the PO is being created from
     * @return PurchaseOrderDocument
     */
    public PurchaseOrderDocument createAutomaticPurchaseOrderDocument(RequisitionDocument reqDocument) {
        // TODO delyea - override user session so fiscal approver does not become PO initiator
        // update REQ data
        reqDocument.setPurchaseOrderAutomaticIndicator(Boolean.TRUE);
        reqDocument.setContractManagerCode(PurapConstants.APO_CONTRACT_MANAGER);

        try {
            // create PO and populate with default data
            PurchaseOrderDocument poDocument = createPurchaseOrderDocument(reqDocument);
            poDocument.setDefaultValuesForAPO();
            documentService.routeDocument(poDocument, null, null);

            return poDocument;
        }
        catch (WorkflowException e) {
            LOG.error("Error creating PO document: " + e.getMessage(),e);
            throw new RuntimeException("Error creating PO document: " + e.getMessage(),e);
        }
    }

    /**
     * Creates a PurchaseOrderDocument from given RequisitionDocument. Both documents need to be saved after this method is called.
     * 
     * @param reqDocument - RequisitionDocument that the PO is being created from
     * @return PurchaseOrderDocument
     */
    public PurchaseOrderDocument createPurchaseOrderDocument(RequisitionDocument reqDocument) {
        try {
            PurchaseOrderDocument poDocument = createPurchaseOrder(reqDocument);
            documentService.updateDocument(poDocument);
            documentService.prepareWorkflowDocument(poDocument);
            workflowDocumentService.save(poDocument.getDocumentHeader().getWorkflowDocument(), "", null);
            return poDocument;
        }
        catch (WorkflowException e) {
            LOG.error("Error creating PO document: " + e.getMessage(),e);
            throw new RuntimeException("Error creating PO document: " + e.getMessage(),e);
        }
    }
    
    private PurchaseOrderDocument createPurchaseOrder(RequisitionDocument reqDocument) throws WorkflowException {
        // create PO and populate with default data
        PurchaseOrderDocument poDocument = null;
//        try {
            poDocument = (PurchaseOrderDocument) documentService.getNewDocument(PurchaseOrderDocTypes.PURCHASE_ORDER_DOCUMENT);
            poDocument.populatePurchaseOrderFromRequisition(reqDocument);
            poDocument.setStatusCode(PurchaseOrderStatuses.IN_PROCESS);
            poDocument.setPurchaseOrderCurrentIndicator(true);
            poDocument.setPendingActionIndicator(false);

            // TODO: need this?
            // poDocument.setInternalPurchasingLimit(getInternalPurchasingDollarLimit(po, u.getOrganization().getChart().getCode(),
            // u.getOrganization().getCode()));

            if (RequisitionSources.B2B.equals(poDocument.getRequisitionSourceCode())) {
                poDocument.setPurchaseOrderVendorChoiceCode(VendorChoice.CONTRACTED_PRICE);
            }

            VendorDetail vendor = vendorService.getVendorDetail(poDocument.getVendorHeaderGeneratedIdentifier(), poDocument.getVendorDetailAssignedIdentifier());

            if (ObjectUtils.isNotNull(poDocument.getVendorContract())) {
                poDocument.setVendorPaymentTermsCode(poDocument.getVendorContract().getVendorPaymentTermsCode());
                poDocument.setVendorShippingPaymentTermsCode(poDocument.getVendorContract().getVendorShippingPaymentTermsCode());
                poDocument.setVendorShippingTitleCode(poDocument.getVendorContract().getVendorShippingTitleCode());
            }
            else if (ObjectUtils.isNotNull(vendor)) {
                poDocument.setVendorPaymentTermsCode(vendor.getVendorPaymentTermsCode());
                poDocument.setVendorShippingPaymentTermsCode(vendor.getVendorShippingPaymentTermsCode());
                poDocument.setVendorShippingTitleCode(vendor.getVendorShippingTitleCode());
            }

            purapService.addBelowLineItems(poDocument);

//            if (isAPO) {
//                poDocument.setDefaultValuesForAPO();
//                documentService.routeDocument(poDocument, null, null);
//            }
//            else {
//                documentService.updateDocument(poDocument);
//                documentService.prepareWorkflowDocument(poDocument);
//                workflowDocumentService.save(poDocument.getDocumentHeader().getWorkflowDocument(), "", null);
//            }
//        }
//        catch (WorkflowException e) {
//            LOG.error("Error creating PO document: " + e.getMessage(),e);
//            throw new RuntimeException("Error creating PO document: " + e.getMessage(),e);
//        }
//        catch (Exception e) {
//            LOG.error("Error persisting document # " + poDocument.getDocumentHeader().getDocumentNumber() + " " + e.getMessage(),e);
//            throw new RuntimeException("Error persisting document # " + poDocument.getDocumentHeader().getDocumentNumber() + " " + e.getMessage(),e);
//        }
        return poDocument;
    }

    public KualiDecimal getInternalPurchasingDollarLimit(PurchasingDocumentBase document, String chartCode, String orgCode) {
        if ((document.getVendorContract() != null) && (document.getContractManager() != null)) {
            KualiDecimal contractDollarLimit = vendorService.getApoLimitFromContract(document.getVendorContract().getVendorContractGeneratedIdentifier(), chartCode, orgCode);
            KualiDecimal contractManagerLimit = document.getContractManager().getContractManagerDelegationDollarLimit();
            if ((contractDollarLimit != null) && (contractManagerLimit != null)) {
                if (contractDollarLimit.compareTo(contractManagerLimit) > 0) {
                    return contractDollarLimit;
                }
                else {
                    return contractManagerLimit;
                }
            } else if (contractDollarLimit != null) {
                return contractDollarLimit;
            } else {
                return contractManagerLimit;
            }
        }
        else if ((document.getVendorContract() == null) && (document.getContractManager() != null)) {
            return document.getContractManager().getContractManagerDelegationDollarLimit();
        }
        else if ((document.getVendorContract() != null) && (document.getContractManager() == null)) {
            return purapService.getApoLimit(document.getVendorContract().getVendorContractGeneratedIdentifier(), chartCode, orgCode);
        }
        else {
            String errorMsg = "No internal purchase order dollar limit found for purchase order '" + document.getPurapDocumentIdentifier() + "'.";
            LOG.warn(errorMsg);
            return null;
        }
    }
    
    public boolean printPurchaseOrderQuotePDF(PurchaseOrderDocument po, PurchaseOrderVendorQuote povq, ByteArrayOutputStream baosPDF) {

        String environment = kualiConfigurationService.getPropertyString(KFSConstants.ENVIRONMENT_KEY);
        Collection<String> generatePDFErrors = printService.generatePurchaseOrderQuotePdf(po, povq, baosPDF, environment);

        if (generatePDFErrors.size() > 0) {
            for (String error : generatePDFErrors) {
                GlobalVariables.getErrorMap().putError(KFSConstants.GLOBAL_ERRORS, PurapKeyConstants.ERROR_PURCHASE_ORDER_PDF, error);
            }
            return false;
        }
        else {
            // TODO QUOTE - update PurchaseOrderVendorQuote here
            saveDocumentWithoutValidation(po);
            return true;
        }
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#printPurchaseOrderPDF(org.kuali.module.purap.document.PurchaseOrderDocument,
     *      java.lang.String, java.lang.String, java.util.List, java.io.ByteArrayOutputStream, java.lang.String)
     */
    public boolean printPurchaseOrderPDF(PurchaseOrderDocument po, String docType, String annotation, List adhocRoutingRecipients, ByteArrayOutputStream baosPDF) {

        String environment = kualiConfigurationService.getPropertyString(KFSConstants.ENVIRONMENT_KEY);
        boolean isRetransmit = false;
        boolean result = true;
        Collection<String> generatePDFErrors = printService.generatePurchaseOrderPdf(po, baosPDF, isRetransmit, environment);

        if (generatePDFErrors.size() > 0) {
            for (String error : generatePDFErrors) {
                GlobalVariables.getErrorMap().putError(KFSConstants.GLOBAL_ERRORS, PurapKeyConstants.ERROR_PURCHASE_ORDER_PDF, error);
            }
            result = false;
        }
        if (result) {
            // logic can stay here because PO has a pending status for this transmission type
            Date currentSqlDate = dateTimeService.getCurrentSqlDate();
            po.setPurchaseOrderFirstTransmissionDate(currentSqlDate);
            po.setPurchaseOrderInitialOpenDate(currentSqlDate);
            po.setPurchaseOrderLastTransmitDate(currentSqlDate);
            po.setPurchaseOrderCurrentIndicator(true);
            purapService.updateStatusAndStatusHistory(po, PurchaseOrderStatuses.OPEN);
            saveDocumentWithoutValidation(po);
        }
        return result;
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#printPurchaseOrderPDF(org.kuali.module.purap.document.PurchaseOrderDocument,
     *      java.lang.String, java.lang.String, java.util.List, java.io.ByteArrayOutputStream, java.lang.String)
     */
    public boolean retransmitPurchaseOrderPDF(PurchaseOrderDocument po, String docType, String annotation, List adhocRoutingRecipients, ByteArrayOutputStream baosPDF) {

        String environment = kualiConfigurationService.getPropertyString(KFSConstants.ENVIRONMENT_KEY);
        boolean isRetransmit = true;
        boolean result = true;
        List<PurchaseOrderItem> items = po.getItems();
        List<PurchaseOrderItem> retransmitItems = new ArrayList();
        for (PurchaseOrderItem item : items) {
            if (item.isItemSelectedForRetransmitIndicator()) {
                item.refreshNonUpdateableReferences();
                retransmitItems.add(item);
            }
        }
        po.setItems(retransmitItems);
        Collection<String> generatePDFErrors = printService.generatePurchaseOrderPdf(po, baosPDF, isRetransmit, environment);

        if (generatePDFErrors.size() > 0) {
            for (String error : generatePDFErrors) {
                GlobalVariables.getErrorMap().putError(KFSConstants.GLOBAL_ERRORS, PurapKeyConstants.ERROR_PURCHASE_ORDER_PDF, error);
            }
            result = false;
        }
        // below logic moved to post processor PurchaseOrderPostProcessorRetransmitService.handleRouteStatusChange()
//        if (result) {
//            Date currentSqlDate = dateTimeService.getCurrentSqlDate();
//            po.setPurchaseOrderLastTransmitDate(currentSqlDate);
//            po.setPurchaseOrderCurrentIndicator(true);
//            saveDocumentWithoutValidation(po);
//        }
        return result;
    }

    /**
     * @see org.kuali.module.purap.service.PurchaseOrderService#updateFlagsAndRoute(org.kuali.module.purap.document.PurchaseOrderDocument,
     *      java.lang.String, java.lang.String, java.util.List)
     */
    public PurchaseOrderDocument updateFlagsAndRoute(String documentNumber, String docType, String annotation, List adhocRoutingRecipients) {
        try {
            PurchaseOrderDocument po = SpringServiceLocator.getPurchaseOrderService().getPurchaseOrderByDocumentNumber(documentNumber);
            //TODO: Chris - RESEARCH: does this have any effect?  I think it may be lost before the po is brought up again.
            po.setSummaryAccountsWithItems(new HashMap());
            po.setSummaryAccountsWithItemsKey(new ArrayList());
            po.setSummaryAccountsWithItemsValue(new ArrayList());
            po.setPendingActionIndicator(true);

            businessObjectService.save(po);

            PurchaseOrderDocument newPO = (PurchaseOrderDocument)documentService.getNewDocument(docType);
            //TODO: Chris - RESEARCH: does this have any effect?  I think it may be lost before the po is brought up again.
            newPO.refreshAccountSummary();
            PurApObjectUtils.populateFromBaseWithSuper(po, newPO);
            newPO.refreshNonUpdateableReferences();
            newPO.setPurchaseOrderCurrentIndicator(false);
            newPO.setPendingActionIndicator(false);

            // check the rules before taking action
            if (docType.equals(PurapConstants.PurchaseOrderDocTypes.PURCHASE_ORDER_AMENDMENT_DOCUMENT)) {
                boolean rulePassed = kualiRuleService.applyRules(new SaveDocumentEvent(newPO));
                if (!rulePassed) {
                    po.setPendingActionIndicator(false);
                    saveDocumentWithoutValidation(po);
                    return po;
                }
                else {
                    newPO.setStatusCode(PurapConstants.PurchaseOrderStatuses.AMENDMENT);
                    saveDocumentWithoutValidation(po);
                    documentService.saveDocument(newPO);
                }
            }
            else {
                boolean rulePassed = kualiRuleService.applyRules(new RouteDocumentEvent(newPO));
                if (!rulePassed) {
                    po.setPendingActionIndicator(false);
                    saveDocumentWithoutValidation(po);
                    return po;
                }
                else {
                    saveDocumentWithoutValidation(po);
                    documentService.routeDocument(newPO, annotation, adhocRoutingRecipients);
                }
            }
            return newPO;
        }
        catch (WorkflowException we) {
            LOG.error("Error during updateFlagsAndRoute on PO document: " + we);
            throw new RuntimeException("Error during updateFlagsAndRoute on PO document: " + we.getMessage(),we);
        }
        catch (OjbOperationException ooe) {
            LOG.error("Error during updateFlagsAndRoute on PO document: " + ooe);
            throw new RuntimeException("Error during updateFlagsAndRoute on PO document: " + ooe.getMessage(),ooe);
        }
        catch (Exception e) {
            LOG.error("Error during updateFlagsAndRoute on PO document: " + e);
            throw new RuntimeException("Error during updateFlagsAndRoute on PO document: " + e.getMessage(),e);
        }
    }

    /* We don't need to lock the PO during amendment routing anymore, we'll lock it in updateFlagsAndRoute()
    public boolean routePurchaseOrderAmendmentDocument(KualiDocumentFormBase kualiDocumentFormBase, String annotation, List adhocRoutingRecipients) {
        PurchaseOrderDocument document = (PurchaseOrderDocument) kualiDocumentFormBase.getDocument();
        try {
            // get the previous PO, i.e. the PO with the same PO_ID but the current indicator is Y.
            PurchaseOrderDocument oldPO = getCurrentPurchaseOrder(document.getPurapDocumentIdentifier());
            if (oldPO.isPendingActionIndicator()) {
                GlobalVariables.getErrorMap().putError(PurapPropertyConstants.PURCHASE_ORDER_IDENTIFIER, PurapKeyConstants.ERROR_PURCHASE_ORDER_CANNOT_AMEND);
                return false;
            }
            oldPO.setPendingActionIndicator(true);
            save(oldPO);
            documentService.routeDocument(document, kualiDocumentFormBase.getAnnotation(), adhocRoutingRecipients);
            GlobalVariables.getMessageList().add(KeyConstants.MESSAGE_ROUTE_SUCCESSFUL);
        }
        catch (WorkflowException we) {
            LOG.error("Error during updateFlagsAndRoute on PO document: " + we);
            throw new RuntimeException("Error during updateFlagsAndRoute on PO document: " + we.getMessage());
        }
        catch (OjbOperationException ooe) {
            LOG.error("Error during updateFlagsAndRoute on PO document: " + ooe);
            GlobalVariables.getErrorMap().putError(PurapPropertyConstants.PURCHASE_ORDER_IDENTIFIER, PurapKeyConstants.ERROR_PURCHASE_ORDER_CANNOT_AMEND);
            return false;
        }
        catch (Exception e) {
            LOG.error("Error during updateFlagsAndRoute on PO document: " + e);
            throw new RuntimeException("Error during updateFlagsAndRoute on PO document: " + e.getMessage());
        }
        return true;
    }
    */
    
    public void cancelAmendment(KualiDocumentFormBase kualiDocumentFormBase) {
        PurchaseOrderDocument document = (PurchaseOrderDocument) kualiDocumentFormBase.getDocument();
        PurchaseOrderDocument oldPO = getCurrentPurchaseOrder(document.getPurapDocumentIdentifier());
        oldPO.setPendingActionIndicator(false);
        saveDocumentWithoutValidation(oldPO);
    }
    
    /*
     * public void sendFYItoWorkgroup(PurchaseOrderDocument po, String annotation, Long workgroupId) { LOG.debug("SendFYI started
     * with annotation: "+ annotation); try { KualiWorkflowDocument workflowDoc = po.getDocumentHeader().getWorkflowDocument();
     * String currentNodeName = PurapConstants.DOC_ADHOC_NODE_NAME; if
     * (!(EdenConstants.ROUTE_HEADER_INITIATED_CD.equals(workflowDoc.getRouteHeader().getDocRouteStatus()))) { if
     * (getCurrentRouteNodeName(workflowDoc) != null) { currentNodeName = getCurrentRouteNodeName(workflowDoc); } } //We can't do
     * this because we can't instantiate a new WorkgroupIdVO.
     * //workflowDoc.appSpecificRouteDocumentToWorkgroup(EdenConstants.ACTION_REQUEST_FYI_REQ, currentNodeName, 0, // annotation,
     * new WorkgroupIdVO(workgroupId), "Initiator", true); } catch (WorkflowException we) { LOG.error("Error during sendFYI on PO
     * document: " + we.getMessage()); throw new RuntimeException("Error during sendFYI on PO document: " + we.getMessage()); }
     * catch (Exception e) { LOG.error("Error during sendFYI on PO document: " + e.getMessage()); throw new RuntimeException("Error
     * during sendFYI on PO document: " + e.getMessage()); } LOG.debug("SendFYI ended."); }
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
     * @see org.kuali.module.purap.service.PurchaseOrderService#convertDocTypeToService()
     */
    public PurchaseOrderPostProcessorService convertDocTypeToService(String docTypeId) {
        PurchaseOrderPostProcessorService popp = null;
        String docType;
        docType = (String) PurapConstants.PURCHASE_ORDER_DOC_TYPE_MAP.get(docTypeId);
        if (StringUtils.isNotEmpty(docType)) {
            popp = (PurchaseOrderPostProcessorService) SpringServiceLocator.getService(docType);
        }

        return popp;
    }

    public void completePurchaseOrder(PurchaseOrderDocument po) {
        LOG.debug("completePurchaseOrder() started");

        UniversalUser user = GlobalVariables.getUserSession().getUniversalUser();

        // create general ledger entries for this PO
        generalLedgerService.generateEntriesApprovePo(po);

//        if (POTransmissionMethods.PRINT.equals(po.getPurchaseOrderTransmissionMethodCode())) {
//            LOG.debug("completePurchaseOrder() Purchase Order Transmission Type is Print");
//            po.setPurchaseOrderCurrentIndicator(true);
//            po.setPendingActionIndicator(true);
//            purapService.updateStatusAndStatusHistory(po, PurchaseOrderStatuses.PENDING_PRINT);
//        }
//        else {
//            LOG.info("completePurchaseOrder() Unhandled Transmission Status: " + po.getPurchaseOrderTransmissionMethodCode() + " -- Defaulting Status to OPEN");
//            purapService.updateStatusAndStatusHistory(po, PurchaseOrderStatuses.OPEN);
//            po.setPurchaseOrderInitialOpenDate(dateTimeService.getCurrentSqlDate());
//        }
        if (!PurchaseOrderStatuses.OPEN.equals(po.getStatusCode())) {
            String statusCode = PurchaseOrderStatuses.OPEN;
            LOG.info("completePurchaseOrder() Setting po document id " + po.getDocumentNumber() + " status from '" + po.getStatusCode() + "' to '" + statusCode + "'" );
            purapService.updateStatusAndStatusHistory(po, PurchaseOrderStatuses.OPEN);
            po.setPurchaseOrderInitialOpenDate(dateTimeService.getCurrentSqlDate());
        }
        this.saveDocumentWithoutValidation(po);
        
    }
    
    public void setupDocumentForPendingFirstTransmission(PurchaseOrderDocument po, boolean hasActionRequestForDocumentTransmission) {
        if (POTransmissionMethods.PRINT.equals(po.getPurchaseOrderTransmissionMethodCode())) {
            String newStatusCode = PurchaseOrderStatuses.STATUSES_BY_TRANSMISSION_TYPE.get(po.getPurchaseOrderTransmissionMethod());
            LOG.debug("setupDocumentForPendingFirstTransmission() Purchase Order Transmission Type is '" + po.getPurchaseOrderTransmissionMethodCode() + "' setting status to '" + newStatusCode + "'");
            po.setPurchaseOrderCurrentIndicator(true);
            po.setPendingActionIndicator(true);
            purapService.updateStatusAndStatusHistory(po, newStatusCode);
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
            if (!PurchaseOrderStatuses.OPEN.equals(po.getStatusCode())) {
                if (ObjectUtils.isNull(po.getPurchaseOrderInitialOpenDate())) {
                    po.setPurchaseOrderInitialOpenDate(dateTimeService.getCurrentSqlDate());
                }
                purapService.updateStatusAndStatusHistory(po, PurchaseOrderStatuses.OPEN);
            }
        }
    }

    public PurchaseOrderDocument getCurrentPurchaseOrder(Integer id) {
        return getPurchaseOrderByDocumentNumber(purchaseOrderDao.getDocumentNumberForCurrentPurchaseOrder(id));
    }

    public PurchaseOrderDocument getPurchaseOrderByDocumentNumber(String documentNumber) {
        if (ObjectUtils.isNotNull(documentNumber)) {
            try {
                PurchaseOrderDocument doc = (PurchaseOrderDocument)documentService.getByDocumentHeaderId(documentNumber);
                if (ObjectUtils.isNotNull(doc)) {
                    doc.refreshNonUpdateableReferences();
                }
                return doc;
            }
            catch (WorkflowException e) {
                String errorMessage = "Error getting purchase order document from document service";
                LOG.error("getPurchaseOrderByDocumentNumber() " + errorMessage,e);
                throw new RuntimeException(errorMessage,e);
            }
        }
        return null;
    }
    
    public PurchaseOrderDocument getOldestPurchaseOrder(PurchaseOrderDocument po) {
        LOG.debug("entering getOldestPO(PurchaseOrderDocument)");
        if (ObjectUtils.isNotNull(po)) {
            String oldestDocumentNumber = purchaseOrderDao.getOldestPurchaseOrderDocumentNumber(po.getPurapDocumentIdentifier());
            if (StringUtils.equals(oldestDocumentNumber, po.getDocumentNumber())) {
                //manually set bo notes - this is mainly done for performance reasons (preferably we could call
                //retrieve doc notes in PersistableBusinessObjectBase but that is private)
                po.setBoNotes(SpringServiceLocator.getNoteService().getByRemoteObjectId(po.getObjectId()));
                LOG.debug("exiting getOldestPO(PurchaseOrderDocument)");
                return po;
            }
            LOG.debug("exiting getOldestPO(PurchaseOrderDocument)");
            return getPurchaseOrderByDocumentNumber(oldestDocumentNumber);
        }
        return null;
    }
    
    public ArrayList<Note> getPurchaseOrderNotes(Integer id) {
        ArrayList notes = new TypedArrayList(Note.class);
        PurchaseOrderDocument po = getPurchaseOrderByDocumentNumber(purchaseOrderDao.getOldestPurchaseOrderDocumentNumber(id));
        if (ObjectUtils.isNotNull(po)) {
            notes = noteService.getByRemoteObjectId(po.getObjectId());
        }
        return notes;
    }

    public void setCurrentAndPendingIndicatorsInPostProcessor(PurchaseOrderDocument newPO) {
        // TODO delyea - ask about if this is best status/place to set indicators
        if (newPO.getDocumentHeader().getWorkflowDocument().stateIsProcessed()) {
            setCurrentAndPendingIndicatorsForApprovedPODocuments(newPO);
        } else if (newPO.getDocumentHeader().getWorkflowDocument().stateIsDisapproved()) {
            setCurrentAndPendingIndicatorsForDisapprovedPODocuments(newPO);
        }
    }

    private void setCurrentAndPendingIndicatorsForApprovedPODocuments(PurchaseOrderDocument newPO) {
        // Get the "current PO" that's in the database, i.e. the PO row that contains current indicator = Y
        PurchaseOrderDocument oldPO = getCurrentPurchaseOrder(newPO.getPurapDocumentIdentifier());
        // First, we set the indicators for the oldPO to : Current = N and Pending = N
        oldPO.setPurchaseOrderCurrentIndicator(false);
        oldPO.setPendingActionIndicator(false);
        saveDocumentWithoutValidation(oldPO);
        // Now, we set the "new PO" indicators so that Current = Y and Pending = N
        newPO.setPurchaseOrderCurrentIndicator(true);
        newPO.setPendingActionIndicator(false);
    }

    private void setCurrentAndPendingIndicatorsForDisapprovedPODocuments(PurchaseOrderDocument newPO) {
        // Get the "current PO" that's in the database, i.e. the PO row that contains current indicator = Y
        PurchaseOrderDocument oldPO = getCurrentPurchaseOrder(newPO.getPurapDocumentIdentifier());
        // Set the Pending indicator for the oldPO to N
        oldPO.setPendingActionIndicator(false);
        saveDocumentWithoutValidation(oldPO);
    }

    public ArrayList<PurchaseOrderQuoteStatus> getPurchaseOrderQuoteStatusCodes() {
        ArrayList poQuoteStatuses = new TypedArrayList(PurchaseOrderQuoteStatus.class);
        poQuoteStatuses = (ArrayList) businessObjectService.findAll(PurchaseOrderQuoteStatus.class);
        return poQuoteStatuses;
    }
}
