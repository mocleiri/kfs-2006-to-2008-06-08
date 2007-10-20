package org.kuali.module.cams.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.core.bo.DocumentHeader;
import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kfs.bo.OriginationCode;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.AccountingPeriod;
import org.kuali.module.chart.bo.Chart;
import org.kuali.module.chart.bo.ObjectCode;
import org.kuali.module.chart.bo.ObjectType;
import org.kuali.module.chart.bo.ProjectCode;
import org.kuali.module.chart.bo.SubAccount;
import org.kuali.module.chart.bo.SubObjCd;
import org.kuali.module.chart.bo.codes.BalanceTyp;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class PendingCapitalAssetTemporary extends PersistableBusinessObjectBase {

	private Integer universityFiscalYear;
	private String chartOfAccountsCode;
	private String accountNumber;
	private String subAccountNumber;
	private String financialObjectCode;
	private String financialSubObjectCode;
	private String financialBalanceTypeCode;
	private String financialObjectTypeCode;
	private String universityFiscalPeriodCode;
	private String financialDocumentTypeCode;
	private String financialSystemOriginationCode;
	private String documentNumber;
	private Long transactionLedgerEntrySequenceNumber;
	private String transactionLedgerEntryDescription;
	private KualiDecimal transactionLedgerEntryAmount;
	private String transactionDebitCreditCode;
	private Date transactionDate;
	private String organizationDocumentNumber;
	private String projectCode;
	private String organizationReferenceId;
	private String referenceFinancialDocumentTypeCode;
	private String referenceFinancialSystemOriginationCode;
	private String referenceFinancialDocumentNumber;
	private Date financialDocumentReversalDate;
	private String transactionEncumbranceUpdateCode;
	private Date transactionPostingDate;
	private String junk;

    private AccountingPeriod universityFiscalPeriod;
	private ObjectCode financialObject;
	private SubObjCd financialSubObject;
	private SubAccount subAccount;
	private Account account;
	private Chart chartOfAccounts;
	private BalanceTyp financialBalanceType;
	private ObjectType financialObjectType;
	private ProjectCode project;
	private OriginationCode referenceFinancialSystemOrigination;
    private OriginationCode financialSystemOrigination;
    private DocumentHeader documentHeader;
    
	/**
	 * Default constructor.
	 */
	public PendingCapitalAssetTemporary() {

	}

	/**
	 * Gets the universityFiscalYear attribute.
	 * 
	 * @return Returns the universityFiscalYear
	 * 
	 */
	public Integer getUniversityFiscalYear() { 
		return universityFiscalYear;
	}

	/**
	 * Sets the universityFiscalYear attribute.
	 * 
	 * @param universityFiscalYear The universityFiscalYear to set.
	 * 
	 */
	public void setUniversityFiscalYear(Integer universityFiscalYear) {
		this.universityFiscalYear = universityFiscalYear;
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
	 * Gets the accountNumber attribute.
	 * 
	 * @return Returns the accountNumber
	 * 
	 */
	public String getAccountNumber() { 
		return accountNumber;
	}

	/**
	 * Sets the accountNumber attribute.
	 * 
	 * @param accountNumber The accountNumber to set.
	 * 
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	/**
	 * Gets the subAccountNumber attribute.
	 * 
	 * @return Returns the subAccountNumber
	 * 
	 */
	public String getSubAccountNumber() { 
		return subAccountNumber;
	}

	/**
	 * Sets the subAccountNumber attribute.
	 * 
	 * @param subAccountNumber The subAccountNumber to set.
	 * 
	 */
	public void setSubAccountNumber(String subAccountNumber) {
		this.subAccountNumber = subAccountNumber;
	}


	/**
	 * Gets the financialObjectCode attribute.
	 * 
	 * @return Returns the financialObjectCode
	 * 
	 */
	public String getFinancialObjectCode() { 
		return financialObjectCode;
	}

	/**
	 * Sets the financialObjectCode attribute.
	 * 
	 * @param financialObjectCode The financialObjectCode to set.
	 * 
	 */
	public void setFinancialObjectCode(String financialObjectCode) {
		this.financialObjectCode = financialObjectCode;
	}


	/**
	 * Gets the financialSubObjectCode attribute.
	 * 
	 * @return Returns the financialSubObjectCode
	 * 
	 */
	public String getFinancialSubObjectCode() { 
		return financialSubObjectCode;
	}

	/**
	 * Sets the financialSubObjectCode attribute.
	 * 
	 * @param financialSubObjectCode The financialSubObjectCode to set.
	 * 
	 */
	public void setFinancialSubObjectCode(String financialSubObjectCode) {
		this.financialSubObjectCode = financialSubObjectCode;
	}


	/**
	 * Gets the financialBalanceTypeCode attribute.
	 * 
	 * @return Returns the financialBalanceTypeCode
	 * 
	 */
	public String getFinancialBalanceTypeCode() { 
		return financialBalanceTypeCode;
	}

	/**
	 * Sets the financialBalanceTypeCode attribute.
	 * 
	 * @param financialBalanceTypeCode The financialBalanceTypeCode to set.
	 * 
	 */
	public void setFinancialBalanceTypeCode(String financialBalanceTypeCode) {
		this.financialBalanceTypeCode = financialBalanceTypeCode;
	}


	/**
	 * Gets the financialObjectTypeCode attribute.
	 * 
	 * @return Returns the financialObjectTypeCode
	 * 
	 */
	public String getFinancialObjectTypeCode() { 
		return financialObjectTypeCode;
	}

	/**
	 * Sets the financialObjectTypeCode attribute.
	 * 
	 * @param financialObjectTypeCode The financialObjectTypeCode to set.
	 * 
	 */
	public void setFinancialObjectTypeCode(String financialObjectTypeCode) {
		this.financialObjectTypeCode = financialObjectTypeCode;
	}


	/**
	 * Gets the universityFiscalPeriodCode attribute.
	 * 
	 * @return Returns the universityFiscalPeriodCode
	 * 
	 */
	public String getUniversityFiscalPeriodCode() { 
		return universityFiscalPeriodCode;
	}

	/**
	 * Sets the universityFiscalPeriodCode attribute.
	 * 
	 * @param universityFiscalPeriodCode The universityFiscalPeriodCode to set.
	 * 
	 */
	public void setUniversityFiscalPeriodCode(String universityFiscalPeriodCode) {
		this.universityFiscalPeriodCode = universityFiscalPeriodCode;
	}


	/**
	 * Gets the financialDocumentTypeCode attribute.
	 * 
	 * @return Returns the financialDocumentTypeCode
	 * 
	 */
	public String getFinancialDocumentTypeCode() { 
		return financialDocumentTypeCode;
	}

	/**
	 * Sets the financialDocumentTypeCode attribute.
	 * 
	 * @param financialDocumentTypeCode The financialDocumentTypeCode to set.
	 * 
	 */
	public void setFinancialDocumentTypeCode(String financialDocumentTypeCode) {
		this.financialDocumentTypeCode = financialDocumentTypeCode;
	}


	/**
	 * Gets the financialSystemOriginationCode attribute.
	 * 
	 * @return Returns the financialSystemOriginationCode
	 * 
	 */
	public String getFinancialSystemOriginationCode() { 
		return financialSystemOriginationCode;
	}

	/**
	 * Sets the financialSystemOriginationCode attribute.
	 * 
	 * @param financialSystemOriginationCode The financialSystemOriginationCode to set.
	 * 
	 */
	public void setFinancialSystemOriginationCode(String financialSystemOriginationCode) {
		this.financialSystemOriginationCode = financialSystemOriginationCode;
	}


	/**
	 * Gets the documentNumber attribute.
	 * 
	 * @return Returns the documentNumber
	 * 
	 */
	public String getDocumentNumber() { 
		return documentNumber;
	}

	/**
	 * Sets the documentNumber attribute.
	 * 
	 * @param documentNumber The documentNumber to set.
	 * 
	 */
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}


	/**
	 * Gets the transactionLedgerEntrySequenceNumber attribute.
	 * 
	 * @return Returns the transactionLedgerEntrySequenceNumber
	 * 
	 */
	public Long getTransactionLedgerEntrySequenceNumber() { 
		return transactionLedgerEntrySequenceNumber;
	}

	/**
	 * Sets the transactionLedgerEntrySequenceNumber attribute.
	 * 
	 * @param transactionLedgerEntrySequenceNumber The transactionLedgerEntrySequenceNumber to set.
	 * 
	 */
	public void setTransactionLedgerEntrySequenceNumber(Long transactionLedgerEntrySequenceNumber) {
		this.transactionLedgerEntrySequenceNumber = transactionLedgerEntrySequenceNumber;
	}


	/**
	 * Gets the transactionLedgerEntryDescription attribute.
	 * 
	 * @return Returns the transactionLedgerEntryDescription
	 * 
	 */
	public String getTransactionLedgerEntryDescription() { 
		return transactionLedgerEntryDescription;
	}

	/**
	 * Sets the transactionLedgerEntryDescription attribute.
	 * 
	 * @param transactionLedgerEntryDescription The transactionLedgerEntryDescription to set.
	 * 
	 */
	public void setTransactionLedgerEntryDescription(String transactionLedgerEntryDescription) {
		this.transactionLedgerEntryDescription = transactionLedgerEntryDescription;
	}


	/**
	 * Gets the transactionLedgerEntryAmount attribute.
	 * 
	 * @return Returns the transactionLedgerEntryAmount
	 * 
	 */
	public KualiDecimal getTransactionLedgerEntryAmount() { 
		return transactionLedgerEntryAmount;
	}

	/**
	 * Sets the transactionLedgerEntryAmount attribute.
	 * 
	 * @param transactionLedgerEntryAmount The transactionLedgerEntryAmount to set.
	 * 
	 */
	public void setTransactionLedgerEntryAmount(KualiDecimal transactionLedgerEntryAmount) {
		this.transactionLedgerEntryAmount = transactionLedgerEntryAmount;
	}


	/**
	 * Gets the transactionDebitCreditCode attribute.
	 * 
	 * @return Returns the transactionDebitCreditCode
	 * 
	 */
	public String getTransactionDebitCreditCode() { 
		return transactionDebitCreditCode;
	}

	/**
	 * Sets the transactionDebitCreditCode attribute.
	 * 
	 * @param transactionDebitCreditCode The transactionDebitCreditCode to set.
	 * 
	 */
	public void setTransactionDebitCreditCode(String transactionDebitCreditCode) {
		this.transactionDebitCreditCode = transactionDebitCreditCode;
	}


	/**
	 * Gets the transactionDate attribute.
	 * 
	 * @return Returns the transactionDate
	 * 
	 */
	public Date getTransactionDate() { 
		return transactionDate;
	}

	/**
	 * Sets the transactionDate attribute.
	 * 
	 * @param transactionDate The transactionDate to set.
	 * 
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}


	/**
	 * Gets the organizationDocumentNumber attribute.
	 * 
	 * @return Returns the organizationDocumentNumber
	 * 
	 */
	public String getOrganizationDocumentNumber() { 
		return organizationDocumentNumber;
	}

	/**
	 * Sets the organizationDocumentNumber attribute.
	 * 
	 * @param organizationDocumentNumber The organizationDocumentNumber to set.
	 * 
	 */
	public void setOrganizationDocumentNumber(String organizationDocumentNumber) {
		this.organizationDocumentNumber = organizationDocumentNumber;
	}


	/**
	 * Gets the projectCode attribute.
	 * 
	 * @return Returns the projectCode
	 * 
	 */
	public String getProjectCode() { 
		return projectCode;
	}

	/**
	 * Sets the projectCode attribute.
	 * 
	 * @param projectCode The projectCode to set.
	 * 
	 */
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}


	/**
	 * Gets the organizationReferenceId attribute.
	 * 
	 * @return Returns the organizationReferenceId
	 * 
	 */
	public String getOrganizationReferenceId() { 
		return organizationReferenceId;
	}

	/**
	 * Sets the organizationReferenceId attribute.
	 * 
	 * @param organizationReferenceId The organizationReferenceId to set.
	 * 
	 */
	public void setOrganizationReferenceId(String organizationReferenceId) {
		this.organizationReferenceId = organizationReferenceId;
	}


	/**
	 * Gets the referenceFinancialDocumentTypeCode attribute.
	 * 
	 * @return Returns the referenceFinancialDocumentTypeCode
	 * 
	 */
	public String getReferenceFinancialDocumentTypeCode() { 
		return referenceFinancialDocumentTypeCode;
	}

	/**
	 * Sets the referenceFinancialDocumentTypeCode attribute.
	 * 
	 * @param referenceFinancialDocumentTypeCode The referenceFinancialDocumentTypeCode to set.
	 * 
	 */
	public void setReferenceFinancialDocumentTypeCode(String referenceFinancialDocumentTypeCode) {
		this.referenceFinancialDocumentTypeCode = referenceFinancialDocumentTypeCode;
	}


	/**
	 * Gets the referenceFinancialSystemOriginationCode attribute.
	 * 
	 * @return Returns the referenceFinancialSystemOriginationCode
	 * 
	 */
	public String getReferenceFinancialSystemOriginationCode() { 
		return referenceFinancialSystemOriginationCode;
	}

	/**
	 * Sets the referenceFinancialSystemOriginationCode attribute.
	 * 
	 * @param referenceFinancialSystemOriginationCode The referenceFinancialSystemOriginationCode to set.
	 * 
	 */
	public void setReferenceFinancialSystemOriginationCode(String referenceFinancialSystemOriginationCode) {
		this.referenceFinancialSystemOriginationCode = referenceFinancialSystemOriginationCode;
	}


	/**
	 * Gets the referenceFinancialDocumentNumber attribute.
	 * 
	 * @return Returns the referenceFinancialDocumentNumber
	 * 
	 */
	public String getReferenceFinancialDocumentNumber() { 
		return referenceFinancialDocumentNumber;
	}

	/**
	 * Sets the referenceFinancialDocumentNumber attribute.
	 * 
	 * @param referenceFinancialDocumentNumber The referenceFinancialDocumentNumber to set.
	 * 
	 */
	public void setReferenceFinancialDocumentNumber(String referenceFinancialDocumentNumber) {
		this.referenceFinancialDocumentNumber = referenceFinancialDocumentNumber;
	}


	/**
	 * Gets the financialDocumentReversalDate attribute.
	 * 
	 * @return Returns the financialDocumentReversalDate
	 * 
	 */
	public Date getFinancialDocumentReversalDate() { 
		return financialDocumentReversalDate;
	}

	/**
	 * Sets the financialDocumentReversalDate attribute.
	 * 
	 * @param financialDocumentReversalDate The financialDocumentReversalDate to set.
	 * 
	 */
	public void setFinancialDocumentReversalDate(Date financialDocumentReversalDate) {
		this.financialDocumentReversalDate = financialDocumentReversalDate;
	}


	/**
	 * Gets the transactionEncumbranceUpdateCode attribute.
	 * 
	 * @return Returns the transactionEncumbranceUpdateCode
	 * 
	 */
	public String getTransactionEncumbranceUpdateCode() { 
		return transactionEncumbranceUpdateCode;
	}

	/**
	 * Sets the transactionEncumbranceUpdateCode attribute.
	 * 
	 * @param transactionEncumbranceUpdateCode The transactionEncumbranceUpdateCode to set.
	 * 
	 */
	public void setTransactionEncumbranceUpdateCode(String transactionEncumbranceUpdateCode) {
		this.transactionEncumbranceUpdateCode = transactionEncumbranceUpdateCode;
	}


	/**
	 * Gets the transactionPostingDate attribute.
	 * 
	 * @return Returns the transactionPostingDate
	 * 
	 */
	public Date getTransactionPostingDate() { 
		return transactionPostingDate;
	}

	/**
	 * Sets the transactionPostingDate attribute.
	 * 
	 * @param transactionPostingDate The transactionPostingDate to set.
	 * 
	 */
	public void setTransactionPostingDate(Date transactionPostingDate) {
		this.transactionPostingDate = transactionPostingDate;
	}


	/**
	 * Gets the junk attribute.
	 * 
	 * @return Returns the junk
	 * 
	 */
	public String getJunk() { 
		return junk;
	}

	/**
	 * Sets the junk attribute.
	 * 
	 * @param junk The junk to set.
	 * 
	 */
	public void setJunk(String junk) {
		this.junk = junk;
	}


	/**
	 * Gets the universityFiscalPeriod attribute.
	 * 
	 * @return Returns the universityFiscalPeriod
	 * 
	 */
	public AccountingPeriod getUniversityFiscalPeriod() { 
		return universityFiscalPeriod;
	}

	/**
	 * Sets the universityFiscalPeriod attribute.
	 * 
	 * @param universityFiscalPeriod The universityFiscalPeriod to set.
	 * @deprecated
	 */
	public void setUniversityFiscalPeriod(AccountingPeriod universityFiscalPeriod) {
		this.universityFiscalPeriod = universityFiscalPeriod;
	}

	/**
	 * Gets the financialObject attribute.
	 * 
	 * @return Returns the financialObject
	 * 
	 */
	public ObjectCode getFinancialObject() { 
		return financialObject;
	}

	/**
	 * Sets the financialObject attribute.
	 * 
	 * @param financialObject The financialObject to set.
	 * @deprecated
	 */
	public void setFinancialObject(ObjectCode financialObject) {
		this.financialObject = financialObject;
	}

	/**
	 * Gets the financialSubObject attribute.
	 * 
	 * @return Returns the financialSubObject
	 * 
	 */
	public SubObjCd getFinancialSubObject() { 
		return financialSubObject;
	}

	/**
	 * Sets the financialSubObject attribute.
	 * 
	 * @param financialSubObject The financialSubObject to set.
	 * @deprecated
	 */
	public void setFinancialSubObject(SubObjCd financialSubObject) {
		this.financialSubObject = financialSubObject;
	}

	/**
	 * Gets the subAccount attribute.
	 * 
	 * @return Returns the subAccount
	 * 
	 */
	public SubAccount getSubAccount() { 
		return subAccount;
	}

	/**
	 * Sets the subAccount attribute.
	 * 
	 * @param subAccount The subAccount to set.
	 * @deprecated
	 */
	public void setSubAccount(SubAccount subAccount) {
		this.subAccount = subAccount;
	}

	/**
	 * Gets the account attribute.
	 * 
	 * @return Returns the account
	 * 
	 */
	public Account getAccount() { 
		return account;
	}

	/**
	 * Sets the account attribute.
	 * 
	 * @param account The account to set.
	 * @deprecated
	 */
	public void setAccount(Account account) {
		this.account = account;
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
	 * Gets the financialBalanceType attribute.
	 * 
	 * @return Returns the financialBalanceType
	 * 
	 */
	public BalanceTyp getFinancialBalanceType() { 
		return financialBalanceType;
	}

	/**
	 * Sets the financialBalanceType attribute.
	 * 
	 * @param financialBalanceType The financialBalanceType to set.
	 * @deprecated
	 */
	public void setFinancialBalanceType(BalanceTyp financialBalanceType) {
		this.financialBalanceType = financialBalanceType;
	}

	/**
	 * Gets the financialObjectType attribute.
	 * 
	 * @return Returns the financialObjectType
	 * 
	 */
	public ObjectType getFinancialObjectType() { 
		return financialObjectType;
	}

	/**
	 * Sets the financialObjectType attribute.
	 * 
	 * @param financialObjectType The financialObjectType to set.
	 * @deprecated
	 */
	public void setFinancialObjectType(ObjectType financialObjectType) {
		this.financialObjectType = financialObjectType;
	}

	/**
	 * Gets the project attribute.
	 * 
	 * @return Returns the project
	 * 
	 */
	public ProjectCode getProject() { 
		return project;
	}

	/**
	 * Sets the project attribute.
	 * 
	 * @param project The project to set.
	 * @deprecated
	 */
	public void setProject(ProjectCode project) {
		this.project = project;
	}

	/**
	 * Gets the referenceFinancialSystemOrigination attribute.
	 * 
	 * @return Returns the referenceFinancialSystemOrigination
	 * 
	 */
	public OriginationCode getReferenceFinancialSystemOrigination() { 
		return referenceFinancialSystemOrigination;
	}

	/**
	 * Sets the referenceFinancialSystemOrigination attribute.
	 * 
	 * @param referenceFinancialSystemOrigination The referenceFinancialSystemOrigination to set.
	 * @deprecated
	 */
	public void setReferenceFinancialSystemOrigination(OriginationCode referenceFinancialSystemOrigination) {
		this.referenceFinancialSystemOrigination = referenceFinancialSystemOrigination;
	}

    /**
     * Gets the documentHeader attribute. 
     * @return Returns the documentHeader.
     */
    public DocumentHeader getDocumentHeader() {
        return documentHeader;
    }

    /**
     * Sets the documentHeader attribute value.
     * @param documentHeader The documentHeader to set.
     * @deprecated
     */
    public void setDocumentHeader(DocumentHeader documentHeader) {
        this.documentHeader = documentHeader;
    }

    /**
     * Gets the financialSystemOrigination attribute. 
     * @return Returns the financialSystemOrigination.
     */
    public OriginationCode getFinancialSystemOrigination() {
        return financialSystemOrigination;
    }

    /**
     * Sets the financialSystemOrigination attribute value.
     * @param financialSystemOrigination The financialSystemOrigination to set.
     * @deprecated
     */
    public void setFinancialSystemOrigination(OriginationCode financialSystemOrigination) {
        this.financialSystemOrigination = financialSystemOrigination;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();      
        if (this.universityFiscalYear != null) {
            m.put("universityFiscalYear", this.universityFiscalYear.toString());
        }
        m.put("chartOfAccountsCode", this.chartOfAccountsCode);
        m.put("accountNumber", this.accountNumber);
        m.put("subAccountNumber", this.subAccountNumber);
        m.put("financialObjectCode", this.financialObjectCode);
        m.put("financialSubObjectCode", this.financialSubObjectCode);
        m.put("financialBalanceTypeCode", this.financialBalanceTypeCode);
        m.put("financialObjectTypeCode", this.financialObjectTypeCode);
        m.put("universityFiscalPeriodCode", this.universityFiscalPeriodCode);
        m.put("financialDocumentTypeCode", this.financialDocumentTypeCode);
        m.put("financialSystemOriginationCode", this.financialSystemOriginationCode);
        m.put("documentNumber", this.documentNumber);
        if (this.transactionLedgerEntrySequenceNumber != null) {
            m.put("transactionLedgerEntrySequenceNumber", this.transactionLedgerEntrySequenceNumber.toString());
        }
        return m;
    }    

}
