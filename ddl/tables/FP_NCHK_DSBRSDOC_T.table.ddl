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
CREATE TABLE FP_NCHK_DSBRSDOC_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT FP_NCHK_DSBRSDOC_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_NCHK_DSBRSDOC_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_NCHK_DSBRSDOC_TN3 NOT NULL,
        FDOC_NXT_LINE_NBR              NUMBER(7),
        FDOC_POST_YR                   NUMBER(4),
        FDOC_POST_PRD_CD               VARCHAR2(2),
     CONSTRAINT FP_NCHK_DSBRSDOC_TP1 PRIMARY KEY (
        FDOC_NBR),
     CONSTRAINT FP_NCHK_DSBRSDOC_TC0 UNIQUE (OBJ_ID)
)
/
