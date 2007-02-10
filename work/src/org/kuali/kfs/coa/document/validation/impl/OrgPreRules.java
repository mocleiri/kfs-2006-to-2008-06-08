/*
 * Copyright 2006 The Kuali Foundation.
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
package org.kuali.module.chart.rules;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.document.MaintenanceDocument;
import org.kuali.core.util.ObjectUtils;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.Org;
import org.kuali.module.chart.bo.OrganizationExtension;

/**
 * This class...
 * 
 * 
 */
public class OrgPreRules extends MaintenancePreRulesBase {
    private Org newAccount;
    private Org copyAccount;


    public OrgPreRules() {

    }

    protected boolean doCustomPreRules(MaintenanceDocument document) {
        setupConvenienceObjects(document);
        checkForContinuationAccounts(); // run this first to avoid side effects

        LOG.debug("done with continuation account, proceeeding with remaining pre rules");

        updateHRMSUpdateDate((Org) document.getOldMaintainableObject().getBusinessObject(), (Org) document.getNewMaintainableObject().getBusinessObject());

        return true;
    }

    private void checkForContinuationAccounts() {
        LOG.debug("entering checkForContinuationAccounts()");

        if (StringUtils.isNotBlank(newAccount.getOrganizationDefaultAccountNumber())) {
            Account account = checkForContinuationAccount("Account Number", newAccount.getChartOfAccountsCode(), newAccount.getOrganizationDefaultAccountNumber(), "");
            if (ObjectUtils.isNotNull(account)) { // override old user inputs
                newAccount.setOrganizationDefaultAccountNumber(account.getAccountNumber());
                newAccount.setChartOfAccountsCode(account.getChartOfAccountsCode());
            }
        }
    }

    private void setupConvenienceObjects(MaintenanceDocument document) {

        // setup newAccount convenience objects, make sure all possible sub-objects are populated
        newAccount = (Org) document.getNewMaintainableObject().getBusinessObject();
        copyAccount = (Org) ObjectUtils.deepCopy(newAccount);
        copyAccount.refresh();
    }

    /**
     * Check if the HRMS data has changed on this document. If so, update the last update date.
     * 
     * @param oldData
     * @param newData
     */
    private void updateHRMSUpdateDate(Org oldData, Org newData) {
        if (oldData != null) {
            OrganizationExtension oldExt = oldData.getOrganizationExtension();
            OrganizationExtension newExt = newData.getOrganizationExtension();
            if (oldExt != null) {
                if (!ObjectUtils.nullSafeEquals(oldExt.getHrmsCompany(), newExt.getHrmsCompany()) || !ObjectUtils.nullSafeEquals(oldExt.getHrmsIuOrganizationAddress2(), newExt.getHrmsIuOrganizationAddress2()) || !ObjectUtils.nullSafeEquals(oldExt.getHrmsIuOrganizationAddress3(), newExt.getHrmsIuOrganizationAddress3()) || !ObjectUtils.nullSafeEquals(oldExt.getHrmsIuCampusCode(), newExt.getHrmsIuCampusCode()) || !ObjectUtils.nullSafeEquals(oldExt.getHrmsIuCampusBuilding(), newExt.getHrmsIuCampusBuilding()) || !ObjectUtils.nullSafeEquals(oldExt.getHrmsIuCampusRoom(), newExt.getHrmsIuCampusRoom()) || oldExt.isHrmsIuPositionAllowedFlag() != newExt.isHrmsIuPositionAllowedFlag() || oldExt.isHrmsIuTenureAllowedFlag() != newExt.isHrmsIuTenureAllowedFlag() || oldExt.isHrmsIuTitleAllowedFlag() != newExt.isHrmsIuTitleAllowedFlag() || oldExt.isHrmsIuOccupationalUnitAllowedFlag() != newExt.isHrmsIuOccupationalUnitAllowedFlag() || !ObjectUtils.nullSafeEquals(oldExt.getHrmsPersonnelApproverUniversalId(), newExt.getHrmsPersonnelApproverUniversalId()) || !ObjectUtils.nullSafeEquals(oldExt.getFiscalApproverUniversalId(), newExt.getFiscalApproverUniversalId())) {
                    newExt.setHrmsLastUpdateDate(new Timestamp(new Date().getTime()));
                }
            }
            else {
                newExt.setHrmsLastUpdateDate(new Timestamp(new Date().getTime()));
            }
        }
        else {
            newData.getOrganizationExtension().setHrmsLastUpdateDate(new Timestamp(new Date().getTime()));
        }
    }

}
