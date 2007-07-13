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
package org.kuali.module.purap.web.struts.form;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.ObjectUtils;
import org.kuali.core.util.TypedArrayList;
import org.kuali.core.web.ui.ExtraButton;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.util.SpringServiceLocator;
import org.kuali.module.purap.PurapAuthorizationConstants;
import org.kuali.module.purap.PurapConstants;
import org.kuali.module.purap.bo.PaymentRequestAccount;
import org.kuali.module.purap.bo.PurchaseOrderAccount;
import org.kuali.module.purap.bo.PurchaseOrderItem;
import org.kuali.module.purap.bo.PurchaseOrderVendorStipulation;
import org.kuali.module.purap.bo.PurchasingApItem;
import org.kuali.module.purap.document.CreditMemoDocument;
import org.kuali.module.purap.document.PaymentRequestDocument;

/**
 * This class is the form class for the PaymentRequest document.
 */
public class PaymentRequestForm extends AccountsPayableFormBase {

    private PurchaseOrderVendorStipulation newPurchaseOrderVendorStipulationLine;
    /**
     * Constructs a PurchaseOrderForm instance and sets up the appropriately casted document. 
     */
    public PaymentRequestForm() {
        super();
        setDocument(new PaymentRequestDocument());
        this.setNewPurchasingItemLine(setupNewPurchasingItemLine());
        setNewPurchaseOrderVendorStipulationLine(new PurchaseOrderVendorStipulation());
    }

    /**
     * @return Returns the internalBillingDocument.
     */
    public PaymentRequestDocument getPaymentRequestDocument() {
        return (PaymentRequestDocument) getDocument();
    }

    /**
     * @param internalBillingDocument The internalBillingDocument to set.
     */
    public void setPaymentRequestDocument(PaymentRequestDocument purchaseOrderDocument) {
        setDocument(purchaseOrderDocument);
    }

    /**
     * @see org.kuali.core.web.struts.form.KualiForm#getAdditionalDocInfo1()
     */
    public KeyLabelPair getAdditionalDocInfo1() {
        if (ObjectUtils.isNotNull(this.getPaymentRequestDocument().getPurapDocumentIdentifier())) {
            return new KeyLabelPair("DataDictionary.PaymentRequestDocument.attributes.purapDocumentIdentifier", ((PaymentRequestDocument)this.getDocument()).getPurapDocumentIdentifier().toString());
        } else {
            return new KeyLabelPair("DataDictionary.PaymentRequestDocument.attributes.purapDocumentIdentifier", "Not Available");
        }
    }

    /**
     * @see org.kuali.core.web.struts.form.KualiForm#getAdditionalDocInfo2()
     */
    public KeyLabelPair getAdditionalDocInfo2() {
        if (ObjectUtils.isNotNull(this.getPaymentRequestDocument().getStatus())) {
            return new KeyLabelPair("DataDictionary.PaymentRequestDocument.attributes.statusCode", ((PaymentRequestDocument)this.getDocument()).getStatus().getStatusDescription());
        } else {
            return new KeyLabelPair("DataDictionary.PaymentRequestDocument.attributes.statusCode", "Not Available");
        }
    }

    /**
     * @see org.kuali.module.purap.web.struts.form.PurchasingFormBase#setupNewPurchasingItemLine()
     */
    @Override
    public PurchasingApItem setupNewPurchasingItemLine() {
        return new PurchaseOrderItem();
    }

    public PurchaseOrderVendorStipulation getAndResetNewPurchaseOrderVendorStipulationLine() {
        PurchaseOrderVendorStipulation aPurchaseOrderVendorStipulationLine = getNewPurchaseOrderVendorStipulationLine();
        setNewPurchaseOrderVendorStipulationLine(new PurchaseOrderVendorStipulation());
    
       // aPurchaseOrderVendorStipulationLine.setDocumentNumber(getPurchaseOrderDocument().getDocumentNumber());
        aPurchaseOrderVendorStipulationLine.setVendorStipulationAuthorEmployeeIdentifier(GlobalVariables.getUserSession().getUniversalUser().getPersonUniversalIdentifier());
        aPurchaseOrderVendorStipulationLine.setVendorStipulationCreateDate(SpringServiceLocator.getDateTimeService().getCurrentSqlDate());

        return aPurchaseOrderVendorStipulationLine;
}

