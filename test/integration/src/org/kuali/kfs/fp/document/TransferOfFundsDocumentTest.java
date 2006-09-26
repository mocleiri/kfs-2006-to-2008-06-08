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

import static org.kuali.test.fixtures.AccountingLineFixture.LINE1;

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.document.Document;
import org.kuali.core.document.TransactionalDocumentTestBase;
import org.kuali.core.workflow.service.KualiWorkflowDocument;
import org.kuali.test.DocumentTestUtils;
import org.kuali.test.TestsWorkflowViaDatabase;
import org.kuali.test.WithTestSpringContext;
import org.kuali.test.fixtures.AccountingLineFixture;
import org.kuali.workflow.WorkflowTestUtils;

import edu.iu.uis.eden.EdenConstants;
import edu.iu.uis.eden.clientapp.vo.NetworkIdVO;
/**
 * This class is used to test TransferOfFundsDocument.
 * 
 * 
 */
@WithTestSpringContext
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
     * 
     * @param String name of user to use.
     */
    protected String getTestUserName() {
        return getUserName().toString();
    }


    // /////////////////////////////////////////////////////////////////////////
    // Fixture Methods End Here //
    // /////////////////////////////////////////////////////////////////////////

    @TestsWorkflowViaDatabase
    public void testWorkflowRouting() throws Exception {
        NetworkIdVO VPUTMAN = new NetworkIdVO("VPUTMAN");
        NetworkIdVO RORENFRO = new NetworkIdVO("RORENFRO");
        NetworkIdVO CSWINSON = new NetworkIdVO("CSWINSON");
        NetworkIdVO RRUFFNER = new NetworkIdVO("RRUFFNER");
        NetworkIdVO SEASON = new NetworkIdVO("SEASON");

        // save and route the document
        Document document = buildDocument();
        routeDocument(document);

        WorkflowTestUtils.waitForNodeChange(document.getDocumentHeader().getWorkflowDocument(), ACCOUNT_REVIEW);

        // the document should now be routed to VPUTMAN and RORENFRO as Fiscal Officers
        KualiWorkflowDocument wfDoc = WorkflowTestUtils.refreshDocument(document, VPUTMAN);
        assertTrue("At incorrect node.", WorkflowTestUtils.isAtNode(document, ACCOUNT_REVIEW));
        assertTrue("Document should be enroute.", wfDoc.stateIsEnroute());
        assertTrue("VPUTMAN should have an approve request.", wfDoc.isApprovalRequested());
        getDocumentService().approveDocument(document, "Test approving as VPUTMAN", null);

        WorkflowTestUtils.waitForApproveRequest(wfDoc, RORENFRO.getNetworkId());
        wfDoc = WorkflowTestUtils.refreshDocument(document, RORENFRO);
        assertTrue("RORENFRO should have an approve request.", wfDoc.isApprovalRequested());
        getDocumentService().approveDocument(document, "Test approving as RORENFRO", null);

        WorkflowTestUtils.waitForNodeChange(document.getDocumentHeader().getWorkflowDocument(), ORG_REVIEW);

        // now doc should be in Org Review routing to CSWINSON, RRUFFNER, and SEASON
        wfDoc = WorkflowTestUtils.refreshDocument(document, CSWINSON);
        assertTrue("At incorrect node.", WorkflowTestUtils.isAtNode(document, ORG_REVIEW));
        assertTrue("CSWINSON should have an approve request.", wfDoc.isApprovalRequested());
        getDocumentService().approveDocument(document, "Test approving as CSWINSON", null);

        WorkflowTestUtils.waitForApproveRequest(wfDoc, RRUFFNER.getNetworkId());
        wfDoc = WorkflowTestUtils.refreshDocument(document, RRUFFNER);
        assertTrue("RRUFFNER should have an approve request.", wfDoc.isApprovalRequested());
        getDocumentService().approveDocument(document, "Test approving as RRUFFNER", null);

        WorkflowTestUtils.waitForApproveRequest(wfDoc, SEASON.getNetworkId());
        wfDoc = WorkflowTestUtils.refreshDocument(document, SEASON);
        assertTrue("SEASON should have an approve request.", wfDoc.isApprovalRequested());
        getDocumentService().approveDocument(document, "Test approving as SEASON", null);


        // TODO once the sub fund node has been added, add code here to test it...

        WorkflowTestUtils.waitForStatusChange(wfDoc, EdenConstants.ROUTE_HEADER_FINAL_CD);

        wfDoc = WorkflowTestUtils.refreshDocument(document, VPUTMAN);
        assertTrue("Document should now be final.", wfDoc.stateIsFinal());
    }

}
