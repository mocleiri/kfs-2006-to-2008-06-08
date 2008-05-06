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
package org.kuali.module.budget.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.kfs.KFSPropertyConstants;
import org.kuali.module.budget.BCKeyConstants;
import org.kuali.module.budget.bo.BudgetConstructionObjectPick;
import org.kuali.module.budget.bo.BudgetConstructionOrgSalaryStatisticsReport;
import org.kuali.module.budget.bo.BudgetConstructionSalaryTotal;
import org.kuali.module.budget.dao.BudgetConstructionSalaryStatisticsReportDao;
import org.kuali.module.budget.service.BudgetConstructionOrganizationReportsService;
import org.kuali.module.budget.service.BudgetConstructionSalaryStatisticsReportService;
import org.kuali.module.budget.util.BudgetConstructionReportHelper;
import org.kuali.module.chart.bo.Chart;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service implementation of BudgetConstructionAccountSummaryReportService.
 */
@Transactional
public class BudgetConstructionSalaryStatisticsReportServiceImpl implements BudgetConstructionSalaryStatisticsReportService {

    BudgetConstructionSalaryStatisticsReportDao budgetConstructionSalaryStatisticsReportDao;
    BudgetConstructionOrganizationReportsService budgetConstructionOrganizationReportsService;
    KualiConfigurationService kualiConfigurationService;
    BusinessObjectService businessObjectService;


    

    public void updateSalaryStatisticsReport(String personUserIdentifier, Integer universityFiscalYear) {
        budgetConstructionSalaryStatisticsReportDao.updateReportsSalaryStatisticsTable(personUserIdentifier, universityFiscalYear); 
            
    }

