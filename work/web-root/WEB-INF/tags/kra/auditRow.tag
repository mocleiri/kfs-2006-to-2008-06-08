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
<%@ taglib prefix="c" uri="/tlds/c.tld" %>
<%@ taglib uri="/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://rice.kuali.org/tagfiles/core" prefix="kul"%>
<%@ taglib tagdir="/WEB-INF/tags/kra" prefix="kra" %>

<%@ attribute name="tabTitle" required="true" %>
<%@ attribute name="defaultOpen" required="true" %>
<%@ attribute name="totalErrors" required="true" %>

<c:set var="currentTabIndex" value="${KualiForm.currentTabIndex}"/>
<c:set var="currentTab" value="${KualiForm.tabStateJstl}"/>

<c:choose>
    <c:when test="${empty currentTab}">
        <c:set var="isOpen" value="${defaultOpen}" />
    </c:when>
    <c:when test="${!empty currentTab}" >
        <c:set var="isOpen" value="${currentTab.open}" />
    </c:when>
</c:choose>

<html:hidden property="tabState[${currentTabIndex}].open" value="${isOpen}" />

<!-- ROW -->

<tbody>
    <tr>
	    <td class="tab-subhead">
	      	<c:if test="${isOpen == 'true' || isOpen == 'TRUE'}">
	            <html:image property="methodToCall.toggleTab.tab${currentTabIndex}" src="images/tinybutton-hide.gif" alt="hide" styleClass="tinybutton" styleId="tab-${currentTabIndex}-imageToggle" onclick="javascript: return toggleTab(document, ${currentTabIndex}); " />
	        </c:if>
	        <c:if test="${isOpen != 'true' && isOpen != 'TRUE'}">
	            <html:image property="methodToCall.toggleTab.tab${currentTabIndex}" src="images/tinybutton-show.gif" alt="show" styleClass="tinybutton" styleId="tab-${currentTabIndex}-imageToggle" onclick="javascript: return toggleTab(document, ${currentTabIndex}); " />
	        </c:if>
	    </td>
	    <td colspan="3" class="tab-subhead" width="99%"><b>${tabTitle} (${totalErrors})</b></td>
    </tr>
</tbody>

<c:if test="${isOpen == 'true' || isOpen == 'TRUE'}">
	<tbody style="display: ;" id="tab-${currentTabIndex}-div">
</c:if>
<c:if test="${isOpen != 'true' && isOpen != 'TRUE'}">
	<tbody style="display: none;" id="tab-${currentTabIndex}-div">
</c:if>

<!-- Before the jsp:doBody of the kul:tab tag -->
<jsp:doBody/>
<!-- After the jsp:doBody of the kul:tab tag -->

</tbody>