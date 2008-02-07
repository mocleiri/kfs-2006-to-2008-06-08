/*
 * Copyright 2005-2007 The Kuali Foundation.
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
CREATE TABLE FP_DV_NRA_TAX_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT FP_DV_NRA_TAX_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_DV_NRA_TAX_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_DV_NRA_TAX_TN3 NOT NULL,
        FED_INC_TAX_PCT                NUMBER(5,2),
        ST_INC_TAX_PCT                 NUMBER(5,2),
        INC_CLS_CD                     VARCHAR2(2),
        POSTAL_CNTRY_CD                VARCHAR2(2),
        INC_TAX_TRTY_EXMPT_IND         CHAR(1),
        FRGN_SRC_INC_IND               CHAR(1),
        INC_TAX_GRS_UP_IND             CHAR(1),
        FS_REF_ORIGIN_CD               VARCHAR2(2),
        FDOC_REF_NBR                   VARCHAR2(14),
        FDOC_ACCTG_LN_TXT              VARCHAR2(100),
     CONSTRAINT FP_DV_NRA_TAX_TP1 PRIMARY KEY (
        FDOC_NBR),
     CONSTRAINT FP_DV_NRA_TAX_TC0 UNIQUE (OBJ_ID)
)
/
