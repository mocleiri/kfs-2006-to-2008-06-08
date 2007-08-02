<%-- 
 Copyright 2006 The Kuali Foundation.
 
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

<div class="h3-container">
	<h3>Money In</h3>
</div>
<kul:htmlControlAttribute property="document.currentTransaction.workgroupName" attributeEntry="${DataDictionary.CashieringTransaction.attributes.workgroupName}" />
<kul:htmlControlAttribute property="document.currentTransaction.referenceFinancialDocumentNumber" attributeEntry="${DataDictionary.CashieringTransaction.attributesreferenceFinancialDocumentNumber}" />
<%-- <kul:htmlControlAttribute property="document.currentTransaction.transactionStarted" attributeEntry="${DataDictionary.CashieringTransaction.attributes.transactionStarted}" /> --%>
<fin:currencyCoinLine currencyProperty="document.currentTransaction.moneyInCurrency" coinProperty="document.currentTransaction.moneyOutCoin" editingMode="${KualiForm.editingMode}" />
<table border="0" cellspacing="0" cellpadding="0" class="datatable">
  <tr>
    <th colspan="3">
      New Misc. Advance
    </th>
  </tr>
  <cm:miscAdvanceHeader itemInProcessProperty="document.currentTransaction.newItemInProcess" creatingItemInProcess="true" />
  <cm:miscAdvanceLine itemInProcessProperty="document.currentTransaction.newItemInProcess" creatingItemInProcess="true" />
</table>
<cm:checkLines checkDetailMode="true" editingMode="${KualiForm.editingMode}" displayHidden="false" />

<div class="h3-container">
	<h3>Money Out</h3>
</div>
<fin:currencyCoinLine currencyProperty="document.currentTransaction.moneyOutCurrency" coinProperty="document.currentTransaction.moneyInCoin" editingMode="${KualiForm.editingMode}" />
<cm:openMiscAdvanceLines />
