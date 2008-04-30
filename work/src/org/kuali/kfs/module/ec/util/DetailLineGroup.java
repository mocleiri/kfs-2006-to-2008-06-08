/*
 * Copyright 2008 The Kuali Foundation.
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

import org.kuali.core.util.KualiDecimal;
import org.kuali.kfs.util.ObjectUtil;
import org.kuali.module.effort.bo.EffortCertificationDetail;

/**
 * grouping a set of detail lines. The class is implemented to manage: summary line and delegating line.
 */
public class DetailLineGroup {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(DetailLineGroup.class);

    EffortCertificationDetail summaryDetailLine;
    EffortCertificationDetail delegateDetailLine;
    List<EffortCertificationDetail> detailLines;

    /**
     * Constructs a DetailLineGroup.java.
     */
    public DetailLineGroup() {
        this(null);
    }

    /**
     * Constructs a DetailLineGroup.java.
     * 
     * @param detailLine the given detail line
     */
    public DetailLineGroup(EffortCertificationDetail newDetailLine) {
        detailLines = new ArrayList<EffortCertificationDetail>();
        summaryDetailLine = new EffortCertificationDetail();

        if (newDetailLine != null) {
            ObjectUtil.buildObject(summaryDetailLine, newDetailLine);
            this.addNewLineIntoGroup(newDetailLine);
        }

        summaryDetailLine.setFinancialObjectCode(null);
        summaryDetailLine.setPositionNumber(null);
    }

    /**
     * update the effort percent of the delegate detail line if the effort on the summary line has been changed
     */
    public void updateDelegateDetailLineEffort() {
        Integer difference = this.getEffortPercentChanged();
        if (difference != 0) {
            Integer effortPercent = delegateDetailLine.getEffortCertificationUpdatedOverallPercent() + difference;
            delegateDetailLine.setEffortCertificationUpdatedOverallPercent(effortPercent);
        }
    }

    /**
     * group the given detail lines by the key fields
     * 
     * @param detailLines the given detail lines
     * @param keyFields the given key fields
     * @return the groups of detail lines
     */
    public static Map<String, DetailLineGroup> groupDetailLines(List<EffortCertificationDetail> detailLines, List<String> keyFields) {
        Map<String, DetailLineGroup> detailLineGroupMap = new HashMap<String, DetailLineGroup>();

        for (EffortCertificationDetail line : detailLines) {
            String keysAsString = ObjectUtil.concatPropertyAsString(line, keyFields);

            if (detailLineGroupMap.containsKey(keysAsString)) {
                DetailLineGroup group = detailLineGroupMap.get(keysAsString);
                group.addNewLineIntoGroup(line);
            }
            else {
                DetailLineGroup group = new DetailLineGroup(line);
                detailLineGroupMap.put(keysAsString, group);
            }
        }

        return detailLineGroupMap;
    }

    /**
     * get the difference between the updated effort amount and the current effort amount
     * 
     * @return the difference between the updated effort amount and the original effort amount
     */
    private Integer getEffortPercentChanged() {
        Integer currentEffortPercent = EffortCertificationDetail.getTotalEffortPercent(detailLines);
        Integer updatedEffortPercent = summaryDetailLine.getEffortCertificationUpdatedOverallPercent();

        return updatedEffortPercent - currentEffortPercent;
    }

    /**
     * update the group when a new detail line is added
     * 
     * @param line the new detail line
     */
    private void addNewLineIntoGroup(EffortCertificationDetail newDetailLine) {
        if (detailLines.contains(newDetailLine)) {
            return;
        }

        detailLines.add(newDetailLine);
        delegateDetailLine = this.getDetailLineWithMaxPayrollAmount(detailLines);

        this.updateSummaryDetailLineAmount();
    }

    /**
     * update the payroll amounts and effort percents based on current detail lines
     */
    private void updateSummaryDetailLineAmount() {
        Integer originalEffortPercent = EffortCertificationDetail.getTotalOriginalEffortPercent(detailLines);
        summaryDetailLine.setEffortCertificationCalculatedOverallPercent(originalEffortPercent);

        Integer effortPercent = EffortCertificationDetail.getTotalEffortPercent(detailLines);
        summaryDetailLine.setEffortCertificationUpdatedOverallPercent(effortPercent);

        KualiDecimal originalPayrollAmount = EffortCertificationDetail.getTotalOriginalPayrollAmount(detailLines);
        summaryDetailLine.setEffortCertificationOriginalPayrollAmount(originalPayrollAmount);

        KualiDecimal payrollAmount = EffortCertificationDetail.getTotalPayrollAmount(detailLines);
        summaryDetailLine.setEffortCertificationPayrollAmount(payrollAmount);
    }

    /**
     * find the detail lines that have max payroll amount
     * 
     * @return the detail lines that have max payroll amount
     */
    private EffortCertificationDetail getDetailLineWithMaxPayrollAmount(List<EffortCertificationDetail> detailLines) {
        KualiDecimal maxAmount = null;
        EffortCertificationDetail detailLineWithMaxPayrollAmount = null;

        for (EffortCertificationDetail line : detailLines) {
            KualiDecimal currentAmount = line.getEffortCertificationOriginalPayrollAmount();

            if (detailLineWithMaxPayrollAmount == null) {
                maxAmount = currentAmount;
                detailLineWithMaxPayrollAmount = line;
                continue;
            }

            if (maxAmount.isLessThan(currentAmount)) {
                maxAmount = currentAmount;
                detailLineWithMaxPayrollAmount = line;
            }
        }

        return detailLineWithMaxPayrollAmount;
    }

    /**
     * Gets the summaryDetailLine attribute.
     * 
     * @return Returns the summaryDetailLine.
     */
    public EffortCertificationDetail getSummaryDetailLine() {
        return summaryDetailLine;
    }

    /**
     * Sets the summaryDetailLine attribute value.
     * 
     * @param summaryDetailLine The summaryDetailLine to set.
     */
    public void setSummaryDetailLine(EffortCertificationDetail summaryDetailLine) {
        this.summaryDetailLine = summaryDetailLine;
    }

    /**
     * Gets the detailLines attribute.
     * 
     * @return Returns the detailLines.
     */
    public List<EffortCertificationDetail> getDetailLines() {
        return detailLines;
    }

    /**
     * Sets the detailLines attribute value.
     * 
     * @param detailLines The detailLines to set.
     */
    public void setDetailLines(List<EffortCertificationDetail> detailLines) {
        this.detailLines = detailLines;
    }

    /**
     * Gets the delegateDetailLine attribute.
     * 
     * @return Returns the delegateDetailLine.
     */
    public EffortCertificationDetail getDelegateDetailLine() {
        return delegateDetailLine;
    }

    /**
     * Sets the delegateDetailLine attribute value.
     * 
     * @param delegateDetailLine The delegateDetailLine to set.
     */
    public void setDelegateDetailLine(EffortCertificationDetail delegateDetailLine) {
        this.delegateDetailLine = delegateDetailLine;
    }
}