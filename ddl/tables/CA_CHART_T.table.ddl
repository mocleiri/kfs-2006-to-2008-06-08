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
CREATE TABLE CA_CHART_T(
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT CA_CHART_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CA_CHART_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CA_CHART_TN3 NOT NULL,
        FIN_COA_MGRUNVL_ID             VARCHAR2(10),
        FIN_COA_DESC                   VARCHAR2(40),
        FIN_COA_ACTIVE_CD              VARCHAR2(1),
        FIN_CASH_OBJ_CD                VARCHAR2(4),
        FIN_AP_OBJ_CD                  VARCHAR2(4),
        INCBDGT_ELIMOBJ_CD             VARCHAR2(4),
        EXPBDGT_ELIMOBJ_CD             VARCHAR2(4),
        RPTS_TO_FIN_COA_CD             VARCHAR2(2),
        FIN_AR_OBJ_CD                  VARCHAR2(4),
        FIN_INT_ENC_OBJ_CD             VARCHAR2(4),
        FIN_EXT_ENC_OBJ_CD             VARCHAR2(4),
        FIN_PRE_ENC_OBJ_CD             VARCHAR2(4),
        ICR_INC_FIN_OBJ_CD             VARCHAR2(4),
        ICR_EXP_FIN_OBJ_CD             VARCHAR2(4),
        FND_BAL_OBJ_CD                 VARCHAR2(4),
     CONSTRAINT CA_CHART_TP1 PRIMARY KEY (
        FIN_COA_CD),
     CONSTRAINT CA_CHART_TC0 UNIQUE (OBJ_ID)
)
/
