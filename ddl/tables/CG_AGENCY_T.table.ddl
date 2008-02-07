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
CREATE TABLE CG_AGENCY_T(
        CG_AGENCY_NBR                  VARCHAR2(5) CONSTRAINT CG_AGENCY_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CG_AGENCY_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CG_AGENCY_TN3 NOT NULL,
        CG_AGENCY_RPT_NM               VARCHAR2(30),
        CG_AGENCY_FULL_NM              VARCHAR2(50),
        CG_AGNCY_INDR_AMT              NUMBER(19, 2),
        CG_AGENCY_HIST_IND             VARCHAR2(1),
        CG_AGNCY_IN_ST_IND             VARCHAR2(1),
        CG_AGENCY_TYP_CD               VARCHAR2(1),
        CG_RPTTO_AGNCY_NBR             VARCHAR2(5),
     CONSTRAINT CG_AGENCY_TP1 PRIMARY KEY (
        CG_AGENCY_NBR),
     CONSTRAINT CG_AGENCY_TC0 UNIQUE (OBJ_ID)
)
/
