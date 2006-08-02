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
<%@ attribute name="personListIndex" required="true"%>


                 <c:set var="appointmentTypes" value="${fn:split(person.appointmentTypeCode, ',')}" />
                 
                 <c:choose>
	                 <c:when test="${fn:contains(KualiForm.appointmentTypeGridMappings['fullYear'], appointmentTypes[0])}">
	                   <kra-b:budgetPersonnelFullYearGrid person="${person}" personListIndex="${personListIndex}" matchAppointmentType="${appointmentTypes[0]}" />
	                 </c:when>
                   <c:when test="${fn:contains(KualiForm.appointmentTypeGridMappings['academicYear'], appointmentTypes[0])}">
	                   <kra-b:budgetPersonnelFullYearGrid person="${person}" personListIndex="${personListIndex}"  matchAppointmentType="${appointmentTypes[0]}"/>
	                 </c:when>
                   <c:when test="${fn:contains(KualiForm.appointmentTypeGridMappings['academicSummer'], appointmentTypes[0])}">
                     <kra-b:budgetPersonnelSummerGrid person="${person}" personListIndex="${personListIndex}"  matchAppointmentType="${appointmentTypes[0]}"/>
                   </c:when>
                   <c:when test="${fn:contains(KualiForm.appointmentTypeGridMappings['hourly'], appointmentTypes[0])}">
                     <kra-b:budgetPersonnelHourlyGrid person="${person}" personListIndex="${personListIndex}" matchAppointmentType="${appointmentTypes[0]}" />
                   </c:when>
                   <c:when test="${fn:contains(KualiForm.appointmentTypeGridMappings['gradResAssistant'], appointmentTypes[0])}">
                     <kra-b:budgetResGradAsstGrid person="${person}" personListIndex="${personListIndex}"  matchAppointmentType="${appointmentTypes[0]}" />
                   </c:when>
                 </c:choose>
                                    
                                    
                                    
                 <c:if test="${not empty appointmentTypes[1] }">
                  <c:choose>
	                  <c:when test="${fn:contains(KualiForm.appointmentTypeGridMappings['fullYear'], appointmentTypes[1])}">
	                    <kra-b:budgetPersonnelFullYearGrid person="${person}" personListIndex="${personListIndex}" matchAppointmentType="${appointmentTypes[1]}" />
	                  </c:when>
                    <c:when test="${fn:contains(KualiForm.appointmentTypeGridMappings['academicYear'], appointmentTypes[1])}">
	                    <kra-b:budgetPersonnelFullYearGrid person="${person}" personListIndex="${personListIndex}" matchAppointmentType="${appointmentTypes[1]}"  />
                    </c:when>
                    <c:when test="${fn:contains(KualiForm.appointmentTypeGridMappings['academicSummer'], appointmentTypes[1])}">
                      <kra-b:budgetPersonnelSummerGrid person="${person}" personListIndex="${personListIndex}" matchAppointmentType="${appointmentTypes[1]}"  />
                    </c:when>
                    <c:when test="${fn:contains(KualiForm.appointmentTypeGridMappings['hourly'], appointmentTypes[1])}">
                      <kra-b:budgetPersonnelHourlyGrid person="${person}" personListIndex="${personListIndex}" matchAppointmentType="${appointmentTypes[1]}"  />
                    </c:when>
                    <c:when test="${fn:contains(KualiForm.appointmentTypeGridMappings['gradResAssistant'], appointmentTypes[1])}">
                      <kra-b:budgetResGradAsstGrid person="${person}" personListIndex="${personListIndex}" matchAppointmentType="${appointmentTypes[1]}"  />
                    </c:when>
                  </c:choose>
                 </c:if>
                 
                 <logic:iterate id="userAppointmentTask" name="KualiForm" property="document.budget.personFromList[${personListIndex}].userAppointmentTasks" indexId="userAppointmentTaskIndex">
                   <c:set var="userAppointmentType" value="${KualiForm.document.budget.personnel[personListIndex].userAppointmentTasks[userAppointmentTaskIndex].universityAppointmentTypeCode}" />
                   
                    <!-- User Appointment Task Fields -->
	                 <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].documentHeaderId" />
	                 <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].budgetUserSequenceNumber" />
	                 <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].budgetTaskSequenceNumber" />
	                 <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].universityAppointmentTypeCode" />
	                 <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].versionNumber" />
	                 <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].objectId" />
	                 
	                 <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].budgetFringeRate.documentHeaderId" />
	                 <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].budgetFringeRate.universityAppointmentTypeCode" />
                   <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].budgetFringeRate.contractsAndGrantsFringeRateAmount" />
                   <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].budgetFringeRate.universityCostShareFringeRateAmount" />
	                 
	                 <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].task.documentHeaderId" />
	                 <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].task.budgetTaskSequenceNumber" />
	                 <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].task.budgetTaskName" />
	                 
	                 <logic:iterate id="userAppointmentTaskPeriod" name="KualiForm" property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriods" indexId="userAppointmentTaskPeriodIndex">
                     <!-- User Appointment Task Period Fields -->
                     <!-- All Appointment Types -->
                     <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].documentHeaderId" />
                     <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].budgetUserSequenceNumber" />
                     <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].budgetTaskSequenceNumber" />
                     <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].budgetPeriodSequenceNumber" />
                     <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].universityAppointmentTypeCode" />
                     <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].versionNumber" />
                     <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].objectId" />

                     <!-- Fringe Rate Info -->                      
                     <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].budgetFringeRate.documentHeaderId" />
                     <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].budgetFringeRate.universityAppointmentTypeCode" />

                     <!-- Period Fields -->
                     <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].period.documentHeaderId" />
                     <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].period.budgetPeriodSequenceNumber" />
                     <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].period.budgetPeriodBeginDate" />
                     <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].period.budgetPeriodEndDate" />

                     <!-- Task Fields -->
                     <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].task.documentHeaderId" />
                     <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].task.budgetTaskSequenceNumber" />
                     <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].task.budgetTaskName" />


	                     <c:if test="${ (!(fn:contains(KualiForm.appointmentTypeGridMappings['fullYear'], userAppointmentType) or fn:contains(KualiForm.appointmentTypeGridMappings['academicYearSummer'], userAppointmentType))) or not (userAppointmentTask.budgetTaskSequenceNumber eq person.currentTaskNumber) }">
                         <!-- Salary Appointment Types -->
                         <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].userBudgetPeriodSalaryAmount" />
                         <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].agencyPercentEffortAmount" />
                         <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].agencyRequestTotalAmount" />
                         <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].agencyFringeBenefitTotalAmount" />
                         <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].universityCostSharePercentEffortAmount" />
                         <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].universityCostShareRequestTotalAmount" />
                         <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].universityCostShareFringeBenefitTotalAmount" />
                         <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].universityCostSharePercentEffortAmount" />
	                     </c:if>
	                  
                       <c:if test="${ !fn:contains(KualiForm.appointmentTypeGridMappings['academicSummer'], userAppointmentType) or not (userAppointmentTask.budgetTaskSequenceNumber eq person.currentTaskNumber)  }">
                         <!-- Summer Appointment Type -->
                         <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].personWeeksAmount" />
                       </c:if>
                    
                       <c:if test="${!fn:contains(KualiForm.appointmentTypeGridMappings['hourly'], userAppointmentType) or not (userAppointmentTask.budgetTaskSequenceNumber eq person.currentTaskNumber) }">
                         <!-- Hourly Appointment Types -->
                         <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].userHourlyRate" />
                         <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].userAgencyHours" />
                         <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].userUniversityHours" />
                       </c:if>
                    
                       <c:if test="${!fn:contains(KualiForm.appointmentTypeGridMappings['gradResAssistant'], userAppointmentType) or not (userAppointmentTask.budgetTaskSequenceNumber eq person.currentTaskNumber) }">
                         <!-- Grad Asst Appointment -->
                         <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].agencyFullTimeEquivalentPercent" />
                         <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].agencyHealthInsuranceAmount" />
                         <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].agencyRequestedFeesAmount" />
                         <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].agencySalaryAmount" />
                         <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].universityFullTimeEquivalentPercent" />
                         <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].universityHealthInsuranceAmount" />
                         <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].universityRequestedFeesAmount" />
                         <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].universitySalaryAmount" />
                         <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].userCreditHoursNumber" />
                         <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].userCreditHourAmount" />
                         <html:hidden property="document.budget.personFromList[${personListIndex}].userAppointmentTask[${userAppointmentTaskIndex}].userAppointmentTaskPeriod[${userAppointmentTaskPeriodIndex}].userMiscellaneousFeeAmount" />
                       </c:if>

	                 </logic:iterate>
                 </logic:iterate>
                 