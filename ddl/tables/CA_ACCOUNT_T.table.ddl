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
CREATE TABLE CA_ACCOUNT_T(
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT CA_ACCOUNT_TN1 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT CA_ACCOUNT_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CA_ACCOUNT_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CA_ACCOUNT_TN4 NOT NULL,
        ACCOUNT_NM                     VARCHAR2(40),
        ACCT_FSC_OFC_UID               VARCHAR2(10),
        ACCT_SPVSR_UNVL_ID             VARCHAR2(10),
        ACCT_MGR_UNVL_ID               VARCHAR2(10),
        ORG_CD                         VARCHAR2(4),
        ACCT_TYP_CD                    VARCHAR2(2),
        ACCT_PHYS_CMP_CD               VARCHAR2(2),
        SUB_FUND_GRP_CD                VARCHAR2(6),
        ACCT_FRNG_BNFT_CD              VARCHAR2(1),
        FIN_HGH_ED_FUNC_CD             VARCHAR2(4),
        ACCT_RSTRC_STAT_CD             VARCHAR2(1),
        ACCT_RSTRC_STAT_DT             DATE,
        ACCT_CITY_NM                   VARCHAR2(25),
        ACCT_STATE_CD                  VARCHAR2(2),
        ACCT_STREET_ADDR               VARCHAR2(30),
        ACCT_ZIP_CD                    VARCHAR2(11),
        RPTS_TO_FIN_COA_CD             VARCHAR2(2),
        RPTS_TO_ACCT_NBR               VARCHAR2(7),
        ACCT_CREATE_DT                 DATE,
        ACCT_EFFECT_DT                 DATE,
        ACCT_EXPIRATION_DT             DATE,
        CONT_FIN_COA_CD                VARCHAR2(2),
        CONT_ACCOUNT_NBR               VARCHAR2(7),
        AWRD_PERIOD_END_YR             NUMBER(4),
        AWRD_PERIOD_END_MO             VARCHAR2(2),
        AWRD_PERIOD_BEG_YR             NUMBER(4),
        AWRD_PERIOD_BEG_MO             VARCHAR2(2),
        ENDOW_FIN_COA_CD               VARCHAR2(2),
        ENDOW_ACCOUNT_NBR              VARCHAR2(7),
        CONTR_CTRL_FCOA_CD             VARCHAR2(2),
        CONTR_CTRLACCT_NBR             VARCHAR2(7),
        INCOME_FIN_COA_CD              VARCHAR2(2),
        INCOME_ACCOUNT_NBR             VARCHAR2(7),
        ACCT_ICR_TYP_CD                VARCHAR2(2),
        AC_CSTM_ICREXCL_CD             VARCHAR2(1),
        FIN_SERIES_ID                  VARCHAR2(3),
        ICR_FIN_COA_CD                 VARCHAR2(2),
        ICR_ACCOUNT_NBR                VARCHAR2(7),
        ACCT_IN_FP_CD                  VARCHAR2(1),
        BDGT_REC_LVL_CD                VARCHAR2(1),
        ACCT_SF_CD                     VARCHAR2(1),
        ACCT_PND_SF_CD                 VARCHAR2(1),
        FIN_EXT_ENC_SF_CD              VARCHAR2(1),
        FIN_INT_ENC_SF_CD              VARCHAR2(1),
        FIN_PRE_ENC_SF_CD              VARCHAR2(1),
        FIN_OBJ_PRSCTRL_CD             VARCHAR2(1),
        CG_CFDA_NBR                    VARCHAR2(6),
        ACCT_OFF_CMP_IND               VARCHAR2(1),
        ACCT_CLOSED_IND                VARCHAR2(1),
     CONSTRAINT CA_ACCOUNT_TP1 PRIMARY KEY (
        FIN_COA_CD,
        ACCOUNT_NBR),
     CONSTRAINT CA_ACCOUNT_TC0 UNIQUE (OBJ_ID)
)
/
