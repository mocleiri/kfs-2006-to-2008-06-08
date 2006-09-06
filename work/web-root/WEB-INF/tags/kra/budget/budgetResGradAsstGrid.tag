
<%@ taglib prefix="c" uri="/tlds/c.tld" %>
<%@ taglib uri="/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/tlds/fn.tld" prefix="fn" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="kul" %>
<%@ taglib tagdir="/WEB-INF/tags/dd" prefix="dd" %>
<%@ taglib tagdir="/WEB-INF/tags/kra" prefix="kra" %>
<%@ taglib tagdir="/WEB-INF/tags/kra/budget" prefix="kra-b" %>

<%@ attribute name="person" required="true" type="org.kuali.module.kra.budget.bo.BudgetUser"%>
<%@ attribute name="personListIndex" required="true" %>
<%@ attribute name="matchAppointmentType" required="true" %>

<c:set var="budgetUserAttributes" value="${DataDictionary.BudgetUser.attributes}" />
<c:set var="userAppointmentTaskAttributes" value="${DataDictionary.UserAppointmentTask.attributes}" />
<c:set var="userAppointmentTaskPeriodAttributes" value="${DataDictionary.UserAppointmentTaskPeriod.attributes}" />

<c:set var="viewOnly" value="${KualiForm.editingMode['viewOnly']}" />

              <tr>

                <th rowspan="2" class="bord-l-b"><b>Period</b></th>
                <td colspan="3" class="tab-subhead"><div align="center"><b>Agency Amount Requested</b> </div></td>
                <td colspan="3" class="tab-subhead"><div align="center"><b>Institution CS</b></div></td>
                <th rowspan="2" class="bord-l-b">Total Effort</th>
                <th rowspan="2" class="bord-l-b"><b>Total Salary </b></th>
                <th rowspan="2" class="bord-l-b">Total Health Insurance</th>

              </tr>
              <tr>
                <th class="bord-l-b"> Effort </th>
                <th class="bord-l-b"> Salary</th>
                <th class="bord-l-b"> Health Insurance</th>
                <th class="bord-l-b"> Effort  </th>

                <th class="bord-l-b"> Salary</th>
                <th class="bord-l-b"> Health Insurance</th>
              </tr>
              
              <logic:iterate id="userAppointmentTask" name="KualiForm" property="document.budget.personFromList[${personListIndex}].userAppointmentTasks" indexId="userAppointmentTaskIndex">
                <c:if test="${userAppointmentTask.budgetTaskSequenceNumber eq person.currentTaskNumber and matchAppointmentType eq person.appointmentTypeCode}">
                  <logic:iterate id="userAppointmentTaskPeriod" name="KualiForm" property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriods" indexId="userAppointmentTaskPeriodIndex">
  		              <tr>
  		                <td class="datacell"><div class="nowrap" align="center"><strong>${userAppointmentTaskPeriodIndex + 1}</strong><span class="fineprint"><br />
  		                    (<fmt:formatDate value="${userAppointmentTaskPeriod.period.budgetPeriodBeginDate}" dateStyle="short"/> - 
  		                     <fmt:formatDate value="${userAppointmentTaskPeriod.period.budgetPeriodEndDate}" dateStyle="short"/>)</span></div>
  		                </td>
                      
                      <td class="datacell">
                        <div align="center">
                          <kul:htmlControlAttribute property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].agencyFullTimeEquivalentPercent" attributeEntry="${userAppointmentTaskPeriodAttributes.agencyFullTimeEquivalentPercent}" readOnly="${viewOnly}" />%
                        </div>
                      </td>
                          
                      <td class="datacell">
                        <div align="center">
                          <kul:htmlControlAttribute property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].agencySalaryAmount" attributeEntry="${userAppointmentTaskPeriodAttributes.agencySalaryAmount}" readOnly="${viewOnly}" />
                        </div>
                      </td>
                      
                      <td class="datacell">
                        <div align="center">
                          <kul:htmlControlAttribute property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].agencyHealthInsuranceAmount" attributeEntry="${userAppointmentTaskPeriodAttributes.agencyHealthInsuranceAmount}" readOnly="${viewOnly}" />
                        </div>
                      </td>
                      
                      <td class="datacell" nowrap="nowrap">
                        <div align="center">
                          <kul:htmlControlAttribute property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].universityFullTimeEquivalentPercent" disabled="${! KualiForm.document.budget.universityCostShareIndicator}" attributeEntry="${userAppointmentTaskPeriodAttributes.universityFullTimeEquivalentPercent}" readOnly="${viewOnly}" />%
                        </div>
                      </td>
      
                      <td class="datacell">
                        <div align="center">
                          <kul:htmlControlAttribute property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].universitySalaryAmount" disabled="${! KualiForm.document.budget.universityCostShareIndicator}" attributeEntry="${userAppointmentTaskPeriodAttributes.universitySalaryAmount}" readOnly="${viewOnly}" />
                        </div>
                      </td>
                      
                      <td class="datacell">
                        <div align="center">
                          <kul:htmlControlAttribute property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].universityHealthInsuranceAmount" disabled="${! KualiForm.document.budget.universityCostShareIndicator}" attributeEntry="${userAppointmentTaskPeriodAttributes.universityHealthInsuranceAmount}" readOnly="${viewOnly}" />
                        </div>
                      </td>
                      
                      <td class="datacell">
                        <div align="right">${userAppointmentTaskPeriod.totalFteAmount}% </div>
                      </td>
                      
                      <td class="datacell">
                        <div align="right"><fmt:formatNumber value="${userAppointmentTaskPeriod.totalGradAsstSalaryAmount}" type="currency" currencySymbol="$" maxFractionDigits="0" /></div>
                      </td>
      
                      <td class="datacell">
                        <div align="right"><fmt:formatNumber value="${userAppointmentTaskPeriod.totalHealthInsuranceAmount}" type="currency" currencySymbol="$" maxFractionDigits="0" /></div>
                      </td>
  		              </tr>
                  </logic:iterate>
	              <tr>
	                <th class="bord-l-b"><b>TOTAL</b> </th>
	                <td class="infoline">
                      &nbsp;
                  </td>
	
	                <td class="infoline">
                    <div align="right">
                      <b><fmt:formatNumber value="${userAppointmentTask.gradAsstAgencySalaryTotal}" type="currency" currencySymbol="$" maxFractionDigits="0" /></b>
                    </div>
                  </td>
	                
                  <td class="infoline">
                    <div align="right">
                      <b><fmt:formatNumber value="${userAppointmentTask.gradAsstAgencyHealthInsuranceTotal}" type="currency" currencySymbol="$" maxFractionDigits="0" /></b>
                    </div>
                  </td>
	                
                  <td class="infoline">&nbsp;</td>
	                
                  <td class="infoline">
                    <div align="right">
                      <b><fmt:formatNumber value="${userAppointmentTask.gradAsstUnivSalaryTotal}" type="currency" currencySymbol="$" maxFractionDigits="0" /></b>
                    </div>
                  </td>
	                
                  <td class="infoline">
                    <div align="right">
                      <b><fmt:formatNumber value="${userAppointmentTask.gradAsstUnivHealthInsuranceTotal}" type="currency" currencySymbol="$" maxFractionDigits="0" /></b>
                    </div>
                  </td>
	                
                  <td class="infoline">
                    &nbsp;
                  </td>
	
	                <td colspan="3" class="infoline">&nbsp;</td>
	              </tr>
                
                <tr bgcolor="#ffffff">
                  <td colspan="13" class="tab-subhead" height="30"><span class="left"><strong>Fee Remissions </strong></span></td>
                </tr>

                <tr>
                  <th class="bord-l-b"> Period </th>
                  <th class="bord-l-b">Credit Hours</th>
                  <th colspan="2" class="bord-l-b">$ Per Credit Hour</th>
                  <th colspan="2" class="bord-l-b">Miscellaneous Fees</th>
  
                  <th colspan="2" class="bord-l-b">Total Fee Remissions</th>
                  <th class="bord-l-b">Agency Amount Requested</th>
                  <th class="bord-l-b">Institution Cost Share</th>
                </tr>

                <logic:iterate id="userAppointmentTaskPeriod" name="KualiForm" property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriods" indexId="userAppointmentTaskPeriodIndex">
                  <tr>
                    <td class="datacell"><div class="nowrap" align="center"><strong>${userAppointmentTaskPeriodIndex + 1}</strong><span class="fineprint"><br />
                        (<fmt:formatDate value="${userAppointmentTaskPeriod.period.budgetPeriodBeginDate}" dateStyle="short"/> - 
                         <fmt:formatDate value="${userAppointmentTaskPeriod.period.budgetPeriodEndDate}" dateStyle="short"/>)</span></div></td>

                    <td class="datacell">
                      <div align="center">
                        <kul:htmlControlAttribute property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].userCreditHoursNumber" attributeEntry="${userAppointmentTaskPeriodAttributes.userCreditHoursNumber}" readOnly="${viewOnly}" />
                      </div>
                    </td>
                    
                    <td colspan="2" class="datacell">
                      <div align="center">
                        <kul:htmlControlAttribute property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].userCreditHourAmount" attributeEntry="${userAppointmentTaskPeriodAttributes.userCreditHourAmount}" readOnly="${viewOnly}" />
                      </div>
                    </td>
                    
                    <td colspan="2" class="datacell">
                      <div align="center">
                        <kul:htmlControlAttribute property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].userMiscellaneousFeeAmount" attributeEntry="${userAppointmentTaskPeriodAttributes.userMiscellaneousFeeAmount}" readOnly="${viewOnly}" />
                      </div>
                    </td>
                    
                    <td colspan="2" class="datacell">
                      <div align="right"><fmt:formatNumber value="${userAppointmentTaskPeriod.totalFeeRemissionsAmount}" type="currency" currencySymbol="$" maxFractionDigits="0" /></div>
                    </td>
                    
                    <td class="datacell">
                      <div align="center">
                        <kul:htmlControlAttribute property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].agencyRequestedFeesAmount" attributeEntry="${userAppointmentTaskPeriodAttributes.agencyRequestedFeesAmount}" readOnly="${viewOnly}" />
                      </div>
                    </td>
                    
                    <td class="datacell">
                      <div align="center">
                        <kul:htmlControlAttribute property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].universityRequestedFeesAmount" disabled="${! KualiForm.document.budget.universityCostShareIndicator}" attributeEntry="${userAppointmentTaskPeriodAttributes.universityRequestedFeesAmount}" readOnly="${viewOnly}" />
                      </div>
                    </td>
                  </tr>
                </logic:iterate>
              </c:if>
            </logic:iterate>