/*
 * Copyright 2006-2007 The Kuali Foundation.
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

import java.util.HashMap;
import java.util.Map;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DateTimeService;
import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.context.KualiTestBase;
import org.kuali.kfs.context.SpringContext;
import org.kuali.module.financial.bo.CashDrawer;
import org.kuali.test.ConfigureContext;

/**
 * This class tests the Check service.
 */
@ConfigureContext
public class CashDrawerServiceTest extends KualiTestBase {
    private static final String BLANK_WORKGROUP_NAME = "";
    private static final String VALID_WORKGROUP_NAME = "testWorkgroup";
    private static final String BLANK_DOC_ID = "    ";
    private static final String VALID_DOC_ID = "1234";
    private static final String OTHER_DOC_ID = "4321";

    public final void testOpenCashDrawer_blankWorkgroup() {
        boolean failedAsExpected = false;

        try {
            SpringContext.getBean(CashDrawerService.class).openCashDrawer(BLANK_WORKGROUP_NAME, VALID_DOC_ID);
        }
        catch (IllegalArgumentException e) {
            failedAsExpected = true;
        }

        assertTrue(failedAsExpected);
    }

    public final void testOpenCashDrawer_blankDocId() {
        boolean failedAsExpected = false;

        try {
            SpringContext.getBean(CashDrawerService.class).openCashDrawer(VALID_WORKGROUP_NAME, BLANK_DOC_ID);
        }
        catch (IllegalArgumentException e) {
            failedAsExpected = true;
        }

        assertTrue(failedAsExpected);
    }


    public final void testOpenCashDrawer_nonexistent() {
        final String workgroup = VALID_WORKGROUP_NAME;

        // make sure it doesn't exist
        deleteCashDrawer(workgroup);

        // open it
        SpringContext.getBean(CashDrawerService.class).openCashDrawer(workgroup, VALID_DOC_ID);

        // verify that it is open
        CashDrawer drawer = SpringContext.getBean(CashDrawerService.class).getByWorkgroupName(workgroup, false);
        assertEquals(KFSConstants.CashDrawerConstants.STATUS_OPEN, drawer.getStatusCode());
        assertEquals(VALID_DOC_ID, drawer.getReferenceFinancialDocumentNumber());
    }

    public final void testOpenCashDrawer() {
        final String workgroup = VALID_WORKGROUP_NAME;

        // make sure it exists
        createCashDrawer(workgroup);

        // open it
        SpringContext.getBean(CashDrawerService.class).openCashDrawer(workgroup, VALID_DOC_ID);

        // make sure it is open
        CashDrawer drawer = SpringContext.getBean(CashDrawerService.class).getByWorkgroupName(workgroup, false);
        assertEquals(KFSConstants.CashDrawerConstants.STATUS_OPEN, drawer.getStatusCode());
        assertEquals(VALID_DOC_ID, drawer.getReferenceFinancialDocumentNumber());
    }


    public final void testCloseCashDrawer_blankWorkgroup() {
        boolean failedAsExpected = false;

        try {
            SpringContext.getBean(CashDrawerService.class).closeCashDrawer(BLANK_WORKGROUP_NAME);
        }
        catch (IllegalArgumentException e) {
            failedAsExpected = true;
        }

        assertTrue(failedAsExpected);
    }

    public final void testCloseCashDrawer_nonexistent() {
        final String workgroup = VALID_WORKGROUP_NAME;

        // make sure it doesn't exist
        deleteCashDrawer(workgroup);

        // close it
        SpringContext.getBean(CashDrawerService.class).closeCashDrawer(workgroup);

        // verify that it is closed
        CashDrawer drawer = SpringContext.getBean(CashDrawerService.class).getByWorkgroupName(workgroup, false);
        assertEquals(KFSConstants.CashDrawerConstants.STATUS_CLOSED, drawer.getStatusCode());
        assertNull(drawer.getReferenceFinancialDocumentNumber());
    }

    public final void testCloseCashDrawer_existent() {
        final String workgroup = VALID_WORKGROUP_NAME;

        // make sure it exists
        createCashDrawer(workgroup);

        // close it
        SpringContext.getBean(CashDrawerService.class).closeCashDrawer(workgroup);

        // make sure it is closed
        CashDrawer drawer = SpringContext.getBean(CashDrawerService.class).getByWorkgroupName(workgroup, false);
        assertEquals(KFSConstants.CashDrawerConstants.STATUS_CLOSED, drawer.getStatusCode());
        assertNull(drawer.getReferenceFinancialDocumentNumber());
    }


    public final void testLockCashDrawer_blankWorkgroup() {
        boolean failedAsExpected = false;

        try {
            SpringContext.getBean(CashDrawerService.class).lockCashDrawer(BLANK_WORKGROUP_NAME, VALID_DOC_ID);
        }
        catch (IllegalArgumentException e) {
            failedAsExpected = true;
        }

        assertTrue(failedAsExpected);
    }

