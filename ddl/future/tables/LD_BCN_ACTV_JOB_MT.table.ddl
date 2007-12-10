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
CREATE TABLE LD_BCN_ACTV_JOB_MT(
        PERSON_UNVL_ID                 VARCHAR2(10) CONSTRAINT LD_BCN_ACTV_JOB_MTN1 NOT NULL,
        EMPLID                         VARCHAR2(11) CONSTRAINT LD_BCN_ACTV_JOB_MTN2 NOT NULL,
        EMPL_RCD                       NUMBER(3) CONSTRAINT LD_BCN_ACTV_JOB_MTN3 NOT NULL,
        EFFDT                          DATE CONSTRAINT LD_BCN_ACTV_JOB_MTN4 NOT NULL,
        EFFSEQ                         NUMBER(3) CONSTRAINT LD_BCN_ACTV_JOB_MTN5 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT LD_BCN_ACTV_JOB_MTN6 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_BCN_ACTV_JOB_MTN7 NOT NULL,
        POSITION_NBR                   VARCHAR2(8),
        EMPL_STATUS                    VARCHAR2(1),
        DEPTID                         VARCHAR2(10),
     CONSTRAINT LD_BCN_ACTV_JOB_MTP1 PRIMARY KEY (
        PERSON_UNVL_ID,
        EMPLID,
        EMPL_RCD,
        EFFDT,
        EFFSEQ),
     CONSTRAINT LD_BCN_ACTV_JOB_MTC0 UNIQUE (OBJ_ID)
)
/
