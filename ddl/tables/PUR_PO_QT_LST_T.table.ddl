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
CREATE TABLE PUR_PO_QT_LST_T(
        PO_QT_LST_ID                   NUMBER(9) CONSTRAINT PUR_PO_QT_LST_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT PUR_PO_QT_LST_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_PO_QT_LST_TN3 NOT NULL,
        PO_QT_LST_NM                   VARCHAR2(100) CONSTRAINT PUR_PO_QT_LST_TN4 NOT NULL,
        CONTR_MGR_CD                   NUMBER(2) CONSTRAINT PUR_PO_QT_LST_TN5 NOT NULL,
     CONSTRAINT PUR_PO_QT_LST_TP1 PRIMARY KEY (
        PO_QT_LST_ID),
     CONSTRAINT PUR_PO_QT_LST_TC0 UNIQUE (OBJ_ID)
)
/