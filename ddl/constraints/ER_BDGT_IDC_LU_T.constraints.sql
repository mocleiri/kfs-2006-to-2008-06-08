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
ALTER TABLE ER_BDGT_IDC_LU_T
  ADD (CONSTRAINT ER_BDGT_IDC_LU_TR1 FOREIGN KEY (
        RDOC_NBR)
  REFERENCES ER_BDGT_T (
        RDOC_NBR))
  ADD (CONSTRAINT ER_BDGT_IDC_LU_TR2 FOREIGN KEY (
        BDGT_ON_CMP_IND,BDGT_PRPS_CD)
  REFERENCES ER_IDC_LU_T (
        BDGT_ON_CMP_IND,BDGT_PRPS_CD))
  ADD (CONSTRAINT ER_BDGT_IDC_LU_TR3 FOREIGN KEY (
        BDGT_PRPS_CD)
  REFERENCES ER_PRPS_T (
        PRPS_CD))
/
