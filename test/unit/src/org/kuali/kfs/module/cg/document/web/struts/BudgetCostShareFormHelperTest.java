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
package org.kuali.module.kra.web.struts.form;

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.util.KualiInteger;
import org.kuali.module.kra.bo.BudgetNonpersonnelTest;
import org.kuali.module.kra.bo.BudgetPeriodTest;
import org.kuali.module.kra.budget.bo.BudgetNonpersonnel;
import org.kuali.module.kra.budget.bo.BudgetPeriod;
import org.kuali.module.kra.budget.bo.BudgetPeriodThirdPartyCostShare;
import org.kuali.module.kra.budget.bo.BudgetPeriodUniversityCostShare;
import org.kuali.module.kra.budget.bo.BudgetTaskPeriodIndirectCost;
import org.kuali.module.kra.budget.bo.BudgetThirdPartyCostShare;
import org.kuali.module.kra.budget.bo.BudgetUniversityCostShare;
import org.kuali.module.kra.budget.bo.BudgetUser;
import org.kuali.module.kra.budget.bo.UniversityCostSharePersonnel;
import org.kuali.module.kra.budget.web.struts.form.BudgetCostShareFormHelper;
import org.kuali.module.kra.budget.web.struts.form.BudgetIndirectCostFormHelper;
import org.kuali.test.KualiTestBaseWithSpring;

