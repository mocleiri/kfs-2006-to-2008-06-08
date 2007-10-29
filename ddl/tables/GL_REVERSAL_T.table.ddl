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
CREATE TABLE GL_REVERSAL_T(
        FDOC_REVERSAL_DT               DATE CONSTRAINT GL_REVERSAL_TN1 NOT NULL,
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT GL_REVERSAL_TN2 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT GL_REVERSAL_TN3 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT GL_REVERSAL_TN4 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT GL_REVERSAL_TN5 NOT NULL,
        FIN_OBJECT_CD                  VARCHAR2(4) CONSTRAINT GL_REVERSAL_TN6 NOT NULL,
        FIN_SUB_OBJ_CD                 VARCHAR2(3) CONSTRAINT GL_REVERSAL_TN7 NOT NULL,
        FIN_BALANCE_TYP_CD             VARCHAR2(2) CONSTRAINT GL_REVERSAL_TN8 NOT NULL,
        FIN_OBJ_TYP_CD                 VARCHAR2(2) CONSTRAINT GL_REVERSAL_TN9 NOT NULL,
        UNIV_FISCAL_PRD_CD             VARCHAR2(2) CONSTRAINT GL_REVERSAL_TN10 NOT NULL,
        FDOC_TYP_CD                    VARCHAR2(4) CONSTRAINT GL_REVERSAL_TN11 NOT NULL,
        FS_ORIGIN_CD                   VARCHAR2(2) CONSTRAINT GL_REVERSAL_TN12 NOT NULL,
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT GL_REVERSAL_TN13 NOT NULL,
        TRN_ENTR_SEQ_NBR               NUMBER(5) CONSTRAINT GL_REVERSAL_TN14 NOT NULL,
        TRN_LDGR_ENTR_DESC             VARCHAR2(40),
        TRN_LDGR_ENTR_AMT              NUMBER(19, 2),
        TRN_DEBIT_CRDT_CD              VARCHAR2(1),
        TRANSACTION_DT                 DATE,
        ORG_DOC_NBR                    VARCHAR2(10),
        PROJECT_CD                     VARCHAR2(10),
        ORG_REFERENCE_ID               VARCHAR2(8),
        FDOC_REF_TYP_CD                VARCHAR2(4),
        FS_REF_ORIGIN_CD               VARCHAR2(2),
        FDOC_REF_NBR                   VARCHAR2(14),
        TRN_ENCUM_UPDT_CD              VARCHAR2(1),
        TRN_POST_DT                    DATE,
     CONSTRAINT GL_REVERSAL_TP1 PRIMARY KEY (
        FDOC_REVERSAL_DT,
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
        TRN_ENTR_SEQ_NBR)
)
/
