/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.module.chart.dao;

/**
 * This interface defines the methods necessary to manage the Prior Year Organization values.
 */
public interface PriorYearOrganizationDao {

    /**
     * 
     * This method purges all records in the Prior Year Organization table.
     */
    public int purgePriorYearOrganizations();

    /**
     * 
     * This method copies all current organizations from the Org table into the Prior Year Organization table.
     */
    public int copyCurrentOrganizationsToPriorYearTable();

}
