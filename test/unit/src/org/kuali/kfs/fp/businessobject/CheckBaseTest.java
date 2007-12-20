/*
 * Copyright 2005-2007 The Kuali Foundation.
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
package org.kuali.module.financial.bo;


import java.sql.Date;

import org.kuali.core.service.DateTimeService;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kfs.context.KualiTestBase;
import org.kuali.kfs.context.SpringContext;
import org.kuali.test.ConfigureContext;

/**
 * This class...
 */
@ConfigureContext
public class CheckBaseTest extends KualiTestBase {
    private CheckBase crchk = null;
    private static final KualiDecimal AMOUNT = new KualiDecimal("100.27");
    private static final String GUID = "123456789012345678901234567890123456";
    private static final Long VER_NBR = new Long(1);
    private static Date date;
    private static final String CHECK_NUMBER = "123456";
    private static final String DESCRIPTION = "Description 123.";
    private static final String DOC_HDR_ID = "999999";
    private static final Integer SEQ_ID = new Integer(1);
    private static final Integer DEPOSIT_LINE_NUMBER = new Integer(1);
    private static final String DOCUMENT_TYPE = "CR";
    private static final String CASHIERING_SOURCE = "R";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        date = new Date(SpringContext.getBean(DateTimeService.class).getCurrentDate().getTime());
        crchk = new CheckBase();
        crchk.setAmount(AMOUNT);
        crchk.setCheckDate(date);
        crchk.setCheckNumber(CHECK_NUMBER);
        crchk.setDescription(DESCRIPTION);
        crchk.setDocumentNumber(DOC_HDR_ID);
        crchk.setFinancialDocumentDepositLineNumber(DEPOSIT_LINE_NUMBER);
        crchk.setObjectId(GUID);
        crchk.setSequenceId(SEQ_ID);
        crchk.setVersionNumber(VER_NBR);
        crchk.setCashieringRecordSource(CASHIERING_SOURCE);
        crchk.setFinancialDocumentTypeCode(DOCUMENT_TYPE);
    }

    public void testCashReceiptCheckPojo() {
        assertEquals(AMOUNT, crchk.getAmount());
        assertEquals(date, crchk.getCheckDate());
        assertEquals(CHECK_NUMBER, crchk.getCheckNumber());
        assertEquals(DESCRIPTION, crchk.getDescription());
        assertEquals(DOC_HDR_ID, crchk.getDocumentNumber());
        assertEquals(DEPOSIT_LINE_NUMBER, crchk.getFinancialDocumentDepositLineNumber());
        assertEquals(GUID, crchk.getObjectId());
        assertEquals(SEQ_ID, crchk.getSequenceId());
        assertEquals(VER_NBR, crchk.getVersionNumber());
        assertEquals(DOCUMENT_TYPE, crchk.getFinancialDocumentTypeCode());
        assertEquals(CASHIERING_SOURCE, crchk.getCashieringRecordSource());
    }
}