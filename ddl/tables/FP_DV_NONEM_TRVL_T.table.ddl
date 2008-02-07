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
CREATE TABLE FP_DV_NONEM_TRVL_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT FP_DV_NONEM_TRVL_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_DV_NONEM_TRVL_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_DV_NONEM_TRVL_TN3 NOT NULL,
        DV_TRVLFROM_CTY_NM             VARCHAR2(40),
        DV_TRVL_FROM_ST_CD             VARCHAR2(2),
        DV_TRVL_TO_CTY_NM              VARCHAR2(40),
        DV_TRVL_TO_ST_CD               VARCHAR2(2),
        DV_DIEM_STRT_TS                DATE,
        DV_DIEM_END_TS                 DATE,
        DV_DIEM_CALC_AMT               NUMBER(19, 2),
        DV_DIEM_ACTL_AMT               NUMBER(19, 2),
        DV_DIEM_CHG_TXT                VARCHAR2(200),
        DV_SRVC_PRFRM_DESC             VARCHAR2(50),
        DV_SRVC_LOC_NM                 VARCHAR2(40),
        DV_SRVC_EMPR_NM                VARCHAR2(40),
        DV_AUTOFROM_CTY_NM             VARCHAR2(40),
        DV_AUTO_FROM_ST_CD             VARCHAR2(2),
        DV_AUTO_TO_CTY_NM              VARCHAR2(40),
        DV_AUTO_TO_ST_CD               VARCHAR2(2),
        DV_AUTO_RNDTRP_IND             CHAR(1),
        DV_PRSNCAR_MLG_AMT             NUMBER(7),
        DV_PRSNL_CAR_RT                NUMBER(19, 2),
        DV_PRSNL_CAR_AMT               NUMBER(19, 2),
        DV_EXCPN_IND                   CHAR(1),
        FDOC_NXT_LINE_NBR              NUMBER(7),
        DV_NONEMP_TRVLR_NM             VARCHAR2(40),
        DV_DIEM_RT                     NUMBER(19, 2),
        DV_DIEM_CTGRY_NM               VARCHAR2(10),
        DV_MLG_CALC_AMT                NUMBER(19, 2),
        DV_TRVL_FRM_CNTRY_CD           VARCHAR2(2),
        DV_TRVL_TO_CNTRY_CD            VARCHAR2(2),
     CONSTRAINT FP_DV_NONEM_TRVL_TP1 PRIMARY KEY (
        FDOC_NBR),
     CONSTRAINT FP_DV_NONEM_TRVL_TC0 UNIQUE (OBJ_ID)
)
/
