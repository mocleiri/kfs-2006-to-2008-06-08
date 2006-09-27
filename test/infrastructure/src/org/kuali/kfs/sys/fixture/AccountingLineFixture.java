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
package org.kuali.test.fixtures;

import static org.kuali.Constants.GL_DEBIT_CODE;
import static org.kuali.Constants.GL_CREDIT_CODE;

import org.kuali.core.bo.AccountingLine;
import org.kuali.core.bo.SourceAccountingLine;
import org.kuali.core.bo.TargetAccountingLine;
import org.kuali.core.util.KualiDecimal;

public enum AccountingLineFixture {
    LINE("1005", 2004, 1, "BL", "1031400", "AC", "ADV", "5000", "SSS", "KUL3", "Y", "IN", "ONE", "01", "1", "blah", GL_DEBIT_CODE, "2.50"),
    LINE1(null, 2004, null, "BA", "6044900", null, null, "1697", null, null, null, "IN", null, null, null, null, null, "1000.00"),
    LINE2(null, 2004, null, "BL", "1031400", null, "ADV", "5000", "SSS", "KUL3", null, "IN", "ONE", null, null, null, null, "1.10"),
    LINE3(null, 2004, null, "BA", "6044900", null, null, "4008", "POL", null, null, "IN", "TWO", null, null, null, null, "1.10"),
    LINE4(null, 2004, null, "UA", "1912201", null, null, "5033", null, null, null, "EX", null, null, "123", null, null, "1.10"),
    LINE5(null, 2004, null, "BL", "1031400", "AC", "ADV", "5000", "SSS", "KUL3", null, "AS", "ONE", null, null, null, GL_DEBIT_CODE, "1.10"),
    LINE6(null, 2004, null, "BL", "1031400", "PE", "ADV", "5000", "SSS", "KUL3", null, "IN", "ONE", null, "123", null, GL_DEBIT_CODE, "1.10"),
    LINE7(null, 2004, null, "BA", "6044900", null, null, "4061", null, null, null, "IN", null, null, null, null, null, "1.10"),

    LINE8(null, 2004, null, "UA", "1912201", "AC", null, "9980", null, "BOB", "F", "TI", null, "01", "2004", "blah", GL_DEBIT_CODE, "1.10"),
    LINE9(null, 2004, null, "UA", "1912201", "AC", null, "9980", null, "BOB", "F", "FB", null, "01", "2004", null, GL_DEBIT_CODE, "1.10"),
    LINE10(null, 2004, null, "UA", "1912201", "AC", null, "8160", null, "BOB", "F", "TI", null, "01", "2004", "blah", GL_DEBIT_CODE, "1.10"),
    LINE11(null, 2004, null, "UA", "1912201", "AC", null, "9980", null, "BOB", "F", "TI", null, null, null, null, GL_DEBIT_CODE, "1.10"),
    LINE12(null, 2004, null, "UA", "1912201", "AC", null, "9897", null, "BOB", "F", "TI", null, null, null, null, GL_DEBIT_CODE, "1.10"),
    LINE13(null, 2004, null, "UA", "1912201", "AC", null, "9899", null, "BOB", "F", "TI", null, null, null, null, GL_DEBIT_CODE, "1.10"),
    LINE14(null, 2004, null, "UA", "1912201", null, null, "5033", null, null, null, "EX", null, null, "123", null, GL_DEBIT_CODE, "1.10"),
    LINE15(null, 2004, null, "UA", "1912201", null, null, "5033", null, null, null, "EX", null, null, "123", null, GL_CREDIT_CODE, "1.10"),
    LINE16(null, 2004, null, "UA", "1912201", "AC", null, "1175", null, "BOB", "F", "IC", null, "01", "2004", null, GL_DEBIT_CODE, "1.10"),
    LINE17(null, 2004, null, "UA", "1912201", "AC", null, "7600", null, "BOB", "F", "AM", null, "01", "2004", null, GL_DEBIT_CODE, "1.10"),


