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

package org.kuali.module.effort.document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.document.Document;
import org.kuali.core.document.TransactionalDocumentBase;
import org.kuali.core.service.UniversalUserService;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.util.TypedArrayList;
import org.kuali.core.web.format.CurrencyFormatter;
import org.kuali.kfs.KFSPropertyConstants;
import org.kuali.kfs.bo.Options;
import org.kuali.kfs.context.SpringContext;
import org.kuali.kfs.service.LaborModuleService;
import org.kuali.module.effort.bo.EffortCertificationDetail;
import org.kuali.module.effort.bo.EffortCertificationReportDefinition;
import org.kuali.module.effort.service.EffortCertificationDocumentService;

/**
 * Effort Certification Document Class.
 */
public class EffortCertificationDocument extends TransactionalDocumentBase {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(EffortCertificationDocument.class);

    private String documentNumber;
    private String effortCertificationReportNumber;
    private String effortCertificationDocumentCode;
    private Integer universityFiscalYear;
    private String emplid;

    private Integer totalEffortPercent;
    private Integer totalOriginalEffortPercent;
    private KualiDecimal totalPayrollAmount;
    private KualiDecimal totalOriginalPayrollAmount;

    private EffortCertificationReportDefinition effortCertificationReportDefinition;
    private UniversalUser employee;
    private Options options;

    // boolean to indicate to workflow the distribution of effort has changed and special routing should occur
    private boolean effortDistributionChanged;

    private List<EffortCertificationDetail> effortCertificationDetailLines;

    /**
     * Default constructor.
     */
    public EffortCertificationDocument() {
        effortCertificationDetailLines = new TypedArrayList(EffortCertificationDetail.class);
    }

    /**
     * Gets the documentNumber attribute.
     * 
     * @return Returns the documentNumber.
     */
    public String getDocumentNumber() {
        return documentNumber;
    }

    /**
     * Sets the documentNumber attribute value.
     * 
     * @param documentNumber The documentNumber to set.
     */
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    /**
     * Gets the effortCertificationReportNumber attribute.
     * 
     * @return Returns the effortCertificationReportNumber.
     */
    public String getEffortCertificationReportNumber() {
        return effortCertificationReportNumber;
    }

    /**
     * Sets the effortCertificationReportNumber attribute value.
     * 
     * @param effortCertificationReportNumber The effortCertificationReportNumber to set.
     */
    public void setEffortCertificationReportNumber(String effortCertificationReportNumber) {
        this.effortCertificationReportNumber = effortCertificationReportNumber;
    }

    /**
     * Gets the effortCertificationDocumentCode attribute.
     * 
     * @return Returns the effortCertificationDocumentCode.
     */
    public String getEffortCertificationDocumentCode() {
        return effortCertificationDocumentCode;
    }

    /**
     * Sets the effortCertificationDocumentCode attribute value.
     * 
     * @param effortCertificationDocumentCode The effortCertificationDocumentCode to set.
     */
    public void setEffortCertificationDocumentCode(String effortCertificationDocumentCode) {
        this.effortCertificationDocumentCode = effortCertificationDocumentCode;
    }

    /**
     * Gets the universityFiscalYear attribute.
     * 
     * @return Returns the universityFiscalYear.
     */
    public Integer getUniversityFiscalYear() {
        return universityFiscalYear;
    }

    /**
     * Sets the universityFiscalYear attribute value.
     * 
     * @param universityFiscalYear The universityFiscalYear to set.
     */
    public void setUniversityFiscalYear(Integer universityFiscalYear) {
        this.universityFiscalYear = universityFiscalYear;
    }

    /**
     * Gets the emplid attribute.
     * 
     * @return Returns the emplid.
     */
    public String getEmplid() {
        return emplid;
    }

    /**
     * Sets the emplid attribute value.
     * 
     * @param emplid The emplid to set.
     */
    public void setEmplid(String emplid) {
        this.emplid = emplid;
    }

