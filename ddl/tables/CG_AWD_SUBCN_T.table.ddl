/*
 * Copyright 2005-2007 The Kuali Foundation.
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
CREATE TABLE CG_AWD_SUBCN_T(
        AWD_SUBCN_AMND_NBR             VARCHAR2(2) CONSTRAINT CG_AWD_SUBCN_TN1 NOT NULL,
        CGAWD_SUBCN_NBR                VARCHAR2(2) CONSTRAINT CG_AWD_SUBCN_TN2 NOT NULL,
        CG_SUBCNR_NBR                  VARCHAR2(5) CONSTRAINT CG_AWD_SUBCN_TN3 NOT NULL,
        CGPRPSL_NBR                    NUMBER(12) CONSTRAINT CG_AWD_SUBCN_TN4 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CG_AWD_SUBCN_TN5 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CG_AWD_SUBCN_TN6 NOT NULL,
        CG_SUBCN_AMT                   NUMBER(19, 2),
        SUBCNR_CNTCT_F_NM              VARCHAR2(15),
        SUBCNR_CNTCT_L_NM              VARCHAR2(25),
        SUBCN_AUDTHIST_TXT             VARCHAR2(2000),
        CGAWD_SUBCN_DESC               VARCHAR2(30),
        ROW_ACTV_IND                   VARCHAR2(1) CONSTRAINT CG_AWD_SUBCN_TN7 NOT NULL,
     CONSTRAINT CG_AWD_SUBCN_TP1 PRIMARY KEY (
        AWD_SUBCN_AMND_NBR,
        CGAWD_SUBCN_NBR,
        CG_SUBCNR_NBR,
        CGPRPSL_NBR),
     CONSTRAINT CG_AWD_SUBCN_TC0 UNIQUE (OBJ_ID)
)
/
