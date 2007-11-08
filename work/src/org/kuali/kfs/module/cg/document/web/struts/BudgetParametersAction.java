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
package org.kuali.module.kra.budget.web.struts.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.util.SpringServiceLocator;
import org.kuali.module.kra.KraConstants;
import org.kuali.module.kra.budget.bo.AgencyExtension;
import org.kuali.module.kra.budget.bo.AppointmentType;
import org.kuali.module.kra.budget.bo.Budget;
import org.kuali.module.kra.budget.bo.BudgetFringeRate;
import org.kuali.module.kra.budget.bo.BudgetGraduateAssistantRate;
import org.kuali.module.kra.budget.bo.BudgetModular;
import org.kuali.module.kra.budget.bo.BudgetPeriod;
import org.kuali.module.kra.budget.bo.BudgetTask;
import org.kuali.module.kra.budget.bo.GraduateAssistantRate;
import org.kuali.module.kra.budget.rules.event.InsertPeriodLineEventBase;
import org.kuali.module.kra.budget.service.BudgetFringeRateService;
import org.kuali.module.kra.budget.service.BudgetGraduateAssistantRateService;
import org.kuali.module.kra.budget.web.struts.form.BudgetForm;


/**
 * This class handles Actions for Research Administration.
 * 
 * 
 */

public class BudgetParametersAction extends BudgetAction {

    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(BudgetParametersAction.class);

    /**
     * This method overrides the BudgetAction execute method. It does so for the purpose of recalculating Personnel expenses any
     * time the Personnel page is accessed
     * 
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward superForward = super.execute(mapping, form, request, response);

        BudgetForm budgetForm = (BudgetForm) form;
        
//      On first load, set the default task name for the initial task.
        if (budgetForm.getBudgetDocument().getTaskListSize() == 0) {
            String DEFAULT_BUDGET_TASK_NAME = SpringServiceLocator.getKualiConfigurationService().getApplicationParameterValue(KraConstants.KRA_DEVELOPMENT_GROUP, "defaultBudgetTaskName");
            budgetForm.getNewTask().setBudgetTaskName(DEFAULT_BUDGET_TASK_NAME + " 1");
            budgetForm.getNewTask().setBudgetTaskOnCampus(true);
        }

        // Set default budget types
        setupBudgetTypes(budgetForm);

        // pre-fetch academic year subdivision names for later use
        setupAcademicYearSubdivisionConstants(budgetForm);

        // Disable/enable appropriate navigation tabs
        budgetForm.checkHeaderNavigation();

        return superForward;
    }

    public ActionForward saveParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;

        // Need to retain modular task number, since it's set on this page
        Integer budgetModularTaskNumber = null;
        if (!ObjectUtils.isNull(budgetForm.getBudgetDocument().getBudget().getModularBudget())) {
            budgetModularTaskNumber = budgetForm.getBudgetDocument().getBudget().getModularBudget().getBudgetModularTaskNumber();
        }

        List referenceObjects = new ArrayList();

        referenceObjects.add("budgetAgency");
        referenceObjects.add("federalPassThroughAgency");
        referenceObjects.add("projectDirector");
        referenceObjects.add("nonpersonnelItems");
        referenceObjects.add("personnel");
        referenceObjects.add("modularBudget");
        referenceObjects.add("indirectCost");
        referenceObjects.add("thirdPartyCostShareItems");
        referenceObjects.add("institutionCostShareItems");
        referenceObjects.add("institutionCostSharePersonnelItems");
        
        SpringServiceLocator.getPersistenceService().retrieveReferenceObjects(budgetForm.getBudgetDocument().getBudget(), referenceObjects);
        
        List docReferenceObjects = new ArrayList();
        docReferenceObjects.add("adhocPersons");
        docReferenceObjects.add("adhocOrgs");
        docReferenceObjects.add("adhocWorkgroups");

        SpringServiceLocator.getPersistenceService().retrieveReferenceObjects(budgetForm.getBudgetDocument(), docReferenceObjects);

        if (budgetForm.getBudgetDocument().getBudget().isAgencyModularIndicator()) {
            if (ObjectUtils.isNull(budgetForm.getBudgetDocument().getBudget().getModularBudget())) {
                // Modular budget with no modular data generated. So generate it.
                SpringServiceLocator.getBudgetModularService().generateModularBudget(budgetForm.getBudgetDocument().getBudget());
            }
            budgetForm.getBudgetDocument().getBudget().getModularBudget().setBudgetModularTaskNumber(budgetModularTaskNumber);
        }

        Object question = request.getParameter(KFSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        KualiConfigurationService kualiConfiguration = SpringServiceLocator.getKualiConfigurationService();

        // Logic for Cost Share question.
        ActionForward preRulesForward = preRulesCheck(mapping, form, request, response, "saveParameters");
        if (preRulesForward != null) {
            return preRulesForward;
        }

        super.save(mapping, form, request, response);

        if (GlobalVariables.getErrorMap().size() == 0) {
            if (budgetForm.isAuditActivated()) {
                return mapping.findForward("auditmode");
            }
            
            // This is so that tab states are not shared between parameters and overview. 
            budgetForm.newTabState(true, true); 
            
            return super.overview(mapping, budgetForm, request, response);
        }

        return mapping.findForward(KFSConstants.MAPPING_BASIC);
    }

    public ActionForward copyFringeRateLines(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get the form
        BudgetForm budgetForm = (BudgetForm) form;

        BudgetFringeRateService bfrService = SpringServiceLocator.getBudgetFringeRateService();
        for (BudgetFringeRate budgetFringeRate : budgetForm.getBudgetDocument().getBudget().getFringeRates()) {
            budgetFringeRate.setContractsAndGrantsFringeRateAmount(budgetFringeRate.getAppointmentTypeFringeRateAmount());
        }

        return mapping.findForward(KFSConstants.MAPPING_BASIC);
    }

    public ActionForward copyInstitutionCostShareLines(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get the form
        BudgetForm budgetForm = (BudgetForm) form;
        
        BudgetFringeRateService bfrService = SpringServiceLocator.getBudgetFringeRateService();
        for (BudgetFringeRate budgetFringeRate : budgetForm.getBudgetDocument().getBudget().getFringeRates()) {
            budgetFringeRate.setInstitutionCostShareFringeRateAmount(budgetFringeRate.getAppointmentTypeCostShareFringeRateAmount());
        }

        return mapping.findForward(KFSConstants.MAPPING_BASIC);
    }

    /*
     * A struts action to copy the first academic year subdivision graduate assistance rate system values
     */
    public ActionForward copyPeriod1GraduateAssistantLines(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return copyPeriodGraduateAssistantLines(mapping, form, request, response, 1);
    }

