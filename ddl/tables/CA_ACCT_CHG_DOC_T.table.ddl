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
CREATE TABLE CA_ACCT_CHG_DOC_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT CA_ACCT_CHG_DOC_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CA_ACCT_CHG_DOC_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CA_ACCT_CHG_DOC_TN3 NOT NULL,
		ACCT_FSC_OFC_UID               VARCHAR2(10),
        ACCT_SPVSR_UNVL_ID             VARCHAR2(10),
        ACCT_MGR_UNVL_ID               VARCHAR2(10),
        FIN_COA_CD                     VARCHAR2(2),
        ORG_CD                         VARCHAR2(4),
        SUB_FUND_GRP_CD                VARCHAR2(6),
        ACCT_CITY_NM                   VARCHAR2(25),
        ACCT_STATE_CD                  VARCHAR2(2),
        ACCT_STREET_ADDR               VARCHAR2(30),
        ACCT_ZIP_CD                    VARCHAR2(11),
        ACCT_EXPIRATION_DT             DATE,
        CONT_FIN_COA_CD                VARCHAR2(2),
        CONT_ACCOUNT_NBR               VARCHAR2(7),
        INCOME_FIN_COA_CD              VARCHAR2(2),
        INCOME_ACCOUNT_NBR             VARCHAR2(7),
        CG_CFDA_NBR                    VARCHAR2(6),
        FIN_HGH_ED_FUNC_CD             VARCHAR2(4),
        ACCT_SF_CD                     VARCHAR2(1),
        ACCT_PND_SF_CD                 VARCHAR2(1),
        ACCT_SRCH_CRTA_TXT             VARCHAR2(400),
     CONSTRAINT CA_ACCT_CHG_DOC_TP1 PRIMARY KEY (
        FDOC_NBR),
     CONSTRAINT CA_ACCT_CHG_DOC_TC0 UNIQUE (OBJ_ID)
)
/