    public final void testLockCashDrawer_nonexistent() {
        final String workgroup = VALID_WORKGROUP_NAME;

        // make sure it doesn't exist
        deleteCashDrawer(workgroup);

        // lock it
        boolean failedAsExpected = false;
        try {
            SpringContext.getBean(CashDrawerService.class).lockCashDrawer(workgroup, VALID_DOC_ID);
        }
        catch (IllegalStateException e) {
            failedAsExpected = true;
        }

        assertTrue(failedAsExpected);
    }

    public final void testLockCashDrawer_closed() {
        final String workgroup = VALID_WORKGROUP_NAME;

        // close it
        SpringContext.getBean(CashDrawerService.class).closeCashDrawer(workgroup);

        // lock it
        boolean failedAsExpected = false;
        try {
            SpringContext.getBean(CashDrawerService.class).lockCashDrawer(workgroup, VALID_DOC_ID);
        }
        catch (IllegalStateException e) {
            failedAsExpected = true;
        }

        assertTrue(failedAsExpected);
    }

    public final void testLockCashDrawer_alreadyLocked() {
        final String workgroup = VALID_WORKGROUP_NAME;

        // open it
        SpringContext.getBean(CashDrawerService.class).openCashDrawer(workgroup, VALID_DOC_ID);

        // lock it twice
        SpringContext.getBean(CashDrawerService.class).lockCashDrawer(workgroup, VALID_DOC_ID);

        boolean failedAsExpected = false;
        try {
            SpringContext.getBean(CashDrawerService.class).lockCashDrawer(workgroup, VALID_DOC_ID);
        }
        catch (IllegalStateException e) {
            failedAsExpected = true;
        }

        assertTrue(failedAsExpected);
    }

    public final void testLockCashDrawer_openedByDifferentDocument() {
        final String workgroup = VALID_WORKGROUP_NAME;

        // open it
        SpringContext.getBean(CashDrawerService.class).openCashDrawer(workgroup, OTHER_DOC_ID);

        // lock it
        boolean failedAsExpected = false;
        try {
            SpringContext.getBean(CashDrawerService.class).lockCashDrawer(workgroup, VALID_DOC_ID);
        }
        catch (IllegalStateException e) {
            failedAsExpected = true;
        }

        assertTrue(failedAsExpected);
    }


    public final void testLockCashDrawer_open() {
        final String workgroup = VALID_WORKGROUP_NAME;

        // open it
        SpringContext.getBean(CashDrawerService.class).openCashDrawer(workgroup, VALID_DOC_ID);

        // lock it
        SpringContext.getBean(CashDrawerService.class).lockCashDrawer(workgroup, VALID_DOC_ID);

        // verify that it is locked
        CashDrawer drawer = SpringContext.getBean(CashDrawerService.class).getByWorkgroupName(workgroup, false);
        assertEquals(KFSConstants.CashDrawerConstants.STATUS_LOCKED, drawer.getStatusCode());
        assertEquals(VALID_DOC_ID, drawer.getReferenceFinancialDocumentNumber());
    }


    public final void testUnlockCashDrawer_blankWorkgroup() {
        boolean failedAsExpected = false;

        try {
            SpringContext.getBean(CashDrawerService.class).unlockCashDrawer(BLANK_WORKGROUP_NAME, VALID_DOC_ID);
        }
        catch (IllegalArgumentException e) {
            failedAsExpected = true;
        }

        assertTrue(failedAsExpected);
    }

    public final void testUnlockCashDrawer_nonexistent() {
        final String workgroup = VALID_WORKGROUP_NAME;

        // make sure it doesn't exist
        deleteCashDrawer(workgroup);

        // lock it
        boolean failedAsExpected = false;
        try {
            SpringContext.getBean(CashDrawerService.class).unlockCashDrawer(workgroup, VALID_DOC_ID);
        }
        catch (IllegalStateException e) {
            failedAsExpected = true;
        }

        assertTrue(failedAsExpected);
    }

    public final void testUnlockCashDrawer_open() {
        final String workgroup = VALID_WORKGROUP_NAME;

        // open it
        SpringContext.getBean(CashDrawerService.class).openCashDrawer(workgroup, VALID_DOC_ID);

        // unlock it
        boolean failedAsExpected = false;
        try {
            SpringContext.getBean(CashDrawerService.class).unlockCashDrawer(workgroup, VALID_DOC_ID);
        }
        catch (IllegalStateException e) {
            failedAsExpected = true;
        }

        assertTrue(failedAsExpected);
    }

