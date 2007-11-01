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
package org.kuali.module.chart.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.test.KualiTestBase;
import org.kuali.test.WithTestSpringContext;

@WithTestSpringContext
public class OrganizationRoutingModelTest extends KualiTestBase {

    OrganizationRoutingModel model = new OrganizationRoutingModel();

    private final static String MODEL_NAME = "junit-test";

    BusinessObjectService boService;

    protected void setUp() throws Exception {
        super.setUp();
        boService = SpringServiceLocator.getBusinessObjectService();
    }

    public void testSaveModel() {
        model.setOrganizationRoutingModelName(MODEL_NAME);
        model.setChartOfAccountsCode("BL");
        model.setOrganizationCode("AMUS");
        model.setAccountDelegateUniversalId("12345");
        model.setFinancialDocumentTypeCode("xx");

        boService.save(model);
        assertTrue(loadModel(MODEL_NAME));
    }

    public boolean loadModel(String name) {

        Map fieldValues = new HashMap();
        Collection<OrganizationRoutingModel> foundModel;
        fieldValues.put("ORG_RTNG_MDL_NM", name);

        foundModel = boService.findMatching(model.getClass(), fieldValues);

        List<DelegateChangeDocument> delegateChanges = new ArrayList();

        for (OrganizationRoutingModel model : foundModel) {
            delegateChanges.add(new DelegateChangeDocument(model));
        }

        return foundModel != null && foundModel.size() > 0;

    }


}
