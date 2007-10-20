/*
 * Copyright 2006-2007 The Kuali Foundation.
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
CREATE TABLE AP_CRDT_MEMO_STAT_HIST_T(
        CRDT_MEMO_STAT_HIST_ID         NUMBER(10) CONSTRAINT AP_CRDT_MEMO_STAT_HIST_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT AP_CRDT_MEMO_STAT_HIST_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AP_CRDT_MEMO_STAT_HIST_TN3 NOT NULL,
        CRDT_MEMO_ID                   NUMBER(9) CONSTRAINT AP_CRDT_MEMO_STAT_HIST_TN4 NOT NULL,
        CRDT_MEMO_OLD_STAT_CD          VARCHAR2(4),
        CRDT_MEMO_NEW_STAT_CD          VARCHAR2(4),
        CRDT_MEMO_STATHST_TS           DATE,
        CRDT_MEMO_STATHST_USR_ID       VARCHAR2(10),
     CONSTRAINT AP_CRDT_MEMO_STAT_HIST_TP1 PRIMARY KEY (
        CRDT_MEMO_STAT_HIST_ID),
     CONSTRAINT AP_CRDT_MEMO_STAT_HIST_TC0 UNIQUE (OBJ_ID)
)
/
