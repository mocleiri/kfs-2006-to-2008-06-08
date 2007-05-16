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
package org.kuali.module.financial.batch;

import java.io.File;
import java.util.Collection;

import org.kuali.kfs.util.SpringServiceLocator;
import org.kuali.module.financial.batch.pcard.PcdoLoadStep;
import org.kuali.module.financial.bo.ProcurementCardTransaction;
import org.kuali.test.KualiTestBase;
import org.kuali.test.WithTestSpringContext;

/**
 * Tests the PcdoLoadStep.
 * DEPENDENCIES:
 * 
 * Procurement card xml file transaction1.xml must be in /opt/kuali/dev/staging/PCDO/
 * this file can be obtained by running the project's ant dist-local, or copying from
 * build/externalConfigDirectory/static/staging/PCDO/
 */
@WithTestSpringContext
public class PcdoLoadStepTest extends KualiTestBase {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PcdoLoadStepTest.class);
    
    public PcdoLoadStepTest() {
        super();
    }

    /**
     * Creats .done file for test input file.
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        String doneFileName = SpringServiceLocator.getProcurementCardInputFileType().getDirectoryPath() + "/transactions1.done";
        File doneFile = new File(doneFileName);
        if (!doneFile.exists()) {
            doneFile.createNewFile();
        }
    }
    
    /**
     * Tests the whole step completes successfully.
     */
    public void testAll() throws Exception {
        PcdoLoadStep pcdoLoadStep = SpringServiceLocator.getPcdoLoadStep();
        boolean goodExit = pcdoLoadStep.execute();
        
        assertTrue("pcdo load step did not exit with pass", goodExit);
        
        Collection loadedTransactions = SpringServiceLocator.getBusinessObjectService().findAll(ProcurementCardTransaction.class);
        assertNotNull("no transactions loaded ", loadedTransactions);
        assertEquals("incorrect number of transactions loaded ",10,loadedTransactions.size());
    }
}
