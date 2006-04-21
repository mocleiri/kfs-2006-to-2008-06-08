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
package org.kuali.module.financial.web.struts.form;

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.web.struts.form.KualiTransactionalDocumentFormBase;
import org.kuali.module.financial.bo.CreditCardDetail;
import org.kuali.module.financial.document.CreditCardReceiptDocument;

/**
 * This class is the struts form for Credit Card Receipt document.
 * 
 * @author Kuali Financial Transactions Team (kualidev@oncourse.iu.edu)
 */
public class CreditCardReceiptForm extends KualiTransactionalDocumentFormBase {
    private static final long serialVersionUID = 1L;
    private CreditCardDetail newCreditCardReceipt;
    private List creditCardReceipts;

    /**
     * Constructs a CreditCardReceiptForm.java.
     */
    public CreditCardReceiptForm() {
        super();
        setDocument(new CreditCardReceiptDocument());
        setNewCreditCardReceipt(new CreditCardDetail());
        creditCardReceipts = new ArrayList();
    }

    /**
     * @return CreditCardReceiptDocument
     */
    public CreditCardReceiptDocument getCreditCardReceiptDocument() {
        return (CreditCardReceiptDocument) getDocument();
    }

    /**
     * @return List
     */
    public List getCreditCardReceipts() {
        return creditCardReceipts;
    }

    /**
     * @param creditCardReceipts
     */
    public void setCreditCardReceipts(List creditCardReceipts) {
        this.creditCardReceipts = creditCardReceipts;
    }

    /**
     * @return CreditCardDetail
     */
    public CreditCardDetail getNewCreditCardReceipt() {
        return newCreditCardReceipt;
    }

    /**
     * @param newCreditCardReceipt
     */
    public void setNewCreditCardReceipt(CreditCardDetail newCreditCardReceipt) {
        this.newCreditCardReceipt = newCreditCardReceipt;
    }

    /**
     * Implementation creates empty CreditCardDetail as a side-effect, so that Struts' efforts to set fields of lines which haven't
     * been created will succeed rather than causing a NullPointerException.
     * 
     * @param index
     * @return CreditCardDetail at the given index
     */
    public CreditCardDetail getCreditCardReceipt(int index) {
        while (creditCardReceipts.size() <= index) {
            creditCardReceipts.add(new CreditCardDetail());
        }
        return (CreditCardDetail) creditCardReceipts.get(index);
    }
}