    /*
     * A struts action to copy the second academic year subdivision graduate assistance rate system values
     */
    public ActionForward copyPeriod2GraduateAssistantLines(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return copyPeriodGraduateAssistantLines(mapping, form, request, response, 2);
    }

    /*
     * A struts action to copy the third academic year subdivision graduate assistance rate system values
     */
    public ActionForward copyPeriod3GraduateAssistantLines(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return copyPeriodGraduateAssistantLines(mapping, form, request, response, 3);
    }

    /*
     * A struts action to copy the forth academic year subdivision graduate assistance rate system values
     */
    public ActionForward copyPeriod4GraduateAssistantLines(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return copyPeriodGraduateAssistantLines(mapping, form, request, response, 4);
    }

    /*
     * A struts action to copy the fifth academic year subdivision graduate assistance rate system values
     */
    public ActionForward copyPeriod5GraduateAssistantLines(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return copyPeriodGraduateAssistantLines(mapping, form, request, response, 5);
    }

    /*
     * A struts action to copy the sixth academic year subdivision graduate assistance rate system values
     */
    public ActionForward copyPeriod6GraduateAssistantLines(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return copyPeriodGraduateAssistantLines(mapping, form, request, response, 6);
    }

    /*
     * A struts action to copy all the academic year subdivision graduate assistance rate system values
     */
    public ActionForward copySystemGraduateAssistantLines(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return copyPeriodGraduateAssistantLines(mapping, form, request, response, 0);
    }

    /*
     * A struts action to copy the academic year subdivision graduate assistance rate system values @param periodToCopy - the
     * academic year subdivision number to copy - 0 means copy all
     */
    public ActionForward copyPeriodGraduateAssistantLines(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, int periodToCopy) throws Exception {
        // get the form
        BudgetForm budgetForm = (BudgetForm) form;
        // get the fringe rate list
        List graduateAssistantRate = budgetForm.getBudgetDocument().getBudget().getGraduateAssistantRates();
        int i = 0;
        BudgetGraduateAssistantRateService bgarService = SpringServiceLocator.getBudgetGraduateAssistantRateService();
        for (Iterator iter = bgarService.getAllGraduateAssistantRates().iterator(); iter.hasNext();) {
            GraduateAssistantRate gar = (GraduateAssistantRate) iter.next();
            BudgetGraduateAssistantRate currentGraduateAssistantRate = budgetForm.getBudgetDocument().getBudget().getGraduateAssistantRate(i);
            KualiDecimal[] periodRates = new KualiDecimal[6];
            for (int j = 0; j < 6; j++) {
                if (periodToCopy == 0 || j + 1 == periodToCopy) {
                    periodRates[j] = gar.getCampusMaximumPeriodRate(j + 1);
                }
                else {
                    periodRates[j] = currentGraduateAssistantRate.getCampusMaximumPeriodRate(j + 1);
                }
            }
            BudgetGraduateAssistantRate budgetGraduateAssistantRate = new BudgetGraduateAssistantRate(budgetForm.getDocument().getDocumentNumber(), gar.getCampusCode(), periodRates[0], periodRates[1], periodRates[2], periodRates[3], periodRates[4], periodRates[5], gar, currentGraduateAssistantRate.getObjectId(), currentGraduateAssistantRate.getVersionNumber());

            graduateAssistantRate.set(i, budgetGraduateAssistantRate);
            i++;
        }
        return mapping.findForward(KFSConstants.MAPPING_BASIC);
    }


