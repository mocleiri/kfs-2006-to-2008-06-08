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
CREATE TABLE SH_ROOM_T(
        CAMPUS_CD                      VARCHAR2(2) CONSTRAINT SH_ROOM_TN1 NOT NULL,
        BLDG_CD                        VARCHAR2(10) CONSTRAINT SH_ROOM_TN2 NOT NULL,
        BLDG_ROOM_NBR                  VARCHAR2(8) CONSTRAINT SH_ROOM_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT SH_ROOM_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT SH_ROOM_TN5 NOT NULL,
        BLDG_ROOM_TYPE                 VARCHAR2(50),
        BLDG_ROOM_DEPT                 VARCHAR2(40),
        BLDG_ROOM_DESC                 VARCHAR2(25),
        ROW_ACTV_IND                   VARCHAR2(1) CONSTRAINT SH_ROOM_TN6 NOT NULL,
     CONSTRAINT SH_ROOM_TP1 PRIMARY KEY (
        CAMPUS_CD,
        BLDG_CD,
        BLDG_ROOM_NBR),
     CONSTRAINT SH_ROOM_TC0 UNIQUE (OBJ_ID)
)
/
