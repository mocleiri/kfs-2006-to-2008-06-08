/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.module.cams.web.struts.action;

import static org.kuali.module.cams.CamsPropertyConstants.Asset.CAPITAL_ASSET_NUMBER;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.RiceConstants;
import org.kuali.RiceKeyConstants;
import org.kuali.core.document.Document;
import org.kuali.core.question.ConfirmationQuestion;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DocumentService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.format.CurrencyFormatter;
import org.kuali.core.web.struts.form.KualiDocumentFormBase;
import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.KFSKeyConstants;
import org.kuali.kfs.KFSPropertyConstants;
import org.kuali.kfs.context.SpringContext;
import org.kuali.kfs.web.struts.action.KualiAccountingDocumentActionBase;
import org.kuali.module.cams.CamsConstants;
import org.kuali.module.cams.CamsKeyConstants;
import org.kuali.module.cams.CamsPropertyConstants;
import org.kuali.module.cams.bo.Asset;
import org.kuali.module.cams.bo.AssetHeader;
import org.kuali.module.cams.bo.AssetPaymentDetail;
import org.kuali.module.cams.document.AssetPaymentDocument;
import org.kuali.module.cams.document.AssetTransferDocument;
import org.kuali.module.cams.service.AssetLocationService;
import org.kuali.module.cams.service.PaymentSummaryService;
import org.kuali.module.cams.web.struts.form.AssetPaymentForm;
import org.kuali.module.cams.web.struts.form.AssetTransferForm;
import org.kuali.module.financial.document.JournalVoucherDocument;
import org.kuali.module.financial.web.struts.form.JournalVoucherForm;
import org.kuali.rice.KNSServiceLocator;

import edu.iu.uis.eden.exception.WorkflowException;

public class AssetPaymentAction extends KualiAccountingDocumentActionBase {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(AssetPaymentAction.class);

    /**
     * 
     * @see org.kuali.kfs.web.struts.action.KualiAccountingDocumentActionBase#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AssetPaymentForm apForm = (AssetPaymentForm) form;
        String command = ((AssetPaymentForm) form).getCommand();
        String docID = ((AssetPaymentForm) form).getDocId();
        String capitalAssetNumber = ((AssetPaymentForm) form).getCapitalAssetNumber();
        LOG.info("***AssetPaymentAction.execute() - menthodToCall: " + apForm.getMethodToCall() + " - Command:" + command + " - DocId:" + docID + " - Capital Asset Number:" + capitalAssetNumber);
        return super.execute(mapping, form, request, response);
    }

    /**
     * 
     * @see org.kuali.kfs.web.struts.action.KualiAccountingDocumentActionBase#refresh(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     *
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //The edoc is populated right after hitting the return value link.        
        AssetPaymentDocument assetPaymentDocument = ((AssetPaymentForm) form).getAssetPaymentDocument();
        Asset asset = new Asset();
        AssetPayment assetPayment = new AssetPayment();
        List <AssetPayment> assetPayments = new ArrayList();

        String refreshCaller = ((AssetPaymentForm)form).getRefreshCaller();
        if (CamsConstants.ASSET_LOOKUPABLE_ID.equals(refreshCaller)) {                        
            //Getting the list of asset payments.
            Map<String,Long> fieldValues = new HashMap<String,Long>();
            fieldValues.put(CamsPropertyConstants.Asset.CAPITAL_ASSET_NUMBER,assetPaymentDocument.getCapitalAssetNumber());
            assetPayments= (List<AssetPayment>)SpringContext.getBean(BusinessObjectService.class).findMatching(AssetPayment.class, fieldValues);

            //Getting the asset record.
            asset.setCapitalAssetNumber(assetPaymentDocument.getCapitalAssetNumber());
            asset = (Asset)SpringContext.getBean(BusinessObjectService.class).retrieve(asset);
            asset.setAssetPayments(assetPayments);
            assetPaymentDocument.setAsset(asset);   
        }
        return super.refresh(mapping, form, request, response);
    }*/
    
    @Override
    protected void createDocument(KualiDocumentFormBase kualiDocumentFormBase) throws WorkflowException {
        super.createDocument(kualiDocumentFormBase);
        AssetPaymentForm assetPaymentForm = (AssetPaymentForm) kualiDocumentFormBase;
        AssetPaymentDocument assetPaymentDocument = assetPaymentForm.getAssetPaymentDocument();

        String capitalAssetNumber = assetPaymentForm.getCapitalAssetNumber();

        Asset asset = assetPaymentDocument.getAsset();
        asset = handleRequestFromLookup(capitalAssetNumber, assetPaymentForm, assetPaymentDocument, asset);

        //Populating the hidden fields in the assetPayment.jsp
        assetPaymentDocument.setCapitalAssetNumber(asset.getCapitalAssetNumber());
        assetPaymentDocument.setOrganizationOwnerAccountNumber(asset.getOrganizationOwnerAccountNumber());
        assetPaymentDocument.setOrganizationOwnerChartOfAccountsCode(asset.getOrganizationOwnerChartOfAccountsCode());
        assetPaymentDocument.setCampusCode(asset.getCampusCode());
        assetPaymentDocument.setAgencyNumber(asset.getAgencyNumber());
        assetPaymentDocument.setBuildingCode(asset.getBuildingCode());
        assetPaymentDocument.setRepresentativeUniversalIdentifier(asset.getRepresentativeUniversalIdentifier());

        //Adding the changes made in the document in the ActionForm.
        assetPaymentForm.setDocument(assetPaymentDocument);
    }

