package org.kuali.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.kuali.exceptions.IllegalObjectStateException;
import org.kuali.util.GlobalVariables;
import org.kuali.validation.ValidationErrorGroup;
import org.kuali.validation.ValidationErrorList;

import edu.iu.uis.eden.routetemplate.FlexDoc;

/**
 * @author bmcgough
 *
 */
public abstract class DocumentBase implements Document {

    protected Long documentHeaderId;
    protected DocumentHeader documentHeader;
    protected String explanation;
    protected Long versionNumber;
    protected Integer validationNumber;
    
    private List adHocRoutePersons;
    private List adHocRouteWorkgroups;


	public DocumentBase() {
		setDocumentHeader(new DocumentHeader());
        adHocRoutePersons = new ArrayList();
        adHocRouteWorkgroups = new ArrayList();
	}
	
	/**
	 * get the document header for the document 
     */
    public DocumentHeader getDocumentHeader() {
        return this.documentHeader;
    }


	/**
	 * sets the document header for this document 
	 * @param documentHeader
     */
    public void setDocumentHeader(DocumentHeader documentHeader) {
        this.documentHeader = documentHeader;
    }

    /**
	 * @return Returns the documentHeaderId.
	 */
	public Long getDocumentHeaderId() {
		return documentHeaderId;
	}
	
	/**
	 * @param documentHeaderId The documentHeaderId to set.
	 */
	public void setDocumentHeaderId(Long documentHeaderId) {
		this.documentHeaderId = documentHeaderId;
	}

    /**
     * @return Returns the versionNumber.
     */
    public Long getVersionNumber() {
        return versionNumber;
    }

    /**
     * @param versionNumber The versionNumber to set.
     */
    public void setVersionNumber(Long versionNumber) {
        this.versionNumber = versionNumber;
    }

	/**
	 * 
	 * @return
	 */
	public String getExplanation() {
		return this.explanation;
	}

	/**
	 * 
	 * @param explanation
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
    /**
     * 
     * @see org.kuali.bo.Document#isCancelable()
     */
    public boolean isCancelable()
    {
        boolean cancelable = false;
        
        FlexDoc flexDoc = getDocumentHeader().getFlexDoc();
        boolean isInitiator = flexDoc.getInitiatorNetworkId().equals( GlobalVariables.getUserSession().getNetworkId() );
        
        if ( !flexDoc.getRouteHeader().isRouted() && isInitiator ) {
          cancelable = true;
        } else if ( isActionable() ) {
          cancelable = true;
        }
          
        return cancelable;
    }

    /**
     * 
     * @see org.kuali.bo.Document#isActionable()
     */
    public boolean isActionable() {
        boolean actionable = false;

        FlexDoc flexDoc = getDocumentHeader().getFlexDoc();
        if ( flexDoc.isAcknowledgeRequested() || flexDoc.isApprovalRequested()
                || flexDoc.isBlanketApproveCapable()
                || flexDoc.isCompletionRequested() || flexDoc.isFYIRequested() ) {
            actionable = true;
        }

    	return actionable;
    }

    /**
     * 
     * @see org.kuali.bo.Document#isModifiable()
     */
    public boolean isModifiable() {
        boolean modifiable = false;
        
        // TODO: individual document-types need to set isModifiable to true based on when the
        // business logic dictates that that document can be modified; if a document can be
        // modified after it gets routed, some additional workflow sleight-of-hand may be
        // needed if any of the data used to route the document gets modified
        FlexDoc flexDoc = getDocumentHeader().getFlexDoc();
		boolean isInitiator = flexDoc.getInitiatorNetworkId().equals(GlobalVariables.getUserSession().getNetworkId());
        
        // any resemblence between this if and the if in isCancelable is a coincidence; the
        // two methods are conceptually distinct
        if ( !flexDoc.getRouteHeader().isRouted() && isInitiator ) {
            modifiable = true;
        }

        return modifiable;
    }

    /**
     * @return Returns the validationNumber.
     */
    public Integer getValidationNumber() {
        return validationNumber;
    }
    
    
    /**
     * @param validationNumber The validationNumber to set.
     */
    public void setValidationNumber(Integer validationNumber) {
        this.validationNumber = validationNumber;
    }

    /**
     * getter for the ad hoc route persons list
     */
    public List getAdHocRoutePersons() {
        return adHocRoutePersons;
    }
    /**
     * setter for the ad hoc route peresons list
     */
    public void setAdHocRoutePersons(List adHocRoutePersons) {
        this.adHocRoutePersons = adHocRoutePersons;
    }
    /**
     * getter for the ad hoc route workgroups list
     */
    public List getAdHocRouteWorkgroups() {
        return adHocRouteWorkgroups;
    }
    /**
     * setter for the ad hoc route workgroups list
     */
    public void setAdHocRouteWorkgroups(List adHocRouteWorkgroups) {
        this.adHocRouteWorkgroups = adHocRouteWorkgroups;
    }
    
    public void validateDocument(ValidationErrorList errors) throws ValidationErrorList, IllegalObjectStateException {
        validateAdHocRoutingRequests(errors);
    }

    public void validateForRouting(ValidationErrorList errors) throws ValidationErrorList, IllegalObjectStateException {
        validateAdHocRoutingRequests(errors);
    }
    
    /**
     * Convenience method for validating ad hoc routing requests
     * 
     * @param errors
     * @throws IllegalObjectStateException
     */
    private void validateAdHocRoutingRequests(ValidationErrorList errors) throws IllegalObjectStateException {
        ValidationErrorGroup adHocErrorGroup = new ValidationErrorGroup("adHocErrors");
        if (getAdHocRoutePersons() != null) {
            for (Iterator iter = getAdHocRoutePersons().iterator(); iter.hasNext();) {
                AdHocRouteRecipient adHocRoutePerson = (AdHocRouteRecipient) iter.next();
                adHocErrorGroup.accumulateValidationErrors(adHocRoutePerson, true, errors);
            }
        }
        if (getAdHocRouteWorkgroups() != null) {
            for (Iterator iter = getAdHocRouteWorkgroups().iterator(); iter.hasNext();) {
                AdHocRouteRecipient adHocRouteWorkgroup = (AdHocRouteRecipient) iter.next();
                adHocErrorGroup.accumulateValidationErrors(adHocRouteWorkgroup, true, errors);
            }
        }
        errors.addValidationGroup(adHocErrorGroup);
    }
    
}
