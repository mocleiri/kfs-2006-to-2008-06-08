/*
 * Copyright 2006-2007 The Kuali Foundation.
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
package org.kuali.module.purap;

/**
 * Holds error key constants for PURAP.
 * 
 */
public class PurapKeyConstants {

    public static final String PURAP_GENERAL_POTENTIAL_DUPLICATE = "error.document.purap.potentialDuplicate";
    public static final String PURAP_GENERAL_NO_ACCOUNTS_TO_DISTRIBUTE = "error.document.purap.noAccountsToDistribute";
    public static final String PURAP_GENERAL_NO_ITEMS_TO_DISTRIBUTE_TO = "error.document.purap.noItemsToDistributeTo";
    public static final String PURAP_GENERAL_NO_ITEMS_TO_REMOVE_ACCOUNTS_FROM = "error.document.purap.noItemsToRemoveAccountsFrom";
    public static final String PURAP_GENERAL_ACCOUNTS_DISTRIBUTED = "message.document.purap.accountsDistributed";
    public static final String PURAP_GENERAL_ACCOUNTS_REMOVED = "message.document.purap.accountsRemoved";
    
    //Purchase Order & Requisition
    public static final String ERROR_PURCHASE_ORDER_BEGIN_DATE_AFTER_END = "error.purchaseOrder.beginDateAfterEnd";
    public static final String ERROR_PURCHASE_ORDER_BEGIN_DATE_NO_END_DATE = "error.purchaseOrder.beginDateNoEndDate";
    public static final String ERROR_PURCHASE_ORDER_END_DATE_NO_BEGIN_DATE = "error.purchaseOrder.endDateNoBeginDate";
    public static final String ERROR_RECURRING_DATE_NO_TYPE = "errors.recurring.type";
    public static final String ERROR_RECURRING_TYPE_NO_DATE = "errors.recurring.dates";
    public static final String ERROR_POSTAL_CODE_INVALID = "errors.postalCode.invalid";
    public static final String ERROR_FAX_NUMBER_INVALID = "errors.faxNumber.invalid";
    public static final String ERROR_FAX_NUMBER_PO_TRANSMISSION_TYPE = "error.faxNumber.PoTransmissionType";
    public static final String REQ_TOTAL_GREATER_THAN_PO_TOTAL_LIMIT = "error.purchaseOrderTotalLimit";
    public static final String INVALID_CONTRACT_MANAGER_CODE = "error.invalidContractManagerCode";
    public static final String ERROR_REQ_COPY_EXPIRED_CONTRACT = "error.requisition.copy.expired.contract";
    public static final String ERROR_REQ_COPY_INACTIVE_VENDOR = "error.requisition.copy.inactive.vendor";
    public static final String ERROR_STIPULATION_DESCRIPTION = "error.purchaseOrder.stipulationDescriptionEmpty";
    public static final String ERROR_QUOTE_TRANSMITTED = "error.purchaseOrder.quote.transmitted";

    //Purchase Order  
    public static final String PURCHASE_ORDER_QUESTION_DOCUMENT = "purchaseOrder.question.text";
    public static final String PURCHASE_ORDER_MESSAGE_CLOSE_DOCUMENT = "purchaseOrder.route.message.close.text";
    public static final String ERROR_PURCHASE_ORDER_REASON_REQUIRED = "error.purchaseOrder.reasonRequired";    
    public static final String ERROR_PURCHASE_ORDER_STATUS_INCORRECT = "error.purchaseOrder.status.incorrect";
    public static final String ERROR_PURCHASE_ORDER_STATUS_NOT_REQUIRED_STATUS = "error.close.purchaseOrder.status.not.required.status";
    public static final String ERROR_PURCHASE_ORDER_CLOSE_NO_PREQ = "error.close.purchaseOrder.no.paymentRequest";
    public static final String ERROR_PURCHASE_ORDER_CLOSE_PREQ_IN_PROCESS = "error.close.purchaseOrder.paymentRequest.inProcess";
    public static final String PURCHASE_ORDER_MESSAGE_VOID_DOCUMENT = "purchaseOrder.route.message.void.text";
    public static final String PURCHASE_ORDER_MESSAGE_PAYMENT_HOLD = "purchaseOrder.route.message.payment.hold.text";
    public static final String PURCHASE_ORDER_MESSAGE_REMOVE_HOLD = "purchaseOrder.route.message.remove.hold.text";
    public static final String PURCHASE_ORDER_MESSAGE_REOPEN_DOCUMENT="purchaseOrder.route.message.reopen.text";
    public static final String PURCHASE_ORDER_MESSAGE_AMEND_DOCUMENT="purchaseOrder.route.message.amend.text";
    public static final String ERROR_USER_NONPURCHASING="errors.user.nonPurchasing";
    public static final String ERROR_PURCHASE_ORDER_PDF = "error.purchaseOrder.pdf";
    public static final String ERROR_PURCHASE_ORDER_TRANSMIT_PRIOR_TRANSMISSION = "error.transmit.purchaseOrder.priorTransmission";
    public static final String ERROR_PURCHASE_ORDER_TRANSMIT_INVALID_TRANSMIT_TYPE= "error.transmit.purchaseOrder.invalidTransmitType";
    public static final String WARNING_PURCHASE_ORDER_NOT_CURRENT="warning.purchaseOrder.notCurrent";
    public static final String WARNING_PURCHASE_ORDER_PENDING_ACTION_NOT_CURRENT="warning.purchaseOrder.pendingAction.notCurrent";
    public static final String WARNING_PURCHASE_ORDER_PENDING_ACTION="warning.purchaseOrder.pendingAction";
    public static final String WARNING_PURCHASE_ORDER_ENTIRE_STATUS_HISTORY="warning.purchaseOrder.entireStatusHistory";
    public static final String WARNING_PURCHASE_ORDER_ALL_NOTES="warning.purchaseOrder.allNotes";
    public static final String ERROR_PURCHASE_ORDER_CANNOT_AMEND="error.purchaseOrder.cannot.amend";
    public static final String PURCHASE_ORDER_QUESTION_CONFIRM_AWARD = "purchaseOrder.route.message.confirm.award.text";
    public static final String PURCHASE_ORDER_QUESTION_CONFIRM_CANCEL_QUOTE = "purchaseOrder.route.message.confirm.cancel.quote.text";
    public static final String PURCHASE_ORDER_CANCEL_QUOTE_NOTE_TEXT = "purchaseOrder.route.message.cancel.note.text";
    public static final String ERROR_PURCHASE_ORDER_QUOTE_NO_VENDOR_AWARDED= "error.transmit.purchaseOrder.noVendorAwarded";
    
