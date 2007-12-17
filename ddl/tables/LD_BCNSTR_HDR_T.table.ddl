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
CREATE TABLE LD_BCNSTR_HDR_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT LD_BCNSTR_HDR_TN1 NOT NULL,
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT LD_BCNSTR_HDR_TN2 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT LD_BCNSTR_HDR_TN3 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT LD_BCNSTR_HDR_TN4 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT LD_BCNSTR_HDR_TN5 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT LD_BCNSTR_HDR_TN6 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_BCNSTR_HDR_TN7 NOT NULL,
        ORG_LEVEL_CD                   NUMBER(7),
        ORG_COA_OF_LVL_CD              VARCHAR2(2),
        ORG_OF_LVL_CD                  VARCHAR2(4),
        BDGT_LOCK_USR_ID               VARCHAR2(10),
        BDGT_TRNLCK_USR_ID             VARCHAR2(10),
     CONSTRAINT LD_BCNSTR_HDR_TP1 PRIMARY KEY (
        FDOC_NBR,
        UNIV_FISCAL_YR,
        FIN_COA_CD,
        ACCOUNT_NBR,
        SUB_ACCT_NBR),
     CONSTRAINT LD_BCNSTR_HDR_TC0 UNIQUE (OBJ_ID)
)
/