    /**
     * Gets the effortCertificationReportDefinition attribute.
     * 
     * @return Returns the effortCertificationReportDefinition.
     */
    public EffortCertificationReportDefinition getEffortCertificationReportDefinition() {
        return effortCertificationReportDefinition;
    }

    /**
     * Sets the effortCertificationReportDefinition attribute value.
     * 
     * @param effortCertificationReportDefinition The effortCertificationReportDefinition to set.
     */
    @Deprecated
    public void setEffortCertificationReportDefinition(EffortCertificationReportDefinition effortCertificationReportDefinition) {
        this.effortCertificationReportDefinition = effortCertificationReportDefinition;
    }

    /**
     * Gets the employee attribute.
     * 
     * @return Returns the employee.
     */
    public UniversalUser getEmployee() {
        Map<String, Object> searchCriteria = new HashMap<String, Object>();
        searchCriteria.put(KFSPropertyConstants.PERSON_PAYROLL_IDENTIFIER, getEmplid());

        return new ArrayList<UniversalUser>(SpringContext.getBean(UniversalUserService.class).findUniversalUsers(searchCriteria)).get(0);
    }

    /**
     * Sets the employee attribute value.
     * 
     * @param employee The employee to set.
     */
    public void setEmployee(UniversalUser employee) {
        this.employee = employee;
    }

    /**
     * Gets the options attribute.
     * 
     * @return Returns the options.
     */
    public Options getOptions() {
        return options;
    }

    /**
     * Sets the options attribute value.
     * 
     * @param options The options to set.
     */
    public void setOptions(Options options) {
        this.options = options;
    }

    /**
     * Gets the effortCertificationDetailLines attribute.
     * 
     * @return Returns the effortCertificationDetailLines.
     */
    public List<EffortCertificationDetail> getEffortCertificationDetailLines() {
        return effortCertificationDetailLines;
    }

    /**
     * Sets the effortCertificationDetailLines attribute value.
     * 
     * @param effortCertificationDetailLines The effortCertificationDetailLines to set.
     */
    @Deprecated
    public void setEffortCertificationDetailLines(List<EffortCertificationDetail> effortCertificationDetailLines) {
        this.effortCertificationDetailLines = effortCertificationDetailLines;
    }


    /**
     * Gets the effortDistributionChanged attribute.
     * 
     * @return Returns the effortDistributionChanged.
     */
    public boolean isEffortDistributionChanged() {
        return effortDistributionChanged;
    }

    /**
     * Sets the effortDistributionChanged attribute value.
     * 
     * @param effortDistributionChanged The effortDistributionChanged to set.
     */
    public void setEffortDistributionChanged(boolean effortDistributionChanged) {
        this.effortDistributionChanged = effortDistributionChanged;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @SuppressWarnings("unchecked")
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();
        m.put(KFSPropertyConstants.DOCUMENT_NUMBER, this.documentNumber);
        return m;
    }

    /**
     * get the total amount of the given effort certification document
     * 
     * @param effortCertificationDocument the given effort certification document
     * @return the total amount of the given effort certification document
     */
    public static KualiDecimal getDocumentTotalAmount(EffortCertificationDocument effortCertificationDocument) {
        KualiDecimal totalAmount = KualiDecimal.ZERO;

        List<EffortCertificationDetail> detailLines = effortCertificationDocument.getEffortCertificationDetailLines();
        for (EffortCertificationDetail line : detailLines) {
            totalAmount = totalAmount.add(line.getEffortCertificationPayrollAmount());
        }
        return totalAmount;
    }

    /**
     * @see org.kuali.core.document.DocumentBase#handleRouteStatusChange()
     */
    @Override
    public void handleRouteStatusChange() {
        LOG.debug("handleRouteStatusChange() start...");

        super.handleRouteStatusChange();
        SpringContext.getBean(EffortCertificationDocumentService.class).processApprovedEffortCertificationDocument(this);
    }

    /**
     * Gets the totalEffortPercent attribute.
     * 
     * @return Returns the totalEffortPercent.
     */
    public Integer getTotalEffortPercent() {
        totalEffortPercent = 0;

        for (EffortCertificationDetail detailLine : effortCertificationDetailLines) {
            totalEffortPercent += detailLine.getEffortCertificationUpdatedOverallPercent();
        }

        return totalEffortPercent;
    }

