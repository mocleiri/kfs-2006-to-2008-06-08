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
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.core.service.KualiConfigurationService;
import org.kuali.kfs.KFSPropertyConstants;
import org.kuali.module.budget.BCConstants;
import org.kuali.module.budget.BCKeyConstants;
import org.kuali.module.budget.bo.BudgetConstructionLevelSummary;
import org.kuali.module.budget.bo.BudgetConstructionOrgLevelSummaryReport;
import org.kuali.module.budget.bo.BudgetConstructionOrgLevelSummaryReportTotal;
import org.kuali.module.budget.dao.BudgetConstructionLevelSummaryReportDao;
import org.kuali.module.budget.service.BudgetConstructionLevelSummaryReportService;
import org.kuali.module.budget.service.BudgetConstructionOrganizationReportsService;
import org.kuali.module.budget.util.BudgetConstructionReportHelper;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service implementation of BudgetConstructionLevelSummaryReportService.
 */
@Transactional
public class BudgetConstructionLevelSummaryReportServiceImpl implements BudgetConstructionLevelSummaryReportService {

    BudgetConstructionLevelSummaryReportDao budgetConstructionLevelSummaryReportDao;
    BudgetConstructionOrganizationReportsService budgetConstructionOrganizationReportsService;
    KualiConfigurationService kualiConfigurationService;

    /**
     * @see org.kuali.module.budget.service.BudgetReportsControlListService#updateRepotsLevelSummaryTable(java.lang.String)
     */
    public void updateLevelSummaryReport(String personUserIdentifier) {
        budgetConstructionLevelSummaryReportDao.cleanReportsLevelSummaryTable(personUserIdentifier);
        budgetConstructionLevelSummaryReportDao.updateReportsLevelSummaryTable(personUserIdentifier);
    }

    /**
     * sets budgetConstructionLevelSummaryReportDao
     * 
     * @param budgetConstructionLevelSummaryReportDao
     */
    public void setBudgetConstructionLevelSummaryReportDao(BudgetConstructionLevelSummaryReportDao budgetConstructionLevelSummaryReportDao) {
        this.budgetConstructionLevelSummaryReportDao = budgetConstructionLevelSummaryReportDao;
    }

    /**
     * @see org.kuali.module.budget.service.BudgetConstructionLevelSummaryReportService#buildReports(java.lang.Integer,
     *      java.util.Collection)
     */
    public Collection<BudgetConstructionOrgLevelSummaryReport> buildReports(Integer universityFiscalYear, String personUserIdentifier) {
        Collection<BudgetConstructionOrgLevelSummaryReport> reportSet = new ArrayList();

        BudgetConstructionOrgLevelSummaryReport orgLevelSummaryReportEntry;
        // build searchCriteria
        Map searchCriteria = new HashMap();
        searchCriteria.put(KFSPropertyConstants.KUALI_USER_PERSON_UNIVERSAL_IDENTIFIER, personUserIdentifier);

        // build order list
        List<String> orderList = buildOrderByList();
        Collection<BudgetConstructionLevelSummary> levelSummaryList = budgetConstructionOrganizationReportsService.getBySearchCriteriaOrderByList(BudgetConstructionLevelSummary.class, searchCriteria, orderList);


        // Making a list with same organizationChartOfAccountsCode, organizationCode, chartOfAccountsCode, subFundGroupCode
        List<String> fieldList = new ArrayList();
        fieldList.add(KFSPropertyConstants.ORGANIZATION_CHART_OF_ACCOUNTS_CODE);
        fieldList.add(KFSPropertyConstants.ORGANIZATION_CODE);
        fieldList.add(KFSPropertyConstants.SUB_FUND_GROUP_CODE);
        fieldList.add(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE);
        List listForCalculateTotal = BudgetConstructionReportHelper.deleteDuplicated((List) levelSummaryList, fieldList);
        
        fieldList.add(KFSPropertyConstants.INCOME_EXPENSE_CODE);
        List listForCalculateGexpAndType = BudgetConstructionReportHelper.deleteDuplicated((List) levelSummaryList, fieldList);
        
        fieldList.add(KFSPropertyConstants.FINANCIAL_CONSOLIDATION_SORT_CODE);
        List listForCalculateCons = BudgetConstructionReportHelper.deleteDuplicated((List) levelSummaryList, fieldList );

        // Calculate Total Section
        List<BudgetConstructionOrgLevelSummaryReportTotal> levelSummaryTotalConsList;
        List<BudgetConstructionOrgLevelSummaryReportTotal> levelSummaryTotalGexpAndTypeList;
        List<BudgetConstructionOrgLevelSummaryReportTotal> levelSummaryTotalList;

        levelSummaryTotalConsList = calculateConsTotal((List) levelSummaryList, listForCalculateCons);
        levelSummaryTotalGexpAndTypeList = calculateGexpAndTypeTotal((List) levelSummaryList, listForCalculateGexpAndType);
        levelSummaryTotalList = calculateTotal((List) levelSummaryList, listForCalculateTotal);


        for (BudgetConstructionLevelSummary levelSummaryEntry : levelSummaryList) {
            orgLevelSummaryReportEntry = new BudgetConstructionOrgLevelSummaryReport();
            buildReportsHeader(universityFiscalYear, orgLevelSummaryReportEntry, levelSummaryEntry);
            buildReportsBody(orgLevelSummaryReportEntry, levelSummaryEntry);
            buildReportsTotal(orgLevelSummaryReportEntry, levelSummaryEntry, levelSummaryTotalConsList, levelSummaryTotalGexpAndTypeList, levelSummaryTotalList);
            reportSet.add(orgLevelSummaryReportEntry);
        }

        return reportSet;
    }

