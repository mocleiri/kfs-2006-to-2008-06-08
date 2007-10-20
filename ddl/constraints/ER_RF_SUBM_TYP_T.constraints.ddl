/*
 * Copyright 2007 The Kuali Foundation.
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
ALTER TABLE ER_RF_SUBM_TYP_T
  ADD (CONSTRAINT ER_RF_SUBM_TYP_TR1 FOREIGN KEY (
        RDOC_NBR)
  REFERENCES ER_RF_DOC_T (
        RDOC_NBR))
  ADD (CONSTRAINT ER_RF_SUBM_TYP_TR2 FOREIGN KEY (
        SUBM_TYP_CD)
  REFERENCES ER_SUBM_TYP_T (
        SUBM_TYP_CD))
/
