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
package org.kuali.module.purap.fixtures;

import java.sql.Date;
import java.sql.Timestamp;

import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.service.DateTimeService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kfs.context.SpringContext;
import org.kuali.module.purap.document.CreditMemoDocument;

public enum SystemParameterBelowTheLineItemCMFixture {
    
    CM_VALID_BELOW_LINE_ITEMS(
            new CreditMemoItemFixture[] {CreditMemoItemFixture.CM_VALID_RSTO_ITEM, CreditMemoItemFixture.CM_VALID_MSCR_ITEM} // creditMemoItemMultiFixtures
            ),
            
    CM_INVALID_BELOW_LINE_ITEMS(
            new CreditMemoItemFixture[] {CreditMemoItemFixture.CM_VALID_MISC_ITEM} // creditMemoItemMultiFixtures
            ),
            
    CM_VALID_NEGATIVE_BELOW_LINE_ITEMS(
            new CreditMemoItemFixture[] {CreditMemoItemFixture.CM_VALID_RSTO_ITEM, 
                                         CreditMemoItemFixture.CM_VALID_MSCR_ITEM,
                                         CreditMemoItemFixture.CM_NEGATIVE_DISC_ITEM,
                                         CreditMemoItemFixture.CM_NEGATIVE_ORDS_ITEM,
                                         CreditMemoItemFixture.CM_NEGATIVE_TRDI_ITEM,
                                         CreditMemoItemFixture.CM_NEGATIVE_FDTX_ITEM,
                                         CreditMemoItemFixture.CM_NEGATIVE_STTX_ITEM,
                                         CreditMemoItemFixture.CM_NEGATIVE_MISC_ITEM} // creditMemoItemMultiFixtures
            ),
            
    CM_INVALID_NEGATIVE_BELOW_LINE_ITEMS(
            new CreditMemoItemFixture[] {CreditMemoItemFixture.CM_NEGATIVE_FREIGHT_ITEM,
                                         CreditMemoItemFixture.CM_NEGATIVE_SHIPPING_AND_HANDLING_ITEM,
                                         CreditMemoItemFixture.CM_NEGATIVE_MIN_ORDER_ITEM,
                                         CreditMemoItemFixture.CM_NEGATIVE_FED_GROSS_CODE_ITEM,
                                         CreditMemoItemFixture.CM_NEGATIVE_STATE_GROSS_CODE_ITEM} // creditMemoItemMultiFixtures
            ),   
            
    CM_VALID_POSITIVE_BELOW_LINE_ITEMS(
            new CreditMemoItemFixture[] {CreditMemoItemFixture.CM_POSITIVE_FREIGHT_ITEM,
                                         CreditMemoItemFixture.CM_POSITIVE_SHIPPING_AND_HANDLING_ITEM,
                                         CreditMemoItemFixture.CM_POSITIVE_MIN_ORDER_ITEM,
                                         CreditMemoItemFixture.CM_POSITIVE_FED_GROSS_CODE_ITEM,
                                         CreditMemoItemFixture.CM_POSITIVE_STATE_GROSS_CODE_ITEM,
                                         CreditMemoItemFixture.CM_VALID_MISC_ITEM,
                                         CreditMemoItemFixture.CM_POSITIVE_MSCR_ITEM} // creditMemoItemMultiFixtures
            ),    
            
    CM_INVALID_POSITIVE_BELOW_LINE_ITEMS(
            new CreditMemoItemFixture[] {CreditMemoItemFixture.CM_POSITIVE_RSTO_ITEM, 
                                         CreditMemoItemFixture.CM_POSITIVE_DISC_ITEM,
                                         CreditMemoItemFixture.CM_POSITIVE_ORDS_ITEM,
                                         CreditMemoItemFixture.CM_POSITIVE_TRDI_ITEM,
                                         CreditMemoItemFixture.CM_POSITIVE_FDTX_ITEM,
                                         CreditMemoItemFixture.CM_POSITIVE_STTX_ITEM} // creditMemoItemMultiFixtures
            ),  
            
    CM_VALID_ZERO_BELOW_LINE_ITEMS(
            new CreditMemoItemFixture[] {CreditMemoItemFixture.CM_ZERO_FREIGHT_ITEM,
                                         CreditMemoItemFixture.CM_ZERO_SHIPPING_AND_HANDLING_ITEM,
                                         CreditMemoItemFixture.CM_ZERO_MIN_ORDER_ITEM,
                                         CreditMemoItemFixture.CM_ZERO_RSTO_ITEM,
                                         CreditMemoItemFixture.CM_ZERO_MISC_ITEM,
                                         CreditMemoItemFixture.CM_ZERO_DISC_ITEM,
                                         CreditMemoItemFixture.CM_ZERO_MSCR_ITEM} // creditMemoItemMultiFixtures
                    ),    
                    
    CM_INVALID_ZERO_BELOW_LINE_ITEMS(
            new CreditMemoItemFixture[] {CreditMemoItemFixture.CM_ZERO_FED_GROSS_CODE_ITEM,
                                         CreditMemoItemFixture.CM_ZERO_STATE_GROSS_CODE_ITEM,
                                         CreditMemoItemFixture.CM_ZERO_ORDS_ITEM,
                                         CreditMemoItemFixture.CM_ZERO_TRDI_ITEM,
                                         CreditMemoItemFixture.CM_ZERO_FDTX_ITEM,
                                         CreditMemoItemFixture.CM_ZERO_STTX_ITEM} // creditMemoItemMultiFixtures
            ),      
            
    CM_WITH_BELOW_LINE_ITEMS_WITHOUT_DESCRIPTION(
            new CreditMemoItemFixture[] {CreditMemoItemFixture.CM_WITH_FREIGHT_ITEM_NO_DESC} // creditMemoItemMultiFixtures
            ),
            
    CM_WITH_BELOW_LINE_ITEMS_WITH_DESCRIPTION(
            new CreditMemoItemFixture[] {CreditMemoItemFixture.CM_WITH_MISC_CREDIT_ITEM_WITH_DESC,
                                         CreditMemoItemFixture.CM_WITH_MISC_ITEM_WITH_DESC} // creditMemoItemMultiFixtures
            ),            
            ;   
    private CreditMemoItemFixture[] creditMemoItemFixtures;
    
    
    private SystemParameterBelowTheLineItemCMFixture(
            CreditMemoItemFixture[] creditMemoItemFixtures) {
        this.creditMemoItemFixtures = creditMemoItemFixtures;
    }
    
    public CreditMemoDocument createCreditMemoDocument() {
        CreditMemoDocument doc = CreditMemoDocumentFixture.CM_ONLY_REQUIRED_FIELDS.createCreditMemoDocument();
        //Removes all the existing item from doc, we'll add the appropriate items later.
        doc.getItems().clear();         
        for (CreditMemoItemFixture creditMemoItemFixture : creditMemoItemFixtures) { 
            creditMemoItemFixture.addTo(doc);
        }
        
        return doc;
    }
    
}