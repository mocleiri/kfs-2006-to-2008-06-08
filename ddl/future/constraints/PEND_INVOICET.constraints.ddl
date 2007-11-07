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
ALTER TABLE PEND_INVOICET
ADD (CONSTRAINT PEND_INVOICETR1 FOREIGN KEY (
      LAST_ACTION_CD )
REFERENCES PEND_ACTIONT ( 
      ACTION_CODE ))
ADD (CONSTRAINT PEND_INVOICETR2 FOREIGN KEY (
      CAMPUS_CODE )
REFERENCES SH_CAMPUS_T (
      CAMPUS_CD ))
ADD (CONSTRAINT PEND_INVOICETR3 FOREIGN KEY (
      PO_NUMBER )
REFERENCES PEND_PURCH_ORDERT ( 
      PO_NUMBER ))
/
