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
CREATE TABLE LD_BCN_ACCT_DUMP_T(
        PERSON_UNVL_ID                 VARCHAR2(10) CONSTRAINT LD_BCN_ACCT_DUMP_TN1 NOT NULL,
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT LD_BCN_ACCT_DUMP_TN2 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT LD_BCN_ACCT_DUMP_TN3 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT LD_BCN_ACCT_DUMP_TN4 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT LD_BCN_ACCT_DUMP_TN5 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_BCN_ACCT_DUMP_TN7 NOT NULL,
     CONSTRAINT LD_BCN_ACCT_DUMP_TP1 PRIMARY KEY (
        PERSON_UNVL_ID,
        UNIV_FISCAL_YR,
        FIN_COA_CD,
        ACCOUNT_NBR,
        SUB_ACCT_NBR)
)
/
