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
CREATE TABLE PUR_PO_VNDR_QT_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT PUR_PO_VNDR_QT_TN1 NOT NULL,
        PO_VNDR_QT_ID                  NUMBER(9) CONSTRAINT PUR_PO_VNDR_QT_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_PO_VNDR_QT_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_PO_VNDR_QT_TN4 NOT NULL,
        VNDR_HDR_GNRTD_ID              NUMBER(10),
        VNDR_DTL_ASND_ID               NUMBER(10),
        VNDR_NM                        VARCHAR2(45),
        VNDR_LN1_ADDR                  VARCHAR2(45),
        VNDR_LN2_ADDR                  VARCHAR2(45),
        VNDR_CTY_NM                    VARCHAR2(45),
        VNDR_ST_CD                     VARCHAR2(2),
        VNDR_PSTL_CD                   VARCHAR2(20),
        VNDR_PHN_NBR                   VARCHAR2(45),
        VNDR_FAX_NBR                   VARCHAR2(45),
        VNDR_EMAIL_ADDR                VARCHAR2(100),
        VNDR_ATTN_NM                   VARCHAR2(45),
        PO_QT_TRANS_TYP_CD             VARCHAR2(50),
        PO_QT_TRANS_DT                 DATE,
        PO_QT_PRCE_EXPR_DT             DATE,
        PO_QT_STAT_CD                  VARCHAR2(4),
        PO_QT_AWD_DT                   DATE,
        PO_QT_RANK_NBR                 VARCHAR2(3),
        VNDR_CNTRY_CD                  VARCHAR2(2),
        VNDR_ADDR_INTL_PROV_NM         VARCHAR2(45),
     CONSTRAINT PUR_PO_VNDR_QT_TP1 PRIMARY KEY (
        FDOC_NBR,
        PO_VNDR_QT_ID),
     CONSTRAINT PUR_PO_VNDR_QT_TC0 UNIQUE (OBJ_ID)
)
/
