/*
 * Copyright (c) 2004, 2005 The National Association of College and University Business Officers,
 * Cornell University, Trustees of Indiana University, Michigan State University Board of Trustees,
 * Trustees of San Joaquin Delta College, University of Hawai'i, The Arizona Board of Regents on
 * behalf of the University of Arizona, and the r*smart group.
 * 
 * Licensed under the Educational Community License Version 1.0 (the "License"); By obtaining,
 * using and/or copying this Original Work, you agree that you have read, understand, and will
 * comply with the terms and conditions of the Educational Community License.
 * 
 * You may obtain a copy of the License at:
 * 
 * http://kualiproject.org/license.html
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.kuali.module.kra.budget.web.struts.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.util.KualiInteger;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.module.kra.budget.KraConstants;
import org.kuali.module.kra.budget.bo.Budget;
import org.kuali.module.kra.budget.bo.BudgetModular;
import org.kuali.module.kra.budget.bo.BudgetModularPeriod;
import org.kuali.module.kra.budget.bo.BudgetNonpersonnel;
import org.kuali.module.kra.budget.bo.BudgetTaskPeriodIndirectCost;
import org.kuali.module.kra.budget.bo.BudgetUser;
import org.kuali.module.kra.budget.bo.UserAppointmentTask;
import org.kuali.module.kra.budget.bo.UserAppointmentTaskPeriod;
import org.kuali.module.kra.budget.web.struts.action.BudgetAction;

/**
 * This is used by the UI to get totals, counts, and other things needed to render the page properly.
 * 
 * @author Kuali KRA Team (kualidev@oncourse.iu.edu)
 */
public class BudgetOverviewFormHelper {

    // No getters for the following fields since they are only needed locally for initialization
    private final String TO_BE_NAMED;
    public final List HOURLY_APPOINTMENTS;
    public final List GRADUATE_RA_APPOINTMENTS;
    public final List FULL_YEAR_APPOINTMENTS;
    public final String SUMMER_GRID_APPOINTMENT;
    public final List SUMMER_GRID_APPOINTMENTS;
    
    public final String GRADUATE_ASSISTANT_NONPERSONNEL_CATEGORY_CODE;
    public final String GRADUATE_ASSISTANT_NONPERSONNEL_SUBCATEGORY_CODE;
    public final String GRADUATE_ASSISTANT_NONPERSONNEL_DESCRIPTION;

    // Helper objects
    private List<BudgetOverviewPersonnelHelper> budgetOverviewPersonnelHelpers = new ArrayList<BudgetOverviewPersonnelHelper>();
    private BudgetTaskPeriodIndirectCost indirectCostItem = new BudgetTaskPeriodIndirectCost();
    private KualiInteger modularBudgetTotalConsortiumAmount;

    // Personnel Expenses -- initialized to 0 for easy of summation in setupPersonnel
    private KualiInteger personnelSalaryAgencyRequest = new KualiInteger(0);
    private KualiInteger personnelSalaryInstitutionCostShare = new KualiInteger(0);
    private KualiInteger personnelFringeBenefitsAgencyRequest = new KualiInteger(0);
    private KualiInteger personnelFringeBenefitsInstitutionCostShare = new KualiInteger(0);

    // Total Direct Costs
    private KualiInteger totalDirectCostsAgencyRequest;
    private KualiInteger totalDirectCostsInstitutionCostShare;
    private KualiInteger totalDirectThirdPartyCostShare;

    // Modular
    private boolean overviewShowModular;
    private KualiInteger modularAdjustmentAgencyRequest;
    private KualiInteger adjustedDirectCostsAgencyRequest;

    // Total Costs
    private KualiInteger totalCostsAgencyRequest;
    private KualiInteger totalCostsInstitutionCostShare;
    private KualiInteger totalCostsThirdPartyCostShare;

    /**
     * Constructs a BudgetOverviewFormHelper. Sets necessary constants.
     */
    public BudgetOverviewFormHelper() {
        KualiConfigurationService kualiConfigurationService = SpringServiceLocator.getKualiConfigurationService();
        this.TO_BE_NAMED = kualiConfigurationService.getApplicationParameterValue(KraConstants.KRA_DEVELOPMENT_GROUP, KraConstants.TO_BE_NAMED_LABEL);
        this.HOURLY_APPOINTMENTS = Arrays.asList(kualiConfigurationService.getApplicationParameterValues(KraConstants.KRA_DEVELOPMENT_GROUP, KraConstants.KRA_BUDGET_PERSONNEL_HOURLY_APPOINTMENT_TYPES));
        this.GRADUATE_RA_APPOINTMENTS = Arrays.asList(kualiConfigurationService.getApplicationParameterValues(KraConstants.KRA_DEVELOPMENT_GROUP, KraConstants.KRA_BUDGET_PERSONNEL_GRADUATE_RESEARCH_ASSISTANT_APPOINTMENT_TYPES));
        this.FULL_YEAR_APPOINTMENTS = Arrays.asList(kualiConfigurationService.getApplicationParameterValues(KraConstants.KRA_DEVELOPMENT_GROUP, KraConstants.KRA_BUDGET_PERSONNEL_FULL_YEAR_APPOINTMENT_TYPES));
        this.SUMMER_GRID_APPOINTMENT = kualiConfigurationService.getApplicationParameterValue(KraConstants.KRA_DEVELOPMENT_GROUP, KraConstants.KRA_BUDGET_PERSONNEL_SUMMER_GRID_APPOINTMENT_TYPE);
        this.SUMMER_GRID_APPOINTMENTS = Arrays.asList(kualiConfigurationService.getApplicationParameterValues(KraConstants.KRA_DEVELOPMENT_GROUP, KraConstants.KRA_BUDGET_PERSONNEL_SUMMER_GRID_APPOINTMENT_TYPES));
        
        this.GRADUATE_ASSISTANT_NONPERSONNEL_CATEGORY_CODE = kualiConfigurationService.getApplicationParameterValue(KraConstants.KRA_DEVELOPMENT_GROUP, KraConstants.GRADUATE_ASSISTANT_NONPERSONNEL_CATEGORY_CODE);
        this.GRADUATE_ASSISTANT_NONPERSONNEL_SUBCATEGORY_CODE = kualiConfigurationService.getApplicationParameterValue(KraConstants.KRA_DEVELOPMENT_GROUP, KraConstants.GRADUATE_ASSISTANT_NONPERSONNEL_SUB_CATEGORY_CODE);
        this.GRADUATE_ASSISTANT_NONPERSONNEL_DESCRIPTION = kualiConfigurationService.getApplicationParameterValue(KraConstants.KRA_DEVELOPMENT_GROUP, KraConstants.GRADUATE_ASSISTANT_NONPERSONNEL_DESCRIPTION);
    }

