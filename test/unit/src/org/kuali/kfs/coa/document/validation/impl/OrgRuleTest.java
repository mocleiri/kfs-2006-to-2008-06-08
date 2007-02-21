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

import java.util.Map;
import java.util.HashMap;

import org.kuali.core.document.MaintenanceDocument;
import org.kuali.module.chart.bo.Org;
import org.kuali.test.WithTestSpringContext;
import static org.kuali.test.fixtures.UserNameFixture.KHUNTLEY;
import static org.kuali.test.util.KualiTestAssertionUtils.assertGlobalErrorMapEmpty;
import org.kuali.Constants;

import org.kuali.core.util.SpringServiceLocator;

@WithTestSpringContext(session=KHUNTLEY)
public class OrgRuleTest extends ChartRuleTestBase {

    private static final String GOOD_CHART = "UA";
    private static final String BAD_CHART = "ZZ";
    
    private static class TopLevelOrg {
        private String chartOfAccountsCode;
        private String organizationCode;
        private String organizationManager;
        
        private TopLevelOrg(String chartOfAccountsCode, String organizationCode, String organizationManager) {
            this.chartOfAccountsCode = chartOfAccountsCode;
            this.organizationCode = organizationCode;
            this.organizationManager = organizationManager;
        }
        
        static TopLevelOrg getGoodTopLevelOrgKeys() {
            return new TopLevelOrg(GoodTopLevelOrg.CHART_OF_ACCOUNTS_CODE, GoodTopLevelOrg.ORGANIZATION_CODE, GoodTopLevelOrg.ORGANIZATION_MANAGER);
        }
        
        static TopLevelOrg getBadTopLevelOrgKeys() {
            return new TopLevelOrg(BadTopLevelOrg.CHART_OF_ACCOUNTS_CODE, BadTopLevelOrg.ORGANIZATION_CODE, BadTopLevelOrg.ORGANIZATION_MANAGER);
        }
    
        private class GoodTopLevelOrg {
            private static final String CHART_OF_ACCOUNTS_CODE = "IU";
            private static final String ORGANIZATION_CODE = "UNIV";
            private static final String ORGANIZATION_MANAGER = "CAMCSWEE";
        }
        
        private class BadTopLevelOrg {
            private static final String CHART_OF_ACCOUNTS_CODE = "FW";
            private static final String ORGANIZATION_CODE = "FW";
            private static final String ORGANIZATION_MANAGER = "MFLANDER";
        }
    }

    private OrgRule rule;
    private Org org;
    private MaintenanceDocument maintDoc;

    public void testDefaultExistenceChecks_chartOfAccounts_good() {
        org = (Org)SpringServiceLocator.getBusinessObjectService().findByPrimaryKey(Org.class, getPrimaryKeysForTopLevelOrg(TopLevelOrg.getGoodTopLevelOrgKeys()));
        maintDoc = this.newMaintDoc(org);
        testDefaultExistenceCheck(org, "organizationCode", false);
        assertGlobalErrorMapEmpty();
    }
    
    /**
     * 
     * This tests makes certain that only one top level heirarchy is allowed.
     */
    public void testCheckSimpleRules_topLevelHeirarchy_noMoreThanOne() {
        Org oldBO = (Org)SpringServiceLocator.getBusinessObjectService().findByPrimaryKey(Org.class, this.getPrimaryKeysForTopLevelOrg(TopLevelOrg.getGoodTopLevelOrgKeys()));
        Org newBO = (Org)SpringServiceLocator.getBusinessObjectService().findByPrimaryKey(Org.class, this.getPrimaryKeysForTopLevelOrg(TopLevelOrg.getBadTopLevelOrgKeys()));
        maintDoc = this.newMaintDoc(oldBO, newBO);
        maintDoc.getNewMaintainableObject().setMaintenanceAction(Constants.MAINTENANCE_EDIT_ACTION); // simulate editing
        newBO.setReportsToChartOfAccountsCode(TopLevelOrg.BadTopLevelOrg.CHART_OF_ACCOUNTS_CODE); // simulate trying to create a new top level org
        newBO.setReportsToOrganizationCode(TopLevelOrg.BadTopLevelOrg.ORGANIZATION_CODE);
        rule = (OrgRule)this.setupMaintDocRule(maintDoc, OrgRule.class);
        assertFalse(rule.checkSimpleRules(maintDoc)); // we may not add more than one top level org
    }
    
    /**
     * 
     * This test makes certain that a top level heirarchy can be edited in a maintenance doc.
     */
    public void testCheckSimpleRules_topLevelHeirarchy_mayEdit() {
        rule = new OrgRule();
        Org oldBO = (Org)SpringServiceLocator.getBusinessObjectService().findByPrimaryKey(Org.class, this.getPrimaryKeysForTopLevelOrg(TopLevelOrg.getGoodTopLevelOrgKeys()));
        Org newBO = (Org)SpringServiceLocator.getBusinessObjectService().findByPrimaryKey(Org.class, this.getPrimaryKeysForTopLevelOrg(TopLevelOrg.getGoodTopLevelOrgKeys()));
        newBO.setReportsToChartOfAccountsCode(TopLevelOrg.GoodTopLevelOrg.CHART_OF_ACCOUNTS_CODE); // simulate editing top level org
        newBO.setReportsToOrganizationCode(TopLevelOrg.GoodTopLevelOrg.ORGANIZATION_CODE);
        maintDoc = this.newMaintDoc(oldBO, newBO);
        maintDoc.getNewMaintainableObject().setMaintenanceAction(Constants.MAINTENANCE_EDIT_ACTION); // simulate editing
        rule = (OrgRule)this.setupMaintDocRule(maintDoc, OrgRule.class);
        assertTrue(rule.checkSimpleRules(maintDoc)); // it is okay to edit the top level org
    }
    
    private Map getPrimaryKeysForTopLevelOrg(TopLevelOrg org) {
        Map primaryKeys = new HashMap();
        primaryKeys.put("chartOfAccountsCode", org.chartOfAccountsCode);
        primaryKeys.put("organizationCode", org.organizationCode);
        return primaryKeys;
    }
}
