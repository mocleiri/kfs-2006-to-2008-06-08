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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.sql.Date;

import org.apache.log4j.Logger;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

import org.kuali.core.dao.ojb.PlatformAwareDaoBaseOjb;
import org.kuali.core.service.DateTimeService;

import org.kuali.module.chart.bo.AccountingPeriod;
import org.kuali.module.budget.BCConstants;
import org.kuali.module.budget.bo.BudgetConstructionHeader;
import org.kuali.module.budget.bo.BudgetConstructionMonthly;
import org.kuali.module.budget.bo.PendingBudgetConstructionGeneralLedger;
import org.kuali.module.budget.dao.GeneralLedgerBudgetLoadDao;

import org.kuali.kfs.bo.GeneralLedgerPendingEntry;
import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.KFSPropertyConstants;
import org.kuali.kfs.context.SpringContext;

import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.SubFundGroup;

public class GeneralLedgerBudgetLoadDaoOjb extends PlatformAwareDaoBaseOjb implements GeneralLedgerBudgetLoadDao {

    /*  turn on the logger for the persistence broker */
    private static Logger LOG = org.apache.log4j.Logger.getLogger(GenesisDaoOjb.class);
    private Date BC_GL_LOAD_TRANSACTION_DATE;

    /*
     *   see GeneralLedgerBudgetLoadDao.LoadGeneralLedgerFromBudget
     */
    public void LoadGeneralLedgerFromBudget (Integer fiscalYear)
    {
        //  this method calls a series of steps that load the general ledger from the budget into
        //  the general ledger pending entry table.
        //  this method takes a fiscal year as input, but all that is required is that this object be
        //  a key labeling the bduget construction general ledger rows for the budget period to be loaded.  
        //  it need not be an actual fiscal year.
        //
        //  set the transaction date
        BC_GL_LOAD_TRANSACTION_DATE = SpringContext.getBean(DateTimeService.class).getCurrentSqlDate();
        //  initialize the sequence numbers
        HashMap<String,Integer> entrySequenceMap = entrySequenceNumber(fiscalYear);
    }
    
    /****************************************************************************************************************
     *                                  methods to do the actual load                                               *
     ****************************************************************************************************************/
    
    /**
     *   this method builds a hashmap containing the next entry sequence number to use for each document (document number)
     *     to be loaded from budget construction to the general ledger
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
     * @return intiliazed GeneralLedgerPendingEntry business object
     */
    
    private GeneralLedgerPendingEntry getNewPendingEntryWithDefaults(Integer requestYear)
    {
        GeneralLedgerPendingEntry newRow = new GeneralLedgerPendingEntry();
        newRow.setUniversityFiscalYear(requestYear);
        newRow.setTransactionLedgerEntryDescription(BCConstants.BC_TRN_LDGR_ENTR_DESC);
        newRow.setFinancialDocumentTypeCode(KFSConstants.BudgetConstructionConstants.BUDGET_CONSTRUCTION_DOCUMENT_TYPE);
        newRow.setFinancialDocumentApprovedCode(KFSConstants.PENDING_ENTRY_APPROVED_STATUS_CODE.APPROVED);
        newRow.setTransactionDate(BC_GL_LOAD_TRANSACTION_DATE);
        newRow.setTransactionEntryOffsetIndicator(false);
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
    
    private void setUpGeneralLedgerPendingEntry (GeneralLedgerPendingEntry newRow,
                                                 String BalanceType,
                                                 String FiscalPeriod)
    {
        
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
     *  this method gets a list of accounts that should not be loaded from the budget to the General Ledger
     *  @return hashset of accounts NOT to be loaded
     */
    
    private HashSet<String> getAccountsNotToBeLoaded()
    {
      HashSet<String> bannedAccounts;
      // list of subfunds which should not be loaded
      HashSet<String> bannedSubFunds = getSubFundsNotToBeLoaded();
      // query for load properties of accounts in the system
      ReportQueryByCriteria queryID = 
          new ReportQueryByCriteria(Account.class,org.apache.ojb.broker.query.ReportQueryByCriteria.CRITERIA_SELECT_ALL);
      queryID.setAttributes(new String[] {KFSPropertyConstants.ACCOUNT_NUMBER,
                                          KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE,
                                          KFSPropertyConstants.SUB_FUND_GROUP_CODE});
      bannedAccounts = new HashSet<String>(hashCapacity(queryID));
      // create a list of the accounts which should not be loaded
      Iterator accountProperties = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(queryID);
      while (accountProperties.hasNext())
      {
          Object[] selectListValues = (Object[]) accountProperties.next();
          //we will add an account/chart to the list if it has a no-load subfundgroup
          if (bannedSubFunds.contains((String) selectListValues[2]))
          {
            // hash content is account number concatenated with chart (the key of the chart of accounts table)  
             bannedAccounts.add(((String) selectListValues[0])+((String) selectListValues[1]));   
          }
      }
      return bannedAccounts;
    }
    
    /**
     *  @return  list of subfunds whose accounts will NOT be loaded
     */
     private HashSet<String> getSubFundsNotToBeLoaded ()
     {
       HashSet<String> bannedSubFunds;
       if (BCConstants.NO_BC_GL_LOAD_FUND_GROUPS.size() != 0)
       {
           // look for subfunds in the banned fund groups
           Criteria criteriaID = new Criteria();
           criteriaID.addIn(KFSPropertyConstants.FUND_GROUP_CODE,BCConstants.NO_BC_GL_LOAD_FUND_GROUPS);
           ReportQueryByCriteria queryID = new ReportQueryByCriteria(SubFundGroup.class,criteriaID);
           queryID.setAttributes(new String[]{KFSPropertyConstants.SUB_FUND_GROUP_CODE});
           // set the size of the hashset
           bannedSubFunds = new HashSet<String>(hashCapacity(queryID)+BCConstants.NO_BC_GL_LOAD_SUBFUND_GROUPS.size());
           Iterator subfundsForBannedFunds = 
               getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(queryID);
           // add the subfunds for the fund groups to be skipped to the hash set
           while (subfundsForBannedFunds.hasNext())
           {
              bannedSubFunds.add((String)((Object[]) subfundsForBannedFunds.next())[0]); 
           }
       }
       else
       {
           bannedSubFunds = new HashSet<String>(BCConstants.NO_BC_GL_LOAD_SUBFUND_GROUPS.size()+1);    
       }
       // now add the specific sub funds we don't want to the hash set
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
    
    public void unitTestRoutine(Integer LoadFiscalYear)
    {
        // 11/29/07testAccountElimination();
        // 11/29/07openAllAccountingPeriods(2010);
        // 11/30/08testSequenceNumbers(2008);
        //  we got a load exception--we have to initialize the date here
        BC_GL_LOAD_TRANSACTION_DATE = SpringContext.getBean(DateTimeService.class).getCurrentSqlDate();
        GeneralLedgerPendingEntry newRow = getNewPendingEntryWithDefaults(2008);
        LOG.warn(String.format("\nGeneralLedgerPendingEntry initialized without incident"));
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

}
