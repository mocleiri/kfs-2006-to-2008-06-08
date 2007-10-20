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
CREATE TABLE GL_ENCUMBRANCE_T(
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT GL_ENCUMBRANCE_TN1 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT GL_ENCUMBRANCE_TN2 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT GL_ENCUMBRANCE_TN3 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT GL_ENCUMBRANCE_TN4 NOT NULL,
        FIN_OBJECT_CD                  VARCHAR2(4) CONSTRAINT GL_ENCUMBRANCE_TN5 NOT NULL,
        FIN_SUB_OBJ_CD                 VARCHAR2(3) CONSTRAINT GL_ENCUMBRANCE_TN6 NOT NULL,
        FIN_BALANCE_TYP_CD             VARCHAR2(2) CONSTRAINT GL_ENCUMBRANCE_TN7 NOT NULL,
        FDOC_TYP_CD                    VARCHAR2(4) CONSTRAINT GL_ENCUMBRANCE_TN8 NOT NULL,
        FS_ORIGIN_CD                   VARCHAR2(2) CONSTRAINT GL_ENCUMBRANCE_TN9 NOT NULL,
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT GL_ENCUMBRANCE_TN10 NOT NULL,
        TRN_ENCUM_DESC                 VARCHAR2(40),
        TRN_ENCUM_DT                   DATE,
        ACLN_ENCUM_AMT                 NUMBER(19, 2),
        ACLN_ENCUM_CLS_AMT             NUMBER(19, 2),
        ACLN_ENCUM_PRG_CD              VARCHAR2(1),
        TIMESTAMP                      DATE,
     CONSTRAINT GL_ENCUMBRANCE_TP1 PRIMARY KEY (
        UNIV_FISCAL_YR,
        FIN_COA_CD,
        ACCOUNT_NBR,
        SUB_ACCT_NBR,
        FIN_OBJECT_CD,
        FIN_SUB_OBJ_CD,
        FIN_BALANCE_TYP_CD,
        FDOC_TYP_CD,
        FS_ORIGIN_CD,
        FDOC_NBR),
     CONSTRAINT GL_ENCUMBRANCE_TC0 UNIQUE (OBJ_ID)
)
/
