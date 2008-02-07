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
CREATE TABLE FP_DV_TRVL_CO_NM_T(
        DV_EXP_CD                      VARCHAR2(2) CONSTRAINT FP_DV_TRVL_CO_NM_TN1 NOT NULL,
        DV_EXP_CO_NM                   VARCHAR2(40) CONSTRAINT FP_DV_TRVL_CO_NM_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_DV_TRVL_CO_NM_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_DV_TRVL_CO_NM_TN4 NOT NULL,
     CONSTRAINT FP_DV_TRVL_CO_NM_TP1 PRIMARY KEY (
        DV_EXP_CD,
        DV_EXP_CO_NM),
     CONSTRAINT FP_DV_TRVL_CO_NM_TC0 UNIQUE (OBJ_ID)
)
/
