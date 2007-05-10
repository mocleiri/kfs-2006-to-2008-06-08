<%--
 Copyright 2005-2007 The Kuali Foundation.
 
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
<%@ taglib prefix="c" uri="/tlds/c.tld"%>
<%@ taglib prefix="fn" uri="/tlds/fn.tld"%>
<%@ taglib prefix="kul" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fin" tagdir="/WEB-INF/tags/fin"%>
<%@ taglib uri="/tlds/struts-html.tld" prefix="html"%>

<%@ attribute name="optionalFields" required="false"
	description="A comma separated list of names of accounting line fields
              to be appended to the required field columns, before the amount column.
              The optional columns appear in both source and target groups
              of accounting lines."%>
<%@ attribute name="isOptionalFieldsInNewRow" required="false"
	type="java.lang.Boolean"
	description="indicate if the oprtional fields are put in a new row under the default accouting line"%>

<%@ attribute name="sourceAccountingLinesOnly" required="false"
	description="A boolean controling whether the group of
              target accounting lines will be generated.
              Null or empty is the same as false."%>
<%@ attribute name="extraSourceRowFields" required="false"
	description="A comma seperated list of names of any non-standard fields
              required on the source accounting lines of this eDoc.
              See accountingLineRow.tag for details."%>
<%@ attribute name="extraTargetRowFields" required="false"
	description="A comma seperated list of names of any non-standard fields
              required on the target accounting lines of this eDoc.
              See accountingLineRow.tag for details."%>
<%@ attribute name="editingMode" required="false" type="java.util.Map"%>
<%@ attribute name="editableAccounts" required="true"
	type="java.util.Map"
	description="Map of Accounts which this user is allowed to edit"%>
<%@ attribute name="editableFields" required="false"
	type="java.util.Map"
	description="Map of accounting line fields which this user is allowed to edit"%>
<%@ attribute name="useCurrencyFormattedTotal" required="false"
	description="boolean indicating that the form's currency formatted total
              should be displayed instead of the document's source or target total.
              As with all boolean tag attributes, if it is not provided, it defaults to false."%>

<%@ attribute name="includeObjectTypeCode" required="false"
	description="boolean indicating that the object type code column should be displayed.
              As with all boolean tag attributes, if it is not provided, it defaults to false."%>

<%@ attribute name="debitCreditAmount" required="false"
	description="boolean whether the amount column is displayed as
              separate debit and credit columns, and the totals as the form's
              currency formatted debit and credit totals.
              As with all boolean tag attributes, if it is not provided, it defaults to false."%>

<%@ attribute name="currentBaseAmount" required="false"
	description="boolean whether the amount column is displayed as
              separate debit and credit columns, and the totals as the form's
              currency formatted debit and credit totals.
              As with all boolean tag attributes, if it is not provided, it defaults to false."%>

<%@ attribute name="extraHiddenFields" required="false"
	description="A comma seperated list of names of any accounting line fields
              that should be added to the list of normally hidden fields
              for the existing (but not the new) accounting lines."%>

<%@ attribute name="displayMonthlyAmounts" required="false"
	description="A boolean whether the monthy amounts table is displayed
              below each accounting line (needed for budget adjustment document).
              As with all boolean tag attributes, if it is not provided, it defaults to false."%>

<%@ attribute name="forcedReadOnlyFields" required="false"
	type="java.util.Map"
	description="map containing accounting line field names that should be marked as read only."%>

<%@ attribute name="accountingLineAttributes" required="false"
	type="java.util.Map"
	description="A parameter to specify an data dictionary entry for a sub-classed accounting line."%>

<%@ attribute name="inherit" required="false" type="java.lang.Boolean"
	description="Should the default Financial Transactions Accounting Line tags be used?"%>
<%@ attribute name="groupsOverride" required="false" fragment="true"
	description="Fragment of code to override the default accountingline groups"%>