    /**
     * Gets the totalOriginalEffortPercent attribute.
     * 
     * @return Returns the totalOriginalEffortPercent.
     */
    public Integer getTotalOriginalEffortPercent() {
        totalOriginalEffortPercent = 0;

        for (EffortCertificationDetail detailLine : effortCertificationDetailLines) {
            totalOriginalEffortPercent += detailLine.getEffortCertificationCalculatedOverallPercent();
        }

        return totalOriginalEffortPercent;
    }

    /**
     * Gets the totalPayrollAmount attribute.
     * 
     * @return Returns the totalPayrollAmount.
     */
    public KualiDecimal getTotalPayrollAmount() {
        totalPayrollAmount = KualiDecimal.ZERO;

        for (EffortCertificationDetail detailLine : effortCertificationDetailLines) {
            totalPayrollAmount = totalPayrollAmount.add(detailLine.getEffortCertificationPayrollAmount());
        }

        return totalPayrollAmount;
    }

    /**
     * Gets the totalOriginalPayrollAmount attribute.
     * 
     * @return Returns the totalOriginalPayrollAmount.
     */
    public KualiDecimal getTotalOriginalPayrollAmount() {
        totalOriginalPayrollAmount = KualiDecimal.ZERO;

        for (EffortCertificationDetail detailLine : effortCertificationDetailLines) {
            totalOriginalPayrollAmount = totalOriginalPayrollAmount.add(detailLine.getEffortCertificationOriginalPayrollAmount());
        }

        return totalOriginalPayrollAmount;
    }

    /**
     * find the detail lines that have max payroll amount
     * 
     * @return the detail lines that have max payroll amount
     */
    public List<EffortCertificationDetail> getEffortCertificationDetailWithMaxPayrollAmount() {
        List<EffortCertificationDetail> detailLines = new ArrayList<EffortCertificationDetail>();

        KualiDecimal maxAmount = null;
        for (EffortCertificationDetail line : this.getEffortCertificationDetailLines()) {
            KualiDecimal currentAmount = line.getEffortCertificationPayrollAmount();

            if (maxAmount == null) {
                maxAmount = currentAmount;
                detailLines.add(line);
                continue;
            }

            if (maxAmount.isLessThan(currentAmount)) {
                detailLines.removeAll(detailLines);
                maxAmount = currentAmount;
                detailLines.add(line);
            }
            else if (maxAmount.equals(currentAmount)) {
                detailLines.add(line);
            }
        }

        return detailLines;
    }

    public Integer getEffortFederalTotal() {
        Integer effortFederalTotal = 0;
        List<EffortCertificationDetail> detailLineList = this.getEffortCertificationDetailLines();

        for (EffortCertificationDetail detailLine : detailLineList) {
            if (detailLine.isFederalOrFederalPassThroughIndicator()) {
                effortFederalTotal += detailLine.getEffortCertificationUpdatedOverallPercent();
            }
        }

        return effortFederalTotal;
    }

    public Integer getEffortOrigFederalTotal() {
        Integer effortOrigFederalTotal = 0;
        List<EffortCertificationDetail> detailLineList = this.getEffortCertificationDetailLines();

        for (EffortCertificationDetail detailLine : detailLineList) {
            if (detailLine.isFederalOrFederalPassThroughIndicator()) {
                effortOrigFederalTotal += detailLine.getEffortCertificationCalculatedOverallPercent();
            }
        }

        return effortOrigFederalTotal;
    }

    public KualiDecimal getTotalOriginalBenefitFederalAmount() {
        KualiDecimal totalBenAmount = KualiDecimal.ZERO;
        List<EffortCertificationDetail> detailLineList = this.getEffortCertificationDetailLines();

        for (EffortCertificationDetail detailLine : detailLineList) {
            if (detailLine.isFederalOrFederalPassThroughIndicator()) {
                totalBenAmount = totalBenAmount.add(detailLine.getOriginalFringeBenefitAmount());
            }
        }

        return totalBenAmount;
    }

