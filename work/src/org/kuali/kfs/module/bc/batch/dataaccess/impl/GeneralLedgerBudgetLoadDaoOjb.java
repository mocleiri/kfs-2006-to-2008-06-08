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
package org.kuali.module.budget.dao.ojb;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.sql.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

import org.kuali.core.dao.ojb.PlatformAwareDaoBaseOjb;
import org.kuali.core.service.DateTimeService;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.util.KualiInteger;

import org.kuali.module.chart.bo.AccountingPeriod;
import org.kuali.module.budget.BCConstants;
import org.kuali.module.budget.bo.BudgetConstructionHeader;
import org.kuali.module.budget.bo.BudgetConstructionMonthly;
import org.kuali.module.budget.bo.PendingBudgetConstructionGeneralLedger;
import org.kuali.module.budget.dao.GeneralLedgerBudgetLoadDao;

import org.kuali.kfs.bo.GeneralLedgerPendingEntry;
import org.kuali.kfs.service.HomeOriginationService;
import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.KFSPropertyConstants;
import org.kuali.kfs.context.SpringContext;

import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.SubFundGroup;

public class GeneralLedgerBudgetLoadDaoOjb extends PlatformAwareDaoBaseOjb implements GeneralLedgerBudgetLoadDao {

    /*  turn on the logger for the persistence broker */
    private static Logger LOG = org.apache.log4j.Logger.getLogger(GenesisDaoOjb.class);

    private DateTimeService dateTimeService;
    private HomeOriginationService homeOriginationService;
    
    /*
     *   see GeneralLedgerBudgetLoadDao.LoadGeneralLedgerFromBudget
     */
    public void loadGeneralLedgerFromBudget (Integer fiscalYear)
    {
        /**
         * 
         *  this method calls a series of steps that load the general ledger from the budget into
         *  the general ledger pending entry table.
         *  this method takes a fiscal year as input, but all that is required is that this object be
         *  a key labeling the bduget construction general ledger rows for the budget period to be loaded.  
         *  it need not be an actual fiscal year.
         */
        //
        // set up the global variables
        // this is a single object that can be passed to all methods that need it, to make the code "thread safe"
        // (1)  the fiscal year to load
        // (2)  the initial sequence numbers for each document to be loaded
        // (3)  the run date (which will be the transaction date)
        // (4)  the "origination code", which comes from the database
        DaoGlobalVariables daoGlobalVariables = new DaoGlobalVariables (fiscalYear);
        /**
         * initiliaze the counter variables
         */ 
        DiagnosticCounters diagnosticCounters = new DiagnosticCounters();
        /**
         * make sure all the accounting periods for the load year are open, so the entry lines we create can be posted
         */ 
        openAllAccountingPeriods(fiscalYear);
        /**
         * process pending budget construction general ledger rows
         */ 
        loadPendingBudgetConstructionGeneralLedger(daoGlobalVariables,diagnosticCounters);
        /**
         * process budget construction monthly budget rows
         */
           loadBudgetConstructionMonthlyBudget (daoGlobalVariables, diagnosticCounters);
        //write out the counts for verification
        diagnosticCounters.writeDiagnosticCounters();
     }
    
    /****************************************************************************************************************
     *                                  methods to do the actual load                                               *
     ****************************************************************************************************************/
    
    /**
     *   build a hashmap containing the next entry sequence number to use for each document (document number)
     *   to be loaded from budget construction to the general ledger
     *   @param  target fiscal year for the budget load
     *   @return HashMapap keyed on document number containing the next entry sequence number to use for 
     *           the key
     */
    
    private HashMap<String,Integer> entrySequenceNumber (Integer requestYear)
    {
        HashMap<String,Integer> nextEntrySequenceNumber;
        // set up the query: each distinct document number in the source load table
        Criteria criteriaID = new Criteria();
        criteriaID.addEqualTo(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR,requestYear);
        ReportQueryByCriteria queryID = new ReportQueryByCriteria(BudgetConstructionHeader.class,criteriaID);
        queryID.setAttributes(new String[] {KFSPropertyConstants.DOCUMENT_NUMBER});
        
        nextEntrySequenceNumber = new HashMap<String,Integer>(hashCapacity(queryID));
        
        // OK. build the hash map
        // there are NO entries for these documents yet, so we initialize the entry sequence number to 0
        Iterator documentNumbersToLoad = 
            getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(queryID);
        while (documentNumbersToLoad.hasNext())
        {
            Object[] resultRow = (Object[]) documentNumbersToLoad.next();
            nextEntrySequenceNumber.put((String) resultRow[0],new Integer(0));
        }

        return nextEntrySequenceNumber;
    }
    
