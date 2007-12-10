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
CREATE TABLE LD_A21_DTL_REAS_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT LD_A21_DTL_REAS_TN1 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT LD_A21_DTL_REAS_TN2 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT LD_A21_DTL_REAS_TN3 NOT NULL,
        LBR_EFFRT_CTGRY_CD             VARCHAR2(2) CONSTRAINT LD_A21_DTL_REAS_TN4 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT LD_A21_DTL_REAS_TN5 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_A21_DTL_REAS_TN6 NOT NULL,
        LBR_DTL_REAS_DESC              VARCHAR2(200),
     CONSTRAINT LD_A21_DTL_REAS_TP1 PRIMARY KEY (
        FDOC_NBR,
        FIN_COA_CD,
        ACCOUNT_NBR,
        LBR_EFFRT_CTGRY_CD),
     CONSTRAINT LD_A21_DTL_REAS_TC0 UNIQUE (OBJ_ID)
)
/
