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
CREATE TABLE ER_AGNCY_EXTNS_T(
        CG_AGNCY_NBR                   VARCHAR2(5) CONSTRAINT ER_AGNCY_EXTNS_TN1 NOT NULL,
        VER_NBR                        NUMBER(8,0) DEFAULT 1,
        AGNCY_MOD_IND                  VARCHAR2(1),
        BDGT_MOD_INCR_AMT              NUMBER(6,0),
        BDGT_PRD_MAX_AMT               NUMBER(7,0),
        AGNCY_NSF_OUT_IND              VARCHAR2(1),
     CONSTRAINT ER_AGNCY_EXTNS_TP1 PRIMARY KEY (
        CG_AGNCY_NBR)
)
/

