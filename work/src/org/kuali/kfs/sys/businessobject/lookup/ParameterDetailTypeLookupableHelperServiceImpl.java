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
package org.kuali.kfs.lookup;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.bo.BusinessObject;
import org.kuali.core.datadictionary.DataDictionary;
import org.kuali.core.datadictionary.DocumentEntry;
import org.kuali.core.datadictionary.TransactionalDocumentEntry;
import org.kuali.core.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.kfs.batch.Step;
import org.kuali.kfs.context.SpringContext;

public class ParameterDetailTypeLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {

    private static TreeMap<String,String> components = new TreeMap<String, String>(); 
    
    @Override
    public List<? extends BusinessObject> getSearchResults(java.util.Map<String,String> fieldValues) {

        List<? extends BusinessObject> baseLookup = super.getSearchResults(fieldValues);
        getDataDictionaryService().getDataDictionary().forceCompleteDataDictionaryLoad();
        
        // all step beans
        // all BO beans
        // all trans doc beans 
        
        if ( components.isEmpty() ) {
            // get all components from the DD and make a list.
            // hold statically since it won't change after DD load is complete
            DataDictionary dd = getDataDictionaryService().getDataDictionary();
            for ( String boClassName : dd.getBusinessObjectClassNames() ) {
                String simpleName = StringUtils.substringAfterLast(boClassName, ".");
                //simpleName = simpleName.replace( "([^A-Z][A-Z])", "_\\1" ).toUpperCase();
                components.put( simpleName, dd.getBusinessObjectEntry(boClassName).getObjectLabel() );
            }
            Map<String,DocumentEntry> ddDocuments = dd.getDocumentEntries();
            for ( String transDocName : ddDocuments.keySet() ) {
                DocumentEntry doc = ddDocuments.get(transDocName);
                if ( doc instanceof TransactionalDocumentEntry ) {
                    components.put(transDocName, doc.getLabel());
                }
            }
            Map<String,Step> steps = SpringContext.getBeansOfType(Step.class);
            for ( String stepName : steps.keySet() ) {
                components.put(stepName, stepName );
            }
        }
        // TODO: incomplete at present
        return baseLookup;
    }
}
