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
<%@ include file="/jsp/kfs/kfsTldHeader.jsp"%>

<%@ attribute name="currencyProperty" required="true" %>
<%@ attribute name="coinProperty" required="true" %>
<%@ attribute name="readOnly" required="false" %>
<%@ attribute name="editingMode" required="false" type="java.util.Map" %>

<c:if test="${!readOnly && !empty editingMode}">
  <c:set var="readOnly" value="${editingMode['viewOnly']}" />
</c:if>

<c:set var="currencyAttributes" value="${DataDictionary.CurrencyDetail.attributes}" />
<c:set var="coinAttributes" value="${DataDictionary.CoinDetail.attributes}" />

<html:hidden property="${currencyProperty}.financialDocumentTypeCode" />
<html:hidden property="${currencyProperty}.cashieringRecordSource" />
<html:hidden property="${currencyProperty}.documentNumber" />
<html:hidden property="${currencyProperty}.versionNumber" />
<html:hidden property="${currencyProperty}.objectId" />
<html:hidden property="${coinProperty}.financialDocumentTypeCode" />
<html:hidden property="${coinProperty}.cashieringRecordSource" />
<html:hidden property="${coinProperty}.documentNumber" />
<html:hidden property="${coinProperty}.versionNumber" />
<html:hidden property="${coinProperty}.objectId" />

      <table border="0" cellspacing="0" cellpadding="0" class="datatable" width="100%">
        <tr>
          <th>&nbsp;</th>
          <th>Count</th>
          <th>Amount</th>
          <th>&nbsp;</th>
          <th>Count</th>
          <th>Amount</th>
        </tr>
        <tr>
          <td>
            <kul:htmlAttributeLabel labelFor="${currencyProperty}.hundredDollarCount" attributeEntry="${currencyAttributes.hundredDollarCount}" />
          </td>
          <td>
            <kul:htmlControlAttribute property="${currencyProperty}.hundredDollarCount" attributeEntry="${currencyAttributes.hundredDollarCount}" readOnly="${readOnly}" />
          </td>
          <td><span id="${currencyProperty}.financialDocumentHundredDollarAmount.span">$<bean:write name="KualiForm" property="${currencyProperty}.financialDocumentHundredDollarAmount" /></span></td>
          <td>
            <kul:htmlAttributeLabel labelFor="${coinProperty}.hundredCentCount" attributeEntry="${coinAttributes.hundredCentCount}" />
          </td>
          <td>
            <kul:htmlControlAttribute property="${coinProperty}.hundredCentCount" attributeEntry="${coinAttributes.hundredCentCount}" readOnly="${readOnly}" />
          </td>
          <td><span id="${coinProperty}.financialDocumentHundredCentAmount.span">$<bean:write name="KualiForm" property="${coinProperty}.financialDocumentHundredCentAmount" /></span></td>
        </tr>
        <tr>
          <td>
            <kul:htmlAttributeLabel labelFor="${currencyProperty}.fiftyDollarCount" attributeEntry="${currencyAttributes.fiftyDollarCount}" />
          </td>
          <td>
            <kul:htmlControlAttribute property="${currencyProperty}.fiftyDollarCount" attributeEntry="${currencyAttributes.fiftyDollarCount}" readOnly="${readOnly}" />
          </td>
          <td><span id="${currencyProperty}.financialDocumentFiftyDollarAmount.span">$<bean:write name="KualiForm" property="${currencyProperty}.financialDocumentFiftyDollarAmount" /></span></td>
          <td>
            <kul:htmlAttributeLabel labelFor="${coinProperty}.fiftyCentCount" attributeEntry="${coinAttributes.fiftyCentCount}" />
          </td>
          <td>
            <kul:htmlControlAttribute property="${coinProperty}.fiftyCentCount" attributeEntry="${coinAttributes.fiftyCentCount}" readOnly="${readOnly}" />
          </td>
          <td><span id="${coinProperty}.financialDocumentFiftyCentAmount.span">$<bean:write name="KualiForm" property="${coinProperty}.financialDocumentFiftyCentAmount" /></span></td>
        </tr>
        <tr>
          <td>
            <kul:htmlAttributeLabel labelFor="${currencyProperty}.twentyDollarCount" attributeEntry="${currencyAttributes.twentyDollarCount}" />
          </td>
          <td>
            <kul:htmlControlAttribute property="${currencyProperty}.twentyDollarCount" attributeEntry="${currencyAttributes.twentyDollarCount}" readOnly="${readOnly}" />
          </td>
          <td><span id="${currencyProperty}.financialDocumentTwentyDollarAmount.span">$<bean:write name="KualiForm" property="${currencyProperty}.financialDocumentTwentyDollarAmount" /></span></td>
          <td>
            <kul:htmlAttributeLabel labelFor="${coinProperty}.twentyFiveCentCount" attributeEntry="${coinAttributes.twentyFiveCentCount}" />
          </td>
          <td>
            <kul:htmlControlAttribute property="${coinProperty}.twentyFiveCentCount" attributeEntry="${coinAttributes.twentyFiveCentCount}" readOnly="${readOnly}" />
          </td>
          <td><span id="${coinProperty}.financialDocumentTwentyFiveCentAmount.span">$<bean:write name="KualiForm" property="${coinProperty}.financialDocumentTwentyFiveCentAmount" /></span></td>
        </tr>
        <tr>
          <td>
            <kul:htmlAttributeLabel labelFor="${currencyProperty}.tenDollarCount" attributeEntry="${currencyAttributes.tenDollarCount}" />
          </td>
          <td>
            <kul:htmlControlAttribute property="${currencyProperty}.tenDollarCount" attributeEntry="${currencyAttributes.tenDollarCount}" readOnly="${readOnly}" />
          </td>
          <td><span id="${currencyProperty}.financialDocumentTenDollarAmount.span">$<bean:write name="KualiForm" property="${currencyProperty}.financialDocumentTenDollarAmount" /></span></td>
          <td>
            <kul:htmlAttributeLabel labelFor="${coinProperty}.tenCentCount" attributeEntry="${coinAttributes.tenCentCount}" />
          </td>
          <td>
            <kul:htmlControlAttribute property="${coinProperty}.tenCentCount" attributeEntry="${coinAttributes.tenCentCount}" readOnly="${readOnly}" />
          </td>
          <td><span id="${coinProperty}.financialDocumentTenCentAmount.span">$<bean:write name="KualiForm" property="${coinProperty}.financialDocumentTenCentAmount" /></span></td>
        </tr>
        <tr>
          <td>
            <kul:htmlAttributeLabel labelFor="${currencyProperty}.fiveDollarCount" attributeEntry="${currencyAttributes.fiveDollarCount}" />
          </td>
          <td>
            <kul:htmlControlAttribute property="${currencyProperty}.fiveDollarCount" attributeEntry="${currencyAttributes.fiveDollarCount}" readOnly="${readOnly}" />
          </td>
          <td><span id="${currencyProperty}.financialDocumentFiveDollarAmount.span">$<bean:write name="KualiForm" property="${currencyProperty}.financialDocumentFiveDollarAmount" /></span></td>
          <td>
            <kul:htmlAttributeLabel labelFor="${coinProperty}.fiveCentCount" attributeEntry="${coinAttributes.fiveCentCount}" />
          </td>
          <td>
            <kul:htmlControlAttribute property="${coinProperty}.fiveCentCount" attributeEntry="${coinAttributes.fiveCentCount}" readOnly="${readOnly}" />
          </td>
          <td><span id="${coinProperty}.financialDocumentFiveCentAmount.span">$<bean:write name="KualiForm" property="${coinProperty}.financialDocumentFiveCentAmount" /></span></td>
        </tr>
        <tr>
          <td>
            <kul:htmlAttributeLabel labelFor="${currencyProperty}.twoDollarCount" attributeEntry="${currencyAttributes.twoDollarCount}" />
          </td>
          <td>
            <kul:htmlControlAttribute property="${currencyProperty}.twoDollarCount" attributeEntry="${currencyAttributes.twoDollarCount}" readOnly="${readOnly}" />
          </td>
          <td><span id="${currencyProperty}.financialDocumentTwoDollarAmount.span">$<bean:write name="KualiForm" property="${currencyProperty}.financialDocumentTwoDollarAmount" /></span></td>
          <td>
            <kul:htmlAttributeLabel labelFor="${coinProperty}.oneCentCount" attributeEntry="${coinAttributes.oneCentCount}" />
          </td>
          <td>
            <kul:htmlControlAttribute property="${coinProperty}.oneCentCount" attributeEntry="${coinAttributes.oneCentCount}" readOnly="${readOnly}" />
          </td>
          <td><span id="${coinProperty}.financialDocumentOneCentAmount.span">$<bean:write name="KualiForm" property="${coinProperty}.financialDocumentOneCentAmount" /></span></td>
        </tr>
        <tr>
          <td>
            <kul:htmlAttributeLabel labelFor="${currencyProperty}.oneDollarCount" attributeEntry="${currencyAttributes.oneDollarCount}" />
          </td>
          <td>
            <kul:htmlControlAttribute property="${currencyProperty}.oneDollarCount" attributeEntry="${currencyAttributes.oneDollarCount}" readOnly="${readOnly}" />
          </td>
          <td><span id="${currencyProperty}.financialDocumentOneDollarAmount.span">$<bean:write name="KualiForm" property="${currencyProperty}.financialDocumentOneDollarAmount" /></span></td>
          <td>
            <kul:htmlAttributeLabel labelFor="${coinProperty}.financialDocumentOtherCentAmount" attributeEntry="${coinAttributes.financialDocumentOtherCentAmount}" />
          </td>
          <td>&nbsp;</td>
          <td>
            $<kul:htmlControlAttribute property="${coinProperty}.financialDocumentOtherCentAmount" attributeEntry="${coinAttributes.financialDocumentOtherCentAmount}" readOnly="${readOnly}" />
          </td>
        </tr>
        <tr>
          <td>
            <kul:htmlAttributeLabel labelFor="${currencyProperty}.financialDocumentOtherDollarAmount" attributeEntry="${currencyAttributes.financialDocumentOtherDollarAmount}" />
          </td>
          <td>&nbsp;</td>
          <td>
            $<kul:htmlControlAttribute property="${currencyProperty}.financialDocumentOtherDollarAmount" attributeEntry="${currencyAttributes.financialDocumentOtherDollarAmount}" readOnly="${readOnly}" />
          </td>
          <td colspan="3">&nbsp;</td>
        </tr>
      </table>