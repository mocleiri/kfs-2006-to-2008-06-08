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
CREATE INDEX PUR_REQS_ITM_TI1 ON PUR_REQS_ITM_T(
        ITM_TYP_CD)
/
CREATE INDEX PUR_REQS_ITM_TI2 ON PUR_REQS_ITM_T(
        CPTL_AST_TRN_TYP_CD)
/
CREATE INDEX PUR_REQS_ITM_TI3 ON PUR_REQS_ITM_T(
        REQS_ID)
/