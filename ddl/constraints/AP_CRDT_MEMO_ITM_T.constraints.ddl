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
ALTER TABLE AP_CRDT_MEMO_ITM_T
ADD (CONSTRAINT AP_CRDT_MEMO_ITM_TR1 FOREIGN KEY (
      CRDT_MEMO_ID )
REFERENCES AP_CRDT_MEMO_T (
      CRDT_MEMO_ID ))
ADD (CONSTRAINT AP_CRDT_MEMO_ITM_TR2 FOREIGN KEY (
      ITM_TYP_CD )
REFERENCES PUR_AP_ITM_TYP_T (
      ITM_TYP_CD ))
/
