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
CREATE TABLE LD_A21_PRD_STAT_T(
        LBR_RPT_PRDSTAT_CD             VARCHAR2(1) CONSTRAINT LD_A21_PRD_STAT_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT LD_A21_PRD_STAT_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_A21_PRD_STAT_TN3 NOT NULL,
        LBR_PRD_STAT_DESC              VARCHAR2(40),
     CONSTRAINT LD_A21_PRD_STAT_TP1 PRIMARY KEY (
        LBR_RPT_PRDSTAT_CD),
     CONSTRAINT LD_A21_PRD_STAT_TC0 UNIQUE (OBJ_ID)
)
/
