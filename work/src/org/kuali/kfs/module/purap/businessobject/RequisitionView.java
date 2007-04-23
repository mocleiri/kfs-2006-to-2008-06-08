package org.kuali.module.purap.bo;

import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.core.bo.Note;
import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.module.purap.document.PurchasingAccountsPayableDocumentBase;
import org.kuali.module.purap.document.RequisitionDocument;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class RequisitionView extends AbstractRelatedView {
    private Integer requisitionIdentifier;

	/**
	 * Gets the requisitionIdentifier attribute.
	 *
	 * @return Returns the requisitionIdentifier
	 *
	 */
	public Integer getRequisitionIdentifier() {
		return requisitionIdentifier;
	}

	/**
	 * Sets the requisitionIdentifier attribute.
	 *
	 * @param requisitionIdentifier The requisitionIdentifier to set.
	 *
	 */
	public void setRequisitionIdentifier(Integer requisitionIdentifier) {
		this.requisitionIdentifier = requisitionIdentifier;
	}

    public List<Note> getNotes() {
        return super.getNotes();
    }
}
