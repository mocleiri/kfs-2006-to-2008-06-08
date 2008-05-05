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
import org.kuali.module.budget.bo.BudgetConstructionOrgAccountSummaryReport;
import org.kuali.module.budget.bo.BudgetConstructionOrgList2PLGReport;
import org.kuali.module.budget.bo.BudgetConstructionTwoPlugListMove;
import org.kuali.module.budget.dao.BudgetConstructionList2PLGReportDao;
import org.kuali.module.budget.service.BudgetConstructionList2PLGReportService;
import org.kuali.module.budget.service.BudgetConstructionOrganizationReportsService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service implementation of BudgetConstructionAccountSummaryReportService.
 */
@Transactional
public class BudgetConstructionList2PLGReportServiceImpl implements BudgetConstructionList2PLGReportService {

    BudgetConstructionList2PLGReportDao budgetConstructionList2PLGReportDao;
    BudgetConstructionOrganizationReportsService budgetConstructionOrganizationReportsService;
    KualiConfigurationService kualiConfigurationService;
    BusinessObjectService businessObjectService;

    public void updateList2PLGReport(String personUserIdentifier, Integer universityFiscalYear) {
        budgetConstructionList2PLGReportDao.updateList2PLGReportsTable(personUserIdentifier);

    }

    public Collection<BudgetConstructionOrgList2PLGReport> buildReports(Integer universityFiscalYear, String personUserIdentifier) {
        Collection<BudgetConstructionOrgList2PLGReport> reportSet = new ArrayList();
        BudgetConstructionOrgList2PLGReport orgList2PLGReportEntry;
        // build searchCriteria
        Map searchCriteria = new HashMap();
        searchCriteria.put(KFSPropertyConstants.KUALI_USER_PERSON_UNIVERSAL_IDENTIFIER, personUserIdentifier);

        // build order list
        List<String> orderList = buildOrderByList();
        Collection<BudgetConstructionTwoPlugListMove> twoPlugList = budgetConstructionOrganizationReportsService.getBySearchCriteriaOrderByList(BudgetConstructionTwoPlugListMove.class, searchCriteria, orderList);
        for (BudgetConstructionTwoPlugListMove orgList2PLGEntry : twoPlugList) {
            orgList2PLGReportEntry = new BudgetConstructionOrgList2PLGReport();
            buildReportsHeader(universityFiscalYear, orgList2PLGReportEntry, orgList2PLGEntry);
            buildReportsBody(orgList2PLGReportEntry, orgList2PLGEntry);
            reportSet.add(orgList2PLGReportEntry);
        }
        return reportSet;
    }

    public void buildReportsHeader(Integer universityFiscalYear, BudgetConstructionOrgList2PLGReport orgList2PLGReportEntry, BudgetConstructionTwoPlugListMove twoPlugListMoveEntry) {
        String orgChartDesc = twoPlugListMoveEntry.getOrganizationChartOfAccounts().getFinChartOfAccountDescription();
        String chartDesc = twoPlugListMoveEntry.getChartOfAccounts().getFinChartOfAccountDescription();
        String orgName = twoPlugListMoveEntry.getOrganization().getOrganizationName();
        String finChartDesc = twoPlugListMoveEntry.getChartOfAccounts().getReportsToChartOfAccounts().getFinChartOfAccountDescription();
        Integer prevFiscalyear = universityFiscalYear - 1;
        orgList2PLGReportEntry.setFiscalYear(prevFiscalyear.toString() + " - " + universityFiscalYear.toString().substring(2, 4));
        orgList2PLGReportEntry.setOrgChartOfAccountsCode(twoPlugListMoveEntry.getOrganizationChartOfAccountsCode());

        if (orgChartDesc == null) {
            orgList2PLGReportEntry.setOrgChartOfAccountDescription(kualiConfigurationService.getPropertyString(BCKeyConstants.ERROR_REPORT_GETTING_CHART_DESCRIPTION));
        }
        else {
            orgList2PLGReportEntry.setOrgChartOfAccountDescription(orgChartDesc);
        }

        orgList2PLGReportEntry.setOrganizationCode(twoPlugListMoveEntry.getOrganizationCode());
        if (orgName == null) {
            orgList2PLGReportEntry.setOrganizationName(kualiConfigurationService.getPropertyString(BCKeyConstants.ERROR_REPORT_GETTING_ORGANIZATION_NAME));
        }
        else {
            orgList2PLGReportEntry.setOrganizationName(orgName);
        }

        orgList2PLGReportEntry.setChartOfAccountsCode(twoPlugListMoveEntry.getChartOfAccountsCode());
        if (chartDesc == null) {
            orgList2PLGReportEntry.setChartOfAccountDescription(kualiConfigurationService.getPropertyString(BCKeyConstants.ERROR_REPORT_GETTING_CHART_DESCRIPTION));
        }
        else {
            orgList2PLGReportEntry.setChartOfAccountDescription(chartDesc);
        }
        Integer prevPrevFiscalyear = prevFiscalyear - 1;
        orgList2PLGReportEntry.setReqFy(prevFiscalyear.toString() + " - " + universityFiscalYear.toString().substring(2, 4));
    }
    
    public void buildReportsBody(BudgetConstructionOrgList2PLGReport orgList2PLGReportEntry, BudgetConstructionTwoPlugListMove twoPlugListMoveEntry) {
        orgList2PLGReportEntry.setAccountNumber(twoPlugListMoveEntry.getAccountNumber());
        orgList2PLGReportEntry.setSubAccountNumber(twoPlugListMoveEntry.getSubAccountNumber());
        orgList2PLGReportEntry.setAccountSubAccountName(twoPlugListMoveEntry.getAccount().getAccountName());
        orgList2PLGReportEntry.setReqAmount(new Integer(twoPlugListMoveEntry.getAccountLineAnnualBalanceAmount().intValue()));
    }
    
    /**
     * builds orderByList for sort order.
     * 
     * @return returnList
     */
    public List<String> buildOrderByList() {
        List<String> returnList = new ArrayList();
        returnList.add(KFSPropertyConstants.ORGANIZATION_CHART_OF_ACCOUNTS_CODE);
        returnList.add(KFSPropertyConstants.ORGANIZATION_CODE);
        returnList.add(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE);
        returnList.add(KFSPropertyConstants.ACCOUNT_NUMBER);
        returnList.add(KFSPropertyConstants.SUB_ACCOUNT_NUMBER);
        return returnList;
    }

    public void setBudgetConstructionList2PLGReportDao(BudgetConstructionList2PLGReportDao budgetConstructionList2PLGReportDao) {
        this.budgetConstructionList2PLGReportDao = budgetConstructionList2PLGReportDao;
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
