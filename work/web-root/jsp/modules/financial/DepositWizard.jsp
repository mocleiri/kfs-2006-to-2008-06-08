<%@ include file="/jsp/core/tldHeader.jsp" %>
<kul:page showDocumentInfo="false" headerTitle="Create a New Deposit Document" docTitle="Create a New Deposit Document" transactionalDocument="false" htmlFormAction="depositWizard" >
<script type="text/javascript">
function checkAllOrNone() {
  for(var i = 0; i < document.KualiForm.elements.length; i++) {
    var e = document.KualiForm.elements[i];
    if((e.name != 'masterCheckBox') && (e.type == 'checkbox') && (!e.disabled)) {
      e.checked = document.KualiForm.elements['masterCheckBox'].checked;
    }
  }
}
</script>
<div class="tab-container" align=center>   
    <div align="left">
    	<kul:errors keyMatch="*" />
    </div>
    <div align="left">
    	<h3>Please select the Cash Receipt documents that you would like to deposit.</h3>
    </div>
    <br>  
    <br>
	<table cellpadding=0 class="datatable" summary="choose cash receipts to deposit">
	  <tr>
      	<td colspan=6 class="tab-subhead">Cash Receipts Ready for Deposit</td>
      </tr>
	  <tr>
	  	  <td>
	  	      <div align="center">
	  	      		<input type="checkbox" name="masterCheckBox" onclick="checkAllOrNone();" />
	  	      </div>
	  	  </td>
	  	  <kul:htmlAttributeHeaderCell
	          literalLabel=""
	          scope="col"/>
	      <kul:htmlAttributeHeaderCell
	          literalLabel="Document Number"
	          scope="col"/>
	      <kul:htmlAttributeHeaderCell
	          literalLabel="Description"
			  scope="col"/>
		  <kul:htmlAttributeHeaderCell
	          literalLabel="Create Date"
			  scope="col"/>
	      <kul:htmlAttributeHeaderCell
	          literalLabel="Total"
			  scope="col"/>
	  </tr>
	  <logic:iterate name="KualiForm" id="cashReceipt" property="cashReceiptsReadyForDeposit" indexId="ctr">
	  <tr>
	  	<td class="datacell center">
	  		<div align="center">
	  		    <html:checkbox property="selectedCashReceipt[${ctr}].selectedValue" />
	  		</div>
	    </td>
	    <td class="datacell center">
	  		<div align="center">
	  		    <b>${(ctr + 1)}</b>
	  		</div>
	  	</td>
	    <td class="datacell center">
	    	<div align="center">
	    		<a href="financialCashReceipt.do?methodToCall=docHandler&docId=${cashReceipt.documentHeader.financialDocumentNumber}&command=displayDocSearchView" target="new">${cashReceipt.documentHeader.financialDocumentNumber}</a> 
	    	</div>
	    </td>
	    <td class="datacell center">
		    <div align="center">
		    	${cashReceipt.documentHeader.financialDocumentDescription}
		    </div>
	    </td>
	    <td class="datacell center">
		    <div align="center">
		    	${cashReceipt.documentHeader.workflowDocument.createDate}
		    </div>
	    </td>
	    <td class="datacell center">
		    <div align="center">
		    	$&nbsp;${cashReceipt.currencyFormattedSumTotalAmount}
		    </div>
	    </td>
	  </tr>
	  </logic:iterate>
	</table>
	<br>
	<br>
	<html:image property="methodToCall.cancel" src="images/buttonsmall_cancel.gif" alt="cancel" styleClass="globalbuttons" />&nbsp;&nbsp;
	<html:image property="methodToCall.createDeposit" src="images/buttonsmall_create.gif" alt="create the deposit" styleClass="globalbuttons" />
	</div>
</kul:page>