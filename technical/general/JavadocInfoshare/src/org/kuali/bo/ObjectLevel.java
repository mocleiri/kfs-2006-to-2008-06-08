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

import org.kuali.Constants;
import org.kuali.service.ObjectLevelService;
import org.kuali.util.SpringServiceLocator;
import org.kuali.validation.ValidationError;
import org.kuali.validation.ValidationErrorList;

/**
 * @author rolstad ObjectLevel Description:
 */
public class ObjectLevel extends BusinessObjectBase {

    private String chartOfAccountsCode;

    private String objectLevelCode;

    private String objectLevelName;

    private String objectLevelShortName;

    private String consolidatedObjectCode;

    private String objectLevelActivationCode;

    private String reportSortCode;
    
    /**
     * Static method to support spring-injection of the service
     * 
     * @return Returns the ObjectCodeService.
     */
    public static ObjectLevelService getObjectLevelService() {
        return SpringServiceLocator.getObjectLevelService();
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
     * @return Returns the consolidatedObjectCode.
     */
    public String getConsolidatedObjectCode() {
        return consolidatedObjectCode;
    }

    /**
     * @param consolidatedObjectCode
     *            The consolidatedObjectCode to set.
     */
    public void setConsolidatedObjectCode(String consolidatedObjectCode) {
        this.consolidatedObjectCode = consolidatedObjectCode;
    }

    /**
     * @return Returns the objectLevelActivationCode.
     */
    public String getObjectLevelActivationCode() {
        return objectLevelActivationCode;
    }

    /**
     * @param objectLevelActivationCode
     *            The objectLevelActivationCode to set.
     */
    public void setObjectLevelActivationCode(String objectLevelActivationCode) {
        this.objectLevelActivationCode = objectLevelActivationCode;
    }

    /**
     * @return Returns the objectLevelCode.
     */
    public String getObjectLevelCode() {
        return objectLevelCode;
    }

    /**
     * @param objectLevelCode
     *            The objectLevelCode to set.
     */
    public void setObjectLevelCode(String objectLevelCode) {
        this.objectLevelCode = objectLevelCode;
    }

    /**
     * @return Returns the objectLevelName.
     */
    public String getObjectLevelName() {
        return objectLevelName;
    }

    /**
     * @param objectLevelName
     *            The objectLevelName to set.
     */
    public void setObjectLevelName(String objectLevelName) {
        this.objectLevelName = objectLevelName;
    }

    /**
     * @return Returns the objectLevelShortName.
     */
    public String getObjectLevelShortName() {
        return objectLevelShortName;
    }

    /**
     * @param objectLevelShortName
     *            The objectLevelShortName to set.
     */
    public void setObjectLevelShortName(String objectLevelShortName) {
        this.objectLevelShortName = objectLevelShortName;
    }

    /**
     * @return Returns the reportSortCode.
     */
    public String getReportSortCode() {
        return reportSortCode;
    }

    /**
     * @param reportSortCode
     *            The reportSortCode to set.
     */
    public void setReportSortCode(String reportSortCode) {
        this.reportSortCode = reportSortCode;
    }

	public void validate(ValidationErrorList errors) throws ValidationErrorList {
	    validationNumber = errors.getNextValidationNumber();

        ObjectLevel objectLevel = getObjectLevelService().getByPrimaryId(
                this.chartOfAccountsCode,
                this.objectLevelCode);
        if (objectLevel == null) {
            ValidationError error = new ValidationError(Constants.OBJECT_LEVEL_PROPERTY_NAME,Constants.ERROR_FIELD_EXISTENCE);
            errors.addError(validationNumber, error);
        }

        errors.throwErrors();
        
    }

}