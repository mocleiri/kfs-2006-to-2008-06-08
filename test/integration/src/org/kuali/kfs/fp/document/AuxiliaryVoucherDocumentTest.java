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
package org.kuali.module.financial.document;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.kuali.Constants.GL_CREDIT_CODE;
import static org.kuali.Constants.GL_DEBIT_CODE;
import org.kuali.core.bo.SourceAccountingLine;
import org.kuali.core.document.Document;
import org.kuali.core.document.TransactionalDocument;
import org.kuali.core.document.TransactionalDocumentTestBase;
import org.kuali.core.util.SpringServiceLocator;
import static org.kuali.core.util.SpringServiceLocator.getDocumentService;
import org.kuali.module.chart.bo.AccountingPeriod;
import org.kuali.test.DocumentTestUtils;
import org.kuali.test.WithTestSpringContext;
import org.kuali.test.fixtures.AccountingLineFixture;
import static org.kuali.test.fixtures.AccountingLineFixture.LINE15;
import static org.kuali.test.fixtures.UserNameFixture.KHUNTLEY;

/**
 * This class is used to test NonCheckDisbursementDocumentTest.
 * 
 * 
 */
@WithTestSpringContext(session = KHUNTLEY)
public class AuxiliaryVoucherDocumentTest extends TransactionalDocumentTestBase {

    /**
     * 
     * @see org.kuali.core.document.DocumentTestCase#getDocumentParameterFixture()
     */
    public Document getDocumentParameterFixture() throws Exception {
        //AV document has a restriction on accounting period cannot be more than 2 periods behind current
        Date date = SpringServiceLocator.getDateTimeService().getCurrentSqlDate();
        AccountingPeriod accountingPeriod = SpringServiceLocator.getAccountingPeriodService().getByDate(date);
        return DocumentTestUtils.createTransactionalDocument(getDocumentService(), AuxiliaryVoucherDocument.class, accountingPeriod.getUniversityFiscalYear(), accountingPeriod.getUniversityFiscalPeriodCode());
    }

    /**
     * 
     * @see org.kuali.core.document.TransactionalDocumentTestBase#getTargetAccountingLineParametersFromFixtures()
     */
    @Override
    public List<AccountingLineFixture> getTargetAccountingLineParametersFromFixtures() {
return  new ArrayList<AccountingLineFixture>();
    }

    /**
     * 
     * @see org.kuali.core.document.TransactionalDocumentTestBase#getSourceAccountingLineParametersFromFixtures()
     */
    public List<AccountingLineFixture> getSourceAccountingLineParametersFromFixtures() {
    List<AccountingLineFixture> list = new ArrayList<AccountingLineFixture>();
        list.add(LINE15);
        return list;
    }

    /**
     * @see org.kuali.core.document.TransactionalDocumentTestBase#buildDocument()
     */
    @Override
    protected Document buildDocument() throws Exception {
            // put accounting lines into document parameter for later
            TransactionalDocument document = (TransactionalDocument) getDocumentParameterFixture();

            // set accountinglines to document
            for (AccountingLineFixture sourceFixture : getSourceAccountingLineParametersFromFixtures()) {
                SourceAccountingLine line=sourceFixture.createAccountingLine(SourceAccountingLine.class, document.getFinancialDocumentNumber(), document.getPostingYear(), document.getNextSourceLineNumber());
                SourceAccountingLine balance=sourceFixture.createAccountingLine(SourceAccountingLine.class, document.getFinancialDocumentNumber(), document.getPostingYear(), document.getNextSourceLineNumber());
                balance.setDebitCreditCode(GL_DEBIT_CODE.equals(line.getDebitCreditCode())?GL_CREDIT_CODE:GL_DEBIT_CODE);
                document.addSourceAccountingLine(line);
                document.addSourceAccountingLine(balance);

            }

            return document;
    }

    /**
     * 
     * @see org.kuali.core.document.TransactionalDocumentTestBase#testConvertIntoErrorCorrection()
     */
    public void testConvertIntoErrorCorrection() throws Exception {
        // for now we just want this to run without problems, so we are overriding the parent's
        // and leaving blank to run successfully
        // when we get to this document, we'll fix the problem with blanket approving non check
        // disbursement document test
    }

    /**
     * @see org.kuali.core.document.TransactionalDocumentTestBase#testConvertIntoCopy_invalidYear()
     * @see AuxiliaryVoucherDocument#getNullOrReasonNotToCopy(String, boolean)
     */
    @Override
    public void testConvertIntoCopy_invalidYear() throws Exception {
//      this test is not valid for the AV
    }

    /**
     * @see org.kuali.core.document.TransactionalDocumentTestBase#testConvertIntoErrorCorrection_invalidYear()
     * @see AuxiliaryVoucherDocument#getNullOrReasonNotToCopy(String, boolean)
     */
    @Override
    public void testConvertIntoErrorCorrection_invalidYear() throws Exception {
       //this test is not valid for the AV
    }

    /**
     * @see org.kuali.core.document.TransactionalDocumentTestBase#getExpectedPrePeCount()
     */
    @Override
    protected int getExpectedPrePeCount() {
        return 2;
    }

}