    /**
     * Constructs a BudgetOverviewFormHelper based on data extracted from a BudgetForm. Will add NonpersonnelCategories,
     * BudgetNonpersonnelFormHelper, and BudgetIndirectCostFormHelper to BudgetForum. Runs
     * BudgetModularService.generateModularBudget and BudgetIndirectCostService.refreshIndirectCost to satisfy requirements of
     * recalculate which is called as the final step.
     * 
     * @param budgetForm
     */
    public BudgetOverviewFormHelper(BudgetForm budgetForm) throws Exception {
        this();

        Budget budget = budgetForm.getBudgetDocument().getBudget();

        // set up data that reculculate requires to run
        BudgetAction.setupNonpersonnelCategories(budgetForm);
        if (isOverviewShowModular(budgetForm.getCurrentTaskNumber(), budget)) {
            SpringServiceLocator.getBudgetModularService().generateModularBudget(budget, budgetForm.getNonpersonnelCategories());
        }
        SpringServiceLocator.getBudgetIndirectCostService().refreshIndirectCost(budgetForm.getBudgetDocument());
        budgetForm.setBudgetIndirectCostFormHelper(new BudgetIndirectCostFormHelper(budget.getTasks(), budget.getPeriods(), budget.getIndirectCost().getBudgetTaskPeriodIndirectCostItems()));

        budgetForm.setBudgetNonpersonnelFormHelper(recalculate(budgetForm.getCurrentTaskNumber(), budgetForm.getCurrentPeriodNumber(), budgetForm.getNonpersonnelCategories(), budgetForm.getBudgetIndirectCostFormHelper(), budget));
    }

    /**
     * <p>
     * Constructor for BudgetOverviewFormHelper with detailed argument list. Populars several interesting objects in class scope
     * that may be used to reduce service calls. Could be changed to pass those local variables as arguments if that is needed.
     * </p>
     * <p>
     * <b>Note that BudgetIndirectCostService.refreshIndirectCost and BudgetModularService.generateModularBudget has to have been
     * called before this.<b>
     * </p>
     * 
     * @param currentTaskNumber determines for which task the calculations should be performed.
     * @param currentPeriodNumber determines for which period the calculations should be performed.
     * @param nonpersonnelCategories passed to avoid refetching it from the DB when BudgetXml calls this for each task / period.
     * @param budgetIndirectCostFormHelper passed to avoid refetching it from the DB when BudgetXml calls this for each task /
     *        period.
     * @param budget with refreshIndirectCost and generateModularBudget performed on.
     * @return BudgetNonpersonnelFormHelper is returned with proper Fee Remission items from personnel (if applicable).
     */
    public BudgetNonpersonnelFormHelper recalculate(Integer currentTaskNumber, Integer currentPeriodNumber, List nonpersonnelCategories, BudgetIndirectCostFormHelper budgetIndirectCostFormHelper, Budget budget) {
        overviewShowModular = isOverviewShowModular(currentTaskNumber, budget);
        Integer currentTaskNumberIndex = SpringServiceLocator.getBudgetTaskService().getTaskIndex(currentTaskNumber, budget.getTasks());
        Integer currentPeriodNumberIndex = SpringServiceLocator.getBudgetPeriodService().getPeriodIndex(currentPeriodNumber, budget.getPeriods());

        // Used for the Personnel section, note this should be called before BudgetNonpersonnelFormHelper because it will add Fee
        // Remission items to
        // nonpersonnel for display only.
        setupPersonnel(currentTaskNumber, currentPeriodNumber, budget.getPersonnel(), budget.getNonpersonnelItems());

        // Used for the Nonpersonnel section
        BudgetNonpersonnelFormHelper budgetNonpersonnelFormHelper = new BudgetNonpersonnelFormHelper(currentTaskNumber, currentPeriodNumber, nonpersonnelCategories, budget.getNonpersonnelItems(), true);

        // Used for IDC section
        setupIndirectCost(currentTaskNumber, currentPeriodNumber, currentTaskNumberIndex, currentPeriodNumberIndex, budgetIndirectCostFormHelper, budget);

        // Used for Modular
        if (overviewShowModular) {
            setupModular(currentPeriodNumber, currentPeriodNumberIndex, nonpersonnelCategories, budget.getModularBudget());
        }

        // Calculate total fields
        setupTotals(overviewShowModular, budgetNonpersonnelFormHelper);

        return budgetNonpersonnelFormHelper;
    }

