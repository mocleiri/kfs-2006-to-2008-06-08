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
package org.kuali.module.labor.batch;

import org.kuali.kfs.batch.AbstractStep;
import org.kuali.module.labor.service.LaborNightlyOutService;

/**
 * Labor Batch Step.
 */
public class LaborNightlyOutStep extends AbstractStep {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(LaborNightlyOutStep.class);

    private LaborNightlyOutService laborNightlyOutService;

    /**
     * Invokes service that approve pending ledger entries
     * 
     * @param String jobName
     * @return boolean
     * @see org.kuali.kfs.batch.Step#execute()
     */
    public boolean execute(String jobName) {
        try {
            laborNightlyOutService.copyApprovedPendingLedgerEntries();
        }
        catch (Exception e) {
            LOG.fatal(e);
            return false;
        }
        return true;
    }

    /**
     * Sets the laborNightlyOutService attribute value.
     * 
     * @param laborNightlyOutService the laborNightlyOutService to set.
     */
    public void setLaborNightlyOutService(LaborNightlyOutService laborNightlyOutService) {
        this.laborNightlyOutService = laborNightlyOutService;
    }
}
