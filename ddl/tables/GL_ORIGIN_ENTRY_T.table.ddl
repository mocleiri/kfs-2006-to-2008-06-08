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
CREATE TABLE GL_ORIGIN_ENTRY_T(
        ORIGIN_ENTRY_ID                NUMBER(14) CONSTRAINT GL_ORIGIN_ENTRY_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT GL_ORIGIN_ENTRY_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT GL_ORIGIN_ENTRY_TN3 NOT NULL,
        ORIGIN_ENTRY_GRP_ID            NUMBER(14) CONSTRAINT GL_ORIGIN_ENTRY_TN4 NOT NULL,
        UNIV_FISCAL_YR                 NUMBER(4),
        FIN_COA_CD                     VARCHAR2(2),
        ACCOUNT_NBR                    VARCHAR2(7),
        SUB_ACCT_NBR                   VARCHAR2(5),
        FIN_OBJECT_CD                  VARCHAR2(4),
        FIN_SUB_OBJ_CD                 VARCHAR2(3),
        FIN_BALANCE_TYP_CD             VARCHAR2(2),
        FIN_OBJ_TYP_CD                 VARCHAR2(2),
        UNIV_FISCAL_PRD_CD             VARCHAR2(2),
        FDOC_TYP_CD                    VARCHAR2(4),
        FS_ORIGIN_CD                   VARCHAR2(2),
        FDOC_NBR                       VARCHAR2(14),
        TRN_ENTR_SEQ_NBR               NUMBER(5),
        TRN_LDGR_ENTR_DESC             VARCHAR2(40),
        TRN_LDGR_ENTR_AMT              NUMBER(19,2),
        TRN_DEBIT_CRDT_CD              VARCHAR2(1),
        TRANSACTION_DT                 DATE,
        ORG_DOC_NBR                    VARCHAR2(10),
        PROJECT_CD                     VARCHAR2(10),
        ORG_REFERENCE_ID               VARCHAR2(8),
        FDOC_REF_TYP_CD                VARCHAR2(4),
        FS_REF_ORIGIN_CD               VARCHAR2(2),
        FDOC_REF_NBR                   VARCHAR2(14),
        FDOC_REVERSAL_DT               DATE,
        TRN_ENCUM_UPDT_CD              VARCHAR2(1),
        TRN_SCRBBR_OFST_GEN_IND        VARCHAR2(1),
     CONSTRAINT GL_ORIGIN_ENTRY_TP1 PRIMARY KEY (
        ORIGIN_ENTRY_ID),
     CONSTRAINT GL_ORIGIN_ENTRY_TC0 UNIQUE (OBJ_ID)
)
/
