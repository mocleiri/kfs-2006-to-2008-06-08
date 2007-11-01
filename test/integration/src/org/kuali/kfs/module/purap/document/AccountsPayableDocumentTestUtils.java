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
package org.kuali.module.purap.document;

import java.util.List;

import org.kuali.kfs.context.KualiTestBase;
import org.kuali.module.purap.bo.AccountsPayableItem;

public class AccountsPayableDocumentTestUtils extends KualiTestBase {

    public static void testAddItem(AccountsPayableDocument document, List<AccountsPayableItem> items, int expectedItemTotal) throws Exception {
        assertTrue("no items found", items.size() > 0);

        assertEquals(0, document.getItems().size());

        for (AccountsPayableItem item : items) {
            document.addItem(item);
        }

        assertEquals("item count mismatch", expectedItemTotal, document.getItems().size());
    }

}
