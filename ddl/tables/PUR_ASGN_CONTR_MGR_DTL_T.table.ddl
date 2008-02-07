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
CREATE TABLE PUR_ASGN_CONTR_MGR_DTL_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT PUR_ASGN_CONTR_MGR_DTL_TN1 NOT NULL,
        REQS_ID                        NUMBER(8) CONSTRAINT PUR_ASGN_CONTR_MGR_DTL_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_ASGN_CONTR_MGR_DTL_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_ASGN_CONTR_MGR_DTL_TN4 NOT NULL,
        CONTR_MGR_CD                   NUMBER(2),
     CONSTRAINT PUR_ASGN_CONTR_MGR_DTL_TP1 PRIMARY KEY (
        FDOC_NBR,
        REQS_ID),
     CONSTRAINT PUR_ASGN_CONTR_MGR_DTL_TC0 UNIQUE (OBJ_ID)
)
/
