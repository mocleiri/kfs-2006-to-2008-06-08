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
CREATE TABLE FP_DV_PAYEE_T(
        DV_PAYEE_ID_NBR                VARCHAR2(10) CONSTRAINT FP_DV_PAYEE_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_DV_PAYEE_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_DV_PAYEE_TN3 NOT NULL,
        DV_TAX_ID_NBR                  VARCHAR2(4000),
        DV_TXPYR_TYP_CD                CHAR(1),
        DV_PAYEE_PRSN_NM               VARCHAR2(40),
        DV_PAYEE_LN1_ADDR              VARCHAR2(40),
        DV_PAYEE_LN2_ADDR              VARCHAR2(40),
        DV_PAYEE_CTY_NM                VARCHAR2(37),
        DV_PAYEE_ST_CD                 VARCHAR2(2),
        DV_PAYEE_ZIP_CD                VARCHAR2(9),
        DV_TXPYR_LN1_ADDR              VARCHAR2(40),
        DV_TXPYR_LN2_ADDR              VARCHAR2(40),
        DV_TXPYR_CTY_NM                VARCHAR2(37),
        DV_TXPYR_ST_CD                 VARCHAR2(2),
        DV_TXPYR_ZIP_CD                VARCHAR2(9),
        DV_PAYEE_OWNTYP_CD             VARCHAR2(1),
        DV_PAYEE_TAX_CD                CHAR(1),
        DV_PAYEE_TXCTRL_CD             VARCHAR2(1),
        DV_TAXCTRL_MDFY_DT             DATE,
        DV_PAYEE_ENTRY_DT              DATE,
        DV_ALIEN_PMT_IND               CHAR(1),
        DV_W9_CMPLT_IND                CHAR(1),
        DV_PAYEE_ACTV_IND              CHAR(1),
        DV_PAYEE_LST_PD_DT             DATE,
        DV_PAYEE_EMP_IND               CHAR(1),
        DV_PAYEE_RVFND_IND             CHAR(1),
        DV_PAYEE_CNTRY_CD              VARCHAR2(2),
        DV_TXPYR_CNTRY_CD              VARCHAR2(2),
     CONSTRAINT FP_DV_PAYEE_TP1 PRIMARY KEY (
        DV_PAYEE_ID_NBR),
     CONSTRAINT FP_DV_PAYEE_TC0 UNIQUE (OBJ_ID)
)
/
