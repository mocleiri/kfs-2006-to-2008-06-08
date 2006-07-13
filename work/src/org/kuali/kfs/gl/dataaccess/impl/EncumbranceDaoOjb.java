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

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.PropertyConstants;
import org.kuali.module.gl.bo.AccountBalance;
import org.kuali.module.gl.bo.Encumbrance;
import org.kuali.module.gl.bo.Transaction;
import org.kuali.module.gl.dao.EncumbranceDao;
import org.kuali.module.gl.util.BusinessObjectHandler;
import org.springframework.orm.ojb.support.PersistenceBrokerDaoSupport;

/**
 * @author Kuali General Ledger Team <kualigltech@oncourse.iu.edu>
 * @version $Id: EncumbranceDaoOjb.java,v 1.10 2006-07-13 21:20:44 larevans Exp $
 */
public class EncumbranceDaoOjb extends PersistenceBrokerDaoSupport implements EncumbranceDao {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(EncumbranceDaoOjb.class);

    public EncumbranceDaoOjb() {
        super();
    }

    /**
     * @see org.kuali.module.gl.dao.EncumbranceDao#getEncumbranceByTransaction(org.kuali.module.gl.bo.Transaction)
     */
    public Encumbrance getEncumbranceByTransaction(Transaction t) {
        LOG.debug("getEncumbranceByTransaction() started");

        Criteria crit = new Criteria();
        crit.addEqualTo("universityFiscalYear", t.getUniversityFiscalYear());
        crit.addEqualTo("chartOfAccountsCode", t.getChartOfAccountsCode());
        crit.addEqualTo("accountNumber", t.getAccountNumber());
        crit.addEqualTo("subAccountNumber", t.getSubAccountNumber());
        crit.addEqualTo("objectCode", t.getFinancialObjectCode());
        crit.addEqualTo("subObjectCode", t.getFinancialSubObjectCode());
        crit.addEqualTo("balanceTypeCode", t.getFinancialBalanceTypeCode());
        crit.addEqualTo("documentTypeCode", t.getFinancialDocumentTypeCode());
        crit.addEqualTo("originCode", t.getFinancialSystemOriginationCode());
        crit.addEqualTo("documentNumber", t.getFinancialDocumentNumber());

        QueryByCriteria qbc = QueryFactory.newQuery(Encumbrance.class, crit);
        return (Encumbrance) getPersistenceBrokerTemplate().getObjectByQuery(qbc);
    }

    /**
     * @see org.kuali.module.gl.dao.EncumbranceDao#getEncumbrancesToClose(java.lang.Integer)
     */
    public Iterator getEncumbrancesToClose(Integer fiscalYear) {

        Criteria criteria = new Criteria();
        criteria.addEqualTo("universityFiscalYear", fiscalYear);

        QueryByCriteria query = new QueryByCriteria(Encumbrance.class, criteria);
        query.addOrderByAscending("chartOfAccountsCode");
        query.addOrderByAscending("accountNumber");
        query.addOrderByAscending("subAccountNumber");
        query.addOrderByAscending("objectCode");
        query.addOrderByAscending("subObjectCode");
        query.addOrderByAscending("balanceTypeCode");

        return getPersistenceBrokerTemplate().getIteratorByQuery(query);
    }

    /**
     * @see org.kuali.module.gl.dao.EncumbranceDao#save(org.kuali.module.gl.bo.Encumbrance)
     */
    public void save(Encumbrance e) {
        LOG.debug("save() started");

        getPersistenceBrokerTemplate().store(e);
    }

    /**
     * @see org.kuali.module.gl.dao.EncumbranceDao#purgeYearByChart(java.lang.String, int)
     */
    public void purgeYearByChart(String chartOfAccountsCode, int year) {
        LOG.debug("purgeYearByChart() started");

        Criteria criteria = new Criteria();
        criteria.addEqualTo("chartOfAccountsCode", chartOfAccountsCode);
        criteria.addLessThan("universityFiscalYear", new Integer(year));

        getPersistenceBrokerTemplate().deleteByQuery(new QueryByCriteria(Encumbrance.class, criteria));

        // This is required because if any deleted account balances are in the cache, deleteByQuery
        // doesn't
        // remove them from the cache so a future select will retrieve these deleted account
        // balances from
        // the cache and return them. Clearing the cache forces OJB to go to the database again.
        getPersistenceBrokerTemplate().clearCache();
    }

    /**
     * @see org.kuali.module.gl.dao.EncumbranceDao#getAllEncumbrances()
     */
    public Iterator getAllEncumbrances() {
        Criteria criteria = new Criteria();
        QueryByCriteria query = QueryFactory.newQuery(Encumbrance.class, criteria);
        return getPersistenceBrokerTemplate().getIteratorByQuery(query);
    }

    /**
     * @see org.kuali.module.gl.dao.EncumbranceDao#getSummarizedEncumbrances(String, boolean)
     */
    public Iterator getSummarizedEncumbrances(String documentTypeCode, boolean included) {
        Criteria criteria = new Criteria();

        if (included) {
            criteria.addEqualTo(PropertyConstants.DOCUMENT_TYPE_CODE, documentTypeCode);
        }
        else {
            criteria.addNotEqualTo(PropertyConstants.DOCUMENT_TYPE_CODE, documentTypeCode);
        }

        ReportQueryByCriteria query = QueryFactory.newReportQuery(Encumbrance.class, criteria);

        // set the selection attributes
        List attributeList = buildAttributeList();
        String[] attributes = (String[]) attributeList.toArray(new String[attributeList.size()]);
        query.setAttributes(attributes);

        // add the group criteria into the selection statement
        List groupByList = buildGroupByList();
        String[] groupBy = (String[]) groupByList.toArray(new String[groupByList.size()]);
        query.addGroupBy(groupBy);

        return getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(query);
    }
    
    /**
     * @see org.kuali.module.gl.dao.EncumbranceDao#findOpenEncumbrance(java.util.Map)
     */
    public Iterator findOpenEncumbrance(Map fieldValues) {
        LOG.debug("findOpenEncumbrance() started");

        Criteria criteria = BusinessObjectHandler.buildCriteriaFromMap(fieldValues, new Encumbrance());
        QueryByCriteria query = QueryFactory.newReportQuery(Encumbrance.class, criteria);
        
        return getPersistenceBrokerTemplate().getIteratorByQuery(query);
    }

    /**
     * This method builds the atrribute list used by balance searching
     */
    private List buildAttributeList() {
        List attributeList = this.buildGroupByList();

        attributeList.add("sum(" + PropertyConstants.ACCOUNT_LINE_ENCUMBRANCE_AMOUNT + ")");
        attributeList.add("sum(" + PropertyConstants.ACCOUNT_LINE_ENCUMBRANCE_CLOSED_AMOUNT + ")");

        return attributeList;
    }

    /**
     * This method builds group by attribute list used by balance searching
     */
    private List buildGroupByList() {
        List attributeList = new ArrayList();

        attributeList.add(PropertyConstants.UNIVERSITY_FISCAL_YEAR);
        attributeList.add(PropertyConstants.CHART_OF_ACCOUNTS_CODE);
        attributeList.add(PropertyConstants.ACCOUNT_NUMBER);
        attributeList.add(PropertyConstants.SUB_ACCOUNT_NUMBER);
        attributeList.add(PropertyConstants.OBJECT_CODE);
        attributeList.add(PropertyConstants.SUB_OBJECT_CODE);
        attributeList.add(PropertyConstants.BALANCE_TYPE_CODE);

        return attributeList;
    }
}