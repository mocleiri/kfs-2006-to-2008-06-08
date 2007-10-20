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
ALTER TABLE AP_CRDT_MEMO_STAT_HIST_T
ADD (CONSTRAINT AP_CRDT_MEMO_STAT_HIST_TR1 FOREIGN KEY (
      CRDT_MEMO_OLD_STAT_CD )
REFERENCES AP_CRDT_MEMO_STAT_T (
      CRDT_MEMO_STAT_CD ))
ADD (CONSTRAINT AP_CRDT_MEMO_STAT_HIST_TR2 FOREIGN KEY (
      CRDT_MEMO_NEW_STAT_CD )
REFERENCES AP_CRDT_MEMO_STAT_T (
      CRDT_MEMO_STAT_CD ))
/
