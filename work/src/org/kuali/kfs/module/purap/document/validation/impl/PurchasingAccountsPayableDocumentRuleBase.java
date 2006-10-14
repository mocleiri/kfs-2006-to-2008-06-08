/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/work/src/org/kuali/kfs/module/purap/document/validation/impl/PurchasingAccountsPayableDocumentRuleBase.java,v $
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

import org.kuali.core.bo.AccountingLine;
import org.kuali.core.document.TransactionalDocument;
import org.kuali.module.financial.rules.TransactionalDocumentRuleBase;

public class PurchasingAccountsPayableDocumentRuleBase extends TransactionalDocumentRuleBase {

    /*
     * THIS IS BAD...WE DO NOT NEED THIS METHOD AND SHOULD NOT HAVE TO IMPLEMENT IT
     */
    public boolean isDebit(TransactionalDocument transactionalDocument, AccountingLine accountingLine) {
        // TODO Auto-generated method stub
        return false;
    }

}
