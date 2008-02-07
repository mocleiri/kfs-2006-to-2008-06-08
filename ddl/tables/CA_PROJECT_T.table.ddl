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
CREATE TABLE CA_PROJECT_T(
        PROJECT_CD                     VARCHAR2(10) CONSTRAINT CA_PROJECT_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CA_PROJECT_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CA_PROJECT_TN3 NOT NULL,
        PROJ_MGR_UNVL_ID               VARCHAR2(10),
        PROJECT_NM                     VARCHAR2(40),
        FIN_COA_CD                     VARCHAR2(2),
        ORG_CD                         VARCHAR2(4),
        PROJ_ACTIVE_CD                 VARCHAR2(1),
        PROJECT_DESC                   VARCHAR2(400),
     CONSTRAINT CA_PROJECT_TP1 PRIMARY KEY (
        PROJECT_CD),
     CONSTRAINT CA_PROJECT_TC0 UNIQUE (OBJ_ID)
)
/
