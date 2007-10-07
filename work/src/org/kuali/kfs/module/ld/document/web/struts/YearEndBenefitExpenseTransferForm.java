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
package org.kuali.module.labor.web.struts.form;

import org.kuali.module.labor.document.YearEndBenefitExpenseTransferDocument;

/**
 * This class is the form class for the Labor Year End Benefit Expense Transfer document.
 */
public class YearEndBenefitExpenseTransferForm extends BenefitExpenseTransferForm {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(YearEndBenefitExpenseTransferForm.class);

    /**
     * Constructs a BenefitExpenseTransferForm instance and sets up the appropriately casted document.
     */
    public YearEndBenefitExpenseTransferForm() {
        
        super();
        setDocument(new YearEndBenefitExpenseTransferDocument());
    }
}
