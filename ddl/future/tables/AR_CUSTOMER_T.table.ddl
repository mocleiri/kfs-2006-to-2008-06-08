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
CREATE TABLE AR_CUSTOMER_T(
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT AR_CUSTOMER_TN1 NOT NULL,
        ORG_CD                         VARCHAR2(4) CONSTRAINT AR_CUSTOMER_TN2 NOT NULL,
        CUST_NBR                       VARCHAR2(9) CONSTRAINT AR_CUSTOMER_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AR_CUSTOMER_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AR_CUSTOMER_TN5 NOT NULL,
        CUST_NM                        VARCHAR2(40),
        CUST_LOC_ADDR_NM               VARCHAR2(40),
        CUST_LOC_LN1_ADDR              VARCHAR2(30),
        CUST_LOC_LN2_ADDR              VARCHAR2(30),
        CUST_LOC_CITY_NM               VARCHAR2(25),
        CUST_LOC_ST_CD                 VARCHAR2(2),
        CUST_LOC_ZIP_CD                VARCHAR2(11),
        CUST_BILL_ADDR_NM              VARCHAR2(40),
        CUST_BILL_LN1_ADDR             VARCHAR2(30),
        CUST_BILL_LN2_ADDR             VARCHAR2(30),
        CUST_BILL_CITY_NM              VARCHAR2(25),
        CUST_BILL_ST_CD                VARCHAR2(2),
        CUST_BILL_ZIP_CD               VARCHAR2(11),
        CUST_PREV_ADDR_NM              VARCHAR2(40),
        CUST_PREV_LN1_ADDR             VARCHAR2(30),
        CUST_PREV_LN2_ADDR             VARCHAR2(30),
        CUST_PREV_CITY_NM              VARCHAR2(25),
        CUST_PREV_ST_CD                VARCHAR2(2),
        CUST_PREV_ZIP_CD               VARCHAR2(11),
        CUST_ADDR_CHG_DT               DATE,
        CUST_FEIN_SSN_ID               VARCHAR2(9),
        CUST_ACTIVE_CD                 VARCHAR2(1),
        CUST_LST_ACTV_DT               DATE,
        CUST_PHONE_NBR                 VARCHAR2(13),
        CUST_800_PHONE_NBR             VARCHAR2(13),
        CUST_CNTCT_NM                  VARCHAR2(40),
        CUST_CNTCT_PHN_NBR             VARCHAR2(13),
        CUST_FAX_NBR                   VARCHAR2(13),
        CUST_BIRTH_DT                  DATE,
        CUST_STOP_BILL_CD              VARCHAR2(3),
        CUST_TAX_EXMPT_IND             VARCHAR2(1),
        CUST_CLCTN_STAT_CD             VARCHAR2(3),
        CLCTR_FIN_COA_CD               VARCHAR2(2),
        CLCTR_ORG_CD                   VARCHAR2(4),
        ORG_ASGN_CLCTR_ID              VARCHAR2(9),
        CUST_CLCTN_RVW_DT              DATE,
        CUST_BNKRPTCY_CD               VARCHAR2(3),
        CUST_BNKRPTCY_DT               DATE,
        CUST_LAST_BILL_DT              DATE,
        CUST_BANK_NBR                  VARCHAR2(10),
        CUST_CHECK_NBR                 VARCHAR2(15),
        CUST_BNKACCTTYP_CD             VARCHAR2(1),
        CUST_CRDT_LMT_AMT              NUMBER(19, 2),
        CUST_CRDT_APRV_NM              VARCHAR2(40),
     CONSTRAINT AR_CUSTOMER_TP1 PRIMARY KEY (
        FIN_COA_CD,
        ORG_CD,
        CUST_NBR),
     CONSTRAINT AR_CUSTOMER_TC0 UNIQUE (OBJ_ID)
)
/
