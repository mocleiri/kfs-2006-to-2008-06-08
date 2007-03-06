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
package org.kuali.module.chart.service.impl;

import org.kuali.core.service.KualiUserService;
import org.kuali.module.chart.bo.PriorYearAccount;
import org.kuali.module.chart.dao.PriorYearAccountDao;
import org.kuali.module.chart.service.PriorYearAccountService;

/**
 * @version $Id: PriorYearAccountServiceImpl.java,v 1.1.4.5 2007-02-10 11:37:24 j2eemgr Exp $
 */
public class PriorYearAccountServiceImpl implements PriorYearAccountService {

    private PriorYearAccountDao priorYearAccountDao;
    private KualiUserService kualiUserService;
    
    public PriorYearAccountServiceImpl() {
        super();
    }

    /**
     * @param priorYearAccountDao The priorYearAccountDao to set.
     */
    public void setPriorYearAccountDao(PriorYearAccountDao priorYearAccountDao) {
        this.priorYearAccountDao = priorYearAccountDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.kuali.module.chart.service.PriorYearAccountService#getByPrimaryKey(java.lang.String, java.lang.String)
     */
    public PriorYearAccount getByPrimaryKey(String chartCode, String accountNumber) {
        return priorYearAccountDao.getByPrimaryId(chartCode, accountNumber);
    }

    public void setKualiUserService(KualiUserService kualiUserService) {
        this.kualiUserService = kualiUserService;
    }    
    
}
