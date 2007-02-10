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
package org.kuali.core.service;

import java.text.DateFormat;
import java.util.Date;

import org.kuali.core.bo.KualiSystemCode;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.module.chart.bo.codes.BudgetAggregationCode;
import org.kuali.test.KualiTestBase;
import org.kuali.test.WithTestSpringContext;

/**
 * This class tests the BudgetAggregationCode service.
 * 
 * 
 */
@WithTestSpringContext
public class BudgetAggregationCodeServiceTest extends KualiTestBase {

    private BudgetAggregationCode bac;
    private KualiCodeService kualiCodeService;
    private String timestamp;

    /**
     * Performs setup operations before tests are executed.
     */
    protected void setUp() throws Exception {
        super.setUp();
        kualiCodeService = SpringServiceLocator.getKualiCodeService();
        timestamp = DateFormat.getDateInstance().format(new Date());
    }

    /**
     * Performs all tests for this service.
     */
    public void testLookupByCode_code_known() {
        bac = null;
        bac = (BudgetAggregationCode) kualiCodeService.getByCode(BudgetAggregationCode.class, TestConstants.Data5.BUDGET_AGGREGATION_CODE1);
        assertNotNull("Should be a valid object.", bac);
        assertEquals("Known-good code results in expected returned Name.", TestConstants.Data5.BUDGET_AGGREGATION_NAME1, bac.getName());
    }

    public void testLookupByCode_code_unknown() {
        bac = null;
        bac = (BudgetAggregationCode) kualiCodeService.getByCode(BudgetAggregationCode.class, TestConstants.Data5.FEDERAL_FUNDED_CODE_BAD);
        assertNull("Known-bad code should return null object.", bac);
    }

    public void testLookupByCode_code_blank() {
        bac = null;
        bac = (BudgetAggregationCode) kualiCodeService.getByCode(BudgetAggregationCode.class, "");
        assertNull("Known-empty code returns null object.", bac);
    }

    public void testLookupByCode_code_null() {
        bac = null;
        bac = (BudgetAggregationCode) kualiCodeService.getByCode(BudgetAggregationCode.class, null);
        assertNull("Known-null code returns null object.", bac);
    }

    public void testLookupByName_name_known() {
        KualiSystemCode bac = null;
        Object result = kualiCodeService.getByName(BudgetAggregationCode.class, TestConstants.Data5.BUDGET_AGGREGATION_NAME1);
        bac = (KualiSystemCode) result;
        assertNotNull("Should be a valid object.", bac);
        assertEquals("Known-good name results in expected returned code.", TestConstants.Data5.BUDGET_AGGREGATION_CODE1, bac.getCode());
    }

    public void testLookupByName_name_unknown() {
        bac = null;
        bac = (BudgetAggregationCode) kualiCodeService.getByName(BudgetAggregationCode.class, "This is not a valid code description in this table.");
        assertNull("Known-bad code returns null object.", bac);
    }

    public void testLookupByCode_name_blank() {
        bac = null;
        bac = (BudgetAggregationCode) kualiCodeService.getByName(BudgetAggregationCode.class, "");
        assertNull("Known-empty name returns null object.", bac);
    }

    public void testLookupByCode_name_null() {
        bac = null;
        bac = (BudgetAggregationCode) kualiCodeService.getByName(BudgetAggregationCode.class, null);
        assertNull("Known-null name returns null object.", bac);
    }

    public void testActive() {

        // test known-good active code
        bac = null;
        bac = (BudgetAggregationCode) kualiCodeService.getByCode(BudgetAggregationCode.class, TestConstants.Data5.BUDGET_AGGREGATION_CODE1);
        assertEquals("The active code associated with this field is incorrect", true, bac.isActive());

    }

    /**
     * This tests the caching mechanism by looping five times through some get calls. The first time through, the data should be
     * retrieved from the DB. Every time after that, the object should be retrieved from the cache therefore time to retrieve should
     * be less than or equal to.
     * 
     * @author Aaron Godert (ag266@cornell.edu)
     */
    public void testCache() {
        long tsStart;
        long tsStop;

        for (int i = 0; i < 5; i++) {
            long firstTime = -1000;
            tsStart = System.currentTimeMillis();
            BudgetAggregationCode O = (BudgetAggregationCode) kualiCodeService.getByCode(BudgetAggregationCode.class, TestConstants.Data5.BUDGET_AGGREGATION_CODE1);
            BudgetAggregationCode C = (BudgetAggregationCode) kualiCodeService.getByCode(BudgetAggregationCode.class, TestConstants.Data5.BUDGET_AGGREGATION_CODE2);
            BudgetAggregationCode L = (BudgetAggregationCode) kualiCodeService.getByCode(BudgetAggregationCode.class, TestConstants.Data5.BUDGET_AGGREGATION_CODE1);
            tsStop = System.currentTimeMillis();
            long diff = tsStop - tsStart;
            if (firstTime == -1000) {
                firstTime = diff;
            }
            assertTrue(diff <= firstTime);
        }
    }
}
