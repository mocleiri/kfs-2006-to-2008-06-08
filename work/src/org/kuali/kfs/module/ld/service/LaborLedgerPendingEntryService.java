/*
 * Copyright 2006-2007 The Kuali Foundation.
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

import org.kuali.module.chart.bo.Account;
import org.kuali.module.labor.bo.PendingLedgerEntry;
import org.kuali.module.labor.document.LaborLedgerPostingDocument;

/**
 * This interface defines methods that a LaborLedgerPendingEntry Service must provide
 */
public interface LaborLedgerPendingEntryService {

    /**
     * Does the given account have any labor ledger entries? It is necessary to check this before closing an account.
     * 
     * @param account
     * @return
     */

    public boolean hasPendingLaborLedgerEntry(Account account);

    /**
     * This method checks that the given employee has any labor ledger entries?
     * 
     * @param emplid
     * @return
     */
    public boolean hasPendingLaborLedgerEntry(String emplid);

    /**
     * This method clears cancelled/disapproved entries to clear pending labor entries
     */
    public void deleteEntriesForCancelledOrDisapprovedDocuments();

    /**
     * This method generates labor ledger pending entries.
     * 
     * @param document
     * @return
     */
    public boolean generateLaborLedgerPendingEntries(LaborLedgerPostingDocument document);

    /**
     * Get all entries that have been approved but still in pending entry queue
     * 
     * @return all approved pending entries
     */
    public Iterator<PendingLedgerEntry> findApprovedPendingLedgerEntries();

    /**
     * Delete the pending entries with the given financial document approved code
     * 
     * @param approvedCode
     */
    public void deleteByFinancialDocumentApprovedCode(String financialDocumentApprovedCode);
}