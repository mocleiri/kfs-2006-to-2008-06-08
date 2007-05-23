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
package org.kuali.module.gl.util;

import java.util.List;

import org.kuali.module.gl.bo.OriginEntry;


public interface CorrectionDocumentEntryMetadata {

    public List<OriginEntry> getAllEntries();

    public boolean getDataLoadedFlag();

    /**
     * Gets the input group ID of the document when it was persisted in the DB
     * @return the input group ID of the document when it was persisted in the DB
     */
    public Integer getInputGroupIdFromLastDocumentLoad();

    public boolean isRestrictedFunctionalityMode();
    
    public boolean getMatchCriteriaOnly();
    
    public String getEditMethod();
}