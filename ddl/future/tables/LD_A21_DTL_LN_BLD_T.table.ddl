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
CREATE TABLE LD_A21_DTL_LN_BLD_T(
        A21_LBR_BLD_NBR                NUMBER(19) CONSTRAINT LD_A21_DTL_LN_BLD_TN1 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT LD_A21_DTL_LN_BLD_TN2 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT LD_A21_DTL_LN_BLD_TN3 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT LD_A21_DTL_LN_BLD_TN4 NOT NULL,
        POSITION_NBR                   VARCHAR2(8) CONSTRAINT LD_A21_DTL_LN_BLD_TN5 NOT NULL,
        FIN_OBJECT_CD                  VARCHAR2(4) CONSTRAINT LD_A21_DTL_LN_BLD_TN6 NOT NULL,
        SOURCE_FIN_COA_CD              VARCHAR2(2) CONSTRAINT LD_A21_DTL_LN_BLD_TN7 NOT NULL,
        SOURCE_ACCT_NBR                VARCHAR2(7) CONSTRAINT LD_A21_DTL_LN_BLD_TN8 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT LD_A21_DTL_LN_BLD_TN9 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_A21_DTL_LN_BLD_TN10 NOT NULL,
        A21_LBR_PYRL_AMT               NUMBER(19, 2),
        LBR_DRVD_PYRL_CD               VARCHAR2(1),
        LBR_DRVD_PYRL_PCT              NUMBER(3),
        A21LBR_CST_SHR_CD              VARCHAR2(1),
        A21LBR_CST_SHR_PCT             NUMBER(3),
        LBR_CALC_OVRLL_CD              VARCHAR2(1),
        LBR_CALC_OVRLL_PCT             NUMBER(3),
        LBR_UPDT_OVRLL_CD              VARCHAR2(1),
        LBR_UPDT_OVRLL_PCT             NUMBER(3),
        A21_LBR_PRORT_CD               VARCHAR2(1),
        A21_LBR_PRORT_PCT              NUMBER(3),
        FDOC_POST_YR                   NUMBER(4),
        CST_SRCSUBACCT_NBR             VARCHAR2(5),
        A21_LBRORIG_PY_AMT             NUMBER(19, 2),
     CONSTRAINT LD_A21_DTL_LN_BLD_TP1 PRIMARY KEY (
        A21_LBR_BLD_NBR,
        FIN_COA_CD,
        ACCOUNT_NBR,
        SUB_ACCT_NBR,
        POSITION_NBR,
        FIN_OBJECT_CD,
        SOURCE_FIN_COA_CD,
        SOURCE_ACCT_NBR),
     CONSTRAINT LD_A21_DTL_LN_BLD_TC0 UNIQUE (OBJ_ID)
)
/
