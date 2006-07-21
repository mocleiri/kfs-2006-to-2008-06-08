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
package org.kuali.module.financial.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.Constants;
import org.kuali.core.bo.AccountingLine;
import org.kuali.core.bo.TargetAccountingLine;
import org.kuali.core.document.TransactionalDocument;
import org.kuali.core.rule.event.AddAccountingLineEvent;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.core.util.TypedArrayList;
import org.kuali.core.web.struts.action.KualiTransactionalDocumentActionBase;
import org.kuali.core.web.struts.form.KualiTransactionalDocumentFormBase;
import org.kuali.core.web.uidraw.AccountingLineDecorator;
import org.kuali.module.financial.bo.ProcurementCardTargetAccountingLine;
import org.kuali.module.financial.document.ProcurementCardDocument;
import org.kuali.module.financial.web.struts.form.ProcurementCardForm;

/**
 * This class handles specific Actions requests for the ProcurementCard.
 * 
 * @author Kuali Financial Transactions Team (kualidev@oncourse.iu.edu)
 */
public class ProcurementCardAction extends KualiTransactionalDocumentActionBase {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProcurementCardAction.class);

    /**
     * Override to add the new accounting line to the correct transaction
     * 
     * @see org.kuali.core.web.struts.action.KualiTransactionalDocumentActionBase#insertSourceLine(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward insertTargetLine(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProcurementCardForm procurementCardForm = (ProcurementCardForm) form;

        // get index of new target line
        int newTargetIndex = super.getSelectedLine(request);

        ProcurementCardTargetAccountingLine line = (ProcurementCardTargetAccountingLine) procurementCardForm.getNewTargetLines().get(newTargetIndex);

        // check any business rules
        boolean rulePassed = SpringServiceLocator.getKualiRuleService().applyRules(new AddAccountingLineEvent(Constants.NEW_TARGET_ACCT_LINES_PROPERTY_NAME + "[" + Integer.toString(newTargetIndex) + "]", procurementCardForm.getDocument(), (AccountingLine) line));

        if (rulePassed) {
            // add accountingLine
            SpringServiceLocator.getPersistenceService().retrieveNonKeyFields(line);
            insertAccountingLine(false, procurementCardForm, line);

            // clear the used newTargetIndex
            procurementCardForm.getNewTargetLines().set(newTargetIndex, new ProcurementCardTargetAccountingLine());
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }


    /**
     * Override to resync base accounting lines. New lines on the PCDO document can be inserted anywhere in the list, not necessary
     * at the end.
     * 
     * @see org.kuali.core.web.struts.action.KualiTransactionalDocumentActionBase#insertAccountingLine(boolean,
     *      org.kuali.core.web.struts.form.KualiTransactionalDocumentFormBase, org.kuali.core.bo.AccountingLine)
     */
    protected void insertAccountingLine(boolean isSource, KualiTransactionalDocumentFormBase transactionalDocumentForm, AccountingLine line) {
        TransactionalDocument tdoc = transactionalDocumentForm.getTransactionalDocument();

        // create and init a decorator
        AccountingLineDecorator decorator = new AccountingLineDecorator();
        decorator.setRevertible(false);

        // add it to the document
        tdoc.addTargetAccountingLine((TargetAccountingLine) line);

        // get the index of the inserted line
        int newLineIndex = tdoc.getTargetAccountingLines().indexOf(line);

        // add it to the baseline, to prevent generation of spurious update events
        transactionalDocumentForm.getBaselineTargetAccountingLines().add(newLineIndex, line);

        // add the decorator
        transactionalDocumentForm.getTargetLineDecorators().add(newLineIndex, decorator);
    }

    /**
     * Override to remove the accounting line from the correct transaction
     * 
     * @see org.kuali.core.web.struts.action.KualiTransactionalDocumentActionBase#deleteAccountingLine(boolean,
     *      org.kuali.core.web.struts.form.KualiTransactionalDocumentFormBase, int)
     */
    protected void deleteAccountingLine(boolean isSource, KualiTransactionalDocumentFormBase transactionalDocumentForm, int deleteIndex) {
        ProcurementCardDocument procurementCardDocument = (ProcurementCardDocument) transactionalDocumentForm.getDocument();
        procurementCardDocument.removeTargetAccountingLine(deleteIndex);

        // remove baseline duplicate and decorator
        transactionalDocumentForm.getBaselineTargetAccountingLines().remove(deleteIndex);
        transactionalDocumentForm.getTargetLineDecorators().remove(deleteIndex);
    }
    
    /**
     * Ensures that ProcurementCardForm.newTargetLines is cleared. Otherwise works like super.reload.
     * 
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#reload(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProcurementCardForm procurementCardForm = (ProcurementCardForm) form;
        procurementCardForm.setNewTargetLines(new TypedArrayList(ProcurementCardTargetAccountingLine.class));
        
        return super.reload(mapping, procurementCardForm, request, response);
    }
}