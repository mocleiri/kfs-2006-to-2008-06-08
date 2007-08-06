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
package org.kuali.module.financial.web.struts.action;

import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.core.authorization.AuthorizationConstants;

import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.document.authorization.DocumentAuthorizer;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.UrlFactory;
import org.kuali.core.web.struts.action.KualiDocumentActionBase;
import org.kuali.core.web.struts.form.KualiDocumentFormBase;
import org.kuali.core.workflow.service.KualiWorkflowDocument;
import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.KFSKeyConstants;
import org.kuali.kfs.KFSConstants.CashDrawerConstants;
import org.kuali.kfs.KFSConstants.DepositConstants;
import org.kuali.kfs.KFSKeyConstants.CashManagement;
import org.kuali.kfs.util.SpringServiceLocator;
import org.kuali.module.financial.bo.Check;
import org.kuali.module.financial.bo.Deposit;
import org.kuali.module.financial.document.CashManagementDocument;
import org.kuali.module.financial.document.CashReceiptDocument;
import org.kuali.module.financial.document.authorization.CashManagementDocumentAuthorizer;
import org.kuali.module.financial.rule.event.AddCheckEvent;
import org.kuali.module.financial.rule.event.DeleteCheckEvent;
import org.kuali.module.financial.service.CashDrawerService;
import org.kuali.module.financial.service.CashManagementService;
import org.kuali.module.financial.web.struts.form.CashManagementForm;
import org.kuali.module.financial.web.struts.form.CashReceiptForm;
import org.kuali.module.financial.web.struts.form.CashManagementForm.CashDrawerSummary;

import edu.iu.uis.eden.exception.WorkflowException;

/**
 * Action class for CashManagementForm
 * 
 * 
 */
public class CashManagementAction extends KualiDocumentActionBase {
    private static Logger LOG = Logger.getLogger(CashManagementAction.class);


    /**
     * Default constructor
     */
    public CashManagementAction() {
    }


    /**
     * Overrides to call super, but also make sure the helpers are populated.
     * 
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#execute(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward dest = super.execute(mapping, form, request, response);

        CashManagementForm cmf = (CashManagementForm) form;
        if (cmf.getDepositHelpers().isEmpty()) {
            cmf.populateDepositHelpers();
        }
        KualiWorkflowDocument kwd = cmf.getDocument().getDocumentHeader().getWorkflowDocument();
        if (kwd.stateIsEnroute() || kwd.stateIsFinal()) {
            cmf.setCashDrawerSummary(null);
        }
        else {
            if (cmf.getCashDrawerSummary() == null) {
                cmf.populateCashDrawerSummary();
            }
        }

        return dest;
    }


    /**
     * Overrides the default document-creation code to auto-save new documents upon creation: since creating a CMDoc changes the
     * CashDrawer's state as a side-effect, we need all CMDocs to be docsearchable so that someone can relocate and use or cancel
     * whatever the current CMDoc is.
     * 
     * @param kualiDocumentFormBase
     * @throws WorkflowException
     */
    @Override
    protected void createDocument(KualiDocumentFormBase kualiDocumentFormBase) throws WorkflowException {
        UniversalUser user = GlobalVariables.getUserSession().getUniversalUser();
        String workgroupName = SpringServiceLocator.getCashReceiptService().getCashReceiptVerificationUnitForUser(user);

        String defaultDescription = SpringServiceLocator.getKualiConfigurationService().getPropertyString(CashManagement.DEFAULT_DOCUMENT_DESCRIPTION);
        defaultDescription = StringUtils.replace(defaultDescription, "{0}", workgroupName);
        defaultDescription = StringUtils.substring(defaultDescription, 0, 39);

        // create doc
        CashManagementDocument cmDoc = SpringServiceLocator.getCashManagementService().createCashManagementDocument(workgroupName, defaultDescription, null);

        // update form
        kualiDocumentFormBase.setDocument(cmDoc);
        kualiDocumentFormBase.setDocTypeName(cmDoc.getDocumentHeader().getWorkflowDocument().getDocumentType());
    }

