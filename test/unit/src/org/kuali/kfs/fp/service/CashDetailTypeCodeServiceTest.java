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
package org.kuali.module.financial.service;

import java.util.ArrayList;

import org.kuali.core.util.SpringServiceLocator;
import org.kuali.module.financial.bo.CashDetailTypeCode;
import org.kuali.module.financial.service.impl.CashDetailTypeCodeServiceImpl;
import org.kuali.test.KualiTestBase;
import org.kuali.test.WithTestSpringContext;

/**
 * This class tests the CashDetailTypeCode service.
 * 
 * 
 */
@WithTestSpringContext
public class CashDetailTypeCodeServiceTest extends KualiTestBase {
    private ArrayList validCashDetailTypeCodes;

    private CashDetailTypeCodeService cashDetailTypeCodeService;

    protected void setUp() throws Exception {
        super.setUp();
        this.cashDetailTypeCodeService = SpringServiceLocator.getCashDetailTypeCodeService();
        // this.validCashDetailTypeCodes = (ArrayList)
        // SpringServiceLocator.getBusinessObjectService().findAll(CashDetailTypeCodeService.class);
        // TODO - uncomment the above line of code when the database table for cash detail type codes is put in place and populated;
        // remove the line below
        populateValidCashDetailTypeCodes();
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetCashReceiptTypeCode() {
        assertEquals(true, validCashDetailTypeCodes.contains(cashDetailTypeCodeService.getCashReceiptCheckTypeCode()));
    }

    private CashDetailTypeCode getDummyInstance(String cashDetailTypeCodeCode) {
        CashDetailTypeCode cashDetailTypeCode = new CashDetailTypeCode();
        cashDetailTypeCode.setCode(cashDetailTypeCodeCode);
        return cashDetailTypeCode;
    }

    /**
     * TODO Remove this once the database table is in place.
     */
    private void populateValidCashDetailTypeCodes() {
        this.validCashDetailTypeCodes = new ArrayList();
        this.validCashDetailTypeCodes.add(getDummyInstance(CashDetailTypeCodeServiceImpl.CASH_RECEIPT_CHECK));
    }
}
