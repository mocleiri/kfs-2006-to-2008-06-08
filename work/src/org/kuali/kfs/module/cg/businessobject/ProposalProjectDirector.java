/*
 * Copyright 2006-2007 The Kuali Foundation.
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

package org.kuali.module.cg.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.module.kra.routingform.bo.RoutingFormPersonnel;

/**
 * 
 */
public class ProposalProjectDirector extends PersistableBusinessObjectBase {

    private String personUniversalIdentifier;
    private Long proposalNumber;
    private boolean proposalPrimaryProjectDirectorIndicator;
    private String proposalProjectDirectorProjectTitle;

    private ProjectDirector projectDirector;

    /**
     * Default constructor.
     */
    public ProposalProjectDirector() {

    }

    /**
     * Constructor from RoutingFormDocument.
     */
    public ProposalProjectDirector(RoutingFormPersonnel routingFormProjectDirector, Long proposalNumber, boolean primaryProjectDirector) {
        // todo: not set proposalNumber?  Doesn't OJB do this automatically?
        this.setProposalNumber(proposalNumber);
        this.setPersonUniversalIdentifier(routingFormProjectDirector.getPersonUniversalIdentifier());
        this.setProposalPrimaryProjectDirectorIndicator(primaryProjectDirector);
    }

    
    /**
     * Gets the personUniversalIdentifier attribute.
     * 
     * @return Returns the personUniversalIdentifier
     * 
     */
    public String getPersonUniversalIdentifier() {
        return personUniversalIdentifier;
    }

    /**
     * Sets the personUniversalIdentifier attribute.
     * 
     * @param personUniversalIdentifier The personUniversalIdentifier to set.
     * 
     */
    public void setPersonUniversalIdentifier(String personUniversalIdentifier) {
        this.personUniversalIdentifier = personUniversalIdentifier;
    }


    /**
     * Gets the proposalNumber attribute.
     * 
     * @return Returns the proposalNumber
     * 
     */
    public Long getProposalNumber() {
        return proposalNumber;
    }

    /**
     * Sets the proposalNumber attribute.
     * 
     * @param proposalNumber The proposalNumber to set.
     * 
     */
    public void setProposalNumber(Long proposalNumber) {
        this.proposalNumber = proposalNumber;
    }


    /**
     * Gets the proposalPrimaryProjectDirectorIndicator attribute.
     * 
     * @return Returns the proposalPrimaryProjectDirectorIndicator
     * 
     */
    public boolean isProposalPrimaryProjectDirectorIndicator() {
        return proposalPrimaryProjectDirectorIndicator;
    }


    /**
     * Sets the proposalPrimaryProjectDirectorIndicator attribute.
     * 
     * @param proposalPrimaryProjectDirectorIndicator The proposalPrimaryProjectDirectorIndicator to set.
     * 
     */
    public void setProposalPrimaryProjectDirectorIndicator(boolean proposalPrimaryProjectDirectorIndicator) {
        this.proposalPrimaryProjectDirectorIndicator = proposalPrimaryProjectDirectorIndicator;
    }


    /**
     * Gets the proposalProjectDirectorProjectTitle attribute.
     * 
     * @return Returns the proposalProjectDirectorProjectTitle
     * 
     */
    public String getProposalProjectDirectorProjectTitle() {
        return proposalProjectDirectorProjectTitle;
    }

    /**
     * Sets the proposalProjectDirectorProjectTitle attribute.
     * 
     * @param proposalProjectDirectorProjectTitle The proposalProjectDirectorProjectTitle to set.
     * 
     */
    public void setProposalProjectDirectorProjectTitle(String proposalProjectDirectorProjectTitle) {
        this.proposalProjectDirectorProjectTitle = proposalProjectDirectorProjectTitle;
    }

    /**
     * @return the projectDirector.
     */
    public ProjectDirector getProjectDirector() {
        return projectDirector;
    }

    /**
     * Sets the projectDirector.
     * @param projectDirector the projectDirector to set
     * @deprecated required by UniversalUserServiceImpl.isUniversalUserProperty() for PojoPropertyUtilsBean.getPropertyDescriptor()
     */
    public void setProjectDirector(ProjectDirector projectDirector) {
        this.projectDirector = projectDirector;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("personUniversalIdentifier", this.personUniversalIdentifier);
        if (this.proposalNumber != null) {
            m.put("proposalNumber", this.proposalNumber.toString());
        }
        return m;
    }
}
