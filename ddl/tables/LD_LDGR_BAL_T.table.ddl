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
CREATE TABLE LD_LDGR_BAL_T(
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT LD_LDGR_BAL_TN1 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT LD_LDGR_BAL_TN2 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT LD_LDGR_BAL_TN3 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT LD_LDGR_BAL_TN4 NOT NULL,
        FIN_OBJECT_CD                  VARCHAR2(4) CONSTRAINT LD_LDGR_BAL_TN5 NOT NULL,
        FIN_SUB_OBJ_CD                 VARCHAR2(3) CONSTRAINT LD_LDGR_BAL_TN6 NOT NULL,
        FIN_BALANCE_TYP_CD             VARCHAR2(2) CONSTRAINT LD_LDGR_BAL_TN7 NOT NULL,
        FIN_OBJ_TYP_CD                 VARCHAR2(2) CONSTRAINT LD_LDGR_BAL_TN8 NOT NULL,
        POSITION_NBR                   VARCHAR2(8) CONSTRAINT LD_LDGR_BAL_TN9 NOT NULL,
        EMPLID                         VARCHAR2(11) CONSTRAINT LD_LDGR_BAL_TN10 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT LD_LDGR_BAL_TN11 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_LDGR_BAL_TN12 NOT NULL,
        ACLN_ANNL_BAL_AMT              NUMBER(19, 2),
        FIN_BEG_BAL_LN_AMT             NUMBER(19, 2),
        CONTR_GR_BB_AC_AMT             NUMBER(19, 2),
        MO1_ACCT_LN_AMT                NUMBER(19, 2),
        MO2_ACCT_LN_AMT                NUMBER(19, 2),
        MO3_ACCT_LN_AMT                NUMBER(19, 2),
        MO4_ACCT_LN_AMT                NUMBER(19, 2),
        MO5_ACCT_LN_AMT                NUMBER(19, 2),
        MO6_ACCT_LN_AMT                NUMBER(19, 2),
        MO7_ACCT_LN_AMT                NUMBER(19, 2),
        MO8_ACCT_LN_AMT                NUMBER(19, 2),
        MO9_ACCT_LN_AMT                NUMBER(19, 2),
        MO10_ACCT_LN_AMT               NUMBER(19, 2),
        MO11_ACCT_LN_AMT               NUMBER(19, 2),
        MO12_ACCT_LN_AMT               NUMBER(19, 2),
        MO13_ACCT_LN_AMT               NUMBER(19, 2),
        TIMESTAMP                      DATE,
     CONSTRAINT LD_LDGR_BAL_TP1 PRIMARY KEY (
        UNIV_FISCAL_YR,
        FIN_COA_CD,
        ACCOUNT_NBR,
        SUB_ACCT_NBR,
        FIN_OBJECT_CD,
        FIN_SUB_OBJ_CD,
        FIN_BALANCE_TYP_CD,
        FIN_OBJ_TYP_CD,
        POSITION_NBR,
        EMPLID),
     CONSTRAINT LD_LDGR_BAL_TC0 UNIQUE (OBJ_ID)
)
/
