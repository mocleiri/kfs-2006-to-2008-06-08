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
CREATE TABLE LD_A21_RPT_TYP_T(
        A21_LBR_RPT_TYP_CD             VARCHAR2(2) CONSTRAINT LD_A21_RPT_TYP_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT LD_A21_RPT_TYP_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_A21_RPT_TYP_TN3 NOT NULL,
        A21_LBR_RPT_DESC               VARCHAR2(40),
        ROW_ACTV_IND                   VARCHAR2(1) CONSTRAINT LD_A21_RPT_TYP_TN4 NOT NULL,
     CONSTRAINT LD_A21_RPT_TYP_TP1 PRIMARY KEY (
        A21_LBR_RPT_TYP_CD),
     CONSTRAINT LD_A21_RPT_TYP_TC0 UNIQUE (OBJ_ID)
)
/