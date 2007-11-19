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
package org.kuali.module.gl.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.module.gl.bo.CollectorDetail;
import org.kuali.module.gl.bo.OriginEntryFull;
import org.kuali.module.gl.bo.Transaction;

public class DocumentGroupData {
    protected String documentNumber;
    protected String financialDocumentTypeCode;
    protected String financialSystemOriginationCode;

    public DocumentGroupData(Transaction entry) {
        documentNumber = entry.getDocumentNumber();
        financialDocumentTypeCode = entry.getFinancialDocumentTypeCode();
        financialSystemOriginationCode = entry.getFinancialSystemOriginationCode();
    }

    public DocumentGroupData(String documentNumber, String financialDocumentTypeCode, String financialSystemOriginationCode) {
        this.documentNumber = documentNumber;
        this.financialDocumentTypeCode = financialDocumentTypeCode;
        this.financialSystemOriginationCode = financialSystemOriginationCode;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof DocumentGroupData)) {
            return false;
        }
        DocumentGroupData o2 = (DocumentGroupData) obj;
        return StringUtils.equals(documentNumber, o2.documentNumber) && StringUtils.equals(financialDocumentTypeCode, o2.financialDocumentTypeCode) && StringUtils.equals(financialSystemOriginationCode, o2.financialSystemOriginationCode);
    }

    public boolean matchesTransaction(Transaction transaction) {
        return StringUtils.equals(documentNumber, transaction.getDocumentNumber()) && StringUtils.equals(financialDocumentTypeCode, transaction.getFinancialDocumentTypeCode()) && StringUtils.equals(financialSystemOriginationCode, transaction.getFinancialSystemOriginationCode());
    }

    public boolean matchesCollectorDetail(CollectorDetail detail) {
        return StringUtils.equals(documentNumber, detail.getDocumentNumber()) && StringUtils.equals(financialDocumentTypeCode, detail.getFinancialDocumentTypeCode()) && StringUtils.equals(financialSystemOriginationCode, detail.getFinancialSystemOriginationCode());
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        // hash based on the doc #, because it's likely to have the most variation within an origin entry doc
        if (documentNumber == null) {
            return "".hashCode();
        }
        return documentNumber.hashCode();
    }

    public OriginEntryFull populateDocumentGroupDataFieldsInOriginEntry() {
        OriginEntryFull entry = new OriginEntryFull();
        entry.setDocumentNumber(documentNumber);
        entry.setFinancialDocumentTypeCode(financialDocumentTypeCode);
        entry.setFinancialSystemOriginationCode(financialSystemOriginationCode);
        return entry;
    }

    /**
     * Gets the documentNumber attribute.
     * 
     * @return Returns the documentNumber.
     */
    public String getDocumentNumber() {
        return documentNumber;
    }

    /**
     * Sets the documentNumber attribute value.
     * 
     * @param documentNumber The documentNumber to set.
     */
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    /**
     * Gets the financialDocumentTypeCode attribute.
     * 
     * @return Returns the financialDocumentTypeCode.
     */
    public String getFinancialDocumentTypeCode() {
        return financialDocumentTypeCode;
    }

    /**
     * Sets the financialDocumentTypeCode attribute value.
     * 
     * @param financialDocumentTypeCode The financialDocumentTypeCode to set.
     */
    public void setFinancialDocumentTypeCode(String financialDocumentTypeCode) {
        this.financialDocumentTypeCode = financialDocumentTypeCode;
    }

    /**
     * Gets the financialSystemOriginationCode attribute.
     * 
     * @return Returns the financialSystemOriginationCode.
     */
    public String getFinancialSystemOriginationCode() {
        return financialSystemOriginationCode;
    }

    /**
     * Sets the financialSystemOriginationCode attribute value.
     * 
     * @param financialSystemOriginationCode The financialSystemOriginationCode to set.
     */
    public void setFinancialSystemOriginationCode(String financialSystemOriginationCode) {
        this.financialSystemOriginationCode = financialSystemOriginationCode;
    }

    /**
     * Given an iterator of {@link Transaction} objects, return a set of all the document groups (doc #, doc type, origination code)
     * for these transactions
     * 
     * @param transactions
     * @return
     */
    public static <E extends Transaction> Set<DocumentGroupData> getDocumentGroupDatasForTransactions(Iterator<E> transactions) {
        Set<DocumentGroupData> documentGroupDatas = new HashSet<DocumentGroupData>();
        while (transactions.hasNext()) {
            Transaction transaction = transactions.next();
            documentGroupDatas.add(new DocumentGroupData(transaction));
        }
        return documentGroupDatas;
    }
}