/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

function EffortAmountUpdator(){ 
	totalAmountFiledName ="document.totalOriginalPayrollAmount";
	editableDetailLineTableId = "editableDetailLineTable";
	detailLinesPrefix = "document.effortCertificationDetailLines";
	
	fiscalYearFieldNameSuffix = ".universityFiscalYear";
	objectCodeFieldNameSuffix = ".financialObjectCode";
	chartOfAccountsCodeFieldNameSuffix = ".chartOfAccountsCode";
	
	payrollAmountFieldNameSuffix = ".effortCertificationPayrollAmount";
	effortPercentFieldNameSuffix = ".effortCertificationUpdatedOverallPercent";
	fringeBenefitFieldNameSuffix = ".fringeBenefitAmount"
	
	federalIndicatorFieldNameSuffix = ".federalOrFederalPassThroughIndicator";
	
	spanSuffix = ".span";
	divSuffix = ".div";
	readonlySuffix = ".readonly";
	comma = ",";
	percentageSign = "%";
}

// recalculate the payroll amount when the effort percent is changed
EffortAmountUpdator.prototype.recalculatePayrollAmount = function(effortPercentFieldName, payrollAmountFieldName){
	var fieldNamePrefix = findElPrefix(effortPercentFieldName);
	var totalPayrollAmount = this.removeDelimator(DWRUtil.getValue(totalAmountFiledName), comma);
	var effortPercent = parseInt(this.removeDelimator(DWRUtil.getValue(effortPercentFieldName), comma));
	
	if(effortPercent > 100 || effortPercent <0){
		return;
	}
	
	if(totalPayrollAmount != '' && effortPercent != ''){
		var updatePayrollAmount = {
			callback:function(data) {
				var amount = new Number(data).toFixed(2);
				var percent = effortPercent + percentageSign;
				
				var	payrollAmountFieldName = fieldNamePrefix + payrollAmountFieldNameSuffix;
				effortAmountUpdator.setValueByElementId( payrollAmountFieldName, amount);
				
				var payrollAmountSpanReadonly = payrollAmountFieldName + spanSuffix + readonlySuffix;			
				effortAmountUpdator.setValueByElementId( payrollAmountSpanReadonly, amount);
				
				var effortPercentSpanReadonly = effortPercentFieldName + spanSuffix + readonlySuffix;				
				effortAmountUpdator.setValueByElementId( effortPercentSpanReadonly, percent);
				
				effortAmountUpdator.recalculateFringeBenefit(fieldNamePrefix, data);
			}
		};
		
		PayrollAmountUtil.recalculatePayrollAmount(totalPayrollAmount, effortPercent, updatePayrollAmount);
	}
};

// recalculate the effort percent when the payroll amount is changed
EffortAmountUpdator.prototype.recalculateEffortPercent = function(payrollAmountFieldName, effortPercentFieldName){
	var fieldNamePrefix = findElPrefix(payrollAmountFieldName);
	var totalPayrollAmount = parseFloat(this.removeDelimator(DWRUtil.getValue(totalAmountFiledName), comma));
	var payrollAmount = this.removeDelimator(DWRUtil.getValue(payrollAmountFieldName), comma);	
	
	if(payrollAmount > totalPayrollAmount || payrollAmount <0){
		return;
	}
	
	if(totalPayrollAmount != '' && payrollAmount != ''){
		var updateEffortPercent = {
			callback:function(data) {
				var percent = Math.round(data);
				var amount = new Number(payrollAmount).toFixed(2);
				
				var effortPercentFieldName = fieldNamePrefix + effortPercentFieldNameSuffix;
				effortAmountUpdator.setValueByElementId( effortPercentFieldName, percent);	
				
				var effortPercentSpanReadonly = effortPercentFieldName + spanSuffix + readonlySuffix;											
				effortAmountUpdator.setValueByElementId( effortPercentSpanReadonly, percent + percentageSign);
								
				var payrollAmountSpanReadonly = payrollAmountFieldName + spanSuffix + readonlySuffix;										
				effortAmountUpdator.setValueByElementId( payrollAmountSpanReadonly, amount);
				
				effortAmountUpdator.recalculateFringeBenefit(fieldNamePrefix, payrollAmount);
			}
		};
		
		PayrollAmountUtil.recalculateEffortPercent(totalPayrollAmount, payrollAmount, updateEffortPercent);				
	}
};

// recalculate the fringe benefit when the payroll amount is changes
EffortAmountUpdator.prototype.recalculateFringeBenefit = function(fieldNamePrefix, payrollAmount){
	try{
		var fiscalYear = DWRUtil.getValue(fieldNamePrefix + fiscalYearFieldNameSuffix);
		var objectCode = DWRUtil.getValue(fieldNamePrefix + objectCodeFieldNameSuffix);
		var chartOfAccountsCode = DWRUtil.getValue(fieldNamePrefix + chartOfAccountsCodeFieldNameSuffix);	

		if(fiscalYear != '' && objectCode!='' && chartOfAccountsCode != '') {				
			var updateFringeBenefit = function(data) {				
				var benefit = new Number(data).toFixed(2);
												
				var benefitFieldName = fieldNamePrefix + fringeBenefitFieldNameSuffix;	
				effortAmountUpdator.setValueByElementId( benefitFieldName, benefit);
				
				var benefitFieldNameSpan = benefitFieldName + spanSuffix;	
				effortAmountUpdator.setValueByElementId( benefitFieldNameSpan, benefit);
				
				var benefitFieldNameReadonly = benefitFieldNameSpan + readonlySuffix;
				effortAmountUpdator.setValueByElementId( benefitFieldNameReadonly, benefit);
				
				effortAmountUpdator.updateTotals();
			};
			
			LaborModuleService.calculateFringeBenefit(fiscalYear, chartOfAccountsCode, objectCode, payrollAmount, updateFringeBenefit);
		}
	}catch(err){
  		window.status = err.description;
	}
};

