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
package org.kuali.module.purap.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.Constants;
import org.kuali.core.util.ObjectUtils;
import org.kuali.module.purap.document.PurchaseOrderDocument;
import org.kuali.module.purap.web.struts.form.PurchaseOrderForm;

/**
 * This class handles specific Actions requests for the Requisition.
 * 
 */
public class PurchaseOrderAction extends PurchasingActionBase {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PurchaseOrderAction.class);

    /**
     * @see org.kuali.core.web.struts.action.KualiAction#refresh(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
// TODO make sure this fits for PO
//        PurchaseOrderForm poForm = (PurchaseOrderForm) form;
//        PurchaseOrderDocument document = (PurchaseOrderDocument) poForm.getDocument();
//        BusinessObjectService businessObjectService = SpringServiceLocator.getBusinessObjectService();
//        /* refresh from requisition vendor lookup */
//        if (document.getVendorDetail() == null &&
//            document.getVendorDetailAssignedIdentifier() != null &&
//            document.getVendorHeaderGeneratedIdentifier() != null)  {
//
//            Integer vendorDetailAssignedId = ((RequisitionDocument) rqForm.getDocument()).getVendorDetailAssignedIdentifier();
//            Integer vendorHeaderGeneratedId = ((RequisitionDocument) rqForm.getDocument()).getVendorHeaderGeneratedIdentifier();
//            VendorDetail refreshVendorDetail = new VendorDetail();
//            refreshVendorDetail.setVendorDetailAssignedIdentifier(vendorDetailAssignedId);
//            refreshVendorDetail.setVendorHeaderGeneratedIdentifier(vendorHeaderGeneratedId);
//            refreshVendorDetail = (VendorDetail)businessObjectService.retrieve(refreshVendorDetail);
//            ((RequisitionDocument) rqForm.getDocument()).templateVendorDetail(refreshVendorDetail);
//        } 
//        if (Constants.KUALI_LOOKUPABLE_IMPL.equals(rqForm.getRefreshCaller()) &&
//            request.getParameter("document.vendorContractGeneratedIdentifier") != null) {
//            Integer vendorContractGeneratedId = ((RequisitionDocument) rqForm.getDocument()).getVendorContractGeneratedIdentifier();
//            VendorContract refreshVendorContract = new VendorContract();
//            refreshVendorContract.setVendorContractGeneratedIdentifier(vendorContractGeneratedId);
//            refreshVendorContract = (VendorContract)businessObjectService.retrieve(refreshVendorContract);
//            ((RequisitionDocument) rqForm.getDocument()).templateVendorContract(refreshVendorContract);
//        }
//
//        // Format phone numbers
//        document.setInstitutionContactPhoneNumber(PhoneNumberUtils.formatNumberIfPossible(document.getInstitutionContactPhoneNumber()));    
//        document.setRequestorPersonPhoneNumber(PhoneNumberUtils.formatNumberIfPossible(document.getRequestorPersonPhoneNumber()));    
//        document.setDeliveryToPhoneNumber(PhoneNumberUtils.formatNumberIfPossible(document.getDeliveryToPhoneNumber()));    
 
        return super.refresh(mapping, form, request, response);
    }

    public ActionForward viewRelatedDocuments(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOG.debug("viewRelatedDocuments() enter action");

        //TODO add code

        return mapping.findForward("viewRelatedDocuments");
    }

    public ActionForward viewPaymentHistory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOG.debug("viewPaymentHistory() enter action");

        //TODO add code

        return mapping.findForward("viewPaymentHistory");
    }

}