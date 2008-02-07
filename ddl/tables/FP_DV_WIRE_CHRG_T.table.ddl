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
CREATE TABLE FP_DV_WIRE_CHRG_T(
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT FP_DV_WIRE_CHRG_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_DV_WIRE_CHRG_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_DV_WIRE_CHRG_TN3 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2),
        ACCOUNT_NBR                    VARCHAR2(7),
        INC_FIN_OBJ_CD                 VARCHAR2(4),
        EXP_FIN_OBJ_CD                 VARCHAR2(4),
        DV_DOMSTC_CHG_AMT              NUMBER(19, 2),
        DV_FRGN_CHRG_AMT               NUMBER(19, 2),
     CONSTRAINT FP_DV_WIRE_CHRG_TP1 PRIMARY KEY (
        UNIV_FISCAL_YR),
     CONSTRAINT FP_DV_WIRE_CHRG_TC0 UNIQUE (OBJ_ID)
)
/
