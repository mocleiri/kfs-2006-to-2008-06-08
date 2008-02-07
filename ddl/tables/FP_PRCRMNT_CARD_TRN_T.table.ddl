/*
 * Copyright 2006 The Kuali Foundation.
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
CREATE TABLE FP_PRCRMNT_CARD_TRN_T(
        TRN_SEQ_ROW_NBR                NUMBER(7) CONSTRAINT FP_PRCRMNT_CARD_TRN_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_PRCRMNT_CARD_TRN_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_PRCRMNT_CARD_TRN_TN3 NOT NULL,
        TRN_CC_NBR                     VARCHAR2(16),
        FDOC_TOTAL_AMT                 NUMBER(19,2),
        TRN_DEBIT_CRDT_CD              VARCHAR2(1),
        FIN_COA_CD                     VARCHAR2(2),
        ACCOUNT_NBR                    VARCHAR2(7),
        SUB_ACCT_NBR                   VARCHAR2(5),
        FIN_OBJECT_CD                  VARCHAR2(4),
        FIN_SUB_OBJ_CD                 VARCHAR2(3),
        PROJECT_CD                     VARCHAR2(10),
        TRN_CYCLE_STRT_DT              DATE,
        TRN_CYCLE_END_DT               DATE,
        FDOC_CARD_HLDR_NM              VARCHAR2(35),
        TRANSACTION_DT                 DATE,
        TRN_REF_NBR                    VARCHAR2(23),
        TRN_MCC_CD                     VARCHAR2(4),
        TRN_POST_DT                    DATE,
        TRN_ORIG_CRNCY_CD              VARCHAR2(3),
        TRN_BILL_CRNCY_CD              VARCHAR2(3),
        TRN_ORIG_CRNCY_AMT             NUMBER(19,2),
        TRN_CRNCY_EXCH_RT              NUMBER(10,6),
        TRN_STLMNT_AMT                 NUMBER(19,2),
        TRN_SALES_TAX_AMT              NUMBER(19,2),
        TRN_TAX_EXMPT_IND              VARCHAR2(1),
        TRN_PURCH_ID_IND               VARCHAR2(1),
        TRN_PURCH_ID_DESC              VARCHAR2(25),
        TRN_UNIT_CNTCT_NM              VARCHAR2(35),
        TRN_TRVL_AUTH_CD               VARCHAR2(50),
        TRN_PT_OF_SALE_CD              VARCHAR2(25),
        VNDR_NM                        VARCHAR2(45),
        VNDR_LN1_ADDR                  VARCHAR2(45),
        VNDR_LN2_ADDR                  VARCHAR2(45),
        VNDR_CTY_NM                    VARCHAR2(45),
        VNDR_ST_CD                     VARCHAR2(2),
        VNDR_ZIP_CD                    VARCHAR2(20),
        VNDR_ORD_NBR                   VARCHAR2(12),
        VISA_VNDR_ID                   VARCHAR2(16),
        FDOC_CARD_HLDR_ALTRNT_NM       VARCHAR2(45),
        FDOC_CARD_HLDR_LN1_ADDR        VARCHAR2(45),
        FDOC_CARD_HLDR_LN2_ADDR        VARCHAR2(45),
        FDOC_CARD_HLDR_CTY_NM          VARCHAR2(45),
        FDOC_CARD_HLDR_ST_CD           VARCHAR2(2),
        FDOC_CARD_HLDR_ZIP_CD          VARCHAR2(20),
        FDOC_CARD_HLDR_WRK_PHN_NBR     VARCHAR2(10),
        FDOC_CARD_LMT                  NUMBER(19,2),
        FDOC_CARD_CYCLE_AMT_LMT        NUMBER(19,2),
        FDOC_CARD_CYCLE_VOL_LMT        NUMBER(19,2),
        FDOC_CARD_STAT_CD              VARCHAR2(2),
        FDOC_CARD_NTE_TXT              VARCHAR2(50),
     CONSTRAINT FP_PRCRMNT_CARD_TRN_TP1 PRIMARY KEY (
        TRN_SEQ_ROW_NBR),
     CONSTRAINT FP_PRCRMNT_CARD_TRN_TC0 UNIQUE (OBJ_ID)
)
/
