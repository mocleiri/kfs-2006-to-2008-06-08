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
CREATE INDEX CG_PRPSL_TI2 ON CG_PRPSL_T(
        CG_AGENCY_NBR )
/
CREATE INDEX CG_PRPSL_TI3 ON CG_PRPSL_T(
        CGPRPSL_AWD_TYP_CD )
/
CREATE INDEX CG_PRPSL_TI4 ON CG_PRPSL_T(
        CGPRPSL_PURPOSE_CD )
/
CREATE INDEX CG_PRPSL_TI5 ON CG_PRPSL_T(
        CGPRPSL_STAT_CD )
/
CREATE INDEX CG_PRPSL_TI6 ON CG_PRPSL_T(
        CG_FEDPT_AGNCY_NBR )
/
