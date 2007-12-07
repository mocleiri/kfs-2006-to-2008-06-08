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
package org.kuali.module.effort.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.util.spring.Logged;
import org.kuali.kfs.KFSPropertyConstants;
import org.kuali.kfs.service.ParameterService;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.effort.EffortConstants;
import org.kuali.module.effort.EffortKeyConstants;
import org.kuali.module.effort.EffortPropertyConstants;
import org.kuali.module.effort.batch.EffortCertificationExtractStep;
import org.kuali.module.effort.bo.EffortCertificationReportDefinition;
import org.kuali.module.effort.bo.EffortCertificationReportEarnPaygroup;
import org.kuali.module.effort.bo.EffortCertificationReportPosition;
import org.kuali.module.effort.document.EffortCertificationDocument;
import org.kuali.module.effort.service.EffortCertificationExtractService;
import org.kuali.module.effort.util.LaborObjectKeyFieldMap;
import org.kuali.module.effort.util.LedgerBalanceConsolidationHelper;
import org.kuali.module.gl.util.Message;
import org.kuali.module.labor.LaborConstants;
import org.kuali.module.labor.LaborPropertyConstants;
import org.kuali.module.labor.bo.LaborObject;
import org.kuali.module.labor.bo.LedgerBalance;
import org.kuali.module.labor.service.LaborLedgerBalanceService;
import org.kuali.module.labor.service.LaborLedgerEntryService;
import org.kuali.module.labor.service.LaborObjectService;
import org.kuali.module.labor.util.MessageBuilder;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is an implemeation of Effort Certification Extract process, which extracts Labor Ledger records of the employees who were
 * paid on a grant or cost shared during the selected reporting period, and generates effort certification document. Its major tasks
 * include:
 * 
 * <li>Identify employees who were paid on a grant or cost shared;</li>
 * <li>Select qualified Labor Ledger records for each identified employee;</li>
 * <li>Generate effor certification build document from the selected Labor Ledger records for each employee.</li>
 */
@Transactional
public class EffortCertificationExtractServiceImpl implements EffortCertificationExtractService {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(EffortCertificationExtractServiceImpl.class);

    private BusinessObjectService businessObjectService;
    private ParameterService parameterService;

    private LaborObjectService laborObjectService;
    private LaborLedgerEntryService laborLedgerEntryService;
    private LaborLedgerBalanceService laborLedgerBalanceService;

    /**
     * @see org.kuali.module.effort.service.EffortCertificationExtractService#extract()
     */
    @Logged
    public void extract() {
        Integer fiscalYear = Integer.valueOf(parameterService.getParameterValue(EffortCertificationExtractStep.class, null)); // TODO:
        String reportNumber = parameterService.getParameterValue(EffortCertificationExtractStep.class, null); // TODO:

        this.extract(fiscalYear, reportNumber);
    }

    /**
     * @see org.kuali.module.effort.service.EffortCertificationExtractService#extract(java.lang.Integer, java.lang.String)
     */
    @Logged
    public void extract(Integer fiscalYear, String reportNumber) {
        Map<String, String> fieldValues = EffortCertificationReportDefinition.buildKeyMap(fiscalYear, reportNumber);

        Message errorMessage = this.validateReportNumber(fieldValues);
        if (errorMessage != null) {
            LOG.error(errorMessage);
            throw new IllegalArgumentException(errorMessage.getMessage());
        }

        EffortCertificationReportDefinition reportDefinition = this.findReportDefinitionByPrimaryKey(fieldValues);
        Map<Integer, Set<String>> reportPeriods = reportDefinition.findReportPeriods();
        LaborObjectKeyFieldMap laborObjectKeyFieldMap = this.buildLaborObjectMap(reportDefinition);

        List<String> employeesWith12MonthPay = this.findEmployeesWith12MonthPay(reportDefinition, reportPeriods);
        for (String emplid : employeesWith12MonthPay) {
            Collection<LedgerBalance> qualifiedLedgerBalance = this.extractQualifiedLedgerBalances(emplid, laborObjectKeyFieldMap, reportPeriods);

            if (qualifiedLedgerBalance != null) {
                this.generateBuildDocumentForEmployee(reportDefinition, qualifiedLedgerBalance);
            }
        }
    }

