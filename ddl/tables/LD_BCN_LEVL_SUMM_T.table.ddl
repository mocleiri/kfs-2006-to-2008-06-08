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
CREATE TABLE LD_BCN_LEVL_SUMM_T(
        PERSON_UNVL_ID                 VARCHAR2(10) CONSTRAINT LD_BCN_LEVL_SUMM_TN1 NOT NULL,
        ORG_FIN_COA_CD                 VARCHAR2(2) CONSTRAINT LD_BCN_LEVL_SUMM_TN2 NOT NULL,
        ORG_CD                         VARCHAR2(4) CONSTRAINT LD_BCN_LEVL_SUMM_TN3 NOT NULL,
        SUB_FUND_GRP_CD                VARCHAR2(6) CONSTRAINT LD_BCN_LEVL_SUMM_TN4 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT LD_BCN_LEVL_SUMM_TN5 NOT NULL,
        INC_EXP_CD                     VARCHAR2(1) CONSTRAINT LD_BCN_LEVL_SUMM_TN6 NOT NULL,
        FIN_CONS_SORT_CD               VARCHAR2(2) CONSTRAINT LD_BCN_LEVL_SUMM_TN7 NOT NULL,
        FIN_LEV_SORT_CD                VARCHAR2(2) CONSTRAINT LD_BCN_LEVL_SUMM_TN8 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_BCN_LEVL_SUMM_TN10 NOT NULL,
        ACLN_ANNL_BAL_AMT              NUMBER(19, 2),
        FIN_BEG_BAL_LN_AMT             NUMBER(19, 2),
        FIN_CONS_OBJ_CD                VARCHAR2(4),
        FIN_OBJ_LEVEL_CD               VARCHAR2(4),
        APPT_RQCSF_FTE_QTY             NUMBER,
        APPT_RQST_FTE_QTY              NUMBER,
        POS_CSF_FTE_QTY                NUMBER,
        POS_CSF_LV_FTE_QTY             NUMBER,
     CONSTRAINT LD_BCN_LEVL_SUMM_TP1 PRIMARY KEY (
        PERSON_UNVL_ID,
        ORG_FIN_COA_CD,
        ORG_CD,
        SUB_FUND_GRP_CD,
        FIN_COA_CD,
        INC_EXP_CD,
        FIN_CONS_SORT_CD,
        FIN_LEV_SORT_CD)
)
/
