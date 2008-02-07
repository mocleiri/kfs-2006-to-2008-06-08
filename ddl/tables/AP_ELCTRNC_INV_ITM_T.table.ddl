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
CREATE TABLE AP_ELCTRNC_INV_ITM_T(
        INV_ITM_ID                     NUMBER(9) CONSTRAINT AP_ELCTRNC_INV_ITM_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AP_ELCTRNC_INV_ITM_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AP_ELCTRNC_INV_ITM_TN3 NOT NULL,
        INV_HDR_INFO_ID                NUMBER(9) CONSTRAINT AP_ELCTRNC_INV_ITM_TN4 NOT NULL,
        INV_ITM_LN_NBR                 NUMBER(4),
        INV_ITM_QTY                    NUMBER(11,4),
        INV_ITM_UOM_CD                 VARCHAR2(4),
        INV_ITM_UNIT_PRC               NUMBER(19,4),
        INV_ITM_UNIT_PRC_CRNCY_CD      VARCHAR2(20),
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
        INV_REF_ITM_LN_NBR             NUMBER(4),
        INV_REF_ITM_SERIAL_NBR         VARCHAR2(100),
        INV_REF_ITM_SUPP_PART_ID       VARCHAR2(100),
        INV_REF_ITM_SUPP_PART_AUX_ID   VARCHAR2(100),
        INV_REF_ITM_DESC               VARCHAR2(400),
        INV_REF_ITM_MFR_PART_ID        VARCHAR2(100),
        INV_REF_ITM_MFR_NM             VARCHAR2(100),
        INV_REF_ITM_CNTRY_CD           VARCHAR2(50),
        INV_REF_ITM_CNTRY_NM           VARCHAR2(100),
        INV_CATLG_NBR                  VARCHAR2(50),
     CONSTRAINT AP_ELCTRNC_INV_ITM_TP1 PRIMARY KEY (
        INV_ITM_ID),
     CONSTRAINT AP_ELCTRNC_INV_ITM_TC0 UNIQUE (OBJ_ID)
)
/
