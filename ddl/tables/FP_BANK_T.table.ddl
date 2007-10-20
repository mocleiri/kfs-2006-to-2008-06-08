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
CREATE TABLE FP_BANK_T(
        FDOC_BANK_CD                   VARCHAR2(4) CONSTRAINT FP_BANK_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT FP_BANK_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_BANK_TN3 NOT NULL,
        FDOC_BANK_NM                   VARCHAR2(40),
        FDOC_BANK_SHRT_NM              VARCHAR2(12),
        BNK_RTNG_NBR                   VARCHAR2(9),
     CONSTRAINT FP_BANK_TP1 PRIMARY KEY (
        FDOC_BANK_CD),
     CONSTRAINT FP_BANK_TC0 UNIQUE (OBJ_ID)
)
/
