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
CREATE TABLE LD_BCN_POS_INIT_MT(
        PERSON_UNVL_ID                 VARCHAR2(10) CONSTRAINT LD_BCN_POS_INIT_MTN1 NOT NULL,
        POSITION_NBR                   VARCHAR2(8) CONSTRAINT LD_BCN_POS_INIT_MTN2 NOT NULL,
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT LD_BCN_POS_INIT_MTN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT LD_BCN_POS_INIT_MTN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_BCN_POS_INIT_MTN5 NOT NULL,
        POS_EFFDT                      DATE,
        POS_EFF_STATUS                 VARCHAR2(1),
        POSN_STATUS                    VARCHAR2(1),
        BUDGETED_POSN                  VARCHAR2(1),
        CONFIDENTIAL_POSN              VARCHAR2(1),
        POS_STD_HRS_DFLT               NUMBER(6, 2),
        POS_REG_TEMP                   VARCHAR2(1),
        POS_FTE                        NUMBER,
        IU_NORM_WORK_MONTHS            NUMBER(2),
        IU_PAY_MONTHS                  NUMBER(2),
        POS_DESCR                      VARCHAR2(30),
        SETID_DEPT                     VARCHAR2(5),
        POS_DEPTID                     VARCHAR2(10),
        RC_CD                          VARCHAR2(2),
        POS_UNION_CD                   VARCHAR2(3),
        POS_SAL_PLAN_DFLT              VARCHAR2(4),
        POS_GRADE_DFLT                 VARCHAR2(3),
        SETID_JOBCODE                  VARCHAR2(5),
        JOBCODE                        VARCHAR2(6),
        JBCD_DESCR                     VARCHAR2(30),
        SETID_SALARY                   VARCHAR2(5),
        IU_DFLT_OBJ_CD                 VARCHAR2(4),
        IU_POSITION_TYPE               VARCHAR2(2),
     CONSTRAINT LD_BCN_POS_INIT_MTP1 PRIMARY KEY (
        PERSON_UNVL_ID,
        POSITION_NBR,
        UNIV_FISCAL_YR),
     CONSTRAINT LD_BCN_POS_INIT_MTC0 UNIQUE (OBJ_ID)
)
/