    /**
     * builds report Header
     * 
     * @param BudgetConstructionLevelSummary bcas
     */
    public void buildReportsHeader(Integer universityFiscalYear, BudgetConstructionOrgLevelSummaryReport orgLevelSummaryReportEntry, BudgetConstructionLevelSummary levelSummary) {
        String orgChartDesc = levelSummary.getOrganizationChartOfAccounts().getFinChartOfAccountDescription();
        String chartDesc = levelSummary.getChartOfAccounts().getFinChartOfAccountDescription();
        String orgName = levelSummary.getOrganization().getOrganizationName();
        String reportChartDesc = levelSummary.getChartOfAccounts().getReportsToChartOfAccounts().getFinChartOfAccountDescription();
        String subFundGroupName = levelSummary.getSubFundGroup().getSubFundGroupCode();
        String subFundGroupDes = levelSummary.getSubFundGroup().getSubFundGroupDescription();
        String fundGroupName = levelSummary.getSubFundGroup().getFundGroupCode();
        String fundGroupDes = levelSummary.getSubFundGroup().getFundGroup().getName();

        Integer prevFiscalyear = universityFiscalYear - 1;
        orgLevelSummaryReportEntry.setFiscalYear(prevFiscalyear.toString() + " - " + universityFiscalYear.toString().substring(2, 4));
        orgLevelSummaryReportEntry.setOrgChartOfAccountsCode(levelSummary.getOrganizationChartOfAccountsCode());

        if (orgChartDesc == null) {
            orgLevelSummaryReportEntry.setOrgChartOfAccountDescription(kualiConfigurationService.getPropertyString(BCKeyConstants.ERROR_REPORT_GETTING_CHART_DESCRIPTION));
        }
        else {
            orgLevelSummaryReportEntry.setOrgChartOfAccountDescription(orgChartDesc);
        }

        orgLevelSummaryReportEntry.setOrganizationCode(levelSummary.getOrganizationCode());
        if (orgName == null) {
            orgLevelSummaryReportEntry.setOrganizationName(kualiConfigurationService.getPropertyString(BCKeyConstants.ERROR_REPORT_GETTING_ORGANIZATION_NAME));
        }
        else {
            orgLevelSummaryReportEntry.setOrganizationName(orgName);
        }

        orgLevelSummaryReportEntry.setChartOfAccountsCode(levelSummary.getChartOfAccountsCode());
        if (chartDesc == null) {
            orgLevelSummaryReportEntry.setChartOfAccountDescription(kualiConfigurationService.getPropertyString(BCKeyConstants.ERROR_REPORT_GETTING_CHART_DESCRIPTION));
        }
        else {
            orgLevelSummaryReportEntry.setChartOfAccountDescription(chartDesc);
        }

        orgLevelSummaryReportEntry.setFundGroupCode(levelSummary.getSubFundGroup().getFundGroupCode());
        if (fundGroupDes == null) {
            orgLevelSummaryReportEntry.setFundGroupName(kualiConfigurationService.getPropertyString(BCKeyConstants.ERROR_REPORT_GETTING_FUNDGROUP_NAME));
        }
        else {
            orgLevelSummaryReportEntry.setFundGroupName(fundGroupDes);
        }

        orgLevelSummaryReportEntry.setSubFundGroupCode(levelSummary.getSubFundGroupCode());
        if (subFundGroupDes == null) {
            orgLevelSummaryReportEntry.setSubFundGroupDescription(kualiConfigurationService.getPropertyString(BCKeyConstants.ERROR_REPORT_GETTING_SUBFUNDGROUP_DESCRIPTION));
        }
        else {
            orgLevelSummaryReportEntry.setSubFundGroupDescription(subFundGroupDes);
        }

        Integer prevPrevFiscalyear = prevFiscalyear - 1;
        orgLevelSummaryReportEntry.setBaseFy(prevPrevFiscalyear.toString() + " - " + prevFiscalyear.toString().substring(2, 4));
        orgLevelSummaryReportEntry.setReqFy(prevFiscalyear.toString() + " - " + universityFiscalYear.toString().substring(2, 4));
        orgLevelSummaryReportEntry.setHeader1("Object Level Name");
        orgLevelSummaryReportEntry.setHeader2a("Lv. FTE");
        orgLevelSummaryReportEntry.setHeader2("FTE");
        orgLevelSummaryReportEntry.setHeader3("Amount");
        orgLevelSummaryReportEntry.setHeader31("FTE");
        orgLevelSummaryReportEntry.setHeader40("FTE");
        orgLevelSummaryReportEntry.setHeader4("Amount");
        orgLevelSummaryReportEntry.setHeader5(kualiConfigurationService.getPropertyString(BCKeyConstants.MSG_REPORT_HEADER_CHANGE));
        orgLevelSummaryReportEntry.setHeader6(kualiConfigurationService.getPropertyString(BCKeyConstants.MSG_REPORT_HEADER_CHANGE));
        orgLevelSummaryReportEntry.setConsHdr("");

        // For page break for objectLevelCode
        orgLevelSummaryReportEntry.setFinancialObjectLevelCode(levelSummary.getFinancialObjectLevelCode());
        orgLevelSummaryReportEntry.setIncomeExpenseCode(levelSummary.getIncomeExpenseCode());
        orgLevelSummaryReportEntry.setFinancialConsolidationSortCode(levelSummary.getFinancialConsolidationSortCode());

    }