<%@ attribute name="accountPrefix" required="false"
	description="an optional prefix to specify a different location for acocunting lines rather
              than just on the document."%>
<%@ attribute name="hideTotalLine" required="false"
	type="java.lang.Boolean"
	description="an optional attribute to hide the total line."%>
<%@ attribute name="hideFields" required="false"
	description="comma delimited list of fields to hide for this type of accounting line"%>
<%@ attribute name="accountingAddLineIndex" required="false"
	description="index for multiple add new source lines"%>

<c:if test="${!accountingLineScriptsLoaded}">
	<script type='text/javascript' src="dwr/interface/ChartService.js"></script>
	<script type='text/javascript' src="dwr/interface/AccountService.js"></script>
	<script type='text/javascript' src="dwr/interface/SubAccountService.js"></script>
	<script type='text/javascript' src="dwr/interface/ObjectCodeService.js"></script>
	<script type='text/javascript' src="dwr/interface/ObjectTypeService.js"></script>
	<script type='text/javascript'
		src="dwr/interface/SubObjectCodeService.js"></script>
	<script type='text/javascript'
		src="dwr/interface/ProjectCodeService.js"></script>
	<script type='text/javascript'
		src="dwr/interface/OriginationCodeService.js"></script>
	<script type='text/javascript'
		src="dwr/interface/DocumentTypeService.js"></script>
	<script language="JavaScript" type="text/javascript"
		src="scripts/kfs/objectInfo.js"></script>
	<c:set var="accountingLineScriptsLoaded" value="true" scope="page" />
</c:if>

<c:forEach items="${editableAccounts}" var="account">
	<html:hidden property="editableAccounts(${account.key})"
		value="${account.key}" />
</c:forEach>

<c:forEach items="${editableFields}" var="field">
	<html:hidden property="accountingLineEditableFields(${field.key})" />
</c:forEach>

<c:set var="optionalFieldCount"
	value="${empty optionalFields ? 0 : fn:length(fn:split(optionalFields, ' ,'))}" />
<c:set var="columnCountUntilAmount"
	value="${8
                                        + (includeObjectTypeCode ? 1 : 0)
                                        + (isOptionalFieldsInNewRow ? 0 : optionalFieldCount)}" />

<c:set var="arrHideFields" value="${fn:split(hideFields,',') }" />
<c:set var="numHideFields" value="${fn:length(numHideFields) }" />
<%-- add extra columns count for the "Action" button and/or dual amounts --%>
<c:set var="columnCount"
	value="${columnCountUntilAmount
                                        + (debitCreditAmount || currentBaseAmount ? 2 : 1)
                                        - (not empty hideFields ? 0 : numHideFields)
                                        + (empty editingMode['viewOnly'] ? 1 : 0)}" />

<%@ include
	file="/WEB-INF/tags/fin/accountingLinesVariablesOverride.tag"%>

<c:set var="currentTabIndex" value="${KualiForm.currentTabIndex}"
	scope="request" />
<c:set var="topLevelTabIndex" value="${KualiForm.currentTabIndex}"
	scope="request" />

<c:set var="currentTab" value="${KualiForm.tabStateJstl}" />

<%-- default to closed --%>
<c:choose>
	<c:when test="${empty currentTab}">
		<c:set var="isOpen" value="false" />
	</c:when>
	<c:when test="${!empty currentTab}">
		<c:set var="isOpen" value="${currentTab.open}" />
	</c:when>
</c:choose>


<html:hidden property="tabState[${currentTabIndex}].open"
	value="${isOpen}" />

