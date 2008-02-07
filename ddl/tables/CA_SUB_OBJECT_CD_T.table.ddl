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
CREATE TABLE CA_SUB_OBJECT_CD_T(
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT CA_SUB_OBJECT_CD_TN1 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT CA_SUB_OBJECT_CD_TN2 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT CA_SUB_OBJECT_CD_TN3 NOT NULL,
        FIN_OBJECT_CD                  VARCHAR2(4) CONSTRAINT CA_SUB_OBJECT_CD_TN4 NOT NULL,
        FIN_SUB_OBJ_CD                 VARCHAR2(3) CONSTRAINT CA_SUB_OBJECT_CD_TN5 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CA_SUB_OBJECT_CD_TN6 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CA_SUB_OBJECT_CD_TN7 NOT NULL,
        FIN_SUB_OBJ_CD_NM              VARCHAR2(40),
        FIN_SUBOBJ_SHRT_NM             VARCHAR2(12),
        FIN_SUBOBJ_ACTV_CD             VARCHAR2(1),
     CONSTRAINT CA_SUB_OBJECT_CD_TP1 PRIMARY KEY (
        UNIV_FISCAL_YR,
        FIN_COA_CD,
        ACCOUNT_NBR,
        FIN_OBJECT_CD,
        FIN_SUB_OBJ_CD),
     CONSTRAINT CA_SUB_OBJECT_CD_TC0 UNIQUE (OBJ_ID)
)
/
