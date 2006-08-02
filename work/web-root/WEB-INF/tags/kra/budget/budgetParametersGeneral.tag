<%@ taglib prefix="c" uri="/tlds/c.tld" %>
<%@ taglib prefix="fn" uri="/tlds/fn.tld"%>
<%@ taglib uri="/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="kul" %>
<%@ taglib tagdir="/WEB-INF/tags/dd" prefix="dd" %>
<%@ taglib tagdir="/WEB-INF/tags/kra" prefix="kra" %>
<%@ taglib tagdir="/WEB-INF/tags/kra/budget" prefix="kra-b" %>

<%@ attribute name="supportsModular" required="true" %>

<c:set var="budgetAttributes" value="${DataDictionary.Budget.attributes}" />
<c:set var="businessObjectClass" value="${DataDictionary.Budget.businessObjectClass}" />
<c:set var="viewOnly" value="${KualiForm.editingMode['viewOnly']}"/>

<script type="text/javascript">
	function modularVarianceToggle() {
		var agencyModularIndicator = document.forms[0].elements['document.budget.agencyModularIndicator'].checked;
		var budgetModularTaskNumber = document.forms[0].elements['document.budget.modularBudget.budgetModularTaskNumber'];
		
		if (budgetModularTaskNumber.length >= 1) {
		    for (var i = 1; i < budgetModularTaskNumber.length; i++) {
		     	budgetModularTaskNumber[i].disabled = !agencyModularIndicator;
		    }
			budgetModularTaskNumber[1].checked = agencyModularIndicator;
		}
	}
</script>

