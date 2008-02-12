/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.module.ar.maintenance;

import java.util.List;

import org.kuali.RiceConstants;
import org.kuali.core.bo.PersistableBusinessObject;
import org.kuali.core.document.MaintenanceLock;
import org.kuali.core.maintenance.KualiMaintainableImpl;
import org.kuali.core.util.ObjectUtils;
import org.kuali.module.ar.bo.CustomerInvoiceItemCode;
import org.kuali.module.ar.maintenance.util.CustomerInvoiceItemCodeMaintainableImplUtil;

public class OrganizationOptionsMaintainableImpl extends KualiMaintainableImpl {

    public List<MaintenanceLock> generateMaintenanceLocks() {
        
        List<MaintenanceLock> maintenanceLocks = super.generateMaintenanceLocks();
        maintenanceLocks.addAll(CustomerInvoiceItemCodeMaintainableImplUtil.generateCustomerInvoiceItemCodeMaintenanceLocks(getBusinessObject(), documentNumber));
        return maintenanceLocks;
    }
}