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
CREATE TABLE FP_RPT_CD_T(
        FIN_RPT_CHRT_CD                VARCHAR2(2) CONSTRAINT FP_RPT_CD_TN1 NOT NULL,
        FIN_RPT_ORG_CD                 VARCHAR2(4) CONSTRAINT FP_RPT_CD_TN2 NOT NULL,
        FIN_RPT_CD                     VARCHAR2(10) CONSTRAINT FP_RPT_CD_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT FP_RPT_CD_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_RPT_CD_TN5 NOT NULL,
        FIN_RPT_CD_DESC                VARCHAR2(40),
        FIN_REP_CD_MGR_ID              VARCHAR2(10),
        FIN_RPTS_TO_RPT_CD             VARCHAR2(10),
     CONSTRAINT FP_RPT_CD_TP1 PRIMARY KEY (
        FIN_RPT_CHRT_CD,
        FIN_RPT_ORG_CD,
        FIN_RPT_CD),
     CONSTRAINT FP_RPT_CD_TC0 UNIQUE (OBJ_ID)
)
/
