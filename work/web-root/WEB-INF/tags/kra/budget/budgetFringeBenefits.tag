<%@ taglib prefix="c" uri="/tlds/c.tld" %>
<%@ taglib uri="/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="kul" %>
<%@ taglib tagdir="/WEB-INF/tags/dd" prefix="dd" %>
<%@ taglib tagdir="/WEB-INF/tags/kra" prefix="kra" %>
<%@ taglib tagdir="/WEB-INF/tags/kra/budget" prefix="kra-b" %>

<c:set var="budgetAttributes" value="${DataDictionary.Budget.attributes}" /> <c:set var="budgetFringeRateAttributes" value="${DataDictionary.BudgetFringeRate.attributes}" /> <c:set var="budgetGraduateFringeRateAttributes" value="${DataDictionary.BudgetGraduateAssistantRate.attributes}" />
<c:set var="businessObjectClass" value="${DataDictionary.BudgetFringeRate.businessObjectClass}" />
<c:set var="viewOnly" value="${KualiForm.editingMode['viewOnly']}"/>

<div class="h2-container"> <span class="subhead-left">
  <a name="Fringes"></a><h2>Fringe Benefit Rates</h2>
  </span><span class="subhead-right"><kul:help businessObjectClassName="${businessObjectClass}" altText="help"/></span> </div>
<table cellpadding="0" cellspacing="0" class="datatable" summary=""> 
  <tr>
    <th rowspan="2"><div align="left">Position (Code) </div></th>
    <td colspan="2" class="tab-subhead"><div align="center">Contracts &amp; Grants</div></td>
    <th width="20" rowspan="2">&nbsp;</th>
    <td colspan="2" class="tab-subhead"><div align="center">Cost Share</div></td>
  </tr>
  <tr>
    <th width="90">System Rate</th>
    <th width="95">* Current Rate</th>
    <th width="90">System Rate</th>
    <th width="95">* Current Rate</th>
  </tr>
  <!---  START TO ITERATE OVER THE APPOINTMENTS -->
  <logic:iterate id="fringeRatesLine" name="KualiForm" property="document.budget.fringeRates" indexId="ctr">
  <tr>
    <td><html:hidden property="document.budget.fringeRate[${ctr}].documentHeaderId" /> <html:hidden property="document.budget.fringeRate[${ctr}].universityAppointmentTypeCode" /> <html:hidden property="document.budget.fringeRate[${ctr}].objectId"/> <html:hidden property="document.budget.fringeRate[${ctr}].versionNumber" /> <html:hidden property="document.budget.fringeRate[${ctr}].budgetLastUpdateTimestamp" /> <html:hidden property="document.budget.fringeRate[${ctr}].appointmentType.appointmentTypeCode" /> <html:hidden property="document.budget.fringeRate[${ctr}].appointmentType.appointmentTypeDescription" /> <html:hidden property="document.budget.fringeRate[${ctr}].appointmentType.fringeRateAmount" /> <html:hidden property="document.budget.fringeRate[${ctr}].appointmentType.costShareFringeRateAmount" /> ${fringeRatesLine.appointmentType.appointmentTypeDescription} ( ${fringeRatesLine.appointmentType.appointmentTypeCode} ) </td>
    <td><div align="center">${fringeRatesLine.appointmentType.fringeRateAmount}%</div></td>
    <td nowrap="nowrap"><div align="center"><kul:htmlControlAttribute property="document.budget.fringeRate[${ctr}].contractsAndGrantsFringeRateAmount" attributeEntry="${budgetFringeRateAttributes.contractsAndGrantsFringeRateAmount}" readOnly="${viewOnly}"/>%</div></td>
    <th width="20">&nbsp;</th>
    <td><div align="center">${fringeRatesLine.appointmentType.costShareFringeRateAmount}%</div></td>
    <td nowrap="nowrap"><div align="center"><kul:htmlControlAttribute property="document.budget.fringeRate[${ctr}].universityCostShareFringeRateAmount" attributeEntry="${budgetFringeRateAttributes.universityCostShareFringeRateAmount}" readOnly="${viewOnly}"/>%</div></td>
  </tr>
  </logic:iterate>
  <!---  END ITERATE OVER THE APPOINTMENTS -->
  <c:if test="${not viewOnly}">
  <tr align="left">
    <th>&nbsp;
      <div align="left"></div></th>
    <th height="22" colspan="2"><div align="center"><html:image property="methodToCall.copyFringeRateLines.anchorFringes" styleClass="tinybutton" src="images/tinybutton-copsysrates.gif" alt="copy fall rate"/></div></th>
    <th width="20">&nbsp;</th>
    <th height="22" colspan="2"><div align="center"><html:image property="methodToCall.copyUniversityCostShareLines.anchorFringes" styleClass="tinybutton" src="images/tinybutton-copsysrates.gif" alt="copy spring rate"/></div></th>
  </tr>
  </c:if>
</table>
<div align="right">*required&nbsp;&nbsp;&nbsp;<br>
  <br>
</div>
