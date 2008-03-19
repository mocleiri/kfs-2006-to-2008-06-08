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
CREATE TABLE ER_BDGT_FRNG_RT_T(
        RDOC_NBR                       VARCHAR2(14) CONSTRAINT ER_BDGT_FRNG_RT_TN1 NOT NULL,
        INST_APPT_TYP_CD               VARCHAR2(2) CONSTRAINT ER_BDGT_FRNG_RT_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT ER_BDGT_FRNG_RT_TN3 NOT NULL,
        VER_NBR                        NUMBER(8,0) DEFAULT 1 CONSTRAINT ER_BDGT_FRNG_RT_TN4 NOT NULL,
        BDGT_LST_UPDT_TS               DATE,
        CG_FRNG_RT_AMT                 NUMBER(5,4),
        INST_CST_SHR_FRNG_RT_AMT       NUMBER(5,4),
     CONSTRAINT ER_BDGT_FRNG_RT_TP1 PRIMARY KEY (
        RDOC_NBR,
        INST_APPT_TYP_CD),
     CONSTRAINT ER_BDGT_FRNG_RT_TC0 UNIQUE (OBJ_ID)
)
/
