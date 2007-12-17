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
CREATE TABLE LD_BCN_II_INIT_MT(
        PERSON_UNVL_ID                 VARCHAR2(10) CONSTRAINT LD_BCN_II_INIT_MTN1 NOT NULL,
        EMPLID                         VARCHAR2(11) CONSTRAINT LD_BCN_II_INIT_MTN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT LD_BCN_II_INIT_MTN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_BCN_II_INIT_MTN4 NOT NULL,
        PERSON_NM                      VARCHAR2(50),
        SETID_SALARY                   VARCHAR2(5),
        SAL_ADMIN_PLAN                 VARCHAR2(4),
        GRADE                          VARCHAR2(3),
        IU_CLASSIF_LEVEL               VARCHAR2(2),
     CONSTRAINT LD_BCN_II_INIT_MTP1 PRIMARY KEY (
        PERSON_UNVL_ID,
        EMPLID),
     CONSTRAINT LD_BCN_II_INIT_MTC0 UNIQUE (OBJ_ID)
)
/
