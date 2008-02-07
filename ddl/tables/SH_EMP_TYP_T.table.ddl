/*
 * Copyright 2007 The Kuali Foundation.
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
CREATE TABLE SH_EMP_TYP_T(
        EMP_TYP_CD                     VARCHAR2(1) CONSTRAINT SH_EMP_TYP_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT SH_EMP_TYP_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT SH_EMP_TYP_TN3 NOT NULL,
        EMP_TYP_DESC                   VARCHAR2(30),
        ROW_ACTV_IND                   VARCHAR2(1) CONSTRAINT SH_EMP_TYP_TN4 NOT NULL,
     CONSTRAINT SH_EMP_TYP_TP1 PRIMARY KEY (
        EMP_TYP_CD),
     CONSTRAINT SH_EMP_TYP_TC0 UNIQUE (OBJ_ID)
)
/
