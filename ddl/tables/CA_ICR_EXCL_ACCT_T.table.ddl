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
CREATE TABLE CA_ICR_EXCL_ACCT_T(
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT CA_ICR_EXCL_ACCT_TN1 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT CA_ICR_EXCL_ACCT_TN2 NOT NULL,
        FIN_OBJ_COA_CD                 VARCHAR2(2) CONSTRAINT CA_ICR_EXCL_ACCT_TN3 NOT NULL,
        FIN_OBJECT_CD                  VARCHAR2(4) CONSTRAINT CA_ICR_EXCL_ACCT_TN4 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CA_ICR_EXCL_ACCT_TN5 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CA_ICR_EXCL_ACCT_TN6 NOT NULL,
     CONSTRAINT CA_ICR_EXCL_ACCT_TP1 PRIMARY KEY (
        FIN_COA_CD,
        ACCOUNT_NBR,
        FIN_OBJ_COA_CD,
        FIN_OBJECT_CD),
     CONSTRAINT CA_ICR_EXCL_ACCT_TC0 UNIQUE (OBJ_ID)
)
/
