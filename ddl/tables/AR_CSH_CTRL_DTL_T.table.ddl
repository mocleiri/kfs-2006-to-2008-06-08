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
CREATE TABLE AR_CSH_CTRL_DTL_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT AR_CSH_CTRL_DTL_TN1 NOT NULL,
        FDOC_REF_NBR                   VARCHAR2(14) CONSTRAINT AR_CSH_CTRL_DTL_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AR_CSH_CTRL_DTL_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AR_CSH_CTRL_DTL_TN4 NOT NULL,
        CUST_PMT_MEDIUM_ID             VARCHAR2(30),
        FDOC_LINE_AMT                  NUMBER(19, 2),
        CUST_PMT_DESC                  VARCHAR2(60),
        CUST_NBR                       VARCHAR2(9),
        CUST_PMT_DT                    DATE,
     CONSTRAINT AR_CSH_CTRL_DTL_TP1 PRIMARY KEY (
        FDOC_NBR,
        FDOC_REF_NBR),
     CONSTRAINT AR_CSH_CTRL_DTL_TC0 UNIQUE (OBJ_ID)
)
/