    private CashManagementDocumentAuthorizer getDocumentAuthorizer() {
        String documentTypeName = SpringServiceLocator.getDataDictionaryService().getDocumentTypeNameByClass(CashManagementDocument.class);
        DocumentAuthorizer documentAuthorizer = SpringServiceLocator.getDocumentAuthorizationService().getDocumentAuthorizer(documentTypeName);

        return (CashManagementDocumentAuthorizer) documentAuthorizer;
    }


    /**
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward addInterimDeposit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CashManagementForm cmForm = (CashManagementForm) form;
        CashManagementDocument cmDoc = cmForm.getCashManagementDocument();

        checkDepositAuthorization(cmDoc, DepositConstants.DEPOSIT_TYPE_INTERIM);

        String wizardUrl = buildDepositWizardUrl(cmDoc, DepositConstants.DEPOSIT_TYPE_INTERIM);
        return new ActionForward(wizardUrl, true);
    }

    /**
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward addFinalDeposit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CashManagementForm cmForm = (CashManagementForm) form;
        CashManagementDocument cmDoc = cmForm.getCashManagementDocument();

        checkDepositAuthorization(cmDoc, DepositConstants.DEPOSIT_TYPE_FINAL);

        String wizardUrl = buildDepositWizardUrl(cmDoc, DepositConstants.DEPOSIT_TYPE_FINAL);
        return new ActionForward(wizardUrl, true);
    }

    /**
     * Throws a DocumentAuthorizationException if the current user is not authorized to add a deposit of the given type to the given
     * document.
     * 
     * @param cmDoc
     * @param depositTypeCode
     */
    private void checkDepositAuthorization(CashManagementDocument cmDoc, String depositTypeCode) {
        // deposits can only be added if the CashDrawer is open
        if (!cmDoc.getCashDrawerStatus().equals(CashDrawerConstants.STATUS_OPEN)) {
            throw new IllegalStateException("CashDrawer '" + cmDoc.getWorkgroupName() + "' must be open for deposits to be made");
        }

        // verify user's ability to add a deposit
        UniversalUser user = GlobalVariables.getUserSession().getUniversalUser();
        Map editModes = getDocumentAuthorizer().getEditMode(cmDoc, user);
        if (!editModes.containsKey(AuthorizationConstants.CashManagementEditMode.ALLOW_ADDITIONAL_DEPOSITS)) {
            throw buildAuthorizationException("add a deposit", cmDoc);
        }
    }

    /**
     * @param cmDoc
     * @param depositTypeCode
     * @return URL for passing control to the DepositWizard
     */
    private String buildDepositWizardUrl(CashManagementDocument cmDoc, String depositTypeCode) {
        Properties params = new Properties();
        params.setProperty("methodToCall", "startWizard");
        params.setProperty("cmDocId", cmDoc.getDocumentNumber());
        params.setProperty("depositTypeCode", depositTypeCode);

        String wizardActionUrl = UrlFactory.parameterizeUrl("depositWizard.do", params);
        return wizardActionUrl;
    }


