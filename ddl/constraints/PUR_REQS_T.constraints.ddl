/*
 * Copyright 2006-2007 The Kuali Foundation.
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
ALTER TABLE PUR_REQS_T
ADD (CONSTRAINT PUR_REQS_TR1 FOREIGN KEY (
      FDOC_NBR )
REFERENCES FP_DOC_HEADER_T (
      FDOC_NBR ))
ADD (CONSTRAINT PUR_REQS_TR2 FOREIGN KEY (
      REQS_STAT_CD )
REFERENCES PUR_REQS_STAT_T (
      REQS_STAT_CD ))
ADD (CONSTRAINT PUR_REQS_TR3 FOREIGN KEY (
      VNDR_HDR_GNRTD_ID, VNDR_DTL_ASND_ID )
REFERENCES PUR_VNDR_DTL_T (
      VNDR_HDR_GNRTD_ID, VNDR_DTL_ASND_ID ))
ADD (CONSTRAINT PUR_REQS_TR4 FOREIGN KEY (
      FND_SRC_CD )
REFERENCES PUR_FND_SRC_T (
      FND_SRC_CD ))
ADD (CONSTRAINT PUR_REQS_TR5 FOREIGN KEY (
      REQS_SRC_CD )
REFERENCES PUR_REQS_SRC_T (
      REQS_SRC_CD ))
ADD (CONSTRAINT PUR_REQS_TR6 FOREIGN KEY (
      PO_TRNS_MTHD_CD )
REFERENCES PUR_PO_TRNS_MTHD_T (
      PO_TRNS_MTHD_CD ))
ADD (CONSTRAINT PUR_REQS_TR7 FOREIGN KEY (
      PO_CST_SRC_CD )
REFERENCES PUR_PO_CST_SRC_T (
      PO_CST_SRC_CD ))
ADD (CONSTRAINT PUR_REQS_TR8 FOREIGN KEY (
      DLVY_REQ_DT_REAS_CD )
REFERENCES PUR_DLVY_REQ_DT_REAS_T (
      DLVY_REQ_DT_REAS_CD ))
ADD (CONSTRAINT PUR_REQS_TR9 FOREIGN KEY (
      RECUR_PMT_TYP_CD )
REFERENCES PUR_AP_RECUR_PMT_TYP_T (
      RECUR_PMT_TYP_CD ))
ADD (CONSTRAINT PUR_REQS_TR10 FOREIGN KEY (
      FIN_COA_CD, ORG_CD )
REFERENCES CA_ORG_T (
      FIN_COA_CD, ORG_CD ))
ADD (CONSTRAINT PUR_REQS_TR11 FOREIGN KEY (
      FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT PUR_REQS_TR12 FOREIGN KEY (
      DLVY_CMP_CD )
REFERENCES SH_CAMPUS_T (
      CAMPUS_CD ))
ADD (CONSTRAINT PUR_REQS_TR13 FOREIGN KEY (
      NON_INST_FND_ORG_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT PUR_REQS_TR14 FOREIGN KEY (
      NON_INST_FND_COA_CD, NON_INST_FND_ORG_CD )
REFERENCES CA_ORG_T (
      FIN_COA_CD, ORG_CD ))
ADD (CONSTRAINT PUR_REQS_TR15 FOREIGN KEY (
      NON_INST_FND_COA_CD, NON_INST_FND_ACCT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
ADD (CONSTRAINT PUR_REQS_TR16 FOREIGN KEY (
      NON_INST_FND_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT PUR_REQS_TR17 FOREIGN KEY (
      VNDR_CONTR_GNRTD_ID )
REFERENCES PUR_VNDR_CONTR_T (
      VNDR_CONTR_GNRTD_ID ))
ADD (CONSTRAINT PUR_REQS_TR18 FOREIGN KEY (
      CONTR_MGR_CD )
REFERENCES PUR_CONTR_MGR_T (
      CONTR_MGR_CD ))
ADD (CONSTRAINT PUR_REQS_TR19 FOREIGN KEY (
      VNDR_CNTRY_CD )
REFERENCES SH_COUNTRY_T (
      POSTAL_CNTRY_CD ))
ADD (CONSTRAINT PUR_REQS_TR20 FOREIGN KEY (
      CPTL_AST_SYS_TYP_CD )
REFERENCES PUR_CPTL_AST_SYS_TYP_T (
      CPTL_AST_SYS_TYP_CD))
/
