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
ALTER TABLE PUR_VNDR_HDR_T
ADD (CONSTRAINT PUR_VNDR_HDR_TR1 FOREIGN KEY (
      VNDR_TYP_CD )
REFERENCES PUR_VNDR_TYP_T (
      VNDR_TYP_CD ))
ADD (CONSTRAINT PUR_VNDR_HDR_TR2 FOREIGN KEY (
      VNDR_OWNR_CD )
REFERENCES PUR_OWNR_TYP_T (
      VNDR_OWNR_CD ))
ADD (CONSTRAINT PUR_VNDR_HDR_TR3 FOREIGN KEY (
      VNDR_OWNR_CTGRY_CD )
REFERENCES PUR_OWNR_CTGRY_T (
      VNDR_OWNR_CTGRY_CD ))
/