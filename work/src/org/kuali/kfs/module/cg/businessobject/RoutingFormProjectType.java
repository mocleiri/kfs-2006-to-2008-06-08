/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/work/src/org/kuali/kfs/module/cg/businessobject/RoutingFormProjectType.java,v $
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

package org.kuali.module.kra.routingform.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.BusinessObjectBase;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class RoutingFormProjectType extends BusinessObjectBase {

	private String projectTypeCode;
	private String researchDocumentNumber;
	private String projectTypeDescription;

    private ProjectType projectType;
    
	/**
	 * Default constructor.
	 */
	public RoutingFormProjectType() {

	}

	/**
	 * Gets the projectTypeCode attribute.
	 * 
	 * @return - Returns the projectTypeCode
	 * 
	 */
	public String getProjectTypeCode() { 
		return projectTypeCode;
	}

	/**
	 * Sets the projectTypeCode attribute.
	 * 
	 * @param - projectTypeCode The projectTypeCode to set.
	 * 
	 */
	public void setProjectTypeCode(String projectTypeCode) {
		this.projectTypeCode = projectTypeCode;
	}


	/**
	 * Gets the researchDocumentNumber attribute.
	 * 
	 * @return - Returns the researchDocumentNumber
	 * 
	 */
	public String getResearchDocumentNumber() { 
		return researchDocumentNumber;
	}

	/**
	 * Sets the researchDocumentNumber attribute.
	 * 
	 * @param - researchDocumentNumber The researchDocumentNumber to set.
	 * 
	 */
	public void setResearchDocumentNumber(String researchDocumentNumber) {
		this.researchDocumentNumber = researchDocumentNumber;
	}


	/**
	 * Gets the projectTypeDescription attribute.
	 * 
	 * @return - Returns the projectTypeDescription
	 * 
	 */
	public String getProjectTypeDescription() { 
		return projectTypeDescription;
	}

	/**
	 * Sets the projectTypeDescription attribute.
	 * 
	 * @param - projectTypeDescription The projectTypeDescription to set.
	 * 
	 */
	public void setProjectTypeDescription(String projectTypeDescription) {
		this.projectTypeDescription = projectTypeDescription;
	}

    /**
     * Gets the projectType attribute. 
     * @return Returns the projectType.
     */
    public ProjectType getProjectType() {
        return projectType;
    }

    /**
     * Sets the projectType attribute value.
     * @param projectType The projectType to set.
     * @deprecated
     */
    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    /**
     * @see org.kuali.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();      
        m.put("projectTypeCode", this.projectTypeCode);
        m.put("researchDocumentNumber", this.researchDocumentNumber);
        return m;
    }


}
