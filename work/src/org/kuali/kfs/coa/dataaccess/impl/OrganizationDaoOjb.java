/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/work/src/org/kuali/kfs/coa/dataaccess/impl/OrganizationDaoOjb.java,v $
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
package org.kuali.module.chart.dao.ojb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.Org;
import org.kuali.module.chart.dao.OrganizationDao;
import org.springframework.orm.ojb.support.PersistenceBrokerDaoSupport;

/**
 * This class is the OJB implementation of the OrganizationDao interface.
 * 
 * 
 */
public class OrganizationDaoOjb extends PersistenceBrokerDaoSupport implements OrganizationDao {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(OrganizationDaoOjb.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.kuali.dao.OrganizationDao#getByPrimaryId(java.lang.String, java.lang.String)
     */
    public Org getByPrimaryId(String chartOfAccountsCode, String organizationCode) {
        Criteria criteria = new Criteria();
        criteria.addEqualTo("chartOfAccountsCode", chartOfAccountsCode);
        criteria.addEqualTo("organizationCode", organizationCode);

        return (Org) getPersistenceBrokerTemplate().getObjectByQuery(QueryFactory.newQuery(Org.class, criteria));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.kuali.dao.OrganizationDao#save(org.kuali.bo.Org)
     */
    public void save(Org organization) {
        getPersistenceBrokerTemplate().store(organization);
    }

    /**
     * 
     * @see org.kuali.module.chart.dao.OrganizationDao#getActiveAccountsByOrg(java.lang.String, java.lang.String)
     */
    public List getActiveAccountsByOrg(String chartOfAccountsCode, String organizationCode) {

        List accounts = new ArrayList();

        Criteria criteria = new Criteria();
        criteria.addEqualTo("chartOfAccountsCode", chartOfAccountsCode);
        criteria.addEqualTo("organizationCode", organizationCode);
        criteria.addEqualTo("accountClosedIndicator", Boolean.FALSE);

        accounts = (List) getPersistenceBrokerTemplate().getCollectionByQuery(QueryFactory.newQuery(Account.class, criteria));

        if (accounts.isEmpty() || accounts.size() == 0) {
            return Collections.EMPTY_LIST;
        }
        return accounts;
    }

    /**
     * 
     * @see org.kuali.module.chart.dao.OrganizationDao#getActiveChildOrgs(java.lang.String, java.lang.String)
     */
    public List getActiveChildOrgs(String chartOfAccountsCode, String organizationCode) {

        List orgs = new ArrayList();

        Criteria criteria = new Criteria();
        criteria.addEqualTo("reportsToChartOfAccountsCode", chartOfAccountsCode);
        criteria.addEqualTo("reportsToOrganizationCode", organizationCode);
        criteria.addEqualTo("organizationActiveIndicator", Boolean.TRUE);

        orgs = (List) getPersistenceBrokerTemplate().getCollectionByQuery(QueryFactory.newQuery(Org.class, criteria));

        if (orgs.isEmpty() || orgs.size() == 0) {
            return Collections.EMPTY_LIST;
        }
        return orgs;
    }

}
