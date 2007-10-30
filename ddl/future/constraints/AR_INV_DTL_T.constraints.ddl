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
ALTER TABLE AR_INV_DTL_T
ADD (CONSTRAINT AR_INV_DTL_TR1 FOREIGN KEY (
      FDOC_NBR )
REFERENCES AR_INV_DOC_T ( 
      FDOC_NBR ))
ADD (CONSTRAINT AR_INV_DTL_TR2 FOREIGN KEY (
      FIN_COA_CD,
      ACCOUNT_NBR )
REFERENCES CA_ACCOUNT_T ( 
      FIN_COA_CD,
      ACCOUNT_NBR ))
ADD (CONSTRAINT AR_INV_DTL_TR3 FOREIGN KEY (
      FDOC_POST_YR,
      FIN_COA_CD,
      FIN_OBJECT_CD )
REFERENCES CA_OBJECT_CODE_T ( 
      UNIV_FISCAL_YR,
      FIN_COA_CD,
      FIN_OBJECT_CD ))
ADD (CONSTRAINT AR_INV_DTL_TR4 FOREIGN KEY (
      FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT AR_INV_DTL_TR5 FOREIGN KEY (
      FDOC_POST_YR,
      FIN_COA_CD,
      FIN_AR_OBJ_CD )
REFERENCES CA_OBJECT_CODE_T ( 
      UNIV_FISCAL_YR,
      FIN_COA_CD,
      FIN_OBJECT_CD ))
ADD (CONSTRAINT AR_INV_DTL_TR6 FOREIGN KEY (
      FIN_COA_CD,
      ACCOUNT_NBR,
      SUB_ACCT_NBR )
REFERENCES CA_SUB_ACCT_T (
      FIN_COA_CD,
      ACCOUNT_NBR,
      SUB_ACCT_NBR ))
ADD (CONSTRAINT AR_INV_DTL_TR7 FOREIGN KEY (
      FDOC_POST_YR,
      FIN_COA_CD,
      ACCOUNT_NBR,
      FIN_OBJECT_CD,
      FIN_SUB_OBJ_CD )
REFERENCES CA_SUB_OBJECT_CD_T (
      UNIV_FISCAL_YR,
      FIN_COA_CD,
      ACCOUNT_NBR,
      FIN_OBJECT_CD,
      FIN_SUB_OBJ_CD ))      
ADD (CONSTRAINT AR_INV_DTL_TR8 FOREIGN KEY (
      FDOC_POST_YR,
      FIN_COA_CD,
      ACCOUNT_NBR,
      FIN_AR_OBJ_CD,
      FIN_AR_SUB_OBJ_CD )
REFERENCES CA_SUB_OBJECT_CD_T (
      UNIV_FISCAL_YR,
      FIN_COA_CD,
      ACCOUNT_NBR,
      FIN_OBJECT_CD,
      FIN_SUB_OBJ_CD ))      
ADD (CONSTRAINT AR_INV_DTL_TR9 FOREIGN KEY (
      PROJECT_CD )
REFERENCES CA_PROJECT_T (
      PROJECT_CD ))
/
