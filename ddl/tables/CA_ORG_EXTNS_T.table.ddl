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
CREATE TABLE CA_ORG_EXTNS_T(
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT CA_ORG_EXTNS_TN1 NOT NULL,
        ORG_CD                         VARCHAR2(4) CONSTRAINT CA_ORG_EXTNS_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CA_ORG_EXTNS_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CA_ORG_EXTNS_TN4 NOT NULL,
        DESCRSHORT                     VARCHAR2(10),
        COMPANY                        VARCHAR2(3),
        SETID_LOCATION                 VARCHAR2(5),
        LOCATION                       VARCHAR2(10),
        TAX_LOCATION_CD                VARCHAR2(10),
        MANAGER_UNVL_ID                VARCHAR2(10),
        MANAGER_POSN                   VARCHAR2(8),
        BUDGET_YR_END_DT               NUMBER(4),
        BUDGET_LVL                     VARCHAR2(1),
        GL_EXPENSE                     VARCHAR2(35),
        EEO4_FUNCTION                  VARCHAR2(2),
        ACCIDENT_INS                   VARCHAR2(3),
        SI_ACCIDENT_NUM                VARCHAR2(15),
        HAZARD                         VARCHAR2(4),
        ESTABID                        VARCHAR2(5),
        RISKCD                         VARCHAR2(6),
        FTE_EDIT_INDC                  VARCHAR2(1),
        DEPT_TENURE_FLG                VARCHAR2(1),
        TL_DISTRIB_INFO                VARCHAR2(1),
        USE_BUDGETS                    VARCHAR2(1),
        USE_ENCUMBRANCES               VARCHAR2(1),
        USE_DISTRIBUTION               VARCHAR2(1),
        BUDGET_DEPTID                  VARCHAR2(10),
        DIST_PRORATE_OPTN              VARCHAR2(1),
        HP_STATS_DEPT_CD               VARCHAR2(3),
        HP_STATS_FACULTY               VARCHAR2(5),
        ACCOUNTING_OWNER               VARCHAR2(30),
        COUNTRY_GRP                    VARCHAR2(18),
        IU_ORG_MAIL_DRP_CD             VARCHAR2(15),
        IU_ORG_ADDRESS_2               VARCHAR2(30),
        IU_ORG_ADDRESS_3               VARCHAR2(30),
        IU_CAMPUS_CD                   VARCHAR2(2),
        IU_CAMPUS_BLDG                 VARCHAR2(10),
        IU_CAMPUS_ROOM                 VARCHAR2(8),
        IU_POSN_ALLOW_FL               VARCHAR2(1),
        IU_TENURE_ALLOW_FL             VARCHAR2(1),
        IU_TITLE_ALLOW_FL              VARCHAR2(1),
        IU_OCC_UN_ALLOW_FL             VARCHAR2(1),
        ORG_FSCL_APRVR_UID             VARCHAR2(10),
        LAST_UPDATE_DATE               DATE,
     CONSTRAINT CA_ORG_EXTNS_TP1 PRIMARY KEY (
        FIN_COA_CD,
        ORG_CD),
     CONSTRAINT CA_ORG_EXTNS_TC0 UNIQUE (OBJ_ID)
)
/
