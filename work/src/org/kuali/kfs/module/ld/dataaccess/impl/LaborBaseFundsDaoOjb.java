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
package org.kuali.module.labor.dao.ojb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.core.dao.ojb.PlatformAwareDaoBaseOjb;
import org.kuali.kfs.KFSPropertyConstants;
import org.kuali.module.budget.bo.CalculatedSalaryFoundationTracker;
import org.kuali.module.gl.util.OJBUtility;
import org.kuali.module.gl.web.Constant;
import org.kuali.module.labor.LaborConstants;
import org.kuali.module.labor.bo.AccountStatusBaseFunds;
import org.kuali.module.labor.dao.LaborBaseFundsDao;
import org.kuali.module.labor.util.ConsolidationUtil;
import org.kuali.module.labor.util.ObjectUtil;

public class LaborBaseFundsDaoOjb extends PlatformAwareDaoBaseOjb implements LaborBaseFundsDao {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(LaborBaseFundsDaoOjb.class);

    /**
     * @see org.kuali.module.labor.dao.LaborBaseFundsDao#findCSFTrackers(java.util.Map, boolean)
     */
    public List<CalculatedSalaryFoundationTracker> findCSFTrackers(Map fieldValues, boolean isConsolidated) {

        Iterator<Object[]> queryResults = this.findCSFTrackerRawData(fieldValues, isConsolidated);

        // unmarshal CalculatedSalaryFoundationTracker from the query results
        List<CalculatedSalaryFoundationTracker> CSFCollection = new ArrayList<CalculatedSalaryFoundationTracker>();
        while (queryResults != null && queryResults.hasNext()) {
            CSFCollection.add(this.unmarshalCSFTracker(queryResults.next()));
        }
        return CSFCollection;
    }

    /**
     * @see org.kuali.module.labor.dao.LaborBaseFundsDao#findCSFTrackersAsAccountStatusBaseFunds(java.util.Map, boolean)
     */
    public List<AccountStatusBaseFunds> findCSFTrackersAsAccountStatusBaseFunds(Map fieldValues, boolean isConsolidated) {
        Iterator<Object[]> queryResults = this.findCSFTrackerRawData(fieldValues, isConsolidated);

        // unmarshal CalculatedSalaryFoundationTracker from the query results and convert to AccountStatusBaseFunds
        List<AccountStatusBaseFunds> BaseFundsCollection = new ArrayList<AccountStatusBaseFunds>();
        while (queryResults != null && queryResults.hasNext()) {
            BaseFundsCollection.add(this.unmarshalCSFTrackerAsAccountStatusBaseFunds(queryResults.next()));
        }
        return BaseFundsCollection;
    }

    /**
     * @see org.kuali.module.labor.dao.LaborBaseFundsDao#findLaborBaseFunds(java.util.Map, boolean)
     */
    public List<AccountStatusBaseFunds> findLaborBaseFunds(Map fieldValues, boolean isConsolidated) {
        Iterator<Object[]> queryResults = this.findLaborBaseFundsRawData(fieldValues, isConsolidated);

        // unmarshal AccountStatusBaseFunds from the query results
        List<AccountStatusBaseFunds> BaseFundsCollection = new ArrayList<AccountStatusBaseFunds>();
        while (queryResults != null && queryResults.hasNext()) {
            BaseFundsCollection.add(this.unmarshalAccountStatusBaseFunds(queryResults.next()));
        }
        return BaseFundsCollection;
    }

    // get the CSF trackers according to the given criteria
    private Iterator<Object[]> findCSFTrackerRawData(Map fieldValues, boolean isConsolidated) {

        Criteria tempCriteria1 = new Criteria();
        tempCriteria1.addEqualTo(KFSPropertyConstants.CSF_DELETE_CODE, LaborConstants.DASHES_DELETE_CODE);

        Criteria tempCriteria2 = new Criteria();
        tempCriteria2.addIsNull(KFSPropertyConstants.CSF_DELETE_CODE);

        /* KFSPropertyConstants.CSF_DELETE_CODE = "-" OR is null */
        tempCriteria2.addOrCriteria(tempCriteria1);

        Criteria criteria = OJBUtility.buildCriteriaFromMap(fieldValues, new CalculatedSalaryFoundationTracker());
        criteria.addAndCriteria(tempCriteria2);

        ReportQueryByCriteria query = QueryFactory.newReportQuery(CalculatedSalaryFoundationTracker.class, criteria);

        List<String> groupByList = getGroupByList(isConsolidated);
        String[] groupBy = (String[]) groupByList.toArray(new String[groupByList.size()]);
        query.addGroupBy(groupBy);

        List<String> getAttributeList = getAttributeListForCSFTracker(isConsolidated, false);
        String[] attributes = (String[]) getAttributeList.toArray(new String[getAttributeList.size()]);
        query.setAttributes(attributes);

        return getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(query);
    }