    /**
     * check if a report has been defined and its docuemnt has not been generated. The combination of fiscal year and report number
     * can determine a report definition.
     * 
     * @param fiscalYear the given fiscal year
     * @param reportNumber the given report number
     * @return a message if a report has been defined and its document has not been gerenated; otherwise, return null
     */
    private Message validateReportNumber(Map<String, String> fieldValues) {
        String fiscalYear = fieldValues.get(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR);
        String reportNumber = fieldValues.get(EffortPropertyConstants.EFFORT_CERTIFICATION_REPORT_NUMBER);
        String inputValues = fiscalYear + " ," + reportNumber;

        // Fiscal Year is Required
        if (StringUtils.isEmpty(fiscalYear)) {
            return MessageBuilder.buildErrorMessage(EffortKeyConstants.ERROR_FISCAL_YEAR_MISSING, null, Message.TYPE_FATAL);
        }

        // Report Number is Required
        if (StringUtils.isEmpty(reportNumber)) {
            return MessageBuilder.buildErrorMessage(EffortKeyConstants.ERROR_REPORT_NUMBER_MISSING, null, Message.TYPE_FATAL);
        }

        // check if a report has been defined
        EffortCertificationReportDefinition reportDefinition = this.findReportDefinitionByPrimaryKey(fieldValues);
        if (reportDefinition == null) {
            return MessageBuilder.buildErrorMessage(EffortKeyConstants.ERROR_FISCAL_YEAR_OR_REPORT_NUMBER_INVALID, inputValues, Message.TYPE_FATAL);
        }

        // check if the selected report definition is still active
        if (!reportDefinition.isActive()) {
            return MessageBuilder.buildErrorMessage(EffortKeyConstants.ERROR_REPORT_DEFINITION_INACTIVE, inputValues, Message.TYPE_FATAL);
        }

        // check if any document has been generated for the selected report definition
        int countOfDocuments = businessObjectService.countMatching(EffortCertificationDocument.class, fieldValues);
        if (countOfDocuments > 0) {
            return MessageBuilder.buildErrorMessageWithPlaceHolder(EffortKeyConstants.ERROR_REPORT_DOCUMENT_EXIST, Message.TYPE_FATAL, fiscalYear, reportNumber);
        }
        return null;
    }

    /**
     * find all valid labor objects for the given report definition and store them as an object of LaborObjectKeyFieldMap
     * 
     * @param reportDefinition the specified report definition
     * @return an object of LaborObjectKeyFieldMap containing all valid labor objects for the given report definition
     */
    private LaborObjectKeyFieldMap buildLaborObjectMap(EffortCertificationReportDefinition reportDefinition) {
        Map<String, String> fieldValues = reportDefinition.buildKeyMapForCurrentReportDefinition();
        Collection<EffortCertificationReportPosition> reportPosition = businessObjectService.findMatching(EffortCertificationReportPosition.class, fieldValues);

        List<String> positionGroupCodes = new ArrayList<String>();
        for (EffortCertificationReportPosition position : reportPosition) {
            positionGroupCodes.add(position.getEffortCertificationReportPositionObjectGroupCode());
        }

        Map<String, Object> laborObjectFieldValues = new HashMap<String, Object>();
        laborObjectFieldValues.put(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR, reportDefinition.getUniversityFiscalYear());
        laborObjectFieldValues.put(LaborPropertyConstants.FINANCIAL_OBJECT_FRINGE_OR_SALARY_CODE, LaborConstants.SalaryExpenseTransfer.LABOR_LEDGER_SALARY_CODE);

        Collection<LaborObject> laborObjects = laborObjectService.findAllLaborObjectInPositionGroups(laborObjectFieldValues, positionGroupCodes);

        return new LaborObjectKeyFieldMap(laborObjects);
    }

    /**
     * find the 12 Month employees who were paid within the given report periods.
     * 
     * @param reportDefinition the specified report definition
     * @param reportPeriods the given report periods
     * @return the 12 Month employees who were paid within the given report periods
     */
    private List<String> findEmployeesWith12MonthPay(EffortCertificationReportDefinition reportDefinition, Map<Integer, Set<String>> reportPeriods) {
        Map<String, Set<String>> earnCodePayGroupMap = this.findReportEarnPayMap(reportDefinition);
        List<String> balanceTypeList = EffortConstants.ELIGIBLE_BALANCE_TYPES_FOR_EFFORT_REPORT;

        return laborLedgerEntryService.findEmployeesWith12MonthPay(reportPeriods, balanceTypeList, earnCodePayGroupMap);
    }