    /**
     * 
     * This method creates a new generalLedgerPendingEntry object and initializes it with the default settings
     * for the budget construction general ledger load.
     * @param requestYear
     * @param financialSystemOriginationCode
     * @return intiliazed GeneralLedgerPendingEntry business object
     */
    
    private GeneralLedgerPendingEntry getNewPendingEntryWithDefaults(DaoGlobalVariables daoGlobalVariables)
    {
        GeneralLedgerPendingEntry newRow = new GeneralLedgerPendingEntry();
        newRow.setUniversityFiscalYear(daoGlobalVariables.getRequestYear());
        newRow.setTransactionLedgerEntryDescription(BCConstants.BC_TRN_LDGR_ENTR_DESC);
        newRow.setFinancialDocumentTypeCode(KFSConstants.BudgetConstructionConstants.BUDGET_CONSTRUCTION_DOCUMENT_TYPE);
        newRow.setFinancialDocumentApprovedCode(KFSConstants.PENDING_ENTRY_APPROVED_STATUS_CODE.APPROVED);
        newRow.setTransactionDate(daoGlobalVariables.getTransactionDate());
        newRow.setTransactionEntryOffsetIndicator(false);
        newRow.setFinancialSystemOriginationCode(daoGlobalVariables.getFinancialSystemOriginationcode());
        // the fields below are set to null
        newRow.setOrganizationDocumentNumber(null);
        newRow.setProjectCode(null);
        newRow.setOrganizationReferenceId(null);
        newRow.setReferenceFinancialDocumentTypeCode(null);
        newRow.setReferenceOriginationCode(null);
        newRow.setReferenceFinancialDocumentNumber(null);
        newRow.setFinancialDocumentReversalDate(null);
        newRow.setTransactionEncumbranceUpdateCode(null);
        newRow.setAcctSufficientFundsFinObjCd(null);
        newRow.setTransactionDebitCreditCode(null);
        newRow.setTransactionEntryProcessedTs(null);
        return newRow;
    }
    
    private void loadBudgetConstructionMonthlyBudget (DaoGlobalVariables daoGlobalVariables, DiagnosticCounters diagnosticCounters)
    {
        QueryByCriteria queryID = queryForBudgetConstructionMonthly (daoGlobalVariables.getRequestYear());
        Iterator<BudgetConstructionMonthly> monthlyBudgetRows = 
            getPersistenceBrokerTemplate().getIteratorByQuery(queryID);
        while (monthlyBudgetRows.hasNext())
        {
            BudgetConstructionMonthly monthlyBudgetIn = monthlyBudgetRows.next();
            diagnosticCounters.increaseBudgetConstructionMonthlyBudgetRead();
            if (daoGlobalVariables.shouldThisAccountLoad(monthlyBudgetIn.getAccountNumber()+monthlyBudgetIn.getChartOfAccountsCode()))
            {
                GeneralLedgerPendingEntry newRow = getNewPendingEntryWithDefaults(daoGlobalVariables);
                writeGeneralLedgerPendingEntryFromMonthly(newRow, monthlyBudgetIn, daoGlobalVariables, diagnosticCounters);
            }
            else
            {
                diagnosticCounters.increaseBudgetConstructionMonthlyBudgetSkipped();
            }
        }
    }
    
    /**
     * 
     * This method loads all the eligible pending budget construction general ledger rows
     * @param daoGlobalVariables
     * @param diagnosticCounters
     */
    private void loadPendingBudgetConstructionGeneralLedger(DaoGlobalVariables daoGlobalVariables, DiagnosticCounters diagnosticCounters)
    {
        QueryByCriteria queryID = queryForPendingBudgetConstructionGeneralLedger(daoGlobalVariables.getRequestYear());
        Iterator<PendingBudgetConstructionGeneralLedger> pbglRows =
            getPersistenceBrokerTemplate().getIteratorByQuery(queryID);
        while (pbglRows.hasNext())
        {
            PendingBudgetConstructionGeneralLedger pbglIn = pbglRows.next();
            diagnosticCounters.increaseBudgetConstructionPendingGeneralLedgerRead();
            if (daoGlobalVariables.shouldThisAccountLoad(pbglIn.getAccountNumber()+pbglIn.getChartOfAccountsCode()))
            {
                GeneralLedgerPendingEntry newRow = getNewPendingEntryWithDefaults(daoGlobalVariables);
                writeGeneralLedgerPendingEntryFromAnnual(newRow,pbglIn,daoGlobalVariables,diagnosticCounters);
            }
            else
            {
                diagnosticCounters.increaseBudgetConstructionPendingGeneralLedgerSkipped();
            }
        }
    }

