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
ALTER TABLE AP_CRDT_MEMO_T
ADD (CONSTRAINT AP_CRDT_MEMO_TR2 FOREIGN KEY (
      FDOC_NBR )
REFERENCES FP_DOC_HEADER_T (
      FDOC_NBR ))
ADD (CONSTRAINT AP_CRDT_MEMO_TR3 FOREIGN KEY (
      VNDR_HDR_GNRTD_ID, VNDR_DTL_ASND_ID )
REFERENCES PUR_VNDR_DTL_T (
      VNDR_HDR_GNRTD_ID, VNDR_DTL_ASND_ID ))
ADD (CONSTRAINT AP_CRDT_MEMO_TR4 FOREIGN KEY (
      VNDR_CNTRY_CD )
REFERENCES SH_COUNTRY_T (
      POSTAL_CNTRY_CD ))
ADD (CONSTRAINT AP_CRDT_MEMO_TR6 FOREIGN KEY (
      PMT_RQST_ID )
REFERENCES AP_PMT_RQST_T (
      PMT_RQST_ID ))
ADD (CONSTRAINT AP_CRDT_MEMO_TR7 FOREIGN KEY (
      CRDT_MEMO_STAT_CD )
REFERENCES AP_CRDT_MEMO_STAT_T (
      CRDT_MEMO_STAT_CD ))
ADD (CONSTRAINT AP_CRDT_MEMO_TR8 FOREIGN KEY (
      PROC_CMP_CD )
REFERENCES SH_CAMPUS_T (
      CAMPUS_CD ))
/