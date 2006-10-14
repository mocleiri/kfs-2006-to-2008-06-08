/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/test/unit/src/org/kuali/kfs/fp/document/service/AccountPresenceServiceTest.java,v $
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
package org.kuali.module.financial.service;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.test.KualiTestBase;
import org.kuali.test.WithTestSpringContext;
import static org.kuali.test.fixtures.AccountFixture.ACCOUNT_NON_PRESENCE_ACCOUNT;
import static org.kuali.test.fixtures.AccountFixture.ACCOUNT_PRESENCE_ACCOUNT;
import static org.kuali.test.fixtures.ObjectCodeFixture.OBJECT_CODE_BUDGETED_OBJECT_CODE;
import static org.kuali.test.fixtures.ObjectCodeFixture.OBJECT_CODE_NON_BUDGET_OBJECT_CODE;

/**
 * This class tests the AccountPresenceService.
 * 
 * 
 */
@WithTestSpringContext
public class AccountPresenceServiceTest extends KualiTestBase {
    private AccountPresenceService accountPresenceService;
    private BusinessObjectService businessObjectService;

    protected void setUp() throws Exception {
        super.setUp();

        accountPresenceService = SpringServiceLocator.getAccountPresenceService();
        businessObjectService = SpringServiceLocator.getBusinessObjectService();
    }


    /**
     * Tests non budgeted object code for account with presence control fails service method.
     * 
     * @throws Exception
     */
    public void testAccountPresenceNonBudgetedObject() throws Exception {
        assertFalse("Non budgeded object code passed ", accountPresenceService.isObjectCodeBudgetedForAccountPresence(ACCOUNT_PRESENCE_ACCOUNT.createAccount(businessObjectService), OBJECT_CODE_NON_BUDGET_OBJECT_CODE.createObjectCode(businessObjectService)));

    }

    /**
     * Tests budgeted object code for account with presence control passes service method.
     * 
     * @throws Exception
     */
    // public void testAccountPresenceBudgetedObject() throws Exception {
    // assertTrue("Budgeted object code failed ",
    // accountPresenceService.isObjectCodeBudgetedForAccountPresence(getAccountWithPresenceControl(),
    // getBudgetedObjectCode()));
    //
    // }
    /**
     * Tests non budgeted object code passes for account without presence control.
     * 
     * @throws Exception
     */
    public void testAccountNonPresenceNonBudgetedObject() throws Exception {
        assertTrue("non budgeted object code failed on account without presence control ", accountPresenceService.isObjectCodeBudgetedForAccountPresence(ACCOUNT_NON_PRESENCE_ACCOUNT.createAccount(businessObjectService), OBJECT_CODE_NON_BUDGET_OBJECT_CODE.createObjectCode(businessObjectService)));

    }

    /**
     * Tests budgeted object code passes for account without presence control.
     * 
     * @throws Exception
     */
    public void testAccountNonPresenceBudgetedObject() throws Exception {
        assertTrue("budgeted object code failed on account without presence control ", accountPresenceService.isObjectCodeBudgetedForAccountPresence(ACCOUNT_NON_PRESENCE_ACCOUNT.createAccount(businessObjectService), OBJECT_CODE_BUDGETED_OBJECT_CODE.createObjectCode(businessObjectService)));

    }
}
