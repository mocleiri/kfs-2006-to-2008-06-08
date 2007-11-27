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
        WHERE table_name = 'LD_BCN_AF_LOAD02_MT';

        IF obj_count > 0 THEN
                EXECUTE IMMEDIATE('drop table LD_BCN_AF_LOAD02_MT');
        END IF;
END;
/

/* create the table */
CREATE TABLE LD_BCN_AF_LOAD02_MT(
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT LD_BCN_AF_LOAD02_MTN1 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT LD_BCN_AF_LOAD02_MTN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT LD_BCN_AF_LOAD02_MTN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_BCN_AF_LOAD02_MTN4 NOT NULL,
        CONT_FIN_COA_CD                VARCHAR2(2),
        CONT_ACCOUNT_NBR               VARCHAR2(7),
     CONSTRAINT LD_BCN_AF_LOAD02_MTP1 PRIMARY KEY (
        FIN_COA_CD,
        ACCOUNT_NBR),
     CONSTRAINT LD_BCN_AF_LOAD02_MTC0 UNIQUE (OBJ_ID)
)
/
