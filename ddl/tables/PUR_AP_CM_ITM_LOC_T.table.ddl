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
CREATE TABLE PUR_AP_CM_ITM_LOC_T(
        CM_ITM_LOC_ID                  NUMBER(10) CONSTRAINT PUR_AP_CM_ITM_LOC_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_AP_CM_ITM_LOC_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_AP_CM_ITM_LOC_TN3 NOT NULL,
        CM_ITM_ID                      NUMBER(10),
        CM_ITM_QTY                     NUMBER(11,2),
        CMP_CD                         VARCHAR2(2),
        CM_OFF_CMP_IND                 VARCHAR2(1),
        BLDG_CD                        VARCHAR2(10),
        BLDG_NM                        VARCHAR2(40),
        BLDG_ROOM_NBR                  VARCHAR2(8),
        CM_ITM_ADDR                    VARCHAR2(45),
        CM_ITM_CTY_NM                  VARCHAR2(45),
        CM_ITM_ST_CD                   VARCHAR2(2),
        CM_ITM_PSTL_CD                 VARCHAR2(20),
        CM_ITM_CNTRY_CD                VARCHAR2(2),
     CONSTRAINT PUR_AP_CM_ITM_LOC_TP1 PRIMARY KEY (
        CM_ITM_LOC_ID),
     CONSTRAINT PUR_AP_CM_ITM_LOC_TC0 UNIQUE (OBJ_ID)
)
/
