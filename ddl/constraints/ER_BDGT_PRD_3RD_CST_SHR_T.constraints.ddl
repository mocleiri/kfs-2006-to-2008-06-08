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
ALTER TABLE ER_BDGT_PRD_3RD_CST_SHR_T
  ADD (CONSTRAINT ER_BDGT_PRD_3RD_CST_SHR_TR1 FOREIGN KEY (
        RDOC_NBR,BDGT_3RD_PRTY_CST_SHR_SEQ_NBR)
  REFERENCES ER_BDGT_3RD_PRTY_CST_SHR_T (
        RDOC_NBR,BDGT_3RD_PRTY_CST_SHR_SEQ_NBR))
  ADD (CONSTRAINT ER_BDGT_PRD_3RD_CST_SHR_TR2 FOREIGN KEY (
        RDOC_NBR,BDGT_PRD_SEQ_NBR)
  REFERENCES ER_BDGT_PRD_T (
        RDOC_NBR,BDGT_PRD_SEQ_NBR))
  ADD (CONSTRAINT ER_BDGT_PRD_3RD_CST_SHR_TR3 FOREIGN KEY (
        RDOC_NBR)
  REFERENCES ER_BDGT_T (
        RDOC_NBR))
/
