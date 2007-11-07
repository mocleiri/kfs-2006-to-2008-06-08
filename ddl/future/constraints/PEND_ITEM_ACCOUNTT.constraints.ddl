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
ADD (CONSTRAINT PEND_ITEM_ACCOUNTTR3 FOREIGN KEY (
      FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT PEND_ITEM_ACCOUNTTR4 FOREIGN KEY (
      FIN_COA_CD,
      ACCOUNT_NUMBER,
      SUB_ACCT_NBR )
REFERENCES CA_SUB_ACCT_T (
      FIN_COA_CD,
      ACCOUNT_NBR,
      SUB_ACCT_NBR ))
ADD (CONSTRAINT PEND_ITEM_ACCOUNTTR5 FOREIGN KEY (
      FIN_COA_CD,
      ACCOUNT_NUMBER )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD,
      ACCOUNT_NBR ))
ADD (CONSTRAINT PEND_ITEM_ACCOUNTTR6 FOREIGN KEY (
      UNIV_FISCAL_YR,
      FIN_COA_CD,
      ACCT_OBJ_CODE )
REFERENCES CA_OBJECT_CODE_T (
      UNIV_FISCAL_YR,
      FIN_COA_CD,
      FIN_OBJECT_CD ))
ADD (CONSTRAINT PEND_ITEM_ACCOUNTTR7 FOREIGN KEY (
      UNIV_FISCAL_YR,
      FIN_COA_CD,
      ACCOUNT_NUMBER,
      ACCT_OBJ_CODE,
      FIN_SUB_OBJ_CD )
REFERENCES CA_SUB_OBJECT_CD_T (
      UNIV_FISCAL_YR,
      FIN_COA_CD,
      ACCOUNT_NBR,
      FIN_OBJECT_CD,
      FIN_SUB_OBJ_CD ))
ADD (CONSTRAINT PEND_ITEM_ACCOUNTTR8 FOREIGN KEY (
      PROJECT_CD )
REFERENCES CA_PROJECT_T (
      PROJECT_CD ))
ADD (CONSTRAINT PEND_ITEM_ACCOUNTTR9 FOREIGN KEY (
      UNIV_FISCAL_YR,
      UNIV_FISCAL_PRD_CD )
REFERENCES SH_ACCT_PERIOD_T (
      UNIV_FISCAL_YR,
      UNIV_FISCAL_PRD_CD ))
/
