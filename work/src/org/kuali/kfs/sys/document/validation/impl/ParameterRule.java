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
package org.kuali.kfs.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.bo.Parameter;
import org.kuali.core.bo.ParameterDetailType;
import org.kuali.core.datadictionary.DataDictionary;
import org.kuali.core.datadictionary.DocumentEntry;
import org.kuali.core.datadictionary.TransactionalDocumentEntry;
import org.kuali.core.document.MaintenanceDocument;
import org.kuali.core.service.DataDictionaryService;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kfs.batch.Step;
import org.kuali.kfs.context.SpringContext;

public class ParameterRule extends org.kuali.core.rules.ParameterRule {

    private static ArrayList<String> ddComponentNames = new ArrayList<String>();
    private static ArrayList<String[]> stepNames = new ArrayList<String[]>();
    
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        boolean result = super.processCustomRouteDocumentBusinessRules( document );

        result &= checkComponent( (Parameter)getNewBo() );

        return result;
    }
    
    protected boolean checkComponent( Parameter param ) {
        String component = param.getParameterDetailTypeCode();
        String namespace = param.getParameterNamespaceCode();
        boolean result = false;
        
        // check the DD
        if ( ddComponentNames.isEmpty() ) {
            initComponents();
        }
        result = ddComponentNames.contains(component);
        if ( !result ) {
            for ( String[] step : stepNames ) {
                if ( step[0].equals(namespace) && step[1].equals(component) ) {
                    result = true;
                    break;
                }
            }
        }        
        
        // check the table
        if ( !result ) {
            Map<String,String> primaryKeys = new HashMap<String, String>( 2 );
            primaryKeys.put("parameterNamespaceCode", namespace);
            primaryKeys.put("parameterDetailTypeCode", component);
            result = ObjectUtils.isNotNull( getBoService().findByPrimaryKey(ParameterDetailType.class, primaryKeys) );
        }
        
        if ( !result ) {
            putFieldError( "parameterDetailTypeCode", "error.document.parameter.detailType.invalid", component );            
        }
        
        return result;
    }
    
    private synchronized void initComponents() {
        if ( ddComponentNames.isEmpty() ) {
            DataDictionaryService dds = SpringContext.getBean( DataDictionaryService.class );
            dds.getDataDictionary().forceCompleteDataDictionaryLoad();
            DataDictionary dd = dds.getDataDictionary();
            for ( String boClassName : dd.getBusinessObjectClassNames() ) {
                String simpleName = StringUtils.substringAfterLast(boClassName, ".");
                if ( StringUtils.isBlank( simpleName ) ) continue;
                ddComponentNames.add( simpleName );
            }
            Map<String,DocumentEntry> ddDocuments = dd.getDocumentEntries();
            for ( String transDocName : ddDocuments.keySet() ) {
                if ( StringUtils.isBlank( transDocName ) ) continue;
                DocumentEntry doc = ddDocuments.get(transDocName);
                if ( doc instanceof TransactionalDocumentEntry ) {
                    ddComponentNames.add( doc.getDocumentTypeName() );
                }
            }
            Map<String,Step> steps = SpringContext.getBeansOfType(Step.class);
            for ( String stepName : steps.keySet() ) {
                stepNames.add( new String[] { steps.get(stepName).getNamespace(), steps.get(stepName).getComponentName() } );
            }            
        }
    }
}
