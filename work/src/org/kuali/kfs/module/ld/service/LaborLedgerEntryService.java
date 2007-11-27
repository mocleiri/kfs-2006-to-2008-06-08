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

import java.util.Iterator;
import java.util.Map;

import org.kuali.module.labor.bo.LedgerEntry;

/**
 * This interface provides its clients with access to labor leger entries in the backend data store.
 */
public interface LaborLedgerEntryService {

    /**
     * Save the given ledger entry or update it if it exsits
     * 
     * @param ledgerEntry the given ledger entry
     */
    void save(LedgerEntry ledgerEntry);

    /**
     * The sequence number is one of the primary keys of ledger entry. The entries can be grouped by other keys. This method is used
     * to get the maximum sequence number in the group of entries.
     * 
     * @param ledgerEntry the given ledger entry
     * @return the maximum sequence number in a group of entries. If the group doesn't exist, return 0.
     */
    Integer getMaxSequenceNumber(LedgerEntry ledgerEntry);

    /**
     * Find the ledger entries that satisfy the all entries in the given field-value pair
     * 
     * @param fieldValues the given field-value pair
     * @return the ledger entries that satisfy the all entries in the given field-value pair
     */
    Iterator<LedgerEntry> find(Map<String, String> fieldValues);
}