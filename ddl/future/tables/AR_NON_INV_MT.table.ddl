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
        WHERE table_name = 'AR_NON_INV_MT';
        
        IF obj_count > 0 THEN
                EXECUTE IMMEDIATE('drop table AR_NON_INV_MT');
        END IF;
END;
/

/* create the table */
CREATE TABLE AR_NON_INV_MT(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT AR_NON_INV_MTN1 NOT NULL,
        FDOC_LINE_NBR                  NUMBER(7) CONSTRAINT AR_NON_INV_MTN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AR_NON_INV_MTN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AR_NON_INV_MTN4 NOT NULL,
        FDOC_POST_YR                   NUMBER(4),
        FIN_COA_CD                     VARCHAR2(2),
        ACCOUNT_NBR                    VARCHAR2(7),
        SUB_ACCT_NBR                   VARCHAR2(5),
        FIN_OBJECT_CD                  VARCHAR2(4),
        FIN_SUB_OBJ_CD                 VARCHAR2(3),
        PROJECT_CD                     VARCHAR2(10),
        FDOC_LINE_AMT                  NUMBER(19, 2),
        FDOC_OVERRIDE_CD               VARCHAR2(1),
     CONSTRAINT AR_NON_INV_MTP1 PRIMARY KEY (
        FDOC_NBR,
        FDOC_LINE_NBR),
     CONSTRAINT AR_NON_INV_MTC0 UNIQUE (OBJ_ID)
)
/
