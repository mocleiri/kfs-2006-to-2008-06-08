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
CREATE TABLE ER_BDGT_MOD_T(
        RDOC_NBR                       VARCHAR2(14) CONSTRAINT ER_BDGT_MOD_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT ER_BDGT_MOD_TN2 NOT NULL,
        VER_NBR                        NUMBER(8,0) DEFAULT 1,
        BDGT_MOD_INCRM_AMT             NUMBER(6,0),
        BDGT_MOD_TSK_NBR               NUMBER(2,0),
        BDGT_PRD_MAX_AMT               NUMBER(7,0),
        BDGT_MOD_CSRM_DESC             CLOB,
        BDGT_MOD_PSNL_DESC             CLOB,
        BDGT_MOD_VAR_ADJ_DESC          CLOB,
     CONSTRAINT ER_BDGT_MOD_TP1 PRIMARY KEY (
        RDOC_NBR),
     CONSTRAINT ER_BDGT_MOD_TC0 UNIQUE (OBJ_ID)
)
/

