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
package org.kuali.module.effort.rules;

import static org.kuali.kfs.bo.AccountingLineOverride.CODE.EXPIRED_ACCOUNT;
import static org.kuali.kfs.bo.AccountingLineOverride.CODE.EXPIRED_ACCOUNT_AND_NON_FRINGE_ACCOUNT_USED;

import java.util.List;

import org.kuali.core.datadictionary.DataDictionary;
import org.kuali.core.service.DataDictionaryService;
import org.kuali.core.service.DictionaryValidationService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.context.SpringContext;
import org.kuali.kfs.rules.AccountingLineRuleUtil;
import org.kuali.kfs.util.ObjectUtil;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.effort.bo.EffortCertificationDetail;
import org.kuali.module.effort.document.EffortCertificationDocument;

/**
 * Provides a set of facilities to determine whether the given Effort Certification Documents or Effort Certification
 * Detail meet the specified requirements.
 */
public class EffortCertificationDocumentRuleUtil {

    /**
     * determine if the certain attributes of the given detail line have valid references
     * 
     * @param detailLine the given detail line
     * @return true if the certain attributes of the given detail line have valid references; otherwise, false
     */
    public static boolean hasValidReferences(EffortCertificationDetail detailLine) {
        DataDictionary dataDictionary = SpringContext.getBean(DataDictionaryService.class).getDataDictionary();

        boolean hasValidReference = true;
        hasValidReference &= AccountingLineRuleUtil.isValidAccount(detailLine.getAccount(), dataDictionary);
        hasValidReference &= AccountingLineRuleUtil.isValidChart(detailLine.getChartOfAccounts(), dataDictionary);
        hasValidReference &= AccountingLineRuleUtil.isValidSubAccount(detailLine.getSubAccount(), dataDictionary);

        return hasValidReference;
    }

    /**
     * determine if the fields in the detail line are in the correct formats defined in the data dictionary
     * 
     * @param detailLine the given detail line
     * @return true if the fields in the detail line are in the correct formats defined in the data dictionary; otherwise, false
     */
    public static boolean hasValidFormat(EffortCertificationDetail detailLine) {
        int originalErrorCount = GlobalVariables.getErrorMap().getErrorCount();
        SpringContext.getBean(DictionaryValidationService.class).validateBusinessObject(detailLine);
        int currentErrorCount = GlobalVariables.getErrorMap().getErrorCount();

        return currentErrorCount == originalErrorCount;
    }

    /**
     * determine if the change on the total payroll amount exceeds the specified limit
     * 
     * @param document the given effort certification document
     * @param limitOfTotalPayrollAmountChange the specified upper bound limit
     * @return true if the change on the total payroll amount exceeds the specified limit; otherwise, false
     */
    public static boolean isTotalPayrollAmountOverChanged(EffortCertificationDocument document, KualiDecimal limitOfTotalPayrollAmountChange) {
        KualiDecimal totalPayrollAmount = document.getTotalPayrollAmount();
        KualiDecimal totalOriginalPayrollAmount = document.getTotalOriginalPayrollAmount();
        KualiDecimal difference = totalOriginalPayrollAmount.subtract(totalPayrollAmount).abs();

        return difference.divide(totalOriginalPayrollAmount).isGreaterThan(limitOfTotalPayrollAmountChange);
    }

    /**
     * determine if there is a change on the payroll amount of a detail line that exceeds the specified limit
     * 
     * @param document the given effort certification document
     * @param limitOfLinePayrollAmountChange the specified upper bound limit
     * @return true if the change on the payroll amount of any detail line exceeds the specified limit; otherwise, false
     */
    public static boolean isPayrollAmountOverChanged(EffortCertificationDocument document, KualiDecimal limitOfLinePayrollAmountChange) {
        List<EffortCertificationDetail> detailLines = document.getEffortCertificationDetailLines();

        for (EffortCertificationDetail line : detailLines) {
            if (isPayrollAmountOverChanged(line, limitOfLinePayrollAmountChange)) {
                return true;
            }
        }

        return false;
    }

    /**
     * determine if the change on the payroll amount of the given detail line exceeds the specified limit
     * 
     * @param detailLine the given effort certification detail line
     * @param limitOfLinePayrollAmountChange the specified upper bound limit
     * @return true if the change on the payroll amount of the given detail line exceeds the specified limit; otherwise, false
     */
    public static boolean isPayrollAmountOverChanged(EffortCertificationDetail detailLine, KualiDecimal limitOfLinePayrollAmountChange) {
        KualiDecimal payrollAmount = detailLine.getEffortCertificationPayrollAmount();
        KualiDecimal originalPayrollAmount = detailLine.getEffortCertificationOriginalPayrollAmount();
        KualiDecimal difference = originalPayrollAmount.subtract(payrollAmount).abs();

        return difference.divide(originalPayrollAmount).isGreaterThan(limitOfLinePayrollAmountChange);
    }
    
    /**
     * determine if there is a change on the payroll amount of the given document
     * 
     * @param document the given effort certification document
     * @return true if there is the change on the payroll amount of any detail line in the given document
     */
    public static boolean isPayrollAmountChanged(EffortCertificationDocument document) {
        List<EffortCertificationDetail> detailLines = document.getEffortCertificationDetailLines();

        for (EffortCertificationDetail line : detailLines) {
            if (isPayrollAmountChanged(line)) {
                return true;
            }
        }

        return false;
    }
    
