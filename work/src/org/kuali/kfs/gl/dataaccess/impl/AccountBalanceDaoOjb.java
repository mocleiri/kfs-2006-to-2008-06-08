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
package org.kuali.module.gl.dao.ojb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

import org.kuali.module.gl.bo.AccountBalance;
import org.kuali.module.gl.bo.Balance;
import org.kuali.module.gl.bo.Transaction;
import org.kuali.module.gl.dao.AccountBalanceDao;

import org.springframework.orm.ojb.support.PersistenceBrokerDaoSupport;

/**
 * @author jsissom
 *  
 */
public class AccountBalanceDaoOjb extends PersistenceBrokerDaoSupport implements AccountBalanceDao {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(AccountBalanceDaoOjb.class);

    public AccountBalanceDaoOjb() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.kuali.module.gl.dao.AccountBalanceDao#getByTransaction(org.kuali.module.gl.bo.Transaction)
     */
    public AccountBalance getByTransaction(Transaction t) {
        LOG.debug("getByTransaction() started");

        Criteria crit = new Criteria();
        crit.addEqualTo("universityFiscalYear", t.getUniversityFiscalYear());
        crit.addEqualTo("chartOfAccountsCode", t.getChartOfAccountsCode());
        crit.addEqualTo("accountNumber", t.getAccountNumber());
        crit.addEqualTo("subAccountNumber", t.getSubAccountNumber());
        crit.addEqualTo("objectCode", t.getFinancialObjectCode());
        crit.addEqualTo("subObjectCode", t.getFinancialSubObjectCode());

        QueryByCriteria qbc = QueryFactory.newQuery(AccountBalance.class, crit);
        return (AccountBalance) getPersistenceBrokerTemplate().getObjectByQuery(qbc);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.kuali.module.gl.dao.AccountBalanceDao#save(org.kuali.module.gl.bo.AccountBalance)
     */
    public void save(AccountBalance ab) {
        LOG.debug("save() started");

        getPersistenceBrokerTemplate().store(ab);
    }

    /**
     * @see org.kuali.module.gl.dao.AccountBalanceDao#findAvailableAccountBalance(java.util.Map, boolean)
     */
    public Iterator findAvailableAccountBalance(Map fieldValues, boolean isConsolidated) {

        Criteria criteria = buildCriteriaFromMap(fieldValues, new AccountBalance());
        ReportQueryByCriteria query = QueryFactory.newReportQuery(AccountBalance.class, criteria);

        List attributeList = buildAttributeList(false);
        List groupByList = buildGroupByList(false);

        if (isConsolidated) {
            attributeList.remove("subAccountNumber");
            groupByList.remove("subAccountNumber");

            // set the selection attributes
            String[] attributes = (String[]) attributeList.toArray(new String[attributeList.size()]);
            query.setAttributes(attributes);

            // add the group criteria into the selection statement
            String[] groupBy = (String[]) groupByList.toArray(new String[groupByList.size()]);
            query.addGroupBy(groupBy);
        }

        return getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(query);
    }

    /**
     * @see org.kuali.module.gl.dao.AccountBalanceDao#findAccountBalanceByConsolidation(java.util.Map, boolean, boolean)
     */
    public Iterator findAccountBalanceByConsolidation(Map fieldValues, boolean isCostShareInclusive, boolean isConsolidated) {
        
        Criteria criteria = buildCriteriaFromMap(fieldValues, new AccountBalance());
        
        // join account balance and object code tables
        criteria.addEqualToField("universityFiscalYear", "financialObject.universityFiscalYear");
        criteria.addEqualToField("chartOfAccountsCode", "financialObject.chartOfAccountsCode");
        criteria.addEqualToField("objectCode", "financialObject.financialObjectCode");

        // join object code and object type code tables
        criteria.addEqualToField("financialObject.financialObjectTypeCode", 
                "financialObject.financialObjectType.code");
        criteria.addIn("financialObject.financialObjectTypeCode", this.buildObjectTypeCodeList());
        
        // join object code and object level tables
        criteria.addEqualToField("financialObject.chartOfAccountsCode", 
                "financialObject.financialObjectLevel.chartOfAccountsCode");
        criteria.addEqualToField("financialObject.financialObjectLevelCode", 
                "financialObject.financialObjectLevel.financialObjectLevelCode"); 
        
        // join object level and object consolidation tables
        criteria.addEqualToField("financialObject.financialObjectLevel.chartOfAccountsCode", 
                "financialObject.financialObjectLevel.financialConsolidationObject.chartOfAccountsCode");
        criteria.addEqualToField("financialObject.financialObjectLevel.finConsolidationObjectCode",
                "financialObject.financialObjectLevel.financialConsolidationObject.finConsolidationObjectCode");
        
        // exclude the entries whose subaccount type code is cost share
        if(!isCostShareInclusive){
            criteria.addEqualToField("chartOfAccountsCode", "a21SubAccount.chartOfAccountsCode");
            criteria.addEqualToField("accountNumber", "a21SubAccount.accountNumber");
            criteria.addEqualToField("subAccountNumber", "a21SubAccount.subAccountNumber");
            criteria.addNotEqualTo("a21SubAccount.subAccountTypeCode", "CS");
        }
        
        ReportQueryByCriteria query = QueryFactory.newReportQuery(AccountBalance.class, criteria);
        
        List attributeList = buildAttributeList(true);
        List groupByList = buildGroupByList(true);
        
        // remove the attributes and grouping attributes that will not be get involved into oncoming query
        attributeList.remove("objectCode");
        groupByList.remove("objectCode");        
        attributeList.remove("financialObject.financialObjectTypeCode");
        groupByList.remove("financialObject.financialObjectTypeCode");        
        
        if(isConsolidated){
            attributeList.remove("subAccountNumber");
            groupByList.remove("subAccountNumber");
        }
        
        // set the selection attributes
        String[] attributes = (String[]) attributeList.toArray(new String[attributeList.size()]);
        query.setAttributes(attributes);

        // add the group criteria into the selection statement
        String[] groupBy = (String[]) groupByList.toArray(new String[groupByList.size()]);
        query.addGroupBy(groupBy);

        return getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(query);
    }
    /**
     * This method builds the query criteria based on the input field map
     * 
     * @param fieldValues
     * @param accountBalance
     * @return a query criteria
     */
    private Criteria buildCriteriaFromMap(Map fieldValues, AccountBalance accountBalance) {
        Criteria criteria = new Criteria();

        Iterator propsIter = fieldValues.keySet().iterator();
        while (propsIter.hasNext()) {
            String propertyName = (String) propsIter.next();
            String propertyValue = (String) fieldValues.get(propertyName);

            // if searchValue is empty and the key is not a valid property ignore
            if (StringUtils.isBlank(propertyValue) || !(PropertyUtils.isWriteable(accountBalance, propertyName))) {
                continue;
            }

            criteria.addEqualTo(propertyName, propertyValue);
        }
        return criteria;
    }

    /**
     * This method builds the atrribute list used by balance searching
     * 
     * @param isExtended
     * @return List an attribute list
     */
    private List buildAttributeList(boolean isExtended) {
        List attributeList = new ArrayList();

        attributeList.add("universityFiscalYear");
        attributeList.add("chartOfAccountsCode");
        attributeList.add("accountNumber");
        attributeList.add("subAccountNumber");

        attributeList.add("objectCode");
        attributeList.add("financialObject.financialObjectTypeCode");

        if (isExtended) {
            attributeList.add("financialObject.financialObjectLevel.financialConsolidationObject.financialReportingSortCode");
            attributeList.add("financialObject.financialObjectType.financialReportingSortCode");
            attributeList.add("financialObject.financialObjectLevel.financialConsolidationObjectCode");
        }

        attributeList.add("sum(currentBudgetLineBalanceAmount)");
        attributeList.add("sum(accountLineActualsBalanceAmount)");
        attributeList.add("sum(accountLineEncumbranceBalanceAmount)");

        return attributeList;
    }

    /**
     * This method builds group by attribute list used by balance searching
     * 
     * @return List an group by attribute list
     */
    private List buildGroupByList(boolean isExtended) {
        List attributeList = new ArrayList();

        attributeList.add("universityFiscalYear");
        attributeList.add("chartOfAccountsCode");
        attributeList.add("accountNumber");
        attributeList.add("subAccountNumber");

        attributeList.add("objectCode");
        attributeList.add("financialObject.financialObjectTypeCode");

        if (isExtended) {
            attributeList.add("financialObject.financialObjectLevel.financialConsolidationObject.financialReportingSortCode");
            attributeList.add("financialObject.financialObjectType.financialReportingSortCode");
            attributeList.add("financialObject.financialObjectLevel.financialConsolidationObjectCode");
        }

        return attributeList;
    }

    /**
     * This method builds an object type code list
     * 
     * @return List an object type code list
     */
    private List buildObjectTypeCodeList() {
        List objectTypeCodeList = new ArrayList();

        objectTypeCodeList.add("EE");
        objectTypeCodeList.add("EX");
        objectTypeCodeList.add("ES");
        objectTypeCodeList.add("IN");
        objectTypeCodeList.add("IC");
        objectTypeCodeList.add("CH");

        return objectTypeCodeList;
    }
}