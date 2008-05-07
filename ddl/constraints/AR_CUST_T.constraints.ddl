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
ALTER TABLE AR_CUST_T
ADD (CONSTRAINT AR_CUST_TR1 FOREIGN KEY (
      CUST_PARENT_CO_NBR )
REFERENCES AR_CUST_T ( 
      CUST_NBR ))
ADD (CONSTRAINT AR_CUST_TR2 FOREIGN KEY (
      CUST_TYP_CD )
REFERENCES AR_CUST_TYP_T (
      CUST_TYP_CD ))
/
