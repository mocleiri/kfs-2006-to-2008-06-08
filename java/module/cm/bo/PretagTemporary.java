package org.kuali.module.cams.bo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.module.chart.bo.Chart;
import org.kuali.module.chart.bo.Org;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class PretagTemporary extends PersistableBusinessObjectBase {

	private String purchaseOrderNumber;
	private Long lineItemNumber;
	private BigDecimal quantityInvoiced;
	private String vendorName;
	private String assetTopsDescription;
	private String organizationInventoryName;
	private Date pretagCreateDate;
	private String chartOfAccountsCode;
	private String organizationCode;

    private Org organization;
	private Chart chartOfAccounts;

	/**
	 * Default constructor.
	 */
	public PretagTemporary() {

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
	 * Gets the lineItemNumber attribute.
	 * 
	 * @return Returns the lineItemNumber
	 * 
	 */
	public Long getLineItemNumber() { 
		return lineItemNumber;
	}

	/**
	 * Sets the lineItemNumber attribute.
	 * 
	 * @param lineItemNumber The lineItemNumber to set.
	 * 
	 */
	public void setLineItemNumber(Long lineItemNumber) {
		this.lineItemNumber = lineItemNumber;
	}


	/**
	 * Gets the quantityInvoiced attribute.
	 * 
	 * @return Returns the quantityInvoiced
	 * 
	 */
	public BigDecimal getQuantityInvoiced() { 
		return quantityInvoiced;
	}

	/**
	 * Sets the quantityInvoiced attribute.
	 * 
	 * @param quantityInvoiced The quantityInvoiced to set.
	 * 
	 */
	public void setQuantityInvoiced(BigDecimal quantityInvoiced) {
		this.quantityInvoiced = quantityInvoiced;
	}


	/**
	 * Gets the vendorName attribute.
	 * 
	 * @return Returns the vendorName
	 * 
	 */
	public String getVendorName() { 
		return vendorName;
	}

	/**
	 * Sets the vendorName attribute.
	 * 
	 * @param vendorName The vendorName to set.
	 * 
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}


	/**
	 * Gets the assetTopsDescription attribute.
	 * 
	 * @return Returns the assetTopsDescription
	 * 
	 */
	public String getAssetTopsDescription() { 
		return assetTopsDescription;
	}

	/**
	 * Sets the assetTopsDescription attribute.
	 * 
	 * @param assetTopsDescription The assetTopsDescription to set.
	 * 
	 */
	public void setAssetTopsDescription(String assetTopsDescription) {
		this.assetTopsDescription = assetTopsDescription;
	}


	/**
	 * Gets the organizationInventoryName attribute.
	 * 
	 * @return Returns the organizationInventoryName
	 * 
	 */
	public String getOrganizationInventoryName() { 
		return organizationInventoryName;
	}

	/**
	 * Sets the organizationInventoryName attribute.
	 * 
	 * @param organizationInventoryName The organizationInventoryName to set.
	 * 
	 */
	public void setOrganizationInventoryName(String organizationInventoryName) {
		this.organizationInventoryName = organizationInventoryName;
	}


	/**
	 * Gets the pretagCreateDate attribute.
	 * 
	 * @return Returns the pretagCreateDate
	 * 
	 */
	public Date getPretagCreateDate() { 
		return pretagCreateDate;
	}

	/**
	 * Sets the pretagCreateDate attribute.
	 * 
	 * @param pretagCreateDate The pretagCreateDate to set.
	 * 
	 */
	public void setPretagCreateDate(Date pretagCreateDate) {
		this.pretagCreateDate = pretagCreateDate;
	}


	/**
	 * Gets the chartOfAccountsCode attribute.
	 * 
	 * @return Returns the chartOfAccountsCode
	 * 
	 */
	public String getChartOfAccountsCode() { 
		return chartOfAccountsCode;
	}

	/**
	 * Sets the chartOfAccountsCode attribute.
	 * 
	 * @param chartOfAccountsCode The chartOfAccountsCode to set.
	 * 
	 */
	public void setChartOfAccountsCode(String chartOfAccountsCode) {
		this.chartOfAccountsCode = chartOfAccountsCode;
	}


	/**
	 * Gets the organizationCode attribute.
	 * 
	 * @return Returns the organizationCode
	 * 
	 */
	public String getOrganizationCode() { 
		return organizationCode;
	}

	/**
	 * Sets the organizationCode attribute.
	 * 
	 * @param organizationCode The organizationCode to set.
	 * 
	 */
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}


	/**
	 * Gets the organization attribute.
	 * 
	 * @return Returns the organization
	 * 
	 */
	public Org getOrganization() { 
		return organization;
	}

	/**
	 * Sets the organization attribute.
	 * 
	 * @param organization The organization to set.
	 * @deprecated
	 */
	public void setOrganization(Org organization) {
		this.organization = organization;
	}

	/**
	 * Gets the chartOfAccounts attribute.
	 * 
	 * @return Returns the chartOfAccounts
	 * 
	 */
	public Chart getChartOfAccounts() { 
		return chartOfAccounts;
	}

	/**
	 * Sets the chartOfAccounts attribute.
	 * 
	 * @param chartOfAccounts The chartOfAccounts to set.
	 * @deprecated
	 */
	public void setChartOfAccounts(Chart chartOfAccounts) {
		this.chartOfAccounts = chartOfAccounts;
	}

	/**
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
	    LinkedHashMap m = new LinkedHashMap();	    
        m.put("purchaseOrderNumber", this.purchaseOrderNumber);
        if (this.lineItemNumber != null) {
            m.put("lineItemNumber", this.lineItemNumber.toString());
        }
	    return m;
    }
}
