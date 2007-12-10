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
DECLARE
  obj_count INTEGER;

BEGIN
        /* drop any existing table */
        SELECT count(*) into obj_count
        FROM user_tables
        WHERE table_name = 'LD_BCN_AF_LOAD01_MT';

        IF obj_count > 0 THEN
                EXECUTE IMMEDIATE('drop table LD_BCN_AF_LOAD01_MT');
        END IF;
END;
/

/* create the table */
CREATE TABLE LD_BCN_AF_LOAD01_MT(
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT LD_BCN_AF_LOAD01_MTN1 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT LD_BCN_AF_LOAD01_MTN2 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT LD_BCN_AF_LOAD01_MTN3 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT LD_BCN_AF_LOAD01_MTN4 NOT NULL,
        FIN_OBJECT_CD                  VARCHAR2(4) CONSTRAINT LD_BCN_AF_LOAD01_MTN5 NOT NULL,
        FIN_SUB_OBJ_CD                 VARCHAR2(3) CONSTRAINT LD_BCN_AF_LOAD01_MTN6 NOT NULL,
        POSITION_NBR                   VARCHAR2(8) CONSTRAINT LD_BCN_AF_LOAD01_MTN7 NOT NULL,
        EMPLID                         VARCHAR2(11) CONSTRAINT LD_BCN_AF_LOAD01_MTN8 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT LD_BCN_AF_LOAD01_MTN9 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_BCN_AF_LOAD01_MTN10 NOT NULL,
        APPT_FND_DUR_CD                VARCHAR2(4),
        APPT_RQST_CSF_AMT              NUMBER(19, 2),
        APPT_RQCSF_FTE_QTY             NUMBER,
        APPT_RQCSF_TM_PCT              NUMBER,
        APPT_TOT_INTND_AMT             NUMBER(19, 2),
        APPT_TOTINTFTE_QTY             NUMBER,
        APPT_RQST_AMT                  NUMBER(19, 2),
        APPT_RQST_TM_PCT               NUMBER,
        APPT_RQST_FTE_QTY              NUMBER,
        APPT_RQST_PAY_RT               NUMBER,
        APPT_FND_DLT_CD                VARCHAR2(1),
        APPT_FND_MO                    NUMBER(2),
        NEW_FIN_COA_CD                 VARCHAR2(2),
        NEW_ACCOUNT_NBR                VARCHAR2(7),
     CONSTRAINT LD_BCN_AF_LOAD01_MTP1 PRIMARY KEY (
        UNIV_FISCAL_YR,
        FIN_COA_CD,
        ACCOUNT_NBR,
        SUB_ACCT_NBR,
        FIN_OBJECT_CD,
        FIN_SUB_OBJ_CD,
        POSITION_NBR,
        EMPLID),
     CONSTRAINT LD_BCN_AF_LOAD01_MTC0 UNIQUE (OBJ_ID)
)
/
