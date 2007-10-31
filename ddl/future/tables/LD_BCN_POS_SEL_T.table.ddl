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
CREATE TABLE LD_BCN_POS_SEL_T(
        PERSON_UNVL_ID                 VARCHAR2(10) CONSTRAINT LD_BCN_POS_SEL_TN1 NOT NULL,
        POSITION_NBR                   VARCHAR2(8) CONSTRAINT LD_BCN_POS_SEL_TN2 NOT NULL,
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT LD_BCN_POS_SEL_TN3 NOT NULL,
        EMPLID                         VARCHAR2(11) CONSTRAINT LD_BCN_POS_SEL_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_BCN_POS_SEL_TN6 NOT NULL,
        IU_POSITION_TYPE               VARCHAR2(2),
        POS_DEPTID                     VARCHAR2(10),
        SETID_SALARY                   VARCHAR2(5),
        SAL_ADMIN_PLAN                 VARCHAR2(4),
        GRADE                          VARCHAR2(3),
        POS_DESCR                      VARCHAR2(30),
        PERSON_NM                      VARCHAR2(50),
     CONSTRAINT LD_BCN_POS_SEL_TP1 PRIMARY KEY (
        PERSON_UNVL_ID,
        POSITION_NBR,
        UNIV_FISCAL_YR,
        EMPLID)
)
/
