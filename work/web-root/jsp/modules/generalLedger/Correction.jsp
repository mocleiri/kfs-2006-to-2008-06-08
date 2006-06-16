<%@ include file="/jsp/core/tldHeader.jsp" %>
<kul:documentPage showDocumentInfo="true" documentTypeName="KualiGeneralLedgerErrorCorrectionDocument" htmlFormAction="generalLedgerCorrection" renderMultipart="true" > 
 
    <kul:hiddenDocumentFields isFinancialDocument="false" />
    <kul:documentOverview editingMode="${KualiForm.editingMode}"/>
    <input type="hidden" name="document.correctionChangeGroupNextLineNumber" value="<c:out value="${KualiForm.document.correctionChangeGroupNextLineNumber}" />" />

 	<div class="tab-container" align="center" >
      
            <table cellpadding=0 class="datatable" summary=""> 
                <tr>
                    <td align="left" valign="middle" class="subhead"><span class="subhead-left"></span>Select System and Edit Method</td>
                </tr>
 	 <tr>
     <td>
     	<center>
		<html:select property="chooseSystem">
		<html:optionsCollection property="validOptionsMap.org|kuali|core|lookup|keyvalues|CorrectionChooseSystemValuesFinder" label="label" value="key"/>
		</html:select>
               
        <html:select property="editMethod">
		<html:optionsCollection property="validOptionsMap.org|kuali|core|lookup|keyvalues|CorrectionEditMethodValuesFinder" label="label" value="key"/>
		</html:select>
		
	     <input type="image" name="methodToCall.chooseMainDropdown" 
                   src="images/tinybutton-select.gif" alt="chooseRadio" class="tinybutton" />
	  </center>
	  </td>
	  </tr>
	  </table>
 	</div>

 	 <c:if test="${KualiForm.chooseSystem == 'file'}" >
   		 <!--  File upload control -->
   			 <div class="tab-container" align="center"> 
    
     		   <div class="h2-container">
    		    <h2>Corrections File Upload</h2>
    	       </div>
      		  <table cellpadding=0 class="datatable" summary=""> 
 	           	<tr>
    	            <td class="bord-l-b" style="padding: 4px;">
        	        
        	        
        	        <html:file size="30" property="sourceFile" />
        	        <html:image property="methodToCall.uploadFile" src="images/tinybutton-loaddoc.gif"
                                    styleClass="tinybutton" alt="uploadFile" />
        	     	</td>	    	    
               	</tr>
	        	</table>
   			 </div>
    </c:if>
 
	
            
   <c:if test="${KualiForm.editMethod == 'criteria'}" >
    <!-- Search criteria control -->
    <div class="tab-container" align="center"> 
    
        <kul:errors keyMatch="searchFieldError" />
    
        <table cellpadding=0 class="datatable" summary="">
        
            <tr>
                <td align="left" valign="middle" class="subhead"><span class="subhead-left">Search Criteria</span></td> 
                <td align="left" valign="middle" class="subhead"><span class="subhead-left">Modification Criteria</span></td>
            </tr>
            <tr>
            <td colspan="2" align="left" class="bord-l-b" style="padding: 4px; vertical-align: top;"> 
            <STRONG>Add Groups </STRONG> 
            <input type="image" 
                   name="methodToCall.addCorrectionGroup" 
                   src="images/tinybutton-add1.gif" alt="add correction group" class="tinybutton" /> 
            </td>
            
            </tr>
            
               <c:forEach var="group" items="${ KualiForm.document.correctionChangeGroup}"> 
                   <tr>
                       <td colspan="2" align="left" class="bord-l-b" style="padding: 4px; vertical-align: top;"> 
                           <strong>Group:</strong> <c:out value="${groupCounter}" /> 
                           <c:set var="groupCounter" value="${groupCounter + 1}" /> 
                           <input type="image" 
                            name="methodToCall.removeCorrectionGroup.group<c:out value="${group.correctionChangeGroupLineNumber}" />" 
                            src="images/tinybutton-delete1.gif" alt="delete correction group" class="tinybutton" />
                       </td>
                   </tr>
                <tr style="border-bottom: 1px solid #333;"> 
                    <td class="bord-l-b" style="padding: 4px; vertical-align: top;">
                        <input type="hidden" name="correction-groups[<c:out 
                            value="${group.correctionChangeGroupLineNumber}" />][group-number]" value="" /> 
                        <input type="hidden" name="correction-groups[<c:out 
                            value="${group.correctionChangeGroupLineNumber}" />][next-search-criterion-number]" value="<c:out 
                            value="${group.correctionCriteriaNextLineNumber}" />" /> 
                        <input type="hidden" name="correction-groups[<c:out 
                            value="${group.correctionChangeGroupLineNumber}" />][next-replacement-specification-number]" value="<c:out 
                            value="${group.correctionChangeNextLineNumber}" />" />
                        <c:forEach items="${group.correctionCriteria}" var="criterion"> 

                            <div>
                                <label for="correction-groups[<c:out value="${group.correctionChangeGroupLineNumber }" />][search-criteria][<c:out 
                                    value="${criterion.correctionCriteriaLineNumber}" />][field-name]">Field</label>
                                <select id="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][search-criteria][<c:out 
                                    value="${criterion.correctionCriteriaLineNumber}" />][field-name]"
                                      name="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][search-criteria][<c:out 
                                        value="${criterion.correctionCriteriaLineNumber}" />][field-name]">
                                    <option value="" selected>Select Search Criteria</option> 
                                    <c:forEach var="fieldName" items="${KualiForm.fieldNames}">
                                        <option value="<c:out value="${fieldName}" />"<c:if 
                                            test="${fieldName eq criterion.correctionFieldName}"> selected="true"</c:if>>
                                            <c:out value="${fieldName}" /> 
                                        </option>
                                    </c:forEach>
                                </select>
                                <label for="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][search-criteria][<c:out 
                                    value="${criterion.correctionCriteriaLineNumber}" />][operator]">Operator</label>
                                <select id="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][search-criteria][<c:out 
                                    value="${criterion.correctionCriteriaLineNumber}" />][operator]"
                                  name="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][search-criteria][<c:out 
                                    value="${criterion.correctionCriteriaLineNumber}" />][operator]">
                                    <option value="">Select Operator</option> 
                                    <c:forEach var="operator" items="${KualiForm.searchOperators}">
                                        <option value="<c:out value="${operator.key }" />"<c:if 
                                            test="${operator.key eq criterion.operator}">selected="true"</c:if>><c:out 
                                            value="${ operator.value}" /></option>
                                    </c:forEach>
                                </select>
                                <label for="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][search-criteria][<c:out 
                                    value="${criterion.correctionCriteriaLineNumber}" />][field-value]">Value</label>
                                <input id="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][search-criteria][<c:out 
                                    value="${criterion.correctionCriteriaLineNumber}" />][field-value]"
                                    value="${ criterion.correctionFieldValue}"
                                    name="correction-groups[<c:out value="${group.correctionChangeGroupLineNumber}" />][search-criteria][<c:out 
                                    value="${ criterion.correctionCriteriaLineNumber}" />][field-value]" type="text">
                                <input type="image" 
                                    name="methodToCall.removeSearchCriterion.criterion <c:out value="${criterion.correctionChangeGroupLineNumber}" />-<c:out 
                                        value="${criterion.correctionCriteriaLineNumber}" />" 
                                    src="images/tinybutton-delete1.gif" alt="delete search criterion" class="tinybutton" />
                            </div>
                        </c:forEach>

                        
                        <div>
                            <label for="correction-groups[<c:out value="${group.correctionChangeGroupLineNumber}" />][search-criteria][field-name]">Field</label> 
                            <select id="correction-groups[<c:out value="${group.correctionChangeGroupLineNumber}" />][search-criteria][field-name]"
                                  name="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][search-criteria][field-name]">
                                <option value="" selected="selected">Select Search Criteria</option>
                                <c:forEach var="fieldName" items="${ KualiForm.fieldNames}"><option value="<c:out 
                                    value="${fieldName}" />"><c:out value="${fieldName}" /></option></c:forEach> 
                            </select>
                            <label for="correction-groups[<c:out value="${group.correctionChangeGroupLineNumber}" />][search-criteria][operator]">Operator</label> 
                            <select id="correction-groups[<c:out value="${group.correctionChangeGroupLineNumber}" />][search-criteria][operator]"
                                  name="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][search-criteria][operator]">
                                <option value="" selected="selected">Select Operator</option>
                                <c:forEach var="operator" items="${ KualiForm.searchOperators}">
                                    <option value="<c:out value="${operator.key}" />"><c:out value="${operator.value}" /></option> 
                                </c:forEach>
                            </select>
                            <label for="correction-groups[<c:out value="${group.correctionChangeGroupLineNumber}" />][search-criteria][field-value]">Value</label> 
                            <input id="correction-groups[<c:out value="${group.correctionChangeGroupLineNumber}" />][search-criteria][field-value]"
                                  name="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][search-criteria][field-value]" 
                                  type="text">
                            <html:image property="methodToCall.addSearchCriterion" src="images/tinybutton-add1.gif" 
                                alt="add search criteria" styleClass="tinybutton" />
                        </div>
                        
                    </td> 
                    <td class="bord-l-b" style="padding: 4px; vertical-align: top;">
                        <c:forEach var="specification" items="${group.correctionChange }">

                           
                            <div>
                                <label for="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][replacement-specifications][<c:out 
                                    value="${specification.correctionChangeLineNumber}" />][field-name]">Field</label>
                                <select id="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][replacement-specifications][<c:out 
                                        value="${specification.correctionChangeLineNumber}" />][field-name]" 
                                    name="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][replacement-specifications][<c:out 
                                        value="${specification.correctionChangeLineNumber}" />][field-name]">
                                    <c:forEach var="fieldName" items="${ KualiForm.fieldNames}">
                                        <option value="<c:out value="${fieldName}" />"<c:if 
                                            test="${fieldName eq specification.correctionFieldName}"> selected="true"</c:if>>
                                            <c:out value="${fieldName}" />
                                        </option> 
                                    </c:forEach>
                                </select>
                                <label for="correction-groups[<c:out value="${group.correctionChangeGroupLineNumber }" />][replacement-specifications][<c:out 
                                    value="${specification.correctionChangeLineNumber}" />][replacement-value]">Replacement Value</label>
                                <input type="text" 
                                    name="correction-groups[<c:out value="${group.correctionChangeGroupLineNumber}" />][replacement-specifications][<c:out 
                                        value="${ specification.correctionChangeLineNumber}" />][replacement-value]" 
                                    value="<c:out value="${specification.correctionFieldValue}" />"
                                    id="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][replacement-specifications][<c:out 
                                        value="${specification.correctionChangeLineNumber}" />][replacement-value]">
                                <input type="image" 
                                    name="methodToCall.removeReplacementSpecification.specification<c:out value="${specification.correctionChangeGroupLineNumber}" />-<c:out 
                                    value="${ specification.correctionChangeLineNumber}" />" 
                                    src="images/tinybutton-delete1.gif" alt="delete search specification" class="tinybutton" />
                            </div>
                        </c:forEach>

                       
                        <div>
                            <label for="correction-groups[<c:out value="${group.correctionChangeGroupLineNumber}" />][replacement-specifications][field-name]">Field</label> 
                            <select id="correction-groups[<c:out value="${group.correctionChangeGroupLineNumber}" />][replacement-specifications][field-name]"
                                name="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][replacement-specifications][field-name]">
                                <option value="" selected="selected">Specify Modification</option>
                                <c:forEach var="fieldName" items="${ KualiForm.fieldNames}"><option value="<c:out 
                                    value="${fieldName}" />"><c:out value="${fieldName}" /></option></c:forEach> 
                            </select>
                            <label for="correction-groups[<c:out value="${group.correctionChangeGroupLineNumber}" />][replacement-specifications][replacement-value]">Replacement 
                                Value</label>
                            <input type="text" name="correction-groups[<c:out 
                                value="${group.correctionChangeGroupLineNumber }" />][replacement-specifications][replacement-value]" 
                                id="correction-groups[<c:out value="${group.correctionChangeGroupLineNumber}" />][replacement-specifications][replacement-value]"> 
                            <html:image property="methodToCall.addReplacementSpecification" src="images/tinybutton-add1.gif" 
                                alt="add replacement specification" styleClass="tinybutton" /> 
                        </div>

                        
			                   
			                   
			                   
                    </td>
                </tr>
            </c:forEach>
			
		 		                   
	</table>

	<div>	
		<div align="center">
			<html:checkbox property="deleteOutput" > <STRONG> Delete output file? </STRONG> </html:checkbox>
       	    <html:checkbox property="matchCriteriaOnly" > <STRONG> Output only recoreds which match criteria? </STRONG> </html:checkbox>
		
        	<c:if test="${KualiForm.chooseSystem == 'system'}" >	
		
    		   		<input type="image" name="methodToCall.searchAndReplaceWithCriteria" 
	             	  src="images/buttonsmall_errcorr.gif" alt="error correction" class="tinybutton" /> 
	    
	             	  
            </c:if>

            <c:if test="${KualiForm.chooseSystem == 'file'}" >
		
					<input type="image" name="methodToCall.fileUploadSearchAndReplaceWithCriteria" 
			 		       src="images/buttonsmall_errcorr.gif" alt="error correction" class="tinybutton" />
		
	    	</c:if>	
	    	</br>
	    	</br>
       			<div align="center">
					<STRONG>Show Output file<STRONG>
					<input type="image" name="methodToCall.showOutputFile" 
					  src="images/buttonsmall_submit.gif" alt="showOutputFile" class="tinybutton" />
				</div>
				
	
    
    </div>		

