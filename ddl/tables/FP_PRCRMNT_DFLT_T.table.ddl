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
CREATE TABLE FP_PRCRMNT_DFLT_T(
        PURCH_CCRD_NBR                 VARCHAR2(16) CONSTRAINT FP_PRCRMNT_DFLT_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT FP_PRCRMNT_DFLT_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_PRCRMNT_DFLT_TN3 NOT NULL,
        PURCH_CCRD_USR_NM              VARCHAR2(35),
        FIN_COA_CD                     VARCHAR2(2),
        ACCOUNT_NBR                    VARCHAR2(7),
        FIN_OBJECT_CD                  VARCHAR2(4),
        SUB_ACCT_NBR                   VARCHAR2(5),
        FIN_SUB_OBJ_CD                 VARCHAR2(3),
        PROJECT_CD                     VARCHAR2(10),
        PURCH_CCRD_ACTV_IND            CHAR(1),
     CONSTRAINT FP_PRCRMNT_DFLT_TP1 PRIMARY KEY (
        PURCH_CCRD_NBR),
     CONSTRAINT FP_PRCRMNT_DFLT_TC0 UNIQUE (OBJ_ID)
)
/
