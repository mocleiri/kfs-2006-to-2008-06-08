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
package org.kuali.module.labor.util;

import static org.kuali.module.gl.bo.OriginEntrySource.LABOR_SCRUBBER_VALID;
import static org.kuali.module.gl.bo.OriginEntrySource.LABOR_BACKUP;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DateTimeService;
import org.kuali.core.service.PersistenceService;
import org.kuali.module.gl.bo.OriginEntry;
import org.kuali.module.gl.bo.OriginEntryGroup;
import org.kuali.module.gl.service.OriginEntryGroupService;
import org.kuali.module.gl.web.TestDataGenerator;
import org.kuali.module.labor.bo.LaborLedgerPendingEntry;
import org.kuali.module.labor.bo.LaborOriginEntry;
import org.kuali.module.labor.bo.LedgerEntry;
import org.kuali.module.labor.service.LaborOriginEntryService;
import org.kuali.module.labor.service.impl.LaborScrubberProcess;
import org.kuali.module.labor.util.testobject.PendingLedgerEntryForTesting;

public class TestDataLoader {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(LaborScrubberProcess.class);
    
    private Properties properties;
    private String fieldNames;
    private String fieldLength;
    private String deliminator;

    private List<String> keyFieldList;
    private List<String> fieldLengthList;

    private BusinessObjectService businessObjectService;
    private LaborOriginEntryService laborOriginEntryService;

    public TestDataLoader() {
        String messageFileName = "test/src/org/kuali/module/labor/testdata/message.properties";
        String propertiesFileName = "test/src/org/kuali/module/labor/testdata/laborTransaction2.properties";

        properties = (new TestDataGenerator(propertiesFileName, messageFileName)).getProperties();
        fieldNames = properties.getProperty("fieldNames");
        fieldLength = properties.getProperty("fieldLength");
        deliminator = properties.getProperty("deliminator");
        
        LaborSpringContext.initializeApplicationContext();
        keyFieldList = Arrays.asList(StringUtils.split(fieldNames, deliminator));
        fieldLengthList = Arrays.asList(StringUtils.split(fieldLength, deliminator));
        businessObjectService = LaborSpringContext.getBean(BusinessObjectService.class);

        laborOriginEntryService = LaborSpringContext.getBean(LaborOriginEntryService.class);
    }

    public int loadTransactionIntoPendingEntryTable() {
        int numberOfInputData = Integer.valueOf(properties.getProperty("numOfData"));
        int[] fieldLength = this.getFieldLength(fieldLengthList);
        return this.loadInputData("data", numberOfInputData, keyFieldList, fieldLength);
    }

    public int loadTransactionIntoOriginEntryTable(OriginEntryGroup group) {
        int numberOfInputData = Integer.valueOf(properties.getProperty("numOfData"));

        Date today = LaborSpringContext.getBean(DateTimeService.class).getCurrentSqlDate();
        group.setDate(today);
        businessObjectService.save(group);

        int[] fieldLength = this.getFieldLength(fieldLengthList);
        List<LaborOriginEntry> originEntries = this.loadInputData(LaborOriginEntry.class, "data", numberOfInputData, keyFieldList, fieldLength);
        for (LaborOriginEntry entry : originEntries) {
            entry.setEntryGroupId(group.getId());
        }

        businessObjectService.save(originEntries);
        return originEntries.size();
    }

    private int loadInputData(String propertyKeyPrefix, int numberOfInputData, List<String> keyFieldList, int[] fieldLength) {
        int count = 0;
        for (int i = 1; i <= numberOfInputData; i++) {
            String propertyKey = propertyKeyPrefix + i;
            PendingLedgerEntryForTesting inputData = new PendingLedgerEntryForTesting();
            ObjectUtil.populateBusinessObject(inputData, properties, propertyKey, fieldLength, keyFieldList);

            if (businessObjectService.countMatching(LaborLedgerPendingEntry.class, inputData.getPrimaryKeyMap()) <= 0) {
                businessObjectService.save(inputData);
                count++;
            }
        }
        return count;
    }

    private List loadInputData(Class clazz, String propertyKeyPrefix, int numberOfInputData, List<String> keyFieldList, int[] fieldLength) {
        List inputDataList = new ArrayList();
        for (int i = 1; i <= numberOfInputData; i++) {
            String propertyKey = propertyKeyPrefix + i;
            try {
                Object inputData = clazz.newInstance();
                ObjectUtil.populateBusinessObject(inputData, properties, propertyKey, fieldLength, keyFieldList);

                inputDataList.add(inputData);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return inputDataList;
    }

    private int[] getFieldLength(List<String> fieldLengthList) {
        int[] fieldLengthArray = new int[fieldLengthList.size()];
        for (int i = 0; i < fieldLengthArray.length; i++) {
            fieldLengthArray[i] = Integer.valueOf(fieldLengthList.get(i).trim());
        }
        return fieldLengthArray;
    }

    public static void main(String[] args) {
        TestDataLoader testDataLoader = new TestDataLoader();
        
        if(ArrayUtils.contains(args, "poster")){
            OriginEntryGroup group = new OriginEntryGroup();
            group.setSourceCode(LABOR_SCRUBBER_VALID);
            group.setValid(true);
            group.setScrub(false);
            group.setProcess(true); 
            int numOfData = testDataLoader.loadTransactionIntoOriginEntryTable(group);
            System.out.println("Number of Origin Entries for Poster = " + numOfData);
        }
        
        if(ArrayUtils.contains(args, "scrubber")){
            OriginEntryGroup group = new OriginEntryGroup();
            group.setSourceCode(LABOR_BACKUP);
            group.setValid(true);
            group.setScrub(true);
            group.setProcess(true);
            int numOfData = testDataLoader.loadTransactionIntoOriginEntryTable(group);
            System.out.println("Number of Origin Entries for Scrubber = " + numOfData);
        }
        
        if(ArrayUtils.contains(args, "pending")){
            int numOfData = testDataLoader.loadTransactionIntoPendingEntryTable();
            System.out.println("Number of Pending Entries = " + numOfData);
        }
        System.exit(0);
    }
}
