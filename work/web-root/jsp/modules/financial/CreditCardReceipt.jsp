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
<%@ include file="/jsp/core/tldHeader.jsp"%>
<%@ taglib prefix="c" uri="/tlds/c.tld"%>
<%@ taglib uri="/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="kul"%>
<%@ taglib tagdir="/WEB-INF/tags/ccr" prefix="ccr"%>
<%@ taglib tagdir="/WEB-INF/tags/dd" prefix="dd"%>
<c:set var="creditCardReceiptAttributes"
	value="${DataDictionary['KualiCreditCardReceiptDocument'].attributes}" />
<c:set var="readOnly"
	value="${!empty KualiForm.editingMode['viewOnly']}" />
<kul:documentPage showDocumentInfo="true"
	htmlFormAction="financialCreditCardReceipt"
	documentTypeName="KualiCreditCardReceiptDocument"
	renderMultipart="true" showTabButtons="true">
	<kul:hiddenDocumentFields />
	<!-- Credit Card Receipt Document Specific Hidden Fields -->
	<html:hidden property="document.nextCcCrLineNumber" />
	<kul:documentOverview editingMode="${KualiForm.editingMode}" />
	<SCRIPT type="text/javascript">
	    <!--
	        function submitForm() {
	            document.forms[0].submit();
	        }
	    //-->
	</SCRIPT>
	<ccr:creditCardReceipts editingMode="${KualiForm.editingMode}" />
	<fin:accountingLines editingMode="${KualiForm.editingMode}"
		editableAccounts="${KualiForm.editableAccounts}"
		sourceAccountingLinesOnly="true"
		extraSourceRowFields="financialDocumentLineDescription" />
	<kul:generalLedgerPendingEntries />
	<kul:notes />
	<kul:adHocRecipients />
	<kul:routeLog />
	<kul:panelFooter />
	<kul:documentControls
		transactionalDocument="${documentEntry.transactionalDocument}" />
</kul:documentPage>
