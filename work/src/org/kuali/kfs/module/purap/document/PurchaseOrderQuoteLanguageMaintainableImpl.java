/*
 * Copyright 2007 The Kuali Foundation.
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
import org.kuali.core.service.DateTimeService;
import org.kuali.kfs.context.SpringContext;
import org.kuali.module.purap.bo.PurchaseOrderQuoteLanguage;

/* 
 * THIS CODE IS NOT USED IN RELEASE 2 BUT THE CODE WAS LEFT IN TO
 * FACILITATE TURNING IT BACK ON EARLY IN THE DEVELOPMENT CYCLE OF RELEASE 3.
*/
public class PurchaseOrderQuoteLanguageMaintainableImpl extends KualiMaintainableImpl {

    @Override
    public void processAfterCopy() {
        intializePoQuoteLangauge();
    }

    private void intializePoQuoteLangauge() {
        // set create date
        PurchaseOrderQuoteLanguage poql = (PurchaseOrderQuoteLanguage) super.getBusinessObject();
        poql.setPurchaseOrderQuoteLanguageCreateDate(SpringContext.getBean(DateTimeService.class).getCurrentSqlDate());
    }
}
