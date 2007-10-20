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
ALTER TABLE ER_BDGT_TSK_PRD_IDC_T
  ADD (CONSTRAINT ER_BDGT_TSK_PRD_IDC_TR1 FOREIGN KEY (
        RDOC_NBR)
  REFERENCES ER_BDGT_T (
        RDOC_NBR))
  ADD (CONSTRAINT ER_BDGT_TSK_PRD_IDC_TR2 FOREIGN KEY (
        RDOC_NBR,BDGT_TSK_SEQ_NBR)
  REFERENCES ER_BDGT_TSK_T (
        RDOC_NBR,BDGT_TSK_SEQ_NBR))
  ADD (CONSTRAINT ER_BDGT_TSK_PRD_IDC_TR3 FOREIGN KEY (
        RDOC_NBR,BDGT_PRD_SEQ_NBR)
  REFERENCES ER_BDGT_PRD_T (
        RDOC_NBR,BDGT_PRD_SEQ_NBR))
/

/*
 * KULERA-733: Disable 1:1 relations due to OJB / Oracle difference
 * in handling constraints on 1:1 mappings only.
 */
ALTER TABLE ER_BDGT_TSK_PRD_IDC_T
  disable CONSTRAINT ER_BDGT_TSK_PRD_IDC_TR1
  disable CONSTRAINT ER_BDGT_TSK_PRD_IDC_TR2
  disable CONSTRAINT ER_BDGT_TSK_PRD_IDC_TR3
/
