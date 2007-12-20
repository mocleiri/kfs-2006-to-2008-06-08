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
package org.kuali.module.labor.batch.poster;

import static org.kuali.module.gl.bo.OriginEntrySource.LABOR_MAIN_POSTER_VALID;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DateTimeService;
import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.context.KualiTestBase;
import org.kuali.kfs.context.SpringContext;
import org.kuali.module.gl.batch.poster.PostTransaction;
import org.kuali.module.gl.bo.OriginEntryGroup;
import org.kuali.module.gl.service.OriginEntryGroupService;
import org.kuali.module.gl.web.TestDataGenerator;
import org.kuali.module.labor.bo.LaborGeneralLedgerEntry;
import org.kuali.module.labor.bo.LaborOriginEntry;
import org.kuali.module.labor.service.LaborGeneralLedgerEntryService;
import org.kuali.module.labor.util.ObjectUtil;
import org.kuali.module.labor.util.TestDataPreparator;
import org.kuali.test.ConfigureContext;

@ConfigureContext
public class LaborGLLedgerEntryPosterTest extends KualiTestBase {

    private Properties properties;
    private String fieldNames;
    private String deliminator;
    private List<String> keyFieldList;
    private Map fieldValues;
    private OriginEntryGroup group1;
    private Date today;

    private BusinessObjectService businessObjectService;
    private PostTransaction laborGLLedgerEntryPoster;
    private OriginEntryGroupService originEntryGroupService;
    private LaborGeneralLedgerEntryService laborGeneralLedgerEntryService;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        String messageFileName = "test/src/org/kuali/module/labor/testdata/message.properties";
        String propertiesFileName = "test/src/org/kuali/module/labor/testdata/laborGLLedgerEntryPoster.properties";

        properties = (new TestDataGenerator(propertiesFileName, messageFileName)).getProperties();
        fieldNames = properties.getProperty("fieldNames");
        deliminator = properties.getProperty("deliminator");
        keyFieldList = Arrays.asList(StringUtils.split(fieldNames, deliminator));

        laborGLLedgerEntryPoster = SpringContext.getBeansOfType(PostTransaction.class).get("laborGLLedgerEntryPoster");
        businessObjectService = SpringContext.getBean(BusinessObjectService.class);
        originEntryGroupService = SpringContext.getBean(OriginEntryGroupService.class);
        laborGeneralLedgerEntryService = SpringContext.getBean(LaborGeneralLedgerEntryService.class);
        DateTimeService dateTimeService = SpringContext.getBean(DateTimeService.class);

        group1 = originEntryGroupService.createGroup(dateTimeService.getCurrentSqlDate(), LABOR_MAIN_POSTER_VALID, false, false, false);
        today = dateTimeService.getCurrentDate();

        LaborGeneralLedgerEntry cleanup = new LaborGeneralLedgerEntry();
        ObjectUtil.populateBusinessObject(cleanup, properties, "dataCleanup", fieldNames, deliminator);
        fieldValues = ObjectUtil.buildPropertyMap(cleanup, Arrays.asList(StringUtils.split(fieldNames, deliminator)));
        businessObjectService.deleteMatching(LaborGeneralLedgerEntry.class, fieldValues);
    }

    public void testPost() throws Exception {
        int numberOfTestData = Integer.valueOf(properties.getProperty("post.numOfData"));
        int expectedMaxSequenceNumber = Integer.valueOf(properties.getProperty("post.expectedMaxSequenceNumber"));
        int expectedInsertion = Integer.valueOf(properties.getProperty("post.expectedInsertion"));

        List<LaborOriginEntry> transactionList = TestDataPreparator.getLaborOriginEntryList(properties, "post.testData", numberOfTestData, group1);
        Map<String, Integer> operationType = new HashMap<String, Integer>();

        for (LaborOriginEntry transaction : transactionList) {
            String operation = laborGLLedgerEntryPoster.post(transaction, 0, today);
            Integer currentNumber = operationType.get(operation);
            Integer numberOfOperation = currentNumber != null ? currentNumber + 1 : 1;
            operationType.put(operation, numberOfOperation);
        }

        Collection returnValues = businessObjectService.findMatching(LaborGeneralLedgerEntry.class, fieldValues);
        assertEquals(numberOfTestData, returnValues.size());

        assertEquals(1, operationType.size());
        assertEquals(expectedInsertion, operationType.get(KFSConstants.OperationType.INSERT).intValue());

        LaborGeneralLedgerEntry expected1 = new LaborGeneralLedgerEntry();
        ObjectUtil.populateBusinessObject(expected1, properties, "post.expected1", fieldNames, deliminator);
        assertEquals(expectedMaxSequenceNumber, laborGeneralLedgerEntryService.getMaxSequenceNumber(expected1).intValue());

        LaborGeneralLedgerEntry expected2 = new LaborGeneralLedgerEntry();
        ObjectUtil.populateBusinessObject(expected2, properties, "post.expected2", fieldNames, deliminator);
        assertEquals(expectedMaxSequenceNumber, laborGeneralLedgerEntryService.getMaxSequenceNumber(expected2).intValue());

        LaborGeneralLedgerEntry expected3 = new LaborGeneralLedgerEntry();
        ObjectUtil.populateBusinessObject(expected3, properties, "post.expected3", fieldNames, deliminator);
        assertEquals(expectedMaxSequenceNumber, laborGeneralLedgerEntryService.getMaxSequenceNumber(expected3).intValue());
    }
}