    /**
     * 
     * This method builds the query to fetch the monthly budget general ledger lines to be loaded
     * @param fiscalYear : the year to be loaded
     * @return query for fetching monthly budget rows
     */
    private QueryByCriteria queryForBudgetConstructionMonthly (Integer fiscalYear)
    {
        // we only select rows which have non-zero budget amounts
        // on this object, proxy=true, so we can do a regular query for a business object instead of a report query
        Criteria criteriaID = new Criteria();
        criteriaID.addEqualTo(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR,fiscalYear);
        // we want to test for at least one non-zero monthly amount
        Criteria orCriteriaID = new Criteria();
        Iterator<String[]> monthlyPeriods = BCConstants.BC_MONTHLY_AMOUNTS.iterator();
        while (monthlyPeriods.hasNext())
        {
            // the first array element is the amount field name (the second is the corresponding accounting period)
            String monthlyAmountName = monthlyPeriods.next()[0];
            Criteria amountCriteria = new Criteria();
            amountCriteria.addNotEqualTo(monthlyAmountName,new KualiInteger(0));
            orCriteriaID.addOrCriteria(amountCriteria);
        }
        criteriaID.addAndCriteria(orCriteriaID);
        QueryByCriteria queryID = new QueryByCriteria(BudgetConstructionMonthly.class,criteriaID);
        return queryID; 
    }
    
    /**
     * 
     * This method builds the query to fetch the pending budget construction general ledger rows to be loaded
     * @param fiscalYear: the year to be loaded 
     * @return query for fetching pending budget construction GL rows
     */
    
    private QueryByCriteria queryForPendingBudgetConstructionGeneralLedger(Integer fiscalYear)
    {
        // we only select rows which have non-zero budget amounts
        // on this object, proxy=true, so we can do a regular query for a business object instead of a report query
        Criteria criteriaID = new Criteria();
        criteriaID.addEqualTo(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR,fiscalYear);
        criteriaID.addNotEqualTo(KFSPropertyConstants.ACCOUNT_LINE_ANNUAL_BALANCE_AMOUNT,new KualiInteger(0));
        QueryByCriteria queryID = new QueryByCriteria(PendingBudgetConstructionGeneralLedger.class,criteriaID);
        return queryID;
    }
    
    /**
     * 
     * complete the pending entry row based on the data returned from the DB
     * store it to the DB
     * @param newRow
     * @param source annual budget construction GL row
     * @param object containing global constants
     */
    private void writeGeneralLedgerPendingEntryFromAnnual (GeneralLedgerPendingEntry newRow,
                                                           PendingBudgetConstructionGeneralLedger pbgl,
                                                           DaoGlobalVariables daoGlobalVariables,
                                                           DiagnosticCounters diagnosticCounters)
    {   
        /**
         * first get the document number
         */
        String incomingDocumentNumber = pbgl.getDocumentNumber();
        /**
         * write a base budget row
         */
        newRow.setFinancialBalanceTypeCode(KFSConstants.BALANCE_TYPE_BASE_BUDGET);
        newRow.setUniversityFiscalPeriodCode(KFSConstants.PERIOD_CODE_BEGINNING_BALANCE);
        /**
         * set the variable fields
         */
        newRow.setTransactionLedgerEntrySequenceNumber(daoGlobalVariables.getNextSequenceNumber(incomingDocumentNumber));
        newRow.setDocumentNumber(incomingDocumentNumber);                                       // document number
        newRow.setChartOfAccountsCode(pbgl.getChartOfAccountsCode());                           // chart of accounts
        newRow.setAccountNumber(pbgl.getAccountNumber());                                       // account number
        newRow.setSubAccountNumber(pbgl.getSubAccountNumber());                                 // sub account number
        newRow.setFinancialObjectCode(pbgl.getFinancialObjectCode());                           // object code
        newRow.setFinancialSubObjectCode(pbgl.getFinancialSubObjectCode());                     // sub object code
        newRow.setFinancialObjectTypeCode(pbgl.getFinancialObjectTypeCode());                   // object type code
        /**
         * the budget works with whole numbers--we must convert to decimal for the general ledger
         */  
        newRow.setTransactionLedgerEntryAmount(pbgl.getAccountLineAnnualBalanceAmount().kualiDecimalValue());
        /**
         * now we store the base budget value
         */
        getPersistenceBrokerTemplate().store(newRow);
        diagnosticCounters.increaseGeneralLedgerBaseBudgetWritten();
        /**
         * the same row needs to be written as a current budget item
         * we change only the balance type and the sequence number
         */
        newRow.setFinancialBalanceTypeCode(KFSConstants.BALANCE_TYPE_CURRENT_BUDGET);
        newRow.setTransactionLedgerEntrySequenceNumber(daoGlobalVariables.getNextSequenceNumber(incomingDocumentNumber));
        /** 
         * store the current budget value
         */
        getPersistenceBrokerTemplate().store(newRow);
        diagnosticCounters.increasGenneralLedgerCurrentBudgetWritten();
    }
    
