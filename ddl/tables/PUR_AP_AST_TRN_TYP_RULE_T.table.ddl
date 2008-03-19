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
CREATE TABLE PUR_AP_AST_TRN_TYP_RULE_T(
        CPTL_AST_TRN_TYP_CD            VARCHAR2(4) CONSTRAINT PUR_AP_AST_TRN_TYP_RULE_TN1 NOT NULL,
        FIN_OBJ_SUB_TYP_CD             VARCHAR2(2) CONSTRAINT PUR_AP_AST_TRN_TYP_RULE_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_AP_AST_TRN_TYP_RULE_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_AP_AST_TRN_TYP_RULE_TN4 NOT NULL,
        CPTL_AST_RELSHP_LMT_NBR        NUMBER(3),
     CONSTRAINT PUR_AP_AST_TRN_TYP_RULE_TP1 PRIMARY KEY (
        CPTL_AST_TRN_TYP_CD,
        FIN_OBJ_SUB_TYP_CD),
     CONSTRAINT PUR_AP_AST_TRN_TYP_RULE_TC0 UNIQUE (OBJ_ID)
)
/