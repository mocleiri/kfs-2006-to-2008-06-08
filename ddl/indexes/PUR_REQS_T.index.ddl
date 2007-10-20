/*
 * Copyright 2006-2007 The Kuali Foundation.
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
CREATE INDEX PUR_REQS_TI1 ON PUR_REQS_T(
        REQS_SRC_CD)
/
CREATE INDEX PUR_REQS_TI2 ON PUR_REQS_T(
        FND_SRC_CD)
/
CREATE INDEX PUR_REQS_TI3 ON PUR_REQS_T(
        REQS_STAT_CD)
/
CREATE INDEX PUR_REQS_TI4 ON PUR_REQS_T(
        PO_TRNS_MTHD_CD)
/
CREATE INDEX PUR_REQS_TI5 ON PUR_REQS_T(
        RECUR_PMT_TYP_CD)
/
CREATE INDEX PUR_REQS_TI6 ON PUR_REQS_T(
        DLVY_REQ_DT_REAS_CD)
/
CREATE INDEX PUR_REQS_TI7 ON PUR_REQS_T(
        FDOC_NBR)
/
CREATE INDEX PUR_REQS_TI8 ON PUR_REQS_T(
        PO_CST_SRC_CD)
/
