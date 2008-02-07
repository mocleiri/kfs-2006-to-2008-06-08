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
CREATE TABLE CA_ACCT_GDLNPRPS_T(
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT CA_ACCT_GDLNPRPS_TN1 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT CA_ACCT_GDLNPRPS_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CA_ACCT_GDLNPRPS_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CA_ACCT_GDLNPRPS_TN4 NOT NULL,
        ACCT_EXP_GDLN_TXT              VARCHAR2(400),
        ACCT_INC_GDLN_TXT              VARCHAR2(400),
        ACCT_PURPOSE_TXT               VARCHAR2(400),
     CONSTRAINT CA_ACCT_GDLNPRPS_TP1 PRIMARY KEY (
        FIN_COA_CD,
        ACCOUNT_NBR),
     CONSTRAINT CA_ACCT_GDLNPRPS_TC0 UNIQUE (OBJ_ID)
)
/

