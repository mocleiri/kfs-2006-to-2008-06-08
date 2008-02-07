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
CREATE TABLE ER_RF_RSRCH_RSK_STDY_T(
        RDOC_NBR                       VARCHAR2(14) CONSTRAINT ER_RF_RSRCH_RSK_STDY_TN1 NOT NULL,
        RF_RSRCH_RSK_STDY_SQ_NBR       NUMBER(4,0) CONSTRAINT ER_RF_RSRCH_RSK_STDY_TN2 NOT NULL,
        RSRCH_RSK_TYP_CD               VARCHAR2(2) CONSTRAINT ER_RF_RSRCH_RSK_STDY_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT ER_RF_RSRCH_RSK_STDY_TN4 NOT NULL,
        VER_NBR                        NUMBER(8,0) DEFAULT 1 CONSTRAINT ER_RF_RSRCH_RSK_STDY_TN5 NOT NULL,
        RSRCH_RSK_STDY_APRV_STAT_CD    VARCHAR2(1),
        RSRCH_RSK_EXMPT_NBR            VARCHAR2(10),
        RSRCH_RSK_STDY_APRV_DT         DATE,
        RSRCH_RSK_STDY_NBR             VARCHAR2(15),
        RSRCH_RSK_STDY_NM              VARCHAR2(255),
        RSRCH_RSK_STDY_RVW_CD          VARCHAR2(1),
        RSRCH_RSK_STDY_EXPR_DT         DATE,
     CONSTRAINT ER_RF_RSRCH_RSK_STDY_TP1 PRIMARY KEY (
        RDOC_NBR,
        RF_RSRCH_RSK_STDY_SQ_NBR,
        RSRCH_RSK_TYP_CD),
        CONSTRAINT ER_RF_RSRCH_RSK_STDY_TC0 UNIQUE (OBJ_ID)
)
/

