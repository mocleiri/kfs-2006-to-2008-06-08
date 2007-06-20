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

import java.io.BufferedOutputStream;
import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.kuali.module.gl.bo.OriginEntryGroup;
import org.kuali.module.gl.util.LedgerEntryHolder;
import org.kuali.module.gl.util.OriginEntryStatistics;
import org.kuali.module.gl.util.PosterOutputSummaryEntry;
import org.kuali.module.labor.bo.LaborOriginEntry;
import org.kuali.module.labor.bo.LaborTransaction;

/**
 * This interface provides its clients with access to labor origin entries in the backend data store.  
 */
public interface LaborOriginEntryService {

    /**
     * Get statistics from a group
     */
    public OriginEntryStatistics getStatistics(Integer groupId);

    /**
     * Copy a set of entries into a new group
     */
    public OriginEntryGroup copyEntries(Date date, String sourceCode, boolean valid,boolean process,boolean scrub,Collection<LaborOriginEntry> entries);
    
    public OriginEntryGroup copyEntries(Date date, String sourceCode, boolean valid, boolean process, boolean scrub, Iterator<LaborOriginEntry> entries);
    /**
     * Delete entry
     * 
     * @param oe Entry to delete
     */
    public void delete(LaborOriginEntry oe);

    /**
     * Return all documents in a group
     * 
     * @param oeg Group used to select documents
     * @return Collection to all documents
     */
    public Collection<LaborOriginEntry> getDocumentsByGroup(OriginEntryGroup oeg);

    /**
     * Return all entries for a group sorted by account number for the error
     * 
     * @param oeg
     * @return
     */
    public Iterator<LaborOriginEntry> getEntriesByGroupAccountOrder(OriginEntryGroup oeg);

    /**
     * Return all entries for a group sorted for display on the pending entry report.
     * 
     * @param oeg
     * @return
     */
    public Iterator<LaborOriginEntry> getEntriesByGroupReportOrder(OriginEntryGroup oeg);
    
    /**
     * Return all entries for a group sorted across the columns in report from left to right.
     * 
     * @param oeg
     * @return
     */
    public Iterator<LaborOriginEntry> getEntriesByGroupListingReportOrder(OriginEntryGroup oeg);

    /**
     * Return all entries for the groups where the balance type is empty
     * 
     * @param groups
     * @return
     */
    public Iterator<LaborOriginEntry> getBadBalanceEntries(Collection groups);

    /**
     * Return all the entries for a specific document in a specific group
     * 
     * @param oeg Group selection
     * @param documentNumber Document number selection
     * @param documentTypeCode Document type selection
     * @param originCode Origin Code selection
     * @return iterator to all the entries
     */
    public Iterator<LaborOriginEntry> getEntriesByDocument(OriginEntryGroup oeg, String documentNumber, String documentTypeCode, String originCode);

    /**
     * Take a generic transaction and save it as an origin entry in a specific group
     * 
     * @param tran transaction to save
     * @param group group to save the transaction
     */
    public void createEntry(LaborTransaction laborTran, OriginEntryGroup group);

    /**
     * Save an origin entry
     * 
     * @param entry
     */
    public void save(LaborOriginEntry entry);

    /**
     * Export all origin entries in a group to a flat text file
     * 
     * @param filename Filename to save the text
     * @param groupId Group to save
     */
    public void exportFlatFile(String filename, Integer groupId);

    /**
     * Load a flat file of transations into the origin entry table
     * 
     * @param filename Filename with the text
     * @param groupSourceCode Source of the new group
     * @param valid Valid flag for new group
     * @param processed Process flag for new group
     * @param scrub Scrub flag for new group
     */
    public void loadFlatFile(String filename, String groupSourceCode, boolean valid, boolean processed, boolean scrub);

    /**
     * Send data to an output stream
     * 
     * @param groupId
     * @param bw
     */
    public void flatFile(Integer groupId, BufferedOutputStream bw);

    public Collection getMatchingEntriesByCollection(Map searchCriteria);

    public LaborOriginEntry getExactMatchingEntry(Integer entryId);


    /**
     * Get origin entries that belong to the given group
     * @param group the given origin entry group
     * @return origin entries that belong to the given group
     */
    public Iterator<LaborOriginEntry> getEntriesByGroup(OriginEntryGroup group);

    /**
     * Get origin entries that belong to the given groups
     * @param groups the given origin entry groups
     * @return origin entries that belong to the given groups
     */
    public Iterator<LaborOriginEntry> getEntriesByGroups(Collection<OriginEntryGroup> groups);

    /**
     * Get the origin entries that belong to the given group in either the consolidation manner or not
     * @param group the given group
     * @param isConsolidated the flag that indicates if return origin entries in either the consolidation manner or not
     * @return the origin entries that belong to the given group in either the consolidation manner or not
     */
    public Iterator<LaborOriginEntry> getEntriesByGroup(OriginEntryGroup group, boolean isConsolidated);  
    
    /**
     * Get the origin entries that belong to the given group in either the consolidation manner or not
     * @param group the given group
     * @param isConsolidated the flag that indicates if return origin entries in either the consolidation manner or not
     * @return the origin entries that belong to the given group in either the consolidation manner or not
     */
    public Collection<LaborOriginEntry> getConsolidatedEntryCollectionByGroup(OriginEntryGroup group); 
    
    /**
     * get the summarized information of the entries that belong to the given entry groups
     * @param groups the origin entry groups
     * @return a set of summarized information of the entries within the specified groups
     */
    public LedgerEntryHolder getSummariedEntriesByGroups(Collection<OriginEntryGroup> groups);
    
    /**
     * get the summarized information of poster input entries that belong to the given entry groups 
     * @param groups the origin entry groups
     * @return a map of summarized information of poster input entries within the specified groups
     */    
    public Map<String,PosterOutputSummaryEntry> getPosterOutputSummaryByGroups(Collection<OriginEntryGroup> groups);
    
    /**
     * get the count of the origin entry collection in the given groups 
     * @param groups the given groups
     * @return the count of the origin entry collection in the given group
     */
    public int getCountOfEntriesInGroups(Collection<OriginEntryGroup> groups);
    
    public List<LaborOriginEntry> getEntriesByGroupId(Integer groupId);
    
    
    /**
     * get the summarized information of the entries that belong to the entry groups with the given group id list
     * 
     * @param groupIdList the origin entry groups
     * @return a set of summarized information of the entries within the specified group
     */
    public LedgerEntryHolder getSummaryByGroupId(Collection groupIdList);
    
}