    private Collection<LedgerBalance> extractQualifiedLedgerBalances(String emplid, LaborObjectKeyFieldMap laborObjectKeyFieldMap, Map<Integer, Set<String>> reportPeriods) {
        Collection<LedgerBalance> ledgerBalances = this.selectLedgerBalancesForEmployee(emplid, reportPeriods);

        for (LedgerBalance balance : ledgerBalances) {
            KualiDecimal totalAmount = this.calculateTotalAmountWithinReportPeriod(balance, reportPeriods);
            if (totalAmount.isZero() || !hasValidObjectCode(balance, laborObjectKeyFieldMap) || !hasValidAccount(balance)) {
                ledgerBalances.remove(balance);
            }
        }

        if (!hasPaidByGrantAccount(ledgerBalances)) {
            return null;
        }

        return this.getQualifiedCosolidatedLedgerBalances(ledgerBalances, reportPeriods);
    }

    private void generateBuildDocumentForEmployee(EffortCertificationReportDefinition reportDefinition, Collection<LedgerBalance> ledgerBalances) {

    }
    

    private Collection<LedgerBalance> selectLedgerBalancesForEmployee(String emplid, Map<Integer, Set<String>> reportPeriods) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(KFSPropertyConstants.EMPLID, emplid);
        fieldValues.put(KFSPropertyConstants.FINANCIAL_OBJECT_TYPE_CODE, "EX"); // TODO: remove the hardcoded constants

        Map<String, String> exclusiveFieldValues = new HashMap<String, String>();
        exclusiveFieldValues.put(KFSPropertyConstants.ACCOUNT + "." + KFSPropertyConstants.ACCOUNT_TYPE_CODE, "WS"); // TODO

        Set<Integer> fiscalYears = reportPeriods.keySet();
        List<String> balanceTypeList = EffortConstants.ELIGIBLE_BALANCE_TYPES_FOR_EFFORT_REPORT;

