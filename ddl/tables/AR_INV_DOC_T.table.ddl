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
CREATE TABLE AR_INV_DOC_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT AR_INV_DOC_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AR_INV_DOC_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AR_INV_DOC_TN3 NOT NULL,
        UNIV_FISCAL_YR                 NUMBER(4),
        UNIV_FISCAL_PRD_CD             VARCHAR2(2),
        AR_INV_HDR_TXT                 VARCHAR2(120),
        AR_INV_ATTN_LN_TXT             VARCHAR2(80),
        AR_INV_DUE_DT                  DATE,
        AR_BILLING_DT                  DATE,
        AR_INV_TERMS_TXT               VARCHAR2(40),
        ORG_INV_NBR                    VARCHAR2(9),
        AR_WRITEOFF_IND                VARCHAR2(1),
        CUST_PO_NBR                    VARCHAR2(25),
        AR_PRN_INV_IND                 VARCHAR2(1),
        AR_INV_WRTOFF_AMT              NUMBER(19, 2),
        AR_INV_DESC                    VARCHAR2(40),
        CUST_PO_DT                     DATE,
        AR_BILL_BY_COA_CD              VARCHAR2(2),
        AR_BILL_BY_ORG_CD              VARCHAR2(4),
        CUST_SHIP_ADDR_ID              NUMBER(7),
        CUST_BILL_ADDR_ID              NUMBER(7),
        CUST_SPCL_PRCS_CD              VARCHAR2(2),
        CUST_REC_ATT_IND               CHAR(1),
        AR_OPEN_INV_IND                VARCHAR2(1),
        PMT_FIN_COA_CD                 VARCHAR2(2),
        PMT_ACCT_NBR                   VARCHAR2(7),
        PMT_SUB_ACCT_NBR               VARCHAR2(5),
        PMT_FIN_OBJ_CD                 VARCHAR2(4),
        PMT_FIN_SUB_OBJ_CD             VARCHAR2(3), 
        PMT_PROJECT_CD                 VARCHAR2(10),
        PMT_ORG_REF_ID                 VARCHAR2(8),
     CONSTRAINT AR_INV_DOC_TP1 PRIMARY KEY (
        FDOC_NBR),
     CONSTRAINT AR_INV_DOC_TC0 UNIQUE (OBJ_ID)
)
/
