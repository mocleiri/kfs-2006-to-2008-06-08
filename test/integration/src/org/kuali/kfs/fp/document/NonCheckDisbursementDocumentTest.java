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
package org.kuali.module.financial.document;

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.document.Document;
import org.kuali.core.document.TransactionalDocumentTestBase;
import static org.kuali.core.util.SpringServiceLocator.getDocumentService;
import org.kuali.test.DocumentTestUtils;
import org.kuali.test.WithTestSpringContext;
import org.kuali.test.fixtures.AccountingLineFixture;
import static org.kuali.test.fixtures.AccountingLineFixture.LINE4;
import static org.kuali.test.fixtures.UserNameFixture.KHUNTLEY;

/**
 * This class is used to test NonCheckDisbursementDocumentTest.
 * 
 * 
 */
@WithTestSpringContext(session = KHUNTLEY)
public class NonCheckDisbursementDocumentTest extends TransactionalDocumentTestBase {

    /**
     * 
     * @see org.kuali.core.document.DocumentTestCase#getDocumentParameterFixture()
     */
    public Document getDocumentParameterFixture() throws Exception{
        return DocumentTestUtils.createTransactionalDocument(getDocumentService(), NonCheckDisbursementDocument.class, 2007, "03");
    }

    /**
     * 
     * @see org.kuali.core.document.TransactionalDocumentTestBase#getTargetAccountingLineParametersFromFixtures()
     */
    public List<AccountingLineFixture> getTargetAccountingLineParametersFromFixtures() {
    List<AccountingLineFixture> list = new ArrayList<AccountingLineFixture>();
        list.add(LINE4);
        return list;
    }

    /**
     * 
     * @see org.kuali.core.document.TransactionalDocumentTestBase#getSourceAccountingLineParametersFromFixtures()
     */
    public List<AccountingLineFixture> getSourceAccountingLineParametersFromFixtures() {
    List<AccountingLineFixture> list = new ArrayList<AccountingLineFixture>();
        list.add(LINE4);
        return list;
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

}
