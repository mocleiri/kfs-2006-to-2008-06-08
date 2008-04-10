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
package org.kuali.module.cams.service;

import org.kuali.core.util.KualiDecimal;
import org.kuali.module.cams.bo.Asset;

public interface PaymentSummaryService {
    /**
     * @param asset Asset that needs Payment Summary Information computed
     *        <ol>
     *        Computes following information and sets them to asset object
     *        <li>Federal contribution amount</li>
     *        <li>Payments till date</li>
     *        <li>Total Cost of Asset</li>
     *        <li>Accumulated Depreciation</li>
     *        <li>Primary Base Amount</li>
     *        <li>Book Value</li>
     *        <li>Previous Year Depreciation</li>
     *        <li>Year to Date Depreciation</li>
     *        <li>Current month depreciation</li>
     *        </ol>
     */
    void calculateAndSetPaymentSummary(Asset asset);

    /**
     * Sums up federal contribution amount for an asset
     * 
     * @param asset Asset
     * @return Federal Contribution Amount
     */
    KualiDecimal calculateFederalContribution(Asset asset);
}
