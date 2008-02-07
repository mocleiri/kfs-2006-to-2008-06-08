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
CREATE TABLE FP_DEP_CSH_RCPT_T(
        FDOC_DPST_NBR                  VARCHAR2(14) CONSTRAINT FP_DEP_CSH_RCPT_TN1 NOT NULL,
        FDOC_LINE_NBR                  NUMBER(7) CONSTRAINT FP_DEP_CSH_RCPT_TN2 NOT NULL,
        FDOC_CSH_RCPT_NBR              VARCHAR2(14) CONSTRAINT FP_DEP_CSH_RCPT_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_DEP_CSH_RCPT_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_DEP_CSH_RCPT_TN5 NOT NULL,
        FS_CSHRCPT_PRCS_TS             DATE,
        FS_PRCS_OPR_ID                 VARCHAR2(10),
     CONSTRAINT FP_DEP_CSH_RCPT_TP1 PRIMARY KEY (
        FDOC_DPST_NBR,
        FDOC_LINE_NBR,
        FDOC_CSH_RCPT_NBR),
     CONSTRAINT FP_DEP_CSH_RCPT_TC0 UNIQUE (OBJ_ID)
)
/
