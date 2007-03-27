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
package org.kuali.bo;

import org.kuali.validation.ValidationErrorList;

/**
 * This class represents an actual value entered in for a given entity record
 * which has a custom attribute.
 * @author Aaron Godert (ag266@cornell.edu) - first cut at the bo
 */
public class ExtendedAttributeValue extends BusinessObjectBase {
    
	//attributes
	private Long attributeId;
	protected String ojbConcreteClass;
	
	private String objectId; 

	//	set the default value of the attributeValue, such that if one does not get 
    // specified, it will still get saved in the DB, and also will have all objects 
    // populated when OJB loads from the DB.  In other words, the only reason we're 
	// using a magic-number instead of a null value it to support much-simplified 
	// use of OJB.  The default values will likely be converted to nulls in the DSS.
	private String attributeValue = "***NO VALUE SPECIFIED***";

	//business object references
	private ExtendedAttribute extendedAttribute;
	
	/**
	 * Default constructor.
	 */
	public ExtendedAttributeValue() {
		
	}
	
    //getters and setters
	/**
	 * @return Returns the attributeId.
	 */
	public Long getAttributeId() {
		return attributeId;
	}

	/**
	 * @param attributeId The attributeId to set.
	 */
	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}

	/**
	 * @return Returns the attributeValue.
	 */
	public String getAttributeValue() {
		return attributeValue;
	}

	/**
	 * @param attributeValue The attributeValue to set.
	 */
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	/**
	 * @return Returns the extendedAttribute.
	 */
	public ExtendedAttribute getExtendedAttribute() {
		return extendedAttribute;
	}

	/**
	 * @param extendedAttribute The extendedAttribute to set.
	 */
	public void setExtendedAttribute(ExtendedAttribute extendedAttribute) {
		this.extendedAttribute = extendedAttribute;
	}

	/**
	 * @return Returns the objectId.
	 */
	public String getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId The objectId to set.
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	
	//validation methods
	public void validate(ValidationErrorList list) {
		//TODO - do we need a validate here?
	}
	
	/**
	 * Lists the fields and values in a string dump.
	 */
	public String toString() {
	    
	    return "attributeId = '" + attributeId + "'; " + 
	    		"objectId = '" + objectId + "'; " + 
	    		"attributeValue = '" + attributeValue + "';";
	}
}