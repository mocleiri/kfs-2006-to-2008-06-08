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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.kfs.KFSPropertyConstants;
import org.kuali.module.budget.BCKeyConstants;
import org.kuali.module.budget.bo.BudgetConstructionAccountSummary;
import org.kuali.module.budget.bo.BudgetConstructionAdministrativePost;
import org.kuali.module.budget.bo.BudgetConstructionIntendedIncumbent;
import org.kuali.module.budget.bo.BudgetConstructionOrgAccountSummaryReport;
import org.kuali.module.budget.bo.BudgetConstructionOrgSalarySummaryReport;
import org.kuali.module.budget.bo.BudgetConstructionOrgSynchronizationProblemsReport;
import org.kuali.module.budget.bo.BudgetConstructionPosition;
import org.kuali.module.budget.bo.BudgetConstructionPositionFunding;
import org.kuali.module.budget.bo.BudgetConstructionSalaryFunding;
import org.kuali.module.budget.bo.BudgetConstructionSalarySocialSecurityNumber;
import org.kuali.module.budget.bo.PendingBudgetConstructionAppointmentFunding;
import org.kuali.module.budget.dao.BudgetConstructionSynchronizationProblemsReportDao;
import org.kuali.module.budget.service.BudgetConstructionOrganizationReportsService;
import org.kuali.module.budget.service.BudgetConstructionSynchronizationProblemsReportService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service implementation of BudgetConstructionAccountSummaryReportService.
 */
@Transactional
public class BudgetConstructionSynchronizationProblemsReportServiceImpl implements BudgetConstructionSynchronizationProblemsReportService {

    BudgetConstructionSynchronizationProblemsReportDao budgetConstructionSynchronizationProblemsReportDao;
    BudgetConstructionOrganizationReportsService budgetConstructionOrganizationReportsService;
    KualiConfigurationService kualiConfigurationService;
    BusinessObjectService businessObjectService;


    public void updateSynchronizationProblemsReport(String personUserIdentifier) {
        budgetConstructionSynchronizationProblemsReportDao.updateReportsSynchronizationProblemsTable(personUserIdentifier);

    }

    public Collection<BudgetConstructionOrgSynchronizationProblemsReport> buildReports(Integer universityFiscalYear, String personUserIdentifier) {
        Collection<BudgetConstructionOrgSynchronizationProblemsReport> reportSet = new ArrayList();


        BudgetConstructionOrgSynchronizationProblemsReport orgSynchronizationProblemsReportEntry;
        // build searchCriteria
        Map searchCriteria = new HashMap();
        searchCriteria.put(KFSPropertyConstants.KUALI_USER_PERSON_UNIVERSAL_IDENTIFIER, personUserIdentifier);

        // build order list
        List<String> orderList = buildOrderByList();
        Collection<BudgetConstructionPositionFunding> synchronizationProblemsList = budgetConstructionOrganizationReportsService.getBySearchCriteriaOrderByList(BudgetConstructionPositionFunding.class, searchCriteria, orderList);
        Map positionMap = new HashMap();
        for (BudgetConstructionPositionFunding positionFundingEntry : synchronizationProblemsList) {
            BudgetConstructionPosition budgetConstructionPosition = getBudgetConstructionPosition(universityFiscalYear, positionFundingEntry.getPendingAppointmentFunding());
            positionMap.put(positionFundingEntry, budgetConstructionPosition);
        }

        for (BudgetConstructionPositionFunding positionFundingEntry : synchronizationProblemsList) {
            orgSynchronizationProblemsReportEntry = new BudgetConstructionOrgSynchronizationProblemsReport();
            buildReportsHeader(universityFiscalYear, orgSynchronizationProblemsReportEntry, positionFundingEntry);
            buildReportsBody(orgSynchronizationProblemsReportEntry, positionFundingEntry, positionMap);
            reportSet.add(orgSynchronizationProblemsReportEntry);
        }

        return reportSet;
    }


