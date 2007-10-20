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
ALTER TABLE FP_DV_PAYEE_T
ADD (CONSTRAINT FP_DV_PAYEE_TR1 FOREIGN KEY (
      DV_PAYEE_OWNTYP_CD )
REFERENCES FP_DV_OWNR_TYP_T (
      DV_PAYEE_OWNTYP_CD ))
ADD (CONSTRAINT FP_DV_PAYEE_TR2 FOREIGN KEY (
      DV_PAYEE_TXCTRL_CD )
REFERENCES FP_DV_TAX_CTRL_T (
      DV_PAYEE_TXCTRL_CD ))
/