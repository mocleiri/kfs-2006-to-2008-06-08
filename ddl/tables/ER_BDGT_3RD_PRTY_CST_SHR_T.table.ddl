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
CREATE TABLE ER_BDGT_3RD_PRTY_CST_SHR_T(
        RDOC_NBR                       VARCHAR2(14) CONSTRAINT ER_BDGT_3RD_PRTY_CST_SHR_TN1 NOT NULL,
        BDGT_3RD_PRTY_CST_SHR_SEQ_NBR  NUMBER(2,0) CONSTRAINT ER_BDGT_3RD_PRTY_CST_SHR_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT ER_BDGT_3RD_PRTY_CST_SHR_TN3 NOT NULL,
        VER_NBR                        NUMBER(8,0) DEFAULT 1 CONSTRAINT ER_BDGT_3RD_PRTY_CST_SHR_TN4 NOT NULL,
        BDGT_3RD_PRTY_CST_SHR_DESC     VARCHAR2(250),
        BDGT_3RD_PRTY_CST_SHR_SRC_NM   VARCHAR2(25),
     CONSTRAINT ER_BDGT_3RD_PRTY_CST_SHR_TP1 PRIMARY KEY (
        RDOC_NBR,
        BDGT_3RD_PRTY_CST_SHR_SEQ_NBR),
     CONSTRAINT ER_BDGT_3RD_PRTY_CST_SHR_TC0 UNIQUE (OBJ_ID)
)
/