    /**
     * determine if there is a change on the payroll amount of the given detail line
     * 
     * @param detailLine the given effort certification detail line
     * @return true if there is a change on the payroll amount of the given detail line
     */
    public static boolean isPayrollAmountChanged(EffortCertificationDetail detailLine) {
        KualiDecimal payrollAmount = detailLine.getEffortCertificationPayrollAmount();
        KualiDecimal originalPayrollAmount = detailLine.getEffortCertificationOriginalPayrollAmount();
        KualiDecimal difference = originalPayrollAmount.subtract(payrollAmount);

        return difference.isNonZero();
    }

    /**
     * determine if there is a line in the given document that has the same values for the comparable fields as the given detail
     * line
     * 
     * @param document the given effort certification document
     * @param detailLine the given detail line
     * @param comparableFields the comparable fields
     * @return true if there is a line in the given document that has the same values for the comparable fields as the given detail
     *         line; otherwise, false
     */
    public static boolean hasSameExistingLine(EffortCertificationDocument document, EffortCertificationDetail detailLine, List<String> comparableFields) {
        List<EffortCertificationDetail> detailLines = document.getEffortCertificationDetailLines();

        for (EffortCertificationDetail line : detailLines) {
            if (ObjectUtil.compareObject(line, detailLine, comparableFields)) {
                return true;
            }
        }

        return false;
    }

    /**
     * determine if the sub account associated with the given detail line is a valid A21 sub account
     * 
     * @param detailLine the given detail line
     * @return true if the sub account associated with the given detail line is a valid A21 sub account; otherwise, false
     */
    public static boolean hasA21SubAccount(EffortCertificationDetail detailLine) {
        String subAccountNumber = detailLine.getSubAccountNumber();
        if (KFSConstants.getDashSubAccountNumber().equals(subAccountNumber)) {
            return false;
        }

        return ObjectUtils.isNotNull(detailLine.getSubAccount().getA21SubAccount());
    }

    /**
     * determine if the given detail line is associated with a sub account whose type code is in the given list
     * 
     * @param detailLine the given detail line
     * @param designatedCostShareSubAccountTypeCode the designated cost share sub account type codes
     * @return true if the given detail line is associated with a sub account whose type code is in the given list; otherwise, false
     */
    public static boolean hasCostShareSubAccount(EffortCertificationDetail detailLine, List<String> designatedCostShareSubAccountTypeCodes) {
        if (!hasA21SubAccount(detailLine)) {
            return false;
        }

        String costShareSubAccountTypeCode = detailLine.getSubAccount().getA21SubAccount().getSubAccountTypeCode();
        return designatedCostShareSubAccountTypeCodes.contains(costShareSubAccountTypeCode);
    }

    /**
     * determine if the given detail line is associated with a closed account
     * 
     * @param detailLine the given detail line
     * @return true if the given detail line is associated with a closed account; otherwise, false
     */
    public static boolean hasClosedAccount(EffortCertificationDetail detailLine) {
        return detailLine.getAccount().isAccountClosedIndicator();
    }

    /**
     * determine if the given detail line is associated with a contract grant account
     * 
     * @param detailLine the given detail line
     * @return true if the given detail line is associated with a contract grant account; otherwise, false
     */
    public static boolean hasContractGrantAccount(EffortCertificationDetail detailLine) {
        return detailLine.getAccount().isForContractsAndGrants();
    }

    /**
     * determine whether the expired account in the detail line can be used.
     * 
     * @param detailLine the given detail line
     * @return true if the expired account in the detail line can be used; otherwise, false
     */
    public static boolean canExpiredAccountBeUsed(EffortCertificationDetail detailLine) {
        Account account = detailLine.getAccount();

        if (account != null && account.isExpired()) {
            String overrideCode = detailLine.getOverrideCode();
            boolean canExpiredAccountUsed = EXPIRED_ACCOUNT.equals(overrideCode);
            canExpiredAccountUsed = canExpiredAccountUsed || EXPIRED_ACCOUNT_AND_NON_FRINGE_ACCOUNT_USED.equals(overrideCode);

            if (!canExpiredAccountUsed) {
                return false;
            }
        }
        return true;
    }

    /**
     * determine if the given detail line has a valid effort percentage. The percentage should be between 0 and 100.
     * 
     * @param detailLine the given detail line
     * @return true if the given detail line has a valid effort percentage; otherwise, false
     */
    public static boolean hasValidEffortPercent(EffortCertificationDetail detailLine) {
        return isValidPercent(detailLine.getEffortCertificationCalculatedOverallPercent());
    }

    /**
     * detrmine if the total effort percent of the given document is 100
     * 
     * @param document the given effort certification document
     * @return true if the total effort percent of the given document is 100
     */
    public static boolean isTotalEffortPercentageAs100(EffortCertificationDocument document) {
        return document.getTotalEffortPercent() == 100;
    }

    /**
     * determine if the payroll amount of the given detail line is not negative
     * 
     * @param detailLine the given detail line
     * @return true if the payroll amount of the given detail line is not negative; otherwise, false
     */
    public static boolean hasNonnegativePayrollAmount(EffortCertificationDetail detailLine) {
        return isPayrollAmountNonnegative(detailLine.getEffortCertificationPayrollAmount());
    }

    /**
     * determine if the given percent is between 0 and 100.
     * 
     * @param percent the given percent
     * @return true if the given percent is between 0 and 100; otherwise, false
     */
    public static boolean isValidPercent(Integer percent) {
        return percent >= 0 && percent <= 100;
    }

    /**
     * determine if the given payroll amount is greater than and equal to 0
     * 
     * @param payrollAmount the given payroll amount
     * @return true if the given payroll amount is greater than and equal to 0; otherwise, false
     */
    public static boolean isPayrollAmountNonnegative(KualiDecimal payrollAmount) {
        return payrollAmount.isGreaterEqual(KualiDecimal.ZERO);
    }
}
