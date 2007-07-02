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
package org.kuali.module.labor.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kfs.KFSPropertyConstants;
import org.kuali.kfs.context.KualiTestBase;
import org.kuali.kfs.util.SpringServiceLocator;
import org.kuali.module.gl.web.TestDataGenerator;
import org.kuali.module.labor.bo.LaborGeneralLedgerEntry;
import org.kuali.module.labor.util.ObjectUtil;
import org.kuali.test.WithTestSpringContext;

@WithTestSpringContext
public class LaborGeneralLedgerEntryServiceTest extends KualiTestBase {

    private Properties properties;
    private String fieldNames;
    private String deliminator;
    private List<String> keyFieldList;

    private LaborGeneralLedgerEntryService laborGeneralLedgerEntryService;
    private BusinessObjectService businessObjectService;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        String messageFileName = "test/src/org/kuali/module/labor/testdata/message.properties";
        String propertiesFileName = "test/src/org/kuali/module/labor/testdata/laborGeneralLedgerEntryService.properties";

        properties = (new TestDataGenerator(propertiesFileName, messageFileName)).getProperties();
        fieldNames = properties.getProperty("fieldNames");
        deliminator = properties.getProperty("deliminator");
        keyFieldList = Arrays.asList(StringUtils.split(fieldNames, deliminator));

        laborGeneralLedgerEntryService = (LaborGeneralLedgerEntryService) SpringServiceLocator.getService("laborGeneralLedgerEntryService");
        businessObjectService = (BusinessObjectService) SpringServiceLocator.getService("businessObjectService");
    }

    public void testSave() throws Exception {
        LaborGeneralLedgerEntry input1 = new LaborGeneralLedgerEntry();
        ObjectUtil.populateBusinessObject(input1, properties, "save.testData1", fieldNames, deliminator);

        LaborGeneralLedgerEntry expected1 = new LaborGeneralLedgerEntry();
        ObjectUtil.populateBusinessObject(expected1, properties, "save.expected1", fieldNames, deliminator);
        Map fieldValues = ObjectUtil.buildPropertyMap(expected1, keyFieldList);

        businessObjectService.deleteMatching(LaborGeneralLedgerEntry.class, fieldValues);
        assertEquals(0, businessObjectService.countMatching(LaborGeneralLedgerEntry.class, fieldValues));

        laborGeneralLedgerEntryService.save(input1);
        assertEquals(1, businessObjectService.countMatching(LaborGeneralLedgerEntry.class, fieldValues));

        LaborGeneralLedgerEntry input2 = new LaborGeneralLedgerEntry();
        ObjectUtil.populateBusinessObject(input2, properties, "save.testData2", fieldNames, deliminator);
        try {
            laborGeneralLedgerEntryService.save(input2);
            fail();
        }
        catch (Exception e) {
        }
    }

    public void testGetMaxSequenceNumber() throws Exception {
        LaborGeneralLedgerEntry input1 = new LaborGeneralLedgerEntry();
        ObjectUtil.populateBusinessObject(input1, properties, "maxSeqNumber.testData1", fieldNames, deliminator);

        Map fieldValues = ObjectUtil.buildPropertyMap(input1, keyFieldList);
        fieldValues.remove(KFSPropertyConstants.TRANSACTION_ENTRY_SEQUENCE_NUMBER);
        businessObjectService.deleteMatching(LaborGeneralLedgerEntry.class, fieldValues);

        Integer maxSeqNumber = laborGeneralLedgerEntryService.getMaxSequenceNumber(input1);
        assertEquals(Integer.valueOf(0), maxSeqNumber);

        LaborGeneralLedgerEntry LaborGeneralLedgerEntryExpected1 = new LaborGeneralLedgerEntry();
        String expectedSeqNumber1 = properties.getProperty("maxSeqNumber.expected1");

        laborGeneralLedgerEntryService.save(input1);
        maxSeqNumber = laborGeneralLedgerEntryService.getMaxSequenceNumber(input1);
        assertEquals(Integer.valueOf(expectedSeqNumber1), maxSeqNumber);

        LaborGeneralLedgerEntry input2 = new LaborGeneralLedgerEntry();
        ObjectUtil.populateBusinessObject(input2, properties, "maxSeqNumber.testData2", fieldNames, deliminator);

        LaborGeneralLedgerEntry expected2 = new LaborGeneralLedgerEntry();
        String expectedSeqNumber2 = properties.getProperty("maxSeqNumber.expected2");

        laborGeneralLedgerEntryService.save(input2);
        maxSeqNumber = laborGeneralLedgerEntryService.getMaxSequenceNumber(input1);
        assertEquals(Integer.valueOf(expectedSeqNumber2), maxSeqNumber);

        maxSeqNumber = laborGeneralLedgerEntryService.getMaxSequenceNumber(input2);
        assertEquals(Integer.valueOf(expectedSeqNumber2), maxSeqNumber);
    }
}