    /**
     * 
     * @see org.kuali.kfs.web.struts.action.KualiAccountingDocumentActionBase#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override    
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward returnForward;
        AssetPaymentForm apForm     = (AssetPaymentForm) form;
        AssetPaymentDocument apDoc  = apForm.getAssetPaymentDocument();

        apDoc.getAsset().refreshReferenceObject(CamsPropertyConstants.Asset.ASSET_PAYMENTS);        
        if (apDoc.hasDifferentObjectSubTypes()) {
            // it's not in "balance"
            returnForward = processDifferentObjectSubTypeConfirmationQuestion(mapping, form, request, response);
    
            // if not null, then the question component either has control of the flow and needs to ask its questions
            // or the person chose the "cancel" or "no" button. Otherwise we have control.
            if (returnForward != null) {
                return returnForward;
            }
        } 
        return super.save(mapping, form, request, response);
    }
    
    /**
     * 
     * Displays a confirmation question to let the user know there are payments for a particular asset that have
     * different object sub type codes
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    protected ActionForward processDifferentObjectSubTypeConfirmationQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AssetPaymentForm apForm     = (AssetPaymentForm) form;
        AssetPaymentDocument apDoc  = apForm.getAssetPaymentDocument();

        String question = request.getParameter(KFSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        KualiConfigurationService kualiConfiguration = SpringContext.getBean(KualiConfigurationService.class);

        if (question == null) { // question hasn't been asked
            // now transfer control over to the question component
            String warningMessage=kualiConfiguration.getPropertyString(CamsKeyConstants.Payment.WARNING_NOT_SAME_OBJECT_SUB_TYPES);
            return this.performQuestionWithoutInput(mapping, form, request, response, CamsConstants.ASSET_PAYMENT_DIFFERENT_OBJECT_SUB_TYPE_CONFIRMATION_QUESTION,warningMessage, KFSConstants.CONFIRMATION_QUESTION, KFSConstants.SAVE_METHOD, "");
        }
        else {
            String buttonClicked = request.getParameter(KFSConstants.QUESTION_CLICKED_BUTTON);
            
            if ((CamsConstants.ASSET_PAYMENT_DIFFERENT_OBJECT_SUB_TYPE_CONFIRMATION_QUESTION.equals(question)) && 
                 ConfirmationQuestion.NO.equals(buttonClicked)) {
                GlobalVariables.getMessageList().add(CamsKeyConstants.Payment.MESSAGE_PAYMENT_WAS_NOT_SAVED);
                return mapping.findForward(KFSConstants.MAPPING_BASIC);
            }
        }
        return null;
    }
    
    /**
     * 
     * This method...
     * @param capitalAssetNumber
     * @param assetPaymentForm
     * @param assetPaymentDocument
     * @param asset
     * @return
     */
    private Asset handleRequestFromLookup(String capitalAssetNumber, AssetPaymentForm assetPaymentForm, AssetPaymentDocument assetPaymentDocument, Asset asset) {
        Asset newAsset = new Asset();
        HashMap<String, Object> keys = new HashMap<String, Object>();
        keys.put(CAPITAL_ASSET_NUMBER, capitalAssetNumber.toString());

        newAsset = (Asset) SpringContext.getBean(BusinessObjectService.class).findByPrimaryKey(Asset.class, keys);
        if (newAsset != null) {
            assetPaymentDocument.setAsset(newAsset);
        }
        return newAsset;
    }
    
        
    /**
     * This action executes a call to upload CSV accounting line values as SourceAccountingLines for a given transactional document.
     * The "uploadAccountingLines()" method handles the multi-part request.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Override
    public ActionForward uploadSourceLines(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
        // call method that sourceform and destination list
        uploadAccountingLines(true, form);

        return mapping.findForward(KFSConstants.MAPPING_BASIC);
    }

    /**
     * This method determines whether we are uploading source or target lines, and then calls uploadAccountingLines directly on the
     * document object. This method handles retrieving the actual upload file as an input stream into the document.
     * 
     * @param isSource
     * @param form
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Override
    protected void uploadAccountingLines(boolean isSource, ActionForm form) throws FileNotFoundException, IOException {
        AssetPaymentForm assetPaymentForm = (AssetPaymentForm) form;
        
        super.uploadAccountingLines(isSource, assetPaymentForm);
    }
}
