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

import java.io.Serializable;

import org.kuali.exceptions.IllegalObjectStateException;
import org.kuali.validation.ValidationErrorList;


/**
 * This is the generic class which contains all the elements on a 
 * typical line of accounting elements.  In other words, these are 
 * all the items necessary to evaluate and transform into an actual 
 * transaction to the GL.
 * 
 * Most transactional documents should be able to use it as is.  At most
 * it can be extended to do process/document specific line-level validations.
 * @author ahollamona
 */
public class OffsetDefinition extends BusinessObjectBase implements Serializable {

    private Integer universityFiscalYear;
    private String chartOfAccountsCode;
    private String documentTypeCode;
    private String balanceTypeCode;
    private ObjectCode objectCode;
    private String subObjectCode;

    /**
     * @return Returns the chartOfAccountsCode.
     */
    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }
    /**
     * @param chartOfAccountsCode The chartOfAccountsCode to set.
     */
    public void setChartOfAccountsCode(String account) {
        this.chartOfAccountsCode = account;
    }
    /**
     * @return Returns the balanceTypeCode.
     */
    public String getBalanceTypeCode() {
        return balanceTypeCode;
    }
    /**
     * @param balanceTypeCode The balanceTypeCode to set.
     */
    public void setBalanceTypeCode(String balanceTypeCode) {
        this.balanceTypeCode = balanceTypeCode;
    }
    /**
     * @return Returns the documentTypeCode.
     */
    public String getDocumentTypeCode() {
        return documentTypeCode;
    }
    /**
     * @param documentTypeCode The documentTypeCode to set.
     */
    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }
    /**
     * @return Returns the universityFiscalYear.
     */
    public Integer getUniversityFiscalYear() {
        return universityFiscalYear;
    }
    /**
     * @param universityFiscalYear The universityFiscalYear to set.
     */
    public void setUniversityFiscalYear(Integer fiscalYear) {
        this.universityFiscalYear = fiscalYear;
    }
    /**
     * @return Returns the objectCode.
     */
    public ObjectCode getObjectCode() {
        return objectCode;
    }
    /**
     * @param objectCode The objectCode to set.
     */
    public void setObjectCode(ObjectCode objectCode) {
        this.objectCode = objectCode;
    }
    /**
     * @return Returns the subObjectCode.
     */
    public String getSubObjectCode() {
        return subObjectCode;
    }
    /**
     * @param subObjectCode The subObjectCode to set.
     */
    public void setSubObjectCode(String subObjectCode) {
        this.subObjectCode = subObjectCode;
    }
    /**
     * This method instructs the object to validate itself and 
     * all its dependent components.
     * 
     * In the specific case of the AccountingLineBase, all this does is 
     * call the validate() methods on all its component objects.
     * @throws IllegalObjectStateException
     */
    public void validate(ValidationErrorList errors) throws ValidationErrorList, IllegalObjectStateException {
        
        //	do validate() callbacks on all dependent objects
        errors.accumulateValidationErrors(objectCode,false,errors);
        
        //	throw any outstanding errors
        errors.throwErrors();
        
    }
    
}
