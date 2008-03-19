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
CREATE TABLE FP_DV_PAYEE_DTL_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT FP_DV_PAYEE_DTL_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_DV_PAYEE_DTL_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_DV_PAYEE_DTL_TN3 NOT NULL,
        DV_PMT_REAS_CD                 VARCHAR2(1),
        DV_ALIEN_PMT_IND               CHAR(1),
        DV_PAYEE_ID_NBR                VARCHAR2(10),
        DV_PAYEE_PRSN_NM               VARCHAR2(40),
        DV_PAYEE_LN1_ADDR              VARCHAR2(40),
        DV_PAYEE_LN2_ADDR              VARCHAR2(40),
        DV_PAYEE_CTY_NM                VARCHAR2(37),
        DV_PAYEE_ST_CD                 VARCHAR2(2),
        DV_PAYEE_ZIP_CD                VARCHAR2(11),
        DV_RMT_PRSN_NM                 VARCHAR2(40),
        DV_RMT_LN1_ADDR                VARCHAR2(40),
        DV_RMT_LN2_ADDR                VARCHAR2(40),
        DV_RMT_CTY_NM                  VARCHAR2(37),
        DV_RMT_ST_CD                   VARCHAR2(2),
        DV_RMT_ZIP_CD                  VARCHAR2(11),
        DV_PAYEE_EMP_IND               CHAR(1),
        DV_PAYEE_RVFND_IND             CHAR(1),
        DV_PAYEE_CNTRY_CD              VARCHAR2(2),
        DV_RMT_CNTRY_CD                VARCHAR2(2),
        DV_PAYEE_TYP_CD                VARCHAR2(1),
     CONSTRAINT FP_DV_PAYEE_DTL_TP1 PRIMARY KEY (
        FDOC_NBR),
     CONSTRAINT FP_DV_PAYEE_DTL_TC0 UNIQUE (OBJ_ID)
)
/