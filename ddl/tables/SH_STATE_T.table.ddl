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
CREATE TABLE SH_STATE_T(
        POSTAL_STATE_CD                VARCHAR2(2) CONSTRAINT SH_STATE_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT SH_STATE_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT SH_STATE_TN3 NOT NULL,
        POSTAL_STATE_NM                VARCHAR2(40),
     CONSTRAINT SH_STATE_TP1 PRIMARY KEY (
        POSTAL_STATE_CD),
     CONSTRAINT SH_STATE_TC0 UNIQUE (OBJ_ID)
)
/