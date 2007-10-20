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
CREATE INDEX AP_ELCTRNC_INV_RJT_REAS_TI1 ON AP_ELCTRNC_INV_RJT_REAS_T(
        INV_RJT_REAS_TYP_CD)
/
CREATE INDEX AP_ELCTRNC_INV_RJT_REAS_TI2 ON AP_ELCTRNC_INV_RJT_REAS_T(
        INV_HDR_INFO_ID)
/
