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
CREATE TABLE GL_COR_DOC_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT GL_COR_DOC_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT GL_COR_DOC_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT GL_COR_DOC_TN3 NOT NULL,
        GL_COR_TYP_CD                  VARCHAR2(1),
        GL_COR_SELECT_IND              CHAR(1),
        GL_COR_FL_DEL_IND              CHAR(1),
        GL_COR_ROW_CNT                 NUMBER(7),
        GL_COR_CHG_GRP_NXT_LN_NBR      NUMBER(3),
        GL_COR_DEBIT_TOT_AMT           NUMBER(19, 2),
        GL_COR_CRDT_TOT_AMT            NUMBER(19, 2),
        GL_COR_INP_FL_NM               VARCHAR2(255),
        GL_COR_OUT_FL_NM               VARCHAR2(255),
        GL_COR_SCR_TXT                 CLOB,
        GL_COR_INP_GRP_ID              NUMBER(14),
        GL_COR_OUT_GRP_ID              NUMBER(14),
        GL_COR_BDGT_TOT_AMT            NUMBER(19, 2),
     CONSTRAINT GL_COR_DOC_TP1 PRIMARY KEY (
        FDOC_NBR),
     CONSTRAINT GL_COR_DOC_TC0 UNIQUE (OBJ_ID)
)
/