    public KualiDecimal getTotalOriginalBenefitOtherAmount() {
        KualiDecimal totalBenAmount = KualiDecimal.ZERO;
        List<EffortCertificationDetail> detailLineList = this.getEffortCertificationDetailLines();

        for (EffortCertificationDetail detailLine : detailLineList) {
            if (!detailLine.isFederalOrFederalPassThroughIndicator()) {
                totalBenAmount = totalBenAmount.add(detailLine.getOriginalFringeBenefitAmount());
            }
        }

        return totalBenAmount;
    }

    public KualiDecimal getTotalUpdatedBenefitFederalAmount() {
        KualiDecimal totalBenAmount = KualiDecimal.ZERO;
        List<EffortCertificationDetail> detailLineList = this.getEffortCertificationDetailLines();

        for (EffortCertificationDetail detailLine : detailLineList) {
            if (detailLine.isFederalOrFederalPassThroughIndicator()) {
                totalBenAmount = totalBenAmount.add(detailLine.getFringeBenefitAmount());
            }
        }

        return totalBenAmount;
    }

    public KualiDecimal getTotalUpdatedBenefitOtherAmount() {
        KualiDecimal totalBenAmount = KualiDecimal.ZERO;
        List<EffortCertificationDetail> detailLineList = this.getEffortCertificationDetailLines();

        for (EffortCertificationDetail detailLine : detailLineList) {
            if (!detailLine.isFederalOrFederalPassThroughIndicator()) {
                totalBenAmount = totalBenAmount.add(detailLine.getFringeBenefitAmount());
            }
        }

        return totalBenAmount;
    }

    public Integer getEffortOrigOtherTotal() {
        Integer effortOrigOtherTotal = 0;
        List<EffortCertificationDetail> detailLineList = this.getEffortCertificationDetailLines();

        for (EffortCertificationDetail detailLine : detailLineList) {
            if (!detailLine.isFederalOrFederalPassThroughIndicator()) {
                effortOrigOtherTotal += detailLine.getEffortCertificationCalculatedOverallPercent();
            }
        }

        return effortOrigOtherTotal;
    }

    public Integer getEffortOtherTotal() {
        Integer effortOtherTotal = 0;
        List<EffortCertificationDetail> detailLineList = this.getEffortCertificationDetailLines();

        for (EffortCertificationDetail detailLine : detailLineList) {
            if (!detailLine.isFederalOrFederalPassThroughIndicator()) {
                effortOtherTotal += detailLine.getEffortCertificationUpdatedOverallPercent();
            }
        }

        return effortOtherTotal;
    }

    public KualiDecimal getSalaryFederalTotal() {
        KualiDecimal salaryFederalTotal = KualiDecimal.ZERO;
        List<EffortCertificationDetail> detailLineList = this.getEffortCertificationDetailLines();

        for (EffortCertificationDetail detailLine : detailLineList) {
            if (detailLine.isFederalOrFederalPassThroughIndicator()) {
                salaryFederalTotal = salaryFederalTotal.add(detailLine.getEffortCertificationPayrollAmount());
            }
        }

        return salaryFederalTotal;
    }

    public KualiDecimal getSalaryOrigFederalTotal() {
        KualiDecimal salaryOrigFederalTotal = KualiDecimal.ZERO;
        List<EffortCertificationDetail> detailLineList = this.getEffortCertificationDetailLines();

        for (EffortCertificationDetail detailLine : detailLineList) {
            if (detailLine.isFederalOrFederalPassThroughIndicator()) {
                salaryOrigFederalTotal = salaryOrigFederalTotal.add(detailLine.getEffortCertificationOriginalPayrollAmount());
            }
        }

        return salaryOrigFederalTotal;
    }