<div class="h2-container"> <span class="subhead-left">
  <a name="General"></a><h2>General</h2>
  </span><span class="subhead-right"> <span class="subhead"><kul:help businessObjectClassName="${businessObjectClass}" altText="help"/></span> </span> </div>
  <table cellpadding="0" cellspacing="0" class="datatable" summary=""> 
  
  <tr>
    <th scope="row" ><div align="right">* ${budgetAttributes.budgetProjectDirectorSystemId.label}:</div></th>
    <td>
    	<html:hidden property="document.budget.projectDirectorToBeNamedIndicator"/>
    	<html:hidden property="document.budget.budgetProjectDirectorSystemId" />
    	<html:hidden write="true" property="document.budget.projectDirector.universalUser.personName"/>
    	<c:if test="${empty KualiForm.document.budget.budgetProjectDirectorSystemId && !KualiForm.document.budget.projectDirectorToBeNamedIndicator}">(select)</c:if>
    	<c:if test="${KualiForm.document.budget.projectDirectorToBeNamedIndicator}">TO BE NAMED</c:if>
    	<c:if test="${!viewOnly}">
	    	<kul:lookup boClassName="org.kuali.module.cg.bo.ProjectDirector" fieldConversions="universalUser.personUniversalIdentifier:document.budget.budgetProjectDirectorSystemId,universalUser.personName:document.budget.projectDirector.universalUser.personName" tabindexOverride="5000" extraButtonSource="images/buttonsmall_namelater.gif" extraButtonParams="&document.budget.projectDirectorToBeNamedIndicator=true" anchor="General" />
    	</c:if>
    </td>
    <th scope="row" ><div align="right">* ${budgetAttributes.budgetPersonnelInflationRate.label}:</div></th>
    <td  nowrap="nowrap"><kul:htmlControlAttribute property="document.budget.budgetPersonnelInflationRate" attributeEntry="${budgetAttributes.budgetPersonnelInflationRate}" readOnly="${viewOnly}" tabindexOverride="5080" /> % (Range: 0 - 11.00)</td>
  </tr>
  
  <tr>
    <th scope="row" ><div align="right">${budgetAttributes.budgetName.label}:</div></th>
    <td><kul:htmlControlAttribute property="document.budget.budgetName" attributeEntry="${budgetAttributes.budgetName}" readOnly="${viewOnly}" tabindexOverride="5010" /></td>
    <th scope="row" ><div align="right">* ${budgetAttributes.budgetNonpersonnelInflationRate.label}:</div></th>
    <td  nowrap="nowrap"><kul:htmlControlAttribute property="document.budget.budgetNonpersonnelInflationRate" attributeEntry="${budgetAttributes.budgetNonpersonnelInflationRate}" readOnly="${viewOnly}" tabindexOverride="5090" /> % (Range: 0 - 11.00)</td>
  </tr>
  
  <tr>
    <th scope="row" ><div align="right">${budgetAttributes.budgetProgramAnnouncementName.label}:</div></th>
    <td><kul:htmlControlAttribute property="document.budget.budgetProgramAnnouncementName" attributeEntry="${budgetAttributes.budgetProgramAnnouncementName}" readOnly="${viewOnly}" tabindexOverride="5020" /></td>
    <th scope="row" ><div align="right">* ${budgetAttributes.budgetAgency.label}:</div></th>
    <td>
    	<html:hidden property="document.budget.agencyToBeNamedIndicator" />
    	<html:hidden property="document.budget.budgetAgencyNumber" /> 
    	<html:hidden write="true" property="document.budget.budgetAgency.fullName"/>
    	<c:if test="${empty KualiForm.document.budget.budgetAgencyNumber && !KualiForm.document.budget.agencyToBeNamedIndicator}">(select)</c:if>
    	<c:if test="${KualiForm.document.budget.agencyToBeNamedIndicator}">TO BE NAMED</c:if>
    	<c:if test="${!viewOnly}">
    		<kul:lookup boClassName="org.kuali.module.cg.bo.Agency" lookupParameters="document.budget.budgetAgencyNumber:agencyNumber,document.budget.budgetAgency.fullName:fullName" fieldConversions="agencyNumber:document.budget.budgetAgencyNumber,fullName:document.budget.budgetAgency.fullName" tabindexOverride="5100" extraButtonSource="images/buttonsmall_namelater.gif" extraButtonParams="&document.budget.agencyToBeNamedIndicator=true" anchor="General" />
    	</c:if>
    </td>
  </tr>
  
  <tr>
    <th scope="row" ><div align="right">${budgetAttributes.budgetProgramAnnouncementNumber.label}:</div></th>
    <td><kul:htmlControlAttribute property="document.budget.budgetProgramAnnouncementNumber" attributeEntry="${budgetAttributes.budgetProgramAnnouncementNumber}" readOnly="${viewOnly}" tabindexOverride="5030" /></td>
    <th scope="row" ><div align="right">${budgetAttributes.federalPassThroughAgency.label}:</div></th>
    <td>
    	<html:hidden property="document.budget.federalPassThroughAgencyNumber" />
    	<html:hidden write="true" property="document.budget.federalPassThroughAgency.fullName"/>
    	<c:if test="${empty KualiForm.document.budget.federalPassThroughAgencyNumber}">(select)</c:if>
    	<c:if test="${!viewOnly}">    	
    		<kul:lookup boClassName="org.kuali.module.cg.bo.Agency" fieldConversions="agencyNumber:document.budget.federalPassThroughAgencyNumber,fullName:document.budget.federalPassThroughAgency.fullName" tabindexOverride="5110" anchor="General" />
    	</c:if>
    </td>
  </tr>
  
  <tr>
    <th scope="row" ><div align="right">${budgetAttributes.electronicResearchAdministrationGrantNumber.label}:</div></th>
    <td><kul:htmlControlAttribute property="document.budget.electronicResearchAdministrationGrantNumber" attributeEntry="${budgetAttributes.electronicResearchAdministrationGrantNumber}" readOnly="${viewOnly}" tabindexOverride="5040" /></td>
    <th scope="row" ><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.universityCostShareIndicator}" labelFor="document.budget.universityCostShareIndicator" skipHelpUrl="true" /></div></th>
    <td><kul:htmlControlAttribute property="document.budget.universityCostShareIndicator" attributeEntry="${budgetAttributes.universityCostShareIndicator}" readOnly="${viewOnly}" tabindexOverride="5120"/>
      <label for="document.budget.universityCostShareIndicator"> include</label></td>
  </tr>
  
  <tr>
    <th scope="row" ><div align="right">* Budget Type:</div></th>
    <td>
    	<logic:iterate id="budgetType" name="KualiForm" property="budgetTypeCodes" indexId="i"> 
    		<html:hidden property="budgetTypeCode[${i}].budgetTypeCode" /> 
    		<html:hidden property="budgetTypeCode[${i}].budgetTypeDescription" />
    		<c:choose>
    			<c:when test="${not viewOnly}">
		    		<html:multibox name="KualiForm" property="document.budget.budgetTypeCodeArray"> ${budgetType.budgetTypeCode} </html:multibox> ${budgetType.budgetTypeDescription} <br/>
    			</c:when>
    			<c:otherwise>
    				<c:choose>
    					<c:when test="${fn:contains(KualiForm.document.budget.budgetTypeCodeText, budgetType.budgetTypeCode)}"> Yes </c:when>
    					<c:otherwise> No </c:otherwise>
    				</c:choose>
    				${budgetType.budgetTypeDescription} <br/>
    			</c:otherwise>
    		</c:choose>
      	</logic:iterate> 
      		
      <kul:htmlControlAttribute property="document.budget.agencyModularIndicator" attributeEntry="${budgetAttributes.agencyModularIndicator}" readOnly="${viewOnly}" onclick="modularVarianceToggle(); " disabled="${!supportsModular}" tabindexOverride="5070"/><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.agencyModularIndicator}" labelFor="document.budget.agencyModularIndicator" useShortLabel="true" skipHelpUrl="true" noColon="true" />
      <html:hidden property="supportsModular" />
    </td>
    <th scope="row" ><div align="right"><kul:htmlAttributeLabel attributeEntry="${budgetAttributes.budgetThirdPartyCostShareIndicator}" labelFor="document.budget.budgetThirdPartyCostShareIndicator" skipHelpUrl="true" /></div></th>
    <td><kul:htmlControlAttribute property="document.budget.budgetThirdPartyCostShareIndicator" attributeEntry="${budgetAttributes.budgetThirdPartyCostShareIndicator}" readOnly="${viewOnly}" tabindexOverride="5130"/>
      <label for="document.budget.budgetThirdPartyCostShareIndicator"> include</label></td>
  </tr>
  
  </tbody>
</table>
<div align="right">*required&nbsp;&nbsp;&nbsp;<br>
  <br>
</div>