    GEC_LINE1(null, 2004, null, "BL", "1031400", null, "ADV", "5000", "SSS", "KUL3", null, "IN", "ONE", "01", "123", null, null, "1.10"),
    ICA_LINE(null, 2004, null, "BL", "5431400", null, null, "5500", null, null, null, null, null, null, null, null, null, "1.10"),
    EXPENSE_GEC_LINE(null, 2004, null, "BA", "6044900", "AC", null, "1940", null, "BOB", "F", null, null, "01", "123", null, null, "1.10"),

    DOCUMENT_SERVICE_TEST_LINE(null, null, null, "BL", "1031400", null, "ADV", "5000", "SSS", "KUL3", "Y", "AS", null, null, null, null, GL_DEBIT_CODE, "2.50"),
    PFIP_SUB_FUND_LINE(null, 2004, null, "BA", "9544900", "AC", null, "9900", null, null, null, null, null, null, "2004", null, null, "1000.00"),
    SOURCE_LINE(null, 2004, null, "UA", "1912201", "AC", null, "9980", null, "BOB", "F", "AS", null, "01", "2004", "blah", GL_DEBIT_CODE, "1000.00"),
    EXPENSE_LINE(null, 2004, null, "UA", "1912201", "AC", "BEER", "9900", null, "BOB", "F", "EX", null, "01", "2004", "blah", GL_DEBIT_CODE, "1000.00"),
    EXPENSE_LINE2(null, 2004, null, "BL", "1031400", "AC", "BLDG", "9900", null, "BOB", "F", "EX", null, null, null, null, GL_DEBIT_CODE, "1000.00"),
    EXTERNAL_ENCUMBRANCE_LINE(null, 2004, null, "BL", "1031400", "EX", "BLDG", "9900", null, "BOB", "F", "EX", null, "01", "2004", "PE", GL_DEBIT_CODE, "1000.00"),

    FLEXIBLE_EXPENSE_LINE(null, 2004, null, "BL", "2231401", "AC", null, "9900", null, "BOB", "F", "EX", null, "01", "1", "blah", GL_DEBIT_CODE, "1000.00"),
    CASH_LINE(null, 2004, null, "BA", "6044900", null, null, "8000", null, "BOB", null, "ES", null, null, null, null, null, "1000.00"),
    LOSSS_ON_RETIRE_LINE(null, 2004, null, "BA", "6044900", null, null, "5137", "CF", "BOB", null, "ES", null, null, null, null, null, "1000.00"),
    ACCRUED_INCOME_LINE(null, 2004, null, "BA", "6044900", null, null, "8111", null, "BOB", null, "ES", null, "01", "2004", null, null, "1000.00"),
    ACCRUED_SICK_PAY_LINE(null, 2004, null, "UA", "1912201", null, null, "2998", null, "BOB", null, "ES", null, null, "01", "2004", null, "1000.00"),
    FUND_BALANCE_LINE(null, 2004, null, "BA", "6044900", null, null, "9899", null, "BOB", null, null, null, null, "01", "2004", GL_DEBIT_CODE, "1000.00"),
    ;

    public final String accountNumber;
    public final String balanceTypeCode;
    public final String chartOfAccountsCode;
    public final String debitCreditCode;
    public final String encumbranceUpdateCode;
    public final String financialObjectCode;
    public final String financialSubObjectCode;
    public final String objectTypeCode;
    public final String organizationReferenceId;
    public final String projectCode;
    public final String referenceOriginCode;
    public final String referenceNumber;
    public final String referenceTypeCode;
    public final String subAccountNumber;
    public final KualiDecimal amount;
    public final String financialDocumentNumber;
    public final Integer postingYear;
    public final Integer sequenceNumber;


