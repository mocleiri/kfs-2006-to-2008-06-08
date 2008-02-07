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
CREATE TABLE FP_PRCRMNT_VNDR_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT FP_PRCRMNT_VNDR_TN1 NOT NULL,
        FDOC_TRN_LN_NBR                NUMBER(7) CONSTRAINT FP_PRCRMNT_VNDR_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_PRCRMNT_VNDR_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_PRCRMNT_VNDR_TN4 NOT NULL,
        VNDR_NM                        VARCHAR2(45),
        VNDR_LN1_ADDR                  VARCHAR2(45),
        VNDR_LN2_ADDR                  VARCHAR2(45),
        VNDR_CTY_NM                    VARCHAR2(45),
        VNDR_ST_CD                     VARCHAR2(2),
        VNDR_ZIP_CD                    VARCHAR2(20),
        VISA_VNDR_ID                   VARCHAR2(16),
        VNDR_ORD_NBR                   VARCHAR2(12),
        TRN_MCC_CD                     VARCHAR2(4),
     CONSTRAINT FP_PRCRMNT_VNDR_TP1 PRIMARY KEY (
        FDOC_NBR,
        FDOC_TRN_LN_NBR),
     CONSTRAINT FP_PRCRMNT_VNDR_TC0 UNIQUE (OBJ_ID)
)
/
