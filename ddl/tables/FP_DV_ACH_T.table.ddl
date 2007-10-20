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
CREATE TABLE FP_DV_ACH_T(
        DV_ACH_PRFL_NBR                VARCHAR2(3) CONSTRAINT FP_DV_ACH_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT FP_DV_ACH_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_DV_ACH_TN3 NOT NULL,
        DV_PMT_MTHD_CD                 VARCHAR2(1),
        DV_BNK_NM                      VARCHAR2(40),
        DV_BNK_RTNG_NBR                VARCHAR2(9),
        DV_BNK_CTY_NM                  VARCHAR2(37),
        DV_BNK_ST_CD                   VARCHAR2(2),
        DV_BNK_CNTRY_NM                VARCHAR2(40),
        DV_ATTN_LN_TXT                 VARCHAR2(80),
        DV_ADDL_WIRE_TXT               VARCHAR2(120),
        DV_PAYEE_ACCT_NBR              VARCHAR2(40),
        DV_PAYEE_ACCT_NM               VARCHAR2(40),
        DV_PAYEE_ACCT_TYP_CD           VARCHAR2(1),
        DV_WIRE_TRNFR_FEE_WVR_IND      CHAR(1),
     CONSTRAINT FP_DV_ACH_TP1 PRIMARY KEY (
        DV_ACH_PRFL_NBR),
     CONSTRAINT FP_DV_ACH_TC0 UNIQUE (OBJ_ID)
)
/
