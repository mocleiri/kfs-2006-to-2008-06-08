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
CREATE TABLE CG_AWD_ORG_T(
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT CG_AWD_ORG_TN1 NOT NULL,
        ORG_CD                         VARCHAR2(4) CONSTRAINT CG_AWD_ORG_TN2 NOT NULL,
        CGPRPSL_NBR                    NUMBER(12) CONSTRAINT CG_AWD_ORG_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CG_AWD_ORG_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CG_AWD_ORG_TN5 NOT NULL,
        CGAWD_PRM_ORG_IND              VARCHAR2(1),
        ROW_ACTV_IND                   VARCHAR2(1) CONSTRAINT CG_AWD_ORG_TN6 NOT NULL,
     CONSTRAINT CG_AWD_ORG_TP1 PRIMARY KEY (
        FIN_COA_CD,
        ORG_CD,
        CGPRPSL_NBR),
     CONSTRAINT CG_AWD_ORG_TC0 UNIQUE (OBJ_ID)
)
/