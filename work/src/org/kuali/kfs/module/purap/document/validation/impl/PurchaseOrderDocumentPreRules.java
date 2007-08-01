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
package org.kuali.module.purap.rules;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.document.AmountTotaling;
import org.kuali.core.document.Document;
import org.kuali.core.rules.PreRulesContinuationBase;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.util.SpringServiceLocator;
import org.kuali.module.kra.KraConstants;
import org.kuali.module.kra.KraKeyConstants;

import org.kuali.module.purap.PurapConstants;
import org.kuali.module.purap.PurapKeyConstants;
import org.kuali.module.purap.document.PurchaseOrderDocument;
import org.kuali.module.purap.document.PurchasingDocument;

/**
 * @see org.kuali.module.kra.budget.rules.budget.BudgetDocumentPreRules
 */
public class PurchaseOrderDocumentPreRules extends PreRulesContinuationBase {

    @Override
    public boolean doRules(Document document) {
        
        boolean preRulesOK = true;
        
        PurchaseOrderDocument purchaseOrderDocument = (PurchaseOrderDocument)document;
        
        if (StringUtils.isBlank(event.getQuestionContext()) || StringUtils.equals(question, PurapConstants.PO_OVERRIDE_NOT_TO_EXCEED_QUESTION)) {
            preRulesOK = confirmNotToExceedOverride(purchaseOrderDocument);
        }
        
        return preRulesOK;
    }
    
    /**
     * This method checks whether the 'Not-to-exceed' amount has been exceeded by the PO TotalDollarAmount.  If so, it propts the user
     * for confirmation.
     *  
     * @param   purchaseOrderDocument   The current PurchaseOrderDocument
     * @return  True if the 'Not-to-exceed' amount is to be overridden
     */
    private boolean confirmNotToExceedOverride(PurchaseOrderDocument purchaseOrderDocument) {
        
        // If the total exceeds the limit, ask for confirmation.
        if (!validateTotalDollarAmountIsLessThanPurchaseOrderTotalLimit(purchaseOrderDocument)) {
            String questionText = SpringServiceLocator.getKualiConfigurationService().getPropertyString(
                    PurapKeyConstants.PURCHASE_ORDER_QUESTION_OVERRIDE_NOT_TO_EXCEED);
        
            boolean confirmOverride = super.askOrAnalyzeYesNoQuestion(PurapConstants.PO_OVERRIDE_NOT_TO_EXCEED_QUESTION, questionText);
            
            // Set a marker to record that this method has been used.
            if (StringUtils.isBlank(event.getQuestionContext())) {
                event.setQuestionContext(PurapConstants.PO_OVERRIDE_NOT_TO_EXCEED_QUESTION);
            }
        
            if (!confirmOverride) {
                event.setActionForwardName(KFSConstants.MAPPING_BASIC);
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Validate that if the PurchaseOrderTotalLimit is not null then the TotalDollarAmount cannot be greater than the
     * PurchaseOrderTotalLimit.
     * 
     * @return True if the TotalDollarAmount is less than the PurchaseOrderTotalLimit. False otherwise.
     */
    public boolean validateTotalDollarAmountIsLessThanPurchaseOrderTotalLimit(PurchasingDocument purDocument) {
        boolean valid = true;
        if (ObjectUtils.isNotNull(purDocument.getPurchaseOrderTotalLimit()) && ObjectUtils.isNotNull(((AmountTotaling) purDocument).getTotalDollarAmount())) {
            KualiDecimal totalAmount = ((AmountTotaling) purDocument).getTotalDollarAmount();
            if (((AmountTotaling) purDocument).getTotalDollarAmount().isGreaterThan(purDocument.getPurchaseOrderTotalLimit())) {
                valid &= false;
                GlobalVariables.getMessageList().add(PurapKeyConstants.PO_TOTAL_GREATER_THAN_PO_TOTAL_LIMIT);
            }
        }
        return valid;
    }

}
