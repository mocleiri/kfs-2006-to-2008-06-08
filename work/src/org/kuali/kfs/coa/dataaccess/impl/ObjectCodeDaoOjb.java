/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/work/src/org/kuali/kfs/coa/dataaccess/impl/ObjectCodeDaoOjb.java,v $
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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.core.dao.ojb.PlatformAwareDaoBaseOjb;
import org.kuali.module.chart.bo.ObjectCode;
import org.kuali.module.chart.dao.ObjectCodeDao;


/**
 * This class is the OJB implementation of the ObjectCodeDao interface.
 */
public class ObjectCodeDaoOjb extends PlatformAwareDaoBaseOjb implements ObjectCodeDao {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ObjectCodeDaoOjb.class);

    /**
     * Retrieves object code business object by primary key
     * 
     * @param universityFiscalYear - part of composite key
     * @param chartOfAccountsCode - part of composite key
     * @param financialObjectCode - part of composite key
     * @return ObjectCode
     * @see ObjectCodeDao
     */
    public ObjectCode getByPrimaryId(Integer universityFiscalYear, String chartOfAccountsCode, String financialObjectCode) {
        Criteria criteria = new Criteria();
        criteria.addEqualTo("universityFiscalYear", universityFiscalYear);
        criteria.addEqualTo("chartOfAccountsCode", chartOfAccountsCode);
        criteria.addEqualTo("financialObjectCode", financialObjectCode);

        return (ObjectCode) getPersistenceBrokerTemplate().getObjectByQuery(QueryFactory.newQuery(ObjectCode.class, criteria));
    }

    public List getYearList(String chartOfAccountsCode, String financialObjectCode) {

        List returnList = new ArrayList();
        Criteria criteria = new Criteria();
        criteria.addEqualTo("chartOfAccountsCode", chartOfAccountsCode);
        criteria.addEqualTo("financialObjectCode", financialObjectCode);
        Collection years = getPersistenceBrokerTemplate().getCollectionByQuery(QueryFactory.newQuery(ObjectCode.class, criteria));
        for (Iterator iter = years.iterator(); iter.hasNext();) {
            ObjectCode o = (ObjectCode) iter.next();
            if (o != null) {
                returnList.add(o.getUniversityFiscalYear());
            }
        }
        return returnList;


    }
}
