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
public class RecurringPaymentType extends BusinessObjectBase {

	private String recurringPaymentTypeCode;
	private String recurringPaymentTypeDescription;

    private Requisition recurringPaymentType;

	/**
	 * Default constructor.
	 */
	public RecurringPaymentType() {

	}

	/**
	 * Gets the recurringPaymentTypeCode attribute.
	 * 
	 * @return - Returns the recurringPaymentTypeCode
	 * 
	 */
	public String getRecurringPaymentTypeCode() { 
		return recurringPaymentTypeCode;
	}

	/**
	 * Sets the recurringPaymentTypeCode attribute.
	 * 
	 * @param - recurringPaymentTypeCode The recurringPaymentTypeCode to set.
	 * 
	 */
	public void setRecurringPaymentTypeCode(String recurringPaymentTypeCode) {
		this.recurringPaymentTypeCode = recurringPaymentTypeCode;
	}


	/**
	 * Gets the recurringPaymentTypeDescription attribute.
	 * 
	 * @return - Returns the recurringPaymentTypeDescription
	 * 
	 */
	public String getRecurringPaymentTypeDescription() { 
		return recurringPaymentTypeDescription;
	}

	/**
	 * Sets the recurringPaymentTypeDescription attribute.
	 * 
	 * @param - recurringPaymentTypeDescription The recurringPaymentTypeDescription to set.
	 * 
	 */
	public void setRecurringPaymentTypeDescription(String recurringPaymentTypeDescription) {
		this.recurringPaymentTypeDescription = recurringPaymentTypeDescription;
	}


	/**
	 * Gets the recurringPaymentType attribute.
	 * 
	 * @return - Returns the recurringPaymentType
	 * 
	 */
	public Requisition getRecurringPaymentType() { 
		return recurringPaymentType;
	}

	/**
	 * Sets the recurringPaymentType attribute.
	 * 
	 * @param - recurringPaymentType The recurringPaymentType to set.
	 * @deprecated
	 */
	public void setRecurringPaymentType(Requisition recurringPaymentType) {
		this.recurringPaymentType = recurringPaymentType;
	}

	/**
	 * @see org.kuali.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
	    LinkedHashMap m = new LinkedHashMap();	    
        m.put("recurringPaymentTypeCode", this.recurringPaymentTypeCode);
	    return m;
    }
}
