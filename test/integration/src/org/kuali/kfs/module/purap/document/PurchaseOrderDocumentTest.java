/*
 * Copyright 2005-2007 The Kuali Foundation.
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
package org.kuali.module.purap.document;

import static org.kuali.module.financial.document.AccountingDocumentTestUtils.testGetNewDocument_byDocumentClass;
import static org.kuali.module.purap.fixtures.PurchaseOrderItemAccountsFixture.WITH_DESC_WITH_UOM_WITH_PRICE_WITH_ACCOUNT;
import static org.kuali.test.fixtures.UserNameFixture.KHUNTLEY;
import static org.kuali.test.fixtures.UserNameFixture.RJWEISS;
import static org.kuali.test.fixtures.UserNameFixture.RORENFRO;

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.document.Document;
import org.kuali.core.service.DataDictionaryService;
import org.kuali.core.service.DocumentService;
import org.kuali.core.service.TransactionalDocumentDictionaryService;
import org.kuali.kfs.context.KualiTestBase;
import org.kuali.kfs.context.SpringContext;
import org.kuali.kfs.document.AccountingDocument;
import org.kuali.module.financial.document.AccountingDocumentTestUtils;
import org.kuali.module.purap.bo.PurchasingItem;
import org.kuali.module.purap.bo.PurchaseOrderItem;
import org.kuali.module.purap.fixtures.PurchaseOrderDocumentFixture;
import org.kuali.module.purap.fixtures.PurchaseOrderItemAccountsFixture;
import org.kuali.test.ConfigureContext;
import org.kuali.test.DocumentTestUtils;
import org.kuali.test.fixtures.UserNameFixture;

/**
 * Unit Test Class for Internal Billing Document.
 */
@ConfigureContext(session = KHUNTLEY)
public class PurchaseOrderDocumentTest extends KualiTestBase {
    public static final Class<PurchaseOrderDocument> DOCUMENT_CLASS = PurchaseOrderDocument.class;

    private List<PurchaseOrderItemAccountsFixture> getItemParametersFromFixtures() {
        List<PurchaseOrderItemAccountsFixture> list = new ArrayList<PurchaseOrderItemAccountsFixture>();
        list.add(WITH_DESC_WITH_UOM_WITH_PRICE_WITH_ACCOUNT);
        return list;
    }

    private int getExpectedPrePeCount() {
        return 0;
    }

    public final void testAddItem() throws Exception {
        List<PurchasingItem> items = new ArrayList<PurchasingItem>();
        for (PurchaseOrderItem item : generateItems()) {
            items.add(item);
        }
        int expectedItemTotal = items.size();
        PurchasingDocumentTestUtils.testAddItem((PurchasingDocument)DocumentTestUtils.createDocument(SpringContext.getBean(DocumentService.class), DOCUMENT_CLASS), items, expectedItemTotal);
    }

    public final void testGetNewDocument() throws Exception {
        testGetNewDocument_byDocumentClass(DOCUMENT_CLASS, SpringContext.getBean(DocumentService.class));
    }

    public final void testConvertIntoErrorCorrection_documentAlreadyCorrected() throws Exception {
        AccountingDocumentTestUtils.testConvertIntoErrorCorrection_documentAlreadyCorrected(buildSimpleDocument(), SpringContext.getBean(TransactionalDocumentDictionaryService.class));
    }

    @ConfigureContext(session = KHUNTLEY, shouldCommitTransactions=true)
    public final void testConvertIntoErrorCorrection() throws Exception {
        AccountingDocumentTestUtils.testConvertIntoErrorCorrection(buildSimpleDocument(), getExpectedPrePeCount(), SpringContext.getBean(DocumentService.class), SpringContext.getBean(TransactionalDocumentDictionaryService.class));
    }
/*
    @ConfigureContext(session = KHUNTLEY, shouldCommitTransactions=true)
    public final void testRouteDocument() throws Exception {
        AccountingDocumentTestUtils.testRouteDocument(buildSimpleDocument(), SpringContext.getBean(DocumentService.class));
    }

    @ConfigureContext(session = KHUNTLEY, shouldCommitTransactions=true)
    public final void testSaveDocument() throws Exception {     
        AccountingDocumentTestUtils.testSaveDocument(buildSimpleDocument(), SpringContext.getBean(DocumentService.class));
    }
*/
    // test util methods
    
    private List<PurchaseOrderItem> generateItems() throws Exception {
        List<PurchaseOrderItem> items = new ArrayList<PurchaseOrderItem>();
        // set items to document
        for (PurchaseOrderItemAccountsFixture itemFixture : getItemParametersFromFixtures()) {
            items.add(itemFixture.populateItem());
        }

        return items;
    }

    private PurchaseOrderDocument buildSimpleDocument() throws Exception {
        return PurchaseOrderDocumentFixture.PO_ONLY_REQUIRED_FIELDS.createPurchaseOrderDocument();
    }

    private UserNameFixture getInitialUserName() {
        return RJWEISS;
    }

    protected UserNameFixture getTestUserName() {
        return RORENFRO;
    }
}
