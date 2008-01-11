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
CREATE TABLE CG_PRPSL_STAT_T(
        CGPRPSL_STAT_CD                VARCHAR2(2) CONSTRAINT CG_PRPSL_STAT_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT CG_PRPSL_STAT_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CG_PRPSL_STAT_TN3 NOT NULL,
        CGPRPSL_STAT_DESC              VARCHAR2(15),
        ROW_ACTV_IND                   VARCHAR2(1) CONSTRAINT CG_PRPSL_STAT_TN4 NOT NULL,
     CONSTRAINT CG_PRPSL_STAT_TP1 PRIMARY KEY (
        CGPRPSL_STAT_CD),
     CONSTRAINT CG_PRPSL_STAT_TC0 UNIQUE (OBJ_ID)
)
/