    public final void testUnlockCashDrawer_locked() {
        final String workgroup = VALID_WORKGROUP_NAME;

        // lock it
        SpringContext.getBean(CashDrawerService.class).openCashDrawer(workgroup, VALID_DOC_ID);
        SpringContext.getBean(CashDrawerService.class).lockCashDrawer(workgroup, VALID_DOC_ID);

        // unlock it
        SpringContext.getBean(CashDrawerService.class).unlockCashDrawer(workgroup, VALID_DOC_ID);

        // verify that it is unlocked
        CashDrawer drawer = SpringContext.getBean(CashDrawerService.class).getByWorkgroupName(workgroup, false);
        assertEquals(KFSConstants.CashDrawerConstants.STATUS_OPEN, drawer.getStatusCode());
        assertEquals(VALID_DOC_ID, drawer.getReferenceFinancialDocumentNumber());
    }


    public final void testUnlockCashDrawer_lockedByDifferentDocumentId() {
        final String workgroup = VALID_WORKGROUP_NAME;

        // lock it
        SpringContext.getBean(CashDrawerService.class).openCashDrawer(workgroup, OTHER_DOC_ID);
        SpringContext.getBean(CashDrawerService.class).lockCashDrawer(workgroup, OTHER_DOC_ID);

        // unlock it
        boolean failedAsExpected = false;
        try {
            SpringContext.getBean(CashDrawerService.class).unlockCashDrawer(workgroup, VALID_DOC_ID);
        }
        catch (IllegalStateException e) {
            failedAsExpected = true;
        }

        assertTrue(failedAsExpected);
    }


    public final void testGetByWorkgroupName_blankWorkgroup() {
        boolean failedAsExpected = false;

        try {
            SpringContext.getBean(CashDrawerService.class).getByWorkgroupName("  ", false);
        }
        catch (IllegalArgumentException e) {
            failedAsExpected = true;
        }

        assertTrue(failedAsExpected);
    }

    public final void testGetByWorkgroupName_nonexistentWorkgroup() {
        CashDrawer d = SpringContext.getBean(CashDrawerService.class).getByWorkgroupName("foo", false);

        assertNull(d);
    }

    public final void testGetByWorkgroupName_existingWorkgroup() {
        final String workgroup = VALID_WORKGROUP_NAME;

        createCashDrawer(VALID_WORKGROUP_NAME);

        CashDrawer d = SpringContext.getBean(CashDrawerService.class).getByWorkgroupName(workgroup, false);

        assertNotNull(d);
        assertEquals(workgroup, d.getWorkgroupName());
        assertEquals(KFSConstants.CashDrawerConstants.STATUS_CLOSED, d.getStatusCode());
    }


    public final void testLifeCycle() {
        final String RANDOM_WORKGROUP_NAME = "testWorkgroup-" + SpringContext.getBean(DateTimeService.class).getCurrentDate().getTime();

        boolean deleteSucceeded = false;

        CashDrawer preExisting = SpringContext.getBean(CashDrawerService.class).getByWorkgroupName(RANDOM_WORKGROUP_NAME, false);
        assertNull(preExisting);

        CashDrawer created = new CashDrawer();
        created.setWorkgroupName(RANDOM_WORKGROUP_NAME);
        created.setStatusCode(KFSConstants.CashDrawerConstants.STATUS_CLOSED);

        CashDrawer retrieved = null;
        try {
            SpringContext.getBean(BusinessObjectService.class).save(created);

            retrieved = SpringContext.getBean(CashDrawerService.class).getByWorkgroupName(RANDOM_WORKGROUP_NAME, false);
            assertNotNull(retrieved);

            // compare
            assertEquals(created.getWorkgroupName(), retrieved.getWorkgroupName());
            assertEquals(created.getStatusCode(), retrieved.getStatusCode());
            assertNull(retrieved.getReferenceFinancialDocumentNumber());
        }
        finally {
            // delete it
            if (retrieved != null) {
                SpringContext.getBean(BusinessObjectService.class).delete(retrieved);
            }
        }

        // verify that the delete succeeded
        retrieved = SpringContext.getBean(CashDrawerService.class).getByWorkgroupName(RANDOM_WORKGROUP_NAME, false);
        assertNull(retrieved);
    }


    // utility methods
    private void deleteCashDrawer(String workgroupName) {
        Map deleteCriteria = new HashMap();
        deleteCriteria.put("workgroupName", workgroupName);
        SpringContext.getBean(BusinessObjectService.class).deleteMatching(CashDrawer.class, deleteCriteria);
    }

    private void createCashDrawer(String workgroupName) {
        deleteCashDrawer(workgroupName);

        CashDrawer cd = new CashDrawer();
        cd.setWorkgroupName(workgroupName);
        cd.setStatusCode(KFSConstants.CashDrawerConstants.STATUS_CLOSED);
        SpringContext.getBean(BusinessObjectService.class).save(cd);
    }
}