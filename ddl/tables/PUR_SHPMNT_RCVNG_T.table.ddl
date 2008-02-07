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
CREATE TABLE PUR_SHPMNT_RCVNG_T(
        SHPMNT_RCVNG_ID                NUMBER(9) CONSTRAINT PUR_SHPMNT_RCVNG_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_SHPMNT_RCVNG_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_SHPMNT_RCVNG_TN3 NOT NULL,
        PO_ID                          NUMBER(9),
        PO_GNRL_DESC                   VARCHAR2(255),
        CARI_CD                        VARCHAR2(4),
        SHPMNT_TRACK_NBR               VARCHAR2(20),
        SHPMNT_RCVD_DT                 DATE,
        SHPMNT_RCVNG_NTE_TXT           VARCHAR2(255),
        VNDR_NM                        VARCHAR2(45),
        VNDR_LN1_ADDR                  VARCHAR2(45),
        VNDR_LN2_ADDR                  VARCHAR2(45),
        VNDR_CTY_NM                    VARCHAR2(45),
        VNDR_ST_CD                     VARCHAR2(2),
        VNDR_PSTL_CD                   VARCHAR2(20),
        VNDR_CNTRY_CD                  VARCHAR2(2),
        VNDR_SHP_PMT_TERM_CD           VARCHAR2(2),
        DLVY_CMP_CD                    VARCHAR2(2),
        DLVY_BLDG_CD                   VARCHAR2(4),
        DLVY_BLDG_NM                   VARCHAR2(45),
        DLVY_BLDG_RM_NBR               VARCHAR2(8),
        DLVY_BLDG_LN1_ADDR             VARCHAR2(45),
        DLVY_BLDG_LN2_ADDR             VARCHAR2(45),
        DLVY_CTY_NM                    VARCHAR2(45),
        DLVY_ST_CD                     VARCHAR2(2),
        DLVY_PSTL_CD                   VARCHAR2(20),
        DLVY_CNTRY_CD                  VARCHAR2(2),
        DLVY_TO_NM                     VARCHAR2(45),
        DLVY_TO_EMAIL_ADDR             VARCHAR2(100),
        DLVY_TO_PHN_NBR                VARCHAR2(45),
        DLVY_REQ_DT                    DATE,
        DLVY_INSTRC_TXT                VARCHAR2(255),
     CONSTRAINT PUR_SHPMNT_RCVNG_TP1 PRIMARY KEY (
        SHPMNT_RCVNG_ID),
     CONSTRAINT PUR_SHPMNT_RCVNG_TC0 UNIQUE (OBJ_ID)
)
/
