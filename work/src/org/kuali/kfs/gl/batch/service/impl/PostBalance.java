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
package org.kuali.module.gl.batch.poster.impl;

import java.util.Date;

import org.kuali.core.util.KualiDecimal;
import org.kuali.module.chart.dao.ObjectTypeCodeDao;
import org.kuali.module.chart.service.BalanceTypService;
import org.kuali.module.gl.batch.poster.PostTransaction;
import org.kuali.module.gl.bo.Balance;
import org.kuali.module.gl.bo.Transaction;
import org.kuali.module.gl.dao.BalanceDao;

/**
 * @author jsissom
 *
 */
public class PostBalance implements PostTransaction {
  private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PostBalance.class);

  private ObjectTypeCodeDao objectTypeCodeDao;
  private BalanceDao balanceDao;
  private BalanceTypService balanceTypService;

  /**
   * 
   */
  public PostBalance() {
    super();
  }

  /* (non-Javadoc)
   * @see org.kuali.module.gl.batch.poster.PostTransaction#post(org.kuali.module.gl.bo.Transaction)
   */
  public String post(Transaction t,int mode,Date postDate) {
    LOG.debug("post() started");

    String postType = "U";

    KualiDecimal amount = t.getTransactionLedgerEntryAmount();

    // Subtract the amount if offset generation indicator & the debit/credit code isn't the same
    // as the one in the object type code table
    if ( t.getBalanceType().isFinancialOffsetGenerationIndicator() ) {
      if ( ! t.getDebitOrCreditCode().equals(t.getObjectType().getFinObjectTypeDebitcreditCd()) ) {
        amount = amount.multiply(new KualiDecimal(-1));
      }
    }

    Balance b = balanceDao.getBalanceByTransaction(t);
    if ( b == null ) {
      postType = "I";
      b = new Balance(t);
    }
    b.setTimestamp(new java.sql.Date(postDate.getTime()));

    String period = t.getUniversityFiscalAccountingPeriod();
    b.setAmount(period,b.getAmount(period).add(amount));

    balanceDao.save(b);

    return postType;
  }

  public String getDestinationName() {
    return "GL_BALANCE_T";
  }

  public void setBalanceDao(BalanceDao bd) {
    balanceDao = bd;
  }
}
