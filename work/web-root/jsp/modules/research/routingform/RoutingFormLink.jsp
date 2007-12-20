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

<c:set var="routingFormDocumentAttributes" value="${DataDictionary.KualiRoutingFormDocument.attributes}" />
<c:set var="routingFormBudgetAttributes" value="${DataDictionary.RoutingFormBudget.attributes}" />


<kul:documentPage showDocumentInfo="true"
	documentTypeName="KualiRoutingFormDocument"
	htmlFormAction="researchRoutingFormLink"
	headerDispatch="save" feedbackKey="app.feedback.link"
	headerTabActive="link" showTabButtons="true">
  
  <kra-rf:routingFormHiddenDocumentFields />

	<div id="workarea" >

  <kul:tabTop tabTitle="Budget Link" defaultOpen="true" tabErrorKey="document.routingFormBudgetNumber*">
  
          <div class="tab-container" align="center">
            <div class="h2-container">
              <h2>Budget Link</h2>
            </div>
            <table cellpadding="0" cellspacing="0" summary="view/edit document overview information">
              <tr>
                <td colspan=4 class="tab-subhead"><span class="left">Select A Budget</span> </td>
              </tr>
              <tr>
                <th align=right valign=middle width="25%">Budget Document Number:</th>
                <td colspan="3" align=left valign=middle nowrap >
                  <kul:htmlControlAttribute property="document.routingFormBudgetNumber" attributeEntry="${routingFormDocumentAttributes.routingFormBudgetNumber}" onblur="budgetNameLookup('document.routingFormBudgetNumber')" />
	    		  <kul:lookup boClassName="org.kuali.module.kra.budget.bo.Budget" lookupParameters="document.routingFormBudgetNumber:documentNumber" fieldConversions="documentNumber:document.routingFormBudgetNumber,projectDirector.universalUser.personName:document.budget.projectDirector.universalUser.personName,budgetAgency.fullName:document.budget.budgetAgency.fullName" anchor="${currentTabIndex}" />
                  <html:hidden property="document.budget.documentNumber" />
                  <html:hidden property="document.budget.budgetName" />
                  <html:hidden property="document.budget.projectDirector.universalUser.personName" />
                  <html:hidden property="document.budget.budgetAgency.fullName" />
		           <div id="budgetNameDiv">
    			    <div id="document.budget.projectDirector.universalUser.personName.div" style="float: left; text-align: left;">
<!-- 
		             <c:if test="${!empty KualiForm.document.routingFormBudgetNumber}">
		                 <c:choose>
							<c:when test="${empty KualiForm.document.budget.projectDirector.universalUser.personName && empty KualiForm.document.budget.budgetAgency.fullName}">
								<span style='color: red;'><c:out value="budget document not found" /> </span>
							</c:when>
							<c:otherwise>
								PD: ${KualiForm.document.budget.projectDirector.universalUser.personName}&nbsp;&nbsp;Agency: ${KualiForm.document.budget.budgetAgency.fullName}
							</c:otherwise>
						 </c:choose>                        
		              </c:if>
		 -->
		              </div>
		           </div>
		           <!-- 
     			    <div id="document.budget.budgetAgency.fullName.div" style="float: right; text-align: right;">

		             <c:if test="${!empty KualiForm.document.routingFormBudgetNumber && !empty KualiForm.document.budget.budgetAgency.fullName}">
								&nbsp &nbspAgency: ${KualiForm.document.budget.budgetAgency.fullName}
		              </c:if>
		           </div>
		         -->
                </td>
              </tr>
              <tr>
                <th>&nbsp;</th>
                <td colspan="3" align=left valign=middle nowrap >
                  <html:image property="methodToCall.loadBudget.anchor${currentTabIndex}" styleClass="tinybutton" src="${ConfigProperties.externalizable.images.url}tinybutton-loadbud.gif" alt="load budget"/>
                  &nbsp;
                  <c:if test="${not empty KualiForm.periodBudgetOverviewFormHelpers}">
                    <html:image property="methodToCall.deleteBudget.anchor${currentTabIndex}" styleClass="tinybutton" src="${ConfigProperties.externalizable.images.url}tinybutton-delbudlink.gif" alt="delete budget link"/>
                  </c:if>
                </td>
              </tr>
              
            </table>
            <br/>

