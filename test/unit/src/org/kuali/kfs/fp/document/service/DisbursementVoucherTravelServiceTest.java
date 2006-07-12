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
package org.kuali.module.financial.service;

import java.sql.Timestamp;

import org.kuali.core.util.KualiDecimalMoney;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.module.financial.document.DisbursementVoucherDocument;
import org.kuali.test.KualiTestBaseWithFixtures;

/**
 * This class tests the DisbursementVoucherTravel service.
 * 
 * @author Kuali Financial Transactions (kualidev@oncourse.iu.edu)
 */
public class DisbursementVoucherTravelServiceTest extends KualiTestBaseWithFixtures {
    private DisbursementVoucherTravelService disbursementVoucherTravelService;
    private DisbursementVoucherDocument dvDocument;

    protected void setUp() throws Exception {
        super.setUp();
        this.disbursementVoucherTravelService = SpringServiceLocator.getDisbursementVoucherTravelService();
    }

    /**
     * Test calculation of per diem.
     * 
     * @throws Exception
     */
    public void testCalculatePerDiem() throws Exception {
        dvDocument = new DisbursementVoucherDocument();
        dvDocument.getDvNonEmployeeTravel().setPerDiemStartDateTime("04/21/2006 0:00 AM");
        dvDocument.getDvNonEmployeeTravel().setPerDiemEndDateTime("04/22/2006 0:01 AM");
        runPerDiemTest(dvDocument.getDvNonEmployeeTravel().getDvPerdiemStartDttmStamp(), dvDocument.getDvNonEmployeeTravel().getDvPerdiemEndDttmStamp(), new KualiDecimalMoney(10), new KualiDecimalMoney(12.50));

        dvDocument.getDvNonEmployeeTravel().setPerDiemStartDateTime("04/21/2006 10:00 PM");
        dvDocument.getDvNonEmployeeTravel().setPerDiemEndDateTime("04/22/2006 5:00 AM");
        runPerDiemTest(dvDocument.getDvNonEmployeeTravel().getDvPerdiemStartDttmStamp(), dvDocument.getDvNonEmployeeTravel().getDvPerdiemEndDttmStamp(), new KualiDecimalMoney(10), new KualiDecimalMoney(0));

        dvDocument.getDvNonEmployeeTravel().setPerDiemStartDateTime("04/21/2006 0:00 AM");
        dvDocument.getDvNonEmployeeTravel().setPerDiemEndDateTime("04/23/2006 0:01 AM");
        runPerDiemTest(dvDocument.getDvNonEmployeeTravel().getDvPerdiemStartDttmStamp(), dvDocument.getDvNonEmployeeTravel().getDvPerdiemEndDttmStamp(), new KualiDecimalMoney(10), new KualiDecimalMoney(22.50));

        dvDocument.getDvNonEmployeeTravel().setPerDiemStartDateTime("04/21/2006 12:01 PM");
        dvDocument.getDvNonEmployeeTravel().setPerDiemEndDateTime("04/23/2006 11:59 PM");
        runPerDiemTest(dvDocument.getDvNonEmployeeTravel().getDvPerdiemStartDttmStamp(), dvDocument.getDvNonEmployeeTravel().getDvPerdiemEndDttmStamp(), new KualiDecimalMoney(10), new KualiDecimalMoney(25.00));

        dvDocument.getDvNonEmployeeTravel().setPerDiemStartDateTime("04/21/2006 11:59 PM");
        dvDocument.getDvNonEmployeeTravel().setPerDiemEndDateTime("04/23/2006 11:59 PM");
        runPerDiemTest(dvDocument.getDvNonEmployeeTravel().getDvPerdiemStartDttmStamp(), dvDocument.getDvNonEmployeeTravel().getDvPerdiemEndDttmStamp(), new KualiDecimalMoney(10), new KualiDecimalMoney(22.50));

        dvDocument.getDvNonEmployeeTravel().setPerDiemStartDateTime("04/21/2006 0:00 AM");
        dvDocument.getDvNonEmployeeTravel().setPerDiemEndDateTime("04/23/2006 12:01 PM");
        runPerDiemTest(dvDocument.getDvNonEmployeeTravel().getDvPerdiemStartDttmStamp(), dvDocument.getDvNonEmployeeTravel().getDvPerdiemEndDttmStamp(), new KualiDecimalMoney(10), new KualiDecimalMoney(30.00));

        dvDocument.getDvNonEmployeeTravel().setPerDiemStartDateTime("12/28/2005 0:00 AM");
        dvDocument.getDvNonEmployeeTravel().setPerDiemEndDateTime("01/01/2006 0:01 AM");
        runPerDiemTest(dvDocument.getDvNonEmployeeTravel().getDvPerdiemStartDttmStamp(), dvDocument.getDvNonEmployeeTravel().getDvPerdiemEndDttmStamp(), new KualiDecimalMoney(10), new KualiDecimalMoney(42.50));

        dvDocument.getDvNonEmployeeTravel().setPerDiemStartDateTime("04/21/2006 1:00 PM");
        dvDocument.getDvNonEmployeeTravel().setPerDiemEndDateTime("04/23/2006 0:01 AM");
        runPerDiemTest(dvDocument.getDvNonEmployeeTravel().getDvPerdiemStartDttmStamp(), dvDocument.getDvNonEmployeeTravel().getDvPerdiemEndDttmStamp(), new KualiDecimalMoney(10), new KualiDecimalMoney(17.50));

        dvDocument.getDvNonEmployeeTravel().setPerDiemStartDateTime("04/21/2006 6:00 PM");
        dvDocument.getDvNonEmployeeTravel().setPerDiemEndDateTime("04/23/2006 0:01 AM");
        runPerDiemTest(dvDocument.getDvNonEmployeeTravel().getDvPerdiemStartDttmStamp(), dvDocument.getDvNonEmployeeTravel().getDvPerdiemEndDttmStamp(), new KualiDecimalMoney(10), new KualiDecimalMoney(15.00));

        dvDocument.getDvNonEmployeeTravel().setPerDiemStartDateTime("12/28/2005 0:00 AM");
        dvDocument.getDvNonEmployeeTravel().setPerDiemEndDateTime("01/01/2006 6:01 AM");
        runPerDiemTest(dvDocument.getDvNonEmployeeTravel().getDvPerdiemStartDttmStamp(), dvDocument.getDvNonEmployeeTravel().getDvPerdiemEndDttmStamp(), new KualiDecimalMoney(10), new KualiDecimalMoney(45.00));

        dvDocument.getDvNonEmployeeTravel().setPerDiemStartDateTime("12/28/2005 0:00 AM");
        dvDocument.getDvNonEmployeeTravel().setPerDiemEndDateTime("01/01/2006 11:59 PM");
        runPerDiemTest(dvDocument.getDvNonEmployeeTravel().getDvPerdiemStartDttmStamp(), dvDocument.getDvNonEmployeeTravel().getDvPerdiemEndDttmStamp(), new KualiDecimalMoney(10), new KualiDecimalMoney(50.00));

        dvDocument.getDvNonEmployeeTravel().setPerDiemStartDateTime("04/21/2006 0:00 AM");
        dvDocument.getDvNonEmployeeTravel().setPerDiemEndDateTime("04/21/2006 11:59 PM");
        runPerDiemTest(dvDocument.getDvNonEmployeeTravel().getDvPerdiemStartDttmStamp(), dvDocument.getDvNonEmployeeTravel().getDvPerdiemEndDttmStamp(), new KualiDecimalMoney(10), new KualiDecimalMoney(7.50));

        dvDocument.getDvNonEmployeeTravel().setPerDiemStartDateTime("04/21/2006 3:00 PM");
        dvDocument.getDvNonEmployeeTravel().setPerDiemEndDateTime("04/21/2006 6:00 PM");
        runPerDiemTest(dvDocument.getDvNonEmployeeTravel().getDvPerdiemStartDttmStamp(), dvDocument.getDvNonEmployeeTravel().getDvPerdiemEndDttmStamp(), new KualiDecimalMoney(10), new KualiDecimalMoney(0));

        dvDocument.getDvNonEmployeeTravel().setPerDiemStartDateTime("04/21/2006 5:00 AM");
        dvDocument.getDvNonEmployeeTravel().setPerDiemEndDateTime("04/21/2006 5:00 PM");
        runPerDiemTest(dvDocument.getDvNonEmployeeTravel().getDvPerdiemStartDttmStamp(), dvDocument.getDvNonEmployeeTravel().getDvPerdiemEndDttmStamp(), new KualiDecimalMoney(10), new KualiDecimalMoney(0.00));

        dvDocument.getDvNonEmployeeTravel().setPerDiemStartDateTime("04/21/2006 4:59 AM");
        dvDocument.getDvNonEmployeeTravel().setPerDiemEndDateTime("04/21/2006 5:00 PM");
        runPerDiemTest(dvDocument.getDvNonEmployeeTravel().getDvPerdiemStartDttmStamp(), dvDocument.getDvNonEmployeeTravel().getDvPerdiemEndDttmStamp(), new KualiDecimalMoney(10), new KualiDecimalMoney(5.00));

        dvDocument.getDvNonEmployeeTravel().setPerDiemStartDateTime("04/21/2006 1:00 AM");
        dvDocument.getDvNonEmployeeTravel().setPerDiemEndDateTime("04/21/2006 7:01 PM");
        runPerDiemTest(dvDocument.getDvNonEmployeeTravel().getDvPerdiemStartDttmStamp(), dvDocument.getDvNonEmployeeTravel().getDvPerdiemEndDttmStamp(), new KualiDecimalMoney(50), new KualiDecimalMoney(37.50));

        dvDocument.getDvNonEmployeeTravel().setPerDiemStartDateTime("04/21/2006 11:59 PM");
        dvDocument.getDvNonEmployeeTravel().setPerDiemEndDateTime("04/22/2006 6:00 AM");
        runPerDiemTest(dvDocument.getDvNonEmployeeTravel().getDvPerdiemStartDttmStamp(), dvDocument.getDvNonEmployeeTravel().getDvPerdiemEndDttmStamp(), new KualiDecimalMoney(10), new KualiDecimalMoney(0));

        dvDocument.getDvNonEmployeeTravel().setPerDiemStartDateTime("04/21/2006 6:01 PM");
        dvDocument.getDvNonEmployeeTravel().setPerDiemEndDateTime("04/22/2006 5:59 AM");
        runPerDiemTest(dvDocument.getDvNonEmployeeTravel().getDvPerdiemStartDttmStamp(), dvDocument.getDvNonEmployeeTravel().getDvPerdiemEndDttmStamp(), new KualiDecimalMoney(10), new KualiDecimalMoney(5.00));

        dvDocument.getDvNonEmployeeTravel().setPerDiemStartDateTime("04/21/2006 3:00 PM");
        dvDocument.getDvNonEmployeeTravel().setPerDiemEndDateTime("04/22/2006 6:01 AM");
        runPerDiemTest(dvDocument.getDvNonEmployeeTravel().getDvPerdiemStartDttmStamp(), dvDocument.getDvNonEmployeeTravel().getDvPerdiemEndDttmStamp(), new KualiDecimalMoney(10), new KualiDecimalMoney(10));

    }

