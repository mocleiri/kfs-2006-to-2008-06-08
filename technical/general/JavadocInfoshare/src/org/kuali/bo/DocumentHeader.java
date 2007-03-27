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

import org.kuali.Constants;
import org.kuali.exceptions.IllegalObjectStateException;
import org.kuali.validation.ValidationErrorList;

import edu.iu.uis.eden.routetemplate.FlexDoc;


/**
 * DocumentHeader object contains the unique metadata for a document, 
 * and is also tied into Workflow.
 * 
 * @author ahollamona
 */
public class DocumentHeader extends BusinessObjectBase {
	private String documentDescription;
	private Long documentHeaderId;
	private String organizationDocumentNumber;
	private List notes;
	private String kualiDocumentStatusCode;
    private transient FlexDoc flexDoc;

	/**
	 * Constructor - creates empty instances of dependent objects
	 *
	 */
	public DocumentHeader()
	{
		setKualiDocumentStatusCode( Constants.INITIAL_KUALI_DOCUMENT_STATUS_CD );
    
        notes = new ArrayList();
	}

	/**
	 * 
	 * @return kualiDocumentStatusCode
	 */
	public String getKualiDocumentStatusCode() {
		return kualiDocumentStatusCode;
	}

	/**
	 * 
	 * @param kualiDocumentStatusCode
	 */
	public void setKualiDocumentStatusCode(String kualiDocumentStatusCode) {
		this.kualiDocumentStatusCode = kualiDocumentStatusCode;
	}

	/**
	 * @return notes
	 */
	public List getNotes() {
		return notes;
	}
	/**
	 * return a note if in range, or an empty note if not
	 * @param nbr
	 * @return return a note if in range, or an empty note if not
	 */
	public DocumentNote getNote(int nbr) {
	    while(getNotes().size() <= nbr) {
			getNotes().add(new DocumentNote());
		}
	    
	return (DocumentNote)this.getNotes().get(nbr);
	}
	/**
	 * @param notes
	 */
	public void setNotes(List notes) {
		this.notes = notes;
	}

	/**
	 * 
	 * @param note
	 */
	public void addNote(DocumentNote note) {
		notes.add(note);
	}

    /**
     * 
     * @return flexdoc
     */
    public FlexDoc getFlexDoc() {
      if ( flexDoc == null ) {
        throw new RuntimeException( "transient FlexDoc is null - this should never happen" );
      }

      return flexDoc;
    }
    /**
     * 
     * @param flexDoc
     */
    public void setFlexDoc( FlexDoc flexDoc ) {
    this.flexDoc = flexDoc;
    }
 
	/**
	 * 
	 * @return documentDescription
	 */
	public String getDocumentDescription() {
		return documentDescription;
	}

	/**
	 * 
	 * @param documentDescription
	 */
	public void setDocumentDescription(String documentDescription) {
		this.documentDescription = documentDescription;
	}
	
	/**
	 * 
	 * @return documentHeaderId
	 */
	public Long getDocumentHeaderId() {
		return documentHeaderId;
	}

	/**
	 * 
	 * @param documentHeaderId
	 */
	public void setDocumentHeaderId(Long documentHeaderId) {
		this.documentHeaderId = documentHeaderId;
	}

	/**
	 * 
	 * @return
	 */
	public String getOrganizationDocumentNumber() {
		return organizationDocumentNumber;
	}

	/**
	 * 
	 * @param organizationDocumentNumber
	 */
	public void setOrganizationDocumentNumber(String organizationDocumentNumber) {
		this.organizationDocumentNumber = organizationDocumentNumber;
	}

  
    public void validate(ValidationErrorList errors) throws ValidationErrorList, IllegalObjectStateException {
        if (documentHeaderId == null) {
           throw new IllegalObjectStateException("Doc header id is null");
       }
		//TODO: uncomment for note validation see notes validate for more info
		/*
        ValidationErrorGroup documentErrors = new ValidationErrorGroup(Constants.DOCUMENT_NOTES_ERRORS);
        for(Iterator iter = notes.iterator(); iter.hasNext();) {
			DocumentNote note = (DocumentNote)iter.next();
			documentErrors.accumulateValidationErrors(note,false,errors);
		}
        errors.throwErrors();
		*/
    }
  
    // OJB-related
	/**
	 * 
	 * @return
	 */
	public Long getVersionNumber() {
		return this.versionNumber;
	}

	/**
	 * 
	 * @param versionNumber
	 */
	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}
  
    /**
     * 
     * @see java.lang.Object#toString()
     */
	public String toString() {
	  StringBuffer b = new StringBuffer( "DocumentHeader(" );

      b.append( documentHeaderId + "," );
      b.append( documentDescription + "," );
      b.append( organizationDocumentNumber + "," );
      b.append( kualiDocumentStatusCode + ")" );
      
      return b.toString();
    }
}