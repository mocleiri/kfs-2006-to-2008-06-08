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
ALTER TABLE AP_ELCTRNC_INV_HDR_INFO_T
ADD (CONSTRAINT AP_ELCTRNC_INV_HDR_INFO_TR1 FOREIGN KEY (
      INV_HDR_INFO_ID )
REFERENCES AP_ELCTRNC_INV_RJT_REAS_T (
      INV_RJT_REAS_ID ))
ADD (CONSTRAINT AP_ELCTRNC_INV_HDR_INFO_TR2 FOREIGN KEY (
      AP_ELCTRNC_INV_LOAD_SUM_ID, VNDR_DUNS_NBR )
REFERENCES AP_ELCTRNC_INV_LOAD_SUM_T (
      AP_ELCTRNC_INV_LOAD_SUM_ID, VNDR_DUNS_NBR ))
ADD (CONSTRAINT AP_ELCTRNC_INV_HDR_INFO_TR3 FOREIGN KEY (
      EPIC_PO_DLVY_CMP_CD )
REFERENCES SH_CAMPUS_T (
      CAMPUS_CD ))
/