    /**
     * builds report body
     * 
     * @param BudgetConstructionLevelSummary bcas
     */
    public void buildReportsBody(BudgetConstructionOrgLevelSummaryReport orgLevelSummaryReportEntry, BudgetConstructionLevelSummary levelSummary) {
        if (levelSummary.getFinancialConsolidationObjectCode() == null) {
            // TODO Should changed the error message.
            orgLevelSummaryReportEntry.setFinancialObjectLevelName("error to get blah blah");
        }
        else {
            orgLevelSummaryReportEntry.setFinancialObjectLevelName(levelSummary.getFinancialObjectLevel().getFinancialObjectLevelName());
        }

        orgLevelSummaryReportEntry.setPositionCsfLeaveFteQuantity(BudgetConstructionReportHelper.setDecimalDigit(levelSummary.getPositionCsfLeaveFteQuantity(), 2).toString());
        orgLevelSummaryReportEntry.setCsfFullTimeEmploymentQuantity(BudgetConstructionReportHelper.setDecimalDigit(levelSummary.getCsfFullTimeEmploymentQuantity(), 2).toString());
        orgLevelSummaryReportEntry.setAppointmentRequestedCsfFteQuantity(BudgetConstructionReportHelper.setDecimalDigit(levelSummary.getAppointmentRequestedCsfFteQuantity(), 2).toString());
        orgLevelSummaryReportEntry.setAppointmentRequestedFteQuantity(BudgetConstructionReportHelper.setDecimalDigit(levelSummary.getAppointmentRequestedFteQuantity(), 2).toString());
        
        if (levelSummary.getAccountLineAnnualBalanceAmount() != null) {
            orgLevelSummaryReportEntry.setAccountLineAnnualBalanceAmount(new Integer(levelSummary.getAccountLineAnnualBalanceAmount().intValue()));
        }

        if (levelSummary.getFinancialBeginningBalanceLineAmount() != null) {
            orgLevelSummaryReportEntry.setFinancialBeginningBalanceLineAmount(new Integer(levelSummary.getFinancialBeginningBalanceLineAmount().intValue()));
        }

        if (levelSummary.getAccountLineAnnualBalanceAmount() != null && levelSummary.getFinancialBeginningBalanceLineAmount() != null) {
            int changeAmount = levelSummary.getAccountLineAnnualBalanceAmount().subtract(levelSummary.getFinancialBeginningBalanceLineAmount()).intValue();
            orgLevelSummaryReportEntry.setAmountChange(new Integer(changeAmount));
        }

        orgLevelSummaryReportEntry.setPercentChange(BudgetConstructionReportHelper.calculatePercent(orgLevelSummaryReportEntry.getAmountChange(), orgLevelSummaryReportEntry.getFinancialBeginningBalanceLineAmount()));
    }

