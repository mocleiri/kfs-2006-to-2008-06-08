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
ALTER TABLE PUR_VNDR_ALIAS_T
ADD (CONSTRAINT PUR_VNDR_ALIAS_TR1 FOREIGN KEY (
      VNDR_HDR_GNRTD_ID, VNDR_DTL_ASND_ID )
REFERENCES PUR_VNDR_DTL_T (
      VNDR_HDR_GNRTD_ID, VNDR_DTL_ASND_ID ))
/