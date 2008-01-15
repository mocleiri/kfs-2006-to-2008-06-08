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
ALTER TABLE CM_PRTG_T
ADD (CONSTRAINT CM_PRTG_TR1 FOREIGN KEY (
      FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT CM_PRTG_TR2 FOREIGN KEY (
      FIN_COA_CD, ORG_CD )
REFERENCES CA_ORG_T (
      FIN_COA_CD, ORG_CD ))
ADD (CONSTRAINT CM_PRTG_TR3 FOREIGN KEY (
      PO_NUMBER )
REFERENCES CB_PND_PO_T (
      PO_NUMBER ))
ADD (CONSTRAINT CM_PRTG_TR4 FOREIGN KEY (
      CPTLAST_TYP_CD )
REFERENCES CM_ASSET_TYPE_T (
      CPTLAST_TYP_CD ))
/
