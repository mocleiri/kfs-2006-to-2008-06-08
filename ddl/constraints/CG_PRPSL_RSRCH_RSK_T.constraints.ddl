/*
 * Copyright 2007 The Kuali Foundation.
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
ALTER TABLE CG_PRPSL_RSRCH_RSK_T
ADD (CONSTRAINT CG_PRPSL_RSRCH_RSK_TR1 FOREIGN KEY (
      CGPRPSL_NBR )
REFERENCES CG_PRPSL_T (
      CGPRPSL_NBR ))
ADD (CONSTRAINT CG_PRPSL_RSRCH_RSK_TR2 FOREIGN KEY (
      RSRCH_RSK_TYP_CD )
REFERENCES ER_RSRCH_RSK_TYP_T (
      RSRCH_RSK_TYP_CD ))
/