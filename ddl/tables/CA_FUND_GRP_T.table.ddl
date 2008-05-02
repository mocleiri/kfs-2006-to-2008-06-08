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
CREATE TABLE CA_FUND_GRP_T(
        FUND_GRP_CD                    VARCHAR2(2) CONSTRAINT CA_FUND_GRP_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CA_FUND_GRP_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CA_FUND_GRP_TN3 NOT NULL,
        DOBJ_MAINT_CD_ACTV_IND         VARCHAR2(1) CONSTRAINT CA_FUND_GRP_TN4 NOT NULL ENABLE, 
        FUND_GRP_NM                    VARCHAR2(40),
        FIN_REPORT_SORT_CD             VARCHAR2(2),
        FND_GRP_BA_RSTR_LVL_CD         VARCHAR2(1) DEFAULT 'F' CONSTRAINT CA_FUND_GRP_TN5 NOT NULL,
     CONSTRAINT CA_FUND_GRP_TP1 PRIMARY KEY (
        FUND_GRP_CD),
     CONSTRAINT CA_FUND_GRP_TC0 UNIQUE (OBJ_ID)
)
/
	 
