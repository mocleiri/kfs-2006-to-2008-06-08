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
CREATE TABLE AP_CRDT_MEMO_STAT_T(
        CRDT_MEMO_STAT_CD              VARCHAR2(4) CONSTRAINT AP_CRDT_MEMO_STAT_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AP_CRDT_MEMO_STAT_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AP_CRDT_MEMO_STAT_TN3 NOT NULL,
        CRDT_MEMO_STAT_DESC            VARCHAR2(45) CONSTRAINT AP_CRDT_MEMO_STAT_TN4 NOT NULL,
     CONSTRAINT AP_CRDT_MEMO_STAT_TP1 PRIMARY KEY (
        CRDT_MEMO_STAT_CD),
     CONSTRAINT AP_CRDT_MEMO_STAT_TC0 UNIQUE (OBJ_ID)
)
/
