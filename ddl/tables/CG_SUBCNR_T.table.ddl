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
CREATE TABLE CG_SUBCNR_T(
        CG_SUBCNR_NBR                  VARCHAR2(5) CONSTRAINT CG_SUBCNR_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CG_SUBCNR_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CG_SUBCNR_TN3 NOT NULL,
        CG_SUBCNR_NM                   VARCHAR2(50),
        CG_SUBCNR_LN1_ADDR             VARCHAR2(25),
        CG_SUBCNR_LN2_ADDR             VARCHAR2(20),
        CG_SUBCNR_CITY_NM              VARCHAR2(20),
        CG_SUBCNR_ST_CD                VARCHAR2(2),
        CG_SUBCNR_ZIP_CD               VARCHAR2(10),
        CGSUBCNR_CNTRY_CD              VARCHAR2(2),
        ROW_ACTV_IND                   VARCHAR2(1) CONSTRAINT CG_SUBCNR_TN4 NOT NULL,
     CONSTRAINT CG_SUBCNR_TP1 PRIMARY KEY (
        CG_SUBCNR_NBR),
     CONSTRAINT CG_SUBCNR_TC0 UNIQUE (OBJ_ID)
)
/