    /**
     * builds report total
     * 
     * @param BudgetConstructionLevelSummary bcas
     * @param List reportTotalList
     */
    public void buildReportsTotal(BudgetConstructionOrgLevelSummaryReport orgLevelSummaryReportEntry, BudgetConstructionLevelSummary levelSummary, List<BudgetConstructionOrgLevelSummaryReportTotal> levelSummaryTotalConsList, List<BudgetConstructionOrgLevelSummaryReportTotal> levelSummaryTotalGexpAndTypeList, List<BudgetConstructionOrgLevelSummaryReportTotal> levelSummaryTotalList) {

        for (BudgetConstructionOrgLevelSummaryReportTotal consTotal : levelSummaryTotalConsList) {
            if (isSameLevelSummaryEntry(levelSummary, consTotal.getBcls())) {
                orgLevelSummaryReportEntry.setTotalConsolidationDescription(levelSummary.getFinancialConsolidationObject().getFinConsolidationObjectName());
                
                // The total part shouldn't have null value, so just checking '0'
                orgLevelSummaryReportEntry.setTotalConsolidationPositionCsfLeaveFteQuantity(BudgetConstructionReportHelper.setDecimalDigit(consTotal.getTotalConsolidationPositionCsfLeaveFteQuantity(), 2).toString());
                orgLevelSummaryReportEntry.setTotalConsolidationPositionCsfFullTimeEmploymentQuantity(BudgetConstructionReportHelper.setDecimalDigit(consTotal.getTotalConsolidationPositionCsfFullTimeEmploymentQuantity(), 2).toString());
                orgLevelSummaryReportEntry.setTotalConsolidationFinancialBeginningBalanceLineAmount(consTotal.getTotalConsolidationFinancialBeginningBalanceLineAmount());
                orgLevelSummaryReportEntry.setTotalConsolidationAppointmentRequestedCsfFteQuantity(BudgetConstructionReportHelper.setDecimalDigit(consTotal.getTotalConsolidationAppointmentRequestedCsfFteQuantity(), 2).toString());
                orgLevelSummaryReportEntry.setTotalConsolidationAppointmentRequestedFteQuantity(BudgetConstructionReportHelper.setDecimalDigit(consTotal.getTotalConsolidationAppointmentRequestedFteQuantity(), 2).toString());
                orgLevelSummaryReportEntry.setTotalConsolidationAccountLineAnnualBalanceAmount(consTotal.getTotalConsolidationAccountLineAnnualBalanceAmount());

                orgLevelSummaryReportEntry.setTotalConsolidationAmountChange(consTotal.getTotalConsolidationAmountChange());
                orgLevelSummaryReportEntry.setTotalConsolidationPercentChange(BudgetConstructionReportHelper.calculatePercent(consTotal.getTotalConsolidationAmountChange(), consTotal.getTotalConsolidationFinancialBeginningBalanceLineAmount()));
            }
        }

        for (BudgetConstructionOrgLevelSummaryReportTotal gexpAndTypeTotal : levelSummaryTotalGexpAndTypeList) {
            if (isSameLevelSummaryEntryWithoutSortCode(levelSummary, gexpAndTypeTotal.getBcls())) {

                orgLevelSummaryReportEntry.setGrossFinancialBeginningBalanceLineAmount(gexpAndTypeTotal.getGrossFinancialBeginningBalanceLineAmount());
                orgLevelSummaryReportEntry.setGrossAccountLineAnnualBalanceAmount(gexpAndTypeTotal.getGrossAccountLineAnnualBalanceAmount());
                orgLevelSummaryReportEntry.setGrossAmountChange(gexpAndTypeTotal.getGrossAmountChange());
                orgLevelSummaryReportEntry.setGrossPercentChange(BudgetConstructionReportHelper.calculatePercent(gexpAndTypeTotal.getGrossAmountChange(), gexpAndTypeTotal.getGrossFinancialBeginningBalanceLineAmount()));

                if (levelSummary.getIncomeExpenseCode().equals(BCConstants.Report.INCOME_EXP_TYPE_A)) {
                    orgLevelSummaryReportEntry.setTypeDesc(kualiConfigurationService.getPropertyString(BCKeyConstants.MSG_REPORT_INCOME_EXP_DESC_UPPERCASE_REVENUE));
                }
                else {
                    orgLevelSummaryReportEntry.setTypeDesc(kualiConfigurationService.getPropertyString(BCKeyConstants.MSG_REPORT_INCOME_EXP_DESC_EXPENDITURE_NET_TRNFR));
                }
                orgLevelSummaryReportEntry.setTypePositionCsfLeaveFteQuantity(BudgetConstructionReportHelper.setDecimalDigit(gexpAndTypeTotal.getTypePositionCsfLeaveFteQuantity(), 2).toString());
                orgLevelSummaryReportEntry.setTypePositionCsfFullTimeEmploymentQuantity(BudgetConstructionReportHelper.setDecimalDigit(gexpAndTypeTotal.getTypePositionCsfFullTimeEmploymentQuantity(), 2).toString());
                orgLevelSummaryReportEntry.setTypeFinancialBeginningBalanceLineAmount(gexpAndTypeTotal.getTypeFinancialBeginningBalanceLineAmount());
                orgLevelSummaryReportEntry.setTypeAppointmentRequestedCsfFteQuantity(BudgetConstructionReportHelper.setDecimalDigit(gexpAndTypeTotal.getTypeAppointmentRequestedCsfFteQuantity(), 2).toString());
                orgLevelSummaryReportEntry.setTypeAppointmentRequestedFteQuantity(BudgetConstructionReportHelper.setDecimalDigit(gexpAndTypeTotal.getTypeAppointmentRequestedFteQuantity(), 2).toString());

                orgLevelSummaryReportEntry.setTypeAccountLineAnnualBalanceAmount(gexpAndTypeTotal.getTypeAccountLineAnnualBalanceAmount());
                orgLevelSummaryReportEntry.setTypeAmountChange(gexpAndTypeTotal.getTypeAmountChange());
                orgLevelSummaryReportEntry.setTypePercentChange(BudgetConstructionReportHelper.calculatePercent(gexpAndTypeTotal.getTypeFinancialBeginningBalanceLineAmount(), gexpAndTypeTotal.getTypeFinancialBeginningBalanceLineAmount()));
            }
        }

        for (BudgetConstructionOrgLevelSummaryReportTotal total : levelSummaryTotalList) {
            if (isSameLevelSummaryEntryWithoutSortCodeAndExpenseCode(levelSummary, total.getBcls())) {

                orgLevelSummaryReportEntry.setTotalSubFundGroupDesc(levelSummary.getSubFundGroup().getSubFundGroupDescription());
                orgLevelSummaryReportEntry.setRevenueFinancialBeginningBalanceLineAmount(total.getRevenueFinancialBeginningBalanceLineAmount());
                orgLevelSummaryReportEntry.setRevenueAccountLineAnnualBalanceAmount(total.getRevenueAccountLineAnnualBalanceAmount());
                orgLevelSummaryReportEntry.setExpenditureFinancialBeginningBalanceLineAmount(total.getExpenditureFinancialBeginningBalanceLineAmount());
                orgLevelSummaryReportEntry.setExpenditureAccountLineAnnualBalanceAmount(total.getExpenditureAccountLineAnnualBalanceAmount());

                orgLevelSummaryReportEntry.setRevenueAmountChange(total.getRevenueAmountChange());
                orgLevelSummaryReportEntry.setRevenuePercentChange(BudgetConstructionReportHelper.calculatePercent(total.getRevenueAmountChange(), total.getRevenueFinancialBeginningBalanceLineAmount()));

                orgLevelSummaryReportEntry.setExpenditureAmountChange(total.getExpenditureAmountChange());
                orgLevelSummaryReportEntry.setExpenditurePercentChange(BudgetConstructionReportHelper.calculatePercent(total.getExpenditureAmountChange(), total.getExpenditureFinancialBeginningBalanceLineAmount()));

                orgLevelSummaryReportEntry.setDifferenceFinancialBeginningBalanceLineAmount(total.getDifferenceFinancialBeginningBalanceLineAmount());
                orgLevelSummaryReportEntry.setDifferenceAccountLineAnnualBalanceAmount(total.getDifferenceAccountLineAnnualBalanceAmount());

                orgLevelSummaryReportEntry.setDifferenceAmountChange(total.getDifferenceAmountChange());
                orgLevelSummaryReportEntry.setDifferencePercentChange(BudgetConstructionReportHelper.calculatePercent(total.getDifferenceAmountChange(), total.getDifferenceFinancialBeginningBalanceLineAmount()));
            }
        }
    }


