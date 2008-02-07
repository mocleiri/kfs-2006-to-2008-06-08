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
CREATE TABLE FP_COIN_DTL_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT FP_COIN_DTL_TN1 NOT NULL,
        FDOC_TYP_CD                    VARCHAR2(4) CONSTRAINT FP_COIN_DTL_TN2 NOT NULL,
        FDOC_COLUMN_TYP_CD             VARCHAR2(1) CONSTRAINT FP_COIN_DTL_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_COIN_DTL_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_COIN_DTL_TN5 NOT NULL,
        FDOC_50_CENT_AMT               NUMBER(19, 2),
        FDOC_25_CENT_AMT               NUMBER(19, 2),
        FDOC_10_CENT_AMT               NUMBER(19, 2),
        FDOC_5_CENT_AMT                NUMBER(19, 2),
        FDOC_1_CENT_AMT                NUMBER(19, 2),
        FDOC_OTHR_CENT_AMT             NUMBER(19, 2),
        FDOC_100_CENT_AMT              NUMBER(19, 2),
     CONSTRAINT FP_COIN_DTL_TP1 PRIMARY KEY (
        FDOC_NBR,
        FDOC_TYP_CD,
        FDOC_COLUMN_TYP_CD),
     CONSTRAINT FP_COIN_DTL_TC0 UNIQUE (OBJ_ID)
)
/
