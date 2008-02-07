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
CREATE TABLE GL_PENDING_BALANCES_MT(
        PERSON_UNVL_ID                 VARCHAR2(10) CONSTRAINT GL_PENDING_BALANCES_MTN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT GL_PENDING_BALANCES_MTN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT GL_PENDING_BALANCES_MTN3 NOT NULL,
        APPR_BUDGET                    NUMBER(19, 2),
        APPR_ACTUAL                    NUMBER(19, 2),
        APPR_ENCUM                     NUMBER(19, 2),
        PEND_BUDGET                    NUMBER(19, 2),
        PEND_ACTUAL                    NUMBER(19, 2),
        PEND_ENCUM                     NUMBER(19, 2),
     CONSTRAINT GL_PENDING_BALANCES_MTP1 PRIMARY KEY (
        PERSON_UNVL_ID),
     CONSTRAINT GL_PENDING_BALANCES_MTC0 UNIQUE (OBJ_ID)
)
/
