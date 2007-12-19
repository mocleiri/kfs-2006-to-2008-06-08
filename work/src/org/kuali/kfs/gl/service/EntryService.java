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

import java.util.Map;

/**
 * An interface declaring methods to deal with Entry
 */
public interface EntryService {
    /**
     * Purge the entry table by year/chart
     * 
     * @param chart chart of entries to purge
     * @param year fiscal year of entries to purge
     */
    public void purgeYearByChart(String chart, int year);

    /**
     * This method gets the number of GL entries according to input fields and values
     * 
     * @param fieldValues the input fields and values
     * @return the number of the open encumbrances
     */
    public Integer getEntryRecordCount(Map fieldValues);
}