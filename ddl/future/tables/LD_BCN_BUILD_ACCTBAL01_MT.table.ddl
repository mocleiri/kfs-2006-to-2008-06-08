/*
 * Copyright 2006 The Kuali Foundation.
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
DECLARE
  obj_count INTEGER;

BEGIN
    /* drop any existing table */
    SELECT count(*) into obj_count
    FROM user_tables
    WHERE table_name = 'LD_BCN_BUILD_ACCTBAL01_MT';

    IF obj_count > 0 THEN
        EXECUTE IMMEDIATE('drop table LD_BCN_BUILD_ACCTBAL01_MT');
    END IF;
END;
/

/* create the table */
CREATE TABLE LD_BCN_BUILD_ACCTBAL01_MT(
        SESID                          VARCHAR2(36) CONSTRAINT LD_BCN_BUILD_ACCTBAL01_MTN1 NOT NULL,
        ORG_FIN_COA_CD                 VARCHAR2(2) CONSTRAINT LD_BCN_BUILD_ACCTBAL01_MTN2 NOT NULL,
        ORG_CD                         VARCHAR2(4) CONSTRAINT LD_BCN_BUILD_ACCTBAL01_MTN3 NOT NULL,
        SUB_FUND_GRP_CD                VARCHAR2(6) CONSTRAINT LD_BCN_BUILD_ACCTBAL01_MTN4 NOT NULL,
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT LD_BCN_BUILD_ACCTBAL01_MTN5 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT LD_BCN_BUILD_ACCTBAL01_MTN6 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT LD_BCN_BUILD_ACCTBAL01_MTN7 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT LD_BCN_BUILD_ACCTBAL01_MTN8 NOT NULL,
        INC_EXP_CD                     VARCHAR2(1) CONSTRAINT LD_BCN_BUILD_ACCTBAL01_MTN9 NOT NULL,
        FIN_CONS_SORT_CD               VARCHAR2(2) CONSTRAINT LD_BCN_BUILD_ACCTBAL01_MTN10 NOT NULL,
        FIN_LEVEL_SORT_CD              VARCHAR2(2) CONSTRAINT LD_BCN_BUILD_ACCTBAL01_MTN11 NOT NULL,
        FIN_OBJECT_CD                  VARCHAR2(4) CONSTRAINT LD_BCN_BUILD_ACCTBAL01_MTN12 NOT NULL,
        FIN_SUB_OBJ_CD                 VARCHAR2(3) CONSTRAINT LD_BCN_BUILD_ACCTBAL01_MTN13 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT LD_BCN_BUILD_ACCTBAL01_MTN14 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_BCN_BUILD_ACCTBAL01_MTN15 NOT NULL,
        FIN_OBJ_LEVEL_CD               VARCHAR2(4),
        APPT_RQST_FTE_QTY              NUMBER,
        APPT_RQCSF_FTE_QTY             NUMBER,
        POS_CSF_FTE_QTY                NUMBER,
        FIN_BEG_BAL_LN_AMT             NUMBER(19, 2),
        ACLN_ANNL_BAL_AMT              NUMBER(19, 2),
        POS_CSF_LV_FTE_QTY             NUMBER,
     CONSTRAINT LD_BCN_BUILD_ACCTBAL01_MTP1 PRIMARY KEY (
        SESID,
        ORG_FIN_COA_CD,
        ORG_CD,
        SUB_FUND_GRP_CD,
        UNIV_FISCAL_YR,
        FIN_COA_CD,
        ACCOUNT_NBR,
        SUB_ACCT_NBR,
        INC_EXP_CD,
        FIN_CONS_SORT_CD,
        FIN_LEVEL_SORT_CD,
        FIN_OBJECT_CD,
        FIN_SUB_OBJ_CD),
     CONSTRAINT LD_BCN_BUILD_ACCTBAL01_MTC0 UNIQUE (OBJ_ID)
)
/
