/*
 * Copyright (c) 2004, 2005 The National Association of College and University Business Officers,
 * Cornell University, Trustees of Indiana University, Michigan State University Board of Trustees,
 * Trustees of San Joaquin Delta College, University of Hawai'i, The Arizona Board of Regents on
 * behalf of the University of Arizona, and the r*smart group.
 * 
 * Licensed under the Educational Community License Version 1.0 (the "License"); By obtaining,
 * using and/or copying this Original Work, you agree that you have read, understand, and will
 * comply with the terms and conditions of the Educational Community License.
 * 
 * You may obtain a copy of the License at:
 * 
 * http://kualiproject.org/license.html
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.kuali.module.financial.document;

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.document.Document;
import org.kuali.core.document.TransactionalDocument;
import org.kuali.core.document.TransactionalDocumentTestBase;
import static org.kuali.core.util.SpringServiceLocator.getDocumentService;
import org.kuali.test.DocumentTestUtils;
import org.kuali.test.TestsWorkflowViaDatabase;
import org.kuali.test.WithTestSpringContext;
import org.kuali.test.fixtures.AccountingLineFixture;
import static org.kuali.test.fixtures.AccountingLineFixture.LINE1;
import org.kuali.test.fixtures.UserNameFixture;
import static org.kuali.test.fixtures.UserNameFixture.CSWINSON;
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
        final String docId = document.getFinancialDocumentNumber();
        routeDocument(document);

        WorkflowTestUtils.waitForNodeChange(document.getDocumentHeader().getWorkflowDocument(), ACCOUNT_REVIEW);

        // the document should now be routed to VPUTMAN and RORENFRO as Fiscal Officers
        changeCurrentUser(VPUTMAN);
        document = getDocumentService().getByDocumentHeaderId(docId);
        assertTrue("At incorrect node.", WorkflowTestUtils.isAtNode(document, ACCOUNT_REVIEW));
        assertTrue("Document should be enroute.", document.getDocumentHeader().getWorkflowDocument().stateIsEnroute());
        assertTrue("VPUTMAN should have an approve request.", document.getDocumentHeader().getWorkflowDocument().isApprovalRequested());
        getDocumentService().approveDocument(document, "Test approving as VPUTMAN", null);

        changeCurrentUser(RORENFRO);
        WorkflowTestUtils.waitForApproveRequest(document.getDocumentHeader().getWorkflowDocument(), RORENFRO.toString());
        document = getDocumentService().getByDocumentHeaderId(docId);
        assertTrue("RORENFRO should have an approve request.", document.getDocumentHeader().getWorkflowDocument().isApprovalRequested());
        getDocumentService().approveDocument(document, "Test approving as RORENFRO", null);

        WorkflowTestUtils.waitForNodeChange(document.getDocumentHeader().getWorkflowDocument(), ORG_REVIEW);

        // now doc should be in Org Review routing to CSWINSON, RRUFFNER, and SEASON
        changeCurrentUser(CSWINSON);
        document = getDocumentService().getByDocumentHeaderId(docId);
        assertTrue("At incorrect node.", WorkflowTestUtils.isAtNode(document, ORG_REVIEW));
        assertTrue("CSWINSON should have an approve request.", document.getDocumentHeader().getWorkflowDocument().isApprovalRequested());
        getDocumentService().approveDocument(document, "Test approving as CSWINSON", null);

        changeCurrentUser(RRUFFNER);
        WorkflowTestUtils.waitForApproveRequest(document.getDocumentHeader().getWorkflowDocument(), RRUFFNER.toString());
        document = getDocumentService().getByDocumentHeaderId(docId);
        assertTrue("RRUFFNER should have an approve request.", document.getDocumentHeader().getWorkflowDocument().isApprovalRequested());
        getDocumentService().approveDocument(document, "Test approving as RRUFFNER", null);

        changeCurrentUser(SEASON);
        WorkflowTestUtils.waitForApproveRequest(document.getDocumentHeader().getWorkflowDocument(), SEASON.toString());
        document = getDocumentService().getByDocumentHeaderId(docId);
        assertTrue("SEASON should have an approve request.", document.getDocumentHeader().getWorkflowDocument().isApprovalRequested());
        getDocumentService().approveDocument(document, "Test approving as SEASON", null);


        // TODO once the sub fund node has been added, add code here to test it...

        WorkflowTestUtils.waitForStatusChange(document.getDocumentHeader().getWorkflowDocument(), EdenConstants.ROUTE_HEADER_FINAL_CD);

        changeCurrentUser(VPUTMAN);
        document = getDocumentService().getByDocumentHeaderId(docId);
        assertTrue("Document should now be final.", document.getDocumentHeader().getWorkflowDocument().stateIsFinal());
    }

    private Document buildDocumentForWorkflowRoutingTest()
        throws Exception
    {
        TransactionalDocument document = (TransactionalDocument) buildDocument();
        // todo: figure out which accounting lines the TOF needs to route to the people in the above test method.
//        AccountingLineFixture.LINE2.addAsSourceTo(document);
//        AccountingLineFixture.LINE2.addAsTargetTo(document);
//        AccountingLineFixture.LINE3.addAsSourceTo(document);
//        AccountingLineFixture.LINE3.addAsTargetTo(document);
        return document;
    } 
}
