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
CREATE TABLE PUR_REQS_T(
        REQS_ID                        NUMBER(8) CONSTRAINT PUR_REQS_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT PUR_REQS_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_REQS_TN3 NOT NULL,
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT PUR_REQS_TN4 NOT NULL,
        FND_SRC_CD                     VARCHAR2(4),
        REQS_SRC_CD                    VARCHAR2(4) CONSTRAINT PUR_REQS_TN5 NOT NULL,
        REQS_STAT_CD                   VARCHAR2(4) CONSTRAINT PUR_REQS_TN6 NOT NULL,
        PO_TRNS_MTHD_CD                VARCHAR2(4),
        PO_CST_SRC_CD                  VARCHAR2(4) CONSTRAINT PUR_REQS_TN8 NOT NULL,
        DLVY_REQ_DT_REAS_CD            VARCHAR2(4),
        RECUR_PMT_TYP_CD               VARCHAR2(4),
        FIN_COA_CD                     VARCHAR2(2),
        ORG_CD                         VARCHAR2(4),
        DLVY_CMP_CD                    VARCHAR2(2) CONSTRAINT PUR_REQS_TN9 NOT NULL,
        PO_ENCUM_FSCL_YR               NUMBER(4) CONSTRAINT PUR_REQS_TN10 NOT NULL,
        PO_TOT_LMT                     NUMBER(19,2),
        VNDR_HDR_GNRTD_ID              NUMBER(10),
        VNDR_DTL_ASND_ID               NUMBER(10),
        VNDR_NM                        VARCHAR2(45),
        VNDR_LN1_ADDR                  VARCHAR2(45),
        VNDR_LN2_ADDR                  VARCHAR2(45),
        VNDR_CTY_NM                    VARCHAR2(45),
        VNDR_ST_CD                     VARCHAR2(2),
        VNDR_PSTL_CD                   VARCHAR2(20),
        VNDR_CNTRY_CD                  VARCHAR2(2),
        VNDR_RSTRC_IND                 VARCHAR2(1),
        VNDR_PHN_NBR                   VARCHAR2(45),
        VNDR_FAX_NBR                   VARCHAR2(40),
        VNDR_CUST_NBR                  VARCHAR2(30),
        VNDR_CONTR_GNRTD_ID            NUMBER(10),
        VNDR_NTE_TXT                   VARCHAR2(255),
        RQSTR_PRSN_NM                  VARCHAR2(45),
        RQSTR_PRSN_EMAIL_ADDR          VARCHAR2(100),
        RQSTR_PRSN_PHN_NBR             VARCHAR2(45),
        NON_INST_FND_ORG_COA_CD        VARCHAR2(2),
        NON_INST_FND_ORG_CD            VARCHAR2(4),
        NON_INST_FND_COA_CD            VARCHAR2(2),
        NON_INST_FND_ACCT_NBR          VARCHAR2(7),
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
        REQS_ORG_REF_1_TXT             VARCHAR2(20),
        REQS_ORG_REF_2_TXT             VARCHAR2(20),
        REQS_ORG_REF_3_TXT             VARCHAR2(20),
        PO_BEG_DT                      DATE,
        PO_END_DT                      DATE,
        INST_CNTCT_NM                  VARCHAR2(45),
        INST_CNTCT_PHN_NBR             VARCHAR2(45),
        INST_CNTCT_EMAIL_ADDR          VARCHAR2(100),
        ALTRNT_1_VNDR_NM               VARCHAR2(45),
        ALTRNT_2_VNDR_NM               VARCHAR2(45),
        ALTRNT_3_VNDR_NM               VARCHAR2(45),
        ALTRNT_4_VNDR_NM               VARCHAR2(45),
        ALTRNT_5_VNDR_NM               VARCHAR2(45),
        BILL_NM                        VARCHAR2(45) CONSTRAINT PUR_REQS_TN11 NOT NULL,
        BILL_LN1_ADDR                  VARCHAR2(45) CONSTRAINT PUR_REQS_TN12 NOT NULL,
        BILL_LN2_ADDR                  VARCHAR2(45),
        BILL_CTY_NM                    VARCHAR2(45) CONSTRAINT PUR_REQS_TN13 NOT NULL,
        BILL_ST_CD                     VARCHAR2(2) CONSTRAINT PUR_REQS_TN14 NOT NULL,
        BILL_PSTL_CD                   VARCHAR2(20) CONSTRAINT PUR_REQS_TN15 NOT NULL,
        BILL_CNTRY_CD                  VARCHAR2(2) CONSTRAINT PUR_REQS_TN16 NOT NULL,
        BILL_PHN_NBR                   VARCHAR2(20) CONSTRAINT PUR_REQS_TN17 NOT NULL,
        EXT_ORG_B2B_SUPP_ID            VARCHAR2(19),
        CONTR_MGR_CD                   NUMBER(2),
        ORG_AUTO_PO_LMT                NUMBER(19,2),
        PO_AUTO_IND                    VARCHAR2(1),
        AP_PUR_DOC_LNK_ID              NUMBER(10),
     CONSTRAINT PUR_REQS_TP1 PRIMARY KEY (
        REQS_ID),
     CONSTRAINT PUR_REQS_TC0 UNIQUE (OBJ_ID)
)
/
