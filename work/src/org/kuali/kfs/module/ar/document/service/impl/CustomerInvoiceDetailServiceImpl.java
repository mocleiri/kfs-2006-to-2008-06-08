/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.module.ar.service.impl;

import java.math.BigDecimal;

import org.kuali.module.ar.ArConstants;
import org.kuali.module.ar.bo.CustomerInvoiceDetail;
import org.kuali.module.ar.bo.OrganizationAccountingDefault;
import org.kuali.module.ar.service.CustomerInvoiceDetailService;
import org.kuali.module.ar.service.OrganizationAccountingDefaultService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CustomerInvoiceDetailServiceImpl implements CustomerInvoiceDetailService {

    private OrganizationAccountingDefaultService organizationAccountingDefaultService;
    
    /**
     * @see org.kuali.module.ar.service.CustomerInvoiceDetailService#getAddCustomerInvoiceDetail(java.lang.Integer, java.lang.String, java.lang.String)
     */
    public CustomerInvoiceDetail getCustomerInvoiceDetailForAddLine(Integer universityFiscalYear, String chartOfAccountsCode, String organizationCode) {
        CustomerInvoiceDetail customerInvoiceDetail = new CustomerInvoiceDetail();
        
        /*
        OrganizationAccountingDefault organizationAccountingDefault = organizationAccountingDefaultService.getByPrimaryKey(universityFiscalYear, chartOfAccountsCode, organizationCode);
        if( organizationAccountingDefault != null ){
            customerInvoiceDetail.setChartOfAccountsCode(organizationAccountingDefault.getDefaultInvoiceChartOfAccountsCode());
            customerInvoiceDetail.setAccountNumber(organizationAccountingDefault.getDefaultInvoiceAccountNumber());
            customerInvoiceDetail.setSubAccountNumber(organizationAccountingDefault.getDefaultInvoiceSubAccountNumber());
            customerInvoiceDetail.setFinancialObjectCode(organizationAccountingDefault.getDefaultInvoiceFinancialObjectCode());
            customerInvoiceDetail.setFinancialSubObjectCode(organizationAccountingDefault.getDefaultInvoiceFinancialSubObjectCode());
            customerInvoiceDetail.setProjectCode(organizationAccountingDefault.getDefaultInvoiceProjectCode());
        }*/
        
        customerInvoiceDetail.setInvoiceItemQuantity(new BigDecimal(1));
        customerInvoiceDetail.setInvoiceItemUnitOfMeasureCode(ArConstants.CUSTOMER_INVOICE_DETAIL_UOM_DEFAULT);
        
        return customerInvoiceDetail;
    }

    public OrganizationAccountingDefaultService getOrganizationAccountingDefaultService() {
        return organizationAccountingDefaultService;
    }

    public void setOrganizationAccountingDefaultService(OrganizationAccountingDefaultService organizationAccountingDefaultService) {
        this.organizationAccountingDefaultService = organizationAccountingDefaultService;
    }

}