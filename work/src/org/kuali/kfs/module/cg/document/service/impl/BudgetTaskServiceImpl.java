/*
 * Copyright (c) 2004, 2005 The National Association of College and University
 * Business Officers, Cornell University, Trustees of Indiana University,
 * Michigan State University Board of Trustees, Trustees of San Joaquin Delta
 * College, University of Hawai'i, The Arizona Board of Regents on behalf of the
 * University of Arizona, and the r*smart group.
 * 
 * Licensed under the Educational Community License Version 1.0 (the "License");
 * By obtaining, using and/or copying this Original Work, you agree that you
 * have read, understand, and will comply with the terms and conditions of the
 * Educational Community License.
 * 
 * You may obtain a copy of the License at:
 * 
 * http://kualiproject.org/license.html
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *  
 */

package org.kuali.module.kra.budget.service.impl;

import java.util.Iterator;
import java.util.List;

import org.kuali.module.kra.budget.bo.BudgetTask;
import org.kuali.module.kra.budget.dao.BudgetTaskDao;
import org.kuali.module.kra.budget.service.BudgetTaskService;

/**
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 * 
 */
public class BudgetTaskServiceImpl implements BudgetTaskService {

    private BudgetTaskDao budgetTaskDao;


    /**
     * @return Returns the businessObjectService.
     */
    public BudgetTaskDao getBudgetTaskDao() {
        return budgetTaskDao;
    }

    /**
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBudgetTaskDao(BudgetTaskDao budgetTaskDao) {
        this.budgetTaskDao = budgetTaskDao;
    }

    /**
     * @return Returns the budgetTaskDao.
     */

    /*
     * (non-Javadoc)
     * 
     * @see org.kuali.service.BudgetPeriodService#getBudgetPeriod(java.lang.Long, java.lang.Integer)
     */
    public BudgetTask getBudgetTask(String documentHeaderId, Integer budgetTaskSequenceNumber) {
        return budgetTaskDao.getBudgetTask(documentHeaderId, budgetTaskSequenceNumber);
    }

    public BudgetTask getFirstBudgetTask(String documentHeaderId) {
        List budgetTasks = budgetTaskDao.getBudgetTaskList(documentHeaderId);
        // there should always be a budgetTask by the time we get to here, so we want an exception thrown if there's not
        return (BudgetTask) budgetTasks.get(0);
    }

    public int getTaskIndex(Integer budgetTaskSequenceNumber, List budgetTaskList) {
        int taskIndexNumber = -1;
        Iterator budgetTaskListIter = budgetTaskList.iterator();

        for (int i = 0; budgetTaskListIter.hasNext(); i++) {
            BudgetTask budgetTask = (BudgetTask) budgetTaskListIter.next();

            if (budgetTask.getBudgetTaskSequenceNumber().equals(budgetTaskSequenceNumber)) {
                taskIndexNumber = i;
                break;
            }
        }

        return taskIndexNumber;
    }
}