    /**
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward cancelDeposit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CashManagementForm cmForm = (CashManagementForm) form;
        CashManagementDocument cmDoc = cmForm.getCashManagementDocument();

        // validate cancelability
        int depositIndex = getSelectedLine(request);
        Deposit deposit = cmDoc.getDeposit(depositIndex);
        if (StringUtils.equals(deposit.getDepositTypeCode(), DepositConstants.DEPOSIT_TYPE_INTERIM) && cmDoc.hasFinalDeposit()) {
            throw new IllegalStateException("interim deposits cannot be canceled if the document already has a final deposit");
        }

        // cancel the deposit
        deposit = cmDoc.removeDeposit(depositIndex);
        SpringServiceLocator.getCashManagementService().cancelDeposit(deposit);

        // update the form
        cmForm.removeDepositHelper(depositIndex);

        // display status message
        GlobalVariables.getMessageList().add(CashManagement.STATUS_DEPOSIT_CANCELED);

        return mapping.findForward(KFSConstants.MAPPING_BASIC);
    }


    /**
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#reload(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward dest = super.reload(mapping, form, request, response);

        // refresh the CashDrawerSummary, just in case
        CashManagementForm cmForm = (CashManagementForm) form;
        CashManagementDocument cmDoc = cmForm.getCashManagementDocument();

        CashDrawerSummary cms = cmForm.getCashDrawerSummary();
        if (cms != null) {
            cms.resummarize(cmDoc);
        }

        return dest;
    }


    /**
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward refreshSummary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CashManagementForm cmForm = (CashManagementForm) form;
        CashManagementDocument cmDoc = cmForm.getCashManagementDocument();

        cmForm.getCashDrawerSummary().resummarize(cmDoc);

        return mapping.findForward(KFSConstants.MAPPING_BASIC);
    }


    /**
     * Saves the document, then opens the cash drawer
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward openCashDrawer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CashManagementForm cmForm = (CashManagementForm) form;
        CashManagementDocument cmDoc = cmForm.getCashManagementDocument();

        if (!cmDoc.getDocumentHeader().getWorkflowDocument().stateIsInitiated()) {
            throw new IllegalStateException("openCashDrawer should only be called on documents which haven't yet been saved");
        }

        // open the CashDrawer
        CashDrawerService cds = SpringServiceLocator.getCashDrawerService();
        cds.openCashDrawer(cmDoc.getCashDrawer(), cmDoc.getDocumentNumber());
        // now that the cash drawer is open, let's create currency/coin detail records for this document
        //      create and save the cumulative cash receipt, deposit, money in and money out curr/coin details
        SpringServiceLocator.getCashManagementService().createNewCashDetails(cmDoc, KFSConstants.CurrencyCoinSources.CASH_RECEIPTS);
        SpringServiceLocator.getCashManagementService().createNewCashDetails(cmDoc, KFSConstants.CurrencyCoinSources.CASH_MANAGEMENT_IN);
        SpringServiceLocator.getCashManagementService().createNewCashDetails(cmDoc, KFSConstants.CurrencyCoinSources.CASH_MANAGEMENT_OUT);
        try {
            SpringServiceLocator.getDocumentService().saveDocument(cmDoc);
        }
        catch (WorkflowException e) {
            // force it closed if workflow proves recalcitrant
            cds.closeCashDrawer(cmDoc.getCashDrawer());
            throw e;
        }

        // update the CashDrawerSummary to reflect the change
        cmForm.populateCashDrawerSummary();

        return mapping.findForward(KFSConstants.MAPPING_BASIC);
    }
    
    /**
     * This action makes the last interim deposit a final deposit
     * @param mapping the mapping of the actions
     * @param form the Struts form populated on the post
     * @param request the servlet request
     * @param response the servlet response
     * @return a forward to the same page we were on
     * @throws Exception because you never know when something just might go wrong
     */
    public ActionForward finalizeLastInterimDeposit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CashManagementDocument cmDoc = ((CashManagementForm)form).getCashManagementDocument();
        CashManagementService cms = SpringServiceLocator.getCashManagementService();
        
        if (cmDoc.hasFinalDeposit()) {
            GlobalVariables.getErrorMap().putError(KFSConstants.CASH_MANAGEMENT_DEPOSIT_ERRORS, CashManagement.ERROR_DOCUMENT_ALREADY_HAS_FINAL_DEPOSIT, new String[]{});
        } else if (cmDoc.getDeposits().size() == 0) {
            GlobalVariables.getErrorMap().putError(KFSConstants.CASH_MANAGEMENT_DEPOSIT_ERRORS, CashManagement.ERROR_DOCUMENT_NO_DEPOSITS_TO_MAKE_FINAL, new String[]{});
        } else if (!cms.allVerifiedCashReceiptsAreDeposited(cmDoc)) {
            GlobalVariables.getErrorMap().putError(KFSConstants.CASH_MANAGEMENT_DEPOSIT_ERRORS, CashManagement.ERROR_NON_DEPOSITED_VERIFIED_CASH_RECEIPTS, new String[]{});
        }
        
