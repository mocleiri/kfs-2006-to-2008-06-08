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
CREATE TABLE FP_CSH_DRWR_T(
        WRKGRP_NM                      VARCHAR2(70) CONSTRAINT FP_CSH_DRWR_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_CSH_DRWR_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_CSH_DRWR_TN3 NOT NULL,
        FDOC_OPEN_DPST_CD              VARCHAR2(1),
        CSH_DRWR_TOT_AMT               NUMBER(19, 2),
        FDOC_100_DLR_AMT               NUMBER(19, 2),
        FDOC_50_DLR_AMT                NUMBER(19, 2),
        FDOC_20_DLR_AMT                NUMBER(19, 2),
        FDOC_10_DLR_AMT                NUMBER(19, 2),
        FDOC_5_DLR_AMT                 NUMBER(19, 2),
        FDOC_2_DLR_AMT                 NUMBER(19, 2),
        FDOC_1_DLR_AMT                 NUMBER(19, 2),
        FDOC_OTHR_DLR_AMT              NUMBER(19, 2),
        FDOC_50_CENT_AMT               NUMBER(19, 2),
        FDOC_25_CENT_AMT               NUMBER(19, 2),
        FDOC_10_CENT_AMT               NUMBER(19, 2),
        FDOC_5_CENT_AMT                NUMBER(19, 2),
        FDOC_1_CENT_AMT                NUMBER(19, 2),
        FDOC_OTHR_CENT_AMT             NUMBER(19, 2),
        FDOC_100_CENT_AMT              NUMBER(19, 2),
        FDOC_MISC_ADV_AMT              NUMBER(19, 2),
        FDOC_REF_NBR                   VARCHAR2(14),
     CONSTRAINT FP_CSH_DRWR_TP1 PRIMARY KEY (
        WRKGRP_NM),
     CONSTRAINT FP_CSH_DRWR_TC0 UNIQUE (OBJ_ID)
)
/
