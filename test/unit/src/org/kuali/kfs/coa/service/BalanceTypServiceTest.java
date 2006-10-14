/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/test/unit/src/org/kuali/kfs/coa/service/BalanceTypServiceTest.java,v $
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
package org.kuali.module.chart.service;

import static org.kuali.core.util.SpringServiceLocator.getBusinessObjectService;

import java.util.HashMap;

import org.kuali.module.chart.bo.codes.BalanceTyp;
import org.kuali.test.KualiTestBase;
import org.kuali.test.WithTestSpringContext;

/**
 * This class tests the BalanceType service.
 * 
 * 
 */
@WithTestSpringContext
public class BalanceTypServiceTest extends KualiTestBase {
    private static final boolean ACTIVE = true;
    private static final boolean BAL_TYPE_ENCUMB = true;
    private static final String BAL_TYPE_CODE = "ZZ";
    private static final String BAL_TYPE_NAME = "Z NAME";
    private static final String GUID = "123456789012345678901234567890123456";
    private static final Long VER_NBR = new Long(1);
    private static final boolean OFFSET_GEN = false;
    private static final String SHORT_NAME = "Z SHORT";

    private static final String ACTUAL_BAL_TYPE_CODE = "AC";

    public void testCreateLookupDelete1() {
        // create
        BalanceTyp bal = new BalanceTyp();
        bal.setActive(true);
        bal.setFinBalanceTypeEncumIndicator(true);
        bal.setCode(BAL_TYPE_CODE);
        bal.setName(BAL_TYPE_NAME);
        bal.setObjectId(GUID);
        bal.setFinancialOffsetGenerationIndicator(OFFSET_GEN);
        bal.setFinancialBalanceTypeShortNm(SHORT_NAME);
        bal.setVersionNumber(VER_NBR);

        getBusinessObjectService().save(bal);

        // lookup
        HashMap map = new HashMap();
        map.put("code", BAL_TYPE_CODE);
        BalanceTyp bal2 = (BalanceTyp) getBusinessObjectService().findByPrimaryKey(BalanceTyp.class, map);
        assertNotNull("Should be a valid object.", bal2);
        assertEquals("Known-good code results in expected returned Name.", BAL_TYPE_NAME, bal2.getName());

        // delete
        getBusinessObjectService().delete(bal2);

        // try to lookup again
        map = new HashMap();
        map.put("code", BAL_TYPE_CODE);
        BalanceTyp bal3 = (BalanceTyp) getBusinessObjectService().findByPrimaryKey(BalanceTyp.class, map);
        assertNull("Shouldn't be a valid object.", bal3);
    }

    /*
     * Disable this test because no data in database yet RO 9-22-05
     * 
     * public void testActualBalanceTypeLookup() { //test known-good byCode BalanceTyp bal =
     * SpringServiceLocator.getBalanceTypService().getActualBalanceTyp(); assertNotNull("Should be a valid object.", bal);
     * assertEquals(ACTUAL_BAL_TYPE_CODE, bal.getCode()); }
     */
}
