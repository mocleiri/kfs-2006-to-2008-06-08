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
package org.kuali.module.chart.service;

import org.kuali.core.service.KualiCodeService;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.module.chart.bo.FundGroup;
import org.kuali.test.KualiTestBase;
import org.kuali.test.WithTestSpringContext;

/**
 * This class tests the FundGroup service.
 * 
 * 
 */

@WithTestSpringContext
public class FundGroupServiceTest extends KualiTestBase {

    KualiCodeService kualiCodeService;

    protected void setUp() throws Exception {
        super.setUp();
        kualiCodeService = SpringServiceLocator.getKualiCodeService();
    }

    public void testGetByCode_knownCode1() {
        FundGroup fundGroup = (FundGroup) kualiCodeService.getByCode(FundGroup.class, "LF");
        assertEquals("Known code does not produce expected name.", "LOAN FUNDS", fundGroup.getName());
    }

    public void testGetByCode_knownName1() {
        FundGroup fundGroup = (FundGroup) kualiCodeService.getByName(FundGroup.class, "LOAN FUNDS");
        assertEquals("Known code does not produce expected name.", "LF", fundGroup.getCode());
    }

    public void testGetByCode_knownCode2() {
        FundGroup fundGroup = (FundGroup) kualiCodeService.getByCode(FundGroup.class, "AF");
        assertEquals("Known code does not produce expected name.", "AGENCY FUNDS", fundGroup.getName());
    }

    public void testGetByCode_knownName2() {
        FundGroup fundGroup = (FundGroup) kualiCodeService.getByName(FundGroup.class, "AGENCY FUNDS");
        assertEquals("Known code does not produce expected name.", "AF", fundGroup.getCode());
    }

    public void testGetByCode_unknownCode() {
        FundGroup fundGroup = (FundGroup) kualiCodeService.getByCode(FundGroup.class, "XX");
        assertNull("Known-bad code does not produce expected null object.", fundGroup);
    }

    public void testGetByName_unknownName() {
        FundGroup fundGroup = (FundGroup) kualiCodeService.getByName(FundGroup.class, "The Cat In the Hat");
        assertNull("Known-bad name does not produce expected null object.", fundGroup);
    }
}
