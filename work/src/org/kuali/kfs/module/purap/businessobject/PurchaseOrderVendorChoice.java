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

package org.kuali.module.purap.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.BusinessObjectBase;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class PurchaseOrderVendorChoice extends BusinessObjectBase {

	private String purchaseOrderVendorChoiceCode;
	private String purchaseOrderVendorChoiceDescription;
	private boolean dataObjectMaintenanceCodeActiveIndicator;

    private PurchaseOrder purchaseOrderVendorChoice;

	/**
	 * Default constructor.
	 */
	public PurchaseOrderVendorChoice() {

	}

	/**
	 * Gets the purchaseOrderVendorChoiceCode attribute.
	 * 
	 * @return - Returns the purchaseOrderVendorChoiceCode
	 * 
	 */
	public String getPurchaseOrderVendorChoiceCode() { 
		return purchaseOrderVendorChoiceCode;
	}

	/**
	 * Sets the purchaseOrderVendorChoiceCode attribute.
	 * 
	 * @param - purchaseOrderVendorChoiceCode The purchaseOrderVendorChoiceCode to set.
	 * 
	 */
	public void setPurchaseOrderVendorChoiceCode(String purchaseOrderVendorChoiceCode) {
		this.purchaseOrderVendorChoiceCode = purchaseOrderVendorChoiceCode;
	}


	/**
	 * Gets the purchaseOrderVendorChoiceDescription attribute.
	 * 
	 * @return - Returns the purchaseOrderVendorChoiceDescription
	 * 
	 */
	public String getPurchaseOrderVendorChoiceDescription() { 
		return purchaseOrderVendorChoiceDescription;
	}

	/**
	 * Sets the purchaseOrderVendorChoiceDescription attribute.
	 * 
	 * @param - purchaseOrderVendorChoiceDescription The purchaseOrderVendorChoiceDescription to set.
	 * 
	 */
	public void setPurchaseOrderVendorChoiceDescription(String purchaseOrderVendorChoiceDescription) {
		this.purchaseOrderVendorChoiceDescription = purchaseOrderVendorChoiceDescription;
	}


	/**
	 * Gets the dataObjectMaintenanceCodeActiveIndicator attribute.
	 * 
	 * @return - Returns the dataObjectMaintenanceCodeActiveIndicator
	 * 
	 */
	public boolean getDataObjectMaintenanceCodeActiveIndicator() { 
		return dataObjectMaintenanceCodeActiveIndicator;
	}

	/**
	 * Sets the dataObjectMaintenanceCodeActiveIndicator attribute.
	 * 
	 * @param - dataObjectMaintenanceCodeActiveIndicator The dataObjectMaintenanceCodeActiveIndicator to set.
	 * 
	 */
	public void setDataObjectMaintenanceCodeActiveIndicator(boolean dataObjectMaintenanceCodeActiveIndicator) {
		this.dataObjectMaintenanceCodeActiveIndicator = dataObjectMaintenanceCodeActiveIndicator;
	}


	/**
	 * Gets the purchaseOrderVendorChoice attribute.
	 * 
	 * @return - Returns the purchaseOrderVendorChoice
	 * 
	 */
	public PurchaseOrder getPurchaseOrderVendorChoice() { 
		return purchaseOrderVendorChoice;
	}

	/**
	 * Sets the purchaseOrderVendorChoice attribute.
	 * 
	 * @param - purchaseOrderVendorChoice The purchaseOrderVendorChoice to set.
	 * @deprecated
	 */
	public void setPurchaseOrderVendorChoice(PurchaseOrder purchaseOrderVendorChoice) {
		this.purchaseOrderVendorChoice = purchaseOrderVendorChoice;
	}

	/**
	 * @see org.kuali.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
	    LinkedHashMap m = new LinkedHashMap();	    
        m.put("purchaseOrderVendorChoiceCode", this.purchaseOrderVendorChoiceCode);
	    return m;
    }
}
