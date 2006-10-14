/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/test/integration/src/org/kuali/kfs/fp/document/TransferOfFundsDocumentTest.java,v $
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
import org.kuali.core.document.TransactionalDocument;
import org.kuali.core.document.TransactionalDocumentTestBase;
import static org.kuali.core.util.SpringServiceLocator.getDocumentService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.test.DocumentTestUtils;
import org.kuali.test.TestsWorkflowViaDatabase;
import org.kuali.test.WithTestSpringContext;
import org.kuali.test.fixtures.AccountingLineFixture;
import static org.kuali.test.fixtures.AccountingLineFixture.LINE1;
import org.kuali.test.fixtures.UserNameFixture;
import static org.kuali.test.fixtures.UserNameFixture.CSWINSON;
import static org.kuali.test.fixtures.UserNameFixture.DFOGLE;
import static org.kuali.test.fixtures.UserNameFixture.KHUNTLEY;
import static org.kuali.test.fixtures.UserNameFixture.RORENFRO;
import static org.kuali.test.fixtures.UserNameFixture.RRUFFNER;
import static org.kuali.test.fixtures.UserNameFixture.SEASON;
import static org.kuali.test.fixtures.UserNameFixture.VPUTMAN;
import org.kuali.workflow.WorkflowTestUtils;

import edu.iu.uis.eden.EdenConstants;
/**
 * This class is used to test TransferOfFundsDocument.
 * 
 * 
 */
@WithTestSpringContext(session = KHUNTLEY)
public class TransferOfFundsDocumentTest extends TransactionalDocumentTestBase {

    // The set of Route Nodes that the test document will progress through

    private static final String ACCOUNT_REVIEW = "Account Review";
    private static final String ORG_REVIEW = "Org Review";



    // /////////////////////////////////////////////////////////////////////////
    // Fixture Methods Start Here //
    // /////////////////////////////////////////////////////////////////////////

    /**
     * 
     * @see org.kuali.core.document.DocumentTestBase#getDocumentParameterFixture()
     */
    public Document getDocumentParameterFixture() throws Exception{
        return DocumentTestUtils.createTransactionalDocument(getDocumentService(), TransferOfFundsDocument.class, 2007, "06");
    }

    /**
     * 
     * @see org.kuali.core.document.TransactionalDocumentTestBase#getTargetAccountingLineParametersFromFixtures()
     */
    @Override
    public List<AccountingLineFixture> getTargetAccountingLineParametersFromFixtures() {
        List<AccountingLineFixture> list = new ArrayList<AccountingLineFixture>();
        list.add(LINE1);
        // list.add( LINE2 );
        // list.add( LINE3 );
        return list;
    }

    /**
     * 
     * @see org.kuali.core.document.TransactionalDocumentTestBase#getSourceAccountingLineParametersFromFixtures()
     */
    @Override
    public List<AccountingLineFixture> getSourceAccountingLineParametersFromFixtures() {
        List<AccountingLineFixture> list = new ArrayList<AccountingLineFixture>();
        list.add(LINE1);
        // list.add( LINE2 );
        // list.add( LINE3 );
        return list;
    }

    /**
     * User name fixture to be used for this test.
     */
    protected UserNameFixture getTestUserName() {
        return getUserName();
    }


    // /////////////////////////////////////////////////////////////////////////
    // Fixture Methods End Here //
    // /////////////////////////////////////////////////////////////////////////

    @TestsWorkflowViaDatabase
    public void testWorkflowRouting() throws Exception {
        // save and route the document
        Document document = buildDocumentForWorkflowRoutingTest();
        final String docHeaderId = document.getFinancialDocumentNumber();
        routeDocument(document);

        // the document should now be routed to VPUTMAN and RORENFRO as Fiscal Officers
        WorkflowTestUtils.waitForNodeChange(document.getDocumentHeader().getWorkflowDocument(), ACCOUNT_REVIEW);
        approve(docHeaderId, VPUTMAN, ACCOUNT_REVIEW);
        approve(docHeaderId, RORENFRO, ACCOUNT_REVIEW);

        // now doc should be in Org Review routing to CSWINSON, RRUFFNER, SEASON, and DFOGLE
        WorkflowTestUtils.waitForNodeChange(document.getDocumentHeader().getWorkflowDocument(), ORG_REVIEW);
        approve(docHeaderId, CSWINSON, ORG_REVIEW);
        approve(docHeaderId, RRUFFNER, ORG_REVIEW);
        approve(docHeaderId, SEASON, ORG_REVIEW);
        approve(docHeaderId, DFOGLE, ORG_REVIEW);

        // TODO once the sub fund node has been added, add code here to test it...

        WorkflowTestUtils.waitForStatusChange(document.getDocumentHeader().getWorkflowDocument(), EdenConstants.ROUTE_HEADER_FINAL_CD);

        changeCurrentUser(VPUTMAN);
        document = getDocumentService().getByDocumentHeaderId(docHeaderId);
        assertTrue("Document should now be final.", document.getDocumentHeader().getWorkflowDocument().stateIsFinal());
    }

    private void approve(String docHeaderId, UserNameFixture user, String expectedNode)
        throws Exception
    {
        changeCurrentUser(user);
        WorkflowTestUtils.waitForApproveRequest(Long.valueOf(docHeaderId), GlobalVariables.getUserSession().getKualiUser());
        Document document = getDocumentService().getByDocumentHeaderId(docHeaderId);
        assertTrue("Document should be at routing node " + expectedNode, WorkflowTestUtils.isAtNode(document, expectedNode));
        assertTrue("Document should be enroute.", document.getDocumentHeader().getWorkflowDocument().stateIsEnroute());
        assertTrue(user + " should have an approve request.", document.getDocumentHeader().getWorkflowDocument().isApprovalRequested());
        getDocumentService().approveDocument(document, "Test approving as " + user, null);
    }

    private Document buildDocumentForWorkflowRoutingTest()
        throws Exception
    {
        TransactionalDocument document = (TransactionalDocument) buildDocument();
        AccountingLineFixture.LINE2_TOF.addAsSourceTo(document);
        AccountingLineFixture.LINE2_TOF.addAsTargetTo(document);
        return document;
    } 
}
