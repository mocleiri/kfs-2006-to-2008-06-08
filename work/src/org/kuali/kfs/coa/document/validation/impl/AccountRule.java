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
package org.kuali.module.chart.rules;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.kuali.KeyConstants;
import org.kuali.core.bo.PostalZipCode;
import org.kuali.core.bo.user.KualiUser;
import org.kuali.core.document.MaintenanceDocument;
import org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.ObjectUtils;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.AccountGuideline;
import org.kuali.module.chart.bo.SubFundGroup;
import org.kuali.module.financial.rules.KualiParameterRule;

/**
 * Business rule(s) applicable to AccountMaintenance documents.
 * 
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class AccountRule extends MaintenanceDocumentRuleBase {
    protected static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(AccountRule.class);
    
    private KualiParameterRule validBudgetRule;
    private boolean ruleValuesSetup;
    
    public static String CHART_MAINTENANCE_EDOC = "ChartMaintenanceEDoc";
    public static String ACCT_BUDGET_CODES_RESTRICT = "Account.BudgetCodesRestriction";
    public static String ACCT_PREFIX_RESTRICTION = "Account.PrefixRestriction";
    
    Account oldAccount;
    Account newAccount;
    AccountGuideline oldAccountGuideline;
    AccountGuideline newAccountGuideline;
    
    private static String CONTRACTS_GRANTS_CD = "CG";
    private static String GENERAL_FUND_CD = "GF";
    private static String RESTRICTED_FUND_CD = "RF";
    private static String ENDOWMENT_FUND_CD = "EN";
    private static String PLANT_FUND_CD = "PF";

    private KualiConfigurationService configService;
    
    public AccountRule() {
        ruleValuesSetup = false;
        configService=SpringServiceLocator.getKualiConfigurationService();
    }
    
    /**
     * This method initializes our rule values for Account Maintenance
     * @param document
     */
    private void initializeRuleValues(MaintenanceDocument document) {
        if(!ruleValuesSetup) {
            validBudgetRule = configService.getApplicationParameterRule(CHART_MAINTENANCE_EDOC, ACCT_BUDGET_CODES_RESTRICT);
        }
    }
    
    /**
     * 
     * This method sets the convenience objects like newAccount and oldAccount, so you
     * have short and easy handles to the new and old objects contained in the 
     * maintenance document.
     * 
     * @param document - the maintenanceDocument being evaluated
     * 
     */
    private void setupConvenienceObjects(MaintenanceDocument document) {
        
        //	setup oldAccount convenience objects
        oldAccount = (Account) document.getOldMaintainableObject().getBusinessObject();
        SpringServiceLocator.getPersistenceService().retrieveNonKeyFields(oldAccount);
        oldAccountGuideline = oldAccount.getAccountGuideline();

        //	setup newAccount convenience objects
        newAccount = (Account) document.getNewMaintainableObject().getBusinessObject();
        SpringServiceLocator.getPersistenceService().retrieveNonKeyFields(newAccount);
        newAccountGuideline = newAccount.getAccountGuideline();
    }
    
    protected boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) {
        
        setupConvenienceObjects(document);
        initializeRuleValues(document);
        
        //	default to success
        boolean success = true;
        success &= checkEmptyValues(document);
        return success;
    }
    
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        LOG.info("processCustomRouteDocumentBusinessRules called");
        setupConvenienceObjects(document);
        initializeRuleValues(document);
        
        //	default to success
        boolean success = true;
        
        success &= checkEmptyValues(document);
        success &= checkGeneralRules(document);
        success &= checkCloseAccount(document);
        success &= checkContractsAndGrants(document);
        success &= checkExpirationDate(document);
        success &= checkFundGroup(document);
        success &= checkSubFundGroup(document);
        
        return success;
    }

    /**
     * 
     * This method checks the basic rules for empty values in an account and associated
     * objects with this account
     * @param maintenanceDocument
     * @return
     */
    private boolean checkEmptyValues(MaintenanceDocument maintenanceDocument) {
        boolean success = true;
        
        success &= checkEmptyBOField("chartOfAccountsCode", newAccount.getChartOfAccountsCode(), "Chart of Accounts Code");
        success &= checkEmptyBOField("accountNumber", newAccount.getAccountNumber(), "Account Number");
        success &= checkEmptyBOField("accountName", newAccount.getAccountName(), "Account Name");
        success &= checkEmptyBOField("organizationCode", newAccount.getOrganizationCode(), "Organization Code");
        success &= checkEmptyBOField("accountPhysicalCampusCode", newAccount.getAccountPhysicalCampusCode(), "Campus Code");
        success &= checkEmptyBOField("accountEffectiveDate", newAccount.getAccountEffectiveDate(), "Effective Date");
        success &= checkEmptyBOField("accountCityName", newAccount.getAccountCityName(), "City Name");
        success &= checkEmptyBOField("accountStateCode", newAccount.getAccountStateCode(), "State Code");
        success &= checkEmptyBOField("accountStreetAddress", newAccount.getAccountStreetAddress(), "Address");
        success &= checkEmptyBOField("accountZipCode", newAccount.getAccountZipCode(), "Zip Code");
        success &= checkEmptyBOField("budgetRecordingLevelCode", newAccount.getBudgetRecordingLevelCode(), "Budget Recording Level");
        success &= checkEmptyBOField("accountSufficientFundsCode", newAccount.getAccountSufficientFundsCode(), "Sufficient Funds Code");
        success &= checkEmptyBOField("subFundGroupCode", newAccount.getSubFundGroupCode(), "Sub Fund Group");
        success &= checkEmptyBOField("financialHigherEdFunctionCd", newAccount.getFinancialHigherEdFunctionCd(), "Higher Ed Function Code");
        success &= checkEmptyBOField("accountRestrictedStatusCode", newAccount.getAccountRestrictedStatusCode(), "Restricted Status Code");
        //TODO: Please see KULCOA-301 for details, but these need to go elsewhere
        //success &= checkEmptyValue(, newAccount.getAcctIndirectCostRcvyTypeCd(), "ICR Type Code");
        //success &= checkEmptyValue(, newAccount.getFinancialIcrSeriesIdentifier(), "ICR Series Identifier");
        //success &= checkEmptyValue(, newAccount.getIndirectCostRecoveryAcctNbr(), "ICR Cost Recovery Account");
        //success &= checkEmptyValue(, newAccount.getCgCatlfFedDomestcAssistNbr(), "C&G Domestic Assistance Number");

        //	ObjectUtils.isNull() avoids null sub-objects that may or may not be proxied
        if (!ObjectUtils.isNull(newAccount.getAccountFiscalOfficerUser())) {
            success &= checkEmptyBOField("accountFiscalOfficerUser.personUniversalIdentifier", newAccount.getAccountFiscalOfficerUser().getPersonUniversalIdentifier(), "Account Fiscal Officer");
        }
        if (!ObjectUtils.isNull(newAccount.getAccountManagerUser())) {
            success &= checkEmptyBOField("accountManagerUser.personUniversalIdentifier", newAccount.getAccountManagerUser().getPersonUniversalIdentifier(), "Account Manager");
        }
        if (!ObjectUtils.isNull(newAccount.getAccountSupervisoryUser())) {
            success &= checkEmptyBOField("accountSupervisoryUser.personUniversalIdentifier", newAccount.getAccountSupervisoryUser().getPersonUniversalIdentifier(), "Account Supervisor");
        }
        if (!ObjectUtils.isNull(newAccount.getAccountGuideline())) {
            //	if the expiration date is earlier than today, guidelines are not required.
            if (newAccount.getAccountExpirationDate().after(new Timestamp(Calendar.getInstance().getTimeInMillis()))) {
	            success &= checkEmptyBOField("accountGuideline.accountExpenseGuidelineText", newAccount.getAccountGuideline().getAccountExpenseGuidelineText(), "Expense Guideline");
	            success &= checkEmptyBOField("accountGuideline.accountIncomeGuidelineText", newAccount.getAccountGuideline().getAccountIncomeGuidelineText(), "Income Guideline");
	            success &= checkEmptyBOField("accountGuideline.accountPurposeText", newAccount.getAccountGuideline().getAccountPurposeText(), "Account Purpose");
            }
        }
        return success;
    }
    
    /**
     * 
     * This method checks some of the general business rules associated with this document
     * @param maintenanceDocument
     * @return false on rules violation
     */
    private boolean checkGeneralRules(MaintenanceDocument maintenanceDocument) {

        KualiUser fiscalOfficer = newAccount.getAccountFiscalOfficerUser();
        KualiUser accountManager = newAccount.getAccountManagerUser();
        KualiUser accountSupervisor = newAccount.getAccountSupervisoryUser();
        
        boolean success = true;

        GlobalVariables.getErrorMap().addToErrorPath("newMaintainableObject");
        
           
        // Enforce institutionally specified restrictions on account number prefixes
        // (e.g. the account number cannot begin with a 3 or with 00.)
        
        String[] illegalValues=configService.getApplicationParameterValues(CHART_MAINTENANCE_EDOC,ACCT_PREFIX_RESTRICTION);
        
        if (illegalValues!=null) {
            for (int i=0; i<illegalValues.length; i++) {
                if(newAccount.getAccountNumber().startsWith(illegalValues[i])) {
                    success &= false;
                    putFieldError("accountNumber", KeyConstants.ERROR_DOCUMENT_ACCMAINT_ACCT_NMBR_NOT_ALLOWED+"("+illegalValues[i]+")", newAccount.getAccountNumber());
                }
            }
        } else {
            LOG.warn("No Financial System Parameter found for CHART_MAINTENANCE_EDOC/ACCT_PREFIX_RESTRICTION");
        }
        
        //only a FIS supervisor can reopen a closed account. (This is the central super user, not an account supervisor).
        //we need to get the old maintanable doc here
        if (maintenanceDocument.isEdit()) {
            if (oldAccount.isAccountClosedIndicator()) {
                if (!newAccount.isAccountClosedIndicator()) {
                    KualiUser thisUser = GlobalVariables.getUserSession().getKualiUser();
                    if (!thisUser.isSupervisorUser()) {
                        success &= false;
                        putFieldError("accountClosedIndicator", KeyConstants.ERROR_DOCUMENT_ACCMAINT_ONLY_SUPERVISORS_CAN_REOPEN);
                    }
                }
            }
        }
        
        //when a restricted status code of 'T' (temporarily restricted) is selected, a restricted status date must be supplied.
        if (!StringUtils.isEmpty(newAccount.getAccountRestrictedStatusCode())) {
	        if (newAccount.getAccountRestrictedStatusCode().equalsIgnoreCase("T")) {
	            if (newAccount.getAccountRestrictedStatusDate() == null) {
		            success &= false;
		            putFieldError("accountRestrictedStatusDate", KeyConstants.ERROR_DOCUMENT_ACCMAINT_RESTRICTED_STATUS_DT_REQ, newAccount.getAccountNumber());
	            }
	        }
        }
        
        // the fringe benefit account (otherwise known as the reportsToAccount) is required if 
        // the fringe benefit code is set to N. 
        // The fringe benefit code of the account designated to accept the fringes must be Y.
        if (!newAccount.isAccountsFringesBnftIndicator()) {
            if (StringUtils.isEmpty(newAccount.getReportsToAccountNumber()) || 
                	ObjectUtils.isNull(newAccount.getReportsToAccount())) { // proxy-safe null test
                success &= false;
                putFieldError("reportsToAccount.accountNumber", KeyConstants.ERROR_DOCUMENT_ACCMAINT_RPTS_TO_ACCT_REQUIRED_IF_FRINGEBENEFIT_FALSE);
            }
            else {
                Account reportsToAccount = newAccount.getReportsToAccount();
                if (!reportsToAccount.isAccountsFringesBnftIndicator()) {
                    success &= false;
                    putFieldError("reportsToAccount.accountNumber", KeyConstants.ERROR_DOCUMENT_ACCMAINT_RPTS_TO_ACCT_MUST_BE_FLAGGED_FRINGEBENEFIT, newAccount.getReportsToAccountNumber());
                }
            }
        }
        
        //the employee type for fiscal officer, account manager, and account supervisor must be 'P' � professional.
        success &= checkUserType("accountFiscalOfficerUser.personName", fiscalOfficer, "P", "Fiscal Officer");
        success &= checkUserType("accountManagerUser.personName", accountManager, "P", "Account Manager");
        success &= checkUserType("accountSupervisoryUser.personName", accountSupervisor, "P", "Account Supervisor");
        
        //the supervisor cannot be the same as the fiscal officer or account manager.
        if (accountSupervisor.equals(fiscalOfficer)) {
            success &= false;
            putGlobalError(KeyConstants.ERROR_DOCUMENT_ACCMAINT_ACCT_SUPER_CANNOT_BE_FISCAL_OFFICER);
        }
        if (accountSupervisor.equals(accountManager)) {
            success &= false;
            putGlobalError(KeyConstants.ERROR_DOCUMENT_ACCMAINT_ACCT_SUPER_CANNOT_BE_ACCT_MGR);
        }
        
        //valid values for the budget code are account, consolidation, level, object code, mixed, sub-account and no budget.
        if (validBudgetRule.failsRule(newAccount.getBudgetRecordingLevelCode())) {
            success &= false;
            
            putFieldError("budgetRecordingLevelCode", KeyConstants.ERROR_DOCUMENT_ACCMAINT_INVALID_BUDGET_RECORD_LVL_CD, newAccount.getBudgetRecordingLevelCode());
        }
        
        //TODO: If a document is enroute that affects the filled in account number then give the user an error indicating that 
        //      the current account is locked for editing
        //      DEFERRED - must consult workflow folks for best way to accomplish this
        
        //	In this whole stretch of tests which follows where we're testing to make sure that subObject X 
        // is valid (ie, present in the its table) and active, we're cheating a little bit, to 
        // simplify the code.  This assumes that we've run the PersistenceService.retrieveNonKeyFields() 
        // on the object.  After this happens, if the String value for the subObject is valid, the 
        // subObject itself will be populated.  So if its null, then its not present in the DB.
        // This is quite a bundle of assumptions, so be aware that if any of these assumptions become 
        // untrue, then this whole section breaks down.
        
        //org_cd must be a valid org and active in the ca_org_t table
        if (ObjectUtils.isNull(newAccount.getOrganization())) {
            success &= false;
            putFieldError("organization.organizationCode", KeyConstants.ERROR_DOCUMENT_ACCMAINT_INVALID_ORG);
        }
        else if (!newAccount.getOrganization().isOrganizationActiveIndicator()) {
            success &= false;
            putFieldError("organization.organizationCode", KeyConstants.ERROR_DOCUMENT_ACCMAINT_INACTIVE_ORG);
        }
        
        //acct_phys_cmp_cd must be valid campus in the acct_phys_cmp_cd table
        if (ObjectUtils.isNull(newAccount.getAccountPhysicalCampus())) {
            success &= false;
            putFieldError("accountPhysicalCampus.campusCode", KeyConstants.ERROR_DOCUMENT_ACCMAINT_INVALID_CAMPUS_CD);
            putGlobalError("Physical Campus Code entered must be a valid Physical Campus Code that exists in the system.");
        } // campus doesnt have an Active code, so this isnt checked
        
        //acct_state_cd must be valid in the sh_state_t table
        if (ObjectUtils.isNull(newAccount.getAccountState())) {
            success &= false;
            putFieldError("accountStateCode", KeyConstants.ERROR_DOCUMENT_ACCMAINT_INVALID_STATE_CD);
        } // state doesnt have an Active code, so this isnt checked
        
        //acct_zip_cd must be a valid in the sh_zip_code_t table
        if (ObjectUtils.isNull(newAccount.getAccountZipCode())) {
            success &= false;
            putFieldError("accountZipCode", KeyConstants.ERROR_DOCUMENT_ACCMAINT_INVALID_ZIP_CD);
        } // zipcode doesnt have an Active code, so this isnt checked
        
        return success;
    }

    /**
     * 
     * This method checks to see if the user passed in is of the type requested.  
     * 
     * If so, it returns true.  If not, it returns false, and adds an error to 
     * the GlobalErrors.
     * @param user - KualiUser to be tested
     * @param employeeType - String value expected for Employee Type 
     * @param userRoleDescription - User Role being tested, to be passed into an error message
     * 
     * @return - true if user is of the requested employee type, false if not
     * 
     */
    private boolean checkUserType(String propertyName, KualiUser user, String employeeType, String userRoleDescription) {
        
        //	if the user isnt populated, it will fail
        if (user == null) {
            return false;
        }
        
        //	if the KualiUser record is not properly setup with this value, this will fail
        if (StringUtils.isEmpty(user.getEmployeeTypeCode())) {
            return false;
        }

        if (user.getEmployeeTypeCode().equalsIgnoreCase(employeeType)) {
	            return true;
        }
        else {
            putFieldError(propertyName, KeyConstants.ERROR_DOCUMENT_ACCMAINT_PRO_TYPE_REQD_FOR_EMPLOYEE, userRoleDescription);
            return false;
        }
    }
    
    /**
     * 
     * This method checks to see if the user is trying to close the account and if so if any 
     * rules are being violated
     * @param maintenanceDocument
     * @return false on rules violation
     */
    private boolean checkCloseAccount(MaintenanceDocument maintenanceDocument) {

        boolean success = true;
        boolean isBeingClosed;

        //	if the account isnt being closed, then dont bother processing the rest of 
        // the method
        if(!oldAccount.isAccountClosedIndicator() && newAccount.isAccountClosedIndicator()) {
            isBeingClosed = true;
        } else {
            isBeingClosed = false;
        }
        if (!isBeingClosed) {
            return true;
        }
        
        //	this business is to get two Calendar objects, one the current date (with no 
        // time component) and one a Calendar version of AccountExpirationDate (with no 
        // time component).
        Timestamp closeTimestamp = newAccount.getAccountExpirationDate();
        Calendar todaysDate = Calendar.getInstance();
        Calendar closeDate = new GregorianCalendar();
        
        //	convert java.sql.Timestamp to java.util.Calendar, and make sure there is no time-component.
        // there may be better ways to do this, but there appears to be a bit of an impedence mismatch 
        // between java.sql.Timestamp (of which a bunch of stuff is deprecated) and java.util.Calendar.
        // if there's a better way to do this, and still avoid contamination by time-components to the 
        // date comparisons, feel free to fix it up
        closeDate.setTimeInMillis(closeTimestamp.getTime() + (closeTimestamp.getNanos() / 1000000));
        closeDate.set(closeDate.get(Calendar.YEAR), closeDate.get(Calendar.MONTH), closeDate.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        
        //when closing an account, the account expiration date must be the current date or earlier
        if (closeDate.before(todaysDate) || closeDate.equals(todaysDate)) {
            putGlobalError(KeyConstants.ERROR_DOCUMENT_ACCMAINT_ACCT_CANNOT_BE_CLOSED_EXP_DATE_INVALID);
            success &= false;
        }
        
        // when closing an account, a continuation account is required 
        // error message - "When closing an Account a Continuation Account Number entered on the Responsibility screen is required."
        if (StringUtils.isEmpty(newAccount.getContinuationAccountNumber())) {
            putGlobalError(KeyConstants.ERROR_DOCUMENT_ACCMAINT_ACCT_CLOSE_CONTINUATION_ACCT_REQD);
            success &= false;
        }
        
        //TODO: DEFERRED - Need to add a new method to GeneralLedgerPendingEntryService to find GLPE by Account
        //TODO: DEFERRED - must have no base budget, must have no pending ledger entries or pending labor ledger entries, 
        //      must have no open encumbrances, must have no asset, liability or fund balance balances other than object code 9899 
        //      (9899 is fund balance for us), and the process of closing income and expense into 9899 must take the 9899 balance to zero.
        //
        //NOTES:
        //budget first - no idea (maybe through Options? AccountBalance?)
        //definitely looks like we need to pull AccountBalance
        //pending ledger entries or pending labor ledger entries
        //possibly use GeneralLedgerPendingEntryService to find, but what keys are used?
        //no clue on how to check for balances in the other areas (encumbrances, asset, liability, fund balance [other than 9899])
        //accounts can only be closed if they dont have balances or any pending ledger entries

        return true;
    }
    
    /**
     * 
     * This method checks to see if any Contracts and Grants business rules were violated
     * @param maintenanceDocument
     * @return false on rules violation
     */
    private boolean checkContractsAndGrants(MaintenanceDocument maintenanceDocument) {

        boolean success = true;
        
        //	an income stream account is required for accounts in the C&G (CG) and General Fund (GF) fund groups 
        // (except for the MPRACT sub-fund group in the general fund fund group).
        if (!ObjectUtils.isNull(newAccount.getSubFundGroup())) {
            String fundGroupCode = newAccount.getSubFundGroup().getFundGroupCode();
            if (fundGroupCode.equalsIgnoreCase(CONTRACTS_GRANTS_CD) || 
               (fundGroupCode.equalsIgnoreCase(GENERAL_FUND_CD) && !fundGroupCode.equalsIgnoreCase("MPRACT"))) {
                
                if(StringUtils.isEmpty(newAccount.getIncomeStreamAccountNumber())) {
                    putFieldError("incomeStreamAccount.accountNumber", KeyConstants.ERROR_DOCUMENT_ACCMAINT_INCOME_STREAM_ACCT_NBR_CANNOT_BE_NULL);
                    success &= false;
                }
                if(StringUtils.isEmpty(newAccount.getIncomeStreamFinancialCoaCode())) {
                    putFieldError("incomeStreamAccount.chartOfAccounts.chartOfAccountsCode", KeyConstants.ERROR_DOCUMENT_ACCMAINT_INCOME_STREAM_ACCT_COA_CANNOT_BE_NULL);
                    success &= false;
                }
            }
        }
        return success;
    }
    
    /**
     * 
     * This method checks to see if any expiration date field rules were violated
     * @param maintenanceDocument
     * @return false on rules violation
     */
    private boolean checkExpirationDate(MaintenanceDocument maintenanceDocument) {

        boolean success = true;

        Timestamp oldExpDate = oldAccount.getAccountExpirationDate();
        Timestamp newExpDate = newAccount.getAccountExpirationDate();
        Timestamp today = new Timestamp(Calendar.getInstance().getTimeInMillis());
        
        //	When updating an account expiration date, the date must be today or later (except for C&G accounts).
        if (maintenanceDocument.isEdit() && !oldExpDate.equals(newExpDate)) {
            if (!ObjectUtils.isNull(newAccount.getSubFundGroup())) {
                String fundGroupCode = newAccount.getSubFundGroup().getFundGroupCode();
                if (!fundGroupCode.equalsIgnoreCase(CONTRACTS_GRANTS_CD)) {
                    if (!newExpDate.after(today) || newExpDate.equals(today)) {
                        putGlobalError(KeyConstants.ERROR_DOCUMENT_ACCMAINT_EXP_DATE_TODAY_LATER_EXCEPT_CANDG_ACCT);
                        success &= false;
                    }
                }
            }
        }
        
        //	a continuation account is required if the expiration date is completed.
        if (newExpDate != null) {
            if (StringUtils.isEmpty(newAccount.getContinuationAccountNumber()) || 
                StringUtils.isEmpty(newAccount.getContinuationFinChrtOfAcctCd())) {
                
                putGlobalError(KeyConstants.ERROR_DOCUMENT_ACCMAINT_CONTINUATION_ACCT_REQD_IF_EXP_DATE_COMPLETED);
                success &= false;
            }
        }
        
        //	If creating a new account if acct_expiration_dt is set and the fund_group is not "CG" then 
        // the acct_expiration_dt must be changed to a date that is today or later
        if(maintenanceDocument.isNew() && newExpDate != null ) {
            if(!ObjectUtils.isNull(newAccount.getSubFundGroup())) {
                if(!newAccount.getSubFundGroup().getFundGroupCode().equalsIgnoreCase("CG")) {
                    if(!newExpDate.after(today) || newExpDate.equals(today) ) {
                        putGlobalError(KeyConstants.ERROR_DOCUMENT_ACCMAINT_EXP_DATE_TODAY_LATER_EXCEPT_CANDG_ACCT);
                        success &= false;
                    }
                }
            }
        }
        
        //acct_expiration_dt can not be before acct_effect_dt
        Timestamp effectiveDate = newAccount.getAccountEffectiveDate();
        if(newExpDate.before(effectiveDate)) {
            putGlobalError(KeyConstants.ERROR_DOCUMENT_ACCMAINT_EXP_DATE_CANNOT_BE_BEFORE_EFFECTIVE_DATE);
            success &= false;
        }

        return success;
    }
    
    /**
     * 
     * This method checks to see if any Fund Group rules were violated
     * @param maintenanceDocument
     * @return false on rules violation
     * 
     */
    private boolean checkFundGroup(MaintenanceDocument maintenanceDocument) {
        
        boolean success = true;
        SubFundGroup subFundGroup = newAccount.getSubFundGroup();
        String fundGroupCode = subFundGroup.getFundGroupCode();
        String restrictedStatusCode = newAccount.getAccountRestrictedStatusCode();
        
        //	on the account screen, if the fund group of the account is CG (contracts & grants) or 
        // RF (restricted funds), the restricted status code is set to 'R'.
        if (fundGroupCode.equalsIgnoreCase(CONTRACTS_GRANTS_CD) || fundGroupCode.equalsIgnoreCase(RESTRICTED_FUND_CD)) {
            if (!restrictedStatusCode.equalsIgnoreCase("R")) {
                putGlobalError(KeyConstants.ERROR_DOCUMENT_ACCMAINT_ACCT_RESTRICTED_STATUS_CD_MUST_BE_R);
                success &= false;
            }
        }

        //	If the fund group is EN (endowment) or PF (plant fund) the value is not set by the system and 
        // must be set by the user 
        else if (fundGroupCode.equalsIgnoreCase(ENDOWMENT_FUND_CD) || fundGroupCode.equalsIgnoreCase(PLANT_FUND_CD)) {
            if (StringUtils.isEmpty(restrictedStatusCode) || 
               (!restrictedStatusCode.equalsIgnoreCase("R") && !restrictedStatusCode.equalsIgnoreCase("U"))) {
               
                putGlobalError(KeyConstants.ERROR_DOCUMENT_ACCMAINT_ACCT_RESTRICTED_STATUS_CD_MUST_BE_U_OR_R);
                success &= false;
            }
        }
        
        //	for all other fund groups the value is set to 'U'. R being restricted,U being unrestricted.
        else {
            if (!restrictedStatusCode.equalsIgnoreCase("U")) {
				putGlobalError(KeyConstants.ERROR_DOCUMENT_ACCMAINT_ACCT_RESTRICTED_STATUS_CD_MUST_BE_U);
				success &= false;
            }
        }
        
        //	an account in the general fund fund group cannot have a budget recording level of mixed.
        if (fundGroupCode.equalsIgnoreCase(GENERAL_FUND_CD)) {
            if (newAccount.getBudgetRecordingLevelCode().equalsIgnoreCase("M")) {
                putFieldError("budgetRecordingLevelCode", KeyConstants.ERROR_DOCUMENT_ACCMAINT_ACCT_GF_BUDGET_RECORD_LVL_MIXED);
                success &= false;
            }
        }
        return success;
    }
    
    /**
     * 
     * This method checks to see if any SubFund Group rules were violated
     * @param maintenanceDocument
     * @return false on rules violation
     * 
     */
    private boolean checkSubFundGroup(MaintenanceDocument maintenanceDocument) {
        
        boolean success = true;
        
        //	sub_fund_grp_cd on the account must be set to a valid sub_fund_grp_cd that exists in the ca_sub_fund_grp_t table
        //	assuming here that since we did the PersistenceService.refreshNonKeyFields() at beginning of rule that if the 
        // SubFundGroup object would be populated.
        if (ObjectUtils.isNull(newAccount.getSubFundGroup())) {
            putFieldError("subFundGroup", KeyConstants.ERROR_DOCUMENT_ACCMAINT_INVALID_SUBFUNDGROUP);
            success &= false;
        }
        
        //TODO: DEFERRED - if the sub fund group code is plant fund, construction and major remodeling (PFCMR), 
        //      the campus and building are required on the description screen for CAMS.
        //      NOTE:  we dont have any description fields associated with the Account ... dont know what to do here
        
        //	PFCMD (Plant Fund, Construction and Major Remodeling) SubFundCode checks
        if(newAccount.getSubFundGroupCode().equalsIgnoreCase("PFCMR")) {
            
            //	if sub_fund_grp_cd is 'PFCMR' then campus_cd must be entered
            if (StringUtils.isEmpty(newAccount.getAccountPhysicalCampusCode())) {
                putGlobalError(KeyConstants.ERROR_DOCUMENT_ACCMAINT_SUBFUNDGROUP_WITH_INVALID_CAMPUS_CD);
                success &= false;
            }
        
        	//	if sub_fund_grp_cd is 'PFCMR' then bldg_cd must be entered
        	if (StringUtils.isEmpty(newAccount.getAccountZipCode())) {
                putGlobalError(KeyConstants.ERROR_DOCUMENT_ACCMAINT_SUBFUNDGROUP_WITH_INVALID_ZIP_AND_BUILDING_CD);
                success &= false;
        	} 
        	else {
        	    
        	    //	get a reference to the ZipCode bo
                HashMap primaryKeys = new HashMap();
                primaryKeys.put("postalZipCode", newAccount.getAccountZipCode());
                PostalZipCode zip = (PostalZipCode) SpringServiceLocator.getBusinessObjectService().findByPrimaryKey(PostalZipCode.class, primaryKeys);
                
                //	now we can check the building code
                if (StringUtils.isEmpty(zip.getBuildingCode())) {
                    putGlobalError(KeyConstants.ERROR_DOCUMENT_ACCMAINT_SUBFUNDGROUP_WITH_INVALID_ZIP_AND_BUILDING_CD);
                    success &= false;
                }
            }
        }

        return success;
    }
    

}