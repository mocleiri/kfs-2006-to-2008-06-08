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
CREATE TABLE LD_BCN_ACCTSEL_T(
        PERSON_UNVL_ID                 VARCHAR2(10) CONSTRAINT LD_BCN_ACCTSEL_TN1 NOT NULL,
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT LD_BCN_ACCTSEL_TN2 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT LD_BCN_ACCTSEL_TN3 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT LD_BCN_ACCTSEL_TN4 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT LD_BCN_ACCTSEL_TN5 NOT NULL,
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT LD_BCN_ACCTSEL_TN6 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_BCN_ACCTSEL_TN8 NOT NULL,
        ORG_LEVEL_CD                   NUMBER(7),
        ORG_FIN_COA_CD                 VARCHAR2(2),
        ORG_CD                         VARCHAR2(4),
        FDOC_STATUS_CD                 VARCHAR2(2),
        FDOC_INITIATOR_ID              VARCHAR2(10),
        FDOC_CREATE_DT                 DATE,
     CONSTRAINT LD_BCN_ACCTSEL_TP1 PRIMARY KEY (
        PERSON_UNVL_ID,
        UNIV_FISCAL_YR,
        FIN_COA_CD,
        ACCOUNT_NBR,
        SUB_ACCT_NBR,
        FDOC_NBR)
)
/
