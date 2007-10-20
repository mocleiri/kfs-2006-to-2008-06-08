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
CREATE TABLE CA_MNXFR_ELIM_T(
        FOBJ_MNXFR_ELIM_CD             VARCHAR2(1) CONSTRAINT CA_MNXFR_ELIM_TN1 NOT NULL, 
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT CA_MNXFR_ELIM_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CA_MNXFR_ELIM_TN3 NOT NULL,
        DOBJ_MAINT_CD_ACTV_IND         VARCHAR2(1) CONSTRAINT CA_MNXFR_ELIM_TN4 NOT NULL,
        FOBJ_MNXFR_ELIM_NM             VARCHAR2(40),
     CONSTRAINT CA_MNXFR_ELIM_TP1 PRIMARY KEY (
        FOBJ_MNXFR_ELIM_CD),
     CONSTRAINT CA_MNXFR_ELIM_TC0 UNIQUE (OBJ_ID)
)
/
