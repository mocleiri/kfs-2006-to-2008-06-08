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

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import org.kuali.core.util.ErrorMap;
import org.kuali.module.gl.batch.collector.CollectorBatch;
import org.kuali.module.gl.bo.CollectorDetail;
import org.kuali.module.gl.bo.Transaction;
import org.kuali.module.gl.service.impl.scrubber.DemergerReportData;
import org.kuali.module.gl.service.impl.scrubber.ScrubberReportData;

/**
 * This class aggregates all of the status information together from all of the collector-related processes. Note: this code assumes
 * that each batch is identified by a different collector batch name.
 */
public class CollectorReportData {
    private Map<String, String> emailSendingStatus;

    private Set<String> unparsableBatchNames;
    private Map<String, List<Message>> validationErrorsForBatchName;
    private Map<String, OriginEntryTotals> collectorFileTotals;
    private Map<String, CollectorBatch> addedBatches;
    private Map<String, Map<CollectorDetail, List<Message>>> detailScrubberErrors;
    private Map<String, Map<Transaction, List<Message>>> originEntryScrubberErrors;
    private Map<String, ScrubberReportData> scrubberReportDataForBatchName;
    private Map<String, DemergerReportData> demergerReportDataForBatchName;
    private Map<String, Integer> numDetailAccountValuesChangedForBatchName;

    private Map<String, Integer> numDetailDeletedForBatchName;
    private Map<String, Map<DocumentGroupData, OriginEntryTotals>> totalsOnInputOriginEntriesAssociatedWithErrorGroupForBatchName;
    private Map<String, Integer> numInputDetailsForBatchName;
    private Map<String, Integer> numSavedDetailsForBatchName;
    private SortedMap<String, ErrorMap> errorsForBatchName;
    private Map<String, Boolean> validationStatuses;

    private LedgerEntryHolder ledgerEntryHolder;

    private int numPersistedBatches;
    private int numNotPersistedBatches;

    public CollectorReportData() {
        emailSendingStatus = new HashMap<String, String>();
        unparsableBatchNames = new TreeSet<String>();
        validationErrorsForBatchName = new HashMap<String, List<Message>>();
        collectorFileTotals = new HashMap<String, OriginEntryTotals>();
        addedBatches = new LinkedHashMap<String, CollectorBatch>();
        detailScrubberErrors = new HashMap<String, Map<CollectorDetail, List<Message>>>();
        originEntryScrubberErrors = new HashMap<String, Map<Transaction, List<Message>>>();
        scrubberReportDataForBatchName = new HashMap<String, ScrubberReportData>();
        demergerReportDataForBatchName = new HashMap<String, DemergerReportData>();
        numDetailAccountValuesChangedForBatchName = new HashMap<String, Integer>();
        numDetailDeletedForBatchName = new HashMap<String, Integer>();
        totalsOnInputOriginEntriesAssociatedWithErrorGroupForBatchName = new HashMap<String, Map<DocumentGroupData, OriginEntryTotals>>();
        ledgerEntryHolder = null;
        numInputDetailsForBatchName = new HashMap<String, Integer>();
        numSavedDetailsForBatchName = new HashMap<String, Integer>();
        errorsForBatchName = new TreeMap<String, ErrorMap>();
        validationStatuses = new HashMap<String, Boolean>();

        numPersistedBatches = 0;
        numNotPersistedBatches = 0;
    }

    /**
     * Adds a batch to this report data object. If the batch (identified using batch.getBatchName()) has already been added, then an
     * exception is thrown.
     * 
     * @param batch
     */
    public void addBatch(CollectorBatch batch) {
        if (isBatchAdded(batch)) {
            throw new RuntimeException("Can't add a batch twice");
        }
        addedBatches.put(batch.getBatchName(), batch);
    }

    /**
     * Returns whether a batch has already been added
     * 
     * @param batch
     * @return
     */
    public boolean isBatchAdded(CollectorBatch batch) {
        return addedBatches.containsKey(batch.getBatchName());
    }

    /**
     * Returns the number of batches that have been added using the {@link #addBatch(CollectorBatch)} method
     * 
     * @return
     */
    public int getNumberOfAddedBatches() {
        return addedBatches.size();
    }

    protected void throwExceptionIfBatchNotAdded(CollectorBatch batch) {
        if (!isBatchAdded(batch)) {
            throw new RuntimeException("Batch must be added first");
        }
    }

