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
ALTER TABLE PUR_REQS_ITM_T
ADD (CONSTRAINT PUR_REQS_ITM_TR1 FOREIGN KEY (
      REQS_ID )
REFERENCES PUR_REQS_T (
      REQS_ID ))
ADD (CONSTRAINT PUR_REQS_ITM_TR2 FOREIGN KEY (
      CPTL_AST_TRN_TYP_CD )
REFERENCES PUR_AP_CPTL_AST_TRN_TYP_T (
      CPTL_AST_TRN_TYP_CD ))
ADD (CONSTRAINT PUR_REQS_ITM_TR3 FOREIGN KEY (
      ITM_TYP_CD )
REFERENCES PUR_AP_ITM_TYP_T (
      ITM_TYP_CD ))
ADD (CONSTRAINT PUR_REQS_ITM_TR4 FOREIGN KEY (
      PUR_COMM_CD )
REFERENCES PUR_COMM_T (
      PUR_COMM_CD ))
ADD (CONSTRAINT PUR_REQS_ITM_TR5 FOREIGN KEY (
      REQS_ID, CM_SYS_NBR )
REFERENCES PUR_REQS_CM_SYS_T (
      REQS_ID, CM_SYS_NBR))
/
