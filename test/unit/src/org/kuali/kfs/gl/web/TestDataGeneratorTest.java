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
package org.kuali.module.gl.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.kuali.kfs.PropertyConstants;
import org.kuali.kfs.bo.GeneralLedgerPendingEntry;
import org.kuali.module.gl.bo.AccountBalance;
import org.kuali.test.KualiTestBase;

/**
 * This class...
 * 
 * 
 */
public class TestDataGeneratorTest extends KualiTestBase {

    private TestDataGenerator testDataGenerator;
    private GeneralLedgerPendingEntry pendingEntry;
    private AccountBalance accountBalance;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        testDataGenerator = new TestDataGenerator();
        pendingEntry = new GeneralLedgerPendingEntry();
        accountBalance = new AccountBalance();
    }

    // test case for generateTransactionDate method of TestDataGenerator class
    public void testGenerateTransactionData() throws Exception {
        testDataGenerator.generateTransactionData(pendingEntry);
        assertEquals(pendingEntry.getAccountNumber(), testDataGenerator.getProperties().getProperty("accountNumber"));
        assertNull(pendingEntry.getTransactionDate());
        try {
            Object property = PropertyUtils.getProperty(pendingEntry, "objectCode");
            assertTrue(false);
        }
        catch (Exception e) {
            assertTrue(true);
        }
    }

    // test case for generateTransactionDate method of TestDataGenerator class
    public void testGenerateFieldValues() throws Exception {
        Map fieldValues = new HashMap();

        // test business object implementing transaction
        fieldValues = testDataGenerator.generateLookupFieldValues(pendingEntry);
        assertEquals(testDataGenerator.getProperties().getProperty("accountNumber"), fieldValues.get("accountNumber"));
        assertNull(fieldValues.get("transactionDate"));
        assertNull(fieldValues.get("objectCode"));

        // test business object not implementing transaction
        fieldValues = testDataGenerator.generateLookupFieldValues(accountBalance);
        assertEquals(testDataGenerator.getProperties().getProperty("accountNumber"), fieldValues.get("accountNumber"));
        assertEquals(testDataGenerator.getProperties().getProperty("dummyBusinessObject.consolidationOption"), fieldValues.get("dummyBusinessObject.consolidationOption"));

        assertNull(fieldValues.get("timestamp"));
        assertNull(fieldValues.get("finacialObjectCode"));
    }

    // test case for generateTransactionDate method of TestDataGenerator class
    public void testGenerateFieldValues(String test) throws Exception {
        Map fieldValues = new HashMap();

        List lookupFields = getLookupFields(true);

        // test business object implementing transaction
        fieldValues = testDataGenerator.generateLookupFieldValues(pendingEntry, lookupFields);
        assertEquals(testDataGenerator.getProperties().getProperty("accountNumber"), fieldValues.get("accountNumber"));
        assertEquals(testDataGenerator.getProperties().getProperty("dummyBusinessObject.consolidationOption"), fieldValues.get("dummyBusinessObject.consolidationOption"));
        assertNull(fieldValues.get("transactionDate"));
        assertNull(fieldValues.get("objectCode"));

        // test business object not implementing transaction
        fieldValues = testDataGenerator.generateLookupFieldValues(accountBalance, lookupFields);
        assertEquals(testDataGenerator.getProperties().getProperty("accountNumber"), fieldValues.get("accountNumber"));
        assertEquals(testDataGenerator.getProperties().getProperty("dummyBusinessObject.consolidationOption"), fieldValues.get("dummyBusinessObject.consolidationOption"));

        assertNull(fieldValues.get("timestamp"));
        assertNull(fieldValues.get("finacialObjectCode"));
    }

    protected List getLookupFields(boolean isExtended) {
        List lookupFields = new ArrayList();

        lookupFields.add(PropertyConstants.UNIVERSITY_FISCAL_YEAR);
        lookupFields.add(PropertyConstants.CHART_OF_ACCOUNTS_CODE);
        lookupFields.add(PropertyConstants.ACCOUNT_NUMBER);
        lookupFields.add(PropertyConstants.UNIVERSITY_FISCAL_PERIOD_CODE);
        lookupFields.add(PropertyConstants.FINANCIAL_BALANCE_TYPE_CODE);
        lookupFields.add("dummyBusinessObject.consolidationOption");
        lookupFields.add("dummyBusinessObject.pendingEntryOption");

        // include the extended fields
        if (isExtended) {
            lookupFields.add(PropertyConstants.SUB_ACCOUNT_NUMBER);
            lookupFields.add(PropertyConstants.FINANCIAL_OBJECT_CODE);
            lookupFields.add(PropertyConstants.FINANCIAL_SUB_OBJECT_CODE);

            lookupFields.add(PropertyConstants.FINANCIAL_OBJECT_TYPE_CODE);
            lookupFields.add(PropertyConstants.FINANCIAL_SYSTEM_ORIGINATION_CODE);
            lookupFields.add(PropertyConstants.FINANCIAL_DOCUMENT_TYPE_CODE);
            lookupFields.add(PropertyConstants.DOCUMENT_NUMBER);
            lookupFields.add(PropertyConstants.ORGANIZATION_DOCUMENT_NUMBER);
        }
        return lookupFields;
    }
}
