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
CREATE TABLE FP_SB_CTRL_T(
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT FP_SB_CTRL_TN1 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT FP_SB_CTRL_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_SB_CTRL_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_SB_CTRL_TN4 NOT NULL,
        WRKGRP_NM                      VARCHAR2(70),
     CONSTRAINT FP_SB_CTRL_TP1 PRIMARY KEY (
        FIN_COA_CD,
        ACCOUNT_NBR),
     CONSTRAINT FP_SB_CTRL_TC0 UNIQUE (OBJ_ID)
)
/
