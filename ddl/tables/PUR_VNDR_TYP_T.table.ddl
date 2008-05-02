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
CREATE TABLE PUR_VNDR_TYP_T(
        VNDR_TYP_CD                    VARCHAR2(4) CONSTRAINT PUR_VNDR_TYP_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_VNDR_TYP_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_VNDR_TYP_TN3 NOT NULL,
        VNDR_TYP_DESC                  VARCHAR2(45),
        DOBJ_MAINT_CD_ACTV_IND         VARCHAR2(1) CONSTRAINT PUR_VNDR_TYP_TN4 NOT NULL,
        VNDR_TAX_NBR_REQ_IND           VARCHAR2(1), 
        VNDR_TYP_CHG_ALLW_IND          VARCHAR2(1),
        VNDR_ADDR_TYP_REQ_CD           VARCHAR2(4), 
        VNDR_CONTR_ALLW_IND            VARCHAR2(1),
        VNDR_SHOW_RVW_IND              VARCHAR2(1),
        VNDR_RVW_TXT                   VARCHAR2(4000),
        PUR_COMM_REQ_IND               VARCHAR2(1),
     CONSTRAINT PUR_VNDR_TYP_TP1 PRIMARY KEY (
        VNDR_TYP_CD),
     CONSTRAINT PUR_VNDR_TYP_TC0 UNIQUE (OBJ_ID)
)
/
