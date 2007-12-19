/*
 * Copyright 2006-2007 The Kuali Foundation.
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


/**
 * Requisition Account Business Object.
 */
public class RequisitionAccount extends PurApAccountingLineBase {

    private static final long serialVersionUID = -8655437895493693864L;

    private RequisitionItem requisitionItem;

    public RequisitionItem getRequisitionItem() {
        return requisitionItem;
    }

    /**
     * Sets the requisitionItem attribute.
     * 
     * @param requisitionItem The requisitionItem to set.
     * @deprecated
     */
    public void setRequisitionItem(RequisitionItem requisitionItem) {
        this.requisitionItem = requisitionItem;
    }

}