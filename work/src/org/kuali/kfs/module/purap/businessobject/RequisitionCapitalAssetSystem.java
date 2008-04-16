package org.kuali.module.purap.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */

public class RequisitionCapitalAssetSystem extends PersistableBusinessObjectBase {

	private Integer requisitionIdentifier;
	private Integer capitalAssetSystemNumber;
	private String capitalAssetSystemDescription;
	private boolean capitalAssetNotReceivedCurrentFiscalYearIndicator;
	private String capitalAssetTypeCode;
	private boolean capitalAssetManufacturerIsVendorIndicator;
	private String capitalAssetManufacturerName;
	private String capitalAssetModelDescription;

	/**
	 * Default constructor.
	 */
	public RequisitionCapitalAssetSystem() {

	}

	public Integer getRequisitionIdentifier() {
        return requisitionIdentifier;
    }

    public void setRequisitionIdentifier(Integer requisitionIdentifier) {
        this.requisitionIdentifier = requisitionIdentifier;
    }

    public Integer getCapitalAssetSystemNumber() {
        return capitalAssetSystemNumber;
    }

    public void setCapitalAssetSystemNumber(Integer capitalAssetSystemNumber) {
        this.capitalAssetSystemNumber = capitalAssetSystemNumber;
    }

    public String getCapitalAssetSystemDescription() {
        return capitalAssetSystemDescription;
    }

    public void setCapitalAssetSystemDescription(String capitalAssetSystemDescription) {
        this.capitalAssetSystemDescription = capitalAssetSystemDescription;
    }

    public boolean isCapitalAssetNotReceivedCurrentFiscalYearIndicator() {
        return capitalAssetNotReceivedCurrentFiscalYearIndicator;
    }

    public void setCapitalAssetNotReceivedCurrentFiscalYearIndicator(boolean capitalAssetNotReceivedCurrentFiscalYearIndicator) {
        this.capitalAssetNotReceivedCurrentFiscalYearIndicator = capitalAssetNotReceivedCurrentFiscalYearIndicator;
    }

    public String getCapitalAssetTypeCode() {
        return capitalAssetTypeCode;
    }

    public void setCapitalAssetTypeCode(String capitalAssetTypeCode) {
        this.capitalAssetTypeCode = capitalAssetTypeCode;
    }

    public boolean isCapitalAssetManufacturerIsVendorIndicator() {
        return capitalAssetManufacturerIsVendorIndicator;
    }

    public void setCapitalAssetManufacturerIsVendorIndicator(boolean capitalAssetManufacturerIsVendorIndicator) {
        this.capitalAssetManufacturerIsVendorIndicator = capitalAssetManufacturerIsVendorIndicator;
    }

    public String getCapitalAssetManufacturerName() {
        return capitalAssetManufacturerName;
    }

    public void setCapitalAssetManufacturerName(String capitalAssetManufacturerName) {
        this.capitalAssetManufacturerName = capitalAssetManufacturerName;
    }

    public String getCapitalAssetModelDescription() {
        return capitalAssetModelDescription;
    }

    public void setCapitalAssetModelDescription(String capitalAssetModelDescription) {
        this.capitalAssetModelDescription = capitalAssetModelDescription;
    }

    /**
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
	    LinkedHashMap m = new LinkedHashMap();	    
        if (this.requisitionIdentifier != null) {
            m.put("requisitionIdentifier", this.requisitionIdentifier.toString());
        }
        if (this.capitalAssetSystemNumber != null) {
            m.put("capitalAssetSystemNumber", this.capitalAssetSystemNumber.toString());
        }
	    return m;
    }
}