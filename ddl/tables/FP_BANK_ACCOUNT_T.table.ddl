/*
 * Copyright 2005-2007 The Kuali Foundation.
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
CREATE TABLE FP_BANK_ACCOUNT_T(
        FDOC_BANK_CD                   VARCHAR2(4) CONSTRAINT FP_BANK_ACCOUNT_TN1 NOT NULL,
        FDOC_BANK_ACCT_NBR             VARCHAR2(255) CONSTRAINT FP_BANK_ACCOUNT_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_BANK_ACCOUNT_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_BANK_ACCOUNT_TN4 NOT NULL,
        FDOC_BANKACCT_DESC             VARCHAR2(40),
        CSH_OFST_FIN_COA_CD            VARCHAR2(2),
        CSH_OFST_ACCT_NBR              VARCHAR2(7),
        CSH_OFST_SUB_ACCT_NBR          VARCHAR2(5),
        CSH_OFST_OBJ_CD                VARCHAR2(4),
        CSH_OFST_SUB_OBJ_CD            VARCHAR2(3),
     CONSTRAINT FP_BANK_ACCOUNT_TP1 PRIMARY KEY (
        FDOC_BANK_CD,
        FDOC_BANK_ACCT_NBR),
     CONSTRAINT FP_BANK_ACCOUNT_TC0 UNIQUE (OBJ_ID)
)
/
