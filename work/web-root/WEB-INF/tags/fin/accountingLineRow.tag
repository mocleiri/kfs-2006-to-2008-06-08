<%@ taglib prefix="c" uri="/tlds/c.tld" %>
<%@ taglib prefix="fn" uri="/tlds/fn.tld" %>
<%@ taglib prefix="html" uri="/tlds/struts-html.tld" %>
<%@ taglib prefix="bean" uri="/tlds/struts-bean.tld" %>
<%@ taglib prefix="kul" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fin" tagdir="/WEB-INF/tags/fin" %>

<%@ attribute name="accountingLine" required="true"
              description="The name in the form of the accounting line
              being edited or displayed by this row." %>
<%@ attribute name="baselineAccountingLine" required="false"
              description="The name in the form of the baseline accounting line
              from before the most recent edit of this row,
              to put in hidden fields for comparison or reversion." %>
<%@ attribute name="accountingLineAttributes" required="true" type="java.util.Map"
              description="The DataDictionary entry containing attributes for this row's fields." %>
<%@ attribute name="accountingLineIndex" required="false" description="index of this accountingLine in the corresponding form list" %>

<%@ attribute name="dataCellCssClass" required="true"
              description="The name of the CSS class for this data cell.
              This is used to distinguish the look of the add row from the already-added rows." %>
<%@ attribute name="rowHeader" required="true"
              description="The value of the header cell of this row.
              It would be 'add:' or the number of this row's accounting line within its group." %>

<%@ attribute name="actionGroup" required="true" description="The name of the group of action buttons to be displayed; valid values are newLine and existingLine." %>
<%@ attribute name="actionInfix" required="true" description="Infix used to build method names which will be invoked by the buttons in this actionGroup" %>

<%@ attribute name="accountingAddLineIndex" required="false" description="index for multiple add new source lines" %>

<%@ attribute name="readOnly" required="true" %>
<%@ attribute name="editableFields" required="false" type="java.util.Map"
              description="Map of accounting line fields which this user is allowed to edit" %>
<%@ attribute name="hiddenFields" required="true"
              description="A comma separated list of names of accounting line fields
              to be put in hidden fields on this form." %>
<%@ attribute name="columnCountUntilAmount" required="true"
              description="the number of columns to the left of the amount column(s) in the
              accounting lines table.  This depends on the number
              of optional fields and whether there is an object type column." %>

<%@ attribute name="optionalFields" required="false"
              description="A comma separated list of names of accounting line fields
              to be appended to the required field columns, before the amount column." %>

<%@ attribute name="extraRowFields" required="false"
              description="A comma seperated list of names of any non-standard fields
              required by this accounting line.  They are placed on a second row, sharing the
              same row header and action (if any). Each field includes its own label and takes up two columns.
              Note that since there are only 8 standard fields, no more than 4+(optionalFieldsCount/2)
              extra fields can be properly formatted in the table.
              This attribute requires the extraRowLabelFontWeight attribute." %>
<%@ attribute name="extraRowLabelFontWeight" required="false"
              description="The font weight for the in-cell labels on the extra row, e.g., bold or normal.
              Providing this attribute without the extraRowFields attribute has no effect.  Changing the weight
              allows the labels in the 'add' row to correspond to the weight of the column headers." %>

<%@ attribute name="debitCreditAmount" required="false"
              description="boolean whether the amount column is displayed as
              separate debit and credit columns.
              If true, debitCellProperty and creditCellProperty are required.
              As with all boolean tag attributes, if it is not provided, it defaults to false." %>

<%@ attribute name="currentBaseAmount" required="false"
              description="boolean whether the amount column is displayed as
              separate current and base columns.
              If true, currentCellProperty and baseCellProperty are required.
              As with all boolean tag attributes, if it is not provided, it defaults to false." %>

<%@ attribute name="debitCellProperty" required="false"
              description="the name of the form property to display or edit in the debit amount column"%>
<%@ attribute name="creditCellProperty" required="false"
              description="the name of the form property to display or edit in the credit amount column"%>

<%@ attribute name="currentCellProperty" required="false"
              description="the name of the form property to display or edit in the current amount column"%>
