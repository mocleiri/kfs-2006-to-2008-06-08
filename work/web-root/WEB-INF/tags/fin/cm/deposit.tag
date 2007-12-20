<%--
 Copyright 2007 The Kuali Foundation.
 
 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl1.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/jsp/kfs/kfsTldHeader.jsp"%>

<%@ attribute name="editingMode" required="true" type="java.util.Map"%>
<%@ attribute name="depositIndex" required="true" %>
<%@ attribute name="deposit" required="true" type="org.kuali.module.financial.bo.Deposit"%>

<c:set var="readOnly" value="${empty editingMode[AuthorizationConstants.EditMode.FULL_ENTRY]}" />
<c:set var="allowAdditionalDeposits" value="${editingMode[AuthorizationConstants.CashManagementEditMode.ALLOW_ADDITIONAL_DEPOSITS]}" />
<c:set var="allowCancelDeposits" value="${editingMode[AuthorizationConstants.CashManagementEditMode.ALLOW_CANCEL_DEPOSITS]}" />

<c:set var="receiptAttributes" value="${DataDictionary.CashReceiptDocument.attributes}" />
<c:set var="dummyAttributes" value="${DataDictionary.AttributeReferenceDummy.attributes}" />
<c:set var="depositAttributes" value="${DataDictionary.Deposit.attributes}" />
<c:set var="checkAttributes" value="${DataDictionary.CheckBase.attributes}" />

<c:set var="depositPropertyBase" value="document.deposit[${depositIndex}]" />
<c:set var="labelBase" value="document.deposit[${depositIndex}]" />

<c:set var="depositType">
    <bean:write name="KualiForm" property="${depositPropertyBase}.rawDepositTypeCode" />
</c:set>

<c:set var="depositTitle">
    <bean:write name="KualiForm" property="${depositPropertyBase}.depositTypeCode" /> Deposit
</c:set>
<c:if test="${depositType != KFSConstants.DepositConstants.DEPOSIT_TYPE_FINAL}">
    <c:set var="depositTitle" value="${depositTitle} ${depositIndex + 1}" />
</c:if>


<div class="h2-container">
    <h2>${depositTitle}</h2>
</div>


