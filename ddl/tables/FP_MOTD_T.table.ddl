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
CREATE TABLE FP_MOTD_T(
        FS_ORIGIN_CD                   VARCHAR2(2) CONSTRAINT FP_MOTD_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_MOTD_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_MOTD_TN3 NOT NULL,
        FS_MOTD_TXT                    VARCHAR2(2000),
     CONSTRAINT FP_MOTD_TP1 PRIMARY KEY (
        FS_ORIGIN_CD),
     CONSTRAINT FP_MOTD_TC0 UNIQUE (OBJ_ID)
)
/
