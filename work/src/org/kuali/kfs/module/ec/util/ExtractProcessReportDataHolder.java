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
package org.kuali.module.effort.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.module.effort.bo.EffortCertificationReportDefinition;
import org.kuali.module.labor.bo.LedgerBalance;

/**
 * This class groups and holds the data presented to working reports of extract process
 */
public class ExtractProcessReportDataHolder {
    private EffortCertificationReportDefinition reportDefinition;
    private Map<String, Integer> basicStatistics;
    private List<LedgerBalanceWithMessage> ledgerBalancesWithMessage;

    /**
     * Constructs a ExtractProcessReportDataHolder.java.
     */
    public ExtractProcessReportDataHolder() {
        this(null);
    }

    /**
     * Constructs a ExtractProcessReportDataHolder.java.
     * 
     * @param reportDefinition
     */
    public ExtractProcessReportDataHolder(EffortCertificationReportDefinition reportDefinition) {
        super();
        this.reportDefinition = reportDefinition;
        this.basicStatistics = new HashMap<String, Integer>();
        this.ledgerBalancesWithMessage = new ArrayList<LedgerBalanceWithMessage>();
    }

    /**
     * update the value of the entry with the given key. If the key exists, the value will be the sum of the given and existing
     * values; otherwise, create a new entry with the key and value.
     * 
     * @param key the given key
     * @param count the given count
     */
    public void updateBasicStatistics(String key, Integer count) {
        if (basicStatistics.containsKey(key)) {
            Integer currentCount = basicStatistics.get(key);
            count = currentCount + count;
        }
        basicStatistics.put(key, count);
    }

    /**
     * Gets the reportDefinition attribute.
     * 
     * @return Returns the reportDefinition.
     */
    public EffortCertificationReportDefinition getReportDefinition() {
        return reportDefinition;
    }

    /**
     * Sets the reportDefinition attribute value.
     * 
     * @param reportDefinition The reportDefinition to set.
     */
    public void setReportDefinition(EffortCertificationReportDefinition reportDefinition) {
        this.reportDefinition = reportDefinition;
    }

    /**
     * Gets the basicStatistics attribute.
     * 
     * @return Returns the basicStatistics.
     */
    public Map<String, Integer> getBasicStatistics() {
        return basicStatistics;
    }

    /**
     * Sets the basicStatistics attribute value.
     * 
     * @param basicStatistics The basicStatistics to set.
     */
    public void setBasicStatistics(Map<String, Integer> basicStatistics) {
        this.basicStatistics = basicStatistics;
    }

    /**
     * Gets the ledgerBalancesWithMessage attribute. 
     * @return Returns the ledgerBalancesWithMessage.
     */
    public List<LedgerBalanceWithMessage> getLedgerBalancesWithMessage() {
        return ledgerBalancesWithMessage;
    }

    /**
     * Sets the ledgerBalancesWithMessage attribute value.
     * @param ledgerBalancesWithMessage The ledgerBalancesWithMessage to set.
     */
    public void setLedgerBalancesWithMessage(List<LedgerBalanceWithMessage> ledgerBalancesWithMessage) {
        this.ledgerBalancesWithMessage = ledgerBalancesWithMessage;
    }
}