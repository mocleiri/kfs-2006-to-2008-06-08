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
CREATE TABLE FP_PRCRMNT_TRN_DTL_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT FP_PRCRMNT_TRN_DTL_TN1 NOT NULL,
        FDOC_TRN_LN_NBR                NUMBER(7) CONSTRAINT FP_PRCRMNT_TRN_DTL_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_PRCRMNT_TRN_DTL_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_PRCRMNT_TRN_DTL_TN4 NOT NULL,
        TRANSACTION_DT                 DATE,
        TRN_REF_NBR                    VARCHAR2(23),
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
        TRN_CYCLE_STRT_DT              DATE,
        TRN_CYCLE_END_DT               DATE,
        TRN_TOT_AMT                    NUMBER(19,2),
     CONSTRAINT FP_PRCRMNT_TRN_DTL_TP1 PRIMARY KEY (
        FDOC_NBR,
        FDOC_TRN_LN_NBR),
     CONSTRAINT FP_PRCRMNT_TRN_DTL_TC0 UNIQUE (OBJ_ID)
)
/
