package org.kuali.module.cams.bo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.core.util.KualiDecimal;

/**
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class PendingInvoiceItem extends PersistableBusinessObjectBase {

	private String purchaseOrderNumber;
	private String invoiceNumber;
	private Long lineItemNumber;
	private String itemExpenseClass;
	private String financialSubObjectCode;
	private BigDecimal quantityInvoiced;
	private KualiDecimal lineItemCost;
	private KualiDecimal lineItemDiscount;
	private KualiDecimal lineItemAdditionalCharge;
	private String manufacturerName;
	private String purchasingCommodityCode;
	private String lineItemDescription;
	private String itemTypeCode;
	private String transactionTypeCode;
	private boolean transactionTypeServiceIndicator;
	private boolean itemAssignedToTradeInIndicator;

    private PendingItemAccount lineItem;
	private PendingReasonCode reasonPending;

	/**
	 * Default constructor.
	 */
	public PendingInvoiceItem() {

	}

	/**
	 * Gets the purchaseOrderNumber attribute.
	 * 
	 * @return Returns the purchaseOrderNumber
	 * 
	 */
	public String getPurchaseOrderNumber() { 
		return purchaseOrderNumber;
	}

	/**
	 * Sets the purchaseOrderNumber attribute.
	 * 
	 * @param purchaseOrderNumber The purchaseOrderNumber to set.
	 * 
	 */
	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}


	/**
	 * Gets the invoiceNumber attribute.
	 * 
	 * @return Returns the invoiceNumber
	 * 
	 */
	public String getInvoiceNumber() { 
		return invoiceNumber;
	}

	/**
	 * Sets the invoiceNumber attribute.
	 * 
	 * @param invoiceNumber The invoiceNumber to set.
	 * 
	 */
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}


	/**
	 * Gets the lineItemNumber attribute.
	 * 
	 * @return Returns the lineItemNumber
	 * 
	 */
	public Long getLineItemNumber() { 
		return lineItemNumber;
	}

	/**
	 * Sets the lineItemNumber attribute.
	 * 
	 * @param lineItemNumber The lineItemNumber to set.
	 * 
	 */
	public void setLineItemNumber(Long lineItemNumber) {
		this.lineItemNumber = lineItemNumber;
	}


	/**
	 * Gets the itemExpenseClass attribute.
	 * 
	 * @return Returns the itemExpenseClass
	 * 
	 */
	public String getItemExpenseClass() { 
		return itemExpenseClass;
	}

	/**
	 * Sets the itemExpenseClass attribute.
	 * 
	 * @param itemExpenseClass The itemExpenseClass to set.
	 * 
	 */
	public void setItemExpenseClass(String itemExpenseClass) {
		this.itemExpenseClass = itemExpenseClass;
	}


	/**
	 * Gets the financialSubObjectCode attribute.
	 * 
	 * @return Returns the financialSubObjectCode
	 * 
	 */
	public String getFinancialSubObjectCode() { 
		return financialSubObjectCode;
	}

	/**
	 * Sets the financialSubObjectCode attribute.
	 * 
	 * @param financialSubObjectCode The financialSubObjectCode to set.
	 * 
	 */
	public void setFinancialSubObjectCode(String financialSubObjectCode) {
		this.financialSubObjectCode = financialSubObjectCode;
	}


	/**
	 * Gets the quantityInvoiced attribute.
	 * 
	 * @return Returns the quantityInvoiced
	 * 
	 */
	public BigDecimal getQuantityInvoiced() { 
		return quantityInvoiced;
	}

	/**
	 * Sets the quantityInvoiced attribute.
	 * 
	 * @param quantityInvoiced The quantityInvoiced to set.
	 * 
	 */
	public void setQuantityInvoiced(BigDecimal quantityInvoiced) {
		this.quantityInvoiced = quantityInvoiced;
	}


	/**
	 * Gets the lineItemCost attribute.
	 * 
	 * @return Returns the lineItemCost
	 * 
	 */
	public KualiDecimal getLineItemCost() { 
		return lineItemCost;
	}

	/**
	 * Sets the lineItemCost attribute.
	 * 
	 * @param lineItemCost The lineItemCost to set.
	 * 
	 */
	public void setLineItemCost(KualiDecimal lineItemCost) {
		this.lineItemCost = lineItemCost;
	}


	/**
	 * Gets the lineItemDiscount attribute.
	 * 
	 * @return Returns the lineItemDiscount
	 * 
	 */
	public KualiDecimal getLineItemDiscount() { 
		return lineItemDiscount;
	}

	/**
	 * Sets the lineItemDiscount attribute.
	 * 
	 * @param lineItemDiscount The lineItemDiscount to set.
	 * 
	 */
	public void setLineItemDiscount(KualiDecimal lineItemDiscount) {
		this.lineItemDiscount = lineItemDiscount;
	}


	/**
	 * Gets the lineItemAdditionalCharge attribute.
	 * 
	 * @return Returns the lineItemAdditionalCharge
	 * 
	 */
	public KualiDecimal getLineItemAdditionalCharge() { 
		return lineItemAdditionalCharge;
	}

	/**
	 * Sets the lineItemAdditionalCharge attribute.
	 * 
	 * @param lineItemAdditionalCharge The lineItemAdditionalCharge to set.
	 * 
	 */
	public void setLineItemAdditionalCharge(KualiDecimal lineItemAdditionalCharge) {
		this.lineItemAdditionalCharge = lineItemAdditionalCharge;
	}


	/**
	 * Gets the manufacturerName attribute.
	 * 
	 * @return Returns the manufacturerName
	 * 
	 */
	public String getManufacturerName() { 
		return manufacturerName;
	}

	/**
	 * Sets the manufacturerName attribute.
	 * 
	 * @param manufacturerName The manufacturerName to set.
	 * 
	 */
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}


	/**
	 * Gets the purchasingCommodityCode attribute.
	 * 
	 * @return Returns the purchasingCommodityCode
	 * 
	 */
	public String getPurchasingCommodityCode() { 
		return purchasingCommodityCode;
	}

	/**
	 * Sets the purchasingCommodityCode attribute.
	 * 
	 * @param purchasingCommodityCode The purchasingCommodityCode to set.
	 * 
	 */
	public void setPurchasingCommodityCode(String purchasingCommodityCode) {
		this.purchasingCommodityCode = purchasingCommodityCode;
	}


	/**
	 * Gets the lineItemDescription attribute.
	 * 
	 * @return Returns the lineItemDescription
	 * 
	 */
	public String getLineItemDescription() { 
		return lineItemDescription;
	}

	/**
	 * Sets the lineItemDescription attribute.
	 * 
	 * @param lineItemDescription The lineItemDescription to set.
	 * 
	 */
	public void setLineItemDescription(String lineItemDescription) {
		this.lineItemDescription = lineItemDescription;
	}


	/**
	 * Gets the itemTypeCode attribute.
	 * 
	 * @return Returns the itemTypeCode
	 * 
	 */
	public String getItemTypeCode() { 
		return itemTypeCode;
	}

	/**
	 * Sets the itemTypeCode attribute.
	 * 
	 * @param itemTypeCode The itemTypeCode to set.
	 * 
	 */
	public void setItemTypeCode(String itemTypeCode) {
		this.itemTypeCode = itemTypeCode;
	}


	/**
	 * Gets the transactionTypeCode attribute.
	 * 
	 * @return Returns the transactionTypeCode
	 * 
	 */
	public String getTransactionTypeCode() { 
		return transactionTypeCode;
	}

	/**
	 * Sets the transactionTypeCode attribute.
	 * 
	 * @param transactionTypeCode The transactionTypeCode to set.
	 * 
	 */
	public void setTransactionTypeCode(String transactionTypeCode) {
		this.transactionTypeCode = transactionTypeCode;
	}


	/**
	 * Gets the transactionTypeServiceIndicator attribute.
	 * 
	 * @return Returns the transactionTypeServiceIndicator
	 * 
	 */
	public boolean isTransactionTypeServiceIndicator() { 
		return transactionTypeServiceIndicator;
	}

	/**
	 * Sets the transactionTypeServiceIndicator attribute.
	 * 
	 * @param transactionTypeServiceIndicator The transactionTypeServiceIndicator to set.
	 * 
	 */
	public void setTransactionTypeServiceIndicator(boolean transactionTypeServiceIndicator) {
		this.transactionTypeServiceIndicator = transactionTypeServiceIndicator;
	}


	/**
	 * Gets the itemAssignedToTradeInIndicator attribute.
	 * 
	 * @return Returns the itemAssignedToTradeInIndicator
	 * 
	 */
	public boolean isItemAssignedToTradeInIndicator() { 
		return itemAssignedToTradeInIndicator;
	}

	/**
	 * Sets the itemAssignedToTradeInIndicator attribute.
	 * 
	 * @param itemAssignedToTradeInIndicator The itemAssignedToTradeInIndicator to set.
	 * 
	 */
	public void setItemAssignedToTradeInIndicator(boolean itemAssignedToTradeInIndicator) {
		this.itemAssignedToTradeInIndicator = itemAssignedToTradeInIndicator;
	}


	/**
	 * Gets the lineItem attribute.
	 * 
	 * @return Returns the lineItem
	 * 
	 */
	public PendingItemAccount getLineItem() { 
		return lineItem;
	}

	/**
	 * Sets the lineItem attribute.
	 * 
	 * @param lineItem The lineItem to set.
	 * @deprecated
	 */
	public void setLineItem(PendingItemAccount lineItem) {
		this.lineItem = lineItem;
	}

	/**
	 * Gets the reasonPending attribute.
	 * 
	 * @return Returns the reasonPending
	 * 
	 */
	public PendingReasonCode getReasonPending() { 
		return reasonPending;
	}

	/**
	 * Sets the reasonPending attribute.
	 * 
	 * @param reasonPending The reasonPending to set.
	 * @deprecated
	 */
	public void setReasonPending(PendingReasonCode reasonPending) {
		this.reasonPending = reasonPending;
	}

	/**
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
	    LinkedHashMap m = new LinkedHashMap();	    
        m.put("purchaseOrderNumber", this.purchaseOrderNumber);
        m.put("invoiceNumber", this.invoiceNumber);
        if (this.lineItemNumber != null) {
            m.put("lineItemNumber", this.lineItemNumber.toString());
        }
	    return m;
    }
}