<%@ attribute name="baseCellProperty" required="false"
              description="the name of the form property to display or edit in the base amount column"%>

<%@ attribute name="includeObjectTypeCode" required="false"
              description="boolean indicating that the object type code column should be displayed.
              As with all boolean tag attributes, if it is not provided, it defaults to false." %>

<%@ attribute name="displayHidden" required="false" description="display values of hidden fields" %>
<%@ attribute name="decorator" required="false" description="propertyName of the AccountingLineDecorator associated with this accountingLine" %>

<%@ attribute name="accountingLineValuesMap" required="true" type="java.util.Map" 
              description="map of the accounting line primitive fields and values" %>
<%@ attribute name="forcedReadOnlyFields" required="false" type="java.util.Map"
              description="foces the object code to become a read only field regardless of document state"%>
<c:set var="rowCount" value="${empty extraRowFields ? 1 : 2}"/>

<tr>
<kul:htmlAttributeHeaderCell literalLabel="${rowHeader}:" scope="row" rowspan="${rowCount}">
    <%-- these hidden fields are inside a table cell to keep the HTML valid --%>
    <c:forTokens var="hiddenField" items="${hiddenFields}" delims=",">
        <fin:hiddenAccountingLineField
            accountingLine="${accountingLine}"
            hiddenField="${hiddenField}"
            displayHidden="${displayHidden}"
            />
        <c:if test="${!empty baselineAccountingLine}">
            <fin:hiddenAccountingLineField
                accountingLine="${baselineAccountingLine}"
                isBaseline="true"
                hiddenField="${hiddenField}"
                displayHidden="${displayHidden}"
                />
        </c:if>
    </c:forTokens>
</kul:htmlAttributeHeaderCell>

<fin:accountingLineDataCell
    dataCellCssClass="${dataCellCssClass}"
    accountingLine="${accountingLine}"
    baselineAccountingLine="${baselineAccountingLine}"
    field="chartOfAccountsCode"
    detailFunction="loadChartInfo"
    detailField="chart.finChartOfAccountDescription"
    attributes="${accountingLineAttributes}"
    lookup="false"
    inquiry="true"
    boClassSimpleName="Chart"
    readOnly="${readOnly&&(empty editableFields['chartOfAccountsCode'])}"
    displayHidden="${displayHidden}"
    accountingLineValuesMap="${accountingLineValuesMap}"
    />

<fin:accountingLineDataCell
    dataCellCssClass="${dataCellCssClass}"
    accountingLine="${accountingLine}"
    baselineAccountingLine="${baselineAccountingLine}"
    field="accountNumber"
    detailFunction="loadAccountInfo"
    detailField="account.accountName"
    attributes="${accountingLineAttributes}"
    lookup="true"
    inquiry="true"
    boClassSimpleName="Account"
    readOnly="${readOnly&&(empty editableFields['accountNumber'])}"
    displayHidden="${displayHidden}"
    overrideField="accountExpiredOverride"
    lookupOrInquiryKeys="chartOfAccountsCode"
    accountingLineValuesMap="${accountingLineValuesMap}"
    />
    
<fin:accountingLineDataCell
    dataCellCssClass="${dataCellCssClass}"
    accountingLine="${accountingLine}"
    baselineAccountingLine="${baselineAccountingLine}"
    field="subAccountNumber"
    detailFunction="loadSubAccountInfo"
    detailField="subAccount.subAccountName"
    attributes="${accountingLineAttributes}"
    lookup="true"
    inquiry="true"
    boClassSimpleName="SubAccount"
    readOnly="${readOnly&&(empty editableFields['subAccountNumber'])}"
    displayHidden="${displayHidden}"
    lookupOrInquiryKeys="chartOfAccountsCode,accountNumber"
    accountingLineValuesMap="${accountingLineValuesMap}"
    />
    
