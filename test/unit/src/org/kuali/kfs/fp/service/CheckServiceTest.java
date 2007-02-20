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

import java.util.Iterator;
import java.util.List;

import org.kuali.core.util.KualiDecimal;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.module.financial.bo.Check;
import org.kuali.module.financial.bo.CheckBase;
import org.kuali.test.KualiTestBase;
import org.kuali.test.WithTestSpringContext;

/**
 * This class tests the Check service.
 * 
 * 
 */
@WithTestSpringContext
public class CheckServiceTest extends KualiTestBase {

    private CheckService checkService;
    private Check check;

    private final String DOCUMENT_HEADER_ID = "-1";

    protected void setUp() throws Exception {
        super.setUp();
        checkService = SpringServiceLocator.getCheckService();

        // setup check
        check = new CheckBase();
        check.setFinancialDocumentNumber(DOCUMENT_HEADER_ID);
        check.setAmount(new KualiDecimal("314.15"));
        check.setCheckDate(SpringServiceLocator.getDateTimeService().getCurrentSqlDate());
        check.setCheckNumber("2112");
        check.setDescription("test check");
        check.setInterimDepositAmount(true);
        check.setSequenceId(new Integer(2001));

        // clean up remnants of earlier tests
        clearTestData();
    }


    public void testLifecycle() throws Exception {
        boolean deleteSucceeded = false;

        List retrievedChecks = checkService.getByDocumentHeaderId(DOCUMENT_HEADER_ID);
        assertTrue(retrievedChecks.size() == 0);

        Check savedCheck = null;
        Check retrievedCheck = null;
        try {
            // save a check
            savedCheck = checkService.save(check);

            // retrieve it
            retrievedChecks = checkService.getByDocumentHeaderId(DOCUMENT_HEADER_ID);
            assertTrue(retrievedChecks.size() > 0);
            retrievedCheck = (Check) retrievedChecks.get(0);

            // compare
            assertTrue(check.isLike(savedCheck));
            assertTrue(savedCheck.isLike(retrievedCheck));
        }
        finally {
            // delete it
            checkService.deleteCheck(savedCheck);
        }

        // verify that the delete succeeded
        retrievedChecks = checkService.getByDocumentHeaderId(DOCUMENT_HEADER_ID);
        assertTrue(retrievedChecks.size() == 0);

    }


    private void clearTestData() {
        List retrievedChecks = checkService.getByDocumentHeaderId(DOCUMENT_HEADER_ID);
        if (retrievedChecks.size() > 0) {
            for (Iterator i = retrievedChecks.iterator(); i.hasNext();) {
                checkService.deleteCheck((Check) i.next());
            }
        }
    }
}
