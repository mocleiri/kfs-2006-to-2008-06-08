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
ALTER TABLE CM_CPTLAST_T
ADD (CONSTRAINT CM_CPTLAST_TR1 FOREIGN KEY (
      CPTLAST_TYP_CD )
REFERENCES CM_ASSET_TYPE_T (
      CPTLAST_TYP_CD ))
ADD (CONSTRAINT CM_CPTLAST_TR2 FOREIGN KEY (
      ORG_OWNER_COA_CD, ORG_OWNER_ACCT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
ADD (CONSTRAINT CM_CPTLAST_TR3 FOREIGN KEY (
      ORG_OWNER_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT CM_CPTLAST_TR4 FOREIGN KEY (
      CAMPUS_CD )
REFERENCES SH_CAMPUS_T (
      CAMPUS_CD ))
ADD (CONSTRAINT CM_CPTLAST_TR5 FOREIGN KEY (
      CAMPUS_CD, BLDG_CD, BLDG_ROOM_NBR )
REFERENCES SH_ROOM_T (
      CAMPUS_CD, BLDG_CD, BLDG_ROOM_NBR ))
ADD (CONSTRAINT CM_CPTLAST_TR6 FOREIGN KEY (
      AST_RETIR_COA_CD, AST_RETIR_ACCT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
ADD (CONSTRAINT CM_CPTLAST_TR7 FOREIGN KEY (
      AST_RETIR_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT CM_CPTLAST_TR8 FOREIGN KEY (
      AST_INS_COA_CD, AST_INS_ACCT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
ADD (CONSTRAINT CM_CPTLAST_TR9 FOREIGN KEY (
      AST_INS_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT CM_CPTLAST_TR11 FOREIGN KEY (
      FDOC_POST_YR, FDOC_POST_PRD_CD )
REFERENCES SH_ACCT_PERIOD_T (
      UNIV_FISCAL_YR, UNIV_FISCAL_PRD_CD ))
ADD (CONSTRAINT CM_CPTLAST_TR12 FOREIGN KEY (
      CAMPUS_CD, BLDG_CD )
REFERENCES SH_BUILDING_T (
      CAMPUS_CD, BLDG_CD ))
ADD (CONSTRAINT CM_CPTLAST_TR13 FOREIGN KEY (
      AST_RETIR_FSCL_YR, AST_RETIR_PRD_CD )
REFERENCES SH_ACCT_PERIOD_T (
      UNIV_FISCAL_YR, UNIV_FISCAL_PRD_CD ))
ADD (CONSTRAINT CM_CPTLAST_TR14 FOREIGN KEY (
      AST_RETIRE_REAS_CD )
REFERENCES CM_RETIRE_REAS_T (
      AST_RETIRE_REAS_CD ))
ADD (CONSTRAINT CM_CPTLAST_TR15 FOREIGN KEY (
      CASH_RCPT_FDOC_NBR )
REFERENCES FP_DOC_HEADER_T (
      FDOC_NBR ))
ADD (CONSTRAINT CM_CPTLAST_TR16 FOREIGN KEY (
      TRNFR_FND_FDOC_NBR )
REFERENCES FP_DOC_HEADER_T (
      FDOC_NBR ))
ADD (CONSTRAINT CM_CPTLAST_TR17 FOREIGN KEY (
      CPTLAST_COND_CD )
REFERENCES CM_AST_CONDITION_T (
      CPTLAST_COND_CD ))
ADD (CONSTRAINT CM_CPTLAST_TR18 FOREIGN KEY (
      AST_INVN_STAT_CD )
REFERENCES CM_AST_STATUS_T (
      AST_INVN_STAT_CD ))
ADD (CONSTRAINT CM_CPTLAST_TR19 FOREIGN KEY (
      CPTLAST_NBR )
REFERENCES CM_AST_ORG_T (
      CPTLAST_NBR ))
ADD (CONSTRAINT CM_CPTLAST_TR20 FOREIGN KEY (
      CPTLAST_NBR )
REFERENCES CM_AST_WRNTY_T (
      CPTLAST_NBR ))
/
