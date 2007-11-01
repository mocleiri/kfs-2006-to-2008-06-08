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
package org.kuali.module.gl.batch;

import org.kuali.kfs.batch.AbstractStep;
import org.kuali.kfs.batch.TestingStep;
import org.kuali.module.gl.service.OriginEntryGroupService;

/**
 * This step, which would only be run in testing or extraordinary production circumstances, stops the posting fo any postable
 * scrubber groups.
 */
public class MarkPostableScrubberValidGroupsAsUnpostableStep extends AbstractStep implements TestingStep {
    private OriginEntryGroupService originEntryGroupService;

    /**
     * Marks all ready-to-be-posted scrubber groups as unpostable
     * 
     * @param jobName the name of the job this step is being run as part of
     * @return true if the job completed successfully, false if otherwise
     * @see org.kuali.kfs.batch.Step#execute(java.lang.String)
     */
    public boolean execute(String jobName) throws InterruptedException {
        originEntryGroupService.markPostableScrubberValidGroupsAsUnpostable();
        return true;
    }

    /**
     * Sets the originEntryGroupSerivce, allowing the injection of an implementation of that service
     * 
     * @param originEntryGroupService the originEntryGroupService to set
     * @see org.kuali.module.gl.service.OriginEntryGroupService
     */
    public void setOriginEntryGroupService(OriginEntryGroupService originEntryGroupService) {
        this.originEntryGroupService = originEntryGroupService;
    }
}
