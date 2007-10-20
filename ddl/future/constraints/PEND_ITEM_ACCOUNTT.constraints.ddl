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
ALTER TABLE PEND_ITEM_ACCOUNTT
ADD (CONSTRAINT PEND_ITEM_ACCOUNTTR1 FOREIGN KEY (
      PO_NUMBER,
      INVOICE_NUMBER,
      FIN_COA_CD,
      ACCOUNT_NUMBER,
      SUB_ACCT_NBR,
      ACCT_OBJ_CODE,
      FIN_SUB_OBJ_CD,
      PROJECT_CD,
      ORG_REFERENCE_ID )
REFERENCES PEND_INVOICE_ACCTT ( 
      PO_NUMBER,
      INVOICE_NUMBER,
      FIN_COA_CD,
      ACCOUNT_NUMBER,
      SUB_ACCT_NBR,
      ACCT_OBJ_CODE,
      FIN_SUB_OBJ_CD,
      PROJECT_CD,
      ORG_REFERENCE_ID ))
ADD (CONSTRAINT PEND_ITEM_ACCOUNTTR2 FOREIGN KEY (
      PO_NUMBER,
      INVOICE_NUMBER,
      LINE_ITEM_NUMBER )
REFERENCES PEND_INVOICE_ITEMT ( 
      PO_NUMBER,
      INVOICE_NUMBER,
      LINE_ITEM_NUMBER ))
/
