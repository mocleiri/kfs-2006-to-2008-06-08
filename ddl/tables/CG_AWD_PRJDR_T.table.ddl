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
CREATE TABLE CG_AWD_PRJDR_T(
        PERSON_UNVL_ID                 VARCHAR2(10) CONSTRAINT CG_AWD_PRJDR_TN1 NOT NULL,
        CGPRPSL_NBR                    NUMBER(12) CONSTRAINT CG_AWD_PRJDR_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CG_AWD_PRJDR_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CG_AWD_PRJDR_TN4 NOT NULL,
        CGAWD_PRMPRJDR_IND             VARCHAR2(1),
        CGAWD_PRJDRPRJ_TTL             VARCHAR2(100),
        ROW_ACTV_IND                   VARCHAR2(1) CONSTRAINT CG_AWD_PRJDR_TN5 NOT NULL,
     CONSTRAINT CG_AWD_PRJDR_TP1 PRIMARY KEY (
        PERSON_UNVL_ID,
        CGPRPSL_NBR),
     CONSTRAINT CG_AWD_PRJDR_TC0 UNIQUE (OBJ_ID)
)
/
