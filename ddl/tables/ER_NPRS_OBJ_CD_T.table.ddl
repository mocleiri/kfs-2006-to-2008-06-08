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
CREATE TABLE ER_NPRS_OBJ_CD_T(
        BDGT_NPRS_CTGRY_CD             VARCHAR2(2) CONSTRAINT ER_NPRS_OBJ_CD_TN1 NOT NULL,
        BDGT_NPRS_SUB_CTGRY_CD         VARCHAR2(3) CONSTRAINT ER_NPRS_OBJ_CD_TN2 NOT NULL,
        VER_NBR                        NUMBER(8,0) DEFAULT 1,
        BDGT_NPRS_OBJ_CD               VARCHAR2(4),
        DOBJ_MAINT_CD_ACTV_IND         VARCHAR2(1) CONSTRAINT ER_NPRS_OBJ_CD_TN4 NOT NULL ENABLE,
     CONSTRAINT ER_NPRS_OBJ_CD_TP1 PRIMARY KEY (
        BDGT_NPRS_CTGRY_CD,
        BDGT_NPRS_SUB_CTGRY_CD)
)
/