    private void writeGeneralLedgerPendingEntryFromMonthly (GeneralLedgerPendingEntry newRow,
                                                            BudgetConstructionMonthly pbglMonthly,
                                                            DaoGlobalVariables daoGlobalVariables,
                                                            DiagnosticCounters diagnosticCounters)  
    {
    /**
     * first get the document number
     */ 
    String incomingDocumentNumber = pbglMonthly.getDocumentNumber();
    /**
     * write a base budget row
     */
    newRow.setFinancialBalanceTypeCode(KFSConstants.BALANCE_TYPE_MONTHLY_BUDGET);
    /**
     * set the variable fields
     */
    newRow.setDocumentNumber(incomingDocumentNumber);                                              // document number
    newRow.setChartOfAccountsCode(pbglMonthly.getChartOfAccountsCode());                           // chart of accounts
    newRow.setAccountNumber(pbglMonthly.getAccountNumber());                                       // account number
    newRow.setSubAccountNumber(pbglMonthly.getSubAccountNumber());                                 // sub account number
    newRow.setFinancialObjectCode(pbglMonthly.getFinancialObjectCode());                           // object code
    newRow.setFinancialSubObjectCode(pbglMonthly.getFinancialSubObjectCode());                     // sub object code
    newRow.setFinancialObjectTypeCode(pbglMonthly.getFinancialObjectTypeCode());                   // object type code

    /**
     *  we have to loop through the monthly array, and write an MB row for each monthly row with a non-zero amount
     * (we do this to write less code.  we hope that the extra hit from reflection won't be too bad)
     */
    Iterator<String[]> monthlyPeriodAmounts = BCConstants.BC_MONTHLY_AMOUNTS.iterator();
    while (monthlyPeriodAmounts.hasNext())
    {
        String[] monthlyPeriodProperties = monthlyPeriodAmounts.next();
        KualiInteger monthlyAmount;
        try
        {
           monthlyAmount = (KualiInteger) PropertyUtils.getSimpleProperty(pbglMonthly,monthlyPeriodProperties[0]);
        }    
        catch (IllegalAccessException ex)
        {
            LOG.error(String.format("\nunable to use get method to access value of %s in %s\n",monthlyPeriodProperties[0],BudgetConstructionMonthly.class.getName()),ex);
            throw new RuntimeException(ex);
        }
        catch (InvocationTargetException ex)
        {
            LOG.error(String.format("\nunable to invoke get method for %s in %s\n",monthlyPeriodProperties[0],BudgetConstructionMonthly.class.getName()),ex);
            throw new RuntimeException(ex);
        }
        catch (NoSuchMethodException ex)
        {
            LOG.error(String.format("\nNO get method found for %s in %s ???\n",monthlyPeriodProperties[0],BudgetConstructionMonthly.class.getName()),ex);
            throw new RuntimeException(ex);
        }
        finally
        {
            diagnosticCounters.writeDiagnosticCounters();
        }
        if (!(monthlyAmount.isZero()))
        {    
           newRow.setTransactionLedgerEntrySequenceNumber(daoGlobalVariables.getNextSequenceNumber(incomingDocumentNumber));
           newRow.setUniversityFiscalPeriodCode(monthlyPeriodProperties[1]);                        // accounting period
           newRow.setTransactionLedgerEntryAmount(monthlyAmount.kualiDecimalValue());               // amount
           getPersistenceBrokerTemplate().store(newRow);
           diagnosticCounters.increaseBudgetConstructionMonthlyBudgetWritten();
        }   
    }
    }
    
    

