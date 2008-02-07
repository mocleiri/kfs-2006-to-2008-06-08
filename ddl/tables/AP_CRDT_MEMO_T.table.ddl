/*
 * Copyright 2006-2007 The Kuali Foundation.
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
CREATE TABLE AP_CRDT_MEMO_T(
        CRDT_MEMO_ID                   NUMBER(9) CONSTRAINT AP_CRDT_MEMO_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AP_CRDT_MEMO_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AP_CRDT_MEMO_TN3 NOT NULL,
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT AP_CRDT_MEMO_TN4 NOT NULL,
        VNDR_HDR_GNRTD_ID              NUMBER(10) CONSTRAINT AP_CRDT_MEMO_TN5 NOT NULL,
        VNDR_DTL_ASND_ID               NUMBER(10) CONSTRAINT AP_CRDT_MEMO_TN6 NOT NULL,
        VNDR_CUST_NBR                  VARCHAR2(30),
        VNDR_NM                        VARCHAR2(45), 
        VNDR_LN1_ADDR                  VARCHAR2(45), 
        VNDR_LN2_ADDR                  VARCHAR2(45), 
        VNDR_CTY_NM                    VARCHAR2(45), 
        VNDR_ST_CD                     VARCHAR2(2), 
        VNDR_PSTL_CD                   VARCHAR2(20), 
        VNDR_CNTRY_CD                  VARCHAR2(2), 
        PO_ID                          NUMBER(9),
        PO_ENCUM_FSCL_YR               NUMBER(4), 
        PMT_RQST_ID                    NUMBER(9),
        CRDT_MEMO_STAT_CD              VARCHAR2(4) CONSTRAINT AP_CRDT_MEMO_TN7 NOT NULL,
        CRDT_MEMO_NBR                  VARCHAR2(35) CONSTRAINT AP_CRDT_MEMO_TN8 NOT NULL,
        CRDT_MEMO_DT                   DATE CONSTRAINT AP_CRDT_MEMO_TN9 NOT NULL,
        CRDT_MEMO_AMT                  NUMBER(19,2) CONSTRAINT AP_CRDT_MEMO_TN10 NOT NULL,
        CRDT_MEMO_NTE_LN1_TXT          VARCHAR2(90),
        CRDT_MEMO_NTE_LN2_TXT          VARCHAR2(90),
        CRDT_MEMO_NTE_LN3_TXT          VARCHAR2(90),
        CRDT_MEMO_PD_TS                DATE, 
        CRDT_HLD_IND                   VARCHAR2(1) DEFAULT 'N' CONSTRAINT AP_CRDT_MEMO_TN11 NOT NULL,
        AP_PRCSR_ID                    VARCHAR2(11) CONSTRAINT AP_CRDT_MEMO_TN12 NOT NULL,
        PRCS_CMP_CD                    VARCHAR2(2) CONSTRAINT AP_CRDT_MEMO_TN13 NOT NULL,
        AP_APRVL_DT                    DATE,
        ITM_MISC_CRDT_DESC             VARCHAR2(255),
        CRDT_MEMO_EXTRT_DT             DATE,
        AP_PUR_DOC_LNK_ID              NUMBER(10),
        AP_HLD_ID                      VARCHAR2(11), 
        CONT_ACCT_IND                  VARCHAR2(1),
        CLOSE_PO_IND                   VARCHAR2(1), 
        REOPEN_PO_IND                  VARCHAR2(1),
        VNDR_ADDR_INTL_PROV_NM         VARCHAR2(45),
     CONSTRAINT AP_CRDT_MEMO_TP1 PRIMARY KEY (
        CRDT_MEMO_ID),
     CONSTRAINT AP_CRDT_MEMO_TC0 UNIQUE (OBJ_ID)
)
/
