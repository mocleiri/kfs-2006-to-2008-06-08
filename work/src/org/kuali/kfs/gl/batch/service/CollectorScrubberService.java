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
package org.kuali.module.gl.service;

import java.util.Collection;

import org.kuali.module.gl.batch.collector.CollectorBatch;
import org.kuali.module.gl.util.CollectorReportData;
import org.kuali.module.gl.util.CollectorScrubberStatus;

/**
 * This class...
 */
public interface CollectorScrubberService {
    /**
     * Runs the scrubber on the origin entries in the batch. Any OEs edits/removals result of the scrub and demerger are removed
     * from the batch, and the same changes are reflected in the details in the same batch.
     * 
     * @param batch
     * @param collectorReportData
     * @return an object with the collector scrubber status. Note that it contains references to at least 4 origin entry groups, and
     *         the origin entry group service and origin entry service under which these groups and their entries are stored. The
     *         groups and their entries are created to facilitate the scrub and reporting processes, and they should not be
     *         persisted after the collector finishes running. Therefore, an collection of all CollectorScrubberStatus objects
     *         returned in a single collector execution (i.e. from a nightly job) must be passed into a parameter to the
     *         {@link #removeTempGroups(Collection)} method.. The service definitions are needed because the collector may choose to
     *         store temporary origin entries and origin entry groups in another service segregated from the database.
     */
    public CollectorScrubberStatus scrub(CollectorBatch batch, CollectorReportData collectorReportData);

    /**
     * Removes any temporarily created origin entries and origin entry groups so that they won't be persisted after the transaction
     * is committed.
     * 
     * @param allStatusObjectsFromCollectorExecution
     */
    public void removeTempGroups(Collection<CollectorScrubberStatus> allStatusObjectsFromCollectorExecution);
}
