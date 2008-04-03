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
package org.kuali.module.purap.batch;

import java.util.List;

import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kfs.batch.AbstractStep;
import org.kuali.kfs.service.ParameterService;
import org.kuali.module.purap.PurapParameterConstants;
import org.kuali.module.purap.document.PaymentRequestDocument;
import org.kuali.module.purap.service.PaymentRequestService;

/**
 * Step used to auto approve payment requests that meet a certain criteria
 * 
 * DO NOT ANNOTATE THIS CLASS AS TRANSACTIONAL.  Otherwise, all documents will run within the same transaction, and if
 * a RuntimeException is thrown, then it will cause the transaction for all documents to rollback, causing no requests to auto-approve
 */
public class AutoApprovePaymentRequestsStep extends AbstractStep {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(AutoApprovePaymentRequestsStep.class);

    private PaymentRequestService paymentRequestService;
    private ParameterService parameterService;

    public AutoApprovePaymentRequestsStep() {
        super();
    }

    /**
     * Calls service method to approve payment requests
     * 
     * @see org.kuali.kfs.batch.Step#execute(java.lang.String)
     */
    public boolean execute(String jobName) throws InterruptedException {
        boolean hasNoErrors = true;
        // should objects from existing user session be copied over
        List<String> autoApprovableDocumentNumbers = paymentRequestService.getPaymentRequestsDocumentNumberEligibleForAutoApproval();
        if (autoApprovableDocumentNumbers != null) {
            String samt = parameterService.getParameterValue(PaymentRequestDocument.class, PurapParameterConstants.PURAP_DEFAULT_NEGATIVE_PAYMENT_REQUEST_APPROVAL_LIMIT);
            KualiDecimal defaultMinimumLimit = new KualiDecimal(samt);
            for (String documentNumber : autoApprovableDocumentNumbers) {
                try {
                    LOG.info("Attempting to auto-approve payment request: " + documentNumber);
                    GlobalVariables.setErrorMap(new ErrorMap());
                    // the service call starts and ends its own transaction since this class is not transactional, so it's one transaction per document
                    if (!paymentRequestService.autoApprovePaymentRequest(documentNumber, defaultMinimumLimit)) {
                        hasNoErrors = false;
                    }
                }
                catch (Exception e) {
                    hasNoErrors = false;
                    LOG.error("Payment Request Document " + documentNumber + " threw an exception during auto-approval.  Transaction rolled back.", e);
                }
            }
        }
        return hasNoErrors;
    }

    /**
     * Invoke execute method
     * 
     * @return
     * @throws InterruptedException
     */
    public boolean execute() throws InterruptedException {
        return execute(null);
    }

    public void setPaymentRequestService(PaymentRequestService paymentRequestService) {
        this.paymentRequestService = paymentRequestService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

}
