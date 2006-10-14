/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/work/src/org/kuali/kfs/coa/dataaccess/impl/OrganizationReversionDaoOjb.java,v $
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

import java.util.List;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.module.chart.bo.OrganizationReversion;
import org.kuali.module.chart.bo.OrganizationReversionCategory;
import org.kuali.module.chart.dao.OrganizationReversionDao;
import org.springframework.orm.ojb.support.PersistenceBrokerDaoSupport;

/**
 * 
 */
public class OrganizationReversionDaoOjb extends PersistenceBrokerDaoSupport implements OrganizationReversionDao {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(OrganizationReversionDaoOjb.class);

    /**
     * @see org.kuali.module.chart.dao.OrganizationReversionDao#getByPrimaryId(java.lang.Integer, java.lang.String,
     *      java.lang.String)
     */
    public OrganizationReversion getByPrimaryId(Integer universityFiscalYear, String financialChartOfAccountsCode, String organizationCode) {
        LOG.debug("getByPrimaryId() started");

        Criteria criteria = new Criteria();
        criteria.addEqualTo("universityFiscalYear", universityFiscalYear);
        criteria.addEqualTo("chartOfAccountsCode", financialChartOfAccountsCode);
        criteria.addEqualTo("organizationCode", organizationCode);

        return (OrganizationReversion) getPersistenceBrokerTemplate().getObjectByQuery(QueryFactory.newQuery(OrganizationReversion.class, criteria));
    }

    public List<OrganizationReversionCategory> getCategories() {
        LOG.debug("getCategories() started");

        Criteria criteria = new Criteria();
        QueryByCriteria q = QueryFactory.newQuery(OrganizationReversionCategory.class, criteria);
        q.addOrderByAscending("organizationReversionSortCode");

        return (List) getPersistenceBrokerTemplate().getCollectionByQuery(q);
    }
}
