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
package org.kuali.workflow.attribute;

public final class RoutingAccount extends RoutingObject {
    String routingChart;
    String routingAccount;
    
    public RoutingAccount(String routingChart, String routingAccount){
        this.routingChart=routingChart;
        this.routingAccount=routingAccount;
    }
    
    public String getRoutingAccount() {
        return routingAccount;
    }
    public void setRoutingAccount(String routingAccount) {
        this.routingAccount = routingAccount;
    }
    public String getRoutingChart() {
        return routingChart;
    }
    public void setRoutingChart(String routingChart) {
        this.routingChart = routingChart;
    }

}