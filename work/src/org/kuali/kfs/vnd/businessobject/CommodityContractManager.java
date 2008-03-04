package org.kuali.module.vendor.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.Campus;
import org.kuali.core.bo.PersistableBusinessObjectBase;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class CommodityContractManager extends PersistableBusinessObjectBase {

	private String purchasingCommodityCode;
	private String campusCode;
	private Integer contractManagerCode;

    private Campus campus;
    private CommodityCode commodity;
    private ContractManager contractManager;
    
	/**
	 * Default constructor.
	 */
	public CommodityContractManager() {

	}

	/**
	 * Gets the purchasingCommodityCode attribute.
	 * 
	 * @return Returns the purchasingCommodityCode
	 * 
	 */
	public String getPurchasingCommodityCode() { 
		return purchasingCommodityCode;
	}

	/**
	 * Sets the purchasingCommodityCode attribute.
	 * 
	 * @param purchasingCommodityCode The purchasingCommodityCode to set.
	 * 
	 */
	public void setPurchasingCommodityCode(String purchasingCommodityCode) {
		this.purchasingCommodityCode = purchasingCommodityCode;
	}


	/**
	 * Gets the campusCode attribute.
	 * 
	 * @return Returns the campusCode
	 * 
	 */
	public String getCampusCode() { 
		return campusCode;
	}

	/**
	 * Sets the campusCode attribute.
	 * 
	 * @param campusCode The campusCode to set.
	 * 
	 */
	public void setCampusCode(String campusCode) {
		this.campusCode = campusCode;
	}


	/**
	 * Gets the contractManagerCode attribute.
	 * 
	 * @return Returns the contractManagerCode
	 * 
	 */
	public Integer getContractManagerCode() { 
		return contractManagerCode;
	}

	/**
	 * Sets the contractManagerCode attribute.
	 * 
	 * @param contractManagerCode The contractManagerCode to set.
	 * 
	 */
	public void setContractManagerCode(Integer contractManagerCode) {
		this.contractManagerCode = contractManagerCode;
	}


	/**
	 * Gets the campus attribute.
	 * 
	 * @return Returns the campus
	 * 
	 */
	public Campus getCampus() { 
		return campus;
	}

	/**
	 * Sets the campus attribute.
	 * 
	 * @param campus The campus to set.
	 * @deprecated
	 */
	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	/**
     * Gets the commodity attribute. 
     * @return Returns the commodity.
     */
    public CommodityCode getCommodity() {
        return commodity;
    }

    /**
     * Sets the commodity attribute value.
     * @param commodity The commodity to set.
     * @deprecated
     */
    public void setCommodity(CommodityCode commodity) {
        this.commodity = commodity;
    }

    /**
     * Gets the contractManager attribute. 
     * @return Returns the contractManager.
     */
    public ContractManager getContractManager() {
        return contractManager;
    }

    /**
     * Sets the contractManager attribute value.
     * @param contractManager The contractManager to set.
     * @deprecated
     */
    public void setContractManager(ContractManager contractManager) {
        this.contractManager = contractManager;
    }

    /**
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
	    LinkedHashMap m = new LinkedHashMap();	    
        m.put("purchasingCommodityCode", this.purchasingCommodityCode);
        m.put("campusCode", this.campusCode);
        if (this.contractManagerCode != null) {
            m.put("contractManagerCode", this.contractManagerCode.toString());
        }
	    return m;
    }
}