    /****************************************************************************************************************
     *                                                                                                              *
     *   This section build the list of accounts that SHOULD NOT be loaded to the general ledger                    *
     *   (This may seem strange--why build a budget if you aren't going to load it--but in the FIS the budget       *
     *    loaded to payroll as well.  For grant accounts, the FIS allowed people to set salaries for the new year   * 
     *    so those would load to payroll.  But, the actual budget for a grant account was not necessarily done on a * 
     *    fiscal year basis, and was not part of the university's operating budget, so there was no "base budget"   *
     *    for a grant account to load to the general ledger.)                                                       *
     *   (1)  We will inhibit the load to the general ledger of all accounts in given sub fund groups               *
     *   (2)  (We WILL allow closed accounts to load.  There should not be any--they should have been filtered      * 
     *         out in the budget application, but if there are, they will be caught by the GL scrubber.  We want    * 
     *         people to have a record of this kind of load failure, so it can be corrected.                        *                                                          *
     *                                                                                                              *
     ****************************************************************************************************************/

    /**
     *   get a list of accounts that should not be loaded from the budget to the General Ledger
     *  @return hashset of accounts NOT to be loaded
     */
    
    private HashSet<String> getAccountsNotToBeLoaded()
    {
      HashSet<String> bannedAccounts;
      /**
       * list of subfunds which should not be loaded
       */ 
      HashSet<String> bannedSubFunds = getSubFundsNotToBeLoaded();
      /**
       * query for the subfund property for each account in the DB
       */
      ReportQueryByCriteria queryID = 
          new ReportQueryByCriteria(Account.class,org.apache.ojb.broker.query.ReportQueryByCriteria.CRITERIA_SELECT_ALL);
      queryID.setAttributes(new String[] {KFSPropertyConstants.ACCOUNT_NUMBER,
                                          KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE,
                                          KFSPropertyConstants.SUB_FUND_GROUP_CODE});
      bannedAccounts = new HashSet<String>(hashCapacity(queryID));
      /** 
       *  use the results to build a hash set of accounts which should NOT be loaded (that is, their subfunds are in
       *  the list of subfunds we do not want
       */
      Iterator accountProperties = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(queryID);
      while (accountProperties.hasNext())
      {
          Object[] selectListValues = (Object[]) accountProperties.next();
          /**
           * we will add an account/chart to the list if it has a no-load subfundgroup
           */
          if (bannedSubFunds.contains((String) selectListValues[2]))
          {
            /**
             * hash content is account number concatenated with chart (the key of the chart of accounts table)
             */  
             bannedAccounts.add(((String) selectListValues[0])+((String) selectListValues[1]));   
          }
      }
      return bannedAccounts;
    }
    
    /**
     *  build a hash set of subfunds whose accounts should NOT be loaded
     *  this can be done by either a list of FUND groups and/or a list of subfund groups
     *  @see org.kuali.module.budget.BCConstants to initialize the String[] array(s) as desired
     *  @return  list of subfunds whose accounts will NOT be loaded
     */
     private HashSet<String> getSubFundsNotToBeLoaded ()
     {
       HashSet<String> bannedSubFunds;
       if (BCConstants.NO_BC_GL_LOAD_FUND_GROUPS.size() != 0)
       {
           /**
            * look for subfunds in the banned fund groups
            */
           Criteria criteriaID = new Criteria();
           criteriaID.addIn(KFSPropertyConstants.FUND_GROUP_CODE,BCConstants.NO_BC_GL_LOAD_FUND_GROUPS);
           ReportQueryByCriteria queryID = new ReportQueryByCriteria(SubFundGroup.class,criteriaID);
           queryID.setAttributes(new String[]{KFSPropertyConstants.SUB_FUND_GROUP_CODE});
           /**
            * set the size of the hashset based on the number of rows the query will return
            */
           bannedSubFunds = new HashSet<String>(hashCapacity(queryID)+BCConstants.NO_BC_GL_LOAD_SUBFUND_GROUPS.size());
           Iterator subfundsForBannedFunds = 
               getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(queryID);
           /**
            * add the subfunds for the fund groups to be skipped to the hash set
            */
           while (subfundsForBannedFunds.hasNext())
           {
              bannedSubFunds.add((String)((Object[]) subfundsForBannedFunds.next())[0]); 
           }
       }
       else
       {
           bannedSubFunds = new HashSet<String>(BCConstants.NO_BC_GL_LOAD_SUBFUND_GROUPS.size()+1);    
       }
       /**
        * now add the specific sub funds we don't want from the hard-coded array in BCConstants to the hash set
        */
       Iterator<String> additionalBannedSubFunds = BCConstants.NO_BC_GL_LOAD_SUBFUND_GROUPS.iterator();
       while (additionalBannedSubFunds.hasNext())
       {
           bannedSubFunds.add(additionalBannedSubFunds.next());
       }
       return bannedSubFunds;
     }
     
