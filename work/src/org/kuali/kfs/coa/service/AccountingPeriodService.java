/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/work/src/org/kuali/kfs/coa/service/AccountingPeriodService.java,v $
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

import java.sql.Date;
import java.util.Collection;

import org.kuali.module.chart.bo.AccountingPeriod;

/**
 * This service interface defines methods necessary for retreiving fully populated AccountingPeriod business objects from the
 * database that are necessary for transaction processing in the application.
 * 
 * 
 */
public interface AccountingPeriodService {
    /**
     * This method retrieves all valid accounting periods in the system.
     * 
     * @return A list of accounting periods in Kuali.
     */
    public Collection getAllAccountingPeriods();

    /**
     * This method retrieves a list of all open accounting periods in the system.
     * 
     * @return
     */
    public Collection getOpenAccountingPeriods();

    /**
     * 
     * This method retrieves an individual AccountingPeriod based on the period and fiscal year
     * 
     * @param periodCode
     * @param fiscalYear
     * @return an accounting period
     */
    public AccountingPeriod getByPeriod(String periodCode, Integer fiscalYear);

    /**
     * This method takes a date and returns the corresponding period
     * 
     * @param date
     * @return period that matches the date
     */
    public AccountingPeriod getByDate(Date date);
}