        cms.finalizeLastInterimDeposit(cmDoc);
        
        return mapping.findForward(KFSConstants.MAPPING_BASIC);
    }
    
    /**
     * This action applies the current cashiering transaction to the cash drawer
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward applyCashieringTransaction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CashManagementDocument cmDoc = ((CashManagementForm)form).getCashManagementDocument();
        CashManagementService cmService = SpringServiceLocator.getCashManagementService();
        
        cmService.applyCashieringTransaction(cmDoc);
        
        return mapping.findForward(KFSConstants.MAPPING_BASIC);
    }
    
    /**
     * This action allows the user to go to the cash drawer correction screen
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward correctCashDrawer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ActionForward("CashDrawerCorrectionForm", buildCashDrawerCorrectionUrl(((CashManagementForm)form).getCashManagementDocument()), true);
    }
    
    /**
     * @param cmDoc
     * @param depositTypeCode
     * @return URL for passing control to the DepositWizard
     */
    private String buildCashDrawerCorrectionUrl(CashManagementDocument cmDoc) {
        Properties params = new Properties();
        params.setProperty("methodToCall", "startCorrections");
        params.setProperty("wrkgrpNm", cmDoc.getWorkgroupName());

        return UrlFactory.parameterizeUrl("cashDrawerCorrection.do", params);
    }
    
    /**
     * Adds Check instance created from the current "new check" line to the document
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward addCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CashManagementDocument cmDoc = ((CashManagementForm)form).getCashManagementDocument();

        Check newCheck = cmDoc.getCurrentTransaction().getNewCheck();
        newCheck.setDocumentNumber(cmDoc.getDocumentNumber());

        // check business rules
        boolean rulePassed = SpringServiceLocator.getKualiRuleService().applyRules(new AddCheckEvent(KFSConstants.NEW_CHECK_PROPERTY_NAME, cmDoc, newCheck));
        if (rulePassed) {
            // add check
            cmDoc.getCurrentTransaction().addCheck(newCheck);

            // clear the used newCheck
            cmDoc.getCurrentTransaction().setNewCheck(cmDoc.getCurrentTransaction().createNewCheck());
        }

        return mapping.findForward(KFSConstants.MAPPING_BASIC);
    }

    /**
     * Deletes the selected check (line) from the document
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward deleteCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CashManagementDocument cmDoc = ((CashManagementForm)form).getCashManagementDocument();

        int deleteIndex = getLineToDelete(request);
        Check oldCheck = cmDoc.getCurrentTransaction().getCheck(deleteIndex);


        boolean rulePassed = SpringServiceLocator.getKualiRuleService().applyRules(new DeleteCheckEvent(KFSConstants.EXISTING_CHECK_PROPERTY_NAME, cmDoc, oldCheck));

        if (rulePassed) {
            // delete check
            cmDoc.getCurrentTransaction().removeCheck(deleteIndex);

            // delete baseline check, if any
            if (cmDoc.getCurrentTransaction().hasBaselineCheck(deleteIndex)) {
                cmDoc.getCurrentTransaction().getBaselineChecks().remove(deleteIndex);
            }
        }
        else {
            GlobalVariables.getErrorMap().putError("document.currentTransaction.check[" + deleteIndex + "]", KFSKeyConstants.Check.ERROR_CHECK_DELETERULE, Integer.toString(deleteIndex));
        }

        return mapping.findForward(KFSConstants.MAPPING_BASIC);
    }

    /**
     * Overridden to clear the CashDrawerSummary info
     * 
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#route(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CashManagementForm cmForm = (CashManagementForm) form;
        CashManagementDocument cmDoc = cmForm.getCashManagementDocument();

        ActionForward dest = super.route(mapping, form, request, response);

        // clear the CashDrawerSummary
        cmForm.setCashDrawerSummary(null);

        return dest;
    }
}