    /****************************************************************************************************************
     *    This section sets all the accounting periods for the coming year to open.                                 *
     *    The monthly budget will load by accounting period.  If some are not open, some monthly rows will error    *
     *    out in the scrubber.  Current FIS procedure is to prevent this from happening, by opening all the         *
     *    accounting periods and letting the university chart manager close them after the budget is loaded if that *
     *    is desirable for some reason.  If an institution prefers another policy, just don't call these methods.   *
     *    But, even if we let the scrubber fail, there will be no way to load the monthly rows from the error tables*
     *    unless the corresponding accounting periods are open.                                                     *   
     ****************************************************************************************************************/
     
     /**
      *  this method makes sure all accounting periods inn the target fiscal year are open 
      *  @param request fiscal year (or other fiscal period) which is the TARGET of the load
      */
     private void openAllAccountingPeriods (Integer requestYear)
     {
         Criteria criteriaID = new Criteria();
         criteriaID.addEqualTo(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR,requestYear);
         criteriaID.addNotEqualTo(KFSPropertyConstants.UNIVERSITY_FISCAL_PERIOD_STATUS_CODE,
                 KFSConstants.ACCOUNTING_PERIOD_STATUS_OPEN);
         QueryByCriteria queryID = new QueryByCriteria(AccountingPeriod.class,criteriaID);
         Iterator<AccountingPeriod> unopenPeriods = 
             getPersistenceBrokerTemplate().getIteratorByQuery(queryID);
         int periodsOpened = 0;
         while (unopenPeriods.hasNext())
         {
             AccountingPeriod periodToOpen = unopenPeriods.next();
             periodToOpen.setUniversityFiscalPeriodStatusCode(KFSConstants.ACCOUNTING_PERIOD_STATUS_OPEN);
             getPersistenceBrokerTemplate().store(periodToOpen);
             periodsOpened = periodsOpened+1;
         }
         LOG.warn(String.format("\n\naccounting periods for %d changed to open status: %d",requestYear,new Integer(periodsOpened)));
     }
     
     /**********************************************************************************************************************************
      * These two classes are containers so we can make certain variables accessible to all methods without making them global to the  *
      * outer class and without cluttering up the method signatures.                                                                   *
      **********************************************************************************************************************************/
     
     /**
      * 
      * This class keeps a set of counters and provides a method to print them out
      * This allows us to set up thread-local counters in the unlikely event this code is run by more than one thread
      */
     private class DiagnosticCounters
     {
         long budgetConstructionPendingGeneralLedgerRead    = 0;
         long budgetConstructionPendingGeneralLedgerSkipped = 0;
         long generalLedgerBaseBudgetWritten                = 0;
         long generalLedgerCurrentBudgetWritten             = 0;
         
         long budgetConstructionMonthlyBudgetRead           = 0;
         long budgetConstructionMonthlyBudgetSkipped        = 0;
         long budgetConstructionMonthlyBudgetWritten        = 0;
         
         public void increaseBudgetConstructionPendingGeneralLedgerRead()
         {
             budgetConstructionPendingGeneralLedgerRead++;
         }
         
         public void increaseBudgetConstructionPendingGeneralLedgerSkipped()
         {
             budgetConstructionPendingGeneralLedgerSkipped++;
         }
         
         public void increaseGeneralLedgerBaseBudgetWritten()
         {
             generalLedgerBaseBudgetWritten++;
         }
         
         public void increasGenneralLedgerCurrentBudgetWritten()
         {
             generalLedgerCurrentBudgetWritten++;
         }
         
         public void increaseBudgetConstructionMonthlyBudgetRead ()
         {
             budgetConstructionMonthlyBudgetRead++;
         }
         
         public void increaseBudgetConstructionMonthlyBudgetSkipped ()
         {
             budgetConstructionMonthlyBudgetSkipped++;
         }
         
         public void increaseBudgetConstructionMonthlyBudgetWritten ()
         {
             budgetConstructionMonthlyBudgetWritten++;
         }
         
