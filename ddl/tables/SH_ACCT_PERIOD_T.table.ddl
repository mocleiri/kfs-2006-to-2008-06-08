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
CREATE TABLE SH_ACCT_PERIOD_T(
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT SH_ACCT_PERIOD_TN1 NOT NULL,
        UNIV_FISCAL_PRD_CD             VARCHAR2(2) CONSTRAINT SH_ACCT_PERIOD_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT SH_ACCT_PERIOD_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT SH_ACCT_PERIOD_TN4 NOT NULL,
        UNIV_FISCAL_PRD_NM             VARCHAR2(10),
        UNIV_FSCPD_STAT_CD             VARCHAR2(1),
        BDGT_ROLLOVER_CD               VARCHAR2(1),
        UNIV_FSCPD_END_DT              DATE,
     CONSTRAINT SH_ACCT_PERIOD_TP1 PRIMARY KEY (
        UNIV_FISCAL_YR,
        UNIV_FISCAL_PRD_CD),
     CONSTRAINT SH_ACCT_PERIOD_TC0 UNIQUE (OBJ_ID)
)
/
