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
package org.kuali.module.budget.bo;

import java.math.BigDecimal;

/**
 * Budget Construction Organization Account Summary Report Business Object.
 */
public class BudgetConstructionOrgObjectSummaryReportTotal {

    private BudgetConstructionObjectSummary bcos;

    // Total parts

    private BigDecimal totalConsolidationPositionCsfLeaveFteQuantity;
    private BigDecimal totalConsolidationPositionCsfFullTimeEmploymentQuantity;
    private Integer totalConsolidationFinancialBeginningBalanceLineAmount;
    private BigDecimal totalConsolidationAppointmentRequestedCsfFteQuantity;
    private BigDecimal totalConsolidationAppointmentRequestedFteQuantity;
    private Integer totalConsolidationAccountLineAnnualBalanceAmount;
    private Integer totalConsolidationAmountChange;
    private BigDecimal totalConsolidationPercentChange;

    private Integer grossFinancialBeginningBalanceLineAmount;
    private Integer grossAccountLineAnnualBalanceAmount;
    private Integer grossAmountChange;
    private BigDecimal grossPercentChange;

    private BigDecimal typePositionCsfLeaveFteQuantity;
    private BigDecimal typePositionCsfFullTimeEmploymentQuantity;
    private Integer typeFinancialBeginningBalanceLineAmount;
    private BigDecimal typeAppointmentRequestedCsfFteQuantity;
    private BigDecimal typeAppointmentRequestedFteQuantity;
    private Integer typeAccountLineAnnualBalanceAmount;
    private Integer typeAmountChange;
    private BigDecimal typePercentChange;

    private Integer revenueFinancialBeginningBalanceLineAmount;
    private Integer revenueAccountLineAnnualBalanceAmount;
    private Integer revenueAmountChange;
    private BigDecimal revenuePercentChange;

    private Integer expenditureFinancialBeginningBalanceLineAmount;
    private Integer expenditureAccountLineAnnualBalanceAmount;
    private Integer expenditureAmountChange;
    private BigDecimal expenditurePercentChange;