    /**
     * Stores the errors encountered trying to scrub the InterDepartmentalBilling records in the given batch. This method must be
     * called after addBatch has been called with the same batch. Previously saved errors for this batch will be overwritten.
     * 
     * @param batch
     * @param errorsMap
     */
    public void setBatchDetailScrubberErrors(CollectorBatch batch, Map<CollectorDetail, List<Message>> errorsMap) {
        throwExceptionIfBatchNotAdded(batch);

        detailScrubberErrors.put(batch.getBatchName(), errorsMap);
    }

    /**
     * Stores the errors encountered trying to scrub the InterDepartmentalBilling records in the given batch. This method must be
     * called after addBatch has been called with the same batch. Previously saved errors for this batch will be overwritten.
     * 
     * @param batch
     * @param errorsMap
     */
    public void setBatchOriginEntryScrubberErrors(CollectorBatch batch, Map<Transaction, List<Message>> errorsMap) {
        throwExceptionIfBatchNotAdded(batch);

        originEntryScrubberErrors.put(batch.getBatchName(), errorsMap);
    }

    /**
     * Returns the scrubber errors related to a batch
     * 
     * @param batch
     * @return
     */
    public Map<Transaction, List<Message>> getBatchOriginEntryScrubberErrors(CollectorBatch batch) {
        throwExceptionIfBatchNotAdded(batch);

        return originEntryScrubberErrors.get(batch.getBatchName());
    }

    public void setScrubberReportData(CollectorBatch batch, ScrubberReportData scrubberReportData) {
        throwExceptionIfBatchNotAdded(batch);

        scrubberReportDataForBatchName.put(batch.getBatchName(), scrubberReportData);
    }

    public ScrubberReportData getScrubberReportData(CollectorBatch batch) {
        throwExceptionIfBatchNotAdded(batch);

        return scrubberReportDataForBatchName.get(batch.getBatchName());
    }

    public void setDemergerReportData(CollectorBatch batch, DemergerReportData demergerReportData) {
        throwExceptionIfBatchNotAdded(batch);

        demergerReportDataForBatchName.put(batch.getBatchName(), demergerReportData);
    }

    public DemergerReportData getDemergerReportData(CollectorBatch batch) {
        throwExceptionIfBatchNotAdded(batch);

        return demergerReportDataForBatchName.get(batch.getBatchName());
    }

    public void markUnparsableBatchNames(String batchName) {
        unparsableBatchNames.add(batchName);
    }

    public Set<String> getAllUnparsableBatchNames() {
        return Collections.unmodifiableSet(unparsableBatchNames);
    }

    public void setEmailSendingStatusForParsedBatch(CollectorBatch batch, String emailStatus) {
        throwExceptionIfBatchNotAdded(batch);

        emailSendingStatus.put(batch.getBatchName(), emailStatus);
    }

    public Iterator<CollectorBatch> getAddedBatches() {
        return addedBatches.values().iterator();
    }

    public void setOriginEntryTotals(CollectorBatch batch, OriginEntryTotals totals) {
        throwExceptionIfBatchNotAdded(batch);

        collectorFileTotals.put(batch.getBatchName(), totals);
    }

    public OriginEntryTotals getOriginEntryTotals(CollectorBatch batch) {
        throwExceptionIfBatchNotAdded(batch);

        return collectorFileTotals.get(batch.getBatchName());
    }


    /**
     * Sets the number of times the details in a batch have had their account numbers changed
     * 
     * @param batch
     */
    public void setNumDetailAccountValuesChanged(CollectorBatch batch, Integer numDetailAccountValuesChanged) {
        throwExceptionIfBatchNotAdded(batch);

        numDetailAccountValuesChangedForBatchName.put(batch.getBatchName(), numDetailAccountValuesChanged);
    }

    public Integer getNumDetailAccountValuesChanged(CollectorBatch batch) {
        throwExceptionIfBatchNotAdded(batch);

        return numDetailAccountValuesChangedForBatchName.get(batch.getBatchName());
    }

    public void setNumDetailDeleted(CollectorBatch batch, Integer numDetailDeleted) {
        throwExceptionIfBatchNotAdded(batch);

        numDetailDeletedForBatchName.put(batch.getBatchName(), numDetailDeleted);
    }

    public Integer getNumDetailDeleted(CollectorBatch batch) {
        throwExceptionIfBatchNotAdded(batch);

        return numDetailDeletedForBatchName.get(batch.getBatchName());
    }

    /**
     * Stores the totals or all origin entries in the input group that match the document group (doc #, doc type, origination code)
     * of at least one origin entry in the error group, which is generated by the scrubber
     * 
     * @param batch
     * @param totals a map such that the key is a document group (doc #, doc type, origination code) and the value is the totals of
     *        the origin entry of all those
     */
    public void setTotalsOnInputOriginEntriesAssociatedWithErrorGroup(CollectorBatch batch, Map<DocumentGroupData, OriginEntryTotals> totals) {
        throwExceptionIfBatchNotAdded(batch);

        totalsOnInputOriginEntriesAssociatedWithErrorGroupForBatchName.put(batch.getBatchName(), totals);
    }

