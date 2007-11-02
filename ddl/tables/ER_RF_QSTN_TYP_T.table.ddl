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
CREATE TABLE ER_RF_QSTN_TYP_T(
        RDOC_NBR                       VARCHAR2(14) CONSTRAINT ER_RF_QSTN_TYP_TN1 NOT NULL,
        QSTN_TYP_CD                    VARCHAR2(2) CONSTRAINT ER_RF_QSTN_TYP_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT ER_RF_QSTN_TYP_TN3 NOT NULL,
        VER_NBR                        NUMBER(8,0) DEFAULT 1 CONSTRAINT ER_RF_QSTN_TYP_TN4 NOT NULL,
        YES_NO_IND                     VARCHAR2(1),
     CONSTRAINT ER_RF_QSTN_TYP_TP1 PRIMARY KEY (
        RDOC_NBR, QSTN_TYP_CD),
     CONSTRAINT ER_RF_QSTN_TYP_TC0 UNIQUE (OBJ_ID)
)
/
