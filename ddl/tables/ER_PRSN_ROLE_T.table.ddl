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
CREATE TABLE ER_PRSN_ROLE_T(
        PRSN_ROLE_CD                   VARCHAR2(1) CONSTRAINT ER_PRSN_ROLE_TN1 NOT NULL,
        VER_NBR                        NUMBER(8,0) DEFAULT 1 CONSTRAINT ER_PRSN_ROLE_TN3 NOT NULL,
        DOBJ_MAINT_CD_ACTV_IND         VARCHAR2(1) CONSTRAINT ER_PRSN_ROLE_TN4 NOT NULL,
        PRSN_ROLE_DESC                 VARCHAR2(40),
        PRSN_ROLE_SORT_NBR             NUMBER(2,0),
     CONSTRAINT ER_PRSN_ROLE_TP1 PRIMARY KEY (
        PRSN_ROLE_CD)
)
/

