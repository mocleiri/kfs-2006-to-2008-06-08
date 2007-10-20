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
        WHERE table_name = 'FP_INTERIM2_CONS_MT';

        IF obj_count > 0 THEN
                EXECUTE IMMEDIATE('drop table FP_INTERIM2_CONS_MT');
        END IF;
END;
/

CREATE TABLE FP_INTERIM2_CONS_MT(
        SESID                          VARCHAR2(36) CONSTRAINT FP_INTERIM2_CONS_MTN1 NOT NULL,
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT FP_INTERIM2_CONS_MTN2 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT FP_INTERIM2_CONS_MTN3 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT FP_INTERIM2_CONS_MTN4 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT FP_INTERIM2_CONS_MTN5 NOT NULL,
        FIN_OBJECT_CD                  VARCHAR2(4) CONSTRAINT FP_INTERIM2_CONS_MTN6 NOT NULL,
        FIN_SUB_OBJ_CD                 VARCHAR2(3) CONSTRAINT FP_INTERIM2_CONS_MTN7 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT FP_INTERIM2_CONS_MTN8 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_INTERIM2_CONS_MTN9 NOT NULL,
        CURR_BDLN_BAL_AMT              NUMBER(19, 2),
        ACLN_ACTLS_BAL_AMT             NUMBER(19, 2),
        ACLN_ENCUM_BAL_AMT             NUMBER(19, 2),
        TIMESTAMP                      DATE,
		FIN_REPORT_SORT_CD             VARCHAR2(1),
        FIN_OBJ_TYP_CD                 VARCHAR2(2),	
        CONS_FIN_REPORT_SORT_CD        VARCHAR2(2),
        FIN_CONS_OBJ_CD                VARCHAR2(4),
CONSTRAINT FP_INTERIM2_CONS_MTP1 PRIMARY KEY (
        SESID,
        UNIV_FISCAL_YR,
        FIN_COA_CD,
        ACCOUNT_NBR,
        SUB_ACCT_NBR,
        FIN_OBJECT_CD,
        FIN_SUB_OBJ_CD),
CONSTRAINT FP_INTERIM2_CONS_MTC0 UNIQUE (OBJ_ID)
)
/
