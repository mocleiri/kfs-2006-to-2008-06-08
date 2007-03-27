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

import java.io.Serializable;
import java.util.List;

import org.kuali.exceptions.IllegalObjectStateException;
import org.kuali.validation.ValidationErrorList;

/**
 * 
 * @author bmcgough
 *
 * The document interface is the interface that must be implemented by all documents
 * in the kuali system.
 * 
 */
public interface Document extends Serializable {        
    
    /**
     * validateDocument should be implemented such that the document is valid for saving
     * and not necessarily that the document is complete
     *  
     * @param errors
     * @throws ValidationErrorList
     * @throws IllegalObjectStateException
     */
    public void validateDocument(ValidationErrorList errors) throws ValidationErrorList, IllegalObjectStateException;
    
    /**
     * validateForRouting should be implemented such that the document is complete and valid
     *  
     * @param errors
     * @throws ValidationErrorList
     * @throws IllegalObjectStateException
     */
    public void validateForRouting(ValidationErrorList errors) throws ValidationErrorList, IllegalObjectStateException;

    /**
     * @return document header since all docs will have a document header
     */
    public DocumentHeader getDocumentHeader();
    
    /**
     * setter fo the document header since all docs will have a document header
     * @param documentHeader
     */
    public void setDocumentHeader(DocumentHeader documentHeader);
    
    /**
     * @return doc header id
     */
    public Long getDocumentHeaderId();
    
    /**
     * setter for document header id
     * @param documentHeaderId
     */
    public void setDocumentHeaderId(Long documentHeaderId);
    
    /**
     * getter for version number
     * @return
     */
	public Long getVersionNumber();
	
	/**
	 * setter for version number
	 * @param versionNumber
	 */
	public void setVersionNumber(Long versionNumber);
	
    /**
     * @return true if some workflow-related action is being requested of the current user for this document
     */
	public boolean isActionable();
   
	/**
     * @return true if the document contents can be modified by the current user
     */
	public boolean isModifiable();
  
	/**
     * @return true if the document can be canceled by the current user
     */
	public boolean isCancelable();
	
	/**
	 * method to integrate with workflow, where we will actually populate the document (flexdoc) for
	 * routing, this method will be called right before a document is sent workflow.
	 *
	 */
	public void populateDocumentForRouting();
	
	/**
	 * setter for validation number that is required by our validation mechanism
	 * 
	 * @param validationNumber
	 */
	public void setValidationNumber(Integer validationNumber);
	
	/**
	 * getter for validation number that is required by our validation mechnanism
	 * 
	 * @return
	 */
	public Integer getValidationNumber();
	
	/**
	 * method to integrate with workflow, where we will actually handle the transitions of status
	 * for documents
	 * 
	 * @param newRouteStatus
	 */
	public void handleRouteStatusChange(String newRouteStatus);
	
	/**
	 * getter method to get the document title as it will appear and be searchable in workflow
	 * 
	 * @return
	 */
	public String getDocumentTitle();
	
	/**
	 * getter method to get the list of ad hoc route persons associated with a document
	 * at a point in time, this list is only valid for a given users version of a document
	 * as this state is only persisted in workflow itself when someone takes an action on 
	 * a document
	 * 
	 * @return
	 */
	public List getAdHocRoutePersons();
	
	/**
	 * getter method to get the list of ad hoc route workgroups associated with a document
	 * at a point in time, this list is only valid for a given users version of a document
	 * as this state is only persisted in workflow itself when someone takes an action on 
	 * a document
	 * 
	 * @return
	 */
	public List getAdHocRouteWorkgroups();
	
	/**
	 * setter method to set the list of ad hoc route persons associated with a document
	 * at a point in time, this list is only valid for a given users version of a document
	 * as this state is only persisted in workflow itself when someone takes an action on 
	 * a document
	 * 
	 * @param adHocRoutePersons
	 */
	public void setAdHocRoutePersons(List adHocRoutePersons);

	/**
	 * setter method to set the list of ad hoc route workgroups associated with a document
	 * at a point in time, this list is only valid for a given users version of a document
	 * as this state is only persisted in workflow itself when someone takes an action on 
	 * a document
	 * 
	 * @param adHocRoutePersons
	 */
	public void setAdHocRouteWorkgroups(List adHocRouteWorkgroups);
	
}
