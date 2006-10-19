/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/work/src/org/kuali/kfs/coa/businessobject/inquiry/OrgInquirable.java,v $
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
package org.kuali.module.chart.maintenance;

import java.util.List;

import org.kuali.core.bo.BusinessObject;
import org.kuali.core.inquiry.KualiInquirableImpl;
import org.kuali.core.web.uidraw.Column;
import org.kuali.module.chart.bo.Org;

public class OrgInquirable extends KualiInquirableImpl {

    
    public void addAdditionalColumns(List columns, BusinessObject bo) {
        // TODO: extract this into a superclass (KULCOA-917)
        if (bo instanceof Org) {
            Org org = (Org) bo;

            Column c = new Column();
            c.setColumnTitle("Organization Hierarchy");
            c.setPropertyValue(org.getOrganizationHierarchy());
            columns.add(c);

            c = new Column();
            c.setColumnTitle("Organization Review Hierarchy");
            c.setPropertyValue("run search");
            c.setPropertyURL(org.getOrganizationReviewHierarchy());
            columns.add(c);
        }
    }


}
    
