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

import org.kuali.module.ar.bo.AccountsReceivableDocumentHeader;

public interface AccountsReceivableDocumentHeaderService {
    
    /**
     * This method returns a new AccountsReceivableDocumentHeader for a specific chart and organization code
     * @param chartOfAccountsCode
     * @param organizationCode
     * @return
     */
    public AccountsReceivableDocumentHeader getNewAccountsReceivableDocumentHeader(String chartOfAccountsCode, String organizationCode);

    /**
     * This method returns a new AccountsReceivableDocumentHeader based on the current user.
     * @return
     */
    public AccountsReceivableDocumentHeader getNewAccountsReceivableDocumentHeaderForCurrentUser();
}
