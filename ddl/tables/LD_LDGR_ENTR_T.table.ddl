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
CREATE TABLE LD_LDGR_ENTR_T(
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT LD_LDGR_ENTR_TN1 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT LD_LDGR_ENTR_TN2 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT LD_LDGR_ENTR_TN3 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT LD_LDGR_ENTR_TN4 NOT NULL,
        FIN_OBJECT_CD                  VARCHAR2(4) CONSTRAINT LD_LDGR_ENTR_TN5 NOT NULL,
        FIN_SUB_OBJ_CD                 VARCHAR2(3) CONSTRAINT LD_LDGR_ENTR_TN6 NOT NULL,
        FIN_BALANCE_TYP_CD             VARCHAR2(2) CONSTRAINT LD_LDGR_ENTR_TN7 NOT NULL,
        FIN_OBJ_TYP_CD                 VARCHAR2(2) CONSTRAINT LD_LDGR_ENTR_TN8 NOT NULL,
        UNIV_FISCAL_PRD_CD             VARCHAR2(2) CONSTRAINT LD_LDGR_ENTR_TN9 NOT NULL,
        FDOC_TYP_CD                    VARCHAR2(4) CONSTRAINT LD_LDGR_ENTR_TN10 NOT NULL,
        FS_ORIGIN_CD                   VARCHAR2(2) CONSTRAINT LD_LDGR_ENTR_TN11 NOT NULL,
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT LD_LDGR_ENTR_TN12 NOT NULL,
        TRN_ENTR_SEQ_NBR               NUMBER(5) CONSTRAINT LD_LDGR_ENTR_TN13 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT LD_LDGR_ENTR_TN14 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_LDGR_ENTR_TN15 NOT NULL,
        POSITION_NBR                   VARCHAR2(8),
        PROJECT_CD                     VARCHAR2(10),
        TRN_LDGR_ENTR_DESC             VARCHAR2(40),
        TRN_LDGR_ENTR_AMT              NUMBER(19, 2),
        TRN_DEBIT_CRDT_CD              VARCHAR2(1),
        TRANSACTION_DT                 DATE,
        ORG_DOC_NBR                    VARCHAR2(10),
        ORG_REFERENCE_ID               VARCHAR2(8),
        FDOC_REF_TYP_CD                VARCHAR2(4),
        FS_REF_ORIGIN_CD               VARCHAR2(2),
        FDOC_REF_NBR                   VARCHAR2(14),
        FDOC_REVERSAL_DT               DATE,
        TRN_ENCUM_UPDT_CD              VARCHAR2(1),
        TRN_POST_DT                    DATE,
        PAY_PERIOD_END_DT              DATE,
        TRN_TOTAL_HR                   NUMBER,
        PYRL_DT_FSCL_YR                NUMBER(4),
        PYRL_DT_FSCLPRD_CD             VARCHAR2(2),
        EMPLID                         VARCHAR2(11),
        EMPL_RCD                       NUMBER(3),
        ERNCD                          VARCHAR2(3),
        PAYGROUP                       VARCHAR2(3),
        SAL_ADMIN_PLAN                 VARCHAR2(4),
        GRADE                          VARCHAR2(3),
        RUN_ID                         VARCHAR2(10),
        LL_ORIG_FIN_COA_CD             VARCHAR2(2),
        LL_ORIG_ACCT_NBR               VARCHAR2(7),
        LL_ORIG_SUB_ACCT_NBR           VARCHAR2(5),
        LL_ORIG_FIN_OBJECT_CD          VARCHAR2(4),
        LL_ORIG_FIN_SUB_OBJ_CD         VARCHAR2(3),
        COMPANY                        VARCHAR2(3),
        SETID                          VARCHAR2(5),
        TIMESTAMP                      DATE,
     CONSTRAINT LD_LDGR_ENTR_TP1 PRIMARY KEY (
        UNIV_FISCAL_YR,
        FIN_COA_CD,
        ACCOUNT_NBR,
        SUB_ACCT_NBR,
        FIN_OBJECT_CD,
        FIN_SUB_OBJ_CD,
        FIN_BALANCE_TYP_CD,
        FIN_OBJ_TYP_CD,
        UNIV_FISCAL_PRD_CD,
        FDOC_TYP_CD,
        FS_ORIGIN_CD,
        FDOC_NBR,
        TRN_ENTR_SEQ_NBR),
     CONSTRAINT LD_LDGR_ENTR_TC0 UNIQUE (OBJ_ID)
)
/
