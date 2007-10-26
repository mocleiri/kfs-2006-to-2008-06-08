package org.kuali.module.cams.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class PendingPurchaseOrderNote extends PersistableBusinessObjectBase {

	private String purchaseOrderNumber;
	private Long noteLineNumber;
	private String noteTypeCode;
	private String noteDescription;

    private PendingPurchaseOrder purchaseOrder;
    
	/**
	 * Default constructor.
	 */
	public PendingPurchaseOrderNote() {

	}

	/**
	 * Gets the purchaseOrderNumber attribute.
	 * 
	 * @return Returns the purchaseOrderNumber
	 * 
	 */
	public String getPurchaseOrderNumber() { 
		return purchaseOrderNumber;
	}

	/**
	 * Sets the purchaseOrderNumber attribute.
	 * 
	 * @param purchaseOrderNumber The purchaseOrderNumber to set.
	 * 
	 */
	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}


	/**
	 * Gets the noteLineNumber attribute.
	 * 
	 * @return Returns the noteLineNumber
	 * 
	 */
	public Long getNoteLineNumber() { 
		return noteLineNumber;
	}

	/**
	 * Sets the noteLineNumber attribute.
	 * 
	 * @param noteLineNumber The noteLineNumber to set.
	 * 
	 */
	public void setNoteLineNumber(Long noteLineNumber) {
		this.noteLineNumber = noteLineNumber;
	}


	/**
	 * Gets the noteTypeCode attribute.
	 * 
	 * @return Returns the noteTypeCode
	 * 
	 */
	public String getNoteTypeCode() { 
		return noteTypeCode;
	}

	/**
	 * Sets the noteTypeCode attribute.
	 * 
	 * @param noteTypeCode The noteTypeCode to set.
	 * 
	 */
	public void setNoteTypeCode(String noteTypeCode) {
		this.noteTypeCode = noteTypeCode;
	}


	/**
	 * Gets the noteDescription attribute.
	 * 
	 * @return Returns the noteDescription
	 * 
	 */
	public String getNoteDescription() { 
		return noteDescription;
	}

	/**
	 * Sets the noteDescription attribute.
	 * 
	 * @param noteDescription The noteDescription to set.
	 * 
	 */
	public void setNoteDescription(String noteDescription) {
		this.noteDescription = noteDescription;
	}

    /**
     * Gets the purchaseOrder attribute. 
     * @return Returns the purchaseOrder.
     */
    public PendingPurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    /**
     * Sets the purchaseOrder attribute value.
     * @param purchaseOrder The purchaseOrder to set.
     * @deprecated
     */
    public void setPurchaseOrder(PendingPurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
    
	/**
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
	    LinkedHashMap m = new LinkedHashMap();	    
        m.put("purchaseOrderNumber", this.purchaseOrderNumber);
        if (this.noteLineNumber != null) {
            m.put("noteLineNumber", this.noteLineNumber.toString());
        }
        m.put("noteTypeCode", this.noteTypeCode);
	    return m;
    }

}