    private void runPerDiemTest(Timestamp startTime, Timestamp endTime, KualiDecimalMoney perDiemRate, KualiDecimalMoney expectedPerDiemAmount) {
        assertEquals("Per diem amount not correct ", expectedPerDiemAmount, disbursementVoucherTravelService.calculatePerDiemAmount(startTime, endTime, perDiemRate));
    }

    /**
     * Tests the calculation of travel mileage amount. This is testing against the mileage rates defined currently, we need to find
     * a way to fix this for when they change:
     * 
     * 0-500 0.375 500-3000 0.18 3000 - 0
     * 
     * @throws Exception
     */
    public void testCalculateMileageAmount() throws Exception {
        Timestamp effectiveDate = SpringServiceLocator.getDateTimeService().getCurrentTimestamp();

        runMileageAmountTest(new Integer(0), new KualiDecimalMoney(0), effectiveDate);
        runMileageAmountTest(new Integer(1), new KualiDecimalMoney(.38), effectiveDate);
        runMileageAmountTest(new Integer(10), new KualiDecimalMoney(3.75), effectiveDate);
        runMileageAmountTest(new Integer(15), new KualiDecimalMoney(5.62), effectiveDate);
        runMileageAmountTest(new Integer(100), new KualiDecimalMoney(37.5), effectiveDate);
        runMileageAmountTest(new Integer(200), new KualiDecimalMoney(75.00), effectiveDate);
        runMileageAmountTest(new Integer(380), new KualiDecimalMoney(142.5), effectiveDate);
        runMileageAmountTest(new Integer(500), new KualiDecimalMoney(187.5), effectiveDate);

        runMileageAmountTest(new Integer(501), new KualiDecimalMoney(187.68), effectiveDate);
        runMileageAmountTest(new Integer(600), new KualiDecimalMoney(205.5), effectiveDate);
        runMileageAmountTest(new Integer(2500), new KualiDecimalMoney(547.5), effectiveDate);
        runMileageAmountTest(new Integer(3000), new KualiDecimalMoney(637.5), effectiveDate);

        runMileageAmountTest(new Integer(3001), new KualiDecimalMoney(637.5), effectiveDate);
        runMileageAmountTest(new Integer(8000), new KualiDecimalMoney(637.5), effectiveDate);
    }

    private void runMileageAmountTest(Integer totalMiles, KualiDecimalMoney expectedMileageAmount, Timestamp effectiveDate) {
        assertEquals("Mileage amount not correct ", expectedMileageAmount, disbursementVoucherTravelService.calculateMileageAmount(totalMiles, effectiveDate));
    }


}
