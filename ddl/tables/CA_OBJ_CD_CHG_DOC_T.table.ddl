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
CREATE TABLE CA_OBJ_CD_CHG_DOC_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT CA_OBJ_CD_CHG_DOC_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CA_OBJ_CD_CHG_DOC_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CA_OBJ_CD_CHG_DOC_TN3 NOT NULL,
        UNIV_FISCAL_YR                 NUMBER(4),
        FIN_COA_CD                     VARCHAR2(2),
        FIN_OBJECT_CD                  VARCHAR2(4),
        FIN_OBJ_CD_NM                  VARCHAR2(40),
        FIN_OBJ_CD_SHRT_NM             VARCHAR2(12),
        FIN_OBJ_LEVEL_CD               VARCHAR2(4),
        RPTS_TO_FIN_COA_CD             VARCHAR2(2),
        RPTS_TO_FIN_OBJ_CD             VARCHAR2(4),
        FIN_OBJ_TYP_CD                 VARCHAR2(2),
        FIN_OBJ_SUB_TYP_CD             VARCHAR2(2),
        HIST_FIN_OBJECT_CD             VARCHAR2(4),
        FIN_OBJ_ACTIVE_CD              VARCHAR2(1),
        FOBJ_BDGT_AGGR_CD              VARCHAR2(1),
        FOBJ_MNXFR_ELIM_CD             VARCHAR2(1),
        FIN_FED_FUNDED_CD              VARCHAR2(1),
        NXT_YR_FIN_OBJ_CD              VARCHAR2(4),
     CONSTRAINT CA_OBJ_CD_CHG_DOC_TP1 PRIMARY KEY (
        FDOC_NBR),
     CONSTRAINT CA_OBJ_CD_CHG_DOC_TC0 UNIQUE (OBJ_ID)
)
/