    public Collection<BudgetConstructionOrgSalaryStatisticsReport> buildReports(Integer universityFiscalYear, String personUserIdentifier) {
        Collection<BudgetConstructionOrgSalaryStatisticsReport> reportSet = new ArrayList();

        
        BudgetConstructionOrgSalaryStatisticsReport orgSalaryStatisticsReportEntry;
        // build searchCriteria
        Map searchCriteria = new HashMap();
        searchCriteria.put(KFSPropertyConstants.KUALI_USER_PERSON_UNIVERSAL_IDENTIFIER, personUserIdentifier);

        // build order list
        List<String> orderList = buildOrderByList();
        Collection<BudgetConstructionSalaryTotal> salaryStatisticsList = budgetConstructionOrganizationReportsService.getBySearchCriteriaOrderByList(BudgetConstructionSalaryTotal.class, searchCriteria, orderList);
        // get object codes
        searchCriteria.clear();
        searchCriteria.put(KFSPropertyConstants.PERSON_UNIVERSAL_IDENTIFIER, personUserIdentifier);
        Collection<BudgetConstructionObjectPick> objectPickList = businessObjectService.findMatching(BudgetConstructionObjectPick.class, searchCriteria);
        String objectCodes = "";
        for (BudgetConstructionObjectPick objectPick : objectPickList) {
            objectCodes += objectPick.getFinancialObjectCode() + " ";
        }
        //build reports
        for (BudgetConstructionSalaryTotal salaryStatisticsEntry : salaryStatisticsList) {
            orgSalaryStatisticsReportEntry = new BudgetConstructionOrgSalaryStatisticsReport();
            buildReportsHeader(universityFiscalYear, objectCodes, orgSalaryStatisticsReportEntry, salaryStatisticsEntry);
            buildReportsBody(orgSalaryStatisticsReportEntry, salaryStatisticsEntry);
            reportSet.add(orgSalaryStatisticsReportEntry);
        }
        return reportSet;
    }

    
    
    
    /**
     * builds report Header
     * 
     * @param BudgetConstructionObjectDump bcod
     */
    public void buildReportsHeader(Integer universityFiscalYear, String objectCodes, BudgetConstructionOrgSalaryStatisticsReport orgSalaryStatisticsReportEntry, BudgetConstructionSalaryTotal salaryTotalEntry) {
        
        // set fiscal year
        Integer prevFiscalyear = universityFiscalYear - 1;
        orgSalaryStatisticsReportEntry.setFiscalYear(prevFiscalyear.toString() + " - " + universityFiscalYear.toString().substring(2, 4));
        // get Chart with orgChartCode
        Map searchCriteria = new HashMap();
        searchCriteria.put(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE, salaryTotalEntry.getOrganizationChartOfAccountsCode());
        Chart chart = (Chart) businessObjectService.findByPrimaryKey(Chart.class, searchCriteria);

        //set OrgCode and Desc
        String orgName = salaryTotalEntry.getOrganization().getOrganizationName();
        orgSalaryStatisticsReportEntry.setOrganizationCode(salaryTotalEntry.getOrganizationCode());
        if (orgName == null) {
            orgSalaryStatisticsReportEntry.setOrganizationName(kualiConfigurationService.getPropertyString(BCKeyConstants.ERROR_REPORT_GETTING_ORGANIZATION_NAME));
        }
        else {
            orgSalaryStatisticsReportEntry.setOrganizationName(orgName);
        }
        //set ChartCode and Desc
        if (chart == null) {
            orgSalaryStatisticsReportEntry.setChartOfAccountDescription(kualiConfigurationService.getPropertyString(BCKeyConstants.ERROR_REPORT_GETTING_CHART_DESCRIPTION));
            orgSalaryStatisticsReportEntry.setChartOfAccountsCode(kualiConfigurationService.getPropertyString(BCKeyConstants.ERROR_REPORT_GETTING_CHART_DESCRIPTION));
        }
        else {
            orgSalaryStatisticsReportEntry.setChartOfAccountsCode(chart.getChartOfAccountsCode());
            orgSalaryStatisticsReportEntry.setChartOfAccountDescription(chart.getFinChartOfAccountDescription());
        }
        Integer prevPrevFiscalyear = prevFiscalyear - 1;
        orgSalaryStatisticsReportEntry.setObjectCodes(objectCodes);
    }

    
    public void buildReportsBody(BudgetConstructionOrgSalaryStatisticsReport orgSalaryStatisticsReportEntry, BudgetConstructionSalaryTotal salaryTotalEntry) {
        orgSalaryStatisticsReportEntry.setInitialRequestedFteQuantity(salaryTotalEntry.getInitialRequestedFteQuantity());
        if (salaryTotalEntry.getInitialRequestedFteQuantity().equals(BigDecimal.ZERO)){
            orgSalaryStatisticsReportEntry.setTotalInitialRequestedAmount(0);
        } else {
            BigDecimal requestedAmount = salaryTotalEntry.getInitialRequestedAmount().divide(salaryTotalEntry.getInitialRequestedFteQuantity());
            orgSalaryStatisticsReportEntry.setTotalInitialRequestedAmount(new Integer(BudgetConstructionReportHelper.setZeroDecimalDigit(requestedAmount).intValue()));
        }
        
        orgSalaryStatisticsReportEntry.setAppointmentRequestedFteQuantity(salaryTotalEntry.getAppointmentRequestedFteQuantity());
        if (salaryTotalEntry.getAppointmentRequestedFteQuantity().equals(BigDecimal.ZERO)){
            orgSalaryStatisticsReportEntry.setAverageCsfAmount(0);
            orgSalaryStatisticsReportEntry.setAverageAppointmentRequestedAmount(0);
        } else {
            BigDecimal averageCsfAmount = salaryTotalEntry.getCsfAmount().divide(salaryTotalEntry.getAppointmentRequestedFteQuantity());
            BigDecimal averageRequestedAmount = salaryTotalEntry.getAppointmentRequestedAmount().divide(salaryTotalEntry.getAppointmentRequestedFteQuantity());
            orgSalaryStatisticsReportEntry.setAverageCsfAmount(new Integer(BudgetConstructionReportHelper.setZeroDecimalDigit(averageCsfAmount).intValue()));
            orgSalaryStatisticsReportEntry.setAverageAppointmentRequestedAmount(new Integer(BudgetConstructionReportHelper.setZeroDecimalDigit(averageRequestedAmount).intValue()));
        }
        orgSalaryStatisticsReportEntry.setAverageChange(orgSalaryStatisticsReportEntry.getAverageAppointmentRequestedAmount() - orgSalaryStatisticsReportEntry.getAverageCsfAmount());
        
        BigDecimal percentChange = BigDecimal.ZERO;
        if (!orgSalaryStatisticsReportEntry.getAverageCsfAmount().equals(0)){
            percentChange = new BigDecimal(orgSalaryStatisticsReportEntry.getAverageChange()).divide(new BigDecimal(orgSalaryStatisticsReportEntry.getAverageCsfAmount()));
            BudgetConstructionReportHelper.setOneDecimalDigit(percentChange);
        }
        orgSalaryStatisticsReportEntry.setPercentChange(percentChange);
        
        
    }
    
    /**
     * builds orderByList for sort order.
     * 
     * @return returnList
     */
    public List<String> buildOrderByList() {
        List<String> returnList = new ArrayList();
        returnList.add(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE);
        returnList.add(KFSPropertyConstants.ORGANIZATION_CODE);
        return returnList;
    }
  
    
    public void setBudgetConstructionSalaryStatisticsReportDao(BudgetConstructionSalaryStatisticsReportDao budgetConstructionSalaryStatisticsReportDao) {
        this.budgetConstructionSalaryStatisticsReportDao = budgetConstructionSalaryStatisticsReportDao;
    }

    public void setBudgetConstructionOrganizationReportsService(BudgetConstructionOrganizationReportsService budgetConstructionOrganizationReportsService) {
        this.budgetConstructionOrganizationReportsService = budgetConstructionOrganizationReportsService;
    }


    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }

}