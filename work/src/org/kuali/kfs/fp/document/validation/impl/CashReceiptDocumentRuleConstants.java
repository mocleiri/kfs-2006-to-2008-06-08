/*
 * Copyright 2006 The Kuali Foundation.
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
package org.kuali.module.financial.rules;

import org.kuali.kfs.rules.AccountingDocumentRuleBaseConstants;


/**
 * Holds constants for cash receipts document.
 * 
 * 
 */
public interface CashReceiptDocumentRuleConstants extends AccountingDocumentRuleBaseConstants {
    // Security grouping constants used to do application parameter lookups
    public static final String KUALI_TRANSACTION_PROCESSING_CASH_RECEIPT_SECURITY_GROUPING = "Kuali.FinancialTransactionProcessing.CashReceiptDocument";

    // Application parameter lookup constants to be used in conjunction with the grouping constants above
    public static final String RESTRICTED_OBJECT_TYPE_CODES = "RestrictedObjectTypeCodes";
    public static final String RESTRICTED_OBJECT_SUB_TYPE_CODES = "RestrictedObjectSubTypeCodes";
    public static final String RESTRICTED_CONSOLIDATED_OBJECT_CODES = "RestrictedConsolidatedObjectCodes";
}