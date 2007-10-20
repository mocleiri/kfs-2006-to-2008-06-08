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
ALTER TABLE CG_AWD_T
ADD (CONSTRAINT CG_AWD_TR1 FOREIGN KEY (
      CGPRPSL_NBR )
REFERENCES CG_PRPSL_T (
      CGPRPSL_NBR ))
ADD (CONSTRAINT CG_AWD_TR2 FOREIGN KEY (
      CGPRPSL_AWD_TYP_CD )
REFERENCES CG_PRPSL_AWD_TYP_T (
      CGPRPSL_AWD_TYP_CD ))
ADD (CONSTRAINT CG_AWD_TR3 FOREIGN KEY (
      CGAWD_STAT_CD )
REFERENCES CG_AWD_STAT_T (
      CGAWD_STAT_CD ))
ADD (CONSTRAINT CG_AWD_TR4 FOREIGN KEY (
      CG_LTRCR_FNDGRP_CD )
REFERENCES CG_LTRCR_FNDGRP_T (
      CG_LTRCR_FNDGRP_CD ))
ADD (CONSTRAINT CG_AWD_TR5 FOREIGN KEY (
      CG_GRANT_DESC_CD )
REFERENCES CG_GRANT_DESC_T (
      CG_GRANT_DESC_CD ))
ADD (CONSTRAINT CG_AWD_TR6 FOREIGN KEY (
      CG_AGENCY_NBR )
REFERENCES CG_AGENCY_T (
      CG_AGENCY_NBR ))
ADD (CONSTRAINT CG_AWD_TR7 FOREIGN KEY (
      CG_FEDPT_AGNCY_NBR )
REFERENCES CG_AGENCY_T (
      CG_AGENCY_NBR ))
ADD (CONSTRAINT CG_AWD_TR8 FOREIGN KEY (
      CGAWD_PURPOSE_CD )
REFERENCES CG_PRPSL_PURPOSE_T (
      CGPRPSL_PURPOSE_CD ))
/