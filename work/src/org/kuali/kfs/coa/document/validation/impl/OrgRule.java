/*
 * Copyright 2006 The Kuali Foundation.
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
package org.kuali.module.chart.rules;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.kuali.Constants;
import org.kuali.KeyConstants;
import org.kuali.core.bo.user.KualiGroup;
import org.kuali.core.bo.user.KualiUser;
import org.kuali.core.document.MaintenanceDocument;
import org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.ObjectUtils;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.Org;
import org.kuali.module.chart.service.OrganizationService;

public class OrgRule extends MaintenanceDocumentRuleBase {

    protected static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(OrgRule.class);

    protected static final String APC_HRMS_ACTIVE_KEY = "Org.HrmsOrgActive";
    protected static final String PLANT_WORKGROUP_PARM_NAME = "Org.PlantWorkgroup";

    private OrganizationService orgService;

    private Org oldOrg;
    private Org newOrg;
    private boolean isChartManager;
    private boolean isHrmsOrgActivated;

    public OrgRule() {
        super();
        isChartManager = false;

        // Pseudo-inject some services.
        //
        // This approach is being used to make it simpler to convert the Rule classes
        // to spring-managed with these services injected by Spring at some later date.
        // When this happens, just remove these calls to the setters with
        // SpringServiceLocator, and configure the bean defs for spring.
        this.setOrgService(SpringServiceLocator.getOrganizationService());
    }

    /**
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {

        boolean success = true;

        LOG.info("Entering processCustomApproveDocumentBusinessRules()");

        // determine whether this person is the Chart manager for this org
        isChartManager = isChartManager(GlobalVariables.getUserSession().getKualiUser());

        // determine whether HRMS ORG is activated in this app instance
        isHrmsOrgActivated = isHrmsOrgActivated();

        // check that all sub-objects whose keys are specified have matching objects in the db
        success &= checkExistenceAndActive();

        success &= checkOrgClosureRules(document);

        // check that end date is greater than begin date and Reports To Chart/Org should not be same as this Chart/Org
        success &= checkSimpleRules(document);

        // check that defaultAccount is present unless
        // ( (orgType = U or C) and ( document is a "create new" ))
        success &= checkDefaultAccountNumber(document);
        return success;
    }

    /**
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {

        boolean success = true;

        LOG.info("Entering processCustomRouteDocumentBusinessRules()");

        // determine whether this person is the Chart manager for this org
        isChartManager = isChartManager(GlobalVariables.getUserSession().getKualiUser());

        // determine whether HRMS ORG is activated in this app instance
        isHrmsOrgActivated = isHrmsOrgActivated();

        // check that all sub-objects whose keys are specified have matching objects in the db
        success &= checkExistenceAndActive();

        // check that end date is greater than begin date and Reports To Chart/Org should not be same as this Chart/Org
        success &= checkSimpleRules(document);

        // check that defaultAccount is present unless
        // ( (orgType = U or C) and ( document is a "create new" ))
        success &= checkDefaultAccountNumber(document);

        success &= checkOrgClosureRules(document);

        return success;
    }

    /**
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomSaveDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    protected boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) {

        LOG.info("Entering processCustomSaveDocumentBusinessRules()");

        // determine whether this person is the Chart manager for this org
        isChartManager = isChartManager(GlobalVariables.getUserSession().getKualiUser());

        // determine whether HRMS ORG is activated in this app instance
        isHrmsOrgActivated = isHrmsOrgActivated();

        // check that all sub-objects whose keys are specified have matching objects in the db
        checkExistenceAndActive();

        checkOrgClosureRules(document);

        // check that end date is greater than begin date and Reports To Chart/Org should not be same as this Chart/Org
        checkSimpleRules(document);

        // check that defaultAccount is present unless
        // ( (orgType = U or C) and ( document is a "create new" ))
        checkDefaultAccountNumber(document);

        return true;
    }

    protected boolean checkExistenceAndActive() {

        LOG.info("Entering checkExistenceAndActive()");
        boolean success = true;

        // shortcut out with no enforcement if this org is closed
        if (!newOrg.isOrganizationActiveIndicator()) {
            return success;
        }

        success &= checkChartManagerRequiredActiveExistence();

        return success;
    }

    protected boolean checkChartManagerRequiredActiveExistence() {

        boolean success = true;

        // shortcut out with no enforcement if this org is closed
        if (!newOrg.isOrganizationActiveIndicator()) {
            return success;
        }

        /* KULCOA-1132 - exit if the user is not a member of the
           plant maintainer work group.  */
        
        //get user
        KualiUser user = GlobalVariables.getUserSession().getKualiUser();
        
        //if not authroized to edit plant fields, exit with true
        if( isPlantAuthorized(user) == false ){
            return true;
        }

        // require Org Plant ChartCode
        success &= checkEmptyBOField("organizationPlantChartCode", newOrg.getOrganizationPlantChartCode(), "Organization Plant Chart of Accounts Code");

        // require Org Plant AccountNumber
        success &= checkEmptyBOField("organizationPlantAccountNumber", newOrg.getOrganizationPlantAccountNumber(), "Organization Plant Account Number");

        // require Campus Plant ChartCode
        success &= checkEmptyBOField("campusPlantChartCode", newOrg.getCampusPlantChartCode(), "Campus Plant Chart of Accounts Code");

        // require Org Plant ChartCode
        success &= checkEmptyBOField("campusPlantAccountNumber", newOrg.getCampusPlantAccountNumber(), "Campus Plant Account Number");

        // validate Org Plant Account
        success &= getDictionaryValidationService().validateReferenceExistsAndIsActive(newOrg, "organizationPlantAccount", "accountClosedIndicator", true, true, MAINTAINABLE_ERROR_PREFIX + "organizationPlantAccountNumber", "Organization Plant Account");

        // validate Campus Plant Account
        success &= getDictionaryValidationService().validateReferenceExistsAndIsActive(newOrg, "campusPlantAccount", "accountClosedIndicator", true, true, MAINTAINABLE_ERROR_PREFIX + "campusPlantAccountNumber", "Campus Plant Account");

        return success;
    }

    /**
     * 
     * This method enforces the business rules surrounding when an Org becomes closed/inactive.
     * 
     * @param document
     * @return
     * 
     */
    protected boolean checkOrgClosureRules(MaintenanceDocument document) {

        boolean success = true;
        boolean orgBeingClosed = false;

        // if its an edit, and its being closed
        if (document.isEdit()) {
            if (oldOrg.isOrganizationActiveIndicator() && !newOrg.isOrganizationActiveIndicator()) {
                orgBeingClosed = true;
            }
        }

        // if its new, and is being created as closed
        if (document.isNew()) {
            if (!newOrg.isOrganizationActiveIndicator()) {
                orgBeingClosed = true;
            }
        }

        // if the org isnt being closed, stop processing here
        if (!orgBeingClosed) {
            return success;
        }

        // FROM HERE ON WE'RE ASSUMING THE ORG IS BEING CLOSED

        // do not allow the org to be closed while there are active accounts tied
        // to this org
        List childAccounts = orgService.getActiveAccountsByOrg(newOrg.getChartOfAccountsCode(), newOrg.getOrganizationCode());
        if (childAccounts.size() > 0) {

            // get the first three accounts on the list for display
            StringBuffer childAccountList = new StringBuffer();
            int count = 0;
            String delim = "";
            for (Iterator iter = childAccounts.iterator(); iter.hasNext();) {
                Account account = (Account) iter.next();
                childAccountList.append(delim + account.getChartOfAccountsCode() + "-" + account.getAccountNumber());
                count++;
                if (count >= 1) {
                    delim = ", ";
                }
                if (count >= 3) {
                    break;
                }
            }
            if (childAccounts.size() > count) {
                childAccountList.append(", ... (" + (childAccounts.size() - count) + " more)");
            }

            putGlobalError(KeyConstants.ERROR_DOCUMENT_ORGMAINT_OPEN_CHILD_ACCOUNTS_ON_ORG_CLOSURE, childAccountList.toString());
            success &= false;
        }

        // do not allow this org to be closed while there are still active orgs
        // that have this org as their reportsToOrg
        List childOrgs = orgService.getActiveChildOrgs(newOrg.getChartOfAccountsCode(), newOrg.getOrganizationCode());
        if (childOrgs.size() > 0) {

            // get the first three orgs on the list for display
            StringBuffer childOrgsList = new StringBuffer();
            int count = 0;
            String delim = "";
            for (Iterator iter = childOrgs.iterator(); iter.hasNext();) {
                Org org = (Org) iter.next();
                childOrgsList.append(delim + org.getChartOfAccountsCode() + "-" + org.getOrganizationCode());
                count++;
                if (count >= 1) {
                    delim = ", ";
                }
                if (count >= 3) {
                    break;
                }
            }
            if (childOrgs.size() > count) {
                childOrgsList.append(", ... (" + (childOrgs.size() - count) + " more)");
            }

            putGlobalError(KeyConstants.ERROR_DOCUMENT_ORGMAINT_OPEN_CHILD_ORGS_ON_ORG_CLOSURE, childOrgsList.toString());
            success &= false;
        }


        // if org is being closed, end-date must be valid and present
        if (ObjectUtils.isNull(newOrg.getOrganizationEndDate())) {
            success &= false;
            putFieldError("organizationEndDate", KeyConstants.ERROR_DOCUMENT_ORGMAINT_END_DATE_REQUIRED_ON_ORG_CLOSURE);
        }
        return success;

    }

    /**
     * 
     * This method implements the HRMS Org rules.
     * 
     * @param document
     * @return
     */
    protected boolean checkHrmsOrgRules(MaintenanceDocument document) {

        boolean success = true;

        // shortcut out with no enforcement if this org is closed
        if (!newOrg.isOrganizationActiveIndicator()) {
            return success;
        }

        // short circuit and fail if HRMSOrg is turned off
        if (!isHrmsOrgActivated) {
            return success;
        }

        // if the system has a HRMS Org record attached to this org record, then prompt the
        // user to fill out the HRMS Org info

        // HRMS Org Campus == Org Campus
        // HRMS Org campus code must be the same as Org campus code


        // if the
        return success;
    }

    protected boolean checkSimpleRules(MaintenanceDocument document) {

        boolean success = true;
        String lastReportsToChartOfAccountsCode;
        String lastReportsToOrganizationCode;
        boolean continueSearch;
        Org tempOrg;
        Integer loopCount;
        Integer maxLoopCount = 40;

        // begin date must be greater than or equal to end date
        if ((ObjectUtils.isNotNull(newOrg.getOrganizationBeginDate()) && (ObjectUtils.isNotNull(newOrg.getOrganizationEndDate())))) {

            Date beginDate = newOrg.getOrganizationBeginDate();
            Date endDate = newOrg.getOrganizationEndDate();

            if (endDate.before(beginDate)) {
                putFieldError("organizationEndDate", KeyConstants.ERROR_DOCUMENT_ORGMAINT_END_DATE_GREATER_THAN_BEGIN_DATE);
                success &= false;
            }
        }

        // start date must be greater than or equal to today if new Document
        if ((ObjectUtils.isNotNull(newOrg.getOrganizationBeginDate()) && (document.isNew()))) {
            Timestamp today = getDateTimeService().getCurrentTimestamp();
            today.setTime(DateUtils.truncate(today, Calendar.DAY_OF_MONTH).getTime());
            if (newOrg.getOrganizationBeginDate().before(today)) {
                putFieldError("organizationBeginDate", KeyConstants.ERROR_DOCUMENT_ORGMAINT_STARTDATE_IN_PAST);
                success &= false;
            }
        }


        // Reports To Chart/Org should not be same as this Chart/Org
        // However, allow special case where organizationCode = "UNIV"
        if ((ObjectUtils.isNotNull(newOrg.getReportsToChartOfAccountsCode())) && (ObjectUtils.isNotNull(newOrg.getReportsToOrganizationCode())) && (ObjectUtils.isNotNull(newOrg.getChartOfAccountsCode())) && (ObjectUtils.isNotNull(newOrg.getOrganizationCode())) && (!(newOrg.getOrganizationCode().equals("UNIV")))) {

            if ((newOrg.getReportsToChartOfAccountsCode().equals(newOrg.getChartOfAccountsCode())) && (newOrg.getReportsToOrganizationCode().equals(newOrg.getOrganizationCode())))

            {
                putFieldError("reportsToOrganizationCode", KeyConstants.ERROR_DOCUMENT_ORGMAINT_REPORTING_ORG_CANNOT_BE_SAME_ORG);
                success &= false;
            }
            else

            // Dont't allow a circular reference on Reports to Chart/Org
            // However, do allow special case where organizationCode = "UNIV"
            {
                lastReportsToChartOfAccountsCode = newOrg.getReportsToChartOfAccountsCode();
                lastReportsToOrganizationCode = newOrg.getReportsToOrganizationCode();
                continueSearch = true;
                loopCount = 0;
                do {
                    tempOrg = orgService.getByPrimaryId(lastReportsToChartOfAccountsCode, lastReportsToOrganizationCode);
                    loopCount = loopCount + 1;
                    if (ObjectUtils.isNull(tempOrg)) {
                        continueSearch = false;
                    }
                    else {
                        // LOG.info("Found Org = " + lastReportsToChartOfAccountsCode + "/" + lastReportsToOrganizationCode);
                        lastReportsToChartOfAccountsCode = tempOrg.getReportsToChartOfAccountsCode();
                        lastReportsToOrganizationCode = tempOrg.getReportsToOrganizationCode();

                        if ((tempOrg.getReportsToChartOfAccountsCode().equals(newOrg.getChartOfAccountsCode())) && (tempOrg.getReportsToOrganizationCode().equals(newOrg.getOrganizationCode())) && (!tempOrg.getReportsToOrganizationCode().equals("UNIV"))) {
                            putFieldError("reportsToOrganizationCode", KeyConstants.ERROR_DOCUMENT_ORGMAINT_REPORTING_ORG_CANNOT_BE_CIRCULAR_REF_TO_SAME_ORG);
                            success &= false;
                            continueSearch = false;
                        }
                    }
                    if (loopCount > maxLoopCount) {
                        continueSearch = false;
                    }
                    if (lastReportsToOrganizationCode.equals("UNIV")) {
                        continueSearch = false;
                    }

                } while (continueSearch == true);
            } // end else
        }


        return success;
    }

    // check that defaultAccount is present unless
    // ( (orgType = U or C) and ( document is a "create new" ))

    protected boolean checkDefaultAccountNumber(MaintenanceDocument document) {

        boolean success = true;
        boolean missingDefaultAccountNumber;
        boolean exemptOrganizationTypeCode = false;
        String organizationTypeCode;

        // begin date must be greater than or equal to end date

        missingDefaultAccountNumber = StringUtils.isBlank(newOrg.getOrganizationDefaultAccountNumber());

        if (ObjectUtils.isNotNull(newOrg.getOrganizationTypeCode())) {
            organizationTypeCode = newOrg.getOrganizationTypeCode();
            if (applyApcRule(Constants.ChartApcParms.GROUP_CHART_MAINT_EDOCS, Constants.ChartApcParms.DEFAULT_ACCOUNT_NOT_REQUIRED_ORG_TYPES, newOrg.getOrganizationTypeCode())) {
                exemptOrganizationTypeCode = true;
            }
        }
        if (missingDefaultAccountNumber && (!exemptOrganizationTypeCode | !document.isNew())) {
            putFieldError("organizationDefaultAccountNumber", KeyConstants.ERROR_DOCUMENT_ORGMAINT_DEFAULT_ACCOUNT_NUMBER_REQUIRED);
            success &= false;
        }
        return success;
    }

    /**
     * 
     * This method compares an old and new value, and determines if they've changed.
     * 
     * If the old was null/blank, and the new is not, return true. If the old had a value, and the new is null/blank, return true.
     * If both old and new had a value, and the values are different (excluding trailing or leading whitespaces, and excluding case
     * changes), return true. If none of the above, return false.
     * 
     * @param oldValue - Old value to test.
     * @param newValue - New value to test.
     * @return - true or false, based on the algorithm described above.
     * 
     */
    protected boolean fieldsHaveChanged(String oldValue, String newValue) {

        // if old was null/blank and new is not
        if (StringUtils.isBlank(oldValue) && StringUtils.isNotBlank(newValue)) {
            return true;
        }

        // if old had a value, but new is null/blank
        if (StringUtils.isNotBlank(oldValue) && StringUtils.isBlank(newValue)) {
            return true;
        }

        // at this point, we know that we had a value before, and we have a
        // value now, so we need to test whether this value has changed
        if (oldValue != null && newValue != null) {
            if (!oldValue.trim().equalsIgnoreCase(newValue.trim())) {
                return true;
            }
        }

        // if we've made it to here, then no changes have happened to the values
        return false;
    }

    /**
     * 
     * This method determines whether the given use is a ChartManager for the chart this Org belongs to.
     * 
     * @param user - user to test
     * @return - true if the user is the Chart Manager, false otherwise
     * 
     */
    protected boolean isChartManager(KualiUser user) {

        // see if this person is manager for the requested chart
        boolean success = user.isManagerForChart(newOrg.getChartOfAccountsCode());

        if (success) {
            LOG.info("User: [" + user.getUniversalUser().getPersonUserIdentifier() + "] " + user.getUniversalUser().getPersonName() + " is a Chart Manager for this Org's Chart: " + newOrg.getChartOfAccountsCode());
        }
        else {
            LOG.info("User: [" + user.getUniversalUser().getPersonUserIdentifier() + "] " + user.getUniversalUser().getPersonName() + " is NOT a Chart Manager for this Org's Chart: " + newOrg.getChartOfAccountsCode());
        }

        return success;
    }

    /**
     * 
     * This method looks up in the APC system whether ther HRMS Org system is turned on.
     * 
     * @return - true or false depending on the app configuration
     * 
     */
    protected boolean isHrmsOrgActivated() {

        String flag = getConfigService().getApplicationParameterValue(Constants.ChartApcParms.GROUP_CHART_MAINT_EDOCS, APC_HRMS_ACTIVE_KEY);
        if (flag.trim().equalsIgnoreCase("Y")) {
            return true;
        }
        return false;
    }

    /**
     * 
     * This method sets the convenience objects like newAccount and oldAccount, so you have short and easy handles to the new and
     * old objects contained in the maintenance document.
     * 
     * It also calls the BusinessObjectBase.refresh(), which will attempt to load all sub-objects from the DB by their primary keys,
     * if available.
     * 
     * @param document - the maintenanceDocument being evaluated
     * 
     */
    public void setupConvenienceObjects() {

        // setup oldAccount convenience objects, make sure all possible sub-objects are populated
        oldOrg = (Org) super.getOldBo();

        // setup newAccount convenience objects, make sure all possible sub-objects are populated
        newOrg = (Org) super.getNewBo();
    }

    /**
     * Sets the orgService attribute value.
     * 
     * @param orgService The orgService to set.
     */
    public void setOrgService(OrganizationService orgService) {
        this.orgService = orgService;
    }

    /**
     * 
     * This method tests whether the specified user is part of the group that grants authorization to the Plant fields.
     * 
     * @param user - the user to test
     * @return - true if user is part of the group, false otherwise
     * 
     */
    protected boolean isPlantAuthorized(KualiUser user) {

        // attempt to get the group name that grants access to the Plant fields
        String allowedPlantWorkgroup = getConfigService().getApplicationParameterValue(Constants.ChartApcParms.GROUP_CHART_MAINT_EDOCS, PLANT_WORKGROUP_PARM_NAME);

        if (user.isMember(new KualiGroup(allowedPlantWorkgroup))) {
            LOG.info("User '" + user.getUniversalUser().getPersonUserIdentifier() + "' is a member of the group '" + allowedPlantWorkgroup + "', which gives them access to the Plant fields.");
            return true;
		}
        else {
            LOG.info("User '" + user.getUniversalUser().getPersonUserIdentifier() + "' is not a member of the group '" + allowedPlantWorkgroup + "', so they have no access to the Plant fields.");
            return false;
        }
    }
}