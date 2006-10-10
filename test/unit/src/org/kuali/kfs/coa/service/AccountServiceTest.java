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
package org.kuali.module.chart.service;

import static org.kuali.core.util.SpringServiceLocator.*;
import org.kuali.module.chart.bo.Account;
import org.kuali.test.KualiTestBase;
import org.kuali.test.WithTestSpringContext;

/**
 * This class tests the Account service.
 * 
 * 
 */
@WithTestSpringContext
public class AccountServiceTest extends KualiTestBase {

    public void testValidateAccount() {
        Account account = null;
        account = getAccountService().getByPrimaryId("BA", "6044900");
        assertNotNull(account);
        // assertNotNull(account.getSubAccounts());
        // assertEquals("sub account list should contain 27 subaccounts", 27, account.getSubAccounts().size());

        account = null;
        account = getAccountService().getByPrimaryId("XX", "0000000");
        assertNull(account);

        account = null;
        account = getAccountService().getByPrimaryId("KO", "");
        assertNull(account);

        account = null;
        account = getAccountService().getByPrimaryId("UA", null);
        assertNull(account);

        account = null;
        account = getAccountService().getByPrimaryId(null, "1912610");
        assertNull(account);

        account = null;
        account = getAccountService().getByPrimaryId(null, null);
        assertNull(account);
    }


}
