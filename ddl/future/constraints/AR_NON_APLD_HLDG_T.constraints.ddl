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
ALTER TABLE AR_NON_APLD_HLDG_T
ADD (CONSTRAINT AR_NON_APLD_HLDG_TR1 FOREIGN KEY (
      CUST_NBR )
REFERENCES AR_CUST_T ( 
      CUST_NBR ))
ADD (CONSTRAINT AR_NON_APLD_HLDG_TR2 FOREIGN KEY (
      FDOC_REF_NBR )
REFERENCES AR_DOC_HDR_T ( 
      FDOC_NBR ))
ADD (CONSTRAINT AR_NON_APLD_HLDG_TR3 FOREIGN KEY (
      FDOC_REF_NBR )
REFERENCES FP_DOC_HEADER_T ( 
      FDOC_NBR ))
/
