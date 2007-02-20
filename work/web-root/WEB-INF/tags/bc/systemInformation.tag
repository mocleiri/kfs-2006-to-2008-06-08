<%--
 Copyright 2007 The Kuali Foundation.
 
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
<%@ taglib uri="/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="kul" %>

<c:set var="documentAttributes" value="${DataDictionary.KualiBudgetConstructionDocument.attributes}" />
<c:set var="accountAttributes" value="${DataDictionary.Account.attributes}" />
<c:set var="subFundGroupAttributes" value="${DataDictionary.SubFundGroup.attributes}" />

<kul:tab tabTitle="System Information" defaultOpen="true" tabErrorKey="${Constants.BUDGET_CONSTRUCTION_SYSTEM_INFORMATION_TAB_ERRORS}">
<div class="tab-container" align=center>
	<div class="h2-container">
		<h2>System Information</h2>
	</div>
		  <table cellpadding="0" cellspacing="0" class="datatable" title="view system information" summary="view system information">
		    <tr>
		      <kul:htmlAttributeHeaderCell
		          labelFor="document.universityFiscalYear"
		          literalLabel="Fiscal Year:"
		          horizontal="true"
		          />
		      <td></td>
		      <td align="center" valign="middle">
		      	<kul:htmlControlAttribute property="document.universityFiscalYear" attributeEntry="${documentAttributes.universityFiscalYear}" readOnly="${true}"/>
		      </td>
		      <td>
		      </td>
            </tr>
		    <tr>
		      <kul:htmlAttributeHeaderCell
		          labelFor="document.accountNumber"
		          literalLabel="Chart/Account:"
		          horizontal="true"
		          />
		      <td align="center" valign="middle">
		      	<kul:htmlControlAttribute
		      		property="document.chartOfAccountsCode"
		      		attributeEntry="${documentAttributes.chartOfAccountsCode}"
		      		readOnly="true"
		      		readOnlyBody="true">
		      		<kul:inquiry
					    boClassName="org.kuali.module.chart.bo.Chart"
					    keyValues="chartOfAccountsCode=${KualiForm.document.chartOfAccountsCode}"
					    render="true">
				    	<html:hidden write="true" property="document.chartOfAccountsCode" />
					</kul:inquiry>
		      		
		      	</kul:htmlControlAttribute>
		      </td>
		      <td align="center" valign="middle">
		      	<kul:htmlControlAttribute
		      		property="document.accountNumber"
		      		attributeEntry="${documentAttributes.accountNumber}"
		      		readOnly="true"
		      		readOnlyBody="true">
		      		<kul:inquiry
					    boClassName="org.kuali.module.chart.bo.Account"
					    keyValues="chartOfAccountsCode=${KualiForm.document.chartOfAccountsCode}&amp;accountNumber=${KualiForm.document.accountNumber}"
					    render="true">
				    	<html:hidden write="true" property="document.accountNumber" />
					</kul:inquiry>
		      		
		      	</kul:htmlControlAttribute>
		      </td>
		      <td align="center" valign="middle">
		      	<kul:htmlControlAttribute property="document.account.accountName" attributeEntry="${accountAttributes.accountName}" readOnly="${true}"/>
		      </td>
            </tr>
		    <tr>
		      <kul:htmlAttributeHeaderCell
		          labelFor="document.subAccountNumber"
		          literalLabel="Sub-Account:"
		          horizontal="true"
		          />
		      <td>
		      </td>
		      <td align="center" valign="middle">
		      	<kul:htmlControlAttribute property="document.subAccountNumber" attributeEntry="${documentAttributes.subAccountNumber}" readOnly="${true}"/>
		      </td>
		      <td align="center" valign="middle">
		      	<kul:htmlControlAttribute property="document.subAccount.subAccountName" attributeEntry="${DataDictionary.SubAccount.attributes.subAccountName}" readOnly="${true}"/>
		      </td>
            </tr>
		    <tr>
		      <kul:htmlAttributeHeaderCell
		          labelFor="document.account.subFundGroupCode"
		          literalLabel="Sub-Fund Group:"
		          horizontal="true"
		          />
		      <td>
		      </td>
		      <td align="center" valign="middle">
		      	<kul:htmlControlAttribute
		      		property="document.account.subFundGroupCode"
		      		attributeEntry="${DataDictionary.Account.attributes.subFundGroupCode}"
		      		readOnly="true"
		      		readOnlyBody="true">
		      		<kul:inquiry
					    boClassName="org.kuali.module.chart.bo.SubFundGroup"
					    keyValues="subFundGroupCode=${KualiForm.document.account.subFundGroupCode}"
					    render="true">
				    	<html:hidden write="true" property="document.account.subFundGroupCode" />
					</kul:inquiry>
		      		
		      	</kul:htmlControlAttribute>
		      </td>
		      <td align="center" valign="middle">
		      	<kul:htmlControlAttribute property="document.account.subFundGroup.subFundGroupDescription" attributeEntry="${DataDictionary.SubFundGroup.attributes.subfundGroupDescription}" readOnly="${true}"/>
		      </td>
            </tr>

