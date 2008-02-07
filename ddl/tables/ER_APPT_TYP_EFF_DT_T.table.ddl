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
CREATE TABLE ER_APPT_TYP_EFF_DT_T(
        INST_APPT_TYP_CD               VARCHAR2(2) CONSTRAINT ER_APPT_TYP_EFF_DT_TN1 NOT NULL,
        INST_FISCAL_YR                 NUMBER(4) CONSTRAINT ER_APPT_TYP_EFF_DT_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT ER_APPT_TYP_EFF_DT_TN3 NOT NULL,
        VER_NBR                        NUMBER(8,0) DEFAULT 1,
        APPT_TYP_BEG_DT                DATE,
        APPT_TYP_END_DT                DATE,
     CONSTRAINT ER_APPT_TYP_EFF_DT_TP1 PRIMARY KEY (
        INST_APPT_TYP_CD,
        INST_FISCAL_YR),
     CONSTRAINT ER_APPT_TYP_EFF_DT_TC0 UNIQUE (OBJ_ID)
)
/
