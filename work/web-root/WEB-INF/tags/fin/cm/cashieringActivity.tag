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

<c:set var="isDrawerOpen" value="${(KualiForm.editingMode[AuthorizationConstants.EditMode.FULL_ENTRY]) && (KualiForm.document.rawCashDrawerStatus == Constants.CashDrawerConstants.STATUS_OPEN)}" />

<c:if test="${isDrawerOpen}">
	<kul:tab tabTitle="Cashiering Transactions" defaultOpen="true" tabErrorKey="${Constants.CashManagementConstants.CASH_MANAGEMENT_ERRORS}" >
		<div class="tab-container" align="center">
			<cm:cashieringTransaction />
      <p style="padding: 10px">
        <html:image src="${ConfigProperties.externalizable.images.url}buttonsmall_applytrans.gif" style="border: none" property="methodToCall.applyCashieringTransaction" title="Apply Cashiering Transaction" alt="Apply Cashiering Transaction" />
      </p>
		</div>
	</kul:tab>
</c:if>
