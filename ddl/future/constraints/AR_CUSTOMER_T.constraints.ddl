/*
 * Copyright 2005-2006 The Kuali Foundation.
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
ALTER TABLE AR_CUSTOMER_T
ADD (CONSTRAINT AR_CUSTOMER_TR1 FOREIGN KEY (
      CUST_BNKRPTCY_CD )
REFERENCES AR_BANKRUPTCY_T ( 
      CUST_BNKRPTCY_CD ))
ADD (CONSTRAINT AR_CUSTOMER_TR2 FOREIGN KEY (
      CUST_BNKACCTTYP_CD )
REFERENCES AR_BANK_ACCT_TYP_T ( 
      CUST_BNKACCTTYP_CD ))
ADD (CONSTRAINT AR_CUSTOMER_TR3 FOREIGN KEY (
      CUST_CLCTN_STAT_CD )
REFERENCES AR_CLCTN_STATUS_T ( 
      CUST_CLCTN_STAT_CD ))
ADD (CONSTRAINT AR_CUSTOMER_TR4 FOREIGN KEY (
      CLCTR_FIN_COA_CD,
      CLCTR_ORG_CD,
      ORG_ASGN_CLCTR_ID )
REFERENCES AR_COLLECTOR_T ( 
      FIN_COA_CD,
      ORG_CD,
      ORG_ASGN_CLCTR_ID ))
ADD (CONSTRAINT AR_CUSTOMER_TR5 FOREIGN KEY (
      CUST_STOP_BILL_CD )
REFERENCES AR_STOP_BILLING_T ( 
      CUST_STOP_BILL_CD ))
ADD (CONSTRAINT AR_CUSTOMER_TR6 FOREIGN KEY (
      FIN_COA_CD,
      ORG_CD )
REFERENCES CA_ORG_T ( 
      FIN_COA_CD,
      ORG_CD ))
/
