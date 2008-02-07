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
CREATE TABLE GL_OFFSET_DEFN_T(
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT GL_OFFSET_DEFN_TN1 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT GL_OFFSET_DEFN_TN2 NOT NULL,
        FDOC_TYP_CD                    VARCHAR2(4) CONSTRAINT GL_OFFSET_DEFN_TN3 NOT NULL,
        FIN_BALANCE_TYP_CD             VARCHAR2(2) CONSTRAINT GL_OFFSET_DEFN_TN4 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT GL_OFFSET_DEFN_TN5 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT GL_OFFSET_DEFN_TN6 NOT NULL,
        FIN_OBJECT_CD                  VARCHAR2(4),
     CONSTRAINT GL_OFFSET_DEFN_TP1 PRIMARY KEY (
        UNIV_FISCAL_YR,
        FIN_COA_CD,
        FDOC_TYP_CD,
        FIN_BALANCE_TYP_CD),
     CONSTRAINT GL_OFFSET_DEFN_TC0 UNIQUE (OBJ_ID)
)
/
