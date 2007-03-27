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

import org.apache.commons.lang.StringUtils;
import org.kuali.Constants;
import org.kuali.exceptions.IllegalObjectStateException;
import org.kuali.util.KualiUtilities;
import org.kuali.validation.ValidationError;
import org.kuali.validation.ValidationErrorGroup;
import org.kuali.validation.ValidationErrorList;

/**
 * 
 * @author anon, dohester, ckirschenman, rolstad
 *
 * Generic DocumentNote Pojo
 * 
 * Getters/setters for KUL.FS_DOC_NTE_T as specified in OJB mapping.
 * 
 */
public class DocumentNote extends BusinessObjectBase
{

  private Long documentHeaderId;
  private Long documentNoteAuthorEmployeeId;
  private java.sql.Timestamp documentNoteCreationTimestamp;
  private String documentNoteText;
  private java.sql.Timestamp lastUpdateTimestamp;
  private String lastUpdateUserId;
  
  //not used anymore, keep around in case Formatter stuff doesn't work
  /*
  private Long documentNoteCreationTimestampAsLong;
  */
  
  
  public DocumentNote() {
      this("");
  }
  
  public DocumentNote(String noteText) {
      super();

      java.sql.Timestamp now = KualiUtilities.getCurrentDateAsTimestamp();
      this.setDocumentNoteCreationTimestamp(now);
      this.setDocumentNoteText(noteText);
  }

  public Long getDocumentHeaderId() {
     return this.documentHeaderId;
  }
  
  public void setDocumentHeaderId(Long param) {
    this.documentHeaderId = param;
  }

  public Long getDocumentNoteAuthorEmployeeId() {
     return this.documentNoteAuthorEmployeeId;
  }
  
  public void setDocumentNoteAuthorEmployeeId(Long param) {
    this.documentNoteAuthorEmployeeId = param;
  }


  public java.sql.Timestamp getDocumentNoteCreationTimestamp() {
     return this.documentNoteCreationTimestamp;
  }
  
  public void setDocumentNoteCreationTimestamp(java.sql.Timestamp param) {
    this.documentNoteCreationTimestamp = param;
  }

  public String getDocumentNoteText() {
     return this.documentNoteText;
  }
  
  public void setDocumentNoteText(String param) {
    this.documentNoteText = param;
  }

  public java.sql.Timestamp getLastUpdateTimestamp() {
     return this.lastUpdateTimestamp;
  }
  
  public void setLastUpdateTimestamp(java.sql.Timestamp param) {
    this.lastUpdateTimestamp = param;
  }

  public String getLastUpdateUserId() {
     return this.lastUpdateUserId;
  }
  
  public void setLastUpdateUserId(String param) {
    this.lastUpdateUserId = param;
  }

  public Long getVersionNumber() {
     return this.versionNumber;
  }

  public void setVersionNumber(Long param) {
    this.versionNumber = param;
  }

/** 
 * BO validate 
 * @throws ValidationErrorList
 */
  public void validate(ValidationErrorList errors)throws ValidationErrorList{
      
      validationNumber = errors.getNextValidationNumber();
      
      if (StringUtils.isBlank(this.documentNoteText)) {
          ValidationError error = new ValidationError(Constants.DOCUMENT_NOTE_TEXT_PROPERTY_NAME, Constants.ERROR_FIELD_REQUIRED);
          errors.addError(validationNumber,error);
      }
      
      errors.throwErrors();
  }
/**
 * This method instructs the object to validate itself
 * 
 * @throws IllegalObjectStateException
 */  
	public void validate() throws ValidationErrorList, IllegalObjectStateException {

		ValidationErrorList errors = new ValidationErrorList();
		ValidationErrorGroup noteErrors = new ValidationErrorGroup(Constants.DOCUMENT_NOTES_ERRORS);
		
		noteErrors.accumulateValidationErrors(this, false, errors);
		
		errors.addValidationGroup(noteErrors);
		errors.throwErrors();
	}
  
  public String toString(){
    return  " [DOC_HDR_ID] " + documentHeaderId + " [DOC_NTE_AUTH_EMP_ID] " 
    + documentNoteAuthorEmployeeId + " [DOC_NTE_CRTE_TS] " + documentNoteCreationTimestamp 
     + " [DOC_NTE_TXT] " + documentNoteText + " [LST_UPDT_TS] " 
    + lastUpdateTimestamp + " [LST_UPDT_USR_ID] " + lastUpdateUserId + " [VER_NBR] " + versionNumber;
  }

}
