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
ALTER TABLE CB_PND_ITM_ACCT_T
ADD (CONSTRAINT CB_PND_ITM_ACCT_TR1 FOREIGN KEY (
      PO_NUMBER,
      INVOICE_NUMBER,
      FIN_COA_CD,
      ACCOUNT_NUMBER,
      SUB_ACCT_NBR,
      ACCT_OBJ_CODE,
      FIN_SUB_OBJ_CD,
      PROJECT_CD,
      ORG_REFERENCE_ID )
REFERENCES CB_PND_INV_ACCT_T ( 
      PO_NUMBER,
      INVOICE_NUMBER,
      FIN_COA_CD,
      ACCOUNT_NUMBER,
      SUB_ACCT_NBR,
      ACCT_OBJ_CODE,
      FIN_SUB_OBJ_CD,
      PROJECT_CD,
      ORG_REFERENCE_ID ))
ADD (CONSTRAINT CB_PND_ITM_ACCT_TR2 FOREIGN KEY (
      PO_NUMBER,
      INVOICE_NUMBER,
      LINE_ITEM_NUMBER )
REFERENCES CB_PND_INV_ITEM_T ( 
      PO_NUMBER,
      INVOICE_NUMBER,
      LINE_ITEM_NUMBER ))
ADD (CONSTRAINT CB_PND_ITM_ACCT_TR3 FOREIGN KEY (
      FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT CB_PND_ITM_ACCT_TR4 FOREIGN KEY (
      FIN_COA_CD,
      ACCOUNT_NUMBER,
      SUB_ACCT_NBR )
REFERENCES CA_SUB_ACCT_T (
      FIN_COA_CD,
      ACCOUNT_NBR,
      SUB_ACCT_NBR ))
ADD (CONSTRAINT CB_PND_ITM_ACCT_TR5 FOREIGN KEY (
      FIN_COA_CD,
      ACCOUNT_NUMBER )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD,
      ACCOUNT_NBR ))
ADD (CONSTRAINT CB_PND_ITM_ACCT_TR6 FOREIGN KEY (
      UNIV_FISCAL_YR,
      FIN_COA_CD,
      ACCT_OBJ_CODE )
REFERENCES CA_OBJECT_CODE_T (
      UNIV_FISCAL_YR,
      FIN_COA_CD,
      FIN_OBJECT_CD ))
ADD (CONSTRAINT CB_PND_ITM_ACCT_TR7 FOREIGN KEY (
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
ADD (CONSTRAINT CB_PND_ITM_ACCT_TR8 FOREIGN KEY (
      PROJECT_CD )
REFERENCES CA_PROJECT_T (
      PROJECT_CD ))
ADD (CONSTRAINT CB_PND_ITM_ACCT_TR9 FOREIGN KEY (
      UNIV_FISCAL_YR,
      UNIV_FISCAL_PRD_CD )
REFERENCES SH_ACCT_PERIOD_T (
      UNIV_FISCAL_YR,
      UNIV_FISCAL_PRD_CD ))
/
