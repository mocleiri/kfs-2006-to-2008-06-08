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

import org.kuali.Constants;
import org.kuali.core.exceptions.ValidationException;
import org.kuali.core.service.DocumentService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.module.financial.document.CashReceiptDocument;
import org.kuali.module.financial.util.CashReceiptFamilyTestUtil;
import org.kuali.test.KualiTestBase;
import org.kuali.test.TestsWorkflowViaDatabase;
import org.kuali.test.WithTestSpringContext;
import org.kuali.test.fixtures.UserNameFixture;
import static org.kuali.test.fixtures.UserNameFixture.KHUNTLEY;

import edu.iu.uis.eden.exception.WorkflowException;

@WithTestSpringContext(session = KHUNTLEY)
public class CashReceiptServiceTest extends KualiTestBase {
    private static final String TEST_CAMPUS_CD = Constants.CashReceiptConstants.TEST_CASH_RECEIPT_CAMPUS_LOCATION_CODE;
    private static final String TEST_UNIT_NAME = Constants.CashReceiptConstants.TEST_CASH_RECEIPT_VERIFICATION_UNIT;

    private static final String DEFAULT_CAMPUS_CD = Constants.CashReceiptConstants.DEFAULT_CASH_RECEIPT_CAMPUS_LOCATION_CODE;
    private static final String DEFAULT_UNIT_NAME = Constants.CashReceiptConstants.DEFAULT_CASH_RECEIPT_VERIFICATION_UNIT;

    private static final String UNKNOWN_UNIT_NAME = "unknownUnit";


    CashReceiptService crService;
    DocumentService docService;

    protected void setUp() throws Exception {
        super.setUp();

        crService = SpringServiceLocator.getCashReceiptService();
        docService = SpringServiceLocator.getDocumentService();
    }

    public final void testGetCampusCodeForCashReceiptVerificationUnit_blankVerificationUnit() {
        boolean failedAsExpected = false;

        try {
            crService.getCampusCodeForCashReceiptVerificationUnit(" ");
        }
        catch (IllegalArgumentException e) {
            failedAsExpected = true;
        }

        assertTrue(failedAsExpected);
    }

    // TODO: once we stop returning default campusCode for unknown verificationUnit, need a test for unknown verificationUnit
    public final void testGetCampusCodeForCashReceiptVerificationUnit_defaultVerificationUnit() {
        String returnedCode = crService.getCampusCodeForCashReceiptVerificationUnit(DEFAULT_UNIT_NAME);

        assertNotNull(returnedCode);
        assertEquals(DEFAULT_CAMPUS_CD, returnedCode);
    }

    public final void testGetCampusCodeForCashReceiptVerificationUnit_knownVerificationUnit() {
        String returnedCode = crService.getCampusCodeForCashReceiptVerificationUnit(TEST_UNIT_NAME);

        assertNotNull(returnedCode);
        assertEquals(TEST_CAMPUS_CD, returnedCode);
    }


    public final void testGetCashReceiptVerificationUnitForCampusCode_blankCampusCode() {
        boolean failedAsExpected = false;

        try {
            crService.getCashReceiptVerificationUnitForCampusCode(null);
        }
        catch (IllegalArgumentException e) {
            failedAsExpected = true;
        }

        assertTrue(failedAsExpected);
    }

    // TODO: once we stop returning defaultVerificationUnit for unknown campusCode, need a test for unknown campusCode
    public final void testGetCashReceiptVerificationUnitForCampusCode_defaultCampusCode() {
        String returnedUnit = crService.getCashReceiptVerificationUnitForCampusCode(DEFAULT_CAMPUS_CD);

        assertNotNull(returnedUnit);
        assertEquals(DEFAULT_UNIT_NAME, returnedUnit);
    }

    public final void testGetCashReceiptVerificationUnitForCampusCode_knownCampusCode() {
        String returnedUnit = crService.getCashReceiptVerificationUnitForCampusCode(TEST_CAMPUS_CD);

        assertNotNull(returnedUnit);
        assertEquals(TEST_UNIT_NAME, returnedUnit);
    }


    public final void testGetCashReceiptVerificationUnit_nullUser() {
        boolean failedAsExpected = false;

        try {
            crService.getCashReceiptVerificationUnitForUser(null);
        }
        catch (IllegalArgumentException e) {
            failedAsExpected = true;
        }

        assertTrue(failedAsExpected);
    }

    public final void testGetCashReceiptVerificationUnit_validUser() {
        String expectedUnit = Constants.CashReceiptConstants.DEFAULT_CASH_RECEIPT_VERIFICATION_UNIT;

        String unit = crService.getCashReceiptVerificationUnitForUser(GlobalVariables.getUserSession().getKualiUser());
        assertEquals(expectedUnit, unit);
    }

