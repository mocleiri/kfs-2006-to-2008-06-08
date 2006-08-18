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

import java.util.List;

import org.kuali.core.bo.AccountingLine;
import org.kuali.core.document.TransactionalDocument;
import org.kuali.core.web.struts.action.KualiTransactionalDocumentActionBase;
import org.kuali.core.web.struts.form.KualiTransactionalDocumentFormBase;
import org.kuali.core.web.uidraw.AccountingLineDecorator;

/**
 * This class handles Actions for <ocde>IndirectCostAdjustmentDocument</code>s
 * 
 * @author Kuali Financial Transactions Team (kualidev@oncourse.iu.edu)
 */
public class IndirectCostAdjustmentAction extends KualiTransactionalDocumentActionBase {
/**
 * added target line baseline creation for lines created by source add
 * @see org.kuali.core.web.struts.action.KualiTransactionalDocumentActionBase#insertAccountingLine(boolean, org.kuali.core.web.struts.form.KualiTransactionalDocumentFormBase, org.kuali.core.bo.AccountingLine)
 */
    @Override
    protected void insertAccountingLine(boolean isSource, KualiTransactionalDocumentFormBase transactionalDocumentForm, AccountingLine line) {
        super.insertAccountingLine(isSource, transactionalDocumentForm, line);
        if (isSource) {
            AccountingLineDecorator decorator = new AccountingLineDecorator();
            decorator.setRevertible(false);
            // add it to the baseline, to prevent generation of spurious update events
            TransactionalDocument tDoc = (TransactionalDocument) transactionalDocumentForm.getDocument();
            List targetLines = tDoc.getTargetAccountingLines();
            transactionalDocumentForm.getBaselineTargetAccountingLines().add(targetLines.get(targetLines.size() - 1));

            // add the decorator
            transactionalDocumentForm.getTargetLineDecorators().add(decorator);
        }
    }
}