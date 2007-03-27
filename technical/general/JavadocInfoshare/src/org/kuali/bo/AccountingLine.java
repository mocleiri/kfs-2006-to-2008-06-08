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

import org.kuali.exceptions.IllegalObjectStateException;
import org.kuali.util.KualiDecimal;
import org.kuali.validation.ValidationErrorList;

/**
 * The interface for an accounting line structure.
 * 
 * @author Nervous System Team (kualidev@oncourse.iu.edu)
 */
public interface AccountingLine {
    /**
     * This method instructs the object to validate itself and all its dependent
     * components.
     * 
     * In the specific case of the AccountingLineBase, all this does is call the
     * validate() methods on all its component objects.
     * 
     * @throws IllegalObjectStateException
     */
    public abstract void validate(ValidationErrorList errors) throws ValidationErrorList, IllegalObjectStateException;

    public abstract void validateForPersistence() throws ValidationErrorList;

    /**
     * @return Returns the account.
     */
    public abstract Account getAccount();

    /**
     * @param account The account to set.
     */
    public abstract void setAccount(Account account);

    /**
     * @return Returns the chartOfAccountsCode.
     */
    public abstract Chart getChart();

    /**
     * @param chartOfAccountsCode The chartOfAccountsCode to set.
     */
    public abstract void setChart(Chart chart);

    /**
     * @return Returns the documentHeaderId.
     */
    public abstract Long getDocumentHeaderId();

    /**
     * @return Returns the amount.
     */
    public abstract KualiDecimal getAmount();

    /**
     * @param amount The amount to set.
     */
    public abstract void setAmount(KualiDecimal amount);

    /**
     * @return Returns the balanceTypeCode.
     */
    public abstract String getBalanceTypeCode();

    /**
     * @param balanceTypeCode The balanceTypeCode to set.
     */
    public abstract void setBalanceTypeCode(String balanceTypeCode);

    /**
     * @return Returns the objectCode.
     */
    public abstract ObjectCode getObjectCode();

    /**
     * @param objectCode The objectCode to set.
     */
    public abstract void setObjectCode(ObjectCode objectCode);

    /**
     * @return Returns the referenceOriginCode.
     */
    public abstract String getReferenceOriginCode();

    /**
     * @param referenceOriginCode The referenceOriginCode to set.
     */
    public abstract void setReferenceOriginCode(String originCode);

    /**
     * @return Returns the organizationReferenceId.
     */
    public abstract String getOrganizationReferenceId();

    /**
     * @param organizationReferenceId The organizationReferenceId to set.
     */
    public abstract void setOrganizationReferenceId(String organizationReferenceId);

    /**
     * @return Returns the overrideCode.
     */
    public abstract String getOverrideCode();

    /**
     * @param overrideCode The overrideCode to set.
     */
    public abstract void setOverrideCode(String overrideCode);

    /**
     * @return Returns the postingYear.
     */
    public abstract Integer getPostingYear();

    /**
     * @param postingYear The postingYear to set.
     */
    public abstract void setPostingYear(Integer postingYear);

    /**
     * @return Returns the projectCode.
     */
    public abstract ProjectCode getProjectCode();

    /**
     * @param projectCode The projectCode to set.
     */
    public abstract void setProjectCode(ProjectCode projectCode);

    /**
     * @return Returns the referenceNumber.
     */
    public abstract String getReferenceNumber();

    /**
     * @param referenceNumber The referenceNumber to set.
     */
    public abstract void setReferenceNumber(String referenceNumber);

    /**
     * @return Returns the referenceTypeCode.
     */
    public abstract String getReferenceTypeCode();

    /**
     * @param referenceTypeCode The referenceTypeCode to set.
     */
    public abstract void setReferenceTypeCode(String referenceTypeCode);

    /**
     * @return Returns the sequenceNumber.
     */
    public abstract Integer getSequenceNumber();

    /**
     * @param sequenceNumber The sequenceNumber to set.
     */
    public abstract void setSequenceNumber(Integer sequenceNumber);

    /**
     * @return Returns the subAccount.
     */
    public abstract SubAccount getSubAccount();

    /**
     * @param subAccount The subAccount to set.
     */
    public abstract void setSubAccount(SubAccount subAccount);

    /**
     * @return Returns the subObjectCode.
     */
    public abstract SubObjectCode getSubObjectCode();

    /**
     * @param subObjectCode The subObjectCode to set.
     */
    public abstract void setSubObjectCode(SubObjectCode subObjectCode);

    /**
     * @param documentHeaderId The documentHeaderId to set.
     */
    public abstract void setDocumentHeaderId(Long documentHeaderId);

    /**
     * This method instructs the object to validate itself and all its dependent
     * components.
     * 
     * In the specific case of the AccountingLineBase, all this does is call the
     * validate() methods on all its component objects.
     */
    public abstract void validate() throws ValidationErrorList;

    /**
     * This method instructs the object to validate itself and all its dependent
     * components with the scope of saving in mind. In the specific case of the
     * AccountingLineBase, all this does is call the validate() methods on all
     * its component objects.
     * 
     * @throws ValidationErrorList
     */
    public abstract void validateForSave() throws ValidationErrorList;
}