<c:if test="${not empty KualiForm.periodBudgetOverviewFormHelpers}">

            <table cellpadding="0" cellspacing="0" summary="view/edit document overview information">
              <tr>
                <td colspan=5 class="tab-subhead"><span class="left">Select Budget Periods</span> </td>
              </tr>
              <tr>
                <th class="bord-l-b">Period</th>
                <th class="bord-l-b">Direct Cost</th>
                <th class="bord-l-b">Indirect Cost</th>
                <th class="bord-l-b">Total Cost</th>
                <th class="bord-l-b">Select</th>
              </tr>

                  <c:forEach items="${KualiForm.periodBudgetOverviewFormHelpers}" var="periodBudgetOverviewFormHelper" varStatus="status">

                    <tr>
                      <td class="datacell">
                        <div class="nowrap" align="center">
                          <strong>${status.index + 1}</strong>
                          <br />
                          <span class="fineprint">${KualiForm.periodBudgetOverviewFormHelpers[status.index].budgetPeriod.budgetPeriodLabel}</span>
                        </div>
                        <html:hidden property="periodBudgetOverviewFormHelper[${status.index}].budgetPeriod.budgetPeriodBeginDate"/>
                        <html:hidden property="periodBudgetOverviewFormHelper[${status.index}].budgetPeriod.budgetPeriodEndDate"/>
                        <html:hidden property="periodBudgetOverviewFormHelper[${status.index}].budgetPeriod.budgetPeriodSequenceNumber"/>
                      </td>
                      
                      <td class="datacell">
                        <div align="right">
                          <html:hidden property="periodBudgetOverviewFormHelper[${status.index}].overviewShowModular"/>
                          <c:choose>
                            <c:when test="${periodBudgetOverviewFormHelper.overviewShowModular}">
                              <html:hidden property="periodBudgetOverviewFormHelper[${status.index}].adjustedDirectCostsAgencyRequest" write="true" />
                            </c:when>
                            <c:otherwise>
                              <html:hidden property="periodBudgetOverviewFormHelper[${status.index}].totalDirectCostsAgencyRequest" write="true" /> 
                            </c:otherwise>
                          </c:choose>
                        </div>
                      </td>
                      
                      <td class="datacell">
                        <div align="right">
                          <html:hidden property="periodBudgetOverviewFormHelper[${status.index}].indirectCostItem.calculatedIndirectCost" write="true" />
                        </div>
                      </td>
                      
                      <td class="datacell">
                        <div align="right">
                          <html:hidden property="periodBudgetOverviewFormHelper[${status.index}].totalCostsAgencyRequest" write="true" />
                        </div>
                      </td>
                      
                      <td class="datacell"><div align="center"><html:checkbox property="periodBudgetOverviewFormHelper[${status.index}].selected"/></div></td>
                    </tr>

                  </c:forEach>

                    <tr>
                      <td class="infoline">
                        <div class="nowrap" align="center">
                          <strong>Total</strong>
                          <br />
                          <span class="fineprint">${KualiForm.summaryBudgetOverviewFormHelper.budgetPeriod.budgetPeriodLabel}</span>
                        </div>
                      </td>
                      
                      <td class="infoline">
                        <div align="right">
                          <c:choose>
                            <c:when test="${KualiForm.summaryBudgetOverviewFormHelper.overviewShowModular}">
                              <c:set var="agencyRequestAmount" value="${KualiForm.summaryBudgetOverviewFormHelper.adjustedDirectCostsAgencyRequest}" />
                            </c:when>
                            <c:otherwise>
                              <c:set var="agencyRequestAmount" value="${KualiForm.summaryBudgetOverviewFormHelper.totalDirectCostsAgencyRequest}" />
                            </c:otherwise>
                          </c:choose>
                        
                          <b><fmt:formatNumber value="${agencyRequestAmount}" type="currency" currencySymbol="" maxFractionDigits="0" /></b>
                        </div>
                      </td>
                      
                      <td class="infoline">
                        <div align="right">
                          <b><fmt:formatNumber value="${KualiForm.summaryBudgetOverviewFormHelper.totalIndirectCostsAgencyRequest}" type="currency" currencySymbol="" maxFractionDigits="0" /></b>
                        </div>
                      </td>
                      
                      <td class="infoline">
                        <div align="right">
                          <b><fmt:formatNumber value="${KualiForm.summaryBudgetOverviewFormHelper.totalCostsAgencyRequest}" type="currency" currencySymbol="" maxFractionDigits="0" /></b>
                        </div>
                      </td>
                      
                      <td class="infoline"><div align="center"><html:checkbox property="allPeriodsSelected" /></div></td>
                    </tr>
                    
                    <tr>
                      <td colspan="74" class="infoline" height="30">
                      <div align="center">
                        <html:image property="methodToCall.linkBudget.anchor${currentTabIndex}" styleClass="tinybutton" src="${ConfigProperties.externalizable.images.url}tinybutton-linkselperiods.gif" alt="link selected periods"/>
                      </div></td>
                    </tr>
                  </table>
                </c:if>

  </div>
  
  </kul:tabTop>

  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="b3" summary="">
    <tr>
      <td align="left" class="footer"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="12" height="14" class="bl3"></td>
      <td align="right" class="footer-right"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="12" height="14" class="br3"></td>
    </tr>
  </table>  
  
  </div>
<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;
</SCRIPT>
<script language="javascript" src="scripts/research/researchDocument.js"></script>
<script language="javascript" src="dwr/interface/BudgetService.js"></script>
<SCRIPT type="text/javascript">
	budgetNameLookup('document.routingFormBudgetNumber')
</SCRIPT>	
</kul:documentPage>