    // TODO: once we stop returning default campus code for every user, need a test for a user who is not in a verification unit

    public final void testGetCashReceipts1_blankUnitName() {
        boolean failedAsExpected = false;

        try {
            crService.getCashReceipts("   ", (String) null);
        }
        catch (IllegalArgumentException e) {
            failedAsExpected = true;
        }

        assertTrue(failedAsExpected);
    }

    public final void testGetCashReceipts1_blankStatusCode() {
        boolean failedAsExpected = false;

        try {
            crService.getCashReceipts(TEST_UNIT_NAME, "");
        }
        catch (IllegalArgumentException e) {
            failedAsExpected = true;
        }

        assertTrue(failedAsExpected);
    }

    // TODO: once we stop returning default campus code for unknown unit, need tests for unknown unit

    public final void testGetCashReceipts1_knownVerificationUnit_noVerifiedReceipts() {
        final String workgroup = TEST_UNIT_NAME;

        // clean up before testing
        denatureCashReceipts(workgroup);

        List receipts = crService.getCashReceipts(workgroup, Constants.DocumentStatusCodes.CashReceipt.VERIFIED);
        assertEquals(0, receipts.size());
    }

    public final void testGetCashReceipts1_knownVerificationUnit_noInterimReceipts() {
        final String workgroup = TEST_UNIT_NAME;

        // clean up before testing
        denatureCashReceipts(workgroup);

        List receipts = crService.getCashReceipts(workgroup, Constants.DocumentStatusCodes.CashReceipt.INTERIM);
        assertEquals(0, receipts.size());
    }


    @TestsWorkflowViaDatabase
    public final void testGetCashReceipts1_knownVerificationUnit_interimReceipts() throws Exception {
        final String workgroup = TEST_UNIT_NAME;

        // clean up before testing
        denatureCashReceipts(workgroup);

        // create some CRs
        changeCurrentUser(UserNameFixture.INEFF);
        CashReceiptDocument cr1 = buildCashReceiptDoc(workgroup, "ww2 CRST cr1", Constants.DocumentStatusCodes.CashReceipt.INTERIM, new KualiDecimal("101.01"), new KualiDecimal("898.99"));

        CashReceiptDocument cr2 = buildCashReceiptDoc(workgroup, "ww2 CRST cr2", Constants.DocumentStatusCodes.CashReceipt.INTERIM, new KualiDecimal("212.12"), new KualiDecimal("787.87"));


        // verify that there are only interim CRs
        List vreceipts = crService.getCashReceipts(workgroup, Constants.DocumentStatusCodes.CashReceipt.VERIFIED);
        assertEquals(0, vreceipts.size());

        List ireceipts = crService.getCashReceipts(workgroup, Constants.DocumentStatusCodes.CashReceipt.INTERIM);
        assertEquals(2, ireceipts.size());

        // clean up afterwards
        denatureCashReceipts(workgroup);
    }

    @TestsWorkflowViaDatabase
    public final void testGetCashReceipts1_knownVerificationUnit_verifiedReceipts() throws Exception {
        final String workgroup = TEST_UNIT_NAME;

        // clean up before testing
        denatureCashReceipts(workgroup);

        // create some CRs
        changeCurrentUser(UserNameFixture.INEFF);
        CashReceiptDocument cr1 = buildCashReceiptDoc(workgroup, "ww2 CRST cr1", Constants.DocumentStatusCodes.CashReceipt.VERIFIED, new KualiDecimal("101.01"), new KualiDecimal("898.99"));

        CashReceiptDocument cr2 = buildCashReceiptDoc(workgroup, "ww2 CRST cr2", Constants.DocumentStatusCodes.CashReceipt.VERIFIED, new KualiDecimal("212.12"), new KualiDecimal("787.87"));


        // verify that there are only verified CRs
        List ireceipts = crService.getCashReceipts(workgroup, Constants.DocumentStatusCodes.CashReceipt.INTERIM);
        assertEquals(0, ireceipts.size());

        List vreceipts = crService.getCashReceipts(workgroup, Constants.DocumentStatusCodes.CashReceipt.VERIFIED);
        assertEquals(2, vreceipts.size());

        // clean up afterwards
        denatureCashReceipts(workgroup);
    }

