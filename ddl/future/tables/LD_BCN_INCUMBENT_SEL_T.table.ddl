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
CREATE TABLE LD_BCN_INCUMBENT_SEL_T(
        PERSON_UNVL_ID                 VARCHAR2(10) CONSTRAINT LD_BCN_INCUMBENT_SEL_TN1 NOT NULL,
        EMPLID                         VARCHAR2(11) CONSTRAINT LD_BCN_INCUMBENT_SEL_TN2 NOT NULL,
        FIN_OBJECT_CD                  VARCHAR2(4) CONSTRAINT LD_BCN_INCUMBENT_SEL_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT LD_BCN_INCUMBENT_SEL_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_BCN_INCUMBENT_SEL_TN5 NOT NULL,
        PERSON_NM                      VARCHAR2(50),
     CONSTRAINT LD_BCN_INCUMBENT_SEL_TP1 PRIMARY KEY (
        PERSON_UNVL_ID,
        EMPLID,
        FIN_OBJECT_CD),
     CONSTRAINT LD_BCN_INCUMBENT_SEL_TC0 UNIQUE (OBJ_ID)
)
/
