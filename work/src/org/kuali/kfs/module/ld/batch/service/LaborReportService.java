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
package org.kuali.module.labor.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.kuali.module.gl.bo.OriginEntryGroup;
import org.kuali.module.gl.bo.Transaction;
import org.kuali.module.gl.util.Message;
import org.kuali.module.gl.util.Summary;
import org.kuali.module.labor.util.ReportRegistry;

/**
 * This defines a set of reporting generation facilities
 */
public interface LaborReportService {

    /**
     * Generate poster main ledger summary report with the given information
     * 
     * @param groups the given origin entry groups
     * @param reportInfo the primary elements of a report, such as report title and report file name
     * @param reportsDirectory the directory in file system that is used to contain reports
     * @param runDate the datetime of the repor generation
     */
    public void generatePosterMainLedgerSummaryReport(Collection<OriginEntryGroup> groups, ReportRegistry reportInfo, String reportsDirectory, Date runDate);

    /**
     * Generate poster error transaction listing as a report
     * 
     * @param group the given origin entry group
     * @param reportInfo the primary elements of a report, such as report title and report file name
     * @param reportsDirectory the directory in file system that is used to contain reports
     * @param runDate the datetime of the repor generation
     */
    public void generatePosterErrorTransactionListing(OriginEntryGroup group, ReportRegistry reportInfo, String reportsDirectory, Date runDate);

    /**
     * Generate poster statistics report with the given information
     * 
     * @param reportSummary a list of report <code>Summary<code> objects
     * @param errors the tansactions with problems and the descriptions of the problems
     * @param reportInfo the primary elements of a report, such as report title and report file name
     * @param reportsDirectory the directory in file system that is used to contain reports
     * @param runDate the datetime of the repor generation
     */
    public void generatePosterStatisticsReport(List<Summary> reportSummary, Map<Transaction, List<Message>> errors, ReportRegistry reportInfo, String reportsDirectory, Date runDate);

    /**
     * Generate poster output summary report with the given information
     * 
     * @param groups the given origin entry groups
     * @param reportInfo the primary elements of a report, such as report title and report file name
     * @param reportsDirectory the directory in file system that is used to contain reports
     * @param runDate the datetime of the repor generation
     */
    public void generatePosterOutputSummaryReport(Collection<OriginEntryGroup> groups, ReportRegistry reportInfo, String reportsDirectory, Date runDate);

    /**
     * Generate poster output summary report with the given information
     * 
     * @param group the given origin entry group
     * @param reportInfo the primary elements of a report, such as report title and report file name
     * @param reportsDirectory the directory in file system that is used to contain reports
     * @param runDate the datetime of the repor generation
     */
    public void generatePosterOutputSummaryReport(OriginEntryGroup group, ReportRegistry reportInfo, String reportsDirectory, Date runDate);
}