    private Integer differenceFinancialBeginningBalanceLineAmount;
    private Integer differenceAccountLineAnnualBalanceAmount;
    private Integer differenceAmountChange;
    private BigDecimal differencePercentChange;
    public BudgetConstructionObjectSummary getBcos() {
        return bcos;
    }
    public void setBcos(BudgetConstructionObjectSummary bcos) {
        this.bcos = bcos;
    }
    public Integer getDifferenceAccountLineAnnualBalanceAmount() {
        return differenceAccountLineAnnualBalanceAmount;
    }
    public void setDifferenceAccountLineAnnualBalanceAmount(Integer differenceAccountLineAnnualBalanceAmount) {
        this.differenceAccountLineAnnualBalanceAmount = differenceAccountLineAnnualBalanceAmount;
    }
    public Integer getDifferenceAmountChange() {
        return differenceAmountChange;
    }
    public void setDifferenceAmountChange(Integer differenceAmountChange) {
        this.differenceAmountChange = differenceAmountChange;
    }
    public Integer getDifferenceFinancialBeginningBalanceLineAmount() {
        return differenceFinancialBeginningBalanceLineAmount;
    }
    public void setDifferenceFinancialBeginningBalanceLineAmount(Integer differenceFinancialBeginningBalanceLineAmount) {
        this.differenceFinancialBeginningBalanceLineAmount = differenceFinancialBeginningBalanceLineAmount;
    }
    public BigDecimal getDifferencePercentChange() {
        return differencePercentChange;
    }
    public void setDifferencePercentChange(BigDecimal differencePercentChange) {
        this.differencePercentChange = differencePercentChange;
    }
    public Integer getExpenditureAccountLineAnnualBalanceAmount() {
        return expenditureAccountLineAnnualBalanceAmount;
    }
    public void setExpenditureAccountLineAnnualBalanceAmount(Integer expenditureAccountLineAnnualBalanceAmount) {
        this.expenditureAccountLineAnnualBalanceAmount = expenditureAccountLineAnnualBalanceAmount;
    }
    public Integer getExpenditureAmountChange() {
        return expenditureAmountChange;
    }
    public void setExpenditureAmountChange(Integer expenditureAmountChange) {
        this.expenditureAmountChange = expenditureAmountChange;
    }
    public Integer getExpenditureFinancialBeginningBalanceLineAmount() {
        return expenditureFinancialBeginningBalanceLineAmount;
    }
    public void setExpenditureFinancialBeginningBalanceLineAmount(Integer expenditureFinancialBeginningBalanceLineAmount) {
        this.expenditureFinancialBeginningBalanceLineAmount = expenditureFinancialBeginningBalanceLineAmount;
    }
    public BigDecimal getExpenditurePercentChange() {
        return expenditurePercentChange;
    }
    public void setExpenditurePercentChange(BigDecimal expenditurePercentChange) {
        this.expenditurePercentChange = expenditurePercentChange;
    }
    public Integer getGrossAccountLineAnnualBalanceAmount() {
        return grossAccountLineAnnualBalanceAmount;
    }
    public void setGrossAccountLineAnnualBalanceAmount(Integer grossAccountLineAnnualBalanceAmount) {
        this.grossAccountLineAnnualBalanceAmount = grossAccountLineAnnualBalanceAmount;
    }
    public Integer getGrossFinancialBeginningBalanceLineAmount() {
        return grossFinancialBeginningBalanceLineAmount;
    }
    public void setGrossFinancialBeginningBalanceLineAmount(Integer grossFinancialBeginningBalanceLineAmount) {
        this.grossFinancialBeginningBalanceLineAmount = grossFinancialBeginningBalanceLineAmount;
    }
    public Integer getRevenueAccountLineAnnualBalanceAmount() {
        return revenueAccountLineAnnualBalanceAmount;
    }
    public void setRevenueAccountLineAnnualBalanceAmount(Integer revenueAccountLineAnnualBalanceAmount) {
        this.revenueAccountLineAnnualBalanceAmount = revenueAccountLineAnnualBalanceAmount;
    }
    public Integer getRevenueAmountChange() {
        return revenueAmountChange;
    }
    public void setRevenueAmountChange(Integer revenueAmountChange) {
        this.revenueAmountChange = revenueAmountChange;
    }
    public Integer getRevenueFinancialBeginningBalanceLineAmount() {
        return revenueFinancialBeginningBalanceLineAmount;
    }
    public void setRevenueFinancialBeginningBalanceLineAmount(Integer revenueFinancialBeginningBalanceLineAmount) {
        this.revenueFinancialBeginningBalanceLineAmount = revenueFinancialBeginningBalanceLineAmount;
    }
    public BigDecimal getRevenuePercentChange() {
        return revenuePercentChange;
    }
    public void setRevenuePercentChange(BigDecimal revenuePercentChange) {
        this.revenuePercentChange = revenuePercentChange;
    }
    public Integer getTotalConsolidationAccountLineAnnualBalanceAmount() {
        return totalConsolidationAccountLineAnnualBalanceAmount;
    }
    public void setTotalConsolidationAccountLineAnnualBalanceAmount(Integer totalConsolidationAccountLineAnnualBalanceAmount) {
        this.totalConsolidationAccountLineAnnualBalanceAmount = totalConsolidationAccountLineAnnualBalanceAmount;
    }
    public Integer getTotalConsolidationAmountChange() {
        return totalConsolidationAmountChange;
    }
    public void setTotalConsolidationAmountChange(Integer totalConsolidationAmountChange) {
        this.totalConsolidationAmountChange = totalConsolidationAmountChange;
    }
    public BigDecimal getTotalConsolidationAppointmentRequestedCsfFteQuantity() {
        return totalConsolidationAppointmentRequestedCsfFteQuantity;
    }
    public void setTotalConsolidationAppointmentRequestedCsfFteQuantity(BigDecimal totalConsolidationAppointmentRequestedCsfFteQuantity) {
        this.totalConsolidationAppointmentRequestedCsfFteQuantity = totalConsolidationAppointmentRequestedCsfFteQuantity;
    }
    public BigDecimal getTotalConsolidationAppointmentRequestedFteQuantity() {
        return totalConsolidationAppointmentRequestedFteQuantity;
    }
    public void setTotalConsolidationAppointmentRequestedFteQuantity(BigDecimal totalConsolidationAppointmentRequestedFteQuantity) {
        this.totalConsolidationAppointmentRequestedFteQuantity = totalConsolidationAppointmentRequestedFteQuantity;
    }
    public Integer getTotalConsolidationFinancialBeginningBalanceLineAmount() {
        return totalConsolidationFinancialBeginningBalanceLineAmount;
    }
    public void setTotalConsolidationFinancialBeginningBalanceLineAmount(Integer totalConsolidationFinancialBeginningBalanceLineAmount) {
        this.totalConsolidationFinancialBeginningBalanceLineAmount = totalConsolidationFinancialBeginningBalanceLineAmount;
    }
    public BigDecimal getTotalConsolidationPercentChange() {
        return totalConsolidationPercentChange;
    }
    public void setTotalConsolidationPercentChange(BigDecimal totalConsolidationPercentChange) {
        this.totalConsolidationPercentChange = totalConsolidationPercentChange;
    }
    public BigDecimal getTotalConsolidationPositionCsfFullTimeEmploymentQuantity() {
        return totalConsolidationPositionCsfFullTimeEmploymentQuantity;
    }
    public void setTotalConsolidationPositionCsfFullTimeEmploymentQuantity(BigDecimal totalConsolidationPositionCsfFullTimeEmploymentQuantity) {
        this.totalConsolidationPositionCsfFullTimeEmploymentQuantity = totalConsolidationPositionCsfFullTimeEmploymentQuantity;
    }
    public BigDecimal getTotalConsolidationPositionCsfLeaveFteQuantity() {
        return totalConsolidationPositionCsfLeaveFteQuantity;
    }
    public void setTotalConsolidationPositionCsfLeaveFteQuantity(BigDecimal totalConsolidationPositionCsfLeaveFteQuantity) {
        this.totalConsolidationPositionCsfLeaveFteQuantity = totalConsolidationPositionCsfLeaveFteQuantity;
    }
    public Integer getTypeAccountLineAnnualBalanceAmount() {
        return typeAccountLineAnnualBalanceAmount;
    }
    public void setTypeAccountLineAnnualBalanceAmount(Integer typeAccountLineAnnualBalanceAmount) {
        this.typeAccountLineAnnualBalanceAmount = typeAccountLineAnnualBalanceAmount;
    }
    public Integer getTypeAmountChange() {
        return typeAmountChange;
    }
    public void setTypeAmountChange(Integer typeAmountChange) {
        this.typeAmountChange = typeAmountChange;
    }
    public BigDecimal getTypeAppointmentRequestedCsfFteQuantity() {
        return typeAppointmentRequestedCsfFteQuantity;
    }
    public void setTypeAppointmentRequestedCsfFteQuantity(BigDecimal typeAppointmentRequestedCsfFteQuantity) {
        this.typeAppointmentRequestedCsfFteQuantity = typeAppointmentRequestedCsfFteQuantity;
    }
    public BigDecimal getTypeAppointmentRequestedFteQuantity() {
        return typeAppointmentRequestedFteQuantity;
    }
    public void setTypeAppointmentRequestedFteQuantity(BigDecimal typeAppointmentRequestedFteQuantity) {
        this.typeAppointmentRequestedFteQuantity = typeAppointmentRequestedFteQuantity;
    }
    public Integer getTypeFinancialBeginningBalanceLineAmount() {
        return typeFinancialBeginningBalanceLineAmount;
    }
    public void setTypeFinancialBeginningBalanceLineAmount(Integer typeFinancialBeginningBalanceLineAmount) {
        this.typeFinancialBeginningBalanceLineAmount = typeFinancialBeginningBalanceLineAmount;
    }
    public BigDecimal getTypePercentChange() {
        return typePercentChange;
    }
    public void setTypePercentChange(BigDecimal typePercentChange) {
        this.typePercentChange = typePercentChange;
    }
    public BigDecimal getTypePositionCsfFullTimeEmploymentQuantity() {
        return typePositionCsfFullTimeEmploymentQuantity;
    }
    public void setTypePositionCsfFullTimeEmploymentQuantity(BigDecimal typePositionCsfFullTimeEmploymentQuantity) {
        this.typePositionCsfFullTimeEmploymentQuantity = typePositionCsfFullTimeEmploymentQuantity;
    }
    public BigDecimal getTypePositionCsfLeaveFteQuantity() {
        return typePositionCsfLeaveFteQuantity;
    }
    public void setTypePositionCsfLeaveFteQuantity(BigDecimal typePositionCsfLeaveFteQuantity) {
        this.typePositionCsfLeaveFteQuantity = typePositionCsfLeaveFteQuantity;
    }
    public Integer getGrossAmountChange() {
        return grossAmountChange;
    }
    public void setGrossAmountChange(Integer grossAmountChange) {
        this.grossAmountChange = grossAmountChange;
    }
    public BigDecimal getGrossPercentChange() {
        return grossPercentChange;
    }
    public void setGrossPercentChange(BigDecimal grossPercentChange) {
        this.grossPercentChange = grossPercentChange;
    }

}