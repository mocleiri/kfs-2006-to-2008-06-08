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
CREATE TABLE GL_PENDING_ENTRY_MT(
        SESID                          VARCHAR2(36) CONSTRAINT GL_PENDING_ENTRY_MTN1 NOT NULL,
        FS_ORIGIN_CD                   VARCHAR2(2) CONSTRAINT GL_PENDING_ENTRY_MTN2 NOT NULL,
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT GL_PENDING_ENTRY_MTN3 NOT NULL,
        TRN_ENTR_SEQ_NBR               NUMBER(5) CONSTRAINT GL_PENDING_ENTRY_MTN4 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT GL_PENDING_ENTRY_MTN5 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT GL_PENDING_ENTRY_MTN6 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2),
        ACCOUNT_NBR                    VARCHAR2(7),
        SUB_ACCT_NBR                   VARCHAR2(5),
        FIN_OBJECT_CD                  VARCHAR2(4),
        FIN_SUB_OBJ_CD                 VARCHAR2(3),
        FIN_BALANCE_TYP_CD             VARCHAR2(2),
        FIN_OBJ_TYP_CD                 VARCHAR2(2),
        UNIV_FISCAL_YR                 NUMBER(4),
        UNIV_FISCAL_PRD_CD             VARCHAR2(2),
        TRN_LDGR_ENTR_DESC             VARCHAR2(40),
        TRN_LDGR_ENTR_AMT              NUMBER(19, 2),
        TRN_DEBIT_CRDT_CD              VARCHAR2(1),
        TRANSACTION_DT                 DATE,
        FDOC_TYP_CD                    VARCHAR2(4),
        ORG_DOC_NBR                    VARCHAR2(10),
        PROJECT_CD                     VARCHAR2(10),
        ORG_REFERENCE_ID               VARCHAR2(8),
        FDOC_REF_TYP_CD                VARCHAR2(4),
        FS_REF_ORIGIN_CD               VARCHAR2(2),
        FDOC_REF_NBR                   VARCHAR2(14),
        FDOC_REVERSAL_DT               DATE,
        TRN_ENCUM_UPDT_CD              VARCHAR2(1),
        FDOC_APPROVED_CD               VARCHAR2(1),
        ACCT_SF_FINOBJ_CD              VARCHAR2(4),
        TRN_ENTR_OFST_CD               VARCHAR2(1),
        TRNENTR_PROCESS_TM             DATE,
     CONSTRAINT GL_PENDING_ENTRY_MTP1 PRIMARY KEY (
        SESID,
        FS_ORIGIN_CD,
        FDOC_NBR,
        TRN_ENTR_SEQ_NBR),
     CONSTRAINT GL_PENDING_ENTRY_MTC0 UNIQUE (OBJ_ID)
)
/
