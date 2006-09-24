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

import java.util.List;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.module.gl.bo.CorrectionCriteria;
import org.kuali.module.gl.dao.CorrectionCriteriaDao;
import org.springframework.orm.ojb.support.PersistenceBrokerDaoSupport;

public class CorrectionCriteriaDaoOjb extends PersistenceBrokerDaoSupport implements CorrectionCriteriaDao {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CorrectionCriteriaDaoOjb.class);

    /**
     * 
     * @see org.kuali.module.gl.dao.CorrectionCriteriaDao#delete(org.kuali.module.gl.bo.CorrectionCriteria)
     */
    public void delete(CorrectionCriteria criterion) {
        LOG.debug("delete() started");

        getPersistenceBrokerTemplate().delete(criterion);
    }

    /**
     * 
     * @see org.kuali.module.gl.dao.CorrectionCriteriaDao#findByDocumentNumberAndCorrectionGroupNumber(java.lang.String, java.lang.Integer)
     */
    public List findByDocumentNumberAndCorrectionGroupNumber(String documentNumber, Integer correctionGroupLineNumber) {
        LOG.debug("findByDocumentNumberAndCorrectionGroupNumber() started");

        Criteria criteria = new Criteria();
        criteria.addEqualTo("financialDocumentNumber", documentNumber);
        criteria.addEqualTo("correctionChangeGroupLineNumber", correctionGroupLineNumber);

        Class clazz = CorrectionCriteria.class;
        QueryByCriteria query = QueryFactory.newQuery(clazz, criteria);

        List returnList = (List) getPersistenceBrokerTemplate().getCollectionByQuery(query);
        return returnList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.kuali.module.gl.dao.CorrectionCriteriaDao#save(org.kuali.module.gl.bo.CorrectionCriteria)
     */
    public void save(CorrectionCriteria criterion) {
        LOG.debug("save() started");

        getPersistenceBrokerTemplate().store(criterion);
    }
}
