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
package org.kuali.core.service;

import org.kuali.core.bo.HomeOrigination;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.test.KualiTestBase;
import org.kuali.test.WithTestSpringContext;

/**
 * This class tests the Home Origination service.
 */
@WithTestSpringContext
public class HomeOriginationServiceTest extends KualiTestBase {
    private HomeOriginationService homeOriginationService;

    protected void setUp() throws Exception {
        super.setUp();
        homeOriginationService = SpringServiceLocator.getHomeOriginationService();
    }

    public void testGetHomeOrigination() {
        HomeOrigination homeOrigination = homeOriginationService.getHomeOrigination();
        assertNotNull("Home Origination object cannot be retrieved", homeOrigination);
        assertEquals("Home Origination Code should be 01", "01", homeOrigination.getFinSystemHomeOriginationCode());
    }
}
