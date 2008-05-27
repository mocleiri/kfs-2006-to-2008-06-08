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
package org.kuali.module.ar.fixture;

import org.kuali.module.ar.bo.Customer;

public enum CustomerFixture {
    CUSTOMER1("CUSTOMER1", true),
    CUSTOMER2("CUSTOMER2", true);
    
    public String customerNumber;
    public boolean customerActiveIndicator;
    
    private CustomerFixture(String customerNumber, boolean activeIndicator){
        this.customerNumber = customerNumber;
        this.customerActiveIndicator = activeIndicator;
    }
    
    public Customer createCustomer(){
        Customer customer = new Customer();
        customer.setCustomerNumber(customerNumber);
        customer.setCustomerActiveIndicator(customerActiveIndicator);
        return customer;
    }
}