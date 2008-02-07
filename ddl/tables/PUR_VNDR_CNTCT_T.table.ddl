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
CREATE TABLE PUR_VNDR_CNTCT_T(
        VNDR_CNTCT_GNRTD_ID            NUMBER(10) CONSTRAINT PUR_VNDR_CNTCT_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_VNDR_CNTCT_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_VNDR_CNTCT_TN3 NOT NULL,
        VNDR_HDR_GNRTD_ID              NUMBER(10) CONSTRAINT PUR_VNDR_CNTCT_TN4 NOT NULL,
        VNDR_DTL_ASND_ID               NUMBER(10) CONSTRAINT PUR_VNDR_CNTCT_TN5 NOT NULL,
        VNDR_CNTCT_TYP_CD              VARCHAR2(4) CONSTRAINT PUR_VNDR_CNTCT_TN6 NOT NULL,
        VNDR_CNTCT_NM                  VARCHAR2(45),
        VNDR_CNTCT_EMAIL_ADDR          VARCHAR2(90),
        VNDR_CNTCT_CMNT_TXT            VARCHAR2(800),
        VNDR_LN1_ADDR                  VARCHAR2(45), 
        VNDR_LN2_ADDR                  VARCHAR2(45), 
        VNDR_CTY_NM                    VARCHAR2(45), 
        VNDR_ST_CD                     VARCHAR2(2), 
        VNDR_ZIP_CD                    VARCHAR2(20), 
        VNDR_CNTRY_CD                  VARCHAR2(2), 
        VNDR_ATTN_NM                   VARCHAR2(45), 
        VNDR_ADDR_INTL_PROV_NM         VARCHAR2(45), 
        DOBJ_MAINT_CD_ACTV_IND         VARCHAR2(1) CONSTRAINT PUR_VNDR_CNTCT_TN7 NOT NULL,
     CONSTRAINT PUR_VNDR_CNTCT_TP1 PRIMARY KEY (
        VNDR_CNTCT_GNRTD_ID),
     CONSTRAINT PUR_VNDR_CNTCT_TC0 UNIQUE (OBJ_ID)
)
/