    public PurchaseOrderVendorStipulation getNewPurchaseOrderVendorStipulationLine() {
        return newPurchaseOrderVendorStipulationLine;
    }

    public void setNewPurchaseOrderVendorStipulationLine(PurchaseOrderVendorStipulation newPurchaseOrderVendorStipulationLine) {
        this.newPurchaseOrderVendorStipulationLine = newPurchaseOrderVendorStipulationLine;
    }
    
    /**
     * Gets the initialized attribute. 
     * @return Returns the initialized.
     */
   /*
    public boolean isInitialized() {
        return initialized;
    }
*/
   
    /**
     * Gets the PaymentRequestInitiated attribute for JSP 
     * @return Returns the DisplayInitiateTab.
     */
  
    public boolean isPaymentRequestInitiated() { 
        return StringUtils.equals(this.getPaymentRequestDocument().getStatusCode(),PurapConstants.PaymentRequestStatuses.INITIATE);
      } 

    
    /**
     * Build additional credit memo specific buttons and set extraButtons list.
     */
    @Override
    public List<ExtraButton> getExtraButtons() {
        // clear out the extra buttons array
        extraButtons.clear();

        PaymentRequestDocument preqDoc = this.getPaymentRequestDocument();

        String externalImageURL = SpringServiceLocator.getKualiConfigurationService().getPropertyString(KFSConstants.RICE_EXTERNALIZABLE_IMAGES_URL_KEY);
        String appExternalImageURL = SpringServiceLocator.getKualiConfigurationService().getPropertyString(KFSConstants.EXTERNALIZABLE_IMAGES_URL_KEY);

        if (this.getEditingMode().containsKey(PurapAuthorizationConstants.PaymentRequestEditMode.DISPLAY_INIT_TAB)) {
            if (this.getEditingMode().get(PurapAuthorizationConstants.PaymentRequestEditMode.DISPLAY_INIT_TAB).equals("TRUE")) {
                addExtraButton("methodToCall.continuePREQ", externalImageURL + "buttonsmall_continue.gif", "Continue");
                addExtraButton("methodToCall.clearInitFields", externalImageURL + "buttonsmall_clear.gif", "Clear");

            }
            else {

                //if preq holdable and user can put on hold, show button
                if (SpringServiceLocator.getPaymentRequestService().isPaymentRequestHoldable(preqDoc)) {
                    if (SpringServiceLocator.getPaymentRequestService().canHoldPaymentRequest(preqDoc, GlobalVariables.getUserSession().getUniversalUser())) {
                        addExtraButton("methodToCall.addHoldOnPayment", appExternalImageURL + "buttonsmall_hold.gif", "Hold");
                    }

                }
                else {
                    //if person can remove hold
                    if (SpringServiceLocator.getPaymentRequestService().canRemoveHoldPaymentRequest(preqDoc, GlobalVariables.getUserSession().getUniversalUser())) {
                        addExtraButton("methodToCall.removeHoldFromPayment", appExternalImageURL + "buttonsmall_removehold.gif", "Remove");
                    }
                }

                //if preq can have a cancel request and user can submit request cancel, show button
                if (SpringServiceLocator.getPaymentRequestService().canRequestCancelOnPaymentRequest(preqDoc)) {
                    if (SpringServiceLocator.getPaymentRequestService().canUserRequestCancelOnPaymentRequest(preqDoc, GlobalVariables.getUserSession().getUniversalUser())) {
                        addExtraButton("methodToCall.requestCancelOnPayment", appExternalImageURL + "buttonsmall_requestcancel.gif", "Cancel");
                    }
                }
                else {
                    //if person can remove request cancel
                    if (SpringServiceLocator.getPaymentRequestService().canUserRemoveRequestCancelOnPaymentRequest(preqDoc, GlobalVariables.getUserSession().getUniversalUser())) {
                        addExtraButton("methodToCall.removeCancelRequestFromPayment", appExternalImageURL + "buttonsmall_remreqcanc.gif", "Remove");
                    }
                }

                //add the calcuate button
                addExtraButton("methodToCall.calculate", appExternalImageURL + "buttonsmall_calculate.gif", "Calculate");
            }
        }

        return extraButtons;
    }
    
 
}