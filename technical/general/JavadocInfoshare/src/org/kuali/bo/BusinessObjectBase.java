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

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.kuali.util.Guid;
import org.kuali.util.SpringServiceLocator;

/**
 * @author ckirschenman
 *
 * Abstract class to give all Business Objects a version number
 */
public abstract class BusinessObjectBase implements BusinessObject {

    protected Long versionNumber;
    protected Integer validationNumber;
    private String objectId;
    private List extendedAttributeValues;
    

    /**
     * Default constructor. Required to do some of the voodoo involved in
     * letting the DataDictionary validate attributeNames for a given
     * BusinessObject subclass.
     */
    public BusinessObjectBase() {
    }

    //  COMMENTED out until versionNumber in db
    /* (non-Javadoc)
     * @see org.kuali.bo.BusinessObject#getVersionNumber()
     */
    
    public Long getVersionNumber() {
        // TODO Auto-generated method stub
        return this.versionNumber;
    } 
    
    /* (non-Javadoc)
     * @see org.kuali.bo.BusinessObject#setVersionNumber()
     */
    
    public void setVersionNumber(Long versionNumber) {
        // TODO Auto-generated method stub
         this.versionNumber = versionNumber;
    }         
    

    /**
     * @return Returns the validationNumber.
     */
    public Integer getValidationNumber() {
        return validationNumber;
    }
    
    
    /**
     * getter for the guid based object id that is assignable to all objects, in order to support custom
     * attributes a mapping must also be added to the OJB file and a column must be added to the database for
     * each business object that extension attributes are supposed to work on.
     * 
     * @return
     */
    public String getObjectId() {
        return objectId;
    }
    
    /**
     * setter for the guid based object id that is assignable to all objects, in order to support custom
     * attributes a mapping must also be added to the OJB file and column must be added to the database for each
     * business object that extension attributes are supposed to work on.
     * 
     * @param objectId
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
    
    /**
     * @param validationNumber The validationNumber to set.
     */
    public void setValidationNumber(Integer validationNumber) {
        this.validationNumber = validationNumber;
    }
    
    /**
     * @return Returns the extendedAttributeValues.
     */
    public List getExtendedAttributeValues() {
        return extendedAttributeValues;
    }
    
    
    
    /**
     * @param extendedAttributeValues The extendedAttributeValues to set.
     */
    public void setExtendedAttributeValues(List extendedAttributeValues) {
        this.extendedAttributeValues = extendedAttributeValues;
    }
    
    public void afterDelete(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
    }

    public void afterInsert(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        populateAndSaveExtendedAttributeValues();
    }

    public void afterLookup(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        this.extendedAttributeValues = SpringServiceLocator.getExtendedAttributeService().getPopulatedListByObject(this);
    }
    
    public void afterUpdate(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        populateAndSaveExtendedAttributeValues();
    }

    public void beforeDelete(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
    }
    public void beforeInsert(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        if (!(this instanceof ExtendedAttributeValue)) {
            this.setObjectId(new Guid().toString());    
        }
    }
    public void beforeUpdate(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        if (!(this instanceof ExtendedAttributeValue)) {
            if (StringUtils.isEmpty(this.getObjectId())) {
                this.setObjectId(new Guid().toString());
            }
        }
    }

    /**
     * Convencience method for insert and update of the extended attributes since,
     * we do not have this behavior from ojb right now
     * 
     */
    private void populateAndSaveExtendedAttributeValues() {
        if (getExtendedAttributeValues() != null && !getExtendedAttributeValues().isEmpty()) { 
            for (Iterator iter = getExtendedAttributeValues().iterator(); iter.hasNext();) {
                ExtendedAttributeValue element = (ExtendedAttributeValue) iter.next();
                element.setObjectId(getObjectId());
            }
    	}
        SpringServiceLocator.getExtendedAttributeService().saveExtendedAttributeValueList(getExtendedAttributeValues());
    }

}
