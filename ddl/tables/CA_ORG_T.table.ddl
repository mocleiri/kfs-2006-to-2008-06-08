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
CREATE TABLE CA_ORG_T(
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT CA_ORG_TN1 NOT NULL,
        ORG_CD                         VARCHAR2(4) CONSTRAINT CA_ORG_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CA_ORG_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CA_ORG_TN4 NOT NULL,
        ORG_MGR_UNVL_ID                VARCHAR2(10),
        ORG_NM                         VARCHAR2(40),
        RC_CD                          VARCHAR2(2),
        ORG_PHYS_CMP_CD                VARCHAR2(2),
        ORG_TYP_CD                     VARCHAR2(1),
        ORG_DFLT_ACCT_NBR              VARCHAR2(7),
        ORG_LN1_ADDR                   VARCHAR2(30),
        ORG_LN2_ADDR                   VARCHAR2(30),
        ORG_CITY_NM                    VARCHAR2(25),
        ORG_STATE_CD                   VARCHAR2(2),
        ORG_ZIP_CD                     VARCHAR2(11),
        ORG_CNTRY_CD                   VARCHAR2(2),
        ORG_BEGIN_DT                   DATE,
        ORG_END_DT                     DATE,
        RPTS_TO_FIN_COA_CD             VARCHAR2(2),
        RPTS_TO_ORG_CD                 VARCHAR2(4),
        ORG_ACTIVE_CD                  VARCHAR2(1),
        ORG_IN_FP_CD                   VARCHAR2(1),
        ORG_PLNT_ACCT_NBR              VARCHAR2(7),
        CMP_PLNT_ACCT_NBR              VARCHAR2(7),
        ORG_PLNT_COA_CD                VARCHAR2(2),
        CMP_PLNT_COA_CD                VARCHAR2(2),
     CONSTRAINT CA_ORG_TP1 PRIMARY KEY (
        FIN_COA_CD,
        ORG_CD),
     CONSTRAINT CA_ORG_TC0 UNIQUE (OBJ_ID)
)
/
