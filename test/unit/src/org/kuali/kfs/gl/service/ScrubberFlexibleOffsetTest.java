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
package org.kuali.module.gl.service;

import java.util.Calendar;

import org.kuali.Constants;
import org.kuali.test.WithTestSpringContext;
import org.kuali.module.gl.GLSpringBeansRegistry;
import org.kuali.module.gl.OriginEntryTestBase;
import org.kuali.module.gl.bo.OriginEntrySource;

/**
 * Test Flexible Offset in the scrubber
 * 
 * 
 */
@WithTestSpringContext
public class ScrubberFlexibleOffsetTest extends OriginEntryTestBase {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ScrubberFlexibleOffsetTest.class);

    private ScrubberService scrubberService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        scrubberService = (ScrubberService) beanFactory.getBean(GLSpringBeansRegistry.glScrubberService);

        // Get the test date time service so we can specify the date/time of the run
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.YEAR, 2006);
        
        // since the cutoff time  is set to 10am (https://test.kuali.org/confluence/display/KFSP1/Scrubber+cutoff+time+configuration)
        // we want to ensure that the time is always after that time so the cutoff algorithm is not invoked
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        
        date = c.getTime();
        dateTimeService.currentDate = date;
    }

    /**
     * Test to make sure that flexible offset is off when the flag is off
     * 
     * @throws Exception
     */
    public void testNonFlexibleOffsetGeneration() throws Exception {

        resetFlexibleOffsetEnableFlag(false);

        updateDocTypeForScrubberOffsetGeneration();
        setOffsetAccounts();

        String[] input = new String[] { "2007BA9120656-----4190---ACEX02ID0101NOFLEX001     00000TEST FLEXIBLE OFFSET - NO FLEX                    2000.00D2006-01-01          ----------                                       ", "2007BA6044900-----4190---ACEX02ID0101NOFLEX002     00000TEST FLEXIBLE OFFSET - FLEX                       1000.00D2006-01-01          ----------                                       ", "2007BL1023200-----4190---ACEX02ID0101NOFLEX003     00000TEST FLEXIBLE OFFSET - FLEX                       3000.00D2006-01-01          ----------                                       ", "2007BL1023200-----7030---ACEX02ID0101NOFLEX004     00000TEST FLEXIBLE OFFSET - FLEX                       3500.00D2006-01-01          ----------                                       ", "2007BL2331473-----4190---ACEX02ID0101NOFLEX005     00000TEST FLEXIBLE OFFSET - FLEX                       4000.00D2006-01-01          ----------                                       ", };

        EntryHolder[] output = new EntryHolder[] { new EntryHolder(OriginEntrySource.BACKUP, "2007BA9120656-----4190---ACEX02ID0101NOFLEX001     00000TEST FLEXIBLE OFFSET - NO FLEX                    2000.00D2006-01-01          ----------                                       "), new EntryHolder(OriginEntrySource.BACKUP, "2007BA6044900-----4190---ACEX02ID0101NOFLEX002     00000TEST FLEXIBLE OFFSET - FLEX                       1000.00D2006-01-01          ----------                                       "), new EntryHolder(OriginEntrySource.BACKUP, "2007BL1023200-----4190---ACEX02ID0101NOFLEX003     00000TEST FLEXIBLE OFFSET - FLEX                       3000.00D2006-01-01          ----------                                       "), new EntryHolder(OriginEntrySource.BACKUP, "2007BL1023200-----7030---ACEX02ID0101NOFLEX004     00000TEST FLEXIBLE OFFSET - FLEX                       3500.00D2006-01-01          ----------                                       "),
                new EntryHolder(OriginEntrySource.BACKUP, "2007BL2331473-----4190---ACEX02ID0101NOFLEX005     00000TEST FLEXIBLE OFFSET - FLEX                       4000.00D2006-01-01          ----------                                       "), new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BA9120656-----4190---ACEX02ID0101NOFLEX001     00000TEST FLEXIBLE OFFSET - NO FLEX                    2000.00D2006-01-01          ----------                                       "), new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BA9120656-----8000---ACAS02ID0101NOFLEX001     00000GENERATED OFFSET                                  2000.00C2006-01-01          ----------                                       "), new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BA6044900-----4190---ACEX02ID0101NOFLEX002     00000TEST FLEXIBLE OFFSET - FLEX                       1000.00D2006-01-01          ----------                                       "),
                new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BA6044900-----8000---ACAS02ID0101NOFLEX002     00000GENERATED OFFSET                                  1000.00C2006-01-01          ----------                                       "), new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BL1023200-----4190---ACEX02ID0101NOFLEX003     00000TEST FLEXIBLE OFFSET - FLEX                       3000.00D2006-01-01          ----------                                       "), new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BL1023200-----8000---ACAS02ID0101NOFLEX003     00000GENERATED OFFSET                                  3000.00C2006-01-01          ----------                                       "), new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BL9520004-----8611---ACAS02ID0101NOFLEX004     00000GENERATED CAPITALIZATION                          3500.00D2006-01-01          ----------                                       "),
                new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BL9520004-----9899---ACFB02ID0101NOFLEX004     00000GENERATED CAPITALIZATION                          3500.00C2006-01-01          ----------                                       "), new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BL1023200-----7030---ACEX02ID0101NOFLEX004     00000TEST FLEXIBLE OFFSET - FLEX                       3500.00D2006-01-01          ----------                                       "), new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BL1023200-----8000---ACAS02ID0101NOFLEX004     00000GENERATED OFFSET                                  3500.00C2006-01-01          ----------                                       "), new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BL2331473-----4190---ACEX02ID0101NOFLEX005     00000TEST FLEXIBLE OFFSET - FLEX                       4000.00D2006-01-01          ----------                                       "),
                new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BL2331473-----8000---ACAS02ID0101NOFLEX005     00000GENERATED OFFSET                                  4000.00C2006-01-01          ----------                                       "), };

        scrub(input);
        assertOriginEntries(4, output);
    }

    /**
     * Test it when the flag is on
     * 
     * @throws Exception
     */
    // This test works in Eclipse, but not in Anthil
