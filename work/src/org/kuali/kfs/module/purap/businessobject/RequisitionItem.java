/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/work/src/org/kuali/kfs/module/purap/businessobject/RequisitionItem.java,v $
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

package org.kuali.module.purap.bo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import org.kuali.core.bo.BusinessObjectBase;
import org.kuali.core.util.KualiDecimal;
import org.kuali.module.purap.document.RequisitionDocument;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class RequisitionItem extends PurchasingItemBase {

    //Superclass contains
    //private Integer itemIdentifier;
    //private Integer itemLineNumber;
    //private String capitalAssetTransactionTypeCode;
    //private String itemUnitOfMeasureCode;
    //private String itemCatalogNumber;
    //private String itemDescription;
    //private String itemCapitalAssetNoteText;
    //private BigDecimal itemUnitPrice;
    //private String itemTypeCode;
    //private String requisitionLineIdentifier;
    //private String itemAuxiliaryPartIdentifier;
    //private String externalOrganizationB2bProductReferenceNumber;
    //private String externalOrganizationB2bProductTypeName;
    //private boolean itemAssignedToTradeInIndicator;

	private Integer purapDocumentIdentifier;
	private KualiDecimal itemQuantity;
	private boolean itemRestrictedIndicator;

    private RequisitionDocument requisition;
	
	/**
	 * Default constructor.
	 */
	public RequisitionItem() {

	}

    /**
     * Gets the itemQuantity attribute. 
     * @return Returns the itemQuantity.
     */
    public KualiDecimal getItemQuantity() {
        return itemQuantity;
    }

    /**
     * Sets the itemQuantity attribute value.
     * @param itemQuantity The itemQuantity to set.
     */
    public void setItemQuantity(KualiDecimal itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    /**
     * Gets the itemRestrictedIndicator attribute. 
     * @return Returns the itemRestrictedIndicator.
     */
    public boolean isItemRestrictedIndicator() {
        return itemRestrictedIndicator;
    }

    /**
     * Sets the itemRestrictedIndicator attribute value.
     * @param itemRestrictedIndicator The itemRestrictedIndicator to set.
     */
    public void setItemRestrictedIndicator(boolean itemRestrictedIndicator) {
        this.itemRestrictedIndicator = itemRestrictedIndicator;
    }

    /**
     * Gets the requisition attribute. 
     * @return Returns the requisition.
     */
    public RequisitionDocument getRequisition() {
        return requisition;
    }

    /**
     * Sets the requisition attribute value.
     * @param requisition The requisition to set.
     */
    public void setRequisition(RequisitionDocument requisition) {
        this.requisition = requisition;
    }

    /**
     * Gets the purapDocumentIdentifier attribute. 
     * @return Returns the purapDocumentIdentifier.
     */
    public Integer getPurapDocumentIdentifier() {
        return purapDocumentIdentifier;
    }

    /**
     * Sets the purapDocumentIdentifier attribute value.
     * @param purapDocumentIdentifier The purapDocumentIdentifier to set.
     */
    public void setPurapDocumentIdentifier(Integer purapDocumentIdentifier) {
        this.purapDocumentIdentifier = purapDocumentIdentifier;
    }


}