    public List calculateConsTotal(List<BudgetConstructionLevelSummary> bclsList, List<BudgetConstructionLevelSummary> simpleList) {

        BigDecimal totalConsolidationPositionCsfLeaveFteQuantity = BigDecimal.ZERO;
        BigDecimal totalConsolidationPositionCsfFullTimeEmploymentQuantity = BigDecimal.ZERO;
        Integer totalConsolidationFinancialBeginningBalanceLineAmount = new Integer(0);
        BigDecimal totalConsolidationAppointmentRequestedCsfFteQuantity = BigDecimal.ZERO;
        BigDecimal totalConsolidationAppointmentRequestedFteQuantity = BigDecimal.ZERO;
        Integer totalConsolidationAccountLineAnnualBalanceAmount = new Integer(0);
        Integer totalConsolidationAmountChange = new Integer(0);

        List returnList = new ArrayList();
        for (BudgetConstructionLevelSummary simpleBclsEntry : simpleList) {
            BudgetConstructionOrgLevelSummaryReportTotal bcLevelTotal = new BudgetConstructionOrgLevelSummaryReportTotal();
            for (BudgetConstructionLevelSummary bclsListEntry : bclsList) {
                if (isSameLevelSummaryEntry(simpleBclsEntry, bclsListEntry)) {
                    totalConsolidationFinancialBeginningBalanceLineAmount += new Integer(bclsListEntry.getFinancialBeginningBalanceLineAmount().intValue());
                    totalConsolidationAccountLineAnnualBalanceAmount += new Integer(bclsListEntry.getAccountLineAnnualBalanceAmount().intValue());
                    totalConsolidationPositionCsfLeaveFteQuantity = totalConsolidationPositionCsfLeaveFteQuantity.add(bclsListEntry.getPositionCsfLeaveFteQuantity());
                    totalConsolidationPositionCsfFullTimeEmploymentQuantity = totalConsolidationPositionCsfFullTimeEmploymentQuantity.add(bclsListEntry.getCsfFullTimeEmploymentQuantity());
                    totalConsolidationAppointmentRequestedCsfFteQuantity = totalConsolidationAppointmentRequestedCsfFteQuantity.add(bclsListEntry.getAppointmentRequestedCsfFteQuantity());
                    totalConsolidationAppointmentRequestedFteQuantity = totalConsolidationAppointmentRequestedFteQuantity.add(bclsListEntry.getAppointmentRequestedFteQuantity());
                }
            }
            bcLevelTotal.setBcls(simpleBclsEntry);
            bcLevelTotal.setTotalConsolidationPositionCsfLeaveFteQuantity(totalConsolidationPositionCsfLeaveFteQuantity);
            bcLevelTotal.setTotalConsolidationPositionCsfFullTimeEmploymentQuantity(totalConsolidationPositionCsfFullTimeEmploymentQuantity);
            bcLevelTotal.setTotalConsolidationFinancialBeginningBalanceLineAmount(totalConsolidationFinancialBeginningBalanceLineAmount);
            bcLevelTotal.setTotalConsolidationAppointmentRequestedCsfFteQuantity(totalConsolidationAppointmentRequestedCsfFteQuantity);
            bcLevelTotal.setTotalConsolidationAppointmentRequestedFteQuantity(totalConsolidationAppointmentRequestedFteQuantity);
            bcLevelTotal.setTotalConsolidationAccountLineAnnualBalanceAmount(totalConsolidationAccountLineAnnualBalanceAmount);
            totalConsolidationAmountChange = totalConsolidationAccountLineAnnualBalanceAmount - totalConsolidationFinancialBeginningBalanceLineAmount;

            bcLevelTotal.setTotalConsolidationAmountChange(totalConsolidationAmountChange);
            returnList.add(bcLevelTotal);

            totalConsolidationPositionCsfLeaveFteQuantity = BigDecimal.ZERO;
            totalConsolidationPositionCsfFullTimeEmploymentQuantity = BigDecimal.ZERO;
            totalConsolidationFinancialBeginningBalanceLineAmount = new Integer(0);
            totalConsolidationAppointmentRequestedCsfFteQuantity = BigDecimal.ZERO;
            totalConsolidationAppointmentRequestedFteQuantity = BigDecimal.ZERO;
            totalConsolidationAccountLineAnnualBalanceAmount = new Integer(0);
            totalConsolidationAmountChange = new Integer(0);
        }
        return returnList;
    }

