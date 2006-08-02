<%@ taglib prefix="c" uri="/tlds/c.tld"%>
<%@ taglib uri="/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="kul"%>
<%@ taglib tagdir="/WEB-INF/tags/dd" prefix="dd"%>
<%@ taglib tagdir="/WEB-INF/tags/kra" prefix="kra"%>
<%@ taglib uri="/tlds/fmt.tld" prefix="fmt"%>

<c:set var="showCostShareIndirect" value="${KualiForm.document.budget.universityCostShareIndicator && KualiForm.document.budget.indirectCost.budgetIndirectCostCostShareIndicator}"/>

<c:set var="tabDescription">
	<c:choose>
		<c:when test="${showCostShareIndirect}">
			&nbsp;
		</c:when>
		<c:otherwise>
			None
		</c:otherwise>
	</c:choose>
</c:set>

<kul:tab tabTitle="Cost Share - Institution Indirect" tabDescription="${tabDescription}" defaultOpen="false" transparentBackground="false">
	<c:if test="${!showCostShareIndirect}">
		<div class="tab-container" align="center">
			<div class="h2-container">
				<span class="subhead-left">
					<h2>
						Cost Share - Institution Indirect
					</h2> </span>
			</div>
			<table cellpadding=0 summary="view/edit document overview information">
				<tr>
					<td height="70" align=left valign=middle class="datacell">
						<div align="center">
							Not Included In This Budget
						</div>
					</td>
				</tr>
			</table>
		</div>
	</c:if>

	<c:if test="${showCostShareIndirect}">
		<div class="tab-container" align=center>
			<table class="datatable" cellpadding="0">
				<tr>
					<td class="subhead" colspan="${KualiForm.document.periodListSize+3}">
						<span class="subhead-left">Cost Share - Institution Indirect</span>
					</td>
				</tr>
				<tr>
					<th class="bord-l-b">
						&nbsp;
					</th>
					<!--- Iterate over the periods to create the columns -->
					<logic:iterate id="period" name="KualiForm" property="document.budget.periods" indexId="ctr">
						<th class="bord-l-b">
							${ctr+1}
						</th>
					</logic:iterate>
					<th class="bord-l-b">
						Total
					</th>
				</tr>
				<tr>
					<th class="bord-l-b">
						<div align="right">
							Calculated Indirect Cost:
						</div>
					</th>
					<!--- Iterate over the periods to create the columns -->
					<logic:iterate id="periodTotal" name="KualiForm" property="budgetIndirectCostFormHelper.periodTotals" indexId="ctr">
	
						<c:choose>
							<c:when test="${KualiForm.document.budget.indirectCost.budgetIndirectCostCostShareIndicator}">
								<td class="datacell">
									<div align="right">
										<fmt:formatNumber value="${periodTotal.costShareCalculatedIndirectCost}" type="currency" currencySymbol="$" maxFractionDigits="0" />
									</div>
								</td>
							</c:when>
							<c:otherwise>
								<td>
									&nbsp;
								</td>
							</c:otherwise>
						</c:choose>
					</logic:iterate>
	
	
					<td class="datacell">
						<div align="right">
							<strong> <fmt:formatNumber value="${KualiForm.budgetIndirectCostFormHelper.periodSubTotal.costShareCalculatedIndirectCost}" type="currency" currencySymbol="$" maxFractionDigits="0" /> </strong>
						</div>
					</td>
				</tr>
				<tr>
					<th class="bord-l-b">
						<div align="right">
							Unrecovered Indirect Cost:
						</div>
					</th>
					<!--- Iterate over the periods to create the columns -->
					<logic:iterate id="periodTotal" name="KualiForm" property="budgetIndirectCostFormHelper.periodTotals" indexId="ctr">
						<td class="datacell">
							<c:choose>
								<c:when test="${KualiForm.document.budget.indirectCost.budgetIndirectCostCostShareIndicator}">
									<div align="right">
										<fmt:formatNumber value="${periodTotal.costShareUnrecoveredIndirectCost}" type="currency" currencySymbol="$" maxFractionDigits="0" />
									</div>
								</c:when>
								<c:otherwise>
								&nbsp;
							</c:otherwise>
							</c:choose>
						</td>
					</logic:iterate>
					<td class="datacell">
						<div align="right">
							<strong> <fmt:formatNumber value="${KualiForm.budgetIndirectCostFormHelper.periodSubTotal.costShareUnrecoveredIndirectCost}" type="currency" currencySymbol="$" maxFractionDigits="0" /> </strong>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</c:if>
</kul:tab>

<!-- Hidden variables for field level validation. -->
<html:hidden property="document.budget.indirectCost.budgetIndirectCostCostShareIndicator" />

<html:hidden property="budgetIndirectCostFormHelper.periodSubTotal.costShareCalculatedIndirectCost" />
<html:hidden property="budgetIndirectCostFormHelper.periodSubTotal.costShareUnrecoveredIndirectCost" />

<logic:iterate id="period" name="KualiForm" property="document.budget.periods" indexId="ctr">
	<html:hidden property="budgetIndirectCostFormHelper.periodTotal[${ctr}].costShareCalculatedIndirectCost" />
	<html:hidden property="budgetIndirectCostFormHelper.periodTotal[${ctr}].costShareUnrecoveredIndirectCost" />
</logic:iterate>
