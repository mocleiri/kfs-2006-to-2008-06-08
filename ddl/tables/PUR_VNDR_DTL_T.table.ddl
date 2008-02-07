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
CREATE TABLE PUR_VNDR_DTL_T(
        VNDR_HDR_GNRTD_ID              NUMBER(10) CONSTRAINT PUR_VNDR_DTL_TN1 NOT NULL,
        VNDR_DTL_ASND_ID               NUMBER(10) CONSTRAINT PUR_VNDR_DTL_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_VNDR_DTL_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_VNDR_DTL_TN4 NOT NULL,
        VNDR_PARENT_IND                VARCHAR2(1),
        VNDR_NM                        VARCHAR2(45) CONSTRAINT PUR_VNDR_DTL_TN5 NOT NULL,
        DOBJ_MAINT_CD_ACTV_IND         VARCHAR2(1) CONSTRAINT PUR_VNDR_DTL_TN6 NOT NULL,
        VNDR_INACTV_REAS_CD            VARCHAR2(4),
        VNDR_DUNS_NBR                  VARCHAR2(9),
        VNDR_PMT_TERM_CD               VARCHAR2(5),
        VNDR_SHP_TTL_CD                VARCHAR2(4),
        VNDR_SHP_PMT_TERM_CD           VARCHAR2(4),
        VNDR_CNFM_IND                  VARCHAR2(1),
        VNDR_PRPYMT_IND                VARCHAR2(1),
        VNDR_CCRD_IND                  VARCHAR2(1),
        VNDR_MIN_ORD_AMT               NUMBER(7,2),
        VNDR_URL_ADDR                  VARCHAR2(45),
        VNDR_SOLD_TO_NM                VARCHAR2(100),
        VNDR_RMT_NM                    VARCHAR2(45),
        VNDR_RSTRC_IND                 VARCHAR2(1),
        VNDR_RSTRC_REAS_TXT            VARCHAR2(60),
        VNDR_RSTRC_DT                  DATE,
        VNDR_RSTRC_PRSN_ID             VARCHAR2(32),
        VNDR_SOLD_TO_GNRTD_ID          NUMBER(10),
        VNDR_SOLD_TO_ASND_ID           NUMBER(10),
        VNDR_1ST_LST_NM_IND            VARCHAR2(1),
     CONSTRAINT PUR_VNDR_DTL_TP1 PRIMARY KEY (
        VNDR_HDR_GNRTD_ID,
        VNDR_DTL_ASND_ID),
     CONSTRAINT PUR_VNDR_DTL_TC0 UNIQUE (OBJ_ID)
)
/
