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
ALTER TABLE CG_PRPSL_T
ADD (CONSTRAINT CG_PRPSL_TR2 FOREIGN KEY (
      CGPRPSL_AWD_TYP_CD )
REFERENCES CG_PRPSL_AWD_TYP_T (
      CGPRPSL_AWD_TYP_CD ))
ADD (CONSTRAINT CG_PRPSL_TR3 FOREIGN KEY (
      CG_AGENCY_NBR )
REFERENCES CG_AGENCY_T (
      CG_AGENCY_NBR ))
ADD (CONSTRAINT CG_PRPSL_TR4 FOREIGN KEY (
      CGPRPSL_STAT_CD )
REFERENCES CG_PRPSL_STAT_T (
      CGPRPSL_STAT_CD ))
ADD (CONSTRAINT CG_PRPSL_TR5 FOREIGN KEY (
      CG_FEDPT_AGNCY_NBR )
REFERENCES CG_AGENCY_T (
      CG_AGENCY_NBR ))
ADD (CONSTRAINT CG_PRPSL_TR6 FOREIGN KEY (
      CGPRPSL_PURPOSE_CD )
REFERENCES CG_PRPSL_PURPOSE_T (
      CGPRPSL_PURPOSE_CD ))
ADD (CONSTRAINT CG_PRPSL_TR7 FOREIGN KEY (
      CG_CFDA_NBR )
REFERENCES CG_CFDA_REF_T (
      CG_CFDA_NBR ))
/