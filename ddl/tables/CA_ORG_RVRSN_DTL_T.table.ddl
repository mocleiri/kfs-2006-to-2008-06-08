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
CREATE TABLE CA_ORG_RVRSN_DTL_T(
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT CA_ORG_RVRSN_DTL_TN1 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT CA_ORG_RVRSN_DTL_TN2 NOT NULL,
        ORG_CD                         VARCHAR2(4) CONSTRAINT CA_ORG_RVRSN_DTL_TN3 NOT NULL,
        ORG_RVRSN_CTGRY_CD             VARCHAR2(8) CONSTRAINT CA_ORG_RVRSN_DTL_TN4 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CA_ORG_RVRSN_DTL_TN5 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CA_ORG_RVRSN_DTL_TN6 NOT NULL,
        ORG_RVRSN_CD                   VARCHAR2(2),
        ORG_RVRSN_OBJ_CD               VARCHAR2(4),
     CONSTRAINT CA_ORG_RVRSN_DTL_TP1 PRIMARY KEY (
        UNIV_FISCAL_YR,
        FIN_COA_CD,
        ORG_CD,
        ORG_RVRSN_CTGRY_CD),
     CONSTRAINT CA_ORG_RVRSN_DTL_TC0 UNIQUE (OBJ_ID)
)
/
