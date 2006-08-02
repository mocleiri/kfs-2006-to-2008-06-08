/*
 * Copyright (c) 2004, 2005 The National Association of College and University Business Officers,
 * Cornell University, Trustees of Indiana University, Michigan State University Board of Trustees,
 * Trustees of San Joaquin Delta College, University of Hawai'i, The Arizona Board of Regents on
 * behalf of the University of Arizona, and the r*smart group.
 * 
 * Licensed under the Educational Community License Version 1.0 (the "License"); By obtaining,
 * using and/or copying this Original Work, you agree that you have read, understand, and will
 * comply with the terms and conditions of the Educational Community License.
 * 
 * You may obtain a copy of the License at:
 * 
 * http://kualiproject.org/license.html
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.kuali.module.kra.budget.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.kuali.core.bo.BusinessObjectBase;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.module.kra.budget.document.BudgetDocument;
import org.kuali.module.kra.budget.service.BudgetPersonnelService;

/**
 * This class...
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class BudgetUser extends BusinessObjectBase implements Comparable {

    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(BudgetUser.class);
    private transient BudgetPersonnelService budgetPersonnelService;

    /**
     * Constructs a BudgetUser.java.
     */
    public BudgetUser() {
        super();
        budgetPersonnelService = SpringServiceLocator.getBudgetPersonnelService();
    }

    public BudgetUser(BudgetUser budgetUser) {
        this();
        this.documentHeaderId = budgetUser.getDocumentHeaderId();
        this.budgetUserSequenceNumber = budgetUser.getBudgetUserSequenceNumber();
        this.fiscalCampusCode = budgetUser.getFiscalCampusCode();
        this.primaryDepartmentCode = budgetUser.getPrimaryDepartmentCode();
        this.baseSalary = budgetUser.getBaseSalary();
        this.role = budgetUser.getRole();
        this.personUniversalIdentifier = budgetUser.getPersonUniversalIdentifier();
        this.personNamePrefixText = budgetUser.getPersonNamePrefixText();
        this.personNameSuffixText = budgetUser.getPersonNameSuffixText();
        this.personSalaryJustificationText = budgetUser.getPersonSalaryJustificationText();
        this.personProjectDirectorIndicator = budgetUser.isPersonProjectDirectorIndicator();
        this.personSeniorKeyIndicator = budgetUser.isPersonSeniorKeyIndicator();
        this.personSecretarialClericalIndicator = budgetUser.isPersonSecretarialClericalIndicator();
        this.personPostDoctoralIndicator = budgetUser.isPersonPostDoctoralIndicator();
        this.budgetSalaryFiscalYear = budgetUser.getBudgetSalaryFiscalYear();
        
        this.userAppointmentTasks = new ArrayList(budgetUser.getUserAppointmentTasks());
    }

    private String documentHeaderId; // ER_REF_TRACK_NBR
    private Integer budgetUserSequenceNumber; // BDGT_USR_SEQ_NBR
    private String fiscalCampusCode; // EMP_FSCL_CMP_CD
    private String primaryDepartmentCode; // EMP_PRM_DEPT_CD
    private KualiDecimal baseSalary; // PRSN_BASE_SLRY
    private Integer budgetSalaryFiscalYear;
    private String role; //
    private String personUniversalIdentifier; // PERSON_UNVL_ID
    private UniversalUser user; // referenced object for PERSON_UNVL_ID
    private String appointmentTypeCode; // Not present in the database - only for the convenience of the user interface
    private boolean personSeniorKeyIndicator;
    private boolean personSecretarialClericalIndicator;
    private boolean personPostDoctoralIndicator;
    private String personNamePrefixText;
    private String personNameSuffixText;
    private String personSalaryJustificationText;
    private boolean personProjectDirectorIndicator;
    private Integer personHourlyNumber;

    private List userAppointmentTasks = new ArrayList();

    // Only used to ease development of the UI - could/should this be somewhere else?
    private Integer currentTaskNumber;
    private Integer previousTaskNumber;
    private String previousAppointmentTypeCode;

    public void initializeBudgetUser(BudgetDocument budgetDocument) {
        this.setBudgetUserSequenceNumber(budgetDocument.getPersonnelNextSequenceNumber());
        this.setDocumentHeaderId(budgetDocument.getFinancialDocumentNumber());
        this.synchronizeUserObject();
        this.createUserAppointmentTasks(budgetDocument);
    }

    public void createUserAppointmentTasks(BudgetDocument budgetDocument) {
        budgetPersonnelService.createPersonnelDetail(this, budgetDocument);
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("documentHeaderId", this.documentHeaderId);
        m.put("userSequenceNumber", this.budgetUserSequenceNumber);
        return m;
    }


    /**
     * Gets the baseSalary attribute.
     * 
     * @return Returns the baseSalary.
     */
    public KualiDecimal getBaseSalary() {
        return baseSalary;
    }


    /**
     * Sets the baseSalary attribute value.
     * 
     * @param baseSalary The baseSalary to set.
     */
    public void setBaseSalary(KualiDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }


    /**
     * Gets the documentHeaderId attribute.
     * 
     * @return Returns the documentHeaderId.
     */
    public String getDocumentHeaderId() {
        return documentHeaderId;
    }


    /**
     * Sets the documentHeaderId attribute value.
     * 
     * @param documentHeaderId The documentHeaderId to set.
     */
    public void setDocumentHeaderId(String documentHeaderId) {
        this.documentHeaderId = documentHeaderId;
    }


    /**
     * Gets the fiscalCampusCode attribute.
     * 
     * @return Returns the fiscalCampusCode.
     */
    public String getFiscalCampusCode() {
        return fiscalCampusCode;
    }


    /**
     * Sets the fiscalCampusCode attribute value.
     * 
     * @param fiscalCampusCode The fiscalCampusCode to set.
     */
    public void setFiscalCampusCode(String fiscalCampusCode) {
        this.fiscalCampusCode = fiscalCampusCode;
    }


    /**
     * Gets the primaryDepartmentCode attribute.
     * 
     * @return Returns the primaryDepartmentCode.
     */
    public String getPrimaryDepartmentCode() {
        return primaryDepartmentCode;
    }


    /**
     * Sets the primaryDepartmentCode attribute value.
     * 
     * @param primaryDepartmentCode The primaryDepartmentCode to set.
     */
    public void setPrimaryDepartmentCode(String primaryDepartmentCode) {
        this.primaryDepartmentCode = primaryDepartmentCode;
    }

    /**
     * Gets the role attribute.
     * 
     * @return Returns the role.
     */
    public String getRole() {
        return role;
    }


    /**
     * Sets the role attribute value.
     * 
     * @param role The role to set.
     */
    public void setRole(String role) {
        this.role = role;
    }


    /**
     * Gets the personUniversalIdentifier attribute.
     * 
     * @return Returns the personUniversalIdentifier.
     */
    public String getPersonUniversalIdentifier() {
        return personUniversalIdentifier;
    }


    /**
     * Sets the personUniversalIdentifier attribute value.
     * 
     * @param personUniversalIdentifier The personUniversalIdentifier to set.
     */
    public void setPersonUniversalIdentifier(String personUniversalIdentifier) {
        this.personUniversalIdentifier = personUniversalIdentifier;
    }


    /**
     * Gets the user attribute.
     * 
     * @return Returns the user.
     */
    public UniversalUser getUser() {
        return user;
    }


    /**
     * Sets the user attribute value.
     * 
     * @param user The user to set.
     * @deprecated
     */
    public void setUser(UniversalUser user) {
        this.user = user;
    }


    /**
     * Gets the userSequenceNumber attribute.
     * 
     * @return Returns the userSequenceNumber.
     */
    public Integer getBudgetUserSequenceNumber() {
        return budgetUserSequenceNumber;
    }


    /**
     * Sets the userSequenceNumber attribute value.
     * 
     * @param userSequenceNumber The userSequenceNumber to set.
     */
    public void setBudgetUserSequenceNumber(Integer userSequenceNumber) {
        this.budgetUserSequenceNumber = userSequenceNumber;
    }

    /**
     * Gets the personNamePrefixText attribute.
     * 
     * @return - Returns the personNamePrefixText
     * 
     */
    public String getPersonNamePrefixText() {
        return personNamePrefixText;
    }

    /**
     * Sets the personNamePrefixText attribute.
     * 
     * @param personNamePrefixText The personNamePrefixText to set.
     * 
     */
    public void setPersonNamePrefixText(String personNamePrefixText) {
        this.personNamePrefixText = personNamePrefixText;
    }

    /**
     * Gets the personNameSuffixText attribute.
     * 
     * @return - Returns the personNameSuffixText
     * 
     */
    public String getPersonNameSuffixText() {
        return personNameSuffixText;
    }

    /**
     * Sets the personNameSuffixText attribute.
     * 
     * @param personNameSuffixText The personNameSuffixText to set.
     * 
     */
    public void setPersonNameSuffixText(String personNameSuffixText) {
        this.personNameSuffixText = personNameSuffixText;
    }

    /**
     * Gets the personPostDoctoralIndicator attribute.
     * 
     * @return - Returns the personPostDoctoralIndicator
     * 
     */
    public boolean isPersonPostDoctoralIndicator() {
        return personPostDoctoralIndicator;
    }

    /**
     * Sets the personPostDoctoralIndicator attribute.
     * 
     * @param personPostDoctoralIndicator The personPostDoctoralIndicator to set.
     * 
     */
    public void setPersonPostDoctoralIndicator(boolean personPostDoctoralIndicator) {
        this.personPostDoctoralIndicator = personPostDoctoralIndicator;
    }

    /**
     * Gets the personSecretarialClericalIndicator attribute.
     * 
     * @return - Returns the personSecretarialClericalIndicator
     * 
     */
    public boolean isPersonSecretarialClericalIndicator() {
        return personSecretarialClericalIndicator;
    }

    /**
     * Sets the personSecretarialClericalIndicator attribute.
     * 
     * @param personSecretarialClericalIndicator The personSecretarialClericalIndicator to set.
     * 
     */
    public void setPersonSecretarialClericalIndicator(boolean personSecretarialClericalIndicator) {
        this.personSecretarialClericalIndicator = personSecretarialClericalIndicator;
    }

    /**
     * Gets the personSeniorKeyIndicator attribute.
     * 
     * @return - Returns the personSeniorKeyIndicator
     * 
     */
    public boolean isPersonSeniorKeyIndicator() {
        return personSeniorKeyIndicator;
    }

    /**
     * Sets the personSeniorKeyIndicator attribute.
     * 
     * @param personSeniorKeyIndicator The personSeniorKeyIndicator to set.
     * 
     */
    public void setPersonSeniorKeyIndicator(boolean personSeniorKeyIndicator) {
        this.personSeniorKeyIndicator = personSeniorKeyIndicator;
    }

    /**
     * Makes sure that copied fields between the referenced UniversalUser object and this object are properly synchronized.
     */
    public void synchronizeUserObject() {
        if (this.getPersonUniversalIdentifier() != null) {
            this.refreshReferenceObject("user");
            if (this.user.getPersonBaseSalaryAmount() != null) {
                this.baseSalary = this.user.getPersonBaseSalaryAmount();
            }
            else {
                this.baseSalary = new KualiDecimal(0);
            }
            String[] departmentIdSplit = this.user.getDeptid().split("-");
            this.fiscalCampusCode = departmentIdSplit[0];
            if (departmentIdSplit.length > 1)
                this.primaryDepartmentCode = departmentIdSplit[1];
            else
                this.primaryDepartmentCode = departmentIdSplit[0];
        }
        else {
            this.baseSalary = new KualiDecimal(0);
            this.fiscalCampusCode = "";
            this.primaryDepartmentCode = "";
        }
    }


    /**
     * Gets the appointmentType attribute.
     * 
     * @return Returns the appointmentType.
     */
    public String getAppointmentTypeCode() {
        return appointmentTypeCode;
    }


    /**
     * Sets the appointmentType attribute value.
     * 
     * @param appointmentType The appointmentType to set.
     */
    public void setAppointmentTypeCode(String appointmentTypeCode) {
        this.appointmentTypeCode = appointmentTypeCode;
    }

    /**
     * Log the state of this object.
     */
    public void logState() {
        LOG.info("userSequenceNumber: (" + this.budgetUserSequenceNumber + ")");
        /* LOG.info(" version: (" + this.getVersion() +")"); */
        LOG.info("  documentHeaderId: (" + this.documentHeaderId + ")");
        LOG.info("  fiscalCampusCode: (" + this.fiscalCampusCode + ")");
        LOG.info("  primaryDepartmentCode: (" + this.primaryDepartmentCode + ")");
        LOG.info("  baseSalary: (" + this.baseSalary + ")");
        LOG.info("  role: (" + this.role + ")");
        LOG.info("  personUniversalIdentifier: (" + this.personUniversalIdentifier + ")");
        LOG.info("  appointmentTypeCode: (" + this.appointmentTypeCode + ")");
        LOG.info("  personSeniorKeyIndicator: (" + this.personSeniorKeyIndicator + ")");
        LOG.info("  personSecretarialClericalIndicator: (" + this.personSecretarialClericalIndicator + ")");
        LOG.info("  personPostDoctoralIndicator: (" + this.personPostDoctoralIndicator + ")");
        LOG.info("  personNamePrefixText: (" + this.personNamePrefixText + ")");
        LOG.info("  personNameSuffixText: (" + this.personNameSuffixText + ")");
        if (this.user == null) {
            LOG.info("  user: <null>");
        }
        else {
            LOG.info("  user: (" + this.user.getEmplid() + ")");
        }
    }

    public UserAppointmentTask getUserAppointmentTask(int index) {
        while (getUserAppointmentTasks().size() <= index) {
            getUserAppointmentTasks().add(new UserAppointmentTask());
        }
        return (UserAppointmentTask) getUserAppointmentTasks().get(index);
    }

    /**
     * Gets the userAppointmentTasks attribute.
     * 
     * @return Returns the userAppointmentTasks.
     */
    public List<UserAppointmentTask> getUserAppointmentTasks() {
        return userAppointmentTasks;
    }

    /**
     * Sets the userAppointmentTasks attribute value.
     * 
     * @param userAppointmentTasks The userAppointmentTasks to set.
     */
    public void setUserAppointmentTasks(List userAppointmentTasks) {
        this.userAppointmentTasks = userAppointmentTasks;
    }

    /**
     * Gets the budgetPersonnelService attribute.
     * 
     * @return Returns the budgetPersonnelService.
     */
    public BudgetPersonnelService getBudgetPersonnelService() {
        return budgetPersonnelService;
    }

    /**
     * Sets the budgetPersonnelService attribute value.
     * 
     * @param budgetPersonnelService The budgetPersonnelService to set.
     */
    public void setBudgetPersonnelService(BudgetPersonnelService budgetPersonnelService) {
        this.budgetPersonnelService = budgetPersonnelService;
    }

    /**
     * Gets the currentTask attribute.
     * 
     * @return Returns the currentTask.
     */
    public Integer getCurrentTaskNumber() {
        return currentTaskNumber;
    }

    /**
     * Sets the currentTask attribute value.
     * 
     * @param currentTask The currentTask to set.
     */
    public void setCurrentTaskNumber(Integer currentTaskNumber) {
        this.currentTaskNumber = currentTaskNumber;
    }

    /**
     * Gets the personSalaryJustificationText attribute.
     * 
     * @return Returns the personSalaryJustificationText.
     */
    public String getPersonSalaryJustificationText() {
        return personSalaryJustificationText;
    }

    /**
     * Sets the personSalaryJustificationText attribute value.
     * 
     * @param personSalaryJustificationText The personSalaryJustificationText to set.
     */
    public void setPersonSalaryJustificationText(String personSalaryJustificationText) {
        this.personSalaryJustificationText = personSalaryJustificationText;
    }

    /**
     * Gets the personProjectDirectorIndicator attribute.
     * 
     * @return - Returns the personProjectDirectorIndicator
     * 
     */
    public boolean isPersonProjectDirectorIndicator() {
        return personProjectDirectorIndicator;
    }

    /**
     * Sets the personProjectDirectorIndicator attribute.
     * 
     * @param personProjectDirectorIndicator The personProjectDirectorIndicator to set.
     * 
     */
    public void setPersonProjectDirectorIndicator(boolean personProjectDirectorIndicator) {
        this.personProjectDirectorIndicator = personProjectDirectorIndicator;
    }

    /**
     * @see org.apache.ojb.broker.PersistenceBrokerAware#beforeDelete(org.apache.ojb.broker.PersistenceBroker)
     */
    public void beforeDelete(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        super.beforeDelete(persistenceBroker);
        this.refreshReferenceObject("userAppointmentTasks");
    }

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object o) {
        BudgetUser budgetUser = (BudgetUser) o;
        return (this.isPersonProjectDirectorIndicator() ? new Integer(0) : this.getBudgetUserSequenceNumber()).compareTo(budgetUser.isPersonProjectDirectorIndicator() ? new Integer(0) : budgetUser.getBudgetUserSequenceNumber());
    }

    public String getPreviousAppointmentTypeCode() {
        return previousAppointmentTypeCode;
    }

    public void setPreviousAppointmentTypeCode(String previousAppointmentTypeCode) {
        this.previousAppointmentTypeCode = previousAppointmentTypeCode;
    }

    public Integer getPreviousTaskNumber() {
        return previousTaskNumber;
    }

    public void setPreviousTaskNumber(Integer previousTaskNumber) {
        this.previousTaskNumber = previousTaskNumber;
    }

    public Integer getPersonHourlyNumber() {
        return personHourlyNumber;
    }

    public void setPersonHourlyNumber(Integer personHourlyNumber) {
        this.personHourlyNumber = personHourlyNumber;
    }

    public Integer getBudgetSalaryFiscalYear() {
        return budgetSalaryFiscalYear;
    }

    public void setBudgetSalaryFiscalYear(Integer budgetSalaryFiscalYear) {
        this.budgetSalaryFiscalYear = budgetSalaryFiscalYear;
    }
}
