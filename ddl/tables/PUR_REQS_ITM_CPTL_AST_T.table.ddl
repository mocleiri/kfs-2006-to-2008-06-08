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
CREATE TABLE PUR_REQS_ITM_CPTL_AST_T(
        REQS_ITM_CPTL_AST_ID           NUMBER(10) CONSTRAINT PUR_REQS_ITM_CPTL_AST_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_REQS_ITM_CPTL_AST_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_REQS_ITM_CPTL_AST_TN3 NOT NULL,
        REQS_ITM_ID                    NUMBER(10),
        CPTLAST_NBR                    NUMBER(12) CONSTRAINT PUR_REQS_ITM_CPTL_AST_TN4 NOT NULL,
     CONSTRAINT PUR_REQS_ITM_CPTL_AST_TP1 PRIMARY KEY (
        REQS_ITM_CPTL_AST_ID),
     CONSTRAINT PUR_REQS_ITM_CPTL_AST_TC0 UNIQUE (OBJ_ID)
)
/
