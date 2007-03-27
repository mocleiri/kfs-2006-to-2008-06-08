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

import java.util.ArrayList;
import java.util.List;

import org.kuali.validation.ValidationErrorList;

/**
 * This class represents a custom attribute that can be defined within Kuali and
 * added onto a financial entity. Custom attributes can be defined at runtime by
 * end users and custom attributes also have value options.
 * @author Aaron Godert (ag266@cornell.edu)
 */
public class ExtendedAttribute extends BusinessObjectBase {
	//constants
	public static final Integer SELECT_LIST = new Integer(2);

	//attributes
	private Long attributeId;
	private Integer sequenceNumber;
	private String name;
	private String description;
	private Integer typeCode;
	private String className;
	
	//bo references
	private List extendedAttributeOptions;  //collection of attribute options

	/**
	 * This constructor sets up empty instances for the dependent objects.
	 */
	public ExtendedAttribute() {
		name = "";
		description = "";
		typeCode = SELECT_LIST; //for now we are only doing a select list
		this.extendedAttributeOptions = new ArrayList();
	}

	//getters and setters
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Returns the extendedAttributeOptions.
	 */
	public List getExtendedAttributeOptions() {
		return extendedAttributeOptions;
	}
	
	/**
	 * Retrieves a ExtendedAttributeOption at a particular index.
	 * @param index
	 * @return
	 */
	public ExtendedAttributeOption getExtendedAttributeOption(int index) {
		while(extendedAttributeOptions.size() <= index) {
		    extendedAttributeOptions.add(new ExtendedAttributeOption());
		}
		return (ExtendedAttributeOption) extendedAttributeOptions.get(index);
	}

	/**
	 * @param extendedAttributeOptions The extendedAttributeOptions to set.
	 */
	public void setExtendedAttributeOptions(List extendedAttributeOptions) {
		this.extendedAttributeOptions = extendedAttributeOptions;
	}

	/**
	 * @return Returns the attributeId.
	 */
	public Long getAttributeId() {
		return attributeId;
	}

	/**
	 * @param attributeId The attributeId to set.
	 */
	public void setAttributeId(Long id) {
		this.attributeId = id;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the typeCode.
	 */
	public Integer getTypeCode() {
		return typeCode;
	}

	/**
	 * @param typeCode The typeCode to set.
	 */
	public void setTypeCode(Integer typeCode) {
		this.typeCode = typeCode;
	}
	
	/**
	 * @return Returns the sequencNumber.
	 */
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}
	
	/**
	 * @param sequencNumber The sequencNumber to set.
	 */
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
		
	/**
	 * @return Returns the className.
	 */
	public String getClassName() {
		return className;
	}
	
	/**
	 * @param className The className to set.
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	
	//validation methods
	public void validate(ValidationErrorList vel) {
		//TODO Do we need to fill this in?
	}
}
