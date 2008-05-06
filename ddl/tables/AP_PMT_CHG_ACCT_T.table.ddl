/*
 * Copyright 2006 The Kuali Foundation.
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
CREATE TABLE AP_PMT_CHG_ACCT_T(
        PMT_CHG_ID                     NUMBER(10) CONSTRAINT AP_PMT_CHG_ACCT_TN1 NOT NULL,
        ITM_LN_NBR                     NUMBER(3) CONSTRAINT AP_PMT_CHG_ACCT_TN2 NOT NULL,
        FS_ORIGIN_CD                   VARCHAR2(2) CONSTRAINT AP_PMT_CHG_ACCT_TN3 NOT NULL,
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT AP_PMT_CHG_ACCT_TN4 NOT NULL,
        TRN_ENTR_SEQ_NBR               NUMBER(5) CONSTRAINT AP_PMT_CHG_ACCT_TN5 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AP_PMT_CHG_ACCT_TN6 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AP_PMT_CHG_ACCT_TN7 NOT NULL,
        ITM_ACCT_TOT_AMT               NUMBER(19,2) CONSTRAINT AP_PMT_CHG_ACCT_TN8 NOT NULL,
     CONSTRAINT AP_PMT_CHG_ACCT_TP1 PRIMARY KEY (
        PMT_CHG_ID,
        ITM_LN_NBR,
        FS_ORIGIN_CD,
        FDOC_NBR,
        TRN_ENTR_SEQ_NBR),
     CONSTRAINT AP_PMT_CHG_ACCT_TC0 UNIQUE (OBJ_ID)
)
/
