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
CREATE TABLE PUR_SHP_PMT_TERM_T(
        VNDR_SHP_PMT_TERM_CD           VARCHAR2(4) CONSTRAINT PUR_SHP_PMT_TERM_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_SHP_PMT_TERM_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_SHP_PMT_TERM_TN3 NOT NULL,
        VNDR_SHP_PMT_TERM_DESC         VARCHAR2(45),
        VNDR_SHP_PMT_TERM_PAY_IND      VARCHAR2(1),
        DOBJ_MAINT_CD_ACTV_IND         VARCHAR2(1) CONSTRAINT PUR_SHP_PMT_TERM_TN4 NOT NULL,
     CONSTRAINT PUR_SHP_PMT_TERM_TP1 PRIMARY KEY (
        VNDR_SHP_PMT_TERM_CD),
     CONSTRAINT PUR_SHP_PMT_TERM_TC0 UNIQUE (OBJ_ID)
)
/
