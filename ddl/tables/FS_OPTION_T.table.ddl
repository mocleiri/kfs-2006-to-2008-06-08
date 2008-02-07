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
CREATE TABLE FS_OPTION_T(
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT FS_OPTION_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FS_OPTION_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FS_OPTION_TN3 NOT NULL,
        ACT_FIN_BAL_TYP_CD             VARCHAR2(2),
        BDGT_CHK_BALTYP_CD             VARCHAR2(2),
        BDGT_CHK_OPTN_CD               VARCHAR2(1),
        UNIV_FSCYR_STRT_YR             NUMBER(4),
        UNIV_FSCYR_STRT_MO             VARCHAR2(2),
        FOBJTP_INC_CSH_CD              VARCHAR2(2),
        FOBJTP_XPND_EXP_CD             VARCHAR2(2),
        FOBJTP_XPNDNEXP_CD             VARCHAR2(2),
        FOBJTP_EXPNXPND_CD             VARCHAR2(2),
        FOBJ_TYP_ASSET_CD              VARCHAR2(2),
        FOBJ_TYP_LBLTY_CD              VARCHAR2(2),
        FOBJ_TYP_FNDBAL_CD             VARCHAR2(2),
        EXT_ENC_FBALTYP_CD             VARCHAR2(2),
        INT_ENC_FBALTYP_CD             VARCHAR2(2),
        PRE_ENC_FBALTYP_CD             VARCHAR2(2),
        ELIM_FINBAL_TYP_CD             VARCHAR2(2),
        FOBJTP_INC_NCSH_CD             VARCHAR2(2),
        FOBJTP_CSH_NINC_CD             VARCHAR2(2),
        UNIV_FIN_COA_CD                VARCHAR2(2),
        UNIV_FISCAL_YR_NM              VARCHAR2(10),
        FIN_BEGBALLOAD_IND             VARCHAR2(1),
        CSTSHR_ENCUM_FIN_BAL_TYP_CD    VARCHAR2(2),
        BASE_BDGT_FIN_BAL_TYP_CD       VARCHAR2(2),
        MO_BDGT_FIN_BAL_TYP_CD         VARCHAR2(2),
        FIN_OBJECT_TYP_TRNFR_INC_CD    VARCHAR2(2),
        FIN_OBJECT_TYP_TRNFR_EXP_CD    VARCHAR2(2),
        NMNL_FIN_BAL_TYP_CD            VARCHAR2(2),
     CONSTRAINT FS_OPTION_TP1 PRIMARY KEY (
        UNIV_FISCAL_YR),
     CONSTRAINT FS_OPTION_TC0 UNIQUE (OBJ_ID)
)
/