<%--
		      <kul:htmlAttributeHeaderCell
                  labelFor="document.documentHeader.explanation"
                  attributeEntry="${docHeaderAttributes.explanation}"
                  horizontal="true"
		          rowspan="2"
                  />
		      <td align="left" valign="middle" rowspan="2">
                  <kul:htmlControlAttribute
                      property="document.documentHeader.explanation"
                      attributeEntry="${docHeaderAttributes.explanation}"
                      readOnly="${readOnly}"
                      readOnlyAlternateDisplay="${fn:replace(KualiForm.document.documentHeader.explanation, Constants.NEWLINE, '<br/>')}"
                      />
              </td>
		    </tr>
		    <tr>
		      <c:if test="${includePostingYear}">
		          <kul:htmlAttributeHeaderCell
                    labelFor="document.postingYear"
                    attributeEntry="${postingYearAttributes.postingYear}"
                    horizontal="true"
                   />

                <td class="datacell-nowrap">
                    <kul:htmlControlAttribute 
                        attributeEntry="${postingYearAttributes.postingYear}" 
                        property="document.postingYear" 
                        onchange="${postingYearOnChange}"
                        readOnly="${!KualiForm.editingMode['fullEntry']}"/>
                    <c:if test="${!readOnly and includePostingYearRefresh}">
                       <html:image property="methodToCall.refresh" src="images/buttonsmall_refresh.gif" alt="refresh" title="refresh" styleClass="tinybutton"/>    
                    </c:if>   
                </td>
              </c:if>
              <c:if test="${!includePostingYear}">
		        <th colspan="2">
		        	&nbsp;
		        </th>
		      </c:if>
		    </tr>
		    <tr>
	            <c:if test="${KualiForm.documentActionFlags.hasAmountTotal}">
		          <kul:htmlAttributeHeaderCell
                    labelFor="document.documentHeader.financialDocumentTotalAmount"
                    attributeEntry="${docHeaderAttributes.financialDocumentTotalAmount}"
                    horizontal="true"/>

                   <td align="left" valign="middle">
                    <kul:htmlControlAttribute 
                        attributeEntry="${docHeaderAttributes.financialDocumentTotalAmount}" 
                        property="document.documentHeader.financialDocumentTotalAmount" 
                        readOnly="true"/>
                  </td>
                </c:if>
	            <c:if test="${!KualiForm.documentActionFlags.hasAmountTotal}">
		          <th colspan="2">
		             &nbsp;
		          </th>
		        </c:if>
		        
			  <kul:htmlAttributeHeaderCell
		        labelFor="document.documentHeader.organizationDocumentNumber"
		        attributeEntry="${docHeaderAttributes.organizationDocumentNumber}"
		        horizontal="true"
		      />			  
              <td align="left" valign="middle">
              	<kul:htmlControlAttribute property="document.documentHeader.organizationDocumentNumber" attributeEntry="${docHeaderAttributes.organizationDocumentNumber}" readOnly="${readOnly}"/>
              </td>
            </tr>
--%>            
          </table>
	<div class="h2-container">
		<h2>Next Year Data</h2>
	</div>
	<div class="h2-container">
		<h2>Approval Level Data</h2>
	</div>
	<div class="h2-container">
		<h2>Controls</h2>
	</div>

</div>
</kul:tab>
