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
<%@ taglib prefix="c" uri="/tlds/c.tld"%>
<%@ taglib prefix="fn" uri="/tlds/fn.tld"%>
<%@ taglib uri="/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib tagdir="/WEB-INF/tags/kr" prefix="kul"%>
<%@ taglib tagdir="/WEB-INF/tags/kr/dd" prefix="dd"%>

<%@ attribute name="documentAttributes" required="true" type="java.util.Map"
              description="The DataDictionary entry containing attributes for this row's fields." %>

<c:set var="stipulationAttributes" value="${DataDictionary.PurchaseOrderVendorStipulation.attributes}" />

<kul:tab tabTitle="Stipulations & Info" defaultOpen="true" tabErrorKey="${PurapConstants.STIPULATIONS_TAB_ERRORS}">
    <div class="tab-container" align=center>
        <div class="h2-container">
            <h2>Notes to Vendor</h2>
        </div>
        <table cellpadding="0" cellspacing="0" class="datatable" summary="Stipulations & Info Section">
            <tr>
                <kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" align="left"/>
                <kul:htmlAttributeHeaderCell attributeEntry="${stipulationAttributes.vendorStipulationDescription}" />
                <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
            </tr>
            <c:if test="${fullEntryMode}" >
                <tr>
                    <kul:htmlAttributeHeaderCell literalLabel="add:" scope="row"/>
                    <td class="infoline">
                        <kul:htmlControlAttribute 
                            attributeEntry="${stipulationAttributes.vendorStipulationDescription}" 
                            property="newPurchaseOrderVendorStipulationLine.vendorStipulationDescription" />
                        <kul:lookup boClassName="org.kuali.module.purap.bo.VendorStipulation" fieldConversions="vendorStipulationDescription:document.vendorStipulationDescription" /></div>
                    </td>
                    <td class="infoline">
                		<div align="center"><html:image property="methodToCall.addStipulation" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" alt="Insert a Stipulation" title="Add a Stipulation" styleClass="tinybutton"/></div>
    				</td>
                </tr>
            </c:if>
        	<logic:notEmpty name="KualiForm" property="document.purchaseOrderVendorStipulations">
	 			<logic:iterate name="KualiForm" id="stipulation" property="document.purchaseOrderVendorStipulations" indexId="ctr">
                    <tr>
		                <td class="infoline">&nbsp;</td>
		                <td align=center valign=middle class="datacell">
		                    <kul:htmlControlAttribute 
		                        attributeEntry="${stipulationAttributes.vendorStipulationDescription}" 
		                        property="document.purchaseOrderVendorStipulation[${ctr}].vendorStipulationDescription" 
		                        readOnly="${not fullEntryMode}" 
		                    />
                            <html:hidden property="document.purchaseOrderVendorStipulation[${ctr}].vendorStipulationCreateDate" />
                            <html:hidden property="document.purchaseOrderVendorStipulation[${ctr}].vendorStipulationAuthorEmployeeIdentifier" />
                            <html:hidden property="document.purchaseOrderVendorStipulation[${ctr}].purchaseOrderVendorStipulationIdentifier" />
                            <html:hidden property="document.purchaseOrderVendorStipulation[${ctr}].purchaseOrderIdentifier" />
                            <html:hidden property="document.purchaseOrderVendorStipulation[${ctr}].versionNumber" />
		                </td>
		                <td class="infoline"><div align="center"><html:image property="methodToCall.deleteStipulation.line${ctr}" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif" alt="Delete Stipulation ${ctr+1}" title="Delete Stipulation ${ctr+1}" styleClass="tinybutton"/></div></td>
		            </tr>
	        	</logic:iterate>
	        </logic:notEmpty>
        </table>
    </div>
</kul:tab>
