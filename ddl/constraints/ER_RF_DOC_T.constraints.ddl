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
ALTER TABLE ER_RF_DOC_T
  ADD (CONSTRAINT ER_RF_DOC_TR1 FOREIGN KEY (
        RF_BDGT_NBR)
  REFERENCES ER_BDGT_T (
        RDOC_NBR))
  ADD (CONSTRAINT ER_RF_DOC_TR2 FOREIGN KEY (
        RF_PARNT_NBR)
  REFERENCES ER_RF_DOC_T (
        RDOC_NBR))
  ADD (CONSTRAINT ER_RF_DOC_TR3 FOREIGN KEY (
        RDOC_NBR,RF_PRPS_CD)
  REFERENCES ER_RF_PRPS_T (
        RDOC_NBR,PRPS_CD))
  ADD (CONSTRAINT ER_RF_DOC_TR4 FOREIGN KEY (
        RF_STAT_CD)
  REFERENCES ER_RF_STAT_T (
        RF_STAT_CD))
  ADD (CONSTRAINT ER_RF_DOC_TR5 FOREIGN KEY (
        RDOC_NBR,RSRCH_TYP_CD)
  REFERENCES ER_RF_RSRCH_TYP_CD_T (
        RDOC_NBR,RSRCH_TYP_CD))
  ADD (CONSTRAINT ER_RF_DOC_TR6 FOREIGN KEY (
        RDOC_NBR,SUBM_TYP_CD)
  REFERENCES ER_RF_SUBM_TYP_T (
        RDOC_NBR,SUBM_TYP_CD))
  ADD (CONSTRAINT ER_RF_DOC_TR7 FOREIGN KEY (
        RF_CFDA_NBR)
  REFERENCES CG_CFDA_REF_T (
        CG_CFDA_NBR))
/

/*
 * KULERA-733: Disable 1:1 relations due to OJB / Oracle difference
 * in handling constraints on 1:1 mappings only.
 */
ALTER TABLE ER_RF_DOC_T
  disable CONSTRAINT ER_RF_DOC_TR3
/
ALTER TABLE ER_RF_DOC_T
  disable CONSTRAINT ER_RF_DOC_TR5
/
ALTER TABLE ER_RF_DOC_T
  disable CONSTRAINT ER_RF_DOC_TR6
/