    AccountingLineFixture(String financialDocumentNumber, Integer postingYear, Integer sequenceNumber, String chartOfAccountsCode, String accountNumber,
                          String balanceTypeCode, String subAccountNumber, String financialObjectCode, String financialSubObjectCode, String projectCode, String encumbranceUpdateCode,
                          String objectTypeCode, String organizationReferenceId, String referenceOriginCode, String referenceNumber, String referenceTypeCode, String debitCreditCode, String amount)
    {

        this.financialDocumentNumber = financialDocumentNumber;
        this.postingYear = postingYear;
        this.sequenceNumber = sequenceNumber;
        this.accountNumber = accountNumber;
        this.balanceTypeCode = balanceTypeCode;
        this.chartOfAccountsCode = chartOfAccountsCode;
        this.debitCreditCode = debitCreditCode;
        this.encumbranceUpdateCode = encumbranceUpdateCode;
        this.financialObjectCode = financialObjectCode;
        this.financialSubObjectCode = financialSubObjectCode;
        this.objectTypeCode = objectTypeCode;
        this.organizationReferenceId = organizationReferenceId;
        this.projectCode = projectCode;
        this.referenceOriginCode = referenceOriginCode;
        this.referenceNumber = referenceNumber;
        this.referenceTypeCode = referenceTypeCode;
        this.subAccountNumber = subAccountNumber;
        this.amount = new KualiDecimal(amount);
    }

    private <T extends AccountingLine> T createAccountingLine(Class<T> lineClass)
        throws InstantiationException, IllegalAccessException
    {
        return createAccountingLine(lineClass, this.financialDocumentNumber, this.postingYear, this.sequenceNumber);
    }

    public <T extends AccountingLine> T createAccountingLine(Class<T> lineClass, String debitCreditCode)
        throws InstantiationException, IllegalAccessException
    {
        T line = createAccountingLine(lineClass, this.financialDocumentNumber, this.postingYear, this.sequenceNumber);
        line.setDebitCreditCode(debitCreditCode);
        return line;
    }

    public <T extends AccountingLine> T createAccountingLine(Class<T> lineClass, String financialDocumentNumber, Integer postingYear, Integer sequenceNumber)
        throws InstantiationException, IllegalAccessException
    {
        T line = createLine(lineClass);

        line.setFinancialDocumentNumber(financialDocumentNumber);
        line.setPostingYear(postingYear);
        line.setSequenceNumber(sequenceNumber);

        line.refresh();
        return line;
    }

    private <T extends AccountingLine> T createLine(Class<T> lineClass)
        throws InstantiationException, IllegalAccessException
    {
        T line = (T) lineClass.newInstance();
        line.setAccountNumber(this.accountNumber);
        line.setAmount(this.amount);
        line.setBalanceTypeCode(this.balanceTypeCode);
        line.setChartOfAccountsCode(this.chartOfAccountsCode);
        line.setDebitCreditCode(this.debitCreditCode);
        line.setEncumbranceUpdateCode(this.encumbranceUpdateCode);
        line.setFinancialObjectCode(this.financialObjectCode);
        line.setFinancialSubObjectCode(this.financialSubObjectCode);
        line.setObjectTypeCode(this.objectTypeCode);
        line.setOrganizationReferenceId(this.organizationReferenceId);
        line.setProjectCode(this.projectCode);
        line.setReferenceOriginCode(this.referenceOriginCode);
        line.setReferenceNumber(this.referenceNumber);
        line.setReferenceTypeCode(this.referenceTypeCode);
        line.setSubAccountNumber(this.subAccountNumber);

        return line;
    }

    public SourceAccountingLine createSourceAccountingLine()
        throws InstantiationException, IllegalAccessException
    {
        return createAccountingLine(SourceAccountingLine.class);
    }

    public TargetAccountingLine createTargetAccountingLine()
        throws InstantiationException, IllegalAccessException
    {
        return createAccountingLine(TargetAccountingLine.class);
    }
}
