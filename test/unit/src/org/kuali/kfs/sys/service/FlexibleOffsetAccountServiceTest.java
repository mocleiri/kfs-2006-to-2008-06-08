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
package org.kuali.module.financial.service;

import java.lang.reflect.InvocationTargetException;

import org.kuali.core.util.SpringServiceLocator;
import org.kuali.module.financial.bo.OffsetAccount;
import org.kuali.test.KualiTestBase;
import static org.kuali.test.MockServiceUtils.mockConfigurationServiceForFlexibleOffsetEnabled;
import org.kuali.test.WithTestSpringContext;
import static org.kuali.test.fixtures.OffsetAccountFixture.OFFSET_ACCOUNT1;

/**
 * This class...
 * 
 * 
 */
@WithTestSpringContext
public class FlexibleOffsetAccountServiceTest extends KualiTestBase {
    private FlexibleOffsetAccountService service;

    protected void setUp() throws Exception {
        super.setUp();
        service = SpringServiceLocator.getFlexibleOffsetAccountService();
    }

    public void testGetByPrimaryId_valid() throws NoSuchMethodException, InvocationTargetException {
        mockConfigurationServiceForFlexibleOffsetEnabled(true);
        OffsetAccount offsetAccount = service.getByPrimaryIdIfEnabled(OFFSET_ACCOUNT1.chartOfAccountsCode, OFFSET_ACCOUNT1.accountNumber, OFFSET_ACCOUNT1.financialOffsetObjectCode);
        assertSparselyEqualBean(OFFSET_ACCOUNT1.createOffsetAccount(), offsetAccount);
        assertEquals(OFFSET_ACCOUNT1.chartOfAccountsCode, offsetAccount.getChart().getChartOfAccountsCode());
        assertEquals(OFFSET_ACCOUNT1.accountNumber, offsetAccount.getAccount().getAccountNumber());
        assertEquals(OFFSET_ACCOUNT1.financialOffsetChartOfAccountCode, offsetAccount.getFinancialOffsetChartOfAccount().getChartOfAccountsCode());
        assertEquals(OFFSET_ACCOUNT1.financialOffsetAccountNumber, offsetAccount.getFinancialOffsetAccount().getAccountNumber());
    }

    public void testGetByPrimaryId_validDisabled() throws NoSuchMethodException, InvocationTargetException {
        mockConfigurationServiceForFlexibleOffsetEnabled(false);
        assertNull(service.getByPrimaryIdIfEnabled(OFFSET_ACCOUNT1.chartOfAccountsCode, OFFSET_ACCOUNT1.accountNumber, OFFSET_ACCOUNT1.financialOffsetAccountNumber));
    }

    public void testGetByPrimaryId_invalid() {
        mockConfigurationServiceForFlexibleOffsetEnabled(true);
        assertNull(service.getByPrimaryIdIfEnabled("XX", "XX", "XX"));
    }

    public void testMockService() {
        assertSame(service, SpringServiceLocator.getFlexibleOffsetAccountService());
        mockConfigurationServiceForFlexibleOffsetEnabled(true);
        assertEquals(true, SpringServiceLocator.getFlexibleOffsetAccountService().getEnabled());
        SpringServiceLocator.restoreServicesIfMocked();
        mockConfigurationServiceForFlexibleOffsetEnabled(false);
        assertEquals(false, SpringServiceLocator.getFlexibleOffsetAccountService().getEnabled());
    }

    /**
     * Integration test to the database parameter table (not the mock configuration service).
     */
    public void testGetEnabled() {
        // This tests that no RuntimeException is thrown because the parameter is missing from the database
        // or contains a value other than Y or N.
        service.getEnabled();
    }
}
