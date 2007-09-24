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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.KualiModule;
import org.kuali.core.bo.BusinessObject;
import org.kuali.core.bo.ParameterDetailType;
import org.kuali.core.datadictionary.DataDictionary;
import org.kuali.core.datadictionary.DocumentEntry;
import org.kuali.core.datadictionary.TransactionalDocumentEntry;
import org.kuali.core.lookup.CollectionIncomplete;
import org.kuali.core.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.core.lookup.LookupUtils;
import org.kuali.core.service.KualiModuleService;
import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.batch.Step;
import org.kuali.kfs.context.SpringContext;

public class ParameterDetailTypeLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger( ParameterDetailTypeLookupableHelperServiceImpl.class );
    
    private static ArrayList<ParameterDetailType> components = new ArrayList<ParameterDetailType>(); 

    private KualiModuleService kualiModuleService;
    
    @Override
    public List<? extends BusinessObject> getSearchResults(java.util.Map<String,String> fieldValues) {

        List<BusinessObject> baseLookup = (List<BusinessObject>)super.getSearchResults(fieldValues);
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
                if ( StringUtils.isBlank( simpleName ) ) continue;
                ParameterDetailType pdt = null;
                try {
                    KualiModule km = kualiModuleService.getResponsibleModule(Class.forName(boClassName));
                    if ( km != null ) {
                        pdt = new ParameterDetailType( KFSConstants.KFS_NAMESPACE_PREFIX + km.getModuleCode(), simpleName, simpleName );
                    } else {
                        if ( boClassName.startsWith( "org.kuali.core" ) ) {
                            pdt = new ParameterDetailType( KFSConstants.CORE_NAMESPACE, simpleName, simpleName );
                        } else if ( boClassName.startsWith( "org.kuali.kfs" ) ) {
                            pdt = new ParameterDetailType( KFSConstants.KFS_SYSTEM_NAMESPACE, simpleName, simpleName );
                        } else {
                            LOG.error( "Unable to determine module for: " + boClassName );
                            pdt = new ParameterDetailType( "N/A", simpleName, simpleName );
                        }
                    }
                } catch ( ClassNotFoundException ex ) {
                    LOG.error( "Unable to convert string to class for BO: " + boClassName, ex );
                    pdt = new ParameterDetailType( "N/A", simpleName, simpleName );
                }
                if ( pdt != null ) {
                    pdt.refreshNonUpdateableReferences();
                    components.add( pdt );
                }
            }
            Map<String,DocumentEntry> ddDocuments = dd.getDocumentEntries();
            for ( String transDocName : ddDocuments.keySet() ) {
                if ( StringUtils.isBlank( transDocName ) ) continue;
                DocumentEntry doc = ddDocuments.get(transDocName);
                if ( doc instanceof TransactionalDocumentEntry ) {
                    ParameterDetailType pdt = null;
                    KualiModule km = kualiModuleService.getResponsibleModule( doc.getDocumentClass() );
                    if ( km != null ) {
                        pdt = new ParameterDetailType( KFSConstants.KFS_NAMESPACE_PREFIX + km.getModuleCode(), transDocName, doc.getLabel() );
                    } else {
                        if ( doc.getDocumentClass().getName().startsWith( "org.kuali.core" ) ) {
                            pdt = new ParameterDetailType( KFSConstants.CORE_NAMESPACE, transDocName, doc.getLabel() );
                        } else if ( doc.getDocumentClass().getName().startsWith( "org.kuali.kfs" ) ) {
                            pdt = new ParameterDetailType( KFSConstants.KFS_SYSTEM_NAMESPACE, transDocName, doc.getLabel() );
                        } else {
                            LOG.error( "Unable to determine module for: " + doc.getDocumentClass().getName() );
                            pdt = new ParameterDetailType( "N/A", transDocName, doc.getLabel() );
                        }
                    }
                    if ( pdt != null ) {
                        pdt.refreshNonUpdateableReferences();
                        components.add( pdt );
                    }
                }
            }
            Map<String,Step> steps = SpringContext.getBeansOfType(Step.class);
            for ( String stepName : steps.keySet() ) {
                components.add( new ParameterDetailType( steps.get(stepName).getNamespace(), steps.get(stepName).getComponentName(), stepName ) );
            }
        }
        
        String activeCheck = fieldValues.get("active");
        if ( activeCheck == null ) {
            activeCheck = "";
        }
        int maxResultsCount = LookupUtils.getApplicationSearchResultsLimit();
        // only bother with the component lookup if returning active components
        if ( baseLookup instanceof CollectionIncomplete && !activeCheck.equals( "N" ) ) {
            long originalCount = Math.max(baseLookup.size(), ((CollectionIncomplete)baseLookup).getActualSizeIfTruncated() );
            long totalCount = originalCount;
            Pattern detailTypeRegex = null;
            Pattern namespaceRegex = null;
            Pattern nameRegex = null;
            
            if ( StringUtils.isNotBlank( fieldValues.get("parameterDetailTypeCode") ) ) {
                String patternStr = fieldValues.get("parameterDetailTypeCode").replace("*", ".*").toUpperCase();
                try {
                    detailTypeRegex = Pattern.compile(patternStr);
                } catch ( PatternSyntaxException ex ) {
                    LOG.error( "Unable to parse parameterDetailTypeCode pattern, ignoring.", ex );
                }                
            }
            if ( StringUtils.isNotBlank( fieldValues.get("parameterNamespaceCode") ) ) {
                String patternStr = fieldValues.get("parameterNamespaceCode").replace("*", ".*").toUpperCase();
                try {
                    namespaceRegex = Pattern.compile(patternStr);
                } catch ( PatternSyntaxException ex ) {
                    LOG.error( "Unable to parse parameterNamespaceCode pattern, ignoring.", ex );
                }                
            }
            if ( StringUtils.isNotBlank( fieldValues.get("parameterDetailTypeName") ) ) {
                String patternStr = fieldValues.get("parameterDetailTypeName").replace("*", ".*").toUpperCase();
                try {
                    nameRegex = Pattern.compile(patternStr);
                } catch ( PatternSyntaxException ex ) {
                    LOG.error( "Unable to parse parameterDetailTypeName pattern, ignoring.", ex );
                }                
            }
            for ( ParameterDetailType pdt : components ) {
                boolean includeType = true;
                if ( detailTypeRegex != null ) {
                    includeType = detailTypeRegex.matcher( pdt.getParameterDetailTypeCode().toUpperCase() ).matches();
                }
                if ( includeType && namespaceRegex != null ) {
                    includeType = namespaceRegex.matcher( pdt.getParameterNamespaceCode().toUpperCase() ).matches();
                }
                if ( includeType && nameRegex != null ) {
                    includeType = nameRegex.matcher( pdt.getParameterDetailTypeName().toUpperCase() ).matches();
                }
                if ( includeType ) {
                    if ( totalCount < maxResultsCount ) {
                        baseLookup.add( pdt );
                    }
                    totalCount++;
                }
            }
            if ( totalCount > maxResultsCount ) {
                ((CollectionIncomplete)baseLookup).setActualSizeIfTruncated(totalCount);
            } else {
                ((CollectionIncomplete)baseLookup).setActualSizeIfTruncated(0L);
            }
        }
        
        return baseLookup;
    }

    public KualiModuleService getKualiModuleService() {
        return kualiModuleService;
    }

    public void setKualiModuleService(KualiModuleService kualiModuleService) {
        this.kualiModuleService = kualiModuleService;
    }
}
