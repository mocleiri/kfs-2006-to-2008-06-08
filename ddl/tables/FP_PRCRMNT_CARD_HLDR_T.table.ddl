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
CREATE TABLE FP_PRCRMNT_CARD_HLDR_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT FP_PRCRMNT_CARD_HLDR_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_PRCRMNT_CARD_HLDR_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_PRCRMNT_CARD_HLDR_TN3 NOT NULL,
        TRN_CC_NBR                     VARCHAR2(4000),
        FDOC_CARD_HLDR_NM              VARCHAR2(35),
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
        FIN_COA_CD                     VARCHAR2(2),
        ACCOUNT_NBR                    VARCHAR2(7),
        SUB_ACCT_NBR                   VARCHAR2(5),
     CONSTRAINT FP_PRCRMNT_CARD_HLDR_TP1 PRIMARY KEY (
        FDOC_NBR),
     CONSTRAINT FP_PRCRMNT_CARD_HLDR_TC0 UNIQUE (OBJ_ID)
)
/
