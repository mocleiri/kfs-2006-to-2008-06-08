/*
 * Copyright (c) 2004, 2005 The National Association of College and University 
 * Business Officers, Cornell University, Trustees of Indiana University, 
 * Michigan State University Board of Trustees, Trustees of San Joaquin Delta 
 * College, University of Hawai'i, The Arizona Board of Regents on behalf of the 
 * University of Arizona, and the r*smart group.
 * 
 * Licensed under the Educational Community License Version 1.0 (the "License"); 
 * By obtaining, using and/or copying this Original Work, you agree that you 
 * have read, understand, and will comply with the terms and conditions of the 
 * Educational Community License.
 * 
 * You may obtain a copy of the License at:
 * 
 * http://kualiproject.org/license.html
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,  DAMAGES OR OTHER 
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN 
 * THE SOFTWARE.
 */

package org.kuali.module.financial.document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.document.FinancialDocumentBase;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.module.financial.bo.CashDrawer;
import org.kuali.module.financial.bo.Deposit;

import edu.iu.uis.eden.EdenConstants;

/**
 * This class represents the CashManagementDocument.
 * 
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class CashManagementDocument extends FinancialDocumentBase {
    private static final long serialVersionUID = 7475843770851900297L;

    private String workgroupName;
    private String referenceFinancialDocumentNumber;

    private List deposits;


    /**
     * Default constructor.
     */
    public CashManagementDocument() {
        super();
        deposits = new ArrayList();
    }


    /**
     * @return current value of referenceFinancialDocumentNumber.
     */
    public String getReferenceFinancialDocumentNumber() {
        return referenceFinancialDocumentNumber;
    }

    /**
     * Sets the referenceFinancialDocumentNumber attribute value.
     * 
     * @param referenceFinancialDocumentNumber The referenceFinancialDocumentNumber to set.
     */
    public void setReferenceFinancialDocumentNumber(String referenceFinancialDocumentNumber) {
        this.referenceFinancialDocumentNumber = referenceFinancialDocumentNumber;
    }

    /**
     * @return current value of workgroupName.
     */
    public String getWorkgroupName() {
        return workgroupName;
    }

    /**
     * Sets the workgroupName attribute value.
     * 
     * @param workgroupName The workgroupName to set.
     */
    public void setWorkgroupName(String workgroupName) {
        this.workgroupName = workgroupName;
    }

    /**
     * Derives and returns the cash drawer status for the document's workgroup
     */
    public String getCashDrawerStatus() {
        CashDrawer drawer = SpringServiceLocator.getCashDrawerService().getByWorkgroupName(getWorkgroupName());
        String statusCode = drawer.getStatusCode();

        return statusCode;
    }

    /**
     * @param cashDrawerStatus
     */
    public void setCashDrawerStatus(String cashDrawerStatus) {
        // ignored, because that value is dynamically retrieved from the service
    }

    /* Deposit-list maintenance */
    /**
     * @return current List of Deposits
     */
    public List getDeposits() {
        return deposits;
    }

    /**
     * Sets the current List of Deposits
     * 
     * @param deposits
     */
    public void setDeposits(List deposits) {
        this.deposits = deposits;
    }

    /**
     * Implementation creates empty Deposits as a side-effect, so that Struts' efforts to set fields of lines which haven't been
     * created will succeed rather than causing a NullPointerException.
     * 
     * @return Deposit at the given index
     */
    public Deposit getDeposit(int index) {
        extendDeposits(index + 1);

        return (Deposit) deposits.get(index);
    }

    /**
     * Adds default AccountingLineDecorators to sourceAccountingLineDecorators until it contains at least minSize elements
     * 
     * @param minSize
     */
    private void extendDeposits(int minSize) {
        while (deposits.size() < minSize) {
            deposits.add(new Deposit());
        }
    }


    /**
     * @see org.kuali.core.document.DocumentBase#buildListOfDeletionAwareLists()
     */
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();

        managedLists.add(getDeposits());

        return managedLists;
    }


    private static final Set FAILURE_CODES;
    static {
        String[] FAILURE_CODE_ARRAY = { EdenConstants.ROUTE_HEADER_CANCEL_DISAPPROVE_CD, EdenConstants.ROUTE_HEADER_DISAPPROVED_CD,
                EdenConstants.ROUTE_HEADER_CANCEL_CD,
        // EdenConstants.ROUTE_HEADER_EXCEPTION_CD
        };
        FAILURE_CODES = new HashSet();
        for (int i = 0; i < FAILURE_CODE_ARRAY.length; ++i) {
            FAILURE_CODES.add(FAILURE_CODE_ARRAY[i]);
        }
    }

    /**
     * @see org.kuali.core.document.DocumentBase#handleRouteStatusChange(java.lang.String)
     */
    public void handleRouteStatusChange(String newRouteStatus) {
        // all approvals have been processed, finalize  everything
        if (StringUtils.equals(newRouteStatus, EdenConstants.ROUTE_HEADER_PROCESSED_CD)) {
            SpringServiceLocator.getCashManagementService().finalizeCashManagementDocument(this);
        }
        // document has been canceled or disapproved,
        else if (FAILURE_CODES.contains(newRouteStatus)) {
            SpringServiceLocator.getCashManagementService().cancelCashManagementDocument(this);
        }
    }


    /* utility methods */
    /**
     * @see org.kuali.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("financialDocumentNumber", getFinancialDocumentNumber());
        m.put("workgroupName", getWorkgroupName());
        return m;
    }
}
