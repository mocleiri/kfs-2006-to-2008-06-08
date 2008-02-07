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
CREATE TABLE FP_FSCL_YR_CTRL_T(
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT FP_FSCL_YR_CTRL_TN1 NOT NULL,
        FS_FUNC_CTRL_CD                VARCHAR2(6) CONSTRAINT FP_FSCL_YR_CTRL_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_FSCL_YR_CTRL_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_FSCL_YR_CTRL_TN4 NOT NULL,
        FS_FUNC_ACTIVE_IND             VARCHAR2(1),
     CONSTRAINT FP_FSCL_YR_CTRL_TP1 PRIMARY KEY (
        UNIV_FISCAL_YR,
        FS_FUNC_CTRL_CD),
     CONSTRAINT FP_FSCL_YR_CTRL_TC0 UNIQUE (OBJ_ID)
)
/
