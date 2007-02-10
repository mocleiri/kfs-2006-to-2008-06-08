/*
 * Copyright 2005-2006 The Kuali Foundation.
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

import org.kuali.core.util.ObjectUtils;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.module.chart.bo.A21SubAccount;
import org.kuali.module.chart.bo.SubAccount;
import org.kuali.test.KualiTestBase;
import org.kuali.test.WithTestSpringContext;

/**
 * This class tests the SubAccount service.
 * 
 * 
 */
@WithTestSpringContext
public class SubAccountServiceTest extends KualiTestBase {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(SubAccountServiceTest.class);

    private SubAccountService subAccountService;
    private final static String CHART = "BA";
    private final static String ACCOUNT = "6044900";
    private final static String SUB_ACCOUNT = "ARREC";

    protected void setUp() throws Exception {
        super.setUp();

        setSubAccountService((SubAccountService) SpringServiceLocator.getSubAccountService());
    }

    public void testA21SubAccount() {
        SubAccount sa;

        sa = subAccountService.getByPrimaryId(CHART, ACCOUNT, SUB_ACCOUNT);

        assertTrue("expect to find this sub account: " + CHART + "/" + ACCOUNT + "/" + SUB_ACCOUNT, ObjectUtils.isNotNull(sa));
        A21SubAccount a21 = sa.getA21SubAccount();
        assertTrue("expect this to have a21subaccount", ObjectUtils.isNotNull(a21));
        a21.getIndirectCostRecoveryAccount();
    }

    public void testGetByPrimaryId() throws Exception {
        SubAccount sa = new SubAccount();
        sa.setAccountNumber(ACCOUNT);
        sa.setChartOfAccountsCode(CHART);
        sa.setSubAccountNumber(SUB_ACCOUNT);


        SubAccount retrieved = subAccountService.getByPrimaryId(CHART, ACCOUNT, SUB_ACCOUNT);
        assertTrue("Didn't retrieve sub account", ObjectUtils.isNotNull(retrieved));
        assertEquals("Wrong chart", CHART, retrieved.getChartOfAccountsCode());
        assertEquals("Wrong account", ACCOUNT, retrieved.getAccountNumber());
        assertEquals("Wrong Sub account number", SUB_ACCOUNT, retrieved.getSubAccountNumber());
    }

    /**
     * Sets the subAccountService attribute value.
     * 
     * @param subAccountService The subAccountService to set.
     */
    public void setSubAccountService(SubAccountService subAccountService) {
        this.subAccountService = subAccountService;
    }


}