    public KualiDecimal getSalaryOrigOtherTotal() {
        KualiDecimal salaryOrigOtherTotal = KualiDecimal.ZERO;
        List<EffortCertificationDetail> detailLineList = this.getEffortCertificationDetailLines();

        for (EffortCertificationDetail detailLine : detailLineList) {
            if (!detailLine.isFederalOrFederalPassThroughIndicator()) {
                salaryOrigOtherTotal = salaryOrigOtherTotal.add(detailLine.getEffortCertificationOriginalPayrollAmount());
            }
        }

        return salaryOrigOtherTotal;
    }

    public KualiDecimal getSalaryOtherTotal() {
        KualiDecimal salaryOtherTotal = KualiDecimal.ZERO;
        List<EffortCertificationDetail> detailLineList = this.getEffortCertificationDetailLines();

        for (EffortCertificationDetail detailLine : detailLineList) {
            if (!detailLine.isFederalOrFederalPassThroughIndicator()) {
                salaryOtherTotal = salaryOtherTotal.add(detailLine.getEffortCertificationPayrollAmount());
            }

        }

        return salaryOtherTotal;
    }

    public KualiDecimal getTotalOriginalBenefitAmount() {
        KualiDecimal fBenTotal = KualiDecimal.ZERO;

        List<EffortCertificationDetail> detailLineList = this.getEffortCertificationDetailLines();

        for (EffortCertificationDetail detailLine : detailLineList) {
            fBenTotal = fBenTotal.add(detailLine.getOriginalFringeBenefitAmount());
        }

        return fBenTotal;
    }

    public KualiDecimal getTotalUpdatedBenefitAmount() {
        KualiDecimal fBenTotal = KualiDecimal.ZERO;

        List<EffortCertificationDetail> detailLineList = this.getEffortCertificationDetailLines();

        for (EffortCertificationDetail detailLine : detailLineList) {
            fBenTotal = fBenTotal.add(detailLine.getFringeBenefitAmount());
        }

        return fBenTotal;
    }

    /**
     * @see org.kuali.core.document.DocumentBase#processAfterRetrieve()
     */
    @Override
    public void processAfterRetrieve() {
        super.processAfterRetrieve();

        // capture each line's salary amount before route level modification for later rule validation
        for (EffortCertificationDetail detailLine : this.getEffortCertificationDetailLines()) {
            detailLine.setPersistedPayrollAmount(new KualiDecimal(detailLine.getEffortCertificationPayrollAmount().bigDecimalValue()));
        }

        // calculate original fringe benefits for each line
        for (EffortCertificationDetail detailLine : this.getEffortCertificationDetailLines()) {
            detailLine.setOriginalFringeBenefitAmount(SpringContext.getBean(LaborModuleService.class).calculateFringeBenefit(detailLine.getFinancialDocumentPostingYear(), detailLine.getChartOfAccountsCode(), detailLine.getFinancialObjectCode(), detailLine.getEffortCertificationPayrollAmount()));
        }
    }

    /**
     * @see org.kuali.core.document.DocumentBase#getDocumentRepresentationForSerialization()
     */
    @Override
    protected Document getDocumentRepresentationForSerialization() {
        setEffortDistributionChangedIndicator();

        return super.getDocumentRepresentationForSerialization();
    }

    /**
     * Checks if the effort has changed for any of the detail lines and sets the effortDistributionChanged boolean for routing.
     */
    private void setEffortDistributionChangedIndicator() {
        effortDistributionChanged = false;

        for (EffortCertificationDetail detail : this.getEffortCertificationDetailLines()) {
            if (!detail.getEffortCertificationCalculatedOverallPercent().equals(detail.getEffortCertificationUpdatedOverallPercent())) {
                effortDistributionChanged = true;
            }
        }
    }
    
    public String getDefaultPositionNumber() {
        EffortCertificationDetail maxEffortLine = getMaxEffortLine();
        
        return maxEffortLine.getPositionNumber();
    }
    
    public String getDefaultObjectCode() {
        EffortCertificationDetail maxEffortLine = getMaxEffortLine();
        
        return maxEffortLine.getFinancialObjectCode();
    }
    
