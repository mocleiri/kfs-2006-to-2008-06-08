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
CREATE TABLE CA_OBJ_CONSOLDTN_T(
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT CA_OBJ_CONSOLDTN_TN1 NOT NULL,
        FIN_CONS_OBJ_CD                VARCHAR2(4) CONSTRAINT CA_OBJ_CONSOLDTN_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CA_OBJ_CONSOLDTN_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CA_OBJ_CONSOLDTN_TN4 NOT NULL,
        FIN_CONS_OBJ_NM                VARCHAR2(40),
        FIN_CONOBJ_SHRT_NM             VARCHAR2(12),
        FIN_CONOBJ_ACTV_CD             VARCHAR2(1),
        FIN_ELIM_OBJ_CD                VARCHAR2(4),
        FIN_REPORT_SORT_CD             VARCHAR2(2),
     CONSTRAINT CA_OBJ_CONSOLDTN_TP1 PRIMARY KEY (
        FIN_COA_CD,
        FIN_CONS_OBJ_CD),
     CONSTRAINT CA_OBJ_CONSOLDTN_TC0 UNIQUE (OBJ_ID)
)
/