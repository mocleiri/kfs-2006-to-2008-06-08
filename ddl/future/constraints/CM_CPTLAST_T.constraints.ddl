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
ALTER TABLE CM_CPTLAST_T
ADD (CONSTRAINT CM_CPTLAST_TR1 FOREIGN KEY (
      ORG_OWNER_COA_CD,
      ORG_OWNER_ACCT_NBR )
REFERENCES CA_ACCOUNT_T ( 
      FIN_COA_CD,
      ACCOUNT_NBR ))
ADD (CONSTRAINT CM_CPTLAST_TR2 FOREIGN KEY (
      CPTLAST_TYP_CD )
REFERENCES CM_ASSET_TYPE_T ( 
      CPTLAST_TYP_CD ))
ADD (CONSTRAINT CM_CPTLAST_TR3 FOREIGN KEY (
      CPTLAST_INS_CD )
REFERENCES CM_INS_T ( 
      CPTLAST_INS_CD ))
ADD (CONSTRAINT CM_CPTLAST_TR4 FOREIGN KEY (
      CAMPUS_CD,
      BLDG_CD,
      BLDG_ROOM_NBR )
REFERENCES SH_ROOM_T ( 
      CAMPUS_CD,
      BLDG_CD,
      BLDG_ROOM_NBR ))
/
