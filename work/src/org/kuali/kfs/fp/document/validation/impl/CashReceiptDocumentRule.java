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
package org.kuali.module.financial.rules;

import org.kuali.KeyConstants;
import org.kuali.PropertyConstants;
import org.kuali.core.document.Document;
import org.kuali.core.document.TransactionalDocument;
import org.kuali.core.rule.AddCheckRule;
import org.kuali.core.rule.DeleteCheckRule;
import org.kuali.core.rule.UpdateCheckRule;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.core.workflow.service.KualiWorkflowDocument;
import org.kuali.module.financial.bo.Check;
import org.kuali.module.financial.document.CashReceiptDocument;
import org.kuali.module.financial.document.CashReceiptFamilyBase;


/**
 * Business rule(s) applicable to CashReceipt documents.
 * 
 * @author Kuali Financial Transactions Team (kualidev@oncourse.iu.edu)
 */
public class CashReceiptDocumentRule extends CashReceiptFamilyRule implements AddCheckRule, DeleteCheckRule, UpdateCheckRule {
    /**
     * Implements Cash Receipt specific rule checks for the cash reconciliation section, to make sure that the cash, check, and coin
     * totals are not negative.
     * 
     * @see org.kuali.core.rule.DocumentRuleBase#processCustomSaveDocumentBusinessRules(org.kuali.core.document.Document)
     */
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        boolean isValid = super.processCustomSaveDocumentBusinessRules(document);

        if (isValid) {
            isValid &= validateAccountingLineTotal((CashReceiptFamilyBase) document);
            isValid &= !CashReceiptDocumentRuleUtil.areCashTotalsInvalid((CashReceiptDocument) document);
        }

        return isValid;
    }

    /**
     * Checks to make sure that the check passed in passes all data dictionary validation and that the amount is positive.
     * 
     * @see org.kuali.core.rule.AddCheckRule#processAddCheckBusinessRules(org.kuali.core.document.TransactionalDocument,
     *      org.kuali.module.financial.bo.Check)
     */
    public boolean processAddCheckBusinessRules(TransactionalDocument transactionalDocument, Check check) {
        boolean isValid = validateCheck(check);

        return isValid;
    }

    /**
     * Default implementation does nothing now.
     * 
     * @see org.kuali.core.rule.DeleteCheckRule#processDeleteCheckBusinessRules(org.kuali.core.document.TransactionalDocument,
     *      org.kuali.module.financial.bo.Check)
     */
    public boolean processDeleteCheckBusinessRules(TransactionalDocument transactionalDocument, Check check) {
        boolean processed = true;

        return processed;
    }

    /**
     * Checks to make sure that the check passed in passes all data dictionary validation and that the amount is positive.
     * 
     * @see org.kuali.core.rule.UpdateCheckRule#processUpdateCheckRule(org.kuali.core.document.TransactionalDocument,
     *      org.kuali.module.financial.bo.Check)
     */
    public boolean processUpdateCheckRule(TransactionalDocument transactionalDocument, Check check) {
        boolean isValid = validateCheck(check);

        return isValid;
    }

    /**
     * This method validates checks for a CR document.
     * 
     * @param check
     * @return boolean
     */
    private boolean validateCheck(Check check) {
        // validate the specific check coming in
        SpringServiceLocator.getDictionaryValidationService().validateBusinessObject(check);

        boolean isValid = GlobalVariables.getErrorMap().isEmpty();

        // check to make sure the amount is also valid
        if (check.getAmount().isZero()) {
            GlobalVariables.getErrorMap().putError(PropertyConstants.CHECK_AMOUNT, KeyConstants.CashReceipt.ERROR_ZERO_CHECK_AMOUNT, PropertyConstants.CHECKS);
            isValid = false;
        }
        else if (check.getAmount().isNegative()) {
            GlobalVariables.getErrorMap().putError(PropertyConstants.CHECK_AMOUNT, KeyConstants.CashReceipt.ERROR_NEGATIVE_CHECK_AMOUNT, PropertyConstants.CHECKS);
            isValid = false;
        }

        return isValid;
    }

    /**
     * Method used by <code>{@link org.kuali.module.financial.service.CashReceiptCoverSheetService}</code> to determine of the
     * <code>{@link CashReceiptDocument}</code> validates business rules for generating a cover page. <br/> <br/> Rule is the
     * <code>{@link Document}</code> must be ENROUTE.
     * 
     * @param document
     * @return boolean
     */
    public boolean isCoverSheetPrintable(CashReceiptFamilyBase document) {
        KualiWorkflowDocument workflowDocument = document.getDocumentHeader().getWorkflowDocument();
        return !(workflowDocument.stateIsCanceled() || workflowDocument.stateIsInitiated() || workflowDocument.stateIsDisapproved() || workflowDocument.stateIsException() || workflowDocument.stateIsDisapproved() || workflowDocument.stateIsSaved());
    }
}