    public List calculateGexpAndTypeTotal(List<BudgetConstructionLevelSummary> bclsList, List<BudgetConstructionLevelSummary> simpleList) {

        Integer grossFinancialBeginningBalanceLineAmount = new Integer(0);
        Integer grossAccountLineAnnualBalanceAmount = new Integer(0);
        Integer grossAmountChange = new Integer(0);
        BigDecimal grossPercentChange = BigDecimal.ZERO;

        BigDecimal typePositionCsfLeaveFteQuantity = BigDecimal.ZERO;
        BigDecimal typePositionCsfFullTimeEmploymentQuantity = BigDecimal.ZERO;
        Integer typeFinancialBeginningBalanceLineAmount = new Integer(0);
        BigDecimal typeAppointmentRequestedCsfFteQuantity = BigDecimal.ZERO;
        BigDecimal typeAppointmentRequestedFteQuantity = BigDecimal.ZERO;
        Integer typeAccountLineAnnualBalanceAmount = new Integer(0);
        Integer typeAmountChange = new Integer(0);
        BigDecimal typePercentChange = BigDecimal.ZERO;

        List returnList = new ArrayList();
        for (BudgetConstructionLevelSummary simpleBclsEntry : simpleList) {
            BudgetConstructionOrgLevelSummaryReportTotal bcLevelTotal = new BudgetConstructionOrgLevelSummaryReportTotal();
            for (BudgetConstructionLevelSummary bclsListEntry : bclsList) {
                if (isSameLevelSummaryEntryWithoutSortCode(simpleBclsEntry, bclsListEntry)) {

                    typeFinancialBeginningBalanceLineAmount += new Integer(bclsListEntry.getFinancialBeginningBalanceLineAmount().intValue());
                    typeAccountLineAnnualBalanceAmount += new Integer(bclsListEntry.getAccountLineAnnualBalanceAmount().intValue());
                    typePositionCsfLeaveFteQuantity = typePositionCsfLeaveFteQuantity.add(bclsListEntry.getPositionCsfLeaveFteQuantity());
                    typePositionCsfFullTimeEmploymentQuantity = typePositionCsfFullTimeEmploymentQuantity.add(bclsListEntry.getCsfFullTimeEmploymentQuantity());
                    typeAppointmentRequestedCsfFteQuantity = typeAppointmentRequestedCsfFteQuantity.add(bclsListEntry.getAppointmentRequestedCsfFteQuantity());
                    typeAppointmentRequestedFteQuantity = typeAppointmentRequestedFteQuantity.add(bclsListEntry.getAppointmentRequestedFteQuantity());

                    if (bclsListEntry.getIncomeExpenseCode().equals("B") && !bclsListEntry.getFinancialObjectLevelCode().equals("CORI") && !bclsListEntry.getFinancialObjectLevelCode().equals("TRIN")) {
                        grossFinancialBeginningBalanceLineAmount += new Integer(bclsListEntry.getFinancialBeginningBalanceLineAmount().intValue());
                        grossAccountLineAnnualBalanceAmount += new Integer(bclsListEntry.getAccountLineAnnualBalanceAmount().intValue());
                    }
                }
            }
            bcLevelTotal.setBcls(simpleBclsEntry);

            bcLevelTotal.setGrossFinancialBeginningBalanceLineAmount(grossFinancialBeginningBalanceLineAmount);
            bcLevelTotal.setGrossAccountLineAnnualBalanceAmount(grossAccountLineAnnualBalanceAmount);
            grossAmountChange = grossAccountLineAnnualBalanceAmount - grossFinancialBeginningBalanceLineAmount;
            bcLevelTotal.setGrossAmountChange(grossAmountChange);

            bcLevelTotal.setTypePositionCsfLeaveFteQuantity(typePositionCsfLeaveFteQuantity);
            bcLevelTotal.setTypePositionCsfFullTimeEmploymentQuantity(typePositionCsfFullTimeEmploymentQuantity);
            bcLevelTotal.setTypeFinancialBeginningBalanceLineAmount(typeFinancialBeginningBalanceLineAmount);
            bcLevelTotal.setTypeAppointmentRequestedCsfFteQuantity(typeAppointmentRequestedCsfFteQuantity);
            bcLevelTotal.setTypeAppointmentRequestedFteQuantity(typeAppointmentRequestedFteQuantity);
            bcLevelTotal.setTypeAccountLineAnnualBalanceAmount(typeAccountLineAnnualBalanceAmount);

            typeAmountChange = typeAccountLineAnnualBalanceAmount - typeFinancialBeginningBalanceLineAmount;
            bcLevelTotal.setTypeAmountChange(typeAmountChange);
            
            returnList.add(bcLevelTotal);
            grossFinancialBeginningBalanceLineAmount = new Integer(0);
            grossAccountLineAnnualBalanceAmount = new Integer(0);
            grossAmountChange = new Integer(0);
            grossPercentChange = BigDecimal.ZERO;

            typePositionCsfLeaveFteQuantity = BigDecimal.ZERO;
            typePositionCsfFullTimeEmploymentQuantity = BigDecimal.ZERO;
            typeFinancialBeginningBalanceLineAmount = new Integer(0);
            typeAppointmentRequestedCsfFteQuantity = BigDecimal.ZERO;
            typeAppointmentRequestedFteQuantity = BigDecimal.ZERO;
            typeAccountLineAnnualBalanceAmount = new Integer(0);
            typeAmountChange = new Integer(0);
            typePercentChange = BigDecimal.ZERO;
        }
        return returnList;
    }


