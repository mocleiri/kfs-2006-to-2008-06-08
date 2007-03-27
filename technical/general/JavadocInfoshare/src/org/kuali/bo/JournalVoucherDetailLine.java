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

import org.kuali.util.KualiDecimal;

//TODO - This should extend the AccountingLineBase object, and not reinvent the concept

public class JournalVoucherDetailLine {

    private Long detailLineSequenceId;

    private Long documentHeaderId;

    private Long versionNumber;

    private String chartOfAccountsCode;

    private String accountNumber;

    private Integer detailLinePostingYear;

    private String objectCode;

    private KualiDecimal detailLineAmount;

    private String detailLineDebitOrCreditCode;

    private String subAccountNumber;

    private String subObjectCode;

    private String projectCode;

    private String organizationReferenceId;

    private String detailLineReferenceOriginCode;

    private String detailLineReferenceDocumentNumber;

    private String detailLineReferenceDocumentTypeCode;

    private String detailLineOverrideCode;

    private String detailLineObjectTypeCode;

    private String detailLineEncumbranceUpdateCode;

    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }
    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }
    public KualiDecimal getDetailLineAmount() {
        return detailLineAmount;
    }
    public void setDetailLineAmount(KualiDecimal detailLineAmount) {
        this.detailLineAmount = detailLineAmount;
    }
    public String getDetailLineDebitOrCreditCode() {
        return detailLineDebitOrCreditCode;
    }
    public void setDetailLineDebitOrCreditCode(
            String detailLineDebitOrCreditCode) {
        this.detailLineDebitOrCreditCode = detailLineDebitOrCreditCode;
    }
    public String getDetailLineEncumbranceUpdateCode() {
        return detailLineEncumbranceUpdateCode;
    }
    public void setDetailLineEncumbranceUpdateCode(
            String detailLineEncumbranceUpdateCode) {
        this.detailLineEncumbranceUpdateCode = detailLineEncumbranceUpdateCode;
    }
    public String getDetailLineObjectTypeCode() {
        return detailLineObjectTypeCode;
    }
    public void setDetailLineObjectTypeCode(String detailLineObjectTypeCode) {
        this.detailLineObjectTypeCode = detailLineObjectTypeCode;
    }
    public String getDetailLineOverrideCode() {
        return detailLineOverrideCode;
    }
    public void setDetailLineOverrideCode(String detailLineOverrideCode) {
        this.detailLineOverrideCode = detailLineOverrideCode;
    }
    public Integer getDetailLinePostingYear() {
        return detailLinePostingYear;
    }
    public void setDetailLinePostingYear(Integer detailLinePostingYear) {
        this.detailLinePostingYear = detailLinePostingYear;
    }
    public String getDetailLineReferenceDocumentNumber() {
        return detailLineReferenceDocumentNumber;
    }
    public void setDetailLineReferenceDocumentNumber(
            String detailLineReferenceDocumentNumber) {
        this.detailLineReferenceDocumentNumber = detailLineReferenceDocumentNumber;
    }
    public String getDetailLineReferenceDocumentTypeCode() {
        return detailLineReferenceDocumentTypeCode;
    }
    public void setDetailLineReferenceDocumentTypeCode(
            String detailLineReferenceDocumentTypeCode) {
        this.detailLineReferenceDocumentTypeCode = detailLineReferenceDocumentTypeCode;
    }
    public String getDetailLineReferenceOriginCode() {
        return detailLineReferenceOriginCode;
    }
    public void setDetailLineReferenceOriginCode(
            String detailLineReferenceOriginCode) {
        this.detailLineReferenceOriginCode = detailLineReferenceOriginCode;
    }
    public Long getDetailLineSequenceId() {
        return detailLineSequenceId;
    }
    public void setDetailLineSequenceId(Long detailLineSequenceId) {
        this.detailLineSequenceId = detailLineSequenceId;
    }
    public Long getDocumentHeaderId() {
        return documentHeaderId;
    }
    public void setDocumentHeaderId(Long documentHeaderId) {
        this.documentHeaderId = documentHeaderId;
    }
    public String getObjectCode() {
        return objectCode;
    }
    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }
    public String getOrganizationReferenceId() {
        return organizationReferenceId;
    }
    public void setOrganizationReferenceId(String organizationReferenceId) {
        this.organizationReferenceId = organizationReferenceId;
    }
    public String getProjectCode() {
        return projectCode;
    }
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }
    public String getSubAccountNumber() {
        return subAccountNumber;
    }
    public void setSubAccountNumber(String subAccountNumber) {
        this.subAccountNumber = subAccountNumber;
    }
    public String getSubObjectCode() {
        return subObjectCode;
    }
    public void setSubObjectCode(String subObjectCode) {
        this.subObjectCode = subObjectCode;
    }
    public Long getVersionNumber() {
        return versionNumber;
    }
    public void setVersionNumber(Long versionNumber) {
        this.versionNumber = versionNumber;
    }
    public String toString() {
        return "WHO KNOWS??";
//TODO: put in good toString() info
    }
}
