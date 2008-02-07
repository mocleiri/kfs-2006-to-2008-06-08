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
CREATE TABLE AP_CRDT_MEMO_ACCT_T(
        CRDT_MEMO_ACCT_ID              NUMBER(10) CONSTRAINT AP_CRDT_MEMO_ACCT_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AP_CRDT_MEMO_ACCT_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AP_CRDT_MEMO_ACCT_TN3 NOT NULL,
        CRDT_MEMO_ITM_ID               NUMBER(10),
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT AP_CRDT_MEMO_ACCT_TN4 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT AP_CRDT_MEMO_ACCT_TN5 NOT NULL,
        FIN_OBJECT_CD                  VARCHAR2(4) CONSTRAINT AP_CRDT_MEMO_ACCT_TN6 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5),
        FIN_SUB_OBJ_CD                 VARCHAR2(3),
        PROJECT_CD                     VARCHAR2(10),
        ORG_REFERENCE_ID               VARCHAR2(8),
        ITM_ACCT_TOT_AMT               NUMBER(19,2) CONSTRAINT AP_CRDT_MEMO_ACCT_TN7 NOT NULL,
        ACLN_PCT                       NUMBER(35,20) CONSTRAINT AP_CRDT_MEMO_ACCT_TN8 NOT NULL,
     CONSTRAINT AP_CRDT_MEMO_ACCT_TP1 PRIMARY KEY (
        CRDT_MEMO_ACCT_ID),
     CONSTRAINT AP_CRDT_MEMO_ACCT_TC0 UNIQUE (OBJ_ID)
)
/
