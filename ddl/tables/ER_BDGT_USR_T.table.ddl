/*
 * Copyright 2006-2007 The Kuali Foundation.
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
CREATE TABLE ER_BDGT_USR_T(
        RDOC_NBR                       VARCHAR2(14) CONSTRAINT ER_BDGT_USR_TN1 NOT NULL,
        BDGT_USR_SEQ_NBR               NUMBER(4,0) CONSTRAINT ER_BDGT_USR_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT ER_BDGT_USR_TN3 NOT NULL,
        VER_NBR                        NUMBER(8,0) DEFAULT 1,
        BDGT_USR_CRTE_TS               DATE,
        EMP_FSCL_CMP_CD                VARCHAR2(2),
        EMP_PRM_DEPT_CD                VARCHAR2(4),
        PRSN_BASE_SLRY                 NUMBER(19,4),
        PRSN_ROLE_NM                   VARCHAR2(50),
        PRSN_SNR_KEY_IND               VARCHAR2(1),
        PRSN_SCRTRL_CLRCL_IND          VARCHAR2(1),
        PRSN_POST_DCTRL_IND            VARCHAR2(1),
        PRSN_NM_PRFX_TXT               VARCHAR2(15),
        PRSN_NM_SFX_TXT                VARCHAR2(15),
        PRSN_SLRY_JSTF_TXT             VARCHAR2(250),
        PRSN_PRJDR_IND                 VARCHAR2(1),
        PRSN_HRLY_NBR                  NUMBER(3,0),
        BDGT_SLRY_FISCAL_YR            NUMBER(4,0),
        PRSN_UNVL_ID                   VARCHAR2(10),
     CONSTRAINT ER_BDGT_USR_TP1 PRIMARY KEY (
        RDOC_NBR,
        BDGT_USR_SEQ_NBR),
     CONSTRAINT ER_BDGT_USR_TC0 UNIQUE (OBJ_ID)
)
/

