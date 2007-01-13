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
package org.kuali.module.chart.service.impl;

import org.kuali.Constants;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.module.chart.bo.SubFundGroup;
import org.kuali.module.chart.dao.SubFundGroupDao;
import org.kuali.module.chart.service.SubFundGroupService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class SubFundGroupServiceImpl implements SubFundGroupService {
    private KualiConfigurationService configurationService;
    private SubFundGroupDao subFundGroupDao;

    /**
     * @see org.kuali.module.chart.service.SubFundGroupService#isForContractsAndGrants(org.kuali.module.chart.bo.SubFundGroup)
     */
    public boolean isForContractsAndGrants(SubFundGroup subFundGroup) {
        if (configurationService.getApplicationParameterIndicator(Constants.ChartApcParms.GROUP_CHART_MAINT_EDOCS, Constants.ChartApcParms.ACCOUNT_FUND_GROUP_DENOTES_CG)) {
            return isForContractsAndGrants(subFundGroup.getFundGroupCode());
        }
        else {
            return isForContractsAndGrants(subFundGroup.getSubFundGroupCode());
        }
    }

    private boolean isForContractsAndGrants(String fundGroupOrSubFundGroupCode) {
        return configurationService.getApplicationParameterValue(Constants.ChartApcParms.GROUP_CHART_MAINT_EDOCS, Constants.ChartApcParms.ACCOUNT_CG_DENOTING_VALUE).equals(fundGroupOrSubFundGroupCode);
    }

    /**
     * @see org.kuali.module.chart.service.SubFundGroupService#getByPrimaryId(java.lang.String)
     */
    public SubFundGroup getByPrimaryId(String subFundGroupCode) {
        return subFundGroupDao.getByPrimaryId(subFundGroupCode);
    }

    /**
     * @see org.kuali.module.chart.service.SubFundGroupService#getByChartAndAccount(java.lang.String, java.lang.String)
     */
    public SubFundGroup getByChartAndAccount(String chartCode, String accountNumber) {
        return subFundGroupDao.getByChartAndAccount(chartCode, accountNumber);
    }

    /**
     * Sets the configurationService attribute value.
     * 
     * @param configurationService The configurationService to set.
     */
    public void setConfigurationService(KualiConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    /**
     * Sets the subFundGroupDao attribute values.
     * 
     * @param subFundGroupDao The subFundGroupDao to set.
     */
    public void setSubFundGroupDao(SubFundGroupDao subFundGroupDao) {
        this.subFundGroupDao = subFundGroupDao;
    }
}
