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
CREATE TABLE LD_EXP_TRNFR_FRM_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT LD_EXP_TRNFR_FRM_TN1 NOT NULL,
        FDOC_LINE_NBR                  NUMBER(7) CONSTRAINT LD_EXP_TRNFR_FRM_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT LD_EXP_TRNFR_FRM_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_EXP_TRNFR_FRM_TN4 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2),
        ACCOUNT_NBR                    VARCHAR2(7),
        FDOC_POST_YR                   NUMBER(4),
        FIN_OBJECT_CD                  VARCHAR2(4),
        FIN_BALANCE_TYP_CD             VARCHAR2(2),
        FDOC_LINE_AMT                  NUMBER(19, 2),
        SUB_ACCT_NBR                   VARCHAR2(5),
        FIN_SUB_OBJ_CD                 VARCHAR2(3),
        PROJECT_CD                     VARCHAR2(10),
        ORG_REFERENCE_ID               VARCHAR2(8),
        POSITION_NBR                   VARCHAR2(8),
        PYRL_TOTAL_HR                  NUMBER,
        FDOC_OVERRIDE_CD               VARCHAR2(1),
        PYRL_DT_FSCL_YR                NUMBER(4),
        PYRL_DT_FSCLPRD_CD             VARCHAR2(2),
        EMPLID                         VARCHAR2(11),
     CONSTRAINT LD_EXP_TRNFR_FRM_TP1 PRIMARY KEY (
        FDOC_NBR,
        FDOC_LINE_NBR),
     CONSTRAINT LD_EXP_TRNFR_FRM_TC0 UNIQUE (OBJ_ID)
)
/