<tr>
	<td class="total-line" colspan="${columnCount}" style="padding: 0px;">
	<table class="datatable" style="width: 100%;">
		<tr>
			<td colspan="4" class="tab-subhead" style="border-right: none;">Item
			Accounting Lines & CAMS <c:if test="${isOpen == 'true' || isOpen == 'TRUE'}">
				<html:image property="methodToCall.toggleTab.tab${currentTabIndex}"
					src="images/tinybutton-hide.gif" alt="hide" title="toggle"
					styleClass="tinybutton"
					styleId="tab-${currentTabIndex}-imageToggle"
					onclick="javascript: return toggleTab(document, ${currentTabIndex}); " />
			</c:if> <c:if test="${isOpen != 'true' && isOpen != 'TRUE'}">
				<html:image property="methodToCall.toggleTab.tab${currentTabIndex}"
					src="images/tinybutton-show.gif" alt="show" title="toggle"
					styleClass="tinybutton"
					styleId="tab-${currentTabIndex}-imageToggle"
					onclick="javascript: return toggleTab(document, ${currentTabIndex}); " />
			</c:if></td>
		</tr>
	</table>

	<c:if test="${isOpen == 'true' || isOpen == 'TRUE'}">
		<div style="display: block;" id="tab-${currentTabIndex}-div">
	</c:if> <c:if test="${isOpen != 'true' && isOpen != 'TRUE'}">
		<div style="display: none;" id="tab-${currentTabIndex}-div">
	</c:if> <c:if test="${empty inherit || inherit == true}">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="datatable">
			<fin:subheadingWithDetailToggleRow columnCount="${columnCount}"
				subheading="Accounting Lines" />
			<fin:accountingLineGroup isSource="true"
				columnCountUntilAmount="${columnCountUntilAmount}"
				columnCount="${columnCount}" optionalFields="${optionalFields}"
				isOptionalFieldsInNewRow="${isOptionalFieldsInNewRow}"
				extraRowFields="${extraSourceRowFields}"
				editingMode="${editingMode}" editableAccounts="${editableAccounts}"
				editableFields="${editableFields}"
				debitCreditAmount="${debitCreditAmount}"
				currentBaseAmount="${currentBaseAmount}"
				extraHiddenFields="${extraHiddenFields}"
				useCurrencyFormattedTotal="${useCurrencyFormattedTotal}"
				includeObjectTypeCode="${includeObjectTypeCode}"
				displayMonthlyAmounts="${displayMonthlyAmounts}"
				forcedReadOnlyFields="${forcedReadOnlyFields}"
				accountingLineAttributes="${accountingLineAttributes}"
				accountPrefix="${accountPrefix}" hideTotalLine="${hideTotalLine}"
				hideFields="${hideFields}"
				accountingAddLineIndex="${accountingAddLineIndex}" />
			<c:if test="${!sourceAccountingLinesOnly}">
				<fin:accountingLineGroup isSource="false"
					columnCountUntilAmount="${columnCountUntilAmount}"
					columnCount="${columnCount}" optionalFields="${optionalFields}"
					isOptionalFieldsInNewRow="${isOptionalFieldsInNewRow}"
					extraRowFields="${extraTargetRowFields}"
					editingMode="${editingMode}" editableAccounts="${editableAccounts}"
					editableFields="${editableFields}"
					debitCreditAmount="${debitCreditAmount}"
					currentBaseAmount="${currentBaseAmount}"
					extraHiddenFields="${extraHiddenFields}"
					useCurrencyFormattedTotal="${useCurrencyFormattedTotal}"
					includeObjectTypeCode="${includeObjectTypeCode}"
					displayMonthlyAmounts="${displayMonthlyAmounts}"
					forcedReadOnlyFields="${forcedReadOnlyFields}"
					accountingLineAttributes="${accountingLineAttributes}"
					accountPrefix="${accountPrefix}" hideTotalLine="${hideTotalLine}"
					hideFields="${hideFields}" />
			</c:if>
		</table>
	</c:if> <c:if test="${!empty groupsOverride}">
		<jsp:invoke fragment="groupsOverride" />
	</c:if> <SCRIPT type="text/javascript">
    var kualiForm = document.forms['KualiForm'];
    var kualiElements = kualiForm.elements;
  </SCRIPT>
	</div>
	</td>
</tr>
