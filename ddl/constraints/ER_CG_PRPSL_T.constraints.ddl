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
ALTER TABLE ER_CG_PRPSL_T
  ADD (CONSTRAINT ER_CG_PRPSL_TR1 FOREIGN KEY (
        RDOC_NBR)
  REFERENCES ER_RF_DOC_T (
        RDOC_NBR))
/

/*
 * KULERA-733: Disable 1:1 relations due to OJB / Oracle difference
 * in handling constraints on 1:1 mappings only.
 */
ALTER TABLE ER_CG_PRPSL_T
  disable CONSTRAINT ER_CG_PRPSL_TR1
/