    public List calculateTotal(List<BudgetConstructionLevelSummary> bclsList, List<BudgetConstructionLevelSummary> simpleList) {

        Integer revenueFinancialBeginningBalanceLineAmount = new Integer(0);
        Integer revenueAccountLineAnnualBalanceAmount = new Integer(0);
        Integer revenueAmountChange = new Integer(0);
        BigDecimal revenuePercentChange = BigDecimal.ZERO;

        Integer expenditureFinancialBeginningBalanceLineAmount = new Integer(0);
        Integer expenditureAccountLineAnnualBalanceAmount = new Integer(0);
        Integer expenditureAmountChange = new Integer(0);
        BigDecimal expenditurePercentChange = BigDecimal.ZERO;

        Integer differenceFinancialBeginningBalanceLineAmount = new Integer(0);
        Integer differenceAccountLineAnnualBalanceAmount = new Integer(0);
        Integer differenceAmountChange = new Integer(0);
        BigDecimal differencePercentChange = BigDecimal.ZERO;

        List returnList = new ArrayList();
        for (BudgetConstructionLevelSummary simpleBclsEntry : simpleList) {
            BudgetConstructionOrgLevelSummaryReportTotal bcLevelTotal = new BudgetConstructionOrgLevelSummaryReportTotal();
            for (BudgetConstructionLevelSummary bclsListEntry : bclsList) {
                if (isSameLevelSummaryEntryWithoutSortCodeAndExpenseCode(simpleBclsEntry, bclsListEntry)) {

                    if (bclsListEntry.getIncomeExpenseCode().equals("A")) {
                        revenueFinancialBeginningBalanceLineAmount += new Integer(bclsListEntry.getFinancialBeginningBalanceLineAmount().intValue());
                        revenueAccountLineAnnualBalanceAmount += new Integer(bclsListEntry.getAccountLineAnnualBalanceAmount().intValue());
                    }
                    else {
                        expenditureFinancialBeginningBalanceLineAmount += new Integer(bclsListEntry.getFinancialBeginningBalanceLineAmount().intValue());
                        expenditureAccountLineAnnualBalanceAmount += new Integer(bclsListEntry.getAccountLineAnnualBalanceAmount().intValue());
                    }
                }
            }

            bcLevelTotal.setBcls(simpleBclsEntry);

            bcLevelTotal.setRevenueFinancialBeginningBalanceLineAmount(revenueFinancialBeginningBalanceLineAmount);
            bcLevelTotal.setRevenueAccountLineAnnualBalanceAmount(revenueAccountLineAnnualBalanceAmount);

            revenueAmountChange = revenueAccountLineAnnualBalanceAmount - revenueFinancialBeginningBalanceLineAmount;
            bcLevelTotal.setRevenueAmountChange(revenueAmountChange);

            bcLevelTotal.setExpenditureFinancialBeginningBalanceLineAmount(expenditureFinancialBeginningBalanceLineAmount);
            bcLevelTotal.setExpenditureAccountLineAnnualBalanceAmount(expenditureAccountLineAnnualBalanceAmount);

            expenditureAmountChange = expenditureAccountLineAnnualBalanceAmount - expenditureFinancialBeginningBalanceLineAmount;
            bcLevelTotal.setExpenditureAmountChange(expenditureAmountChange);

            
            differenceFinancialBeginningBalanceLineAmount = revenueFinancialBeginningBalanceLineAmount - expenditureFinancialBeginningBalanceLineAmount;
            differenceAccountLineAnnualBalanceAmount = revenueAccountLineAnnualBalanceAmount - expenditureAccountLineAnnualBalanceAmount;
            bcLevelTotal.setDifferenceFinancialBeginningBalanceLineAmount(differenceFinancialBeginningBalanceLineAmount);
            bcLevelTotal.setDifferenceAccountLineAnnualBalanceAmount(differenceAccountLineAnnualBalanceAmount);

            differenceAmountChange = differenceAccountLineAnnualBalanceAmount - differenceFinancialBeginningBalanceLineAmount;
            bcLevelTotal.setDifferenceAmountChange(differenceAmountChange);

            returnList.add(bcLevelTotal);

            revenueFinancialBeginningBalanceLineAmount = new Integer(0);
            revenueAccountLineAnnualBalanceAmount = new Integer(0);
            revenueAmountChange = new Integer(0);
            revenuePercentChange = BigDecimal.ZERO;

            expenditureFinancialBeginningBalanceLineAmount = new Integer(0);
            expenditureAccountLineAnnualBalanceAmount = new Integer(0);
            expenditureAmountChange = new Integer(0);
            expenditurePercentChange = BigDecimal.ZERO;

            differenceFinancialBeginningBalanceLineAmount = new Integer(0);
            differenceAccountLineAnnualBalanceAmount = new Integer(0);
            differenceAmountChange = new Integer(0);
            differencePercentChange = BigDecimal.ZERO;
        }
        return returnList;
    }


