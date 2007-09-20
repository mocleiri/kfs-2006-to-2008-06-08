<%--
 Copyright 2006-2007 The Kuali Foundation.
 
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

<c:set var="balanceInquiryAttributes"
	value="${DataDictionary.LedgerBalanceForBenefitExpenseTransfer.attributes}" />
	
<c:set var="readOnly"
	value="${!empty KualiForm.editingMode['viewOnly']}" />
	
<c:if test="${fn:length(KualiForm.document.sourceAccountingLines)>0 || readOnly}">
	<c:set var="disabledSourceLines" value="true"/>
</c:if>

<c:if test="${fn:length(KualiForm.document.targetAccountingLines)>0 || readOnly}">
	<c:set var="disabledTargetLines" value="true"/>
</c:if>

<kul:documentPage showDocumentInfo="true"
    documentTypeName="KualiYearEndSalaryExpenseTransferDocument"
    htmlFormAction="laborYearEndSalaryExpenseTransfer" renderMultipart="true"
    showTabButtons="true">

    <html:hidden property="financialBalanceTypeCode" />
    <kul:hiddenDocumentFields />
    <kul:documentOverview editingMode="${KualiForm.editingMode}" />
 
	<kul:tab tabTitle="Ledger Balance Importing" defaultOpen="true"
		tabErrorKey="${Constants.EMPLOYEE_LOOKUP_ERRORS}">
		<div class="tab-container" align=center>
		<div class="h2-container">
		<h2>Ledger Balance Importing</h2>
		</div>
		<table cellpadding="0" cellspacing="0" class="datatable" summary="Ledger Balance Importing">

			<tr>
				<kul:htmlAttributeHeaderCell
					attributeEntry="${balanceInquiryAttributes.universityFiscalYear}"
					horizontal="true" width="35%"  forceRequired="true"/>

				<td class="datacell-nowrap"><kul:htmlControlAttribute
					attributeEntry="${balanceInquiryAttributes.universityFiscalYear}"
					property="universityFiscalYear" readOnly="${readOnly}" /> 
					
					<c:if test="${!readOnly}">
						<kul:lookup	boClassName="org.kuali.kfs.bo.Options"
						lookupParameters="universityFiscalYear:universityFiscalYear"
						fieldLabel="${balanceInquiryAttributes.universityFiscalYear.label}" />
					</c:if>
				</td>
			</tr>			

             <tr>
               <kul:htmlAttributeHeaderCell
                   attributeEntry="${DataDictionary.UniversalUser.attributes.personPayrollIdentifier}"
                   horizontal="true"
                   forceRequired="true"/>
               <td>
                     <ld:employee userIdFieldName="emplid" 
                                 userNameFieldName="user.personName" 
                                 fieldConversions="personPayrollIdentifier:emplid"
                                 lookupParameters="emplid:personPayrollIdentifier,universityFiscalYear:universityFiscalYear"
                                 hasErrors="${hasErrors}"
                                 onblur="${onblur}"
                                 highlight="${addHighlighting}" readOnly="${disabled}" >
                     </ld:employee>
               </td>
             </tr>
            
            <tr>
            	<td height="30" class="infoline">&nbsp;</td>
            	<td height="30" class="infoline">
            		<c:if test="${!readOnly}">
	                   <gl:balanceInquiryLookup
	                       boClassName="org.kuali.module.labor.bo.LedgerBalanceForSalaryExpenseTransfer"
	                       actionPath="glBalanceInquiryLookup.do"
	                       lookupParameters="universityFiscalYear:universityFiscalYear,emplid:emplid,financialBalanceTypeCode:financialBalanceTypeCode"
	                       hideReturnLink="false" image="buttonsmall_search.gif"/>
	                </c:if>
				</td>
			</tr>
		</table>
		</div>
	</kul:tab>

      <c:set var="copyMethod" value="" scope="request"/>
      <c:set var="actionInfixVar" value="" scope="request"/>
      <c:set var="accountingLineIndexVar" value="" scope="request"/>
	<fin:accountingLines editingMode="${KualiForm.editingMode}"
		editableAccounts="${KualiForm.editableAccounts}" inherit="false" extraHiddenFields=",objectTypeCode,emplid,positionNumber,balanceTypeCode,payrollTotalHours"
		optionalFields="positionNumber,payrollEndDateFiscalYear,payrollEndDateFiscalPeriodCode,payrollTotalHours">

      <jsp:attribute name="groupsOverride">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="datatable">
        <fin:subheadingWithDetailToggleRow
            columnCount="${columnCount}"
             subheading="Accounting Lines"/>
             
        <ld:importedAccountingLineGroup
            isSource="true"
            columnCountUntilAmount="${columnCountUntilAmount}"
            columnCount="${columnCount}"
            optionalFields="${optionalFieldsMap}"
            extraRowFields="${extraSourceRowFieldsMap}"
            editingMode="${KualiForm.editingMode}"
            editableAccounts="${editableAccountsMap}"
            editableFields="${KualiForm.accountingLineEditableFields}"
            debitCreditAmount="${debitCreditAmountString}"
            currentBaseAmount="${currentBaseAmountString}"
            extraHiddenFields="${extraHiddenFieldsMap}"
            useCurrencyFormattedTotal="${useCurrencyFormattedTotalBoolean}"
            includeObjectTypeCode="${includeObjectTypeCodeBoolean}"
            displayMonthlyAmounts="${displayMonthlyAmountsBoolean}"
            forcedReadOnlyFields="${KualiForm.forcedReadOnlySourceFields}"
            accountingLineAttributes="${accountingLineAttributesMap}">
            <jsp:attribute name="importRowOverride">
            <%-- When data exists show the copy or delete buttons --%>
            <c:if test="${disabledSourceLines}">
                <html:image property="methodToCall.copyAllAccountingLines" 
                	src="${ConfigProperties.externalizable.images.url}tinybutton-copyall.gif" 
                	title="Copy all Source Accounting Lines" 
                	alt="Copy all Source Lines" styleClass="tinybutton"/>
   			    <html:image property="methodToCall.deleteAllSourceAccountingLines"
					src="${ConfigProperties.externalizable.images.url}tinybutton-deleteall.gif"
					title="Delete all Source Accounting Lines"
					alt="Delete all Source Lines" styleClass="tinybutton" />
			 </c:if>
            </jsp:attribute>
            <jsp:attribute name="customActions">
                <c:set var="copyMethod" value="copyAccountingLine.line${accountingLineIndexVar}" scope="request" />
                <html:image property="methodToCall.${copyMethod}.anchoraccounting${actionInfixVar}Anchor" src="${ConfigProperties.externalizable.images.url}tinybutton-copy2.gif" title="Copy an Accounting Line" alt="Copy an Accounting Line" styleClass="tinybutton"/>
            </jsp:attribute>
        </ld:importedAccountingLineGroup>

        <ld:importedAccountingLineGroup
            isSource="false"
            columnCountUntilAmount="${columnCountUntilAmount}"
            columnCount="${columnCount}"
            optionalFields="${optionalFieldsMap}"
            extraRowFields="${extraTargetRowFieldsMap}"
            editingMode="${KualiForm.editingMode}"
            editableAccounts="${editableAccountsMap}"
            editableFields="${editableFieldsMap}"
            debitCreditAmount="${debitCreditAmountString}"
            currentBaseAmount="${currentBaseAmountString}"
            forcedReadOnlyFields="${KualiForm.forcedReadOnlyTargetFields}"
            extraHiddenFields="${extraHiddenFieldsMap}"
            useCurrencyFormattedTotal="${useCurrencyFormattedTotalBoolean}"
            includeObjectTypeCode="${includeObjectTypeCodeBoolean}"
            displayMonthlyAmounts="${displayMonthlyAmountsBoolean}"
            accountingLineAttributes="${accountingLineAttributesMap}">
            <jsp:attribute name="importRowOverride">
            <%-- When data exists show the delete button --%>
            <c:if test="${disabledTargetLines}">
   			    <html:image property="methodToCall.deleteAllTargetAccountingLines"
					src="${ConfigProperties.externalizable.images.url}tinybutton-deleteall.gif"
					title="Delete all Target Accounting Lines"
					alt="Delete all Target Lines" styleClass="tinybutton" />
			 </c:if>            
            </jsp:attribute>
         </ld:importedAccountingLineGroup>
         
      </table>
      </jsp:attribute>
    </fin:accountingLines>
    <ld:laborLedgerPendingEntries />
    <kul:notes />
    <kul:adHocRecipients />
    <kul:routeLog />
    <kul:panelFooter />
    <kul:documentControls transactionalDocument="true" />
</kul:documentPage>
