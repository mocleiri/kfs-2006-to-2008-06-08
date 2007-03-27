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
package org.kuali.service;

import java.util.Collection;
import java.util.List;

import org.kuali.bo.BusinessObject;
import org.kuali.bo.Document;
import org.kuali.bo.DocumentStatusChange;
import org.kuali.exceptions.IllegalObjectStateException;
import org.kuali.validation.ValidationErrorList;

import edu.iu.uis.eden.exception.EdenException;
import edu.iu.uis.eden.exception.EdenUserNotFoundException;

/**
 * 
 * @author bmcgough
 *
 * This is the DocumentService interface which must have an implementation that accompanies it.  This interfaces defines
 * all of the generally required methods for all document instances
 * 
 */

//TODO put exceptions that are kuali based into here instead of implementation based    

public interface DocumentService {

    /**
     * get a new blank document instance based on the document type name
     * 
     * @param documentTypeName
     * @return
     * @throws EdenUserNotFoundException
     * @throws EdenException
     */
    public Document getNewDocument(String documentTypeName) throws EdenUserNotFoundException, EdenException;
    
    /**
     * get a document based on the document header id which is the primary key for all document types
     *  
     * @param documentHeaderId
     * @return
     * @throws EdenUserNotFoundException
     * @throws EdenException
     */
    public Document getByDocumentHeaderId(Long documentHeaderId) throws EdenUserNotFoundException, EdenException;
    
    /**
     * save the document which will save it to the system as well as save it to workflow which will save it into
     * the users action list so that they can come back to the document at a later time.  Optionally providing a list
     * of ad hoc recipients and additionally an annotation to show up in the route log for this document
     * 
     * @param document
     * @param annotation
     * @param adHocRecipients
     * @return
     * @throws EdenException
     * @throws ValidationErrorList
     * @throws IllegalObjectStateException
     */
    public Document save(Document document, String annotation, List adHocRecipients) throws EdenException, ValidationErrorList, IllegalObjectStateException;
    
    /**
     * start the route the document for approval, optionally providing a list of ad hoc recipients, and additionally
     * provideing a annotation to show up in the route log for the document
     * 
     * @param document
     * @param annotation
     * @param adHocRoutingRecipients
     * @return
     * @throws EdenException
     * @throws ValidationErrorList
     * @throws IllegalObjectStateException
     */
    public Document route(Document document, String annotation, List adHocRoutingRecipients) throws EdenException, ValidationErrorList, IllegalObjectStateException;
    
    /**
     * approve this document, optionally providing an annotation which will show up in the route log
     * for this document for this action taken, and optionally providing a list of ad hoc
     * recipients for the document
     * 
     * @param document
     * @param annotation
     * @param adHocRoutingRecipients
     * @return
     * @throws EdenException
     * @throws ValidationErrorList
     * @throws IllegalObjectStateException
     */
    public Document approve(Document document, String annotation, List adHocRoutingRecipients) throws EdenException, ValidationErrorList, IllegalObjectStateException;
    
    /**
     * disapprove this document, optionally providing an annotation for the disapproval which will show up in the
     * route log for the document for this action taken
     * 
     * @param document
     * @param annotation
     * @return
     * @throws EdenException
     */
    public Document disapprove(Document document, String annotation) throws EdenException;
    
    /**
     * cancel this document, optionally providing an annotation for the disapproval which will show up in the route
     * log for the document for this action taken
     * 
     * @param document
     * @param annotation
     * @return
     * @throws EdenException
     */
    public Document cancel(Document document, String annotation) throws EdenException;
    
    /**
     * acknowledge this document, optionally providing an annotation for the acknowledgement which will show
     * up in the route log for the document for this acknowledgement, additionally optionally provide a list of
     * ad hoc recipients that should recieve this document.  The list of ad hoc recipients for this document should
     * have an action requested of acknowledge or fyi as all other actions requested will be discarded as invalid
     * based on the action being taken being an acknowledgement.
     * 
     * @param document
     * @param annotation
     * @param adHocRecipients
     * @return
     * @throws EdenException
     */
    public Document acknowledge(Document document, String annotation, List adHocRecipients) throws EdenException;
    
    /**
     * blanket approve this document which will approve the document and stand in for an approve for all typically
     * generated approval actions requested for this document.  The user must have blanket approval authority for this
     * document by being registered as a user in the blanket approval workgroup that is associated with this document
     * type.  Optionally an annotation can be provided which will show up for this action taken on the document in the
     * route log.  Additionally optionally provide a list of ad hoc recipients for this document, which should be restricted
     * to actions requested of acknowledge and fyi as all other actions requested will be discarded
     * 
     * @param document
     * @param annotation
     * @param adHocRecipients
     * @return
     * @throws EdenException
     * @throws ValidationErrorList
     * @throws IllegalObjectStateException
     */
    public Document blanketApprove(Document document, String annotation, List adHocRecipients) throws EdenException, ValidationErrorList, IllegalObjectStateException;
    
    /**
     * clear the fyi request for this document, optionally providing a list of ad hoc recipients for this document, which
     * should be restricted to action requested of fyi as all other actions requested will be discarded
     * 
     * @param document
     * @param adHocRecipients
     * @return
     * @throws EdenException
     */
    public Document clearFyi(Document document, List adHocRecipients) throws EdenException;
    
    //TODO after workflow 2.0 release we will need to move to a different mechanism
    
    /**
     * temporary method to provide plumbing for getting a handling on the routing status
     * transitions of documents to allow for post processing without intimate integration
     * with the workflow engine prior to release of onestart workflow version 2.0
     */
    public Collection getDocumentStatusChanges();
    
    /**
     * temporary method to provide plumbing for saving the fact that a document status change
     * was processed so that it occurs only once.  This is to allow for post processing
     * without intimate integration with the workflow engine prior to release of onestart 
     * workflow version 2.0
     * 
     * @param documentStatusChange
     */
    public void saveDocumentStatusChange(DocumentStatusChange documentStatusChange);
    
    /**
     * This method is to provide a facility to save the actual changes to maintainable
     * business objects from the document from which they are being maintained
     * 
     * @param document
     * @param object
     */
    public void saveMaintainableBusinessObject(BusinessObject businessObject);
    
    /**
     * 
     * This method is to allow for documents to be updated
     * which is currently used to update the document status as
     * well as to allow for locked docs to be unlocked
     * 
     * @param document
     */
    public void updateDocument(Document document);
    

    /**
     * This method is to handle route status changes of docs
     * 
     * @param document
     * @param object
     */
    public void handleDocumentRouteStatusChangeEvent(DocumentStatusChange documentStatusChange);
    
    
}
