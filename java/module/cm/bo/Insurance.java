package org.kuali.module.cams.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.core.util.KualiDecimal;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class Insurance extends PersistableBusinessObjectBase {

	private Integer insuranceCode;
	private Integer insuranceFiscalYear;
	private String insuranceItemDescription;
	private KualiDecimal insurancePremiumAmount;
	private KualiDecimal insuranceDeductibleAmount;
	private boolean active;

	/**
	 * Default constructor.
	 */
	public Insurance() {

	}

	/**
	 * Gets the insuranceCode attribute.
	 * 
	 * @return Returns the insuranceCode
	 * 
	 */
	public Integer getInsuranceCode() { 
		return insuranceCode;
	}

	/**
	 * Sets the insuranceCode attribute.
	 * 
	 * @param insuranceCode The insuranceCode to set.
	 * 
	 */
	public void setInsuranceCode(Integer insuranceCode) {
		this.insuranceCode = insuranceCode;
	}


	/**
	 * Gets the insuranceFiscalYear attribute.
	 * 
	 * @return Returns the insuranceFiscalYear
	 * 
	 */
	public Integer getInsuranceFiscalYear() { 
		return insuranceFiscalYear;
	}

	/**
	 * Sets the insuranceFiscalYear attribute.
	 * 
	 * @param insuranceFiscalYear The insuranceFiscalYear to set.
	 * 
	 */
	public void setInsuranceFiscalYear(Integer insuranceFiscalYear) {
		this.insuranceFiscalYear = insuranceFiscalYear;
	}


	/**
	 * Gets the insuranceItemDescription attribute.
	 * 
	 * @return Returns the insuranceItemDescription
	 * 
	 */
	public String getInsuranceItemDescription() { 
		return insuranceItemDescription;
	}

	/**
	 * Sets the insuranceItemDescription attribute.
	 * 
	 * @param insuranceItemDescription The insuranceItemDescription to set.
	 * 
	 */
	public void setInsuranceItemDescription(String insuranceItemDescription) {
		this.insuranceItemDescription = insuranceItemDescription;
	}


	/**
	 * Gets the insurancePremiumAmount attribute.
	 * 
	 * @return Returns the insurancePremiumAmount
	 * 
	 */
	public KualiDecimal getInsurancePremiumAmount() { 
		return insurancePremiumAmount;
	}

	/**
	 * Sets the insurancePremiumAmount attribute.
	 * 
	 * @param insurancePremiumAmount The insurancePremiumAmount to set.
	 * 
	 */
	public void setInsurancePremiumAmount(KualiDecimal insurancePremiumAmount) {
		this.insurancePremiumAmount = insurancePremiumAmount;
	}


	/**
	 * Gets the insuranceDeductibleAmount attribute.
	 * 
	 * @return Returns the insuranceDeductibleAmount
	 * 
	 */
	public KualiDecimal getInsuranceDeductibleAmount() { 
		return insuranceDeductibleAmount;
	}

	/**
	 * Sets the insuranceDeductibleAmount attribute.
	 * 
	 * @param insuranceDeductibleAmount The insuranceDeductibleAmount to set.
	 * 
	 */
	public void setInsuranceDeductibleAmount(KualiDecimal insuranceDeductibleAmount) {
		this.insuranceDeductibleAmount = insuranceDeductibleAmount;
	}


	/**
	 * Gets the active attribute.
	 * 
	 * @return Returns the active
	 * 
	 */
	public boolean isActive() { 
		return active;
	}

	/**
	 * Sets the active attribute.
	 * 
	 * @param active The active to set.
	 * 
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
	    LinkedHashMap m = new LinkedHashMap();	    
        if (this.insuranceCode != null) {
            m.put("insuranceCode", this.insuranceCode.toString());
        }
	    return m;
    }
}
