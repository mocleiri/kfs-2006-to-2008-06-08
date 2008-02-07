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
CREATE TABLE CG_PRPSL_T(
        CGPRPSL_NBR                    NUMBER(12) CONSTRAINT CG_PRPSL_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CG_PRPSL_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CG_PRPSL_TN3 NOT NULL,
        CGPRPSL_BEG_DT                 DATE,
        CGPRPSL_END_DT                 DATE,
        CGPRPSL_TOT_AMT                NUMBER(19, 2),
        CGPRPSL_DRCTCS_AMT             NUMBER(19, 2),
        CGPRPSL_INDRCS_AMT             NUMBER(19, 2),
        CGPRPSL_REJECTD_DT             DATE,
        CGPRPSL_LSTUPDT_DT             DATE,
        CGPRPSL_DUE_DT                 DATE,
        CGPRPSL_TOTPRJ_AMT             NUMBER(19, 2),
        CGPRPSL_SUBMSSN_DT             DATE,
        CGPRPSL_FEDPT_IND              VARCHAR2(1),
        CG_OLD_PRPSL_NBR               VARCHAR2(8),
        CG_GRANT_NBR                   VARCHAR2(27),
        CGPRPSL_CLOSING_DT             DATE,
        CGPRPSL_AWD_TYP_CD             VARCHAR2(1),
        CG_AGENCY_NBR                  VARCHAR2(5),
        CGPRPSL_STAT_CD                VARCHAR2(2),
        CG_FEDPT_AGNCY_NBR             VARCHAR2(5),
        CG_CFDA_NBR                    VARCHAR2(6),
        CGPRPSL_FELLOW_NM              VARCHAR2(30),
        CGPRPSL_PURPOSE_CD             VARCHAR2(1),
        CGPRPSL_PROJ_TTL               VARCHAR2(250),
        ROW_ACTV_IND                   VARCHAR2(1) CONSTRAINT CG_PRPSL_TN4 NOT NULL,
     CONSTRAINT CG_PRPSL_TP1 PRIMARY KEY (
        CGPRPSL_NBR),
     CONSTRAINT CG_PRPSL_TC0 UNIQUE (OBJ_ID)
)
/
