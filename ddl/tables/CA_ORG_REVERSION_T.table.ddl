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
CREATE TABLE CA_ORG_REVERSION_T(
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT CA_ORG_REVERSION_TN1 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT CA_ORG_REVERSION_TN2 NOT NULL,
        ORG_CD                         VARCHAR2(4) CONSTRAINT CA_ORG_REVERSION_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT CA_ORG_REVERSION_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CA_ORG_REVERSION_TN5 NOT NULL,
        BDGT_RVRSN_COA_CD              VARCHAR2(2),
        BDGT_RVRSNACCT_NBR             VARCHAR2(7),
        CF_BY_OBJ_CD_IND               VARCHAR2(1),
        CSH_RVRSNFINCOA_CD             VARCHAR2(2),
        CSH_RVRSN_ACCT_NBR             VARCHAR2(7),
     CONSTRAINT CA_ORG_REVERSION_TP1 PRIMARY KEY (
        UNIV_FISCAL_YR,
        FIN_COA_CD,
        ORG_CD),
     CONSTRAINT CA_ORG_REVERSION_TC0 UNIQUE (OBJ_ID)
)
/