<table width="100%" border="0" cellpadding="0" cellspacing="0" class="datatable">
    <%-- deposit --%>
    <tr>
        <td colspan="4">
            <html:hidden property="${depositPropertyBase}.documentNumber" />
            <html:hidden property="${depositPropertyBase}.financialDocumentDepositLineNumber" />
            <html:hidden property="${depositPropertyBase}.objectId" />
            <html:hidden property="${depositPropertyBase}.versionNumber" />
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="datatable">
                <tr>
                    <kul:htmlAttributeHeaderCell labelFor="${labelBase}.depositBankCode" attributeEntry="${depositAttributes.depositBankCode}" align="left" />
                    <kul:htmlAttributeHeaderCell labelFor="${labelBase}.depositBankAccountNumber" attributeEntry="${depositAttributes.depositBankAccountNumber}" align="left" />

                    <kul:htmlAttributeHeaderCell labelFor="${labelBase}.depositTicketNumber" attributeEntry="${depositAttributes.depositTicketNumber}" align="left" />
                    <kul:htmlAttributeHeaderCell labelFor="${labelBase}.depositTypeCode" attributeEntry="${depositAttributes.depositTypeCode}" hideRequiredAsterisk="true" align="left" />
                    <kul:htmlAttributeHeaderCell labelFor="${labelBase}.depositDate" attributeEntry="${depositAttributes.depositDate}" hideRequiredAsterisk="true" align="left" />
                    <kul:htmlAttributeHeaderCell labelFor="${labelBase}.depositAmount" attributeEntry="${depositAttributes.depositAmount}" hideRequiredAsterisk="true" align="left" />
                </tr>

                <tr>
                    <td align="left" rowspan="2">
                        <kul:htmlControlAttribute property="${depositPropertyBase}.depositBankCode" attributeEntry="${depositAttributes.depositBankCode}" readOnly="${readOnly}" onblur="loadBankInfo(document.forms['KualiForm'], '${depositPropertyBase}.depositBankCode', '${depositPropertyBase}.bank');" />
                        <c:if test="${!readOnly}">
                            <kul:lookup boClassName="org.kuali.module.financial.bo.Bank" fieldConversions="financialDocumentBankCode:${depositPropertyBase}.depositBankCode" />
                        </c:if>
                        
                        <br/>
                        <div id="${depositPropertyBase}.bank.div" class="fineprint">
                            <bean:write name="KualiForm" property="${depositPropertyBase}.bankAccount.bank.financialDocumentBankShortNm"/>&nbsp;
                        </div>
                    </td>

                    <td align="left" rowspan="2">
                        <kul:htmlControlAttribute property="${depositPropertyBase}.depositBankAccountNumber" attributeEntry="${depositAttributes.depositBankAccountNumber}" readOnly="${readOnly}" onblur="loadBankAccountInfo(document.forms['KualiForm'], '${depositPropertyBase}.depositBankCode', '${depositPropertyBase}.depositBankAccountNumber', '${depositPropertyBase}.bankAccount' );" />
                        <c:if test="${!readOnly}">
                            <kul:lookup boClassName="org.kuali.module.financial.bo.BankAccount" fieldConversions="financialDocumentBankCode:${depositPropertyBase}.depositBankCode,finDocumentBankAccountNumber:${depositPropertyBase}.depositBankAccountNumber" lookupParameters="${depositPropertyBase}.depositBankCode:financialDocumentBankCode" />
                        </c:if>
                        
                        <br/>
                        <div id="${depositPropertyBase}.bankAccount.div" class="fineprint">
                            <bean:write name="KualiForm" property="${depositPropertyBase}.bankAccount.finDocumentBankAccountDesc"/>&nbsp;
                        </div>
                    </td>

                    <td align="left" rowspan="2">
                        <kul:htmlControlAttribute property="${depositPropertyBase}.depositTicketNumber" attributeEntry="${depositAttributes.depositTicketNumber}" readOnly="${readOnly}"/>
                        <br/>
                        &nbsp;
                    </td>

                    <td align="left" rowspan="2">
                        <kul:htmlControlAttribute property="${depositPropertyBase}.depositTypeCode" attributeEntry="${depositAttributes.depositTypeCode}" readOnly="true"/>
                        <br/>
                        &nbsp;
                    </td>

                    <td align="left" rowspan="2">
                        <kul:htmlControlAttribute property="${depositPropertyBase}.depositDate" attributeEntry="${depositAttributes.depositDate}" readOnly="true"/>
                        <br/>
                        &nbsp;
                    </td>

                    <td align="left" rowspan="2">
                        <kul:htmlControlAttribute property="${depositPropertyBase}.depositAmount" attributeEntry="${depositAttributes.depositAmount}" readOnly="true"/>
                        <br/>
                        &nbsp;
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    
    <%-- currency/coin details --%>
    <c:if test="${depositType == KFSConstants.DepositConstants.DEPOSIT_TYPE_FINAL && !empty deposit.depositedCurrency && !empty deposit.depositedCoin}">
      <tr>
        <td colspan="4" class="tab-subhead">
          Currency/Coin Details
        </td>
      </tr>
      <tr>
        <td colspan="4">
          <fin:currencyCoinLine currencyProperty="${depositPropertyBase}.depositedCurrency" coinProperty="${depositPropertyBase}.depositedCoin" readOnly="true" />
        </td>
      </tr>
    </c:if>
    
    <%-- cashReceipts header --%>
    <c:if test="${!empty KualiForm.depositHelpers[depositIndex].cashReceiptSummarys}">
    <tr><td colspan="4" class="tab-subhead">
        Cash Receipts
    </td></tr>
    <tr>
        <kul:htmlAttributeHeaderCell attributeEntry="${receiptAttributes.documentNumber}" align="left" />
        <kul:htmlAttributeHeaderCell literalLabel="Description" align="left" />

        <kul:htmlAttributeHeaderCell literalLabel="Created on" align="left" />
        <kul:htmlAttributeHeaderCell attributeEntry="${receiptAttributes.totalCheckAmount}" align="left" />
    </tr>

    <%-- cashReceipts data --%>
    <logic:iterate name="KualiForm" property="depositHelper[${depositIndex}].cashReceiptSummarys" id="receiptSummary" indexId="summaryIndex" >
        <c:set var="receiptSummaryBase" value="depositHelper[${depositIndex}].cashReceiptSummary[${summaryIndex}]" />
        <tr>
            <td align="left">
				<a href="financialCashReceipt.do?methodToCall=docHandler&docId=${receiptSummary.documentNumber}&command=displayDocSearchView" target="new">
                <kul:htmlControlAttribute property="${receiptSummaryBase}.documentNumber" attributeEntry="${receiptAttributes.documentNumber}" readOnly="true" />
				</a>
            </td>

            <td align="left">
                <kul:htmlControlAttribute property="${receiptSummaryBase}.description" attributeEntry="${receiptAttributes.documentNumber}" readOnly="true" />
            </td>

            <td align="left">
                <kul:htmlControlAttribute property="${receiptSummaryBase}.createDate" attributeEntry="${dummyAttributes.genericTimestamp}" readOnly="true" />
            </td>
    
            <td align="left">
                <kul:htmlControlAttribute property="${receiptSummaryBase}.checkAmount" attributeEntry="${dummyAttributes.genericAmount}" readOnly="true" />
            </td>
        </tr>
    </logic:iterate>
    </c:if>
    
    <%-- cashiering checks --%>
    <c:if test="${!empty KualiForm.depositHelpers[depositIndex].cashieringChecks}">
    <tr>
      <td colspan="4" class="tab-subhead">Cashiering Checks</td>
    </tr>
    <tr>
      <kul:htmlAttributeHeaderCell attributeEntry="${checkAttributes.checkNumber}" />
      <kul:htmlAttributeHeaderCell attributeEntry="${checkAttributes.checkDate}" />
      <kul:htmlAttributeHeaderCell attributeEntry="${checkAttributes.description}" />
      <kul:htmlAttributeHeaderCell attributeEntry="${checkAttributes.amount}" />
    </tr>
    <logic:iterate name="KualiForm" property="depositHelper[${depositIndex}].cashieringChecks" id="cashieringCheck" indexId="cashieringCheckCtr">
      <tr>
        <td align="left">
          <kul:htmlControlAttribute property="depositHelper[${depositIndex}].cashieringChecks[${cashieringCheckCtr}].checkNumber" attributeEntry="${checkAttributes.checkNumber}" readOnly="true" />
        </td>
        <td align="left">
          <kul:htmlControlAttribute property="depositHelper[${depositIndex}].cashieringChecks[${cashieringCheckCtr}].checkDate" attributeEntry="${checkAttributes.checkDate}" readOnly="true" />
        </td>
        <td align="left">
          <kul:htmlControlAttribute property="depositHelper[${depositIndex}].cashieringChecks[${cashieringCheckCtr}].description" attributeEntry="${checkAttributes.description}" readOnly="true" />
        </td>
        <td align="left">
          <kul:htmlControlAttribute property="depositHelper[${depositIndex}].cashieringChecks[${cashieringCheckCtr}].amount" attributeEntry="${checkAttributes.amount}" readOnly="true" />
        </td>
      </tr>
    </logic:iterate>
    </c:if>

    <%-- deposit footer --%>
    <c:if test="${(depositType == KFSConstants.DepositConstants.DEPOSIT_TYPE_FINAL && allowCancelDeposits) || allowAdditionalDeposits}">
        <tr>
            <td colspan="4" class="subhead" style="text-align: center">
                <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_cancel.gif" style="border: none" property="methodToCall.cancelDeposit.line${depositIndex}" alt="close" title="close"/>
            </td>
        </tr>         
    </c:if>
</table>