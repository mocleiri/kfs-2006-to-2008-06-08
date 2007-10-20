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
CREATE TABLE GL_COR_CHG_GRP_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT GL_COR_CHG_GRP_TN1 NOT NULL,
        GL_COR_CHG_GRP_LN_NBR          NUMBER(3) CONSTRAINT GL_COR_CHG_GRP_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT GL_COR_CHG_GRP_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT GL_COR_CHG_GRP_TN4 NOT NULL,
        GL_COR_CRTA_NXT_LN_NBR         NUMBER(3),
        GL_COR_CHG_NXT_LN_NBR          NUMBER(3),
     CONSTRAINT GL_COR_CHG_GRP_TP1 PRIMARY KEY (
        FDOC_NBR,
        GL_COR_CHG_GRP_LN_NBR),
     CONSTRAINT GL_COR_CHG_GRP_TC0 UNIQUE (OBJ_ID)
)
/
