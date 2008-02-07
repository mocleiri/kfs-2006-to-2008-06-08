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
CREATE TABLE PUR_PO_CONTR_LANG_T(
        PO_CONTR_LANG_ID               NUMBER(10) CONSTRAINT PUR_PO_CONTR_LANG_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_PO_CONTR_LANG_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_PO_CONTR_LANG_TN3 NOT NULL,
        CMP_CD                         VARCHAR2(2) CONSTRAINT PUR_PO_CONTR_LANG_TN4 NOT NULL,
        PO_CONTR_LANG_DESC             VARCHAR2(2000) CONSTRAINT PUR_PO_CONTR_LANG_TN5 NOT NULL,
        CONTR_LANG_CRTE_DT             DATE CONSTRAINT PUR_PO_CONTR_LANG_TN6 NOT NULL,
        DOBJ_MAINT_CD_ACTV_IND         VARCHAR2(1) CONSTRAINT PUR_PO_CONTR_LANG_TN7 NOT NULL,
     CONSTRAINT PUR_PO_CONTR_LANG_TP1 PRIMARY KEY (
        PO_CONTR_LANG_ID),
     CONSTRAINT PUR_PO_CONTR_LANG_TC0 UNIQUE (OBJ_ID)
)
/
