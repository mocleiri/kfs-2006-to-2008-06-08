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
CREATE TABLE AP_ELCTRNC_INV_HDR_INFO_T(
        INV_HDR_INFO_ID                NUMBER(9) CONSTRAINT AP_ELCTRNC_INV_HDR_INFO_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AP_ELCTRNC_INV_HDR_INFO_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AP_ELCTRNC_INV_HDR_INFO_TN3 NOT NULL,
        AP_ELCTRNC_INV_LOAD_SUM_ID     NUMBER(14),
        INV_PROC_DT                    DATE CONSTRAINT AP_ELCTRNC_INV_HDR_INFO_TN4 NOT NULL,
        INV_FL_NM                      VARCHAR2(200),
        VNDR_DUNS_NBR                  VARCHAR2(50),
        VNDR_HDR_GNRTD_ID              NUMBER(10),
        VNDR_DTL_ASND_ID               NUMBER(10),
        INV_FL_DT                      VARCHAR2(100),
        INV_FL_NBR                     VARCHAR2(100),
        INV_FL_PRPS_ID                 VARCHAR2(50),
        INV_FL_OPRN_ID                 VARCHAR2(50),
        INV_FL_DPLMNT_MODE_VAL         VARCHAR2(50),
        INV_FL_HDR_TYP_IND             VARCHAR2(1) CONSTRAINT AP_ELCTRNC_INV_HDR_INFO_TN5 NOT NULL,
        INV_FL_INFO_ONLY_IND           VARCHAR2(1) CONSTRAINT AP_ELCTRNC_INV_HDR_INFO_TN6 NOT NULL,
        INV_FL_TAX_IN_LN_IND           VARCHAR2(1) CONSTRAINT AP_ELCTRNC_INV_HDR_INFO_TN7 NOT NULL,
        INV_FL_SPCL_HANDLG_IN_LN_IND   VARCHAR2(1) CONSTRAINT AP_ELCTRNC_INV_HDR_INFO_TN8 NOT NULL,
        INV_FL_SHP_IN_LN_IND           VARCHAR2(1) CONSTRAINT AP_ELCTRNC_INV_HDR_INFO_TN9 NOT NULL,
        INV_FL_DSCT_IN_LN_IND          VARCHAR2(1) CONSTRAINT AP_ELCTRNC_INV_HDR_INFO_TN10 NOT NULL,
        INV_ORD_REF_ORD_ID             VARCHAR2(100),
        INV_ORD_REF_DOC_REF_PYLD_ID    VARCHAR2(100),
        INV_ORD_REF_DOC_REF_TXT        VARCHAR2(100),
        INV_ORD_MSTR_AGRMN_REF_ID      VARCHAR2(100),
        INV_ORD_MSTR_AGRMN_REF_DT      VARCHAR2(100),
        INV_ORD_MSTR_AGRMN_INFO_ID     VARCHAR2(100),
        INV_ORD_MSTR_AGRMN_INFO_DT     VARCHAR2(100),
        INV_ORD_PO_ORD_ID              VARCHAR2(100),
        INV_ORD_PO_ORD_DT              VARCHAR2(100),
        INV_ORD_SUPP_ORD_INFO_ID       VARCHAR2(100),
        EPIC_PO_ID                     NUMBER(9),
        INV_ITM_SUB_TOT_AMT            NUMBER(19,4),
        INV_ITM_SUB_TOT_CRNCY_CD       VARCHAR2(20),
        INV_ITM_SPCL_HANDLG_AMT        NUMBER(19,4),
        INV_ITM_SPCL_HANDLG_CRNCY_CD   VARCHAR2(20),
        INV_ITM_SHP_AMT                NUMBER(19,4),
        INV_ITM_SHP_CRNCY_CD           VARCHAR2(20),
        INV_ITM_SHP_DESC               VARCHAR2(300),
        INV_ITM_TAX_AMT                NUMBER(19,4),
        INV_ITM_TAX_CRNCY_CD           VARCHAR2(20),
        INV_ITM_TAX_DESC               VARCHAR2(300),
        INV_ITM_GRS_AMT                NUMBER(19,4),
        INV_ITM_GRS_CRNCY_CD           VARCHAR2(20),
        INV_ITM_DSCT_AMT               NUMBER(19,4),
        INV_ITM_DSCT_CRNCY_CD          VARCHAR2(20),
        INV_ITM_NET_AMT                NUMBER(19,4),
        INV_ITM_NET_CRNCY_CD           VARCHAR2(20),
        INV_RJT_EXTRT_DT               DATE,
        EPIC_PO_DLVY_CMP_CD            VARCHAR2(2),
        INV_SHP_DT                     DATE,
        INV_ADDR_NM                    VARCHAR2(100),
        INV_SHP_TO_LN1_ADDR            VARCHAR2(200),
        INV_SHP_TO_LN2_ADDR            VARCHAR2(200),
        INV_SHP_TO_LN3_ADDR            VARCHAR2(200),
        INV_CUST_NBR                   VARCHAR2(50),
        INV_SHP_TO_ST_CD               VARCHAR2(200),
        INV_SHP_TO_CNTRY_CD            VARCHAR2(200),
        INV_SHP_TO_CTY_NM              VARCHAR2(200),
        INV_SHP_TO_PSTL_CD             VARCHAR2(200),
        INV_PO_NBR                     VARCHAR2(100),
     CONSTRAINT AP_ELCTRNC_INV_HDR_INFO_TP1 PRIMARY KEY (
        INV_HDR_INFO_ID),
     CONSTRAINT AP_ELCTRNC_INV_HDR_INFO_TC0 UNIQUE (OBJ_ID)
)
/