    @TestsWorkflowViaDatabase
    public final void testGetCashReceipts1_knownVerificationUnit_mixedReceipts() throws Exception {
        final String workgroup = TEST_UNIT_NAME;

        // clean up before testing
        denatureCashReceipts(workgroup);

        // create some CRs
        changeCurrentUser(UserNameFixture.INEFF);
        CashReceiptDocument cr1 = buildCashReceiptDoc(workgroup, "ww2 CRST cr1", Constants.DocumentStatusCodes.CashReceipt.INTERIM, new KualiDecimal("101.01"), new KualiDecimal("898.99"));

        CashReceiptDocument cr2 = buildCashReceiptDoc(workgroup, "ww2 CRST cr2", Constants.DocumentStatusCodes.CashReceipt.VERIFIED, new KualiDecimal("212.12"), new KualiDecimal("787.87"));


        // verify that there are some of each
        List ireceipts = crService.getCashReceipts(workgroup, Constants.DocumentStatusCodes.CashReceipt.INTERIM);
        assertEquals(1, ireceipts.size());

        List vreceipts = crService.getCashReceipts(workgroup, Constants.DocumentStatusCodes.CashReceipt.VERIFIED);
        assertEquals(1, vreceipts.size());


        // clean up afterwards
        denatureCashReceipts(workgroup);
    }


    private static final String[] BOTH_STATII = { Constants.DocumentStatusCodes.CashReceipt.VERIFIED, Constants.DocumentStatusCodes.CashReceipt.INTERIM };
    private static final String[] ISTATII = { Constants.DocumentStatusCodes.CashReceipt.INTERIM };
    private static final String[] VSTATII = { Constants.DocumentStatusCodes.CashReceipt.VERIFIED };


    public final void testGetCashReceipts2_blankUnitName() {
        boolean failedAsExpected = false;

        try {
            crService.getCashReceipts("   ", (String[]) null);
        }
        catch (IllegalArgumentException e) {
            failedAsExpected = true;
        }

        assertTrue(failedAsExpected);
    }

    public final void testGetCashReceipts2_nullStatii() {
        boolean failedAsExpected = false;

        try {
            crService.getCashReceipts(TEST_UNIT_NAME, (String[]) null);
        }
        catch (IllegalArgumentException e) {
            failedAsExpected = true;
        }

        assertTrue(failedAsExpected);
    }

    public final void testGetCashReceipts2_emptyStatii() {
        boolean failedAsExpected = false;

        String[] emptyStatii = {};
        try {
            crService.getCashReceipts(TEST_UNIT_NAME, emptyStatii);
        }
        catch (IllegalArgumentException e) {
            failedAsExpected = true;
        }

        assertTrue(failedAsExpected);
    }

    public final void testGetCashReceipts2_blankStatii() {
        boolean failedAsExpected = false;

        String[] blankStatii = { "  " };
        try {
            crService.getCashReceipts(TEST_UNIT_NAME, blankStatii);
        }
        catch (IllegalArgumentException e) {
            failedAsExpected = true;
        }

        assertTrue(failedAsExpected);
    }

    // TODO: once we stop returning default campus code for unknown unit, need tests for unknown unit

    public final void testGetCashReceipts2_knownVerificationUnit_noVerifiedReceipts() {
        final String workgroup = TEST_UNIT_NAME;

        // clean up before testing
        denatureCashReceipts(workgroup);

        List receipts = crService.getCashReceipts(workgroup, VSTATII);
        assertEquals(0, receipts.size());
    }

    public final void testGetCashReceipts2_knownVerificationUnit_noInterimReceipts() {
        final String workgroup = TEST_UNIT_NAME;

        // clean up before testing
        denatureCashReceipts(workgroup);

        List receipts = crService.getCashReceipts(workgroup, ISTATII);
        assertEquals(0, receipts.size());
    }


    @TestsWorkflowViaDatabase
    public final void testGetCashReceipts2_knownVerificationUnit_interimReceipts() throws Exception {
        final String workgroup = TEST_UNIT_NAME;

        // clean up before testing
        denatureCashReceipts(workgroup);

        // create some CRs
        changeCurrentUser(UserNameFixture.INEFF);
        CashReceiptDocument cr1 = buildCashReceiptDoc(workgroup, "ww2 CRST cr1", Constants.DocumentStatusCodes.CashReceipt.INTERIM, new KualiDecimal("101.01"), new KualiDecimal("898.99"));

        CashReceiptDocument cr2 = buildCashReceiptDoc(workgroup, "ww2 CRST cr2", Constants.DocumentStatusCodes.CashReceipt.INTERIM, new KualiDecimal("212.12"), new KualiDecimal("787.87"));


        // verify that there are only interim CRs
        List vreceipts = crService.getCashReceipts(workgroup, VSTATII);
        assertEquals(0, vreceipts.size());

        List ireceipts = crService.getCashReceipts(workgroup, ISTATII);
        assertEquals(2, ireceipts.size());

        // clean up afterwards
        denatureCashReceipts(workgroup);
    }

