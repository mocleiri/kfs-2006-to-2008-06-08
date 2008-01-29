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
CREATE TABLE ER_RF_DOC_T(
        RDOC_NBR                       VARCHAR2(14) CONSTRAINT ER_RF_DOC_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT ER_RF_DOC_TN2 NOT NULL,
        VER_NBR                        NUMBER(8,0) DEFAULT 1 CONSTRAINT ER_RF_DOC_TN3 NOT NULL,
        AGNCY_ADDL_SHIP_INST_IND       VARCHAR2(1),
        AGNCY_FEDPT_NA_IND             VARCHAR2(1),
        AGNCY_FEDPT_NBR                VARCHAR2(5),
        ER_GRANT_NBR                   VARCHAR2(27),
        RF_ANNCMNT_NBR                 VARCHAR2(110),
        RF_BDGT_NBR                    VARCHAR2(14),
        RF_CI_CUR_IND                  VARCHAR2(1),
        RF_CI_EXST_IND                 VARCHAR2(1),
        RF_CO_PRJDR_IND                VARCHAR2(1),
        RF_CRDT_PCT_IND                VARCHAR2(1),
        RF_CRT_DT                      DATE,
        RF_CST_SHR_IND                 VARCHAR2(1),
        RF_FEDPT_IND                   VARCHAR2(1),
        RF_FLLW_1ST_NM                 VARCHAR2(25),
        RF_FLLW_EM_ADDR                VARCHAR2(40),
        RF_FLLW_FULL_NM                VARCHAR2(30),
        RF_FLLW_LST_NM                 VARCHAR2(25),
        RF_LAY_DESC                    VARCHAR2(1000),
        RF_LSTUPDT_DT                  DATE,
        RF_LST_UPDT_UNVL_ID            NUMBER(12,0),
        RF_OTHR_ORG_IND                VARCHAR2(1),
        RF_OTHR_PRPS_DESC              VARCHAR2(50),
        RF_OTH_TYP_DESC                VARCHAR2(75),
        RF_PARNT_NBR                   VARCHAR2(14),
        RF_PHYS_CMP_CD                 VARCHAR2(2),
        RF_PRIOR_GRANT_NBR             VARCHAR2(27),
        RF_PROJ_TTL                    VARCHAR2(500),
        RF_PRPS_CD                     VARCHAR2(1),
        RF_SPC_REQ_DESC                VARCHAR2(1000),
        RF_SPC_REQ_IND                 VARCHAR2(1),
        RF_STAT_CD                     VARCHAR2(2),
        RF_SUBCNR_IND                  VARCHAR2(1),
        INST_ACCT_NBR                  VARCHAR2(12),
        RSRCH_TYP_CD                   VARCHAR2(1),
        PREV_FED_ID                    VARCHAR2(12),
        PROJ_TYP_OTHR_DESC             VARCHAR2(75),
        FED_ID                         VARCHAR2(12),
        GRNTS_GOV_CNFRM_NBR            VARCHAR2(20),
        GRNTS_GOV_SUBM_IND             VARCHAR2(1),
        PROJ_ABST                      VARCHAR2(4000),
        RF_CRDT_PCT_NXT_SEQ_NBR        NUMBER(2),
        RF_INST_CST_SHR_NXT_SEQ_NBR    NUMBER(2),
        RF_OTHR_CST_SHR_NXT_SEQ_NBR    NUMBER(2),
        PRJDR_NXT_SEQ_NBR              NUMBER(2),
        SUBCNR_NXT_SEQ_NBR             NUMBER(2),
        RF_AGNCY_TBN_IND               VARCHAR2(1),
        RF_CFDA_NBR                    VARCHAR2(6),
        RF_PSNL_NXT_SEQ_NBR            NUMBER(2),
     CONSTRAINT ER_RF_DOC_TP1 PRIMARY KEY (
        RDOC_NBR),
        CONSTRAINT ER_RF_DOC_TC0 UNIQUE (OBJ_ID)
)
/
