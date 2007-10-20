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
ALTER TABLE PEND_INVOICE_ITEMT
ADD (CONSTRAINT PEND_INVOICE_ITEMTR1 FOREIGN KEY (
      PO_NUMBER,
      INVOICE_NUMBER )
REFERENCES PEND_INVOICET ( 
      PO_NUMBER,
      INVOICE_NUMBER ))
ADD (CONSTRAINT PEND_INVOICE_ITEMTR2 FOREIGN KEY (
      REASON_PEND_CODE )
REFERENCES PEND_REASONT ( 
      REASON_PEND_CODE ))
/
