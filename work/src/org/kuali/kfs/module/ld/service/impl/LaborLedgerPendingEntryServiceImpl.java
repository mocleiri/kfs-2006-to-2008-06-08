/*
 * Copyright 2005-2006 The Kuali Foundation.
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
package org.kuali.module.labor.service.impl;

import java.util.Map;

import org.kuali.core.dao.BusinessObjectDao;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.module.chart.service.ChartUserService;
import org.kuali.module.labor.service.LaborLedgerPendingEntryService;

/**
 * This class is the service implementation for the laborLedgerPendingEntry structure.
 * 
 * 
 */
public class LaborLedgerPendingEntryServiceImpl implements LaborLedgerPendingEntryService {

    private BusinessObjectService businessObjectService;
    
    /**
     * @see org.kuali.core.service.BusinessObjectService#countMatching(java.lang.Class, java.util.Map)
     */
    public int countMatching(Class clazz, Map fieldValues) {
        return businessObjectService.countMatching(clazz, fieldValues);
    }
    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}