<fin:accountingLineDataCell
    dataCellCssClass="${dataCellCssClass}"
    accountingLine="${accountingLine}"
    baselineAccountingLine="${baselineAccountingLine}"
    field="financialObjectCode"
    detailFunction="loadObjectInfo"
    detailFunctionExtraParam="'${KualiForm.document.postingYear}', "
    detailField="objectCode.financialObjectCodeName"
    attributes="${accountingLineAttributes}"
    lookup="true"
    inquiry="true"
    boClassSimpleName="ObjectCode"
    readOnly="${(readOnly&&(empty editableFields['financialObjectCode']))|| !(empty forcedReadOnlyFields[accountingLineAttributes.financialObjectCode.name])}"
    displayHidden="${displayHidden}"
    overrideField="objectBudgetOverride"
    lookupOrInquiryKeys="chartOfAccountsCode"
    accountingLineValuesMap="${accountingLineValuesMap}"
    inquiryExtraKeyValues="universityFiscalYear=${KualiForm.document.postingYear}"
    />
    
<c:if test="${includeObjectTypeCode}">
    <fin:accountingLineDataCell
        dataCellCssClass="${dataCellCssClass}"
        accountingLine="${accountingLine}"
        baselineAccountingLine="${baselineAccountingLine}"
        field="objectTypeCode"
        detailFunction="loadObjectTypeInfo"
        detailField="objectType.name"
        attributes="${accountingLineAttributes}"
        lookup="true"
        inquiry="true"
        boClassSimpleName="ObjectType"
        conversionField="code"
        readOnly="${readOnly}"
        displayHidden="${displayHidden}"
        accountingLineValuesMap="${accountingLineValuesMap}"
        />
</c:if>
    
<fin:accountingLineDataCell
    dataCellCssClass="${dataCellCssClass}"
    accountingLine="${accountingLine}"
    baselineAccountingLine="${baselineAccountingLine}"
    field="financialSubObjectCode"
    detailFunction="loadSubObjectInfo"
    detailFunctionExtraParam="'${KualiForm.document.postingYear}', "
    detailField="subObjectCode.financialSubObjectCodeName"
    attributes="${accountingLineAttributes}"
    lookup="true"
    inquiry="true"
    boClassSimpleName="SubObjCd"
    readOnly="${readOnly&&(empty editableFields['financialSubObjectCode'])}"
    displayHidden="${displayHidden}"
    lookupOrInquiryKeys="chartOfAccountsCode,financialObjectCode,accountNumber"
    accountingLineValuesMap="${accountingLineValuesMap}"
    inquiryExtraKeyValues="universityFiscalYear=${KualiForm.document.postingYear}"
    />
  
<fin:accountingLineDataCell
    dataCellCssClass="${dataCellCssClass}"
    accountingLine="${accountingLine}"
    baselineAccountingLine="${baselineAccountingLine}"
    field="projectCode"
    detailFunction="loadProjectInfo"
    detailField="project.name"
    attributes="${accountingLineAttributes}"
    lookup="true"
    inquiry="true"
    boClassSimpleName="ProjectCode"
    conversionField="code"
    readOnly="${readOnly&&(empty editableFields['projectCode'])}"
    displayHidden="${displayHidden}"
    accountingLineValuesMap="${accountingLineValuesMap}"
    />
    
<fin:accountingLineDataCell
    dataCellCssClass="${dataCellCssClass}"
    accountingLine="${accountingLine}"
    baselineAccountingLine="${baselineAccountingLine}"
    field="organizationReferenceId"
    attributes="${accountingLineAttributes}"
    readOnly="${readOnly}"
    displayHidden="${displayHidden}"
    />
<c:forTokens items="${optionalFields}" delims=" ," var="currentField">
    <fin:accountingLineDataCell
        dataCellCssClass="${dataCellCssClass}"
        accountingLine="${accountingLine}"
        baselineAccountingLine="${baselineAccountingLine}"
        field="${currentField}"
        attributes="${accountingLineAttributes}"
        readOnly="${readOnly}"
        displayHidden="${displayHidden}"
        />
