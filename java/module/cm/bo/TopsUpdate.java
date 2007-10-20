package org.kuali.module.cams.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class TopsUpdate extends PersistableBusinessObjectBase {

	private String beginningOriginationCode;
	private Long beginningCapitalAssetNumber;
	private String endingOriginationCode;
	private Long endingCapitalAssetNumber;
	private Date expenditureFinancialDocumentPostedDate;
	private String purchaseOrderNumber;

	/**
	 * Default constructor.
	 */
	public TopsUpdate() {

	}

	/**
	 * Gets the beginningOriginationCode attribute.
	 * 
	 * @return Returns the beginningOriginationCode
	 * 
	 */
	public String getBeginningOriginationCode() { 
		return beginningOriginationCode;
	}

	/**
	 * Sets the beginningOriginationCode attribute.
	 * 
	 * @param beginningOriginationCode The beginningOriginationCode to set.
	 * 
	 */
	public void setBeginningOriginationCode(String beginningOriginationCode) {
		this.beginningOriginationCode = beginningOriginationCode;
	}


	/**
	 * Gets the beginningCapitalAssetNumber attribute.
	 * 
	 * @return Returns the beginningCapitalAssetNumber
	 * 
	 */
	public Long getBeginningCapitalAssetNumber() { 
		return beginningCapitalAssetNumber;
	}

	/**
	 * Sets the beginningCapitalAssetNumber attribute.
	 * 
	 * @param beginningCapitalAssetNumber The beginningCapitalAssetNumber to set.
	 * 
	 */
	public void setBeginningCapitalAssetNumber(Long beginningCapitalAssetNumber) {
		this.beginningCapitalAssetNumber = beginningCapitalAssetNumber;
	}


	/**
	 * Gets the endingOriginationCode attribute.
	 * 
	 * @return Returns the endingOriginationCode
	 * 
	 */
	public String getEndingOriginationCode() { 
		return endingOriginationCode;
	}

	/**
	 * Sets the endingOriginationCode attribute.
	 * 
	 * @param endingOriginationCode The endingOriginationCode to set.
	 * 
	 */
	public void setEndingOriginationCode(String endingOriginationCode) {
		this.endingOriginationCode = endingOriginationCode;
	}


	/**
	 * Gets the endingCapitalAssetNumber attribute.
	 * 
	 * @return Returns the endingCapitalAssetNumber
	 * 
	 */
	public Long getEndingCapitalAssetNumber() { 
		return endingCapitalAssetNumber;
	}

	/**
	 * Sets the endingCapitalAssetNumber attribute.
	 * 
	 * @param endingCapitalAssetNumber The endingCapitalAssetNumber to set.
	 * 
	 */
	public void setEndingCapitalAssetNumber(Long endingCapitalAssetNumber) {
		this.endingCapitalAssetNumber = endingCapitalAssetNumber;
	}


	/**
	 * Gets the expenditureFinancialDocumentPostedDate attribute.
	 * 
	 * @return Returns the expenditureFinancialDocumentPostedDate
	 * 
	 */
	public Date getExpenditureFinancialDocumentPostedDate() { 
		return expenditureFinancialDocumentPostedDate;
	}

	/**
	 * Sets the expenditureFinancialDocumentPostedDate attribute.
	 * 
	 * @param expenditureFinancialDocumentPostedDate The expenditureFinancialDocumentPostedDate to set.
	 * 
	 */
	public void setExpenditureFinancialDocumentPostedDate(Date expenditureFinancialDocumentPostedDate) {
		this.expenditureFinancialDocumentPostedDate = expenditureFinancialDocumentPostedDate;
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
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
	    LinkedHashMap m = new LinkedHashMap();	    
        m.put("beginningOriginationCode", this.beginningOriginationCode);
        if (this.beginningCapitalAssetNumber != null) {
            m.put("beginningCapitalAssetNumber", this.beginningCapitalAssetNumber.toString());
        }
	    return m;
    }
}
