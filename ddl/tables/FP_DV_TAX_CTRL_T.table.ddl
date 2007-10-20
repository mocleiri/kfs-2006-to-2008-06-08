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
CREATE TABLE FP_DV_TAX_CTRL_T(
        DV_PAYEE_TXCTRL_CD             VARCHAR2(1) CONSTRAINT FP_DV_TAX_CTRL_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT FP_DV_TAX_CTRL_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_DV_TAX_CTRL_TN3 NOT NULL,
        DV_TAX_CTRL_NM                 VARCHAR2(25),
     CONSTRAINT FP_DV_TAX_CTRL_TP1 PRIMARY KEY (
        DV_PAYEE_TXCTRL_CD),
     CONSTRAINT FP_DV_TAX_CTRL_TC0 UNIQUE (OBJ_ID)
)
/
