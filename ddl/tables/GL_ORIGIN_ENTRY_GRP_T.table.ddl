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
CREATE TABLE GL_ORIGIN_ENTRY_GRP_T(
        ORIGIN_ENTRY_GRP_ID            NUMBER(14) CONSTRAINT GL_ORIGIN_ENTRY_GRP_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT GL_ORIGIN_ENTRY_GRP_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT GL_ORIGIN_ENTRY_GRP_TN3 NOT NULL,
        ORIGIN_ENTRY_GRP_DT            DATE CONSTRAINT GL_ORIGIN_ENTRY_GRP_TN4 NOT NULL,
        ORIGIN_ENTRY_GRP_SRC_CD        VARCHAR2(4) CONSTRAINT GL_ORIGIN_ENTRY_GRP_TN5 NOT NULL,
        ORIGIN_ENTRY_GRP_VLD_IND       VARCHAR2(1) CONSTRAINT GL_ORIGIN_ENTRY_GRP_TN6 NOT NULL,
        ORIGIN_ENTRY_PRCS_IND          VARCHAR2(1) CONSTRAINT GL_ORIGIN_ENTRY_GRP_TN7 NOT NULL,
        ORIGIN_ENTRY_SCRUB_IND         VARCHAR2(1) CONSTRAINT GL_ORIGIN_ENTRY_GRP_TN8 NOT NULL,
     CONSTRAINT GL_ORIGIN_ENTRY_GRP_TP1 PRIMARY KEY (
        ORIGIN_ENTRY_GRP_ID),
     CONSTRAINT GL_ORIGIN_ENTRY_GRP_TC0 UNIQUE (OBJ_ID)
)
/





 









