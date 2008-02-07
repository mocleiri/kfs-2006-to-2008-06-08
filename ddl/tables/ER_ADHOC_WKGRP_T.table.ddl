/*
 * Copyright 2007 The Kuali Foundation.
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
CREATE TABLE ER_ADHOC_WKGRP_T(
	RDOC_NBR                        VARCHAR2(14) CONSTRAINT ER_ADHOC_WKGRP_TN1 NOT NULL,
	WKGRP_ID                 		VARCHAR2(70) CONSTRAINT ER_ADHOC_WKGRP_TN2 NOT NULL,
	OBJ_ID                         	VARCHAR2(36) CONSTRAINT ER_ADHOC_WKGRP_TN3 NOT NULL,
	VER_NBR                        	NUMBER(8,0) DEFAULT 1,
	WKGRP_PRMSN_CD                  VARCHAR2(2),
	PRSN_ADD_BY_UNVL_ID             VARCHAR2(10),
	PRSN_ADD_TS                     DATE,
    ACTN_RQST_CD                    VARCHAR2(30),
    ADHOC_TYP_CD                    VARCHAR2(1),
     CONSTRAINT ER_ADHOC_WKGRP_TP1 PRIMARY KEY (
        RDOC_NBR,
        WKGRP_ID),
     CONSTRAINT ER_ADHOC_WKGRP_TC0 UNIQUE (OBJ_ID)
)
/
