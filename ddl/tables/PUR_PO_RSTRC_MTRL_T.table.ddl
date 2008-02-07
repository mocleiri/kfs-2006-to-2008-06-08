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
CREATE TABLE PUR_PO_RSTRC_MTRL_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT PUR_PO_RSTRC_MTRL_TN1 NOT NULL,
        RSTRC_MTRL_CD                  VARCHAR2(4) CONSTRAINT PUR_PO_RSTRC_MTRL_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_PO_RSTRC_MTRL_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_PO_RSTRC_MTRL_TN4 NOT NULL,
        REQS_ID                        NUMBER(8) CONSTRAINT PUR_PO_RSTRC_MTRL_TN5 NOT NULL,
     CONSTRAINT PUR_PO_RSTRC_MTRL_TP1 PRIMARY KEY (
        FDOC_NBR,
        RSTRC_MTRL_CD),
     CONSTRAINT PUR_PO_RSTRC_MTRL_TC0 UNIQUE (OBJ_ID)
)	
/
