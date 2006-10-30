/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/work/src/org/kuali/kfs/module/cg/document/web/struts/RoutingForm.java,v $
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
package org.kuali.module.kra.routingform.web.struts.form;

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.datadictionary.DataDictionary;
import org.kuali.core.datadictionary.DocumentEntry;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.core.web.struts.form.KualiDocumentFormBase;
import org.kuali.module.kra.routingform.bo.ResearchRiskType;
import org.kuali.module.kra.routingform.bo.RoutingFormInstitutionCostShare;
import org.kuali.module.kra.routingform.bo.RoutingFormKeyword;
import org.kuali.module.kra.routingform.bo.RoutingFormResearchRisk;
import org.kuali.module.kra.routingform.document.RoutingFormDocument;

public class RoutingForm extends KualiDocumentFormBase {
    
    private boolean auditActivated;
    private List<RoutingFormResearchRisk> newRoutingFormResearchRisks;
    private List<ResearchRiskType> researchRiskTypes;
    private RoutingFormKeyword newRoutingFormKeyword;
    private RoutingFormInstitutionCostShare newRoutingFormInstitutionCostShare;
    
    public RoutingForm() {
        super();
        newRoutingFormResearchRisks = new ArrayList<RoutingFormResearchRisk>();
       
        DataDictionary dataDictionary = SpringServiceLocator.getDataDictionaryService().getDataDictionary();
        DocumentEntry budgetDocumentEntry = dataDictionary.getDocumentEntry(org.kuali.module.kra.routingform.document.RoutingFormDocument.class);
        this.setHeaderNavigationTabs(budgetDocumentEntry.getHeaderTabNavigation());
        
        setDocument(new RoutingFormDocument());
    }
    
    public RoutingFormDocument getRoutingFormDocument(){
        return (RoutingFormDocument)this.getDocument();
    }
    
    public boolean isAuditActivated() {
        return auditActivated;
    }

    public void setAuditActivated(boolean auditActivated) {
        this.auditActivated = auditActivated;
    }

    public List<RoutingFormResearchRisk> getNewRoutingFormResearchRisks() {
        return newRoutingFormResearchRisks;
    }

    public void setNewRoutingFormResearchRisks(List<RoutingFormResearchRisk> newRoutingFormResearchRisks) {
        this.newRoutingFormResearchRisks = newRoutingFormResearchRisks;
    }

    public List<ResearchRiskType> getResearchRiskTypes() {
        return researchRiskTypes;
    }
    public RoutingFormKeyword getNewRoutingFormKeyword() {
        return newRoutingFormKeyword;
    }

    public void setResearchRiskTypes(List<ResearchRiskType> researchRiskTypes) {
        this.researchRiskTypes = researchRiskTypes;
    }
    
    public RoutingFormResearchRisk getNewRoutingFormResearchRisk(int index) {
        while (getNewRoutingFormResearchRisks().size() <= index) {
            getNewRoutingFormResearchRisks().add(new RoutingFormResearchRisk());
        }
        return (RoutingFormResearchRisk) getNewRoutingFormResearchRisks().get(index);
    }

    public void setNewRoutingFormKeyword(RoutingFormKeyword newRoutingFormKeyword) {
        this.newRoutingFormKeyword = newRoutingFormKeyword;
    }

    public void setNewRoutingFormInstitutionCostShare(RoutingFormInstitutionCostShare newRoutingFormInstitutionCostShare) {
        this.newRoutingFormInstitutionCostShare = newRoutingFormInstitutionCostShare;
    }

    public RoutingFormInstitutionCostShare getNewRoutingFormInstitutionCostShare() {
        return newRoutingFormInstitutionCostShare;
    }

}