    private EffortCertificationDetail getMaxEffortLine() {
        Integer maxEffort = 0;
        EffortCertificationDetail maxLine = null;
        List<EffortCertificationDetail> detailLines = this.getEffortCertificationDetailLines();
        for (EffortCertificationDetail detailLine : detailLines) {
            if (detailLine.getEffortCertificationUpdatedOverallPercent() > maxEffort) {
                maxEffort = detailLine.getEffortCertificationUpdatedOverallPercent();
                maxLine = detailLine;
            }
        }
        return maxLine;
    }
    
    /**
     * Gets Currency Formatted TotalPayrollAmount
     * 
     * @return
     */
    public String getCurrencyFormattedTotalPayrollAmount() {
        
        return getCurrencyFormattedTotal(getTotalPayrollAmount());
    }
    
    /**
     * Gets Currency Formatted TotalOriginalPayrollAmount
     * 
     * @return
     */
    public String getCurrencyFormattedTotalOriginalPayrollAmount() {
        
        return getCurrencyFormattedTotal(getTotalOriginalPayrollAmount());
    }    
    
    /**
     * Gets Currency FormattedSalaryFederalTotal
     * 
     * @return
     */
    public String getCurrencyFormattedSalaryFederalTotal() {
        
        return getCurrencyFormattedTotal(getSalaryFederalTotal());
    }
    
    /**
     * Gets Currency Formatted SalaryOrigFederalTotal
     * 
     * @return
     */
    public String getCurrencyFormattedSalaryOrigFederalTotal() {
        
        return getCurrencyFormattedTotal(getSalaryOrigFederalTotal());
    }
    
    /**
     * Gets Currency Formatted SalaryOrigOtherTotal
     * 
     * @return
     */
    public String getCurrencyFormattedSalaryOrigOtherTotal() {
        
        return getCurrencyFormattedTotal(getSalaryOrigOtherTotal());
    }
    
    /**
     * Gets Currency Formatted SalaryOtherTotal
     * 
     * @return
     */
    public String getCurrencyFormattedSalaryOtherTotal() {
        
        return getCurrencyFormattedTotal(getSalaryOtherTotal());
    }
    
    /**
     * Get Currency Formatted TotalOriginalBenefitFederalAmount
     * 
     * @return
     */
    public String getCurrencyFormattedTotalOriginalBenefitFederalAmount() {
        
        return getCurrencyFormattedTotal(getTotalOriginalBenefitFederalAmount());
    }

    /**
     * Get Currency Formatted TotalOriginalBenefitOtherAmount
     * 
     * @return
     */
    public String getCurrencyFormattedTotalOriginalBenefitOtherAmount() {
        
        return getCurrencyFormattedTotal(getTotalOriginalBenefitOtherAmount());
    }
    
    /**
     * Gets Currency Formatted TotalOriginalBenefitAmount
     * 
     * @return
     */
    public String getCurrencyFormattedTotalOriginalBenefitAmount() {
        
        return getCurrencyFormattedTotal(getTotalOriginalBenefitAmount());
    }
    
    /**
     * Gets Currency Formatted TotalUpdatedBenefitAmount
     * 
     * @return
     */
    public String getCurrencyFormattedTotalUpdatedBenefitAmount() {
        
        return getCurrencyFormattedTotal(getTotalUpdatedBenefitAmount());
    }
    
    /**
     * Gets Currency Formatted TotalUpdatedBenefitFederalAmount
     * 
     * @return
     */
    public String getCurrencyFormattedTotalUpdatedBenefitFederalAmount() {
        
        return getCurrencyFormattedTotal(getTotalUpdatedBenefitFederalAmount());
    }
    
    /**
     * Get Currency Formatted TotalUpdatedBenefitOtherAmount
     * 
     * @return
     */
    public String getCurrencyFormattedTotalUpdatedBenefitOtherAmount() {
        
        return getCurrencyFormattedTotal(getTotalUpdatedBenefitOtherAmount());
    }
    
    /**
     * Returns a currency formatted string
     * 
     * @param unformattedTotal
     * @return
     */
    private String getCurrencyFormattedTotal(KualiDecimal unformattedTotal) {
        
        return (String) new CurrencyFormatter().format(unformattedTotal);
    }
}