</c:forTokens>
<c:choose>
    <c:when test="${debitCreditAmount}" >
        <fin:accountingLineDataCell
            dataCellCssClass="${dataCellCssClass}"
            cellProperty="${debitCellProperty}"
            attributes="${accountingLineAttributes}"
            field="amount"
            readOnly="${readOnly&&(empty editableFields['amount'])}"
            rowSpan="${rowCount}"
            />
        <fin:accountingLineDataCell
            dataCellCssClass="${dataCellCssClass}"
            cellProperty="${creditCellProperty}"
            attributes="${accountingLineAttributes}"
            field="amount"
            readOnly="${readOnly&&(empty editableFields['amount'])}"
            rowSpan="${rowCount}"
            />
    </c:when>
    <c:when test="${currentBaseAmount}" >
        <fin:accountingLineDataCell
            dataCellCssClass="${dataCellCssClass}"
            accountingLine="${accountingLine}"
            baselineAccountingLine="${baselineAccountingLine}"
            cellProperty="${currentCellProperty}"
            attributes="${accountingLineAttributes}"
            field="currentBudgetAdjustmentAmount"
            readOnly="${readOnly&&(empty editableFields['currentBudgetAdjustmentAmount'])}"
            rowSpan="${rowCount}"
            />
        <fin:accountingLineDataCell
            dataCellCssClass="${dataCellCssClass}"
            accountingLine="${accountingLine}"
            baselineAccountingLine="${baselineAccountingLine}"
            cellProperty="${baseCellProperty}"
            attributes="${accountingLineAttributes}"
            field="baseBudgetAdjustmentAmount"
            readOnly="${(readOnly&&(empty editableFields['baseBudgetAdjustmentAmount']))||!KualiForm.editingMode['baseAmtEntry']}"
            rowSpan="${rowCount}"
            />
    </c:when>
    <c:otherwise>
        <fin:accountingLineDataCell
            dataCellCssClass="${dataCellCssClass}"
            accountingLine="${accountingLine}"
            baselineAccountingLine="${baselineAccountingLine}"
            field="amount"
            attributes="${accountingLineAttributes}"
            readOnly="${readOnly&&(empty editableFields['amount'])}"
            displayHidden="${displayHidden}"
            rowSpan="${rowCount}"
            />
    </c:otherwise>
</c:choose>
<c:if test="${!readOnly}">
    <c:choose>
        <c:when test="${actionGroup == 'newLine' }" >
            <c:set var="insertMethod" value="insert${actionInfix}Line" />

            <td rowspan="${rowCount}" class="${dataCellCssClass}" nowrap><div align="center">
                <html:image property="methodToCall.${insertMethod}" src="images/tinybutton-add1.gif" alt="insert" styleClass="tinybutton"/>
                <fin:accountingLineDataCellDetail/></div>
            </td>
        </c:when>
        
        <c:when test="${actionGroup == 'newGroupLine' }" >
            <c:set var="insertMethod" value="insert${actionInfix}Line.line${accountingAddLineIndex}" />

            <td rowspan="${rowCount}" class="${dataCellCssClass}" nowrap><div align="center">
                <html:image property="methodToCall.${insertMethod}" src="images/tinybutton-add1.gif" alt="insert" styleClass="tinybutton"/>
                <fin:accountingLineDataCellDetail/></div>
            </td>
        </c:when>

        <c:when test="${actionGroup == 'existingLine'}" >
            <c:set var="revertible">
                <bean:write name="KualiForm" property="${decorator}.revertible" />
            </c:set>
            <c:set var="deleteMethod" value="delete${actionInfix}Line.line${accountingLineIndex}" />
            <c:set var="revertMethod" value="revert${actionInfix}Line.line${accountingLineIndex}" />
            <c:set var="balanceInquiryMethod" value="performBalanceInquiryFor${actionInfix}Line.line${accountingLineIndex}" />

            <td rowspan="${rowCount}" class="${dataCellCssClass}" nowrap>
                <div align="center">
                <%-- persist accountingLineDecorator --%>
		        <html:hidden name="KualiForm" property="${decorator}.revertible" />

		        <html:image property="methodToCall.${deleteMethod}" src="images/tinybutton-delete1.gif" alt="delete" styleClass="tinybutton"/>
        		<c:if test="${revertible}">
            		<br>
            		<html:image property="methodToCall.${revertMethod}" src="images/tinybutton-revert1.gif" alt="revert" styleClass="tinybutton"/>
        		</c:if>
        		<br>
        		<html:image property="methodToCall.${balanceInquiryMethod}" src="images/tinybutton-balinquiry.gif" alt="balance inquiry" styleClass="tinybutton" />
                </div>
            </td>
        </c:when>
    </c:choose>
