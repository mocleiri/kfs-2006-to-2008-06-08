/*
 * Copyright (c) 2004, 2005 The National Association of College and University Business Officers,
 * Cornell University, Trustees of Indiana University, Michigan State University Board of Trustees,
 * Trustees of San Joaquin Delta College, University of Hawai'i, The Arizona Board of Regents on
 * behalf of the University of Arizona, and the r*smart group.
 * 
 * Licensed under the Educational Community License Version 1.0 (the "License"); By obtaining,
 * using and/or copying this Original Work, you agree that you have read, understand, and will
 * comply with the terms and conditions of the Educational Community License.
 * 
 * You may obtain a copy of the License at:
 * 
 * http://kualiproject.org/license.html
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.kuali.bo;

import org.apache.commons.lang.StringUtils;
import org.kuali.Constants;
import org.kuali.util.SpringServiceLocator;
import org.kuali.validation.ValidationErrorList;


/**
 * This is the business object for the ProjectCode financial entity.
 * Organization: The Kuali Project
 * 
 * @author Aaron Godert
 */
public class ProjectCode extends BusinessObjectBase {
    //attributes
    private String code;
    private String chartOfAccountsCode;
    private String organizationCode;
    private Long projectManagerKualiUserNumber;
    private String name;
    private boolean active;
    private String description;
    
    
    //referenced objects
    private Chart chart;
    private Organization organization;

    
    /**
     * Default no-args constructor
     *
     */
    public ProjectCode() {
    }
    
    /**
     * @return Returns the chartOfAccountsCode.
     */
    public Chart getChart() {
        return chart;
    }

    /**
     * @param chart
     *            The chart to set.
     */
    public void setChart(Chart chart) {
        this.chart = chart;
    }

    /**
     * @return Returns the organizationCode.
     */
    public Organization getOrganization() {
        return organization;
    }

    /**
     * @param organization
     *            The organization to set.
     */
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
    
    /**
     * @return Returns whether the project code is active or not.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets whether or not the project code is active.
     * 
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return Returns the project code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            The project code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return Returns the project manager's Kuali user number.
     */
    public Long getProjectManagerKualiUserNumber() {
        return projectManagerKualiUserNumber;
    }

    /**
     * @param projectManager
     *            The project manager's Kuali user number to set.
     */
    public void setProjectManagerKualiUserNumber(Long projectManager) {
        this.projectManagerKualiUserNumber = projectManager;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Returns the project's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            The project name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the chartOfAccountsCode.
     */
    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }

    /**
     * @param chartOfAccountsCode
     *            The chartOfAccountsCode to set.
     */
    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }

    /**
     * @return Returns the organizationCode.
     */
    public String getOrganizationCode() {
        return organizationCode;
    }

    /**
     * @param organizationCode
     *            The organizationCode to set.
     */
    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    /**
     * The validation method checks existence if the code is the only thing
     * populated, otherwise it checks if all the values in the object are ready
     * to persist to the database.
     * 
     * @throws ValidationErrorList
     *             If any errors exist, a ValidationErrorList is thrown.
     */
    public void validate(ValidationErrorList errors) throws ValidationErrorList {
        validationNumber = errors.getNextValidationNumber();
        
        //	do not do validations if this is an empty object
        //	empty is defined by the primary key(s) having no data
        if (StringUtils.isEmpty(code)) {
            return;
        }

        // So, try to test for existence in the DB
        ProjectCode projectCode = SpringServiceLocator.getProjectCodeService().getByPrimaryId(this.code);
        if (projectCode == null) {
            errors.addError(validationNumber,Constants.CODE_PROPERTY_NAME,Constants.ERROR_FIELD_EXISTENCE);
        }

        //check to see if any errors were found and deal with them
        errors.throwErrors();
    }

}