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
package org.kuali.module.purap.maintenance;

import org.kuali.core.maintenance.KualiMaintainableImpl;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.module.purap.bo.PurchaseOrderContractLanguage;

public class PurchaseOrderContractLanguageMaintainableImpl extends KualiMaintainableImpl {

    @Override
    public void prepareForSave() {
        // TODO Auto-generated method stub
        super.prepareForSave();
    }

    @Override
    public void saveBusinessObject() {
        // TODO Auto-generated method stub
        super.saveBusinessObject();
    }
    
    @Override
    public String getLockingRepresentation() {
        // TODO Auto-generated method stub
        return super.getLockingRepresentation();
    }
    
    @Override
    public void processAfterRetrieve() {
        // TODO Auto-generated method stub
        super.processAfterRetrieve();
    }

    @Override
    public void processAfterCopy() {
        PurchaseOrderContractLanguage pocl = (PurchaseOrderContractLanguage)super.getBusinessObject();
        pocl.setContractLanguageCreateDate(SpringServiceLocator.getDateTimeService().getCurrentSqlDate());
    }
}
