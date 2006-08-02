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
package org.kuali.module.kra.service;

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.util.SpringServiceLocator;
import org.kuali.module.kra.bo.BudgetPeriodTest;
import org.kuali.module.kra.budget.bo.Budget;
import org.kuali.module.kra.budget.bo.BudgetIndirectCost;
import org.kuali.module.kra.budget.bo.BudgetPeriod;
import org.kuali.module.kra.budget.bo.BudgetTask;
import org.kuali.module.kra.budget.bo.BudgetTaskPeriodIndirectCost;
import org.kuali.module.kra.budget.document.BudgetDocument;
import org.kuali.module.kra.budget.service.BudgetIndirectCostService;
import org.kuali.test.KualiTestBaseWithSpring;

public class BudgetIndirectCostServiceTest extends KualiTestBaseWithSpring {

    private BudgetIndirectCostService indirectCostService;
    private BudgetDocument budgetDocument;

    /**
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        indirectCostService = SpringServiceLocator.getBudgetIndirectCostService();
        budgetDocument = new BudgetDocument();
    }

    protected void populateBudgetTasksPeriods(BudgetDocument budgetDocument) {

        List periods = BudgetPeriodTest.createBudgetPeriods(2);
        for (int i = 0; i < periods.size(); i++) {
            BudgetPeriod period = (BudgetPeriod) periods.get(i);
            period.setDocumentHeaderId("1234");
            period.setBudgetPeriodSequenceNumber(i);
        }

        BudgetTask task1 = new BudgetTask();
        task1.setDocumentHeaderId("1234");
        task1.setBudgetTaskSequenceNumber(new Integer(0));

        BudgetTask task2 = new BudgetTask();
        task2.setDocumentHeaderId("1234");
        task2.setBudgetTaskSequenceNumber(new Integer(1));

        List tasks = new ArrayList();
        tasks.add(task1);
        tasks.add(task2);

        Budget budget = new Budget();
        budget.setDocumentHeaderId("1234");
        budget.setTasks(tasks);
        budget.setPeriods(periods);

        budgetDocument.setBudget(budget);
    }

    public void testReconcileIndirectCost() {

        // Test new document
        populateBudgetTasksPeriods(this.budgetDocument);

        indirectCostService.reconcileIndirectCost(budgetDocument);

        BudgetIndirectCost indirectCost = budgetDocument.getBudget().getIndirectCost();
        assertEquals(indirectCost.getDocumentHeaderId(), "1234");

        List idcList = indirectCost.getBudgetTaskPeriodIndirectCostItems();
        assertEquals(idcList.size(), 4);

        BudgetTaskPeriodIndirectCost idc1 = (BudgetTaskPeriodIndirectCost) idcList.get(0);
        assertEquals(idc1.getDocumentHeaderId(), "1234");
        assertEquals(idc1.getBudgetTaskSequenceNumber(), new Integer(0));
        assertEquals(idc1.getBudgetPeriodSequenceNumber(), new Integer(0));

        BudgetTaskPeriodIndirectCost idc2 = (BudgetTaskPeriodIndirectCost) idcList.get(1);
        assertEquals(idc2.getDocumentHeaderId(), "1234");
        assertEquals(idc2.getBudgetTaskSequenceNumber(), new Integer(0));
        assertEquals(idc2.getBudgetPeriodSequenceNumber(), new Integer(1));

        BudgetTaskPeriodIndirectCost idc3 = (BudgetTaskPeriodIndirectCost) idcList.get(2);
        assertEquals(idc3.getDocumentHeaderId(), "1234");
        assertEquals(idc3.getBudgetTaskSequenceNumber(), new Integer(1));
        assertEquals(idc3.getBudgetPeriodSequenceNumber(), new Integer(0));

        BudgetTaskPeriodIndirectCost idc4 = (BudgetTaskPeriodIndirectCost) idcList.get(3);
        assertEquals(idc4.getDocumentHeaderId(), "1234");
        assertEquals(idc4.getBudgetTaskSequenceNumber(), new Integer(1));
        assertEquals(idc4.getBudgetPeriodSequenceNumber(), new Integer(1));
        
        // Test existing document to be updated
        BudgetTask newBudgetTask = new BudgetTask();
        newBudgetTask.setDocumentHeaderId("1234");
        newBudgetTask.setBudgetTaskSequenceNumber(2);
        
        budgetDocument.getBudget().getTasks().add(newBudgetTask);
        budgetDocument.getBudget().getPeriods().remove(0);
        budgetDocument.getBudget().getTasks().remove(1);
        
        indirectCostService.reconcileIndirectCost(budgetDocument);
        
        indirectCost = budgetDocument.getBudget().getIndirectCost();
        assertFalse(indirectCost.getBudgetIndirectCostCostShareIndicator());
        assertFalse(indirectCost.getBudgetUnrecoveredIndirectCostIndicator());
        
        idcList = indirectCost.getBudgetTaskPeriodIndirectCostItems();
        assertEquals(idcList.size(), 2);
        
        idc1 = (BudgetTaskPeriodIndirectCost) idcList.get(0);
        assertEquals(idc1.getDocumentHeaderId(), "1234");
        assertEquals(idc1.getBudgetTaskSequenceNumber(), new Integer(0));
        assertEquals(idc1.getBudgetPeriodSequenceNumber(), new Integer(1));

        idc2 = (BudgetTaskPeriodIndirectCost) idcList.get(1);
        assertEquals(idc2.getDocumentHeaderId(), "1234");
        assertEquals(idc2.getBudgetTaskSequenceNumber(), new Integer(2));
        assertEquals(idc2.getBudgetPeriodSequenceNumber(), new Integer(1));
    }
}