    public ActionForward insertPeriodLine(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;

        // check any business rules
        boolean rulePassed = SpringServiceLocator.getKualiRuleService().applyRules(new InsertPeriodLineEventBase(budgetForm.getDocument(), budgetForm.getNewPeriod()));

        if (rulePassed) {
            budgetForm.getBudgetDocument().addPeriod(budgetForm.getNewPeriod());
            budgetForm.setNewPeriod(new BudgetPeriod());
        }
        return mapping.findForward(KFSConstants.MAPPING_BASIC);
    }

    public ActionForward deletePeriodLine(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ((BudgetForm) form).getBudgetDocument().setPeriodToDelete(Integer.toString(getLineToDelete(request)));
        ActionForward preRulesForward = preRulesCheck(mapping, form, request, response);
        if (preRulesForward != null) {
            return preRulesForward;
        }
        
        return mapping.findForward(KFSConstants.MAPPING_BASIC);
    }

    public ActionForward insertTaskLine(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BudgetForm budgetForm = (BudgetForm) form;
        budgetForm.getBudgetDocument().addTask(budgetForm.getNewTask());
        budgetForm.setNewTask(new BudgetTask());
        budgetForm.getNewTask().setBudgetTaskOnCampus(true);
        return mapping.findForward(KFSConstants.MAPPING_BASIC);
    }

    public ActionForward deleteTaskLine(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ((BudgetForm) form).getBudgetDocument().setTaskToDelete(Integer.toString(getLineToDelete(request)));
        ActionForward preRulesForward = preRulesCheck(mapping, form, request, response);
        if (preRulesForward != null) {
            return preRulesForward;
        }
        
        return mapping.findForward(KFSConstants.MAPPING_BASIC);
    }

    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.refresh(mapping, form, request, response);
        BudgetForm budgetForm = (BudgetForm) form;
        Budget budget = budgetForm.getBudgetDocument().getBudget();
        if (request.getParameter(KFSConstants.REFRESH_CALLER) != null) {
            String refreshCaller = request.getParameter(KFSConstants.REFRESH_CALLER);
            // check to see if we are coming back from a lookup
            if (refreshCaller.equals(KFSConstants.KUALI_LOOKUPABLE_IMPL)) {
                if ("true".equals(request.getParameter("document.budget.agencyToBeNamedIndicator"))) {
                    // coming back from Agency lookup - To Be Named selected
                    budget.setBudgetAgency(null);
                    budget.setBudgetAgencyNumber(null);
                    BudgetModular modularBudget = budget.getModularBudget() != null ? budget.getModularBudget() : new BudgetModular(budget.getDocumentNumber());
                    resetModularBudget(budget, modularBudget);
                    budget.setModularBudget(modularBudget);
                }
                else if (request.getParameter("document.budget.budgetAgencyNumber") != null) {
                    // coming back from an Agnecy lookup - Agency selected
                    budget.setAgencyToBeNamedIndicator(false);
                    BudgetModular modularBudget = budget.getModularBudget() != null ? budget.getModularBudget() : new BudgetModular(budget.getDocumentNumber());
                    budget.refreshReferenceObject("budgetAgency");
                    budget.getBudgetAgency().refresh();
                    if (budget.getBudgetAgency().getAgencyExtension() != null) {
                        AgencyExtension agencyExtension = budget.getBudgetAgency().getAgencyExtension();
                        modularBudget.setBudgetModularIncrementAmount(agencyExtension.getBudgetModularIncrementAmount());
                        modularBudget.setBudgetPeriodMaximumAmount(agencyExtension.getBudgetPeriodMaximumAmount());
                    }
                    else {
                        resetModularBudget(budget, modularBudget);
                    }
                    budget.setModularBudget(modularBudget);
                }
                else if (request.getParameter("document.budget.budgetProjectDirectorUniversalIdentifier") != null) {
                    // Coming back from project director lookup - project director selected
                    budgetForm.getBudgetDocument().getBudget().setProjectDirectorToBeNamedIndicator(false);
                    budgetForm.getBudgetDocument().getBudget().refreshReferenceObject("projectDirector");
                }
                else if ("true".equals(request.getParameter("document.budget.projectDirectorToBeNamedIndicator"))) {
                    // Coming back from project director lookup - Name Later selected
                    budgetForm.getBudgetDocument().getBudget().setProjectDirector(null);
                    budgetForm.getBudgetDocument().getBudget().setBudgetProjectDirectorUniversalIdentifier(null);
                }
            }
        }
        return mapping.findForward(KFSConstants.MAPPING_BASIC);
    }

    private static void resetModularBudget(Budget budget, BudgetModular modularBudget) {
        modularBudget.setBudgetModularTaskNumber(null);
        budget.setAgencyModularIndicator(false);
    }

    public ActionForward basic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward(KFSConstants.MAPPING_BASIC);
    }
}