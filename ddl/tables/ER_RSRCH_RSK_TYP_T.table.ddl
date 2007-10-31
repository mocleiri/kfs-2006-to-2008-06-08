/*
 * Copyright 2006-2007 The Kuali Foundation.
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
CREATE TABLE ER_RSRCH_RSK_TYP_T(
        RSRCH_RSK_TYP_CD      	       VARCHAR2(2) CONSTRAINT ER_RSRCH_RSK_TYP_TN1 NOT NULL,
        VER_NBR                        NUMBER(8,0) DEFAULT 1 CONSTRAINT ER_RSRCH_RSK_TYP_TN3 NOT NULL,
        DOBJ_MAINT_CD_ACTV_IND         VARCHAR2(1) CONSTRAINT ER_RSRCH_RSK_TYP_TN4 NOT NULL,
        CTRL_ATTRIB_TYP_CD   	       VARCHAR2(1) CONSTRAINT ER_RSRCH_RSK_TYP_TN5 NOT NULL,
        RSRCH_RSK_TYP_DESC    	       VARCHAR2(30) NULL,
        RSRCH_RSK_TYP_SORT_NBR         NUMBER(2,0),
        RSRCH_RSK_TYP_NTFCTN_GRP_TXT   VARCHAR2(50),
        RSRCH_RSK_TYP_NTFCTN_VAL       VARCHAR2(8),
     CONSTRAINT ER_RSRCH_RSK_TYP_TP1 PRIMARY KEY (
        RSRCH_RSK_TYP_CD)
)
/