    // get the labor base funds according to the given criteria
    private Iterator<Object[]> findLaborBaseFundsRawData(Map fieldValues, boolean isConsolidated) {
        Criteria criteria = OJBUtility.buildCriteriaFromMap(fieldValues, new AccountStatusBaseFunds());
        criteria.addEqualTo(KFSPropertyConstants.FINANCIAL_BALANCE_TYPE_CODE, LaborConstants.BalanceInquiries.BALANCE_CODE);

        criteria.addEqualToField(KFSPropertyConstants.LABOR_OBJECT + "." + KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR, KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR);
        criteria.addEqualToField(KFSPropertyConstants.LABOR_OBJECT + "." + KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE, KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE);
        criteria.addEqualToField(KFSPropertyConstants.LABOR_OBJECT + "." + KFSPropertyConstants.FINANCIAL_OBJECT_CODE, KFSPropertyConstants.FINANCIAL_OBJECT_CODE);
        
        // this statement is used to force OJB to join LABOR_OBJECT and GL_BALANCE tables
        criteria.addNotNull(KFSPropertyConstants.LABOR_OBJECT + "." + KFSPropertyConstants.FINANCIAL_OBJECT_FRINGE_OR_SALARY_CODE);
        
        ReportQueryByCriteria query = QueryFactory.newReportQuery(AccountStatusBaseFunds.class, criteria);

        List<String> groupByList = getGroupByList(isConsolidated);
        String[] groupBy = (String[]) groupByList.toArray(new String[groupByList.size()]);
        query.addGroupBy(groupBy);

        List<String> getAttributeList = getAttributeListForBaseFunds(isConsolidated, false);
        String[] attributes = (String[]) getAttributeList.toArray(new String[getAttributeList.size()]);
        query.setAttributes(attributes);

        return getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(query);
    }

    // unmarshal CalculatedSalaryFoundationTracker from the query result
    private CalculatedSalaryFoundationTracker unmarshalCSFTracker(Object[] queryResult) {
        CalculatedSalaryFoundationTracker CSFTracker = new CalculatedSalaryFoundationTracker();
        List<String> keyFields = this.getAttributeListForCSFTracker(false, true);

        ObjectUtil.buildObject(CSFTracker, queryResult, keyFields);
        return CSFTracker;
    }

    // unmarshal CalculatedSalaryFoundationTracker from the query results and convert to AccountStatusBaseFunds
    private AccountStatusBaseFunds unmarshalCSFTrackerAsAccountStatusBaseFunds(Object[] queryResult) {
        AccountStatusBaseFunds baseFunds = new AccountStatusBaseFunds();
        List<String> keyFields = this.getAttributeListForCSFTracker(false, true);

        ObjectUtil.buildObject(baseFunds, queryResult, keyFields);
        return baseFunds;
    }

    // unmarshal AccountStatusBaseFunds from the query result
    private AccountStatusBaseFunds unmarshalAccountStatusBaseFunds(Object[] queryResult) {
        AccountStatusBaseFunds baseFunds = new AccountStatusBaseFunds();
        List<String> keyFields = this.getAttributeListForBaseFunds(false, true);

        ObjectUtil.buildObject(baseFunds, queryResult, keyFields);
        return baseFunds;
    }

    private List<String> getGroupByList(boolean isConsolidated) {
        List<String> groupByList = new ArrayList<String>();
        groupByList.add(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR);
        groupByList.add(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE);
        groupByList.add(KFSPropertyConstants.ACCOUNT_NUMBER);
        groupByList.add(KFSPropertyConstants.FINANCIAL_OBJECT_CODE);

        if (!isConsolidated) {
            groupByList.add(KFSPropertyConstants.SUB_ACCOUNT_NUMBER);
            groupByList.add(KFSPropertyConstants.FINANCIAL_SUB_OBJECT_CODE);
        }
        return groupByList;
    }

    private List<String> getAttributeList(boolean isConsolidated) {
        List<String> attributeList = getGroupByList(isConsolidated);

        if (isConsolidated) {
            attributeList.add("'" + Constant.CONSOLIDATED_SUB_ACCOUNT_NUMBER + "'");
            attributeList.add("'" + Constant.CONSOLIDATED_SUB_OBJECT_CODE + "'");
        }
        return attributeList;
    }

    private List<String> getAttributeListForCSFTracker(boolean isConsolidated, boolean isAttributeNameNeeded) {
        List<String> attributeList = getAttributeList(isConsolidated);

        if (!isAttributeNameNeeded) {
            attributeList.add(ConsolidationUtil.sum(KFSPropertyConstants.CSF_AMOUNT));
        }
        else {
            attributeList.add(KFSPropertyConstants.CSF_AMOUNT);
        }
        return attributeList;
    }

    private List<String> getAttributeListForBaseFunds(boolean isConsolidated, boolean isAttributeNameNeeded) {
        List<String> attributeList = getAttributeList(isConsolidated);

        if (!isAttributeNameNeeded) {
            attributeList.add(ConsolidationUtil.sum(KFSPropertyConstants.ACCOUNTING_LINE_ANNUAL_BALANCE_AMOUNT));
            attributeList.add(ConsolidationUtil.sum(KFSPropertyConstants.FINANCIAL_BEGINNING_BALANCE_LINE_AMOUNT));
            attributeList.add(ConsolidationUtil.sum(KFSPropertyConstants.CONTRACTS_GRANTS_BEGINNING_BALANCE_AMOUNT));
        }
        else {
            attributeList.add(KFSPropertyConstants.ACCOUNTING_LINE_ANNUAL_BALANCE_AMOUNT);
            attributeList.add(KFSPropertyConstants.FINANCIAL_BEGINNING_BALANCE_LINE_AMOUNT);
            attributeList.add(KFSPropertyConstants.CONTRACTS_GRANTS_BEGINNING_BALANCE_AMOUNT);
        }
        return attributeList;
    }
}
