/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/test/unit/src/org/kuali/kfs/coa/service/AccountServiceTest.java,v $
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
package org.kuali.module.chart.service;

import org.kuali.core.util.SpringServiceLocator;
import org.kuali.module.chart.bo.Account;
import org.kuali.test.KualiTestBase;
import org.kuali.test.WithTestSpringContext;

/**
 * This class tests the Account service.
 * 
 * 
 */
@WithTestSpringContext
public class AccountServiceTest extends KualiTestBase {
    private AccountService accountService;

    protected void setUp() throws Exception {
        super.setUp();
        accountService = SpringServiceLocator.getAccountService();
    }

    public void testValidateAccount() {
        Account account = null;
        account = accountService.getByPrimaryId("BA", "6044900");
        assertNotNull(account);
        // assertNotNull(account.getSubAccounts());
        // assertEquals("sub account list should contain 27 subaccounts", 27, account.getSubAccounts().size());

        account = null;
        account = accountService.getByPrimaryId("XX", "0000000");
        assertNull(account);

        account = null;
        account = accountService.getByPrimaryId("KO", "");
        assertNull(account);

        account = null;
        account = accountService.getByPrimaryId("UA", null);
        assertNull(account);

        account = null;
        account = accountService.getByPrimaryId(null, "1912610");
        assertNull(account);

        account = null;
        account = accountService.getByPrimaryId(null, null);
        assertNull(account);
    }


}
