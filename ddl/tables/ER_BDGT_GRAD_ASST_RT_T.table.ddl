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
CREATE TABLE ER_BDGT_GRAD_ASST_RT_T(
        RDOC_NBR                       VARCHAR2(14) CONSTRAINT ER_BDGT_GRAD_ASST_RT_TN1 NOT NULL,
        CMP_CD                         VARCHAR2(2) CONSTRAINT ER_BDGT_GRAD_ASST_RT_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT ER_BDGT_GRAD_ASST_RT_TN3 NOT NULL,
        VER_NBR                        NUMBER(8,0) DEFAULT 1 CONSTRAINT ER_BDGT_GRAD_ASST_RT_TN4 NOT NULL,
        LST_UPDT_TS               DATE,
        CMP_MAX_PRD1_RT                NUMBER(6,2),
        CMP_MAX_PRD2_RT                NUMBER(6,2),
        CMP_MAX_PRD3_RT                NUMBER(6,2),
        CMP_MAX_PRD4_RT                NUMBER(6,2),
        CMP_MAX_PRD5_RT                NUMBER(6,2),
        CMP_MAX_PRD6_RT                NUMBER(6,2),
        DOBJ_MAINT_CD_ACTV_IND         VARCHAR2(1) CONSTRAINT ER_APPT_TYP_TN3 NOT NULL ENABLE,
     CONSTRAINT ER_BDGT_GRAD_ASST_RT_TP1 PRIMARY KEY (
        RDOC_NBR,
        CMP_CD),
     CONSTRAINT ER_BDGT_GRAD_ASST_RT_TC0 UNIQUE (OBJ_ID)
)
/

