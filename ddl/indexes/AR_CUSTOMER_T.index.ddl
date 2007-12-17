/*
 * Copyright 2006 The Kuali Foundation.
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
CREATE INDEX AR_CUSTOMER_TI2 ON AR_CUSTOMER_T(
        CUST_BNKRPTCY_CD )
/
CREATE INDEX AR_CUSTOMER_TI3 ON AR_CUSTOMER_T(
        CUST_BNKACCTTYP_CD )
/
CREATE INDEX AR_CUSTOMER_TI4 ON AR_CUSTOMER_T(
        CUST_CLCTN_STAT_CD )
/
CREATE INDEX AR_CUSTOMER_TI5 ON AR_CUSTOMER_T(
        CLCTR_FIN_COA_CD,
        CLCTR_ORG_CD,
        ORG_ASGN_CLCTR_ID )
/
CREATE INDEX AR_CUSTOMER_TI6 ON AR_CUSTOMER_T(
        CUST_STOP_BILL_CD )
/
