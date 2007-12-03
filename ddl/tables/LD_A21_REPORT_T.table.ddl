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
CREATE TABLE LD_A21_REPORT_T(
        A21_LBR_RPT_NBR                VARCHAR2(3) CONSTRAINT LD_A21_REPORT_TN1 NOT NULL,
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT LD_A21_REPORT_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT LD_A21_REPORT_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_A21_REPORT_TN4 NOT NULL,
        A21LBR_RPT_PRD_TTL             VARCHAR2(30),
        LBR_RPT_PRDSTAT_CD             VARCHAR2(1),
        LBR_ET_FSCL_YR                 NUMBER(4),
        LBR_ET_FSCL_PRD_CD             VARCHAR2(2),
        A21_LBR_RPT_TYP_CD             VARCHAR2(2),
        A21LBR_RPT_RTRN_DT             DATE,
        LBR_RPT_BEG_FSCL_YR            NUMBER(4),
        LBR_RPT_BEG_FSCL_PRD_CD        VARCHAR2(2),
        LBR_RPT_END_FSCL_YR            NUMBER(4),
        LBR_RPT_END_FSCL_PRD_CD        VARCHAR2(2),
     CONSTRAINT LD_A21_REPORT_TP1 PRIMARY KEY (
        A21_LBR_RPT_NBR,
        UNIV_FISCAL_YR),
     CONSTRAINT LD_A21_REPORT_TC0 UNIQUE (OBJ_ID)
)
/
