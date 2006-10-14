/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/test/integration/src/org/kuali/kfs/fp/document/IndirectCostAdjustmentDocumentTest.java,v $
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
import static org.kuali.test.fixtures.AccountingLineFixture.ICA_LINE;
import static org.kuali.test.fixtures.UserNameFixture.KHUNTLEY;

/**
 * This class is used to test the IndirectCostAdjustmentDocument.
 * 
 * 
 */
@WithTestSpringContext(session = KHUNTLEY)
public class IndirectCostAdjustmentDocumentTest extends TransactionalDocumentTestBase {

    /**
     * 
     * @see org.kuali.core.document.DocumentTestBase#getDocumentParameterFixture()
     */
    public Document getDocumentParameterFixture() throws Exception {
        return DocumentTestUtils.createTransactionalDocument(getDocumentService(), IndirectCostAdjustmentDocument.class, 2007, "7");
    }

    /**
     * 
     * @see org.kuali.core.document.TransactionalDocumentTestBase#getTargetAccountingLineParametersFromFixtures()
     */
    public List<AccountingLineFixture> getTargetAccountingLineParametersFromFixtures() {
           return new ArrayList<AccountingLineFixture>();
    }

    /**
     * 
     * @see org.kuali.core.document.TransactionalDocumentTestBase#getSourceAccountingLineParametersFromFixtures()
     */
    public List<AccountingLineFixture> getSourceAccountingLineParametersFromFixtures() {
    List<AccountingLineFixture> list = new ArrayList<AccountingLineFixture>();
        list.add(ICA_LINE);
        return list;
    }


    // START TEST METHODS
    /**
     * Overrides the parent to do nothing since the ICA doesn't set the posting period in the record it stores. This test doesn't
     * apply to this type of document.
     */
    public final void testConvertIntoCopy_invalidYear() throws Exception {
        // do nothing to pass
    }

    /**
     * Overrides the parent to do nothing since the ICA doesn't set the posting period in the record it stores. This test doesn't
     * apply to this type of document.
     */
    public final void testConvertIntoErrorCorrection_invalidYear() throws Exception {
        // do nothing to pass
    }

}