    /**
     * Returns the totals or all origin entries in the input group that match the document group (doc #, doc type, origination code)
     * of at least one origin entry in the error group, which is generated by the scrubber
     * 
     * @param batch return a map such that the key is a document group (doc #, doc type, origination code) and the value is the
     *        totals of the origin entry of all those
     */
    public Map<DocumentGroupData, OriginEntryTotals> getTotalsOnInputOriginEntriesAssociatedWithErrorGroup(CollectorBatch batch) {
        throwExceptionIfBatchNotAdded(batch);

        return totalsOnInputOriginEntriesAssociatedWithErrorGroupForBatchName.get(batch.getBatchName());
    }

    /**
     * Gets the ledgerEntryHolder attribute.
     * 
     * @return Returns the ledgerEntryHolder.
     */
    public LedgerEntryHolder getLedgerEntryHolder() {
        return ledgerEntryHolder;
    }

    /**
     * Sets the ledgerEntryHolder attribute value.
     * 
     * @param ledgerEntryHolder The ledgerEntryHolder to set.
     */
    public void setLedgerEntryHolder(LedgerEntryHolder ledgerEntryHolder) {
        this.ledgerEntryHolder = ledgerEntryHolder;
    }

    /**
     * This method...
     * 
     * @param batch
     */
    public void setNumInputDetails(CollectorBatch batch) {
        throwExceptionIfBatchNotAdded(batch);

        numInputDetailsForBatchName.put(batch.getBatchName(), batch.getCollectorDetails().size());
    }

    public Integer getNumInputDetails(CollectorBatch batch) {
        throwExceptionIfBatchNotAdded(batch);

        return numInputDetailsForBatchName.get(batch.getBatchName());
    }

    public void setNumSavedDetails(CollectorBatch batch, Integer numSavedDetails) {
        throwExceptionIfBatchNotAdded(batch);

        numSavedDetailsForBatchName.put(batch.getBatchName(), numSavedDetails);
    }

    public Integer getNumSavedDetails(CollectorBatch batch) {
        throwExceptionIfBatchNotAdded(batch);

        return numSavedDetailsForBatchName.get(batch.getBatchName());
    }

    /**
     * Retrieves an error map instance for a batch name (typically the file name of the batch file). Each instance of this class
     * guarantees that each time this method is called with specific batch name, the same error map instance is returned, and that
     * each that no 2 different batch names will return the same instance
     * 
     * @param batchName a batch name
     * @return a error map instance specific to this batch name
     */
    public ErrorMap getErrorMapForBatchName(String batchName) {
        ErrorMap errorMap = errorsForBatchName.get(batchName);
        if (errorMap == null) {
            errorMap = new ErrorMap();
            errorsForBatchName.put(batchName, errorMap);
        }
        return errorMap;
    }

    /**
     * Returns a set of batch names that were passed in as parameters into {@link #getErrorMapForBatchName(String)}.
     * 
     * @return a set of batch names that have an associated error map
     */
    public Set<String> getBatchNamesWithErrorMap() {
        return errorsForBatchName.keySet();
    }

    public void incrementNumPersistedBatches() {
        numPersistedBatches++;
    }


    /**
     * Gets the numPersistedBatches attribute.
     * 
     * @return Returns the numPersistedBatches.
     */
    public int getNumPersistedBatches() {
        return numPersistedBatches;
    }

    public void incrementNumNonPersistedBatches() {
        numNotPersistedBatches++;
    }

    /**
     * Gets the numNotPersistedBatches attribute.
     * 
     * @return Returns the numNotPersistedBatches.
     */
    public int getNumNotPersistedBatches() {
        return numNotPersistedBatches;
    }

    /**
     * Marks whether or not a batch is valid or not
     * 
     * @param batch
     * @param validStatus
     */
    public void markValidationStatus(CollectorBatch batch, boolean validStatus) {
        throwExceptionIfBatchNotAdded(batch);

        validationStatuses.put(batch.getBatchName(), new Boolean(validStatus));
    }

    /**
     * Returns true if batch is valid; False if invalid
     * 
     * @param batch
     * @return
     */
    public boolean isBatchValid(CollectorBatch batch) {
        throwExceptionIfBatchNotAdded(batch);

        return (Boolean) validationStatuses.get(batch.getBatchName()).booleanValue();
    }
}
