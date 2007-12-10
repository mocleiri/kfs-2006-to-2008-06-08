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
CREATE TABLE LD_BCN_RQST_MT(
        PERSON_UNVL_ID                 VARCHAR2(10) CONSTRAINT LD_BCN_RQST_MTN1 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT LD_BCN_RQST_MTN2 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT LD_BCN_RQST_MTN3 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT LD_BCN_RQST_MTN4 NOT NULL,
        FIN_OBJECT_CD                  VARCHAR2(4) CONSTRAINT LD_BCN_RQST_MTN5 NOT NULL,
        FIN_SUB_OBJ_CD                 VARCHAR2(3) CONSTRAINT LD_BCN_RQST_MTN6 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT LD_BCN_RQST_MTN7 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_BCN_RQST_MTN8 NOT NULL,
        FIN_OBJ_TYP_CD                 VARCHAR2(2),
        ACLN_ANNL_BAL_AMT              NUMBER(19, 2),
        RQST_UPDT_ERR_CD               VARCHAR2(4),
        FDOC_LN_MO1_AMT                NUMBER(19, 2),
        FDOC_LN_MO2_AMT                NUMBER(19, 2),
        FDOC_LN_MO3_AMT                NUMBER(19, 2),
        FDOC_LN_MO4_AMT                NUMBER(19, 2),
        FDOC_LN_MO5_AMT                NUMBER(19, 2),
        FDOC_LN_MO6_AMT                NUMBER(19, 2),
        FDOC_LN_MO7_AMT                NUMBER(19, 2),
        FDOC_LN_MO8_AMT                NUMBER(19, 2),
        FDOC_LN_MO9_AMT                NUMBER(19, 2),
        FDOC_LN_MO10_AMT               NUMBER(19, 2),
        FDOC_LN_MO11_AMT               NUMBER(19, 2),
        FDOC_LN_MO12_AMT               NUMBER(19, 2),
     CONSTRAINT LD_BCN_RQST_MTP1 PRIMARY KEY (
        PERSON_UNVL_ID,
        FIN_COA_CD,
        ACCOUNT_NBR,
        SUB_ACCT_NBR,
        FIN_OBJECT_CD,
        FIN_SUB_OBJ_CD),
     CONSTRAINT LD_BCN_RQST_MTC0 UNIQUE (OBJ_ID)
)
/
