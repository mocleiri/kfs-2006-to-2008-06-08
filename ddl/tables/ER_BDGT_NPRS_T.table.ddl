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
CREATE TABLE ER_BDGT_NPRS_T(
        RDOC_NBR                       VARCHAR2(14) CONSTRAINT ER_BDGT_NPRS_TN1 NOT NULL,
        BDGT_TSK_SEQ_NBR               NUMBER(2,0) CONSTRAINT ER_BDGT_NPRS_TN2 NOT NULL,
        BDGT_PRD_SEQ_NBR               NUMBER(2,0) CONSTRAINT ER_BDGT_NPRS_TN3 NOT NULL,
        BDGT_NPRS_CTGRY_CD             VARCHAR2(2) CONSTRAINT ER_BDGT_NPRS_TN4 NOT NULL,
        BDGT_NPRS_SEQ_NBR              NUMBER(3,0) CONSTRAINT ER_BDGT_NPRS_TN5 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT ER_BDGT_NPRS_TN6 NOT NULL,
        VER_NBR                        NUMBER(8,0) DEFAULT 1,
        AGNCY_CPY_IND                  VARCHAR2(1),
        AGNCY_RQST_AMT                 NUMBER(9,0),
        BDGT_3RD_PRTY_CS_CPY_IND       VARCHAR2(1),
        BDGT_3RD_PRTY_CST_SHR          NUMBER(9,0),
        BDGT_INST_CST_SHR_AMT          NUMBER(9,0),
        BDGT_INST_CS_CPY_IND           VARCHAR2(1),
        BDGT_NPRS_DESC                 VARCHAR2(250),
        BDGT_NPRS_SUB_CTGRY_CD         VARCHAR2(3),
        BDGT_ORGN_3RD_PRTY_CST_SHR_AMT NUMBER(9,0),
        BDGT_ORGN_AGNCY_AMT            NUMBER(9,0),
        BDGT_ORGN_INST_CST_SHR_AMT     NUMBER(9,0),
        BDGT_ORGN_SEQ_NBR              NUMBER(2,0),
        SBCNR_NBR                      VARCHAR2(5),
     CONSTRAINT ER_BDGT_NPRS_TP1 PRIMARY KEY (
        RDOC_NBR,
        BDGT_NPRS_SEQ_NBR),
     CONSTRAINT ER_BDGT_NPRS_TC0 UNIQUE (OBJ_ID)
)
/
