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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.core.lookup.LookupUtils;
import org.kuali.module.budget.bo.CalculatedSalaryFoundationTracker;
import org.kuali.module.gl.bo.AccountBalance;
import org.kuali.module.gl.util.OJBUtility;
import org.kuali.module.labor.bo.AccountStatus;
import org.kuali.module.labor.bo.BalanceByGeneralLedgerKey;
import org.kuali.module.labor.dao.LaborDao;
import org.springmodules.orm.ojb.support.PersistenceBrokerDaoSupport;

/**
 * This class is a facade for Labor Distribution DAO balance inquiries
 */
public class LaborDaoOjb extends PersistenceBrokerDaoSupport implements LaborDao {
    private LaborDaoOjb dao;

    /**
     * 
     * @see org.kuali.module.labor.dao.LaborDao#getCSFTrackerData(java.util.Map)
     */
    public Collection getCSFTrackerData(Map fieldValues) {
        Criteria criteria = new Criteria();
        criteria.addAndCriteria(OJBUtility.buildCriteriaFromMap(fieldValues, new CalculatedSalaryFoundationTracker()));
        LookupUtils.applySearchResultsLimit(criteria);
        QueryByCriteria query = QueryFactory.newQuery(CalculatedSalaryFoundationTracker.class, criteria);
        return getPersistenceBrokerTemplate().getCollectionByQuery(query);
    }

    /**
     * 
     * @see org.kuali.module.labor.dao.LaborDao#getCurrentYearFunds(java.util.Map)
     */
    public Collection getCurrentYearFunds(Map fieldValues) {
        Criteria criteria = new Criteria();
        criteria.addAndCriteria(OJBUtility.buildCriteriaFromMap(fieldValues, new BalanceByGeneralLedgerKey()));
        LookupUtils.applySearchResultsLimit(criteria);
        QueryByCriteria query = QueryFactory.newQuery(BalanceByGeneralLedgerKey.class, criteria);
        return getPersistenceBrokerTemplate().getCollectionByQuery(query);
    }

    /**
     * 
     * @see org.kuali.module.labor.dao.LaborDao#getBaseFunds(java.util.Map)
     */
    public Collection getBaseFunds(Map fieldValues) {
        Criteria criteria = new Criteria();
        criteria.addAndCriteria(OJBUtility.buildCriteriaFromMap(fieldValues, new AccountStatus()));
        LookupUtils.applySearchResultsLimit(criteria);
        QueryByCriteria query = QueryFactory.newQuery(AccountStatus.class, criteria);
        return getPersistenceBrokerTemplate().getCollectionByQuery(query);
    }    
}