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
CREATE TABLE FP_DV_DIEM_T(
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT FP_DV_DIEM_TN1 NOT NULL,
        DV_DIEM_CNTRY_NM               VARCHAR2(10) CONSTRAINT FP_DV_DIEM_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT FP_DV_DIEM_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_DV_DIEM_TN4 NOT NULL,
        DV_DIEM_RT                     NUMBER(19, 2),
        DV_DIEM_CNTRY_TXT              VARCHAR2(255),
     CONSTRAINT FP_DV_DIEM_TP1 PRIMARY KEY (
        UNIV_FISCAL_YR,
        DV_DIEM_CNTRY_NM),
     CONSTRAINT FP_DV_DIEM_TC0 UNIQUE (OBJ_ID)
)
/
