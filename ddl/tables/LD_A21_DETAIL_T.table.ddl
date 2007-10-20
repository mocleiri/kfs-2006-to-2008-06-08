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
CREATE TABLE LD_A21_DETAIL_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT LD_A21_DETAIL_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT LD_A21_DETAIL_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_A21_DETAIL_TN3 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2),
        ORG_CD                         VARCHAR2(4),
        A21_LBR_RPT_NBR                VARCHAR2(3),
        A21LBR_RPT_PRNT_DT             DATE,
        A21LBR_RPT_APRV_DT             DATE,
        A21_LBR_DOC_CD                 VARCHAR2(2),
        A21_LBR_FSCL_YR                NUMBER(4),
        EMPLID                         VARCHAR2(11),
     CONSTRAINT LD_A21_DETAIL_TP1 PRIMARY KEY (
        FDOC_NBR),
     CONSTRAINT LD_A21_DETAIL_TC0 UNIQUE (OBJ_ID)
)
/