    public boolean isSameLevelSummaryEntry(BudgetConstructionLevelSummary firstBcls, BudgetConstructionLevelSummary secondBcls) {
        if (isSameLevelSummaryEntryWithoutSortCode(firstBcls, secondBcls) && firstBcls.getFinancialConsolidationSortCode().equals(secondBcls.getFinancialConsolidationSortCode())) {
            return true;
        }
        else return false;
    }


    public boolean isSameLevelSummaryEntryWithoutSortCode(BudgetConstructionLevelSummary firstBcls, BudgetConstructionLevelSummary secondBcls) {
        if (isSameLevelSummaryEntryWithoutSortCodeAndExpenseCode(firstBcls, secondBcls) && firstBcls.getIncomeExpenseCode().equals(secondBcls.getIncomeExpenseCode())) {
            return true;
        }
        else return false;
    }


    public boolean isSameLevelSummaryEntryWithoutSortCodeAndExpenseCode(BudgetConstructionLevelSummary firstBcls, BudgetConstructionLevelSummary secondBcls) {
        if (firstBcls.getOrganizationChartOfAccountsCode().equals(secondBcls.getOrganizationChartOfAccountsCode()) && firstBcls.getOrganizationCode().equals(secondBcls.getOrganizationCode()) && firstBcls.getSubFundGroupCode().equals(secondBcls.getSubFundGroupCode()) && firstBcls.getChartOfAccountsCode().equals(secondBcls.getChartOfAccountsCode())) {
            return true;
        }
        else return false;
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
        returnList.add(KFSPropertyConstants.SUB_FUND_GROUP_CODE);
        returnList.add(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE);
        returnList.add(KFSPropertyConstants.INCOME_EXPENSE_CODE);
        returnList.add(KFSPropertyConstants.FINANCIAL_CONSOLIDATION_SORT_CODE);
        returnList.add(KFSPropertyConstants.FINANCIAL_LEVEL_SORT_CODE);
        return returnList;
    }

    public void setBudgetConstructionOrganizationReportsService(BudgetConstructionOrganizationReportsService budgetConstructionOrganizationReportsService) {
        this.budgetConstructionOrganizationReportsService = budgetConstructionOrganizationReportsService;
    }

    public void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }
}
