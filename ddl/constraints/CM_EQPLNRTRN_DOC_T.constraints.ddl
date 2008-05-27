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
ALTER TABLE CM_EQPLNRTRN_DOC_T
ADD (CONSTRAINT CM_EQPLNRTRN_DOC_TR1 FOREIGN KEY (
      FDOC_NBR )
REFERENCES FP_DOC_HEADER_T (
      FDOC_NBR ))
ADD (CONSTRAINT CM_EQPLNRTRN_DOC_TR2 FOREIGN KEY (
      AST_INS_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT CM_EQPLNRTRN_DOC_TR3 FOREIGN KEY (
      AST_INS_COA_CD, AST_INS_ACCT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
ADD (CONSTRAINT CM_EQPLNRTRN_DOC_TR4 FOREIGN KEY (
      CPTLAST_INS_CD )
REFERENCES CM_INS_T (
      CPTLAST_INS_CD ))
ADD (CONSTRAINT CM_EQPLNRTRN_DOC_TR5 FOREIGN KEY (
      AST_BORWR_STATE_CD )
REFERENCES SH_STATE_T (
      POSTAL_STATE_CD ))
ADD (CONSTRAINT CM_EQPLNRTRN_DOC_TR6 FOREIGN KEY (
      AST_BORWRSTRGST_CD )
REFERENCES SH_STATE_T (
      POSTAL_STATE_CD ))
ADD (CONSTRAINT CM_EQPLNRTRN_DOC_TR7 FOREIGN KEY (
      AST_BORWR_CNTRY_CD )
REFERENCES SH_COUNTRY_T (
      POSTAL_CNTRY_CD ))
ADD (CONSTRAINT CM_EQPLNRTRN_DOC_TR8 FOREIGN KEY (
      AST_BORWRSTRCNT_CD )
REFERENCES SH_COUNTRY_T (
      POSTAL_CNTRY_CD ))
ADD (CONSTRAINT CM_EQPLNRTRN_DOC_TR9 FOREIGN KEY (
      CPTLAST_NBR )
REFERENCES CM_CPTLAST_T (
      CPTLAST_NBR ))
/
