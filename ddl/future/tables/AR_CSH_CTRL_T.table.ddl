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
CREATE TABLE AR_CSH_CTRL_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT AR_CSH_CTRL_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AR_CSH_CTRL_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AR_CSH_CTRL_TN3 NOT NULL,
        FDOC_REF_NBR                   VARCHAR2(14),
        UNIV_FISCAL_YR                 NUMBER(4),
        UNIV_FISCAL_PRD_CD             VARCHAR2(2),
        CUST_PMT_MEDIUM_CD             VARCHAR2(2),
        AR_CSHCTRL_TOT_AMT             NUMBER(19, 2),
     CONSTRAINT AR_CSH_CTRL_TP1 PRIMARY KEY (
        FDOC_NBR),
     CONSTRAINT AR_CSH_CTRL_TC0 UNIQUE (OBJ_ID)
)
/
