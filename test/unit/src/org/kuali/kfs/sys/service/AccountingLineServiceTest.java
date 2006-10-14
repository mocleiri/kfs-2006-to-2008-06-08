/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/test/unit/src/org/kuali/kfs/sys/service/AccountingLineServiceTest.java,v $
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

import java.util.Iterator;
import java.util.List;

import org.kuali.core.bo.AccountingLine;
import org.kuali.core.bo.AccountingLineBase;
import org.kuali.core.bo.SourceAccountingLine;
import org.kuali.core.bo.TargetAccountingLine;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.test.KualiTestBase;
import org.kuali.test.WithTestSpringContext;
import static org.kuali.test.fixtures.AccountingLineFixture.LINE;

/**
 * This class tests the AccountingLine service.
 * 
 * 
 */
@WithTestSpringContext
public class AccountingLineServiceTest extends KualiTestBase {

    private AccountingLineService accountingLineService;
    private SourceAccountingLine sline;
    private TargetAccountingLine tline;

    protected void setUp() throws Exception {
        super.setUp();
        accountingLineService = SpringServiceLocator.getAccountingLineService();

        // setup line
        sline = LINE.createSourceAccountingLine();

        tline = LINE.createTargetAccountingLine();
    }

    /**
     * Tests an accounting line is correctly persisted when the primitives of the line are set.
     * 
     * @throws Exception
     */
    public void testPersistence() throws Exception {

        AccountingLine line = null;
            accountingLineService.save(sline);

            List sourceLines = accountingLineService.getByDocumentHeaderId(SourceAccountingLine.class, LINE.financialDocumentNumber);
            assertTrue(sourceLines.size() > 0);

            line = (AccountingLine) sourceLines.get(0);

            assertEquals(LINE.chartOfAccountsCode, line.getChartOfAccountsCode());
            assertEquals(LINE.accountNumber, line.getAccountNumber());
            assertEquals(LINE.subAccountNumber, line.getSubAccountNumber());
            assertEquals(LINE.financialObjectCode, line.getFinancialObjectCode());
            assertEquals(LINE.financialSubObjectCode, line.getFinancialSubObjectCode());

            accountingLineService.deleteAccountingLine((AccountingLineBase) line);

    }


    /**
     * Tests reference objects are being corrected refreshed from changed pritive values.
     */
    public void testRefresh() {
        assertEquals(LINE.chartOfAccountsCode, sline.getAccount().getChartOfAccountsCode());
        assertEquals(LINE.accountNumber, sline.getAccount().getAccountNumber());

        sline.setAccountNumber(TestConstants.Data4.ACCOUNT2);
        sline.refresh();

        assertEquals(LINE.chartOfAccountsCode, sline.getAccount().getChartOfAccountsCode());
        assertEquals(TestConstants.Data4.ACCOUNT2, sline.getAccount().getAccountNumber());

        sline.setChartOfAccountsCode(TestConstants.Data4.CHART_CODE_BA);
        sline.setFinancialObjectCode(TestConstants.Data4.OBJECT_CODE2);
        sline.refresh();

        assertEquals(TestConstants.Data4.CHART_CODE_BA, sline.getObjectCode().getChartOfAccounts().getChartOfAccountsCode());
        assertEquals(TestConstants.Data4.OBJECT_CODE2, sline.getObjectCode().getFinancialObjectCode());

    }


    // no obvious way to test these separately, since we need to create to test save, need to save to (really) test get, and need
    // to delete so future test-runs can recreate
    public void testLifecycle() throws Exception {
        // make sure they dont' exist
        assertFalse(accountingLineService.getByDocumentHeaderId(SourceAccountingLine.class, LINE.financialDocumentNumber).size() > 0);
        assertFalse(accountingLineService.getByDocumentHeaderId(TargetAccountingLine.class, LINE.financialDocumentNumber).size() > 0);
        List sourceLines = null;
        List targetLines = null;

        // save 'em
            accountingLineService.save(sline);
            accountingLineService.save(tline);

            sourceLines = accountingLineService.getByDocumentHeaderId(SourceAccountingLine.class, LINE.financialDocumentNumber);
            targetLines = accountingLineService.getByDocumentHeaderId(TargetAccountingLine.class, LINE.financialDocumentNumber);

            // make sure they got saved
            assertTrue(sourceLines.size() > 0);
            assertTrue(targetLines.size() > 0);
            // delete 'em
            if (sourceLines != null) {
                for (Iterator i = sourceLines.iterator(); i.hasNext();) {
                    accountingLineService.deleteAccountingLine((AccountingLineBase) i.next());
                }
            }
            if (targetLines != null) {
                for (Iterator i = targetLines.iterator(); i.hasNext();) {
                    accountingLineService.deleteAccountingLine((AccountingLineBase) i.next());
                }
            }

            // make sure they got deleted
            assertTrue(accountingLineService.getByDocumentHeaderId(SourceAccountingLine.class, LINE.financialDocumentNumber).size() == 0);
            assertTrue(accountingLineService.getByDocumentHeaderId(TargetAccountingLine.class, LINE.financialDocumentNumber).size() == 0);
    }
}
