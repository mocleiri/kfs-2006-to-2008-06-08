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
CREATE TABLE LD_BCN_AF_LOAD02_MT
AS
        (SELECT C.FIN_COA_CD,
                C.ACCOUNT_NBR,
                C.OBJ_ID,
                C.VER_NBR,
                C.CONT_FIN_COA_CD, 
                C.CONT_ACCOUNT_NBR
           FROM CA_ACCOUNT_T C
           WHERE 1=2)
/
ALTER TABLE LD_BCN_AF_LOAD02_MT ADD CONSTRAINT LD_BCN_AF_LOAD02_MTP1 PRIMARY KEY (
        FIN_COA_CD,
        ACCOUNT_NBR)
/
ALTER TABLE LD_BCN_AF_LOAD02_MT ADD CONSTRAINT LD_BCN_AF_LOAD02_MTC0 UNIQUE (OBJ_ID)
/
ALTER TABLE LD_BCN_AF_LOAD02_MT MODIFY (OBJ_ID DEFAULT SYS_GUID()); 
ALTER TABLE LD_BCN_AF_LOAD02_MT MODIFY (VER_NBR DEFAULT 1);
