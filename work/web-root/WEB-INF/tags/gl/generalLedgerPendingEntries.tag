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

<kul:tab tabTitle="General Ledger Pending Entries" defaultOpen="false" tabErrorKey="${KFSConstants.GENERAL_LEDGER_PENDING_ENTRIES_TAB_ERRORS}">
<div class="tab-container" align=center>
		<div class="h2-container">
		<h2>General Ledger Pending Entries <kul:lookup boClassName="org.kuali.kfs.bo.GeneralLedgerPendingEntry" lookupParameters="document.documentNumber:documentNumber" hideReturnLink="true" suppressActions="true"/></h2>
		</div>
	 <table cellpadding="0" cellspacing="0" class="datatable" summary="view/edit pending entries">

	<c:if test="${empty KualiForm.document.generalLedgerPendingEntries}">
		<tr>
			<td class="datacell" height="50"colspan="12"><div align="center">There are currently no General Ledger Pending Entries associated with this Transaction Processing document.</div></td>
		</tr>
	</c:if>
	<c:if test="${!empty KualiForm.document.generalLedgerPendingEntries}">
        <c:set var="entryAttributes" value="${DataDictionary.GeneralLedgerPendingEntry.attributes}" />
		<tr>
            <kul:htmlAttributeHeaderCell attributeEntry="${entryAttributes.transactionLedgerEntrySequenceNumber}" hideRequiredAsterisk="true" scope="col"/>
            <kul:htmlAttributeHeaderCell attributeEntry="${entryAttributes.universityFiscalYear}" hideRequiredAsterisk="true" scope="col"/>
            <kul:htmlAttributeHeaderCell attributeEntry="${entryAttributes.chartOfAccountsCode}" hideRequiredAsterisk="true" scope="col"/>
            <kul:htmlAttributeHeaderCell attributeEntry="${entryAttributes.accountNumber}" hideRequiredAsterisk="true" scope="col"/>
            <kul:htmlAttributeHeaderCell attributeEntry="${entryAttributes.subAccountNumber}" hideRequiredAsterisk="true" scope="col"/>
            <kul:htmlAttributeHeaderCell attributeEntry="${entryAttributes.financialObjectCode}" hideRequiredAsterisk="true" scope="col"/>
            <kul:htmlAttributeHeaderCell attributeEntry="${entryAttributes.financialSubObjectCode}" hideRequiredAsterisk="true" scope="col"/>
            <kul:htmlAttributeHeaderCell attributeEntry="${entryAttributes.projectCode}" hideRequiredAsterisk="true" scope="col"/>
            <kul:htmlAttributeHeaderCell attributeEntry="${entryAttributes.financialDocumentTypeCode}" hideRequiredAsterisk="true" scope="col"/>
            <kul:htmlAttributeHeaderCell attributeEntry="${entryAttributes.financialBalanceTypeCode}" hideRequiredAsterisk="true" scope="col"/>
            <kul:htmlAttributeHeaderCell attributeEntry="${entryAttributes.financialObjectTypeCode}" hideRequiredAsterisk="true" scope="col"/>
            <kul:htmlAttributeHeaderCell attributeEntry="${entryAttributes.transactionLedgerEntryAmount}" hideRequiredAsterisk="true" scope="col"/>
            <kul:htmlAttributeHeaderCell attributeEntry="${entryAttributes.transactionDebitCreditCode}" hideRequiredAsterisk="true" scope="col"/>
		</tr>
		<logic:iterate id="generalLedgerPendingEntry" name="KualiForm" property="document.generalLedgerPendingEntries" indexId="ctr">
			<tr>
				<th class="datacell center"><html:hidden property="document.generalLedgerPendingEntry[${ctr}].transactionLedgerEntrySequenceNumber" write="true"/></th>
				<td class="datacell center">
					<kul:inquiry boClassName="org.kuali.core.bo.Options" keyValues="universityFiscalYear=${generalLedgerPendingEntry.universityFiscalYear}" render="true">
						<html:hidden property="document.generalLedgerPendingEntry[${ctr}].universityFiscalYear" write="true"/>
					</kul:inquiry>
				</td>
				<td class="datacell center">
					<kul:inquiry boClassName="org.kuali.module.chart.bo.Chart" keyValues="chartOfAccountsCode=${generalLedgerPendingEntry.chartOfAccountsCode}" render="true">
						<html:hidden property="document.generalLedgerPendingEntry[${ctr}].chartOfAccountsCode" write="true"/>
					</kul:inquiry>
				</td>
				<td class="datacell center">
					<kul:inquiry boClassName="org.kuali.module.chart.bo.Account" keyValues="chartOfAccountsCode=${generalLedgerPendingEntry.chartOfAccountsCode}&accountNumber=${generalLedgerPendingEntry.accountNumber}" render="true">
						<html:hidden property="document.generalLedgerPendingEntry[${ctr}].accountNumber" write="true"/>
					</kul:inquiry>
				</td>
				<td class="datacell center">
					<kul:inquiry boClassName="org.kuali.module.chart.bo.SubAccount" keyValues="chartOfAccountsCode=${generalLedgerPendingEntry.chartOfAccountsCode}&accountNumber=${generalLedgerPendingEntry.accountNumber}&subAccountNumber=${generalLedgerPendingEntry.subAccountNumber}" render="${ ! generalLedgerPendingEntry.subAccountNumberBlank}">
						<html:hidden property="document.generalLedgerPendingEntry[${ctr}].subAccountNumber" write="true"/>
					</kul:inquiry>
				</td>
				<td class="datacell center">
					<kul:inquiry boClassName="org.kuali.module.chart.bo.ObjectCode" keyValues="financialObjectCode=${generalLedgerPendingEntry.financialObjectCode}&chartOfAccountsCode=${generalLedgerPendingEntry.chartOfAccountsCode}&universityFiscalYear=${generalLedgerPendingEntry.universityFiscalYear}" render="${ ! generalLedgerPendingEntry.financialObjectCodeBlank}">
						<html:hidden property="document.generalLedgerPendingEntry[${ctr}].financialObjectCode" write="true"/>
					</kul:inquiry>
				</td>
				<td class="datacell center">
					<kul:inquiry boClassName="org.kuali.module.chart.bo.SubObjCd" keyValues="financialSubObjectCode=${generalLedgerPendingEntry.financialSubObjectCode}&financialObjectCode=${generalLedgerPendingEntry.financialObjectCode}&chartOfAccountsCode=${generalLedgerPendingEntry.chartOfAccountsCode}&universityFiscalYear=${generalLedgerPendingEntry.universityFiscalYear}" render="${ ! generalLedgerPendingEntry.financialSubObjectCodeBlank}">
						<html:hidden property="document.generalLedgerPendingEntry[${ctr}].financialSubObjectCode" write="true"/>
					</kul:inquiry>
				</td>
				<td class="datacell center">
					<kul:inquiry boClassName="org.kuali.module.chart.bo.ProjectCode" keyValues="code=${generalLedgerPendingEntry.projectCode}" render="${ ! generalLedgerPendingEntry.projectCodeBlank}">
						<html:hidden property="document.generalLedgerPendingEntry[${ctr}].projectCode" write="true"/>
					</kul:inquiry>
				</td>
				<td class="datacell center">
					<kul:inquiry boClassName="org.kuali.core.bo.DocumentType" keyValues="financialDocumentTypeCode=${generalLedgerPendingEntry.financialDocumentTypeCode}" render="true">
						<html:hidden property="document.generalLedgerPendingEntry[${ctr}].financialDocumentTypeCode" write="true"/>
					</kul:inquiry>
				</td>
				<td class="datacell center">
					<kul:inquiry boClassName="org.kuali.module.chart.bo.codes.BalanceTyp" keyValues="code=${generalLedgerPendingEntry.financialBalanceTypeCode}" render="true">
						<html:hidden property="document.generalLedgerPendingEntry[${ctr}].financialBalanceTypeCode" write="true"/>
					</kul:inquiry>
				</td>
				<td class="datacell center">
					<kul:inquiry boClassName="org.kuali.module.chart.bo.ObjectType" keyValues="code=${generalLedgerPendingEntry.financialObjectTypeCode}" render="${ ! generalLedgerPendingEntry.financialObjectTypeCodeBlank}">
						<html:hidden property="document.generalLedgerPendingEntry[${ctr}].financialObjectTypeCode" write="true"/>
					</kul:inquiry>
				</td>
				<td class="datacell center"><html:hidden property="document.generalLedgerPendingEntry[${ctr}].transactionLedgerEntryAmount" write="true"/></td>
				<td class="datacell center"><html:hidden property="document.generalLedgerPendingEntry[${ctr}].transactionDebitCreditCode" write="true"/>&nbsp;</td>
			</tr>
		</logic:iterate>
	</c:if>
	</table>
</div>
</kul:tab>