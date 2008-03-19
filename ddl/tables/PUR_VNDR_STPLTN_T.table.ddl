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
CREATE TABLE PUR_VNDR_STPLTN_T(
        VNDR_STPLTN_ID                 NUMBER(10) CONSTRAINT PUR_VNDR_STPLTN_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_VNDR_STPLTN_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_VNDR_STPLTN_TN3 NOT NULL,
        VNDR_STPLTN_NM                 VARCHAR2(40) CONSTRAINT PUR_VNDR_STPLTN_TN4 NOT NULL,
        VNDR_STPLTN_DESC               VARCHAR2(4000) CONSTRAINT PUR_VNDR_STPLTN_TN5 NOT NULL,
        DOBJ_MAINT_CD_ACTV_IND         VARCHAR2(1) CONSTRAINT PUR_VNDR_STPLTN_TN6 NOT NULL,
     CONSTRAINT PUR_VNDR_STPLTN_TP1 PRIMARY KEY (
        VNDR_STPLTN_ID),
     CONSTRAINT PUR_VNDR_STPLTN_TC0 UNIQUE (OBJ_ID)
)
/