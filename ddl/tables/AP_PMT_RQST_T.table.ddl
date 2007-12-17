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
CREATE TABLE AP_PMT_RQST_T(
        PMT_RQST_ID                    NUMBER(9) CONSTRAINT AP_PMT_RQST_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT AP_PMT_RQST_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AP_PMT_RQST_TN3 NOT NULL,
        PMT_RQST_STAT_CD               VARCHAR2(4) CONSTRAINT AP_PMT_RQST_TN4 NOT NULL,
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT AP_PMT_RQST_TN5 NOT NULL,
        PO_ID                          NUMBER(9) CONSTRAINT AP_PMT_RQST_TN6 NOT NULL,
        PO_ENCUM_FSCL_YR               NUMBER(4) CONSTRAINT AP_PMT_RQST_TN7 NOT NULL,
        PO_CLSIF_TYP_DESC              VARCHAR2(4),
        INV_DT                         DATE CONSTRAINT AP_PMT_RQST_TN8 NOT NULL,
        INV_NBR                        VARCHAR2(25),
        VNDR_INV_AMT                   NUMBER(19,2),
        VNDR_PMT_TERMS_CD              VARCHAR2(5),
        VNDR_SHP_PMT_TERM_CD           VARCHAR2(4),
        VNDR_CUST_NBR                  VARCHAR2(30),
        PMT_RQST_PAY_DT                DATE,
        PMT_RQST_CST_SRC_CD            VARCHAR2(4) CONSTRAINT AP_PMT_RQST_TN9 NOT NULL,
        PMT_RQST_CNCL_IND              VARCHAR2(1),
        PMT_ATT_IND                    VARCHAR2(1),
        IMD_PMT_IND                    VARCHAR2(1),
        PMT_HLD_IND                    VARCHAR2(1),
        PMT_NTE_LN1_TXT                VARCHAR2(90),
        PMT_NTE_LN2_TXT                VARCHAR2(90),
        PMT_NTE_LN3_TXT                VARCHAR2(90),
        PMT_SPCL_HANDLG_INSTRC_LN1_TXT VARCHAR2(90),
        PMT_SPCL_HANDLG_INSTRC_LN2_TXT VARCHAR2(90),
        PMT_SPCL_HANDLG_INSTRC_LN3_TXT VARCHAR2(90),
        PMT_PD_DT                      DATE, 
        PMT_RQST_ELCTRNC_INV_IND       VARCHAR2(1),
        VNDR_HDR_GNRTD_ID              NUMBER(10),
        VNDR_DTL_ASND_ID               NUMBER(10),
        VNDR_NM                        VARCHAR2(45),
        VNDR_LN1_ADDR                  VARCHAR2(45),
        VNDR_LN2_ADDR                  VARCHAR2(45),
        VNDR_CTY_NM                    VARCHAR2(45),
        VNDR_ST_CD                     VARCHAR2(2),
        VNDR_PSTL_CD                   VARCHAR2(20),
        VNDR_CNTRY_CD                  VARCHAR2(2),
        PMT_EXTRT_DT                   DATE,
        AP_PRCSR_ID                    VARCHAR2(11) CONSTRAINT AP_PMT_RQST_TN10 NOT NULL,
        AP_RQST_CNCL_ID                VARCHAR2(11),
        AP_HLD_ID                      VARCHAR2(11),
        PRCS_CMP_CD                    VARCHAR2(2),
        AP_APRVL_DT                    DATE,
        ORIG_VNDR_HDR_GNRTD_ID         NUMBER(10),
        ORIG_VNDR_DTL_ASND_ID          NUMBER(10),
        ALTRNT_VNDR_HDR_GNRTD_ID       NUMBER(10),
        ALTRNT_VNDR_DTL_ASND_ID        NUMBER(10),
        CONT_ACCT_IND                  VARCHAR2(1),
        AP_PUR_DOC_LNK_ID              NUMBER(10),
        CLOSE_PO_IND                   VARCHAR2(1), 
        REOPEN_PO_IND                  VARCHAR2(1),
        VNDR_ADDR_INTL_PROV_NM         VARCHAR2(45),
     CONSTRAINT AP_PMT_RQST_TP1 PRIMARY KEY (
        PMT_RQST_ID),
     CONSTRAINT AP_PMT_RQST_TC0 UNIQUE (OBJ_ID)
)
/
