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
package org.kuali.module.gl.batch;

import java.util.Iterator;
import java.util.List;

import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.batch.AbstractStep;
import org.kuali.module.chart.service.ChartService;
import org.kuali.module.gl.service.SufficientFundsService;

/**
 * A step to remove old sufficient funds balances from the database.
 */
public class PurgeSufficientFundBalancesStep extends AbstractStep {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PurgeSufficientFundBalancesStep.class);
    private ChartService chartService;
    private SufficientFundsService sufficientFundsService;

    /**
     * This step will purge data from the gl_sf_balances_t table older than a specified year. It purges the data one chart at a time
     * each within their own transaction so database transaction logs don't get completely filled up when doing this. This step
     * class should NOT be transactional.
     * 
     * @param jobName the name of the job this step is being run as part of
     * @return true if the job completed successfully, false if otherwise
     * @see org.kuali.kfs.batch.Step#execute(java.lang.String)
     */
    public boolean execute(String jobName) {
        String yearStr = getParameterService().getParameterValue(getClass(), KFSConstants.SystemGroupParameterNames.PURGE_GL_SF_BALANCES_T_BEFORE_YEAR);
        int year = Integer.parseInt(yearStr);
        List charts = chartService.getAllChartCodes();
        for (Iterator iter = charts.iterator(); iter.hasNext();) {
            String chart = (String) iter.next();
            sufficientFundsService.purgeYearByChart(chart, year);
        }
        return true;
    }

    /**
     * Sets the sufficientFundsService attribute, allowing the injection of an implementation of the service.
     * 
     * @param sufficientFundsService the sufficientFundsService implementation to set
     * @see org.kuali.module.gl.service.SufficientFundsService
     */
    public void setSufficientFundsService(SufficientFundsService sufficientFundsService) {
        this.sufficientFundsService = sufficientFundsService;
    }

    /**
     * Sets the chartService attribute, allowing the injection of an implementation of the service.
     * 
     * @param chartService the chartService implementation to set
     * @see org.kuali.module.chart.service.ChartService
     */
    public void setChartService(ChartService chartService) {
        this.chartService = chartService;
    }
}