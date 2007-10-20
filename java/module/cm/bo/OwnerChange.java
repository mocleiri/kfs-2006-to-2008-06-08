package org.kuali.module.cams.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.Chart;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class OwnerChange extends PersistableBusinessObjectBase {

	private String oldOrganizationOwnerChartCode;
	private String oldOrganizationOwnerAccountNumber;
	private Date changeTransactionRequestDate;
	private String newOrganizationOwnerChartCode;
	private String newOrganizationOwnerAccountNumber;
	private Date changeTransactionExecuteDate;
	private Long changeTransactionRecordCount;

    private Chart oldOrganizationOwnerChart;
    private Chart newOrganizationOwnerChart;
    private Account oldOrganizationOwnerAccount;
	private Account newOrganizationOwnerAccount;

	/**
	 * Default constructor.
	 */
	public OwnerChange() {

	}

	/**
	 * Gets the oldOrganizationOwnerChartCode attribute.
	 * 
	 * @return Returns the oldOrganizationOwnerChartCode
	 * 
	 */
	public String getOldOrganizationOwnerChartCode() { 
		return oldOrganizationOwnerChartCode;
	}

	/**
	 * Sets the oldOrganizationOwnerChartCode attribute.
	 * 
	 * @param oldOrganizationOwnerChartCode The oldOrganizationOwnerChartCode to set.
	 * 
	 */
	public void setOldOrganizationOwnerChartCode(String oldOrganizationOwnerChartCode) {
		this.oldOrganizationOwnerChartCode = oldOrganizationOwnerChartCode;
	}


	/**
	 * Gets the oldOrganizationOwnerAccountNumber attribute.
	 * 
	 * @return Returns the oldOrganizationOwnerAccountNumber
	 * 
	 */
	public String getOldOrganizationOwnerAccountNumber() { 
		return oldOrganizationOwnerAccountNumber;
	}

	/**
	 * Sets the oldOrganizationOwnerAccountNumber attribute.
	 * 
	 * @param oldOrganizationOwnerAccountNumber The oldOrganizationOwnerAccountNumber to set.
	 * 
	 */
	public void setOldOrganizationOwnerAccountNumber(String oldOrganizationOwnerAccountNumber) {
		this.oldOrganizationOwnerAccountNumber = oldOrganizationOwnerAccountNumber;
	}


	/**
	 * Gets the changeTransactionRequestDate attribute.
	 * 
	 * @return Returns the changeTransactionRequestDate
	 * 
	 */
	public Date getChangeTransactionRequestDate() { 
		return changeTransactionRequestDate;
	}

	/**
	 * Sets the changeTransactionRequestDate attribute.
	 * 
	 * @param changeTransactionRequestDate The changeTransactionRequestDate to set.
	 * 
	 */
	public void setChangeTransactionRequestDate(Date changeTransactionRequestDate) {
		this.changeTransactionRequestDate = changeTransactionRequestDate;
	}


	/**
	 * Gets the newOrganizationOwnerChartCode attribute.
	 * 
	 * @return Returns the newOrganizationOwnerChartCode
	 * 
	 */
	public String getNewOrganizationOwnerChartCode() { 
		return newOrganizationOwnerChartCode;
	}

	/**
	 * Sets the newOrganizationOwnerChartCode attribute.
	 * 
	 * @param newOrganizationOwnerChartCode The newOrganizationOwnerChartCode to set.
	 * 
	 */
	public void setNewOrganizationOwnerChartCode(String newOrganizationOwnerChartCode) {
		this.newOrganizationOwnerChartCode = newOrganizationOwnerChartCode;
	}


	/**
	 * Gets the newOrganizationOwnerAccountNumber attribute.
	 * 
	 * @return Returns the newOrganizationOwnerAccountNumber
	 * 
	 */
	public String getNewOrganizationOwnerAccountNumber() { 
		return newOrganizationOwnerAccountNumber;
	}

	/**
	 * Sets the newOrganizationOwnerAccountNumber attribute.
	 * 
	 * @param newOrganizationOwnerAccountNumber The newOrganizationOwnerAccountNumber to set.
	 * 
	 */
	public void setNewOrganizationOwnerAccountNumber(String newOrganizationOwnerAccountNumber) {
		this.newOrganizationOwnerAccountNumber = newOrganizationOwnerAccountNumber;
	}


	/**
	 * Gets the changeTransactionExecuteDate attribute.
	 * 
	 * @return Returns the changeTransactionExecuteDate
	 * 
	 */
	public Date getChangeTransactionExecuteDate() { 
		return changeTransactionExecuteDate;
	}

	/**
	 * Sets the changeTransactionExecuteDate attribute.
	 * 
	 * @param changeTransactionExecuteDate The changeTransactionExecuteDate to set.
	 * 
	 */
	public void setChangeTransactionExecuteDate(Date changeTransactionExecuteDate) {
		this.changeTransactionExecuteDate = changeTransactionExecuteDate;
	}


	/**
	 * Gets the changeTransactionRecordCount attribute.
	 * 
	 * @return Returns the changeTransactionRecordCount
	 * 
	 */
	public Long getChangeTransactionRecordCount() { 
		return changeTransactionRecordCount;
	}

	/**
	 * Sets the changeTransactionRecordCount attribute.
	 * 
	 * @param changeTransactionRecordCount The changeTransactionRecordCount to set.
	 * 
	 */
	public void setChangeTransactionRecordCount(Long changeTransactionRecordCount) {
		this.changeTransactionRecordCount = changeTransactionRecordCount;
	}


	/**
	 * Gets the oldOrganizationOwnerChart attribute.
	 * 
	 * @return Returns the oldOrganizationOwnerChart
	 * 
	 */
	public Chart getOldOrganizationOwnerChart() { 
		return oldOrganizationOwnerChart;
	}

	/**
	 * Sets the oldOrganizationOwnerChart attribute.
	 * 
	 * @param oldOrganizationOwnerChart The oldOrganizationOwnerChart to set.
	 * @deprecated
	 */
	public void setOldOrganizationOwnerChart(Chart oldOrganizationOwnerChart) {
		this.oldOrganizationOwnerChart = oldOrganizationOwnerChart;
	}

	/**
	 * Gets the oldOrganizationOwnerAccount attribute.
	 * 
	 * @return Returns the oldOrganizationOwnerAccount
	 * 
	 */
	public Account getOldOrganizationOwnerAccount() { 
		return oldOrganizationOwnerAccount;
	}

	/**
	 * Sets the oldOrganizationOwnerAccount attribute.
	 * 
	 * @param oldOrganizationOwnerAccount The oldOrganizationOwnerAccount to set.
	 * @deprecated
	 */
	public void setOldOrganizationOwnerAccount(Account oldOrganizationOwnerAccount) {
		this.oldOrganizationOwnerAccount = oldOrganizationOwnerAccount;
	}

	/**
	 * Gets the newOrganizationOwnerAccount attribute.
	 * 
	 * @return Returns the newOrganizationOwnerAccount
	 * 
	 */
	public Account getNewOrganizationOwnerAccount() { 
		return newOrganizationOwnerAccount;
	}

	/**
	 * Sets the newOrganizationOwnerAccount attribute.
	 * 
	 * @param newOrganizationOwnerAccount The newOrganizationOwnerAccount to set.
	 * @deprecated
	 */
	public void setNewOrganizationOwnerAccount(Account newOrganizationOwnerAccount) {
		this.newOrganizationOwnerAccount = newOrganizationOwnerAccount;
	}

	/**
	 * Gets the newOrganizationOwnerChart attribute.
	 * 
	 * @return Returns the newOrganizationOwnerChart
	 * 
	 */
	public Chart getNewOrganizationOwnerChart() { 
		return newOrganizationOwnerChart;
	}

	/**
	 * Sets the newOrganizationOwnerChart attribute.
	 * 
	 * @param newOrganizationOwnerChart The newOrganizationOwnerChart to set.
	 * @deprecated
	 */
	public void setNewOrganizationOwnerChart(Chart newOrganizationOwnerChart) {
		this.newOrganizationOwnerChart = newOrganizationOwnerChart;
	}

	/**
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
	    LinkedHashMap m = new LinkedHashMap();	    
        m.put("oldOrganizationOwnerChartCode", this.oldOrganizationOwnerChartCode);
        m.put("oldOrganizationOwnerAccountNumber", this.oldOrganizationOwnerAccountNumber);
        if (this.changeTransactionRequestDate != null) {
            m.put("changeTransactionRequestDate", this.changeTransactionRequestDate.toString());
        }
	    return m;
    }
}