/**
 * This class tests methods in BudgetOverviewFormHelper.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class BudgetCostShareFormHelperTest extends KualiTestBaseWithSpring {

    List<BudgetPeriod> periods;
    List<BudgetUser> personnel;
    List<BudgetNonpersonnel> budgetNonpersonnelItems;
    List<UniversityCostSharePersonnel> universityCostSharePersonnel;
    List<BudgetUniversityCostShare> budgetUniversityCostShare;
    List<BudgetThirdPartyCostShare> budgetThirdPartyCostShare;
    BudgetIndirectCostFormHelper budgetIndirectCostFormHelper;
    
    protected void setUp() throws Exception {
        super.setUp();

        periods = BudgetPeriodTest.createBudgetPeriods(2);

        personnel = new ArrayList();
        // TODO
        
        String[] categories = { "CO", "CO", "SC", "SC" };
        String[] subCategories = { "C1", "C1", "R3", "R1" };
        String[] subcontractorNumber = { "", "", "1", "2" };
        budgetNonpersonnelItems = BudgetNonpersonnelTest.createBudgetNonpersonnel(categories, subCategories, subcontractorNumber);
        
        universityCostSharePersonnel = new ArrayList();
        //UniversityCostSharePersonnel universityCostSharePersonnel1 = new UniversityCostSharePersonnel();
        // TODO
        
        budgetUniversityCostShare = new ArrayList();
        BudgetUniversityCostShare budgetUniversityCostShare1 = new BudgetUniversityCostShare();
        List budgetPeriodUniversityCostShare = new ArrayList();
        BudgetPeriodUniversityCostShare budgetPeriodUniversityCostShare1 = new BudgetPeriodUniversityCostShare();
        budgetPeriodUniversityCostShare1.setBudgetCostShareAmount(new KualiInteger(1000));
        budgetPeriodUniversityCostShare.add(budgetPeriodUniversityCostShare1);
        BudgetPeriodUniversityCostShare budgetPeriodUniversityCostShare2 = new BudgetPeriodUniversityCostShare();
        budgetPeriodUniversityCostShare2.setBudgetCostShareAmount(new KualiInteger(2000));
        budgetPeriodUniversityCostShare.add(budgetPeriodUniversityCostShare2);
        budgetUniversityCostShare1.setBudgetPeriodCostShare(budgetPeriodUniversityCostShare);
        budgetUniversityCostShare.add(budgetUniversityCostShare1);
        
        budgetThirdPartyCostShare = new ArrayList();
        BudgetThirdPartyCostShare budgetThirdPartyCostShare1 = new BudgetThirdPartyCostShare();
        List budgetPeriodThirdPartyCostShare = new ArrayList();
        BudgetPeriodThirdPartyCostShare budgetPeriodThirdPartyCostShare1 = new BudgetPeriodThirdPartyCostShare();
        budgetPeriodThirdPartyCostShare1.setBudgetCostShareAmount(new KualiInteger(3000));
        budgetPeriodThirdPartyCostShare.add(budgetPeriodThirdPartyCostShare1);
        BudgetPeriodThirdPartyCostShare budgetPeriodThirdPartyCostShare2 = new BudgetPeriodThirdPartyCostShare();
        budgetPeriodThirdPartyCostShare2.setBudgetCostShareAmount(new KualiInteger(4000));
        budgetPeriodThirdPartyCostShare.add(budgetPeriodThirdPartyCostShare2);
        budgetThirdPartyCostShare1.setBudgetPeriodCostShare(budgetPeriodThirdPartyCostShare);
        budgetThirdPartyCostShare.add(budgetThirdPartyCostShare1);
               
        budgetIndirectCostFormHelper = new BudgetIndirectCostFormHelper();
        List periodTotalsList = new ArrayList();
        BudgetTaskPeriodIndirectCost idcPeriod0Exist = new BudgetTaskPeriodIndirectCost();
        idcPeriod0Exist.setCostShareCalculatedIndirectCost(new KualiInteger(300));
        idcPeriod0Exist.setCostShareUnrecoveredIndirectCost(new KualiInteger(200));
        periodTotalsList.add(idcPeriod0Exist);
        BudgetTaskPeriodIndirectCost idcPeriod1Exist = new BudgetTaskPeriodIndirectCost();
        idcPeriod1Exist.setCostShareCalculatedIndirectCost(new KualiInteger(100));
        idcPeriod1Exist.setCostShareUnrecoveredIndirectCost(new KualiInteger(100));
        periodTotalsList.add(idcPeriod1Exist);
        budgetIndirectCostFormHelper.setPeriodTotals(periodTotalsList);
    }

    public void testBudgetCostShareFormHelper() {
        BudgetCostShareFormHelper budgetCostShareFormHelper = new BudgetCostShareFormHelper(periods, personnel, budgetNonpersonnelItems, universityCostSharePersonnel, budgetUniversityCostShare, budgetThirdPartyCostShare, budgetIndirectCostFormHelper);
        
        testSetupInstitutionDirect(budgetCostShareFormHelper.getInstitutionDirect());
        
        testSetupThirdPartyDirect(budgetCostShareFormHelper.getThirdPartyDirect());
        
        testSetupSubcontractors(budgetCostShareFormHelper);
        
        testSetupTotals(budgetCostShareFormHelper);
    }
    
    private void testSetupInstitutionDirect(BudgetCostShareFormHelper.Direct institutionPartyDirect) {
        assertEquals("institutionPartyDirect.getTotalBudgeted()[0] = 8000", institutionPartyDirect.getTotalBudgeted()[0], new KualiInteger(8000));
        assertEquals("institutionPartyDirect.getAmountDistributed()[0] = 1000", institutionPartyDirect.getAmountDistributed()[0], new KualiInteger(1000));
        assertEquals("institutionPartyDirect.getBalanceToBeDistributed()[0] = 7000", institutionPartyDirect.getBalanceToBeDistributed()[0], new KualiInteger(7000));
        
        assertEquals("institutionPartyDirect.getTotalTotalBudgeted()[0] = 8000", institutionPartyDirect.getTotalTotalBudgeted(), new KualiInteger(8000));
        assertEquals("institutionPartyDirect.getTotalAmountDistributed()[0] = 3000", institutionPartyDirect.getTotalAmountDistributed(), new KualiInteger(3000));
        assertEquals("institutionPartyDirect.getTotalBalanceToBeDistributed()[0] = 5000", institutionPartyDirect.getTotalBalanceToBeDistributed(), new KualiInteger(5000));

        assertEquals("institutionPartyDirect.getTotalSource()[0] = 3000", institutionPartyDirect.getTotalSource()[0], new KualiInteger(3000));
        
//        assertEquals("institutionPartyDirect.getTotalSource()[0] = 3000", institutionPartyDirect.getInstitutionDirectPersonnel()[0], new KualiInteger(3000));
//        assertEquals("institutionPartyDirect.getTotalInstitutionDirectPersonnel()[0] = 3000", institutionPartyDirect.getTotalInstitutionDirectPersonnel()[0], new KualiInteger(3000));
        
//        private KualiInteger[][] institutionDirectPersonnel;
//        private KualiInteger[] totalInstitutionDirectPersonnel;
    }
    
    private void testSetupThirdPartyDirect(BudgetCostShareFormHelper.Direct thirdPartyDirect) {
        assertEquals("thirdPartyDirect.getTotalBudgeted()[0] = 12000", thirdPartyDirect.getTotalBudgeted()[0], new KualiInteger(12000));
        assertEquals("thirdPartyDirect.getAmountDistributed()[0] = 9000", thirdPartyDirect.getAmountDistributed()[0], new KualiInteger(9000));
        assertEquals("thirdPartyDirect.getBalanceToBeDistributed()[0] = 3000", thirdPartyDirect.getBalanceToBeDistributed()[0], new KualiInteger(3000));
        
        assertEquals("thirdPartyDirect.getTotalTotalBudgeted()[0] = 12000", thirdPartyDirect.getTotalTotalBudgeted(), new KualiInteger(12000));
        assertEquals("thirdPartyDirect.getTotalAmountDistributed()[0] = 13000", thirdPartyDirect.getTotalAmountDistributed(), new KualiInteger(13000));
        assertEquals("thirdPartyDirect.getTotalBalanceToBeDistributed()[0] = -1000", thirdPartyDirect.getTotalBalanceToBeDistributed(), new KualiInteger(-1000));

        assertEquals("thirdPartyDirect.getTotalSource()[0] = 7000", thirdPartyDirect.getTotalSource()[0], new KualiInteger(7000));
    }
    
    private void testSetupSubcontractors(BudgetCostShareFormHelper budgetCostShareFormHelper) {
        List<BudgetCostShareFormHelper.Subcontractor> subcontractors = budgetCostShareFormHelper.getSubcontractors();

        assertTrue("subcontractors.size() == 2", subcontractors.size() == 2);

        for (BudgetCostShareFormHelper.Subcontractor subcontractor : subcontractors) {
            assertEquals("totalPeriodAmount = 3000", subcontractor.getTotalPeriodAmount(), new KualiInteger(3000));

            KualiInteger[] periodAmounts = subcontractor.getPeriodAmounts();
            assertEquals("periodAmounts[0] = 3000", periodAmounts[0], new KualiInteger(3000));
            assertEquals("periodAmounts[1] = 0", periodAmounts[1], new KualiInteger(0));
        }

        // This test does not do any aggregation (hit Subcontractor.addPeriodAmount). That is not very good but in the
        // interest of time I'm omitting that. BudgetNonpersonnelCopyOverFormHelperTest could possibly be used for that
        // although it wouldn't be trivial...
    }
    
    private void testSetupTotals(BudgetCostShareFormHelper budgetCostShareFormHelper) {
        assertEquals("institutionIndirectCostShare[0] = 500", budgetCostShareFormHelper.getInstitutionIndirectCostShare()[0], new KualiInteger(500));
        assertEquals("total[0] = 16500", budgetCostShareFormHelper.getTotal()[0], new KualiInteger(16500));
        
        assertEquals("institutionIndirectCostShare[1] = 200", budgetCostShareFormHelper.getInstitutionIndirectCostShare()[1], new KualiInteger(200));
        assertEquals("total[1] = 6200", budgetCostShareFormHelper.getTotal()[1], new KualiInteger(6200));
        
        assertEquals("totalInstitutionIndirectCostShare = 700", budgetCostShareFormHelper.getTotalInstitutionIndirectCostShare(), new KualiInteger(700));
        assertEquals("totalTotal = 22700", budgetCostShareFormHelper.getTotalTotal(), new KualiInteger(22700));
    }
}