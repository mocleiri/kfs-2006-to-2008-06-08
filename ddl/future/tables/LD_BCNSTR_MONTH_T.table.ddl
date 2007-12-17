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
CREATE TABLE LD_BCNSTR_MONTH_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT LD_BCNSTR_MONTH_TN1 NOT NULL,
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT LD_BCNSTR_MONTH_TN2 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT LD_BCNSTR_MONTH_TN3 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT LD_BCNSTR_MONTH_TN4 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT LD_BCNSTR_MONTH_TN5 NOT NULL,
        FIN_OBJECT_CD                  VARCHAR2(4) CONSTRAINT LD_BCNSTR_MONTH_TN6 NOT NULL,
        FIN_SUB_OBJ_CD                 VARCHAR2(3) CONSTRAINT LD_BCNSTR_MONTH_TN7 NOT NULL,
        FIN_BALANCE_TYP_CD             VARCHAR2(2) CONSTRAINT LD_BCNSTR_MONTH_TN8 NOT NULL,
        FIN_OBJ_TYP_CD                 VARCHAR2(2) CONSTRAINT LD_BCNSTR_MONTH_TN9 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_BCNSTR_MONTH_TN11 NOT NULL,
        FDOC_LN_MO1_AMT                NUMBER(19, 2),
        FDOC_LN_MO2_AMT                NUMBER(19, 2),
        FDOC_LN_MO3_AMT                NUMBER(19, 2),
        FDOC_LN_MO4_AMT                NUMBER(19, 2),
        FDOC_LN_MO5_AMT                NUMBER(19, 2),
        FDOC_LN_MO6_AMT                NUMBER(19, 2),
        FDOC_LN_MO7_AMT                NUMBER(19, 2),
        FDOC_LN_MO8_AMT                NUMBER(19, 2),
        FDOC_LN_MO9_AMT                NUMBER(19, 2),
        FDOC_LN_MO10_AMT               NUMBER(19, 2),
        FDOC_LN_MO11_AMT               NUMBER(19, 2),
        FDOC_LN_MO12_AMT               NUMBER(19, 2),
     CONSTRAINT LD_BCNSTR_MONTH_TP1 PRIMARY KEY (
        FDOC_NBR,
        UNIV_FISCAL_YR,
        FIN_COA_CD,
        ACCOUNT_NBR,
        SUB_ACCT_NBR,
        FIN_OBJECT_CD,
        FIN_SUB_OBJ_CD,
        FIN_BALANCE_TYP_CD,
        FIN_OBJ_TYP_CD)
)
/
