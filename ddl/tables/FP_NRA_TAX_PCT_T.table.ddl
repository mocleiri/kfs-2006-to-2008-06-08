/*
 * Copyright 2005-2007 The Kuali Foundation.
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
CREATE TABLE FP_NRA_TAX_PCT_T(
        INC_CLS_CD                     VARCHAR2(2) CONSTRAINT FP_NRA_TAX_PCT_TN1 NOT NULL,
        INC_TAX_TYP_CD                 VARCHAR2(1) CONSTRAINT FP_NRA_TAX_PCT_TN2 NOT NULL,
        INC_TAX_PCT                    NUMBER(5,2),
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_NRA_TAX_PCT_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_NRA_TAX_PCT_TN4 NOT NULL,
     CONSTRAINT FP_NRA_TAX_PCT_TP1 PRIMARY KEY (
        INC_CLS_CD,
        INC_TAX_TYP_CD,
        INC_TAX_PCT),
     CONSTRAINT FP_NRA_TAX_PCT_TC0 UNIQUE (OBJ_ID)
)
/
