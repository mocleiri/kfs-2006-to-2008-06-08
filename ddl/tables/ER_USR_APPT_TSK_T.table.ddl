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
CREATE TABLE ER_USR_APPT_TSK_T(
        RDOC_NBR                       VARCHAR2(14) CONSTRAINT ER_USR_APPT_TSK_TN1 NOT NULL,
        BDGT_TSK_SEQ_NBR               NUMBER(2,0) CONSTRAINT ER_USR_APPT_TSK_TN2 NOT NULL,
        BDGT_USR_SEQ_NBR               NUMBER(4,0) CONSTRAINT ER_USR_APPT_TSK_TN3 NOT NULL,
        INST_APPT_TYP_CD               VARCHAR2(2) CONSTRAINT ER_USR_APPT_TSK_TN4 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT ER_USR_APPT_TSK_TN5 NOT NULL,
        VER_NBR                        NUMBER(8,0) DEFAULT 1,
     CONSTRAINT ER_USR_APPT_TSK_TP1 PRIMARY KEY (
        RDOC_NBR,
        BDGT_TSK_SEQ_NBR,
        BDGT_USR_SEQ_NBR,
        INST_APPT_TYP_CD),
     CONSTRAINT ER_USR_APPT_TSK_TC0 UNIQUE (OBJ_ID)
)
/
