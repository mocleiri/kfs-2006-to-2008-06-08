/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/test/unit/src/org/kuali/kfs/coa/businessobject/AccountTest.java,v $
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
package org.kuali.module.chart.bo;

import static org.kuali.core.util.SpringServiceLocator.getDateTimeService;

import java.sql.Timestamp;
import java.text.ParseException;

import org.kuali.test.KualiTestBase;
import org.kuali.test.WithTestSpringContext;

/**
 * This class...
 * 
 * 
 */
@WithTestSpringContext
public class AccountTest extends KualiTestBase {

    private static final String TEST_DATE_1_TODAY = "2002-04-22 19:48:23";
    private static final String TEST_DATE_1_YESTERDAY = "2002-04-21 19:48:23";
    private static final String TEST_DATE_1_TOMORROW = "2002-04-23 19:48:23";

    private static final String TEST_DATE_2_TODAY = "2002-04-22 10:23:08";
    private static final String TEST_DATE_2_YESTERDAY = "2002-04-21 10:23:08";
    private static final String TEST_DATE_2_TOMORROW = "2002-04-23 10:23:08";

    private static final String TEST_DATE_3_TODAY = "2002-04-22 06:14:55";
    private static final String TEST_DATE_3_YESTERDAY = "2002-04-21 06:14:55";
    private static final String TEST_DATE_3_TOMORROW = "2002-04-23 06:14:55";

    // pass this a name, and it returns a setup timestamp instance
    private Timestamp getNamedTimestamp(String timestampString) {
        Timestamp timestamp;
        try {
            timestamp = getDateTimeService().convertToSqlTimestamp(timestampString);
        }
        catch (ParseException e) {
            assertNull("Timestamp String was not parseable", e);
            return null;
        }
        return timestamp;
    }

    // since all the tests are doing the same thing, this is centralized
    private void doTest(String expirationDateString, String testDateString, boolean expectedResult) {

        Timestamp expirationDate = getNamedTimestamp(expirationDateString);
        Timestamp testDate = getNamedTimestamp(testDateString);

        // setup the account, and set its expiration date
        Account account = new Account();
        account.setAccountExpirationDate(expirationDate);

        // test against isExpired, and get the result
        boolean actualResult = account.isExpired(getDateTimeService().getCalendar(testDate));

        // compare the result to what was expected
        assertEquals(expectedResult, actualResult);
    }

    // if date of expiration and date of today is the same date (time excluded)
    // then the account is not considered expired
    public void testIsExpiredToday_ExpirationDateToday_ExpirationDateEarlierTime() {
        doTest(TEST_DATE_2_TODAY, TEST_DATE_1_TODAY, false);
    }

    // if date of expiration and date of today is the same date (time excluded)
    // then the account is not considered expired
    public void testIsExpiredToday_ExpirationDateToday_ExpirationDateLaterTime() {
        doTest(TEST_DATE_2_TODAY, TEST_DATE_3_TODAY, false);
    }

    // if date of expiration is one day later than day of testDate, fail
    public void testIsExpiredToday_ExpirationDateTomorrow() {
        doTest(TEST_DATE_2_TOMORROW, TEST_DATE_1_TODAY, false);
    }

    // if date of expiration is one day earlier than day of testDate, succeed
    public void testIsExpiredToday_ExpirationDateYesterday() {
        doTest(TEST_DATE_2_YESTERDAY, TEST_DATE_1_TODAY, true);
    }

}