         public void writeDiagnosticCounters()
         {
             LOG.warn(String.format("\n\nPending Budget Construction General Ledger Load\n"));
             LOG.warn(String.format("\n  pending budget construction GL rows read:        %,d",budgetConstructionPendingGeneralLedgerRead));
             LOG.warn(String.format("\n  pending budget construction GL rows skipped:     %,d",budgetConstructionPendingGeneralLedgerSkipped));
             LOG.warn(String.format("\n\n  base budget rows written:                        %,d",generalLedgerBaseBudgetWritten));
             LOG.warn(String.format("\n  current budget rows written:                     %,d",generalLedgerCurrentBudgetWritten));
             LOG.warn(String.format("\n\n  pending budget construction monthly rows read:   %,d",budgetConstructionMonthlyBudgetRead));
             LOG.warn(String.format("\n  pending budget construction monthly rows skipped: %,d",budgetConstructionMonthlyBudgetSkipped));
             LOG.warn(String.format("\n  pending budget construction monthly rows written: %,d",budgetConstructionMonthlyBudgetWritten));
         }
     }
     
     /**
      * 
      * This class allows us to create global variables and pass them around.  This should make the code thread safe, in the
      * unlikely event it is called by more than one thread.  it also allows us to fetch constants and build datas stuctures 
      * from the DB once upon instantiation of this class, and make them available for the duration of the run
      * @param requestYear
      * @param <documentNumber, ledger sequence number> HashMap
      * @param current SQL Date (which will be the transaction date in the general ledger entry rows we create)
      * @param the "financial system Origination Code" for this database
      */
     private class DaoGlobalVariables 
     {
         private Integer requestYear;
         private HashMap<String,Integer> entrySequenceNumber;
         private Date transactionDate;
         private String financialSystemOriginationCode;
         private HashSet<String> accountsNotToBeLoaded;

         public DaoGlobalVariables(Integer requestYear)
         {
             this.requestYear = requestYear;
             this.entrySequenceNumber = entrySequenceNumber(requestYear);
//             this.transactionDate = SpringContext.getBean(DateTimeService.class).getCurrentSqlDate();
             this.transactionDate = dateTimeService.getCurrentSqlDate();
             this.financialSystemOriginationCode = 
                 homeOriginationService.getHomeOrigination().getFinSystemHomeOriginationCode();
             this.accountsNotToBeLoaded = getAccountsNotToBeLoaded();
         }

         public Integer getRequestYear() { return this.requestYear;}

         /**
          * return the next available sequence number for the input key and update "next available"
          */ 
         public Integer getNextSequenceNumber(String seqKey) {
             Integer newSeqNumber = entrySequenceNumber.get(seqKey);
             entrySequenceNumber.put(seqKey,new Integer(newSeqNumber.intValue()+1));
             return newSeqNumber;
             }

         public Date getTransactionDate() { return this.transactionDate;}

         public String getFinancialSystemOriginationcode() { return this.financialSystemOriginationCode;}

         public boolean shouldThisAccountLoad(String accountAndChart)
         {
             return (!accountsNotToBeLoaded.contains(accountAndChart));
         }
     }
     
     public void setDateTimeService (DateTimeService dateTimeService)
     {
         this.dateTimeService = dateTimeService;
     }
     
     public void setHomeOriginationService (HomeOriginationService homeOriginationService)
     {
         this.homeOriginationService = homeOriginationService;
     }
     
    
    /****************************************************************************************************************
     *                                                 Utility methods                                              *                         
     ****************************************************************************************************************/

    /**
     * This method determines the capacity of the hash based on the item count returned by the query
     * 
     * @param queryID
     * @return hash capacity based on query result set size
     */
    private Integer hashCapacity(ReportQueryByCriteria queryID) {
        // this corresponds to a load factor of a little more than the default load factor
        // of .75
        // (a rehash supposedly occurs when the actual number of elements exceeds
        // hashcapacity*(load factor). we want to avoid a rehash)
        // N rows < .75*capacity ==> capacity > 4N/3 or 1.3333N We add a little slop.
        Integer actualCount = new Integer(getPersistenceBrokerTemplate().getCount(queryID));
        return ((Integer) ((Double) (actualCount.floatValue() * (1.45))).intValue());
    }
  
    /*****************************************************************************************************************
     *   @@TODO:  these are test methods--remove them                                                                *
     *****************************************************************************************************************/
    