    public void buildReportsHeader(Integer universityFiscalYear, BudgetConstructionOrgSynchronizationProblemsReport orgSynchronizationProblemsReportEntry, BudgetConstructionPositionFunding positionFunding) {

        String chartDesc = positionFunding.getSelectedOrganizationChartOfAccounts().getFinChartOfAccountDescription();
        String orgName = positionFunding.getSelectedOrganization().getOrganizationName();

        Integer prevFiscalyear = universityFiscalYear - 1;
        orgSynchronizationProblemsReportEntry.setChartOfAccountsCode(positionFunding.getSelectedOrganizationChartOfAccountsCode());
        orgSynchronizationProblemsReportEntry.setOrganizationCode(positionFunding.getSelectedOrganizationCode());
        if (chartDesc == null) {
            orgSynchronizationProblemsReportEntry.setChartOfAccountDescription(kualiConfigurationService.getPropertyString(BCKeyConstants.ERROR_REPORT_GETTING_CHART_DESCRIPTION));
        }
        else {
            orgSynchronizationProblemsReportEntry.setChartOfAccountDescription(chartDesc);
        }
        if (orgName == null) {
            orgSynchronizationProblemsReportEntry.setOrganizationName(kualiConfigurationService.getPropertyString(BCKeyConstants.ERROR_REPORT_GETTING_ORGANIZATION_NAME));
        }
        else {
            orgSynchronizationProblemsReportEntry.setOrganizationName(orgName);
        }
        orgSynchronizationProblemsReportEntry.setFiscalYear(prevFiscalyear.toString() + " - " + universityFiscalYear.toString().substring(2, 4));
    }
    
    
    public void buildReportsBody(BudgetConstructionOrgSynchronizationProblemsReport orgSynchronizationProblemsReportEntry, BudgetConstructionPositionFunding positionFunding, Map positionMap) {
        
        orgSynchronizationProblemsReportEntry.setBodyChartOfAccountsCode(positionFunding.getChartOfAccountsCode());
        orgSynchronizationProblemsReportEntry.setAccountNumber(positionFunding.getAccountNumber());
        orgSynchronizationProblemsReportEntry.setSubAccountNumber(positionFunding.getSubAccountNumber());
        orgSynchronizationProblemsReportEntry.setFinancialObjectCode(positionFunding.getFinancialObjectCode());
        orgSynchronizationProblemsReportEntry.setFinancialSubObjectCode(positionFunding.getFinancialSubObjectCode());
        orgSynchronizationProblemsReportEntry.setPositionNumber(positionFunding.getPositionNumber());
        orgSynchronizationProblemsReportEntry.setEmplid(positionFunding.getEmplid());
        orgSynchronizationProblemsReportEntry.setPersonName(positionFunding.getPersonName());
        
        orgSynchronizationProblemsReportEntry.setPositionObjectChangeIndicator(new Boolean(positionFunding.getPendingAppointmentFunding().isPositionObjectChangeIndicator()).toString());
        orgSynchronizationProblemsReportEntry.setPositionSalaryChangeIndicator(new Boolean(positionFunding.getPendingAppointmentFunding().isPositionSalaryChangeIndicator()).toString());
        
        BudgetConstructionPosition budgetConstructionPosition = (BudgetConstructionPosition) positionMap.get(positionFunding);
        orgSynchronizationProblemsReportEntry.setPositionEffectiveStatus(budgetConstructionPosition.getPositionEffectiveStatus());
        orgSynchronizationProblemsReportEntry.setBudgetedPosition(new Boolean(budgetConstructionPosition.isBudgetedPosition()).toString());
    }
    
    
    
    
    
    
    
    
    
    
    
    


    private BudgetConstructionPosition getBudgetConstructionPosition(Integer universityFiscalYear, PendingBudgetConstructionAppointmentFunding appointmentFundingEntry) {
        Map searchCriteria = new HashMap();
        searchCriteria.put(KFSPropertyConstants.POSITION_NUMBER, appointmentFundingEntry.getPositionNumber());
        searchCriteria.put(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR, universityFiscalYear);
        return (BudgetConstructionPosition) businessObjectService.findByPrimaryKey(BudgetConstructionPosition.class, searchCriteria);
    }


    /**
     * builds orderByList for sort order.
     * 
     * @return returnList
     */
    public List<String> buildOrderByList() {
        List<String> returnList = new ArrayList();
        returnList.add(KFSPropertyConstants.PERSON_UNIVERSAL_IDENTIFIER);
        returnList.add(KFSPropertyConstants.SELECTED_ORGANIZATION_CHART_OF_ACCOUNTS_CODE);
        returnList.add(KFSPropertyConstants.SELECTED_ORGANIZATION_CODE);
        returnList.add(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE);
        returnList.add(KFSPropertyConstants.ACCOUNT_NUMBER);
        returnList.add(KFSPropertyConstants.SUB_ACCOUNT_NUMBER);
        returnList.add(KFSPropertyConstants.FINANCIAL_OBJECT_CODE);
        returnList.add(KFSPropertyConstants.FINANCIAL_SUB_OBJECT_CODE);
        returnList.add(KFSPropertyConstants.PERSON_NAME);
        returnList.add(KFSPropertyConstants.POSITION_NUMBER);
        return returnList;
    }


    public void setBudgetConstructionSynchronizationProblemsReportDao(BudgetConstructionSynchronizationProblemsReportDao budgetConstructionSynchronizationProblemsReportDao) {
        this.budgetConstructionSynchronizationProblemsReportDao = budgetConstructionSynchronizationProblemsReportDao;
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