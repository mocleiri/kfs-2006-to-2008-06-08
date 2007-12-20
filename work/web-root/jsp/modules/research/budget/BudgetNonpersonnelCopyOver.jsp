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

<kul:documentPage showDocumentInfo="true"
	documentTypeName="KualiBudgetDocument"
	headerTitle="Research Administration - Non-Personnel Copy Over"
	htmlFormAction="researchBudgetNonpersonnelCopyOver"
	headerTabActive="nonpersonnel" showTabButtons="true"
	feedbackKey="app.feedback.link">

	<kul:errors keyMatch="${Constants.DOCUMENT_ERRORS}" />

	<kra-b:budgetHiddenDocumentFields includeDocumenHeaderIdFields="true"	includeTaskPeriodLists="true" />
  <html:hidden property="budgetDocument.budget.budgetProjectDirectorUniversalIdentifier" />
  

	<kra-b:budgetNonpersonnelCopyOver />

	<div id="globalbuttons" class="globalbuttons" align="center"><c:if test="${!KualiForm.editingMode['viewOnly']}"><html:image
		src="${ConfigProperties.externalizable.images.url}buttonsmall_return.gif" styleClass="globalbuttons"
		property="methodToCall.returnNonpersonnel" alt="return" /></c:if> <html:image
		src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_cancel.gif" styleClass="globalbuttons"
		property="methodToCall.cancel" alt="cancel" /></div>

</kul:documentPage>
