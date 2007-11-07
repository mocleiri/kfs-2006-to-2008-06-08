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
ALTER TABLE CM_PRTG_DTL_T
ADD (CONSTRAINT CM_PRTG_DTL_TR1 FOREIGN KEY (
      CAMPUS_CD )
REFERENCES SH_CAMPUS_T (
      CAMPUS_CD ))
ADD (CONSTRAINT CM_PRTG_DTL_TR2 FOREIGN KEY (
      CAMPUS_CD, BLDG_CD )
REFERENCES SH_BUILDING_T (
      CAMPUS_CD, BLDG_CD ))
ADD (CONSTRAINT CM_PRTG_DTL_TR3 FOREIGN KEY (
      CAMPUS_CD, BLDG_CD, BLDG_ROOM_NBR )
REFERENCES SH_ROOM_T (
      CAMPUS_CD, BLDG_CD, BLDG_ROOM_NBR ))
ADD (CONSTRAINT CM_PRTG_DTL_TR4 FOREIGN KEY (
      PO_NUMBER, LINE_ITEM_NUMBER )
REFERENCES CM_PRTG_T (
      PO_NUMBER, LINE_ITEM_NUMBER ))
/
