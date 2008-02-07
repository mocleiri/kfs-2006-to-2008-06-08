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
CREATE TABLE ER_RF_PSNL_T(
        RDOC_NBR                       VARCHAR2(14) CONSTRAINT ER_RF_PSNL_TN1 NOT NULL,
        RF_PRSN_SEQ_NBR                NUMBER(3,0) CONSTRAINT ER_RF_PSNL_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT ER_RF_PSNL_TN3 NOT NULL,
        VER_NBR                        NUMBER(8,0) DEFAULT 1 CONSTRAINT ER_RF_PSNL_TN4 NOT NULL,
        PRSN_UNVL_ID                   VARCHAR2(10),
        COA_CD                         VARCHAR2(2),
        ORG_CD                         VARCHAR2(4),
        PRSN_CRDT_PCT                  NUMBER(3,2),
        PRSN_FA_PCT                    NUMBER(3,2),
        PRSN_ROLE_CD                   VARCHAR2(1),
        PRSN_PRFX_TXT                  VARCHAR2(15),
        PRSN_SFX_TXT                   VARCHAR2(15),
        PRSN_POS_TTL                   VARCHAR2(30),
        PRSN_DIV_TXT                   VARCHAR2(30),
        PRSN_LN1_ADDR                  VARCHAR2(40),
        PRSN_LN2_ADDR                  VARCHAR2(40),
        PRSN_CTY_NM                    VARCHAR2(30),
        PRSN_CNTY_NM                   VARCHAR2(30),
        PRSN_ST_CD                     VARCHAR2(2),
        PRSN_CNTRY_CD                  VARCHAR2(2),
        PRSN_ZIP_CD                    VARCHAR2(10),
        PRSN_PHN_NBR                   VARCHAR2(10),
        PRSN_FAX_NBR                   VARCHAR2(10),
        PRSN_EMAIL_ADDR                VARCHAR2(100),
        PRSN_ROLE_TXT                  VARCHAR2(50),
        PRSN_TBN_IND                   VARCHAR2(1),
     CONSTRAINT ER_RF_PSNL_TP1 PRIMARY KEY (
        RDOC_NBR,
        RF_PRSN_SEQ_NBR),
        CONSTRAINT ER_RF_PSNL_TC0 UNIQUE (OBJ_ID)
)
/
