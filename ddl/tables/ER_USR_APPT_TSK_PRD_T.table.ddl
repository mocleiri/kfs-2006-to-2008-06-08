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
CREATE TABLE ER_USR_APPT_TSK_PRD_T(
        RDOC_NBR                       VARCHAR2(14) CONSTRAINT ER_USR_APPT_TSK_PRD_TN1 NOT NULL,
        BDGT_TSK_SEQ_NBR               NUMBER(2,0) CONSTRAINT ER_USR_APPT_TSK_PRD_TN2 NOT NULL,
        BDGT_PRD_SEQ_NBR               NUMBER(2,0) CONSTRAINT ER_USR_APPT_TSK_PRD_TN3 NOT NULL,
        BDGT_USR_SEQ_NBR               NUMBER(4,0) CONSTRAINT ER_USR_APPT_TSK_PRD_TN4 NOT NULL,
        INST_APPT_TYP_CD               VARCHAR2(2) CONSTRAINT ER_USR_APPT_TSK_PRD_TN5 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT ER_USR_APPT_TSK_PRD_TN6 NOT NULL,
        VER_NBR                        NUMBER(8,0) DEFAULT 1,
        AGNCY_FRNG_BENE_TOT_AMT        NUMBER(7,0),
        AGNCY_PCT_EFFRT_AMT            NUMBER(5,4),
        AGNCY_RQST_TOT_AMT             NUMBER(7,0),
        PRSN_WKS_AMT                   NUMBER(2,0),
        PRSN_WKS_JSTF_TXT              VARCHAR2(250),
        INST_CST_SHR_FRNG_BENE_TOT_AMT NUMBER(7,0),
        INST_CST_SHR_PCT_EFFRT_AMT     NUMBER(5,4),
        INST_CST_SHR_RQST_TOT_AMT      NUMBER(7,0),
        USR_AGNCY_HRS                  NUMBER(5,0),
        USR_HRLY_RT                    NUMBER(5,2),
        USR_INST_HRS                   NUMBER(5,0),
        USR_BDGT_PRD_SLRY_AMT          NUMBER(9,2),
        AGNCY_FTE_PCT                  NUMBER(3,2),
        AGNCY_HLTH_INS_AMT             NUMBER(9,0),
        AGNCY_RQST_FEE_AMT             NUMBER(9,0),
        AGNCY_SLRY_AMT                 NUMBER(9,0),
        INST_FTE_PCT                   NUMBER(3,2),
        INST_HLTH_INS_AMT              NUMBER(9,0),
        INST_RQST_FEE_AMT              NUMBER(9,0),
        INST_SLRY_AMT                  NUMBER(9,0),
        USR_CRDT_HRS_NBR               NUMBER(2,0),
        USR_CRDT_HR_AMT                NUMBER(6,2),
        USR_MISC_FEE_AMT               NUMBER(4,0),
     CONSTRAINT ER_USR_APPT_TSK_PRD_TP1 PRIMARY KEY (
        RDOC_NBR,
        BDGT_TSK_SEQ_NBR,
        BDGT_PRD_SEQ_NBR,
        BDGT_USR_SEQ_NBR,
        INST_APPT_TYP_CD),
     CONSTRAINT ER_USR_APPT_TSK_PRD_TC0 UNIQUE (OBJ_ID)
)
/