        return laborLedgerBalanceService.findLedgerBalances(fieldValues, exclusiveFieldValues, fiscalYears, balanceTypeList);
    }

    /**
     * find a report definition by the primary key. The primary key is provided by the given field values.
     * 
     * @param fieldValues the given field values containing the primary key of a report definition
     * @return a report definition with the given primary key
     */
    private EffortCertificationReportDefinition findReportDefinitionByPrimaryKey(Map<String, String> fieldValues) {
        return (EffortCertificationReportDefinition) businessObjectService.findByPrimaryKey(EffortCertificationReportDefinition.class, fieldValues);
    }

    /**
     * store the earn code and pay group combination in a Map for the specified report definition
     * 
     * @param reportDefinition the specified report definition
     * @return the earn code and pay group combination for the specified report definition as a Map
     */
    private Map<String, Set<String>> findReportEarnPayMap(EffortCertificationReportDefinition reportDefinition) {
        Collection<EffortCertificationReportEarnPaygroup> reportEarnPay = this.findReportEarnPay(reportDefinition);
        Map<String, Set<String>> earnCodePayGroupMap = new HashMap<String, Set<String>>();

        for (EffortCertificationReportEarnPaygroup earnPay : reportEarnPay) {
            String payGroup = earnPay.getPayGroup();
            String earnCode = earnPay.getEarnCode();

            if (earnCodePayGroupMap.containsKey(payGroup)) {
                Set<String> earnCodeSet = earnCodePayGroupMap.get(payGroup);
                earnCodeSet.add(earnCode);
            }
            else {
                Set<String> earnCodeSet = new HashSet<String>();
                earnCodeSet.add(earnCode);
                earnCodePayGroupMap.put(payGroup, earnCodeSet);
            }
        }
        return earnCodePayGroupMap;
    }

    /**
     * find the earn code and pay group combination for the specified report definition
     * 
     * @param reportDefinition the specified report definition
     * @return the earn code and pay group combination for the specified report definition
     */
    private Collection<EffortCertificationReportEarnPaygroup> findReportEarnPay(EffortCertificationReportDefinition reportDefinition) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR, reportDefinition.getUniversityFiscalYear());
        fieldValues.put(EffortPropertyConstants.EFFORT_CERTIFICATION_REPORT_TYPE_CODE, reportDefinition.getEffortCertificationReportTypeCode());

        return businessObjectService.findMatching(EffortCertificationReportEarnPaygroup.class, fieldValues);
    }

    /**
     * check if the given ledger balance has an account qualified for effort reporting
     * 
     * @param ledgerBalance the given ledger balance
     * @return true if the given ledger balance has an account qualified for effort reporting; otherwise, false
     */
    private boolean hasValidAccount(LedgerBalance ledgerBalance) {
        Account account = ledgerBalance.getAccount();

        if (account == null) {
            LOG.error("");
            return false;
        }

        if (account.getFinancialHigherEdFunction() == null) {
            LOG.error("");
            return false;
        }
        return true;
    }

    private boolean hasValidObjectCode(LedgerBalance ledgerBalance, LaborObjectKeyFieldMap laborObjectKeyFieldMap) {
        Integer fiscalYear = ledgerBalance.getUniversityFiscalYear();
        String chartOfAccountsCode = ledgerBalance.getChartOfAccountsCode();
        String objectCode = ledgerBalance.getFinancialObjectCode();

        if (laborObjectKeyFieldMap.contains(objectCode, chartOfAccountsCode, fiscalYear)) {
            return true;
        }
        return false;
    }

    private Collection<LedgerBalance> getQualifiedCosolidatedLedgerBalances(Collection<LedgerBalance> ledgerBalances, Map<Integer, Set<String>> reportPeriods) {
        Collection<LedgerBalance> cosolidatedLedgerBalances = new ArrayList<LedgerBalance>();

        LedgerBalanceConsolidationHelper ledgerBalanceConsolidationHelper = new LedgerBalanceConsolidationHelper();
        ledgerBalanceConsolidationHelper.consolidateLedgerBalances(ledgerBalances);
        Map<String, LedgerBalance> ledgerBalanceMap = ledgerBalanceConsolidationHelper.getLedgerBalanceConsolidationMap();

        for (String key : ledgerBalanceMap.keySet()) {
            LedgerBalance ledgerBalance = ledgerBalanceMap.get(key);

            KualiDecimal totalAmount = this.calculateTotalAmountWithinReportPeriod(ledgerBalance, reportPeriods);
            if (totalAmount.isNonZero()) {
                cosolidatedLedgerBalances.add(ledgerBalance);
            }
        }

        KualiDecimal totalAmountForEmployee = this.calculateTotalAmountWithinReportPeriod(cosolidatedLedgerBalances, reportPeriods);
        return !totalAmountForEmployee.isPositive() ? null : cosolidatedLedgerBalances;
    }

    // TODO: incomplete
    private boolean hasPaidByGrantAccount(Collection<LedgerBalance> ledgerBalances) {

        for (LedgerBalance balance : ledgerBalances) {
            Account account = balance.getAccount();

            if (account.getSubFundGroup().getFundGroupCode() != null) {
                return true;
            }

            if (account.getSubFundGroup().getSubFundGroupCode() != null) {
                return true;
            }
        }
        return false;
    }

    private KualiDecimal calculateTotalAmountWithinReportPeriod(LedgerBalance ledgerBalance, Map<Integer, Set<String>> reportPeriods) {
        Integer fiscalYear = ledgerBalance.getUniversityFiscalYear();
        KualiDecimal totalAmount = KualiDecimal.ZERO;

        Set<String> periodCodes = reportPeriods.get(fiscalYear);
        for (String period : periodCodes) {
            totalAmount.add(ledgerBalance.getAmountByPeriod(period));
        }
        return totalAmount;
    }

    private KualiDecimal calculateTotalAmountWithinReportPeriod(Collection<LedgerBalance> ledgerBalances, Map<Integer, Set<String>> reportPeriods) {
        KualiDecimal totalAmount = KualiDecimal.ZERO;

        for (LedgerBalance ledgerBalance : ledgerBalances) {
            KualiDecimal totalAmountForOneBalance = this.calculateTotalAmountWithinReportPeriod(ledgerBalance, reportPeriods);
            totalAmount = totalAmount.add(totalAmountForOneBalance);
        }
        return totalAmount;
    }
}