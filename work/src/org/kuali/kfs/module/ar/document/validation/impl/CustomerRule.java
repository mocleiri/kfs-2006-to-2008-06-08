package org.kuali.module.ar.rules;

import org.apache.log4j.Logger;
import org.kuali.core.document.MaintenanceDocument;
import org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.context.SpringContext;
import org.kuali.module.ar.ArConstants;
import org.kuali.module.ar.bo.Customer;
import org.kuali.module.ar.service.CustomerService;

public class CustomerRule extends MaintenanceDocumentRuleBase {
    protected static Logger LOG = org.apache.log4j.Logger.getLogger(CustomerRule.class);
    private Customer oldCustomer;
    private Customer newCustomer;

    /**
     * This method initializes the old and new customer
     * 
     * @param document
     */
    private void initializeAttributes(MaintenanceDocument document) {
        if (newCustomer == null) {
            newCustomer = (Customer) document.getNewMaintainableObject().getBusinessObject();
        }
        if (oldCustomer == null) {
            oldCustomer = (Customer) document.getOldMaintainableObject().getBusinessObject();
        }
    }

    /**
     * @see org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.core.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {

        boolean isValid = true;
        isValid &= super.processCustomRouteDocumentBusinessRules(document);
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        isValid &= errorMap.isEmpty();
        if (isValid) {
            initializeAttributes(document);
            isValid &= checkCustomerHasAddress(newCustomer);

            if (isValid && document.isNew() && newCustomer.getCustomerNumber() == null) {
                setCustomerNumber();
            }
        }

        return isValid;
    }

    /**
     * This method sets the new customer number
     */
    private void setCustomerNumber() {
        String customerNumber = SpringContext.getBean(CustomerService.class).getNextCustomerNumber(newCustomer);
        newCustomer.setCustomerNumber(customerNumber);
        oldCustomer.setCustomerNumber(newCustomer.getCustomerNumber());
    }

    /**
     * This method checks if the new customer has at least one address
     * 
     * @param newCustomer the new customer
     * @return true is the new customer has at least one address, false otherwise
     */
    public boolean checkCustomerHasAddress(Customer newCustomer) {
        boolean success = true;
        if (newCustomer.getCustomerAddresses().isEmpty()) {
            GlobalVariables.getErrorMap().putError(KFSConstants.MAINTENANCE_NEW_MAINTAINABLE + ArConstants.CUSTOMER_TAB_ADDRESSES, ArConstants.ERROR_AT_LEAST_ONE_ADDRESS);
        }
        return success;

    }

}
