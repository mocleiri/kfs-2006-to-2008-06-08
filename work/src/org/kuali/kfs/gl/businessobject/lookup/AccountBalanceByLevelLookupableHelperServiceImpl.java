/*
 * Copyright 2006-2007 The Kuali Foundation.
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
package org.kuali.module.gl.web.lookupable;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.kuali.core.bo.BusinessObject;
import org.kuali.core.lookup.AbstractLookupableHelperServiceImpl;
import org.kuali.core.lookup.CollectionIncomplete;
import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.KFSPropertyConstants;
import org.kuali.module.gl.GLConstants;
import org.kuali.module.gl.bo.AccountBalance;
import org.kuali.module.gl.bo.TransientBalanceInquiryAttributes;
import org.kuali.module.gl.service.AccountBalanceService;
import org.kuali.module.gl.util.BusinessObjectFieldConverter;
import org.kuali.module.gl.web.Constant;
import org.kuali.module.gl.web.inquirable.AccountBalanceByLevelInquirableImpl;

/**
 * An extension of KualiLookupableImpl to support the account balance by level inquiry screen
 */
public class AccountBalanceByLevelLookupableHelperServiceImpl extends AbstractLookupableHelperServiceImpl {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(AccountBalanceByLevelLookupableHelperServiceImpl.class);

    private AccountBalanceService accountBalanceService;

    public void setAccountBalanceService(AccountBalanceService abs) {
        accountBalanceService = abs;
    }

    /**
     * Returns the inquiry url for a field if one exist.
     * 
     * @param bo the business object instance to build the urls for
     * @param propertyName the property which links to an inquirable
     * @return String url to inquiry
     */
    @Override
    public String getInquiryUrl(BusinessObject bo, String propertyName) {
        return (new AccountBalanceByLevelInquirableImpl()).getInquiryUrl(bo, propertyName);
    }

    /**
     * Uses Lookup Service to provide a basic search.
     * 
     * @param fieldValues - Map containing prop name keys and search values
     * @return List found business objects
     */
    public List getSearchResults(Map fieldValues) {
        LOG.debug("getSearchResults() started");

        setBackLocation((String) fieldValues.get(KFSConstants.BACK_LOCATION));
        setDocFormKey((String) fieldValues.get(KFSConstants.DOC_FORM_KEY));

        BusinessObjectFieldConverter.escapeSingleQuote(fieldValues);

        String costShareOption = (String) fieldValues.get(GLConstants.DummyBusinessObject.COST_SHARE_OPTION);
        String pendingEntryOption = (String) fieldValues.get(GLConstants.DummyBusinessObject.PENDING_ENTRY_OPTION);
        String consolidationOption = (String) fieldValues.get(GLConstants.DummyBusinessObject.CONSOLIDATION_OPTION);
        boolean isCostShareExcluded = Constant.COST_SHARE_EXCLUDE.equals(costShareOption);
        int pendingEntryCode = AccountBalanceService.PENDING_NONE;
        if (GLConstants.PendingEntryOptions.APPROVED.equals(pendingEntryOption)) {
            pendingEntryCode = AccountBalanceService.PENDING_APPROVED;
        }
        else if (GLConstants.PendingEntryOptions.ALL.equals(pendingEntryOption)) {
            pendingEntryCode = AccountBalanceService.PENDING_ALL;
        }
        boolean isConsolidated = Constant.CONSOLIDATION.equals(consolidationOption);

        String chartOfAccountsCode = (String) fieldValues.get(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE);
        String accountNumber = (String) fieldValues.get(KFSPropertyConstants.ACCOUNT_NUMBER);
        String subAccountNumber = (String) fieldValues.get(KFSPropertyConstants.SUB_ACCOUNT_NUMBER);
        String financialConsolidationObjectCode = (String) fieldValues.get(GLConstants.BalanceInquiryDrillDowns.CONSOLIDATION_OBJECT_CODE);

        // Dashes means no sub account number
        if (KFSConstants.getDashSubAccountNumber().equals(subAccountNumber)) {
            subAccountNumber = "";
        }

        String ufy = (String) fieldValues.get(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR);

        // TODO Deal with invalid numbers
        Integer universityFiscalYear = new Integer(Integer.parseInt(ufy));

        List results = accountBalanceService.findAccountBalanceByLevel(universityFiscalYear, chartOfAccountsCode, accountNumber, subAccountNumber, financialConsolidationObjectCode, isCostShareExcluded, isConsolidated, pendingEntryCode);

        // Put the search related stuff in the objects
        for (Iterator iter = results.iterator(); iter.hasNext();) {
            AccountBalance ab = (AccountBalance) iter.next();

            TransientBalanceInquiryAttributes dbo = ab.getDummyBusinessObject();
            dbo.setConsolidationOption(consolidationOption);
            dbo.setCostShareOption(costShareOption);
            dbo.setPendingEntryOption(pendingEntryOption);
            dbo.setLinkButtonOption(Constant.LOOKUP_BUTTON_VALUE);
        }
        return new CollectionIncomplete(results, new Long(results.size()));
    }
}