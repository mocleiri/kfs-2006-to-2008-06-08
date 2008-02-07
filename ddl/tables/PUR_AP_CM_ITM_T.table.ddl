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
CREATE TABLE PUR_AP_CM_ITM_T(
        CM_ITM_ID                      NUMBER(10) CONSTRAINT PUR_AP_CM_ITM_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_AP_CM_ITM_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_AP_CM_ITM_TN3 NOT NULL,
        CM_DOC_ID                      NUMBER(10) CONSTRAINT PUR_AP_CM_ITM_TN4 NOT NULL,
        ITM_LN_NBR                     NUMBER(3),
        PO_ID                          NUMBER(9),
        CM_ITM_NOTRCVD_CUR_FSCL_YR_IND VARCHAR2(1),
        CPTLAST_TYP_CD                 VARCHAR2(7),
        CPTLAST_TYP_DESC               VARCHAR2(70),
        CM_ITM_MFR_IS_VNDR_IND         VARCHAR2(1),
        CM_ITM_MFR_NM                  VARCHAR2(45),
        CM_ITM_MDL_DESC                VARCHAR2(45),
     CONSTRAINT PUR_AP_CM_ITM_TP1 PRIMARY KEY (
        CM_ITM_ID),
     CONSTRAINT PUR_AP_CM_ITM_TC0 UNIQUE (OBJ_ID)
)
/
