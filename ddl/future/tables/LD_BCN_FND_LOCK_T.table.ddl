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
CREATE TABLE LD_BCN_FND_LOCK_T(
        APPT_FNDLCK_USR_ID             VARCHAR2(10) CONSTRAINT LD_BCN_FND_LOCK_TN1 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT LD_BCN_FND_LOCK_TN2 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT LD_BCN_FND_LOCK_TN3 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT LD_BCN_FND_LOCK_TN4 NOT NULL,
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT LD_BCN_FND_LOCK_TN5 NOT NULL,
        FILL1                          VARCHAR2(229) CONSTRAINT LD_BCN_FND_LOCK_TN6 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT LD_BCN_FND_LOCK_TN7 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_BCN_FND_LOCK_TN8 NOT NULL,
        FILL2                          VARCHAR2(255) CONSTRAINT LD_BCN_FND_LOCK_TN9 NOT NULL,
        FILL3                          VARCHAR2(255) CONSTRAINT LD_BCN_FND_LOCK_TN10 NOT NULL,
        FILL4                          VARCHAR2(255) CONSTRAINT LD_BCN_FND_LOCK_TN11 NOT NULL,
        FILL5                          VARCHAR2(4) CONSTRAINT LD_BCN_FND_LOCK_TN12 NOT NULL,
     CONSTRAINT LD_BCN_FND_LOCK_TP1 PRIMARY KEY (
        APPT_FNDLCK_USR_ID,
        ACCOUNT_NBR,
        SUB_ACCT_NBR,
        FIN_COA_CD,
        UNIV_FISCAL_YR,
        FILL1),
     CONSTRAINT LD_BCN_FND_LOCK_TC0 UNIQUE (OBJ_ID)
)
/
