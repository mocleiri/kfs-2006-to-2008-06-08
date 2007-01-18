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
import java.util.Iterator;

import org.kuali.module.gl.bo.OriginEntryGroup;
import org.kuali.module.labor.bo.LaborOriginEntry;

public interface LaborOriginEntryService {

    /**
     * Get origin entries that belong to the given group
     * @param group the given origin entry group
     * @return origin entries that belong to the given group
     */
    Iterator<LaborOriginEntry> getEntriesByGroup(OriginEntryGroup group);

    /**
     * Get origin entries that belong to the given groups
     * @param postingGroups the given origin entry groups
     * @return origin entries that belong to the given groups
     */
    Iterator<LaborOriginEntry> getEntriesByGroups(Collection<OriginEntryGroup> postingGroups);

    /**
     * Get the valid origin entries that belong to the given group in either the consolidation manner or not
     * @param validGroup the given group that contains valid origin entry
     * @param isConsolidated the flag that indicates if return origin entries in either the consolidation manner or not
     * @return the valid origin entries that belong to the given group in either the consolidation manner or not
     */
    Iterator<LaborOriginEntry> getEntriesByGroup(OriginEntryGroup validGroup, boolean isConsolidated);
}