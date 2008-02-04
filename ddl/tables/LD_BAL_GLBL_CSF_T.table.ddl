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
CREATE TABLE LD_BAL_GLBL_CSF_T(
        PERSON_UNVL_ID                 VARCHAR2(10) CONSTRAINT LD_BAL_GLBL_CSF_TN1 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT LD_BAL_GLBL_CSF_TN2 NOT NULL,
        FIN_OBJECT_CD                  VARCHAR2(4) CONSTRAINT LD_BAL_GLBL_CSF_TN3 NOT NULL,
        FIN_SUB_OBJ_CD                 VARCHAR2(3) CONSTRAINT LD_BAL_GLBL_CSF_TN4 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT LD_BAL_GLBL_CSF_TN5 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_BAL_GLBL_CSF_TN6 NOT NULL,
        BUDGET_AMT                     NUMBER(19, 2),
        CSF_AMT                        NUMBER(19, 2),
     CONSTRAINT LD_BAL_GLBL_CSF_TP1 PRIMARY KEY (
        PERSON_UNVL_ID,
        SUB_ACCT_NBR,
        FIN_OBJECT_CD,
        FIN_SUB_OBJ_CD),
     CONSTRAINT LD_BAL_GLBL_CSF_TC0 UNIQUE (OBJ_ID)
)
/