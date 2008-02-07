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
CREATE TABLE ER_INST_CST_SHR_PSNL_T(
        RDOC_NBR                       VARCHAR2(14) CONSTRAINT ER_INST_CST_SHR_PSNL_TN1 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT ER_INST_CST_SHR_PSNL_TN2 NOT NULL,
        ORG_CD                         VARCHAR2(4) CONSTRAINT ER_INST_CST_SHR_PSNL_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT ER_INST_CST_SHR_PSNL_TN4 NOT NULL,
        VER_NBR                        NUMBER(8,0) DEFAULT 1 CONSTRAINT ER_INST_CST_SHR_PSNL_TN5 NOT NULL,
        BDGT_INST_CST_SHR_PSNL_DESC      VARCHAR2(250),
     CONSTRAINT ER_INST_CST_SHR_PSNL_TP1 PRIMARY KEY (
        RDOC_NBR,
        FIN_COA_CD,
        ORG_CD),
     CONSTRAINT ER_INST_CST_SHR_PSNL_TC0 UNIQUE (OBJ_ID)
)
/

