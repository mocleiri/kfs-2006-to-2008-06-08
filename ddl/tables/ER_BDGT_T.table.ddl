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
CREATE TABLE ER_BDGT_T(
        RDOC_NBR                       VARCHAR2(14) CONSTRAINT ER_BDGT_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT ER_BDGT_TN2 NOT NULL,
        VER_NBR                        NUMBER(8,0) DEFAULT 1,
        AGNCY_MOD_IND                  VARCHAR2(1),
        BDGT_3RD_PRTY_CST_SHR_IND      VARCHAR2(1),
        BDGT_AGNCY_NBR                 VARCHAR2(5),
        BDGT_CRT_DT                    DATE,
        BDGT_FED_PASS_AGNCY_NBR        VARCHAR2(5),
        BDGT_FRNG_RT_DESC              VARCHAR2(250),
        BDGT_INST_CST_SHR_IND            VARCHAR2(1),
        BDGT_NM                        VARCHAR2(50),
        BDGT_NPRS_INFLTN_RT            NUMBER(5,4),
        BDGT_PGM_ANNCMNT_NBR           VARCHAR2(110),
        BDGT_PGM_ANNCMNT_NM            VARCHAR2(110),
        BDGT_PROJ_DRCTR_UNVL_ID        VARCHAR2(12),
        BDGT_PSNL_INFLTN_RT            NUMBER(5,4),
        ER_GRANT_NBR                   VARCHAR2(27),
        RF_TRACK_NBR                   VARCHAR2(14),
        BDGT_AGNCY_TBN_IND             VARCHAR2(1),
        BDGT_PRJDR_TBN_IND             VARCHAR2(1),
        BDGT_TYP_CD_TXT                VARCHAR2(40), 
        BDGT_PARNT_TRK_NBR             VARCHAR2(14),
     CONSTRAINT ER_BDGT_TP1 PRIMARY KEY (
        RDOC_NBR),
     CONSTRAINT ER_BDGT_TC0 UNIQUE (OBJ_ID)
)
/