    public void unitTestRoutine(Integer fiscalYear)
    {
        // 11/29/07testAccountElimination();
        // 11/29/07openAllAccountingPeriods(2010);
        // 11/30/07testSequenceNumbers(2008);
        //  we got a load exception--we have to initialize the date in the creating the global variables object
        //  we cannot do it in the variable declaration because the entire context may not be there yet
        // 12/04/07testDefaults(fiscalYear);
        testSQL(fiscalYear);
    }
    
    private void testAccountElimination()
    {
        HashSet<String>  verbotenAccts = getAccountsNotToBeLoaded();
        LOG.warn(String.format("\n\nnumber of accounts to skip: %d",verbotenAccts.size()));
        Iterator<String> noLoadList = verbotenAccts.iterator();
        int rowCount = 0;
        int maskCount = 127;
        while (noLoadList.hasNext())
        {
           String skippy = noLoadList.next();
           rowCount = rowCount+1;
           if ((rowCount & maskCount)  == 1)
                   LOG.warn(String.format("\n   sample account to skip: %s",skippy));
        }
    }
    
    private void testDefaults(Integer fiscalYear)
    {
        String maxDoc = new String("");
        String minDoc = new String("");
        // this part tests the routine that initializes a new entry row with the common values
        DaoGlobalVariables daoGlobalVariables = new DaoGlobalVariables (fiscalYear);
        GeneralLedgerPendingEntry newRow = getNewPendingEntryWithDefaults(daoGlobalVariables);
        LOG.warn(String.format("\nGeneralLedgerPendingEntry initialized without incident"));
        //  we also want to test whether the entry sequence numbers are updated properly
        //  this part does that
       ReportQueryByCriteria queryID = new ReportQueryByCriteria(BudgetConstructionHeader.class,ReportQueryByCriteria.CRITERIA_SELECT_ALL);
       String[] selectList = new String[] {"MAX("+KFSPropertyConstants.DOCUMENT_NUMBER+")",
                                           "MIN("+KFSPropertyConstants.DOCUMENT_NUMBER+")"};
       queryID.setAttributes(selectList);
       Iterator<Object[]> returnedRows = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(queryID);
       while (returnedRows.hasNext())
       {
           Object[] returnedValues = returnedRows.next();
           maxDoc = (String) returnedValues[0];
           minDoc = (String) returnedValues[1];
       }
       LOG.warn(String.format("\n\nentry sequence iterations for (%s,%s): the returned number should autoincrement",maxDoc,minDoc));
       for (int i = 1; i<=3; i++)
       {
           LOG.warn(String.format("\n  %s: entry sequence %d",maxDoc,daoGlobalVariables.getNextSequenceNumber(maxDoc)));
           LOG.warn(String.format("\n  %s: entry sequence %d",minDoc,daoGlobalVariables.getNextSequenceNumber(minDoc)));
       }
    }
    
    private void testSequenceNumbers(Integer requestYear)
    {
        HashMap<String,Integer>  seqNos = entrySequenceNumber(requestYear);
        LOG.warn(String.format("\n\nnumber of document number sequence number entries: %d",seqNos.size()));
        int rowCount = 0;
        int maskCount = 15;
        for (Map.Entry<String,Integer> seqNoInstance : seqNos.entrySet() )
        {
           rowCount = rowCount+1;
           if ((rowCount & maskCount)  == 1)
                   LOG.warn(String.format("\n   sample sequence number entry: <%s, %d>",seqNoInstance.getKey(),seqNoInstance.getValue()));
        }
    }

    private void testSQL(Integer requestYear)
    {
        DaoGlobalVariables daoGlobalVariables = new DaoGlobalVariables (requestYear);
        DiagnosticCounters diagnosticCounters = new DiagnosticCounters();
        QueryByCriteria queryID = queryForPendingBudgetConstructionGeneralLedger(daoGlobalVariables.getRequestYear());
        Iterator<PendingBudgetConstructionGeneralLedger> pbglRows =
            getPersistenceBrokerTemplate().getIteratorByQuery(queryID);
        while (pbglRows.hasNext())
        {
            diagnosticCounters.increaseBudgetConstructionPendingGeneralLedgerRead();
        }
        queryID = queryForBudgetConstructionMonthly (daoGlobalVariables.getRequestYear());
        Iterator<BudgetConstructionMonthly> monthlyBudgetRows = 
            getPersistenceBrokerTemplate().getIteratorByQuery(queryID);
        while (monthlyBudgetRows.hasNext())
        {
            diagnosticCounters.increaseBudgetConstructionMonthlyBudgetRead();
        }
        diagnosticCounters.writeDiagnosticCounters();
    }
}