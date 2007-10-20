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
ALTER TABLE ER_RF_PSNL_T
  ADD (CONSTRAINT ER_RF_PSNL_TR1 FOREIGN KEY (
        RDOC_NBR)
  REFERENCES ER_RF_DOC_T (
        RDOC_NBR))
  ADD (CONSTRAINT ER_RF_PSNL_TR2 FOREIGN KEY (
        COA_CD,ORG_CD)
  REFERENCES CA_ORG_T (
        FIN_COA_CD,ORG_CD))
  ADD (CONSTRAINT ER_RF_PSNL_TR3 FOREIGN KEY (
        RDOC_NBR,PRSN_ROLE_CD)
  REFERENCES ER_RF_PRSN_ROLE_T (
        RDOC_NBR,PRSN_ROLE_CD))
  ADD (CONSTRAINT ER_RF_PSNL_TR4 FOREIGN KEY (
        PRSN_CNTRY_CD)
  REFERENCES SH_COUNTRY_T (
        POSTAL_CNTRY_CD))
  ADD (CONSTRAINT ER_RF_PSNL_TR5 FOREIGN KEY (
        PRSN_ST_CD)
  REFERENCES SH_STATE_T (
        POSTAL_STATE_CD))
/

/*
 * KULERA-733: Disable 1:1 relations due to OJB / Oracle difference
 * in handling constraints on 1:1 mappings only.
 */
ALTER TABLE ER_RF_PSNL_T
  disable CONSTRAINT ER_RF_PSNL_TR3
/