//    public void testFlexibleOffsetGeneration() throws Exception {
//
//        resetFlexibleOffsetEnableFlag(true);
//
//        updateDocTypeForScrubberOffsetGeneration();
//        setOffsetAccounts();
//
//        String[] input = new String[] { 
//                "2007BA9120656-----4190---ACEX02ID0101NOFLEX00100000TEST FLEXIBLE OFFSET - NO FLEX                    2000.00D2006-01-01          ----------                                  ", 
//                "2007BA6044900-----4190---ACEX02ID0101NOFLEX00200000TEST FLEXIBLE OFFSET - FLEX                       1000.00D2006-01-01          ----------                                  ", 
//                "2007BL1023200-----4190---ACEX02ID0101NOFLEX00300000TEST FLEXIBLE OFFSET - FLEX                       3000.00D2006-01-01          ----------                                  ", 
//                "2007BL1023200-----7030---ACEX02ID0101NOFLEX00400000TEST FLEXIBLE OFFSET - FLEX                       3500.00D2006-01-01          ----------                                  ", 
//                "2007BL2331473-----4190---ACEX02ID0101NOFLEX00500000TEST FLEXIBLE OFFSET - FLEX                       4000.00D2006-01-01          ----------                                  ", 
//        };
//
//        EntryHolder[] output = new EntryHolder[] { 
//                new EntryHolder(OriginEntrySource.BACKUP, "2007BA9120656-----4190---ACEX02ID0101NOFLEX00100000TEST FLEXIBLE OFFSET - NO FLEX                    2000.00D2006-01-01          ----------                                  "), 
//                new EntryHolder(OriginEntrySource.BACKUP, "2007BA6044900-----4190---ACEX02ID0101NOFLEX00200000TEST FLEXIBLE OFFSET - FLEX                       1000.00D2006-01-01          ----------                                  "), 
//                new EntryHolder(OriginEntrySource.BACKUP, "2007BL1023200-----4190---ACEX02ID0101NOFLEX00300000TEST FLEXIBLE OFFSET - FLEX                       3000.00D2006-01-01          ----------                                  "), 
//                new EntryHolder(OriginEntrySource.BACKUP, "2007BL1023200-----7030---ACEX02ID0101NOFLEX00400000TEST FLEXIBLE OFFSET - FLEX                       3500.00D2006-01-01          ----------                                  "),
//                new EntryHolder(OriginEntrySource.BACKUP, "2007BL2331473-----4190---ACEX02ID0101NOFLEX00500000TEST FLEXIBLE OFFSET - FLEX                       4000.00D2006-01-01          ----------                                  "), 
//                new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BA9120656-----4190---ACEX02ID0101NOFLEX00100000TEST FLEXIBLE OFFSET - NO FLEX                    2000.00D2006-01-01          ----------                                  "), 
//                new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BA9120656-----8000---ACAS02ID0101NOFLEX00100000GENERATED OFFSET                                  2000.00C2006-01-01          ----------                                  "), 
//                new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BA6044900-----4190---ACEX02ID0101NOFLEX00200000TEST FLEXIBLE OFFSET - FLEX                       1000.00D2006-01-01          ----------                                  "),
//                new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BL2231402-----8000---ACAS02ID0101NOFLEX00200000GENERATED OFFSET                                  1000.00C2006-01-01          ----------                                  "), 
//                new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BL1023200-----4190---ACEX02ID0101NOFLEX00300000TEST FLEXIBLE OFFSET - FLEX                       3000.00D2006-01-01          ----------                                  "), 
//                new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BL1023200-----8000---ACAS02ID0101NOFLEX00300000GENERATED OFFSET                                  3000.00C2006-01-01          ----------                                  "), 
//                new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BL9520004-----8611---ACAS02ID0101NOFLEX00400000GENERATED CAPITALIZATION                          3500.00D2006-01-01          ----------                                  "),
//                new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BL2231419-----9899---ACFB02ID0101NOFLEX00400000GENERATED CAPITALIZATION                          3500.00C2006-01-01          ----------                                  "), 
//                new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BL1023200-----7030---ACEX02ID0101NOFLEX00400000TEST FLEXIBLE OFFSET - FLEX                       3500.00D2006-01-01          ----------                                  "), 
//                new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BL1023200-----8000---ACAS02ID0101NOFLEX00400000GENERATED OFFSET                                  3500.00C2006-01-01          ----------                                  "), 
//                new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BL2331473-----4190---ACEX02ID0101NOFLEX00500000TEST FLEXIBLE OFFSET - FLEX                       4000.00D2006-01-01          ----------                                  "),
//                new EntryHolder(OriginEntrySource.SCRUBBER_VALID, "2007BA9120657-----8000---ACAS02ID0101NOFLEX00500000GENERATED OFFSET                                  4000.00C2006-01-01          ----------                                  "),
//        };
//
//        scrub(input);
//        assertOriginEntries(4, output);
//    }

    private void updateDocTypeForScrubberOffsetGeneration() {
        unitTestSqlDao.sqlCommand("update fp_doc_type_t set TRN_SCRBBR_OFST_GEN_IND = 'Y' where fdoc_typ_cd = 'ID01'");
    }

    private void setOffsetAccounts() {
        unitTestSqlDao.sqlCommand("insert into FP_OFST_ACCT_T (FIN_COA_CD,ACCOUNT_NBR,FIN_OFST_OBJ_CD,FIN_OFST_COA_CD,FIN_OFST_ACCT_NBR) values ('BL','2331473','8000','BA','9120657')");
        unitTestSqlDao.sqlCommand("insert into FP_OFST_ACCT_T (FIN_COA_CD,ACCOUNT_NBR,FIN_OFST_OBJ_CD,FIN_OFST_COA_CD,FIN_OFST_ACCT_NBR) values ('BA','6044900','8000','BL','2231402')");
        unitTestSqlDao.sqlCommand("insert into FP_OFST_ACCT_T (FIN_COA_CD,ACCOUNT_NBR,FIN_OFST_OBJ_CD,FIN_OFST_COA_CD,FIN_OFST_ACCT_NBR) values ('BL','1023200','9040','BL','2231419')");
        unitTestSqlDao.sqlCommand("insert into FP_OFST_ACCT_T (FIN_COA_CD,ACCOUNT_NBR,FIN_OFST_OBJ_CD,FIN_OFST_COA_CD,FIN_OFST_ACCT_NBR) values ('BL','9520004','9899','BL','2231419')");
    }

    /**
     * reset the flexible offset enable flag to the given value of the flag
     * 
     * @param flag the given value of the flag.
     */
    private void resetFlexibleOffsetEnableFlag(boolean flag) {
        unitTestSqlDao.sqlCommand("update fs_parm_t set fs_parm_txt = '" + (flag ? "Y" : "N") + "' where fs_scr_nm = '" + Constants.ParameterGroups.SYSTEM + "' and fs_parm_nm = '" + Constants.SystemGroupParameterNames.FLEXIBLE_OFFSET_ENABLED_FLAG + "'");
    }

    private void scrub(String[] inputTransactions) {
        clearOriginEntryTables();
        loadInputTransactions(OriginEntrySource.BACKUP, inputTransactions, date);
        persistenceService.getPersistenceBroker().clearCache();
        scrubberService.scrubEntries();
    }
}