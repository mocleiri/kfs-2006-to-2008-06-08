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
CREATE TABLE PUR_VNDR_CONTR_T(
        VNDR_CONTR_GNRTD_ID            NUMBER(10) CONSTRAINT PUR_VNDR_CONTR_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_VNDR_CONTR_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_VNDR_CONTR_TN3 NOT NULL,
        VNDR_HDR_GNRTD_ID              NUMBER(10) CONSTRAINT PUR_VNDR_CONTR_TN4 NOT NULL,
        VNDR_DTL_ASND_ID               NUMBER(10) CONSTRAINT PUR_VNDR_CONTR_TN5 NOT NULL,
        VNDR_CONTR_NM                  VARCHAR2(20),
        VNDR_CONTR_DESC                VARCHAR2(60),
        VNDR_CMP_CD                    VARCHAR2(2),        
        VNDR_CONTR_BEG_DT              DATE,
        VNDR_CONTR_END_DT              DATE,
        CONTR_MGR_CD                   NUMBER(2),
        PO_CST_SRC_CD                  VARCHAR2(4),
        VNDR_PMT_TERM_CD               VARCHAR2(5),
        VNDR_SHP_PMT_TERM_CD           VARCHAR2(4),
        VNDR_SHP_TTL_CD                VARCHAR2(4),                
        VNDR_CONTR_EXTNS_DT            DATE,
        VNDR_B2B_IND                   VARCHAR2(1),
        ORG_AUTO_PO_LMT                NUMBER(19,2),
        DOBJ_MAINT_CD_ACTV_IND         VARCHAR2(1) CONSTRAINT PUR_VNDR_CONTR_TN6 NOT NULL,
     CONSTRAINT PUR_VNDR_CONTR_TP1 PRIMARY KEY (
        VNDR_CONTR_GNRTD_ID),
     CONSTRAINT PUR_VNDR_CONTR_TC0 UNIQUE (OBJ_ID)
)
/
