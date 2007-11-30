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
CREATE TABLE LD_BCN_RSN_CD_PK_T(
        PERSON_UNVL_ID                 VARCHAR2(10) CONSTRAINT LD_BCN_RSN_CD_PK_TN1 NOT NULL,
        APPT_FND_REASON_CD             VARCHAR2(3) CONSTRAINT LD_BCN_RSN_CD_PK_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_BCN_RSN_CD_PK_TN3 NOT NULL,
        SELECT_FLAG                    NUMBER(7),
     CONSTRAINT LD_BCN_RSN_CD_PK_TP1 PRIMARY KEY (
        PERSON_UNVL_ID,
        APPT_FND_REASON_CD)
)
/
