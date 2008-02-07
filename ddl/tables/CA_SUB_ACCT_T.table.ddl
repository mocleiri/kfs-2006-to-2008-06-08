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
CREATE TABLE CA_SUB_ACCT_T(
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT CA_SUB_ACCT_TN1 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT CA_SUB_ACCT_TN2 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT CA_SUB_ACCT_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CA_SUB_ACCT_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CA_SUB_ACCT_TN5 NOT NULL,
        SUB_ACCT_NM                    VARCHAR2(40),
        SUB_ACCT_ACTV_CD               VARCHAR2(1),
        FIN_RPT_CHRT_CD                VARCHAR2(2),
        FIN_RPT_ORG_CD                 VARCHAR2(4),
        FIN_RPT_CD                     VARCHAR2(10),
     CONSTRAINT CA_SUB_ACCT_TP1 PRIMARY KEY (
        FIN_COA_CD,
        ACCOUNT_NBR,
        SUB_ACCT_NBR),
     CONSTRAINT CA_SUB_ACCT_TC0 UNIQUE (OBJ_ID)
)
/
