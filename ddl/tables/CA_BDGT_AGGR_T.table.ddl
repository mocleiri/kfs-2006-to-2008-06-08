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
CREATE TABLE CA_BDGT_AGGR_T(
        FOBJ_BDGT_AGGR_CD              VARCHAR(1) CONSTRAINT CA_BDGT_AGGR_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT CA_BDGT_AGGR_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CA_BDGT_AGGR_TN3 NOT NULL,
        DOBJ_MAINT_CD_ACTV_IND         VARCHAR(1) CONSTRAINT CA_BDGT_AGGR_TN4 NOT NULL,
        FIN_BDGT_AGGR_NM               VARCHAR(40),
     CONSTRAINT CA_BDGT_AGGR_TP1 PRIMARY KEY (
        FOBJ_BDGT_AGGR_CD),
     CONSTRAINT CA_BDGT_AGGR_TC0 UNIQUE (OBJ_ID)
)
/