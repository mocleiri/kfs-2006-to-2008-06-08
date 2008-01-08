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
CREATE TABLE LD_A21_DTL_BLD_T(
        A21_LBR_BLD_NBR                NUMBER(19) CONSTRAINT LD_A21_DTL_BLD_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT LD_A21_DTL_BLD_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_A21_DTL_BLD_TN3 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2),
        ORG_CD                         VARCHAR2(4),
        A21_LBR_RPT_NBR                VARCHAR2(3),
        A21_LBR_DOC_CD                 VARCHAR2(2),
        A21_LBR_FSCL_YR                NUMBER(4),
        EMPLID                         VARCHAR2(11),
     CONSTRAINT LD_A21_DTL_BLD_TP1 PRIMARY KEY (
        A21_LBR_BLD_NBR),
     CONSTRAINT LD_A21_DTL_BLD_TC0 UNIQUE (OBJ_ID)
)
/
