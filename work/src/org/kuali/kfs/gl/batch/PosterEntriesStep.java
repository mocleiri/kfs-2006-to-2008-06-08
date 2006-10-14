/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/work/src/org/kuali/kfs/gl/batch/PosterEntriesStep.java,v $
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

import org.kuali.core.batch.Step;
import org.kuali.module.gl.service.PosterService;

/**
 * 
 * 
 */
public class PosterEntriesStep implements Step {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PosterEntriesStep.class);

    private PosterService posterService;

    public String getName() {
        return "Poster of GL Entries";
    }

    public boolean performStep() {
        LOG.debug("performStep() started");

        posterService.postMainEntries();
        return true;
    }

    public void setPosterService(PosterService ps) {
        posterService = ps;
    }
}
