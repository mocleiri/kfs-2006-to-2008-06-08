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
CREATE TABLE AR_ORG_OPTION_T(
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT AR_ORG_OPTION_TN1 NOT NULL,
        ORG_CD                         VARCHAR2(4) CONSTRAINT AR_ORG_OPTION_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT AR_ORG_OPTION_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AR_ORG_OPTION_TN4 NOT NULL,
        PRCS_FIN_COA_CD                VARCHAR2(2),
        PRCS_ORG_CD                    VARCHAR2(4),
        AR_PRN_INV_IND                 VARCHAR2(1),
        ORG_PMT_TERMS_TXT              VARCHAR2(40),
        ORG_MSG_TXT                    VARCHAR2(80),
        ORG_REMIT_ADDR_NM              VARCHAR2(40),
        ORG_REMIT_LN1_ADDR             VARCHAR2(30),
        ORG_REMIT_LN2_ADDR             VARCHAR2(30),
        ORG_REMIT_CITY_NM              VARCHAR2(25),
        ORG_REMIT_ST_CD                VARCHAR2(2),
        ORG_REMIT_ZIP_CD               VARCHAR2(11),
        ORG_PHONE_NBR                  VARCHAR2(13),
        ORG_800_PHONE_NBR              VARCHAR2(13),
        ORG_FAX_NBR                    VARCHAR2(13),
        UNIV_NM                        VARCHAR2(40),
        ORG_CHCK_PAY_TO_NM             VARCHAR2(40),
     CONSTRAINT AR_ORG_OPTION_TP1 PRIMARY KEY (
        FIN_COA_CD,
        ORG_CD),
     CONSTRAINT AR_ORG_OPTION_TC0 UNIQUE (OBJ_ID)
)
/
