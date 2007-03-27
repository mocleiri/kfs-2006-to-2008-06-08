package org.kuali.bo;

import org.kuali.exceptions.IllegalObjectStateException;
import org.kuali.validation.ValidationErrorList;

import edu.iu.uis.eden.EdenConstants;

/**
 * @author bmcgough
 *
 * A temporary object until workflow 2.0
 * 
 */
public class DocumentStatusChange extends BusinessObjectBase implements Comparable {

    private Long documentHeaderId;
    private String statusChangeEvent;
    private boolean eventRecognized;
    private boolean returnSuccess;
    
    public Long getDocumentHeaderId() {
        return documentHeaderId;
    }
    public void setDocumentHeaderId(Long documentHeaderId) {
        this.documentHeaderId = documentHeaderId;
    }
    public boolean isEventRecognized() {
        return eventRecognized;
    }
    public void setEventRecognized(boolean eventRecognized) {
        this.eventRecognized = eventRecognized;
    }
    public boolean isReturnSuccess() {
        return returnSuccess;
    }
    public void setReturnSuccess(boolean returnSuccess) {
        this.returnSuccess = returnSuccess;
    }
    public String getStatusChangeEvent() {
        return statusChangeEvent;
    }
    public void setStatusChangeEvent(String statusChangeEvent) {
        this.statusChangeEvent = statusChangeEvent;
    }
    public void validate(ValidationErrorList errors) throws ValidationErrorList, IllegalObjectStateException {
        // TODO Auto-generated method stub
    }

    
    
    public int compareTo(Object o) {
        DocumentStatusChange docStatusChange = (DocumentStatusChange) o;
        if (docStatusChange.getDocumentHeaderId().equals(this.getDocumentHeaderId())) {
            if (docStatusChange.getStatusChangeEvent().equals(EdenConstants.ROUTE_HEADER_ENROUTE_CD)) {
                return 1;
            }
            if (docStatusChange.getStatusChangeEvent().equals(EdenConstants.ROUTE_HEADER_CANCEL_CD)) {
                return -1;
            }
            if (docStatusChange.getStatusChangeEvent().equals(EdenConstants.ROUTE_HEADER_DISAPPROVED_CD)) {
                return -1;
            }
            if (docStatusChange.getStatusChangeEvent().equals(EdenConstants.ROUTE_HEADER_PROCESSED_CD)) {
                return -1;
            }
        } else {
            return docStatusChange.getDocumentHeaderId().compareTo(this.getDocumentHeaderId());
        }
        return 0;
    }
}
