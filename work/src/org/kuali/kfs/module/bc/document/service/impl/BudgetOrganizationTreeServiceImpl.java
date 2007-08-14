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
package org.kuali.module.budget.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.module.budget.bo.BudgetConstructionOrganizationReports;
import org.kuali.module.budget.bo.BudgetConstructionPullup;
import org.kuali.module.budget.dao.BudgetConstructionDao;
import org.kuali.module.budget.service.BudgetConstructionOrganizationReportsService;
import org.kuali.module.budget.service.BudgetOrganizationTreeService;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the BudgetOrganizationTreeService interface
 */
@Transactional
public class BudgetOrganizationTreeServiceImpl implements BudgetOrganizationTreeService {
    private static Logger LOG = org.apache.log4j.Logger.getLogger(BudgetOrganizationTreeServiceImpl.class);

    private BudgetConstructionOrganizationReportsService budgetConstructionOrganizationReportsService;
    private BusinessObjectService businessObjectService;
    private BudgetConstructionDao budgetConstructionDao;
    
    // controls used to trap any runaways due to cycles in the reporting tree
    private static final int MAXLEVEL = 50;
    private int curLevel;

    /**
     * @see org.kuali.module.budget.service.BudgetOrganizationTreeService#buildPullup(java.lang.String, java.lang.String, java.lang.String)
     */
    public void buildPullup(String personUserIdentifier, String chartOfAccountsCode, String organizationCode) {
        cleanPullup(personUserIdentifier);
        BudgetConstructionOrganizationReports bcOrgRpts = budgetConstructionOrganizationReportsService.getByPrimaryId(chartOfAccountsCode, organizationCode);
        if (bcOrgRpts != null){
            if (bcOrgRpts.getOrganization().isOrganizationActiveIndicator()){
                curLevel = 0;
                buildSubTree(personUserIdentifier, bcOrgRpts, curLevel);
            }
        }
    }
    
    private void buildSubTree(String personUserIdentifier, BudgetConstructionOrganizationReports bcOrgRpts, int curLevel){
        
        curLevel++;
        BudgetConstructionPullup bcPullup = new BudgetConstructionPullup();
        bcPullup.setPersonUniversalIdentifier(personUserIdentifier);
        bcPullup.setChartOfAccountsCode(bcOrgRpts.getChartOfAccountsCode());
        bcPullup.setOrganizationCode(bcOrgRpts.getOrganizationCode());
        bcPullup.setReportsToChartOfAccountsCode(bcOrgRpts.getReportsToChartOfAccountsCode());
        bcPullup.setReportsToOrganizationCode(bcOrgRpts.getReportsToOrganizationCode());
        bcPullup.setPullFlag(new Integer(0));
        businessObjectService.save(bcPullup);

        if (curLevel <= MAXLEVEL){
            // getActiveChildOrgs does not return orgs that report to themselves 
            List childOrgs = budgetConstructionOrganizationReportsService.getActiveChildOrgs(bcOrgRpts.getChartOfAccountsCode(), bcOrgRpts.getOrganizationCode());
            if (childOrgs.size() > 0){
                for (Iterator iter = childOrgs.iterator(); iter.hasNext();){
                    BudgetConstructionOrganizationReports bcOrg = (BudgetConstructionOrganizationReports) iter.next();
                    buildSubTree(personUserIdentifier, bcOrg, curLevel);
                }
            }
        } else {
            LOG.warn(String.format("\n%s/%s reports to organization more than maxlevel of %d",
                    bcOrgRpts.getChartOfAccountsCode(),
                    bcOrgRpts.getOrganizationCode(),
                    MAXLEVEL));
        }
    }

    /**
     * @see org.kuali.module.budget.service.BudgetOrganizationTreeService#cleanPullup(java.lang.String)
     */
    public void cleanPullup(String personUserIdentifier) {

        budgetConstructionDao.deleteBudgetConstructionPullupByUserId(personUserIdentifier);

    }

    /**
     * Gets the budgetConstructionOrganizationReportsService attribute. 
     * @return Returns the budgetConstructionOrganizationReportsService.
     */
    public BudgetConstructionOrganizationReportsService getBudgetConstructionOrganizationReportsService() {
        return budgetConstructionOrganizationReportsService;
    }

    /**
     * Sets the budgetConstructionOrganizationReportsService attribute value.
     * @param budgetConstructionOrganizationReportsService The budgetConstructionOrganizationReportsService to set.
     */
    public void setBudgetConstructionOrganizationReportsService(BudgetConstructionOrganizationReportsService budgetConstructionOrganizationReportsService) {
        this.budgetConstructionOrganizationReportsService = budgetConstructionOrganizationReportsService;
    }

    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * Gets the budgetConstructionDao attribute. 
     * @return Returns the budgetConstructionDao.
     */
    public BudgetConstructionDao getBudgetConstructionDao() {
        return budgetConstructionDao;
    }

    /**
     * Sets the budgetConstructionDao attribute value.
     * @param budgetConstructionDao The budgetConstructionDao to set.
     */
    public void setBudgetConstructionDao(BudgetConstructionDao budgetConstructionDao) {
        this.budgetConstructionDao = budgetConstructionDao;
    }

}
