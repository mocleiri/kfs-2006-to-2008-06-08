package org.kuali.module.labor.bo;

import java.math.BigDecimal;
import java.sql.Date;

import org.kuali.core.util.KualiDecimal;
import org.kuali.kfs.bo.Options;
import org.kuali.kfs.bo.SourceAccountingLine;
import org.kuali.module.chart.bo.AccountingPeriod;
import org.kuali.module.labor.document.LaborJournalVoucherDocument;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class LaborJournalVoucherDetail extends SourceAccountingLine{
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(LaborJournalVoucherDetail.class);
    
	private String positionNumber;
	private Date payPeriodEndDate;
	private BigDecimal transactionTotalHours;
	private Integer payrollEndDateFiscalYear;
	private String payrollEndDateFiscalPeriodCode;
	private String emplid;
	private Integer employeeRecord;
	private String earnCode;
	private String payGroup;
	private String salaryAdministrationPlan;
	private String grade;
	private String runIdentifier;
	private String laborLedgerOriginalChartOfAccountsCode;
	private String laborLedgerOriginalAccountNumber;
	private String laborLedgerOriginalSubAccountNumber;
	private String laborLedgerOriginalFinancialObjectCode;
	private String laborLedgerOriginalFinancialSubObjectCode;
	private String hrmsCompany;
	private String setid;

    private Options payrollEndDateOptions;
	private AccountingPeriod payrollEndDateFiscalPeriod;
    private Options options;
   
	/**
	 * Default constructor.
	 */
	public LaborJournalVoucherDetail() {
        super();
	}

    /**
     * Gets the earnCode attribute. 
     * @return Returns the earnCode.
     */
    public String getEarnCode() {
        return earnCode;
    }

    /**
     * Gets the emplid attribute. 
     * @return Returns the emplid.
     */
    public String getEmplid() {
        return emplid;
    }

    /**
     * Gets the employeeRecord attribute. 
     * @return Returns the employeeRecord.
     */
    public Integer getEmployeeRecord() {
        return employeeRecord;
    }

    /**
     * Gets the grade attribute. 
     * @return Returns the grade.
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Gets the hrmsCompany attribute. 
     * @return Returns the hrmsCompany.
     */
    public String getHrmsCompany() {
        return hrmsCompany;
    }

    /**
     * Gets the laborLedgerOriginalAccountNumber attribute. 
     * @return Returns the laborLedgerOriginalAccountNumber.
     */
    public String getLaborLedgerOriginalAccountNumber() {
        return laborLedgerOriginalAccountNumber;
    }

    /**
     * Gets the laborLedgerOriginalChartOfAccountsCode attribute. 
     * @return Returns the laborLedgerOriginalChartOfAccountsCode.
     */
    public String getLaborLedgerOriginalChartOfAccountsCode() {
        return laborLedgerOriginalChartOfAccountsCode;
    }

    /**
     * Gets the laborLedgerOriginalFinancialObjectCode attribute. 
     * @return Returns the laborLedgerOriginalFinancialObjectCode.
     */
    public String getLaborLedgerOriginalFinancialObjectCode() {
        return laborLedgerOriginalFinancialObjectCode;
    }

    /**
     * Gets the laborLedgerOriginalFinancialSubObjectCode attribute. 
     * @return Returns the laborLedgerOriginalFinancialSubObjectCode.
     */
    public String getLaborLedgerOriginalFinancialSubObjectCode() {
        return laborLedgerOriginalFinancialSubObjectCode;
    }

    /**
     * Gets the laborLedgerOriginalSubAccountNumber attribute. 
     * @return Returns the laborLedgerOriginalSubAccountNumber.
     */
    public String getLaborLedgerOriginalSubAccountNumber() {
        return laborLedgerOriginalSubAccountNumber;
    }

    /**
     * Gets the options attribute. 
     * @return Returns the options.
     */
    public Options getOptions() {
        return options;
    }

    /**
     * Gets the payGroup attribute. 
     * @return Returns the payGroup.
     */
    public String getPayGroup() {
        return payGroup;
    }

    /**
     * Gets the payPeriodEndDate attribute. 
     * @return Returns the payPeriodEndDate.
     */
    public Date getPayPeriodEndDate() {
        return payPeriodEndDate;
    }

    /**
     * Gets the payrollEndDateFiscalPeriod attribute. 
     * @return Returns the payrollEndDateFiscalPeriod.
     */
    public AccountingPeriod getPayrollEndDateFiscalPeriod() {
        return payrollEndDateFiscalPeriod;
    }

    /**
     * Gets the payrollEndDateOptions attribute. 
     * @return Returns the payrollEndDateOptions.
     */
    public Options getPayrollEndDateOptions() {
        return payrollEndDateOptions;
    }

    /**
     * Gets the positionNumber attribute. 
     * @return Returns the positionNumber.
     */
    public String getPositionNumber() {
        return positionNumber;
    }

    /**
     * Gets the runIdentifier attribute. 
     * @return Returns the runIdentifier.
     */
    public String getRunIdentifier() {
        return runIdentifier;
    }

    /**
     * Gets the salaryAdministrationPlan attribute. 
     * @return Returns the salaryAdministrationPlan.
     */
    public String getSalaryAdministrationPlan() {
        return salaryAdministrationPlan;
    }

    /**
     * Gets the setid attribute. 
     * @return Returns the setid.
     */
    public String getSetid() {
        return setid;
    }

    /**
     * Gets the transactionTotalHours attribute. 
     * @return Returns the transactionTotalHours.
     */
    public BigDecimal getTransactionTotalHours() {
        return transactionTotalHours;
    }

    /**
     * Sets the earnCode attribute value.
     * @param earnCode The earnCode to set.
     */
    public void setEarnCode(String earnCode) {
        this.earnCode = earnCode;
    }

    /**
     * Sets the emplid attribute value.
     * @param emplid The emplid to set.
     */
    public void setEmplid(String emplid) {
        this.emplid = emplid;
    }

    /**
     * Sets the employeeRecord attribute value.
     * @param employeeRecord The employeeRecord to set.
     */
    public void setEmployeeRecord(Integer employeeRecord) {
        this.employeeRecord = employeeRecord;
    }

    /**
     * Sets the grade attribute value.
     * @param grade The grade to set.
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * Sets the hrmsCompany attribute value.
     * @param hrmsCompany The hrmsCompany to set.
     */
    public void setHrmsCompany(String hrmsCompany) {
        this.hrmsCompany = hrmsCompany;
    }

    /**
     * Sets the laborLedgerOriginalAccountNumber attribute value.
     * @param laborLedgerOriginalAccountNumber The laborLedgerOriginalAccountNumber to set.
     */
    public void setLaborLedgerOriginalAccountNumber(String laborLedgerOriginalAccountNumber) {
        this.laborLedgerOriginalAccountNumber = laborLedgerOriginalAccountNumber;
    }

    /**
     * Sets the laborLedgerOriginalChartOfAccountsCode attribute value.
     * @param laborLedgerOriginalChartOfAccountsCode The laborLedgerOriginalChartOfAccountsCode to set.
     */
    public void setLaborLedgerOriginalChartOfAccountsCode(String laborLedgerOriginalChartOfAccountsCode) {
        this.laborLedgerOriginalChartOfAccountsCode = laborLedgerOriginalChartOfAccountsCode;
    }

    /**
     * Sets the laborLedgerOriginalFinancialObjectCode attribute value.
     * @param laborLedgerOriginalFinancialObjectCode The laborLedgerOriginalFinancialObjectCode to set.
     */
    public void setLaborLedgerOriginalFinancialObjectCode(String laborLedgerOriginalFinancialObjectCode) {
        this.laborLedgerOriginalFinancialObjectCode = laborLedgerOriginalFinancialObjectCode;
    }

    /**
     * Sets the laborLedgerOriginalFinancialSubObjectCode attribute value.
     * @param laborLedgerOriginalFinancialSubObjectCode The laborLedgerOriginalFinancialSubObjectCode to set.
     */
    public void setLaborLedgerOriginalFinancialSubObjectCode(String laborLedgerOriginalFinancialSubObjectCode) {
        this.laborLedgerOriginalFinancialSubObjectCode = laborLedgerOriginalFinancialSubObjectCode;
    }

    /**
     * Sets the laborLedgerOriginalSubAccountNumber attribute value.
     * @param laborLedgerOriginalSubAccountNumber The laborLedgerOriginalSubAccountNumber to set.
     */
    public void setLaborLedgerOriginalSubAccountNumber(String laborLedgerOriginalSubAccountNumber) {
        this.laborLedgerOriginalSubAccountNumber = laborLedgerOriginalSubAccountNumber;
    }

    /**
     * Sets the options attribute value.
     * @param options The options to set.
     */
    public void setOptions(Options options) {
        this.options = options;
    }

    /**
     * Sets the payGroup attribute value.
     * @param payGroup The payGroup to set.
     */
    public void setPayGroup(String payGroup) {
        this.payGroup = payGroup;
    }

    /**
     * Sets the payPeriodEndDate attribute value.
     * @param payPeriodEndDate The payPeriodEndDate to set.
     */
    public void setPayPeriodEndDate(Date payPeriodEndDate) {
        this.payPeriodEndDate = payPeriodEndDate;
    }

    /**
     * Sets the payrollEndDateFiscalPeriod attribute value.
     * @param payrollEndDateFiscalPeriod The payrollEndDateFiscalPeriod to set.
     */
    public void setPayrollEndDateFiscalPeriod(AccountingPeriod payrollEndDateFiscalPeriod) {
        this.payrollEndDateFiscalPeriod = payrollEndDateFiscalPeriod;
    }

    /**
     * Sets the payrollEndDateOptions attribute value.
     * @param payrollEndDateOptions The payrollEndDateOptions to set.
     */
    public void setPayrollEndDateOptions(Options payrollEndDateOptions) {
        this.payrollEndDateOptions = payrollEndDateOptions;
    }

    /**
     * Sets the positionNumber attribute value.
     * @param positionNumber The positionNumber to set.
     */
    public void setPositionNumber(String positionNumber) {
        this.positionNumber = positionNumber;
    }

    /**
     * Sets the runIdentifier attribute value.
     * @param runIdentifier The runIdentifier to set.
     */
    public void setRunIdentifier(String runIdentifier) {
        this.runIdentifier = runIdentifier;
    }

    /**
     * Sets the salaryAdministrationPlan attribute value.
     * @param salaryAdministrationPlan The salaryAdministrationPlan to set.
     */
    public void setSalaryAdministrationPlan(String salaryAdministrationPlan) {
        this.salaryAdministrationPlan = salaryAdministrationPlan;
    }

    /**
     * Sets the setid attribute value.
     * @param setid The setid to set.
     */
    public void setSetid(String setid) {
        this.setid = setid;
    }

    /**
     * Sets the transactionTotalHours attribute value.
     * @param transactionTotalHours The transactionTotalHours to set.
     */
    public void setTransactionTotalHours(BigDecimal transactionTotalHours) {
        this.transactionTotalHours = transactionTotalHours;
    }

    /**
     * Gets the payrollEndDateFiscalPeriodCode attribute. 
     * @return Returns the payrollEndDateFiscalPeriodCode.
     */
    public String getPayrollEndDateFiscalPeriodCode() {
        return payrollEndDateFiscalPeriodCode;
    }

    /**
     * Sets the payrollEndDateFiscalPeriodCode attribute value.
     * @param payrollEndDateFiscalPeriodCode The payrollEndDateFiscalPeriodCode to set.
     */
    public void setPayrollEndDateFiscalPeriodCode(String payrollEndDateFiscalPeriodCode) {
        this.payrollEndDateFiscalPeriodCode = payrollEndDateFiscalPeriodCode;
    }

    /**
     * Gets the payrollEndDateFiscalYear attribute. 
     * @return Returns the payrollEndDateFiscalYear.
     */
    public Integer getPayrollEndDateFiscalYear() {
        return payrollEndDateFiscalYear;
    }

    /**
     * Sets the payrollEndDateFiscalYear attribute value.
     * @param payrollEndDateFiscalYear The payrollEndDateFiscalYear to set.
     */
    public void setPayrollEndDateFiscalYear(Integer payrollEndDateFiscalYear) {
        this.payrollEndDateFiscalYear = payrollEndDateFiscalYear;
    }

}