</c:if>
             
             
             
             
             
      <c:if test="${KualiForm.chooseSystem == 'system'}" >
      	
          <div class="tab-container" align="center"> 
            <table cellpadding=0 class="datatable" summary="">
            
            <tr>
	             <td align="left" valign="middle" class="subhead"><span class="subhead-left"></span>Documents in System</td>
            </tr>
            <tr>
                <td colspan="2" class="bord-l-b" style="padding: 4px; vertical-align: top;"> 
                    <center>
                        <label for="pending-origin-entry-group-id"><strong>Origin Entry Group</strong></label><br/><br/>
                        
                        <html:select property="groupIdList" multiple="true" size="10" >
							<html:optionsCollection property="validOptionsMap.org|kuali|core|lookup|keyvalues|CorrectionPendingGroupEntriesFinder" label="label" value="key" />
						</html:select>
                        
                        
                        
                        <br/><br/> 
                        
	                        <input type="image" name="methodToCall.loadDocument" 
			                	   src="images/tinybutton-loaddoc.gif" alt="show all entries for manual edit" class="tinybutton" />
							Copy To Desktop 
							<input type="image" name="methodToCall.saveToDesktop" 
									src="images/tinybutton-copy2.gif" alt="saveToDeskTop" class="tinybutton" />
							
			                   
			                   
			         
                    </center>
                </td>
            </tr>
        </table> 
    
 	</div>
 	</c:if>
 
 
 
 
 
    <!--  Search Results Control -->
	<div class="tab-container" align="left" style="overflow: scroll; max-width: 100%;">
      
            <table cellpadding=0 class="datatable" summary=""> 
                <tr>
                    <td align="left" valign="middle" class="subhead"><span class="subhead-left">Search Results</span></td>
                </tr>
                <tr>
                    
                      <td>
                                           
                      <display:table id="allEntries" name="${KualiForm.allEntries}" requestURIcontext="false" >
             		   
             		   <c:choose>
        	     			<c:when test="${KualiForm.editableFlag == 'Y'}">
             		    		<display:column title="Manual Edit" >
             		    		    	 <input type="image" name="methodToCall.showOneEntry" value="${allEntries.entryId}"
			    			               src="images/tinybutton-edit1.gif" alt="edit" class="tinybutton" />
				           		          <input type="image" name="methodToCall.deleteEntry" value="${allEntries.entryId}"
			    			               src="images/tinybutton-delete1.gif" alt="delete" class="tinybutton" />
			           		    	</display:column>
			           		     
			          			</c:when>
							<c:otherwise>
								<display:column title="Manual Edit" > </display:column>
							</c:otherwise>
						</c:choose>
						<display:column property="accountNumber" title="Account Number" />
						<display:column property="financialDocumentNumber" title="Financial Document Number" />
						<display:column property="referenceFinancialDocumentNumber" title="Reference Financial Document Number" />
						<display:column property="referenceFinancialDocumentTypeCode" title="Reference Financial Document TypeCode" />
						<display:column property="financialDocumentReversalDate" title="Financial Document Reversal Date" />
						<display:column property="financialDocumentTypeCode" title="Financial Document TypeCode" />
						<display:column property="financialBalanceTypeCode" title="Financial Balance TypeCode" />
						<display:column property="chartOfAccountsCode" title="Chart Of Accounts Code" />
						<display:column property="financialObjectTypeCode" title="Financial Object TypeCode" />
						<display:column property="financialObjectCode" title="Financial Object Code" />
						<display:column property="financialSubObjectCode" title="Financial Sub-Object Code" />
						<display:column property="financialSystemOriginationCode" title="Financial System Origination Code" />
						<display:column property="referenceFinancialSystemOriginationCode" title="Reference Financial System Origination Code" />
						<display:column property="organizationDocumentNumber" title="Organization Document Number" />
						<display:column property="organizationReferenceId" title="Organization ReferenceId" />
						<display:column property="projectCode" title="Project Code" />
						<display:column property="subAccountNumber" title="Sub-Account Number" />
						<display:column property="transactionDate" title="Transaction Date" />
						<display:column property="transactionDebitCreditCode" title="Transaction Debit Credit Code" />
						<display:column property="transactionEncumbranceUpdateCode" title="Transaction Encumbrance Update Code" />
						<display:column property="transactionLedgerEntrySequenceNumber" title="Transaction LedgerEntry Sequence Number" />
						<display:column property="transactionLedgerEntryAmount" title="Transaction LedgerEntry Amount" />
						<display:column property="transactionLedgerEntryDescription" title="Transaction LedgerEntry Description" />
						<display:column property="universityFiscalPeriodCode" title="University FiscalPeriod Code" />
						<display:column property="universityFiscalYear" title="University FiscalYear" />
						<display:column property="budgetYear" title="BudgetYear" />
						
						</display:table>
                      
                      
                      </td>
                      
                 	
              	  </tr>
              	  <td>
              	  	<c:if test="${KualiForm.manualEditFlag == 'Y'}" >           
                      	<STRONG> Do you want to edit this document? </STRONG>
                      	 <input type="image" name="methodToCall.showEditMethod" 
			                   src="images/tinybutton-edit1.gif" alt="show edit" class="tinybutton" />
           			</c:if>
           		  </td>
       		 </table>
       	</div>



 <c:if test="${KualiForm.editMethod == 'manual'}" >        	
      <div class="tab-container" align="left" style="overflow: scroll; max-width: 100%;">
      
            <table cellpadding=0 class="datatable" summary=""> 
                <tr>
                    <td align="left" valign="middle" class="subhead"><span class="subhead-left">Manual Editing</span></td>
                </tr>
                
        	<td>
   			<display:table id="eachEntryForManualEdit" name="${KualiForm.eachEntryForManualEdit}" requestURIcontext="false"  >
	   			
	   			<display:column title="ManualEdit" >
	   				<input type="image" name="methodToCall.editEntry" value="${eachEntryForManualEdit.entryId}"
				           src="images/tinybutton-saveedits.gif" alt="Edit an Entry" class="tinybutton" />
	   			</display:column>
	   			
	   			<display:column title="Account Number" >
   					<input size="8" type="text" name="editAccountNumber"
                	       value="<c:out value="${KualiForm.eachEntryForManualEdit.accountNumber}" />">
        	    </display:column>
      	    
        	    <display:column title="Financial Document Number" >
        	    	<input size="9" type="text" name="editFinancialDocumentNumber"
                    	   value="<c:out value="${KualiForm.eachEntryForManualEdit.financialDocumentNumber}" />">
        	    </display:column>	
        	    
				<display:column title="Reference Financial Document Number" >
					<input size="9" type="text" name="editReferenceFinancialDocumentNumber"
    	                   value="<c:out value="${KualiForm.eachEntryForManualEdit.referenceFinancialDocumentNumber}" />">
				</display:column>
				
				<display:column title="Reference Financial Document TypeCode" >
					<input size="9" type="text" name="editReferenceFinancialDocumentTypeCode"
            	           value="<c:out value="${KualiForm.eachEntryForManualEdit.referenceFinancialDocumentTypeCode}" />">
				</display:column>
				
				<display:column title="Financial Document Reversal Date" >
					<input size="9" type="text" name="editFinancialDocumentReversalDate"
                    	   value="<c:out value="${KualiForm.eachEntryForManualEdit.financialDocumentReversalDate}" />">
				</display:column>
				
				<display:column title="Financial Document TypeCode" >
					<input size="9" type="text" name="editFinancialDocumentTypeCode"
    	                   value="<c:out value="${KualiForm.eachEntryForManualEdit.financialDocumentTypeCode}" />">
				
				</display:column>
			
				<display:column title="Financial Balance TypeCode" >
					<input size="9" type="text" name="editFinancialBalanceTypeCode"
                	       value="<c:out value="${KualiForm.eachEntryForManualEdit.financialBalanceTypeCode}" />">
				</display:column>
				
				<display:column title="Chart Of Accounts Code" >
					<input size="9" type="text" name="editChartOfAccountsCode"
                    	   value="<c:out value="${KualiForm.eachEntryForManualEdit.chartOfAccountsCode}" />">
				</display:column>

				<display:column title="Financial Object TypeCode" >
					<input size="9" type="text" name="editFinancialObjectTypeCode"
    	                   value="<c:out value="${KualiForm.eachEntryForManualEdit.financialObjectTypeCode}" />">
				</display:column>
				
				<display:column title="Financial Object Code" >
					<input size="9" type="text" name="editFinancialObjectCode"
            	           value="<c:out value="${KualiForm.eachEntryForManualEdit.financialObjectCode}" />">
				</display:column>
				
				<display:column title="Financial Sub-Object Code" >
					<input size="9" type="text" name="editFinancialSubObjectCode"
                    	   value="<c:out value="${KualiForm.eachEntryForManualEdit.financialSubObjectCode}" />">
				</display:column>
				
				<display:column title="Financial System Origination Code" >
					<input size="9" type="text" name="editFinancialSystemOriginationCode"
                    	   value="<c:out value="${KualiForm.eachEntryForManualEdit.financialSystemOriginationCode}" />">
				</display:column>
				
				<display:column title="Reference Financial System Origination Code" >
					<input size="9" type="text" name="editReferenceFinancialSystemOriginationCode"
                    	   value="<c:out value="${KualiForm.eachEntryForManualEdit.referenceFinancialSystemOriginationCode}" />">
				</display:column>
				
				<display:column title="Organization Document Number" >
					<input size="9" type="text" name="editOrganizationDocumentNumber"
                    	   value="<c:out value="${KualiForm.eachEntryForManualEdit.organizationDocumentNumber}" />">
				</display:column>
				
				<display:column title="Organization ReferenceId" >
					<input size="13" type="text" name="editOrganizationReferenceId"
                    	   value="<c:out value="${KualiForm.eachEntryForManualEdit.organizationReferenceId}" />">
				</display:column>
				
				<display:column title="Project Code" >
					<input size="9" type="text" name="editProjectCode"
                    	   value="<c:out value="${KualiForm.eachEntryForManualEdit.projectCode}" />">
				</display:column>
				
				<display:column title="Sub-Account Number" >
					<input size="13" type="text" name="editSubAccountNumber"
    	                   value="<c:out value="${KualiForm.eachEntryForManualEdit.subAccountNumber}" />">
				</display:column>
				
				<display:column title="Transaction Date" >
					<input size="12" type="text" name="editTransactionDate"
            	           value="<c:out value="${KualiForm.eachEntryForManualEdit.transactionDate}" />">
				</display:column>

				<display:column title="Transaction Debit Credit Code" >
					<input size="10" type="text" name="editTransactionDebitCreditCode"
                    	   value="<c:out value="${KualiForm.eachEntryForManualEdit.transactionDebitCreditCode}" />">
				</display:column>
				
				<display:column title="Transaction Encumbrance Update Code" >
					<input size="13" type="text" name="editTransactionEncumbranceUpdateCode"
    	                   value="<c:out value="${KualiForm.eachEntryForManualEdit.transactionEncumbranceUpdateCode}" />">
				</display:column>
				
				<display:column title="Transaction LedgerEntry Sequence Number" >
					<input size="12" type="text" name="editTransactionLedgerEntrySequenceNumber"
            	           value="<c:out value="${KualiForm.eachEntryForManualEdit.transactionLedgerEntrySequenceNumber}" />">
				</display:column>
				
				<display:column title="Transaction LedgerEntry Amount" >
					<input size="12" type="text" name="editTransactionLedgerEntryAmount"
                    	   value="<c:out value="${KualiForm.eachEntryForManualEdit.transactionLedgerEntryAmount}" />">
				</display:column>
				
				<display:column title="Transaction LedgerEntry Description" >
					<input size="12" type="text" name="editTransactionLedgerEntryDescription"
                    	   value="<c:out value="${KualiForm.eachEntryForManualEdit.transactionLedgerEntryDescription}" />">
				</display:column>
				
				<display:column title="University FiscalPeriod Code" >
					<input size="12" type="text" name="editUniversityFiscalPeriodCode"
                    	   value="<c:out value="${KualiForm.eachEntryForManualEdit.universityFiscalPeriodCode}" />">
				</display:column>
				
				<display:column title="University FiscalYear" >
					<input size="9" type="text" name="editUniversityFiscalYear"
                    	   value="<c:out value="${KualiForm.eachEntryForManualEdit.universityFiscalYear}" />">
				</display:column>
				
				<display:column title="BudgetYear" >
					<input size="12" type="text" name="editBudgetYear"
                    	   value="<c:out value="${KualiForm.eachEntryForManualEdit.budgetYear}" />">
				</display:column>
        	   
	            </display:table>
                        
   			</td>
   			
   			</table>

		
		
	
		
   		
   		<c:if test="${KualiForm.editableFlag == 'Y'}" >   
   			<div align="center" >	
		    		<input type="checkbox" name="deleteOutput" <c:if test="${deleteOutput == 'on'}"> checked="TRUE"  </c:if> > <STRONG> Delete output file? </STRONG> </input>
		    </div>
		    </br>
   			<div align="center" >	
				 <input type="image" name="methodToCall.manualErrorCorrection" 
		  			      src="images/buttonsmall_errcorr.gif" alt="error correction" class="tinybutton" />
			</div>
		</c:if>  


	
		</div>
		
 	</c:if>
      
    
      
      
      
      <c:if test="${KualiForm.editMethod == 'manual'}" >  
		<c:if test="${KualiForm.editableFlag == 'Y'}" > 
     	 <div class="tab-container" align="center"> 
	         <table cellpadding=0 class="datatable" summary=""> 
             <tr>
                <td align="left" valign="middle" class="subhead"><span class="subhead-left">Search Criteria for Manual Edit</span></td>
             </tr>

           	<c:forEach var="group" items="${KualiForm.document.correctionChangeGroup}"> 
           
     		<tr style="border-bottom: 1px solid #333;"> 
                    <td class="bord-l-b" style="padding: 4px; vertical-align: top;">
                        <!--<input type="hidden" name="correction-groups[<c:out 
                            value="${group.correctionChangeGroupLineNumber}" />][group-number]" value="" /> -->
                        <input type="hidden" name="correction-groups[<c:out 
                            value="${group.correctionChangeGroupLineNumber}" />][next-search-criterion-number]" value="<c:out 
                            value="${group.correctionCriteriaNextLineNumber}" />" /> 
                        <input type="hidden" name="correction-groups[<c:out 
                            value="${group.correctionChangeGroupLineNumber}" />][next-replacement-specification-number]" value="<c:out 
                            value="${group.correctionChangeNextLineNumber}" />" />
                        <c:forEach items="${group.correctionCriteria}" var="criterion"> 

                            <!-- Existing Search Criteria -->
                            <div>
                                <label for="correction-groups[<c:out value="${group.correctionChangeGroupLineNumber }" />][search-criteria][<c:out 
                                    value="${criterion.correctionCriteriaLineNumber}" />][field-name]">Field</label>
                                <select id="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][search-criteria][<c:out 
                                    value="${criterion.correctionCriteriaLineNumber}" />][field-name]"
                                      name="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][search-criteria][<c:out 
                                        value="${criterion.correctionCriteriaLineNumber}" />][field-name]">
                                    <option value="" selected>Select Search Criteria</option> 
                                    <c:forEach var="fieldName" items="${KualiForm.fieldNames}">
                                        <option value="<c:out value="${fieldName}" />"<c:if 
                                            test="${fieldName eq criterion.correctionFieldName}"> selected="true"</c:if>>
                                            <c:out value="${fieldName}" /> 
                                        </option>
                                    </c:forEach>
                                </select>
                                <label for="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][search-criteria][<c:out 
                                    value="${criterion.correctionCriteriaLineNumber}" />][operator]">Operator</label>
                                <select id="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][search-criteria][<c:out 
                                    value="${criterion.correctionCriteriaLineNumber}" />][operator]"
                                  name="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][search-criteria][<c:out 
                                    value="${criterion.correctionCriteriaLineNumber}" />][operator]">
                                    <option value="">Select Operator</option> 
                                    <c:forEach var="operator" items="${KualiForm.searchOperators}">
                                        <option value="<c:out value="${operator.key }" />"<c:if 
                                            test="${operator.key eq criterion.operator}">selected="true"</c:if>><c:out 
                                            value="${ operator.value}" /></option>
                                    </c:forEach>
                                </select>
                                <label for="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][search-criteria][<c:out 
                                    value="${criterion.correctionCriteriaLineNumber}" />][field-value]">Value</label>
                                <input id="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][search-criteria][<c:out 
                                    value="${criterion.correctionCriteriaLineNumber}" />][field-value]"
                                    value="${ criterion.correctionFieldValue}"
                                    name="correction-groups[<c:out value="${group.correctionChangeGroupLineNumber}" />][search-criteria][<c:out 
                                    value="${ criterion.correctionCriteriaLineNumber}" />][field-value]" type="text">
                                <input type="image" 
                                    name="methodToCall.removeSearchCriterionForManualEdit.criterion <c:out value="${criterion.correctionChangeGroupLineNumber}" />-<c:out 
                                        value="${criterion.correctionCriteriaLineNumber}" />" 
                                    src="images/tinybutton-delete1.gif" alt="delete search criterion" class="tinybutton" />
                            </div>
                        </c:forEach>

                        <!-- New Search Criteria --> 
                        <div>
                            <label for="correction-groups[<c:out value="${group.correctionChangeGroupLineNumber}" />][search-criteria][field-name]">Field</label> 
                            <select id="correction-groups[<c:out value="${group.correctionChangeGroupLineNumber}" />][search-criteria][field-name]"
                                  name="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][search-criteria][field-name]">
                                <option value="" selected="selected">Select Search Criteria</option>
                                <c:forEach var="fieldName" items="${ KualiForm.fieldNames}"><option value="<c:out 
                                    value="${fieldName}" />"><c:out value="${fieldName}" /></option></c:forEach> 
                            </select>
                            <label for="correction-groups[<c:out value="${group.correctionChangeGroupLineNumber}" />][search-criteria][operator]">Operator</label> 
                            <select id="correction-groups[<c:out value="${group.correctionChangeGroupLineNumber}" />][search-criteria][operator]"
                                  name="correction-groups[<c:out value="${ group.correctionChangeGroupLineNumber}" />][search-criteria][operator]">
                                <option value="" selected="selected">Select Operator</option>
                                <c:forEach var="operator" items="${ KualiForm.searchOperators}">
                                    <option value="<c:out value="${operator.key}" />"><c:out value="${operator.value}" /></option> 
                                </c:forEach>
                            </select>
                            <label for="correction-groups[<c:out value="${group.correctionChangeGroupLineNumber}" />][search-criteria][field-value]">Value</label> 
                            <input id="correction-groups[<c:out value="${group.correctionChangeGroupLineNumber}" />][search-criteria][field-value]"
                                  name="correction-groups[<c:out value="${group.correctionChangeGroupLineNumber}" />][search-criteria][field-value]" 
                                  type="text">
                            <html:image property="methodToCall.addSearchCriterionForManualEdit" src="images/tinybutton-add1.gif" 
                                alt="add search criteria" styleClass="tinybutton" />
                        </div>
                        
                    </td> 
                    </tr>
                      
                  		</c:forEach>
		   			
		   			
		   			
		   					 	
     		
    	 
     
		</table>
        	<div align="center" >
     	
		
		   			
		   			<input type="image" name="methodToCall.searchForManualEdit" 
			                   src="images/buttonsmall_search.gif" alt="search" class="tinybutton" />
		   		  			
	    		  	
		</div>		
       </div>
      
     	</c:if>	
      </c:if>
      
      
      
      
      
     
    
    <!-- shawn --> 
 
           
              <c:out value="${resultMessage}" /> 
          
 

 
    <kul:notes/>
    <kul:adHocRecipients editingMode="${KualiForm.editingMode}"/>        
    <kul:routeLog/>
    <kul:panelFooter/>
    <kul:documentControls transactionalDocument="false" />
 </kul:documentPage>
 
 



