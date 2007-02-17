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
package org.kuali.module.financial.rules;

import org.kuali.kfs.rules.AccountingDocumentRuleBaseConstants;


/**
 * Holds constants for <code>{@link org.kuali.module.financial.document.GeneralErrorCorrectionDocument}</code> business rules.
 * 
 * 
 */
public interface GeneralErrorCorrectionDocumentRuleConstants extends AccountingDocumentRuleBaseConstants {
    // Security grouping constants used to do application parameter lookups
    public static final String GENERAL_ERROR_CORRECTION_SECURITY_GROUPING = "Kuali.FinancialTransactionProcessing.GeneralErrorCorrectionDocument";

    // Application parameter lookup constants to be used in conjunction with the grouping constants above
    public static final String RESTRICTED_OBJECT_TYPE_CODES = "RestrictedObjectTypeCodes";
    public static final String RESTRICTED_OBJECT_SUB_TYPE_CODES = "RestrictedObjectSubTypeCodes";
    public static final String COMBINED_RESTRICTED_OBJECT_TYPE_CODES = "CombinedRestrictedObjectTypeCodes";
    public static final String COMBINED_RESTRICTED_OBJECT_SUB_TYPE_CODES = "CombinedRestrictedObjectSubTypeCodes";

    public static final String TRANSACTION_LEDGER_ENTRY_DESCRIPTION_DELIMITER = "+";
}