    /**
     * Loops over personnel and adds items that match the current task / period to budgetOverviewPersonnelHelpers. It will not add
     * them if they don't have any percent-effort. It also update the total fields in this class. Finally it "rolls personnel up" if
     * currentTaskNumber or currentPeriodNumber equal KraConstants.TASK_SUMMATION or KraConstants.PERIOD_SUMMATION.
     * 
     * @param currentTaskNumber
     * @param currentPeriodNumber
     * @param personnel
     * @param nonpersonnel
     */
    private void setupPersonnel(Integer currentTaskNumber, Integer currentPeriodNumber, List<BudgetUser> personnel, List<BudgetNonpersonnel> nonpersonnel) {
        // Ensure that project director appears first.
        Collections.sort(personnel);

        // loop over each person there is
        for (Iterator personnelIter = personnel.iterator(); personnelIter.hasNext();) {
            BudgetUser budgetUser = (BudgetUser) personnelIter.next();

            // loop over each task / appointment
            for (Iterator userAppointmentTasksIter = budgetUser.getUserAppointmentTasks().iterator(); userAppointmentTasksIter.hasNext();) {
                UserAppointmentTask userAppointmentTask = (UserAppointmentTask) userAppointmentTasksIter.next();

                // check if this is part of the current task, ignore item if it isn't. If currentTaskNumber == 0 it's
                // a summary and we need to take them all.
                if (currentTaskNumber.equals(KraConstants.TASK_SUMMATION) || userAppointmentTask.getBudgetTaskSequenceNumber().equals(currentTaskNumber)) {

                    // loop over each period
                    for (Iterator userAppointmentTaskPeriodIter = userAppointmentTask.getUserAppointmentTaskPeriods().iterator(); userAppointmentTaskPeriodIter.hasNext();) {
                        UserAppointmentTaskPeriod userAppointmentTaskPeriod = (UserAppointmentTaskPeriod) userAppointmentTaskPeriodIter.next();

                        // check if this is part of the
                        // 1. Current Period (if 0 it's summary, take it all)
                        // 2. Has positive %-effort
                        // 2.1. Or has an hourly rate and # of hours (either agency or university)
                        // Ignore person if any condition not met.
                        if ((currentPeriodNumber.equals(KraConstants.PERIOD_SUMMATION) || userAppointmentTaskPeriod.getBudgetPeriodSequenceNumber().equals(currentPeriodNumber)) && (userAppointmentTaskPeriod.getAgencyPercentEffortAmount().isNonZero() || userAppointmentTaskPeriod.getUniversityCostSharePercentEffortAmount().isNonZero() || userAppointmentTaskPeriod.getAgencyFullTimeEquivalentPercent().isNonZero() || userAppointmentTaskPeriod.getUniversityFullTimeEquivalentPercent().isNonZero() || (userAppointmentTaskPeriod.getUserHourlyRate().isNonZero() && (userAppointmentTaskPeriod.getUserAgencyHours().isNonZero() || userAppointmentTaskPeriod.getUserUniversityHours().isNonZero())))) {
                            boolean itemAdded = false;
                            String budgetUserLabel = "";

                            // Item matched. Check if it this is some kind of summation.
                            if (currentTaskNumber.equals(KraConstants.TASK_SUMMATION) || currentPeriodNumber.equals(KraConstants.PERIOD_SUMMATION)) {
                                // check if already exists (uuid & role equals) and do aggregation if it does
                                for (Iterator lineItemsIter = budgetOverviewPersonnelHelpers.iterator(); !itemAdded && lineItemsIter.hasNext();) {
                                    BudgetOverviewPersonnelHelper budgetOverviewBoHelper = (BudgetOverviewPersonnelHelper) lineItemsIter.next();
                                    if (budgetOverviewBoHelper.getBudgetUserSequenceNumber().equals(userAppointmentTaskPeriod.getBudgetUserSequenceNumber()) && budgetOverviewBoHelper.getUniversityAppointmentTypeCode().equals(userAppointmentTaskPeriod.getUniversityAppointmentTypeCode())) {
                                        budgetOverviewBoHelper.aggregation(userAppointmentTaskPeriod);
                                        itemAdded = true;
                                        budgetUserLabel = budgetOverviewBoHelper.getPersonName();
                                    }
                                }
                            }

                            // if it was not found for aggregation, just add it
                            if (!itemAdded) {
                                // Handle display of to be nameds' properly
                                if (budgetUser.getPersonUniversalIdentifier() == null) {
                                    budgetUserLabel = this.TO_BE_NAMED;
                                }
                                else {
                                    budgetUserLabel = budgetUser.getUser().getPersonName();
                                }

                                BudgetOverviewPersonnelHelper budgetOverviewBoHelper = new BudgetOverviewPersonnelHelper(budgetUserLabel, budgetUser.getRole(), budgetUser.isPersonProjectDirectorIndicator(), userAppointmentTaskPeriod);
                                budgetOverviewPersonnelHelpers.add(budgetOverviewBoHelper);
                            }

                            // update totals. Calculate differently for Grad / Non grad. This is a bit duplicate since this if check
                            // is already twice done in the inner class. Unfortunatly there it's different checks and by the time
                            // the code reaches this place it's either an aggregation or an individual amount. Aggregation can't be
                            // used at this point. Thus the check is re-done. :(
                            if (GRADUATE_RA_APPOINTMENTS.contains(userAppointmentTaskPeriod.getUniversityAppointmentTypeCode())) {
                                this.personnelSalaryAgencyRequest = this.personnelSalaryAgencyRequest.add(userAppointmentTaskPeriod.getAgencySalaryAmount());
                                this.personnelSalaryInstitutionCostShare = this.personnelSalaryInstitutionCostShare.add(userAppointmentTaskPeriod.getUniversitySalaryAmount());
                                this.personnelFringeBenefitsAgencyRequest = this.personnelFringeBenefitsAgencyRequest.add(userAppointmentTaskPeriod.getAgencyHealthInsuranceAmount());
                                this.personnelFringeBenefitsInstitutionCostShare = this.personnelFringeBenefitsInstitutionCostShare.add(userAppointmentTaskPeriod.getUniversityHealthInsuranceAmount());

                                // If it is a GA, we need to add Nonpersonnel Fee Remission (this is done always, even if the values
                                // are 0).
                                String budgetNonpersonnelDescription = String.format(this.GRADUATE_ASSISTANT_NONPERSONNEL_DESCRIPTION, budgetUserLabel, userAppointmentTaskPeriod.getUserCreditHoursNumber().doubleValue(), userAppointmentTaskPeriod.getUserCreditHourAmount().doubleValue(), userAppointmentTaskPeriod.getUserMiscellaneousFeeAmount().intValue());
                                BudgetNonpersonnel budgetNonpersonnel = new BudgetNonpersonnel(userAppointmentTaskPeriod.getBudgetTaskSequenceNumber(), userAppointmentTaskPeriod.getBudgetPeriodSequenceNumber(), this.GRADUATE_ASSISTANT_NONPERSONNEL_CATEGORY_CODE, this.GRADUATE_ASSISTANT_NONPERSONNEL_SUBCATEGORY_CODE, budgetNonpersonnelDescription, userAppointmentTaskPeriod.getAgencyRequestedFeesAmount(), userAppointmentTaskPeriod.getUniversityRequestedFeesAmount());
                                budgetNonpersonnel.refreshReferenceObject("nonpersonnelObjectCode");
                                budgetNonpersonnel.getNonpersonnelObjectCode().refreshReferenceObject("nonpersonnelSubCategory");

                                nonpersonnel.add(budgetNonpersonnel);
                            }
                            else {
                                this.personnelSalaryAgencyRequest = this.personnelSalaryAgencyRequest.add(userAppointmentTaskPeriod.getAgencyRequestTotalAmount());
                                this.personnelSalaryInstitutionCostShare = this.personnelSalaryInstitutionCostShare.add(userAppointmentTaskPeriod.getUniversityCostShareRequestTotalAmount());
                                this.personnelFringeBenefitsAgencyRequest = this.personnelFringeBenefitsAgencyRequest.add(userAppointmentTaskPeriod.getAgencyFringeBenefitTotalAmount());
                                this.personnelFringeBenefitsInstitutionCostShare = this.personnelFringeBenefitsInstitutionCostShare.add(userAppointmentTaskPeriod.getUniversityCostShareFringeBenefitTotalAmount());
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Sets the indirectCostItems in class scope for the current task / period (per BudgetForm.currentTask/Period). It also
     * initializes IDC data in budgetForm.
     * 
     * @param currentTaskNumber
     * @param currentPeriodNumber
     * @param currentTaskNumberIndex
     * @param currentPeriodNumberIndex
     * @param budgetIndirectCostFormHelper
     * @param budget
     */
    private void setupIndirectCost(Integer currentTaskNumber, Integer currentPeriodNumber, Integer currentTaskNumberIndex, Integer currentPeriodNumberIndex, BudgetIndirectCostFormHelper budgetIndirectCostFormHelper, Budget budget) {
        if (KraConstants.TASK_SUMMATION.equals(currentTaskNumber) && KraConstants.PERIOD_SUMMATION.equals(currentPeriodNumber)) {
            // Summary / Summary
            indirectCostItem = budgetIndirectCostFormHelper.getPeriodSubTotal();
        }
        else if (KraConstants.TASK_SUMMATION.equals(currentTaskNumber)) {
            // Task Summation
            indirectCostItem = (BudgetTaskPeriodIndirectCost) budgetIndirectCostFormHelper.getPeriodTotals().get(currentPeriodNumberIndex);
        }
        else if (KraConstants.PERIOD_SUMMATION.equals(currentPeriodNumber)) {
            // Period Summation
            indirectCostItem = (BudgetTaskPeriodIndirectCost) budgetIndirectCostFormHelper.getTaskTotals().get(currentTaskNumberIndex);
        }
        else {
            // Individual Task / Period
            for (Iterator indirectCostItemsIter = budget.getIndirectCost().getBudgetTaskPeriodIndirectCostItems().iterator(); indirectCostItemsIter.hasNext();) {
                indirectCostItem = (BudgetTaskPeriodIndirectCost) indirectCostItemsIter.next();

                if (indirectCostItem.getBudgetTaskSequenceNumber().equals(currentTaskNumber) && indirectCostItem.getBudgetPeriodSequenceNumber().equals(currentPeriodNumber)) {
                    break;
                }
            }
        }
    }

    /**
     * Extracts modular amounts from business objects or if this is not modular budget it just sets the total costs. Note that
     * BudgetModularService.generateModularBudget has to have been called before this.
     * 
     * @param currentPeriodNumber
     * @param currentPeriodNumberIndex
     * @param nonpersonnelCategories
     * @param budget
     */
    private void setupModular(Integer currentPeriodNumber, Integer currentPeriodNumberIndex, List nonpersonnelCategories, BudgetModular budgetModular) {
        this.modularBudgetTotalConsortiumAmount = budgetModular.getTotalConsortiumAmount();

        if (currentPeriodNumber.equals(KraConstants.PERIOD_SUMMATION)) {
            modularAdjustmentAgencyRequest = budgetModular.getTotalModularVarianceAmount();
            adjustedDirectCostsAgencyRequest = budgetModular.getTotalAdjustedModularDirectCostAmount();
        }
        else {
            BudgetModularPeriod budgetModularPeriod = (BudgetModularPeriod) budgetModular.getBudgetModularPeriods().get(currentPeriodNumberIndex);
            modularAdjustmentAgencyRequest = budgetModularPeriod.getModularVarianceAmount();
            adjustedDirectCostsAgencyRequest = budgetModularPeriod.getBudgetAdjustedModularDirectCostAmount();
        }
    }

    /**
     * Most totals can be calculated on the fly with simple getters. The totals in this method though depend on objects that are not
     * stored in BudgetOverviewFormHelper. So rather then storing those objects, the amounts are just extracted, calculated and set
     * within this helper. The other advantage is that this seperates the values from where they come from which centralizes how
     * they are calculated. That is what this method does.
     * 
     * @param showModular affects total calculation
     * @param budgetNonpersonnelFormHelper
     */
    private void setupTotals(boolean overviewShowModular, BudgetNonpersonnelFormHelper budgetNonpersonnelFormHelper) {
        totalDirectCostsAgencyRequest = this.getTotalPersonnelAgencyRequest().add(budgetNonpersonnelFormHelper.getNonpersonnelAgencyTotal());
        totalDirectCostsInstitutionCostShare = getTotalPersonnelInstitutionCostShare().add(budgetNonpersonnelFormHelper.getNonpersonnelUnivCostShareTotal());
        totalDirectThirdPartyCostShare = budgetNonpersonnelFormHelper.getNonpersonnelThirdPartyCostShareTotal();

        if (overviewShowModular) {
            totalCostsAgencyRequest = adjustedDirectCostsAgencyRequest.add(indirectCostItem.getCalculatedIndirectCost()).add(this.modularBudgetTotalConsortiumAmount);
        }
        else {
            totalCostsAgencyRequest = totalDirectCostsAgencyRequest.add(indirectCostItem.getCalculatedIndirectCost());
        }

        totalCostsInstitutionCostShare = personnelSalaryInstitutionCostShare.add(personnelFringeBenefitsInstitutionCostShare).add(budgetNonpersonnelFormHelper.getNonpersonnelUnivCostShareTotal()).add(indirectCostItem.getCostShareCalculatedIndirectCost());

        totalCostsThirdPartyCostShare = budgetNonpersonnelFormHelper.getNonpersonnelThirdPartyCostShareTotal();
    }

    /**
     * Decides whether overview should show modular sections or not.
     * 
     * @param currentTaskNumber
     * @param budget
     * @return whether overview page should show modular or not
     */
    private boolean isOverviewShowModular(Integer currentTaskNumber, Budget budget) {
        return budget.isAgencyModularIndicator() && currentTaskNumber.equals(KraConstants.TASK_SUMMATION);
    }

    /**
     * Convenience method for Overview total personnel line.
     * 
     * @return Returns totalPersonnelAgencyRequest.
     */
    public KualiInteger getTotalPersonnelAgencyRequest() {
        return this.personnelSalaryAgencyRequest.add(personnelFringeBenefitsAgencyRequest);
    }

    /**
     * Convenience method for Overview total personnel line.
     * 
     * @return Returns totalPersonnelInstitutionCostShare.
     */
    public KualiInteger getTotalPersonnelInstitutionCostShare() {
        return this.personnelSalaryInstitutionCostShare.add(personnelFringeBenefitsInstitutionCostShare);
    }

    /**
     * Convenience method for Overview total indirect costs line.
     * 
     * @return Returns totalIndirectCostsAgencyRequest.
     */
    public KualiInteger getTotalIndirectCostsAgencyRequest() {
        return indirectCostItem.getCalculatedIndirectCost();
    }

    /**
     * Convenience method for Overview total indirect costs line.
     * 
     * @return Returns totalIndirectCostsInstitutionCostShare.
     */
    public KualiInteger getTotalIndirectCostsInstitutionCostShare() {
        return indirectCostItem.getCostShareCalculatedIndirectCost();
    }

    /**
     * Convenience method for BudgetXml.
     * 
     * @return Returns totalIndirectCostsInstitutionCostShareUnrecovered.
     */
    public KualiInteger getTotalIndirectCostsInstitutionCostShareUnrecovered() {
        return indirectCostItem.getCostShareUnrecoveredIndirectCost();
    }

    /**
     * @return Returns the budgetOverviewPersonnelHelpers.
     */
    public List<BudgetOverviewPersonnelHelper> getBudgetOverviewPersonnelHelpers() {
        return budgetOverviewPersonnelHelpers;
    }

    /**
     * @param budgetOverviewPersonnelHelpers The budgetOverviewPersonnelHelpers to set.
     */
    public void setBudgetOverviewPersonnelHelpers(List<BudgetOverviewPersonnelHelper> lineItems) {
        this.budgetOverviewPersonnelHelpers = lineItems;
    }

    /**
     * @return Returns the personnelFringeBenefitsAgencyRequest.
     */
    public KualiInteger getPersonnelFringeBenefitsAgencyRequest() {
        return personnelFringeBenefitsAgencyRequest;
    }

    /**
     * @param personnelFringeBenefitsAgencyRequest The personnelFringeBenefitsAgencyRequest to set.
     */
    public void setPersonnelFringeBenefitsAgencyRequest(KualiInteger agencyFringeBenefitTotalAmount) {
        this.personnelFringeBenefitsAgencyRequest = agencyFringeBenefitTotalAmount;
    }

    /**
     * @return Returns the personnelSalaryAgencyRequest.
     */
    public KualiInteger getPersonnelSalaryAgencyRequest() {
        return personnelSalaryAgencyRequest;
    }

    /**
     * @param personnelSalaryAgencyRequest The personnelSalaryAgencyRequest to set.
     */
    public void setPersonnelSalaryAgencyRequest(KualiInteger agencyRequestTotalAmount) {
        this.personnelSalaryAgencyRequest = agencyRequestTotalAmount;
    }

    /**
     * @return Returns the personnelFringeBenefitsInstitutionCostShare.
     */
    public KualiInteger getPersonnelFringeBenefitsInstitutionCostShare() {
        return personnelFringeBenefitsInstitutionCostShare;
    }

    /**
     * @param personnelFringeBenefitsInstitutionCostShare The personnelFringeBenefitsInstitutionCostShare to set.
     */
    public void setPersonnelFringeBenefitsInstitutionCostShare(KualiInteger universityCostShareFringeBenefitTotalAmount) {
        this.personnelFringeBenefitsInstitutionCostShare = universityCostShareFringeBenefitTotalAmount;
    }

    /**
     * @return Returns the personnelSalaryInstitutionCostShare.
     */
    public KualiInteger getPersonnelSalaryInstitutionCostShare() {
        return personnelSalaryInstitutionCostShare;
    }

    /**
     * @param personnelSalaryInstitutionCostShare The personnelSalaryInstitutionCostShare to set.
     */
    public void setPersonnelSalaryInstitutionCostShare(KualiInteger universityCostShareRequestTotalAmount) {
        this.personnelSalaryInstitutionCostShare = universityCostShareRequestTotalAmount;
    }

    /**
     * @return Returns the totalDirectCostsInstitutionCostShare.
     */
    public KualiInteger getTotalDirectCostsInstitutionCostShare() {
        return totalDirectCostsInstitutionCostShare;
    }

    /**
     * @param totalDirectCostsInstitutionCostShare The totalDirectCostsInstitutionCostShare to set.
     */
    public void setTotalDirectCostsInstitutionCostShare(KualiInteger totalDirectCostsInstitutionCostShare) {
        this.totalDirectCostsInstitutionCostShare = totalDirectCostsInstitutionCostShare;
    }

    /**
     * @return Returns the adjustedDirectCostsAgencyRequest.
     */
    public KualiInteger getAdjustedDirectCostsAgencyRequest() {
        return adjustedDirectCostsAgencyRequest;
    }

    /**
     * @param adjustedDirectCostsAgencyRequest The adjustedDirectCostsAgencyRequest to set.
     */
    public void setAdjustedDirectCostsAgencyRequest(KualiInteger adjustedDirectCosts) {
        this.adjustedDirectCostsAgencyRequest = adjustedDirectCosts;
    }

    /**
     * @return Returns the modularAdjustmentAgencyRequest.
     */
    public KualiInteger getModularAdjustmentAgencyRequest() {
        return modularAdjustmentAgencyRequest;
    }

    /**
     * @param modularAdjustmentAgencyRequest The modularAdjustmentAgencyRequest to set.
     */
    public void setModularAdjustmentAgencyRequest(KualiInteger modularAdjustment) {
        this.modularAdjustmentAgencyRequest = modularAdjustment;
    }

    /**
     * @return Returns the totalCostsAgencyRequest.
     */
    public KualiInteger getTotalCostsAgencyRequest() {
        return totalCostsAgencyRequest;
    }

    /**
     * @param totalCostsAgencyRequest The totalCostsAgencyRequest to set.
     */
    public void setTotalCostsAgencyRequest(KualiInteger totalCostsAgencyRequest) {
        this.totalCostsAgencyRequest = totalCostsAgencyRequest;
    }

    /**
     * @return Returns the totalCostsInstitutionCostShare.
     */
    public KualiInteger getTotalCostsInstitutionCostShare() {
        return totalCostsInstitutionCostShare;
    }

    /**
     * @param totalCostsInstitutionCostShare The totalCostsInstitutionCostShare to set.
     */
    public void setTotalCostsInstitutionCostShare(KualiInteger totalCostsInstitutionCostShare) {
        this.totalCostsInstitutionCostShare = totalCostsInstitutionCostShare;
    }

    /**
     * @return Returns the totalCostsThirdPartyCostShare.
     */
    public KualiInteger getTotalCostsThirdPartyCostShare() {
        return totalCostsThirdPartyCostShare;
    }

    /**
     * @param totalCostsThirdPartyCostShare The totalCostsThirdPartyCostShare to set.
     */
    public void setTotalCostsThirdPartyCostShare(KualiInteger totalCostsThirdPartyCostShare) {
        this.totalCostsThirdPartyCostShare = totalCostsThirdPartyCostShare;
    }

    /**
     * @return Returns the totalDirectCostsAgencyRequest.
     */
    public KualiInteger getTotalDirectCostsAgencyRequest() {
        return totalDirectCostsAgencyRequest;
    }

    /**
     * @param totalDirectCostsAgencyRequest The totalDirectCostsAgencyRequest to set.
     */
    public void setTotalDirectCostsAgencyRequest(KualiInteger totalDirectCostsAgencyRequest) {
        this.totalDirectCostsAgencyRequest = totalDirectCostsAgencyRequest;
    }

    /**
     * @return Returns the totalDirectThirdPartyCostShare.
     */
    public KualiInteger getTotalDirectThirdPartyCostShare() {
        return totalDirectThirdPartyCostShare;
    }

    /**
     * @param totalDirectThirdPartyCostShare The totalDirectThirdPartyCostShare to set.
     */
    public void setTotalDirectThirdPartyCostShare(KualiInteger totalDirectThirdPartyCostShare) {
        this.totalDirectThirdPartyCostShare = totalDirectThirdPartyCostShare;
    }

    /**
     * @return Returns the overviewShowModular.
     */
    public boolean isOverviewShowModular() {
        return overviewShowModular;
    }

    /**
     * @param overviewShowModular The overviewShowModular to set.
     */
    public void setOverviewShowModular(boolean overviewShowModular) {
        this.overviewShowModular = overviewShowModular;
    }

    /**
     * Inner class that are elements of budgetOverviewPersonnelHelpers. This represents a personnel line item. It also has a method
     * for doing aggregation.
     */
    public class BudgetOverviewPersonnelHelper {
        // Following two are used for unique identifier (for aggregation), not used on display
        private Integer budgetUserSequenceNumber;
        private String universityAppointmentTypeCode;

        // A convenient helper to properly display hrs. for hourly employees.
        private boolean hourlyAppointmentType = false;

        // Following are listed in the order they appear on Overview. That might be helpful to "connect the dots." ;)
        private String personName;
        private String role;
        private String appointmentTypeDescription;

        private KualiInteger agencyPercentEffortAmount;
        private KualiInteger userAgencyHours;
        private KualiInteger agencyRequestTotalAmount;
        private KualiInteger universityCostSharePercentEffortAmount;
        private KualiInteger userUniversityHours;
        private KualiInteger universityCostShareRequestTotalAmount;
        private KualiDecimal contractsAndGrantsFringeRateAmount; // from budgetFringeRate
        private KualiInteger agencyFringeBenefitTotalAmount;
        private KualiDecimal universityCostShareFringeRateAmount; // from budgetFringeRate
        private KualiInteger universityCostShareFringeBenefitTotalAmount;

        // Not used on overview interface but useful for BudgetXml.
        private boolean personProjectDirectorIndicator;
        private String userBudgetPeriodSalaryAmount; // String because this can be a KualiDecimal or KualiInteger, easier to treat it as String then
        private Integer personWeeksAmount;

        /**
         * Constructor that takes a person representation from our BO and puts it into a representation friendly to our interface.
         * 
         * @param personName
         * @param role
         * @param isPersonProjectDirectorIndicator
         * @param userAppointmentTaskPeriod
         */
        public BudgetOverviewPersonnelHelper(String personName, String role, boolean isPersonProjectDirectorIndicator, UserAppointmentTaskPeriod userAppointmentTaskPeriod) {
            this.budgetUserSequenceNumber = userAppointmentTaskPeriod.getBudgetUserSequenceNumber();
            this.universityAppointmentTypeCode = userAppointmentTaskPeriod.getUniversityAppointmentTypeCode();

            this.personName = personName;
            this.role = role;
            this.appointmentTypeDescription = userAppointmentTaskPeriod.getBudgetFringeRate().getAppointmentType().getAppointmentTypeAbbrieviation();

            this.personProjectDirectorIndicator = isPersonProjectDirectorIndicator;
            this.personWeeksAmount = userAppointmentTaskPeriod.getPersonWeeksAmount();

            // Following is for proper display of "hrs." on hourly appointments.
            if (HOURLY_APPOINTMENTS.contains(universityAppointmentTypeCode)) {
                // Hourly Appointment
                this.hourlyAppointmentType = true;
                
                this.userBudgetPeriodSalaryAmount = ObjectUtils.toString(userAppointmentTaskPeriod.getUserHourlyRate());
                
                this.userAgencyHours = userAppointmentTaskPeriod.getUserAgencyHours();
                this.userUniversityHours = userAppointmentTaskPeriod.getUserUniversityHours();
            }
            else if (GRADUATE_RA_APPOINTMENTS.contains(universityAppointmentTypeCode)) {
                // Graduate Research Assistant
                this.userBudgetPeriodSalaryAmount = ObjectUtils.toString(userAppointmentTaskPeriod.getUserBudgetPeriodSalaryAmount());
                
                this.agencyPercentEffortAmount = userAppointmentTaskPeriod.getAgencyFullTimeEquivalentPercent();
                this.universityCostSharePercentEffortAmount = userAppointmentTaskPeriod.getUniversityFullTimeEquivalentPercent();

                // Rates are not to exceed 100%. This check is specifically present for GAs because GAs can have a %-effort
                // above 100%.
                if (this.agencyPercentEffortAmount.isGreaterEqual(KraConstants.PERSONNEL_MAX_PERCENTAGE)) {
                    this.agencyPercentEffortAmount = KraConstants.PERSONNEL_MAX_PERCENTAGE;
                }
                if (this.universityCostSharePercentEffortAmount.isGreaterEqual(KraConstants.PERSONNEL_MAX_PERCENTAGE)) {
                    this.universityCostSharePercentEffortAmount = KraConstants.PERSONNEL_MAX_PERCENTAGE;
                }
            }
            else {
                // All other appointments
                this.userBudgetPeriodSalaryAmount = ObjectUtils.toString(userAppointmentTaskPeriod.getUserBudgetPeriodSalaryAmount());
                
                this.agencyPercentEffortAmount = userAppointmentTaskPeriod.getAgencyPercentEffortAmount();
                this.universityCostSharePercentEffortAmount = userAppointmentTaskPeriod.getUniversityCostSharePercentEffortAmount();
            }

            // Following is for proper display of Graduate Research Assistants Appointments.
            if (GRADUATE_RA_APPOINTMENTS.contains(universityAppointmentTypeCode)) {
                this.agencyRequestTotalAmount = userAppointmentTaskPeriod.getAgencySalaryAmount();
                this.universityCostShareRequestTotalAmount = userAppointmentTaskPeriod.getUniversitySalaryAmount();
                this.agencyFringeBenefitTotalAmount = userAppointmentTaskPeriod.getAgencyHealthInsuranceAmount();
                this.universityCostShareFringeBenefitTotalAmount = userAppointmentTaskPeriod.getUniversityHealthInsuranceAmount();

                // %-effort for Fringe Rate are always 0 for GAs
                this.contractsAndGrantsFringeRateAmount = new KualiDecimal(0);
                this.universityCostShareFringeRateAmount = new KualiDecimal(0);
            }
            else {
                this.agencyRequestTotalAmount = userAppointmentTaskPeriod.getAgencyRequestTotalAmount();
                this.universityCostShareRequestTotalAmount = userAppointmentTaskPeriod.getUniversityCostShareRequestTotalAmount();
                this.agencyFringeBenefitTotalAmount = userAppointmentTaskPeriod.getAgencyFringeBenefitTotalAmount();
                this.universityCostShareFringeBenefitTotalAmount = userAppointmentTaskPeriod.getUniversityCostShareFringeBenefitTotalAmount();

                // %-effort for Fringe Rate
                this.contractsAndGrantsFringeRateAmount = userAppointmentTaskPeriod.getBudgetFringeRate().getContractsAndGrantsFringeRateAmount();
                this.universityCostShareFringeRateAmount = userAppointmentTaskPeriod.getBudgetFringeRate().getUniversityCostShareFringeRateAmount();
            }
        }

        /**
         * Used for aggregation of BudgetOverviewPersonnelHelper. Happens when user is viewing a summary of any kind. Sums up
         * percentages, hours, and amounts. It caps percentage at 100%. Fringe rate percentages are not touched.
         * 
         * @param userAppointmentTaskPeriod to be added into the current item. It is assumed that item passed in have equal
         *        budgetUserSequenceNumber and universityAppointmentTypeCode.
         */
        public void aggregation(UserAppointmentTaskPeriod userAppointmentTaskPeriod) {
            if (!this.getBudgetUserSequenceNumber().equals(userAppointmentTaskPeriod.getBudgetUserSequenceNumber()) && !this.getUniversityAppointmentTypeCode().equals(userAppointmentTaskPeriod.getUniversityAppointmentTypeCode())) {
                throw new IllegalArgumentException("BudgetUserSequenceNumber or UniversityAppointmentTypeCode does not match for aggregation.");
            }

            // Following is for proper display of "hrs." on hourly appointments.
            if (this.hourlyAppointmentType) {
                this.userAgencyHours = this.userAgencyHours.add(userAppointmentTaskPeriod.getUserAgencyHours());
                this.userUniversityHours = this.userUniversityHours.add(userAppointmentTaskPeriod.getUserUniversityHours());
            }
            else {
                // %-effort does not display on summaries per KULERA-514.
                this.agencyPercentEffortAmount = null;
                this.universityCostSharePercentEffortAmount = null;
            }

            // Following is for proper display of Graduate Research Assistants Appointments.
            if (GRADUATE_RA_APPOINTMENTS.contains(universityAppointmentTypeCode)) {
                this.agencyRequestTotalAmount = this.agencyRequestTotalAmount.add(userAppointmentTaskPeriod.getAgencySalaryAmount());
                this.universityCostShareRequestTotalAmount = this.universityCostShareRequestTotalAmount.add(userAppointmentTaskPeriod.getUniversitySalaryAmount());
                this.agencyFringeBenefitTotalAmount = this.agencyFringeBenefitTotalAmount.add(userAppointmentTaskPeriod.getAgencyHealthInsuranceAmount());
                this.universityCostShareFringeBenefitTotalAmount = this.universityCostShareFringeBenefitTotalAmount.add(userAppointmentTaskPeriod.getUniversityHealthInsuranceAmount());
            }
            else {
                this.agencyRequestTotalAmount = this.agencyRequestTotalAmount.add(userAppointmentTaskPeriod.getAgencyRequestTotalAmount());
                this.universityCostShareRequestTotalAmount = this.universityCostShareRequestTotalAmount.add(userAppointmentTaskPeriod.getUniversityCostShareRequestTotalAmount());
                this.agencyFringeBenefitTotalAmount = this.agencyFringeBenefitTotalAmount.add(userAppointmentTaskPeriod.getAgencyFringeBenefitTotalAmount());
                this.universityCostShareFringeBenefitTotalAmount = this.universityCostShareFringeBenefitTotalAmount.add(userAppointmentTaskPeriod.getUniversityCostShareFringeBenefitTotalAmount());
            }

            // % effort for fringe rates are not affected by aggregation.
        }

        /**
         * @return Returns the agencyFringeBenefitTotalAmount.
         */
        public KualiInteger getAgencyFringeBenefitTotalAmount() {
            return agencyFringeBenefitTotalAmount;
        }

        /**
         * @param agencyFringeBenefitTotalAmount The agencyFringeBenefitTotalAmount to set.
         */
        public void setAgencyFringeBenefitTotalAmount(KualiInteger agencyFringeBenefitTotalAmount) {
            this.agencyFringeBenefitTotalAmount = agencyFringeBenefitTotalAmount;
        }

        /**
         * @return Returns the agencyPercentEffortAmount.
         */
        public KualiInteger getAgencyPercentEffortAmount() {
            return agencyPercentEffortAmount;
        }

        /**
         * @param agencyPercentEffortAmount The agencyPercentEffortAmount to set.
         */
        public void setAgencyPercentEffortAmount(KualiInteger agencyPercentEffortAmount) {
            this.agencyPercentEffortAmount = agencyPercentEffortAmount;
        }

        /**
         * @return Returns the agencyRequestTotalAmount.
         */
        public KualiInteger getAgencyRequestTotalAmount() {
            return agencyRequestTotalAmount;
        }

        /**
         * @param agencyRequestTotalAmount The agencyRequestTotalAmount to set.
         */
        public void setAgencyRequestTotalAmount(KualiInteger agencyRequestTotalAmount) {
            this.agencyRequestTotalAmount = agencyRequestTotalAmount;
        }

        /**
         * @return Returns the appointmentTypeDescription.
         */
        public String getAppointmentTypeDescription() {
            return appointmentTypeDescription;
        }

        /**
         * @param appointmentTypeDescription The appointmentTypeDescription to set.
         */
        public void setAppointmentTypeDescription(String appointmentTypeDescription) {
            this.appointmentTypeDescription = appointmentTypeDescription;
        }

        /**
         * @return Returns the budgetUserSequenceNumber.
         */
        public Integer getBudgetUserSequenceNumber() {
            return budgetUserSequenceNumber;
        }

        /**
         * @param budgetUserSequenceNumber The budgetUserSequenceNumber to set.
         */
        public void setBudgetUserSequenceNumber(Integer budgetUserSequenceNumber) {
            this.budgetUserSequenceNumber = budgetUserSequenceNumber;
        }

        /**
         * @return Returns the contractsAndGrantsFringeRateAmount.
         */
        public KualiDecimal getContractsAndGrantsFringeRateAmount() {
            return contractsAndGrantsFringeRateAmount;
        }

        /**
         * @param contractsAndGrantsFringeRateAmount The contractsAndGrantsFringeRateAmount to set.
         */
        public void setContractsAndGrantsFringeRateAmount(KualiDecimal contractsAndGrantsFringeRateAmount) {
            this.contractsAndGrantsFringeRateAmount = contractsAndGrantsFringeRateAmount;
        }

        /**
         * @return Returns the personName.
         */
        public String getPersonName() {
            return personName;
        }

        /**
         * @param personName The personName to set.
         */
        public void setPersonName(String personName) {
            this.personName = personName;
        }

        /**
         * @return Returns the role.
         */
        public String getRole() {
            return role;
        }

        /**
         * @param role The role to set.
         */
        public void setRole(String role) {
            this.role = role;
        }

        /**
         * @return Returns the universityAppointmentTypeCode.
         */
        public String getUniversityAppointmentTypeCode() {
            return universityAppointmentTypeCode;
        }

        /**
         * @param universityAppointmentTypeCode The universityAppointmentTypeCode to set.
         */
        public void setUniversityAppointmentTypeCode(String universityAppointmentTypeCode) {
            this.universityAppointmentTypeCode = universityAppointmentTypeCode;
        }

        /**
         * @return Returns the universityCostShareFringeBenefitTotalAmount.
         */
        public KualiInteger getUniversityCostShareFringeBenefitTotalAmount() {
            return universityCostShareFringeBenefitTotalAmount;
        }

        /**
         * @param universityCostShareFringeBenefitTotalAmount The universityCostShareFringeBenefitTotalAmount to set.
         */
        public void setUniversityCostShareFringeBenefitTotalAmount(KualiInteger universityCostShareFringeBenefitTotalAmount) {
            this.universityCostShareFringeBenefitTotalAmount = universityCostShareFringeBenefitTotalAmount;
        }

        /**
         * @return Returns the universityCostShareFringeRateAmount.
         */
        public KualiDecimal getUniversityCostShareFringeRateAmount() {
            return universityCostShareFringeRateAmount;
        }

        /**
         * @param universityCostShareFringeRateAmount The universityCostShareFringeRateAmount to set.
         */
        public void setUniversityCostShareFringeRateAmount(KualiDecimal universityCostShareFringeRateAmount) {
            this.universityCostShareFringeRateAmount = universityCostShareFringeRateAmount;
        }

        /**
         * @return Returns the universityCostSharePercentEffortAmount.
         */
        public KualiInteger getUniversityCostSharePercentEffortAmount() {
            return universityCostSharePercentEffortAmount;
        }

        /**
         * @param universityCostSharePercentEffortAmount The universityCostSharePercentEffortAmount to set.
         */
        public void setUniversityCostSharePercentEffortAmount(KualiInteger universityCostSharePercentEffortAmount) {
            this.universityCostSharePercentEffortAmount = universityCostSharePercentEffortAmount;
        }

        /**
         * @return Returns the universityCostShareRequestTotalAmount.
         */
        public KualiInteger getUniversityCostShareRequestTotalAmount() {
            return universityCostShareRequestTotalAmount;
        }

        /**
         * @param universityCostShareRequestTotalAmount The universityCostShareRequestTotalAmount to set.
         */
        public void setUniversityCostShareRequestTotalAmount(KualiInteger universityCostShareRequestTotalAmount) {
            this.universityCostShareRequestTotalAmount = universityCostShareRequestTotalAmount;
        }

        /**
         * @return Returns the userAgencyHours.
         */
        public KualiInteger getUserAgencyHours() {
            return userAgencyHours;
        }

        /**
         * @param userAgencyHours The userAgencyHours to set.
         */
        public void setUserAgencyHours(KualiInteger userAgencyHours) {
            this.userAgencyHours = userAgencyHours;
        }

        /**
         * @return Returns the userUniversityHours.
         */
        public KualiInteger getUserUniversityHours() {
            return userUniversityHours;
        }

        /**
         * @param userUniversityHours The userUniversityHours to set.
         */
        public void setUserUniversityHours(KualiInteger userUniversityHours) {
            this.userUniversityHours = userUniversityHours;
        }

        /**
         * @return Returns the hourlyAppointmentType.
         */
        public boolean isHourlyAppointmentType() {
            return hourlyAppointmentType;
        }

        /**
         * @param hourlyAppointmentType The hourlyAppointmentType to set.
         */
        public void setHourlyAppointmentType(boolean hourlyAppointmentType) {
            this.hourlyAppointmentType = hourlyAppointmentType;
        }

        /**
         * Gets the personProjectDirectorIndicator attribute.
         * 
         * @return Returns the personProjectDirectorIndicator.
         */
        public boolean isPersonProjectDirectorIndicator() {
            return personProjectDirectorIndicator;
        }

        /**
         * Sets the personProjectDirectorIndicator attribute value.
         * 
         * @param personProjectDirectorIndicator The personProjectDirectorIndicator to set.
         */
        public void setPersonProjectDirectorIndicator(boolean personProjectDirectorIndicator) {
            this.personProjectDirectorIndicator = personProjectDirectorIndicator;
        }

        /**
         * Gets the userBudgetPeriodSalaryAmount attribute.
         * 
         * @return Returns the userBudgetPeriodSalaryAmount.
         */
        public String getUserBudgetPeriodSalaryAmount() {
            return userBudgetPeriodSalaryAmount;
        }

        /**
         * Sets the userBudgetPeriodSalaryAmount attribute value.
         * 
         * @param userBudgetPeriodSalaryAmount The userBudgetPeriodSalaryAmount to set.
         */
        public void setUserBudgetPeriodSalaryAmount(String userBudgetPeriodSalaryAmount) {
            this.userBudgetPeriodSalaryAmount = userBudgetPeriodSalaryAmount;
        }

        /**
         * Gets the personWeeksAmount attribute.
         * 
         * @return Returns the personWeeksAmount.
         */
        public Integer getPersonWeeksAmount() {
            return personWeeksAmount;
        }

        /**
         * Sets the personWeeksAmount attribute value.
         * 
         * @param personWeeksAmount The personWeeksAmount to set.
         */
        public void setPersonWeeksAmount(Integer personWeeksAmount) {
            this.personWeeksAmount = personWeeksAmount;
        }
    }
}