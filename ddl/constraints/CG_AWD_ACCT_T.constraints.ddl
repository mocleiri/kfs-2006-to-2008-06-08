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
ALTER TABLE CG_AWD_ACCT_T
ADD (CONSTRAINT CG_AWD_ACCT_TR1 FOREIGN KEY (
      FIN_COA_CD, ACCOUNT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
ADD (CONSTRAINT CG_AWD_ACCT_TR2 FOREIGN KEY (
      FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT CG_AWD_ACCT_TR3 FOREIGN KEY (
      PERSON_UNVL_ID )
REFERENCES CG_PRJDR_T (
      PERSON_UNVL_ID ))
ADD (CONSTRAINT CG_AWD_ACCT_TR4 FOREIGN KEY (
      CGPRPSL_NBR )
REFERENCES CG_AWD_T (
      CGPRPSL_NBR ))
/