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
CREATE TABLE PUR_CONTR_MGR_T(
        CONTR_MGR_CD                   NUMBER(2) CONSTRAINT PUR_CONTR_MGR_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_CONTR_MGR_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_CONTR_MGR_TN3 NOT NULL,
        CONTR_MGR_USR_ID               VARCHAR2(11) CONSTRAINT PUR_CONTR_MGR_TN4 NOT NULL,
        CONTR_MGR_NM                   VARCHAR2(45) CONSTRAINT PUR_CONTR_MGR_TN5 NOT NULL,
        CONTR_MGR_PHN_NBR              VARCHAR2(45) CONSTRAINT PUR_CONTR_MGR_TN6 NOT NULL,
        CONTR_MGR_FAX_NBR              VARCHAR2(45) CONSTRAINT PUR_CONTR_MGR_TN7 NOT NULL,
        CONTR_MGR_LONG_DIST_ACC_CD     VARCHAR2(10),
        CONTR_MGR_DLGN_DLR_LMT         NUMBER(19,2),
        CONTR_MGR_SIG_IMG_LOC_DESC     VARCHAR2(45),
     CONSTRAINT PUR_CONTR_MGR_TP1 PRIMARY KEY (
        CONTR_MGR_CD),
     CONSTRAINT PUR_CONTR_MGR_TC0 UNIQUE (OBJ_ID)
)
/
