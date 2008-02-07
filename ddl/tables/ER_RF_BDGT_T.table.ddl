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
CREATE TABLE ER_RF_BDGT_T(
        RDOC_NBR                       VARCHAR2(14) CONSTRAINT ER_RF_BDGT_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT ER_RF_BDGT_TN3 NOT NULL,
        VER_NBR                        NUMBER(8,0) DEFAULT 1 CONSTRAINT ER_RF_BDGT_TN4 NOT NULL,
        RF_BDGT_MAX_PRD_NBR            NUMBER(2,0),
        RF_BDGT_MIN_PRD_NBR            NUMBER(2,0),
        RF_BGT_DIR_AMT                 NUMBER(19,4),
        RF_BGT_END_DT                  DATE,
        RF_BGT_IDC_AMT                 NUMBER(19,4),
        RF_BGT_IDC_DESC                VARCHAR2(1000),
        RF_BGT_STRT_DT                 DATE,
        RF_BGT_TOT_DIR_AMT             NUMBER(19,4),
        RF_BGT_TOT_END_DT              DATE,
        RF_BGT_TOT_IDC_AMT             NUMBER(19,4),
        RF_BGT_TOT_STRT_DT             DATE,
     CONSTRAINT ER_RF_BDGT_TP1 PRIMARY KEY (
        RDOC_NBR),
        CONSTRAINT ER_RF_BDGT_TC0 UNIQUE (OBJ_ID)
)
/