</c:if>
</tr>

<%-- optional second row of accounting fields, between index and amount columns --%>
<c:if test="${!empty extraRowFields}">
    <tr><td colspan="${columnCountUntilAmount - 1}" style="padding: 0px;border: 0px none ;">
        <table cellpadding="0" cellspacing="0" style="width: 100%;border: 0px;">
        <tr>
        <c:set var="delimitedExtraRowFields" value=",${extraRowFields},"/>
        <c:if test="${fn:contains(delimitedExtraRowFields, ',referenceOriginCode,')}" >
            <fin:accountingLineDataCell
                field="referenceOriginCode"
                lookup="true"
                inquiry="true"
                boClassSimpleName="OriginationCode"
                boPackageName="org.kuali.core.bo"
                conversionField="financialSystemOriginationCode"
                detailFunction="loadOriginationInfo"
                detailField="referenceOrigin.financialSystemServerName"
                accountingLine="${accountingLine}"
                baselineAccountingLine="${baselineAccountingLine}"
                attributes="${accountingLineAttributes}"
                accountingLineValuesMap="${accountingLineValuesMap}"
                dataCellCssClass="${dataCellCssClass}"
                labelFontWeight="${extraRowLabelFontWeight}"
                readOnly="${readOnly}"
                displayHidden="${displayHidden}"
                />
        </c:if>
        <c:if test="${fn:contains(delimitedExtraRowFields, ',referenceTypeCode,')}" >
            <fin:accountingLineDataCell
                field="referenceTypeCode"
                lookup="true"
                inquiry="true"
                boClassSimpleName="DocumentType"
                boPackageName="org.kuali.core.document"
                conversionField="financialDocumentTypeCode"
                detailFunction="loadDocumentTypeInfo"
                detailField="referenceType.financialDocumentName"
                accountingLine="${accountingLine}"
                baselineAccountingLine="${baselineAccountingLine}"
                attributes="${accountingLineAttributes}"
                accountingLineValuesMap="${accountingLineValuesMap}"
                dataCellCssClass="${dataCellCssClass}"
                labelFontWeight="${extraRowLabelFontWeight}"
                readOnly="${readOnly}"
                displayHidden="${displayHidden}"
                />
        </c:if>
        <%-- referenceNumber displays like an unknown field, but explicitly here to follow referenceOriginCode. --%>
        <c:if test="${fn:contains(delimitedExtraRowFields, ',referenceNumber,')}" >
            <fin:accountingLineDataCell
                field="referenceNumber"
                accountingLine="${accountingLine}"
                baselineAccountingLine="${baselineAccountingLine}"
                attributes="${accountingLineAttributes}"
                dataCellCssClass="${dataCellCssClass}"
                labelFontWeight="${extraRowLabelFontWeight}"
                readOnly="${readOnly}"
                displayHidden="${displayHidden}"
                />
        </c:if>
        <c:set var="knownExtraFields" value=",referenceOriginCode,referenceTypeCode,referenceNumber,"/>
        <c:forTokens items="${extraRowFields}" delims="," var="extraField">
            <c:if test="${not fn:contains(knownExtraFields, extraField)}" >
                <fin:accountingLineDataCell
                    field="${extraField}"
                    accountingLine="${accountingLine}"
                    baselineAccountingLine="${baselineAccountingLine}"
                    attributes="${accountingLineAttributes}"
                    dataCellCssClass="${dataCellCssClass}"
                    labelFontWeight="${extraRowLabelFontWeight}"
                    readOnly="${readOnly}"
                    displayHidden="${displayHidden}"
                    />
            </c:if>
        </c:forTokens>
            <%-- use up the remaining space, to push the extra fields together --%>
            <td class="${dataCellCssClass}" width="100%"/>
        </tr>
        </table>
    </td></tr>
</c:if>