    @TestsWorkflowViaDatabase
    public final void testGetCashReceipts2_knownVerificationUnit_verifiedReceipts() throws Exception {
        final String workgroup = TEST_UNIT_NAME;

        // clean up before testing
        denatureCashReceipts(workgroup);

        // create some CRs
        changeCurrentUser(UserNameFixture.INEFF);
        CashReceiptDocument cr1 = buildCashReceiptDoc(workgroup, "ww2 CRST cr1", Constants.DocumentStatusCodes.CashReceipt.VERIFIED, new KualiDecimal("101.01"), new KualiDecimal("898.99"));

        CashReceiptDocument cr2 = buildCashReceiptDoc(workgroup, "ww2 CRST cr2", Constants.DocumentStatusCodes.CashReceipt.VERIFIED, new KualiDecimal("212.12"), new KualiDecimal("787.87"));


        // verify that there are only verified CRs
        List ireceipts = crService.getCashReceipts(workgroup, ISTATII);
        assertEquals(0, ireceipts.size());

        List vreceipts = crService.getCashReceipts(workgroup, VSTATII);
        assertEquals(2, vreceipts.size());

        // clean up afterwards
        denatureCashReceipts(workgroup);
    }

    @TestsWorkflowViaDatabase
    public final void testGetCashReceipts2_knownVerificationUnit_mixedReceipts() throws Exception {
        final String workgroup = TEST_UNIT_NAME;

        // clean up before testing
        denatureCashReceipts(workgroup);

        // create some CRs
        changeCurrentUser(UserNameFixture.INEFF);
        CashReceiptDocument cr1 = buildCashReceiptDoc(workgroup, "ww2 CRST cr1", Constants.DocumentStatusCodes.CashReceipt.INTERIM, new KualiDecimal("101.01"), new KualiDecimal("898.99"));

        CashReceiptDocument cr2 = buildCashReceiptDoc(workgroup, "ww2 CRST cr2", Constants.DocumentStatusCodes.CashReceipt.VERIFIED, new KualiDecimal("212.12"), new KualiDecimal("787.87"));


        // verify that there are some of each
        List ireceipts = crService.getCashReceipts(workgroup, ISTATII);
        assertEquals(1, ireceipts.size());

        List vreceipts = crService.getCashReceipts(workgroup, VSTATII);
        assertEquals(1, vreceipts.size());

        List mixedReceipts = crService.getCashReceipts(workgroup, BOTH_STATII);
        assertEquals(2, mixedReceipts.size());

        // clean up afterwards
        denatureCashReceipts(workgroup);
    }


    private void denatureCashReceipts(String workgroupName) {
        List verifiedReceipts = crService.getCashReceipts(workgroupName, BOTH_STATII);

        for (Iterator i = verifiedReceipts.iterator(); i.hasNext();) {
            CashReceiptDocument receipt = (CashReceiptDocument) i.next();
            receipt.getDocumentHeader().setFinancialDocumentStatusCode("Z");
            docService.updateDocument(receipt);
        }
    }

    private CashReceiptDocument buildCashReceiptDoc(String workgroupName, String description, String status, KualiDecimal cashAmount, KualiDecimal checkAmount) throws WorkflowException {
        CashReceiptDocument crDoc = (CashReceiptDocument) docService.getNewDocument(CashReceiptDocument.class);

        crDoc.getDocumentHeader().setFinancialDocumentDescription(description);
        crDoc.getDocumentHeader().setFinancialDocumentStatusCode(status);

        crDoc.setCheckEntryMode(CashReceiptDocument.CHECK_ENTRY_TOTAL);
        crDoc.setTotalCashAmount(cashAmount);
        crDoc.setTotalCheckAmount(checkAmount);
        
        crDoc.setCampusLocationCode(crService.getCampusCodeForCashReceiptVerificationUnit(workgroupName));
        
        crDoc.addSourceAccountingLine(CashReceiptFamilyTestUtil.buildSourceAccountingLine(crDoc.getFinancialDocumentNumber(), crDoc.getPostingYear(), crDoc.getNextSourceLineNumber()));

        try {
            docService.saveDocument(crDoc);
        }
        catch(ValidationException e) {
            // If the business rule evaluation fails then give us more info for debugging this test.
            fail(e.getMessage() + ", " + GlobalVariables.getErrorMap());
        }
        return crDoc;
    }
}