    //Payment Request
    public static final String ERROR_PURCHASE_ORDER_NOT_EXIST="error.invoice.purchaseOrder.notExist";
    public static final String ERROR_PURCHASE_ORDER_NOT_OPEN="error.invoice.purchaseOrder.notOpen";
    public static final String ERROR_PURCHASE_ORDER_IS_PENDING="error.invoice.purchaseOrder.isPending";
    public static final String ERROR_INVALID_INVOICE_DATE="errors.invalid.invoice.date";
   
    public static final String MESSAGE_DUPLICATE_INVOICE_DATE_AMOUNT="message.duplicate.preq.date.amount";
    public static final String MESSAGE_DUPLICATE_INVOICE_DATE_AMOUNT_CANCELLEDORVOIDED="message.duplicate.invoice.date.amount.cancelledOrVoided";
    public static final String MESSAGE_DUPLICATE_INVOICE_DATE_AMOUNT_VOIDED="message.duplicate.invoice.date.amount.voided";
    public static final String MESSAGE_DUPLICATE_INVOICE_DATE_AMOUNT_CANCELLED="message.duplicate.invoice.date.amount.cancelled";
    
    public static final String MESSAGE_DUPLICATE_INVOICE="errors.duplicate.vendor.invoice";
    public static final String MESSAGE_DUPLICATE_INVOICE_CANCELLEDORVOIDED ="errors.duplicate.vendor.invoice.cancelledOrVoided";
    public static final String MESSAGE_DUPLICATE_INVOICE_CANCELLED="errors.duplicate.vendor.invoice.cancelled";
    public static final String MESSAGE_DUPLICATE_INVOICE_VOIDED="errors.duplicate.vendor.invoice.voided";
    
    public static final String MESSAGE_CLOSED_OR_EXPIRED_ACCOUNTS_REPLACED="message.closed.or.expired.accounts.replaced";
    
    public static final String PAYMENT_REQUEST_QUESTION_DOCUMENT = "paymentRequest.question.text";
    public static final String PAYMENT_REQUEST_MESSAGE_HOLD_DOCUMENT="paymentRequest.message.hold.text";
    public static final String ERROR_PAYMENT_REQUEST_REASON_REQUIRED = "error.paymentRequest.reasonRequired";
    
    //Item
    public static final String ERROR_ITEM_AMOUNT_BELOW_ZERO="errors.item.amount.belowZero";
    public static final String ERROR_ITEM_AMOUNT_NOT_BELOW_ZERO="errors.item.amount.notBelowZero";
    public static final String ERROR_ITEM_ACCOUNTING_NOT_UNIQUE="errors.item.accounting.notunique";
    public static final String ERROR_ITEM_TOTAL_NEGATIVE="errors.item.total.negative";
    public static final String ERROR_ITEM_QUANTITY_NOT_ZERO="errors.item.quantity.notZero";
    public static final String ERROR_ITEM_QUANTITY="errors.item.quantity";
    public static final String ERROR_ITEM_EMPTY="errors.item.empty";
    public static final String ERROR_ITEM_ACCOUNTING_NOT_ALLOWED="errors.item.accounting.notallowed";
    public static final String ERROR_ITEM_ACCOUNTING_INCOMPLETE="errors.item.accounting.incomplete";
    public static final String ERROR_PURCHASE_ORDER_EXCEEDING_TOTAL_LIMIT="errors.purchaseorder.exceedingTotalLimit";
    public static final String ERROR_ITEM_REQUIRED="errors.item.required";
    public static final String ERROR_ITEM_UNIT_OF_MEASURE_REQUIRED="errors.item.unitOfMeasure.required";
    public static final String ERROR_ITEM_TRADEIN_DISCOUNT_COEXISTENCE="errors.purchaseOrderItems.TradeInAndDiscountCoexistence";
    public static final String ERROR_ITEM_BELOW_THE_LINE="errors.item.belowTheLine";
    public static final String ERROR_ITEM_QUANTITY_NOT_ALLOWED="errors.item.quantity.isNotAllowed";
    public static final String ERROR_ITEM_AMND_NULL="errors.item.amnd.null";
    public static final String ERROR_ITEM_AMND_INVALID="errors.item.amnd.invalid";
    public static final String ERROR_ITEM_AMND_INVALID_AMT="errors.item.amnd.invalidAmt";
}