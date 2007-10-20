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
CREATE INDEX AP_CRDT_MEMO_TI1 ON AP_CRDT_MEMO_T(
        PO_ID)
/
CREATE INDEX AP_CRDT_MEMO_TI2 ON AP_CRDT_MEMO_T(
        CRDT_MEMO_STAT_CD)
/
CREATE INDEX AP_CRDT_MEMO_TI3 ON AP_CRDT_MEMO_T(
        PMT_RQST_ID)
/
CREATE INDEX AP_CRDT_MEMO_TI4 ON AP_CRDT_MEMO_T(
        VNDR_HDR_GNRTD_ID,
        VNDR_DTL_ASND_ID)
/
CREATE INDEX AP_CRDT_MEMO_TI6 ON AP_CRDT_MEMO_T(
        FDOC_NBR)
/
