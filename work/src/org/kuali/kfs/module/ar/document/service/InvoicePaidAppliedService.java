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
package org.kuali.module.ar.service;

import java.util.List;

import org.kuali.module.ar.bo.CustomerInvoiceDetail;
import org.kuali.module.ar.document.CustomerInvoiceDocument;

public interface InvoicePaidAppliedService {
    
    
    /**
     * This method persists the corresponding invoice paid applied rows for discounts when an invoice is created
     * @param customerInvoiceDetails
     */
    public void saveInvoicePaidAppliedForDiscounts(List<CustomerInvoiceDetail> customerInvoiceDetails, CustomerInvoiceDocument document);
    
    
    /**
     * This method returns true if invoice has applied amounts (i.e. from application, credit memo, etc), not including
     * discounts
     * 
     * @param document
     * @return
     */
    public boolean doesInvoiceHaveAppliedAmounts(CustomerInvoiceDocument document);

}