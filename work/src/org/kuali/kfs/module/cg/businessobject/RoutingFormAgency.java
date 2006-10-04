/*
 * Copyright (c) 2004, 2005 The National Association of College and University 
 * Business Officers, Cornell University, Trustees of Indiana University, 
 * Michigan State University Board of Trustees, Trustees of San Joaquin Delta 
 * College, University of Hawai'i, The Arizona Board of Regents on behalf of the 
 * University of Arizona, and the r*smart group.
 * 
 * Licensed under the Educational Community License Version 1.0 (the "License"); 
 * By obtaining, using and/or copying this Original Work, you agree that you 
 * have read, understand, and will comply with the terms and conditions of the 
 * Educational Community License.
 * 
 * You may obtain a copy of the License at:
 * 
 * http://kualiproject.org/license.html
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,  DAMAGES OR OTHER 
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN 
 * THE SOFTWARE.
 */

package org.kuali.module.kra.routingform.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.core.bo.BusinessObjectBase;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class RoutingFormAgency extends BusinessObjectBase {

	private String researchDocumentNumber;
	private String agencyAddressDescription;
	private String agencyContactName;
	private String agencyCountryCode;
	private String agencyCityName;
	private boolean agencyDiskAccompanyIndicator;
	private boolean agencyElectronicSubmissionIndicator;
	private String agencyLine1Address;
	private String agencyLine2Address;
	private String agencyLine3Address;
	private String agencyLine4Address;
	private String agencyLine5Address;
	private String agencyLine6Address;
	private String agencyLine7Address;
	private String agencyLine8Address;
	private String agencyNumber;
	private String agencyShippingInstructionsDescription;
	private String agencyStateCode;
	private String agencyZipCode;
	private String proposalDueDateTypeCode;
	private Date proposalDueDate;
	private String proposalDueTime;
	private Integer proposalRequiredCopyNumber;
	private String proposalRequiredCopyText;
	private Date proposalSubmitDate;

	/**
	 * Default constructor.
	 */
	public RoutingFormAgency() {

	}

	/**
	 * Gets the researchDocumentNumber attribute.
	 * 
	 * @return - Returns the researchDocumentNumber
	 * 
	 */
	public String getResearchDocumentNumber() { 
		return researchDocumentNumber;
	}

	/**
	 * Sets the researchDocumentNumber attribute.
	 * 
	 * @param - researchDocumentNumber The researchDocumentNumber to set.
	 * 
	 */
	public void setResearchDocumentNumber(String researchDocumentNumber) {
		this.researchDocumentNumber = researchDocumentNumber;
	}


	/**
	 * Gets the agencyAddressDescription attribute.
	 * 
	 * @return - Returns the agencyAddressDescription
	 * 
	 */
	public String getAgencyAddressDescription() { 
		return agencyAddressDescription;
	}

	/**
	 * Sets the agencyAddressDescription attribute.
	 * 
	 * @param - agencyAddressDescription The agencyAddressDescription to set.
	 * 
	 */
	public void setAgencyAddressDescription(String agencyAddressDescription) {
		this.agencyAddressDescription = agencyAddressDescription;
	}


	/**
	 * Gets the agencyContactName attribute.
	 * 
	 * @return - Returns the agencyContactName
	 * 
	 */
	public String getAgencyContactName() { 
		return agencyContactName;
	}

	/**
	 * Sets the agencyContactName attribute.
	 * 
	 * @param - agencyContactName The agencyContactName to set.
	 * 
	 */
	public void setAgencyContactName(String agencyContactName) {
		this.agencyContactName = agencyContactName;
	}


	/**
	 * Gets the agencyCountryCode attribute.
	 * 
	 * @return - Returns the agencyCountryCode
	 * 
	 */
	public String getAgencyCountryCode() { 
		return agencyCountryCode;
	}

	/**
	 * Sets the agencyCountryCode attribute.
	 * 
	 * @param - agencyCountryCode The agencyCountryCode to set.
	 * 
	 */
	public void setAgencyCountryCode(String agencyCountryCode) {
		this.agencyCountryCode = agencyCountryCode;
	}


	/**
	 * Gets the agencyCityName attribute.
	 * 
	 * @return - Returns the agencyCityName
	 * 
	 */
	public String getAgencyCityName() { 
		return agencyCityName;
	}

	/**
	 * Sets the agencyCityName attribute.
	 * 
	 * @param - agencyCityName The agencyCityName to set.
	 * 
	 */
	public void setAgencyCityName(String agencyCityName) {
		this.agencyCityName = agencyCityName;
	}


	/**
	 * Gets the agencyDiskAccompanyIndicator attribute.
	 * 
	 * @return - Returns the agencyDiskAccompanyIndicator
	 * 
	 */
	public boolean getAgencyDiskAccompanyIndicator() { 
		return agencyDiskAccompanyIndicator;
	}

	/**
	 * Sets the agencyDiskAccompanyIndicator attribute.
	 * 
	 * @param - agencyDiskAccompanyIndicator The agencyDiskAccompanyIndicator to set.
	 * 
	 */
	public void setAgencyDiskAccompanyIndicator(boolean agencyDiskAccompanyIndicator) {
		this.agencyDiskAccompanyIndicator = agencyDiskAccompanyIndicator;
	}


	/**
	 * Gets the agencyElectronicSubmissionIndicator attribute.
	 * 
	 * @return - Returns the agencyElectronicSubmissionIndicator
	 * 
	 */
	public boolean getAgencyElectronicSubmissionIndicator() { 
		return agencyElectronicSubmissionIndicator;
	}

	/**
	 * Sets the agencyElectronicSubmissionIndicator attribute.
	 * 
	 * @param - agencyElectronicSubmissionIndicator The agencyElectronicSubmissionIndicator to set.
	 * 
	 */
	public void setAgencyElectronicSubmissionIndicator(boolean agencyElectronicSubmissionIndicator) {
		this.agencyElectronicSubmissionIndicator = agencyElectronicSubmissionIndicator;
	}


	/**
	 * Gets the agencyLine1Address attribute.
	 * 
	 * @return - Returns the agencyLine1Address
	 * 
	 */
	public String getAgencyLine1Address() { 
		return agencyLine1Address;
	}

	/**
	 * Sets the agencyLine1Address attribute.
	 * 
	 * @param - agencyLine1Address The agencyLine1Address to set.
	 * 
	 */
	public void setAgencyLine1Address(String agencyLine1Address) {
		this.agencyLine1Address = agencyLine1Address;
	}


	/**
	 * Gets the agencyLine2Address attribute.
	 * 
	 * @return - Returns the agencyLine2Address
	 * 
	 */
	public String getAgencyLine2Address() { 
		return agencyLine2Address;
	}

	/**
	 * Sets the agencyLine2Address attribute.
	 * 
	 * @param - agencyLine2Address The agencyLine2Address to set.
	 * 
	 */
	public void setAgencyLine2Address(String agencyLine2Address) {
		this.agencyLine2Address = agencyLine2Address;
	}


	/**
	 * Gets the agencyLine3Address attribute.
	 * 
	 * @return - Returns the agencyLine3Address
	 * 
	 */
	public String getAgencyLine3Address() { 
		return agencyLine3Address;
	}

	/**
	 * Sets the agencyLine3Address attribute.
	 * 
	 * @param - agencyLine3Address The agencyLine3Address to set.
	 * 
	 */
	public void setAgencyLine3Address(String agencyLine3Address) {
		this.agencyLine3Address = agencyLine3Address;
	}


	/**
	 * Gets the agencyLine4Address attribute.
	 * 
	 * @return - Returns the agencyLine4Address
	 * 
	 */
	public String getAgencyLine4Address() { 
		return agencyLine4Address;
	}

	/**
	 * Sets the agencyLine4Address attribute.
	 * 
	 * @param - agencyLine4Address The agencyLine4Address to set.
	 * 
	 */
	public void setAgencyLine4Address(String agencyLine4Address) {
		this.agencyLine4Address = agencyLine4Address;
	}


	/**
	 * Gets the agencyLine5Address attribute.
	 * 
	 * @return - Returns the agencyLine5Address
	 * 
	 */
	public String getAgencyLine5Address() { 
		return agencyLine5Address;
	}

	/**
	 * Sets the agencyLine5Address attribute.
	 * 
	 * @param - agencyLine5Address The agencyLine5Address to set.
	 * 
	 */
	public void setAgencyLine5Address(String agencyLine5Address) {
		this.agencyLine5Address = agencyLine5Address;
	}


	/**
	 * Gets the agencyLine6Address attribute.
	 * 
	 * @return - Returns the agencyLine6Address
	 * 
	 */
	public String getAgencyLine6Address() { 
		return agencyLine6Address;
	}

	/**
	 * Sets the agencyLine6Address attribute.
	 * 
	 * @param - agencyLine6Address The agencyLine6Address to set.
	 * 
	 */
	public void setAgencyLine6Address(String agencyLine6Address) {
		this.agencyLine6Address = agencyLine6Address;
	}


	/**
	 * Gets the agencyLine7Address attribute.
	 * 
	 * @return - Returns the agencyLine7Address
	 * 
	 */
	public String getAgencyLine7Address() { 
		return agencyLine7Address;
	}

	/**
	 * Sets the agencyLine7Address attribute.
	 * 
	 * @param - agencyLine7Address The agencyLine7Address to set.
	 * 
	 */
	public void setAgencyLine7Address(String agencyLine7Address) {
		this.agencyLine7Address = agencyLine7Address;
	}


	/**
	 * Gets the agencyLine8Address attribute.
	 * 
	 * @return - Returns the agencyLine8Address
	 * 
	 */
	public String getAgencyLine8Address() { 
		return agencyLine8Address;
	}

	/**
	 * Sets the agencyLine8Address attribute.
	 * 
	 * @param - agencyLine8Address The agencyLine8Address to set.
	 * 
	 */
	public void setAgencyLine8Address(String agencyLine8Address) {
		this.agencyLine8Address = agencyLine8Address;
	}


	/**
	 * Gets the agencyNumber attribute.
	 * 
	 * @return - Returns the agencyNumber
	 * 
	 */
	public String getAgencyNumber() { 
		return agencyNumber;
	}

	/**
	 * Sets the agencyNumber attribute.
	 * 
	 * @param - agencyNumber The agencyNumber to set.
	 * 
	 */
	public void setAgencyNumber(String agencyNumber) {
		this.agencyNumber = agencyNumber;
	}


	/**
	 * Gets the agencyShippingInstructionsDescription attribute.
	 * 
	 * @return - Returns the agencyShippingInstructionsDescription
	 * 
	 */
	public String getAgencyShippingInstructionsDescription() { 
		return agencyShippingInstructionsDescription;
	}

	/**
	 * Sets the agencyShippingInstructionsDescription attribute.
	 * 
	 * @param - agencyShippingInstructionsDescription The agencyShippingInstructionsDescription to set.
	 * 
	 */
	public void setAgencyShippingInstructionsDescription(String agencyShippingInstructionsDescription) {
		this.agencyShippingInstructionsDescription = agencyShippingInstructionsDescription;
	}


	/**
	 * Gets the agencyStateCode attribute.
	 * 
	 * @return - Returns the agencyStateCode
	 * 
	 */
	public String getAgencyStateCode() { 
		return agencyStateCode;
	}

	/**
	 * Sets the agencyStateCode attribute.
	 * 
	 * @param - agencyStateCode The agencyStateCode to set.
	 * 
	 */
	public void setAgencyStateCode(String agencyStateCode) {
		this.agencyStateCode = agencyStateCode;
	}


	/**
	 * Gets the agencyZipCode attribute.
	 * 
	 * @return - Returns the agencyZipCode
	 * 
	 */
	public String getAgencyZipCode() { 
		return agencyZipCode;
	}

	/**
	 * Sets the agencyZipCode attribute.
	 * 
	 * @param - agencyZipCode The agencyZipCode to set.
	 * 
	 */
	public void setAgencyZipCode(String agencyZipCode) {
		this.agencyZipCode = agencyZipCode;
	}


	/**
	 * Gets the proposalDueDateTypeCode attribute.
	 * 
	 * @return - Returns the proposalDueDateTypeCode
	 * 
	 */
	public String getProposalDueDateTypeCode() { 
		return proposalDueDateTypeCode;
	}

	/**
	 * Sets the proposalDueDateTypeCode attribute.
	 * 
	 * @param - proposalDueDateTypeCode The proposalDueDateTypeCode to set.
	 * 
	 */
	public void setProposalDueDateTypeCode(String proposalDueDateTypeCode) {
		this.proposalDueDateTypeCode = proposalDueDateTypeCode;
	}


	/**
	 * Gets the proposalDueDate attribute.
	 * 
	 * @return - Returns the proposalDueDate
	 * 
	 */
	public Date getProposalDueDate() { 
		return proposalDueDate;
	}

	/**
	 * Sets the proposalDueDate attribute.
	 * 
	 * @param - proposalDueDate The proposalDueDate to set.
	 * 
	 */
	public void setProposalDueDate(Date proposalDueDate) {
		this.proposalDueDate = proposalDueDate;
	}


	/**
	 * Gets the proposalDueTime attribute.
	 * 
	 * @return - Returns the proposalDueTime
	 * 
	 */
	public String getProposalDueTime() { 
		return proposalDueTime;
	}

	/**
	 * Sets the proposalDueTime attribute.
	 * 
	 * @param - proposalDueTime The proposalDueTime to set.
	 * 
	 */
	public void setProposalDueTime(String proposalDueTime) {
		this.proposalDueTime = proposalDueTime;
	}


	/**
	 * Gets the proposalRequiredCopyNumber attribute.
	 * 
	 * @return - Returns the proposalRequiredCopyNumber
	 * 
	 */
	public Integer getProposalRequiredCopyNumber() { 
		return proposalRequiredCopyNumber;
	}

	/**
	 * Sets the proposalRequiredCopyNumber attribute.
	 * 
	 * @param - proposalRequiredCopyNumber The proposalRequiredCopyNumber to set.
	 * 
	 */
	public void setProposalRequiredCopyNumber(Integer proposalRequiredCopyNumber) {
		this.proposalRequiredCopyNumber = proposalRequiredCopyNumber;
	}


	/**
	 * Gets the proposalRequiredCopyText attribute.
	 * 
	 * @return - Returns the proposalRequiredCopyText
	 * 
	 */
	public String getProposalRequiredCopyText() { 
		return proposalRequiredCopyText;
	}

	/**
	 * Sets the proposalRequiredCopyText attribute.
	 * 
	 * @param - proposalRequiredCopyText The proposalRequiredCopyText to set.
	 * 
	 */
	public void setProposalRequiredCopyText(String proposalRequiredCopyText) {
		this.proposalRequiredCopyText = proposalRequiredCopyText;
	}


	/**
	 * Gets the proposalSubmitDate attribute.
	 * 
	 * @return - Returns the proposalSubmitDate
	 * 
	 */
	public Date getProposalSubmitDate() { 
		return proposalSubmitDate;
	}

	/**
	 * Sets the proposalSubmitDate attribute.
	 * 
	 * @param - proposalSubmitDate The proposalSubmitDate to set.
	 * 
	 */
	public void setProposalSubmitDate(Date proposalSubmitDate) {
		this.proposalSubmitDate = proposalSubmitDate;
	}


	/**
	 * @see org.kuali.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
	    LinkedHashMap m = new LinkedHashMap();	    
        m.put("researchDocumentNumber", this.researchDocumentNumber);
	    return m;
    }
}