EffortAmountUpdator.prototype.setValueByElementId = function(elementId, value){
	if(document.getElementById(elementId) != null || document.getElementsByName(elementId).length > 0){
		DWRUtil.setValue( elementId, value);
	}
};

// remove the specified delimators and leading/trailing blanks from the given string
EffortAmountUpdator.prototype.removeDelimator = function(stringObject, delimator){
	return stringObject.replace(delimator, "").trim();
};

// format the given number as the currency format
EffortAmountUpdator.prototype.formatNumberAsCurrency = function(number, currencySymbol) {
	if(currencySymbol == null){
		currencySymbol = "";
	}
	
	// get the fraction part of the given number
	var fractionRegex = /\.\d{1,}/;	
	var fractionPart = (fractionRegex.test(number)) ? fractionRegex.exec(number) : "";
	
	// get the integer part of the given number and format it through putting commas
	var integerPart = parseInt(number,10).toString( );
	var integerRegex = /(-?\d+)(\d{3})/;	
	while (integerRegex.test(integerPart)) {
		integerPart = integerPart.replace(integerRegex, "$1,$2");
	}
	
	return currencySymbol + integerPart + fractionPart;
};

EffortAmountUpdator.prototype.updateTotals = function(){
	// update the payroll amount totals
	totalFieldId = "document.totalPayrollAmount";
	federalTotalFieldId = "document.federalTotalPayrollAmount";
	otherTotalFieldId = "document.otherTotalPayrollAmount";
	this.updateTotalField(editableDetailLineTableId, payrollAmountFieldNameSuffix, false, totalFieldId, federalTotalFieldId, otherTotalFieldId);

	// update the effort percent totals
	totalFieldId = "document.totalEffortPercent";
	federalTotalFieldId = "document.federalTotalEffortPercent";
	otherTotalFieldId = "document.otherTotalEffortPercent";
	this.updateTotalField(editableDetailLineTableId, effortPercentFieldNameSuffix, true, totalFieldId, federalTotalFieldId, otherTotalFieldId);
	
	// update the fringe benefit totals
	totalFieldId = "document.totalFringeBenefit";
	federalTotalFieldId = "document.federalTotalFringeBenefit";
	otherTotalFieldId = "document.otherTotalFringeBenefit";
	this.updateTotalField(editableDetailLineTableId, fringeBenefitFieldNameSuffix, false, totalFieldId, federalTotalFieldId, otherTotalFieldId);
};

EffortAmountUpdator.prototype.updateTotalField = function(detailLineTableId, amountFieldSuffix, isPercent, totalFieldId, federalTotalFieldId, otherTotalFieldId){
	var federalTotal = 0.0;
	var otherTotal = 0.0;
	
	var numOfTableRows = document.getElementById(detailLineTableId).rows.length;  	
  	for (var index = 0; index < numOfTableRows; index++) {
  		var indexHolder = "[" + index + "]";
  		var amountFieldId = detailLinesPrefix + indexHolder + amountFieldSuffix;
  		var fereralIndicatorFieldId = detailLinesPrefix + indexHolder + federalIndicatorFieldNameSuffix;
  		
  		var nodes = document.getElementsByName(amountFieldId);
  		if(document.getElementById(amountFieldId) == null && nodes.length <= 0){
  			continue;
  		}
  		
  		var lineAmount = parseFloat(this.removeDelimator(DWRUtil.getValue(amountFieldId), comma));  		
  		var federalIndicator = DWRUtil.getValue(fereralIndicatorFieldId); 		
  		if(federalIndicator.toUpperCase() == "YES"){
  			federalTotal += lineAmount;	
  		}
  		else{
  			otherTotal += lineAmount;
  		} 
 	}
 	
 	var formattedFederalTotal, formattedOtherTotal, formattedGrandTotal;
 	if(!isPercent){
	 	formattedFederalTotal = this.formatNumberAsCurrency(new Number(federalTotal).toFixed(2));
	 	formattedOtherTotal = this.formatNumberAsCurrency(new Number(otherTotal).toFixed(2));
	 	formattedGrandTotal = this.formatNumberAsCurrency(new Number(federalTotal + otherTotal).toFixed(2));
 	}
 	else{
 		formattedFederalTotal = this.formatNumberAsCurrency(Math.round(federalTotal)) + percentageSign;
	 	formattedOtherTotal = this.formatNumberAsCurrency(Math.round(otherTotal)) + percentageSign;
	 	formattedGrandTotal = this.formatNumberAsCurrency(Math.round(federalTotal + otherTotal)) + percentageSign;
 	}
 	
 	this.setValueByElementId( federalTotalFieldId, formattedFederalTotal);
 	this.setValueByElementId( otherTotalFieldId, formattedOtherTotal);
 	this.setValueByElementId( totalFieldId, formattedGrandTotal);
 	
 	this.setValueByElementId( federalTotalFieldId + readonlySuffix, formattedFederalTotal);
 	this.setValueByElementId( otherTotalFieldId + readonlySuffix, formattedOtherTotal);
 	this.setValueByElementId( totalFieldId + readonlySuffix, formattedGrandTotal);
};

var effortAmountUpdator = new EffortAmountUpdator();