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
CREATE TABLE PUR_AP_CMP_PARM_T(
        CAMPUS_CD                      VARCHAR2(2) CONSTRAINT PUR_AP_CMP_PARM_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_AP_CMP_PARM_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_AP_CMP_PARM_TN3 NOT NULL,
        CMP_PUR_DRCTR_NM               VARCHAR2(45), 
        CMP_PUR_DRCTR_TTL              VARCHAR2(45), 
        CMP_AP_EMAIL_ADDR              VARCHAR2(100), 
        PUR_INST_NM                    VARCHAR2(45), 
        PUR_DEPT_NM                    VARCHAR2(45),
        PUR_DEPT_LN1_ADDR              VARCHAR2(45),
        PUR_DEPT_LN2_ADDR              VARCHAR2(45),
        PUR_DEPT_CTY_NM                VARCHAR2(45),
        PUR_DEPT_ST_CD                 VARCHAR2(2),
        PUR_DEPT_ZIP_CD                VARCHAR2(20),
        PUR_DEPT_CNTRY_CD              VARCHAR2(2),
     CONSTRAINT PUR_AP_CMP_PARM_TP1 PRIMARY KEY (
        CAMPUS_CD),
     CONSTRAINT PUR_AP_CMP_PARM_TC0 UNIQUE (OBJ_ID)
)
/
