/*
 * Copyright 2005-2007 The Kuali Foundation.
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
CREATE TABLE CG_AWD_T(
        CGPRPSL_NBR                    NUMBER(12) CONSTRAINT CG_AWD_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CG_AWD_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CG_AWD_TN3 NOT NULL,
        CGAWD_BEG_DT                   DATE,
        CGAWD_END_DT                   DATE,
        CGAWD_TOT_AMT                  NUMBER(19, 2),
        CGAWD_ADDENDUM_NBR             VARCHAR2(7),
        CGAWD_ALOC_UCS_AMT             NUMBER(19, 2),
        CG_AGENCY_AWD_NBR              VARCHAR2(27),
        CG_FEDPT_FND_AMT               NUMBER(19, 2),
        CGAWD_ENTRY_DT                 DATE,
        CG_AGENCY_FUT1_AMT             NUMBER(19, 2),
        CG_AGENCY_FUT2_AMT             NUMBER(19, 2),
        CG_AGENCY_FUT3_AMT             NUMBER(19, 2),
        CGAWD_DOC_NBR                  VARCHAR2(12),
        CGAWD_LST_UPDT_DT              DATE,
        CG_FEDPT_IND                   VARCHAR2(1),
        CG_OLD_PRPSL_NBR               VARCHAR2(8),
        CGAWD_DRCT_CST_AMT             NUMBER(19, 2),
        CGAWD_INDR_CST_AMT             NUMBER(19, 2),
        CG_FED_FNDED_AMT               NUMBER(19, 2),
        CGAWD_CREATE_TS                DATE,
        CGAWD_CLOSING_DT               DATE,
        CGPRPSL_AWD_TYP_CD             VARCHAR2(1),
        WRKGRP_NM                      VARCHAR2(70),
        CGAWD_STAT_CD                  VARCHAR2(2),
        CG_LTRCR_FNDGRP_CD             VARCHAR2(4),
        CG_GRANT_DESC_CD               VARCHAR2(3),
        CG_AGENCY_NBR                  VARCHAR2(5),
        CG_FEDPT_AGNCY_NBR             VARCHAR2(5),
        CG_AGNCY_ANALST_NM             VARCHAR2(40),
        CG_ANALYST_PHN_NBR             VARCHAR2(10),
        CGAWD_PROJ_TTL                 VARCHAR2(250),
        CGAWD_COMMENT_TXT              VARCHAR2(250),
        CGAWD_PURPOSE_CD               VARCHAR2(1),
        ROW_ACTV_IND                   VARCHAR2(1) CONSTRAINT CG_AWD_TN4 NOT NULL,
     CONSTRAINT CG_AWD_TP1 PRIMARY KEY (
        CGPRPSL_NBR),
     CONSTRAINT CG_AWD_TC0 UNIQUE (OBJ_ID)
)
/
