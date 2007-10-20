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
CREATE TABLE FP_DV_PRECNF_REG_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT FP_DV_PRECNF_REG_TN1 NOT NULL,
        FDOC_LINE_NBR                  NUMBER(7) CONSTRAINT FP_DV_PRECNF_REG_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT FP_DV_PRECNF_REG_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_DV_PRECNF_REG_TN4 NOT NULL,
        DV_PRECONF_DEPT_CD             VARCHAR2(4),
        DV_CONF_RGSTRNT_NM             VARCHAR2(40),
        DV_PRECONF_RQST_NBR            VARCHAR2(6),
        DV_EXP_AMT                     NUMBER(19, 2),
     CONSTRAINT FP_DV_PRECNF_REG_TP1 PRIMARY KEY (
        FDOC_NBR,
        FDOC_LINE_NBR),
     CONSTRAINT FP_DV_PRECNF_REG_TC0 UNIQUE (OBJ_ID)
)
/
