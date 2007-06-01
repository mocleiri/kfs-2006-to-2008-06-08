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

import static org.kuali.core.util.SpringServiceLocator.getDocumentService;
import static org.kuali.test.fixtures.AccountingLineFixture.GEC_LINE1;
import static org.kuali.test.fixtures.UserNameFixture.KHUNTLEY;

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.document.Document;
import org.kuali.core.document.TransactionalDocument;
import org.kuali.core.document.TransactionalDocumentTestBase;
import org.kuali.module.financial.bo.GECSourceAccountingLine;
import org.kuali.module.financial.bo.GECTargetAccountingLine;
import org.kuali.test.DocumentTestUtils;
import org.kuali.test.WithTestSpringContext;
import org.kuali.test.fixtures.AccountingLineFixture;

/**
 * This class is used to test GeneralErrorCorrectionDocument.
 * 
 * 
 */
@WithTestSpringContext(session = KHUNTLEY)
public class YearEndGeneralErrorCorrectionDocumentTest extends TransactionalDocumentTestBase {

    /**
     * 
     * @see org.kuali.core.document.DocumentTestBase#getDocumentParameterFixture()
     */
    public Document getDocumentParameterFixture() throws Exception{
        return DocumentTestUtils.createTransactionalDocument(getDocumentService(), YearEndGeneralErrorCorrectionDocument.class, 2007, "07");
    }

    /**
     * 
     * @see org.kuali.core.document.TransactionalDocumentTestBase#getTargetAccountingLineParametersFromFixtures()
     */
    @Override
    public List<AccountingLineFixture> getTargetAccountingLineParametersFromFixtures() {
	List<AccountingLineFixture> list = new ArrayList<AccountingLineFixture>();
        list.add(GEC_LINE1);
        return list;
    }

    /**
     * 
     * @see org.kuali.core.document.TransactionalDocumentTestBase#getSourceAccountingLineParametersFromFixtures()
     */
    @Override
    public List<AccountingLineFixture> getSourceAccountingLineParametersFromFixtures() {
	List<AccountingLineFixture> list = new ArrayList<AccountingLineFixture>();
        list.add(GEC_LINE1);
        return list;
    }
    /**
     * need to use GEC accounting line types
     * @see org.kuali.core.document.TransactionalDocumentTestBase#buildDocument()
     */
    @Override
    protected Document buildDocument() throws Exception {
        // put accounting lines into document parameter for later
        TransactionalDocument document = (TransactionalDocument) getDocumentParameterFixture();

        // set accountinglines to document
        for (AccountingLineFixture sourceFixture : getSourceAccountingLineParametersFromFixtures()) {
            
            document.addSourceAccountingLine(sourceFixture.createAccountingLine(GECSourceAccountingLine.class, document.getFinancialDocumentNumber(), document.getPostingYear(), document.getNextSourceLineNumber()));
        }

        for (AccountingLineFixture targetFixture : getTargetAccountingLineParametersFromFixtures()) {
            document.addTargetAccountingLine(targetFixture.createAccountingLine(GECTargetAccountingLine.class, document.getFinancialDocumentNumber(), document.getPostingYear(), document.getNextTargetLineNumber()));
        }

        return document;
    }
}
