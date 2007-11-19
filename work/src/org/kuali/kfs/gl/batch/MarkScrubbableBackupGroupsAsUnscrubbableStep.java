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
 * This step will mark all backup groups in the database so that they will not be scrubbed when the nightly scrubber step runs
 * again.
 */
public class MarkScrubbableBackupGroupsAsUnscrubbableStep extends AbstractStep implements TestingStep {
    private OriginEntryGroupService originEntryGroupService;

    /**
     * Marks all scrubbable backup groups as unscrubbable
     * 
     * @param jobName the name of the job this step is being run as part of
     * @return true if the job completed successfully, false if otherwise
     * @see org.kuali.kfs.batch.Step#execute(java.lang.String)
     */
    public boolean execute(String jobName) throws InterruptedException {
        originEntryGroupService.markScrubbableBackupGroupsAsUnscrubbable();
        return true;
    }

    /**
     * Sets the originEntryGroupSerivce, allowing the injection of an implementation of that service
     * 
     * @param originEntryGroupService an implementation originEntryGroupService to set
     * @see org.kuali.module.gl.service.OriginEntryGroupService
     */
    public void setOriginEntryGroupService(OriginEntryGroupService originEntryGroupService) {
        this.originEntryGroupService = originEntryGroupService;
    }
}