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
package org.kuali.module.purap.service.impl;

import java.util.List;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.ObjectUtils;
import org.kuali.module.purap.bo.PurchaseOrderStatusHistory;
import org.kuali.module.purap.bo.RequisitionStatusHistory;
import org.kuali.module.purap.document.PurchaseOrderDocument;
import org.kuali.module.purap.document.PurchasingAccountsPayableDocument;
import org.kuali.module.purap.document.RequisitionDocument;
import org.kuali.module.purap.service.PurapService;

public class PurapServiceImpl implements PurapService {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PurapServiceImpl.class);

    private BusinessObjectService businessObjectService;

    public void setBusinessObjectService(BusinessObjectService boService) {
        this.businessObjectService = boService;    
    }

    /**
     * This method updates the status and status history for a purap document.
     *
    */
    public boolean updateStatusAndStatusHistory(PurchasingAccountsPayableDocument document,String statusToSet) {
        LOG.debug("updateStatusAndStatusHistory(): entered method.");
        
        boolean success = false;
        
        if ( ObjectUtils.isNull(document) || ObjectUtils.isNull(statusToSet) ) {
            return success;
        }

        success &= this.updateStatusHistory(document, statusToSet);
        success = this.updateStatus(document, statusToSet);

        LOG.debug("updateStatusAndStatusHistory(): leaving method.");
        return success;
    }

    /**
     * This method updates the status for a purap document.
     *
    */
    public boolean updateStatus(PurchasingAccountsPayableDocument document,String newStatus) {
        LOG.debug("updateStatus(): entered method.");
        
        boolean success = false;
        
        if ( ObjectUtils.isNull(document) || ObjectUtils.isNull(newStatus) ) {
            return success;
        }
        
        String oldStatus = document.getStatusCode();

        document.setStatusCode(newStatus);
        
        success = true;
        if (success) {
            LOG.debug("Status of document #"+document.getDocumentNumber()+" has been changed from "+
               oldStatus+" to "+newStatus);
        }
        
        LOG.debug("updateStatus(): leaving method.");
        return success;
    }

    /**
     * This method updates the status history for a purap document.
     *
    */
    public boolean updateStatusHistory(PurchasingAccountsPayableDocument document,String newStatus) {
        LOG.debug("updateStatusHistory(): entered method.");
        
        boolean success = false;
        
        if ( ObjectUtils.isNull(document) || ObjectUtils.isNull(newStatus) ) {
            return success;
        }

        String oldStatus = document.getStatusCode();
        List statusHistories = document.getStatusHistories();
        if( document instanceof RequisitionDocument ) {
            RequisitionStatusHistory statusHistory = new RequisitionStatusHistory(oldStatus, newStatus);
            statusHistories.add(statusHistory);
        } else if ( document instanceof PurchaseOrderDocument ) {
            PurchaseOrderStatusHistory statusHistory = new PurchaseOrderStatusHistory(oldStatus, newStatus);
            statusHistories.add(statusHistory);
        }        
        
        document.setStatusHistories(statusHistories);

        success = true;
        if (success) {
            LOG.debug("StatusHistory of document #"+document.getDocumentNumber()+" has been changed from "
                    +oldStatus+" to "+newStatus);
        }
        
        LOG.debug("updateStatusHistory(): leaving method.");
        return success;
    }

}
