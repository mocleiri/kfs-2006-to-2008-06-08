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
CREATE TABLE CA_HIGHR_ED_FUNC_T(
        FIN_HGH_ED_FUNC_CD             VARCHAR2(4) CONSTRAINT CA_HIGHR_ED_FUNC_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CA_HIGHR_ED_FUNC_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CA_HIGHR_ED_FUNC_TN3 NOT NULL,
        FIN_HGH_ED_FUNC_NM             VARCHAR2(40),
        FIN_UBO_FUNC_CD                VARCHAR2(3),
        FIN_AICPA_FUNC_CD              VARCHAR2(3),
        FIN_FED_FUNC_CD                VARCHAR2(3),
     CONSTRAINT CA_HIGHR_ED_FUNC_TP1 PRIMARY KEY (
        FIN_HGH_ED_FUNC_CD),
     CONSTRAINT CA_HIGHR_ED_FUNC_TC0 UNIQUE (OBJ_ID)
)
/
