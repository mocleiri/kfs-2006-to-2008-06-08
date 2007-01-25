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

import java.math.BigDecimal;
import java.util.Iterator;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.PropertyConstants;
import org.kuali.module.gl.bo.Entry;
import org.kuali.module.labor.bo.LedgerEntry;
import org.kuali.module.labor.dao.LaborLedgerEntryDao;
import org.springmodules.orm.ojb.support.PersistenceBrokerDaoSupport;

/**
 * This class...
 */
public class LaborLedgerEntryDaoOjb extends PersistenceBrokerDaoSupport implements LaborLedgerEntryDao {

    /**
     * @see org.kuali.module.labor.dao.LaborLedgerEntryDao#getMaxSquenceNumber(org.kuali.module.labor.bo.LedgerEntry)
     */
    public Integer getMaxSquenceNumber(LedgerEntry ledgerEntry) {
        //TODO: this is a piece of duplicate code. We need to refactor it later
        Criteria criteria = new Criteria();
        
        criteria.addEqualTo(PropertyConstants.UNIVERSITY_FISCAL_YEAR, ledgerEntry.getUniversityFiscalYear());
        criteria.addEqualTo(PropertyConstants.CHART_OF_ACCOUNTS_CODE, ledgerEntry.getChartOfAccountsCode());
        criteria.addEqualTo(PropertyConstants.ACCOUNT_NUMBER, ledgerEntry.getAccountNumber());
        criteria.addEqualTo(PropertyConstants.SUB_ACCOUNT_NUMBER, ledgerEntry.getSubAccountNumber());
        criteria.addEqualTo(PropertyConstants.FINANCIAL_OBJECT_CODE, ledgerEntry.getFinancialObjectCode());
        criteria.addEqualTo(PropertyConstants.FINANCIAL_SUB_OBJECT_CODE, ledgerEntry.getFinancialSubObjectCode());
        criteria.addEqualTo(PropertyConstants.FINANCIAL_BALANCE_TYPE_CODE, ledgerEntry.getFinancialBalanceTypeCode());
        criteria.addEqualTo(PropertyConstants.FINANCIAL_OBJECT_TYPE_CODE, ledgerEntry.getFinancialObjectTypeCode());
        criteria.addEqualTo(PropertyConstants.UNIVERSITY_FISCAL_PERIOD_CODE, ledgerEntry.getUniversityFiscalPeriodCode());
        criteria.addEqualTo(PropertyConstants.FINANCIAL_DOCUMENT_TYPE_CODE, ledgerEntry.getFinancialDocumentTypeCode());
        criteria.addEqualTo(PropertyConstants.FINANCIAL_SYSTEM_ORIGINATION_CODE, ledgerEntry.getFinancialSystemOriginationCode());
        criteria.addEqualTo(PropertyConstants.DOCUMENT_NUMBER, ledgerEntry.getDocumentNumber());

        ReportQueryByCriteria query = QueryFactory.newReportQuery(LedgerEntry.class, criteria);
        query.setAttributes(new String[] { "max(" + PropertyConstants.TRANSACTION_ENTRY_SEQUENCE_NUMBER + ")" });

        Iterator iterator = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(query);
        Integer maxSequenceNumber = new Integer(0);
        
        if (iterator.hasNext()) {
            Object[] data = (Object[]) iterator.next();
            if (data[0] != null) {
                maxSequenceNumber = (Integer)data[0];
            }
        }
        return maxSequenceNumber;
    }
}
